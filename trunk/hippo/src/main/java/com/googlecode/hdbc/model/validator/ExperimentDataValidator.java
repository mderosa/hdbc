package com.googlecode.hdbc.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataValidator extends DataValidator implements Validator {

	@Override
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
	@Override
	public void validate(Object target, Errors errors) {
		ExperimentData data = (ExperimentData) target;
		if (!this.isNullOrGreaterThan(data.getUid(), 0)) {
			errors.rejectValue("uid", ValidationErrorCd.NUMBER_OUT_OF_RANGE.toString());
		}
		if (!this.isNotNullAndUpToLengthN(data.getTitle(), 64)) {
			errors.rejectValue("title", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString());
		}
		if (!this.isNotNullAndUpToLengthN(data.getPurpose(), 128)) {
			errors.rejectValue("purpose", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString());
		}
		if (!this.isNullOrUptoLengthN(data.getMethod(), 4000)) {
			errors.rejectValue("method", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString());
		}
		if (!this.isNullOrUptoLengthN(data.getConclusion(), 4000)) {
			errors.rejectValue("conclusion", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString());
		}
	}

}