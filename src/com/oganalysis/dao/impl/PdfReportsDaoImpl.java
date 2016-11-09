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

import com.oganalysis.dao.PdfReportsDao;
import com.oganalysis.entities.Report;

public class PdfReportsDaoImpl implements PdfReportsDao {
	private HibernateTemplate hibernateTemplate;
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}
	@Override
	public List<Report> getPdfReports(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Report.class);
		
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
		List<String> sectors=selectedOptions.get("sectors");
		
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
		
		if(sectors!=null && sectors.size()>0)
		{
			Criterion sectorCriterion=Restrictions.in("sector", selectedOptions.get("sectors"));
			criteria.add(sectorCriterion);
		}
		
//		Criterion criterion1=Restrictions.eq("country", c1);
//		Criterion criterion2=Restrictions.eq("country", c2);
//		
//		Criterion criterion3=Restrictions.eq("region", r1);
//		Criterion criterion4=Restrictions.eq("region", r2);
//		
//		Criterion countries=Restrictions.or(criterion1, criterion2);
//		Criterion region=Restrictions.or(criterion3, criterion4);
		
		
		
		
		
		List<Report> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

}
