package com.oganalysis.business.impl;

import com.oganalysis.business.LoginBusinessService;
import com.oganalysis.dao.UserDao;
import com.oganalysis.entities.User;

public class LoginBusinessServiceImpl implements LoginBusinessService {
	private UserDao userDao;
	@Override
	public boolean isvalidUser(String email,String password) {
		// TODO Auto-generated method stub
		boolean isValidUser=false;
		User user=userDao.getUser(email);
		if(null!=user && password.equals(user.getPassword()))
			isValidUser=true;
		return isValidUser;
	}	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
