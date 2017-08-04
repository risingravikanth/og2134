package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.ProductionBusinessService;
import com.oganalysis.dao.CompanyOilGasDao;
import com.oganalysis.dao.CrudeOilDao;
import com.oganalysis.dao.NaturalGasDao;
import com.oganalysis.entities.CompanyOilGas;
import com.oganalysis.entities.CrudeOil;
import com.oganalysis.entities.NaturalGas;

public class ProductionBusinessServiceImpl implements ProductionBusinessService {
	private NaturalGasDao naturalGasDao;
	private CrudeOilDao crudeOilDao;
	private CompanyOilGasDao companyOilGasDao;
	private double GAS_MTOE=0.00913;
	private double GAS_MBOE=0.06935;
	private double GAS_BCMNG=0.01022;
	private double OIL_MTOE=0.04980;
	private double OIL_MBOE=0.36500;
	private double OIL_BCMNG=0.05475;
	private String defaultCountry="Algeria";
	@Override
	public List<Map<String,String>> getNaturalGasCapacityByCountry(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<NaturalGas> naturalGasList=naturalGasDao.getNaturalGas(selectedOptions);
		double gasUnit=getGasUnitValue(selectedOptions);
		List<Map<String,String>> naturalGasCapacity=getNaturalGasCapacityByCountry(naturalGasList,gasUnit);
		return naturalGasCapacity;
	}
	@Override
	public List<Map<String, String>> getNaturalGasCapacityByField(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<NaturalGas> naturalGasList=naturalGasDao.getNaturalGas(selectedOptions);
		double gasUnit=getGasUnitValue(selectedOptions);
		List<Map<String,String>> fieldCapacity=getNaturalGasCapacityByField(naturalGasList,gasUnit);
		return fieldCapacity;
	}

	@Override
	public List<Map<String, String>> getCrudeOilCapacityByField(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<CrudeOil> crudeOilList=crudeOilDao.getCrudeOil(selectedOptions);
		double oilUnit=getOilUnitValue(selectedOptions);
		List<Map<String,String>> fieldCapacity=getCrudeOilCapacityByField(crudeOilList,oilUnit);
		return fieldCapacity;
	}

