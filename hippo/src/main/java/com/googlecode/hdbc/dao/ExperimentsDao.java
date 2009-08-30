package com.googlecode.hdbc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.hdbc.model.IExperiment;

@Repository
@Transactional
public class ExperimentsDao implements IExperimentsDao {

	public final List<IExperiment> findActiveExperiments() {
		return null;
	}

}
