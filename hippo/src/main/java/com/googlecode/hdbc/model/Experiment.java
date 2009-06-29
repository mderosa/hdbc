package com.googlecode.hdbc.model;

import com.googlecode.hdbc.model.record.ExperimentAnalysis;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.record.ExperimentGraphics;

public class Experiment implements IExperiment {
	private ExperimentData data;
	
	public Experiment(ExperimentData d) {
		data = d;
	}
	
	public ExperimentAnalysis analyseData() {
		return null;
	}

	public ExperimentGraphics calculateGraphics() {
		return null;
	}

	public ExperimentData getData() {
		return data;
	}

}
