package Core.MyDesk.Dashboard;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

public class CoreMyDeskDashboard_TestClass extends Base_Class {
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
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
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
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Download, "Download");
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
	// should start from the 31

	@Test(priority = 31, enabled = true)
	public void SMA_Tile_Default_Display_in_the_Branch_User_Login(ITestContext context) throws Throwable {
////46
		try {
			System.out.println(" ************** 46 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.ClearDataBase();
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage_MainClass.clickMyDesk();
//		MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickUnassignedAccountsSMA();
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.getTotalAccount();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AllocateToDropdown, "AllocateToDropdown");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.BranchUser, "BranchUser");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Allocate, "Allocate");
			MyDeskDashboardPage_MainClass.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.CheckCountWithAllocatedCount("Branch Attention Required Accounts ", "SMA");
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();
			Thread.sleep(10000);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Collection, "Collection");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocation();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.FilterAllocation();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DropDownDisposition, "DropDownDisposition");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.VerifyTheAccount();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	// 47

	@Test(priority = 32, enabled = true)
	public void Parameter_Activation(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 47 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.DPDInput, "DPDInput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.DPDTo, "DPDTo");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Amount, "Amount");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Overdueinput, "Overdueinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Scheme, "Scheme");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Product, "Product");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.SchemeCodeinput, "SchemeCodeinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Asset, "Asset");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.SMACategoryinput, "SMACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.NPACategoryinput, "NPACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.AccountNo, "AccountNo");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.AllocationTypes, "AllocationTypes");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.AssignedTo, "AssignedTo");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(2000);
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.DPDInput, "DPDInput");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.DPDTo, "DPDTo");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.Amount, "Amount");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.Overdueinput, "Overdueinput");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.Scheme, "Scheme");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.Product, "Product");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.SchemeCodeinput, "SchemeCodeinput");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.Asset, "Asset");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.SMACategoryinput, "SMACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.NPACategoryinput, "NPACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.AccountNo, "AccountNo");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.AllocationTypes, "AllocationTypes");
			MyDeskDashboardPage_MainClass.IsElementEnabled(MyDeskDashboardRepo.AssignedTo, "AssignedTo");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 33, enabled = true)
	public void Individually_Allocating_Accounts_to_Branch_User(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 48 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterUnallocatedAccountsDown();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.ReadTableandVerify();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}
	// ############### the new accounts has account 2

	@Test(priority = 34, enabled = true)
	public void Disposition_Page_Access_Control(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 49 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocation();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DropDownDisposition, "DropDownDisposition");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("NextActionOwner", MyDeskDashboardRepo.NextActionOwner);
			MyDeskDashboardPage_MainClass.VerifyTheAccount();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 35, enabled = true)
	public void Disposition_Page_Access_Denied(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 50 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterNotAllocation();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DropDownDisposition, "DropDownDisposition");
			MyDeskDashboardPage_MainClass.AccessDeniedError();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 36, enabled = true)
	public void Account_Allocation_to_BCO(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 51 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocation_1Unallocated();
			MyDeskDashboardPage_MainClass.AssignedOperation_1();
			MyDeskDashboardPage_MainClass.Assignedsuccessfully();// ****** here it is coming "Some of the accounts were
																	// already allocated." for second account
																	// (//tbody//*[contains(@class,'box')])[5]
			MyDeskDashboardPage_MainClass.WaitLoader();
			// ********** how to verify? only assigned succesfully verified

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 37, enabled = true)
	public void Grid_Column_Display(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 52 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.verifyHeaders();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 38, enabled = true)
	public void Reassign_Accounts(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 53 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocationBCO();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.ReAssignedOperation();
			MyDeskDashboardPage_MainClass.Assignedsuccessfully();
			MyDeskDashboardPage_MainClass.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 39, enabled = true)
	public void BVA_Validations_for_DPD_From_Field(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 54 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.BVAvalidations();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 40, enabled = true)
	public void ECP_Validations_for_DPD_To_Field(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 55 *****************");
			System.out.println();

			MyDeskDashboardPage_MainClass.ECP_Validations();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 41, enabled = true)
	public void Error_Guessing_Empty_Fields(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 56 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.AllocationTypesShouldNotBeEmpty();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 42, enabled = true)
	public void Error_Guessing_Invalid_Format(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 57 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			String value = MyDeskDashboardPage_MainClass.generateAlphanumericUUID();
			driver.findElement(MyDeskDashboardRepo.secondInput).sendKeys(value);
			// *************it is not accepting but how to show
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 43, enabled = true)
	public void SMA_Category_Filter(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 59 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategory, "AssetCategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");
			MyDeskDashboardPage_MainClass.FilterAllocation_1Unallocated();
			MyDeskDashboardPage_MainClass.AssignedOperation_1();
			MyDeskDashboardPage_MainClass.Assignedsuccessfully();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.AssetCategory, "AssetCategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACategory, "SMACategory");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMACate2, "SMACate2");
			MyDeskDashboardPage_MainClass.FilterAllocation();
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.DropDownDisposition, "DropDownDisposition");
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

	@Test(priority = 44, enabled = true)
	public void Allocation_Types_Filter(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 60 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Thread.sleep(1000);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterAllocation();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.VerifyTheGrid_Allocated();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.reset, "reset");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.FilterNotAllocation();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.VerifyTheGrid_NotAllocated();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 45, enabled = true)
	public void Correct_Display_of_Total_Due(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 61 *****************");
			System.out.println();
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.verifyOutstandingInGrid();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 46, enabled = true)
	public void Outstanding_Amount_Validation(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 62 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.verifyOutstandingInGrid();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 47, enabled = true)
	public void Edge_Case_Maximum_Accounts(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 63 *****************");
			System.out.println();
			// ########## the screen branch sma search
			// all accounts but how to verify the performance
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 48, enabled = true)
	public void My_Accounts_Tiles_Displayed(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 64 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SMA, "SMA");
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Accounts, "Accounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.MyAccounts, "MyAccounts");
			MyDeskDashboardPage_MainClass.isDisplayed(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 49, enabled = true)
	public void Verify_Allocated_Accounts_to_Branch_User_from_Branch_Attention_Required_Tile(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 65 *****************");
			System.out.println();
//			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();MyDeskDashboardPage_MainClass.WaitLoader();
//			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Collection, "Collection");MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
			// before new accounts
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

	@Test(priority = 50, enabled = true)
	public void Verify_My_Accounts_Tile_Showing_All_Allocated_Accounts_to_Specific_User(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 66 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.WaitLoader();
// check the grid value number of rows and dash board value	
			String countFromDashboard = MyDeskDashboardPage_MainClass.getNewAccountCount();
			// go inside the my account new account
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.WaitLoader();
			String countFromGrid = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGrid();
			MyDeskDashboardPage_MainClass.compareBothTheValuesEqual(countFromDashboard, countFromGrid);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 51, enabled = true)
	public void Disposition_History_Verification(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 67 *****************");
			System.out.println();// **************** here disposition is showing but this test should be executed
									// after the create disposition i.e 72
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
			Common.fluentWait("Disposition", MyDeskDashboardRepo.Disposition);
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

	@Test(priority = 52, enabled = true)
	public void New_Accounts_Tile_Default_Parameter_State(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 68 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.NewAccounts, "NewAccounts");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.DPDInput, "DPDInput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.DPDTo, "DPDTo");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Amount, "Amount");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Overdueinput, "Overdueinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Scheme, "Scheme");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Product, "Product");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.SchemeCodeinput, "SchemeCodeinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Asset, "Asset");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.SMACategoryinput, "SMACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.NPACategoryinput, "NPACategoryinput");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.Name, "Name");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.AccountNo, "AccountNo");
			MyDeskDashboardPage_MainClass.IsElementDisabled(MyDeskDashboardRepo.CommitmentType, "CommitmentType");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 53, enabled = true)
	public void Grid_Parameter_Verification_New_Accounts_Filter(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 69 *****************");
			System.out.println();
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

	@Test(priority = 54, enabled = true)
	public void Grid_Column_Verification_Data_Display(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 70 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.verifyHeadersNewAccounts();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 55, enabled = true)
	public void Redirect_to_Disposition_Page(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 71 *****************");
			System.out.println();
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Action, "Action");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.View, "View");
			MyDeskDashboardPage_MainClass.WaitLoader();
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

	@Test(priority = 56, enabled = true)
	public void Disposition_Functionality_Current_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 72 *****************");
			System.out.println();
			// ########## it is coming on the disposition page
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			MyDeskDashboardPage_MainClass.SaveDisposition(today);

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 57, enabled = true)
	public void Disposition_History_Verification_2(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 77 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TodaysCommitment, "TodaysCommitment");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.SelectAccountNumber, "SelectAccountNumber");
			MyDeskDashboardPage_MainClass.DispositionHistoryVerification();
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

	@Test(priority = 58, enabled = true)
	public void Disposition_Functionality_Past_Date(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 73 *****************");
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
			MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();
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

//######## not considered the final screen of the 73 test case
	@Test(priority = 59, enabled = true)
	public void Tile_Data_Accuracy_Daily_Commitments(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 74 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			String DashboardCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.TodaysCommitment);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.TodaysCommitment, "TodaysCommitment");
			Common.fluentWait("Action", MyDeskDashboardRepo.Action);
			String GridCount = MyDeskDashboardPage_MainClass.getNewAccountCount_FromGrid();
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

	@Test(priority = 60, enabled = true)
	public void Tile_Data_Accuracy_Monthly_Commitments(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 75 *****************");
			System.out.println();
			MyDeskDashboardPage_MainClass.WaitLoader();
			MyDeskDashboardPage_MainClass.clickMyDesk();
			MyDeskDashboardPage_MainClass.clickDashboard();
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Commitments, "Commitments");
			String DashboardCount = MyDeskDashboardPage_MainClass
					.getNewAccountCount_new(MyDeskDashboardRepo.MonthlyCommitment);
			MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.MonthlyCommitment, "MonthlyCommitment");
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
