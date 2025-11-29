package com.amazon.framework.utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public static String[][] irctcLoginData(String fileName) throws IOException{
		XSSFWorkbook workBook = new XSSFWorkbook("./" + fileName + ".xlsx");
		XSSFSheet sheet = workBook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int cellCount = sheet.getRow(0).getLastCellNum();
		String data[][] = new String[rowCount][cellCount];
		for(int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j = 0; j < cellCount; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter dtf = new DataFormatter();
				String value = dtf.formatCellValue(cell);
				data[i - 1][j] = value;
			}
		}
		workBook.close();
		return data;
	}

}
