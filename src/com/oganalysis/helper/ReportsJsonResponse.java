package com.oganalysis.helper;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oganalysis.entities.Report;
import static com.oganalysis.constants.ApplicationConstants.*;

public class ReportsJsonResponse {
	public String createPdfReportsResponse(List<Report> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(Report report:dataList)
			{
				 
				 JSONObject jsonObj=new JSONObject();				 										  			  			 
				 jsonObj.put(REPORTNAME,report.getReportTitle());				 				      			     
			     array.add(jsonObj);
			      
			}			
			response=array.toJSONString();      
		}
		else
			response=BLANK;
		return response;
	}
}
