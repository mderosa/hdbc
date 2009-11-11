package com.googlecode.hdbc.view.policy;

import java.util.Map;
import net.sf.json.JSONObject;
import com.googlecode.hdbc.controller.ModelAttributes;
import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentOutputPolicy implements ICustomOutputPolicy {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> customOutput(Map<String, Object> model) {
		ExperimentData experiment = (ExperimentData) model.get(ModelAttributes.OBJECT);
		
		JSONObject json = new JSONObject();
		json.put("experiment", experiment.toJson());
		return (Map<String, Object>) json;
	}

}
