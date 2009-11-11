package com.googlecode.hdbc.model.record;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

public abstract class HippoObject {
	private static JsonConfig config = new JsonConfig();
	
	static {
		config.registerDefaultValueProcessor(Long.class, new DefaultValueProcessor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getDefaultValue(Class arg0) {
				return JSONNull.getInstance();
			}
		});
		config.registerDefaultValueProcessor(String.class, new DefaultValueProcessor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getDefaultValue(Class arg0) {
				return JSONNull.getInstance();
			}
		});
		config.registerDefaultValueProcessor(Date.class, new DefaultValueProcessor() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getDefaultValue(Class arg0) {
				return JSONNull.getInstance();
			}
		});
		config.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			@Override
			public Object processArrayValue(Object value, JsonConfig config) {
				throw new UnsupportedOperationException("formatting of date arrays is currently not supported");
			}

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig config) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				return fmt.format(value);
			}
			
		});
	}
	
	public JSONObject toJson() {
		return JSONObject.fromObject(this, config);
	}
}

