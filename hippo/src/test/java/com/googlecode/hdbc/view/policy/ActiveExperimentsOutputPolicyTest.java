package com.googlecode.hdbc.view.policy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.googlecode.hdbc.model.factory.ExperimentDataFactory;
import com.googlecode.hdbc.model.record.ExperimentData;

/**
 * Returns a JSON object of the form
 * {data : [ {ExperimentData.toJson()}, {...}, ...]} 
 *
 */
public class ActiveExperimentsOutputPolicyTest {
	private ExperimentDataFactory factory = new ExperimentDataFactory();
	
	@Test
	public void testAnEmptyExperimentsList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("experiments", new ArrayList<ExperimentData>());
		
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
		model.put("experiments", exps);
		
		ActiveExperimentsOutputPolicy policy = new ActiveExperimentsOutputPolicy();
		JSONObject jsn = (JSONObject) policy.customOutput(model);
		
		JSONArray data = jsn.getJSONArray("data");
		assertEquals(2, data.size());
	}
}
