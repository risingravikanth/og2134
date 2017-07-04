package com.oganalysis.dao.impl;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oganalysis.dao.CustomerDao;
import com.oganalysis.entities.Customer;

public class CustomerDaoImpl implements CustomerDao {
	private SessionFactory sessionFactory;
	@Override
	public boolean saveCustomer(Map<String, String> customerMap) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Session session=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			Customer cust=new Customer();
			cust.setFirstName(customerMap.get("fname"));
			cust.setLastName(customerMap.get("lname"));
			cust.setMobile(customerMap.get("mobile"));
			cust.setLandline(customerMap.get("landline"));
			cust.setEmail(customerMap.get("email"));
			cust.setMessage(customerMap.get("custMsg"));
			session.save(cust);
			flag=true;
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}	
		return flag;
	}
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
