package com.googlecode.hdbc.dao;

import java.util.List;
import com.googlecode.hdbc.model.record.ExperimentData;

public interface IExperimentsDao {

	List<ExperimentData> findActiveExperiments();
}
