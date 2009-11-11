package com.googlecode.hdbc.view.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.googlecode.hdbc.controller.ModelAttributes;
import com.googlecode.hdbc.model.factory.ExperimentDataFactory;
import com.googlecode.hdbc.model.record.ExperimentData;

public class ActiveExperimentsOutputPolicyTest {
	private ExperimentDataFactory factory = new ExperimentDataFactory();
	
	@Test
	public void testAnEmptyExperimentsList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(ModelAttributes.COLLECTION, new ArrayList<ExperimentData>());
		
		ActiveExperimentsOutputPolicy policy = new ActiveExperimentsOutputPolicy();
		JSONObject jsn = (JSONObject) policy.customOutput(model);
		String expected = "{\"data\":[]}";
		assertEquals(expected, jsn.toString());
	}
	
	@Test
	public final void testAExperimentsListWithContents() {
		Map<String, Object> model = new HashMap<String, Object>();
		ArrayList<ExperimentData> exps = new ArrayList<ExperimentData>();
		exps.add(factory.mkStdExperimentData());
		exps.add(factory.mkStdExperimentData());
		model.put(ModelAttributes.COLLECTION, exps);
		
		ActiveExperimentsOutputPolicy policy = new ActiveExperimentsOutputPolicy();
		JSONObject jsn = (JSONObject) policy.customOutput(model);
		
		JSONArray data = jsn.getJSONArray("data");
		assertEquals(2, data.size());
	}
}
