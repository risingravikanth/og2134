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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.oganalysis.dao.ContractsDao;
import com.oganalysis.entities.Contracts;
import com.oganalysis.entities.ContractsFilter;

import static com.oganalysis.constants.ApplicationConstants.*;

public class ContractsDaoImpl implements ContractsDao {

	private SessionFactory sessionFactory;
	@Override
	public List<String> getSelectedExportCompanies(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);		
		createFiltersCriteria(selectedOptions, criteria);
		List<String> contractIndicators=getContractIndicators(startDate, endDate);
		if(contractIndicators.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, contractIndicators));
		else
		{			
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, getEmptyList()));
		}
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_EXPORTCOMPANY)));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedExportCountries(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);		
		createFiltersCriteria(selectedOptions, criteria);
		List<String> contractIndicators=getContractIndicators(startDate, endDate);
		if(contractIndicators.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, contractIndicators));
		else
		{
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, getEmptyList()));
		}
		criteria.setProjection(Projections.distinct(Projections.property(RESTRICTION_PROPERTY_EXPORTCOUNTRY)));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedExportTerminals(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		List<String> contractIndicators=getContractIndicators(startDate, endDate);
		if(contractIndicators.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR,contractIndicators));
		else
		{
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, getEmptyList()));
		}
		criteria.setProjection((Projections.distinct(Projections.property(RESTRICTION_PROPERTY_EXPORTTERMINAL))));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getSelectedContractIndicators(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);
		createFiltersCriteria(selectedOptions, criteria);
		List<String> contractIndicators=getContractIndicators(startDate, endDate);
		if(contractIndicators.size()>0)
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR,contractIndicators));
		else
		{
			criteria.add(Restrictions.in(RESTRICTION_PROPERTY_CONTRACTINDICATOR, getEmptyList()));
		}
		criteria.setProjection((Projections.distinct(Projections.property(RESTRICTION_PROPERTY_CONTRACTINDICATOR))));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}			
	@Override
	public List<String> getContractIndicators(int startDate,int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct contractIndicator from Contracts where year between :startYear and :endYear");
		query.setParameter("startYear", startDate);
		query.setParameter("endYear",endDate);
		List<String> list=query.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<String> getImportCountries(List<String> exportCountries) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct importCountry from ContractsFilter where exportCountry in (:exportCountries)");
		query.setParameterList(RESTRICTION_PROPERTY_EXPORTCOUNTRIES, exportCountries);
		List<String> importCountries=query.list();
		tx.commit();
		session.close();
		return importCountries;
	}
	@Override
	public List<String> getImportCompanies(List<String> exportCompanies) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct importCompany from ContractsFilter where exportCompany in (:exportCompanies)");
		query.setParameterList(RESTRICTION_PROPERTY_EXPORTCOMPANIES, exportCompanies);
		List<String> importCountries=query.list();
		tx.commit();
		return importCountries;
	}
	@Override
	public List<String> getExportCompanies() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct exportCompany from ContractsFilter order by exportCompany asc");		
		List<String> exportCompanies=query.list();
		tx.commit();
		session.close();
		return exportCompanies;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> importCountries=selectedOptions.get(OPTION_SELECTED_IMPORT_COUNTRIES);
		List<String> exportCountries=selectedOptions.get(OPTION_SELECTED_EXPORT_COUNTRIES);
		
		List<String> importCompanies=selectedOptions.get(OPTION_SELECTED_IMPORT_COMPANIES);
		List<String> exportCompanies=selectedOptions.get(OPTION_SELECTED_EXPORT_COMPANIES);
		
		
		if(importCountries!=null && importCountries.size()>0)
		{
			Criterion importCounrtry=Restrictions.in(RESTRICTION_PROPERTY_IMPORTCOUNTRY, importCountries);
			criteria.add(importCounrtry);
		}			
		if(exportCountries!=null && exportCountries.size()>0)
		{
			Criterion exportCounrtry=Restrictions.in(RESTRICTION_PROPERTY_EXPORTCOUNTRY, exportCountries);
			criteria.add(exportCounrtry);
		}
		
		if(importCompanies!=null && importCompanies.size()>0)
		{
			Criterion importCompany=Restrictions.in(RESTRICTION_PROPERTY_IMPORTCOMPANY,importCompanies);
			criteria.add(importCompany);
		}
		if(exportCompanies!=null && exportCompanies.size()>0)
		{
			Criterion exportCompany=Restrictions.in(RESTRICTION_PROPERTY_EXPORTCOMPANY,exportCompanies);
			criteria.add(exportCompany);
		}

	}
	@Override
	public List<String> getCompanyContractIndicators(String company) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportCompany=:company order by contractIndicator asc");
		query.setParameter("company",company);
		List<String> contractIndicators=(List<String>)query.list();
		tx.commit();
		session.close();
		return contractIndicators;
	}
	@Override
	public List<Contracts> getContractIndicators(int year) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("from Contracts where year=:year");
		query.setParameter("year",year);
		List<Contracts> contractIndicators=(List<Contracts>)query.list();
		tx.commit();
		session.close();
		return contractIndicators;
	}
	@Override
	public List<String> getExportCountries() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct exportCountry from ContractsFilter order by exportCountry asc");		
		List<String> exportCompanies=query.list();
		tx.commit();
		session.close();
		return exportCompanies;
	}
	@Override
	public List<String> getCountryContractIndicators(String country) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportCountry=:country order by contractIndicator asc");
		query.setParameter("country",country);
		List<String> contractIndicators=(List<String>)query.list();
		tx.commit();
		session.close();
		return contractIndicators;
	}
	@Override
	public List<String> getTerminalContractIndicators(String terminal) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportTerminal=:terminal order by contractIndicator asc");
		query.setParameter("terminal",terminal);
		List<String> contractIndicators=(List<String>)query.list();
		tx.commit();
		session.close();
		return contractIndicators;
	}
	@Override
	public List<String> getExportTerminals() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct exportTerminal from ContractsFilter order by exportTerminal asc ");		
		List<String> exportCompanies=query.list();
		tx.commit();
		session.close();
		return exportCompanies;
	}
	private List<String> getEmptyList()
	{
		List<String> emptyList=new ArrayList<String>();
		emptyList.add(BLANK);
		return emptyList;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
								
}
