package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Report;

public interface PdfReportsDao {
	public List<Report> getPdfReports(Map<String,List<String>> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries(Map<String,List<String>> selectedOptions);
	public List<String> getSectors(Map<String,List<String>> selectedOptions);
}
