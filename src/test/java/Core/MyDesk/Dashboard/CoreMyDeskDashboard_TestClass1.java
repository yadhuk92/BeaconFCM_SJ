package Core.MyDesk.Dashboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
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

public class CoreMyDeskDashboard_TestClass1 extends Base_Class {
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

	@Test(priority = 1, enabled = true)
	public void Creating_a_core_Zone_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws Throwable {

		try {

			String query = "DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query);
			String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query1);
			String query2 = "DELETE FROM mst_col_agency_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query2);
			System.out.println(" ************** 3 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.VerifyConfigZone();
			MyDeskDashboardPage_MainClass.SQLQueryZone();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 2, enabled = true)
	public void Creating_a_core_Region_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 4 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.VerifyConfigRegion();
			MyDeskDashboardPage_MainClass.SQLQueryRegion();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 3, enabled = true)
	public void Creating_a_core_branch_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 5 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.VerifyConfigBranch();
			MyDeskDashboardPage_MainClass.SQLQueryBranch();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 4, enabled = true)
	public void Configuring_the_core_branch_user_as_BCO_user_through_backend_for_listing(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 6 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.VerifyConfigBCOUser();
			MyDeskDashboardPage_MainClass.SQL_BCOUSer();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5, enabled = true)
	public void Configurating_my_desk_to_core_Branch_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 7 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.SQL_QueryMyDesktoCoreUser();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 6, enabled = true)
	public void Configurating_my_desk_to_core_Region_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 8 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.SQL_QueryMyDesktoRegionUser();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7, enabled = true)
	public void Configurating_my_desk_to_core_Zone_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 9 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.SQL_QueryMyDesktoZoneUser();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 8, enabled = true)
	public void Verify_Tiles_on_My_Desk(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 10 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickCollection();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,
					"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MTNPA, "MTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FTNPA, "FTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NPA, "NPA");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Accounts, "Accounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MyAccounts, "MyAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NewAccounts, "NewAccounts");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SupportRequests, "SupportRequests");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnAttended, "UnAttended");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.AllocatedToUsers, "AllocatedToUsers");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Allocations, "Allocations");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Resolved, "Resolved");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRisk, "LoanAtRisk");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalMTNPA, "TotalMTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalFTNPA, "TotalFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalNPA, "TotalNPA");
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 9, enabled = true)
	public void Verify_Tiles_on_My_Desk_RegionUser(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 11 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch_Region();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickCollection();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,
					"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MTNPA, "MTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FTNPA, "FTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NPA, "NPA");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Accounts, "Accounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MyAccounts, "MyAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NewAccounts, "NewAccounts");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SupportRequests, "SupportRequests");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnAttended, "UnAttended");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.AllocatedToUsers, "AllocatedToUsers");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Allocations, "Allocations");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Resolved, "Resolved");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRisk, "LoanAtRisk");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalMTNPA, "TotalMTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalFTNPA, "TotalFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalNPA, "TotalNPA");
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 10, enabled = true)
	public void Verify_Tiles_on_My_Desk_ZoneUser(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 12 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickCollection();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,
					"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MTNPA, "MTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FTNPA, "FTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NPA, "NPA");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.LoanAtRisk, "LoanAtRisk");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalSMA, "TotalSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalMTNPA, "TotalMTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalFTNPA, "TotalFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.TotalNPA, "TotalNPA");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Accounts, "Accounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MyAccounts, "MyAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NewAccounts, "NewAccounts");

			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccounts, "UnassignedAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsSMA,
					"UnassignedAccountsSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsFTNPA,
					"UnassignedAccountsFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsNPA,
					"UnassignedAccountsNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsMTNPA,
					"UnassignedAccountsMTNPA");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 11, enabled = true)
	public void Load_Unassigned_Accounts_Tiles(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 25 *****************");
			System.out.println();
//only all tiles and having some count
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccounts, "UnassignedAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsSMA,
					"UnassignedAccountsSMA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsFTNPA,
					"UnassignedAccountsFTNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsNPA,
					"UnassignedAccountsNPA");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsMTNPA,
					"UnassignedAccountsMTNPA");
			MyDeskDashboardPage_MainClass.CheckCount("Unassigned Accounts", "SMA");
			MyDeskDashboardPage_MainClass.CheckCount("Unassigned Accounts", "FTNPA");
			MyDeskDashboardPage_MainClass.CheckCount("Unassigned Accounts", "NPA");
			MyDeskDashboardPage_MainClass.CheckCount("Unassigned Accounts", "MTNPA");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 12, enabled = true)
	public void Click_SMA_Tile_to_View_Accounts(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 26 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();

			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();

			MyDeskDashboardPage_MainClass.VerifyUnassignedSMAFields();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 13, enabled = true)
	public void Reset_Filters_on_SMA_Tile(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 27 *****************");
			System.out.println();
//click Reset

			MyDeskDashboardPage_MainClass.clickReset();
//Processing icon
			MyDeskDashboardPage_MainClass.WaitLoader();
//check after click allocated shown or not
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TypesofAccountDropDown, "TypesofAccountDropDown");
			MyDeskDashboardPage_MainClass.isDisplayedActive(MyDeskDashboardRepo.Allocated, "Allocated");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.isDisplayedActive(MyDeskDashboardRepo.NPAValue, "NPAValue");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.VerticalDropDown, "VerticalDropDown");
			MyDeskDashboardPage_MainClass.isDisplayedActive(MyDeskDashboardRepo.LCG, "LCG");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SchemeTypeDropDown, "SchemeTypeDropDown");
			MyDeskDashboardPage_MainClass.isDisplayedActive(MyDeskDashboardRepo.CAA, "CAA");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 14, enabled = true)
	public void Download_SMA_Account_List(ITestContext context) throws Throwable {

		try {
//			MyDeskDashboardPage_MainClass.ClearDataBase();
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AgencyManagementmainmenu,
//					"AgencyManagementmainmenu");
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AddAgencySubmenu, "AddAgencySubmenu");

			System.out.println(" ************** 28 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
//			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Download, "Download");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.ExcelRead();
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 15, enabled = true)
	public void Allocate_SMA_Accounts_to_Branch(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 29 *****************");
			System.out.println();
			// scroll down
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BranchUser, "BranchUser");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
//			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.CheckCountWithAllocatedCount("Branch Attention Required Accounts ", "SMA");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 16, enabled = true)
	public void View_Allocated_List_for_SMA(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 35 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			MyDeskDashboardPage_MainClass.WaitLoader();Thread.sleep(2000);
//			Common.fluentWait("Download", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
//			MyDeskDashboardPage_MainClass.getTotalAccount();//		
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BranchAllocatedList, "BranchAllocatedList");
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 17, enabled = true)
	public void View_allocated_list_of_Branch(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 38 *****************");
			System.out.println();
//			MyDeskDashboardPage_MainClass.getTotalAccountAllocation_withClose();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
			int incount = MyDeskDashboardPage_MainClass.GetInitialdownloadCount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadinExcel, "DownloadinExcel");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			MyDeskDashboardPage_MainClass.checkNewExcelDownloadedorNot(incount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");
			String query = "DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query);

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 18, enabled = true)
	public void Allocate_SMA_Accounts_to_Call_Centre(ITestContext context) throws Throwable {

		try {

			System.out.println(" ************** 30 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Download, "Download");	MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			MyDeskDashboardPage_MainClass.ExcelReadAndStoreForCompare();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallcenterUser, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenter, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCenter, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterNew, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation();
			// here need to download excel and store it and compare it with excel data in 33
			// save account number in List
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 19, enabled = true)
	public void View_allocated_list_of_call_centre(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 36 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
			int incount = MyDeskDashboardPage_MainClass.GetInitialdownloadCount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadinExcel, "DownloadinExcel");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			MyDeskDashboardPage_MainClass.checkNewExcelDownloadedorNot(incount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 20, enabled = true)
	public void Verify_the_allocated_accounts_to_call_centre_is_available_in_the_call_centre_login(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 32 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.LogintotheCallCenter();

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCentreCallLogin, "CallCentreCallLogin");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AccountFiltration, "AccountFiltration");
			MyDeskDashboardPage_MainClass.WaitLoader();

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetDropdown, "AssetDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategorycall, "SMACategorycall");

			MyDeskDashboardPage_MainClass.WaitLoader();
			// *[contains(text(),'SMA Category')]/..//*[contains(@class,'down')]
			driver.findElement(By.xpath("//html")).click();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage_MainClass.selectSMAcategory();

			// select all SMA categories

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedTodown, "AllocatedTodown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedTodownCall, "AllocatedTodownCall");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccount();

//			String query = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
//			DBUtils.executeSQLStatement(query);
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 21, enabled = true)
	public void Verify_the_accounts_shown_in_the_grid_is_same_as_allocated_from_my_desk_by_downloading_the_excel_file(
			ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 33 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadFileDropdown, "DownloadFileDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.ListofAccounts, "ListofAccounts");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadCallCenter, "DownloadCallCenter");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			// Save account number coulmn in list from downloaded file and compare with the
			// test case 30 list
			MyDeskDashboardPage_MainClass.ExcelRead2AndStoreForCompare();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 22, enabled = true)
	public void Allocate_SMA_Accounts_to_Collection_Agency(ITestContext context) throws Throwable {

		try {// 48495051
			System.out.println(" ************** 31 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.LogintotheAgency();
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CollectionAgency, "CollectionAgency");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectAgencyDropDown, "SelectAgencyDropDown");

			MyDeskDashboardPage_MainClass.SelectAgency();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CollectionAgency2, "CollectionAgency2");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectAgencyDropDown, "SelectAgencyDropDown");

			MyDeskDashboardPage_MainClass.SelectAgency();
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation_withClose();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 23, enabled = true)
	public void View_allocated_list_of_collection_agency(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 37 *****************");
			System.out.println();
//			1.click on allocated list button
//			2.chose collection agency
//			3.chose the date and hit search button
//			4.click on the allocation id from the grid and hit download button
//			5.verify excel file is getting downloaded and open it
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CollectionAgency2, "CollectionAgency2");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectAgencyDropDown, "SelectAgencyDropDown");

			MyDeskDashboardPage_MainClass.SelectAgency();
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation_withClose();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.chkbox, "chkbox");
			int incount = MyDeskDashboardPage_MainClass.GetInitialdownloadCount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DownloadinExcel, "DownloadinExcel");
			MyDeskDashboardPage_MainClass.Filedownloadedsuccessfully();
			MyDeskDashboardPage_MainClass.checkNewExcelDownloadedorNot(incount);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 24, enabled = true)
	public void Verify_the_accounts_allocated_to_agency_is_correctly_reflected_to_the_selected_agencies_bucket_list(
			ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 34 *****************");
			System.out.println();

			// login to agency
			MyDeskDashboardPage_MainClass.LogintotheAgency();
//			1.login as allocated agency user

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AccountsAllocation, "AccountsAllocation");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AgentAccountAllocation, "AgentAccountAllocation");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetDropdown, "AssetDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategorycall, "SMACategorycall");
			MyDeskDashboardPage_MainClass.WaitLoader();
			driver.findElement(By.xpath("//html")).click();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage_MainClass.selectSMAcategory();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TypesofAccountDropDown, "TypesofAccountDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.verifyTheAccountType();

