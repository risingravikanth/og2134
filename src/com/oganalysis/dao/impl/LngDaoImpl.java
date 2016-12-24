package com.oganalysis.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
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
		createFiltersCriteria(selectedOptions, criteria);
		
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
		createFiltersCriteria(selectedOptions, criteria);
			
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
	
	/* Method is to get the list of locations(i.e area) to display in filter*/
	@Override
	public List<String> getLocations() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class).setProjection(Projections.distinct(Projections.property("area")));
		
		List<String> locations=criteria.list();
				
		tx.commit();
		session.close();
		return locations;
	}

	@Override
	public List<String> getOperator() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class).setProjection(Projections.distinct(Projections.property("operator")));
		
		List<String> operators=criteria.list();
				
		tx.commit();
		session.close();
		return operators;
	}
	@Override
	public List<String> getOwners() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class).setProjection(Projections.distinct(Projections.property("equityPartners")));
//		criteria.add(Restrictions.ne("equityPartners",""));
//		criteria.add(Restrictions.ne("equityPartners",null));
		List<String> owners=criteria.list();
				
		tx.commit();
		session.close();
		return owners;
	}

	@Override
	public List<Lng> getLiquefactionCriteriaData(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> liquefaction=getLiquefactionCriteriaData(selectedOptions, 0,0);
		return liquefaction;
	}

	@Override
	public List<Lng> getRegasificationCriteriaData(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> regasification=getRegasificationCriteriaData(selectedOptions, 0,0);
		return regasification;
	}

	@Override
	public List<Lng> getTerminalData(String terminalName, String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);
				
		Criterion regasificationCriterion=Restrictions.eq("type",type);		
		criteria.add(regasificationCriterion);
		
		Criterion terminalCriterion=Restrictions.eq("name",terminalName);
		criteria.add(terminalCriterion);
		
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	private void createFiltersCriteria(Map<String,List> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
		List<String> locations=selectedOptions.get("locations");
		List<String> terminals=selectedOptions.get("terminals");		
		List<String> statuses=selectedOptions.get("statuses");
		List<String> offonShores=selectedOptions.get("offonshores");
		List<String> types=selectedOptions.get("types");	
		
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
		if(locations!=null && locations.size()>0)
		{
			Criterion locationCriterion=Restrictions.in("area",selectedOptions.get("locations"));
			criteria.add(locationCriterion);
		}
		if(terminals!=null && terminals.size()>0)
		{
			Criterion terminalNameCriterion=Restrictions.in("name",selectedOptions.get("terminals"));
			criteria.add(terminalNameCriterion);
		}
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in("status",selectedOptions.get("statuses"));
			criteria.add(statusesCriterion);
		}
		if(offonShores!=null && offonShores.size()>0)
		{
			Criterion offonShoresCriterion=Restrictions.in("OnshoreOrOffshore",selectedOptions.get("offonshores"));
			criteria.add(offonShoresCriterion);
		}
		if(types!=null && types.size()>0)
		{
			Criterion typeCriterion=Restrictions.in("type",selectedOptions.get("types"));
			criteria.add(typeCriterion);
		}
	}

	@Override
	public List<Lng> getLiqueTerminalCompany() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);
		criteria.add(Restrictions.eq("type",LIQUEFACTION));
		criteria.add(Restrictions.ne("equityPartners"," "));
//		criteria.add(Restrictions.ne("equityPartners",null));
		List<Lng> terminalsCompanies=criteria.list();
				
		tx.commit();
		session.close();
		return terminalsCompanies;
		
	}

	@Override
	public List<Lng> getRegasTerminalCompany() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);
		criteria.add(Restrictions.eq("type",REGASIFICATION));
		criteria.add(Restrictions.ne("equityPartners"," "));		
		List<Lng> terminalsCompanies=criteria.list();
				
		tx.commit();
		session.close();
		return terminalsCompanies;
		
	}
	@Override
	public List<Lng> getTerminalCompany() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);		
		criteria.add(Restrictions.ne("equityPartners"," "));		
		List<Lng> terminalsCompanies=criteria.list();
				
		tx.commit();
		session.close();
		return terminalsCompanies;
		
	}
	@Override
	public List<Lng> getTerminalOperator() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Lng.class);		
		criteria.add(Restrictions.ne("operator"," "));		
		List<Lng> terminalsOperators=criteria.list();
				
		tx.commit();
		session.close();
		return terminalsOperators;
		
	}
}
