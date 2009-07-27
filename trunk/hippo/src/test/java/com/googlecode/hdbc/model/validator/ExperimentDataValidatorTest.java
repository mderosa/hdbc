package com.googlecode.hdbc.model.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataValidatorTest {
	private ExperimentData data;
	private Validator validator;
	
	@Before
	public final void setUp() {
		data = new ExperimentData(-1L, 
				"title that is way to long aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
				"purpose");
		validator = new ExperimentDataValidator();
	}
	
	/**
	 * Test a item with a bad uid and title
	 */
	@Test
	public final void testBadEquipmentData() {
		Errors errors = new BeanPropertyBindingResult(data, "experimentData");
		validator.validate(data, errors);
		assertEquals(2, errors.getErrorCount());
	}
	
	/**
	 * Test a minimally correct EquipmentData
	 */
	@Test
	public final void testMinimalCorrectObject() {
		data.setUid(null);
		data.setTitle("aTitle");
		Errors errors = new BeanPropertyBindingResult(data, "experimentData");
		validator.validate(data, errors);
		assertEquals(0, errors.getErrorCount());
	}
	
}
