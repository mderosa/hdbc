package com.googlecode.hdbc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

@Controller
public class ExperimentsController {
	public static final String COMMAND_NAME = "experiment";
	private final IExperimentDao dao;
	private final Validator validator;
	
	public ExperimentsController(final IExperimentDao exprmntDao, final Validator exprmntValidator) {
		dao = exprmntDao;
		validator = exprmntValidator;
	}

	@RequestMapping(value="/experiments", method=RequestMethod.GET)
	public String newExperiment(final Model model) {
		model.addAttribute(COMMAND_NAME, new ExperimentData());
		return "experiments";
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.GET)
	public String get(@PathVariable("uid") final long uid, final Model model) {
		IExperiment experiment = dao.find(uid);
		model.addAttribute(COMMAND_NAME, experiment.getData());
		return "experiments";
	}
	
	@RequestMapping(value="/experiments", method=RequestMethod.POST)
	public String create(final Model model, final HttpServletRequest req) {
		DataBinder binder = bind(req);
		ExperimentData data = (ExperimentData) binder.getTarget();
		validator.validate(data, binder.getBindingResult());
		
		String viewName = "experiments";
		model.addAttribute(COMMAND_NAME, data);
		if (binder.getBindingResult().getErrorCount() == 0) {
			dao.insert(new Experiment(data));
			viewName += "/" + data.getUid().toString();
		} else {
			model.addAttribute("errors", binder.getBindingResult());
		}
		return viewName;
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.PUT)
	void update(@PathVariable("uid") final long uid) {
		
	}
	
	protected final DataBinder bind(final HttpServletRequest req) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(new ExperimentData());
		binder.setRequiredFields(new String[] {"title", "purpose"});
		binder.bind(req);
		return binder;
	}
}
