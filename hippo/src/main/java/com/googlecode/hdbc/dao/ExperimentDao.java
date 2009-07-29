package com.googlecode.hdbc.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.googlecode.hdbc.model.IExperiment;
import com.googlecode.hdbc.model.record.ExperimentData;

public class ExperimentDao implements IExperimentDao {
	private HibernateTemplate tmplt;
	
	public ExperimentDao(final SessionFactory factory) {
		tmplt = new HibernateTemplate(factory);
	}

	public final IExperiment find(final long uid) {
		return null;
	}

	public final long insert(final IExperiment experiment) {
		final ExperimentData data = experiment.getData();
		tmplt.save(data);
		return 0;
	}

	public void update(final IExperiment experiment) {
		
	}

}
