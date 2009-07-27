package com.googlecode.hdbc.view.policy;

import java.util.Map;

public class DefaultOutputPolicy implements ICustomOutputPolicy {

	public final Map<String, Object> customOutput(final Map<String, Object> model) {
		assert (!model.containsKey("errors"));
		return model;
	}

}
