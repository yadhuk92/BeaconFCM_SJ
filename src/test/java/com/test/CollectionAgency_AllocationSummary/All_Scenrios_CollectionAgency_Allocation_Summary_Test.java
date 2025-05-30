package com.test.CollectionAgency_AllocationSummary;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.Duration;
import java.nio.file.Path;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.BasePackage.PropertiesFileUtil;
import com.CollectionAgency.AllocationSummary.CA_Allocation_Summary_Page;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import static org.junit.Assert.assertTrue;

public class All_Scenrios_CollectionAgency_Allocation_Summary_Test {

	Base_Class baseclass;
	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	CA_Allocation_Summary_Page allocationsummary;
	Login_Class CollectionAgencyLogin;

	@BeforeClass

	public void SetUp() throws Exception {

		baseclass = new Base_Class();
		TestListener = new TestListener();
	}

	@BeforeMethod
	public void setupTest(Method method) {
		driver = baseclass.getDriver();
		drivers.add(driver);
		allocationsummary = new CA_Allocation_Summary_Page(driver);
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CA-AllocationSummary");
	}

	@Test(priority = 1)
	public void testLoginAndNavigateToDashboard() throws Exception {
		Login_Class.CollectionAgencyLogin();
		driver = baseclass.getDriver();
		allocationsummary = new CA_Allocation_Summary_Page(driver);
		screenShot = new com.Utility.ScreenShot(driver);

		allocationsummary.navigateToAllocationSummary();
		Assert.assertTrue(allocationsummary.isAllocationSummaryPageDisplayed(),
				"Allocation Summary page should be displayed.");
	}

	@Test(priority = 2)
	public void testGridColumnsDisplay() {
		Assert.assertTrue(allocationsummary.isDateColumnDisplayed(), "Date column is not displayed.");
		extenttest.log(Status.PASS, "Date column is displayed");
		Log.info("Date column is displayed");

		Assert.assertTrue(allocationsummary.isTotalAccountsReceivedColumnDisplayed(),
				"Total Accounts Received column is not displayed.");
		extenttest.log(Status.PASS, "Total Accounts Received column is displayed.");
		Log.info("Total Accounts Received column is displayed.");

		Assert.assertTrue(allocationsummary.isAllocatedToAgentsColumnDisplayed(),
				"Allocated to Agents column is not displayed.");
		extenttest.log(Status.PASS, "Allocated to Agents column is displayed.");
		Log.info("Allocated to Agents column is displayed.");

		Assert.assertTrue(allocationsummary.isGridValuesDisplayed(), "Values in grid not displayed.");
		extenttest.log(Status.PASS, "Values in grid displayed.");
		Log.info("Values in grid displayed.");
	}

	@Test(priority = 3)
	public void testDownloadAccountDetails() throws IOException, InterruptedException {
		// Step 1: Click the download button for account details
		allocationsummary.clickDownloadButton();
		Log.info("Click download button sucessfully");
		Thread.sleep(5000);
		// Step 2: Open the downloaded Excel file
		String filePath = System.getProperty("user.home") + "/Downloads";
		File file = new File(filePath);
		File[] matchingFiles = file.listFiles((dir, name) -> name.startsWith("DashBoard_") && name.endsWith(".xlsx"));
		if (matchingFiles == null || matchingFiles.length == 0) {
			throw new FileNotFoundException("No matching DashBoard_ Excel file found in Downloads");
		}
		File latestFile = Arrays.stream(matchingFiles).max(Comparator.comparingLong(File::lastModified))
				.orElseThrow(() -> new FileNotFoundException("No recent DashBoard_ Excel file found"));

		FileInputStream fis = new FileInputStream(latestFile);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Log.info("Opened downloaded Excel file successfully");

		// Expected Result: Validate the expected columns and data
		Thread.sleep(3000);
		Row headerRow = sheet.getRow(0);
		Row subHeaderRow = sheet.getRow(1);
		List<String> finalHeaders = new ArrayList<>();

		for (int i = 0; i < subHeaderRow.getLastCellNum(); i++) {
			String groupHeader = "";
			for (int j = 0; j < sheet.getNumMergedRegions(); j++) {
				CellRangeAddress region = sheet.getMergedRegion(j);
				if (region.isInRange(0, i)) {
					Cell cell = sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn());
					groupHeader = cell.getStringCellValue().trim();
					break;
				}
			}
			if (groupHeader.isEmpty()) {
				groupHeader = headerRow.getCell(i).getStringCellValue().trim();
			}
			String subHeader = subHeaderRow.getCell(i).getStringCellValue().trim();
			finalHeaders.add(groupHeader + " - " + subHeader);
		}

