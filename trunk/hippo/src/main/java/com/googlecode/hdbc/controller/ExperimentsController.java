package com.googlecode.hdbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.googlecode.hdbc.model.Experiment;

@Controller
@RequestMapping("/experiments/*")
public class ExperimentsController {

	@RequestMapping(value="index", method=RequestMethod.GET)
	public void index(Model model) {
		List<Experiment> experiments = new ArrayList<Experiment>();
		experiments.add(new Experiment("exp1", "to test stuff"));
		experiments.add(new Experiment("sign up experiment","to test ui elements for their influence on sign up"));
		model.addAttribute("experiments", experiments);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String home() {
		return "test";
	}
}
