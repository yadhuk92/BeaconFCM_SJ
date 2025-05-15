package Core.SecurityManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.BannedCustomerPageRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.Disposition.DispositionMasterPage;

public class BannedCustomer_TestClass extends Base_Class {
	

	WebDriver driver;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	BannedCustomer_MainClass BannedCustomer_MainClass;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	BannedCustomerPageRepo BannedCustomer;
	//private List<WebDriver> drivers = new ArrayList<>();
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BOLD = "\u001B[1;34m";
    
    
    @BeforeClass
	public void SetUp() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		//SeleniumLogToFile.startLogging();
		ExcelReader = new com.Utility.ExcelReader("Disposition_master");
		TestListener = new TestListener();
		Login_Class.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		} else {
			System.out.println("Driver is not null");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		BannedCustomer_MainClass = new BannedCustomer_MainClass();
		System.out.println("Before class in disposition master");
		BannedCustomer = new BannedCustomerPageRepo();
	}
	
	@BeforeMethod
    public void setupTest(Method method) throws Exception {
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Disposition master");
        Log.info(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
        System.out.println(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
    }

	@Test(priority = 1)
	public void User_logged_into_coreapplication() throws Exception {
		try {
			Log.info("***User should be logged into core application***");
		
			BannedCustomer_MainClass.AccessBannedCustomerPage();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security management main menu");
			Log.info("1. Click on Security management main menu");
			
			boolean flag2 =BannedCustomer_MainClass.AccessBannedCustomerPage();
			ExtentTestManager.getTest().log(Status.PASS, "2. Click on User Management submenu");
			Log.info("2. Click on User Management submenu"+flag2);
		
			String currentUrl = driver.getCurrentUrl();
			extenttest.log(Status.INFO, "Current URL: " + currentUrl);
			System.out.println("Current URL: " + currentUrl); // Log the URL for debugging
			String expectedUrlPart = "/Admin/BannedCustomer";
			Assert.assertTrue(currentUrl.contains(expectedUrlPart),
					"The last link address does not match. Expected: " + expectedUrlPart);
			
			ExtentTestManager.getTest().log(Status.PASS, "Banned Customer page is displayed with link Admin/BannedCustomer..");
		}catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);}
	
	
	@Test(priority = 2)
	public void User_Banned_Customer_page () throws Exception {
		try {
			Log.info("***User is on Banned Customer page***");
		
			BannedCustomer_MainClass.VeriyFieldsinBannedCustomerPage();
			ExtentTestManager.getTest().log(Status.PASS, "1. View fields and buttons available in Banned customer page.");
			Log.info("1. View fields and buttons available in Banned customer page.");
			
			
		
			String currentUrl = driver.getCurrentUrl();
			extenttest.log(Status.INFO, "Current URL: " + currentUrl);
			System.out.println("Current URL: " + currentUrl); // Log the URL for debugging
			String expectedUrlPart = "/Admin/BannedCustomer";
			Assert.assertTrue(currentUrl.contains(expectedUrlPart),
					"The last link address does not match. Expected: " + expectedUrlPart);
			
			ExtentTestManager.getTest().log(Status.PASS, "Customer name field, ID proof document dropdown, ID proof number field, Add new customer button, Search button, Reset button, Close button are displayed.");
		}catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);}
	
	
	
	

		@AfterSuite
		public void takeScreenshotOnFailure(ITestResult result) {
		    if (result.getStatus() == ITestResult.FAILURE) { // Check if the test has failed
		        String methodName = result.getMethod().getMethodName();
		        try {
		            // Capture the screenshot for the failed test
		            File image = screenShot.takeScreenShot(methodName, "Failure");
		
		            // Log the screenshot to the Extent Report
		            ExtentTestManager.getTest().log(
		                Status.FAIL,
		                "Screenshot captured for failed test: " + methodName,
		                MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build()
		            );
		        } catch (IOException e) {
		            System.err.println("Failed to capture screenshot: " + e.getMessage());
		        }
		    }
		}
		
		@AfterClass
		public void AfterClass() {
		     ExtentManager.getInstance().flush();
		  // Close the browser
		        if (driver != null) {
		          //  driver.quit();
		        }
		 }
		
	}


