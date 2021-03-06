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

import com.oganalysis.dao.PipeLineDao;
import com.oganalysis.entities.PipeLine;

public class PipeLineDaoImpl implements PipeLineDao {
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}


	@Override
	public List<String> getCommodities(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> commodities=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			commodities=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COMMODITY)));
			commodities=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return commodities;
	}


	@Override
	public List<String> getStartPoints(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> startPoints=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			startPoints=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_STARTPOINT)));
			startPoints=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return startPoints;
	}


	@Override
	public List<String> getEndPoints(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> endPoints=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			endPoints=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
			createFiltersCriteria(selectedOptions, criteria);
			criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_ENDPOINT)));
			endPoints=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return endPoints;
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
			Query query=session.createQuery("select distinct region from PipeLine where region!=' ' order by region asc");
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
			Criteria criteria=session.createCriteria(PipeLine.class);
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
	public List<String> getStatus(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> status=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			status=new ArrayList<String>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
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
	public List<PipeLine> getSelectedPipeLines(
			Map<String, List<String>> selectedOptions,String relation,String type) {
		// TODO Auto-generated method stub
		Session session=null;
		List<PipeLine> selectedPipelines=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedPipelines=new ArrayList<PipeLine>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
			createFiltersCriteria(selectedOptions, criteria);
			if(relation.equalsIgnoreCase(PARENT))							
				criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_PARENT_CHILD,relation));
			
			criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_PIPELINE_TYPE,type));			
			selectedPipelines=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedPipelines;
	}

	@Override
	public List<PipeLine> getNonBlankChildSelectedPipeLines(
			Map<String, List<String>> selectedOptions, String relation,
			String type) {
		// TODO Auto-generated method stub
		Session session=null;
		List<PipeLine> selectedPipelines=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedPipelines=new ArrayList<PipeLine>();
			tx.begin();
			Criteria criteria=session.createCriteria(PipeLine.class);
			createFiltersCriteria(selectedOptions, criteria);								
			criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_PARENT_CHILD,relation));
			criteria.add(Restrictions.ne(RESTRICTION_PROPERTY_SUBPIPELINE,BLANK));
			criteria.add(Restrictions.isNotNull(RESTRICTION_PROPERTY_SUBPIPELINE));
			criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_PIPELINE_TYPE,type));			
			selectedPipelines=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedPipelines;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
		List<String> statuses=selectedOptions.get(OPTION_SELECTED_STATUSES);
		List<String> commodities=selectedOptions.get(OPTION_SELECTED_COMMODITIES);
		List<String> startpoints=selectedOptions.get(OPTION_SELECTED_STARTPOINTS);
		List<String> endpoints=selectedOptions.get(OPTION_SELECTED_ENDPOINTS);
		
		
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
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in(RESTRICTION_PROPERTY_STATUS,statuses);
			criteria.add(statusesCriterion);
		}
		if(commodities!=null && commodities.size()>0)
		{
			Criterion commoditiesCriterion=Restrictions.in(RESTRICTION_PROPERTY_COMMODITY,commodities);
			criteria.add(commoditiesCriterion);
		}
		if(startpoints!=null && startpoints.size()>0)
		{
			Criterion startPointsCriterion=Restrictions.in(RESTRICTION_PROPERTY_STARTPOINT,startpoints);
			criteria.add(startPointsCriterion);
		}
		if(endpoints!=null && endpoints.size()>0)
		{
			Criterion endPointsCriterion=Restrictions.in(RESTRICTION_PROPERTY_ENDPOINT,endpoints);
			criteria.add(endPointsCriterion);
		}
		
	}

}
