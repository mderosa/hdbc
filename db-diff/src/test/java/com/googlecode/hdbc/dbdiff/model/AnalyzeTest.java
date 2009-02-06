package com.googlecode.hdbc.dbdiff.model;

import java.util.HashSet;
import org.junit.Test;

import com.googlecode.hdbc.dbdiff.model.Analyze;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class AnalyzeTest {

	@Test
	public final void shouldCollectionCommonAndSpecificElements() {
		HashSet<Integer> one = new HashSet<Integer>();
		one.add(1);
		one.add(2);
		one.add(3);
		one.add(4);
		HashSet<Integer> two = new HashSet<Integer>();
		two.add(3);
		two.add(4);
		two.add(5);
		two.add(6);
		Analyze<Integer> actual = new Analyze<Integer>("id").collections(one, two);
		assertThat(actual.getCommon().size(), is(equalTo(2)));
		assertThat(actual.getLeftOnly().size(), is(equalTo(2)));
		assertThat(actual.getRightOnly().size(), is(equalTo(2)));
		assertThat(actual.areEqual(), is(equalTo(false)));
	}

	@Test
	public final void emptyInputsShouldNotCauseProblems() {
		HashSet<Integer> one = new HashSet<Integer>();
		HashSet<Integer> two = new HashSet<Integer>();
		boolean actual = new Analyze<Integer>("id").collections(one, two).areEqual();
		assertThat(actual, is(equalTo(true)));
	}
}
