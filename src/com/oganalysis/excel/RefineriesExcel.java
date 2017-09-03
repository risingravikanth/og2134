package com.oganalysis.excel;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RefineriesExcel {
	public Workbook getExcelTerminalData(Map terminalData)
	{
		Workbook wb=new XSSFWorkbook();
		Sheet refinerySheet=wb.createSheet(EXCEL_REFINERY+(String)terminalData.get(TERMINALNAME));
		
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
		createCapaciityForecastsSection(terminalData, wb, 23);

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
		
		Map<Integer,Double> cduCapacity=(Map<Integer,Double>)terminalData.get(CDUCAPACITY);
		Row cduCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell cduCapacityFieldCell=cduCapacityFieldRow.createCell(1);
		cduCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		cduCapacityFieldCell.setCellValue("CDU Capacity (Kb/d)");
		rowStart++;
		
		Map<Integer,Double> vduCapacity=(Map<Integer,Double>)terminalData.get(VDUCAPACITY);
		Row vduCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell vduCapacityFieldCell=vduCapacityFieldRow.createCell(1);
		vduCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		vduCapacityFieldCell.setCellValue("VDU Capacity (Kb/d)");
		rowStart++;
		
		Map<Integer,Double> cokingCapacity=(Map<Integer,Double>)terminalData.get(COKINGCAPACITY);
		Row cokingCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell cokingCapacityFieldCell=cokingCapacityFieldRow.createCell(1);
		cokingCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		cokingCapacityFieldCell.setCellValue("Coking Capacity (Kb/d)");
		rowStart++;
		
		Map<Integer,Double> fccCapacity=(Map<Integer,Double>)terminalData.get(FCCCAPACITY);
		Row fccCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell fccCapacityFieldCell=fccCapacityFieldRow.createCell(1);
		fccCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		fccCapacityFieldCell.setCellValue("FCC (Kb/d)");
		rowStart++;
		
		Map<Integer,Double> hydroCrackingCapacity=(Map<Integer,Double>)terminalData.get(HYDROCRACKINGCAPACITY);
		Row hydroCrackingCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell hydroCrackingCapacityFieldCell=hydroCrackingCapacityFieldRow.createCell(1);
		hydroCrackingCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		hydroCrackingCapacityFieldCell.setCellValue("HydroCracking Capacity(Kb/d)");
						
		int column=2;
		for(int i=2005;i<=2022;i++)
		{
			Cell nameFieldValCell=nameFieldRow.createCell(column);
			nameFieldValCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
			nameFieldValCell.setCellValue(i);
			
			Cell cduCapacityFieldValCell=cduCapacityFieldRow.createCell(column);
			if(null!=cduCapacity.get(i))
				cduCapacityFieldValCell.setCellValue((Double)cduCapacity.get(i));
			else
				cduCapacityFieldValCell.setCellValue(0);	
			
			Cell vduCapacityFieldValCell=vduCapacityFieldRow.createCell(column);
			if(null!=vduCapacity.get(i))
				vduCapacityFieldValCell.setCellValue((Double)vduCapacity.get(i));
			else
				vduCapacityFieldValCell.setCellValue(0);
			
			Cell cokingFieldValCell=cokingCapacityFieldRow.createCell(column);
			if(null!=cokingCapacity.get(i))
				cokingFieldValCell.setCellValue((Double)cokingCapacity.get(i));
			else
				cokingFieldValCell.setCellValue(0);
			
			Cell fccFieldValCell=fccCapacityFieldRow.createCell(column);
			if(null!=fccCapacity.get(i))
				fccFieldValCell.setCellValue((Double)fccCapacity.get(i));
			else
				fccFieldValCell.setCellValue(0);
			
			Cell hydroCrackingFieldValCell=hydroCrackingCapacityFieldRow.createCell(column);
			if(null!=hydroCrackingCapacity.get(i))
				hydroCrackingFieldValCell.setCellValue((Double)hydroCrackingCapacity.get(i));
			else
				hydroCrackingFieldValCell.setCellValue(0);
			
			column++;
		}
				
	}
	private void createInvestmentInfoSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet refineriesSheet=wb.getSheetAt(0);
		Row investInfoSecRow=refineriesSheet.createRow(rowStart);
		Cell investInfoSecCell=investInfoSecRow.createCell(1);
		investInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		investInfoSecCell.setCellValue("Investment Information");
		rowStart++;
		
		Row capexFieldRow=refineriesSheet.createRow(rowStart);
		Cell capexFieldCell=capexFieldRow.createCell(1);
		capexFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		capexFieldCell.setCellValue("Capex($)");
		
		Cell capexFieldValCell=capexFieldRow.createCell(2);
		capexFieldValCell.setCellValue((String)terminalData.get(CAPEX));
		
	}
	private void createCompanyInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet refineriesSheet=wb.getSheetAt(0);
		Row compInfoSecRow=refineriesSheet.createRow(rowStart);
		Cell compInfoSecCell=compInfoSecRow.createCell(1);
		compInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		compInfoSecCell.setCellValue("Company Information");
		rowStart++;
		
		Row operatorFieldRow=refineriesSheet.createRow(rowStart);
		Cell operatorFieldCell=operatorFieldRow.createCell(1);
		operatorFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		operatorFieldCell.setCellValue("Operator");
		
		Cell operatorFieldValCell=operatorFieldRow.createCell(2);
		operatorFieldValCell.setCellValue((String)terminalData.get(OPERATOR));
		rowStart++;
		
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		Row equityHoldersFieldRow=refineriesSheet.createRow(rowStart);
		Cell equityHoldersFieldCell=equityHoldersFieldRow.createCell(1);
		equityHoldersFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		equityHoldersFieldCell.setCellValue("Equity Holders");
		rowStart++;
		Row stakeFieldRow=refineriesSheet.createRow(rowStart);
		Cell stakeFieldCell=stakeFieldRow.createCell(1);
		stakeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		stakeFieldCell.setCellValue("Stake(%)");
		
		int column=2;
		for(Map<String,String> ownership:ownerShipList)
		{			
			
			Cell equityHoldersFieldValCell=equityHoldersFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTEQUITYPARTNER))
				equityHoldersFieldValCell.setCellValue(ownership.get(CURRENTEQUITYPARTNER));
			else
				equityHoldersFieldValCell.setCellValue(BLANK);
										
			Cell stakeFieldValCell=stakeFieldRow.createCell(column);
			if(null!=ownership.get(CURRENTEQUITYSTAKE))
				stakeFieldValCell.setCellValue(ownership.get(CURRENTEQUITYSTAKE));
			else
				stakeFieldValCell.setCellValue(BLANK);
			column++;
		}
	}
	public void createGeneralInformationSection(Map terminalData,Workbook wb,int rowStart)
	{
		Sheet refineriesSheet=wb.getSheetAt(0);
		Row generalInfoSecRow=refineriesSheet.createRow(rowStart);
		Cell generalInfoSecCell=generalInfoSecRow.createCell(1);
		generalInfoSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		generalInfoSecCell.setCellValue("General Information");
		rowStart++;
			
		Row regionFieldRow=refineriesSheet.createRow(rowStart);
		Cell regionFieldCell=regionFieldRow.createCell(1);
		regionFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		regionFieldCell.setCellValue("Region");
		
		Cell regionFieldValCell=regionFieldRow.createCell(2);
		regionFieldValCell.setCellValue((String)terminalData.get(REGION));
		rowStart++;
		
		Row countryFieldRow=refineriesSheet.createRow(rowStart);
		Cell countryFieldCell=countryFieldRow.createCell(1);
		countryFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		countryFieldCell.setCellValue("Country");
		
		Cell countryFieldValCell=countryFieldRow.createCell(2);
		countryFieldValCell.setCellValue((String)terminalData.get(COUNTRY));
		rowStart++;
		
		Row locationFieldRow=refineriesSheet.createRow(rowStart);
		Cell locationFieldCell=locationFieldRow.createCell(1);
		locationFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		locationFieldCell.setCellValue("Location");
		
		Cell locationFieldValCell=locationFieldRow.createCell(2);
		locationFieldValCell.setCellValue((String)terminalData.get(LOCATION));
		rowStart++;
		
		Row typeFieldRow=refineriesSheet.createRow(rowStart);
		Cell typeFieldCell=typeFieldRow.createCell(1);
		typeFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		typeFieldCell.setCellValue("Type");
		
		Cell typeFieldValCell=typeFieldRow.createCell(2);
		typeFieldValCell.setCellValue((String)terminalData.get(TYPE));
		rowStart++;
		
		Row statusFieldRow=refineriesSheet.createRow(rowStart);
		Cell statusFieldCell=statusFieldRow.createCell(1);
		statusFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		statusFieldCell.setCellValue("Status");
		
		Cell statusFieldValCell=statusFieldRow.createCell(2);
		statusFieldValCell.setCellValue((String)terminalData.get(STATUS));
		rowStart++;
		
		Row refCapacityFieldRow=refineriesSheet.createRow(rowStart);
		Cell refCapacityFieldCell=refCapacityFieldRow.createCell(1);
		refCapacityFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		refCapacityFieldCell.setCellValue("Refining Capacity (Kb/d)");
		
		Cell refCapacityFieldValCell=refCapacityFieldRow.createCell(2);
		refCapacityFieldValCell.setCellValue((Double)terminalData.get(CAPACITY));
		rowStart++;
		
		Row recentDevFieldRow=refineriesSheet.createRow(rowStart);
		Cell recentDevFieldCell=recentDevFieldRow.createCell(1);
		recentDevFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		recentDevFieldCell.setCellValue("Recent Developments");
		
		Cell recentDevFieldValCell=recentDevFieldRow.createCell(2);
		recentDevFieldValCell.setCellValue((String)terminalData.get(STATUSDETAILS));
		rowStart++;
		
		Row startUpFieldRow=refineriesSheet.createRow(rowStart);
		Cell startUpFieldCell=startUpFieldRow.createCell(1);
		startUpFieldCell.setCellStyle(ExcelFileHelper.getFieldCellStyle(wb));
		startUpFieldCell.setCellValue("Start Up");
		
		Cell startUpFieldValCell=startUpFieldRow.createCell(2);
		startUpFieldValCell.setCellValue((String)terminalData.get(COMMENCEMENT));
		
	}
}
