package com.googlecode.hdbc.model.validator;

import net.sf.json.JSONObject;

public interface Validator<T> {
	
	boolean supports(Class<?> clazz);
	
	JSONObject validate(T input);
}
