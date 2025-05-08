package Core.MyDesk.Dashboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
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
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.AddNewAgentMethods.AddNewAgentPage_MainClass;
import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage_MainClass;

public class CoreMyDeskDashboard_TestClass3  extends Base_Class{
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
	CallCenterRoleManagementPage_MainClass CallCenterRoleManagementPage_MainClass;
	AddNewAgentPage_MainClass AddNewAgentPage_MainClass;
	CallCenterRoleManagementRepo CallCenterRoleManagementRepo;
	MyDeskDashboardPage_MainClass MyDeskDashboardPage_MainClass;

	public static String ZoneCoreUserName;
	public static String AccountNo102;
	public static String CountFromTheGRIDBCOloanAtRisk;
	public static String tileCount;

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
		corelogin.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		MyDeskDashboardPage_MainClass = new MyDeskDashboardPage_MainClass(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core My Desk Dashboard");
		System.out.println("Method Name :" + method.getName());
	}
	@Test(priority = 61, enabled = true)
	public void Tile_Data_Accuracy_Failed_Commitments(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 76 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			String DashboardCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.FailedCommitments);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			String GridCount = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGridMonthly();
			MyDeskDashboardPage_MainClass.compareBothTheValuesEqual(DashboardCount, GridCount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 62, enabled = true)
	public void Parameter_Reset_and_Search(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 78 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategory, "AssetCategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.VerifyTheAccountSMA2();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 63, enabled = true)
	public void Grid_Updates_on_Account_Movement(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 79 *****************");
			System.out.println();
//			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();
//			MyDeskDashboardPage_MainClass.WaitLoader();
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Collection, "Collection");
//			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader(); // before new accounts
			String beforCount = MyDeskDashboardPage_MainClass.getNewAccountCount();
//			String query1 = "Select * from br_user_account_link where user_id='IBU0001528'";
//			DBUtils.executeSQLStatement(query1);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocation_1();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.AssignedOperation_1();
			// ***********error came account already assigned sol1 can we run any query to
			// un assign accounts for allocated
			// ******** sol 2 -not allocated account-selectaccount->>assigned/reassigned
			// john doe->assign-successfully->Reset->
			// allocated-->Search-->assigned/reassigned john doe->assign
			MyDeskDashboardPage_MainClass.Assignedsuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();

			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			// After new Accounts
			String afterCount = MyDeskDashboardPage_MainClass.getNewAccountCount();
			MyDeskDashboardPage_MainClass.compareBothTheValues(beforCount, afterCount);

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 64, enabled = true)
	public void Action_Button_Functionality(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 80 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.ClickonLastActionbuttonFromGrid();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Disposition, "Disposition");
			MyDeskDashboardPage_MainClass.DispositionHistoryVerification();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 65, enabled = true)
	public void Disposition_with_Future_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 81 *****************");
			System.out.println();
			String tomorrow = String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());
			MyDeskDashboardPage_MainClass.SaveDisposition(tomorrow);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 66, enabled = true)
	public void Search_Functionality_Verification(ITestContext context) throws Throwable {
//************* no monthly commitment in dashboard Plce this test case where Monthly commitment is there
		try {
			System.out.println(" ************** 82  *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategory, "AssetCategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown,
					"AllocationTypeofAccountDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocated, "Allocated");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "action");
			// then complete this flow
//			  view
//				account verify method for SMA2

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 67, enabled = true)
	public void Grid_Parameter_Display_Monthly_Commitments(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 83 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.DPDInput, "DPDInput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.DPDTo, "DPDTo");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Amount, "Amount");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Overdueinput, "Overdueinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Scheme, "Scheme");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Product, "Product");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SchemeCodeinput, "SchemeCodeinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Asset, "Asset");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SMACategoryinput, "SMACategoryinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NPACategoryinput, "NPACategoryinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Name, "Name");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.AccountNo, "AccountNo");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.CommitmentType, "CommitmentType");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 68, enabled = true)
	public void Error_Guessing_Invalid_Account_Disposition(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 84 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.FilterAllocation_1Unallocated();
			MyDeskDashboardPage_MainClass.AssignedOperation_1();
			MyDeskDashboardPage_MainClass.Assignedsuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("ActionOwner", MyDeskDashboardRepo.ActionOwner);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Save, "Save");
			MyDeskDashboardPage_MainClass.PleaseEnterSuccessfully();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 69, enabled = true)
	public void Verify_No_Accounts_Allocated(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 90 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UnassignedAccountsSMA, "UnassignedAccountsSMA");
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "Reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BCOUser, "BCOUser");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectBCODropdown, "SelectBCODropdown");
			MyDeskDashboardPage_MainClass.SelectBCOUser();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

