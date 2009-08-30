package com.googlecode.hdbc.dao;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

@Repository
@Transactional
public class ExperimentDao implements IExperimentDao {
	private SessionFactory sessionFactory;
	
	public ExperimentDao(final SessionFactory factory) {
		sessionFactory = factory;
	}

	public final IExperiment find(final long uid) {
		return null;
	}

	public final long insert(final IExperiment experiment) {
		final ExperimentData data = experiment.getData();
		Serializable uid = sessionFactory.getCurrentSession().save(data);
		return (Long) uid;
	}

	public void update(final IExperiment experiment) {
		
	}

}
