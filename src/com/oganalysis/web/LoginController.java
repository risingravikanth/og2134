package com.oganalysis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		String email=(String)request.getParameter("email");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		session.setAttribute("email",email);
		String res=loginService.login(email, password);		
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
