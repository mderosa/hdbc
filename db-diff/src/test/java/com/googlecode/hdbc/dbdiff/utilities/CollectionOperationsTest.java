package com.googlecode.hdbc.dbdiff.utilities;

import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;

import com.googlecode.hdbc.dbdiff.utilities.CollectionOperations;
import com.googlecode.hdbc.dbdiff.utilities.Condition;
import com.googlecode.hdbc.dbdiff.utilities.Pair;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class CollectionOperationsTest {

	private class SatisfiesLessThanFour implements Condition<Integer> {
		public boolean isSatisfiedBy(final Integer number) {
			int n = number.intValue();
			return n < 4;
		}
	}

	@Test
	public void shouldSplitListInHalf() {
		HashSet<Integer> test = new HashSet<Integer>();
		test.add(new Integer(1));
		test.add(new Integer(2));
		test.add(new Integer(3));
		test.add(new Integer(4));
		test.add(new Integer(5));
		test.add(new Integer(6));
		Pair<Collection<Integer>, Collection<Integer>> actual = CollectionOperations.span(
				new SatisfiesLessThanFour(), test);
		assertThat(actual.getLeft().size(), is(equalTo(3)));
		assertThat(actual.getRight().size(), is(equalTo(3)));
	}
}
