package com.oganalysis.dao.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.PdfReportsDao;
import com.oganalysis.entities.Report;

public class PdfReportsDaoImpl implements PdfReportsDao {	
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.sessionFactory=sessionFactory;
	}
	@Override
	public List<Report> getPdfReports(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Report> reports=null;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			tx.begin();
			Criteria criteria=session.createCriteria(Report.class);
			
			List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
			List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
			List<String> sectors=selectedOptions.get(OPTION_SELECTED_SECTORS);
			
			if(countries!=null && countries.size()>0)
			{
				Criterion counrtryCriterion=Restrictions.in(RESTRICTION_PROPERTY_COUNTRY,countries);
				criteria.add(counrtryCriterion);
			}
				
			if(regions!=null && regions.size()>0)
			{
				Criterion regionCriterion=Restrictions.in(RESTRICTION_PROPERTY_REGION,regions);
				criteria.add(regionCriterion);
			}
			
			if(sectors!=null && sectors.size()>0)
			{
				Criterion sectorCriterion=Restrictions.in(RESTRICTION_PROPERTY_SECTOR,sectors);
				criteria.add(sectorCriterion);
			}
			
			reports=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return reports;
	}

}
