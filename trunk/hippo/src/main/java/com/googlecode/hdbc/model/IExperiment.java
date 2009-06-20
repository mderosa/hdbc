package com.googlecode.hdbc.model;

import com.googlecode.hdbc.model.record.ExperimentAnalysis;
import com.googlecode.hdbc.model.record.ExperimentData;
import com.googlecode.hdbc.model.record.ExperimentGraphics;

public interface IExperiment {
	
	ExperimentData getData();
	
	ExperimentAnalysis analyseData();
	
	ExperimentGraphics calculateGraphics();

}
