package com.googlecode.hdbc.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.googlecode.hdbc.dao.IExperimentsDao;
import com.googlecode.hdbc.model.IExperiment;

@Controller
@RequestMapping("/experiments/lists/*")
public class ExperimentsListsController {
	private IExperimentsDao data;
	
	public ExperimentsListsController(IExperimentsDao dao) {
		data = dao;
	}

	@RequestMapping(value="active", method=RequestMethod.GET)
	public void active(Model model) {
		List<IExperiment> experiments = data.findActiveExperiments();
		model.addAttribute("experiments", experiments);
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public void search(Model model) {
		List<IExperiment> experiments = data.findActiveExperiments();
		model.addAttribute("experiments", experiments);
	}
}