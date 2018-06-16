package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COMPANIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_LOCATIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_REGIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_STATUSES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TECHNOLOGIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TECHNOLOGYPROVIDERS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TYPES;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_COMPANY;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_LOCATION;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_REGION;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_STATUS;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_TECHNOLOGYPROVIDER;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_TYPE;

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

import com.oganalysis.dao.SmallScaleLngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.SmallScaleLng;
import com.oganalysis.entities.source.Region;

public class SmallScaleLngDaoImpl implements SmallScaleLngDao {
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
	}
	@Override
	public List<SmallScaleLng> getSelectedSmallScaleLng(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<SmallScaleLng> selectedSmallScaleLng=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedSmallScaleLng=new ArrayList<SmallScaleLng>();
			tx.begin();
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);			
			selectedSmallScaleLng=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedSmallScaleLng;
	}
	
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
		List<String> locations=selectedOptions.get(OPTION_SELECTED_LOCATIONS);		
		List<String> companies=selectedOptions.get(OPTION_SELECTED_COMPANIES);
		List<String> technologyProviders=selectedOptions.get(OPTION_SELECTED_TECHNOLOGYPROVIDERS);
		List<String> technologies=selectedOptions.get(OPTION_SELECTED_TECHNOLOGIES);
		List<String> statuses=selectedOptions.get(OPTION_SELECTED_STATUSES);
		List<String> types=selectedOptions.get(OPTION_SELECTED_TYPES);				
		
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
		if(locations!=null && locations.size()>0)
		{
			Criterion locationCriterion=Restrictions.in(RESTRICTION_PROPERTY_LOCATION,locations);
			criteria.add(locationCriterion);
		}
		
		
		else if(companies!=null && companies.size()>0)
		{
			Criterion companiesCriterion=Restrictions.in(RESTRICTION_PROPERTY_COMPANY,companies);
			criteria.add(companiesCriterion);
		}
		else if(technologyProviders!=null && technologyProviders.size()>0)
		{
			Criterion technologyProvidersCriterion=Restrictions.in(RESTRICTION_PROPERTY_TECHNOLOGYPROVIDER,technologyProviders);
			criteria.add(technologyProvidersCriterion);
		}
		else if(technologies!=null && technologies.size()>0)
		{
			Criterion technologyCriterion=Restrictions.in(RESTRICTION_PROPERTY_TECHNOLOGY,technologies);
			criteria.add(technologyCriterion);
		}
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in(RESTRICTION_PROPERTY_STATUS,statuses);
			criteria.add(statusesCriterion);
		}
		
		if(types!=null && types.size()>0)
		{
			Criterion typeCriterion=Restrictions.in(RESTRICTION_PROPERTY_TYPE,types);
			criteria.add(typeCriterion);
		}
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		List<String> regionsList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			regionsList=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct region from SmallScaleLng where region!=' ' order by region asc");				
			regionsList=(List<String>)query.list();
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
	public List<String> getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> countriesList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countriesList=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));
			countriesList=criteria.list();
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
	public List<String> getLocations(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> locationsList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			locationsList=new ArrayList<String>();
			tx.begin();			
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_LOCATION)));
			locationsList=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return locationsList;
	}
	@Override
	public List<String> getCompanies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> companiesList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			companiesList=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COMPANY)));
			companiesList=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return companiesList;
	}
	@Override
	public List<String> getTechnologyProviders(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> technologyProvidersList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			technologyProvidersList=new ArrayList<String>();
			tx.begin();			
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_TECHNOLOGYPROVIDER)));
			technologyProvidersList=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return technologyProvidersList;
	}
	@Override
	public List<String> getTechnologies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> technologyList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			technologyList=new ArrayList<String>();
			tx.begin();			
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_TECHNOLOGY)));
			technologyList=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return technologyList;
	}
	@Override
	public List<String> getStatuses(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> statusList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			statusList=new ArrayList<String>();
			tx.begin();			
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_STATUS)));
			statusList=criteria.list();
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
	public List<String> getTypes(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> typeList=null;
		Session session=null;
		try
		{			
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			typeList=new ArrayList<String>();
			tx.begin();			
			Criteria criteria=session.createCriteria(SmallScaleLng.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_TYPE)));
			typeList=criteria.list();
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