//	@Test(priority = 70 , enabled = true)
//	public void Verify_No_Accounts_Allocated(ITestContext context) throws Throwable {
//
//		try {
//			System.out.println(" ************** 90 *****************");
//			System.out.println();
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName();
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//
//	}
	@Test(priority = 71, enabled = true)
	public void Verify_Total_Outstanding_Balance_Update(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 89 *****************");
			System.out.println();
//			done in 85
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 72, enabled = true)
	public void Allocate_Accounts_to_BCO_User(ITestContext context) throws Throwable {

		try {// accounts should be in zone user unassigned SMA
			System.out.println(" ************** 85 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.ClearDataBase();
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();// check with the 90
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			// get count from Unassigned accounts SMA before
			Common.fluentWait("UnassignedAccountsCount", MyDeskDashboardRepo.UnassignedAccountsCount);
			String BeforeCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.UnassignedAccountsCount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UnassignedAccountsSMA, "UnassignedAccountsSMA");
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "Reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategory");// ******//
																											// not//
																											// selected
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategorycall, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");// *******it has only one
																							// account
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Download", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BCOUser, "BCOUser");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectBCODropdown, "SelectBCODropdown");
			MyDeskDashboardPage_MainClass.SelectBCOUser();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			// MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SecurityManagement,
			// "SecurityManagement");
			// MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UserManagement,
			// "UserManagement");
			// MyDeskDashboardPage_MainClass.WaitLoader();
			// MyDeskDashboardPage_MainClass.GetBCOUserCredentials();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			// get count from Unassigned accounts SMA after
			Common.fluentWait("UnassignedAccountsCount", MyDeskDashboardRepo.UnassignedAccountsCount);
			String AfterCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.UnassignedAccountsCount);
			// compare both
			MyDeskDashboardPage_MainClass.compareBothTheValuesFirstGreater(BeforeCount, AfterCount);// SMA 2 has only
																									// one account
																									// therefore it will
																									// work

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 73, enabled = true)
	public void Verify_My_Accounts_Section(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 86 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.LoginBCO();
			MyDeskDashboardPage_MainClass.WaitLoader();// collection may appear
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.VerifyCount();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 74, enabled = true)
	public void Verify_New_Accounts_Section(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 87 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			String GridCount = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGrid();// this count should be one
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			// is disposition page shown
			Common.fluentWait("Disposition", MyDeskDashboardRepo.Disposition);
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Disposition, "Disposition");
			// check is it SMA2
			MyDeskDashboardPage_MainClass.VerifyTheAccountSMA2();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

//94
	@Test(priority = 75, enabled = true)
	public void Verify_Grid_Column_Verification_Data_Display(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 94 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.verifyHeadersNewAccountsBCO();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//95
	@Test(priority = 76, enabled = true)
	public void Verify_Redirect_to_Disposition_Page(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 95 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			// is disposition page shown
			Common.fluentWait("Disposition", MyDeskDashboardRepo.Disposition);
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Disposition, "Disposition");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//96	
	@Test(priority = 77, enabled = true)
	public void Verify_Disposition_Functionality_Current_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 96  *****************");
			System.out.println();
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			MyDeskDashboardPage_MainClass.SaveDispositionBCO(today);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//97	
	@Test(priority = 78, enabled = true)
	public void Verify_Disposition_Functionality_Past_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 97 *****************");// simillar to 73
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TodaysCommitment, "TodaysCommitment");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("SelectAccountNumberDisposition", MyDeskDashboardRepo.SelectAccountNumberDisposition);
			String AccountNumber = MyDeskDashboardPage_MainClass.getAccountNumberDisposition();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			String DashboardCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.FailedCommitmentsCount);

			String Yesterday = String.valueOf(LocalDate.now().minusDays(1).getDayOfMonth());
			DBUtils.executeSQLStatement_PastDate(Yesterday, AccountNumber);
