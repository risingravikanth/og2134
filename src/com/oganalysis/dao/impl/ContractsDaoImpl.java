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

import com.oganalysis.dao.ContractsDao;
import com.oganalysis.entities.Contracts;
import com.oganalysis.entities.ContractsFilter;

public class ContractsDaoImpl implements ContractsDao {

	private SessionFactory sessionFactory;
	@Override
	public List<String> getSelectedExportCompanies(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedExportCompanies=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedExportCompanies=new ArrayList<String>();
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
			selectedExportCompanies=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return selectedExportCompanies;
	}
	@Override
	public List<String> getSelectedExportCountries(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedExportCountries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedExportCountries=new ArrayList<String>();
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
			selectedExportCountries=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return selectedExportCountries;
	}
	@Override
	public List<String> getSelectedExportTerminals(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedExportTerminals=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedExportTerminals=new ArrayList<String>();
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
			selectedExportTerminals=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return selectedExportTerminals;
	}
	@Override
	public List<String> getSelectedContractIndicators(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> selectedContractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			selectedContractIndicators=new ArrayList<String>();
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
			selectedContractIndicators=criteria.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return selectedContractIndicators;
	}			
	@Override
	public List<String> getContractIndicators(int startDate,int endDate) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> contractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			contractIndicators=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct contractIndicator from Contracts where year between :startYear and :endYear");
			query.setParameter("startYear", startDate);
			query.setParameter("endYear",endDate);
			contractIndicators=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return contractIndicators;
	}
	
	@Override
	public List<String> getImportCountries(List<String> exportCountries) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> importCountries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			importCountries=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct importCountry from ContractsFilter where exportCountry in (:exportCountries)");
			query.setParameterList(RESTRICTION_PROPERTY_EXPORTCOUNTRIES, exportCountries);
			importCountries=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return importCountries;
	}
	@Override
	public List<String> getImportCompanies(List<String> exportCompanies) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> importCompanies=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			importCompanies=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct importCompany from ContractsFilter where exportCompany in (:exportCompanies)");
			query.setParameterList(RESTRICTION_PROPERTY_EXPORTCOMPANIES, exportCompanies);
			importCompanies=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return importCompanies;
	}
	@Override
	public List<String> getExportCompanies() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> exportCompanies=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			exportCompanies=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct exportCompany from ContractsFilter order by exportCompany asc");		
			exportCompanies=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
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
		Session session=null;
		List<String> companyContractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			companyContractIndicators=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportCompany=:company order by contractIndicator asc");
			query.setParameter("company",company);
			companyContractIndicators=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return companyContractIndicators;
	}
	@Override
	public List<Contracts> getContractIndicators(int year) {
		// TODO Auto-generated method stub
		Session session=null;
		List<Contracts> contractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			contractIndicators=new ArrayList<Contracts>();
			tx.begin();
			Query query=session.createQuery("from Contracts where year=:year");
			query.setParameter("year",year);
			contractIndicators=(List<Contracts>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}				
		return contractIndicators;
	}
	@Override
	public List<String> getExportCountries() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> exportCountries=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			exportCountries=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct exportCountry from ContractsFilter order by exportCountry asc");		
			exportCountries=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}		
		return exportCountries;
	}
	@Override
	public List<String> getCountryContractIndicators(String country) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> countryContractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			countryContractIndicators=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportCountry=:country order by contractIndicator asc");
			query.setParameter("country",country);
			countryContractIndicators=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return countryContractIndicators;
	}
	@Override
	public List<String> getTerminalContractIndicators(String terminal) {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> terminalContractIndicators=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			terminalContractIndicators=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct contractIndicator from ContractsFilter where exportTerminal=:terminal order by contractIndicator asc");
			query.setParameter("terminal",terminal);
			terminalContractIndicators=(List<String>)query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return terminalContractIndicators;
	}
	@Override
	public List<String> getExportTerminals() {
		// TODO Auto-generated method stub
		Session session=null;
		List<String> exportCompanies=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			exportCompanies=new ArrayList<String>();
			tx.begin();
			Query query=session.createQuery("select distinct exportTerminal from ContractsFilter order by exportTerminal asc ");		
			exportCompanies=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}	
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
