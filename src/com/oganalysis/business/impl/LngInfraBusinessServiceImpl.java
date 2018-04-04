package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.BLANK;
import static com.oganalysis.constants.ApplicationConstants.COMMA;
import static com.oganalysis.constants.ApplicationConstants.LNG_LIQUEFACTION;
import static com.oganalysis.constants.ApplicationConstants.LNG_REGASIFICATION;
import static com.oganalysis.constants.ApplicationConstants.LOCATION;
import static com.oganalysis.constants.ApplicationConstants.OPERATIONAL;
import static com.oganalysis.constants.ApplicationConstants.OPERATOR;
import static com.oganalysis.constants.ApplicationConstants.PLANNED;
import static com.oganalysis.constants.ApplicationConstants.PROPOSED;
import static com.oganalysis.constants.ApplicationConstants.SHUTDOWN;
import static com.oganalysis.constants.ApplicationConstants.STARTYEAR;
import static com.oganalysis.constants.ApplicationConstants.STATUS;
import static com.oganalysis.constants.ApplicationConstants.STORAGECAPACITY;
import static com.oganalysis.constants.ApplicationConstants.TANKS;
import static com.oganalysis.constants.ApplicationConstants.TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.TERMINALNAME;
import static com.oganalysis.constants.ApplicationConstants.TRAIN;
import static com.oganalysis.constants.ApplicationConstants.TRAINS;
import static com.oganalysis.constants.ApplicationConstants.UNDERCONSTRUCTION;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.cache.LngCache;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public class LngInfraBusinessServiceImpl implements LngInfraBusinessService{
	private LngDao lngDao;
	private LngCache lngCache;
	
	@Override
	public List<Map<String,String>> getRegasificationInfrastructure(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,LNG_REGASIFICATION);
		Map<String,Map<String,String>> regasInfraMap=null;
		if(null==lngCache.getRegasInfrastructure())
		{
			regasInfraMap=lngCache.createInfrastructure(LNG_REGASIFICATION);
			lngCache.setRegasInfrastructure(regasInfraMap);
		}
		else
			regasInfraMap=lngCache.getRegasInfrastructure();
		
		List<Map<String,String>> infrastructureList=new ArrayList<Map<String,String>>();//(terminals, LNG_REGASIFICATION);
		for(String terminal:terminals)
		{
			infrastructureList.add(regasInfraMap.get(terminal.toLowerCase()));
		}
		return infrastructureList;
//		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
//		for(String terminalName:terminals)
//		{
//			Map<String,String> regasificationMap=new HashMap<String, String>();
//			List<Lng> terminalData=lngDao.getTerminalData(terminalName,LNG_REGASIFICATION);
//			if(terminalData.size()>0)
//			{
//				Lng lng=terminalData.get(0);
//				
//				regasificationMap.put(TERMINALNAME, lng.getName());
//				regasificationMap.put(STATUS, lng.getStatus());
//				regasificationMap.put(STARTYEAR, lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():BLANK); // This one need to check
//				regasificationMap.put(LOCATION, lng.getArea());
//				regasificationMap.put(TECHNOLOGY,getTechnologyDetails(terminalData).toString());// This one also check once;
//				regasificationMap.put(TRAIN,String.valueOf(getNumberOfTrainsOrVaporizers(terminalData)));//This one also need to check;
//				regasificationMap.put(OPERATOR,getOperator(lng.getName(),LNG_REGASIFICATION).toString());//This one also need to check;
//				regasificationMap.put(STORAGECAPACITY,String.valueOf(getStorageCapacity(terminalData)));//This one need to check;
//				regasificationMap.put(TANKS,String.valueOf(getTanks(terminalData)));//This one also need to check;
//				mapList.add(regasificationMap);
//			}
//			
//		}
//		return mapList;
	}
	@Override
	public List<Map<String,String>> getLiquefactionInfrastructure(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Map<String,Map<String,String>> liqueInfraMap=null;
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,LNG_LIQUEFACTION);
		if(null==lngCache.getLiqueInfrastructure())
		{
			liqueInfraMap=lngCache.createInfrastructure(LNG_LIQUEFACTION);
			lngCache.setLiqueInfrastructure(liqueInfraMap);
		}
		else
			liqueInfraMap=lngCache.getLiqueInfrastructure();
		
		List<Map<String,String>> liqueInfrastructureList=new ArrayList<Map<String,String>>();//(terminals, LNG_REGASIFICATION);
		for(String terminal:terminals)
		{
			liqueInfrastructureList.add(liqueInfraMap.get(terminal.toLowerCase()));
		}
		return liqueInfrastructureList;
