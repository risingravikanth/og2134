package com.oganalysis.excel;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileHelper {
	
	public static CellStyle getHeaderCellStyle(Workbook wb)
	{
		Font font = wb.createFont();		   
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(IndexedColors.WHITE.getIndex());
		XSSFCellStyle cellStyle= ((XSSFWorkbook)wb).createCellStyle();        
		cellStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());//new XSSFColor(new java.awt.Color(190,112,59)));//IndexedColors.DARK_RED.getIndex());//SKY_BLUE.getIndex()
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); 
        cellStyle.setFont(font);
        return cellStyle;
	}
	public static CellStyle getFieldCellStyle(Workbook wb)
	{
		   Font font = wb.createFont();
//		   font.setColor(XSSFFont.COLOR_RED);
		   font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		   // Create the style
		   CellStyle cellStyle= wb.createCellStyle();
		   cellStyle.setFont(font);
		   return cellStyle;
	}
	public static CellStyle getFieldValueCellStyle(Workbook wb)
	{
		   // Create the style
		Font font = wb.createFont();		   
		font.setColor(IndexedColors.BROWN.getIndex());//SKY_BLUE.getIndex()
		CellStyle cellStyle= wb.createCellStyle();        
//        cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
//        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); 
        cellStyle.setFont(font);
        return cellStyle;
	}
	public static CellStyle getFieldValueWrapCellStyle(Workbook wb)
	{
		   // Create the style
		
		CellStyle cellStyle= wb.createCellStyle();        
		cellStyle.setWrapText(true);
        return cellStyle;
	}
	public static int createCopyRigthsSection(Workbook wb,int rowStart)
	{
		rowStart=rowStart+4;
		Sheet lngSheet=wb.getSheetAt(0);
		Row copyRightsRow=lngSheet.createRow(rowStart);
		Cell copyRightsCell=copyRightsRow.createCell(1);
//		capacityForeCastsSecCell.setCellStyle(ExcelFileHelper.getHeaderCellStyle(wb));
		copyRightsCell.setCellValue("Copyright! All rights reserved");
		rowStart++;
		
		return rowStart;
	}
	public static void createFileHeader(Workbook wb,String fileName,InputStream is)
	{
		Row row=wb.getSheetAt(0).createRow(0);
		Cell headerCell=row.createCell(0);
		headerCell.setCellValue(fileName);	
		insertLog(wb,is);
		CellRangeAddress cellRangeAddress=new CellRangeAddress(0,(short)3, 0, (short)12);		
		wb.getSheetAt(0).addMergedRegion(cellRangeAddress);
		RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, cellRangeAddress, wb.getSheetAt(0),wb);
		RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, cellRangeAddress, wb.getSheetAt(0),wb);
		
		Font headerFont=wb.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.BROWN.getIndex());
		headerFont.setFontHeight((short)(17.5*20));
		
			
		XSSFCellStyle cellStyle=((XSSFWorkbook)wb).createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);		
        cellStyle.setFont(headerFont);        
        headerCell.setCellStyle(cellStyle);
        
        
	}
	private static void insertLog(Workbook wb,InputStream is)
	{
		try
		{
			byte[] bytes = IOUtils.toByteArray(is);
			   //Adds a picture to the workbook
			   int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			   //close the input stream
			   is.close();
			   //Returns an object that handles instantiating concrete classes
			   CreationHelper helper = wb.getCreationHelper();
			   //Creates the top-level drawing patriarch.
			   Drawing drawing = wb.getSheetAt(0).createDrawingPatriarch();

			   //Create an anchor that is attached to the worksheet
			   ClientAnchor anchor = helper.createClientAnchor();

			   //create an anchor with upper left cell _and_ bottom right cell
//			   anchor.setCol1(1); //Column B
//			   anchor.setRow1(2); //Row 3
//			   anchor.setCol2(2); //Column C
//			   anchor.setRow2(3); //Row 4
			   
			   anchor.setCol1(1); //Column B
			   anchor.setRow1(1); //Row 3
			   anchor.setCol2(2); //Column C
			   anchor.setRow2(3);
			   
			   //Creates a picture
			   Picture pict = drawing.createPicture(anchor, pictureIdx);

			   //Reset the image to the original size
			   //pict.resize(); //don't do that. Let the anchor resize the image!

			   //Create the Cell B3
			   Cell cell = wb.getSheetAt(0).createRow(2).createCell(1);
		}
		catch(Exception e)
		{
			throw new RuntimeException();
		}
		  
	}
}
