package com.googlecode.hdbc.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	/**
	 * Validate the ExperimentData dumb object
	 * <p>
	 * Specification
	 * validate :: Object -> Errors -> Boolean
	 * validate obj | obj.uid == null || (isnumeric(obj.uid) && obj.uid > 0)
	 * 					&&
	 * 				  len(obj.title) <= 64
	 * 					&&
	 * 				  len(obj.purpose) <= 128
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

	}

}
