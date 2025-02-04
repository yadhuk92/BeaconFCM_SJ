package com.BasePackage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DownloadedExcelReader {
	
	public static int getCountOfRows() throws IOException {
		// Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");
	    
	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return 0; // Return 0 if no file is found
	    }

	    // Open the latest Excel file
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

	    // Initialize the count variable
	    int count = 0;

	    // Iterate through rows, excluding the first row (header)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from the second row
            Row row = sheet.getRow(i);
            if (row == null) continue; // Skip empty rows

            // Increment count for non-empty rows
            count++;
        }

	    workbook.close();
	    return count; 
	    // Return the total count of rows with data in "A/c No" column
	}
	
	public static DataSummary getCountAndAccountNumbers() throws IOException {
		// Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");
	    
	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return new DataSummary(0, new ArrayList<>(), new ArrayList<>()); // Return empty data if no file is found
	    }

	    // Open the latest Excel file
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

	    // Variables to store row count, account numbers, and contact numbers
	    int count = 0;
	    List<String> accountNumbers = new ArrayList<>();
	    List<String> contactNumbers = new ArrayList<>(); // List to hold contact numbers

	    // Iterate through rows, excluding the first row (header)
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from the second row
	        Row row = sheet.getRow(i);
	        if (row == null) continue; // Skip empty rows

	        // Increment count for non-empty rows
	        count++;

	        // Assuming "A/c No" is in column 8 (index 7 as column index starts from 0)
	        Cell accountCell = row.getCell(7); // Adjust the column index for A/c No
	        if (accountCell != null) {
	            String accountNumber = null;
	            if (accountCell.getCellType() == CellType.NUMERIC) {
	                accountNumber = String.valueOf((long) accountCell.getNumericCellValue());
	            } else if (accountCell.getCellType() == CellType.STRING) {
	                accountNumber = accountCell.getStringCellValue().trim();
	            }

	            if (accountNumber != null && !accountNumber.isEmpty()) {
	                accountNumbers.add(accountNumber);
	            }
	        }

	        // Assuming "Contact No." is in column 37 (index 36 as column index starts from 0)
	        Cell contactCell = row.getCell(36); // Adjust the column index for Contact No.
	        if (contactCell != null) {
	            String contactNumber = null;
	            if (contactCell.getCellType() == CellType.NUMERIC) {
	                contactNumber = String.valueOf((long) contactCell.getNumericCellValue());
	            } else if (contactCell.getCellType() == CellType.STRING) {
	                contactNumber = contactCell.getStringCellValue().trim();
	            }

	            if (contactNumber != null && !contactNumber.isEmpty()) {
	                contactNumbers.add(contactNumber);
	            }
	        }
	    }

	    workbook.close();
	    return new DataSummary(count, accountNumbers, contactNumbers); // Return count, account numbers, and contact numbers
	}
	
	public static DataSummary getAccountNumberSummary() throws IOException {
		// Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");
	    
	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return new DataSummary(0, new ArrayList<>(), new ArrayList<>()); // Return empty data if no file is found
	    }

	    // Open the latest Excel file
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

	    // Variables to store row count and account numbers
	    int count = 0;
	    List<String> accountNumbers = new ArrayList<>();

	    // Iterate through rows, excluding the first row (header)
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from the second row
	        Row row = sheet.getRow(i);
	        if (row == null) continue; // Skip empty rows

	        // Assuming "A/c Number" is in column 8 (index 7 as column index starts from 0)
	        Cell accountCell = row.getCell(7); // Adjust the column index for "A/c Number"
	        if (accountCell != null) {
	            String accountNumber = null;
	            if (accountCell.getCellType() == CellType.NUMERIC) {
	                accountNumber = String.valueOf((long) accountCell.getNumericCellValue());
	            } else if (accountCell.getCellType() == CellType.STRING) {
	                accountNumber = accountCell.getStringCellValue().trim();
	            }

	            if (accountNumber != null && !accountNumber.isEmpty()) {
	                accountNumbers.add(accountNumber);
	                count++;
	            }
	        }
	    }

	    workbook.close();
	    return new DataSummary(count, accountNumbers, new ArrayList<>()); // Return count and account numbers
	}
	
	public static String getOneAccountNumber() throws IOException {
	    // Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");

	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return null; // Return null if no file is found
	    }

	    // Open the latest Excel file
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

	    // Assuming "A/c Number" is in column 8 (index 7 as column index starts from 0)
	    // Get the first row's account number as an example
	    Row row = sheet.getRow(1); // Start from the second row (index 1) to skip the header
	    if (row == null) {
	        workbook.close();
	        return null; // Return null if the row is empty
	    }

	    // Extract the account number from the cell (assuming it's in column 8, index 7)
	    Cell accountCell = row.getCell(7); // Adjust the column index for "A/c Number"
	    String accountNumber = null;

	    if (accountCell != null) {
	        if (accountCell.getCellType() == CellType.NUMERIC) {
	            accountNumber = String.valueOf((long) accountCell.getNumericCellValue());
	        } else if (accountCell.getCellType() == CellType.STRING) {
	            accountNumber = accountCell.getStringCellValue().trim();
	        }
	    }

	    workbook.close();
	    return accountNumber; // Return the first account number found
	}
	/**
	 * Method to get the most recently modified file in a directory.
	 *
	 * @param dirPath Path to the directory
	 * @return File object of the most recently modified file, or null if no files exist
	 */
	private static File getLatestFileFromDir(String dirPath) {
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles((dir1, name) -> name.endsWith(".xlsx")); // Filter for Excel files

	    if (files == null || files.length == 0) {
	        return null;
	    }

	    // Sort files by last modified date in descending order
	    return Arrays.stream(files)
	                 .max(Comparator.comparingLong(File::lastModified))
	                 .orElse(null);
	}
	
	public static class DataSummary {
	    private int rowCount;
	    private List<String> accountNumbers;
	    private List<String> contactNumbers; // List to hold contact numbers

	    public DataSummary(int rowCount, List<String> accountNumbers, List<String> contactNumbers) {
	        this.rowCount = rowCount;
	        this.accountNumbers = accountNumbers;
	        this.contactNumbers = contactNumbers;
	    }

	    public int getRowCount() {
	        return rowCount;
	    }

	    public void setRowCount(int rowCount) {
	        this.rowCount = rowCount;
	    }

	    public List<String> getAccountNumbers() {
	        return accountNumbers;
	    }

	    public void setAccountNumbers(List<String> accountNumbers) {
	        this.accountNumbers = accountNumbers;
	    }

	    public List<String> getContactNumbers() {
	        return contactNumbers; // Getter for contact numbers
	    }

	    public void setContactNumbers(List<String> contactNumbers) {
	        this.contactNumbers = contactNumbers;
	    }

	    @Override
	    public String toString() {
	        return "DataSummary{" +
	                "rowCount=" + rowCount +
	                ", accountNumbers=" + accountNumbers +
	                ", contactNumbers=" + contactNumbers +
	                '}';
	    }
	}
}
