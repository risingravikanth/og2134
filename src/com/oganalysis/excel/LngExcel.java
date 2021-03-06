package com.oganalysis.excel;

import static com.oganalysis.constants.ApplicationConstants.*;


import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LngExcel {
	
	public Workbook getExcelTerminalData(Map terminalData,InputStream is)
	{
		Workbook wb=new XSSFWorkbook();
		Sheet lngSheet=wb.createSheet(EXCEL_LNG+(String)terminalData.get(TERMINALNAME));				
		ExcelFileHelper.createFileHeader(wb, "LNG Terminal Details",is);
		int currentRow=6;
		Row row=lngSheet.createRow(currentRow);
		Cell terminalName=row.createCell(1);
		terminalName.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		terminalName.setCellValue(EXCEL_TERMINAL);		
		Cell terminalNameValue=row.createCell(2);
		terminalNameValue.setCellStyle(ExcelFileHelper.getFieldValueCellStyle(wb));
		terminalNameValue.setCellValue((String)terminalData.get(TERMINALNAME));
		currentRow++;
		
		currentRow=createGeneralInformationSection(terminalData,wb,currentRow);
		currentRow=createInfrastructureSection(terminalData, wb,currentRow); //still pending
		currentRow=createTechnologyAndInvestmentInfoSection(terminalData,wb,currentRow);
		currentRow=createCompanyInformationSection(terminalData, wb, currentRow);
//		createMarineInformationSection(terminalData,wb,28);
		currentRow=createCapacityForecastsSection(terminalData, wb,currentRow);
		currentRow=ExcelFileHelper.createCopyRigthsSection(wb,currentRow);
		lngSheet.autoSizeColumn(1);
////		lngSheet.autoSizeColumn(2);
		lngSheet.setColumnWidth(2, 30*256);
		
		return wb;
	}
	
	private int createCapacityForecastsSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet lngSheet=wb.getSheetAt(0);
		Row capacityForeCastsSecRow=lngSheet.createRow(rowStart);
		Cell capacityForeCastsSecCell=capacityForeCastsSecRow.createCell(1);
		capacityForeCastsSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		capacityForeCastsSecCell.setCellValue(CAPACITY_FORECASTS);
		rowStart++;
		
		Row nameFieldRow=lngSheet.createRow(rowStart);
		Cell nameFieldCell=nameFieldRow.createCell(1);
		nameFieldCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		nameFieldCell.setCellValue(CAPACITY_FORECASTS_NAME);
		rowStart++;
		
		Map<Integer,Double> processingCapacity=(Map<Integer,Double>)terminalData.get(PROCESSINGCAPACITY);
		Row processingCapacityFieldRow=lngSheet.createRow(rowStart);
		Cell processingCapacityFieldCell=processingCapacityFieldRow.createCell(1);
		processingCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		processingCapacityFieldCell.setCellValue("Capacity");
		rowStart++;
		
		Map<Integer,Double> trainsOrVaporizers=(Map<Integer,Double>)terminalData.get(TRAINSORVAPORIZERS);
		Row trainsOrVaporizersFieldRow=lngSheet.createRow(rowStart);
		Cell trainsOrVaporizersFieldCell=trainsOrVaporizersFieldRow.createCell(1);
		trainsOrVaporizersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		trainsOrVaporizersFieldCell.setCellValue("Number of LNG trains (#)");
		rowStart++;
		
		Map<Integer,Double> storageCapacity=(Map<Integer,Double>)terminalData.get(STORAGECAPACITY);
		Row storageCapacityFieldRow=lngSheet.createRow(rowStart);
		Cell storageCapacityFieldCell=storageCapacityFieldRow.createCell(1);
		storageCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		storageCapacityFieldCell.setCellValue("LNG Storage Capacity (Cubic Meters)");
		rowStart++;
		
		Map<Integer,Double> storageTanks=(Map<Integer,Double>)terminalData.get(STORAGETANKS);
		Row storageTanksFieldRow=lngSheet.createRow(rowStart);
		Cell storageTanksFieldCell=storageTanksFieldRow.createCell(1);
		storageTanksFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		storageTanksFieldCell.setCellValue("Number of LNG Storage Tanks (#)");
		rowStart++;
				
		int column=2;
		for(int i=2005;i<=2022;i++)
		{
			Cell nameFieldValCell=nameFieldRow.createCell(column);
			nameFieldValCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
			nameFieldValCell.setCellValue(i);
			
			Cell processingCapacityFieldValCell=processingCapacityFieldRow.createCell(column);
			if(null!=processingCapacity.get(i) && 0!=processingCapacity.get(i))
				processingCapacityFieldValCell.setCellValue((Double)processingCapacity.get(i));
			else
				processingCapacityFieldValCell.setCellValue(BLANK);	
			
			Cell trainsOrVaporizersFieldValCell=trainsOrVaporizersFieldRow.createCell(column);
			if(null!=trainsOrVaporizers.get(i) && 0!=trainsOrVaporizers.get(i))
				trainsOrVaporizersFieldValCell.setCellValue((Double)trainsOrVaporizers.get(i));
			else
				trainsOrVaporizersFieldValCell.setCellValue(BLANK);
			
			Cell storageCapacityFieldValCell=storageCapacityFieldRow.createCell(column);
			if(null!=storageCapacity.get(i) && 0!=storageCapacity.get(i))
				storageCapacityFieldValCell.setCellValue((Double)storageCapacity.get(i));
			else
				storageCapacityFieldValCell.setCellValue(BLANK);
			
			Cell storageTanksFieldValCell=storageTanksFieldRow.createCell(column);
			if(null!=storageTanks.get(i) && 0!=storageTanks.get(i))
				storageTanksFieldValCell.setCellValue((Double)storageTanks.get(i));
			else
				storageTanksFieldValCell.setCellValue(BLANK);
			
			column++;
		}
		return rowStart;		
	}
