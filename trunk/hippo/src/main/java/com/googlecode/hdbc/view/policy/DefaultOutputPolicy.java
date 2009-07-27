package com.googlecode.hdbc.view.policy;

import java.util.Map;

public class DefaultOutputPolicy implements ICustomOutputPolicy {

	@Override
	public Map<String, Object> customOutput(Map<String, Object> model) {
		assert(!model.containsKey("errors"));
		return model;
	}

}
