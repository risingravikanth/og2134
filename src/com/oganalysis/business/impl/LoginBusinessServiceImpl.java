package com.oganalysis.business.impl;

import java.util.Map;

import com.oganalysis.business.LoginBusinessService;
import static com.oganalysis.constants.ApplicationConstants.*;
import com.oganalysis.dao.CustomerDao;
import com.oganalysis.dao.UserDao;
import com.oganalysis.entities.User;

public class LoginBusinessServiceImpl implements LoginBusinessService {
	private UserDao userDao;
	private CustomerDao customerDao;
	@Override
	public User validateUser(String email,String password) {
		// TODO Auto-generated method stub			
		User user=userDao.getUser(email);
		if(null!=user && !password.equals(user.getPassword()))
			user=null;		
		return user;
	}
	@Override
	public String saveCustomer(Map<String, String> customerMap) {
		// TODO Auto-generated method stub
		String status=FAIL;
		boolean flag=customerDao.saveCustomer(customerMap);
		if(flag==true)
			status=SUCCESS;
		return status;
	}
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
}
