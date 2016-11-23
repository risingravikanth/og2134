package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;

public class LngInfraBusinessServiceImpl implements LngInfraBusinessService{
	private LngDao lngDao;
	@Override
	public List<Map<String,String>> getRegasificationInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions);
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(Lng lng:regasificationList)
		{
			Map<String,String> regasificationMap=new HashMap<String, String>();
			regasificationMap.put("terminalName", lng.getName());
			regasificationMap.put("status", lng.getStatus());
			System.out.println();
			regasificationMap.put("startYear", lng.getExpectedStartYear().toString()); // This one need to check
			regasificationMap.put("location", lng.getArea());
			regasificationMap.put("technology",lng.getTechnologyDetails());// This one also check once;
			regasificationMap.put("train","");//This one also need to check;
			regasificationMap.put("operator",lng.getOperator());//This one also need to check;
			regasificationMap.put("storageCapacity","");//This one need to check;
			regasificationMap.put("tanks","");//This one also need to check;
			mapList.add(regasificationMap);
		}
		return mapList;
	}
	@Override
	public List<Map<String,String>> getLiquefactionInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions);
		Set<String> terminals=getTerminals(liquefactionList);
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		
		for(String terminal:terminals)
		{
			for(Lng lng:liquefactionList)
			{
				if(terminal.equalsIgnoreCase(lng.getName()))
				{
					Map<String,String> liquefactionMap=new HashMap<String, String>();
					liquefactionMap.put("terminalName", lng.getName());
					liquefactionMap.put("status", lng.getStatus());
					liquefactionMap.put("startYear", lng.getExpectedStartYear().toString()); // This one need to check
					liquefactionMap.put("location", lng.getArea());
					liquefactionMap.put("technology",lng.getTechnologyDetails());// This one also check once;
					liquefactionMap.put("train","");//This one also need to check;
					liquefactionMap.put("operator",lng.getOperator());//This one also need to check;
					liquefactionMap.put("storageCapacity","");//This one need to check;
					liquefactionMap.put("tanks","");//This one also need to check;
					mapList.add(liquefactionMap);
				}
			}
						
		}
			
		
		return mapList;
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
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	
}