//			2.go to agent account allocation page
//			3.chose type of account as not allocated
//			4.fill the remaining criteria and hit search button 
//			5.observe the grid

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 25, enabled = true)
	public void Boundary_Value_for_Parameters(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 39 *****************");
			System.out.println();
//			1. Access any tile (SMA, NPA, etc.).
//			2. Enter boundary values for numerical parameters.
//			3. Click filter/search.

			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			String minoverdue = driver.findElement(MyDeskDashboardRepo.FirstInput).getAttribute("min");
			String maxoverdue = driver.findElement(MyDeskDashboardRepo.FirstInput).getAttribute("max");
			if (minoverdue.contains("0")) {
				ExtentTestManager.getTest().log(Status.PASS, "minimum value of overdue to EMA is '0'");
				if (maxoverdue.contains("100")) {
					ExtentTestManager.getTest().log(Status.PASS, "maximum value of overdue to EMA is '100'");
				} else
					ExtentTestManager.getTest().log(Status.FAIL, "DPDDaysOperatorShouldNotBeEmpty successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "min and max value range is not satisfied");
			String minDPD = driver.findElement(MyDeskDashboardRepo.secondInput).getAttribute("min");
			Random rand = new Random();
			int randomValue = 100 + rand.nextInt(900); // 100 to 999
			String value = String.valueOf(randomValue);
			System.out.println("Random number: " + randomValue);
			driver.findElement(MyDeskDashboardRepo.FirstInput).sendKeys(value);

			int randomNegativeValue = -(100 + rand.nextInt(900)); // -100 to -999

			// Convert the number to String
			String negativeValueStr = String.valueOf(randomNegativeValue);
			driver.findElement(MyDeskDashboardRepo.FirstInput).sendKeys(negativeValueStr);

			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
//			MyDeskDashboardPage_MainClass.DPDDaysOperatorShouldNotBeEmpty();
			driver.findElement(MyDeskDashboardRepo.secondInput).sendKeys(negativeValueStr);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 26, enabled = true)
	public void Error_Message_for_Empty_Filters(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 40 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.AssetCategoryRequired();

		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 27, enabled = true)
	public void NPA_Tile_Functionality(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 41 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.UnassignedAccountsNPA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPAValue, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPACategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.STANDARD, "STANDARD");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);// ************ not showing accounts
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallcenterUser, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenter, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCenter, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterNew, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");
			String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query1);
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 28, enabled = true)
	public void MFTNPA_Tile_Functionality(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 42 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UnassignedAccountsMTNPA, "UnassignedAccountsMTNPA");
//			MyDeskDashboardPage_MainClass.UnassignedAccountsNPA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPAValue, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPACategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.STANDARD, "STANDARD");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallcenterUser, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenter, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCenter, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterNew, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");
			String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query1);
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 29, enabled = true)
	public void PFTNPA_Tile_Functionality(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 43 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.UnassignedAccountsFTNPA, "UnassignedAccountsFTNPA");
//			MyDeskDashboardPage_MainClass.UnassignedAccountsNPA();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPAValue, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NPACategoryDropDown, "AssetCategoryDropDown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.STANDARD, "STANDARD");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallcenterUser, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenter, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedList, "AllocatedList");

			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocatedDropdown, "AllocatedDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.CallCenter, "CallCenter");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterDropdown,
					"SelectCallCenterDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectCallCenterNew, "SelectCallCenter");
			MyDeskDashboardPage_MainClass.SelectCurrentDate();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccountAllocation();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.close, "close");
			String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
			DBUtils.executeSQLStatement(query1);
		}

		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 30, enabled = true)
	public void Correct_Columns_in_Downloaded_Excel(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 45 *****************");
			System.out.println();
			// *************** need to implement
		}

		catch (AssertionError | Exception e) {
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
