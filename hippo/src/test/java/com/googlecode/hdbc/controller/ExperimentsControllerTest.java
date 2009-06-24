package com.googlecode.hdbc.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentsControllerTest {
	private MockHttpServletRequest req;
	private ExperimentsController ctlr;
	
	@Before
	public final void setUp() {
		req = new MockHttpServletRequest();
		req.setParameter("uid", "23");
		req.setParameter("title", "some name");
		req.setParameter("purpose", "to test values");
		req.setParameter("method", "very good method");
		
		ctlr = new ExperimentsController(null, null);
	}	

}