package com.googlecode.hdbc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.record.ExperimentData;

@Controller
public class ExperimentsController {
	public static final String COMMAND_NAME = "experiment";
	public static final String EXPERIMENT_FORM = "experiments";
	private final IExperimentDao dao;
	private final Validator validator;
	
	public ExperimentsController(final IExperimentDao exprmntDao, final Validator exprmntValidator) {
		dao = exprmntDao;
		validator = exprmntValidator;
	}

	/*@RequestMapping(value = "/experiments/{uid}", method = RequestMethod.GET)
	public final String getExperiment(@PathVariable("uid") final long uid, final Model model) {
		final IExperiment experiment = dao.find(uid);
		model.addAttribute(COMMAND_NAME, experiment.getData());
		return EXPERIMENT_FORM;
	}*/
	
	@RequestMapping(value = "/experiments", method = RequestMethod.POST)
	public final ModelAndView createExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data, final BindingResult bindResults,
			final HttpServletRequest req) {
		validator.validate(data, bindResults);
		
		if (!bindResults.hasErrors()) {
			dao.insert(new Experiment(data));
		}
		
		final ModelAndView mv = new ModelAndView("experimentsView");
		return mv;
	}
	
	@RequestMapping(value = "/experiments/{uid}", method = RequestMethod.PUT)
	public final String updateExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data, final BindingResult result) {
		validator.validate(data, result);
		
		if (!result.hasErrors()) {
			dao.update(new Experiment(data));
		}
		return EXPERIMENT_FORM;
	}

}
