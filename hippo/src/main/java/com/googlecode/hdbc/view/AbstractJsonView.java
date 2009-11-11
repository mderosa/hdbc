package com.googlecode.hdbc.view;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.view.AbstractView;

public abstract class AbstractJsonView extends AbstractView {
	private String encoding;
	
	public AbstractJsonView(String contentType, String outputEncoding) {
		this.setContentType(contentType);
		if (Charset.isSupported(outputEncoding)) {
			this.encoding = outputEncoding;
		} else {
			throw new IllegalArgumentException("The encoding, " + outputEncoding +
				", is not supported on this platform");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// IE workaround: write into byte array first.
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		
		JSONObject json = buildJsonResponse(model, request, response);
		byte[] output = json.toString().getBytes(encoding);
		baos.write(output, 0, output.length);
		
		// Flush to HTTP response.
		writeToResponse(response, baos);
	}
	
	protected abstract JSONObject buildJsonResponse(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response);

	protected JSONArray buildErrorArray(Map<String, Object> model) {
		JSONArray errors = new JSONArray();
		
		Errors bindRslt = (BindingResult) model.get("errors");
		if (bindRslt != null && bindRslt.hasFieldErrors()) {
			MessageSourceAccessor msgSource = getMessageSourceAccessor();
			for (Object error : bindRslt.getFieldErrors()) {
				FieldError err = (FieldError) error;
				JSONObject temp = new JSONObject();
				temp.put(err.getField(), 
					msgSource.getMessage(err.getCode(), err.getArguments()));
				errors.add(temp);
			}
		}
		return errors;
	}

}
