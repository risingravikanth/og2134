package com.oganalysis.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.oganalysis.dao.UserDao;
import com.oganalysis.entities.User;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;
	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Session session=null;
		User user=null;
		try
		{
			session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();			
			tx.begin();
			Query query=session.createQuery("from User where email=:email");
			query.setParameter("email",email);
			user=(User)query.uniqueResult();
			tx.commit();
		}
		finally
		{
			if(null!=session)
				session.close();
		}			
		return user;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
