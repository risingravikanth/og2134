package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.RefineriesInfraBusinessService;
import com.oganalysis.cache.RefineriesCache;
import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.Refinery;

import static com.oganalysis.constants.ApplicationConstants.*;

public class RefineriesInfraBusinessServiceImpl implements
		RefineriesInfraBusinessService {
	private RefineriesDao refineriesDao;
	private RefineriesCache refineriesCache;
	@Override
	public List<Map<String, String>> getInfrastructure(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=refineriesDao.getSelectedTerminals(selectedOptions);
		Map<String,Map<String,String>> infraMap=null;
		if(null==refineriesCache.getInfrastructure())
		{
			infraMap=refineriesCache.createInfrastructure();
			refineriesCache.setInfrastructure(infraMap);
		}
		else
			infraMap=refineriesCache.getInfrastructure();
		
		List<Map<String,String>> infrastructureList=new ArrayList<Map<String,String>>();//(terminals, LNG_REGASIFICATION);
		for(String terminal:terminals)
		{
			infrastructureList.add(infraMap.get(terminal.toLowerCase()));
		}
		return infrastructureList;
	}

	@Override
	public Map<String, Map<String, String>> createInfrastructure(
			List<String> terminals) {
		// TODO Auto-generated method stub
		Map<String,Map<String,String>> infrastructureTerminalMap=new HashMap<String, Map<String,String>>();
		List<Refinery> terminalData=null;
		for(String terminalName:terminals)
		{
			Map<String,String> terminalMap=new HashMap<String, String>();
			
			terminalData=refineriesDao.getTerminalData(terminalName);
			
			if(terminalData.size()>0)
			{
				Refinery refinery=terminalData.get(0);//getRefineryData(terminalData);
				terminalMap.put(TERMINALNAME, refinery.getName());
				terminalMap.put(LOCATION, refinery.getLocation());
				terminalMap.put(STATUS, refinery.getStatus());
				terminalMap.put(STARTYEAR, getYear(terminalData));
				terminalMap.put(OPERATOR, getOperator(terminalData));
				addTerminalCapacities(terminalMap,terminalData);
//				terminalMap.put("cduCapacity",String.valueOf(refinery.getRefiningCapacity()));
//				terminalMap.put("cokingCapacity",String.valueOf(refinery.getCokingCapacity()));
//				terminalMap.put("fccCapacity",String.valueOf(refinery.getFcc()));
//				terminalMap.put("hydroCrackingCapacity",String.valueOf(refinery.getHydroCrackingCapacity()));
				infrastructureTerminalMap.put(terminalName.toLowerCase(), terminalMap);
			}
			
		}					
		return infrastructureTerminalMap;
	}
	private String getYear(List<Refinery> terminalData)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:terminalData)
		{
			if(null!=refinery.getStartYear())
				sb.append(getYearFromDate(refinery.getStartYear())).append(COMMA);
		}
		if(sb.length()>0)
			removeCommaAtEnd(sb);
		return new String(sb);
	}
	private String getYearFromDate(Date date)
	{
		if(null!=date)
		{
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			int year=cal.get(Calendar.YEAR);
			return String.valueOf(year);
		}
		else
			return BLANK;
		
		
	}
	private String getOperator(List<Refinery> terminalData)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:terminalData)
		{
			if(null!=refinery.getCurrentOperator() && !(BLANK).equalsIgnoreCase(refinery.getCurrentOperator()))
				sb.append(refinery.getCurrentOperator()).append(COMMA);
		}
		if(sb.length()>0)
			removeCommaAtEnd(sb);
		return new String(sb);
	}
	private void addTerminalCapacities(Map<String,String> terminalMap,List<Refinery> terminalData)
	{
		int previousYear=getPreviousYear();
		Refinery refineryData=null;
		for(Refinery refinery:terminalData)
		{
			if(refinery.getCapacityYear()==previousYear)
				refineryData=refinery;
		}	
		if(null!=refineryData)
		{
			terminalMap.put(CDUCAPACITY,String.valueOf(refineryData.getRefiningCapacity()));
			terminalMap.put(COKINGCAPACITY,String.valueOf(refineryData.getCokingCapacity()));
			terminalMap.put(FCCCAPACITY,String.valueOf(refineryData.getFcc()));
			terminalMap.put(HYDROCRACKINGCAPACITY,String.valueOf(refineryData.getHydroCrackingCapacity()));
		}
		else
		{
			terminalMap.put(CDUCAPACITY,String.valueOf(0));
			terminalMap.put(COKINGCAPACITY,String.valueOf(0));
			terminalMap.put(FCCCAPACITY,String.valueOf(0));
			terminalMap.put(HYDROCRACKINGCAPACITY,String.valueOf(0));
		}
		
	}
//	private Refinery getRefineryData(List<Refinery> terminal)
//	{
//		int previousYear=getPreviousYear();
//		Refinery refineryData=null;
//		for(Refinery refinery:terminal)
//		{
//			if(refinery.getCapacityYear()==previousYear)
//				refineryData=refinery;
//		}
//		return refineryData;
//	}
	private void removeCommaAtEnd(StringBuffer inputString)
	{
		inputString.deleteCharAt(inputString.length()-1);
	}
	private int getPreviousYear()
	{
		Calendar c=Calendar.getInstance();
		int currYear=c.get(Calendar.YEAR);
		return currYear-1;
	}
	public RefineriesDao getRefineriesDao() {
		return refineriesDao;
	}

	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}

	public RefineriesCache getRefineriesCache() {
		return refineriesCache;
	}

	public void setRefineriesCache(RefineriesCache refineriesCache) {
		this.refineriesCache = refineriesCache;
	}
	
}
