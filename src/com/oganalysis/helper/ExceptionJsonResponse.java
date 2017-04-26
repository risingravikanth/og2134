package com.oganalysis.helper;

import org.json.simple.JSONObject;
import static com.oganalysis.constants.ApplicationConstants.*;
public class ExceptionJsonResponse {
	public JSONObject createErrorMessage()
	{
		JSONObject json=new JSONObject();
		json.put(ERROR_MSG,"appError");
		return json;
	}
	
}
