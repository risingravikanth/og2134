package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
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
		else if(owners!=null && owners.size()>0)
		{
			Criterion ownersCriterion=Restrictions.in(RESTRICTION_PROPERTY_EQUITYPARTNER,owners);
			criteria.add(ownersCriterion);
		}
		else if(operators!=null && operators.size()>0)
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
	
}
