package com.googlecode.hdbc.dao;

import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

public class StubExperimentDao implements IExperimentDao {

	public IExperiment find(long uid) {
		ExperimentData data = new ExperimentData(23L, "test name", "test purpose");
		return new Experiment(data); 
	}

	public long insert(IExperiment experiment) {
		return 0;
	}

	public void update(IExperiment experiment) {

	}

}
