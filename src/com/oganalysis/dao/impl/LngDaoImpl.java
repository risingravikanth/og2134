package com.oganalysis.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;
import com.oganalysis.entities.LngLiquefaction;
import com.oganalysis.entities.LngRegasification;

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
	public List<Lng> getRegasificationCriteriaData(Map<String,List<String>> selectedOptions,List<String> terminals,int startDate,int endDate) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngRegasification.class);
		createFiltersCriteria(selectedOptions, criteria);
		
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		if(terminals.size()>0)
			criteria.add(Restrictions.in("name",terminals));
		else
		{
			List<String> emptyTerminals=new ArrayList<String>();
			emptyTerminals.add("");
			criteria.add(Restrictions.in("name",emptyTerminals));
		}
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<Lng> getLiquefactionCriteriaData(Map<String,List<String>> selectedOptions,List<String> terminals,int startDate,int endDate) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngLiquefaction.class);
		createFiltersCriteria(selectedOptions, criteria); //need to change method definition			
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		if(terminals.size()>0)
			criteria.add(Restrictions.in("name",terminals));
		else
		{
			List<String> emptyTerminals=new ArrayList<String>();
			emptyTerminals.add("");
			criteria.add(Restrictions.in("name",emptyTerminals));
		}
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<Lng> getRegasificationCriteriaData(List<String> terminals) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngRegasification.class);
		criteria.add(Restrictions.in("name",terminals));				
		
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<Lng> getLiquefactionCriteriaData(List<String> terminals) {
//		List<Object> list=hibernateTemplate.find("from Exploration");
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngLiquefaction.class);
		criteria.add(Restrictions.in("name",terminals));
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
		
		List<String> owners=criteria.list();
				
		tx.commit();
		session.close();
		return owners;
	}



	@Override
	public List<Lng> getTerminalData(String terminalName, String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		 Criteria criteria=null;
		if(type.equalsIgnoreCase(REGASIFICATION))
		 criteria=session.createCriteria(LngRegasification.class);
		else
			criteria=session.createCriteria(LngLiquefaction.class);	
			
		Criterion terminalCriterion=Restrictions.eq("name",terminalName);
		criteria.add(terminalCriterion);
		
		List<Lng> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get("countries");
		List<String> regions=selectedOptions.get("regions");
		List<String> locations=selectedOptions.get("locations");		
		List<String> owners=selectedOptions.get("owners");
		List<String> operators=selectedOptions.get("operators");
		List<String> statuses=selectedOptions.get("statuses");
		List<String> offonShores=selectedOptions.get("offonshores");
		List<String> types=selectedOptions.get("types");	
		
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
		if(locations!=null && locations.size()>0)
		{
			Criterion locationCriterion=Restrictions.in("area",locations);
			criteria.add(locationCriterion);
		}
		if((owners!=null && owners.size()>0) && (operators!=null && operators.size()>0))
		{
			Criterion ownersCriterion=Restrictions.in("equityPartners",owners);				
			Criterion operatorCriterion=Restrictions.in("operator",operators);			
			criteria.add(Restrictions.or(ownersCriterion, operatorCriterion));
			
		}
		else if(owners!=null && owners.size()>0)
		{
			Criterion ownersCriterion=Restrictions.in("equityPartners",owners);
			criteria.add(ownersCriterion);
		}
		else if(operators!=null && operators.size()>0)
		{
			Criterion operatorCriterion=Restrictions.in("operator",operators);
			criteria.add(operatorCriterion);
		}
		if(statuses!=null && statuses.size()>0)
		{
			Criterion statusesCriterion=Restrictions.in("status",statuses);
			criteria.add(statusesCriterion);
		}
		if(offonShores!=null && offonShores.size()>0)
		{
			Criterion offonShoresCriterion=Restrictions.in("OnshoreOrOffshore",offonShores);
			criteria.add(offonShoresCriterion);
		}
		if(types!=null && types.size()>0)
		{
			Criterion typeCriterion=Restrictions.in("type",types);
			criteria.add(typeCriterion);
		}
	}

	@Override
	public List<String> getLiqueTerminals(int startDate, int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngLiquefaction.class);				
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getRegasTerminals(int startDate, int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngRegasification.class);				
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion capacityYearCriterion=Restrictions.between("capacityYear", startDate, endDate);
			criteria.add(capacityYearCriterion);
		}					
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<String> getCompanyTerminals(String company,List<String> terminals,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);							
		criteria.add(Restrictions.eq("equityPartners", company));
		if(terminals.size()>0)
			criteria.add(Restrictions.in("name",terminals));
		else
		{
			List<String> emptyTerminals=new ArrayList<String>();
			emptyTerminals.add("");
			criteria.add(Restrictions.in("name",emptyTerminals));
		}
		criteria.add(Restrictions.eq("type",type));
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
//	@Override
//	public List<String> getCompanyTerminals(String company,Map<String,List> selectedOptions,String type) {
//		// TODO Auto-generated method stub
//		List<String> companyTerminals=getCompanyTerminals(company,selectedOptions,0,0, type);
//		return companyTerminals;
//	}

	@Override
	public List<String> getCountryTerminals(String country,List<String> terminals,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		if(terminals.size()>0)
				criteria.add(Restrictions.in("name",terminals));
		else
		{
				List<String> emptyTerminals=new ArrayList<String>();
				emptyTerminals.add("");
				criteria.add(Restrictions.in("name",terminals));
		}			
		criteria.add(Restrictions.eq("country", country));
		criteria.add(Restrictions.eq("type",type));
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<LngFilter> getLngFilter(String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		criteria.add(Restrictions.eq("type",type));		
		List<LngFilter> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedCompanies(Map<String, List<String>> selectedOptions,int startDate,int endDate,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
				
		if(startDate!=0 && endDate!=0)
		{
			List<String> terminals=null;
			if(null!=type && type.equalsIgnoreCase(LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			
			if(terminals.size()>0)
				criteria.add(Restrictions.in("name",terminals));
			else
			{
				List<String> emptyTerminals=new ArrayList<String>();
				emptyTerminals.add("");
				criteria.add(Restrictions.in("name",emptyTerminals));
			}	
		}				
		criteria.add(Restrictions.eq("type",type));		
		criteria.setProjection(Projections.distinct(Projections.property("equityPartners")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedCountries(Map<String, List<String>> selectedOptions,int startDate,int endDate,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		
		if(startDate!=0 && endDate!=0)
		{
			List<String> terminals=null;
			if(null!=type && type.equalsIgnoreCase(LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			if(terminals.size()>0)
				criteria.add(Restrictions.in("name",terminals));
			else
			{
				terminals.add("");
				criteria.add(Restrictions.in("name",terminals));
			}	
		}
		criteria.add(Restrictions.eq("type",type));	
		criteria.setProjection(Projections.distinct(Projections.property("country")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getSelectedTerminals(Map<String, List<String>> selectedOptions,int startDate,int endDate,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		createFiltersCriteria(selectedOptions, criteria);		
		if(startDate!=0 && endDate!=0)
		{
			List<String> terminals=null;
			if(null!=type && type.equalsIgnoreCase(LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			if(terminals.size()>0)
				criteria.add(Restrictions.in("name",terminals));
			else
			{
				terminals.add("");
				criteria.add(Restrictions.in("name",terminals));
			}	
		}
		criteria.add(Restrictions.eq("type",type));	
		criteria.setProjection(Projections.distinct(Projections.property("name")));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<LngFilter> getTerminalCompanies(String terminal,String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(LngFilter.class);
		criteria.add(Restrictions.eq("name",terminal));		
		criteria.add(Restrictions.eq("type",type));
		List<LngFilter> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getSelectedTerminals(Map<String, List<String>> selectedOptions,String type) {
		// TODO Auto-generated method stub
		List<String> terminals=getSelectedTerminals(selectedOptions, 0,0, type);
		return terminals;
	}

	@Override
	public List<Integer> getSelectedYears(int startDate, int endDate,String type) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=null;
		if(type.equalsIgnoreCase(LIQUEFACTION))
			criteria=session.createCriteria(LngLiquefaction.class);
		else
			criteria=session.createCriteria(LngRegasification.class);
		
		criteria.add(Restrictions.between("capacityYear",startDate,endDate));
		criteria.setProjection(Projections.distinct(Projections.property("capacityYear")));			
		List<Integer> listYears=criteria.list();
		tx.commit();
		session.close();
		return listYears;
	}
			
	
}
