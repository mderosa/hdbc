package com.googlecode.hdbc.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.hdbc.view.policy.ICustomOutputPolicy;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StandardJsonView extends AbstractJsonView {
	private ICustomOutputPolicy outputPolicy;
	
	public StandardJsonView(ICustomOutputPolicy policy) {
		super("application/json;charset=UTF-8", "UTF-8");
		outputPolicy = policy;
	}

	public StandardJsonView(String contentType, String outputEncoding, ICustomOutputPolicy policy) {
		super(contentType, outputEncoding);
		outputPolicy = policy;
	}

	@Override
	protected JSONObject buildJsonResponse(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		JSONArray errors = buildErrorArray(model);
		if (errors.size() > 0) {
			json.put("errors", errors);
		}
		
		json.accumulateAll(outputPolicy.customOutput(model));
		return json;
	}

}
