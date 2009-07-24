package com.googlecode.hdbc.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.validator.Validator;

@Controller
public class ExperimentsController {
	public static final String COMMAND_NAME = "experiment";
	public static final String EXPERIMENT_FORM = "experiments";
	private final IExperimentDao dao;
	private final Validator<ExperimentData> validator;
	
	public ExperimentsController(final IExperimentDao exprmntDao, final Validator<ExperimentData> exprmntValidator) {
		dao = exprmntDao;
		validator = exprmntValidator;
	}

	@RequestMapping(value="/experiments", method=RequestMethod.GET)
	public String newExperiment(final Model model) {
		model.addAttribute(COMMAND_NAME, new ExperimentData());
		return EXPERIMENT_FORM;
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.GET)
	public String getExperiment(@PathVariable("uid") final long uid, final Model model) {
		IExperiment experiment = dao.find(uid);
		model.addAttribute(COMMAND_NAME, experiment.getData());
		return EXPERIMENT_FORM;
	}
	
	@RequestMapping(value="/experiments", method=RequestMethod.POST)
	public String createExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data) {
		JSONObject result = validator.validate(data);

		if (!result.containsKey("errors")) {
			dao.insert(new Experiment(data));
		} 
		
		result.put("success", true);
		return EXPERIMENT_FORM;
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.PUT)
	public String updateExperiment(@ModelAttribute(COMMAND_NAME) final ExperimentData data) {
		JSONObject result = validator.validate(data);
		
		if (!result.containsKey("errors")) {
			dao.update(new Experiment(data));
		}
		
		result.put("success", true);
		return EXPERIMENT_FORM;
	}

}
