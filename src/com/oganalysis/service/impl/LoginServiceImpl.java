package com.oganalysis.service.impl;

import java.util.Map;

import org.json.simple.JSONObject;

import com.oganalysis.business.impl.LoginBusinessServiceImpl;
import com.oganalysis.entities.User;
import com.oganalysis.helper.LoginJsonResponse;
import com.oganalysis.service.LoginService;
import static com.oganalysis.constants.ApplicationConstants.LOGIN_STATUS;

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
	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
		JSONObject resObj=new JSONObject();
		String status= loginBusinessServiceImpl.forgotPassword(email);
		resObj.put(LOGIN_STATUS, status);
		return resObj.toJSONString();
		
	}
	@Override
	public String updatePassword(Map<String, String> pwdMap,String reset) {		
		// TODO Auto-generated method stub
		JSONObject resObj=new JSONObject();
		String status= loginBusinessServiceImpl.updatePassword(pwdMap,reset);
		resObj.put(LOGIN_STATUS, status);
		return resObj.toJSONString();
	}
		
	
}
