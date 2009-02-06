package com.googlecode.hdbc.dbdiff.utilities;

public class Pair<A, B> {
	private final A left;
	private final B right;

	public Pair(final A leftSide, final B rightSide) {
		left = leftSide;
		right = rightSide;
	}

	public final A getLeft() {
		return left;
	}

	public final B getRight() {
		return right;
	}
}
