package com.oganalysis.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.PdfReportsService;

@Controller
public class PdfReportsController {
	
	private PdfReportsService pdfReportsServiceImpl;
	
	@ResponseBody
	@RequestMapping("/pdfReports")
	public String pdfReportsFileList(HttpServletRequest request)
	{
		String response=null;
		Map<String,List> selectedOptions=getSelectedOptionsData(request);
		response=pdfReportsServiceImpl.getReportsList(selectedOptions);
		return response;
	}

	@RequestMapping("/pdf/reports/{fileName}")
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response,@PathVariable String fileName) throws IOException {
		 
		final ServletContext servletContext = request.getSession().getServletContext();
	    final String directory = (String) servletContext.getRealPath("/WEB-INF/pdf/");
	    File tempDirectory=new File(directory);
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
 
	    String actualFileName=fileName+".pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ actualFileName);
 
	    try {
 	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+actualFileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
 
	}
	
	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
 
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
	private Map<String,List> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List> optionsMap=new HashMap<String,List>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains("country"))
			{
				selectedCountries.add(request.getParameter(option));
			}
			else if(option.contains("region"))
			{
				selectedRegions.add(request.getParameter(option));
			}
		}
		if(selectedCountries.size()>0 || selectedRegions.size()>0)
		{	
			optionsMap.put("countries", selectedCountries);
			optionsMap.put("regions",selectedRegions);
		}	
		
		
		return optionsMap;
	}
	
	public PdfReportsService getPdfReportsServiceImpl() {
		return pdfReportsServiceImpl;
	}
	@Autowired
	public void setPdfReportsServiceImpl(PdfReportsService pdfReportsServiceImpl) {
		this.pdfReportsServiceImpl = pdfReportsServiceImpl;
	}
}
