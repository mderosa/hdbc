package com.googlecode.hdbc.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DefaultExtJsonView extends AbstractJsonView {
	
	public DefaultExtJsonView(String contentType, String outputEncoding) {
		super(contentType, outputEncoding);
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
		}
		
		return json;
	}

	private JSONArray buildErrorArray(Map<String, Object> model) {
		JSONArray errors = new JSONArray();
		
		Errors bindRslt = (BindingResult) model.get("errors");
		if (bindRslt != null && bindRslt.hasFieldErrors()) {
			MessageSourceAccessor msgSource = getMessageSourceAccessor();
			for (FieldError error : bindRslt.getFieldErrors()) {
				JSONObject temp = new JSONObject();
				temp.put(error.getField(), 
					msgSource.getMessage(error.getCode(), error.getArguments()));
				errors.add(temp);
			}
		}
		return errors;
	}

}
