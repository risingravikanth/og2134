package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.oganalysis.business.PipeLineBusinessService;
import com.oganalysis.dao.PipeLineDao;
import com.oganalysis.entities.PipeLine;

public class PipeLineBusinessServiceImpl implements PipeLineBusinessService {
	private PipeLineDao pipelineDao;
	@Override
	public Map<String, Double> getDomesticLengthByCountry(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Map<String,Double> countriesLength=new HashMap<String, Double>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,DOMESTIC);
		if(selectedPipelines.size()>0)
			countriesLength=calculateLengthByCountry(selectedPipelines);
		return countriesLength;
	}

	@Override
	public Map<String, Double> getDomesticLengthByCompany(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Map<String,Double> companiesLength=new HashMap<String, Double>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,DOMESTIC);
		if(selectedPipelines.size()>0)
			companiesLength=calculateLengthByCompany(selectedPipelines);
		return companiesLength;
	}
	@Override
	public Map<String,List<Map>> getDomesticPipeLines(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Map<String,List<Map>> pipelines=new HashMap<String, List<Map>>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,DOMESTIC);
		if(selectedPipelines.size()>0)
			pipelines=createDomesticPipelines(selectedOptions,selectedPipelines);
		return pipelines;
	}
	private Map<String,List<Map>> createDomesticPipelines(Map<String, List<String>> selectedOptions,List<PipeLine> selectedPipelines)
	{
		List<String> pipeLinesSelected=getSelectedPipelines(selectedPipelines);
		List<PipeLine> childSelectedPipelines=pipelineDao.getNonBlankChildSelectedPipeLines(selectedOptions,CHILD,DOMESTIC);
		Map<String,List<Map>> pipelinesMap=new TreeMap<String, List<Map>>();
		
		for(String pipelineStr:pipeLinesSelected)
		{
			List<Map> pipelinesList=new ArrayList<Map>();
			for(PipeLine pipeLineObj:selectedPipelines)
			{
				Map pipelineDataMap=new HashMap();
				int sortKey=1;
				if(pipelineStr.equals(pipeLineObj.getPipeline()) && (PARENT).equals(pipeLineObj.getParentChildRelation()))
				{
					pipelineDataMap.put(PIPELINE, pipeLineObj.getPipeline());
					pipelineDataMap.put(RELATION,PARENT);
					pipelineDataMap.put(SUBPIPELINE, pipeLineObj.getSubPipelines());
					pipelineDataMap.put(STARTPOINT, pipeLineObj.getStartPoint());
					pipelineDataMap.put(ENDPOINT, pipeLineObj.getEndPoint());
					pipelineDataMap.put(LENGTH, round(pipeLineObj.getLength(),2));
					pipelineDataMap.put(DIAMETER, round(pipeLineObj.getDiameter(),2));
					pipelineDataMap.put(CAPACITY, round(pipeLineObj.getCapacity(),2));
					pipelinesList.add(0,pipelineDataMap);
					for(PipeLine pipelineChildObj:childSelectedPipelines)
					{
						Map pipelineChildDataMap=new HashMap();					
						if(pipelineStr.equals(pipelineChildObj.getPipeline()))
						{
							pipelineChildDataMap.put(PIPELINE,BLANK);
							pipelineChildDataMap.put(RELATION,CHILD);
							pipelineChildDataMap.put(SUBPIPELINE, pipelineChildObj.getSubPipelines());
							pipelineChildDataMap.put(STARTPOINT, pipelineChildObj.getStartPoint());
							pipelineChildDataMap.put(ENDPOINT, pipelineChildObj.getEndPoint());
							pipelineChildDataMap.put(LENGTH, round(pipelineChildObj.getLength(),2));
							pipelineChildDataMap.put(DIAMETER,round(pipelineChildObj.getDiameter(),2));
							pipelineChildDataMap.put(CAPACITY, round(pipelineChildObj.getCapacity(),2));
							pipelinesList.add(sortKey,pipelineChildDataMap);
							sortKey++;
						}
				
					}
				}	
			}
			pipelinesMap.put(pipelineStr,pipelinesList);
		}			
		return pipelinesMap;
	}
	@Override
	public Map<String, Double> getTransNationalLengthByCountry(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		 
		Map<String,Double> countriesLength=new HashMap<String, Double>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,TRANSNATIONAL);
		if(selectedPipelines.size()>0)
			countriesLength=calculateLengthByCountry(selectedPipelines);
		return countriesLength;
	
	}

	@Override
	public Map<String, Double> getTransNationalLengthByCompany(
			Map<String, List<String>> selectedOptions) {
		Map<String,Double> companiesLength=new HashMap<String, Double>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,TRANSNATIONAL);
		if(selectedPipelines.size()>0)
			companiesLength=calculateLengthByCompany(selectedPipelines);
		return companiesLength;
	}

	@Override
	public Map<String, List<Map>> getTransNationalPipeLines(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		Map<String,List<Map>> pipelines=new HashMap<String, List<Map>>();
		
		List<PipeLine> selectedPipelines=pipelineDao.getSelectedPipeLines(selectedOptions, PARENT,TRANSNATIONAL);
		if(selectedPipelines.size()>0)
			pipelines=createTransNationalPipelines(selectedOptions,selectedPipelines);
		return pipelines;
	}
	private Map<String,List<Map>> createTransNationalPipelines(Map<String, List<String>> selectedOptions,List<PipeLine> selectedPipelines)
	{
		List<String> pipeLinesSelected=getSelectedPipelines(selectedPipelines);
		List<PipeLine> childSelectedPipelines=pipelineDao.getNonBlankChildSelectedPipeLines(selectedOptions,CHILD,TRANSNATIONAL);
		Map<String,List<Map>> pipelinesMap=new TreeMap<String, List<Map>>();
		
		for(String pipelineStr:pipeLinesSelected)
		{
			List<Map> pipelinesList=new ArrayList<Map>();
			for(PipeLine pipeLineObj:selectedPipelines)
			{
				Map pipelineDataMap=new HashMap();
				int sortKey=1;
				if(pipelineStr.equals(pipeLineObj.getPipeline()) && (PARENT).equals(pipeLineObj.getParentChildRelation()))
				{
					pipelineDataMap.put(PIPELINE, pipeLineObj.getPipeline());
					pipelineDataMap.put(RELATION,PARENT);
					pipelineDataMap.put(SUBPIPELINE, pipeLineObj.getSubPipelines());
					pipelineDataMap.put(STARTPOINT, pipeLineObj.getStartPoint());
					pipelineDataMap.put(ENDPOINT, pipeLineObj.getEndPoint());
					pipelineDataMap.put(STARTCOUNTRY,pipeLineObj.getStartCountry());
					pipelineDataMap.put(ENDCOUNTRY,pipeLineObj.getEndCountry());
					pipelineDataMap.put(LENGTH, pipeLineObj.getLength());
					pipelineDataMap.put(DIAMETER, pipeLineObj.getDiameter());
					pipelineDataMap.put(CAPACITY, pipeLineObj.getCapacity());
					pipelinesList.add(0,pipelineDataMap);
					for(PipeLine pipelineChildObj:childSelectedPipelines)
					{
						Map pipelineChildDataMap=new HashMap();					
						if(pipelineStr.equals(pipelineChildObj.getPipeline()))
						{
							pipelineChildDataMap.put(PIPELINE,BLANK);
							pipelineChildDataMap.put(RELATION,CHILD);
							pipelineChildDataMap.put(SUBPIPELINE, pipelineChildObj.getSubPipelines());
							pipelineChildDataMap.put(STARTPOINT, pipelineChildObj.getStartPoint());
							pipelineChildDataMap.put(ENDPOINT, pipelineChildObj.getEndPoint());
							pipelineDataMap.put(STARTCOUNTRY,pipelineChildObj.getStartCountry());
							pipelineDataMap.put(ENDCOUNTRY,pipelineChildObj.getEndCountry());
							pipelineChildDataMap.put(LENGTH, pipelineChildObj.getLength());
							pipelineChildDataMap.put(DIAMETER, pipelineChildObj.getDiameter());
							pipelineChildDataMap.put(CAPACITY, pipelineChildObj.getCapacity());
							pipelinesList.add(sortKey,pipelineChildDataMap);
							sortKey++;
						}
				
					}
				}	
			}
			pipelinesMap.put(pipelineStr,pipelinesList);
		}
		return pipelinesMap;
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return pipelineDao.getRegions();
	}

	@Override
	public List<String> getCountries(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return pipelineDao.getCountries(selectedOptions);
	}

	@Override
	public List<String> getCommodities(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return pipelineDao.getCommodities(selectedOptions);
	}

	@Override
	public List<String> getStartPoints(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return pipelineDao.getStartPoints(selectedOptions);
	}

	@Override
	public List<String> getEndPoints(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return pipelineDao.getEndPoints(selectedOptions);
	}

	@Override
	public List<String> getStatus(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		return pipelineDao.getStatus(selectedOptions);
	}
	private List<String> getSelectedPipelines(List<PipeLine> selectedPipeLines)
	{
		List<String> pipelines=new ArrayList<String>();
		for(PipeLine pipeline:selectedPipeLines)
		{
			if(!pipelines.contains(pipeline.getPipeline()))
				pipelines.add(pipeline.getPipeline());
		}
		return pipelines;
	}
	private Map<String,Double> calculateLengthByCompany(List<PipeLine> selectedPipelines)
	{
		Map<String,Double> lengthByCountries=new HashMap<String, Double>();
		List<String> selectedCompanies=getSelectedCompanies(selectedPipelines);
		
		for(String company:selectedCompanies)
		{
			double tol=0;
			for(PipeLine pipeline:selectedPipelines)
			{
				if(null!=pipeline && pipeline.getEquityPartners().equalsIgnoreCase(company))
					tol=tol+pipeline.getLength();
			}
			lengthByCountries.put(company, round(tol,2));
		}
		
		return lengthByCountries;
	}
	private Map<String,Double> calculateLengthByCountry(List<PipeLine> selectedPipelines)
	{
		Map<String,Double> lengthByCountries=new HashMap<String, Double>();
		List<String> selectedCountries=getSelectedCountries(selectedPipelines);
		
		for(String country:selectedCountries)
		{
			double tol=0;
			for(PipeLine pipeline:selectedPipelines)
			{
				if(null!=pipeline && pipeline.getCountry().equalsIgnoreCase(country))
					tol=tol+pipeline.getLength();
			}
			lengthByCountries.put(country, round(tol,2));
		}
		
		return lengthByCountries;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	private List<String> getSelectedCountries(List<PipeLine> selectedPipeLines){
		List<String> countries=new ArrayList<String>();
		for(PipeLine pipeline:selectedPipeLines)
		{
			if(!countries.contains(pipeline.getCountry()))
				countries.add(pipeline.getCountry());
		}
		return countries;
	}
	private List<String> getSelectedCompanies(List<PipeLine> selectedPipeLines){
		List<String> companies=new ArrayList<String>();
		for(PipeLine pipeline:selectedPipeLines)
		{
			if(!companies.contains(pipeline.getEquityPartners()))
				companies.add(pipeline.getEquityPartners());
		}
		return companies;
	}
	public void setPipelineDao(PipeLineDao pipelineDao) {
		this.pipelineDao = pipelineDao;
	}
	
		
}
