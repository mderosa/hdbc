package com.googlecode.hdbc.dbdiff.utilities;

public interface Condition<T> {
	boolean isSatisfiedBy(T obj);
}
