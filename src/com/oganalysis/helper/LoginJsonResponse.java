package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;


import org.json.simple.JSONObject;

import com.oganalysis.entities.User;
public class LoginJsonResponse {
	public JSONObject loginResponse(User user)
	{
		JSONObject resObj=new JSONObject();
		if(null!=user)
		{
			if(user.getPasswordReset().equalsIgnoreCase("Y"))
			{
				resObj.put(LOGIN_STATUS,LOGIN_RESET);
				resObj.put(LOGIN_USER,user.getFirstName()+BLANK+user.getLastName());
			}
			else
			{
				resObj.put(LOGIN_STATUS,CORRECT);
				resObj.put(LOGIN_USER,user.getFirstName()+BLANK+user.getLastName());
			}
			
		}
		else
		{
			resObj.put(LOGIN_STATUS,INCORRECT);
			resObj.put(LOGIN_USER,BLANK);
		}
		return resObj;
	}
}
