package Core.AddAgencyManagement;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;

import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;

import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

import Core.MyDesk.Dashboard.MyDeskDashboardPage_MainClass;

import java.sql.Connection;

public class AddAgencyPage_MainClass extends Base_Class {

	private WebDriver driver;

	public static String AgencyName;
	public static String Username;
	public static String Password;
	public static String AppType;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;

	public AddAgencyPage_MainClass(WebDriver driver) {
		Log.info("Initializing Agency Management Page...");
		this.driver = driver;
		Log.info("WebDriver instance assigned.");
		Log.info("Initializing Web elements using PageFactory...");
		PageFactory.initElements(driver, this);
		Log.info("Web elements initialized using PageFactory.");
		Log.info("CallCenterAccountFiltrationPage initialization completed.");
	}

	// Method to navigate to Agency Management Main menu -AM
	public void navigateAgencyManagement() {
		Log.info("Starting navigation to the Agency management Main Menu..");
		WebElement AgencyManagementMainMenu = driver.findElement(AddAgencyPageRepo.AgencyManagementmainmenu);
		AgencyManagementMainMenu.click();
		Log.info("Clicked on Agency management Main Menu. ");
	}

	// Method to navigate to Add Agency submenu under Agency Management Main menu
	// -AM
	public void navigateToAddAgencyOption() {
		Log.info("Navigating t Add agency submenu.");
		WebElement AddAgencySubmenu = driver.findElement(AddAgencyPageRepo.AddAgencySubmenu);
		AddAgencySubmenu.click();
		Log.info("Clicked on Add Agency  sub Menu. ");
	}

