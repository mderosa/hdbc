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
public class ExperimentController {
	IExperimentDao dao;
	
	public ExperimentController(IExperimentDao exprmntDao) {
		dao = exprmntDao;
	}

	@RequestMapping(value="/experiment", method=RequestMethod.GET)
	public String newExperiment(Model model) {
		model.addAttribute("experiment", new ExperimentData());
		return "experiment";
	}
	
	@RequestMapping(value="/experiment/{uid}", method=RequestMethod.GET)
	public String get(@PathVariable("uid") String uid, Model model) {
		IExperiment experiment = dao.find(10);
		model.addAttribute("experiment", experiment.getData());
		return "experiment";
	}
	
	@RequestMapping(value="/experiment/{uid}", method=RequestMethod.POST)
	void post(@PathVariable("uid") long uid) {
		
	}

	@RequestMapping(value="/experiment/{uid}", method=RequestMethod.PUT)
	void put(@PathVariable("uid") long uid) {
		
	}
}
