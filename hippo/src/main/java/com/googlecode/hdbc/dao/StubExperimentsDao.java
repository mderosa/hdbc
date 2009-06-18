package com.googlecode.hdbc.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.hdbc.model.Experiment;
import com.googlecode.hdbc.model.IExperiment;

public class StubExperimentsDao implements IExperimentsDao {

	@Override
	public List<IExperiment> findActiveExperiments() {
		List<IExperiment> experiments = new ArrayList<IExperiment>();
		experiments.add(new Experiment(23L, "exp1", "to test stuff"));
		experiments.add(new Experiment(87L, "sign up experiment","to test ui elements for their influence on sign up"));
		return experiments;
	}

}
