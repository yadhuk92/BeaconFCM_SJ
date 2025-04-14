package CallCentre.SecurityManagement;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Utility.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import java.lang.reflect.Method;
import com.listeners.TestListener;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;

public class RoleManagement_TestClass extends Base_Class {
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
	RoleManagementPage_MainClass CallCenterRoleManagementPage_MainClass;
	//AddNewAgentPage_MainClass AddNewAgentPage_MainClass;
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
		ExcelReader = new com.Utility.ExcelReader("CallCentreRoleManagement");
		baseclass = new Base_Class();
		TestListener = new TestListener();
		// baseclass.SetUp();
		corelogin = new Login_Class();
		Login_Class.CallCenterLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		CallCenterRoleManagementPage_MainClass = new RoleManagementPage_MainClass(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Center Role Management");
		 Log.info("***********" + method.getName() + "***********");

	}

	@Test(priority = 1)
	public void Login_to_Beacon_FCM_call_centre_application(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** Login_to_Beacon_FCM_call_centre_application 1 *****************");
			System.out.println();

			CallCenterRoleManagementPage_MainClass.VerifyTheText();
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
			CallCenterRoleManagementPage_MainClass.clickSecurityManagement();
			CallCenterRoleManagementPage_MainClass.clickRoleManagement();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Next", com.Page_Repository.CallCenterRoleManagementRepo.Next);
			CallCenterRoleManagementPage_MainClass.RoleManagementHeaderIsDisplayed();
			CallCenterRoleManagementPage_MainClass.AddNewRoleIsDisplayed();
			CallCenterRoleManagementPage_MainClass.checkURL();

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
			CallCenterRoleManagementPage_MainClass.RoleManagementFieldsVerification();
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
			CallCenterRoleManagementPage_MainClass.clickAddNewRole();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Wait Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage_MainClass.FunctionalityPopUPVerification();
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
			Common.fluentWait("Role Name ", com.Page_Repository.CallCenterRoleManagementRepo.RoleName);
			CallCenterRoleManagementPage_MainClass.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage_MainClass.clickSave();
			CallCenterRoleManagementPage_MainClass.isDisplayedRoleName();

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
			driver.findElement(com.Page_Repository.CallCenterRoleManagementRepo.Cancel).click();
			ExtentTestManager.getTest().log(Status.PASS, "Cancel buttom clicked successfully");
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			CallCenterRoleManagementPage_MainClass.RoleManagementHeader();

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

			CallCenterRoleManagementPage_MainClass.clickSecurityManagement();
			CallCenterRoleManagementPage_MainClass.clickRoleManagement();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Next", com.Page_Repository.CallCenterRoleManagementRepo.Next);
			CallCenterRoleManagementPage_MainClass.clickAddNewRole();
			Common.fluentWait("Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage_MainClass.AddRoleName7();
			CallCenterRoleManagementPage_MainClass.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage_MainClass.clickSave();
			CallCenterRoleManagementPage_MainClass.IsRecordSavedSuccessfully();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			CallCenterRoleManagementPage_MainClass.RoleManagemnentHeader();

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
			CallCenterRoleManagementPage_MainClass.SearchFunction7();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			CallCenterRoleManagementPage_MainClass.ActionMenuDisplayed();

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
			CallCenterRoleManagementPage_MainClass.clickAction();
			CallCenterRoleManagementPage_MainClass.clickEdit();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			CallCenterRoleManagementPage_MainClass.RolePermissionPopUp();
			CallCenterRoleManagementPage_MainClass.SelectFunctionalitiesSelectedornot();
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
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "edit the functionality started");
			Common.fluentWait("Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage_MainClass.DeSelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Edited the functionality  deselected");
			CallCenterRoleManagementPage_MainClass.clickSave();
			CallCenterRoleManagementPage_MainClass.RecordUpdatedSuccessfully();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.ActionMenu);
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
	public void Readd_Existing_Role(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** Readd_Existing_Role 11 *****************");
			System.out.println();
//			CallCenterRoleManagementPage_MainClass.LogintoCallCenter();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
//			CallCenterRoleManagementPage_MainClass.checkIsDisplayed();
			CallCenterRoleManagementPage_MainClass.clickSecurityManagement();
			CallCenterRoleManagementPage_MainClass.clickRoleManagement();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Next", com.Page_Repository.CallCenterRoleManagementRepo.Next);
			CallCenterRoleManagementPage_MainClass.clickAddNewRole();
			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.FunctionalityCheckbox);
			CallCenterRoleManagementPage_MainClass.addRoleName7();
			CallCenterRoleManagementPage_MainClass.SelectFunctionalities();
			ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
			CallCenterRoleManagementPage_MainClass.clickSave();
			CallCenterRoleManagementPage_MainClass.LevelExist();
			CallCenterRoleManagementPage_MainClass.checkIsDisplayed();
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
//			System.out.println();
//			Thread.sleep(2000);CallCenterRoleManagementPage_MainClass.checkIsDisplayed();
////		CallCenterRoleManagementPage_MainClass.LogintoCallCenter();
//		
//			CallCenterRoleManagementPage_MainClass.clickSecurityManagement();
//			CallCenterRoleManagementPage_MainClass.clickRoleManagement();
//			CallCenterRoleManagementPage_MainClass.WaitLoader();
//			Common.fluentWait("SecurityManagement", CallCenterRoleManagementRepo.Next);
//			Thread.sleep(4000);
//			CallCenterRoleManagementPage_MainClass.clickAddNewRole();
//			CallCenterRoleManagementPage_MainClass.WaitLoader();
			Common.fluentWait("Functionality Checkbox", com.Page_Repository.CallCenterRoleManagementRepo.FunctionalityCheckbox);
			Thread.sleep(5000);
			CallCenterRoleManagementPage_MainClass.SelectFunctionalities();			
			CallCenterRoleManagementPage_MainClass.EnterRoleName();
			CallCenterRoleManagementPage_MainClass.clickSave();
			CallCenterRoleManagementPage_MainClass.PleaseSelectFunctionality();

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
		ExtentManager.getInstance().flush();
	}

	@AfterClass
	public void afterclass() {
		
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
