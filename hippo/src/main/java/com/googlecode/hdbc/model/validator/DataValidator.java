package com.googlecode.hdbc.model.validator;

public abstract class DataValidator {

	public enum ValidationErrorCd {
		REQUIRED_STRING_TOO_LONG {
			@Override
			public final String toString() {
				return "required_string.too.long";
			}
		},
		OPTIONAL_STRING_TO_LONG {
			@Override
			public final String toString() {
				return "optional.string.too.long";
			}
		},
		NUMBER_BELOW_MINIMUM {
			@Override
			public final String toString () {
				return "number.below.minimum";
			}
		}
	}
	
	public boolean isNullOrUptoLengthN(String field, long maxLength) {
		if (field == null) {
			return true;
		} 
		return field.length() <= maxLength;
	}
	
	public boolean isNullOrGreaterThan(Long field, long min) {
		if (field == null) {
			return true;
		}
		return field.longValue() > min;
	}
	
	public boolean isNotNullAndUpToLengthN(String field, long maxLength) {
		if (field == null) {
			return false;
		}
		return field.length() <= maxLength;
	}
	
}
