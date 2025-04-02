package Core.AddAgency;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.CallCentre.CoreAllocationSummaryPage;
import Core.CallCentre.CoreAutoAllocationPage;
import Core.CallCentre.CoreAutoAllocationPage.ProcedureResult;
import Core.CallCentre.CoreManualAllocationPage;
import Core.AddAgencyManagement.AddAgencyPage;
import bsh.ParseException;
import jdk.internal.org.jline.utils.Log;


public class CoreAddAgency_TestClass {
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	AddAgencyPage addagencypage;
	CoreManualAllocationPage coremanualallocationpage;
	CoreAllocationSummaryPage coreallocationsummarypage;
	
	@BeforeSuite

	public void SetUp() throws Exception {

		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CoreLogin();
		
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		addagencypage=new AddAgencyPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Core_Manual_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		drivers.add(driver);
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		callcenterlogin = new Login_Class();
		// Update the ScreenShot object with the new driver
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre ManualAllocation");
	}
	
	@Test(priority = 1)
	public void Login_to_CoreWithHO_User() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");
			
			 //Home
			
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Home"),
					"Navigated to Home Page.");
			
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to Home  page, URL shows Home.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 2)
	public void Validate_AddAgency_Page() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");
			
			// Navigate to Add Agency Management  Main Menu
			
			addagencypage.navigateAgencyManagement();
			ExtentTestManager.getTest().log(Status.PASS, "Cliked on  Agency Management  Main Menu.");

			// Click on Add Agency option
			
			addagencypage.navigateToAddAgencyOption();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Add Agency sub-menu.");

			// Expected Result: User is navigated to Add Agency  page
			// URL shows Agency/AddNewAgency
		
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Agency/AddNewAgency"),
					"Not navigated to Add Agency  page.");
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to Add Agency  page, URL showsAgency/AddNewAgency.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
//	@Test(priority = 3)
//	public void Validate_Fields_From_AddAgency_Page() throws InterruptedException {
//		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//		
//		try {
//			ExtentTestManager.getTest().log(Status.PASS, "Ads Agency page loaded");
//			
//			// Navigate to Add Agency Management  Main Menu
//			
//			addagencypage.VerifyrequiredFileds();
//			ExtentTestManager.getTest().log(Status.PASS, "Cliked on  Agency Management  Main Menu.");
//
//			// Click on Add Agency option
//			
//			addagencypage.navigateToAddAgencyOption();
//		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Add Agency sub-menu.");
//
//			// Expected Result: User is navigated to Add Agency  page
//			// URL shows Agency/AddNewAgency
//		
//			String currentUrl = driver.getCurrentUrl();
//			Assert.assertTrue(currentUrl.contains("Agency/AddNewAgency"),
//					"Not navigated to Add Agency  page.");
//			ExtentTestManager.getTest().log(Status.PASS,
//					"User is navigated to Add Agency  page, URL showsAgency/AddNewAgency.");
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
//		
//		}
//
//		catch (AssertionError | Exception e) {
//			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//			throw e;
//		}
//		Thread.sleep(3000);
//	}
	
	@Test(priority = 3)
	public void Validate_Invalid_PAN_WarningMessage() throws InterruptedException {
		
		String expectedMessage="Invalid PAN Number";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
		
			
			String ErrorMEssage=addagencypage.Validate_ErrorMessage_InvalidPAN();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Error message");
			
	Assert.assertEquals(ErrorMEssage, expectedMessage);
		
			ExtentTestManager.getTest().log(Status.PASS,
					"Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
//	@Test(priority = 4)
//	public void Validate_Existing_PAN_WarningMessage() throws InterruptedException {
//		
//		String expectedMessage="Invalid PAN Number";
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//		
//		try {
//			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
//		
//			
//			String ErrorMEssage=addagencypage.Validate_ErrorMessage_ExistingPAN();
//			ExtentTestManager.getTest().log(Status.PASS, "Validating Error message");
//			
//	Assert.assertEquals(ErrorMEssage, expectedMessage);
//		
//			ExtentTestManager.getTest().log(Status.PASS,
//					"Error Message Displayed");
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
//		
//		}
//
//		catch (AssertionError | Exception e) {
//			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//			throw e;
//		}
//		Thread.sleep(3000);
//	}
//	
	@Test(priority = 5)
	public void Validate_Valid_PAN() throws InterruptedException {
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
		
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
		
			
			addagencypage.Validate_PAN();
			
			WebElement error=wait.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.PanInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid PAN!");
			
			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 6)
	public void Validate_InvalidGST() throws InterruptedException {
		
		String expectedMessage="Invalid GST Number";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
		
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
		
			String ErrorMEssage=addagencypage.Validate_ErrorMessage_InvalidGST();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Error message");
			
			Assert.assertEquals(ErrorMEssage, expectedMessage);
		
			ExtentTestManager.getTest().log(Status.PASS,
					"Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 7)
	public void Validate_validGST() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {

			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			addagencypage.Validate_GST();

			WebElement error = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.GSTInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid PAN!");

			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);

	}
	
	@Test(priority = 8)
	public void Validate_InvalidCollectionName() throws InterruptedException {
		
		String expectedMessage="Invalid Agency Name";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		try {
		
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
		
			String ErrorMEssage=addagencypage.Validate_ErrorMessage_InvalidCollectionName();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Collection Agency Error message");
			
			Assert.assertEquals(ErrorMEssage, expectedMessage);
		
			ExtentTestManager.getTest().log(Status.PASS,
					"Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 9)
	public void Validate_validCollectionAgencyName() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {

			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			addagencypage.Validate_CollectionAgencyName();

			WebElement error = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.CoolectionAgencyInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid Collection Agency Name");

			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);

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

		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

	@AfterSuite
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