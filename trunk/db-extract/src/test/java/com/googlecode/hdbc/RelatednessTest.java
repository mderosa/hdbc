package com.googlecode.hdbc;

import static org.junit.Assert.*;
import org.junit.Test;

public class RelatednessTest {

	@Test
	public final void testColumnRelatedness() {
		final int expected = 3;
		Relatedness rltness = new Relatedness();
		int actual = rltness.columnRelatedness("kitten", "sitting");
		assertEquals(expected, actual);
	}

	@Test
	public final void testColumnRelatedness2() {
		Relatedness rltness = new Relatedness();
		int actual = rltness.columnRelatedness("kitten", "tens");
		assertEquals(4, actual);
	}
}
