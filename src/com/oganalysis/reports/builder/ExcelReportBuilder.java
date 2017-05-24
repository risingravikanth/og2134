package com.oganalysis.reports.builder;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.springframework.web.servlet.view.document.AbstractExcelView;
//
//import com.oganalysis.entities.Exploration;
//
public class ExcelReportBuilder{// extends AbstractExcelView{
//
////	@Override
////	protected void buildExcelDocument(Map<String, Object> model,
////			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
////			throws Exception {
////		// TODO Auto-generated method stub
////		// get data model which is passed by the Spring container
////		response.setHeader("Content-Disposition","attachment;filename="+"oganalysisReport.xlsx");
////        List<Exploration> explorationList = (List<Exploration>) model.get("dataList");
////         
////        // create a new Excel sheet
////        HSSFSheet sheet = workbook.createSheet("Java Books");
////        sheet.setDefaultColumnWidth(30);
////         
////        // create style for header cells
////        CellStyle style = workbook.createCellStyle();
////        Font font = workbook.createFont();
////        font.setFontName("Arial");
////        style.setFillForegroundColor(HSSFColor.BLUE.index);
////        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
////        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
////        font.setColor(HSSFColor.WHITE.index);
////        style.setFont(font);
////         
////        // create header row
////        HSSFRow header = sheet.createRow(0);
////         
////        header.createCell(0).setCellValue("Name");
////        header.getCell(0).setCellStyle(style);
////         
////        header.createCell(1).setCellValue("Country");
////        header.getCell(1).setCellStyle(style);
////         
////        header.createCell(2).setCellValue("Region");
////        header.getCell(2).setCellStyle(style);
////                      
////        // create data rows
////        int rowCount = 1;
////         
////        for (Exploration exp : explorationList) {
////            HSSFRow aRow = sheet.createRow(rowCount++);
////            aRow.createCell(0).setCellValue(exp.getBlockNo());
////            aRow.createCell(1).setCellValue(exp.getCountry());
////            aRow.createCell(2).setCellValue(exp.getRegion());
////           
////        }
////	}
//
}
