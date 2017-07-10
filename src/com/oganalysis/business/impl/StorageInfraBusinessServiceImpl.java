package com.oganalysis.business.impl;
import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.StorageInfraBusinessService;
import com.oganalysis.cache.StorageCache;
import com.oganalysis.dao.StorageDao;
import com.oganalysis.entities.Refinery;
import com.oganalysis.entities.Storage;

public class StorageInfraBusinessServiceImpl implements
		StorageInfraBusinessService {
	private StorageDao storageDao;
	private StorageCache storageCache;
	@Override
	public List<Map<String, String>> getInfrastructure(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> terminals=storageDao.getSelectedTerminals(selectedOptions);
		Map<String,Map<String,String>> infraMap=null;
		if(null==storageCache.getInfrastructure())
		{
			infraMap=storageCache.createInfrastructure();
			storageCache.setInfrastructure(infraMap);
		}
		else
			infraMap=storageCache.getInfrastructure();
		
		List<Map<String,String>> infrastructureList=new ArrayList<Map<String,String>>();
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
		List<Storage> terminalData=null;
		for(String terminalName:terminals)
		{
			Map<String,String> terminalMap=new HashMap<String, String>();
			
			terminalData=storageDao.getTerminalData(terminalName);
			
			if(terminalData.size()>0)
			{
				Storage storage=terminalData.get(0);//getRefineryData(terminalData);
				terminalMap.put(TERMINALNAME, storage.getTankFarm());
				terminalMap.put(LOCATION, storage.getLocation());
				terminalMap.put(OPERATOR, getOperator(terminalData));
				terminalMap.put(STORAGE_CAPACITY,getCapacity(terminalData));
				infrastructureTerminalMap.put(terminalName.toLowerCase(), terminalMap);
			}
			
		}					
		return infrastructureTerminalMap;
	}
	public String getCapacity(List<Storage> terminalData)
	{
		int previousYear=getPreviousYear();
		Storage storageData=null;
		String capacityVal=null;
		for(Storage storage:terminalData)
		{
			if(storage.getCapacityYear()==previousYear)
				storageData=storage;
		}
		if(null==storageData)
			capacityVal=BLANK;
		else
			capacityVal=String.valueOf(storageData.getCapacityM3());
		return capacityVal;
	}
	public String getOperator(List<Storage> terminalData)
	{
		StringBuffer operator=new StringBuffer();
		for(Storage storage:terminalData)
		{
			if(null!=storage && !(BLANK).equalsIgnoreCase(storage.getCurrentOperator()))
				operator.append(storage.getCurrentOperator()).append(COMMA);
		}
		if(operator.length()>0)
			removeCommaAtEnd(operator);
		return operator.toString();
	}
	private int getPreviousYear()
	{
		Calendar c=Calendar.getInstance();
		int currYear=c.get(Calendar.YEAR);
		return currYear-1;
	}
	private void removeCommaAtEnd(StringBuffer inputString)
	{
		inputString.deleteCharAt(inputString.length()-1);
	}
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public void setStorageCache(StorageCache storageCache) {
		this.storageCache = storageCache;
	}
	
}
