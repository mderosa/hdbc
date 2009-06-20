package com.googlecode.hdbc.model;

import com.googlecode.hdbc.model.record.ExperimentAnalysis;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.record.ExperimentGraphics;

public class Experiment implements IExperiment {
	private ExperimentData data;
	
	public Experiment(ExperimentData d) {
		data = d;
	}
	
	@Override
	public ExperimentAnalysis analyseData() {
		return null;
	}

	@Override
	public ExperimentGraphics calculateGraphics() {
		return null;
	}

	@Override
	public ExperimentData getData() {
		return data;
	}

}
