package com.oganalysis.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.oganalysis.dao.PdfReportsDao;
import com.oganalysis.entities.Report;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.ReportsJsonResponse;
import com.oganalysis.service.PdfReportsService;

public class PdfReportsServiceImpl implements PdfReportsService {
	private PdfReportsDao pdfReportsDao;
	@Override
	public String getReportsList(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String response=null;
		List<Report> reportsList=pdfReportsDao.getPdfReports(selectedOptions);
		ReportsJsonResponse jsonRes=new ReportsJsonResponse();
		response=jsonRes.createPdfReportsResponse(reportsList);
		return response;
		
	}
	public ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
		 
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		List<String> regions=pdfReportsDao.getRegions();
		JsonResponse res=new JsonResponse();
		return res.createRegions(regions);
	}
	@Override
	public String getCountries(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> countries=pdfReportsDao.getCountries(selectedOptions);
		JsonResponse res=new JsonResponse();
		return res.createCountries(countries);
	}
	@Override
	public String getSectors(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> sectors=pdfReportsDao.getSectors(selectedOptions);
		JsonResponse res=new JsonResponse();
		return res.createSectors(sectors);
	}	
	public PdfReportsDao getPdfReportsDao() {
		return pdfReportsDao;
	}
	public void setPdfReportsDao(PdfReportsDao pdfReportsDao) {
		this.pdfReportsDao = pdfReportsDao;
	}
	
	
}
