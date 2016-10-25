package com.oganalysis.web;

import java.awt.PageAttributes.MediaType;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping(value="/login",method={RequestMethod.GET},produces="text/html")
	
//	@ResponseBody
	public String userLoginSubmit(HttpServletRequest request)
	{
		String email=(String)request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("Email:"+email+"password:"+password+"session:"+request.getSession());
		return "home";
	}
	
	@RequestMapping(value="/login",method={RequestMethod.POST},produces="text/plain")
	
//	@ResponseBody
	public String userLogin(HttpServletRequest request)
	{
		String email=(String)request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println("I am in post method Email:"+email+"password:"+password+"session:"+request.getSession());
		return "redirect:/home.htm";
	}
}
