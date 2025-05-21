package CoreAddAgent;

import java.io.File;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.CoreAgentListRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

import Core.CallCenterRoleManagementMethods.CallCenterRoleManagementPage_MainClass;
import Core.MyDesk.Dashboard.MyDeskDashboardPage_MainClass;

import java.sql.Connection;
import CoreAddAgent.CoreAgentList_MainClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CoreAgentList_MainClass extends Base_Class {

	private static WebDriver driver;
	CallCenterRoleManagementPage_MainClass CallCenterRoleManagementPage = new CallCenterRoleManagementPage_MainClass(
			driver);
	public static String AgencyName;
	public static String Username;
	public static String Password;
	public static String Name;
	public static String AgentCode;
	public static String Number;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String orgName;
	public static String orgTypeName;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;
	public static String CoreUserName;
	public static String CoreUserPassword;
	public static String AppType;
	public CoreAgentList_MainClass(WebDriver driver) {
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

	public void GetNewAgencyUserCredentials() throws InterruptedException, Throwable {
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
		String Username = userId;
		String Password = password;
		writeData("AddAgencyList", 1, 7, Username);
		writeData("AddAgencyList", 1, 8, Password);
		ExtentTestManager.getTest().log(Status.PASS, "Agency created Suceessfully");
		ExtentTestManager.getTest().log(Status.PASS, "Username of Agency " + userId);
		ExtentTestManager.getTest().log(Status.PASS, "Password of Agency " + password);
//		SaveConfigFileBCO(userId, password);
	}
	public static void CollectionAgencyLogin(String CollectionUserName, String CollectionUserPassword) throws Exception {
		try {
			driver.navigate().refresh();
			AppType = "CollectionAgency";
			String Browser = configloader().getProperty("Browser");
			String CollectionAppUrl = configloader().getProperty("CollectionAgencyApplicationUrl");
		
			// Load the application URL
			driver.get(CollectionAppUrl);
			Common.setDriver(driver);

			String query = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=3 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
			CollectionAgency_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(query);
			System.out.println("BANNER_DETAILS: " + CollectionAgency_BANNER_DETAILS);

			Common.fluentWait(CollectionAgency_BANNER_DETAILS,
					LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));

			Pagetitle = driver.getTitle();
			Log.info("Title is displayed: " + Pagetitle);

			// Perform login actions
			Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
			Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
			Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

			driver.findElement(LoginPageRepo.UserNameField).sendKeys(CollectionUserName);
			Log.info("Entered " + CollectionUserName + " in user name field");
			driver.findElement(LoginPageRepo.PasswordField).sendKeys(CollectionUserPassword);
			Log.info("Entered " + CollectionUserPassword + " in password field");
			driver.findElement(LoginPageRepo.LoginButton).click();
			Log.info("Clicked on login button");

			try {
				WebElement clickableElement = Common.waitForElementToBeClickable(driver,
						LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

				if (clickableElement != null) {
					clickableElement.click();
					Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

					Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
					Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
					Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

					driver.findElement(LoginPageRepo.UserNameField).sendKeys(CollectionUserName);
					Log.info("Entered " + CollectionUserName + " in user name field");
					driver.findElement(LoginPageRepo.PasswordField).sendKeys(CollectionUserPassword);
					Log.info("Entered " + CollectionUserPassword + " in password field");
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

			SomeErrorOccuredHandling();

			Thread.sleep(6000);

		} catch (Exception e) {
			Log.error("An error occurred in CoreLogin: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	public static void SomeErrorOccuredHandling() {
		try {
			WebElement SomeErrorOccured = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.LoginPageSomeErrorOccurred, Duration.ofSeconds(20));

			if (SomeErrorOccured != null) {
				Log.info("Showing some error occured error message reloading the application");
				driver.navigate().refresh();

				if (AppType == "Core") {
					Common.fluentWait("Core login Banner",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
				} else if (AppType == "CollectionAgency") {
					Common.fluentWait(CollectionAgency_BANNER_DETAILS,
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));
				} else if (AppType == "CallCenter") {
					Common.fluentWait("CallCentre_BANNER_DETAILS",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CallCentre_BANNER_DETAILS));
				}

				Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
				driver.findElement(LoginPageRepo.LoginButton).click();

				try {
					WebElement clickableElement = Common.waitForElementToBeClickable(driver,
							LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

					if (clickableElement != null) {
						clickableElement.click();
						Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

						Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
						Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
						Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
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

	public void CheckRole() throws InterruptedException {

		Thread.sleep(2000);

		CallCenterRoleManagementPage.clickSecurityManagement();
		CallCenterRoleManagementPage.clickRoleManagement();
		CallCenterRoleManagementPage.WaitLoader();
		Common.fluentWait("Next", CallCenterRoleManagementRepo.Next);
		CallCenterRoleManagementPage.clickAddNewRole();
		Common.fluentWait("Functionality Checkbox", CallCenterRoleManagementRepo.FunctionalityCheckbox);
		CallCenterRoleManagementPage.AddRoleName7();
		CallCenterRoleManagementPage.SelectFunctionalities();
		ExtentTestManager.getTest().log(Status.PASS, "Selected the all Functionalities");
		CallCenterRoleManagementPage.clickSave();
		CallCenterRoleManagementPage.IsRecordSavedSuccessfully();
		CallCenterRoleManagementPage.WaitLoader();
		CallCenterRoleManagementPage.RoleManagemnentHeader();
	}

	public void AddAgentCode() {
		try {
			Common.fluentWaitNew("AgentCode", CoreAgentListRepo.AgentCode);
			Random random = new Random();
			int code = 1000 + random.nextInt(9000);
			AgentCode = String.valueOf(code);
			driver.findElement(CoreAgentListRepo.AgentCode).sendKeys(AgentCode);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code Added suceesfully ");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to add agent code");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AddAgentName() {
		try {
			Common.fluentWaitNew("Name", CoreAgentListRepo.Name);
			Name = generateRandomName(4);
			driver.findElement(CoreAgentListRepo.Name).sendKeys(Name);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Name Added suceesfully ");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to add name ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AddAgentPhoneNumber() {
		try {
			Common.fluentWaitNew("Name", CoreAgentListRepo.Name);
			Number = generateValidMobileNumber();
			driver.findElement(CoreAgentListRepo.PhoneNumber).sendKeys(Number);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code Added suceesfully");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to add Phone Number");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void GetNewAgentUserCredentials() throws InterruptedException, Throwable {

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
		writeData("CoreAgentList", 1, 3, Username);
		writeData("CoreAgentList", 1, 4, Password);
		ExtentTestManager.getTest().log(Status.PASS, "Agent created Suceessfully");
		ExtentTestManager.getTest().log(Status.PASS, "Username of Agent " + userId);
		ExtentTestManager.getTest().log(Status.PASS, "Password of Agent " + password);
	}

	public void UserSearch() throws InterruptedException {
		Common.fluentWaitNew("Name", CoreAgentListRepo.Name);
		driver.findElement(CoreAgentListRepo.AgentCode).sendKeys(AgentCode);
		click(CoreAgentListRepo.Search);
	}

	public void LoginToHOUser() throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open();");
		// Switch to the new tab
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String CoreAppUrl = configloader().getProperty("CoreApplicationUrl");
		String CoreUserName = configloader().getProperty("CoreUserName_HO");
		String CoreUserPassword = configloader().getProperty("CoreUserPassword_HO");
		driver.get(CoreAppUrl);
		Common.setDriver(driver);

		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=1 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

		Common.fluentWait("Core login Banner",
				LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));

		// ExtentTestManager.getTest().log(Status.INFO, CoreAppUrl + " loaded
		// successfully!");
		Thread.sleep(9000);

		Pagetitle = driver.getTitle();
		Log.info("Title is displayed: " + Pagetitle);

		// Perform login actions
		Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
		Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
		Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

		// Fetch the default URL from the database
		String query = "select Default_URL from acc_users where user_id = '" + CoreUserName + "'";
		String defaultURL = DBUtils.fetchSingleValueFromDB(query);
		Log.info("Default URL: " + defaultURL);

		// Check if the default URL is null or empty
		if (defaultURL == null || defaultURL.trim().isEmpty()) {
			// If the default URL is null or empty, update it to '/Home'
			String updateQuery = "UPDATE acc_users SET Default_URL = '/Home' WHERE user_id = '" + CoreUserName + "'";
			DBUtils.executeSQLStatement(updateQuery);
		} else if ("/Home".equals(defaultURL)) {
			// If the default URL is already '/Home', no action is needed
			System.out.println("Default URL is already /Home. No action taken.");
		}

		driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + CoreUserName + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + CoreUserPassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		Log.info("Clicked on login button");
		// ExtentTestManager.getTest().log(Status.INFO, "Clicked on login button");

		try {
			WebElement clickableElement = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

			if (clickableElement != null) {
				// Perform the desired action on the element
				clickableElement.click();
				// driver.findElement(LoginPageRepo.AlreadyLoginPopupYesButton).click();
				Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

				Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
				Log.info("Entered " + CoreUserName + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
				Log.info("Entered " + CoreUserPassword + " in password field");
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

		// Handling some error occurred case here
		Login_Class.SomeErrorOccuredHandling();

		// Fetch and display user organization details
		Common.fluentWait("AccountCategoryLabelInDashboard", LoginPageRepo.AccountCategoryLabelInDashboard);
		String UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
		Log.info("UserID in Dashboard: " + UserIDInDashboard);

		Login_Class.GetUserORGDetailsFromDB(UserIDInDashboard);

	}

	public void SearchResult() {

		String UserNameText = driver.findElement(CoreAgentListRepo.UsernameSearch).getText();
		if (UserNameText.equalsIgnoreCase(Username)) {
			ExtentTestManager.getTest().log(Status.PASS, "Search result matched with name");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Search result is not matched");
		}
		String AgentCodeText = driver.findElement(CoreAgentListRepo.AgentCodeSearch).getText();
		if (AgentCodeText.equalsIgnoreCase(Name)) {
			ExtentTestManager.getTest().log(Status.PASS, "Search result matched with Agent Code");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Search result is not matched with Agent code");
		}
		String MobileNoText = driver.findElement(CoreAgentListRepo.MobileNoSearch).getText();
		if (MobileNoText.equalsIgnoreCase(Number)) {
			ExtentTestManager.getTest().log(Status.PASS, "Search result matched with number");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Search result is not matched Number");
		}
		String StatusText = driver.findElement(CoreAgentListRepo.StatusSearch).getAttribute("style");
		if (StatusText.contains("green")) {
			ExtentTestManager.getTest().log(Status.PASS, "Status is active");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status is not active");
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

	public static String generateRandomName(int length) {
		String prefix = "Name";
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder randomPart = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomPart.append(characters.charAt(index));
		}

		return prefix + randomPart.toString();
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
		String MobileNumber = generateInvalidMobileNumber();
		Thread.sleep(2000);
		System.out.println("MobileNumber " + MobileNumber);
		driver.findElement(AddAgencyPageRepo.ContactNumberinput).sendKeys(MobileNumber);
		ExtentTestManager.getTest().log(Status.PASS, "Invalid Contact Number sent");
		click(AddAgencyPageRepo.ModeOfCollection, "ModeOfCollection");
		Thread.sleep(2000);
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
		WebElement invalidContactElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(AddAgencyPageRepo.InvalidContactNumber));
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

	public void VerifyAgencyList(String AgencyNameTestData) {
		String AgencyUser = driver.findElement(AddAgencyPageRepo.AgencyNameTable).getText();
		if (AgencyUser.contains(AgencyNameTestData)) {
			ExtentTestManager.getTest().log(Status.PASS, "New agency created and shown in Agency List");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "New agency not shown in Agency List");
		}

	}

	public void VerifyTheInactivatedAgent() {
		String StatusText = driver.findElement(CoreAgentListRepo.StatusSearchInactive).getAttribute("style");
		if (StatusText.contains("red")) {
			ExtentTestManager.getTest().log(Status.PASS, "Status is Inactive");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status is  active");
		}
	}

	public void VerifyTheactivatedAgent() {
		String StatusText = driver.findElement(CoreAgentListRepo.StatusSearchInactive).getAttribute("style");
		if (StatusText.contains("green")) {
			ExtentTestManager.getTest().log(Status.PASS, "Status is active");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status is not active");
		}
	}

	public void SatusChanged() {
		Common.fluentWait("Status Changed", AgentListPageRepo.StatusChanged);
		if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Status Changed Active to InActive and  Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status changed statement not Displayed");
		}
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

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}
}