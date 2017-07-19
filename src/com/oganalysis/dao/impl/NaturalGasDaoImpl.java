package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_LOCATIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_OPERATORS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_OWNERS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_REGIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_STATUSES;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_CURRENTOPERATOR;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_LOCATION;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_NAME;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_REGION;
import static com.oganalysis.constants.ApplicationConstants.RESTRICTION_PROPERTY_STATUS;

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
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oganalysis.dao.NaturalGasDao;
import com.oganalysis.dao.OGDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.NaturalGas;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class NaturalGasDaoImpl implements NaturalGasDao {
	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<NaturalGas> getNaturalGas(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<NaturalGas> naturalGas=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();			
			tx.begin();
			Criteria criteria=session.createCriteria(NaturalGas.class);
			createFiltersCriteria(selectedOptions, criteria);											
			naturalGas=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return naturalGas;
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
