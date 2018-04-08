package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.entities.Exploration;

public interface ExplorationDao {
	public List<Exploration> getSelectedExploration(Map<String, List<String>> selectedOptions); 	
	public List<String> getRegions();
	public List<String> getCountries();	
	public List<String> getBasins();	
	public List<String> getOwners();	
	public List<String> getOperators();	
	public List<String> getStatus();	
	public List<String> getType();
	
}