//	private void createMarineInformationSection(Map terminalData,Workbook wb,int rowStart)
//	{
//		Sheet lngSheet=wb.getSheetAt(0);
//		Row marineInfoSecRow=lngSheet.createRow(rowStart);
//		Cell marineInfoSecCell=marineInfoSecRow.createCell(1);
//		marineInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
//		marineInfoSecCell.setCellValue("Marine Information");
//		rowStart++;
//		
//		Row lngCarriersFieldRow=lngSheet.createRow(rowStart);
//		Cell lngCarriersFieldCell=lngCarriersFieldRow.createCell(1);
//		lngCarriersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
//		lngCarriersFieldCell.setCellValue("LNG Carriers");
//		
//		Cell lngCarriersFieldValCell=lngCarriersFieldRow.createCell(2);
//		lngCarriersFieldValCell.setCellValue((String)terminalData.get(BLANK));
//		rowStart++;
//	}
	private int  createCompanyInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet lngSheet=wb.getSheetAt(0);
		Row compInfoSecRow=lngSheet.createRow(rowStart);
		Cell compInfoSecCell=compInfoSecRow.createCell(1);
		compInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		compInfoSecCell.setCellValue(COMPANY_INFO);
		rowStart++;
		
		Row operatorFieldRow=lngSheet.createRow(rowStart);
		Cell operatorFieldCell=operatorFieldRow.createCell(1);
		operatorFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		operatorFieldCell.setCellValue(COMPANY_INFO_OPERATOR);
		
		Cell operatorFieldValCell=operatorFieldRow.createCell(2);
		operatorFieldValCell.setCellValue(!((String)terminalData.get(OPERATOR)).equals(NA)?(String)terminalData.get(OPERATOR):BLANK);
		rowStart++;
		
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		Row equityHoldersFieldRow=lngSheet.createRow(rowStart);
		Cell equityHoldersFieldCell=equityHoldersFieldRow.createCell(1);
		equityHoldersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		equityHoldersFieldCell.setCellValue(COMPANY_INFO_EQUITYHOLDERS);
		rowStart++;
		
		Row stakeFieldRow=lngSheet.createRow(rowStart);
		Cell stakeFieldCell=stakeFieldRow.createCell(1);
		stakeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		stakeFieldCell.setCellValue(COMPANY_INFO_STAKE);
		rowStart++;
		int column=2;
		for(Map<String,String> ownership:ownerShipList)
		{			
			
			Cell equityHoldersFieldValCell=equityHoldersFieldRow.createCell(column);
			if(null!=ownership.get(EQUITYPARTNER))
				equityHoldersFieldValCell.setCellValue(ownership.get(EQUITYPARTNER));
													
			Cell stakeFieldValCell=stakeFieldRow.createCell(column);
			if(null!=ownership.get(EQUITYSTAKE))
				stakeFieldValCell.setCellValue(ownership.get(EQUITYSTAKE));
			
			column++;
		}
		return rowStart;
		
	}
	private int createTechnologyAndInvestmentInfoSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet lngSheet=wb.getSheetAt(0);
		Row techAndInvestInfoSecRow=lngSheet.createRow(rowStart);
		Cell techAndInvestInfoSecCell=techAndInvestInfoSecRow.createCell(1);
		techAndInvestInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		techAndInvestInfoSecCell.setCellValue("Technology and Investment Information");
		rowStart++;
		
		Row technologyFieldRow=lngSheet.createRow(rowStart);
		Cell technologyFieldCell=technologyFieldRow.createCell(1);
		technologyFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		technologyFieldCell.setCellValue("Technology");
		
		Cell technologyFieldValCell=technologyFieldRow.createCell(2);
		technologyFieldValCell.setCellValue(!((String)terminalData.get(TECHNOLOGY)).equals(NA)?(String)terminalData.get(TECHNOLOGY):BLANK);		
		rowStart++;
		
		Row capexFieldRow=lngSheet.createRow(rowStart);
		Cell capexFieldCell=capexFieldRow.createCell(1);
		capexFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		capexFieldCell.setCellValue(INVESTMENT_INFO_CAPEX);
		
		Cell capexFieldValCell=capexFieldRow.createCell(2);
		if(null!=terminalData.get(CAPEX) && 0!=(Double)terminalData.get(CAPEX))
			capexFieldValCell.setCellValue((Double)terminalData.get(CAPEX));
		else
			capexFieldValCell.setCellValue(BLANK);
		rowStart++;
		
		List<Map<String,String>> constructionDetailsList=(List<Map<String,String>>)terminalData.get(CONSTRUCTIONDETAILS);
		Row constructioncompanyNameFieldRow=lngSheet.createRow(rowStart);
		Cell constructioncompanyNameFieldCell=constructioncompanyNameFieldRow.createCell(1);
		constructioncompanyNameFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		constructioncompanyNameFieldCell.setCellValue("Construction Company Name");
		rowStart++;
		
		Row constructionContractFieldRow=lngSheet.createRow(rowStart);
		Cell constructionContractFieldCell=constructionContractFieldRow.createCell(1);
		constructionContractFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		constructionContractFieldCell.setCellValue("Construction Contracts Details");
		rowStart++;
		int column=2;
		for(Map<String,String> construction:constructionDetailsList)
		{			
			
			Cell constructionCompanyFieldValCell=constructioncompanyNameFieldRow.createCell(column);
			if(null!=construction.get(CONSTRUCTIONCOMPANYNAME) && BLANK!=construction.get(CONSTRUCTIONCOMPANYNAME))
				constructionCompanyFieldValCell.setCellValue(construction.get(CONSTRUCTIONCOMPANYNAME));
			else
				constructionCompanyFieldValCell.setCellValue(BLANK);
										
			Cell constructionContractValCell=constructionContractFieldRow.createCell(column);
			if(null!=construction.get(CONSTRUCTIONCONTRACTDETAILS) && BLANK!=construction.get(CONSTRUCTIONCONTRACTDETAILS))
				constructionContractValCell.setCellValue(construction.get(CONSTRUCTIONCONTRACTDETAILS));
			else
				constructionContractValCell.setCellValue(BLANK);
			column++;
		}
		return rowStart;
	}
	private int createInfrastructureSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet lngSheet=wb.getSheetAt(0);
		Row infraSecRow=lngSheet.createRow(rowStart);
		Cell infraSecCell=infraSecRow.createCell(1);
		infraSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		infraSecCell.setCellValue("Infrastructure");
		rowStart++;
		
		Row capacityFieldRow=lngSheet.createRow(rowStart);
		Cell capacityFieldCell=capacityFieldRow.createCell(1);
		capacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		capacityFieldCell.setCellValue(CAPACITY);
				
		Cell capacityFieldValCell=capacityFieldRow.createCell(2);
		if(null!=terminalData.get(CAPACITY) && 0!=(Double)terminalData.get(CAPACITY))
			capacityFieldValCell.setCellValue((Double)terminalData.get(CAPACITY));
		else
			capacityFieldValCell.setCellValue(BLANK);
		rowStart++;
		return rowStart;
	}
	private int createGeneralInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet lngSheet=wb.getSheetAt(0);
		Row generalInfoSecRow=lngSheet.createRow(rowStart);
		Cell generalInfoSecCell=generalInfoSecRow.createCell(1);
		generalInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		generalInfoSecCell.setCellValue(GENERAL_INFO);
		rowStart++;
		
		Row regionFieldRow=lngSheet.createRow(rowStart);
		Cell regionFieldCell=regionFieldRow.createCell(1);
		regionFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		regionFieldCell.setCellValue(GENERAL_INFO_REGION);
		
		Cell regionFieldValCell=regionFieldRow.createCell(2);
		regionFieldValCell.setCellValue(!((String)terminalData.get(REGION)).equals(NA)?(String)terminalData.get(REGION):BLANK);
		rowStart++;
		
		Row countryFieldRow=lngSheet.createRow(rowStart);
		Cell countryFieldCell=countryFieldRow.createCell(1);
		countryFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		countryFieldCell.setCellValue(GENERAL_INFO_COUNTRY);
		
		Cell countryFieldValCell=countryFieldRow.createCell(2);
		countryFieldValCell.setCellValue(!((String)terminalData.get(COUNTRY)).equals(NA)?(String)terminalData.get(COUNTRY):BLANK);
		rowStart++;
		
		Row locationFieldRow=lngSheet.createRow(rowStart);
		Cell locationFieldCell=locationFieldRow.createCell(1);
		locationFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		locationFieldCell.setCellValue(GENERAL_INFO_LOCATION);
		
		Cell locationFieldValCell=locationFieldRow.createCell(2);
		locationFieldValCell.setCellValue(!((String)terminalData.get(LOCATION)).equals(NA)?(String)terminalData.get(LOCATION):BLANK);
		rowStart++;
		
		Row typeFieldRow=lngSheet.createRow(rowStart);
		Cell typeFieldCell=typeFieldRow.createCell(1);
		typeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		typeFieldCell.setCellValue(GENERAL_INFO_TYPE);
		
		Cell typeFieldValCell=typeFieldRow.createCell(2);
		typeFieldValCell.setCellValue(!((String)terminalData.get(TYPE)).equals(NA)?(String)terminalData.get(TYPE):BLANK);
		rowStart++;
		
		Row offShoreOnShoreFieldRow=lngSheet.createRow(rowStart);
		Cell offShoreOnShoreFieldCell=offShoreOnShoreFieldRow.createCell(1);
		offShoreOnShoreFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		offShoreOnShoreFieldCell.setCellValue("OffShore/OnShore");
		
		Cell offShoreOnShoreValCell=offShoreOnShoreFieldRow.createCell(2);
		offShoreOnShoreValCell.setCellValue(!((String)terminalData.get(ONSHORE_OR_OFFSHORE)).equals(NA)?(String)terminalData.get(ONSHORE_OR_OFFSHORE):BLANK);
		rowStart++;
		
		Row statusFieldRow=lngSheet.createRow(rowStart);
		Cell statusFieldCell=statusFieldRow.createCell(1);
		statusFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		statusFieldCell.setCellValue(GENERAL_INFO_STATUS);
		
		Cell statusValCell=statusFieldRow.createCell(2);
		statusValCell.setCellValue(!((String)terminalData.get(STATUS)).equals(NA)?(String)terminalData.get(STATUS):BLANK);
		rowStart++;
		
		Row otherStatusDetailsFieldRow=lngSheet.createRow(rowStart);
		Cell otherStatusDetailsFieldCell=otherStatusDetailsFieldRow.createCell(1);
		otherStatusDetailsFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		otherStatusDetailsFieldCell.setCellValue(GENERAL_INFO_RECENT_DEV);
		
		Cell otherStatusDetailsValCell=otherStatusDetailsFieldRow.createCell(2);
		otherStatusDetailsValCell.setCellStyle(ExcelFileHelper.getFieldValueWrapCellStyle(wb));
		otherStatusDetailsValCell.setCellValue(!((String)terminalData.get(OTHER_DETAILS)).equals(NA)?(String)terminalData.get(OTHER_DETAILS):NA);
		rowStart++;
		
		Row expectedStartUpFieldRow=lngSheet.createRow(rowStart);
		Cell expectedStartUpFieldCell=expectedStartUpFieldRow.createCell(1);
		expectedStartUpFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		expectedStartUpFieldCell.setCellValue(GENERAL_INFO_STARTUP);
		
		Cell expectedStartUpValCell=expectedStartUpFieldRow.createCell(2);
		expectedStartUpValCell.setCellValue(!((String)terminalData.get(EXPECTEDSTARTUP)).equals(NA)?(String)terminalData.get(EXPECTEDSTARTUP):NA);	
		rowStart++;
		
		return rowStart;
	}
	
	
}
