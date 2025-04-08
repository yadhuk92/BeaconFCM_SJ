package com.BasePackage;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Class extends Base_Class {

	public static String orgName;
	public static String orgTypeName;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;
	public static String CoreUserName;
	public static String CoreUserPassword;
	public static String AppType;

	public static void main(String[] args) {
		try {

			// CoreLogin();
			// CollectionAgencyLogin();
			CallCenterLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CoreLogin() throws Exception {
		try {
			AppType = "Core";
			String Browser = configloader().getProperty("Browser");
			String CoreAppUrl = configloader().getProperty("CoreApplicationUrl");
			CoreUserName = configloader().getProperty("CoreUserName");
			CoreUserPassword = configloader().getProperty("CoreUserPassword");

			// Initialize WebDriver based on browser type
			switch (Browser.toUpperCase()) {
			case "CHROME":
				ChromeOptions options = new ChromeOptions();
				// Enable insecure downloads by setting Chrome preferences
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.default_content_setting_values.automatic_downloads", 1); // Allow automatic downloads
				prefs.put("profile.default_content_setting_values.mixed_script", 1); // Allow insecure content globally
				prefs.put("profile.default_content_settings.popups", 0); // Block popups
				prefs.put("download.prompt_for_download", false); // Disable download prompt
				// prefs.put("download.default_directory", "C:\\Users\\pinku.peter\\Downloads");
				// // Set download directory
				String userHome = System.getProperty("user.home"); // Gets the user's home directory
				String downloadDirectory = userHome + File.separator + "Downloads";
				prefs.put("download.default_directory", downloadDirectory); // Set the dynamic download directory
				options.setExperimentalOption("prefs", prefs);

				// Add necessary arguments
				options.addArguments("--allow-running-insecure-content"); // Allow insecure content
				options.addArguments("--ignore-certificate-errors"); // Ignore SSL certificate errors
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-popup-blocking");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			// ExtentTestManager.getTest().log(Status.INFO, Browser + " opened
			// successfully!");
			Log.info("Driver has initialized successfully for " + Browser + " browser");

			// Load the application URL
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
				String updateQuery = "UPDATE acc_users SET Default_URL = '/Home' WHERE user_id = '" + CoreUserName
						+ "'";
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

			// Redirect to the module selection page
			// if (Common.waitForElementToBeClickable(driver,
			// LoginPageRepo.GoCollectionButton, Duration.ofSeconds(30)) != null) {
			/*
			 * if (defaultURL == null) {
			 * System.out.println("Entered into module selection page if condition");
			 * Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);
			 * Common.fluentWait("SetAsDefaultRadioButton",
			 * LoginPageRepo.SetAsDefaultRadioButton);
			 * Common.fluentWait("GoCollectionButton", LoginPageRepo.GoCollectionButton);
			 * Thread.sleep(3000);
			 * //driver.findElement(LoginPageRepo.GoCollectionButton).click();
			 * ForLoopClick(LoginPageRepo.GoCollectionButton);
			 * Log.info("Clicked on Go collection button"); } else {
			 * Log.info("Module selection page not appeared"); }
			 */

			// Handling some error occurred case here
			SomeErrorOccuredHandling();

			// Fetch and display user organization details
			Common.fluentWait("AccountCategoryLabelInDashboard", LoginPageRepo.AccountCategoryLabelInDashboard);
			String UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
			Log.info("UserID in Dashboard: " + UserIDInDashboard);

			GetUserORGDetailsFromDB(UserIDInDashboard);
			Log.info("Org Name: " + orgName + ", Org Type Name: " + orgTypeName);

			Common.fluentWait("UserORGDetails", LoginPageRepo.getORGDetailsinLoginLandingPage(orgName, orgTypeName));

		} catch (Exception e) {
			Log.error("An error occurred in CoreLogin: " + e.getMessage());
			e.printStackTrace();
			// ExtentTestManager.getTest().log(Status.ERROR, "An error occurred: " +
			// e.getMessage());
			throw e; // Optionally re-throw to let the calling method handle it
		} /*
			 * finally { // Ensure the WebDriver quits properly to avoid resource leaks if
			 * (driver != null) { driver.quit(); Log.info("Driver session closed."); } }
			 */
	}

	public static void GetUserORGDetailsFromDB(String UserID) throws SQLException, ClassNotFoundException, IOException {
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			// Establish the database connection
			con = Base_Class.OracleDBConnection();

			// Prepare the callable statement for the stored procedure
			String sql = "{CALL SP_GET_ORG_DETAILS_BY_USER(?, ?, ?)}";
			cstmt = con.prepareCall(sql);

			// Set input parameter
			cstmt.setString(1, UserID);

			// Register output parameters
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR); // For org_name
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR); // For org_type_name

			// Execute the stored procedure
			cstmt.execute();

			// Store the retrieved values in static variables
			orgName = cstmt.getString(2);
			orgTypeName = cstmt.getString(3);

			// Print results (optional)
			System.out.println("Org Name = " + orgName);
			System.out.println("Org Type Name = " + orgTypeName);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close resources
			if (cstmt != null) {
				cstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static void CollectionAgencyLogin() throws Exception {
		try {
			AppType = "CollectionAgency";
			String Browser = configloader().getProperty("Browser");
			String CollectionAppUrl = configloader().getProperty("CollectionAgencyApplicationUrl");
			String CollectionUserName = configloader().getProperty("CollectionAgencyUserName");
			String CollectionUserPassword = configloader().getProperty("CollectionAgencyUserPassword");

			// Initialize WebDriver based on browser type
			switch (Browser.toUpperCase()) {
			case "CHROME":
				ChromeOptions options = new ChromeOptions();
				// Enable insecure downloads by setting Chrome preferences
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.default_content_setting_values.automatic_downloads", 1); // Allow automatic downloads
				prefs.put("profile.default_content_setting_values.mixed_script", 1); // Allow insecure content globally
				prefs.put("profile.default_content_settings.popups", 0); // Block popups
				prefs.put("download.prompt_for_download", false); // Disable download prompt
				// prefs.put("download.default_directory", "C:\\Users\\pinku.peter\\Downloads");
				// // Set download directory
				String userHome = System.getProperty("user.home"); // Gets the user's home directory
				String downloadDirectory = userHome + File.separator + "Downloads";
				prefs.put("download.default_directory", downloadDirectory); // Set the dynamic download directory
				options.setExperimentalOption("prefs", prefs);

				// Add necessary arguments
				options.addArguments("--allow-running-insecure-content"); // Allow insecure content
				options.addArguments("--ignore-certificate-errors"); // Ignore SSL certificate errors
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-popup-blocking");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			Log.info("Driver has initialized successfully for " + Browser + " browser");

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

	public static void CallCenterLogin() throws Exception {
		try {
			AppType = "CallCenter";
			String Browser = configloader().getProperty("Browser");
			String CallCenterAppUrl = configloader().getProperty("CallCenterURL");
			String CallCenterUserName = configloader().getProperty("CallcentreUserName");
			String CallCenterPassword = configloader().getProperty("CallcentreUserPassword");

			// Initialize WebDriver based on browser type
			switch (Browser.toUpperCase()) {
			case "CHROME":
				ChromeOptions options = new ChromeOptions();
				// Enable insecure downloads by setting Chrome preferences
				Map<String, Object> prefs = new HashMap<>();
				prefs.put("profile.default_content_setting_values.automatic_downloads", 1); // Allow automatic downloads
				prefs.put("profile.default_content_setting_values.mixed_script", 1); // Allow insecure content globally
				prefs.put("profile.default_content_settings.popups", 0); // Block popups
				prefs.put("download.prompt_for_download", false); // Disable download prompt
				// prefs.put("download.default_directory",
				// "C:\\Users\\yadhukrishnan.p\\Downloads"); // Set download directory
				String userHome = System.getProperty("user.home"); // Gets the user's home directory
				String downloadDirectory = userHome + File.separator + "Downloads";
				prefs.put("download.default_directory", downloadDirectory); // Set the dynamic download directory
				options.setExperimentalOption("prefs", prefs);

				// Add necessary arguments
				options.addArguments("--allow-running-insecure-content"); // Allow insecure content
				options.addArguments("--ignore-certificate-errors"); // Ignore SSL certificate errors
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				options.addArguments("--disable-popup-blocking");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
			}

			driver.manage().window().maximize();
			// driver.manage().deleteAllCookies();
			// ExtentTestManager.getTest().log(Status.INFO, Browser + " opened
			// successfully!");
			Log.info("Driver has initialized successfully for " + Browser + " browser");

			// Load the application URL
			driver.get(CallCenterAppUrl);
			Common.setDriver(driver);
			// Common.fluentWait("LoginHyperlink2Banner",
			// LoginPageRepo.LoginHyperlink2Banner);

			String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
			CallCentre_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
			// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

			Common.fluentWait("CallCentre_BANNER_DETAILS",
					LoginPageRepo.CollectionAgencyLoginBannerDetails(CallCentre_BANNER_DETAILS));

			// ExtentTestManager.getTest().log(Status.INFO, CoreAppUrl + " loaded
			// successfully!");
			Thread.sleep(9000);

			Pagetitle = driver.getTitle();
			Log.info("Title is displayed: " + Pagetitle);

			// Perform login actions
			Common.fluentWait("UserNameField", LoginPageRepo.username);
			Common.fluentWait("PasswordField", LoginPageRepo.password);
			Common.fluentWait("LoginButton", LoginPageRepo.login);

			driver.findElement(LoginPageRepo.username).sendKeys(CallCenterUserName);
			// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
			// user name field");
			Log.info("Entered " + CallCenterUserName + " in user name field");
			driver.findElement(LoginPageRepo.password).sendKeys(CallCenterPassword);
			// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
			// " in password field");
			Log.info("Entered " + CallCenterPassword + " in password field");
			driver.findElement(LoginPageRepo.login).click();
			Log.info("Clicked on login button");
			// ExtentTestManager.getTest().log(Status.INFO, "Clicked on login button");

			try {
				WebElement clickableElement = Common.waitForElementToBeClickable(driver,
						LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

				if (clickableElement != null) {
					// Perform the desired action on the element
					clickableElement.click();
					// driver.findElement(LoginPageRepo.AlreadyLoginPopupYesButton).click();
//                    Common.waitForSpinnerToDisappear2(driver, "Loading Spinner", LoginPageRepo.Spinner);

					Common.fluentWait("UserNameField", LoginPageRepo.username);
					Common.fluentWait("PasswordField", LoginPageRepo.password);
					Common.fluentWait("LoginButton", LoginPageRepo.login);
					Thread.sleep(5000);
					driver.findElement(LoginPageRepo.username).clear();
					driver.findElement(LoginPageRepo.username).sendKeys(CallCenterUserName);
					Log.info("Entered " + CallCenterUserName + " in user name field");
					driver.findElement(LoginPageRepo.password).clear();
					driver.findElement(LoginPageRepo.password).sendKeys(CallCenterPassword);
					Log.info("Entered " + CallCenterUserName + " in password field");
					driver.findElement(LoginPageRepo.login).click();
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

//            Common.waitForSpinnerToDisappear2(driver, "Loading Spinner", LoginPageRepo.Spinner);
			Common.fluentWait("CallcentreFullLogo", LoginPageRepo.CallcentreFullLogo);
			Thread.sleep(5000);

			String UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
			Log.info("UserID in Dashboard: " + UserIDInDashboard);

			// GetUserORGDetailsFromDB(UserIDInDashboard);
			// Log.info("Org Name: " + orgName + ", Org Type Name: " + orgTypeName);

			// Common.fluentWait("UserORGDetails",
			// LoginPageRepo.getORGDetailsinLoginLandingPage(orgName, orgTypeName));

		} catch (Exception e) {
			Log.error("An error occurred in Call Center Login: " + e.getMessage());
			e.printStackTrace();
			// ExtentTestManager.getTest().log(Status.ERROR, "An error occurred: " +
			// e.getMessage());
			throw e; // Optionally re-throw to let the calling method handle it
		} /*
			 * finally { // Ensure the WebDriver quits properly to avoid resource leaks if
			 * (driver != null) { driver.quit(); Log.info("Driver session closed."); } }
			 */
	}

	public static void CoreLoginWithInputs(String UserID, String password) throws Exception {
		try {
			String Browser = configloader().getProperty("Browser");
			String CoreAppUrl = configloader().getProperty("CoreApplicationUrl");

			// Use input parameters if provided; otherwise, fetch from config
			String CoreUserName = (UserID != null && !UserID.isEmpty()) ? UserID
					: configloader().getProperty("CoreUserName");
			String CoreUserPassword = (password != null && !password.isEmpty()) ? password
					: configloader().getProperty("CoreUserPassword");

			// Initialize WebDriver based on browser type
			switch (Browser.toUpperCase()) {
			case "CHROME":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			Log.info("Driver has initialized successfully for " + Browser + " browser");

			// Load the application URL
			driver.get(CoreAppUrl);
			Common.setDriver(driver);

			String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=1 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
			String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);

			Common.fluentWait("Core login Banner",
					LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
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
				String updateQuery = "UPDATE acc_users SET Default_URL = '/Home' WHERE user_id = '" + CoreUserName
						+ "'";
				DBUtils.executeSQLStatement(updateQuery);
			} else if ("/Home".equals(defaultURL)) {
				// If the default URL is already '/Home', no action is needed
				System.out.println("Default URL is already /Home. No action taken.");
			}

			driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
			Log.info("Entered " + CoreUserName + " in user name field");
			driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
			Log.info("Entered password in password field");
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

					driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
					Log.info("Entered " + CoreUserName + " in user name field");
					driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
					Log.info("Entered password in password field");
					driver.findElement(LoginPageRepo.LoginButton).click();
					Log.info("Clicked on login button");
				}
			} catch (Exception e) {
				Log.info("Already login pop-up not appeared");
			}
			/*
			 * if (defaultURL == null) { Common.waitForSpinnerToDisappear("Loading Spinner",
			 * LoginPageRepo.Spinner); Common.fluentWait("SetAsDefaultRadioButton",
			 * LoginPageRepo.SetAsDefaultRadioButton);
			 * Common.fluentWait("GoCollectionButton", LoginPageRepo.GoCollectionButton);
			 * Thread.sleep(3000); ForLoopClick(LoginPageRepo.GoCollectionButton);
			 * Log.info("Clicked on Go collection button"); } else {
			 * Log.info("Module selection page not appeared"); }
			 */

			SomeErrorOccuredHandling();

			Common.fluentWait("AccountCategoryLabelInDashboard", LoginPageRepo.AccountCategoryLabelInDashboard);
			String UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
			Log.info("UserID in Dashboard: " + UserIDInDashboard);

			GetUserORGDetailsFromDB(UserIDInDashboard);
			Log.info("Org Name: " + orgName + ", Org Type Name: " + orgTypeName);

			Common.fluentWait("UserORGDetails", LoginPageRepo.getORGDetailsinLoginLandingPage(orgName, orgTypeName));
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

}
