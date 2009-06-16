package com.googlecode.hdbc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.googlecode.hdbc.model.Experiment;

@Controller
@RequestMapping("/experiments/*")
public class ExperimentsController {

	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public void common(HttpServletRequest req) {
		req.setAttribute("experiment", new Experiment("exp1", "to test stuff"));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String custom() {
		return "test";
	}
}
