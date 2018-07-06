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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class RefineriesDaoImpl implements RefineriesDao{
	
	private SessionFactory sessionFactory;
	@Override
	public List<String> getSelectedCompanies(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		List<String> selectedCompanies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedCompanies=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(RefineriesFilter.class);
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startDate!=0 && endDate!=0)	
			{
				List<String> terminals=null;
				terminals=getTerminals(startDate, endDate);								
				
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
			}				
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS)));		
			selectedCompanies=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedCompanies;
	}	
	@Override
	public List<String> getSelectedCountries(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedCountries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedCountries=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(RefineriesFilter.class);
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startDate!=0 && endDate!=0)
			{
				List<String> terminals=null;
				terminals=getTerminals(startDate, endDate);								
				
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
			}
				
			
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));		
			selectedCountries=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedCountries;
	}

	@Override
	public List<String> getSelectedTerminals(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedTerminals=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedTerminals=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(RefineriesFilter.class);//RefineriesFilter.class
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startDate!=0 && endDate!=0)
			{
				List<String> terminals=null;
				terminals=getTerminals(startDate, endDate);
				
//				Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
//				criteria.add(capacityYearCriterion);
																
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
			}
			
			
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));		
			selectedTerminals=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedTerminals;
	}
	public List<String> getTerminals(int startYear,int endYear)
	{
		List<String> terminals=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminals=new ArrayList<String>();
			tx.begin();
//			Criteria criteria=session.createCriteria(Refinery.class);	
			
			if(startYear!=0 && endYear!=0)
			{
							
				Query query=session.createQuery("select distinct name from Refinery where capacityYear between :startYear and :endYear");
				query.setParameter("startYear", startYear);
				query.setParameter("endYear", endYear);
				terminals=(List<String>)query.list();
//				Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
//				criteria.add(capacityYearCriterion);
			}					
//			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));
			
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return terminals;
	}	
	public List<RefineriesFilter> getRefineriesFilter()
	{
		Session session=null;
		List<RefineriesFilter> refineriesFilter=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			refineriesFilter=new ArrayList<RefineriesFilter>();
			tx.begin();
			Query query=session.createQuery("from RefineriesFilter");//RefineriesFilter.class);						
			refineriesFilter=(List<RefineriesFilter>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return refineriesFilter;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
		List<String> locations=selectedOptions.get(OPTION_SELECTED_LOCATIONS);		
		List<String> owners=selectedOptions.get(OPTION_SELECTED_OWNERS);
		List<String> operators=selectedOptions.get(OPTION_SELECTED_OPERATORS);
		List<String> statuses=selectedOptions.get(OPTION_SELECTED_STATUSES);
		
		
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
		
//		if((owners!=null && owners.size()>0) && (operators!=null && operators.size()>0))
//		{
//			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS,owners);				
//			Criterion operatorCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTOPERATOR,operators);			
//			criteria.add(Restrictions.or(ownersCriterion, operatorCriterion));
//			
//		}
		else if(owners!=null && owners.size()>0)
		{
			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS,owners);
			criteria.add(ownersCriterion);
		}
		else if(operators!=null && operators.size()>0)
		{
			Criterion operatorCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTOPERATOR,operators);
			criteria.add(operatorCriterion);
		}
		
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in(RESTRICTION_PROPERTY_STATUS,statuses);
			criteria.add(statusesCriterion);
		}
		
	}
	@Override
	public List<Refinery> getTerminalData(String terminalName) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Refinery> refinery=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			refinery=new ArrayList<Refinery>();
			tx.begin();
			Query query=session.createQuery("from Refinery where name=:name");
			query.setParameter("name",terminalName);
			refinery=(List<Refinery>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return refinery;
	}
	@Override
	public List<RefineriesFilter> getTerminalCompanies(String terminalName) {
		// TODO Auto-generated method stub
		List<RefineriesFilter> terminalCompanies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminalCompanies=new ArrayList<RefineriesFilter>();
			tx.begin();
			Query query=session.createQuery("from RefineriesFilter where name=:name and currentEquityPartners!=' '");
			query.setParameter("name",terminalName);
			terminalCompanies=(List<RefineriesFilter>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}	
		return terminalCompanies;
	}
	@Override
	public List<RefineriesFilter> getTerminalHistoricCompanies(
			String terminalName) {
		// TODO Auto-generated method stub
		List<RefineriesFilter> terminalHistoricCompanies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminalHistoricCompanies=new ArrayList<RefineriesFilter>();
			tx.begin();
			Query query=session.createQuery("from RefineriesFilter where name=:name and historicEquityPartners!=' '");
			query.setParameter("name",terminalName);
			terminalHistoricCompanies=(List<RefineriesFilter>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return terminalHistoricCompanies;
	}
	//Below one is for cache
	@Override
	public List<Refinery> getRefineries(int year) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Refinery> refineries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			refineries=new ArrayList<Refinery>();
			tx.begin();
			Query query=session.createQuery("from Refinery where capacityYear=:year");
			query.setParameter("year",year);
			refineries=(List<Refinery>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return refineries;
	}
	@Override
	public List<String> getCompanies() {
		// TODO Auto-generated method stub
		List<String> companies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			companies=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct currentEquityPartners from RefineriesFilter where currentEquityPartners!=' ' order by currentEquityPartners asc");		
			companies=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return companies;
	}
	@Override
	public List<String> getCompanyTerminals(String company) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> companyTerminals=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			companyTerminals=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct name from RefineriesFilter where currentEquityPartners=:company order by name asc");	
			query.setParameter("company",company);
			companyTerminals=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return companyTerminals;
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
			Criteria criteria=session.createCriteria(Refinery.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));			
			countries=(List<String>)criteria.list();
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
	public List<String> getCountryTerminals(String country) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> countryTermnials=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countryTermnials=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct name from RefineriesFilter where country=:country order by name asc");	
			query.setParameter("country",country);
			countryTermnials=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return countryTermnials;
	}
	@Override
	public List<String> getTerminals() {
		// TODO Auto-generated method stub
		List<String> terminals=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminals=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct name from RefineriesFilter order by name asc");				
			terminals=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return terminals;
	}
	@Override
	public List<String> getSelectedTerminals(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=getSelectedTerminals(selectedOptions, 0,0);
		return terminals;
	}
	@Override
	public List<String> getLocations(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> locations=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			locations=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Refinery.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_LOCATION)));
			locations=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return locations;
	}
	@Override
	public List<String> getOperators(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> operators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			operators=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Refinery.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CURRENTOPERATOR)));			
			operators=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return operators;
	}
	@Override
	public List<String> getOwners(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> owners=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			owners=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Refinery.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS)));			
			owners=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return owners;
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
			Query query=session.createQuery("select distinct region from Refinery where region!=' ' order by region asc");
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
	public List<String> getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> status=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			status=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Refinery.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_STATUS)));			
			status=(List<String>)criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return status;
	}
	private List<String> getEmptyList()
	{
		List<String> emptyList=new ArrayList<String>();
		emptyList.add("");
		return emptyList;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
								
}
