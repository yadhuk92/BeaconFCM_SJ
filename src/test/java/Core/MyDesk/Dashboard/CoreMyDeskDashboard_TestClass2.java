package Core.MyDesk.Dashboard;

import java.lang.reflect.Method;
import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.AddNewAgentMethods.AddNewAgentPage_MainClass;
import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage_MainClass;

public class CoreMyDeskDashboard_TestClass2  extends Base_Class {
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
//			MyDeskDashboardPage_MainClass.WaitLoader();
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
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Search, "search");
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
//				MyDeskDashboardPage_MainClass.LogintotheNewlyCreatedUserBranch();MyDeskDashboardPage_MainClass.WaitLoader();
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.Collection, "Collection");MyDeskDashboardPage_MainClass.WaitLoader();
				MyDeskDashboardPage_MainClass.clickMyDesk();
				MyDeskDashboardPage_MainClass.clickDashboard();
				MyDeskDashboardPage_MainClass.WaitLoader();
				// before new accounts
				String beforCount = MyDeskDashboardPage_MainClass.getNewAccountCount();
//				String query1 = "Select * from br_user_account_link where user_id='IBU0001528'";
//				DBUtils.executeSQLStatement(query1);
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
//				MyDeskDashboardPage_MainClass.click(MyDeskDashboardRepo.FailedCommitments, "FailedCommitments");
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

}
