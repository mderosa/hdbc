package com.googlecode.hdbc.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.googlecode.hdbc.view.policy.ICustomOutputPolicy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExtFormJsonView extends AbstractJsonView {
	private ICustomOutputPolicy extraOutput;
	
	public ExtFormJsonView(String contentType, String outputEncoding, ICustomOutputPolicy output) {
		super(contentType, outputEncoding);
		this.extraOutput = output;
	}

	@Override
	protected JSONObject buildJsonResponse(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();
		JSONArray errors = buildErrorArray(model);
		if (errors.size() > 0) {
			json.put("success", false);
			json.put("errors", errors);
		} else {
			json.put("success", true);
			json.accumulateAll(extraOutput.customOutput(model));
		}
		
		return json;
	}

}
