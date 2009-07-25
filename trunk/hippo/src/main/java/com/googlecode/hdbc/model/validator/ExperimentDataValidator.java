package com.googlecode.hdbc.model.validator;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.googlecode.hdbc.model.record.ExperimentData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExperimentDataValidator extends DataValidator implements Validator<ExperimentData> {

	public ExperimentDataValidator(MessageSource msgSource) {
		super(msgSource);
	}
	
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
	public JSONObject validate(ExperimentData data, Locale locale) {
		JSONArray errors = new JSONArray();
		if (!this.isNullOrGreaterThan(data.getUid(), 0)) {
			errors.add(
				rejectValue("uid", ValidationErrorCd.NUMBER_BELOW_MINIMUM.toString(), new Object[] {0}, locale));
		}
		if (!this.isNotNullAndUpToLengthN(data.getTitle(), 64)) {
			errors.add(
				rejectValue("title", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString(), new Object[] {64}, locale));
		}
		if (!this.isNotNullAndUpToLengthN(data.getPurpose(), 128)) {
			errors.add(
				rejectValue("purpose", ValidationErrorCd.REQUIRED_STRING_TOO_LONG.toString(), new Object[] {128}, locale));
		}
		if (!this.isNullOrUptoLengthN(data.getMethod(), 4000)) {
			errors.add(
				rejectValue("method", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString(), new Object[] {4000}, locale));
		}
		if (!this.isNullOrUptoLengthN(data.getConclusion(), 4000)) {
			errors.add(
				rejectValue("conclusion", ValidationErrorCd.OPTIONAL_STRING_TO_LONG.toString(), new Object[] {4000}, locale));
		}
		
		JSONObject jsn = new JSONObject();
		if (errors.size() > 0) {
			jsn.put("errors", errors);
		}
		return jsn;
	}

}
