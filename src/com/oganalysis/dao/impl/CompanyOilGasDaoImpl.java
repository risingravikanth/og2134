package com.oganalysis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oganalysis.dao.CompanyOilGasDao;
import com.oganalysis.entities.CompanyOilGas;

public class CompanyOilGasDaoImpl implements CompanyOilGasDao {
	private SessionFactory sessionFactory;
	@Override
	public List<CompanyOilGas> getCompanyOilGas(String country,
			String filterType) {
		// TODO Auto-generated method stub
		Session session=null;
		List<CompanyOilGas> companies=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			companies=new ArrayList<CompanyOilGas>();
			tx.begin();
			Query query=session.createQuery("from CompanyOilGas where country=:country and type=:filterType");
			query.setParameter("country",country);
			query.setParameter("filterType",filterType);
			companies=query.list();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return companies;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
