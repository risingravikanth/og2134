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
		String jsonRes=null;
		List<String> countriesList=pipeLineBusinessServiceImpl.getCountries(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountries(countriesList);
		return jsonRes;
	}
	@Override
	public String getCommodities(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> commodityList=pipeLineBusinessServiceImpl.getCommodities(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCommoditiesResponse(commodityList);
		return jsonRes;		
	}
	@Override
	public String getStartPoints(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> startPointList=pipeLineBusinessServiceImpl.getStartPoints(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createStartPointResponse(startPointList);
		return jsonRes;	
	}
	@Override
	public String getEndPoints(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> endPointList=pipeLineBusinessServiceImpl.getEndPoints(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createEndPointResponse(endPointList);
		return jsonRes;	
	}
	@Override
	public String getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> statusList=pipeLineBusinessServiceImpl.getStatus(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createStatus(statusList);
		return jsonRes;
	}	
	

}
