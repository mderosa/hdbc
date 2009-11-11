package com.googlecode.hdbc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

@Controller
public class ExperimentsController {
	private final IExperimentDao dao;
	private final Validator validator;
	
	public ExperimentsController(final IExperimentDao exprmntDao, final Validator exprmntValidator) {
		dao = exprmntDao;
		validator = exprmntValidator;
	}

	@RequestMapping(value = "/experiments/experiment", method = RequestMethod.GET)
	public final String getExperiment(@RequestParam("uid") final long uid, final Model model) {
		final IExperiment experiment = dao.find(uid);
		model.addAttribute(ModelAttributes.OBJECT, experiment.getData());
		return "experiments_experiment";
	}
	
	@RequestMapping(value = "/experiments", method = RequestMethod.POST)
	public final ModelAndView createExperiment(
			@ModelAttribute(ModelAttributes.OBJECT) final ExperimentData data, 
			final BindingResult bindResults,
			final HttpServletRequest req) {
		validator.validate(data, bindResults);
		
		if (!bindResults.hasErrors()) {
			dao.insert(new Experiment(data));
		}
		
		final ModelAndView mv = new ModelAndView("experiments");
		return mv;
	}
	
	@RequestMapping(value = "/experiments/experiment", method = RequestMethod.PUT)
	public final String updateExperiment(@ModelAttribute(ModelAttributes.OBJECT) final ExperimentData data, final BindingResult result) {
		validator.validate(data, result);
		
		if (!result.hasErrors()) {
			dao.update(new Experiment(data));
		}
		return "experiments_experiment";
	}

}
