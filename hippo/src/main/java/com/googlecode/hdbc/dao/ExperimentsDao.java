package com.googlecode.hdbc.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.hdbc.model.IExperiment;

@Repository
@Transactional
public class ExperimentsDao implements IExperimentsDao {
	private SessionFactory sessionFactory;
	
	public ExperimentsDao(SessionFactory factory) {
		sessionFactory = factory;
	}
	
	private final static String activeExp = 
		"select ed from ExperimentData as ed " +
		"where modifiedDate > :startdt " +
		"and active is true ";
	@SuppressWarnings("unchecked")
	public final List<IExperiment> findActiveExperiments() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -14);
		
		Session session = sessionFactory.getCurrentSession();
		Query qry = session.createQuery(activeExp);
		qry.setDate(0, cal.getTime());
		return qry.list();
	}

}
