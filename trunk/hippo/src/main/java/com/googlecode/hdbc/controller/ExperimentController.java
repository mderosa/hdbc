package com.googlecode.hdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.googlecode.hdbc.dao.IExperimentDao;
import com.googlecode.hdbc.model.IExperiment;

@Controller
@RequestMapping("/experiment/{uid}")
public class ExperimentController {
	IExperimentDao dao;
	
	public ExperimentController(IExperimentDao exprmntDao) {
		dao = exprmntDao;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String get(@PathVariable("uid") long uid, Model model) {
		IExperiment experiment = dao.find(uid);
		model.addAttribute("experiment", experiment);
		return "experiment/view";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	void post() {
		
	}

}
