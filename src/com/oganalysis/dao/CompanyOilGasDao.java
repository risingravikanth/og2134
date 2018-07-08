package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.CompanyOilGas;

public interface CompanyOilGasDao {
	public List<CompanyOilGas> getCompanyOilGas(String country,String filterType);
		
	public List<String> getCountries();
}
