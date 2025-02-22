package com.CollectionAgency.AgentManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.CollectionAgency_AddNewAgentAndAgentList;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AddNewAgentAndAgentList extends Base_Class {
	Log log;
	Base_Class Base_Class;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	Login_Class AllLoginCases;
	//AddNewAgentPage AddNewAgentPage;
	AddNewAgentAndAgentList_MainClass AddNewAgentPage;



	@BeforeSuite
	public void reference() {
		log = new Log();
		TestListener = new TestListener();
		Base_Class = new Base_Class();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeTest
	public void SetUp() {
		ExcelReader = new com.Utility.ExcelReader("AgencyAddNewAgent");
		baseclass = new Base_Class();
		TestListener = new TestListener();
		// baseclass.SetUp();
		corelogin = new Login_Class();
		try {
			corelogin.CollectionAgencyLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		AddNewAgentPage = new AddNewAgentAndAgentList_MainClass(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Agency Add New Agent master");
		System.out.println("Before method in disposition master");
	}

	@Test(priority = 1)
	public void Verify_AgentList_Navigation(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Verify_AgentList_Navigation 1   ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.WaitNext();
			Thread.sleep(4000);
			AddNewAgentPage.AgentManagementDisplay();

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
	public void Verify_Fields_Buttons_AgentListPage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
//			AddNewAgentPage.WaitLoader();
			System.out.println("********** VerifythefieldsandbuttonsinAgentlistpage2   ***********");
			System.out.println();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.AgentListPageIsDisplayed();

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
	public void Verify_AddNewAgent_Navigation(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Addnewagentpagenavigation3   ***********");
			System.out.println();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.AddNewAgentIsDisplayed();

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
	public void Verify_Fields_Buttons_AddNewAgentPage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Verify_Fields_Buttons_AddNewAgentPage 4   ***********");
			System.out.println();
			AddNewAgentPage.AddNewAgentPageFieldsVerification();

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
	public void Verify_DateOfJoining_Pageload(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** VerifyDateofJoiningfieldonpageload 5  ***********");
			System.out.println();
			Common.fluentWait("Agent Management ", CollectionAgency_AddNewAgentAndAgentList.AgentManagement);
			AddNewAgentPage.DateOfJoiningField();

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
	public void Verify_AddNewAgent_CloseButton(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Addnewagentpageclosebuttonfunctionality 6  ***********");
			System.out.println();
			AddNewAgentPage.clickClose();
			AddNewAgentPage.WaitLoader();
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
	public void Verify_Submit_AddNewAgent_WithoutMandatoryFields(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitaddnewagentwithoutMandatoryFields 7  ***********");
			System.out.println();
			Common.fluentWait("Agent Management", CollectionAgency_AddNewAgentAndAgentList.AgentManagement);
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.AddNewAgentIsDisplayed();
			AddNewAgentPage.clickSubmit();
			AddNewAgentPage.ErrorMandatoryField();

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
	public void Verify_InvalidPhoneNumber_Format(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** InvalidPhoneNumberFormat 8  ***********");
			System.out.println();
			AddNewAgentPage.InvalidPhoneNumber();
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
	public void Verify_Submit_ValidNewAgentDetails(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitValidNewAgentDetails 9 ***********");
			System.out.println();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.ClearAddNewAgent();
			AddNewAgentPage.CreateNewAgent9();
			AddNewAgentPage.isUserCreatedSuccessfully();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 10)
	public void Verify_Submit_ValidNewAgentDetails_ExistingAgentCode(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitValidNewAgentDetailswithexistingAgentcode 10 ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.Fillthedetails10();
			Common.fluentWait("AlreadyUserExist", CollectionAgency_AddNewAgentAndAgentList.AlreadyUserExist);
			AddNewAgentPage.AlreadyUserExistDisplayed();
			AddNewAgentPage.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	
	@Test(priority = 17)
	public void Verify_Submit_PastDateOfJoining(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitWithPastDateofJoining 11  ***********");
			System.out.println();
			Thread.sleep(4000);
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.ClearAddNewAgent();
			AddNewAgentPage.AddNewAgentWithPastDateofJoining();			
			AddNewAgentPage.clickSubmit();
			AddNewAgentPage.isUserCreatedSuccessfully();		
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	
	@Test(priority = 18)
	public void Verify_Submit_FutureDateOfJoining(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** SubmitWithFutureDateofJoining 18  ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.ClearAddNewAgent();
			AddNewAgentPage.AddNewAgentWithFutureDate();			
			AddNewAgentPage.clickSubmit();
			AddNewAgentPage.isUserCreatedSuccessfully();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 19)
	public void Verify_SearchAgent_ValidUsername(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SearchAgentbyValidUsername 19 ***********");
			System.out.println();
			Common.fluentWait("AgentManagement", CollectionAgency_AddNewAgentAndAgentList.AgentManagement);
			
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.Search1();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.GetTheDetailsforValidUser();
			
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	
	@Test(priority = 20)
	public void Verify_SearchAgent_InvalidUsername(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** SearchAgentbyInvalidUsername 20  ***********");
			System.out.println();
			Common.fluentWait("Agent Management", CollectionAgency_AddNewAgentAndAgentList.AgentManagement);
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.searchInvalidUser();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.IsNoRecordToDisplay();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 21)
	public void Verify_ToggleAgent_ActiveToDeactive(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
	
			System.out.println("**********  ToggleAgentActiveStatusintodeactivate 21 ***********");
			System.out.println();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.SearchUser();
			AddNewAgentPage.WaitLoader();	
			AddNewAgentPage.clickActionClickAndAvtivateDectivate();
			AddNewAgentPage.SatusChanged();
	
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 22)
	public void Verify_SearchConfirm_UserDeactivated(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  Searchandconfirmtheuserisdeactivatedornot 22 ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();	
			AddNewAgentPage.SearchUserWithIsActive();	
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.InactiveStatus();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 23)
	public void Verify_ToggleAgent_InactiveToActive(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** ToggleAgentInactiveStatusintoactive 23  ***********");
			System.out.println();
			AddNewAgentPage.clickActionClickAndAvtivateDectivate();
			AddNewAgentPage.SatusChanged();
	
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 30)
	public void Verify_ResetPassword_Functionality(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** ResetPasswordFunctionality 30  ***********");
			System.out.println();Thread.sleep(10000);
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAddNewAgent();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(4000);
			AddNewAgentPage.creatNewAgent();			
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.isUserCreatedSuccessfully();
			Common.fluentWait("Action", CollectionAgency_AddNewAgentAndAgentList.Action);
			AddNewAgentPage.clickAction();
			AddNewAgentPage.clickResetPassword();
			AddNewAgentPage.PasswordReserStatus();
			
			
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	
	@Test(priority = 31)
	public void Verify_Fields_Buttons_EditOption_AgentListPage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  VerifythefieldsandbuttonsinEditoptioninagentlistpage 31 ***********");
			System.out.println();
			Common.fluentWait("Action", CollectionAgency_AddNewAgentAndAgentList.Action);	
			AddNewAgentPage.clickAction();
			AddNewAgentPage.clickEdit();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.EditAgentVerification();
			AddNewAgentPage.GetAttributeAndVerify();
			
	
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@Test(priority = 32)
	public void Verify_EditAgentName_Submit(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  Editagentnameandsubmit 32  ***********");
			System.out.println();
			AddNewAgentPage.clearAndSendUsername();
			AddNewAgentPage.clickUpdate();
//			check old code if failed
			AddNewAgentPage.isUserUpdated();
			AddNewAgentPage.WaitLoader();
			AddNewAgentPage.verifyUpdatedUser();
			
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}
	
	
	@Test(priority = 33)
	public void Verify_Checking_EditedUserDetails(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			AddNewAgentPage.WaitLoader();
			System.out.println("**********  Editagentnameandsubmit 33  ***********");
			System.out.println();
			AddNewAgentPage.clickAgentManagement();
			AddNewAgentPage.clickAgentList();
			AddNewAgentPage.verifyUpdatedUser();
	
	
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		// Check if the test case failed
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			try {
				// Take the screenshot for the failed test
				File image = screenShot.takeScreenShot(methodName, "Failure");

				ExtentTestManager.getTest().fail("Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());
			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	@AfterClass
	public void afterclass() {
		ExtentManager.getInstance().flush();
		// Close the browser
		if (driver != null) {
			driver.quit();
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

}
