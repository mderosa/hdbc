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
 * (3) 			| map(keys E ({required fields} + {!fields}) = DataBinder
 * 
 */
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
	
	/**
	 * Test binding all fields - condition (1) (above spec)
	 */
	@Test
	public void testBinding1() {
		DataBinder actual = ctlr.bind(req);
		assertNotNull(actual);
		ExperimentData data = (ExperimentData) actual.getTarget();
		assertEquals("some name", data.getTitle());
		assertEquals(23, data.getUid().longValue());
	}
	
	/**
	 * Test binding condition 2 (above spec)
	 */
	@Test
	public final void testBinding2() {
		req.removeParameter("title");
		DataBinder actual = ctlr.bind(req);
		int count = actual.getBindingResult().getErrorCount();
		assertEquals(1, count);
	}
	
	/**
	 * Test binding condition 2 for when name exists as a key but empty(value) == true
	 */
	@Test
	public final void testBinding2EmptyValue() {
		req.removeParameter("title");
		DataBinder actual = ctlr.bind(req);
		int count = actual.getBindingResult().getErrorCount();
		assertEquals(1, count);
	}
	
	/**
	 * Test binding condition 3 (above spec) 
	 */
	@Test
	public final void testBinding3() {
		req.addParameter("submit", "submit");
		DataBinder actual = ctlr.bind(req);
		int count = actual.getBindingResult().getErrorCount();
		assertEquals(0, count);
	}
	
	/**
	 * Test that the bound fields are trimmed
	 */
	@Test
	public final void testBoundFieldsHaveTrimmedValues() {
		req.addParameter("conclusion", "  conclusion   ");
		DataBinder actual = ctlr.bind(req);
		ExperimentData data = (ExperimentData) actual.getTarget();
		assertEquals("conclusion", data.getConclusion());
	}
}