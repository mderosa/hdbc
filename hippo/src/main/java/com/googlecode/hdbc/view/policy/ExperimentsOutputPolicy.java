package com.googlecode.hdbc.view.policy;

import java.util.Map;

import com.googlecode.hdbc.controller.ExperimentsController;
import com.googlecode.hdbc.model.record.ExperimentData;

import net.sf.json.JSONObject;

public class ExperimentsOutputPolicy implements ICustomOutputPolicy {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> customOutput(Map<String, Object> model) {
		JSONObject detail = new JSONObject();
		ExperimentData experiment = (ExperimentData) model.get(ExperimentsController.COMMAND_NAME);
		detail.put("uid", experiment.getUid());
		detail.put("title", experiment.getTitle());
		detail.put("purpose", experiment.getPurpose());
		JSONObject json = new JSONObject();
		json.put("experiment", detail);
		return (Map<String, Object>) json;
	}

}