//		List<Map<String,String>> infrastructureList=createInfrastructure(terminals, LNG_LIQUEFACTION);
//		return infrastructureMap;
//		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();	
//		for(String terminalName:terminals)
//		{
//			Map<String,String> liquefactionMap=new HashMap<String, String>();
//			List<Lng> terminalData=lngDao.getTerminalData(terminalName,LNG_LIQUEFACTION);
//			if(terminalData.size()>0)
//			{
//				Lng lng=terminalData.get(0);
//				
//				liquefactionMap.put(TERMINALNAME, lng.getName());
//				liquefactionMap.put(STATUS, lng.getStatus());
//				liquefactionMap.put(STARTYEAR, lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():BLANK); // This one need to check
//				liquefactionMap.put(LOCATION, lng.getArea());
//				liquefactionMap.put(TECHNOLOGY,getTechnologyDetails(terminalData).toString());// This one also check once;
//				liquefactionMap.put(TRAIN,String.valueOf(getNumberOfTrainsOrVaporizers(terminalData)));//This one also need to check;
//				liquefactionMap.put(OPERATOR,getOperator(lng.getName(),LNG_LIQUEFACTION).toString());//This one also need to check;
//				liquefactionMap.put(STORAGECAPACITY,String.valueOf(getStorageCapacity(terminalData)));//This one need to check;
//				liquefactionMap.put(TANKS,String.valueOf(getTanks(terminalData)));//This one also need to check;
//				mapList.add(liquefactionMap);
//			}
//			
//		}		
//				
//		return mapList;
	}
	public Map<String,Map<String,String>> createInfrastructure(List<String> terminals,String type)
	{
		Map<String,Map<String,String>> infrastructureTerminalMap=new HashMap<String, Map<String,String>>();
		List<Lng> terminalData=null;
		for(String terminalName:terminals)
		{
			Map<String,String> terminalMap=new HashMap<String, String>();
			if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
				terminalData=lngDao.getTerminalData(terminalName,LNG_LIQUEFACTION);
			else
				terminalData=lngDao.getTerminalData(terminalName, LNG_REGASIFICATION);
			
			if(terminalData.size()>0)
			{
				Lng lng=terminalData.get(0);
				
				terminalMap.put(TERMINALNAME, lng.getName());
				terminalMap.put(STATUS, lng.getStatus());
				terminalMap.put(STARTYEAR, getYearFromDate(lng.getExpectedStartYear()));//lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():BLANK); // This one need to check
				terminalMap.put(LOCATION, lng.getArea());
				terminalMap.put(TECHNOLOGY,getTechnologyDetails(terminalData).toString());// This one also check once;
				terminalMap.put(TRAIN,String.valueOf(getNumberOfTrainsOrVaporizers(terminalData)));//This one also need to check;
				terminalMap.put(OPERATOR,getOperator(lng.getName(),type).toString());//This one also need to check;
				terminalMap.put(STORAGECAPACITY,String.valueOf(getStorageCapacity(terminalData)));//This one need to check;
				terminalMap.put(TANKS,String.valueOf(getTanks(terminalData)));//This one also need to check;
				infrastructureTerminalMap.put(terminalName.toLowerCase(),terminalMap);
			}
			
		}					
		return infrastructureTerminalMap;
	}
	private String getYearFromDate(Date date)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int year=cal.get(Calendar.YEAR);
		return String.valueOf(year);
		
	}
	private StringBuffer getOperator(String terminalName,String type)
	{
		StringBuffer operators=new StringBuffer();
		List<LngFilter> lngFilterList=lngDao.getTerminalCompanies(terminalName,type);
		for(LngFilter lngFilter:lngFilterList)
		{
			if(null!=lngFilter.getOperator() && !(BLANK).equalsIgnoreCase(lngFilter.getOperator()))
				operators.append(lngFilter.getOperator()).append(COMMA);
			
		}
		if(operators.length()>0)
		removeCommaAtEnd(operators);
		return operators;
	}
	private StringBuffer getTechnologyDetails(List<Lng> dataList)
	{
		StringBuffer technology=new StringBuffer();
		for(Lng lng:dataList)
		{	
			if(null!=lng.getTechnologyDetails() && !BLANK.equalsIgnoreCase(lng.getTechnologyDetails()))
			technology.append(lng.getTechnologyDetails()).append(COMMA);
		}	
		if(technology.length()>0)
		removeCommaAtEnd(technology);
		return technology;
	}
	private double getNumberOfTrainsOrVaporizers(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double trains=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase(OPERATIONAL))
			trains=getPreviousYear(lngList,TRAINS);
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase(UNDERCONSTRUCTION) || lng.getStatus().equalsIgnoreCase(PLANNED)|| lng.getStatus().equalsIgnoreCase(PROPOSED) || lng.getStatus().equalsIgnoreCase(SHUTDOWN)))
			trains=getStartYear(lngList,TRAINS);
		return trains;
	}
	private double getStorageCapacity(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double storageCapacity=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase(OPERATIONAL))
			storageCapacity=getPreviousYear(lngList,STORAGECAPACITY);
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase(UNDERCONSTRUCTION) || lng.getStatus().equalsIgnoreCase(PLANNED)|| lng.getStatus().equalsIgnoreCase(PROPOSED) || lng.getStatus().equalsIgnoreCase(SHUTDOWN)))
			storageCapacity=getStartYear(lngList,STORAGECAPACITY);
		return storageCapacity;
	}
	private double getTanks(List<Lng> lngList)
	{
		Lng lng=lngList.get(0);
		double tanks=0.0;
		if(null!=lng.getStatus() && lng.getStatus().equalsIgnoreCase(OPERATIONAL))
			tanks=getPreviousYear(lngList,TANKS);
		else if(null!=lng.getStatus() &&(lng.getStatus().equalsIgnoreCase(UNDERCONSTRUCTION) || lng.getStatus().equalsIgnoreCase(PLANNED)|| lng.getStatus().equalsIgnoreCase(PROPOSED) || lng.getStatus().equalsIgnoreCase(SHUTDOWN)))
			tanks=getStartYear(lngList,TANKS);
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
				if(dataColumn.equalsIgnoreCase(TRAINS))
					previousYearVal=lng.getNumberOfTrainsOrNumberOfVaporizers();
				else if(dataColumn.equalsIgnoreCase(STORAGECAPACITY))
					previousYearVal=lng.getTotalLngStorageCapacity();
				else if(dataColumn.equalsIgnoreCase(TANKS))
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
				if(dataColumn.equalsIgnoreCase(TRAINS))
					startYearVal=lng.getNumberOfTrainsOrNumberOfVaporizers();
				else if(dataColumn.equalsIgnoreCase(STORAGECAPACITY))
					startYearVal=lng.getTotalLngStorageCapacity();
				else if(dataColumn.equalsIgnoreCase(TANKS))
					startYearVal=lng.getNumberOfStorageTanks();
				break;
			}
		}				
		return startYearVal;
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
	private void removeCommaAtEnd(StringBuffer inputString)
	{
		inputString.deleteCharAt(inputString.length()-1);
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	public LngCache getLngCache() {
		return lngCache;
	}
	public void setLngCache(LngCache lngCache) {
		this.lngCache = lngCache;
	}
	
}
