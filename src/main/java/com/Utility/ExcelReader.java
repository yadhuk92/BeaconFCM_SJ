package com.Utility;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static String sheetName;
	public  ExcelReader(String sheetName) {
		this.sheetName = sheetName;
	}
	private  static XSSFSheet getSheet() throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			return sheet;
		}
	}
	public static java.util.List<Map<String,String>> getdata() throws IOException {
		XSSFSheet sheet = getSheet();
		java.util.List<Map<String,String>> completedata = null;

		Map<String,String> testdata=null;


		int lastRowNum = sheet.getPhysicalNumberOfRows();
		int lastCellNum = sheet.getRow(0).getLastCellNum();

		//Getting Keys from excel(column header values)
		java.util.List list = new ArrayList();
		for(int c=0;c<lastCellNum;c++) {
			XSSFRow row = sheet.getRow(0);
			XSSFCell cell = row.getCell(c);
			String Keys = cell.getStringCellValue();
			list.add(Keys);
			//System.out.println(Keys);
		}


		completedata= new ArrayList<Map<String,String>>();

		//Getting values from excel(neglecting the column header)
		for(int i=1;i<lastRowNum;i++) {
			XSSFRow row = sheet.getRow(i);
			testdata = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
			for(int j=0;j<lastCellNum;j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter formatter = new DataFormatter();
				String Values = formatter.formatCellValue(cell);
				//System.out.println(Values);
				String put = testdata.put((String) list.get(j), Values);
				//System.out.println(put);
			}
			completedata.add(testdata);

		}
		return completedata;	

	}


}
