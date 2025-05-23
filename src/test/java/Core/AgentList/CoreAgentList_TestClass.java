package Core.AgentList;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDate;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;

import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CoreAgentListRepo;
import com.Page_Repository.DispositionMasterPageRepo;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.MyDesk.Dashboard.MyDeskDashboardPage_MainClass;
import CoreAddAgent.CoreAgentList_MainClass;
import Core.AddAgencyManagement.AddAgencyPage_MainClass;
import bsh.ParseException;
import jdk.internal.org.jline.utils.Log;

//updated code
public class CoreAgentList_TestClass {
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	private static String UsernameTestData;
	public static String AgencyNameTestData;
	Base_Class baseclass;

	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreAgentList_MainClass CoreAgentList_MainCla1;
	AddAgencyPage_MainClass addagencypage;

	@BeforeClass

	public void SetUp() throws Exception {
		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CoreLogin_HO();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		CoreAgentList_MainCla1 = new CoreAgentList_MainClass(driver);
		ExcelReader = new com.Utility.ExcelReader("CoreAgentList");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
		addagencypage = new AddAgencyPage_MainClass(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		drivers.add(driver);

		callcenterlogin = new Login_Class();
		CoreAgentList_MainCla1 = new CoreAgentList_MainClass(driver);
		// Update the ScreenShot object with the new driver
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreAgentList");
	}

	@Test(priority = 1, enabled = true)
	public void login_with_valid_credentials() throws Throwable {

		try {
			System.out.println(" ************** 2 *****************");
			// Reading the Agency login credntials created in AddAgency Module
			addagencypage.AddAgency();
			CoreAgentList_MainCla1.GetNewAgencyUserCredentials();
			String Username = ExcelReader.readCellData("AddAgencyList", 1, 7);
			String Password = ExcelReader.readCellData("AddAgencyList", 1, 8);
			CoreAgentList_MainCla1.CollectionAgencyLogin(Username, Password);
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 2, enabled = false)
	public void login_with_invalid_credentials() throws Throwable {

		try {
			System.out.println(" ************** 3 *****************");
			String Username = "AGY00021";
			String Password = "xTNb";
			callcenterlogin.CollectionAgencyLogin(Username, Password);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 3, enabled = true)
	public void access_agent_management() throws InterruptedException {

		try {
			System.out.println(" ************** 4 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgentManagement, "AgentManagement");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 4, enabled = true)
	public void Access_Add_New_Agent_sub_menu() throws InterruptedException {

		try {
			System.out.println(" ************** 5 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AddNewAgent, "AddNewAgent");
			CoreAgentList_MainCla1.WaitLoader();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5, enabled = true)
	public void Submit_Add_New_Agent_with_no_Data() throws InterruptedException {
		try {
			System.out.println(" ************** 6 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Submit, "Submit");
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.AgentRequired, accountNumber);
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.NameRequired, accountNumber);
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.PhoneRequired, accountNumber);
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.RoleRequired, accountNumber);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 6, enabled = true)
	public void cancel_form_data_entry() throws InterruptedException {

		try {
			System.out.println(" ************** 7 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Close, "Close");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.isNOTDisplayed(CoreAgentListRepo.Close, "Close");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7, enabled = true)
	public void add_new_agent_with_valid_data() throws Throwable {

		try {
			System.out.println(" ************** 8 *****************");
			CoreAgentList_MainCla1.CheckRole();
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgentManagement, "AgentManagement");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AddNewAgent, "AddNewAgent");

			// add new role if null
			CoreAgentList_MainCla1.AddAgentCode();
			CoreAgentList_MainCla1.AddAgentName();
			CoreAgentList_MainCla1.AddAgentPhoneNumber();
			CoreAgentList_MainCla1.click(CoreAgentListRepo.RoleAddNewAgent, "RoleAddNewAgent");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.agenntrole, "agenntrole");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Submit, "Submit");
			CoreAgentList_MainCla1.isUserCreatedSuccessfully();
			CoreAgentList_MainCla1.GetNewAgentUserCredentials();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 8, enabled = true)
	public void search_for_agent_using_filters() throws InterruptedException {

		try {
			System.out.println(" ************** 9 *****************");
			CoreAgentList_MainCla1.UserSearch();
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.SearchResult();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 9, enabled = true)
	public void login_to_core_application_with_the_same_ho_user_which_is_added_the_agency_in_previous_module_test_case()
			throws Throwable {

		try {
			System.out.println(" ************** 10 *****************");
			CoreAgentList_MainCla1.LoginToHOUser();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 10, enabled = true)
	public void verify_added_agent_in_core_agent_list_page() throws Throwable {

		try {
			System.out.println(" ************** 11 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyManagement, "AgencyManagement");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgentList, "AgentList");	CoreAgentList_MainCla1.WaitLoader();
			UsernameTestData = ExcelReader.readCellData("CoreAgentList", 1, 3);
			AgencyNameTestData = ExcelReader.readCellData("CoreAgentList", 1, 5);
			driver.findElement(CoreAgentListRepo.UserName_HO).sendKeys(UsernameTestData);

			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgentCode, "AgentCode");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyName, "AgencyName");

			driver.findElement(CoreAgentListRepo.searchDropdown).sendKeys(AgencyNameTestData);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyDropdown, "AgencyDropdown");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
//	verify the table here		
			CoreAgentList_MainCla1.VerifyAgencyList(AgencyNameTestData);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 11, enabled = true)
	public void click_reset_button_with_full_data_in_agent_list_page() throws InterruptedException {

		try {
			System.out.println(" ************** 13 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Reset, "Reset");
			Thread.sleep(2000);
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 12, enabled = true)
	public void search_without_any_data_in_agent_list_page() throws InterruptedException {

		try {
			System.out.println(" ************** 12 *****************");

			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.AgencyNameisRequired, "AgencyNameisRequired");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 13, enabled = true)
	public void untick_is_active_and_search_with_active_agent_name() throws Throwable {

		try {
			System.out.println(" ************** 14 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.IsActive, "IsActive");
//			String UsernameTestData = ExcelReader.readCellData("CoreAgentList", 1, 3);
			driver.findElement(CoreAgentListRepo.UserName_HO).sendKeys(UsernameTestData);
//			String AgencyNameTestData = ExcelReader.readCellData("AddAgencyList", 1, 6);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyName, "AgencyName");

			driver.findElement(CoreAgentListRepo.searchDropdown).sendKeys(AgencyNameTestData);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyDropdown, "AgencyDropdown");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.NoRecordToDisplay, "NoRecordToDisplay");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 14, enabled = true)
	public void deactivate_the_saved_agent() throws InterruptedException {

		try {
			System.out.println(" ************** 15 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.IsActive, "IsActive");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Action, "Action");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.ActivateDeactivate, "ActivateDeactivate");
			CoreAgentList_MainCla1.SatusChanged();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 15, enabled = true)
	public void search_for_deactivated_agent_with_tick_is_active() throws Throwable {

		try {
			System.out.println(" ************** 16 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Reset, "Reset");
			Thread.sleep(4000);
//			String UsernameTestData = ExcelReader.readCellData("CoreAgentList", 1, 3);
			driver.findElement(CoreAgentListRepo.UserName_HO).sendKeys(UsernameTestData);
//			String AgencyNameTestData = ExcelReader.readCellData("AddAgencyList", 1, 6);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyName, "AgencyName");

			driver.findElement(CoreAgentListRepo.searchDropdown).sendKeys(AgencyNameTestData);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyDropdown, "AgencyDropdown");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.isDisplayed(CoreAgentListRepo.NoRecordToDisplay, "NoRecordToDisplay");
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 16, enabled = true)
	public void search_for_deactivated_agent_with_untick_is_active() throws Throwable {

		try {
			System.out.println(" ************** 17 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Reset, "Reset");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.IsActive, "IsActive");
//			String UsernameTestData = ExcelReader.readCellData("CoreAgentList", 1, 3);
			driver.findElement(CoreAgentListRepo.UserName_HO).sendKeys(UsernameTestData);
//			String AgencyNameTestData = ExcelReader.readCellData("AddAgencyList", 1, 6);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyName, "AgencyName");

			driver.findElement(CoreAgentListRepo.searchDropdown).sendKeys(AgencyNameTestData);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyDropdown, "AgencyDropdown");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.VerifyAgencyList(AgencyNameTestData);
			CoreAgentList_MainCla1.VerifyTheInactivatedAgent();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 17, enabled = true)
	public void activate_the_de_activated_agent() throws InterruptedException {

		try {
			System.out.println(" ************** 18 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Action, "Action");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.ActivateDeactivate, "ActivateDeactivate");
			CoreAgentList_MainCla1.SatusChanged();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 18, enabled = true)
	public void search_for_the_activated_agent_with_tick_is_active() throws Throwable {

		try {
			System.out.println(" ************** 19 *****************");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Reset, "Reset");
			Thread.sleep(4000);
//			CoreAgentList_MainCla1.click(CoreAgentListRepo.IsActive, "IsActive");
//			String UsernameTestData = ExcelReader.readCellData("CoreAgentList", 1, 3);
			driver.findElement(CoreAgentListRepo.UserName_HO).sendKeys(UsernameTestData);
//			String AgencyNameTestData = ExcelReader.readCellData("AddAgencyList", 1, 6);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyName, "AgencyName");

			driver.findElement(CoreAgentListRepo.searchDropdown).sendKeys(AgencyNameTestData);
			CoreAgentList_MainCla1.click(CoreAgentListRepo.AgencyDropdown, "AgencyDropdown");
			CoreAgentList_MainCla1.click(CoreAgentListRepo.Search, "Search");
			CoreAgentList_MainCla1.WaitLoader();
			CoreAgentList_MainCla1.VerifyAgencyList(AgencyNameTestData);
			CoreAgentList_MainCla1.VerifyTheactivatedAgent();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			File image = screenShot.takeScreenShot(methodName, "Failure");
			extenttest.log(Status.INFO, "Screenshot of failure: ",
					MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());
		}

		ExtentManager.getInstance().flush(); // Move outside the if block
	}

	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {
		ExcelReader = new com.Utility.ExcelReader("AddAgency");
		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

	@AfterClass
	public void afterEachTest() {

		// Close all tracked browser instances
		for (WebDriver driverInstance : drivers) {
			if (driverInstance != null) {
				driverInstance.quit();
			}
		}

		// Clear the list of drivers
		drivers.clear();

		System.out.println("All browser instances have been closed.");
	}

}