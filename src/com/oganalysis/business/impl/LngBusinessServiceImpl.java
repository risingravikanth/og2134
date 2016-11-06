package com.oganalysis.business.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngBusinessService;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;

public class LngBusinessServiceImpl implements LngBusinessService {

	private LngDao lngDao;
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
	@Override
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions,startDateVal,endDateVal);		
				
		Map<String,Map<Integer,Double>> regasification=calculateCapactiesByCountry(regasificationList);						
		
		return regasification;
	}
	@Override
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateCapactiesByCountry(liquefactionList);
	
		return liquefaction;
	}
	private Map<String,Map<Integer,Double>> calculateCapactiesByCountry(List<Lng> dataList)
	{
		Set<String> trackerCountry=new HashSet<String>();		
		List<Lng> copyDataList=dataList;
		List<Lng> copyDataList2=dataList;
		Map<String,Map<Integer,Double>> countryMap=new HashMap<String, Map<Integer,Double>>();
		Map<Integer,Double> yearMap=null;
		for(Lng lng:dataList)
		{
			String countryName=lng.getCountry();
			if(!trackerCountry.contains(countryName))
			{
				yearMap=new HashMap<Integer, Double>();
				Set<Integer> trackerYear=new HashSet<Integer>();
				for(Lng lngCopy1:copyDataList)
				{
					if(countryName.equals(lngCopy1.getCountry()))
					{
						int capacityYear=lngCopy1.getCapacityYear();
						if(!trackerYear.contains(capacityYear))
						{							
							double soc=0;
							for(Lng lngCopy2:copyDataList2)
							{
//								System.out.println(lngCopy2.getName()+" Year:"+lngCopy2.getCapacityYear());
								if(capacityYear==lngCopy2.getCapacityYear() && countryName.equalsIgnoreCase(lngCopy2.getCountry()))
									soc=soc+lngCopy2.getCapacity();
							}
							trackerYear.add(capacityYear);
							yearMap.put(capacityYear,soc);
						}
						
					}
				}
				trackerCountry.add(countryName);
				countryMap.put(countryName, yearMap);
			}
//			countryMap.put(countryName, yearMap);
		}
		return countryMap;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	
}
