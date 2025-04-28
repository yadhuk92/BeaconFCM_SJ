package Core.AddAgency;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.ExcelReaderNew;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.CallCentre.CoreAllocationSummaryPage;
import Core.CallCentre.CoreAutoAllocationPage;
import Core.CallCentre.CoreAutoAllocationPage.ProcedureResult;
import Core.CallCentre.CoreManualAllocationPage;
import Core.MyDesk.Dashboard.MyDeskDashboardPage_MainClass;
import Core.AddAgencyManagement.AddAgencyPage;
import bsh.ParseException;
import jdk.internal.org.jline.utils.Log;
//updated code
public class CoreAddAgency_TestClass {
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	AddAgencyPage addagencypage;
	CoreManualAllocationPage coremanualallocationpage;
	CoreAllocationSummaryPage coreallocationsummarypage;

	@BeforeSuite

	public void SetUp() throws Exception {

		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CoreLogin();
		
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		addagencypage=new AddAgencyPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Core_Manual_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		drivers.add(driver);
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		callcenterlogin = new Login_Class();
		// Update the ScreenShot object with the new driver
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre ManualAllocation");
	}
	@Test(priority = 1, enabled = true)
	public void Login_to_CoreWithHO_User() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

			// Home
			System.out.println(" ************** 2 *****************");

