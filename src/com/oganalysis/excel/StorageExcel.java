package com.oganalysis.excel;

import static com.oganalysis.constants.ApplicationConstants.BLANK;
import static com.oganalysis.constants.ApplicationConstants.CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.CAPACITY_FORECASTS;
import static com.oganalysis.constants.ApplicationConstants.CAPACITY_FORECASTS_NAME;
import static com.oganalysis.constants.ApplicationConstants.CAPEX;
import static com.oganalysis.constants.ApplicationConstants.COMPANY_INFO;
import static com.oganalysis.constants.ApplicationConstants.COMPANY_INFO_EQUITYHOLDERS;
import static com.oganalysis.constants.ApplicationConstants.COMPANY_INFO_OPERATOR;
import static com.oganalysis.constants.ApplicationConstants.COMPANY_INFO_STAKE;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.CURRENTOWNERS;
import static com.oganalysis.constants.ApplicationConstants.CURRENTOWNERSHIP;
import static com.oganalysis.constants.ApplicationConstants.EXCEL_STORAGE;
import static com.oganalysis.constants.ApplicationConstants.EXCEL_TERMINAL;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_LOCATION;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_REGION;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_STARTUP;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_STATUS;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_TANKS;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_TANKS_RANGE_MAX;
import static com.oganalysis.constants.ApplicationConstants.GENERAL_INFO_TANKS_RANGE_MIN;
import static com.oganalysis.constants.ApplicationConstants.INVESTMENT_INFO;
import static com.oganalysis.constants.ApplicationConstants.INVESTMENT_INFO_CAPEX;
import static com.oganalysis.constants.ApplicationConstants.LOCATION;
import static com.oganalysis.constants.ApplicationConstants.OPERATOR;
import static com.oganalysis.constants.ApplicationConstants.OWNERSHIP;
import static com.oganalysis.constants.ApplicationConstants.REGION;
import static com.oganalysis.constants.ApplicationConstants.STARTUP;
import static com.oganalysis.constants.ApplicationConstants.STATUS;
import static com.oganalysis.constants.ApplicationConstants.TANKS;
import static com.oganalysis.constants.ApplicationConstants.TANKSIZERANGE_MAX;
import static com.oganalysis.constants.ApplicationConstants.TANKSIZERANGE_MIN;
import static com.oganalysis.constants.ApplicationConstants.TERMINALNAME;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StorageExcel {
	public Workbook getExcelTerminalData(Map terminalData,InputStream is)
	{
		Workbook wb=new XSSFWorkbook();
		Sheet refinerySheet=wb.createSheet(EXCEL_STORAGE+(String)terminalData.get(TERMINALNAME));
		ExcelFileHelper.createFileHeader(wb, "Storage Terminal Details",is);
		int currentRow=6;
		Row row=refinerySheet.createRow(currentRow);
		Cell terminalName=row.createCell(1);
		terminalName.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		terminalName.setCellValue(EXCEL_TERMINAL);		
		Cell terminalNameValue=row.createCell(2);
		terminalNameValue.setCellStyle(ExcelFileHelper.getFieldValueCellStyle(wb));
		terminalNameValue.setCellValue((String)terminalData.get(TERMINALNAME));
		currentRow++;		
		
		
		currentRow=createGeneralInformationSection(terminalData,wb,currentRow);
		currentRow=createCompanyInformationSection(terminalData, wb,currentRow);
		currentRow=createInvestmentInfoSection(terminalData,wb,currentRow); //still pending
		currentRow=createCapaciityForecastsSection(terminalData, wb,currentRow);

		refinerySheet.autoSizeColumn(1);
//		lngSheet.autoSizeColumn(2);
		refinerySheet.setColumnWidth(2, 30*256);
		
		return wb;
	}
	private int createCapaciityForecastsSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet refineriesSheet=wb.getSheetAt(0);
		Row capacityForeCastsSecRow=refineriesSheet.createRow(rowStart);
		Cell capacityForeCastsSecCell=capacityForeCastsSecRow.createCell(1);
		capacityForeCastsSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		capacityForeCastsSecCell.setCellValue(CAPACITY_FORECASTS);
		rowStart++;
		
		Row nameFieldRow=refineriesSheet.createRow(rowStart);
		Cell nameFieldCell=nameFieldRow.createCell(1);
		nameFieldCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		nameFieldCell.setCellValue(CAPACITY_FORECASTS_NAME);
		rowStart++;
		
		Map<Integer,Double> storageCapacity=(Map<Integer,Double>)terminalData.get(CAPACITY);
		Row storageCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell storageCapacityFieldCell=storageCapacityFieldRow.createCell(1);
		storageCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		storageCapacityFieldCell.setCellValue("Storage Capacity (m3)");
		rowStart++;
		int column=2;
		for(int i=2005;i<=2022;i++)
		{
			Cell nameFieldValCell=nameFieldRow.createCell(column);
			nameFieldValCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
			nameFieldValCell.setCellValue(i);
			
			Cell storageCapacityFieldValCell=storageCapacityFieldRow.createCell(column);
			if(null!=storageCapacity.get(i) && 0!=(Double)storageCapacity.get(i))
				storageCapacityFieldValCell.setCellValue((Double)storageCapacity.get(i));
			else
				storageCapacityFieldValCell.setCellValue(BLANK);										
			column++;
		}
		return rowStart;
	}	
	private int createInvestmentInfoSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet storageSheet=wb.getSheetAt(0);
		Row investInfoSecRow=storageSheet.createRow(rowStart);
		Cell investInfoSecCell=investInfoSecRow.createCell(1);
		investInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		investInfoSecCell.setCellValue(INVESTMENT_INFO);
		rowStart++;
			
		Row capexFieldRow=storageSheet.createRow(rowStart);
		Cell capexFieldCell=capexFieldRow.createCell(1);
		capexFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		capexFieldCell.setCellValue(INVESTMENT_INFO_CAPEX);
		
		Cell capexFieldValCell=capexFieldRow.createCell(2);
		if(null!=terminalData.get(CAPEX) && 0!=(Double)terminalData.get(CAPEX))
			capexFieldValCell.setCellValue((Double)terminalData.get(CAPEX));	
		else
			capexFieldValCell.setCellValue(BLANK);
		rowStart++;
		return rowStart;
	}
	private int createCompanyInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet storageSheet=wb.getSheetAt(0);
		Row compInfoSecRow=storageSheet.createRow(rowStart);
		Cell compInfoSecCell=compInfoSecRow.createCell(1);
		compInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		compInfoSecCell.setCellValue(COMPANY_INFO);
		rowStart++;
			
		Row operatorFieldRow=storageSheet.createRow(rowStart);
		Cell operatorFieldCell=operatorFieldRow.createCell(1);
		operatorFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		operatorFieldCell.setCellValue(COMPANY_INFO_OPERATOR);
		
		Cell operatorFieldValCell=operatorFieldRow.createCell(2);
		operatorFieldValCell.setCellValue((String)terminalData.get(OPERATOR));		
		rowStart++;
		
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		Row equityHoldersFieldRow=storageSheet.createRow(rowStart);
		Cell equityHoldersFieldCell=equityHoldersFieldRow.createCell(1);
		equityHoldersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		equityHoldersFieldCell.setCellValue(COMPANY_INFO_EQUITYHOLDERS);
		rowStart++;
		Row stakeFieldRow=storageSheet.createRow(rowStart);
		Cell stakeFieldCell=stakeFieldRow.createCell(1);
		stakeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		stakeFieldCell.setCellValue(COMPANY_INFO_STAKE);
		rowStart++;
		int column=2;
		for(Map<String,String> ownership:ownerShipList)
		{			
			
			Cell equityHoldersFieldValCell=equityHoldersFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTOWNERS))
				equityHoldersFieldValCell.setCellValue(ownership.get(CURRENTOWNERS));
													
			Cell stakeFieldValCell=stakeFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTOWNERSHIP))
				stakeFieldValCell.setCellValue(ownership.get(CURRENTOWNERSHIP));
			column++;
		}
		return rowStart;
	}
	private int createGeneralInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		rowStart++;
		Sheet storageSheet=wb.getSheetAt(0);
		Row generalInfoSecRow=storageSheet.createRow(rowStart);
		Cell generalInfoSecCell=generalInfoSecRow.createCell(1);
		generalInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		generalInfoSecCell.setCellValue(GENERAL_INFO);
		rowStart++;
			
		Row regionFieldRow=storageSheet.createRow(rowStart);
		Cell regionFieldCell=regionFieldRow.createCell(1);
		regionFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		regionFieldCell.setCellValue(GENERAL_INFO_REGION);
		
		Cell regionFieldValCell=regionFieldRow.createCell(2);
		regionFieldValCell.setCellValue((String)terminalData.get(REGION));
		rowStart++;
		
		Row countryFieldRow=storageSheet.createRow(rowStart);
		Cell countryFieldCell=countryFieldRow.createCell(1);
		countryFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		countryFieldCell.setCellValue(GENERAL_INFO_COUNTRY);
		
		Cell countryFieldValCell=countryFieldRow.createCell(2);
		countryFieldValCell.setCellValue((String)terminalData.get(COUNTRY));
		rowStart++;
		
		Row locationFieldRow=storageSheet.createRow(rowStart);
		Cell locationFieldCell=locationFieldRow.createCell(1);
		locationFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		locationFieldCell.setCellValue(GENERAL_INFO_LOCATION);
		
		Cell locationFieldValCell=locationFieldRow.createCell(2);
		locationFieldValCell.setCellValue((String)terminalData.get(LOCATION));
		rowStart++;
		
		Row statusFieldRow=storageSheet.createRow(rowStart);
		Cell statusFieldCell=statusFieldRow.createCell(1);
		statusFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		statusFieldCell.setCellValue(GENERAL_INFO_STATUS);
		
		Cell statusFieldValCell=statusFieldRow.createCell(2);
		statusFieldValCell.setCellValue((String)terminalData.get(STATUS));
		rowStart++;
		
		Row commencementFieldRow=storageSheet.createRow(rowStart);
		Cell commencementFieldCell=commencementFieldRow.createCell(1);
		commencementFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		commencementFieldCell.setCellValue(GENERAL_INFO_STARTUP);
		
		Cell commencementFieldValCell=commencementFieldRow.createCell(2);
		commencementFieldValCell.setCellValue((String)terminalData.get(STARTUP));
		rowStart++;
		
		Row tanksFieldRow=storageSheet.createRow(rowStart);
		Cell tanksFieldCell=tanksFieldRow.createCell(1);
		tanksFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksFieldCell.setCellValue(GENERAL_INFO_TANKS);
		
		Cell tanksFieldValCell=tanksFieldRow.createCell(2);
		if(null!=terminalData.get(TANKS) && 0!=(Double)terminalData.get(TANKS))
			tanksFieldValCell.setCellValue((Double)terminalData.get(TANKS));
		else
			tanksFieldValCell.setCellValue(BLANK);
		rowStart++;
		
		Row tanksSizeRangeMinFieldRow=storageSheet.createRow(rowStart);
		Cell tanksSizeRangeMinFieldCell=tanksSizeRangeMinFieldRow.createCell(1);
		tanksSizeRangeMinFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksSizeRangeMinFieldCell.setCellValue(GENERAL_INFO_TANKS_RANGE_MIN);
		
		Cell tanksSizeRangeMinFieldValCell=tanksSizeRangeMinFieldRow.createCell(2);
		if(null!=terminalData.get(TANKSIZERANGE_MIN) && 0!=(Double)terminalData.get(TANKSIZERANGE_MIN))
			tanksSizeRangeMinFieldValCell.setCellValue((Double)terminalData.get(TANKSIZERANGE_MIN));
		else
			tanksSizeRangeMinFieldValCell.setCellValue(BLANK);
		rowStart++;
		
		Row tanksSizeRangeMaxFieldRow=storageSheet.createRow(rowStart);
		Cell tanksSizeRangeMaxFieldCell=tanksSizeRangeMaxFieldRow.createCell(1);
		tanksSizeRangeMaxFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksSizeRangeMaxFieldCell.setCellValue(GENERAL_INFO_TANKS_RANGE_MAX);
		
		Cell tanksSizeRangeMaxFieldValCell=tanksSizeRangeMaxFieldRow.createCell(2);
		if(null!=terminalData.get(TANKSIZERANGE_MAX) && 0!=(Double)terminalData.get(TANKSIZERANGE_MAX))
			tanksSizeRangeMaxFieldValCell.setCellValue((Double)terminalData.get(TANKSIZERANGE_MAX));
		else
			tanksSizeRangeMinFieldValCell.setCellValue(BLANK);
		rowStart++;
		
		return rowStart;
	}
}
