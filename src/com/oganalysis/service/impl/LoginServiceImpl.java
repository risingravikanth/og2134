package com.oganalysis.service.impl;

import com.oganalysis.business.impl.LoginBusinessServiceImpl;
import com.oganalysis.service.LoginService;
import static com.oganalysis.constants.ApplicationConstants.*;

public class LoginServiceImpl implements LoginService {
	private LoginBusinessServiceImpl loginBusinessServiceImpl;
	@Override
	public String login(String email,String password) {
		// TODO Auto-generated method stub
		String res=INCORRECT;
		boolean valid=loginBusinessServiceImpl.isvalidUser(email,password);
		if(valid==true)
			res=CORRECT;
		return res;
	}
	public LoginBusinessServiceImpl getLoginBusinessServiceImpl() {
		return loginBusinessServiceImpl;
	}
	public void setLoginBusinessServiceImpl(
			LoginBusinessServiceImpl loginBusinessServiceImpl) {
		this.loginBusinessServiceImpl = loginBusinessServiceImpl;
	}
	
}
