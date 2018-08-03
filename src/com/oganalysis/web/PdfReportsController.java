package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.PdfReportsService;

@Controller
public class PdfReportsController {
	@Autowired
	private PdfReportsService pdfReportsServiceImpl;
	@Autowired
	private ServletContext servletContext;
	@ResponseBody
	@RequestMapping(value="/pdfReports",method={RequestMethod.POST})
	public String pdfReportsFileList(HttpServletRequest request)
	{
		String response=LOGIN;
		System.out.println("Jeevan");
		if(null!=request.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(request);
			response=pdfReportsServiceImpl.getReportsList(selectedOptions);
			
		}		
		return response;
	}

	@RequestMapping("/pdf/reports/{fileName}")
	public String downloadPDF(HttpServletRequest request, HttpServletResponse response,@PathVariable("fileName") String fileName) throws IOException, ServletException {
		 				
	    String actualFileName=fileName+".pdf";
	    String res=null;
	    InputStream is=null;
	    OutputStream os=null;	    
	    if(null!=request.getSession().getAttribute(EMAIL))
	    {
	    	response.setContentType("application/pdf");
	    	response.setHeader(EXCEL_CONTENT_DISPOSITION, PDF_ATTACHMENT+ actualFileName);	   
	    	 try {	    		 
	  	        is=servletContext.getResourceAsStream("/WEB-INF/pdf/"+actualFileName);	  	        
	  	        int read=0;
	  	        byte[] bytes=new byte[1024];
	  	       os= response.getOutputStream();
	  	       while ((read = is.read(bytes)) != -1) {
	  	    	   os.write(bytes, 0, read);
	  	       }
	 	    } catch (Exception e) {
	 	        return ERROR_MSG;	 	       
	 	    }
	    	 finally
	    	 {
	    		 try {
	    			os.flush();
	  	  	       	is.close();
	  	  	       	os.close();
				} catch (Exception e2) {
					return res;
				}
	    	 }
	    }
	    else
	    {
	    	return PDF_REDIRECT;
	    }
	   return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/pdfreport/regions",method={RequestMethod.POST})
	public String getRegions(HttpServletRequest request)
	{							
		return pdfReportsServiceImpl.getRegions();				
	}	
	@ResponseBody
	@RequestMapping(value="/pdfreport/countries",method={RequestMethod.POST})
	public String getCountries(HttpServletRequest request)
	{			
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(request);
		return pdfReportsServiceImpl.getCountries(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/pdfreport/sectors",method={RequestMethod.POST})
	public String getSectors(HttpServletRequest request)
	{			
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(request);
		return pdfReportsServiceImpl.getSectors(selectedOptions);			
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
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
			
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_SECTORS, selectedSectors);
			
				
		return optionsMap;
	}
	
	
}
