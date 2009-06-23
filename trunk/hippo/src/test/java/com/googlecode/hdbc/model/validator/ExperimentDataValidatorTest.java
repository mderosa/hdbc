package com.googlecode.hdbc.model.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.hdbc.model.record.ExperimentData;

/**
 * Specification
 * <p>

 */
public class ExperimentDataValidatorTest {
	private ExperimentData data;
	
	@Before
	public final void setUp() {
		data = new ExperimentData(-1L, 
				"title that is way to long aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
				"purpose");
	}
	
	/**
	 * Test a item with a bad uid and title
	 */
	@Test
	public final void testBadEquipmentData() {
		Validator v = new ExperimentDataValidator();
		Errors errors = new BeanPropertyBindingResult(data, "experimentData");
		v.validate(data, errors);
		assertEquals(2, errors.getErrorCount());
	}
	
}
