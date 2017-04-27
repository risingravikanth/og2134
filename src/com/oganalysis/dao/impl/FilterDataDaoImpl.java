package com.oganalysis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			regionsList=new ArrayList<Region>();
			tx.begin();
			Query query=session.createQuery("from Region");				
			regionsList=(List<Region>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return regionsList;
		
	}
	@Override
	public List<Countries> getCountries() {
		// TODO Auto-generated method stub
		List<Countries> countriesList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countriesList=new ArrayList<Countries>();
			tx.begin();
			Query query=session.createQuery("from Countries");					
			countriesList=(List<Countries>)query.list();
			tx.commit();			
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return countriesList;
	}
	@Override
	public List<Status> getStatus() {
		// TODO Auto-generated method stub
		List<Status> statusList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			statusList=new ArrayList<Status>();
			tx.begin();
			Query query=session.createQuery("from Status");						
			statusList=(List<Status>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return statusList;
	}
	@Override
	public List<Type> getType() {
		// TODO Auto-generated method stub
		List<Type> typeList=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			typeList=new ArrayList<Type>();
			tx.begin();
			Query query=session.createQuery("from Type");					
			typeList=(List<Type>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return typeList;
	}
	
}
