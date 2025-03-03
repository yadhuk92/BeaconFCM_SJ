package Core.MyDesk.Dashboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

import Core.AddNewAgentMethods.AddNewAgentPage;
import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage;

public class CoreMyDeskDashboard_Test extends Base_Class {
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
	MyDeskDashboardPage MyDeskDashboardPage;

	public static String ZoneCoreUserName;

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
		MyDeskDashboardPage = new MyDeskDashboardPage(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Center Role Management Test");

	}

	@Test(priority = 1)
	public void Creating_a_core_Zone_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 3 *****************");
			System.out.println();
			MyDeskDashboardPage.VerifyConfigZone();
			MyDeskDashboardPage.SQLQueryZone();

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
	public void Creating_a_core_Region_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 4 *****************");
			System.out.println();
			MyDeskDashboardPage.VerifyConfigRegion();
			MyDeskDashboardPage.SQLQueryRegion();

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
	public void Creating_a_core_branch_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 5 *****************");
			System.out.println();
			MyDeskDashboardPage.VerifyConfigBranch();
			MyDeskDashboardPage.SQLQueryBranch();

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
	public void Configuring_the_core_branch_user_as_BCO_user_through_backend_for_listing(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 6 *****************");
			System.out.println();
			MyDeskDashboardPage.VerifyConfigBCOUser();
			MyDeskDashboardPage.SQL_BCOUSer();

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

	@Test(priority = 5)
	public void Configurating_my_desk_to_core_Branch_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 7 *****************");
			System.out.println();

			MyDeskDashboardPage.SQL_QueryMyDesktoCoreUser();

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

	@Test(priority = 6)
	public void Configurating_my_desk_to_core_Region_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 8 *****************");
			System.out.println();

			MyDeskDashboardPage.SQL_QueryMyDesktoRegionUser();

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

	@Test(priority = 7)
	public void Configurating_my_desk_to_core_Zone_user(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 9 *****************");
			System.out.println();

			MyDeskDashboardPage.SQL_QueryMyDesktoZoneUser();

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
	@Test(priority = 8)
	public void Verify_Tiles_on_My_Desk(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 10 *****************");
			System.out.println();
			
			MyDeskDashboardPage.LogintotheNewlyCreatedUserBranch();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickCollection();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickMyDesk();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.SMA,"SMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MTNPA,"MTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FTNPA,"FTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NPA,"NPA");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Accounts,"Accounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Commitments,"Commitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FailedCommitments,"FailedCommitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MyAccounts,"MyAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NewAccounts,"NewAccounts");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.SupportRequests,"SupportRequests");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnAttended,"UnAttended");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.AllocatedToUsers,"AllocatedToUsers");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Allocations,"Allocations");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Resolved,"Resolved");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.LoanAtRisk,"LoanAtRisk");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalSMA,"TotalSMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalMTNPA,"TotalMTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalFTNPA,"TotalFTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalNPA,"TotalNPA");
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
	@Test(priority = 9)
	public void Verify_Tiles_on_My_Desk_RegionUser(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 11 *****************");
			System.out.println();

			MyDeskDashboardPage.LogintotheNewlyCreatedUserBranch_Region();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickCollection();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickMyDesk();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.SMA,"SMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MTNPA,"MTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FTNPA,"FTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NPA,"NPA");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Accounts,"Accounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Commitments,"Commitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FailedCommitments,"FailedCommitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MyAccounts,"MyAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NewAccounts,"NewAccounts");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.SupportRequests,"SupportRequests");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnAttended,"UnAttended");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.AllocatedToUsers,"AllocatedToUsers");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Allocations,"Allocations");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Resolved,"Resolved");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.LoanAtRisk,"LoanAtRisk");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalSMA,"TotalSMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalMTNPA,"TotalMTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalFTNPA,"TotalFTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalNPA,"TotalNPA");
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
	@Test(priority = 10)
	public void Verify_Tiles_on_My_Desk_ZoneUser(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 12 *****************");
			System.out.println();

			MyDeskDashboardPage.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickCollection();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickMyDesk();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.BranchAttentionRequiredAccounts,"BranchAttentionRequiredAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.SMA,"SMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MTNPA,"MTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FTNPA,"FTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NPA,"NPA");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.LoanAtRisk,"LoanAtRisk");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalSMA,"TotalSMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalMTNPA,"TotalMTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalFTNPA,"TotalFTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.TotalNPA,"TotalNPA");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Accounts,"Accounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.Commitments,"Commitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.FailedCommitments,"FailedCommitments");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.MyAccounts,"MyAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.NewAccounts,"NewAccounts");
			
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccounts,"UnassignedAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsSMA,"UnassignedAccountsSMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsFTNPA,"UnassignedAccountsFTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsNPA,"UnassignedAccountsNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsMTNPA,"UnassignedAccountsMTNPA");
			
			
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
	
	@Test(priority = 11)
	public void Load_Unassigned_Accounts_Tiles(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 25 *****************");
			System.out.println();
//only all tiles and having some count
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccounts,"UnassignedAccounts");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsSMA,"UnassignedAccountsSMA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsFTNPA,"UnassignedAccountsFTNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsNPA,"UnassignedAccountsNPA");
			MyDeskDashboardPage.isDisplayed(MyDeskDashboardRepo.UnassignedAccountsMTNPA,"UnassignedAccountsMTNPA");
			MyDeskDashboardPage.CheckCount("Unassigned Accounts","SMA");
			MyDeskDashboardPage.CheckCount("Unassigned Accounts","FTNPA");
			MyDeskDashboardPage.CheckCount("Unassigned Accounts","NPA");
			MyDeskDashboardPage.CheckCount("Unassigned Accounts","MTNPA");
			
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
	
	@Test(priority = 12)
	public void Click_SMA_Tile_to_View_Accounts(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 26 *****************");
			System.out.println();
			MyDeskDashboardPage.clickUnassignedAccountsSMA();

			MyDeskDashboardPage.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);MyDeskDashboardPage.WaitLoader();

			MyDeskDashboardPage.VerifyUnassignedSMAFields();
			
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
	@Test(priority = 13)
	public void Reset_Filters_on_SMA_Tile(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 27 *****************");
			System.out.println();
//click Reset
			
			MyDeskDashboardPage.clickReset();
//Processing icon
			MyDeskDashboardPage.WaitLoader();
//check after click allocated shown or not
			MyDeskDashboardPage.click(MyDeskDashboardRepo.TypesofAccountDropDown,"TypesofAccountDropDown");			
			MyDeskDashboardPage.isDisplayedActive(MyDeskDashboardRepo.Allocated,"Allocated");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AssetCategoryDropDown,"AssetCategoryDropDown");			
			MyDeskDashboardPage.isDisplayedActive(MyDeskDashboardRepo.NPAValue,"NPAValue");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.VerticalDropDown,"VerticalDropDown");			
			MyDeskDashboardPage.isDisplayedActive(MyDeskDashboardRepo.LCG,"LCG");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SchemeTypeDropDown,"SchemeTypeDropDown");			
			MyDeskDashboardPage.isDisplayedActive(MyDeskDashboardRepo.CAA,"CAA");
			
			
			
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
	@Test(priority = 14)
	public void Download_SMA_Account_List(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 28 *****************");
			System.out.println();
			MyDeskDashboardPage.clickMyDesk();
//			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickUnassignedAccountsSMA();MyDeskDashboardPage.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.getTotalAccount();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Download,"Download");MyDeskDashboardPage.WaitLoader();			
			MyDeskDashboardPage.ExcelRead();
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
	@Test(priority = 15)
	public void Allocate_SMA_Accounts_to_Branch(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 29 *****************");
			System.out.println();
			//scroll down
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocateToDropdown,"AllocateToDropdown");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.BranchUser,"BranchUser");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Allocate,"Allocate");		
			MyDeskDashboardPage.isAccountAllocatedSuccessfully();
			MyDeskDashboardPage.clickMyDesk();
