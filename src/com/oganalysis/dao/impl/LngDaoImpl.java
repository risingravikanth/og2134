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

import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;
import com.oganalysis.entities.LngLiquefaction;
import com.oganalysis.entities.LngRegasification;

public class LngDaoImpl implements LngDao {
	
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {		
		this.sessionFactory=sessionFactory;
	}
		
	/* Method is to get the list of locations(i.e area) to display in filter*/
	@Override
	public List<String> getLocations() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct area from Lng order by area asc");
		List<String> locations=(List<String>)query.list();
		tx.commit();
		session.close();
		return locations;
	}

	@Override
	public List<String> getOperators() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct operator from Lng where operator!=' ' order by operator asc");
		List<String> operators=(List<String>)query.list();				
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
		Query query=session.createQuery("select distinct equityPartners from Lng where equityPartners!=' ' order by equityPartners asc");
		List<String> owners=(List<String>)query.list();				
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
		if(type.equalsIgnoreCase(LNG_REGASIFICATION))
			criteria=session.createCriteria(LngRegasification.class);
		else
			criteria=session.createCriteria(LngLiquefaction.class);	
			
		Criterion terminalCriterion=Restrictions.eq(RESTRICTION_PROPERTY_NAME,terminalName);
		criteria.add(terminalCriterion);		
		List<Lng> list=(List<Lng>)criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> countries=selectedOptions.get(OPTION_SELECTED_COUNTRIES);
		List<String> regions=selectedOptions.get(OPTION_SELECTED_REGIONS);
		List<String> locations=selectedOptions.get(OPTION_SELECTED_LOCATIONS);		
		List<String> owners=selectedOptions.get(OPTION_SELECTED_OWNERS);
		List<String> operators=selectedOptions.get(OPTION_SELECTED_OPERATORS);
		List<String> statuses=selectedOptions.get(OPTION_SELECTED_STATUSES);
		List<String> offonShores=selectedOptions.get(OPTION_SELECTED_OFFONSHORES);
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
		if(locations!=null && locations.size()>0)
		{
			Criterion locationCriterion=Restrictions.in(RESTRICTION_PROPERTY_AREA,locations);
			criteria.add(locationCriterion);
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
		if(offonShores!=null && offonShores.size()>0)
		{
			Criterion offonShoresCriterion=Restrictions.in(RESTRICTION_PROPERTY_ONSHOREOROFFSHORE,offonShores);
			criteria.add(offonShoresCriterion);
		}
		if(types!=null && types.size()>0)
		{
			Criterion typeCriterion=Restrictions.in(RESTRICTION_PROPERTY_TYPE,types);
			criteria.add(typeCriterion);
		}
	}

	@Override
	public List<String> getLiqueTerminals(int startYear, int endYear) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		List<String> list=new ArrayList<String>();					
		if(startYear!=0 && endYear!=0)
		{
			Query query=session.createQuery("select distinct name from LngLiquefaction where capacityYear between :startYear and :endYear");
			query.setParameter("startYear", startYear);
			query.setParameter("endYear", endYear);
			list=(List<String>)query.list();

		}					
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getRegasTerminals(int startYear, int endYear) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		List<String> list=new ArrayList<String>();			
		if(startYear!=0 && endYear!=0)
		{
						
			Query query=session.createQuery("select distinct name from LngRegasification where capacityYear between :startYear and :endYear");
			query.setParameter("startYear", startYear);
			query.setParameter("endYear", endYear);
			list=(List<String>)query.list();
		}					
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
		Query query=session.createQuery("from LngFilter where type=:type");		
		query.setParameter("type", type);
		List<LngFilter> list=(List<LngFilter>)query.list();
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
			if(null!=type && type.equalsIgnoreCase(LNG_LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			
			if(terminals.size()>0)
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
			else
			{
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
			}	
		}				
		criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_TYPE,type));		
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_EQUITYPARTNER)));
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
			if(null!=type && type.equalsIgnoreCase(LNG_LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			if(terminals.size()>0)
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
			else
			{				
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
			}	
		}
		criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_TYPE,type));	
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_COUNTRY)));
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
			if(null!=type && type.equalsIgnoreCase(LNG_LIQUEFACTION))
				terminals=getLiqueTerminals(startDate, endDate);
			else
				terminals=getRegasTerminals(startDate, endDate);
			if(terminals.size()>0)
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,terminals));
			else
				criteria.add(Restrictions.in(RESTRICTION_PROPERTY_NAME,getEmptyList()));
				
		}
		criteria.add(Restrictions.eq(RESTRICTION_PROPERTY_TYPE,type));	
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_NAME)));
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
		Query query=session.createQuery("from LngFilter where name=:terminal and type=:type");
		query.setParameter("terminal",terminal);
		query.setParameter("type", type);
		List<LngFilter> list=(List<LngFilter>)query.list();
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
	private List<String> getEmptyList()
	{
		List<String> emptyList=new ArrayList<String>();
		emptyList.add(BLANK);
		return emptyList;
	}

	@Override
	public List<Lng> getLng(int year, String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=null;
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
			query=session.createQuery("from LngLiquefaction where capacityYear=:year");
		else
			query=session.createQuery("from LngRegasification where capacityYear=:year");
		query.setParameter("year",year);		
		List<Lng> list=(List<Lng>)query.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getCountries(String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct country from LngFilter where type=:type order by country asc");		
		query.setParameter("type", type);
		List<String> list=(List<String>)query.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getCountryTerminals(String country) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct name from LngFilter where country=:country order by name asc");		
		query.setParameter("country",country);
		List<String> list=(List<String>)query.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getCompanies(String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct equityPartners from LngFilter where type=:type and equityPartners!=' ' order by equityPartners asc");		
		query.setParameter("type", type);
		List<String> list=(List<String>)query.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public List<String> getCompanyTerminals(String company) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct name from LngFilter where equityPartners=:company order by name asc");		
		query.setParameter("company",company);
		List<String> list=(List<String>)query.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getTerminals(String type) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct name from LngFilter where type=:type order by name asc");		
		query.setParameter("type", type);
		List<String> list=(List<String>)query.list();
		tx.commit();
		session.close();
		return list;
	}
	
}
