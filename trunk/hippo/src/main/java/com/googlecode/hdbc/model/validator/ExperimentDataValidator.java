package com.googlecode.hdbc.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataValidator extends DataValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return false;
	}

	/**
	 * Validate the ExperimentData dumb object
	 * <p>
	 * Specification
	 * validate :: Object -> Errors -> Boolean
	 * validate obj | obj.uid == null || obj.uid > 0
	 * 					&&
	 * 				  obj.title != null && len(obj.title) <= 64
	 * 					&&
	 * 				  obj.purpose != null && len(obj.purpose) <= 128
	 * 					&&
	 * 				  obj.method == null || len(obj.method) <= 4000
	 * 					&&
	 * 			      obj.conclusion == null || len(obj.conclusion) <= 4000 = false
	 * 				 | otherwise = true
	 * <p>
	 * precondition is obj.title and obj.purpose /= null
	 * postcondition error.count >= 0
	 */
	public void validate(Object target, Errors errors) {
		ExperimentData data = (ExperimentData) target;
		if (!this.isNullOrGreaterThan(data.getUid(), 0)) {
			errors.rejectValue("uid", ValidationErrorCd.NUMBER_BELOW_MINIMUM.toString(), new Object[] {0}, "na");
		}
		if (!this.isNotNullAndUpToLengthN(data.getTitle(), 64)) {
			errors.rejectValue("title", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString(), new Object[] {64}, "na");
		}
		if (!this.isNotNullAndUpToLengthN(data.getPurpose(), 128)) {
			errors.rejectValue("purpose", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString(), new Object[] {128}, "na");
		}
		if (!this.isNullOrUptoLengthN(data.getMethod(), 4000)) {
			errors.rejectValue("method", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString(), new Object[] {4000}, "na");
		}
		if (!this.isNullOrUptoLengthN(data.getConclusion(), 4000)) {
			errors.rejectValue("conclusion", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString(), new Object[] {4000}, "na");
		}
	}

}