	public void VerifyrequiredFileds() {
		Log.info(
				"Verifying PAN Number, GST Number, Constitution Type, Collection Agency Name fields displayed as expected.");

		Common.fluentWaitNew("zone", AddAgencyPageRepo.Zone);
		Log.info("Clicked on Add Agency  sub Menu. ");
		isDisplayed(AddAgencyPageRepo.PANNumber, "PANNumber");
		isDisplayed(AddAgencyPageRepo.CollectionAgencyName, "CollectionAgencyName");
		isDisplayed(AddAgencyPageRepo.Zone, "Zone");
		isDisplayed(AddAgencyPageRepo.Region, "Region");
		isDisplayed(AddAgencyPageRepo.Scheme, "Scheme");
		isDisplayed(AddAgencyPageRepo.ProductType, "ProductType");
		isDisplayed(AddAgencyPageRepo.ContactNumber, "ContactNumber");
		isDisplayed(AddAgencyPageRepo.DateEmpanelmentExpiry, "DateEmpanelmentExpiry");
		isDisplayed(AddAgencyPageRepo.DateEmpanelment, "DateEmpanelment");
		isDisplayed(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");
		isDisplayed(AddAgencyPageRepo.AgreementEnding, "AgreementEnding");
		isDisplayed(AddAgencyPageRepo.Remarks, "Remarks");
		isDisplayed(AddAgencyPageRepo.Close, "Close");
		isDisplayed(AddAgencyPageRepo.Submit, "Submit");
		isDisplayed(AddAgencyPageRepo.SaveAsDraft, "SaveAsDraft");
		isDisplayed(AddAgencyPageRepo.Address, "Address");
		isDisplayed(AddAgencyPageRepo.State, "State");
		isDisplayed(AddAgencyPageRepo.City, "City");
		isDisplayed(AddAgencyPageRepo.GST, "GST");
		isDisplayed(AddAgencyPageRepo.Constitution, "Constitution");
		isDisplayed(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");

	}

	public String Validate_ErrorMessage_InvalidPAN() {
		Log.info("Entering invalid PAN Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
		PANField.sendKeys("A1234KJHG7");
		Log.info("Entered invalid PAN number");

		Log.info("Clicking outside the Pan Field to validate the error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();

		WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.PanInvalidError);
		String message = ErrorMessage.getText();

		return message;
	}

	public String Validate_ErrorMessage_InvalidGST() {
		Log.info("Entering invalid GST Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.GSTField);
		PANField.sendKeys("123com");
		Log.info("Entered invalid GST number");

		Log.info("Clicking outside the Pan Field to validate the error message");
		WebElement ConsultaionNameField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
		ConsultaionNameField.click();

		WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.GSTInvalidError);
		String message = ErrorMessage.getText();

		return message;
	}

	public String Validate_ErrorMessage_InvalidCollectionName() {
		Log.info("Entering special charcters in  Coolection Agency Name.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
		PANField.sendKeys("ambi@ry");
		Log.info("Entered special charcters in  Coolection Agency Name.");

		Log.info("Clicking outside the Pan Field to validate the error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();

		WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.CoolectionAgencyInvalidError);
		String message = ErrorMessage.getText();

		return message;
	}

	public String Validate_ErrorMessage_ExistingPAN() {
		Log.info("Entering invalid PAN Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
		PANField.sendKeys("A1234KJHG7");
		Log.info("Entered invalid PAN number");

		Log.info("Clicking outside the Pan Field to validate the error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();

		WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.PanInvalidError);
		String message = ErrorMessage.getText();

		return message;
	}

	public void Validate_PAN() {
		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Log.info("Entering  valid  PAN Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
		PANField.sendKeys("AVKHY9356B");
		Log.info("Entered valid  PAN number");

		Log.info("Clicking outside the Pan Field to validate there is no error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();
	}

	public static String generatePAN() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder pan = new StringBuilder();
		Random random = new Random();

		// First 5 letters
		for (int i = 0; i < 5; i++) {
			pan.append(letters.charAt(random.nextInt(letters.length())));
		}

		// Next 4 digits
		for (int i = 0; i < 4; i++) {
			pan.append(random.nextInt(10));
		}

		// Last 1 letter
		pan.append(letters.charAt(random.nextInt(letters.length())));

		return pan.toString();
	}

	public void Validate_PAN_NewNumber() {
		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Log.info("Entering  valid  PAN Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
		String PAN = generatePAN();
		PANField.sendKeys(PAN);
		Log.info("Entered valid  PAN number");

		Log.info("Clicking outside the Pan Field to validate there is no error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();
	}

	public void Validate_GST() {
		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Log.info("Entering  valid  GST Number.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.GSTField);
		PANField.sendKeys("27AAAPA1234A1Z5");
		Log.info("Entered valid  GST number");

		Log.info("Clicking outside the Pan Field to validate there is no error message");
		WebElement ConsultaionNameField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
		ConsultaionNameField.click();
	}

	public void Validate_CollectionAgencyName() {
		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Log.info("Entering  valid  Collection Agency Name.");
		WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
		PANField.sendKeys("agency12");
		Log.info("Entered valid   Collection Agency Name");

		Log.info("Clicking outside the Pan Field to validate there is no error message");
		WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
		GSTField.click();
	}

	public static String generateRandomAgencyName(int length) {
		String prefix = "ATMNAgency";
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder randomPart = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomPart.append(characters.charAt(index));
		}

		return prefix + randomPart.toString();
	}

	public void click(By locator, String elementName) {
		try {
			Common.fluentWaitNew(elementName, locator);
			WebElement element = driver.findElement(locator);

			// JavaScript Executor to move to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			click1(locator);
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator " + elementName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
			// Re-throw the exception so TestNG marks the test as failed
			throw new RuntimeException(e);
		}
	}

	public void VerifyReagion(String region) {
		String zone = driver.findElement(AddAgencyPageRepo.SelectUser).getAttribute("aria-label");
		System.out.println("zone " + zone);
		// check for indore Indore
		if (zone.contains(region)) {
			ExtentTestManager.getTest().log(Status.PASS, "Region drop down is present and selected");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Region drop down is not present ");
		}
	}

	public String generateInvalidMobileNumber() {
		String[] invalidPatterns = { "12345abcde", // mixed numbers and letters
				"98765@#123", // special characters
				"abcdefghij", // only letters
				"123", // too short
				"1234567890123", // too long
				"", // empty
				"     ", // spaces only
				"12345abc678", // interleaved alphanumerics
		};
		Random random = new Random();
		return invalidPatterns[random.nextInt(invalidPatterns.length)];
	}

	public String generateValidMobileNumber() {
		Random random = new Random();
		// Start digit between 6 to 9
		int firstDigit = 6 + random.nextInt(4); // 6, 7, 8, or 9
		StringBuilder mobileNumber = new StringBuilder();
		mobileNumber.append(firstDigit);
		for (int i = 0; i < 9; i++) {
			mobileNumber.append(random.nextInt(10)); // digits 0-9
		}
		return mobileNumber.toString();
	}

	public void InvalidMobileNumber() throws InterruptedException {
		Thread.sleep(2000);
		String MobileNumber = generateInvalidMobileNumber();
		Thread.sleep(2000);
		System.out.println("MobileNumber " + MobileNumber);
		Common.fluentWait("MobileNumber", AddAgencyPageRepo.ContactNumberinput);
		driver.findElement(AddAgencyPageRepo.ContactNumberinput).sendKeys(MobileNumber);
		ExtentTestManager.getTest().log(Status.PASS, "Invalid Contact Number sent");
		click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
		Thread.sleep(8000);
		InvalidContactNumberSuccessfully();

	}

	public void validMobileNumber() {
		driver.findElement(AddAgencyPageRepo.ContactNumberinput).clear();
		String MobileNumber = generateValidMobileNumber();
		System.out.println("MobileNumber " + MobileNumber);
		driver.findElement(AddAgencyPageRepo.ContactNumberinput).sendKeys(MobileNumber);
		ExtentTestManager.getTest().log(Status.PASS, "valid Contact Number sent");
		driver.findElement(By.xpath("//html")).click();

	}

//	public void verifyDropdownSymbols() {
//		// Step 1: Expected symbols
//		List<String> expectedSymbols = Arrays.asList(">=", "<=", "=", ">", "<");
//
//		// Step 2: Open the dropdown
////		WebElement dropdown = driver
////				.findElement(By.xpath("//div[contains(@class,'rz-dropdown') and contains(@class,'form-control')]"));
////		dropdown.click();
//
//		// Step 3: Wait for the dropdown list to appear
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[contains(@class,'rz-dropdown-items')])[last()]")));
//
//
//	    // Step 3: Get the last 5 dropdown <li> elements using XPath
//	    List<WebElement> options = driver.findElements(By.xpath(
//	        "(//ul[contains(@class,'rz-dropdown-items')]//li[contains(@class,'rz-dropdown-item')])[position() > (last() - 5)]"
//	    ));
//
//	    // Step 4: Read from aria-label
//	    List<String> lastFiveSymbols = new ArrayList<>();
//	    for (WebElement option : options) {
//	        String symbol = option.getAttribute("aria-label").trim();
//	        if (!symbol.isEmpty()) {
//	            lastFiveSymbols.add(symbol);
//	        }
//	    }
//
//	    // Step 5: Print the result
//	    System.out.println("Last 5 dropdown symbols: " + lastFiveSymbols);
//	}
	public void verifyDropdownSymbols() {
		// Step 1: Expected symbols
		List<String> expectedSymbols = Arrays.asList(">=", "<=", "=", ">", "<");

		// Step 2: Wait for the dropdown list to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//ul[contains(@class,'rz-dropdown-items')])[last()]")));

		// Step 3: Get the last 5 dropdown <li> elements using XPath
		List<WebElement> options = driver.findElements(By.xpath(
				"(//ul[contains(@class,'rz-dropdown-items')]//li[contains(@class,'rz-dropdown-item')])[position() > (last() - 5)]"));

		// Step 4: Read from aria-label, clean and collect
		List<String> cleanedSymbols = new ArrayList<>();
		for (WebElement option : options) {
			String rawSymbol = option.getAttribute("aria-label").trim();
			if (!rawSymbol.isEmpty()) {
				// Remove all '>' characters
				String cleaned = rawSymbol.replaceFirst("[<>]", "");
				cleanedSymbols.add(cleaned);
			}
		}

		// Step 5: Print and compare
		System.out.println("Cleaned dropdown symbols: " + cleanedSymbols);
		System.out.println("Expected symbols: " + expectedSymbols);

		// Step 6: Validation (optional)
		if (cleanedSymbols.containsAll(expectedSymbols) && expectedSymbols.containsAll(cleanedSymbols)) {
			System.out.println("Dropdown symbols match expected symbols.");
			ExtentTestManager.getTest().log(Status.PASS, "Dropdown symbols match expected symbols.");
		} else {
			System.out.println("Mismatch in dropdown symbols.");
			ExtentTestManager.getTest().log(Status.FAIL, "Mismatch in dropdown symbols.");
		}
	}

	public void verifyDropdownSymbolsConstirutionType() {
		// Step 1: Expected symbols
		List<String> expectedSymbols = Arrays.asList("Proprietorship", "Partnership Firm", "Company", "Other");

		// Step 2: Wait for the dropdown list to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//ul[contains(@class,'-dropdown-items rz-dropdown-list')])[last()]")));

		// Step 3: Get the last 5 dropdown <li> elements using XPath
		List<WebElement> options = driver.findElements(By.xpath(
				"(//ul[contains(@class,'-dropdown-items rz-dropdown-list')]//li[contains(@class,'rz-dropdown-item')])[position() > (last() - 4)]"));

		// Step 4: Read from aria-label, clean and collect
		List<String> cleanedSymbols = new ArrayList<>();
		for (WebElement option : options) {
			String rawSymbol = option.getAttribute("aria-label").trim();
			if (!rawSymbol.isEmpty()) {
				// Remove all '>' characters
				String cleaned = rawSymbol.replaceFirst("[<>]", "");
				cleanedSymbols.add(cleaned);
			}
		}

		// Step 5: Print and compare
		System.out.println("Cleaned dropdown symbols: " + cleanedSymbols);
		System.out.println("Expected symbols: " + expectedSymbols);

		// Step 6: Validation (optional)
		if (cleanedSymbols.containsAll(expectedSymbols) && expectedSymbols.containsAll(cleanedSymbols)) {
			System.out.println("Dropdown Elements of Constitution type matched");
			ExtentTestManager.getTest().log(Status.PASS, "Dropdown Elements of Constitution type matched");
		} else {
			System.out.println("Dropdown Elements of Constitution type mismatched");
			ExtentTestManager.getTest().log(Status.FAIL, "Dropdown Elements of Constitution type mismatched");
		}
	}

	public void InvalidContactNumberSuccessfully() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement invalidContactElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.InvalidContactNumber));
		Common.fluentWaitNew("InvalidContactNumber", AddAgencyPageRepo.InvalidContactNumber);
		if (driver.findElement(AddAgencyPageRepo.InvalidContactNumber).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Invalid Contact Number displayed Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Invalid Contact Number not displayed ");
		}
	}

	public void validContactNumberSuccessfully() {

		if (driver.findElement(AddAgencyPageRepo.InvalidContactNumber).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.FAIL, "valid Contact Number not Accepted ");
		} else {
			ExtentTestManager.getTest().log(Status.PASS, "valid Contact Number Accepted ");
		}
	}

	public void isDisplayed(By locator, String elementName) {
		try {
			Thread.sleep(2000);
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void AddAgencyDetails() {
		try {
			Thread.sleep(2000);

			navigateAgencyManagement();
			navigateToAddAgencyOption();
			Common.fluentWaitNew("Zone", AddAgencyPageRepo.Zone);
			Validate_PAN_NewNumber();
			Log.info("Entering  valid  Collection Agency Name.");
			WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
			AgencyName = generateRandomAgencyName(3);
			PANField.sendKeys(AgencyName);
			Log.info("Entered valid   Collection Agency Name");
			click(AddAgencyPageRepo.Zone, "zone");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.Region, "Region");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			click(AddAgencyPageRepo.Scheme, "Scheme");
			WaitLoader();
			click(AddAgencyPageRepo.allscheme, "allscheme");
			WaitLoader();
			click(AddAgencyPageRepo.ProductType, "ProductType");
			WaitLoader();
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
			validMobileNumber();
			// dates
			click(AddAgencyPageRepo.DateEmpanelment, "DateEmpanelment");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			SelectCurrentDate(today, "DateEmpanelment");

			click(AddAgencyPageRepo.DateEmpanelmentExpiry, "DateEmpanelmentExpiry");
			int year = LocalDate.now().getYear();
			int RequiredYear = year + 3;
			String RequiredYearString = String.valueOf(RequiredYear);
			ChangeYear(RequiredYearString);
			SelectCurrentDate(today, "DateEmpanelment");
			click(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");

			SelectCurrentDate(today, "DateEmpanelment");
			click(AddAgencyPageRepo.AgreementEnding, "AgreementEnding");
			int year1 = LocalDate.now().getYear();
			int RequiredYear1 = year1 + 1;
			String RequiredYearString1 = String.valueOf(RequiredYear1);
			SelectDate(today, null, RequiredYearString1);
			SelectCurrentDate(today, "DateEmpanelment");
			// not worked last and last but one

			SendKeys(AddAgencyPageRepo.Remarks, "Remarks comment");
			click(AddAgencyPageRepo.Submit, "Submit");
			isDisplayed(AddAgencyPageRepo.loader, "Loader is  displayed");
			WaitLoader();
			Common.fluentWaitNew("Action", AddAgencyPageRepo.Action);
			isDisplayed(AddAgencyPageRepo.AddNewAgency,
					"Add New Agency is  displayed sucessfully the page is navigated to the Agency list page");
			ExtentTestManager.getTest().log(Status.PASS, "Agency List page displayed");

		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "");
			e.printStackTrace();
		}
	}

	public void AddAgencyDetailsExistingPAN() {
		try {
			Thread.sleep(2000);
			navigateAgencyManagement();
			click(AddAgencyPageRepo.AgencyList, "AgencyList");
			Common.fluentWaitNew("Action", AddAgencyPageRepo.Action);
			String UsedPanNumber = driver.findElement(AddAgencyPageRepo.AlreadyUsedPAN).getText();
			navigateAgencyManagement();
			navigateToAddAgencyOption();
			Common.fluentWaitNew("Zone", AddAgencyPageRepo.Zone);
			Log.info("Entering  valid  PAN Number.");
			WebElement PANField1 = driver.findElement(AddAgencyPageRepo.PanField);
			// it should be used one already
			PANField1.sendKeys(UsedPanNumber);
			Log.info("Entering  valid  Collection Agency Name.");
			WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
			AgencyName = generateRandomAgencyName(3);
//			writeData("AddAgencyList", 1, 6, AgencyName);
			PANField.sendKeys(AgencyName);
			Log.info("Entered valid   Collection Agency Name");
			click(AddAgencyPageRepo.Zone, "zone");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.Region, "Region");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			click(AddAgencyPageRepo.Scheme, "Scheme");
			WaitLoader();
			click(AddAgencyPageRepo.allscheme, "allscheme");
			WaitLoader();
			click(AddAgencyPageRepo.ProductType, "ProductType");
			WaitLoader();
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
			validMobileNumber();
			// dates
			
			Common.fluentWait("DateEmpanelment", AddAgencyPageRepo.DateEmpanelment);
			click(AddAgencyPageRepo.DateEmpanelment, "DateEmpanelment");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			Common.fluentWait("DateOfEmpanelment_Date", AddAgencyPageRepo.DateOfEmpanelment_Date(today));
			SelectCurrentDate(today, "DateEmpanelment");
			
			Common.fluentWait("DateEmpanelmentExpiry", AddAgencyPageRepo.DateEmpanelmentExpiry);
			//click(AddAgencyPageRepo.DateEmpanelmentExpiry, "DateEmpanelmentExpiry");
			Base_Class.click(AddAgencyPageRepo.DateEmpanelmentExpiry);
			Thread.sleep(3000);
			Common.fluentWait("DateOfEmpanelment_Date", AddAgencyPageRepo.DateOfEmpanelment_Date(today));
			int year = LocalDate.now().getYear();
			int RequiredYear = year + 3;
			String RequiredYearString = String.valueOf(RequiredYear);
			ChangeYear(RequiredYearString);
			SelectCurrentDate(today, "DateEmpanelment");
			click(AddAgencyPageRepo.DateOfEmpanelment_Date(today),"Click on DateOfEmpanelment_Date");
			
			Common.fluentWait("AgreementStarting", AddAgencyPageRepo.AgreementStarting);
			click(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");
			Thread.sleep(3000);
			Common.fluentWait("DateOfEmpanelment_Date", AddAgencyPageRepo.DateOfEmpanelment_Date(today));
			//SelectCurrentDate(today, "DateEmpanelment");
			click(AddAgencyPageRepo.DateOfEmpanelment_Date(today),"Click on agreement starting Date");
			
			Common.fluentWait("AgreementEnding", AddAgencyPageRepo.AgreementEnding);
			click(AddAgencyPageRepo.AgreementEnding, "AgreementEnding");
			Thread.sleep(3000);
			Common.fluentWait("DateOfEmpanelment_Date", AddAgencyPageRepo.DateOfEmpanelment_Date(today));
			int year1 = LocalDate.now().getYear();
			int RequiredYear1 = year1 + 1;
			String RequiredYearString1 = String.valueOf(RequiredYear1);
			SelectDate(today, null, RequiredYearString1);
			//SelectCurrentDate(today, "DateEmpanelment");
			click(AddAgencyPageRepo.DateOfEmpanelment_Date(today),"Click on agreement ending Date");
			// not worked last and last but one

			Common.fluentWait("Remarks", AddAgencyPageRepo.Remarks);
			SendKeys(AddAgencyPageRepo.Remarks, "Remarks comment");
			click(AddAgencyPageRepo.Submit, "Submit");
			Thread.sleep(3000);
			PANNumberalreadyexists();

		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "");
			e.printStackTrace();
		}
	}
	public void AddAgency() {
		try {			
			Thread.sleep(2000);				
			navigateAgencyManagement();
			navigateToAddAgencyOption();			
			Common.fluentWaitNew("Zone", AddAgencyPageRepo.Zone);
			Log.info("Entering  valid  PAN Number.");
			WebElement PANField1 = driver.findElement(AddAgencyPageRepo.PanField);
			// it should be used one already
			Validate_PAN_NewNumber();
			WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
			AgencyName = generateRandomAgencyName(3);
			writeData("AddAgencyList", 1, 6, AgencyName);
			PANField.sendKeys(AgencyName);
			Log.info("Entered valid   Collection Agency Name");
			click(AddAgencyPageRepo.Zone, "zone");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.Region, "Region");
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			click(AddAgencyPageRepo.Scheme, "Scheme");
			WaitLoader();
			click(AddAgencyPageRepo.allscheme, "allscheme");
			WaitLoader();
			click(AddAgencyPageRepo.ProductType, "ProductType");
			WaitLoader();
			click(AddAgencyPageRepo.SelectUser, "SelectUser");
			WaitLoader();
			click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
			validMobileNumber();
			// dates
			
			click(AddAgencyPageRepo.DateEmpanelment, "DateEmpanelment");
			String today = String.valueOf(LocalDate.now().getDayOfMonth());
			SelectCurrentDate(today, "DateEmpanelment");
			
			Common.fluentWaitNew("DateEmpanelmentExpiry", AddAgencyPageRepo.DateEmpanelmentExpiry);
			click(AddAgencyPageRepo.DateEmpanelmentExpiry, "DateEmpanelmentExpiry");
			int year = LocalDate.now().getYear();
			int RequiredYear = year + 3;
			String RequiredYearString = String.valueOf(RequiredYear);
			ChangeYear(RequiredYearString);
			SelectCurrentDate(today, "DateEmpanelment");
			
			Common.fluentWaitNew("AgreementStarting", AddAgencyPageRepo.AgreementStarting);
			click(AddAgencyPageRepo.AgreementStarting, "AgreementStarting");
			SelectCurrentDate(today, "DateEmpanelment");
			
			Common.fluentWaitNew("AgreementEnding", AddAgencyPageRepo.AgreementEnding);
			click(AddAgencyPageRepo.AgreementEnding, "AgreementEnding");
			int year1 = LocalDate.now().getYear();
			int RequiredYear1 = year1 + 1;
			String RequiredYearString1 = String.valueOf(RequiredYear1);
			SelectDate(today, null, RequiredYearString1);
			SelectCurrentDate(today, "DateEmpanelment");
			// not worked last and last but one

			SendKeys(AddAgencyPageRepo.Remarks, "Remarks comment");
			click(AddAgencyPageRepo.Submit, "Submit");
			Thread.sleep(3000);
			

		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "");
			e.printStackTrace();
		}
	}
	public void ClearPanAndSubmit() throws InterruptedException {
		click(AddAgencyPageRepo.PanField, "PanField");
		Thread.sleep(3000);
		driver.findElement(AddAgencyPageRepo.PanField).clear();
		Thread.sleep(1000);
		Validate_PAN_NewNumber();
		click(AddAgencyPageRepo.Submit, "Submit");
		By loader = By.xpath("//*[@class='spinner']");
		isDisplayed(loader, "Loader is  displayed");
		WaitLoader();
		Common.fluentWaitNew("Action", AddAgencyPageRepo.Action);
		isDisplayed(AddAgencyPageRepo.AddNewAgency,
				"Add New Agency is  displayed sucessfully the page is navigated to the Agency list page");
		ExtentTestManager.getTest().log(Status.PASS, "Agency List page displayed");
	}

	public void VerifyAgencyList() {
		String AgencyUser = driver.findElement(AddAgencyPageRepo.AgencyNameTable).getText();
		if (AgencyUser.contains(AgencyName)) {
			ExtentTestManager.getTest().log(Status.PASS, "New agency created and shown in Agency List");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "New agency not shown in Agency List");
		}

	}

