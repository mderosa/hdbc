package com.googlecode.hdbc.model;

import com.googlecode.hdbc.model.record.ExperimentAnalysis;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.record.ExperimentGraphics;

public class Experiment implements IExperiment {
	private ExperimentData data;
	
	public Experiment(final ExperimentData d) {
		data = d;
	}
	
	public final ExperimentAnalysis analyseData() {
		return null;
	}

	public final ExperimentGraphics calculateGraphics() {
		return null;
	}

	public final ExperimentData getData() {
		return data;
	}

}
