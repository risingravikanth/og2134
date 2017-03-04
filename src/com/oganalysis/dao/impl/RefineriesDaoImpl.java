package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class RefineriesDaoImpl implements RefineriesDao{
	
	private SessionFactory sessionFactory;
	@Override
	public List<String> getSelectedCompanies(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		List<String> terminals=null;
		if(startDate!=0 && endDate!=0)		
			terminals=getTerminals(startDate, endDate);								
					
		if(terminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
		else		
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS)));		
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<String> getTerminals(int startDate,int endDate)
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Refinery.class);				
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedCountries(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		List<String> terminals=null;
		if(startDate!=0 && endDate!=0)		
			terminals=getTerminals(startDate, endDate);								
					
		if(terminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
		else		
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));		
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getSelectedTerminals(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		List<String> terminals=null;
		if(startDate!=0 && endDate!=0)		
			terminals=getTerminals(startDate, endDate);								
					
		if(terminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
		else		
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));		
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<String> getCompanyTerminals(String company,List<String> selectedTerminals)
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);				
		if(selectedTerminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,selectedTerminals));
		else
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS, company));
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<String> getCountryTerminals(String country,List<String> selectedTerminals)
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);				
		if(selectedTerminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,selectedTerminals));
		else
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_COUNTRY, country));
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<RefineriesFilter> getRefineriesFilter()
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(RefineriesFilter.class);						
		List<RefineriesFilter> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<Refinery> getRefineriesData(
			Map<String, List<String>> selectedOptions,List<String> selectedTerminals, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Refinery.class);
		createFiltersCriteria(selectedOptions, criteria);		
		if(startDate!=0 && endDate!=0)
		{
			Criterion capacityYearCriterion=Restrictions.between(RESTRICTION_PROPERTY_CAPACITYYEAR, startDate, endDate);
			criteria.add(capacityYearCriterion);
		}						
		if(selectedTerminals.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,selectedTerminals));
		else
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
		List<Refinery> list=criteria.list();
		tx.commit();
		session.close();
		return list;
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
		
		if((owners!=null && owners.size()>0) && (operators!=null && operators.size()>0))
		{
			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS,owners);				
			Criterion operatorCriterion=Restrictions.in(RESTRICTION_PROPERTY_CURRENTOPERATOR,operators);			
			criteria.add(Restrictions.or(ownersCriterion, operatorCriterion));
			
		}
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