	public void GetNewAgencyUserCredentials() throws InterruptedException, Throwable {
		try {

			driver.findElement(AddAgencyPageRepo.AgencyName).sendKeys(AgencyName);
			click(MyDeskDashboardRepo.Search);
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, " Search operation done successfully");

			WaitLoader();
			String text = driver.findElement(AddAgencyPageRepo.Usercreatedsuccessfully).getText();

			Pattern pattern = Pattern.compile("AGY\\d+");
			Matcher matcher = pattern.matcher(text);

			String userId = "";
			if (matcher.find()) {
				userId = matcher.group();
			}

			// Extract the password (last word in the message)
			String[] words = text.split(" ");
			String password = words[words.length - 1]; // Last word is the password

			// Print extracted credentials
			System.out.println("Extracted User ID: " + userId);
			System.out.println("Extracted Password: " + password);
			Username = userId;
			Password = password;
			writeData("AddAgencyList", 1, 7, Username);
			writeData("AddAgencyList", 1, 8, Password);
			ExtentTestManager.getTest().log(Status.PASS, "Agency created Suceessfully");
			ExtentTestManager.getTest().log(Status.PASS, "Username of Agency " + userId);
			ExtentTestManager.getTest().log(Status.PASS, "Password of Agency " + password);
//		SaveConfigFileBCO(userId, password);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "BCO search operation failed");
		}
	}

	public void LoginNewlyCreatedAgency()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		String URL = configloader().getProperty("CollectionAgencyApplicationUrl");
		String UserID = Username;
		String password = Password;

		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("document.querySelector('.tooltip').remove();");

		moveToElementAndClick(driver, MyDeskDashboardRepo.profiledropdownbutton);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);
		driver.get(URL);
		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

