package CoreAgencyList;

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
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.CoreAgencyListRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.AgencyList.CoreAgencyListPage_MainClass;
import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage_MainClass;

public class CoreAgencyList_TestClass extends Base_Class {
	Log log;
	Base_Class Base_Class;
	static com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	Login_Class AllLoginCases;
	CallCenterRoleManagementPage_MainClass CallCenterRoleManagementPage_MainClass;

	CallCenterRoleManagementRepo CallCenterRoleManagementRepo;
	CoreAgencyListPage_MainClass CoreAgencyListPage_MainClass;

	public static String ZoneCoreUserName;
	public static String AccountNo102;
	public static String CountFromTheGRIDBCOloanAtRisk;
	public static String tileCount;
	public static String firstWindowHandle;

	@BeforeSuite
	public void reference() {
		log = new Log();
		TestListener = new TestListener();
		Base_Class = new Base_Class();
		screenShot = new com.Utility.ScreenShot(driver);

	}

	@BeforeTest
	public void SetUp() throws Exception {
		ExcelReader = new com.Utility.ExcelReader("AddAgencyList");
		baseclass = new Base_Class();
		TestListener = new TestListener();
		// baseclass.SetUp();
		corelogin = new Login_Class();
		corelogin.CoreLogin_HO();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		CoreAgencyListPage_MainClass = new CoreAgencyListPage_MainClass(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core Agency List");
		System.out.println("Method name is: " + method.getName());
	}

	@Test(priority = 1, enabled = true)
	public void verify_ho_user_is_logged_into_the_core_application(ITestContext context) throws Throwable {

		try {
			
			System.out.println("*********** 2  *************");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Level, "Level");
			CoreAgencyListPage_MainClass.VerifyAgencyLevel();

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
	public void verify_that_the_user_can_access_the_agency_list_page(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 3  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyManagementmainmenu, "AgencyManagementmainmenu");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyList, "AgencyList");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Action", CoreAgencyListRepo.Action);
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Next, "Next");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.AddNewAgency, "AddNewAgency");

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
	public void verify_that_all_agencies_are_listed_upon_page_load(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  4 *************");

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
	public void verify_the_presence_of_input_filters(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 5  *************");

			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.AgencyName, "AgencyName");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.PANNumber, "PANNumber");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Zone, "Zone");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Region, "Region");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.AgencyName, "AgencyName");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.PANNumber, "PANNumber");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.Zone, "Zone");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.Region, "Region");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 5, enabled = true)
	public void verify_the_functionality_when_no_filters_are_applied(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 6  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Search, "Search");
			Common.fluentWait("loader", CoreAgencyListRepo.loader);
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.loader, "loader");
			CoreAgencyListPage_MainClass.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 6, enabled = true)
	public void verify_the_functionality_of_the_agency_name_field(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 7  *************");
			CoreAgencyListPage_MainClass.VerifyAgencyListFilterByAgencyName();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 7, enabled = true)
	public void verify_the_functionality_of_the_pan_number_filter(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 8  *************");
			CoreAgencyListPage_MainClass.VerifyAgencyListFilterByPanNumber();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 8, enabled = true, dataProvider = "TestData")
	public void verify_the_functionality_of_the_zone_filter(Map<Object, Object> testdata) throws Throwable {

		try {
			System.out.println("*********** 9  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Zone, "Zone");
			System.out.println("Test data used: " + testdata);
			String Zone = testdata.get("Zone").toString();		
			String customLabelZone = Zone;
			By customizedLocatorZone = By.xpath(String.format(CoreAgencyListRepo.SelectUserTC, customLabelZone));
			CoreAgencyListPage_MainClass.click(customizedLocatorZone, "customizedLocatorZone");
			CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Search, "Search");
			CoreAgencyListPage_MainClass.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 9, enabled = true, dataProvider = "TestData")
	public void verify_the_functionality_of_the_region_filter(Map<Object, Object> testdata) throws Throwable {

		try {
			System.out.println("***********  10 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Region, "Region");

			String Region = testdata.get("Region").toString();
			String customLabelRegion = Region;
			By customizedLocatorRegion = By.xpath(String.format(CoreAgencyListRepo.SelectUserTC, customLabelRegion));
			CoreAgencyListPage_MainClass.click(customizedLocatorRegion, "customizedLocatorRegion");
			CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Search, "Search");
			CoreAgencyListPage_MainClass.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 10, enabled = true)
	public void verify_that_agency_data_is_displayed_correctly_when_a_filter_is_applied(ITestContext context)
			throws Throwable {

		try {
			System.out.println("*********** 13  *************");
			CoreAgencyListPage_MainClass.VerifytheSearchResult();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 11, enabled = true)
	public void verify_the_add_new_agency_button_functionality(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  11 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AddNewAgency, "AddNewAgency");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Zone", CoreAgencyListRepo.Zone);
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.AddNewAgency, "AddNewAgency");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 12, enabled = true)
	public void verify_that_the_agency_grid_displays_the_correct_data_columns(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 12  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyManagementmainmenu, "AgencyManagementmainmenu");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyList, "AgencyList");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Action", CoreAgencyListRepo.Action);
			CoreAgencyListPage_MainClass.verifyDropdownAddNewAgencyPageGrid();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 13, enabled = true)
	public void verify_pagination_functionality_next_and_previous(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 14  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Next, "Next");
			CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.VerifySystemID("11");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 14, enabled = true)
	public void verify_pagination_functionality_previous(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  15 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Previous, "Previous");
			CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.VerifySystemID("1");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 15, enabled = true)
	public void verify_that_the_action_button_works_correctly(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 16  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");

			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Edit, "Edit");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.ActivateDeactvate, "ActivateDeactvate");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.ResetPassword, "ResetPassword");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Deempanel, "Deempanel");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 16, enabled = true)
	public void verify_that_the_user_can_access_the_edit_page(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 17  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Edit, "Edit");
			CoreAgencyListPage_MainClass.WaitLoader();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 17, enabled = true)
	public void verify_that_data_is_preloaded_in_all_the_fields(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  18 *************");
			String TextDate = driver.findElement(CoreAgencyListRepo.Contactnumber).getAttribute("value");
			String TextDate1 = driver.findElement(CoreAgencyListRepo.Pan).getAttribute("value");
			String TextDate2 = driver.findElement(CoreAgencyListRepo.NameEdit).getAttribute("value");
			String TextDate3 = driver.findElement(CoreAgencyListRepo.ZoneEdit).getAttribute("value");// not reading the
																										// value of zone
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.Contactnumber, "Contactnumber");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.Pan, "Pan");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.NameEdit, "NameEdit");
			CoreAgencyListPage_MainClass.verifyPrefilledDropdownText(CoreAgencyListRepo.ZoneEdit, "ZoneEdit");
			CoreAgencyListPage_MainClass.verifyPrefilledDropdownText(CoreAgencyListRepo.RegionEdit, "RegionEdit");
			CoreAgencyListPage_MainClass.verifyPrefilledDropdownText(CoreAgencyListRepo.ProductEdit, "ProductEdit");
			
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.Remarks, "Remarks");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.DateEmpanelment, "Date Empanelment");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.DateEmpanelmentExpiry,
					"Date Empanelment Expiry");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.DateStarting, "Date Starting");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.DateEnding, "Date Ending");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 18, enabled = true, dataProvider = "TestData")
	public void verify_the_editable_fields_on_the_edit_page(Map<Object, Object> testdata) throws Throwable {

		try {
			System.out.println("*********** 19  *************");
			String AgencyName = ExcelReader.readCellData("AddAgencyList", 1, 6); // Example: Row 1, Column 3
			System.out.println("Value from Excel: " + AgencyName);
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyManagementmainmenu, "AgencyManagementmainmenu");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyList, "AgencyList");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Action", CoreAgencyListRepo.Action);
			driver.findElement(CoreAgencyListRepo.AgencyName).sendKeys(AgencyName);
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Search, "Search");
			CoreAgencyListPage_MainClass.WaitLoader();
			String Address = testdata.get("Address").toString();
			String RandomString =CoreAgencyListPage_MainClass.generateRandomString(3);
			String result = Address + " " + RandomString;
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Edit, "Edit");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Action", CoreAgencyListRepo.Zone);
			CoreAgencyListPage_MainClass.UpdateAdress(result);
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Update, "Update");// some times error is showing in
																					// update need to update
			CoreAgencyListPage_MainClass.RecordUpdatedSuccessfully();
			CoreAgencyListPage_MainClass.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 19, enabled = true, dataProvider = "TestData")
	public void verify_that_edits_can_be_saved_and_updated(Map<Object, Object> testdata) throws Throwable {

		try {
			System.out.println("*********** 20  *************");
			String Address = testdata.get("Address").toString();
			Common.fluentWait("Action", CoreAgencyListRepo.Action);
			CoreAgencyListPage_MainClass.VerifyTheAdressUpdate(Address);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 20, enabled = true)
	public void verify_that_the_edit_is_reflected_in_the_agency_list_page(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 21  *************");
//			CoreAgencyListPage_MainClass.VerifyTheAdressUpdate(Address);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 21, enabled = true, dataProvider = "TestData")
	public void verify_that_the_edited_agencys_details_are_correct_on_the_edit_page(Map<Object, Object> testdata)
			throws Throwable {

		try {
			System.out.println("*********** 22  *************");
			String Address = testdata.get("Address").toString();
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Edit, "Edit");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Update", CoreAgencyListRepo.Update);
			String TextDate3 = driver.findElement(CoreAgencyListRepo.Address).getAttribute("value");
			CoreAgencyListPage_MainClass.VerifyTheAdressUpdateEdit(Address);

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 22, enabled = true)
	public void verify_that_the_user_can_cancel_edits_and_navigate_back(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 23  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Close, "Close");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("Action", CoreAgencyListRepo.Action);
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.AddNewAgency, "AddNewAgency");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 23, enabled = true)
	public void verify_that_the_user_can_access_the_activate_deactivate_popup(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  24 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.ActivateDeactvate, "ActivateDeactvate");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("CollectionAgencyName", CoreAgencyListRepo.CollectionAgencyName);
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.CollectionAgencyName, "CollectionAgencyName");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Activate, "Activate");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.CurrentStatus, "CurrentStatus");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 24, enabled = true)
	public void verify_that_the_collection_agency_name_field_is_displayed_correctly(ITestContext context)
			throws Throwable {

		try {
			System.out.println("*********** 25  *************");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.CollectionAgencyNameInput,
					"CollectionAgencyName");
			CoreAgencyListPage_MainClass.IsElementDisabled(CoreAgencyListRepo.CollectionAgencyNameInput,
					"CollectionAgencyName");
			CoreAgencyListPage_MainClass.AgencyNameVerification();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

//agency should be active
	@Test(priority = 25, enabled = true)
	public void verify_the_activate_date_field_functionality_when_the_status_is_active(ITestContext context)
			throws Throwable {

		try {
			System.out.println("***********  26 *************");// check is is active or not, if not make it active
			CoreAgencyListPage_MainClass.CheckAgencyStatusandMakeItActiveNew();
			CoreAgencyListPage_MainClass.IsElementDisabled(CoreAgencyListRepo.ActivateDate, "ActivateDate");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 26, enabled = true)
	public void verify_the_deactivate_date_field_functionality_when_the_status_is_active(ITestContext context)
			throws Throwable {

		try {
			System.out.println("*********** 27  *************");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 27, enabled = true)
	public void verify_the_current_status_field_displays_the_correct_status(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 30  *************");
			CoreAgencyListPage_MainClass.GetCurrentStatus();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 28, enabled = true)
	public void verify_the_submit_button_functionality(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 31  *************");
//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.ActivateDeactvate, "ActivateDeactvate");Common.fluentWait("submit", CoreAgencyListRepo.submit);
			CoreAgencyListPage_MainClass.CheckAgencyStatusandMakeItActive();
			CoreAgencyListPage_MainClass.MakeAgencyStatusDeactive();

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

//now status of Agency should be 
	@Test(priority = 29, enabled = true)
	public void verify_that_the_changes_are_reflected_when_reopening_the_popup(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 33  *************");
			// check is it updated or not->done
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.ActivateDeactvate, "ActivateDeactvate");
			CoreAgencyListPage_MainClass.WaitLoader();
			Common.fluentWait("CollectionAgencyName", CoreAgencyListRepo.CollectionAgencyName);
			Thread.sleep(5000);
			String StatusfromUI = CoreAgencyListPage_MainClass.StatusAgency();
			if (StatusfromUI.contains("Deactive")) {
				ExtentTestManager.getTest().log(Status.PASS, "Agency Status is updated");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agency Status is not updated");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

///8888888888
	@Test(priority = 30, enabled = true)
	public void verify_the_activate_date_field_functionality_when_the_status_is_deactive(ITestContext context)
			throws Throwable {

		try {
			System.out.println("***********  28 *************");
			CoreAgencyListPage_MainClass.IsElementEnabled(CoreAgencyListRepo.ActivateDate, "ActivateDate");
			CoreAgencyListPage_MainClass.IsElementDisabled(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 31, enabled = true)
	public void verify_the_deactivate_date_field_functionality_when_the_status_is_deactivated(ITestContext context)
			throws Throwable {

		try {
			System.out.println("*********** 29  *************");
			CoreAgencyListPage_MainClass.IsElementDisabled(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 32, enabled = true)
	public void verify_the_close_button_functionality(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  30 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Close, "Close");
			CoreAgencyListPage_MainClass.WaitLoader();
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
	public void verify_that_the_user_can_click_on_the_reset_password_button(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 35  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.ResetPassword, "ResetPassword");
			CoreAgencyListPage_MainClass.WaitLoader();
			// updated the password
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.passwordresetsuccessfully,
					"passwordresetsuccessfully");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 34, enabled = true)
	public void verify_that_the_reset_password_is_shown_on_the_ui(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 36  *************");

			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.passwordresetsuccessfully,
					"passwordresetsuccessfully");
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
	public void verify_that_a_deactivated_agency_cannot_log_in(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  34 *************");
			CoreAgencyListPage_MainClass.GetUserCredentials();
			firstWindowHandle = driver.getWindowHandle();
			((JavascriptExecutor) driver).executeScript("window.open();");
			CoreAgencyListPage_MainClass.SwitchToNewTab(firstWindowHandle);
			CoreAgencyListPage_MainClass.LoginNewlyCreatedAgency();
			CoreAgencyListPage_MainClass.InvalidUsername();
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName();
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
	public void verify_that_the_agency_can_log_in_with_the_new_password(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 37  *************");
			driver.switchTo().window(firstWindowHandle);

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
	public void verify_that_the_agency_cannot_log_in_with_the_old_password(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 38  *************");
			// logout?

//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyManagementmainmenu, "AgencyManagementmainmenu");
//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyList, "AgencyList");CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.ResetPassword, "ResetPassword");
			CoreAgencyListPage_MainClass.GetUserCredentials();
			CoreAgencyListPage_MainClass.SwitchToNewTab(firstWindowHandle);

			List<WebElement> elements = driver.findElements(CoreAgencyListRepo.Logout);
			if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
			    CoreAgencyListPage_MainClass.Logout();
			}

			// reload
			driver.navigate().refresh();
			Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
			CoreAgencyListPage_MainClass.LoginNewlyCreatedAgency();
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.InvalidUsername, "InvalidUsername");

			// lgin should not happen
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
	public void verify_that_de_empanel_button_is_showing_in_action(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 39  *************");
			driver.switchTo().window(firstWindowHandle);
//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyManagementmainmenu, "AgencyManagementmainmenu");
//			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.AgencyList, "AgencyList");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Action, "Action");
			CoreAgencyListPage_MainClass.SaveAgencyName();
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Deempanel, "Deempanel");

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
	public void de_empanel_popup_open(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 40  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Deempanel, "Deempanel");
			CoreAgencyListPage_MainClass.WaitLoader();
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.DeEmpanelmentPopUp, "DeEmpanelmentPopUp");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.EmpaneledDate, "EmpaneledDate");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.DeEmpanelmentDate, "DeEmpanelmentDate");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Remark, "Remark");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.Close, "Close");
			CoreAgencyListPage_MainClass.isDisplayed(CoreAgencyListRepo.submit, "submit");
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
	public void mandatory_field_validation_check(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 41  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.submit, "submit");
			CoreAgencyListPage_MainClass.DeEmpanelmentdaterequired();
			CoreAgencyListPage_MainClass.WaitLoader();
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
	public void verify_date_is_prefilled_in_empaneled_date_field(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 42  *************");
			CoreAgencyListPage_MainClass.IsPrefilled(CoreAgencyListRepo.EmpaneledDate, "EmpaneledDate");

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
	public void verify_empaneled_date_field_is_non_editable(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 43  *************");

			CoreAgencyListPage_MainClass.IsElementDisabled(CoreAgencyListRepo.EmpaneledDate, "EmpaneledDate");
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
	public void verify_de_empaneled_date_edit(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  44 *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.DeEmpanelmentDate, "DeEmpanelmentDate");
			CoreAgencyListPage_MainClass.SelectDateForDeactivatedate();

			// is need to select the date
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
	public void verify_remark_column(ITestContext context) throws Throwable {

		try {
			System.out.println("***********  45 *************");
			driver.findElement(CoreAgencyListRepo.Remark).sendKeys("Remark");
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
	public void verify_submit_button(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 46  *************");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.Remark, "submit");
			CoreAgencyListPage_MainClass.click(CoreAgencyListRepo.submit, "submit");
			CoreAgencyListPage_MainClass.RecordDeempaneledSuccessfully();

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
	public void verify_de_empaneled_agency_is_not_listing_in_agency_list_page(ITestContext context) throws Throwable {

		try {
			System.out.println("*********** 47  *************");
			CoreAgencyListPage_MainClass.IsAgencyDisplayedAfterDeEmpanel();

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
		// Get the class and package name from the result
		Class<?> testClass = result.getMethod().getRealClass();
		String packageName = testClass.getPackage().getName();

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
//			ExtentManager.getInstance().flush();
		// Close the browser
		if (driver != null) {
			driver.quit();
		}
	}

//	@DataProvider(name = "TestData")
//	public static Object[][] gettestdate() throws IOException {
//
//		ExcelReader = new com.Utility.ExcelReader("AddAgencyList");
//		Object[][] objectarry = null;
//		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();
//
//		objectarry = new Object[completedata.size()][1];
//
//		for (int i = 0; i < completedata.size(); i++) {
//			objectarry[i][0] = completedata.get(i);
//		}
//		return objectarry;
//	}
	@DataProvider(name = "TestData")
	public static Object[][] gettestdata() throws IOException {
	    ExcelReader = new com.Utility.ExcelReader("AddAgencyList");
	    List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();
	    
	    // Filter rows with Run=Yes
	    List<Map<String, String>> filteredData = new ArrayList<>();
	    for (Map<String, String> row : completedata) {
	        if ("Yes".equalsIgnoreCase(row.get("Run"))) {
	            filteredData.add(row);
	        }
	    }

	    Object[][] objectArray = new Object[filteredData.size()][1];
	    for (int i = 0; i < filteredData.size(); i++) {
	        objectArray[i][0] = filteredData.get(i);
	    }

	    return objectArray;
	}
}
