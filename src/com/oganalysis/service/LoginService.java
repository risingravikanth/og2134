package com.oganalysis.service;

import org.json.simple.JSONObject;

public interface LoginService {
	public JSONObject login(String email,String password);
}