//	Common.fluentWaitNew("Core login Banner",
//			LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + UserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + password + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		try {
			WebElement clickableElement = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

			if (clickableElement != null) {
				// Perform the desired action on the element
				clickableElement.click();
				// driver.findElement(LoginPageRepo.AlreadyLoginPopupYesButton).click();
				Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

				Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
				Log.info("Entered " + UserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
				Log.info("Entered " + password + " in password field");
				driver.findElement(LoginPageRepo.LoginButton).click();
				Log.info("Clicked on login button");

				Log.info("Clicked on already login yes button and logged in again with valid credentials");
			} else {
				System.out.println("Element not clickable within the timeout.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
			System.out.println("Already login pop up not appeared");
		}
		try {
			WebElement SomeErrorOccured = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.LoginPageSomeErrorOccurred, Duration.ofSeconds(20));

			if (SomeErrorOccured != null) {
				Log.info("Showing some error occured error message reloading the application");
				driver.navigate().refresh();

				if (AppType == "Core") {
					Common.fluentWaitNew("Core login Banner",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
				} else if (AppType == "CollectionAgency") {
					Common.fluentWaitNew(CollectionAgency_BANNER_DETAILS,
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));
				} else if (AppType == "CallCenter") {
					Common.fluentWaitNew("CallCentre_BANNER_DETAILS",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CallCentre_BANNER_DETAILS));
				}

				Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
				driver.findElement(LoginPageRepo.LoginButton).click();

				try {
					WebElement clickableElement = Common.waitForElementToBeClickable(driver,
							LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

					if (clickableElement != null) {
						clickableElement.click();
						Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

						Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
						Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
						Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
						driver.findElement(LoginPageRepo.LoginButton).click();

						Log.info("Clicked on already login yes button and logged in again with valid credentials");
					} else {
						System.out.println("Element not clickable within the timeout.");
					}
				} catch (Exception e) {
					System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
					System.out.println("Already login pop up not appeared");
				}

			} else {
				System.out.println("Some error occured error message didn't show");
			}
		} catch (Exception e) {
			System.out.println("Some error occured error message didn't show");
		}
	}

	public void VerifyAgencyLogin() {
		String AgencyUser = driver.findElement(AddAgencyPageRepo.AgencyUser).getText();
		if (AgencyUser.contains(Username)) {
			ExtentTestManager.getTest().log(Status.PASS, "New agency created logged in suceesfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to login newly created Agency");
		}

	}

	public void isUserCreatedSuccessfully() {
		Common.fluentWaitNew("Usercreatedsuccessfully", AddAgencyPageRepo.Usercreatedsuccessfully);
		if (driver.findElement(AddAgencyPageRepo.Usercreatedsuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS,
					AddAgencyPageRepo.Usercreatedsuccessfully + "New agent added successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					AddAgencyPageRepo.Usercreatedsuccessfully + "Unable to add New agent");
		}
	}

	public void PANNumberalreadyexists() {
		Common.fluentWaitNew("PANNumberalreadyexists", AddAgencyPageRepo.PANNumberalreadyexists);
		if (driver.findElement(AddAgencyPageRepo.PANNumberalreadyexists).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS,
					AddAgencyPageRepo.PANNumberalreadyexists + "New agent added successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					AddAgencyPageRepo.PANNumberalreadyexists + "Unable to add New agent");
		}
	}

	public void SendKeys(By locator, String elementName) {
		try {
			Thread.sleep(2000);
			driver.findElement(locator).sendKeys(elementName);
			ExtentTestManager.getTest().log(Status.PASS, elementName + " is entered in the text box.");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void isNOTDisplayed(By locator, String elementName) {
		try {
			List<WebElement> elements = driver.findElements(locator);

			if (elements.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is not displayed, as expected.");
			} else if (!elements.get(0).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + " is present but hidden (not displayed), as expected.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is visible but should not be.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void SelectCurrentDate(String date, String CalenderNo) throws InterruptedException, ParseException {

		String today = String.valueOf(LocalDate.now().getDayOfMonth());

		// Construct the indexed XPath
		String xpath = "(//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
				+ today + "'])";

		try {
			WebElement dateElement = driver.findElement(By.xpath(xpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", dateElement);
			ExtentTestManager.getTest().log(Status.PASS, "Clicked current date for calendar #");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Could not click date for calendar #: " + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void SelectCurrentDate2(String date, String CalenderNo) throws InterruptedException, ParseException {

		String today = String.valueOf(LocalDate.now().getDayOfMonth());

		// Construct the indexed XPath
		String xpath = "(//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
				+ today + "'])[2]";

		try {
			WebElement dateElement = driver.findElement(By.xpath(xpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", dateElement);
			ExtentTestManager.getTest().log(Status.PASS, "Clicked current date for calendar #");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Could not click date for calendar #: " + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void SelectDate(String date, String Month, String Year) throws InterruptedException, ParseException {

		// Select the year
		if (Year != null && !Year.isEmpty()) {
			try {
				WebElement yearDropdown = driver.findElement(By.xpath(
						"(//div[@class='rz-datepicker-title']/..//div[@class='rz-dropdown-trigger  rz-corner-right'])[last()]"));
				yearDropdown.click();
				WebElement year = driver.findElement(
						By.xpath("(//ul[@role='listbox']/..//li[contains(@aria-label,'" + Year + "')])[last()]"));
				year.click();
			} catch (Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Could not select year: " + e.getMessage());
			}
		}

		// Select the month
		if (Month != null && !Month.isEmpty()) {
			try {
				WebElement monthDropdown = driver.findElement(By.xpath(
						"(//div[@class='rz-datepicker-title']/..//div[@class='rz-dropdown-trigger  rz-corner-right'])[last()-1]"));
				monthDropdown.click();

				// You can either use the Month parameter or calculate future month
				// Below code assumes you're using Month param directly
				WebElement month = driver.findElement(
						By.xpath("(//ul[@role='listbox']/..//li[contains(@aria-label,'" + Month + "')])[last()]"));
				month.click();
			} catch (Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Could not select month: " + e.getMessage());
			}
		}
	}

	public void ChangeYear(String Year) {
		// 1. Click the datepicker input field to open the calendar
//        WebElement dateInput = driver.findElement(By.cssSelector("input[placeholder='DD-MM-YYYY']"));
//        dateInput.click();

		// 2. Click the year dropdown
		WebElement yearDropdown = driver.findElement(By.xpath(
				"(//div[@class='rz-datepicker-title']/..//div[@class='rz-dropdown-trigger  rz-corner-right'])[last()]"));
		yearDropdown.click();

		// 3. Select year 2028 (visible in dropdown)
		WebElement year = driver
				.findElement(By.xpath("(//ul[@role='listbox']/..//li[contains(@aria-label,'" + Year + "')])[last()]"));
		year.click();
	}

	public void ChangeMonth() {
		// 1. Click the datepicker input field to open the calendar
//        WebElement dateInput = driver.findElement(By.cssSelector("input[placeholder='DD-MM-YYYY']"));
//        dateInput.click();

		// 2. Click the year dropdown
		WebElement MonthDropdown = driver.findElement(By.xpath(
				"(//div[@class='rz-datepicker-title']/..//div[@class='rz-dropdown-trigger  rz-corner-right'])[last()-1]"));
		MonthDropdown.click();

		// 3. Select year 2028 (visible in dropdown)
		// Get the current month
		LocalDate today = LocalDate.now();

		// Add 2 months to get the 3rd month from now (current + 2)
		LocalDate thirdMonthDate = today.plusMonths(2);

		// Get the name of the month (e.g., "July")
		String thirdMonthName = thirdMonthDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		// Print the result
		System.out.println("Third month from now: " + thirdMonthName);
		WebElement month = driver.findElement(
				By.xpath("(//ul[@role='listbox']/..//li[contains(@aria-label,'" + thirdMonthName + "')])[last()]"));
		month.click();
	}

	public void WaitLoader() {
		By loader = By.xpath("//*[@class='spinner']");
		// wait for Processing icon
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.pollingEvery(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Log.info("Loader disappeared.");
			Thread.sleep(4000);
		} catch (Exception e) {
			Log.info("Loader did not appear, proceeding without delay.");
		}
	}

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}
}