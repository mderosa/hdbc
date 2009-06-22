package com.googlecode.hdbc.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;

import com.googlecode.hdbc.model.record.ExperimentData;

/**
 * Experiment Controller tests
 * <p>
 * binding specification
 * bind :: Request -> DataBinder
 * (1) bind map | map(keys E {fields}) = DataBinder
 * (2) 			| map(keys E ({fields} - {required fields}) = DataBinder(error)
 * (3) 			| map(keys E ({required fields} + {!fields}) = DataBinder(error)
 * 
 */
public class ExperimentsControllerTest {
	MockHttpServletRequest req;
	
	@Before
	public final void setUp() {
		req = new MockHttpServletRequest();
		req.setParameter("uid", "23");
		req.setParameter("name", "some name");
		req.setParameter("purpose", "to test values");
		req.setParameter("method", "very good method");
	}
	
	/**
	 * Test binding all fields - condition (1)
	 */
	@Test
	public void testBinding1() {
		ExperimentsController ctlr = new ExperimentsController(null);
		DataBinder actual = ctlr.bind(req);
		assertNotNull(actual);
		ExperimentData data = (ExperimentData) actual.getTarget();
		assertEquals("some name", data.getName());
		assertEquals(23, data.getUid());
	}
}