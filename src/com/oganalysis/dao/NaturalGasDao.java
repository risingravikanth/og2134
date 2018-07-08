package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.NaturalGas;

public interface NaturalGasDao {
	public List<NaturalGas> getNaturalGas(Map<String,List<String>> selectedOptions);
	
	public List<String> getRegions();
	public List<String> getCountries(Map<String,List<String>> selectedOptions);
}