//			MyDeskDashboardPage_MainClass.LoginBCO();//after refresh the Dashboard it should come
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCentreSidebutton, "CallCentreSidebutton");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AutoAllocation, "AutoAllocation");
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();

			String afterCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.FailedCommitmentsCount);
			MyDeskDashboardPage_MainClass.compareBothTheValues(DashboardCount, afterCount);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//98
	@Test(priority = 79, enabled = true)
	public void Verify_Disposition_History_Verification(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 98 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			// how to reach the action check//********* my accounts click
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Disposition", MyDeskDashboardRepo.Disposition);
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Disposition, "Disposition");
			MyDeskDashboardPage_MainClass.DispositionHistoryVerification();// *********history of interaction

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	// 93
	@Test(priority = 80, enabled = true)
	public void Verify_Boundary_Test_Maximum_Accounts_Selectable(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 93 *****************");
			System.out.println();// for all accounts from zone to bco max limit
			MyDeskDashboardPage_MainClass.ClearDataBase();
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();// check with the 90
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			// get count from Unassigned accounts SMA before
			Common.fluentWait("UnassignedAccountsCount", MyDeskDashboardRepo.UnassignedAccountsCount);
			String BeforeCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.UnassignedAccountsCount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UnassignedAccountsSMA, "UnassignedAccountsSMA");
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "Reset");
//				MyDeskDashboardPage_MainClass.WaitLoader();
//				wait for download
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BCOUser, "BCOUser");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectBCODropdown, "SelectBCODropdown");
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectBCOUser, "SelectBCOUser");
			MyDeskDashboardPage_MainClass.SelectBCOUser();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SecurityManagement, "SecurityManagement");//				
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UserManagement, "UserManagement");
//				MyDeskDashboardPage_MainClass.WaitLoader();
//				MyDeskDashboardPage_MainClass.GetBCOUserCredentials();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			// get count from Unassigned accounts SMA after
			Common.fluentWait("UnassignedAccountsCount", MyDeskDashboardRepo.UnassignedAccountsCount);
			String AfterCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.UnassignedAccountsCount);
			// After count should be zero

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//99
	@Test(priority = 81, enabled = true)
	public void Verify_Parameter_Reset_and_Search(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 99 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.LoginBCO();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			// add parameters and sear check the grid
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategory");// ******
																											// not
																											// selected
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategorycall, "SMACategory");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate1, "SMACate1");
			// add parameter as SMA1
			// search
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			// action
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			// view
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			// and check is it displyed or not
			MyDeskDashboardPage_MainClass.VerifyTheAccountSMA1();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//100
	@Test(priority = 82, enabled = true)
	public void Verify_Grid_Updates_on_Account_Movement(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 100 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			String BeforeCount = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGrid();

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			MyDeskDashboardPage_MainClass.SaveDispositionBCO(today);
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			String AfterCount = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGrid();
//			//compare both
			MyDeskDashboardPage_MainClass.compareBothTheValuesFirstGreater(BeforeCount, AfterCount);// SMA 2 has only
																									// one account
																									// therefore it will
																									// work

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//101
	@Test(priority = 83, enabled = true)
	public void Verify_Action_Button_Functionality(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 101 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			MyDeskDashboardPage_MainClass.ClickonLastActionbuttonFromGrid();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Disposition, "Disposition");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//102
	@Test(priority = 84, enabled = true)
	public void Verify_Disposition_with_Future_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 102 *****************");
			System.out.println();
			// it should be on the disposition page
			// ************* save Account number use it in 103
			AccountNo102 = MyDeskDashboardPage_MainClass.getAccountNumberDisposition();

			String tomorrow = String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());
			MyDeskDashboardPage_MainClass.SaveDisposition(tomorrow);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//103
	@Test(priority = 85, enabled = true)
	public void Verify_Search_Functionality_Verification(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 103 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategorycall, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate1");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate0");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CommitmentTypeLoan, "CommitmentTypeLoan");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CommitmentsValue, "CommitmentsValue");
			String tomorrow = String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CommitmentFrom, "CommitmentFrom");
			MyDeskDashboardPage_MainClass.SelectDate(tomorrow);

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CommitmentTo, "CommitmentTo");
			MyDeskDashboardPage_MainClass.SelectDate(tomorrow);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");

			String AccountNo103 = MyDeskDashboardPage_MainClass.getAccountNumber();

			MyDeskDashboardPage_MainClass.checkBothThestringsAreEqual(AccountNo102, AccountNo103);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//104	
	@Test(priority = 86, enabled = true)
	public void Verify_Grid_Parameter_Display_Monthly_Commitments(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 104 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.DPDInput, "DPDInput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.DPDTo, "DPDTo");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Amount, "Amount");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Overdueinput, "Overdueinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Scheme, "Scheme");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Product, "Product");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SchemeCodeinput, "SchemeCodeinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Asset, "Asset");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SMACategoryinput, "SMACategoryinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NPACategoryinput, "NPACategoryinput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Name, "Name");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.AccountNo, "AccountNo");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.CommitmentType, "CommitmentType");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 87, enabled = true)
	public void Verify_Loan_at_Risk_Tiles_Visibility(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 105 *****************");
			System.out.println();
//			MyDeskDashboardPage_MainClass.LoginBCO();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			Common.fluentWait("NewAccounts", MyDeskDashboardRepo.NewAccounts);
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRisk, "LoanAtRisk");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalMTNPA, "TotalMTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalFTNPA, "TotalFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalNPA, "TotalNPA");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 88, enabled = true)
	public void Verify_Popup_for_SMA_and_NPA_Tiles(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 106 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Search", MyDeskDashboardRepo.Search);
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRiskPOPUP, "LoanAtRiskPOPUP");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TotalMTNPA, "TotalMTNPA");
			MyDeskDashboardPage_MainClass.isNOTDisplayed(MyDeskDashboardRepo.LoanAtRiskPOPUP, "LoanAtRiskPOPUP");

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TotalFTNPA, "TotalFTNPA");
			MyDeskDashboardPage_MainClass.isNOTDisplayed(MyDeskDashboardRepo.LoanAtRiskPOPUP, "LoanAtRiskPOPUP");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TotalNPA, "TotalNPA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRiskPOPUP, "LoanAtRiskPOPUP");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 89, enabled = true)
	public void Verify_Parameters_Displayed_in_SMA_Tile_Popup(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 107 *****************");
			System.out.println();

			tileCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.LoanAtRiskAccountsCount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Search", MyDeskDashboardRepo.Search);
			Common.fluentWait("LoanAtRiskPOPUP", MyDeskDashboardRepo.LoanAtRiskPOPUP);

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRiskPOPUPInput, "LoanAtRiskPOPUPInput");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.ZoneDisplay, "Zone");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.RegionLoanDisplay, "RegionLoan");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.BranchLoanDisplay, "BranchLoan");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.GroupDisplay, "Group");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 90, enabled = true)
	public void Verify_Account_Count_Based_on_Selected_Parameters(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 108 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.RegionDropDown, "RegionDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectRegion, "SelectRegion");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.ZoneDropDown, "ZoneDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectRegion, "SelectRegion");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.GroupByDropDown, "GroupByDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectRegion, "SelectRegion");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			CountFromTheGRIDBCOloanAtRisk = MyDeskDashboardPage_MainClass.countFromBCOLoanAtRiskGrid();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 91, enabled = true)
	public void Verify_Count_Consistency_Between_Tile_and_Grid(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 109 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.compareBothTheValuesEqual(CountFromTheGRIDBCOloanAtRisk, tileCount);

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 92, enabled = true)
	public void Verify_Excel_Download_for_Loan_Summary(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 110 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadSummary, "DownloadSummary");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 93, enabled = true)
	public void Verify_Data_in_Excel_File_Matches_Grid_Data(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 111 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.compareDataWithExcel();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 94, enabled = true)
	public void Verify_Excel_Download_for_Zone_Based_Filter(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 114 *****************");
			System.out.println();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 95, enabled = true)
	public void Verify_Excel_File_Format_and_Content(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 112 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadOffline, "DownloadOffline");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			int RoWCountFromExcelDownloadbutton = MyDeskDashboardPage_MainClass.compareDataWithExcelDownload();
			String countFromExcel = Integer.toString(RoWCountFromExcelDownloadbutton);
			MyDeskDashboardPage_MainClass.compareBothTheValuesEqual(countFromExcel, tileCount);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 96, enabled = true)
	public void Verify_Data_Format_in_Excel_File(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 113 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.checkExcelDataFormats();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 97, enabled = true)
	public void Verify_Data_Consistency_and_Format_for_Downloaded_Excel(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 115 *****************");
			System.out.println();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 98, enabled = true)
	public void Error_Handling_for_Missing_Search_Parameters(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 118 *****************");
			System.out.println();
			// screnn should be at loan at risk sma pop up
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.LoanAtRiskReset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.EnterallfieldsinLoanAtRisk();

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


