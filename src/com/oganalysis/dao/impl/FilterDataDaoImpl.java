package com.oganalysis.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oganalysis.dao.FilterDataDao;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;

public class FilterDataDaoImpl implements FilterDataDao{
//	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}
	@Override
	public List<Region> getRegions() {
		// TODO Auto-generated method stub
		List<Region> regionsList=null;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Region.class);				
		regionsList=(List<Region>)criteria.list();
		tx.commit();
		session.close();
		return regionsList;
		
	}
	@Override
	public List<Countries> getCountries() {
		// TODO Auto-generated method stub
		List<Countries> countriesList=null;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Countries.class);				
		countriesList=(List<Countries>)criteria.list();
		tx.commit();
		session.close();
		return countriesList;
	}
	@Override
	public List<Status> getStatus() {
		// TODO Auto-generated method stub
		List<Status> statusList=null;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Status.class);				
		statusList=(List<Status>)criteria.list();
		tx.commit();
		session.close();
		return statusList;
	}
	@Override
	public List<Type> getType() {
		// TODO Auto-generated method stub
		List<Type> typeList=null;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Type.class);				
		typeList=(List<Type>)criteria.list();
		tx.commit();
		session.close();
		return typeList;
	}
	
}
