package com.googlecode.hdbc.model.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataValidatorTest {
	private DataValidator validator;
	
	@Before
	public final void setUp() {
		validator = new ExperimentDataValidator(null);
	}

	/**
	 * A quick test to verify that out of range numbers are flagged.
	 */
	@Test
	public final void testOutOfRangeNumber() {
		assertTrue(validator.isNullOrGreaterThan(null, 5));
		assertFalse(validator.isNullOrGreaterThan(2L, 5));
	}
	
	/**
	 * A quick test to verify that out of range string are flagged
	 */
	@Test
	public final void testOutOfRangeString() {
		assertFalse(validator.isNullOrUptoLengthN("this", 3));
		assertTrue(validator.isNullOrUptoLengthN(null, 100));
	}
	
}
