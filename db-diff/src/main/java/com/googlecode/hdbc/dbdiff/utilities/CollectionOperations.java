package com.googlecode.hdbc.dbdiff.utilities;

import java.util.ArrayList;
import java.util.Collection;

public final class CollectionOperations {

	private CollectionOperations() {}

	@SuppressWarnings("unchecked")
	public static<T> Pair<Collection<T>, Collection<T>> span(
			final Condition<T> condition,
			final Collection<T> list) {
		Collection<T> accepted;
		Collection<T> rejected;
		try {
			accepted = list.getClass().newInstance();
			rejected = list.getClass().newInstance();
		} catch (InstantiationException e) {
			accepted = new ArrayList<T>();
			rejected = new ArrayList<T>();
		} catch (IllegalAccessException e) {
		    accepted = new ArrayList<T>();
            rejected = new ArrayList<T>();
		}

		for (T element : list) {
			if (condition.isSatisfiedBy(element)) {
				accepted.add(element);
			} else {
				rejected.add(element);
			}
		}
		return new Pair<Collection<T>, Collection<T>>(accepted, rejected);
	}
}
