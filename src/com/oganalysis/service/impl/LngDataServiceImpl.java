package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.LNG_LIQUEFACTION;
import static com.oganalysis.constants.ApplicationConstants.LNG_REGASIFICATION;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.oganalysis.business.LngCapacityBusinessService;
import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.excel.LngExcel;
import com.oganalysis.helper.LngJsonResponse;
import com.oganalysis.service.LngDataService;

public class LngDataServiceImpl implements LngDataService{
	
	
	private LngCapacityBusinessService lngCapacityBusinessServiceImpl;
	private LngInfraBusinessService lngInfraBusinessServiceImpl;
	private LngExcel lngExcel;
	
	
	@Override
	public String getCapacityData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate, String displayType) {
		// TODO Auto-generated method stub
		
		String capacityDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))		
			capacityDataRes=getCapacityByCountry(selectedOptions,startDate,endDate);		
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL))		
			capacityDataRes=getCapacityByTerminal(selectedOptions,startDate,endDate);		
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))		
			capacityDataRes=getCapacityByCompany(selectedOptions,startDate,endDate);		
		
		return capacityDataRes;
	}
	@Override
	public String getModalCapacityData(Map<String, List<String>> selectedOptions,
			String startDate, String endDate, String displayType, String type,
			String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		JSONObject modalCapacityDataRes=null;
		int startDateVal=0;
		int endDateVal=0;
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL))
		{
			startDateVal=Integer.parseInt(startDate);
			endDateVal=Integer.parseInt(endDate);
		}		
		
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_LIQUEFACTION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getLiqueModalCapacityForRecord(selectedOptions,startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityLiquefactionRes(modalCapacityData, startDateVal, endDateVal, TERMINAL);
		}			
		else if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_REGASIFICATION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getRegasModalCapacityForRecord(selectedOptions,startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityRegasificationRes(modalCapacityData, startDateVal, endDateVal, TERMINAL);
		}
		// Modal is different for Terminal displayType because of which below conditions are required 
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_LIQUEFACTION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_REGASIFICATION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
							
		return modalCapacityDataRes.toJSONString();
	}
	private String getCapacityByCompany(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCompany(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCompany(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCompany=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByCompany.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByCompany.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByCompanyRes=lngJsonResponse.createCapacityByCompanyRes(capacityDataByCompany, startDate, endDate);
		return capacityByCompanyRes;
	}
	private String getCapacityByTerminal(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByTerminal(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByTerminal(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByTerminal=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByTerminal.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByTerminal.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByTerminalRes=lngJsonResponse.createCapacityByTerminalRes(capacityDataByTerminal, startDate, endDate);
		return capacityByTerminalRes;
	}
	private String getCapacityByCountry(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCountry(selectedOptions,startDate,endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCountry(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCountry=new HashMap<String, Map<String,Map<Integer,Double>>>();
		capacityDataByCountry.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByCountry.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityDataByCountryRes=lngJsonResponse.createCapacityByCountryRes(capacityDataByCountry,startDate,endDate);
		return capacityDataByCountryRes;
	}
	@Override
	public Workbook getExcelTerminalData(String recordName, String type,InputStream is) {
		// TODO Auto-generated method stub
		Map<String,String> terminalData=lngCapacityBusinessServiceImpl.getTerminalData(recordName, type);		
		Workbook wb=lngExcel.getExcelTerminalData(terminalData,is);
		return wb;
	}
	@Override
	public String getInfrastructureData(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String infrastructureDataRes=null;;
		List<Map<String,String>> liquefaction=lngInfraBusinessServiceImpl.getLiquefactionInfrastructure(selectedOptions);
		List<Map<String,String>> regasification=lngInfraBusinessServiceImpl.getRegasificationInfrastructure(selectedOptions);
		Map<String,List<Map<String,String>>> infrastructureMap=new HashMap<String, List<Map<String,String>>>();
		infrastructureMap.put(LNG_LIQUEFACTION, liquefaction);
		infrastructureMap.put(LNG_REGASIFICATION, regasification);
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		infrastructureDataRes=lngJsonResponse.createInfrastructureRes(infrastructureMap);
		return infrastructureDataRes;
	}

	public LngCapacityBusinessService getLngCapacityBusinessServiceImpl() {
		return lngCapacityBusinessServiceImpl;
	}
	public void setLngCapacityBusinessServiceImpl(
			LngCapacityBusinessService lngCapacityBusinessServiceImpl) {
		this.lngCapacityBusinessServiceImpl = lngCapacityBusinessServiceImpl;
	}
	public LngInfraBusinessService getLngInfraBusinessServiceImpl() {
		return lngInfraBusinessServiceImpl;
	}
	public void setLngInfraBusinessServiceImpl(
			LngInfraBusinessService lngInfraBusinessServiceImpl) {
		this.lngInfraBusinessServiceImpl = lngInfraBusinessServiceImpl;
	}
	public void setLngExcel(LngExcel lngExcel) {
		this.lngExcel = lngExcel;
	}
	
	
	
	


}
