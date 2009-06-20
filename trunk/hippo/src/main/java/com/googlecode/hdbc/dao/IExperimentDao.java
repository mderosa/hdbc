package com.googlecode.hdbc.dao;

import com.googlecode.hdbc.model.IExperiment;

public interface IExperimentDao {

	IExperiment find(long uid);
	
	Long insert(IExperiment experiment);
	
	void update(IExperiment experiment);
}
