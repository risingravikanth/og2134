package com.oganalysis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		return "login";
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String onLoginSubmit(HttpServletRequest request)
	{
		String res=INCORRECT;
		String email=request.getParameter("username");//email
		if(null!=email)
		{
			String password=request.getParameter("password");
			HttpSession session=request.getSession();		
			res=loginService.login(email, password);	
			if(null!=res && res.equals(CORRECT))
				session.setAttribute(EMAIL,email);
		}		
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