//			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.CheckCountWithAllocatedCount("Branch Attention Required Accounts ","SMA");
			
			
			//check count in the dashborad first tile
			String query="DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
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
	@Test(priority = 16)
	public void Allocate_SMA_Accounts_to_Call_Centre(ITestContext context)
			throws Throwable {

		try {
			
			System.out.println(" ************** 30 *****************");
			System.out.println();
			MyDeskDashboardPage.clickUnassignedAccountsSMA();MyDeskDashboardPage.WaitLoader();
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.getTotalAccount();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocateToDropdown,"AllocateToDropdown");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.CallcenterUser,"CallCenter");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectCallCenterDropdown,"AllocateToDropdown");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectCallCenter,"CallCenter");			
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Allocate,"Allocate");	
			MyDeskDashboardPage.isAccountAllocatedSuccessfully();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedList,"AllocatedList");
			
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedDropdown,"AllocatedDropdown");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.CallCenter,"CallCenter");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectCallCenterDropdown,"SelectCallCenterDropdown");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectCallCenter,"SelectCallCenter");
			MyDeskDashboardPage.SelectCurrentDate();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Search,"Search");
			MyDeskDashboardPage.WaitLoader();			
			MyDeskDashboardPage.getTotalAccountAllocation();
			
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
	@Test(priority = 17)
	public void Verify_the_allocated_accounts_to_call_centre_is_available_in_the_call_centre_login(ITestContext context)
			throws Throwable {

		try {
			System.out.println(" ************** 32 *****************");
			System.out.println();
			MyDeskDashboardPage.LogintotheCallCenter();

			MyDeskDashboardPage.click(MyDeskDashboardRepo.CallCentreCallLogin, "CallCentreCallLogin");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AccountFiltration, "AccountFiltration");
			MyDeskDashboardPage.WaitLoader();

			MyDeskDashboardPage.click(MyDeskDashboardRepo.AssetDropdown, "AssetDropdown");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SMACategorycall, "SMACategorycall");

			MyDeskDashboardPage.WaitLoader();
			// *[contains(text(),'SMA Category')]/..//*[contains(@class,'down')]

			MyDeskDashboardPage.click(MyDeskDashboardRepo.SMACategoryDropdown, "SMACategoryDropdown");
			MyDeskDashboardPage.selectSMAcategory();

			// select all SMA categories

			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedTodown, "AllocatedTodown");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedTodownCall, "AllocatedTodownCall");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Search, "Search");
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.getTotalAccount();

			String query = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
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
	@Test(priority = 18)
	public void Verify_the_accounts_shown_in_the_grid_is_same_as_allocated_from_my_desk_by_downloading_the_excel_file(
			ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 33 *****************");
			System.out.println();

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
	@Test(priority = 19)
	public void Allocate_SMA_Accounts_to_Collection_Agency(ITestContext context) throws Throwable {

		try {
			System.out.println(" ************** 31 *****************");
			System.out.println();

			MyDeskDashboardPage.LogintotheNewlyCreatedUserZone();
			MyDeskDashboardPage.clickMyDesk();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickDashboard();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.clickUnassignedAccountsSMA();
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocateToDropdown,"AllocateToDropdown");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.CollectionAgency,"CollectionAgency");		
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectAgencyDropDown,"SelectAgencyDropDown");		
			String AgencyName =driver.findElement(MyDeskDashboardRepo.SelectAgency).getText();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectAgency,"SelectAgency");			
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Allocate,"Allocate");	
			MyDeskDashboardPage.isAccountAllocatedSuccessfully();MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedList,"AllocatedList");
			
			MyDeskDashboardPage.WaitLoader();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.AllocatedDropdown,"AllocatedDropdown");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.CollectionAgency2,"CollectionAgency2");
			MyDeskDashboardPage.click(MyDeskDashboardRepo.SelectAgencyDropDown,"SelectAgencyDropDown");
			
			 By SelectAgency  = By.xpath("//*[contains(@aria-label,'"+AgencyName+"')]");
			 
			MyDeskDashboardPage.click(SelectAgency,"SelectAgency");
			MyDeskDashboardPage.SelectCurrentDate();
			MyDeskDashboardPage.click(MyDeskDashboardRepo.Search,"Search");
			MyDeskDashboardPage.WaitLoader();			
			MyDeskDashboardPage.getTotalAccountAllocation();
			
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

	

	@Test(priority = 20)
	public void Verify_the_accounts_allocated_to_agency_is_correctly_reflected_to_the_selected_agencies_bucket_list(
			ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 34 *****************");
			System.out.println();

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

	@Test(priority = 21)
	public void View_Allocated_List_for_SMA(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 35 *****************");
			System.out.println();

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

	@Test(priority = 22)
	public void View_allocated_list_of_call_centre(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 36 *****************");
			System.out.println();

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

	@Test(priority = 23)
	public void View_allocated_list_of_collection_agency(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 37 *****************");
			System.out.println();

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

	@Test(priority = 24)
	public void View_allocated_list_of_Branch(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 38 *****************");
			System.out.println();

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

	@Test(priority = 25)
	public void Boundary_Value_for_Parameters(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 39 *****************");
			System.out.println();

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

	@Test(priority = 26)
	public void Error_Message_for_Empty_Filters(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 40 *****************");
			System.out.println();

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

	@Test(priority = 27)
	public void NPA_Tile_Functionality(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 41 *****************");
			System.out.println();

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

	@Test(priority = 28)
	public void MFTNPA_Tile_Functionality(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 42 *****************");
			System.out.println();

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

	@Test(priority = 29)
	public void PFTNPA_Tile_Functionality(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 43 *****************");
			System.out.println();

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
