package com.BasePackage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public static List<String> getTotalACReceivedCount() throws IOException {
		List<String> values = new ArrayList<>();
	    // Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");
	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";
	    
	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    System.out.println(latestFile);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return values; // Exit if file not found
	    }
	    
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    
	    // Use the correct sheet name; update if your sheet name is different (e.g., "Received")
	    Sheet sheet = workbook.getSheet("sheet1");
	    if (sheet == null) {
	        System.out.println("Sheet 'sheet1' not found in the Excel file.");
	        workbook.close();
	        fis.close();
	        return values; // Exit if sheet not found
	    }
	    
	    // Get the header row (adjust the index if your header is not on the first row)
	    Row headerRow = sheet.getRow(1);
	    if (headerRow == null) {
	        System.out.println("Header row is missing in the Excel sheet.");
	        workbook.close();
	        fis.close();
	        return values; // Exit if header row is missing
	    }
	    
	    // Debug: print out all header cell values
//	    for (Cell cell : headerRow) {
//	        String cellValue = "";
//	        if (cell.getCellType() == CellType.STRING) {
//	            cellValue = cell.getStringCellValue();
//	        }
//	        System.out.println("Header cell " + cell.getColumnIndex() + ": " + cellValue);
//	    }
	    
	    // Locate the column index for "TOTAL A/C RECEIVED"
	    int targetColumn = 1;
	    for (Cell cell : headerRow) {
	        if (cell.getCellType() == CellType.STRING) {
	            String headerText = cell.getStringCellValue();
	            // Normalize the header text: remove non-alphanumeric characters and convert to uppercase
	            String normalizedHeader = headerText.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
	            if (normalizedHeader.equals("TOTAL A/C RECEIVED")) {
	                targetColumn = cell.getColumnIndex();
	                break;
	            }
	        }
	    }
	    
	    if (targetColumn == -1) {
	        System.out.println("'TOTAL A/C RECEIVED' column not found.");
	        workbook.close();
	        fis.close();
	        return values; // Exit if the target column is not found
	    }
	    
	    // Count non-empty cells in the identified column (excluding the header)
	    for (int i = 2; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        Cell cell = row.getCell(targetColumn);
	        if (cell != null && cell.getCellType() != CellType.BLANK) {
	            String cellValue = "";
	            if (cell.getCellType() == CellType.STRING) {
	                cellValue = cell.getStringCellValue().trim();
	            } else if (cell.getCellType() == CellType.NUMERIC) {
	                cellValue = (cell.getNumericCellValue() == (int) cell.getNumericCellValue())
	                        ? String.valueOf((int) cell.getNumericCellValue())
	                        : String.valueOf(cell.getNumericCellValue());
	            }

	            if (!cellValue.isEmpty()) {
	                values.add(cellValue);
	            }
	        }
	    }

	    workbook.close();
	    fis.close();
	    return values;
	}
	
	public static int getTotalAccountNumberCount() throws IOException {
	    // Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");

	    // Construct the path to the Downloads directory
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    System.out.println(latestFile);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return 0; // Return 0 if no file is found
	    }

	    // Open the latest Excel file
	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheet("sheet1"); // Use the correct sheet name

	    // Initialize count variable
	    int count = 0;

	    // Iterate through rows, excluding the first row (header)
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from the second row
	        Row row = sheet.getRow(i);
	        if (row == null) continue; // Skip empty rows

	        // Assuming "A/c Number" is in column 8 (index 7 as column index starts from 0)
	        Cell accountCell = row.getCell(7);
	        if (accountCell != null) {
	            if (accountCell.getCellType() == CellType.NUMERIC || accountCell.getCellType() == CellType.STRING) {
	                count++;
	            }
	        }
	    }

	    workbook.close();
	    fis.close();
	    
	    return count; // Return the total count of values under "A/c Number"
	}
	
	public static List<List<String>> readRequiredColumns() throws IOException {
	    // Get the user's home directory dynamically
	    String userHome = System.getProperty("user.home");
	    String downloadDir = userHome + File.separator + "Downloads";

	    // Get the latest downloaded Excel file
	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No files found in the download directory.");
	        return Collections.emptyList();
	    }

	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0); // First sheet

	    // Define required column names in the specified order
	    List<String> requiredColumns = Arrays.asList(
	        "A/c Number", "A/c Name", "Sol Id", "Branch Name",
	        "Allocated /De Allocated  Status", "Product Type",
	        "O/S Bal", "Total Overdue", "Contact No.", "Overdue Date",
	        "Contact Date", "Disposition", "Next Action Owner"
	    );

	    Map<String, Integer> columnIndexMap = new LinkedHashMap<>();
	    Row headerRow = sheet.getRow(0);
	    if (headerRow == null) {
	        workbook.close();
	        return Collections.emptyList();
	    }

	    // Identify required column indexes
	    for (Cell cell : headerRow) {
	        String headerValue = cell.getStringCellValue().trim();
	        if (requiredColumns.contains(headerValue)) {
	            columnIndexMap.put(headerValue, cell.getColumnIndex());
	        }
	    }

	    List<List<String>> extractedData = new ArrayList<>();
	    DataFormatter dataFormatter = new DataFormatter(); // Formats as displayed in Excel

	    // Iterate through rows and extract values exactly as shown in Excel
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row (0)
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        List<String> rowData = new ArrayList<>();
	        boolean isRowEmpty = true;

	        for (String columnName : requiredColumns) {
	            int columnIndex = columnIndexMap.getOrDefault(columnName, -1);
	            String cellValue = "";
	            if (columnIndex != -1) {
	                Cell cell = row.getCell(columnIndex);
	                if (cell != null) {
	                    cellValue = dataFormatter.formatCellValue(cell); // Formats value as displayed
	                }
	            }
	            if (!cellValue.isEmpty()) {
	                isRowEmpty = false;
	                rowData.add(cellValue);
	            }
	        }

	        if (!isRowEmpty) {
	            extractedData.add(rowData);
	        }
	    }

	    workbook.close();
	    return extractedData;
	}
	
	public static List<List<String>> readAllocatedaccountdetails() throws IOException {
        // Get the user's home directory dynamically
        String userHome = System.getProperty("user.home");
        String downloadDir = userHome + File.separator + "Downloads";

        // Get the latest downloaded Excel file
        File latestFile = getLatestFileFromDir(downloadDir);
        if (latestFile == null) {
            System.out.println("No files found in the download directory.");
            return Collections.emptyList();
        }

        FileInputStream fis = new FileInputStream(latestFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // First sheet

        // Define required column names in the specified order
        List<String> requiredColumns = Arrays.asList(
            "A/c Number", "A/c Name", "Allocated/De Allocated Date",
            "Allocated/De Allocated  Status", "Agency Name", "Agent ID", "Agent Name"
        );

        Map<String, Integer> columnIndexMap = new LinkedHashMap<>();
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            workbook.close();
            return Collections.emptyList();
        }

        // Identify required column indexes
        for (Cell cell : headerRow) {
            String headerValue = cell.getStringCellValue().trim();
            if (requiredColumns.contains(headerValue)) {
                columnIndexMap.put(headerValue, cell.getColumnIndex());
            }
        }

        List<List<String>> extractedData = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter(); // Formats as displayed in Excel

        // Iterate through rows and extract values exactly as shown in Excel
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header row (0)
            Row row = sheet.getRow(i);
            if (row == null) continue;

            List<String> rowData = new ArrayList<>();
            boolean isRowEmpty = true;

            for (String columnName : requiredColumns) {
                int columnIndex = columnIndexMap.getOrDefault(columnName, -1);
                String cellValue = "";
                if (columnIndex != -1) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        cellValue = dataFormatter.formatCellValue(cell); // Formats value as displayed
                    }
                }
                if (!cellValue.isEmpty()) {
                    isRowEmpty = false;
                    rowData.add(cellValue);
                }
            }

            if (!isRowEmpty) {
                extractedData.add(rowData);
            }
        }

        workbook.close();
        return extractedData;
    }

	public static boolean isEmployeePresent(String empId, String empName) throws IOException {
        // Get the user's Downloads directory
        String userHome = System.getProperty("user.home");
        String downloadDir = userHome + File.separator + "Downloads";

        // Get the latest downloaded file
        File latestFile = getLatestFileFromDir(downloadDir);
        if (latestFile == null) {
            System.out.println("No files found in the download directory.");
            return false; 
        }

        // Open the Excel file
        FileInputStream fis = new FileInputStream(latestFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("sheet1"); // Adjust sheet name if needed

        // Iterate through rows (skipping the header row)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { 
            Row row = sheet.getRow(i);
            if (row == null) continue; // Skip empty rows

            // Read Employee ID and Name
            Cell idCell = row.getCell(1); // Assuming Employee ID is in the 2nd column (Index 1)
            Cell nameCell = row.getCell(2); // Assuming Name is in the 3rd column (Index 2)

            // Convert cell values to String
            String idValue = (idCell != null) ? idCell.getStringCellValue().trim() : "";
            String nameValue = (nameCell != null) ? nameCell.getStringCellValue().trim() : "";

            // Check for a match
            if (idValue.equalsIgnoreCase(empId) && nameValue.equalsIgnoreCase(empName)) {
                workbook.close();
                return true; // Match found
            }
        }

        workbook.close();
        return false; // No match found
    }
	
	public static Map<String, Object> getACCountAndOutBalInLakhs() throws IOException {
	    Map<String, Object> result = new HashMap<>();
	    int acCount = 0;
	    double outBalSum = 0.0;

	    String userHome = System.getProperty("user.home");
	    String downloadDir = userHome + File.separator + "Downloads";

	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No Excel file found.");
	        return result;
	    }

	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0); // Assumes first sheet

	    Row headerRow = sheet.getRow(0); // Adjust if header is in a different row
	    if (headerRow == null) {
	        workbook.close();
	        fis.close();
	        System.out.println("Header row not found.");
	        return result;
	    }

	    int acNumberCol = -1;
	    int outBalCol = -1;

	    for (Cell cell : headerRow) {
	        if (cell.getCellType() == CellType.STRING) {
	        	String colName = cell.getStringCellValue().trim();
	            if (colName.equals("AC_Number")) {
	                acNumberCol = cell.getColumnIndex();
	            } else if (colName.equals("OUT_Bal")) {
	                outBalCol = cell.getColumnIndex();
	            }
	        }
	    }

	    if (acNumberCol == -1 || outBalCol == -1) {
	        workbook.close();
	        fis.close();
	        System.out.println("Required columns not found.");
	        return result;
	    }

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        // Count AC Numbers
	        Cell acCell = row.getCell(acNumberCol);
	        if (acCell != null && acCell.getCellType() != CellType.BLANK) {
	            acCount++;
	        }

	        // Sum OUT_Bal if non-zero
	        Cell outBalCell = row.getCell(outBalCol);
	        if (outBalCell != null && outBalCell.getCellType() == CellType.NUMERIC) {
	            double value = outBalCell.getNumericCellValue();
	            if (value != 0) {
	                outBalSum += value;
	            }
	        }
	    }

	    workbook.close();
	    fis.close();

	    result.put("TotalACNumbers", acCount);
	    result.put("TotalOutBalInLakhs", Math.round(outBalSum / 100000.0 * 100.0) / 100.0); // Rounded to 2 decimals

	    return result;
	}
	
	public static Map<String, Object> extractACSummaryDetails() throws IOException {
	    Map<String, Object> result = new HashMap<>();
	    int acCount = 0;
	    double totalOutstanding = 0.0;
	    Set<String> initialCategories = new HashSet<>();

	    String userHome = System.getProperty("user.home");
	    String downloadDir = userHome + File.separator + "Downloads";

	    File latestFile = getLatestFileFromDir(downloadDir);
	    if (latestFile == null) {
	        System.out.println("No Excel file found.");
	        return result;
	    }

	    FileInputStream fis = new FileInputStream(latestFile);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0);

	    Row headerRow = sheet.getRow(0);
	    if (headerRow == null) {
	        workbook.close();
	        fis.close();
	        System.out.println("Header row not found.");
	        return result;
	    }

	    int acCol = -1;
	    int outCol = -1;
	    int categoryCol = -1;

	    for (Cell cell : headerRow) {
	        if (cell.getCellType() == CellType.STRING) {
	            String col = cell.getStringCellValue().trim();
	            if (col.equals("AC_Number")) acCol = cell.getColumnIndex();
	            else if (col.equals("Total_outstanding")) outCol = cell.getColumnIndex();
	            else if (col.equals("Initial_Account_Category")) categoryCol = cell.getColumnIndex();
	        }
	    }

	    if (acCol == -1 || outCol == -1 || categoryCol == -1) {
	        workbook.close();
	        fis.close();
	        System.out.println("Required columns not found.");
	        return result;
	    }

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        Cell acCell = row.getCell(acCol);
	        if (acCell != null && acCell.getCellType() != CellType.BLANK) {
	            acCount++;
	        }

	        Cell outCell = row.getCell(outCol);
	        if (outCell != null && outCell.getCellType() == CellType.NUMERIC) {
	            totalOutstanding += outCell.getNumericCellValue();
	        }

	        Cell catCell = row.getCell(categoryCol);
	        if (catCell != null && catCell.getCellType() == CellType.STRING) {
	            initialCategories.add(catCell.getStringCellValue().trim());
	        }
	    }

	    workbook.close();
	    fis.close();

	    result.put("TotalACNumbers", acCount);
	    result.put("InitialAccountCategories", new ArrayList<>(initialCategories));
	    result.put("TotalOutstandingInLakhs", Math.round(totalOutstanding / 100000.0 * 100.0) / 100.0);

	    return result;
	}
	
	
}
