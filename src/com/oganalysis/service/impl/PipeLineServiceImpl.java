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
//		List<Region> regionsList=pipeLineBusinessServiceImpl.getRegions();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createRegionsResponse(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Countries> countriesList=pipeLineBusinessServiceImpl.getCountries();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createCountriesResponse(countriesList);
		return jsonRes;
	}
	@Override
	public String getCommodities() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> commodityList=pipeLineBusinessServiceImpl.getCommodities();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCommoditiesResponse(commodityList);
		return jsonRes;		
	}
	@Override
	public String getStartPoints() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> startPointList=pipeLineBusinessServiceImpl.getStartPoints();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createStartPointResponse(startPointList);
		return jsonRes;	
	}
	@Override
	public String getEndPoints() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> endPointList=pipeLineBusinessServiceImpl.getEndPoints();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createEndPointResponse(endPointList);
		return jsonRes;	
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Status> statusList=pipeLineBusinessServiceImpl.getStatus();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createStatusResponse(statusList);
		return jsonRes;
	}	
	

}
