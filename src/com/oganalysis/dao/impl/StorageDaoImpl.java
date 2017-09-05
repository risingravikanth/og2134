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

import com.oganalysis.dao.StorageDao;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;
import com.oganalysis.entities.Storage;
import com.oganalysis.entities.StorageFilter;

public class StorageDaoImpl implements StorageDao{
		
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {		
		this.sessionFactory=sessionFactory;
	}
	
	public List<String> getLocations()
	{

		// TODO Auto-generated method stub
		Session session=null;
		List<String> locations=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			locations=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct location from Storage where location!=' ' order by location asc");
			locations=(List<String>)query.list();
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
	public List<String> getOperators() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> operators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			operators=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct currentOperator from Storage where currentOperator!=' ' order by currentOperator asc");
			operators=(List<String>)query.list();
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
	public List<String> getOwners() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> owners=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			owners=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct currentOwners from Storage where currentOwners!=' ' order by currentOwners asc");
			owners=(List<String>)query.list();
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
	public List<String> getSelectedCompanies(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		 
		List<String> selectedCompanies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedCompanies=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(StorageFilter.class);
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startYear!=0 && endYear!=0)	
			{
				List<String> terminals=null;
				terminals=getTerminals(startYear, endYear);								
				
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,getEmptyList()));
			}				
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CURRENTOWNERS)));		
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
	public List<String> getSelectedTerminals(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedTerminals=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedTerminals=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(StorageFilter.class);
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startYear!=0 && endYear!=0)
			{
				List<String> terminals=null;
				terminals=getTerminals(startYear, endYear);
				
//				Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
//				criteria.add(capacityYearCriterion);
																
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,getEmptyList()));
			}
			
			
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_TANKFARM)));		
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
	@Override
	public List<String> getSelectedCountries(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedTerminals=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedTerminals=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(StorageFilter.class);
			createFiltersCriteria(selectedOptions, criteria);
			
			if(startYear!=0 && endYear!=0)
			{
				List<String> terminals=null;
				terminals=getTerminals(startYear, endYear);
				
//				Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
//				criteria.add(capacityYearCriterion);
																
				if(terminals.size()>0)
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,terminals));
				else		
					criteria.add(Restrictions.in(RESTRICTION_PROPERTY_TANKFARM,getEmptyList()));
			}
			
			
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));		
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
							
				Query query=session.createQuery("select distinct tankFarm from Storage where capacityYear between :startYear and :endYear");
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
	@Override
	public List<Storage> getStorage(int year) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Storage> storageList=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			storageList=new ArrayList<Storage>();
			tx.begin();
			Query query=session.createQuery("from Storage where capacityYear=:year");
			query.setParameter("year",year);
			storageList=(List<Storage>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return storageList;
	}
	@Override
	public List<StorageFilter> getStorageFilter() {
		// TODO Auto-generated method stub
		Session session=null;
		List<StorageFilter> storageFilterList=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			storageFilterList=new ArrayList<StorageFilter>();
			tx.begin();
			Query query=session.createQuery("from StorageFilter");						
			storageFilterList=(List<StorageFilter>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return storageFilterList;
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
			Query query=session.createQuery("select distinct currentOwners from StorageFilter where currentOwners!=' ' order by currentOwners asc");		
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
			Query query=session.createQuery("select distinct tankFarm from StorageFilter where currentOwners=:company order by tankFarm asc");	
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
			Query query=session.createQuery("select distinct country from StorageFilter order by country asc");			
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
			Query query=session.createQuery("select distinct tankFarm from StorageFilter where country=:country order by tankFarm asc");	
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
			Query query=session.createQuery("select distinct tankFarm from StorageFilter order by tankFarm asc");				
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
	public List<Storage> getTerminalData(String terminalName) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Storage> storage=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			storage=new ArrayList<Storage>();
			tx.begin();
			Query query=session.createQuery("from Storage where tankFarm=:name");
			query.setParameter("name",terminalName);
			storage=(List<Storage>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return storage;
	}	
	@Override
	public List<StorageFilter> getTerminalCompanies(String terminalName) {
		// TODO Auto-generated method stub
		List<StorageFilter> terminalCompanies=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminalCompanies=new ArrayList<StorageFilter>();
			tx.begin();
			Query query=session.createQuery("from StorageFilter where tankFarm=:name and currentOwners!=' '");
			query.setParameter("name",terminalName);
			terminalCompanies=(List<StorageFilter>)query.list();
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
	public List<String> getSelectedTerminals(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=getSelectedTerminals(selectedOptions, 0,0);
		return terminals;
	}
	private List<String> getEmptyList()
	{
		List<String> emptyList=new ArrayList<String>();
		emptyList.add("");
		return emptyList;
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
			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTOWNERS,owners);
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

}
