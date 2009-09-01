package com.googlecode.hdbc.model.factory;

import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDataFactory {

	public ExperimentData mkStdExperimentData() {
		ExperimentData data = new ExperimentData();
		data.setActive(true);
		data.setConclusion("theconclusion");
		data.setMethod("themethod");
		data.setModifiedBy(1L);
		data.setPurpose("thepurpose");
		data.setTitle("thetitle");
		data.setUid(1L);
		return data;
	}
	
}
