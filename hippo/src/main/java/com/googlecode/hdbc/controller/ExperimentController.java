package com.googlecode.hdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/experiment/*")
public class ExperimentController {

	@RequestMapping(method=RequestMethod.GET)
	void get() {
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	void post() {
		
	}

}
