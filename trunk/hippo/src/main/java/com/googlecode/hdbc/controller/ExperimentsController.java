package com.googlecode.hdbc.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.googlecode.hdbc.dao.IExperimentsDao;
import com.googlecode.hdbc.model.IExperiment;

@Controller
@RequestMapping("/experiments")
public class ExperimentsController {
	private IExperimentsDao data;
	
	public ExperimentsController(IExperimentsDao dao) {
		data = dao;
	}

	@RequestMapping(method=RequestMethod.GET)
	public void index(Model model) {
		List<IExperiment> experiments = data.findActiveExperiments();
		model.addAttribute("experiments", experiments);
	}
	
}
