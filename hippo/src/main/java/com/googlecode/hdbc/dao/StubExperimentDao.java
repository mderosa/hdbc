package com.googlecode.hdbc.dao;

import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

public class StubExperimentDao implements IExperimentDao {

	public final IExperiment find(final long uid) {
		ExperimentData data = new ExperimentData(23L, "test name", "test purpose");
		return new Experiment(data); 
	}

	public final long insert(final IExperiment experiment) {
		return 0;
	}

	public final void update(final IExperiment experiment) {

	}

}
