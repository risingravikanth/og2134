package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.EXPORT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.SupplyDemandBusinessService;
import com.oganalysis.dao.SupplyDemandDao;
import com.oganalysis.entities.SupplyDemandExport;
import com.oganalysis.entities.SupplyDemandImport;

public class SupplyDemandBusinessServiceImpl implements
		SupplyDemandBusinessService {
	
	public SupplyDemandDao supplyDemandDao;
	@Override
	public List<Map<String,String>> getSupplyDemand(
			Map<String, List<String>> selectedOptions, String displayType) {
		// TODO Auto-generated method stub
		
		List<Map<String,String>> supplyDemandList=null;
		if(null!=displayType && displayType.equalsIgnoreCase(EXPORT))
		{
			List<SupplyDemandExport> supplyDemandDataExport=supplyDemandDao.getSupplyDemandExport(selectedOptions);
			supplyDemandList=createSupplyDemandExportData(supplyDemandDataExport);
		}			
		else
		{
			List<SupplyDemandImport> supplyDemandDataImport=supplyDemandDao.getSupplyDemandImport(selectedOptions);
			supplyDemandList=createSupplyDemandImportData(supplyDemandDataImport);
		}
			
		
		return supplyDemandList;
	}
	private List<Map<String,String>> createSupplyDemandExportData(List<SupplyDemandExport> supplyDemandList)
	{
		List<Map<String,String>> sdList=new ArrayList<Map<String,String>>();
		Map<String,String> sdMap=null;//new HashMap<String, String>();
		for(SupplyDemandExport supplyDemand:supplyDemandList)
		{
			sdMap=new HashMap<String, String>();
			sdMap.put(COUNTRY,supplyDemand.getCountry());
			sdMap.put("2000",String.valueOf(supplyDemand.getYear2000()));
			sdMap.put("2001",String.valueOf(supplyDemand.getYear2001()));
			sdMap.put("2002",String.valueOf(supplyDemand.getYear2002()));
			sdMap.put("2003",String.valueOf(supplyDemand.getYear2003()));
			sdMap.put("2004",String.valueOf(supplyDemand.getYear2004()));
			sdMap.put("2005",String.valueOf(supplyDemand.getYear2005()));
			sdMap.put("2006",String.valueOf(supplyDemand.getYear2006()));
			sdMap.put("2007",String.valueOf(supplyDemand.getYear2007()));
			sdMap.put("2008",String.valueOf(supplyDemand.getYear2008()));
			sdMap.put("2009",String.valueOf(supplyDemand.getYear2009()));
			sdMap.put("2010",String.valueOf(supplyDemand.getYear2010()));
			sdMap.put("2011",String.valueOf(supplyDemand.getYear2011()));
			sdMap.put("2012",String.valueOf(supplyDemand.getYear2012()));
			sdMap.put("2013",String.valueOf(supplyDemand.getYear2013()));
			sdMap.put("2014",String.valueOf(supplyDemand.getYear2014()));
			sdMap.put("2015",String.valueOf(supplyDemand.getYear2015()));
			sdMap.put("2016",String.valueOf(supplyDemand.getYear2016()));
			sdMap.put("2017",String.valueOf(supplyDemand.getYear2017()));
			sdMap.put("2018",String.valueOf(supplyDemand.getYear2018()));
			sdMap.put("2019",String.valueOf(supplyDemand.getYear2019()));
			sdMap.put("2020",String.valueOf(supplyDemand.getYear2020()));
			sdMap.put("2021",String.valueOf(supplyDemand.getYear2021()));
			sdMap.put("2022",String.valueOf(supplyDemand.getYear2022()));
			sdList.add(sdMap);
		}
		return sdList;
	}
	private List<Map<String,String>> createSupplyDemandImportData(List<SupplyDemandImport> supplyDemandList)
	{
		List<Map<String,String>> sdList=new ArrayList<Map<String,String>>();
		Map<String,String> sdMap=null;//new HashMap<String, String>();
		for(SupplyDemandImport supplyDemand:supplyDemandList)
		{
			sdMap=new HashMap<String, String>();
			sdMap.put(COUNTRY,supplyDemand.getCountry());
			sdMap.put("2000",String.valueOf(supplyDemand.getYear2000()));
			sdMap.put("2001",String.valueOf(supplyDemand.getYear2001()));
			sdMap.put("2002",String.valueOf(supplyDemand.getYear2002()));
			sdMap.put("2003",String.valueOf(supplyDemand.getYear2003()));
			sdMap.put("2004",String.valueOf(supplyDemand.getYear2004()));
			sdMap.put("2005",String.valueOf(supplyDemand.getYear2005()));
			sdMap.put("2006",String.valueOf(supplyDemand.getYear2006()));
			sdMap.put("2007",String.valueOf(supplyDemand.getYear2007()));
			sdMap.put("2008",String.valueOf(supplyDemand.getYear2008()));
			sdMap.put("2009",String.valueOf(supplyDemand.getYear2009()));
			sdMap.put("2010",String.valueOf(supplyDemand.getYear2010()));
			sdMap.put("2011",String.valueOf(supplyDemand.getYear2011()));
			sdMap.put("2012",String.valueOf(supplyDemand.getYear2012()));
			sdMap.put("2013",String.valueOf(supplyDemand.getYear2013()));
			sdMap.put("2014",String.valueOf(supplyDemand.getYear2014()));
			sdMap.put("2015",String.valueOf(supplyDemand.getYear2015()));
			sdMap.put("2016",String.valueOf(supplyDemand.getYear2016()));
			sdMap.put("2017",String.valueOf(supplyDemand.getYear2017()));
			sdMap.put("2018",String.valueOf(supplyDemand.getYear2018()));
			sdMap.put("2019",String.valueOf(supplyDemand.getYear2019()));
			sdMap.put("2020",String.valueOf(supplyDemand.getYear2020()));
			sdMap.put("2021",String.valueOf(supplyDemand.getYear2021()));
			sdMap.put("2022",String.valueOf(supplyDemand.getYear2022()));
			sdList.add(sdMap);
		}
		return sdList;
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return supplyDemandDao.getRegions();
	}
	@Override
	public List<String> getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return supplyDemandDao.getCountries(selectedOptions);
	}
	public SupplyDemandDao getSupplyDemandDao() {
		return supplyDemandDao;
	}
	public void setSupplyDemandDao(SupplyDemandDao supplyDemandDao) {
		this.supplyDemandDao = supplyDemandDao;
	}
	@Override
	public List<String> getCountries() {
		// TODO Auto-generated method stub
		return supplyDemandDao.getCountries();
	}
		
}
