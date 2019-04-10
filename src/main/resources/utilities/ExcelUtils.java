package com.topscorer.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	 String cellData;

	    public String getDataFromExcel(String fileName, String sheetName, int cellNumber, int rowNumber)
	            throws IOException {

	        try {

	            DataFormatter fmt = new DataFormatter();
	            InputStream fis = new FileInputStream(new File(fileName));
	            XSSFWorkbook workbook = new XSSFWorkbook(fis);
	            XSSFSheet sheet = workbook.getSheet(sheetName);
	            XSSFRow row1 = sheet.getRow(rowNumber);
	            XSSFCell cell1 = row1.getCell(cellNumber);
	            String valueAsSeenInExcel = fmt.formatCellValue(cell1);
	            return valueAsSeenInExcel;
	            
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return String.valueOf(cellData);
	    }

	    public Integer getLastRow(String fileName, String sheetName) throws IOException {
	        int totalNoOfRowsExp = 0;
	        try {
	            FileInputStream file_expected = new FileInputStream(fileName);
	            XSSFWorkbook wb_Expected = new XSSFWorkbook(file_expected);
	            XSSFSheet sheet_Exp = wb_Expected.getSheet(sheetName);
	            totalNoOfRowsExp = sheet_Exp.getLastRowNum();
	            return totalNoOfRowsExp;
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return totalNoOfRowsExp;
	    }

	        
	    public static void writeexcel() throws IOException
		{
			
	    	//Need to update as the global path 
			File src=new File("./TestData/wordpress.xlsx");
			FileInputStream fis=new FileInputStream(src);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sh1= wb.getSheetAt(0);
			sh1.getRow(0).createCell(2).setCellValue("fail");
			FileOutputStream fout=new FileOutputStream(new File(System.getProperty("user.dir")+"\\TestData\\wordpress.xlsx"));
			wb.write(fout);
			fout.close();

			
		}
		
}
