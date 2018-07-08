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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.CrudeOilDao;
import com.oganalysis.entities.CrudeOil;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.NaturalGas;

public class CrudeOilDaoImpl implements CrudeOilDao {

	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
	}


	@Override
	public List<CrudeOil> getCrudeOil(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<CrudeOil> crudeOil=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();			
			tx.begin();
			Criteria criteria=session.createCriteria(CrudeOil.class);
			createFiltersCriteria(selectedOptions, criteria);											
			crudeOil=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return crudeOil;
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
			Query query=session.createQuery("select distinct region from CrudeOil where region!=' ' order by region asc");
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
	public List<String> getCountries(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> countries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countries=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(CrudeOil.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));
			countries=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return countries;
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


	
}
