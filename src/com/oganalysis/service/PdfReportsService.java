package com.oganalysis.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PdfReportsService {
	public String getReportsList(Map<String,List<String>> selectedOptions);
	public ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName);
		
	public String getRegions();	
	public String getCountries(Map<String, List<String>> selectedOptions);
	public String getSectors(Map<String, List<String>> selectedOptions);
	
}