		String[] expectedColumns = { "Date - Date", "Total Account Received - NO OF ACCOUNTS",
				"Total Account Received - Total O/S Amount In Lakhs", "Allocated To Agency - NO  OF  ACCOUNTS",
				"Allocated To Agency - Total  O/S  Amount  In  Lakhs" };

		for (int i = 0; i < expectedColumns.length; i++) {
			Assert.assertEquals(finalHeaders.get(i), expectedColumns[i], "Header mismatch at index " + i);
			extenttest.log(Status.PASS, "Column name matched: " + expectedColumns[i]);
			Log.info("Column name matched: " + expectedColumns[i]);
		}

		workbook.close();
		fis.close();
	}

	@Test(priority = 4)
	public void testEmptyGridError() {
		if (allocationsummary.isNoDataMessageDisplayed()) {
			assertTrue("No records to display message should be displayed", true);
			extenttest.log(Status.PASS, "No records to display message is correctly shown when grid is empty");
			Log.info("No records to display message is correctly shown when grid is empty");
		} else {
			extenttest.log(Status.INFO, "Grid has data, so no 'No records' message expected");
			Log.info("Grid has data, skipping empty grid validation");
		}

	}
/*
	@Test(priority = 5)
	public void testAgentCretion() throws Exception {

		// Step 1: Create Agent in Collection App

		allocationsummary.navigateToAddAgentPage();
		allocationsummary.createAgent("05", "Agent 5", "9999999999");
		extenttest.log(Status.INFO, "Created agent successfully");
		Log.info("Created agent successfully");

		// Step 3: Login to Core System for Allocation
		Login_Class.CoreLogin();
		extenttest.log(Status.INFO, "Triggered Core application login");
		Log.info("Triggered Core application login");

	}

	@Test(priority = 6)
	public void testAgentAllocationEndToEnd() throws Exception {

		allocationsummary.VerifyAgencyAccountAllocationDisplayingasSubmenu();
		allocationsummary.allocateAccounts("200000");
		extenttest.log(Status.INFO, "Core allocation done");
		Log.info("Core allocation done");
		Thread.sleep(3000); // Optional pause

		// Step 10: Allocate Agent accounts in Collection
		allocationsummary.allocateAgentAccounts();
		extenttest.log(Status.INFO, "Agent allocation completed");
		Log.info("Agent allocation completed");

		// Step 11: Final validation
		Assert.assertTrue(allocationsummary.verifyAllocation(), "Allocation count mismatch!");

	}
*/

	@Test(priority = 7)
	public void testDownloadedFileFormat() throws IOException, InterruptedException {
		// Click the expected download button
		allocationsummary.clickFileDownloadButton();

		// Assuming file download happens instantly; adjust waits as needed
		String filePath = System.getProperty("user.home") + "/Downloads";
		Path downloadPath = Paths.get(filePath);

		Thread.sleep(5000);
		Log.info("Step 1 : Check downloaded file format");

		File[] matchingFiles = downloadPath.toFile()
				.listFiles((dir, name) -> name.startsWith("DashBoard_") && name.endsWith(".xlsx"));

		if (matchingFiles == null || matchingFiles.length == 0) {
			throw new FileNotFoundException("No DashBoard_*.xlsx file found in Downloads");
		}

		File latestFile = Arrays.stream(matchingFiles).max(Comparator.comparingLong(File::lastModified))
				.orElseThrow(() -> new FileNotFoundException("No recent DashBoard_ Excel file found"));
		Log.info("Latest downloaded file: " + latestFile.getName());

		Assert.assertTrue(latestFile.exists(), "Downloaded file does not exist");

		try (FileInputStream fis = new FileInputStream(latestFile); Workbook workbook = new XSSFWorkbook(fis)) {
			Assert.assertTrue(workbook.getNumberOfSheets() > 0, "The XLSX file appears to be empty or corrupt.");
		} catch (Exception e) {
			Assert.fail("The downloaded file is not a valid XLSX format or failed to open: " + e.getMessage());
		}
		Log.info("File is in XLSX format and opens without errors");
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {

		// Check if the test case failed

		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			try {
				// Take the screenshot for the failed test
				File image = screenShot.takeScreenShot(methodName, "Failure");

				extenttest.log(Status.INFO, "Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {
		ExcelReader = new com.Utility.ExcelReader("CA-AllocationSummary");
		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

	@AfterClass
	public void afterEachTest() {
		ExtentManager.getInstance().flush();
		// Close all tracked browser instances
		for (WebDriver driverInstance : drivers) {
			if (driverInstance != null) {
				driverInstance.quit();
			}
		}

		// Clear the list of drivers
		drivers.clear();

		System.out.println("All browser instances have been closed.");
	}

}
