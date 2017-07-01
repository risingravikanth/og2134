package com.oganalysis.service;

import java.util.Map;

import org.json.simple.JSONObject;

public interface LoginService {
	public JSONObject login(String email,String password);
	public String saveCustomer(Map<String,String> customerMap);
}
