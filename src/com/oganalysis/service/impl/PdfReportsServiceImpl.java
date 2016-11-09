package com.oganalysis.service.impl;

import java.util.List;
import java.util.Map;

import com.oganalysis.dao.PdfReportsDao;
import com.oganalysis.entities.Report;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.PdfReportsService;

public class PdfReportsServiceImpl implements PdfReportsService {
	private PdfReportsDao pdfReportsDao;
	@Override
	public String getReportsList(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		String response=null;
		List<Report> reportsList=pdfReportsDao.getPdfReports(selectedOptions);
		JsonResponse jsonRes=new JsonResponse();
		response=jsonRes.createPdfReportsResponse(reportsList);
		return response;
		
	}
	public PdfReportsDao getPdfReportsDao() {
		return pdfReportsDao;
	}
	public void setPdfReportsDao(PdfReportsDao pdfReportsDao) {
		this.pdfReportsDao = pdfReportsDao;
	}
	
}
