package com.googlecode.hdbc.view.policy;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ActiveExperimentsOutputPolicy implements ICustomOutputPolicy {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> customOutput(Map<String, Object> model) {
		List<ExperimentData> experiments = (List<ExperimentData>) model.get("experiments");
		
		JSONArray array = new JSONArray();
		for (ExperimentData experiment : experiments) {
			JSONObject elmnt = experiment.toJson();
			array.add(elmnt);
		}
		
		JSONObject jsn = new JSONObject();
		jsn.put("data", array);
		return jsn;
	}

}
