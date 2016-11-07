package com.oganalysis.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;

public class LngDaoImpl implements LngDao {
	
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public List<Lng> getRegasificationCriteriaData(
			Map<String, List> selectedOptions,int startDate,int endDate) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);
		
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
		
		if(countries!=null && countries.size()>0)
		{
			Criterion counrtryCriterion=Restrictions.in("country", selectedOptions.get("countries"));
			criteria.add(counrtryCriterion);
		}
			
		if(regions!=null && regions.size()>0)
		{
			Criterion regionCriterion=Restrictions.in("region", selectedOptions.get("regions"));
			criteria.add(regionCriterion);
		}
		
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		Criterion regasificationCriterion=Restrictions.eq("type",REGASIFICATION);
		criteria.add(regasificationCriterion);
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<Lng> getLiquefactionCriteriaData(
			Map<String, List> selectedOptions,int startDate,int endDate) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);
		
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
		
		if(countries!=null && countries.size()>0)
		{
			Criterion counrtryCriterion=Restrictions.in("country", selectedOptions.get("countries"));
			criteria.add(counrtryCriterion);
		}
			
		if(regions!=null && regions.size()>0)
		{
			Criterion regionCriterion=Restrictions.in("region", selectedOptions.get("regions"));
			criteria.add(regionCriterion);
		}
		
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		Criterion regasificationCriterion=Restrictions.eq("type",LIQUEFACTION);
		criteria.add(regasificationCriterion);
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
//	@Override
//	public List<Object> getOGAnalysisData() {
//		
////		List<Object> list=hibernateTemplate.find("from Exploration");
//		Session session=sessionFactory.openSession();
//		Transaction tx=session.beginTransaction();
//		tx.begin();
//		Criteria criteria=session.createCriteria(Lng.class);
//		
////		Criterion counrtryCriterion=Restrictions.in("country", selectedOptions.get("countries"));
////		Criterion regionCriterion=Restrictions.in("region", selectedOptions.get("regions"));
////		
//////		Criterion criterion1=Restrictions.eq("country", c1);
//////		Criterion criterion2=Restrictions.eq("country", c2);
//////		
//////		Criterion criterion3=Restrictions.eq("region", r1);
//////		Criterion criterion4=Restrictions.eq("region", r2);
//////		
//////		Criterion countries=Restrictions.or(criterion1, criterion2);
//////		Criterion region=Restrictions.or(criterion3, criterion4);
////		
////		criteria.add(counrtryCriterion);
////		criteria.add(regionCriterion);
////		
//		
//		List<Object> list=criteria.list();
//		tx.commit();
//		session.close();
//		return list;
//	}

}
