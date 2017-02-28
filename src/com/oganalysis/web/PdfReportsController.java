package com.oganalysis.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import static com.oganalysis.constants.ApplicationConstants.*;

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
	        baos = pdfReportsServiceImpl.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+actualFileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
 
	}
	
	
	private Map<String,List> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List> optionsMap=new HashMap<String,List>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedSectors=new ArrayList<String>();
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains(OPTION_COUNTRY))
			{				
				selectedCountries.add(request.getParameter(option));
			}
			else if(option.contains(OPTION_REGION))
			{
				selectedRegions.add(request.getParameter(option));
			}
			else if(option.contains(OPTION_SECTOR))
			{
				selectedSectors.add(request.getParameter(option));
			}
		}
		if(selectedCountries.size()>0 || selectedRegions.size()>0 || selectedSectors.size()>0)
		{	
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_SECTORS, selectedSectors);
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