	@Override
	public List<Map<String, String>> getCrudeOilCapacityByCountry(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<CrudeOil> crudeOilList=crudeOilDao.getCrudeOil(selectedOptions);
		double oilUnit=getOilUnitValue(selectedOptions);
		List<Map<String,String>> crudeOilCapacity=getCrudeOilCapacityByCountry(crudeOilList,oilUnit);
		return crudeOilCapacity;
	}
	private List<Map<String,String>> getCrudeOilCapacityByField(List<CrudeOil> crudeOilList,double oilUnit)
	{
		List<Map<String,String>> fieldCapacity=new ArrayList<Map<String,String>>();
		List<String> fields=getCrudeOilDistinctFields(crudeOilList);
		Map<String,String> fieldMap=null;
		for(String field:fields)
		{
			double total2005Capacity=0;
			double total2006Capacity=0;
			double total2007Capacity=0;
			double total2008Capacity=0;
			double total2009Capacity=0;
			double total2010Capacity=0;
			double total2011Capacity=0;
			double total2012Capacity=0;
			double total2013Capacity=0;
			double total2014Capacity=0;
			double total2015Capacity=0;
			double total2016Capacity=0;
			double total2017Capacity=0;	
			fieldMap=new HashMap<String, String>();
			for(CrudeOil co:crudeOilList)
			{
				if(field.equalsIgnoreCase(co.getField()))
				{
					if(co.getYear2005()!=0)
						total2005Capacity=co.getYear2005();
					if(co.getYear2006()!=0)
						total2006Capacity=co.getYear2006();
					if(co.getYear2007()!=0)
						total2007Capacity=co.getYear2007();
					if(co.getYear2008()!=0)
						total2008Capacity=co.getYear2008();
					if(co.getYear2009()!=0)
						total2009Capacity=co.getYear2009();
					if(co.getYear2010()!=0)
						total2010Capacity=co.getYear2010();
					if(co.getYear2011()!=0)
						total2011Capacity=co.getYear2011();
					if(co.getYear2012()!=0)
						total2012Capacity=co.getYear2012();
					if(co.getYear2013()!=0)
						total2013Capacity=co.getYear2013();
					if(co.getYear2014()!=0)
						total2014Capacity=co.getYear2014();
					if(co.getYear2015()!=0)
						total2015Capacity=co.getYear2015();
					if(co.getYear2016()!=0)
						total2016Capacity=co.getYear2016();
					if(co.getYear2017()!=0)
						total2017Capacity=co.getYear2017();					
				}
			 }
			 fieldMap.put(FIELD,field);
			 if(oilUnit!=0)
			 {
				 fieldMap.put(YEAR2005,String.valueOf(round(total2005Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2006,String.valueOf(round(total2006Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2007,String.valueOf(round(total2007Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2008,String.valueOf(round(total2008Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2009,String.valueOf(round(total2009Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2010,String.valueOf(round(total2010Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2011,String.valueOf(round(total2011Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2012,String.valueOf(round(total2012Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2013,String.valueOf(round(total2013Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2014,String.valueOf(round(total2014Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2015,String.valueOf(round(total2015Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2016,String.valueOf(round(total2016Capacity*oilUnit,2)));
				 fieldMap.put(YEAR2017,String.valueOf(round(total2017Capacity*oilUnit,2)));
			 }
			 else
			 {
				 fieldMap.put(YEAR2005,String.valueOf(round(total2005Capacity,2)));
				 fieldMap.put(YEAR2006,String.valueOf(round(total2006Capacity,2)));
				 fieldMap.put(YEAR2007,String.valueOf(round(total2007Capacity,2)));
				 fieldMap.put(YEAR2008,String.valueOf(round(total2008Capacity,2)));
				 fieldMap.put(YEAR2009,String.valueOf(round(total2009Capacity,2)));
				 fieldMap.put(YEAR2010,String.valueOf(round(total2010Capacity,2)));
				 fieldMap.put(YEAR2011,String.valueOf(round(total2011Capacity,2)));
				 fieldMap.put(YEAR2012,String.valueOf(round(total2012Capacity,2)));
				 fieldMap.put(YEAR2013,String.valueOf(round(total2013Capacity,2)));
				 fieldMap.put(YEAR2014,String.valueOf(round(total2014Capacity,2)));
				 fieldMap.put(YEAR2015,String.valueOf(round(total2015Capacity,2)));
				 fieldMap.put(YEAR2016,String.valueOf(round(total2016Capacity,2)));
				 fieldMap.put(YEAR2017,String.valueOf(round(total2017Capacity,2)));
			 }
			 
			 fieldCapacity.add(fieldMap);
		}
		return fieldCapacity;
	}
	private List<Map<String,String>> getNaturalGasCapacityByField(List<NaturalGas> naturalGasList,double gasUnit)
	{
		List<Map<String,String>> fieldCapacity=new ArrayList<Map<String,String>>();
		List<String> fields=getNaturalGasDistinctFields(naturalGasList);
		Map<String,String> fieldMap=null;
		for(String field:fields)
		{
			double total2005Capacity=0;
			double total2006Capacity=0;
			double total2007Capacity=0;
			double total2008Capacity=0;
			double total2009Capacity=0;
			double total2010Capacity=0;
			double total2011Capacity=0;
			double total2012Capacity=0;
			double total2013Capacity=0;
			double total2014Capacity=0;
			double total2015Capacity=0;
			double total2016Capacity=0;
			double total2017Capacity=0;	
			fieldMap=new HashMap<String, String>();
			for(NaturalGas ng:naturalGasList)
			{
				if(field.equalsIgnoreCase(ng.getField()))
				{
					if(ng.getYear2005()!=0)
						total2005Capacity=ng.getYear2005();
					if(ng.getYear2006()!=0)
						total2006Capacity=ng.getYear2006();
					if(ng.getYear2007()!=0)
						total2007Capacity=ng.getYear2007();
					if(ng.getYear2008()!=0)
						total2008Capacity=ng.getYear2008();
					if(ng.getYear2009()!=0)
						total2009Capacity=ng.getYear2009();
					if(ng.getYear2010()!=0)
						total2010Capacity=ng.getYear2010();
					if(ng.getYear2011()!=0)
						total2011Capacity=ng.getYear2011();
					if(ng.getYear2012()!=0)
						total2012Capacity=ng.getYear2012();
					if(ng.getYear2013()!=0)
						total2013Capacity=ng.getYear2013();
					if(ng.getYear2014()!=0)
						total2014Capacity=ng.getYear2014();
					if(ng.getYear2015()!=0)
						total2015Capacity=ng.getYear2015();
					if(ng.getYear2016()!=0)
						total2016Capacity=ng.getYear2016();
					if(ng.getYear2017()!=0)
						total2017Capacity=ng.getYear2017();					
				}
			 }
			 fieldMap.put(FIELD,field);
			 if(gasUnit!=0)
			 {
				 fieldMap.put(YEAR2005,String.valueOf(round(total2005Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2006,String.valueOf(round(total2006Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2007,String.valueOf(round(total2007Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2008,String.valueOf(round(total2008Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2009,String.valueOf(round(total2009Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2010,String.valueOf(round(total2010Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2011,String.valueOf(round(total2011Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2012,String.valueOf(round(total2012Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2013,String.valueOf(round(total2013Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2014,String.valueOf(round(total2014Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2015,String.valueOf(round(total2015Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2016,String.valueOf(round(total2016Capacity*gasUnit,2)));
				 fieldMap.put(YEAR2017,String.valueOf(round(total2017Capacity*gasUnit,2)));
			 }
			 else
			 {
				 fieldMap.put(YEAR2005,String.valueOf(round(total2005Capacity,2)));
				 fieldMap.put(YEAR2006,String.valueOf(round(total2006Capacity,2)));
				 fieldMap.put(YEAR2007,String.valueOf(round(total2007Capacity,2)));
				 fieldMap.put(YEAR2008,String.valueOf(round(total2008Capacity,2)));
				 fieldMap.put(YEAR2009,String.valueOf(round(total2009Capacity,2)));
				 fieldMap.put(YEAR2010,String.valueOf(round(total2010Capacity,2)));
				 fieldMap.put(YEAR2011,String.valueOf(round(total2011Capacity,2)));
				 fieldMap.put(YEAR2012,String.valueOf(round(total2012Capacity,2)));
				 fieldMap.put(YEAR2013,String.valueOf(round(total2013Capacity,2)));
				 fieldMap.put(YEAR2014,String.valueOf(round(total2014Capacity,2)));
				 fieldMap.put(YEAR2015,String.valueOf(round(total2015Capacity,2)));
				 fieldMap.put(YEAR2016,String.valueOf(round(total2016Capacity,2)));
				 fieldMap.put(YEAR2017,String.valueOf(round(total2017Capacity,2)));
			 }
			 
			 fieldCapacity.add(fieldMap);
		}
		return fieldCapacity;
	}
	private List<String> getNaturalGasDistinctFields(List<NaturalGas> naturalGasList)
	{
		List<String> list=new ArrayList<String>();
		for(NaturalGas ng:naturalGasList)
		{
			if(!list.contains(ng.getField()))
				list.add(ng.getField());
		}
		return list;
	}
	private List<String> getCrudeOilDistinctFields(List<CrudeOil> crudeOilList)
	{
		List<String> list=new ArrayList<String>();
		for(CrudeOil co:crudeOilList)
		{
			if(!list.contains(co.getField()))
				list.add(co.getField());
		}
		return list;
	}
	private List<Map<String,String>> getCrudeOilCapacityByCountry(List<CrudeOil> crudeOilList,double oilUnit)
	{
		List<Map<String,String>> capacity=new ArrayList<Map<String,String>>();
		List<String> countries=getCrudeOilDistinctCountries(crudeOilList);
		Map<String,String> countryMap=null;
		for(String country:countries)
		{
			double total2005Capacity=0;
			double total2006Capacity=0;
			double total2007Capacity=0;
			double total2008Capacity=0;
			double total2009Capacity=0;
			double total2010Capacity=0;
			double total2011Capacity=0;
			double total2012Capacity=0;
			double total2013Capacity=0;
			double total2014Capacity=0;
			double total2015Capacity=0;
			double total2016Capacity=0;
			double total2017Capacity=0;	
			countryMap=new HashMap<String, String>();
			for(CrudeOil co:crudeOilList)
			{
				
				if(country.equalsIgnoreCase(co.getCountry()))
				{
					total2005Capacity=total2005Capacity+co.getYear2005();
					total2006Capacity=total2006Capacity+co.getYear2006();
					total2007Capacity=total2007Capacity+co.getYear2007();
					total2008Capacity=total2008Capacity+co.getYear2008();
					total2009Capacity=total2009Capacity+co.getYear2009();
					total2010Capacity=total2010Capacity+co.getYear2010();
					total2011Capacity=total2011Capacity+co.getYear2011();
					total2012Capacity=total2012Capacity+co.getYear2012();
					total2013Capacity=total2013Capacity+co.getYear2013();
					total2014Capacity=total2014Capacity+co.getYear2014();
					total2015Capacity=total2015Capacity+co.getYear2015();
					total2016Capacity=total2016Capacity+co.getYear2016();
					total2017Capacity=total2017Capacity+co.getYear2017();
				}
				
			}
			countryMap.put(COUNTRY,country);
			if(oilUnit!=0)
			{
				countryMap.put(YEAR2005,String.valueOf(round(total2005Capacity*oilUnit,2)));
				countryMap.put(YEAR2006,String.valueOf(round(total2006Capacity*oilUnit,2)));
				countryMap.put(YEAR2007,String.valueOf(round(total2007Capacity*oilUnit,2)));
				countryMap.put(YEAR2008,String.valueOf(round(total2008Capacity*oilUnit,2)));
				countryMap.put(YEAR2009,String.valueOf(round(total2009Capacity*oilUnit,2)));
				countryMap.put(YEAR2010,String.valueOf(round(total2010Capacity*oilUnit,2)));
				countryMap.put(YEAR2011,String.valueOf(round(total2011Capacity*oilUnit,2)));
				countryMap.put(YEAR2012,String.valueOf(round(total2012Capacity*oilUnit,2)));
				countryMap.put(YEAR2013,String.valueOf(round(total2013Capacity*oilUnit,2)));
				countryMap.put(YEAR2014,String.valueOf(round(total2014Capacity*oilUnit,2)));
				countryMap.put(YEAR2015,String.valueOf(round(total2015Capacity*oilUnit,2)));
				countryMap.put(YEAR2016,String.valueOf(round(total2016Capacity*oilUnit,2)));
				countryMap.put(YEAR2017,String.valueOf(round(total2017Capacity*oilUnit,2)));
			}
			else
			{
				countryMap.put(YEAR2005,String.valueOf(round(total2005Capacity,2)));
				countryMap.put(YEAR2006,String.valueOf(round(total2006Capacity,2)));
				countryMap.put(YEAR2007,String.valueOf(round(total2007Capacity,2)));
				countryMap.put(YEAR2008,String.valueOf(round(total2008Capacity,2)));
				countryMap.put(YEAR2009,String.valueOf(round(total2009Capacity,2)));
				countryMap.put(YEAR2010,String.valueOf(round(total2010Capacity,2)));
				countryMap.put(YEAR2011,String.valueOf(round(total2011Capacity,2)));
				countryMap.put(YEAR2012,String.valueOf(round(total2012Capacity,2)));
				countryMap.put(YEAR2013,String.valueOf(round(total2013Capacity,2)));
				countryMap.put(YEAR2014,String.valueOf(round(total2014Capacity,2)));
				countryMap.put(YEAR2015,String.valueOf(round(total2015Capacity,2)));
				countryMap.put(YEAR2016,String.valueOf(round(total2016Capacity,2)));
				countryMap.put(YEAR2017,String.valueOf(round(total2017Capacity,2)));
			}
			
			capacity.add(countryMap);
		}
		return capacity;
	}
	private List<Map<String,String>> getNaturalGasCapacityByCountry(List<NaturalGas> naturalGasList,double gasUnit)
	{
		List<Map<String,String>> capacity=new ArrayList<Map<String,String>>();
		List<String> countries=getNaturalGasDistinctCountries(naturalGasList);
		
		Map<String,String> countryMap=null;
		for(String country:countries)
		{
			double total2005Capacity=0;
			double total2006Capacity=0;
			double total2007Capacity=0;
			double total2008Capacity=0;
			double total2009Capacity=0;
			double total2010Capacity=0;
			double total2011Capacity=0;
			double total2012Capacity=0;
			double total2013Capacity=0;
			double total2014Capacity=0;
			double total2015Capacity=0;
			double total2016Capacity=0;
			double total2017Capacity=0;	
			countryMap=new HashMap<String, String>();
			for(NaturalGas ng:naturalGasList)
			{
				
				if(country.equalsIgnoreCase(ng.getCountry()))
				{
					total2005Capacity=total2005Capacity+ng.getYear2005();
					total2006Capacity=total2006Capacity+ng.getYear2006();
					total2007Capacity=total2007Capacity+ng.getYear2007();
					total2008Capacity=total2008Capacity+ng.getYear2008();
					total2009Capacity=total2009Capacity+ng.getYear2009();
					total2010Capacity=total2010Capacity+ng.getYear2010();
					total2011Capacity=total2011Capacity+ng.getYear2011();
					total2012Capacity=total2012Capacity+ng.getYear2012();
					total2013Capacity=total2013Capacity+ng.getYear2013();
					total2014Capacity=total2014Capacity+ng.getYear2014();
					total2015Capacity=total2015Capacity+ng.getYear2015();
					total2016Capacity=total2016Capacity+ng.getYear2016();
					total2017Capacity=total2017Capacity+ng.getYear2017();
				}
				
			}
			countryMap.put(COUNTRY,country);
			if(gasUnit!=0)
			{
				countryMap.put(YEAR2005,String.valueOf(round(total2005Capacity*gasUnit,2)));
				countryMap.put(YEAR2006,String.valueOf(round(total2006Capacity*gasUnit,2)));
				countryMap.put(YEAR2007,String.valueOf(round(total2007Capacity*gasUnit,2)));
				countryMap.put(YEAR2008,String.valueOf(round(total2008Capacity*gasUnit,2)));
				countryMap.put(YEAR2009,String.valueOf(round(total2009Capacity*gasUnit,2)));
				countryMap.put(YEAR2010,String.valueOf(round(total2010Capacity*gasUnit,2)));
				countryMap.put(YEAR2011,String.valueOf(round(total2011Capacity*gasUnit,2)));
				countryMap.put(YEAR2012,String.valueOf(round(total2012Capacity*gasUnit,2)));
				countryMap.put(YEAR2013,String.valueOf(round(total2013Capacity*gasUnit,2)));
				countryMap.put(YEAR2014,String.valueOf(round(total2014Capacity*gasUnit,2)));
				countryMap.put(YEAR2015,String.valueOf(round(total2015Capacity*gasUnit,2)));
				countryMap.put(YEAR2016,String.valueOf(round(total2016Capacity*gasUnit,2)));
				countryMap.put(YEAR2017,String.valueOf(round(total2017Capacity*gasUnit,2)));
			}
			else
			{
				countryMap.put(YEAR2005,String.valueOf(round(total2005Capacity,2)));
				countryMap.put(YEAR2006,String.valueOf(round(total2006Capacity,2)));
				countryMap.put(YEAR2007,String.valueOf(round(total2007Capacity,2)));
				countryMap.put(YEAR2008,String.valueOf(round(total2008Capacity,2)));
				countryMap.put(YEAR2009,String.valueOf(round(total2009Capacity,2)));
				countryMap.put(YEAR2010,String.valueOf(round(total2010Capacity,2)));
				countryMap.put(YEAR2011,String.valueOf(round(total2011Capacity,2)));
				countryMap.put(YEAR2012,String.valueOf(round(total2012Capacity,2)));
				countryMap.put(YEAR2013,String.valueOf(round(total2013Capacity,2)));
				countryMap.put(YEAR2014,String.valueOf(round(total2014Capacity,2)));
				countryMap.put(YEAR2015,String.valueOf(round(total2015Capacity,2)));
				countryMap.put(YEAR2016,String.valueOf(round(total2016Capacity,2)));
				countryMap.put(YEAR2017,String.valueOf(round(total2017Capacity,2)));
			}
			
			capacity.add(countryMap);
		}
		return capacity;
	}
	@Override
	public List<Map<String, String>> getCompanyOilGasCapacity(Map<String,List<String>> selectedOptions,
			String filterType) {
		// TODO Auto-generated method stub
		String country=null;
		if(null!=selectedOptions.get(OPTION_SELECTED_COUNTRIES) && selectedOptions.get(OPTION_SELECTED_COUNTRIES).size()>0)
			country=selectedOptions.get(OPTION_SELECTED_COUNTRIES).get(0);
		else
			country=defaultCountry;
		List<CompanyOilGas> companiesOilGasList=companyOilGasDao.getCompanyOilGas(country, filterType);
		double units=0;
		if(filterType.equals(NATURALGAS))
			units=getGasUnitValue(selectedOptions);
		else
			units=getOilUnitValue(selectedOptions);
		List<Map<String,String>> companiesOilGasMapList=new ArrayList<Map<String,String>>();
		Map<String,String> countryOilGasMap=null;
		for(CompanyOilGas compOilGas:companiesOilGasList)
		{
			countryOilGasMap=new HashMap<String, String>();
			countryOilGasMap.put(COMPANY,compOilGas.getName());
			if(units!=0)
			{
				countryOilGasMap.put(YEAR2005,String.valueOf(round(compOilGas.getYear2005()*units,2)));
				countryOilGasMap.put(YEAR2006,String.valueOf(round(compOilGas.getYear2006()*units,2)));
				countryOilGasMap.put(YEAR2007,String.valueOf(round(compOilGas.getYear2007()*units,2)));
				countryOilGasMap.put(YEAR2008,String.valueOf(round(compOilGas.getYear2008()*units,2)));
				countryOilGasMap.put(YEAR2009,String.valueOf(round(compOilGas.getYear2009()*units,2)));
				countryOilGasMap.put(YEAR2010,String.valueOf(round(compOilGas.getYear2010()*units,2)));
				countryOilGasMap.put(YEAR2011,String.valueOf(round(compOilGas.getYear2011()*units,2)));
				countryOilGasMap.put(YEAR2012,String.valueOf(round(compOilGas.getYear2012()*units,2)));
				countryOilGasMap.put(YEAR2013,String.valueOf(round(compOilGas.getYear2013()*units,2)));
				countryOilGasMap.put(YEAR2014,String.valueOf(round(compOilGas.getYear2014()*units,2)));
				countryOilGasMap.put(YEAR2015,String.valueOf(round(compOilGas.getYear2015()*units,2)));
				countryOilGasMap.put(YEAR2016,String.valueOf(round(compOilGas.getYear2016()*units,2)));
				countryOilGasMap.put(YEAR2017,String.valueOf(round(compOilGas.getYear2017()*units,2)));
			}
			else
			{
				countryOilGasMap.put(YEAR2005,String.valueOf(round(compOilGas.getYear2005(),2)));
				countryOilGasMap.put(YEAR2006,String.valueOf(round(compOilGas.getYear2006(),2)));
				countryOilGasMap.put(YEAR2007,String.valueOf(round(compOilGas.getYear2007(),2)));
				countryOilGasMap.put(YEAR2008,String.valueOf(round(compOilGas.getYear2008(),2)));
				countryOilGasMap.put(YEAR2009,String.valueOf(round(compOilGas.getYear2009(),2)));
				countryOilGasMap.put(YEAR2010,String.valueOf(round(compOilGas.getYear2010(),2)));
				countryOilGasMap.put(YEAR2011,String.valueOf(round(compOilGas.getYear2011(),2)));
				countryOilGasMap.put(YEAR2012,String.valueOf(round(compOilGas.getYear2012(),2)));
				countryOilGasMap.put(YEAR2013,String.valueOf(round(compOilGas.getYear2013(),2)));
				countryOilGasMap.put(YEAR2014,String.valueOf(round(compOilGas.getYear2014(),2)));
				countryOilGasMap.put(YEAR2015,String.valueOf(round(compOilGas.getYear2015(),2)));
				countryOilGasMap.put(YEAR2016,String.valueOf(round(compOilGas.getYear2016(),2)));
				countryOilGasMap.put(YEAR2017,String.valueOf(round(compOilGas.getYear2017(),2)));
			}
		
			companiesOilGasMapList.add(countryOilGasMap);
		}
		return companiesOilGasMapList;
	}
	private List<String> getNaturalGasDistinctCountries(List<NaturalGas> naturalGasList)
	{
		List<String> list=new ArrayList<String>();
		for(NaturalGas ng:naturalGasList)
		{
			if(!list.contains(ng.getCountry()))
				list.add(ng.getCountry());
		}
		return list;
	}
	private List<String> getCrudeOilDistinctCountries(List<CrudeOil> crudeOilList)
	{
		List<String> list=new ArrayList<String>();
		for(CrudeOil co:crudeOilList)
		{
			if(!list.contains(co.getCountry()))
				list.add(co.getCountry());
		}
		return list;
	}
	private double getGasUnitValue(Map<String,List<String>> selectedOptions)
	{
		List<String> units=selectedOptions.get(OPTION_SELECTED_UNITS);
		double unitVal=0;
		String unit=null;
		if(null!=units && units.size()>0)
			unit=units.get(0);
		if(null!=unit && unit.equalsIgnoreCase(MTOE))
			unitVal=GAS_MTOE;
		else if(null!=unit && unit.equalsIgnoreCase(MBOE))
			unitVal=GAS_MBOE;
		else if(null!=unit && unit.equalsIgnoreCase(BCMNG))
			unitVal=GAS_BCMNG;
		return unitVal;
	}
	private double getOilUnitValue(Map<String,List<String>> selectedOptions)
	{
		List<String> units=selectedOptions.get(OPTION_SELECTED_UNITS);
		double unitVal=0;
		String unit=null;
		if(null!=units && units.size()>0)
			unit=units.get(0);
		if(null!=unit && unit.equalsIgnoreCase(MTOE))
			unitVal=OIL_MTOE;
		else if(null!=unit && unit.equalsIgnoreCase(MBOE))
			unitVal=OIL_MBOE;
		else if(null!=unit && unit.equalsIgnoreCase(BCMNG))
			unitVal=OIL_BCMNG;
		return unitVal;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public void setNaturalGasDao(NaturalGasDao naturalGasDao) {
		this.naturalGasDao = naturalGasDao;
	}
	
	public void setCrudeOilDao(CrudeOilDao crudeOilDao) {
		this.crudeOilDao = crudeOilDao;
	}
	public void setCompanyOilGasDao(CompanyOilGasDao companyOilGasDao) {
		this.companyOilGasDao = companyOilGasDao;
	}	
	
}
