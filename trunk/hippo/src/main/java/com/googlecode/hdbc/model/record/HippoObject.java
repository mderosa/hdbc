package com.googlecode.hdbc.model.record;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public abstract class HippoObject {
	
	public JSONObject toJson() {
		JSONObject jsn = new JSONObject();
		PropertyDescriptor[] descriptions = PropertyUtils.getPropertyDescriptors(this);
		for (PropertyDescriptor desc : descriptions) {
			String methodName = desc.getName();
			Object value;
			try {
				 value = PropertyUtils.getSimpleProperty(this, methodName);
				 if (value == null) {
					 value = JSONNull.getInstance();
				 }
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
			if (!methodName.equals("class")) {
				jsn.put(methodName, value);
			}
		}
		return jsn;
	}
}

