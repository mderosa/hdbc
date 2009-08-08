package com.googlecode.hdbc.view;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
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
		Charset chs = Charset.forName(encoding);
		ByteBuffer output = chs.encode(json.toString());
		baos.write(output.array(), 0, output.array().length - 1);
		
		// Flush to HTTP response.
		writeToResponse(response, baos);
	}
	
	protected abstract JSONObject buildJsonResponse(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response);

}
