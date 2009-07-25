package com.googlecode.hdbc.model.validator;

import static org.junit.Assert.*;

import java.util.Locale;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;


import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataValidatorTest {
	private ExperimentData data;
	private Validator<ExperimentData> validator;
	
	@Before
	public final void setUp() {
		data = new ExperimentData(-1L, 
				"title that is way to long aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
				"purpose");
		StaticMessageSource msgs = new StaticMessageSource();
		msgs.addMessage(DataValidator.ValidationErrorCd.NUMBER_BELOW_MINIMUM.toString(), 
				Locale.CHINA, "the length {0} is too short");
		msgs.addMessage(DataValidator.ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString(), 
				Locale.CHINA, "{0} is too long");
		validator = new ExperimentDataValidator(msgs);
	}
	
	/**
	 * Test a item with a bad uid and title
	 */
	@Test
	public final void testBadEquipmentData() {
		JSONObject json = validator.validate(data, Locale.CHINA);
		assertEquals(2, json.getJSONArray("errors").size());
	}
	
	/**
	 * Test a minimally correct EquipmentData
	 */
	@Test
	public final void testMinimalCorrectObject() {
		data.setUid(null);
		data.setTitle("aTitle");
		Errors errors = new BeanPropertyBindingResult(data, "experimentData");
		validator.validate(data, Locale.CHINA);
		assertEquals(0, errors.getErrorCount());
	}
	
}
