package com.googlecode.hdbc.view.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ActiveExperimentsOutputPolicyTest {

	@Test
	public void testAnEmptyExperimentsList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("experiments", new ArrayList<ExperimentData>());
		
		ActiveExperimentsOutputPolicy policy = new ActiveExperimentsOutputPolicy();
		JSONObject jsn = (JSONObject) policy.customOutput(model);
		String expected = "{\"data\":[]}";
		assertEquals(expected, jsn.toString());
	}
}
