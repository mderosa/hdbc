package com.googlecode.hdbc.dao;

import java.util.List;
import com.googlecode.hdbc.model.IExperiment;

public interface IExperimentsDao {

	List<IExperiment> findActiveExperiments();
}
