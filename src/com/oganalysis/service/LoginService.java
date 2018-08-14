package com.oganalysis.service;

import java.util.Map;

import org.json.simple.JSONObject;

public interface LoginService {
	public JSONObject login(String email,String password);
	public String saveCustomer(Map<String,String> customerMap);
	public String forgotPassword(String email);	
	public String updatePassword(Map<String,String> pwdMap,String reset);
}
