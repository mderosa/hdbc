package com.googlecode.hdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

@Controller
public class ExperimentsController {
	IExperimentDao dao;
	
	public ExperimentsController(IExperimentDao exprmntDao) {
		dao = exprmntDao;
	}

	@RequestMapping(value="/experiments", method=RequestMethod.GET)
	public String newExperiment(Model model) {
		model.addAttribute("experiment", new ExperimentData());
		return "experiments";
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.GET)
	public String get(@PathVariable("uid") long uid, Model model) {
		IExperiment experiment = dao.find(uid);
		model.addAttribute("experiment", experiment.getData());
		return "experiments";
	}
	
	@RequestMapping(value="/experiments", method=RequestMethod.POST)
	void put() {
		
	}
	
	@RequestMapping(value="/experiments/{uid}", method=RequestMethod.PUT)
	void put(@PathVariable("uid") long uid) {
		
	}
}
