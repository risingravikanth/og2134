package com.oganalysis.dao;

import java.util.List;

import com.oganalysis.entities.CompanyOilGas;

public interface CompanyOilGasDao {
	public List<CompanyOilGas> getCompanyOilGas(String country,String filterType);
}
