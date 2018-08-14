package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.CORRECT;
import static com.oganalysis.constants.ApplicationConstants.EMAIL;
import static com.oganalysis.constants.ApplicationConstants.LOGIN;
import static com.oganalysis.constants.ApplicationConstants.LOGIN_STATUS;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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
	@RequestMapping(value="/contactus",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String onContactUsSubmit(HttpServletRequest request)
	{				
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		String landline=request.getParameter("landline");
		String custMsg=request.getParameter("message");
		Map<String,String> custMap=new HashMap<String, String>();
		custMap.put("fname",fname);
		custMap.put("lname",lname);
		custMap.put("mobile",mobile);
		custMap.put("email",email);
		custMap.put("landline",landline);
		custMap.put("custMsg",custMsg);
		String msg=loginService.saveCustomer(custMap);
		return msg;
	}
	@RequestMapping(value="/logout",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String onLogoutSubmit(HttpServletRequest request)
	{
		String res=LOGIN;
		request.getSession().removeAttribute(EMAIL);
		return res;
	}
	@RequestMapping(value="/forgotpwd",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String forgotPassword(HttpServletRequest request)
	{
		
		String email=request.getParameter("email");
		return loginService.forgotPassword(email);		
	}
	@RequestMapping(value="/resetpwd",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String resetPassword(HttpServletRequest request)
	{
		
		String email=request.getParameter("email");
		String currPwd=request.getParameter("currpwd");
		String newPwd=request.getParameter("newpwd");
		String confirmPwd=request.getParameter("confirmpwd");
		Map<String,String> pwds=new HashMap<String, String>();
		pwds.put("email", email);
		pwds.put("currPwd", currPwd);
		pwds.put("newPwd", newPwd);
		pwds.put("confirmPwd", confirmPwd);		
		return loginService.updatePassword(pwds,"Y");
		
	}
	@RequestMapping(value="/changepwd",method={RequestMethod.POST},produces="text/html")	
	@ResponseBody
	public String changePassword(HttpServletRequest request)
	{
		
		String email=request.getParameter("email");
		String currPwd=request.getParameter("currpwd");
		String newPwd=request.getParameter("newpwd");
		String confirmPwd=request.getParameter("confirmpwd");
		Map<String,String> pwds=new HashMap<String, String>();
		pwds.put("email", email);
		pwds.put("currPwd", currPwd);
		pwds.put("newPwd", newPwd);
		pwds.put("confirmPwd", confirmPwd);		
		return loginService.updatePassword(pwds,"N");		
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
