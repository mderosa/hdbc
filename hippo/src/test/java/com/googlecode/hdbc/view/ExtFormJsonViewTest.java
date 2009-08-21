package com.googlecode.hdbc.view;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Locale;
import net.sf.json.JSONObject;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.support.StaticWebApplicationContext;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.validator.DataValidator;
import com.googlecode.hdbc.view.policy.ICustomOutputPolicy;

@RunWith(JMock.class)
public class ExtFormJsonViewTest {
	private AbstractJsonView view;
	private JUnit4Mockery context = new JUnit4Mockery();
	private ICustomOutputPolicy policy;
	
	@Before
	public final void setUp() {
		policy = context.mock(ICustomOutputPolicy.class);
		
		view = new ExtFormJsonView("application/json;charset=UTF-8", "UTF-8", policy);
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
		String expected = "{\"success\":false" +
				",\"errors\":[{\"uid\":\"minimum length is 5\"}]}";
		assertEquals(expected, json.toString());
	}
	
	/**
	 * Test that a null error object doesnt cause an exception
	 */
	@Test
	public final void testBuildJsonResponeNoBindingErrors() {
		final HashMap<String, Object> model = new HashMap<String, Object>();
		context.checking(new Expectations(){{
			oneOf(policy).customOutput(model);
		}});
		
		JSONObject json = view.buildJsonResponse(model,	new MockHttpServletRequest(),
				new MockHttpServletResponse());
		String expected = "{\"success\":true}";

		assertEquals(expected, json.toString());
	}
	
	@Test
	public final void testBehaviorOfAccumulate() {
		JSONObject dest = new JSONObject();
		JSONObject source = new JSONObject();
		source.put("one", 1);
		source.put("two", 2);
		dest.accumulateAll(source);
		
		assertEquals(1, dest.get("one"));
		assertEquals(2, dest.get("two"));
	}
	
	@Test
	public final void testBehaviorOfAccumulate2() {
		JSONObject dest = new JSONObject();
		dest.put("one", "1");
		
		JSONObject temp = new JSONObject();
		temp.put("A", "Aye");
		temp.put("B", "Bee");
		JSONObject source = new JSONObject();
		source.put("Alphabet", temp);
		
		dest.accumulateAll(source);
		
		Object unwind = dest.get("Alphabet");
		assertNotNull(unwind);
		assertEquals("Bee", ((JSONObject) unwind).get("B"));
	}
}
