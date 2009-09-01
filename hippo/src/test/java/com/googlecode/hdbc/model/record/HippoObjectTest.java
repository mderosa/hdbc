package com.googlecode.hdbc.model.record;

import static org.junit.Assert.*;

import net.sf.json.JSONObject;

import org.junit.Test;

public class HippoObjectTest {

	@Test
	public void testToJson() {
		ExperimentData data = new ExperimentData();
		data.setUid(2L);
		data.setTitle("atitle");
		data.setPurpose("apurpose");
		
		JSONObject jsn = data.toJson();
		assertEquals(2L, jsn.getLong("uid"));
		assertEquals("atitle", jsn.getString("title"));
		assertTrue(jsn.getBoolean("active"));
		//assertNull(jsn.get("method"));
		
		assertTrue(jsn.toString().contains("\"method\":null"));
		assertTrue(jsn.toString().contains("\"active\":true"));
	}
}
