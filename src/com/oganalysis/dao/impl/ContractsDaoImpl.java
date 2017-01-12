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

public class ContractsDaoImpl implements ContractsDao {

	private SessionFactory sessionFactory;
//	@Override
	public List<Contracts> getContractsCriteriaData(Map<String,List<String>> selectedOptions,List<String> exportTerminals,int startDate, int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Contracts.class);		
		createFiltersCriteria(selectedOptions, criteria);
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion yearCriterion=Restrictions.between("year", startDate, endDate);
			criteria.add(yearCriterion);
		}					
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal",exportTerminals));
		else
		{
			List<String> terminals=new ArrayList<String>();
			terminals.add("");
			criteria.add(Restrictions.in("exportTerminal",terminals));
		}
		List<Contracts> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
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
		List<String> exportTerminals=getExportTerminals(startDate, endDate);
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		else
		{
			exportTerminals=new ArrayList<String>();
			exportTerminals.add("");
			criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		}
		criteria.setProjection(Projections.distinct(Projections.property("exportCompany")));
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
		List<String> exportTerminals=getExportTerminals(startDate, endDate);
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		else
		{
			exportTerminals=new ArrayList<String>();
			exportTerminals.add("");
			criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		}
		criteria.setProjection(Projections.distinct(Projections.property("exportCountry")));
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
		List<String> exportTerminals=getExportTerminals(startDate, endDate);
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal",exportTerminals));
		else
		{
			exportTerminals=new ArrayList<String>();
			exportTerminals.add("");
			criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		}
		criteria.setProjection((Projections.distinct(Projections.property("exportTerminal"))));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getExportCompanyTerminals(String company,List<String> exportTerminals){
//			Map<String, List<String>> selectedOptions,int startDate,int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);
//		createFiltersCriteria(selectedOptions, criteria);
//		List<String> exportTerminals=getExportTerminals(startDate, endDate);
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal",exportTerminals));
		else
		{
				exportTerminals=new ArrayList<String>();
				exportTerminals.add("");
				criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		}	
		criteria.add(Restrictions.eq("exportCompany", company));
		criteria.setProjection((Projections.distinct(Projections.property("exportTerminal"))));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	@Override
	public List<String> getExportCountryTerminals(String country,List<String> exportTerminals){
//			Map<String, List<String>> selectedOptions,int startDate,int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(ContractsFilter.class);
//		createFiltersCriteria(selectedOptions, criteria);
//		List<String> exportTerminals=getExportTerminals(startDate, endDate);
		if(exportTerminals.size()>0)
			criteria.add(Restrictions.in("exportTerminal",exportTerminals));
		else
		{
				exportTerminals=new ArrayList<String>();
				exportTerminals.add("");
				criteria.add(Restrictions.in("exportTerminal", exportTerminals));
		}	
		criteria.add(Restrictions.eq("exportCountry", country));
		criteria.setProjection((Projections.distinct(Projections.property("exportTerminal"))));
		List<String> list=criteria.list();
		tx.commit();
		session.close();
		return list;
	}
	
	@Override
	public List<String> getExportTerminals(int startDate,int endDate) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Criteria criteria=session.createCriteria(Contracts.class);		
		if(startDate!=0 && endDate!=0)
		{
						
			Criterion yearCriterion=Restrictions.between("year", startDate, endDate);
			criteria.add(yearCriterion);
		}					
		criteria.setProjection((Projections.distinct(Projections.property("exportTerminal"))));
		List<String> list=criteria.list();
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
		query.setParameterList("exportCountries", exportCountries);
		List<String> importCountries=query.list();
		tx.commit();
		return importCountries;
	}
	@Override
	public List<String> getImportCompanies(List<String> exportCompanies) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		tx.begin();
		Query query=session.createQuery("select distinct importCompany from ContractsFilter where exportCompany in (:exportCompanies)");
		query.setParameterList("exportCompanies", exportCompanies);
		List<String> importCountries=query.list();
		tx.commit();
		return importCountries;
	}
	private void createFiltersCriteria(Map<String,List<String>> selectedOptions,Criteria criteria)
	{
		List<String> importCountries=selectedOptions.get("importCountries");
		List<String> exportCountries=selectedOptions.get("exportCountries");
		
		List<String> importCompanies=selectedOptions.get("importCompanies");
		List<String> exportCompanies=selectedOptions.get("exportCompanies");
		
		
		if(importCountries!=null && importCountries.size()>0)
		{
			Criterion importCounrtry=Restrictions.in("importCountry", importCountries);
			criteria.add(importCounrtry);
		}			
		if(exportCountries!=null && exportCountries.size()>0)
		{
			Criterion exportCounrtry=Restrictions.in("exportCountry", exportCountries);
			criteria.add(exportCounrtry);
		}
		
		if(importCompanies!=null && importCompanies.size()>0)
		{
			Criterion importCompany=Restrictions.in("importCompany",importCompanies);
			criteria.add(importCompany);
		}
		if(exportCompanies!=null && exportCompanies.size()>0)
		{
			Criterion exportCompany=Restrictions.in("exportCompany",exportCompanies);
			criteria.add(exportCompany);
		}

	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
}
