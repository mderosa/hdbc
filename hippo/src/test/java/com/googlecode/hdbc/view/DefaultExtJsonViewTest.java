package com.googlecode.hdbc.view;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Locale;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.support.StaticWebApplicationContext;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.validator.DataValidator;

public class DefaultExtJsonViewTest {
	private DefaultExtJsonView view;
	
	@Before
	public final void setUp() {
		view = new DefaultExtJsonView("application/json;charset=UTF-8", "UTF-8");
		StaticWebApplicationContext ctx = new StaticWebApplicationContext();
		ctx.addMessage(DataValidator.ValidationErrorCd.NUMBER_BELOW_MINIMUM.toString(), 
				Locale.getDefault(), "minimum length is {0}");
		ctx.refresh();
		view.setApplicationContext(ctx);
	}

	/**
	 * Check that we can form a standard ext form type response string
	 */
	@Test
	public final void testBuildJsonResponse() {
		Errors bindRslt = new BeanPropertyBindingResult(new ExperimentData(), ExperimentData.class.getName());
		bindRslt.rejectValue("uid", DataValidator.ValidationErrorCd.NUMBER_BELOW_MINIMUM.toString(), 
				new Object[] {5}, "na");
		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("errors", bindRslt);
		
		JSONObject json = view.buildJsonResponse(model, new MockHttpServletRequest(),
				new MockHttpServletResponse());
		String expected = "{\"success\":true" +
				",\"errors\":[{\"uid\":\"minimum length is 5\"}]}";
		assertEquals(expected, json.toString());
	}
	
	/**
	 * Test that a null error object doesnt cause an exception
	 */
	@Test
	public final void testBuildJsonResponeNoBindingErrors() {
		HashMap<String, Object> model = new HashMap<String, Object>();
		JSONObject json = view.buildJsonResponse(model,	new MockHttpServletRequest(),
				new MockHttpServletResponse());
		String expected = "{\"success\":true}";

		assertEquals(expected, json.toString());
	}
}
