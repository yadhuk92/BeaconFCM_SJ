package com.BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class TestFilter implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> selectedMethods = new ArrayList<>();
        LinkedHashMap<String, Boolean> testClassesToRun = getTestClassesFromExcel(".\\Testcases\\TestCases.xlsx", "Sheet1");

		
		  System.out.println("===== Test Cases from Excel ====="); for
		  (Map.Entry<String, Boolean> entry : testClassesToRun.entrySet()) {
		  System.out.println("Class: " + entry.getKey() + " | RunFlag: " +
		  (entry.getValue() ? "Yes" : "No")); }
		  System.out.println("===================================================");
		 

        // Iterate in the order of Excel file
        for (String className : testClassesToRun.keySet()) {
            if (testClassesToRun.get(className)) { // If RunFlag is Yes
                for (IMethodInstance method : methods) {
                    if (method.getMethod().getRealClass().getName().equals(className)) {
                        //System.out.println("Executing Test: " + className + "." + method.getMethod().getMethodName());
                        selectedMethods.add(method);
                    }
                }
            }
        }
        return selectedMethods;
    }

    private LinkedHashMap<String, Boolean> getTestClassesFromExcel(String filePath, String sheetName) {
        LinkedHashMap<String, Boolean> testClasses = new LinkedHashMap<>(); // Maintain order
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                Cell classNameCell = row.getCell(0);
                Cell runFlagCell = row.getCell(1);

                if (classNameCell != null && runFlagCell != null) {
                    String className = classNameCell.getStringCellValue().trim();
                    String runFlag = runFlagCell.getStringCellValue().trim();
                    boolean shouldRun = "Yes".equalsIgnoreCase(runFlag);

                    if (!className.isEmpty()) {
                        testClasses.put(className, shouldRun); // Insertion order is preserved
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testClasses;
    }
}