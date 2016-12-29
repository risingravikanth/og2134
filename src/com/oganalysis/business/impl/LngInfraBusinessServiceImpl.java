package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public class LngInfraBusinessServiceImpl implements LngInfraBusinessService{
	private LngDao lngDao;
	private static final String REGASIFICATION="Regasification";
	private static final String LIQUEFACTION="Liquefaction";
	@Override
	public List<Map<String,String>> getRegasificationInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,REGASIFICATION);
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(String terminalName:terminals)
		{
			Map<String,String> regasificationMap=new HashMap<String, String>();
			List<Lng> terminalData=lngDao.getTerminalData(terminalName,REGASIFICATION);
			if(terminalData.size()>0)
			{
				Lng lng=terminalData.get(0);
				
				regasificationMap.put("terminalName", lng.getName());
				regasificationMap.put("status", lng.getStatus());
				regasificationMap.put("startYear", lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():""); // This one need to check
				regasificationMap.put("location", lng.getArea());
				regasificationMap.put("technology",getTechnologyDetails(terminalData).toString());// This one also check once;
				regasificationMap.put("train",String.valueOf(getNumberOfTrainsOrVaporizers(terminalData)));//This one also need to check;
				regasificationMap.put("operator",getOperator(lng.getName()).toString());//This one also need to check;
				regasificationMap.put("storageCapacity",String.valueOf(getStorageCapacity(terminalData)));//This one need to check;
				regasificationMap.put("tanks",String.valueOf(getTanks(terminalData)));//This one also need to check;
				mapList.add(regasificationMap);
			}
			
		}
		return mapList;
	}
	@Override
	public List<Map<String,String>> getLiquefactionInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,LIQUEFACTION);		
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();	
		for(String terminalName:terminals)
		{
			Map<String,String> liquefactionMap=new HashMap<String, String>();
			List<Lng> terminalData=lngDao.getTerminalData(terminalName,LIQUEFACTION);
			if(terminalData.size()>0)
			{
				Lng lng=terminalData.get(0);
				
				liquefactionMap.put("terminalName", lng.getName());
				liquefactionMap.put("status", lng.getStatus());
				liquefactionMap.put("startYear", lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():""); // This one need to check
				liquefactionMap.put("location", lng.getArea());
				liquefactionMap.put("technology",getTechnologyDetails(terminalData).toString());// This one also check once;
				liquefactionMap.put("train",String.valueOf(getNumberOfTrainsOrVaporizers(terminalData)));//This one also need to check;
				liquefactionMap.put("operator",getOperator(lng.getName()).toString());//This one also need to check;
				liquefactionMap.put("storageCapacity",String.valueOf(getStorageCapacity(terminalData)));//This one need to check;
				liquefactionMap.put("tanks",String.valueOf(getTanks(terminalData)));//This one also need to check;
				mapList.add(liquefactionMap);
			}
			
		}		
				
		return mapList;
	}
	private StringBuffer getOperator(String terminalName)
	{
		StringBuffer operators=new StringBuffer();
		List<LngFilter> lngFilterList=lngDao.getTerminalCompanies(terminalName);
		for(LngFilter lngFilter:lngFilterList)
		{
			if(null!=lngFilter.getOperator() && !("").equalsIgnoreCase(lngFilter.getOperator()))
				operators.append(lngFilter.getOperator()).append(",");
			
		}
		return operators;
	}
	private StringBuffer getTechnologyDetails(List<Lng> dataList)
	{
		StringBuffer technology=new StringBuffer();
		for(Lng lng:dataList)
		{	
			if(null!=lng.getTechnologyDetails() && !"".equalsIgnoreCase(lng.getTechnologyDetails()))
			technology.append(lng.getTechnologyDetails()).append(",");
		}	
		return technology;
	}
	private double getNumberOfTrainsOrVaporizers(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double trains=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase("Operational"))
			trains=getPreviousYear(lngList,"trains");
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase("Under Construction") || lng.getStatus().equalsIgnoreCase("Planned")|| lng.getStatus().equalsIgnoreCase("Proposed") || lng.getStatus().equalsIgnoreCase("Shutdown")))
			trains=getStartYear(lngList,"trains");
		return trains;
	}
	private double getStorageCapacity(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double storageCapacity=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase("Operational"))
			storageCapacity=getPreviousYear(lngList,"storageCapacity");
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase("Under Construction") || lng.getStatus().equalsIgnoreCase("Planned")|| lng.getStatus().equalsIgnoreCase("Proposed") || lng.getStatus().equalsIgnoreCase("Shutdown")))
			storageCapacity=getStartYear(lngList,"storageCapacity");
		return storageCapacity;
	}
	private double getTanks(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double tanks=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase("Operational"))
			tanks=getPreviousYear(lngList,"tanks");
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase("UnderConstruction") || lng.getStatus().equalsIgnoreCase("Planned")|| lng.getStatus().equalsIgnoreCase("Proposed") || lng.getStatus().equalsIgnoreCase("Shutdown")))
			tanks=getStartYear(lngList,"tanks");
		return tanks;
	}
	private double getPreviousYear(List<Lng> lngList,String dataColumn)
	{
		int currentYear=Calendar.getInstance().get(Calendar.YEAR);
		double previousYearVal=0.0;;
		for(Lng lng:lngList)
		{
			if(currentYear-1==lng.getCapacityYear())
			{	
				if(dataColumn.equalsIgnoreCase("trains"))
					previousYearVal=lng.getNumberOfTrainsOrNumberOfVaporizers();
				else if(dataColumn.equalsIgnoreCase("storageCapacity"))
					previousYearVal=lng.getTotalLngStorageCapacity();
				else if(dataColumn.equalsIgnoreCase("tanks"))
					previousYearVal=lng.getNumberOfStorageTanks();
				break;
			}	
		}
		
		return previousYearVal;
	}
	private double getStartYear(List<Lng> lngList,String dataColumn)
	{
		Set<Integer> years=getYears(lngList);
		double startYearVal=0.0;
		ArrayList<Integer> yearsList=new ArrayList<Integer>(years);
		Collections.sort(yearsList);
		for(Lng lng:lngList)
		{
			if(yearsList.get(0)==lng.getCapacityYear())
			{
				if(dataColumn.equalsIgnoreCase("trains"))
					startYearVal=lng.getNumberOfTrainsOrNumberOfVaporizers();
				else if(dataColumn.equalsIgnoreCase("storageCapacity"))
					startYearVal=lng.getTotalLngStorageCapacity();
				else if(dataColumn.equalsIgnoreCase("tanks"))
					startYearVal=lng.getNumberOfStorageTanks();
				break;
			}
		}				
		return startYearVal;
	}
	private Set<String> getTerminals(List<Lng> lngList)
	{
		Set<String> terminals=new HashSet<String>();
		for(Lng lng:lngList)
		{
			terminals.add(lng.getName());
		}
		return terminals;
	}
	private Set<Integer> getYears(List<Lng> lngList)
	{
		Set<Integer> years=new HashSet<Integer>();
		for(Lng lng:lngList)
		{
			if(lng.getCapacityYear()!=0)
			years.add(lng.getCapacityYear());			
		}
		return years;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	
}
