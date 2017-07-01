package com.oganalysis.service.impl;

import java.util.Map;

import org.json.simple.JSONObject;

import com.oganalysis.business.impl.LoginBusinessServiceImpl;
import com.oganalysis.entities.User;
import com.oganalysis.helper.LoginJsonResponse;
import com.oganalysis.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private LoginBusinessServiceImpl loginBusinessServiceImpl;
	@Override
	public JSONObject login(String email,String password) {
		// TODO Auto-generated method stub		
		User user=loginBusinessServiceImpl.validateUser(email,password);		
		LoginJsonResponse loginJson=new LoginJsonResponse();
		JSONObject response=loginJson.loginResponse(user);		
		return response;
	}	
	@Override
	public String saveCustomer(Map<String, String> customerMap) {
		// TODO Auto-generated method stub
		return loginBusinessServiceImpl.saveCustomer(customerMap);		
	}
	public LoginBusinessServiceImpl getLoginBusinessServiceImpl() {
		return loginBusinessServiceImpl;
	}
	public void setLoginBusinessServiceImpl(
			LoginBusinessServiceImpl loginBusinessServiceImpl) {
		this.loginBusinessServiceImpl = loginBusinessServiceImpl;
	}

	
	
}
