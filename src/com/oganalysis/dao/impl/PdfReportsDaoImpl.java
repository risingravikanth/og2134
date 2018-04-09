package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.PdfReportsDao;
import com.oganalysis.entities.Report;

public class PdfReportsDaoImpl implements PdfReportsDao {	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}
	@Override
	public List<Report> getPdfReports(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Report> reports=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			tx.begin();
			Criteria criteria=session.createCriteria(Report.class);
			
			List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
			List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
			List<String> sectors=selectedOptions.get(OPTION_SELECTED_SECTORS);
			
			if(countries!=null && countries.size()>0)
			{
				Criterion counrtryCriterion=Restrictions.in(RESTRICTION_PROPERTY_COUNTRY,countries);
				criteria.add(counrtryCriterion);
			}
				
			if(regions!=null && regions.size()>0)
			{
				Criterion regionCriterion=Restrictions.in(RESTRICTION_PROPERTY_REGION,regions);
				criteria.add(regionCriterion);
			}
			
			if(sectors!=null && sectors.size()>0)
			{
				Criterion sectorCriterion=Restrictions.in(RESTRICTION_PROPERTY_SECTOR,sectors);
				criteria.add(sectorCriterion);
			}
			
			reports=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return reports;
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> regions=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			regions=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct region from Report where region!=' ' order by region asc");
			regions=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return regions;
	}
	@Override
	public List<String> getCountries() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> countries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countries=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct country from Report where country!=' ' order by country asc");
			countries=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return countries;
	}
	@Override
	public List<String> getSecotors() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> sectors=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			sectors=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct sector from Report where sector!=' ' order by sector asc");
			sectors=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return sectors;
	}

}
