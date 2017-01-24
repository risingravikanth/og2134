package com.oganalysis.dao.impl;

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
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oganalysis.dao.SupplyDemandDao;
import com.oganalysis.entities.SupplyDemand;
import com.oganalysis.entities.SupplyDemandExport;
import com.oganalysis.entities.SupplyDemandImport;

public class SupplyDemandDaoImpl implements SupplyDemandDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	@Override
	public List<SupplyDemand> getSupplyDemandImport(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
//		Query query=session.createQuery("from SupplyDemandImport sdi where sdi.region in(:regions) and country in (:countries)");
		Criteria criteria=session.createCriteria(SupplyDemandImport.class);
		createFiltersCriteria(selectedOptions,criteria);
		List<SupplyDemand> supplyDemandImport=criteria.list();
		return supplyDemandImport;
	}

	@Override
	public List<SupplyDemand> getSupplyDemandExport(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
//		Query query=session.createQuery("from SupplyDemandExport sde where sde.region in(:regions) and sde.country in (:countries)");
		Criteria criteria=session.createCriteria(SupplyDemandExport.class);
		createFiltersCriteria(selectedOptions,criteria);
		List<SupplyDemand> supplyDemandExport=criteria.list();
		return supplyDemandExport;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
				
		if(countries!=null && countries.size()>0)
		{
			Criterion counrtryCriterion=Restrictions.in("country", countries);
			criteria.add(counrtryCriterion);
		}
			
		if(regions!=null && regions.size()>0)
		{
			Criterion regionCriterion=Restrictions.in("region", regions);
			criteria.add(regionCriterion);
		}
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}