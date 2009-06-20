package com.googlecode.hdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/experiments/search")
public class ExperimentsSearchController {

	@RequestMapping(method=RequestMethod.GET)
	void get() {
		
	}
	
}
