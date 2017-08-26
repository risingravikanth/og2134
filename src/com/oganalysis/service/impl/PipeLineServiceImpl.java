package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.*;


import java.util.List;
import java.util.Map;

import com.oganalysis.business.PipeLineBusinessService;
import com.oganalysis.helper.PipeLineJsonResponse;
import com.oganalysis.service.PipeLineService;

public class PipeLineServiceImpl implements PipeLineService {
	private PipeLineBusinessService pipeLineBusinessServiceImpl;
	@Override
	public String getDomesticData(Map<String, List<String>> selectedOptions,
			String displayType) {
		// TODO Auto-generated method stub
		String lengthDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))		
			lengthDataRes=getDomesticLengthByCountry(selectedOptions);				
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))		
			lengthDataRes=getDomesticLengthByCompany(selectedOptions);
		else if(null!=displayType && displayType.equalsIgnoreCase(PIPELINE))		
			lengthDataRes=getDomesticLengthByPipeLine(selectedOptions);
		return lengthDataRes;
	}
	private String getDomesticLengthByCountry(Map<String,List<String>> selectedOptions)
	{
		Map<String,Double> lengthByCountry=pipeLineBusinessServiceImpl.getDomesticLengthByCountry(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createLengthByCountry(lengthByCountry);
		return pipelineRes;
	}
	private String getDomesticLengthByCompany(Map<String,List<String>> selectedOptions)
	{
		Map<String,Double> lengthByCompany=pipeLineBusinessServiceImpl.getDomesticLengthByCompany(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createLengthByCompany(lengthByCompany);
		return pipelineRes;
	}
	private String getDomesticLengthByPipeLine(Map<String,List<String>> selectedOptions)
	{
		Map<String,List<Map>> pipeLineData=pipeLineBusinessServiceImpl.getDomesticPipeLines(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createPipelineData(pipeLineData);
		return pipelineRes;
	}
	@Override
	public String getTransNationalData(
			Map<String, List<String>> selectedOptions, String displayType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPipeLineBusinessServiceImpl(
			PipeLineBusinessService pipeLineBusinessServiceImpl) {
		this.pipeLineBusinessServiceImpl = pipeLineBusinessServiceImpl;
	}	
	

}
