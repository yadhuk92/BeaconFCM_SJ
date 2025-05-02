package com.BasePackage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

public class ExcelDrivenTestExecutor {

    public static void main(String[] args) {
        String excelPath = "./Testcases/Testcases.xlsx"; // Replace with actual path

        try (FileInputStream fis = new FileInputStream(new File(excelPath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header (row 0)
                Row row = sheet.getRow(i);
                if (row != null) {
                    String testCaseName = row.getCell(1).getStringCellValue(); // Column B
                    String executeFlag = row.getCell(2).getStringCellValue();  // Column C

                    if ("Yes".equalsIgnoreCase(executeFlag)) {
                        System.out.println("Executing: " + testCaseName);
                        runTestCase(testCaseName);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runTestCase(String className) {
        try {
        	Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            
            for (Method method : methods) {
                //if (method.getName().startsWith("test")) { // Customize logic if needed
                    method.invoke(instance);
                //}
            }

        } catch (Exception e) {
            System.err.println("Failed to execute test case: " + className);
            e.printStackTrace();
        }
    }
}