package com.googlecode.hdbc.dao;

import java.io.Serializable;
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
		Serializable uid = tmplt.save(data);
		return (Long) uid;
	}

	public void update(final IExperiment experiment) {
		
	}

}
