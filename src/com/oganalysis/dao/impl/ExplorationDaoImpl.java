package com.oganalysis.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oganalysis.dao.OGDao;
import com.oganalysis.entities.Exploration;

public class ExplorationDaoImpl implements OGDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<Object> getOGAnalysisCriteriaData(Map<String,List> selectedOptions) {
		// TODO Auto-generated method stub
			
		
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Exploration.class);
		
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
		try
		{
			SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
			
			Criterion dateRange=Restrictions.between("startDate",sfd.parse("2010-01-16"),sfd.parse("2011-03-31"));
			criteria.add(dateRange);
		}
		catch(Exception e)
		{
			System.out.println("Exception in date formatting");
		}
		
//		Criterion criterion1=Restrictions.eq("country", c1);
//		Criterion criterion2=Restrictions.eq("country", c2);
//		
//		Criterion criterion3=Restrictions.eq("region", r1);
//		Criterion criterion4=Restrictions.eq("region", r2);
//		
//		Criterion countries=Restrictions.or(criterion1, criterion2);
//		Criterion region=Restrictions.or(criterion3, criterion4);
		
		
		
		
		
		List<Object> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<Object> getOGAnalysisData() {
		// TODO Auto-generated method stub
			
		
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Exploration.class);
		
//		Criterion counrtryCriterion=Restrictions.in("country", selectedOptions.get("countries"));
//		Criterion regionCriterion=Restrictions.in("region", selectedOptions.get("regions"));
//		
////		Criterion criterion1=Restrictions.eq("country", c1);
////		Criterion criterion2=Restrictions.eq("country", c2);
////		
////		Criterion criterion3=Restrictions.eq("region", r1);
////		Criterion criterion4=Restrictions.eq("region", r2);
////		
////		Criterion countries=Restrictions.or(criterion1, criterion2);
////		Criterion region=Restrictions.or(criterion3, criterion4);
//		
//		criteria.add(counrtryCriterion);
//		criteria.add(regionCriterion);
//		
		
		List<Object> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
}
