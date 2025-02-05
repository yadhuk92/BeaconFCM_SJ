package Core.CallCenterRoleManagement;

import java.io.File;


import Core.AddNewAgentMethods.AddNewAgentPage;
import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
//import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
import java.lang.reflect.Method;
import com.listeners.TestListener;

import Core.Disposition.DispositionMasterPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;

public class CallCenterRoleManagementTest extends Base_Class {
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
	CallCenterRoleManagementPage CallCenterRoleManagementPage;
	AddNewAgentPage AddNewAgentPage;
	CallCenterRoleManagementRepo CallCenterRoleManagementRepo;

	@BeforeSuite
	public void reference() {
		log = new Log();
		TestListener = new TestListener();
		Base_Class = new Base_Class();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeTest
	public void SetUp() throws Exception {
		ExcelReader = new com.Utility.ExcelReader("CallCenter");
		baseclass = new Base_Class();
		TestListener = new TestListener();
		// baseclass.SetUp();
		corelogin = new Login_Class();
		corelogin.CallCenterLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		CallCenterRoleManagementPage = new CallCenterRoleManagementPage(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Center Role Management Test");

	}

	@Test(priority = 1)
	public void Login_to_Beacon_FCM_call_centre_application(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Login_to_Beacon_FCM_call_centre_application 1 *****************");
			System.out.println();
			CallCenterRoleManagementPage.IsCallCenterDisplayed();
			CallCenterRoleManagementPage.VerifyTheText();
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
	public void Navigation_to_Role_Management(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Navigation_to_Role_Management 2 *****************");
			System.out.println();
			CallCenterRoleManagementPage.clickSecurityManagement();
			CallCenterRoleManagementPage.clickRoleManagement();
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.RoleManagementHeaderIsDisplayed();
			CallCenterRoleManagementPage.AddNewRoleIsDisplayed();
			CallCenterRoleManagementPage.checkURL();
			

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
	public void Verify_Role_Management_Fields_and_Buttons(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Verify_Role_Management_Fields_and_Buttons 3 *****************");
			System.out.println();
			CallCenterRoleManagementPage.RoleManagementFieldsVerification();
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
	public void Add_New_Role_Open_Permission_Popup(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Add_New_Role_Open_Permission_Popup 4 *****************");
			System.out.println();
			CallCenterRoleManagementPage.clickAddNewRole();
			CallCenterRoleManagementPage.WaitLoader();
			Common.fluentWait("Wait Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage.FunctionalityPopUPVerification();
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
	public void Save_Role_Without_Name(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Save_Role_Without_Name 5 *****************");
			System.out.println();
			Common.fluentWait("Role Name ", CallCenterRoleManagementRepo.RoleName);
			CallCenterRoleManagementPage.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage.clickSave();
			CallCenterRoleManagementPage.isDisplayedRoleName();

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
	public void Cancel_New_Role_Addition(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Cancel_New_Role_Addition 6 *****************");
			System.out.println();
			driver.findElement(CallCenterRoleManagementRepo.Cancel).click();
			ExtentTestManager.getTest().log(Status.PASS, "Cancel buttom clicked successfully");
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.RoleManagementHeader();

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
	public void Add_Role_with_Valid_Data(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Add_Role_with_Valid_Data 7 *****************");
			System.out.println();

			CallCenterRoleManagementPage.clickSecurityManagement();
			CallCenterRoleManagementPage.clickRoleManagement();
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.clickAddNewRole();
			Common.fluentWait("Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage.AddRoleName7();
			CallCenterRoleManagementPage.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage.clickSave();
			CallCenterRoleManagementPage.IsRecordSavedSuccessfully();
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.RoleManagemnentHeader();

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
	public void Search_for_Existing_Role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Search_for_Existing_Role 8 *****************");
			System.out.println();
			CallCenterRoleManagementPage.SearchFunction7();
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.ActionMenuDisplayed();

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
	public void Edit_Existing_Role_via_Action_Menu(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Edit_Existing_Role_via_Action_Menu 9  *****************");
			System.out.println();
			CallCenterRoleManagementPage.clickAction();
			CallCenterRoleManagementPage.clickEdit();
			CallCenterRoleManagementPage.WaitLoader();
			CallCenterRoleManagementPage.RolePermissionPopUp();
			CallCenterRoleManagementPage.SelectFunctionalitiesSelectedornot();
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
	public void Update_Role_Functionalities(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Update_Role_Functionalities 10 *****************");
			System.out.println();
			CallCenterRoleManagementPage.WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "edit the functionality started");
			Common.fluentWait("Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage.DeSelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Edited the functionality  deselected");
			CallCenterRoleManagementPage.clickSave();
			CallCenterRoleManagementPage.RecordUpdatedSuccessfully();
			CallCenterRoleManagementPage.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 11)
	public void Readd_Existing_Role(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Readd_Existing_Role 11 *****************");
			System.out.println();
			CallCenterRoleManagementPage.checkIsDisplayed();
			CallCenterRoleManagementPage.clickSecurityManagement();
			CallCenterRoleManagementPage.clickRoleManagement();
			CallCenterRoleManagementPage.clickAddNewRole();
			CallCenterRoleManagementPage.WaitLoader();
			Common.fluentWait("Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage.addRoleName7();
			CallCenterRoleManagementPage.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage.clickSave();
			CallCenterRoleManagementPage.LevelExist();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 12)
	public void Error_Guessing_Functionality_Not_Selected(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Error_Guessing_Functionality_Not_Selected 12 *****************");
			System.out.println();
			Thread.sleep(2000);
			CallCenterRoleManagementPage.checkIsDisplayed();
			CallCenterRoleManagementPage.clickSecurityManagement();
			CallCenterRoleManagementPage.clickRoleManagement();
			CallCenterRoleManagementPage.WaitLoader();
			Common.fluentWait("SecurityManagement", CallCenterRoleManagementRepo.Next);
			Thread.sleep(4000);
			CallCenterRoleManagementPage.clickAddNewRole();
			CallCenterRoleManagementPage.WaitLoader();
			Common.fluentWait("Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
			Thread.sleep(5000);
			CallCenterRoleManagementPage.EnterRoleName();
			CallCenterRoleManagementPage.clickSave();
			CallCenterRoleManagementPage.PleaseSelectFunctionality();

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
