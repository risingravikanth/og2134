package com.oganalysis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.oganalysis.constants.ApplicationConstants.*;

import com.oganalysis.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@RequestMapping(value="/",method={RequestMethod.GET})
	
//	@ResponseBody
	public String loginPage(HttpServletRequest request)
	{			
		return LOGIN;
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String onLoginSubmit(HttpServletRequest request)
	{		
		JSONObject resObj=null;
		String email=request.getParameter("username");//email
		if(null!=email)
		{
			String password=request.getParameter("password");
			HttpSession session=request.getSession();		
			resObj=loginService.login(email, password);	
			if(null!=resObj && resObj.get(LOGIN_STATUS).equals(CORRECT))
				session.setAttribute(EMAIL,email);
		}		
		return resObj.toString();
	}
	@RequestMapping(value="/logout",method={RequestMethod.GET},produces="text/html")	
	@ResponseBody
	public String onLogoutSubmit(HttpServletRequest request)
	{
		String res=LOGIN;
		request.getSession().removeAttribute(EMAIL);
		return res;
	}
//	@RequestMapping({"/"})
//	
////	@ResponseBody
//	public String root(HttpServletRequest request)
//	{		
//		System.out.println("rootPath");
//		return "index";
//	}
}
