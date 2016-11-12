package com.oganalysis.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface PdfReportsService {
	public String getReportsList(Map<String,List> selectedOptions);
	public ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName);
}
