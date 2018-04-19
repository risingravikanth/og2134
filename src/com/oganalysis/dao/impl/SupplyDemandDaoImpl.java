package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_REGIONS;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_REGION;

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

import com.oganalysis.dao.SupplyDemandDao;
import com.oganalysis.entities.SupplyDemandExport;
import com.oganalysis.entities.SupplyDemandImport;

public class SupplyDemandDaoImpl implements SupplyDemandDao {
	
	private SessionFactory sessionFactory;
	@Override
	public List<SupplyDemandImport> getSupplyDemandImport(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<SupplyDemandImport> supplyDemandImport=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			supplyDemandImport=new ArrayList<SupplyDemandImport>();
			tx.begin();
//			Query query=session.createQuery("from SupplyDemandImport sdi where sdi.region in(:regions) and country in (:countries)");
			Criteria criteria=session.createCriteria(SupplyDemandImport.class);
			createFiltersCriteria(selectedOptions,criteria);
			supplyDemandImport=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return supplyDemandImport;
	}

	@Override
	public List<SupplyDemandExport> getSupplyDemandExport(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<SupplyDemandExport> supplyDemandExport=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			supplyDemandExport=new ArrayList<SupplyDemandExport>();
			tx.begin();
//			Query query=session.createQuery("from SupplyDemandExport sde where sde.region in(:regions) and sde.country in (:countries)");
			Criteria criteria=session.createCriteria(SupplyDemandExport.class);
			createFiltersCriteria(selectedOptions,criteria);			
			supplyDemandExport=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}
		
		return supplyDemandExport;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
				
		if(countries!=null && countries.size()>0)
		{
			Criterion counrtryCriterion=Restrictions.in(RESTRICTION_PROPERTY_COUNTRY, countries);
			criteria.add(counrtryCriterion);
		}
			
		if(regions!=null && regions.size()>0)
		{
			Criterion regionCriterion=Restrictions.in(RESTRICTION_PROPERTY_REGION, regions);
			criteria.add(regionCriterion);
		}
		
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
			Query query=session.createQuery("select distinct region from SupplyDemand where region!=' ' order by region asc");
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
		List<String> regions=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			regions=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct country from SupplyDemand where country!=' ' order by country asc");
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
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
}
