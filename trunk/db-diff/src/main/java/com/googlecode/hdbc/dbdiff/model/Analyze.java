package com.googlecode.hdbc.dbdiff.model;

import java.util.Collection;
import java.util.HashSet;

import com.googlecode.hdbc.dbdiff.utilities.Condition;

public class Analyze<T> {
    private final String analysisTarget;
	private Collection<T> common = new HashSet<T>();
	private Collection<T> leftOnly = new HashSet<T>();
	private Collection<T> rightOnly = new HashSet<T>();

	private class MembershipCondition implements Condition<T> {
		private final Collection<T> targetCollection;
		public MembershipCondition(final Collection<T> target) {
			targetCollection = target;
		}
		public final boolean isSatisfiedBy(final T element) {
			return targetCollection.contains(element);
		}
	}

	public Analyze(final String targetDescription) {
	    analysisTarget = targetDescription;
	}

	public final Analyze<T> collections(final Collection<T> left,
			final Collection<T> right) {
		MembershipCondition membershipInRight = new MembershipCondition(right);
		for (T leftElement : left) {
			if (membershipInRight.isSatisfiedBy(leftElement)) {
				common.add(leftElement);
			} else {
				leftOnly.add(leftElement);
			}
		}

		MembershipCondition membershipInLeft = new MembershipCondition(left);
		for (T rightElement : right) {
			if (!membershipInLeft.isSatisfiedBy(rightElement)) {
				rightOnly.add(rightElement);
			}
		}
		return this;
	}

	public final Collection<T> getCommon() {
		return common;
	}

	public final Collection<T> getLeftOnly() {
		return leftOnly;
	}

	public final Collection<T> getRightOnly() {
		return rightOnly;
	}

	public final String getAnalysisTarget() {
        return analysisTarget;
    }

    public final boolean areEqual() {
		return this.leftOnly.size() == 0 && this.rightOnly.size() == 0;
	}
}
