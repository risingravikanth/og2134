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
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.ExplorationDao;
import com.oganalysis.entities.Exploration;
import com.oganalysis.entities.PipeLine;

public class ExplorationDaoImpl implements ExplorationDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<Exploration> getSelectedExploration(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Exploration> selectedExploration=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedExploration=new ArrayList<Exploration>();
			tx.begin();
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);			
			selectedExploration=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedExploration;
	}
	
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
		List<String> basins=selectedOptions.get(OPTION_SELECTED_BASINS);		
		List<String> owners=selectedOptions.get(OPTION_SELECTED_OWNERS);
		List<String> operators=selectedOptions.get(OPTION_SELECTED_OPERATORS);
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
		if(basins!=null && basins.size()>0)
		{
			Criterion basinCriterion=Restrictions.in(RESTRICTION_PROPERTY_BASIN,basins);
			criteria.add(basinCriterion);
		}
		
//		if((owners!=null && owners.size()>0) && (operators!=null && operators.size()>0))
//		{
//			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_EQUITYPARTNER,owners);				
//			Criterion operatorCriterion=Restrictions.in(RESTRICTION_PROPERTY_OPERATOR,operators);			
//			criteria.add(Restrictions.or(ownersCriterion, operatorCriterion));
//			
//		}
		if(owners!=null && owners.size()>0)
		{
			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_EQUITYPARTNER,owners);
			criteria.add(ownersCriterion);
		}
		if(operators!=null && operators.size()>0)
		{
			Criterion operatorCriterion=Restrictions.in(RESTRICTION_PROPERTY_OPERATOR,operators);
			criteria.add(operatorCriterion);
		}
		
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in(RESTRICTION_PROPERTY_STATUS,statuses);
			criteria.add(statusesCriterion);
		}
		
		if(types!=null && types.size()>0)
		{
			Criterion typeCriterion=Restrictions.in(RESTRICTION_PROPERTY_ONSHOREOROFFSHORE,types);
			criteria.add(typeCriterion);
		}
	}

	@Override
	public List<String> getBasins(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> basins=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			basins=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_BASIN)));
			basins=criteria.list();				
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return basins;
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
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_EQUITYPARTNER)));
			owners=criteria.list();			
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
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_OPERATOR)));
			operators=criteria.list();				
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
			Query query=session.createQuery("select distinct region from Exploration where region!=' ' order by region asc");
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
	public List<String> getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> countries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countries=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Exploration.class);
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
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_STATUS)));
			status=criteria.list();			
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return status;
	}

	@Override
	public List<String> getType(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> types=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			types=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(Exploration.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_TYPE)));
			types=criteria.list();				
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return types;
	}
	
}
