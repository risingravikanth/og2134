package com.oganalysis.business;

import java.util.Map;

import com.oganalysis.entities.User;

public interface LoginBusinessService {
	public User validateUser(String email,String password);
	public String saveCustomer(Map<String,String> customerMap);
}