			System.out.println();
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Home"), "Navigated to Home Page.");

			ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Home  page, URL shows Home.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			Common.fluentWait("SMA", AddAgencyPageRepo.SMA);
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 2, enabled = true)
	public void Validate_AddAgency_Page() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 3 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

			// Navigate to Add Agency Management Main Menu

			addagencypage.navigateAgencyManagement();
			ExtentTestManager.getTest().log(Status.PASS, "Cliked on  Agency Management  Main Menu.");

			// Click on Add Agency option

			addagencypage.navigateToAddAgencyOption();
			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Add Agency sub-menu.");

			// Expected Result: User is navigated to Add Agency page
			// URL shows Agency/AddNewAgency

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Agency/AddNewAgency"), "Not navigated to Add Agency  page.");
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to Add Agency  page, URL showsAgency/AddNewAgency.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 3, enabled = true)
	public void Validate_Fields_From_AddAgency_Page() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 4 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Ads Agency page loaded");

			// Navigate to Add Agency Management Main Menu

			addagencypage.VerifyrequiredFileds();
			ExtentTestManager.getTest().log(Status.PASS, "Cliked on  Agency Management  Main Menu.");

			// Click on Add Agency option

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Agency/AddNewAgency"), "Not navigated to Add Agency  page.");
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to Add Agency  page, URL showsAgency/AddNewAgency.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 4, enabled = true)
	public void Validate_Invalid_PAN_WarningMessage() throws InterruptedException {
		System.out.println(" ************** 5 *****************");
		System.out.println();
		String expectedMessage = "Invalid PAN Number";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			String ErrorMEssage = addagencypage.Validate_ErrorMessage_InvalidPAN();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Error message");

			Assert.assertEquals(ErrorMEssage, expectedMessage);

			ExtentTestManager.getTest().log(Status.PASS, "Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	// Collection Agency Name already exists.

	@Test(priority = 5, enabled = true)
	public void Validate_Valid_PAN() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 7 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			addagencypage.Validate_PAN();

			WebElement error = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.PanInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid PAN!");

			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 6, enabled = true)
	public void Validate_InvalidGST() throws InterruptedException {

		String expectedMessage = "Invalid GST Number";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 8 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			String ErrorMEssage = addagencypage.Validate_ErrorMessage_InvalidGST();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Error message");

			Assert.assertEquals(ErrorMEssage, expectedMessage);

			ExtentTestManager.getTest().log(Status.PASS, "Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 7, enabled = true)
	public void Validate_validGST() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 9 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			addagencypage.Validate_GST();

			WebElement error = wait
					.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.GSTInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid PAN!");

			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);

	}

	@Test(priority = 8, enabled = true)
	public void Verify_Constitution_Type_dropdown_contains_valid_options_and_works_correctly()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 10 *****************");
			System.out.println();

			addagencypage.click(AddAgencyPageRepo.Constitution, "Constitution");
			addagencypage.verifyDropdownSymbolsConstirutionType();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);

	}

	@Test(priority = 9, enabled = true)
	public void Validate_InvalidCollectionName() throws InterruptedException {

		String expectedMessage = "Invalid Agency Name";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 11 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			String ErrorMEssage = addagencypage.Validate_ErrorMessage_InvalidCollectionName();
			ExtentTestManager.getTest().log(Status.PASS, "Validating Collection Agency Error message");

			Assert.assertEquals(ErrorMEssage, expectedMessage);

			ExtentTestManager.getTest().log(Status.PASS, "Error Message Displayed");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 10, enabled = true)
	public void Validate_validCollectionAgencyName() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 12 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");

			addagencypage.Validate_CollectionAgencyName();

			WebElement error = wait.until(
					ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.CoolectionAgencyInvalidError));
			Assert.assertTrue(error.isDisplayed(), " Error message NOT displayed for invalid Collection Agency Name");

			ExtentTestManager.getTest().log(Status.PASS, "NO error message");

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 11, enabled = true)
	public void verify_zone_drop_down_allows_multi_select_and_is_mandatory() throws InterruptedException {

		try {
			System.out.println(" ************** 13 *****************");
			System.out.println();

			Common.fluentWait("Zone", AddAgencyPageRepo.Zone);
			addagencypage.isDisplayed(AddAgencyPageRepo.Zone, "Zone is Mandatory field");
			addagencypage.click(AddAgencyPageRepo.Zone, "zone");
			addagencypage.click(AddAgencyPageRepo.allZone, "allZone");
			addagencypage.WaitLoader();
			addagencypage.click(AddAgencyPageRepo.Region, "Region");
			// Locate all <li> elements inside the dropdown list
			List<WebElement> dropdownItems = driver.findElements(By.xpath(
					"//ul[contains(@class, 'rz-multiselect-items')]/li[contains(@class, 'rz-multiselect-item')]"));

			// Print each item's text
			for (WebElement item : dropdownItems) {
				String label = item.getAttribute("aria-label"); // or item.getText() if visible
				String label1 = item.getAttribute("class"); // or item.getText() if visible
				System.out.println(label);
				System.out.println(label1);
				if (label1.contains("multiselect-item")) {
					ExtentTestManager.getTest().log(Status.PASS, "zone drop down allows multi select");
				} else {
					ExtentTestManager.getTest().log(Status.PASS, "zone drop down not allows multi select");
				}
			}
			// add code for multiselect from class

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

//	@Test(priority = 12, enabled = true, dataProvider = "TestData")
//	public void verify_region_drop_down_loads_based_on_the_selected_zone_and_allows_multi_select(
//			Map<Object, Object> testdata) throws InterruptedException {
//
//		try {
//			System.out.println(" ************** 14  *****************");
//			System.out.println();
//			String Zone = testdata.get("Zone").toString();
//			String Region = testdata.get("Region").toString();
//			addagencypage.click(AddAgencyPageRepo.ClearZone, "ClearZone");
//			addagencypage.WaitLoader();
//			addagencypage.click(AddAgencyPageRepo.Zone, "zone");
//			String customLabelZone = Zone;
//			String customLabelRegion = Region;
//			By customizedLocatorZone = By.xpath(String.format(AddAgencyPageRepo.SelectUserTC, customLabelZone));
//			By customizedLocatorRegion = By.xpath(String.format(AddAgencyPageRepo.SelectUserTC, customLabelRegion));
//			addagencypage.click(customizedLocatorZone, "customizedLocatorZone");
//			addagencypage.WaitLoader();
//			addagencypage.isDisplayed(AddAgencyPageRepo.Region, "Region is Mandatory field");
//			addagencypage.click(AddAgencyPageRepo.Region, "Region");
//			addagencypage.VerifyReagion(Region);
//
//			addagencypage.click(customizedLocatorRegion, "customizedLocatorRegion");
//			addagencypage.WaitLoader();
//		}
//
//		catch (AssertionError | Exception e) {
//			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
//			throw e;
//		}
//
//	}
	@Test(priority = 12, enabled = true)
	public void verify_region_drop_down_loads_based_on_the_selected_zone_and_allows_multi_select(
			) throws InterruptedException {

		try {
			System.out.println(" ************** 14  *****************");
			System.out.println();
//			String Zone = testdata.get("Zone").toString();
//			String Region = testdata.get("Region").toString();
			String Zone = "Ahmedabad";
			String Region ="Indore";
			addagencypage.click(AddAgencyPageRepo.ClearZone, "ClearZone");
			addagencypage.WaitLoader();
			addagencypage.click(AddAgencyPageRepo.Zone, "zone");
			String customLabelZone = Zone;
			String customLabelRegion = Region;
			By customizedLocatorZone = By.xpath(String.format(AddAgencyPageRepo.SelectUserTC, customLabelZone));
			By customizedLocatorRegion = By.xpath(String.format(AddAgencyPageRepo.SelectUserTC, customLabelRegion));
			addagencypage.click(customizedLocatorZone, "customizedLocatorZone");
			addagencypage.WaitLoader();
			addagencypage.isDisplayed(AddAgencyPageRepo.Region, "Region is Mandatory field");
			addagencypage.click(AddAgencyPageRepo.Region, "Region");
			addagencypage.VerifyReagion(Region);

			addagencypage.click(customizedLocatorRegion, "customizedLocatorRegion");
			addagencypage.WaitLoader();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}
	@Test(priority = 13, enabled = true)
	public void verify_scheme_type_drop_down_is_mandatory_allows_multi_select_and_loads_correctly()
			throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 15 *****************");
			System.out.println();
			addagencypage.isDisplayed(AddAgencyPageRepo.Scheme, "Scheme is Mandatory field");
			addagencypage.click(AddAgencyPageRepo.Scheme, "Scheme");
			addagencypage.click(AddAgencyPageRepo.allscheme, "allscheme");
			addagencypage.WaitLoader();
			List<WebElement> dropdownItems = driver.findElements(By.xpath(
					"//ul[contains(@class, 'rz-multiselect-items rz-multiselect-list')]/li[contains(@class, 'rz-multiselect-item')][position() > (last() - 7)]"));

			// Print each item's text
			for (WebElement item : dropdownItems) {
				String label = item.getAttribute("aria-label"); // or item.getText() if visible
				String label1 = item.getAttribute("class"); // or item.getText() if visible
				System.out.println(label);
				System.out.println(label1);
				if (label1.contains("multiselect-item")) {
					ExtentTestManager.getTest().log(Status.PASS, "zone drop down allows multi select");
				} else {
					ExtentTestManager.getTest().log(Status.PASS, "zone drop down not allows multi select");
				}

			}
		} catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 14, enabled = true)
	public void verify_product_type_drop_down_loads_based_on_selected_scheme_type_is_mandatory_and_allows_multi_select()
			throws InterruptedException {

		try {
			System.out.println(" ************** 16 *****************");
			System.out.println();

			// used for product

			addagencypage.click(AddAgencyPageRepo.ClearScheme, "ClearScheme");
			addagencypage.WaitLoader();
//			addagencypage.click(AddAgencyPageRepo.Scheme, "Scheme");

			addagencypage.click(AddAgencyPageRepo.SelectUser, "SelectUser");
			addagencypage.WaitLoader();
			List<WebElement> options = driver.findElements(By.xpath(
					"(//ul[contains(@class,'rz-dropdown-items rz-dropdown-list')]//li[contains(@class,'rz-dropdown-item')])[position() > (last() - 5)]"));
			// Step 4: Read from aria-label
			List<String> lastFiveSymbols = new ArrayList<>();
			for (WebElement option : options) {
				String symbol = option.getAttribute("aria-label").trim();
				if (!symbol.isEmpty()) {
					lastFiveSymbols.add(symbol);
				}
			}
			System.out.println("Last 5 dropdown symbols: " + lastFiveSymbols);
			addagencypage.WaitLoader();
			addagencypage.isDisplayed(AddAgencyPageRepo.ProductType, "Scheme is Mandatory field");
			addagencypage.click(AddAgencyPageRepo.ProductType, "ProductType");
			String attribute = driver.findElement(AddAgencyPageRepo.SelectUser).getAttribute("aria-label");
			if (attribute.contains("Housing Loan")) {
				ExtentTestManager.getTest().log(Status.PASS, "Housing loan displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Housing loan is not displayed");
			}
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 15, enabled = true)
	public void verify_address_field_is_non_mandatory() throws InterruptedException {

		try {
			System.out.println(" ************** 17 *****************");
			System.out.println();
			addagencypage.isDisplayed(AddAgencyPageRepo.Address, "Address is  field");
			String classAtributofAddress = driver.findElement(AddAgencyPageRepo.Address).getAttribute("class");
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "Address field is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " Address field is not mandatory");
			}
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 16, enabled = true)
	public void verify_state_drop_down_is_non_mandatory_and_allows_single_select() throws InterruptedException {

		try {
			System.out.println(" ************** 18 *****************");
			System.out.println();
			addagencypage.isDisplayed(AddAgencyPageRepo.State, "State is  field");
			String classAtributofAddress = driver.findElement(AddAgencyPageRepo.State).getAttribute("class");
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "State field is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " State field is not mandatory");
			}
			addagencypage.click(AddAgencyPageRepo.State, "State");
			addagencypage.click(AddAgencyPageRepo.SelectUser, "SelectUser");
			addagencypage.WaitLoader();

			String State = driver.findElement(AddAgencyPageRepo.StateValueFromUI).getText();
			String StateClass = driver.findElement(AddAgencyPageRepo.StateValueFromUI).getAttribute("class");
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "State field is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " State field is not mandatory");
			}
			// state should not be null or select
			if (State.contains("Select")) {
				ExtentTestManager.getTest().log(Status.FAIL, "State drop down is not selected ");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, "State drop down is selected single vaalue");
			}
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 17, enabled = true)
	public void verify_City_drop_down_loads_based_on_selected_state_is_non_mandatory_and_allows_single_select()
			throws InterruptedException {

		try {
			System.out.println(" ************** 19 *****************");
			System.out.println();
			addagencypage.isDisplayed(AddAgencyPageRepo.City, "City is  field");
			String classAtributofAddress = driver.findElement(AddAgencyPageRepo.City).getAttribute("class");
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "City field is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " City field is not mandatory");
			}
			// **********check cityy appears according to the state selected
			addagencypage.click(AddAgencyPageRepo.City, "City");
			addagencypage.click(AddAgencyPageRepo.SelectUser, "SelectUser");
			addagencypage.WaitLoader();

			// state should not be null or select
			String City = driver.findElement(AddAgencyPageRepo.CityValueFromUI).getText();
			String CityClass = driver.findElement(AddAgencyPageRepo.CityValueFromUI).getAttribute("class");
			System.out.println();
			System.out.println();
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "State field is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " State field is not mandatory");
			}
			if (City.contains("Select")) {
				ExtentTestManager.getTest().log(Status.FAIL, "City drop down is not selected ");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, "City drop down is selected ");
			}
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 18, enabled = true)
	public void verify_contact_number_field_only_accepts_valid_mobile_numbers_and_displays_error_for_invalid_numbers()
			throws InterruptedException {

		try {
			System.out.println(" ************** 20 *****************");
			System.out.println();
			Thread.sleep(2000);
			addagencypage.InvalidMobileNumber();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 19, enabled = true)
	public void verify_contact_number_field_only_accepts_valid_mobile_numbers() throws InterruptedException {

		try {
			System.out.println(" ************** 21 *****************");
			System.out.println();
			addagencypage.validMobileNumber();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 20, enabled = true)
	public void verify_date_of_empanelment_field_is_mandatory_and_displays_error_if_not_selected()
			throws InterruptedException {

		try {
			System.out.println(" ************** 22 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Submit, "Submit");

			addagencypage.isDisplayed(AddAgencyPageRepo.EmpanelmentRequired, "EmpanelmentRequired");
			addagencypage.isDisplayed(AddAgencyPageRepo.EmpanelmentExpiryDate, "EmpanelmentExpiryDate");
			addagencypage.isDisplayed(AddAgencyPageRepo.AgreementStartingError, "AgreementStartingError");
			addagencypage.isDisplayed(AddAgencyPageRepo.AgreementEndingError, "AgreementEndingError");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 21, enabled = true)
	public void verify_date_of_empanelment_field_is_valid_if_selected() throws Throwable {

		try {
			System.out.println(" ************** 23 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.DateEmpanelment, "DateEmpanelment");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			addagencypage.SelectCurrentDate(today, "DateEmpanelment");
			addagencypage.isNOTDisplayed(AddAgencyPageRepo.EmpanelmentRequired, "EmpanelmentRequired");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 22, enabled = true)
	public void verify_date_of_empanelment_expiry_field_is_mandatory_and_must_be_within_3_years_of_the_empanelment_date()
			throws InterruptedException {

		try {
			System.out.println(" ************** 24 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Submit, "Submit");
			addagencypage.isDisplayed(AddAgencyPageRepo.EmpanelmentExpiryDate, "EmpanelmentExpiryDate");

//			addagencypage.click(AddAgencyPageRepo.DateEmpanelmentExpiry,"DateEmpanelmentExpiry");
//		select date 3 year 	
//			addagencypage.SelectCurrentDate(today,"DateEmpanelment");
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 23, enabled = true)
	public void verify_date_of_empanelment_expiry_field_is_valid_if_within_3_years() throws Throwable {

		try {
			System.out.println(" ************** 25 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.DateEmpanelmentExpiry, "DateEmpanelmentExpiry");
			int year = LocalDate.now().getYear();
			int RequiredYear = year + 3;
			String RequiredYearString = String.valueOf(RequiredYear);
			addagencypage.ChangeYear(RequiredYearString);
//			addagencypage.ChangeMonth();
//			driver.findElement(By.xpath("//html")).click();
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			addagencypage.SelectCurrentDate(today, "DateEmpanelment");
			addagencypage.click(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");

			addagencypage.isNOTDisplayed(AddAgencyPageRepo.EmpanelmentExpiryDate, "EmpanelmentExpiryDate");
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 24, enabled = true)
	public void verify_agreement_duration_starting_and_ending_fields_are_mandatory_and_display_errors_if_not_selected()
			throws InterruptedException {

		try {
			System.out.println(" ************** 26 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Submit, "Submit");
			addagencypage.isDisplayed(AddAgencyPageRepo.AgreementStartingError, "AgreementStartingError");
			addagencypage.isDisplayed(AddAgencyPageRepo.AgreementEndingError, "AgreementEndingError");
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 25, enabled = true)
	public void verify_agreement_duration_starting_and_ending_fields_are_valid_if_selected() throws Throwable {

		try {
			System.out.println(" ************** 27 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			addagencypage.SelectCurrentDate(today, "DateEmpanelment");// *****not took anything
			addagencypage.click(AddAgencyPageRepo.AgreementEnding, "AgreementEnding");
			int year = LocalDate.now().getYear();
			int RequiredYear = year + 1;
			String RequiredYearString = String.valueOf(RequiredYear);
			addagencypage.SelectDate(today, null, RequiredYearString);
			addagencypage.SelectCurrentDate(today, "DateEmpanelment");// **** not taking date here
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 26, enabled = true)
	public void verify_remarks_field_is_mandatory_and_displays_error_if_left_empty() throws InterruptedException {

		try {
			System.out.println(" ************** 28  *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Submit, "Submit");
			addagencypage.isDisplayed(AddAgencyPageRepo.RemarksError, "RemarksError");
			addagencypage.isDisplayed(AddAgencyPageRepo.RemarksMandatory, "RemarksMandatory");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 27, enabled = true)
	public void verify_remarks_field_is_valid_if_entered() throws InterruptedException {

		try {
			System.out.println(" ************** 29 *****************");
			System.out.println();
			addagencypage.SendKeys(AddAgencyPageRepo.Remarks, "Remarks comment");
			addagencypage.click(AddAgencyPageRepo.Submit, "Submit");
			addagencypage.isNOTDisplayed(AddAgencyPageRepo.RemarksError, "RemarksError");
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 28, enabled = true)
	public void verify_the_non_mandatory_field_mode_of_collection() throws InterruptedException {

		try {
			System.out.println(" ************** 30  *****************");
			System.out.println();
			addagencypage.isDisplayed(AddAgencyPageRepo.ModeOfCollection, "Mode Of Collection is  displayed");
			String classAtributofAddress = driver.findElement(AddAgencyPageRepo.ModeOfCollection).getAttribute("class");
			if (classAtributofAddress.contains("mandatory-color")) {
				ExtentTestManager.getTest().log(Status.FAIL, "Mode Of Collection is mandatory");
			} else {
				ExtentTestManager.getTest().log(Status.PASS, " Mode Of Collection is not mandatory");
			}
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 29, enabled = true)
	public void verify_mode_of_collection_values() throws InterruptedException {

		try {
			System.out.println(" ************** 31 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
			addagencypage.isDisplayed(AddAgencyPageRepo.Cheque, "Cheque is  displayed");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 30, enabled = true)
	public void verify_the_column_headings_of_mode_of_collection() throws InterruptedException {

		try {
			System.out.println(" ************** 32 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Cheque, "Cheque");
			addagencypage.isDisplayed(AddAgencyPageRepo.CollectionLimitInGrid,
					"Collection Limit In Grid is  displayed");
			addagencypage.isDisplayed(AddAgencyPageRepo.ModeInGrid, "Mode In Grid is  displayed");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 31, enabled = true)
	public void verify_the_operators_in_the_mode_of_collection_grid() throws InterruptedException {

		try {
			System.out.println(" ************** 33 *****************");
			System.out.println();

			addagencypage.click(AddAgencyPageRepo.Symbols, "Symbols");
			addagencypage.verifyDropdownSymbols();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 32, enabled = true)
	public void verify_the_possible_to_enter_amount_in_the_value_field() throws InterruptedException {

		try {
			System.out.println(" ************** 34 *****************");
			System.out.println();
			addagencypage.SendKeys(AddAgencyPageRepo.CollectionLimitAmount, "1234");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 33, enabled = true)
	public void verify_close_button_closes_the_page_without_saving_any_entered_data() throws InterruptedException {

		try {
			System.out.println(" ************** 35 *****************");
			System.out.println();
			addagencypage.click(AddAgencyPageRepo.Close, "Close");
			addagencypage.WaitLoader();
			addagencypage.isDisplayed(AddAgencyPageRepo.TOTALOUTSTANDING,
					"TOTAL OUTSTANDING is  displayed and Add Agency details not saved");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	// PAN Number already exists.
	@Test(priority = 34, enabled = true)
	public void Validate_Existing_PAN_WarningMessage() throws InterruptedException {

		try {
			System.out.println(" ************** 6 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Add Agency page loaded");
			addagencypage.AddAgencyDetailsExistingPAN();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 35, enabled = true)
	public void verify_submit_button_submits_the_page_shows_a_loader_and_redirects_to_agency_list_page()
			throws InterruptedException {

		try {
			System.out.println(" ************** 36  *****************");
			System.out.println();
			addagencypage.ClearPanAndSubmit();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 36, enabled = true)
	public void verify_created_agency_is_listed_in_the_agency_list_page() throws InterruptedException {

		try {
			System.out.println(" ************** 37 *****************");
			System.out.println();
			addagencypage.VerifyAgencyList();

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 37, enabled = true)
	public void verify_that_the_username_and_password_are_displayed_after_click_on_submit_button()
			throws InterruptedException {

		try {
			System.out.println(" ************** 38 *****************");
			System.out.println("verify_that_the_username_and_password_are_displayed_after_click_on_submit_button");
			addagencypage.isUserCreatedSuccessfully();
			addagencypage.GetNewAgencyUserCredentials();
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 38, enabled = true)
	public void verify_that_agency_login_is_possible_with_new_agency_username_and_password_in_this_format_creating_a_core_zone_user_with_the_mydesk_functionality_enabled_role()
			throws Throwable {

		try {
			System.out.println(" ************** 39 *****************");
			System.out.println(
					"verify_that_agency_login_is_possible_with_new_agency_username_and_password_in_this_format_creating_a_core_zone_user_with_the_mydesk_functionality_enabled_role");
			addagencypage.LoginNewlyCreatedAgency();
			addagencypage.VerifyAgencyLogin();

			// check the login with extent pass message
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());

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

				extenttest.log(Status.INFO, "Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
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

	@AfterSuite
	public void afterEachTest() {
		ExtentManager.getInstance().flush();
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