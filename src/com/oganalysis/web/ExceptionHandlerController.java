package com.oganalysis.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.helper.ExceptionJsonResponse;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exception(Exception e) {		
		ExceptionJsonResponse expJson=new ExceptionJsonResponse();
		return expJson.createErrorMessage().toString();
	}
}
