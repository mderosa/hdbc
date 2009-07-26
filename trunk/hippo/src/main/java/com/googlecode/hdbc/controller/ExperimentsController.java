package com.googlecode.hdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
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

	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.GET)
	public String getExperiment(@PathVariable("uid") final long uid, final Model model) {
		IExperiment experiment = dao.find(uid);
		model.addAttribute(COMMAND_NAME, experiment.getData());
		return EXPERIMENT_FORM;
	}
	
	@RequestMapping(value="/experiments", method=RequestMethod.POST)
	public ModelAndView createExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data, final BindingResult bindResults) {
		validator.validate(data, bindResults);

		if (!bindResults.hasErrors()) {
			dao.insert(new Experiment(data));
		}
		
		ModelAndView mv = new ModelAndView("defaultExtJsonView");
		mv.addObject("errors", bindResults);
		return mv;
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.PUT)
	public String updateExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data, final BindingResult result) {
		validator.validate(data, result);
		
		if (!result.hasErrors()) {
			dao.update(new Experiment(data));
		}
		return EXPERIMENT_FORM;
	}

}
