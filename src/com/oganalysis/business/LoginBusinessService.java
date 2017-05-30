package com.oganalysis.business;

import com.oganalysis.entities.User;

public interface LoginBusinessService {
	public User validateUser(String email,String password);
}
