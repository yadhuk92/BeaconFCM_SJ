package CallCentre.UserManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.CollectionAgency.SecurityManagement.RoleManagement;
import com.Page_Repository.BannedCustomerPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.SecurityManagement.BannedCustomer_MainClass;

public class CallCentreUserManagement_TestClass {
	
	WebDriver driver;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	BannedCustomer_MainClass BannedCustomer_MainClass;
	ExtentTest extenttest;
	//Login_Class corelogin;
	Login_Class AllLoginCases;
	CallCentreUserManagement_MainClass  CallCentreUserManagement;
	//private List<WebDriver> drivers = new ArrayList<>();
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BOLD = "\u001B[1;34m";
    
    
    @BeforeClass
	public void SetUp() throws Exception {
		baseclass = new Base_Class();
		//corelogin = new Login_Class();
		Login_Class.CallCenterLogin();
		//SeleniumLogToFile.startLogging();
		ExcelReader = new com.Utility.ExcelReader("Disposition_master");
		TestListener = new TestListener();
		//Login_Class.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		} else {
			System.out.println("Driver is not null");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		BannedCustomer_MainClass = new BannedCustomer_MainClass();
		System.out.println("Before class in disposition master");
		CallCentreUserManagement = new CallCentreUserManagement_MainClass();
	}
	
	@BeforeMethod
    public void setupTest(Method method) throws Exception {
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Disposition master");
        Log.info(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
        System.out.println(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
    }

	@Test(priority = 1)
	public void Login_to_Beacon_FCM_call_centre_application(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Login_to_Beacon_FCM_call_centre_application 1 *****************");
			System.out.println();

			CallCentreUserManagement_MainClass.Loggedin();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 2)
	public void User_is_logged_in(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** User is logged in *****************");
			System.out.println();

			CallCentreUserManagement_MainClass.AccessUserManagementpage();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	

	@Test(priority = 3)
	public void User_management_page_displayed(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** User management page is displayed *****************");
			System.out.println();

			CallCentreUserManagement_MainClass.validatePageElements1();
			
	//	CallCentreUserManagement_MainClass.validatePageElements2();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	

	@Test(priority = 4)
	public void User_management_page_is_displayed(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** User management page is displayed clicking on add button *****************");
			System.out.println();

			CallCentreUserManagement_MainClass.AccessAddNewUserPage();
			
		
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}
		

		@Test(priority = 5)
		public void Add_New_User_page(ITestContext context)
				throws InterruptedException, IOException, ParseException {

			try {
				System.out.println(" ************** On Add New User page Leave empty *****************");
				System.out.println();

				CallCentreUserManagement_MainClass.AddUserPageEmptySaveAttempt();
				
			
			} catch (AssertionError | Exception e) {
				String testName = new Object() {
				}.getClass().getEnclosingMethod().getName();
				ExtentTestManager.getTest().log(Status.FAIL,
						"Test Failed in method: " + testName + " --> " + e.getMessage());
				Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
				throw e;
			}
		}
			@Test(priority = 6)
			public void On_Add_New_User_ExecutiveID(ITestContext context)
					throws InterruptedException, IOException, ParseException {

				try {
					System.out.println(" ************** On Add New User Executive id*****************");
					System.out.println();

					CallCentreUserManagement_MainClass.EnterValidExecutiveID();
					
				
				} catch (AssertionError | Exception e) {
					String testName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					ExtentTestManager.getTest().log(Status.FAIL,
							"Test Failed in method: " + testName + " --> " + e.getMessage());
					Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
					throw e;
				}


	}
			@Test(priority = 7)
			public void On_Add_New_User_Name(ITestContext context)
					throws InterruptedException, IOException, ParseException {

				try {
					System.out.println(" ************** On Add New User Name*****************");
					System.out.println();

					CallCentreUserManagement_MainClass.EnterValidName();
					
				
				} catch (AssertionError | Exception e) {
					String testName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					ExtentTestManager.getTest().log(Status.FAIL,
							"Test Failed in method: " + testName + " --> " + e.getMessage());
					Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
					throw e;
				}


	}
			

			@Test(priority = 8)
			public void On_Add_New_User_Email(ITestContext context)
					throws InterruptedException, IOException, ParseException {

				try {
					System.out.println(" ************** On Add New User Email*****************");
					System.out.println();

					CallCentreUserManagement_MainClass.EnterValidEmail();
					
				
				} catch (AssertionError | Exception e) {
					String testName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					ExtentTestManager.getTest().log(Status.FAIL,
							"Test Failed in method: " + testName + " --> " + e.getMessage());
					Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
					throw e;
				}


	}

			@Test(priority = 9)
			public void On_Add_New_User_Phone_number(ITestContext context)
					throws InterruptedException, IOException, ParseException {

				try {
					System.out.println(" ************** On Add New User Phone number*****************");
					System.out.println();

					CallCentreUserManagement_MainClass.EnterPhoneNumber();
					
				
				} catch (AssertionError | Exception e) {
					String testName = new Object() {
					}.getClass().getEnclosingMethod().getName();
					ExtentTestManager.getTest().log(Status.FAIL,
							"Test Failed in method: " + testName + " --> " + e.getMessage());
					Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
					throw e;
				}}

				@Test(priority = 10)
				public void On_Add_New_Role(ITestContext context)
						throws InterruptedException, IOException, ParseException {

					try {
						System.out.println(" ************** On Add New role*****************");
						System.out.println();

						CallCentreUserManagement_MainClass.EnterRole();
						
					
					} catch (AssertionError | Exception e) {
						String testName = new Object() {
						}.getClass().getEnclosingMethod().getName();
						ExtentTestManager.getTest().log(Status.FAIL,
								"Test Failed in method: " + testName + " --> " + e.getMessage());
						Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
						throw e;
					}


	}
			
	
	
	
	
	

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




