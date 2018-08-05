package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;

import com.oganalysis.business.PipeLineBusinessService;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.helper.JsonResponse;
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
		String pipelineRes=pipeLineJson.createPipelineData(pipeLineData,DOMESTIC);
		return pipelineRes;
	}
	@Override
	public String getTransNationalData(
			Map<String, List<String>> selectedOptions, String displayType) {
		// TODO Auto-generated method stub
		String lengthDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))		
			lengthDataRes=getTransNationalLengthByCountry(selectedOptions);				
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))		
			lengthDataRes=getTransNationalLengthByCompany(selectedOptions);
		else if(null!=displayType && displayType.equalsIgnoreCase(PIPELINE))		
			lengthDataRes=getTransNationalLengthByPipeLine(selectedOptions);
		return lengthDataRes;
	}
	
	private String getTransNationalLengthByCountry(Map<String,List<String>> selectedOptions)
	{
		Map<String,Double> lengthByCountry=pipeLineBusinessServiceImpl.getTransNationalLengthByCountry(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createLengthByCountry(lengthByCountry);
		return pipelineRes;
	}
	private String getTransNationalLengthByCompany(Map<String,List<String>> selectedOptions)
	{
		Map<String,Double> lengthByCompany=pipeLineBusinessServiceImpl.getTransNationalLengthByCompany(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createLengthByCompany(lengthByCompany);
		return pipelineRes;
	}
	private String getTransNationalLengthByPipeLine(Map<String,List<String>> selectedOptions)
	{
		Map<String,List<Map>> pipeLineData=pipeLineBusinessServiceImpl.getTransNationalPipeLines(selectedOptions);
		PipeLineJsonResponse pipeLineJson=new PipeLineJsonResponse();
		String pipelineRes=pipeLineJson.createPipelineData(pipeLineData,TRANSNATIONAL);
		return pipelineRes;
	}
	
	public void setPipeLineBusinessServiceImpl(
			PipeLineBusinessService pipeLineBusinessServiceImpl) {
		this.pipeLineBusinessServiceImpl = pipeLineBusinessServiceImpl;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=pipeLineBusinessServiceImpl.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_REGIONS).isEmpty())//country is dependent on region filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> countriesList=pipeLineBusinessServiceImpl.getCountries(selectedOptions);			
			return res.createCountries(countriesList);			
		}
		
	}
	@Override
	public String getCommodities(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_COUNTRIES).isEmpty())//commodity is dependent on country filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> commodityList=pipeLineBusinessServiceImpl.getCommodities(selectedOptions);			
			return res.createCommoditiesResponse(commodityList);		
		}
			
	}
	@Override
	public String getStartPoints(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_COMMODITIES).isEmpty())//startPoint is dependent on commodity filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> startPointList=pipeLineBusinessServiceImpl.getStartPoints(selectedOptions);			
			return res.createStartPointResponse(startPointList);			
		}
		
	}
	@Override
	public String getEndPoints(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_STARTPOINTS).isEmpty())//endpoint is dependent on startPoint filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> endPointList=pipeLineBusinessServiceImpl.getEndPoints(selectedOptions);			
			return res.createEndPointResponse(endPointList);				
		}
		
	}
	@Override
	public String getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_ENDPOINTS).isEmpty())//status is dependent on endPoint filter
			return res.createEmptyJsonArray();
		else
		{
			List<String> statusList=pipeLineBusinessServiceImpl.getStatus(selectedOptions);			
			return res.createStatus(statusList);
			
		}
		
	}	
	

}
