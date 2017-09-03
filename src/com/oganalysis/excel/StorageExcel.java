package com.oganalysis.excel;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StorageExcel {
	public Workbook getExcelTerminalData(Map terminalData)
	{
		Workbook wb=new XSSFWorkbook();
		Sheet refinerySheet=wb.createSheet(EXCEL_STORAGE+(String)terminalData.get(TERMINALNAME));
		
		Row row=refinerySheet.createRow(0);
		Cell terminalName=row.createCell(1);
		terminalName.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		terminalName.setCellValue("Terminal");		
		Cell terminalNameValue=row.createCell(2);
		terminalNameValue.setCellStyle(ExcelFileHelper.getFieldValueCellStyle(wb));
		terminalNameValue.setCellValue((String)terminalData.get(TERMINALNAME));
		
		createGeneralInformationSection(terminalData,wb,2);
		createCompanyInformationSection(terminalData, wb, 12);
		createInvestmentInfoSection(terminalData,wb,17); //still pending
		createCapaciityForecastsSection(terminalData, wb, 22);

		refinerySheet.autoSizeColumn(1);
//		lngSheet.autoSizeColumn(2);
		refinerySheet.setColumnWidth(2, 30*256);
		
		return wb;
	}
	private void createCapaciityForecastsSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet refineriesSheet=wb.getSheetAt(0);
		Row capacityForeCastsSecRow=refineriesSheet.createRow(rowStart);
		Cell capacityForeCastsSecCell=capacityForeCastsSecRow.createCell(1);
		capacityForeCastsSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		capacityForeCastsSecCell.setCellValue("Capacity Forecasts");
		rowStart++;
		
		Row nameFieldRow=refineriesSheet.createRow(rowStart);
		Cell nameFieldCell=nameFieldRow.createCell(1);
		nameFieldCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		nameFieldCell.setCellValue("Name");
		rowStart++;
		
		Map<Integer,Double> storageCapacity=(Map<Integer,Double>)terminalData.get(CAPACITY);
		Row storageCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell storageCapacityFieldCell=storageCapacityFieldRow.createCell(1);
		storageCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		storageCapacityFieldCell.setCellValue("Storage Capacity (m3)");
		
		int column=2;
		for(int i=2005;i<=2022;i++)
		{
			Cell nameFieldValCell=nameFieldRow.createCell(column);
			nameFieldValCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
			nameFieldValCell.setCellValue(i);
			
			Cell storageCapacityFieldValCell=storageCapacityFieldRow.createCell(column);
			if(null!=storageCapacity.get(i))
				storageCapacityFieldValCell.setCellValue((Double)storageCapacity.get(i));
			else
				storageCapacityFieldValCell.setCellValue(0);										
			column++;
		}
	}	
	private void createInvestmentInfoSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet storageSheet=wb.getSheetAt(0);
		Row investInfoSecRow=storageSheet.createRow(rowStart);
		Cell investInfoSecCell=investInfoSecRow.createCell(1);
		investInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		investInfoSecCell.setCellValue("Investment Information");
		rowStart++;
			
		Row capexFieldRow=storageSheet.createRow(rowStart);
		Cell capexFieldCell=capexFieldRow.createCell(1);
		capexFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		capexFieldCell.setCellValue("Capex($)");
		
		Cell capexFieldValCell=capexFieldRow.createCell(2);
		capexFieldValCell.setCellValue((String)terminalData.get(CAPEX));		
		rowStart++;
	}
	private void createCompanyInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet storageSheet=wb.getSheetAt(0);
		Row compInfoSecRow=storageSheet.createRow(rowStart);
		Cell compInfoSecCell=compInfoSecRow.createCell(1);
		compInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		compInfoSecCell.setCellValue("Company Information");
		rowStart++;
			
		Row operatorFieldRow=storageSheet.createRow(rowStart);
		Cell operatorFieldCell=operatorFieldRow.createCell(1);
		operatorFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		operatorFieldCell.setCellValue("Operator");
		
		Cell operatorFieldValCell=operatorFieldRow.createCell(2);
		operatorFieldValCell.setCellValue((String)terminalData.get(OPERATOR));		
		rowStart++;
		
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		Row equityHoldersFieldRow=storageSheet.createRow(rowStart);
		Cell equityHoldersFieldCell=equityHoldersFieldRow.createCell(1);
		equityHoldersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		equityHoldersFieldCell.setCellValue("Equity Holders");
		rowStart++;
		Row stakeFieldRow=storageSheet.createRow(rowStart);
		Cell stakeFieldCell=stakeFieldRow.createCell(1);
		stakeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		stakeFieldCell.setCellValue("Stake(%)");
		rowStart++;
		int column=2;
		for(Map<String,String> ownership:ownerShipList)
		{			
			
			Cell equityHoldersFieldValCell=equityHoldersFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTOWNERS))
				equityHoldersFieldValCell.setCellValue(ownership.get(CURRENTOWNERS));
			else
				equityHoldersFieldValCell.setCellValue(BLANK);
										
			Cell stakeFieldValCell=stakeFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTOWNERSHIP))
				stakeFieldValCell.setCellValue(ownership.get(CURRENTOWNERSHIP));
			else
				stakeFieldValCell.setCellValue(BLANK);
			column++;
		}
	}
	private void createGeneralInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet storageSheet=wb.getSheetAt(0);
		Row generalInfoSecRow=storageSheet.createRow(rowStart);
		Cell generalInfoSecCell=generalInfoSecRow.createCell(1);
		generalInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		generalInfoSecCell.setCellValue("General Information");
		rowStart++;
			
		Row regionFieldRow=storageSheet.createRow(rowStart);
		Cell regionFieldCell=regionFieldRow.createCell(1);
		regionFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		regionFieldCell.setCellValue("Region");
		
		Cell regionFieldValCell=regionFieldRow.createCell(2);
		regionFieldValCell.setCellValue((String)terminalData.get(REGION));
		rowStart++;
		
		Row countryFieldRow=storageSheet.createRow(rowStart);
		Cell countryFieldCell=countryFieldRow.createCell(1);
		countryFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		countryFieldCell.setCellValue("Country");
		
		Cell countryFieldValCell=countryFieldRow.createCell(2);
		countryFieldValCell.setCellValue((String)terminalData.get(COUNTRY));
		rowStart++;
		
		Row locationFieldRow=storageSheet.createRow(rowStart);
		Cell locationFieldCell=locationFieldRow.createCell(1);
		locationFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		locationFieldCell.setCellValue("Location");
		
		Cell locationFieldValCell=locationFieldRow.createCell(2);
		locationFieldValCell.setCellValue((String)terminalData.get(LOCATION));
		rowStart++;
		
		Row statusFieldRow=storageSheet.createRow(rowStart);
		Cell statusFieldCell=statusFieldRow.createCell(1);
		statusFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		statusFieldCell.setCellValue("Status");
		
		Cell statusFieldValCell=statusFieldRow.createCell(2);
		statusFieldValCell.setCellValue((String)terminalData.get(STATUS));
		rowStart++;
		
		Row commencementFieldRow=storageSheet.createRow(rowStart);
		Cell commencementFieldCell=commencementFieldRow.createCell(1);
		commencementFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		commencementFieldCell.setCellValue("Start Up");
		
		Cell commencementFieldValCell=commencementFieldRow.createCell(2);
		commencementFieldValCell.setCellValue((String)terminalData.get(COMMENCEMENT));
		rowStart++;
		
		Row tanksFieldRow=storageSheet.createRow(rowStart);
		Cell tanksFieldCell=tanksFieldRow.createCell(1);
		tanksFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksFieldCell.setCellValue("Tanks(#)");
		
		Cell tanksFieldValCell=tanksFieldRow.createCell(2);
		tanksFieldValCell.setCellValue((Double)terminalData.get(TANKS));
		rowStart++;
		
		Row tanksSizeRangeMinFieldRow=storageSheet.createRow(rowStart);
		Cell tanksSizeRangeMinFieldCell=tanksSizeRangeMinFieldRow.createCell(1);
		tanksSizeRangeMinFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksSizeRangeMinFieldCell.setCellValue("Tank Size Range - Min (m3)");
		
		Cell tanksSizeRangeMinFieldValCell=tanksSizeRangeMinFieldRow.createCell(2);
		tanksSizeRangeMinFieldValCell.setCellValue((Double)terminalData.get(TANKSIZERANGE_MIN));
		rowStart++;
		
		Row tanksSizeRangeMaxFieldRow=storageSheet.createRow(rowStart);
		Cell tanksSizeRangeMaxFieldCell=tanksSizeRangeMaxFieldRow.createCell(1);
		tanksSizeRangeMaxFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		tanksSizeRangeMaxFieldCell.setCellValue("Tank Size Range - Max (m3)");
		
		Cell tanksSizeRangeMaxFieldValCell=tanksSizeRangeMaxFieldRow.createCell(2);
		tanksSizeRangeMaxFieldValCell.setCellValue((Double)terminalData.get(TANKSIZERANGE_MAX));		
	}
}
