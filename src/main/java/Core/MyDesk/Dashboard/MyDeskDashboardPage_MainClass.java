package Core.MyDesk.Dashboard;

import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.MyDeskDashboardRepo;

import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.time.Duration;
import java.util.List;

public class MyDeskDashboardPage_MainClass extends Base_Class {
	private WebDriver driver;
	public static String count;
	public static String countAlloaction;
	public static String AgencyName;
	public static String AccountName;
	public static String AppType;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;
	public static String BCOUSername;

	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\MyDeskDashboardconfig2";
	static String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"; // Set download
																									// directory
	// Constructor

	public MyDeskDashboardPage_MainClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("MyDeskDashboardPage_MainClass");
	}

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

	public static Properties configloader1() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

	public static Properties configloader2() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\configBCO.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

	public void SaveConfigFileBCO(String Username, String Password) {
		Properties properties = new Properties();
		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\configBCO.properties")) {
			properties.setProperty("BCOUserID", Username);
			properties.setProperty("BCOpassword", Password);
			properties.store(output, "Updated Config File");
			System.out.println("Config file updated successfully!");
			ExtentTestManager.getTest().log(Status.PASS, "Config file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Config file update failed");

		}
	}

	public void SaveConfigFileZone(String Username, String Password) {
		updateConfig("ZoneUserID", Username, "Zonepassword", Password);
		ExtentTestManager.getTest().log(Status.PASS, "Updated ZoneUserID in config File" + Username);
	}

	public void SaveConfigFileRegion(String Username, String Password) {
		updateConfig("RegionUserID", Username, "Regionpassword", Password);
	}

	public void SaveConfigFileBranch(String Username, String Password) {
		updateConfig("Core_BranchUserID", Username, "Core_Branchpassword", Password);
	}

	private void updateConfig(String key1, String value1, String key2, String value2) {
		Properties properties = new Properties();

		// Load existing properties if the file exists
		File configFile = new File(CONFIG_FILE_PATH);
		if (configFile.exists()) {
			try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Update properties
		properties.setProperty(key1, value1);
		properties.setProperty(key2, value2);

		// Save updated properties back to the file
		try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
			properties.store(output, "Updated Config File");
			System.out.println("Config file updated successfully!");
			ExtentTestManager.getTest().log(Status.PASS, "Config file updated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to save config file");

		}
	}

	public void VerifyConfigZone() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String ZoneUserID = configloader().getProperty("ZoneUserID").trim();
			String ZoneUserPW = configloader().getProperty("Zonepassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Config file Zone user ID  " + ZoneUserID);
			ExtentTestManager.getTest().log(Status.PASS, "Zone User PW  " + ZoneUserPW);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void VerifyConfigRegion() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String RegionUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("RegionUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Region User ID from config2 file" + RegionUserID);

			String Regionpassword = MyDeskDashboardPage_MainClass.configloader().getProperty("Regionpassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Region User password config2 file " + Regionpassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());

		}
	}

	public void VerifyConfigBranch() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID")
					.trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User ID config2 file is " + Core_BranchUserID);
			String Core_Branchpassword = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_Branchpassword")
					.trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User password  " + Core_Branchpassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void VerifyConfigBCOUser() throws IOException {
		// Define procedure name

		String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID").trim();
		ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + Core_BranchUserID);
	}

	public void SQLQueryZone() throws IOException {
		try {
			String ZoneUserID = configloader().getProperty("ZoneUserID").trim();
			List<Object> inputParams = Arrays.asList(ZoneUserID, "John Doe", "john.doe@example.com", 9876543210L);
//		List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ZoneUserIDGenerator", inputParams,
					outputTypes);

			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);
			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);

			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message.contains(Message)) {
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileZone(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void SQLQueryRegion() throws IOException {
		try {
			String RegionUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("RegionUserID").trim();
			List<Object> inputParams = Arrays.asList(RegionUserID, "John Doe", "john.doe@example.com", 9876543210L);
//		List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("RegionUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);
			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);

			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message
						.contains("UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.")) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileRegion(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}

	}

	public void SQLQueryBranch() throws IOException {

		try {
			String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID")
					.trim();
			List<Object> inputParams = Arrays.asList(Core_BranchUserID, "John Doe", "john.doe@example.com",
					9876543210L);
//	List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("Core_BranchUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);
			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);
			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message.contains(Message)) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileBranch(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}

	}

	public void SQL_BCOUSer() throws IOException {
		try {
			String procedureName = "InsertToMSTEmployeeForCoreMyDeskDashboard";
			// Input parameters
			List<Object> inputParams = new ArrayList<>();
			String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID")
					.trim();
			inputParams.add(Core_BranchUserID); // Example USER_ID
			// Output parameters
			List<Integer> outputParamTypes = new ArrayList<>();
			outputParamTypes.add(Types.VARCHAR); // p_MESSAGE output parameter

			// Execute stored procedure
			List<Object> output = DBUtils.InsertToMSTEmployeeForCoreMyDeskDashboard(procedureName, inputParams,
					outputParamTypes);
			ExtentTestManager.getTest().log(Status.PASS,
					"Insert To MST Employee For Core My Desk Dashboard procedure done ");
			// Print output
			if (!output.isEmpty()) {
				System.out.println("Stored Procedure Output: " + output.get(0));

				ExtentTestManager.getTest().log(Status.PASS, "Stored Procedure Output: " + output.get(0));
			} else {
				System.out.println("No output received.");
				ExtentTestManager.getTest().log(Status.FAIL, "Stored Procedure Output: " + output.get(0));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void SQL_QueryMyDesktoCoreUser() throws IOException {
		try {
			String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID")
					.trim();
			List<Object> inputParams = Arrays.asList(Core_BranchUserID);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ConfigureTilesForBranchUser",
					inputParams, outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String MessageFromDB = (String) results.get(0);
			if (MessageFromDB.contains("Tiles are successfully configured to the user")) {
				ExtentTestManager.getTest().log(Status.PASS, "Tiles are successfully configured to the user");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Tiles are not configured to the user");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void SQL_QueryMyDesktoRegionUser() throws IOException {
		try {
			String RegionUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("RegionUserID").trim();
			List<Object> inputParams = Arrays.asList(RegionUserID);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ConfigureTilesForBranchUser",
					inputParams, outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String MessageFromDB = (String) results.get(0);
			if (MessageFromDB.contains("Tiles are successfully configured to the user")) {
				ExtentTestManager.getTest().log(Status.PASS, "Tiles are successfully configured to the user");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Tiles are not configured to the user");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void SQL_QueryMyDesktoZoneUser() throws IOException {
		try {
			String ZoneUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("ZoneUserID").trim();
			List<Object> inputParams = Arrays.asList(ZoneUserID);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ConfigureTilesForBranchUser",
					inputParams, outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String MessageFromDB = (String) results.get(0);
			if (MessageFromDB.contains("Tiles are successfully configured to the user")) {
				ExtentTestManager.getTest().log(Status.PASS, "Tiles are successfully configured to the user");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Tiles are not configured to the user");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void LogintotheCallCenter() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		String CallCenterURL = MyDeskDashboardPage_MainClass.configloader1().getProperty("CallCenterURL").trim();
		String CallCenterUserName = MyDeskDashboardPage_MainClass.configloader1().getProperty("CallCenterUserName")
				.trim();
		String CallCenterPassword = MyDeskDashboardPage_MainClass.configloader1().getProperty("CallCenterPassword")
				.trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		click1(MyDeskDashboardRepo.Logout);
		driver.get(CallCenterURL);
		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);
		Common.fluentWait("Core login Banner",
				LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(CallCenterUserName);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + CallCenterUserName + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(CallCenterPassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + CallCenterPassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		Log.info("Clicked on login button");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(CallCenterUserName);
				Log.info("Entered " + CallCenterUserName + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(CallCenterPassword);
				Log.info("Entered " + CallCenterPassword + " in password field");
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

	}

	public void verifyOutstandingInGrid() throws ClassNotFoundException, SQLException, IOException {
		String AccountNumber = getAccountNumber();
		String TotalDueUI = getTotalDue();
		String OutStandingUI = getTotalOutstanding();
		DBUtils.executeSQLStatement_GridQuery(AccountNumber);
		double OverDuevalue = DBUtils.OverDue;
		double OutStandingvalue = DBUtils.OutStanding;
		// convert double to string

		String DBDueValue = String.valueOf(OverDuevalue);
		String DBOutStandingValue = String.valueOf(OutStandingvalue);
		if (TotalDueUI.equalsIgnoreCase(DBDueValue)) {
			ExtentTestManager.getTest().log(Status.PASS, "both the Due values of DB and UI are matched");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "both the Due values of DB and UI are not matched");
		}
		if (OutStandingUI.equalsIgnoreCase(DBOutStandingValue)) {
			ExtentTestManager.getTest().log(Status.PASS, "both the Outstanding values of DB and UI are matched");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "both the Due values of DB and UI are not matched");
		}
	}

	public String getAccountNumber() {
		String number = driver.findElement(MyDeskDashboardRepo.SelectAccountNumber).getText();
		return number;

	}

	public String getAccountNumberDisposition() {
		String number = driver.findElement(MyDeskDashboardRepo.SelectAccountNumberDisposition).getText();
		return number;

	}

//	
	public String getTotalDue() {
		String number = driver.findElement(MyDeskDashboardRepo.SelectTotalDue).getText();
		return number;

	}

	public String getTotalOutstanding() {
		String number = driver.findElement(MyDeskDashboardRepo.SelectTotalOutstanding).getText();
		return number;

	}

	public void checkBothThestringsAreEqual(String str1, String str2) {
		if (str1.equalsIgnoreCase(str2)) {
			ExtentTestManager.getTest().log(Status.PASS,
					"Both the Account numbers of future disposition and Monthly commitment search are same");
		} else
			ExtentTestManager.getTest().log(Status.FAIL,
					"Both the Account numbers of future disposition and Monthly commitment search are same");
	}

	public void LogintotheNewlyCreatedUserBranch() throws IOException, InterruptedException {
		String Core_BranchUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_BranchUserID").trim();
		String Core_Branchpassword = MyDeskDashboardPage_MainClass.configloader().getProperty("Core_Branchpassword")
				.trim();

		moveToElementAndClick(driver, MyDeskDashboardRepo.profiledropdownbutton);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);

		driver.findElement(LoginPageRepo.UserNameField).sendKeys(Core_BranchUserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + Core_BranchUserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(Core_Branchpassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + Core_Branchpassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		Log.info("Clicked on login button");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(Core_BranchUserID);
				Log.info("Entered " + Core_BranchUserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Core_Branchpassword);
				Log.info("Entered " + Core_Branchpassword + " in password field");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(Core_BranchUserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Core_Branchpassword);
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

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(Core_BranchUserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(Core_Branchpassword);
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

	public void LogintotheNewlyCreatedUserBranch_Region() throws IOException, InterruptedException {
		String RegionUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("RegionUserID").trim();
		String Regionpassword = MyDeskDashboardPage_MainClass.configloader().getProperty("Regionpassword").trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		Thread.sleep(2000);
//		click1(MyDeskDashboardRepo.Logout);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(RegionUserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + RegionUserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(Regionpassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + Regionpassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		Log.info("Clicked on login button");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(RegionUserID);
				Log.info("Entered " + RegionUserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Regionpassword);
				Log.info("Entered " + Regionpassword + " in password field");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(RegionUserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Regionpassword);
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

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(RegionUserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(Regionpassword);
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

	public void ClearDataBase() throws ClassNotFoundException, SQLException, IOException {
		String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query1);
		String query2 = "DELETE FROM mst_col_agency_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query2);
//	use code
		String query = "DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query);
		String query3 = " delete from mst_branch_acc_allocated ";
		DBUtils.executeSQLStatement(query3);
		String query4 = "delete from br_user_account_link";
		DBUtils.executeSQLStatement(query4);
	}

	public void LogintotheNewlyCreatedUserZone()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		String CoreApplicationUrl = MyDeskDashboardPage_MainClass.configloader1().getProperty("CoreApplicationUrl")
				.trim();
		String ZoneUserID = MyDeskDashboardPage_MainClass.configloader().getProperty("ZoneUserID").trim();
		String Zonepassword = MyDeskDashboardPage_MainClass.configloader().getProperty("Zonepassword").trim();
		click1(MyDeskDashboardRepo.profiledropdown);

//		click1(MyDeskDashboardRepo.Logout);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);
		driver.get(CoreApplicationUrl);
		Thread.sleep(6000);
		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

		Common.fluentWait("Core login Banner",
				LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(ZoneUserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + ZoneUserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(Zonepassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + Zonepassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(ZoneUserID);
				Log.info("Entered " + ZoneUserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Zonepassword);
				Log.info("Entered " + Zonepassword + " in password field");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(ZoneUserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(Zonepassword);
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

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(ZoneUserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(Zonepassword);
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

	public void LoginBCO() throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		String BCOUserID = MyDeskDashboardPage_MainClass.configloader2().getProperty("BCOUserID").trim();
		String BCOpassword = MyDeskDashboardPage_MainClass.configloader2().getProperty("BCOpassword").trim();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.tooltip').remove();");

		moveToElementAndClick(driver, MyDeskDashboardRepo.profiledropdownbutton);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);

		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

//		Common.fluentWait("Core login Banner",
//				LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(BCOUserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + BCOUserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(BCOpassword);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + BCOpassword + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(BCOUserID);
				Log.info("Entered " + BCOUserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(BCOpassword);
				Log.info("Entered " + BCOpassword + " in password field");
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(BCOUserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(BCOpassword);
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

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(BCOUserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(BCOpassword);
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

	public void LogintotheAgency() throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		String CoreApplicationUrl = MyDeskDashboardPage_MainClass.configloader1()
				.getProperty("CollectionAgencyApplicationUrl").trim();
		String CoreUserName_Agency_AH = MyDeskDashboardPage_MainClass.configloader1()
				.getProperty("CoreUserName_Agency_AH").trim();
		String CoreUserPassword_Agency_AH = MyDeskDashboardPage_MainClass.configloader1()
				.getProperty("CoreUserPassword_Agency_AH").trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		Thread.sleep(2000);
//		click1(MyDeskDashboardRepo.Logout);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);
		driver.get(CoreApplicationUrl);
		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

		Common.fluentWait("Core login Banner",
				LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName_Agency_AH);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + CoreUserName_Agency_AH + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword_Agency_AH);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + CoreUserPassword_Agency_AH + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
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

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName_Agency_AH);
				Log.info("Entered " + CoreUserName_Agency_AH + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword_Agency_AH);
				Log.info("Entered " + CoreUserPassword_Agency_AH + " in password field");
				driver.findElement(LoginPageRepo.LoginButton).click();
				Log.info("Clicked on login button");

				Log.info("Clicked on already login yes button and logged in again with valid credentials");
			} else {
				System.out.println("Element not clickable within the timeout.");
			}
			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");

		} catch (Exception e) {
			System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
			System.out.println("Already login pop up not appeared");
		}

	}

	public void clickCollection() {
		try {
//			Common.fluentWait("Collection", MyDeskDashboardRepo.Collection);
			click1(MyDeskDashboardRepo.Collection);
			ExtentTestManager.getTest().log(Status.PASS, "Collection is selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Collection Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void VerifyUnassignedSMAFields() {
		try {

			isDisplayed(MyDeskDashboardRepo.TypesofAccount, "TypesofAccount");
			isDisplayed(MyDeskDashboardRepo.AssetCategory, "AssetCategory");
			isDisplayed(MyDeskDashboardRepo.SMACategory, "SMACategory");
			isDisplayed(MyDeskDashboardRepo.NPACategory, "NPACategory");
			isDisplayed(MyDeskDashboardRepo.ZoneCO, "ZoneCO");
			isDisplayed(MyDeskDashboardRepo.Region, "Region");
			isDisplayed(MyDeskDashboardRepo.Branch, "Branch");
			isDisplayed(MyDeskDashboardRepo.Vertical, "Vertical");
			isDisplayed(MyDeskDashboardRepo.SchemeType, "SchemeType");
			isDisplayed(MyDeskDashboardRepo.ProductType, "ProductType");
			isDisplayed(MyDeskDashboardRepo.SchemeCode, "SchemeCode");
			isDisplayed(MyDeskDashboardRepo.Balance, "Balance");
			isDisplayed(MyDeskDashboardRepo.Overdue, "Overdue");
			isDisplayed(MyDeskDashboardRepo.DPD, "DPD");
			isDisplayed(MyDeskDashboardRepo.AssetTaggingType, "AssetTaggingType");
			isDisplayed(MyDeskDashboardRepo.Search, "Search");
			isDisplayed(MyDeskDashboardRepo.reset, "reset");
			isDisplayed(MyDeskDashboardRepo.Download, "Download");
			isDisplayed(MyDeskDashboardRepo.AllocateTo, "AllocateTo");
			isDisplayed(MyDeskDashboardRepo.Allocate, "Allocate");
			isDisplayed(MyDeskDashboardRepo.Close, "Close");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Collection Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickUnassignedAccountsSMA() {
		try {
			Common.fluentWait("UnassignedAccountsSMA", MyDeskDashboardRepo.UnassignedAccountsSMA);

			WebElement element = driver.findElement(MyDeskDashboardRepo.UnassignedAccountsSMA);

			// JavaScript Executor to move to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			click1(MyDeskDashboardRepo.UnassignedAccountsSMA);
			ExtentTestManager.getTest().log(Status.PASS, "Collection is selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Collection Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void UnassignedAccountsNPA() {
		try {
			Common.fluentWait("UnassignedAccountsNPA", MyDeskDashboardRepo.UnassignedAccountsNPA);

			WebElement element = driver.findElement(MyDeskDashboardRepo.UnassignedAccountsNPA);

			// JavaScript Executor to move to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			click1(MyDeskDashboardRepo.UnassignedAccountsSMA);
			ExtentTestManager.getTest().log(Status.PASS, "Unassigned Accounts NPA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Unassigned Accounts NPA");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void BVAvalidations() {
		try {
			String minoverdue = driver.findElement(MyDeskDashboardRepo.secondInput).getAttribute("min");
			String maxoverdue = driver.findElement(MyDeskDashboardRepo.secondInput).getAttribute("maxlength");
			if (minoverdue.contains("0")) {
				ExtentTestManager.getTest().log(Status.PASS, "minimum value of overdue to DPD is '0'");
				if (maxoverdue.contains("10")) {
					ExtentTestManager.getTest().log(Status.PASS, "maximum length value of overdue to DPD is '100'");
				} else
					ExtentTestManager.getTest().log(Status.FAIL, "DPDDaysOperatorShouldNotBeEmpty successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "min and masecondInputx value range is not satisfied");
			String minDPD = driver.findElement(MyDeskDashboardRepo.secondInput).getAttribute("min");
			Random rand = new Random();
			int randomValue = 10 + rand.nextInt(90); // 10 to 99
			int randovalue2 = randomValue - 2;
			String value = String.valueOf(randomValue);
			String value2 = String.valueOf(randovalue2);
			System.out.println("Random number: " + randomValue);
			driver.findElement(MyDeskDashboardRepo.secondInput).sendKeys(value);
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.Allocated, "Allocated");
			click(MyDeskDashboardRepo.Search, "search");

			// dpd form and to both validation
			click(MyDeskDashboardRepo.reset, "reset");
			driver.findElement(MyDeskDashboardRepo.secondInput).sendKeys(value);
			driver.findElement(MyDeskDashboardRepo.DPDTO).sendKeys(value2);
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.Allocated, "Allocated");
			click(MyDeskDashboardRepo.Search, "search");
			DPD_Error();
			int randomNegativeValue = -(1 + rand.nextInt(9)); // -10 to -99

			// Convert the number to String
			String negativeValueStr = String.valueOf(randomNegativeValue);

			click(MyDeskDashboardRepo.Search, "search");
//	MyDeskDashboardPage_MainClass.DPDDaysOperatorShouldNotBeEmpty();
			driver.findElement(MyDeskDashboardRepo.secondInput).sendKeys(negativeValueStr);
			ExtentTestManager.getTest().log(Status.PASS, "BVA validations are done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "BVA validations are failed");
		}
	}

	public void ECP_Validations() {
		try {
			String minoverdue = driver.findElement(MyDeskDashboardRepo.DPDTO).getAttribute("min");
			String maxoverdue = driver.findElement(MyDeskDashboardRepo.DPDTO).getAttribute("maxlength");
			if (minoverdue.contains("0")) {
				ExtentTestManager.getTest().log(Status.PASS, "minimum value of overdue to DPD to is '0'");
				if (maxoverdue.contains("10")) {
					ExtentTestManager.getTest().log(Status.PASS, "maximum length value of overdue to DPD to is '10'");
				} else
					ExtentTestManager.getTest().log(Status.FAIL, "DPDDaysOperatorShouldNotBeEmpty successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "min and masecondInputx value range is not satisfied");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExtentTestManager.getTest().log(Status.FAIL, "ECP validation failed");
			e.printStackTrace();
		}
	}

	public void clickReset() {
		try {
			Common.fluentWait("reset", MyDeskDashboardRepo.reset);
			click1(MyDeskDashboardRepo.reset);
			ExtentTestManager.getTest().log(Status.PASS, "reset is selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select reset Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void SelectBCOUser() throws InterruptedException {
		try {
			String BCOUSername1 = driver.findElement(MyDeskDashboardRepo.SelectBCOUser).getAttribute("aria-label");
			BCOUSername = BCOUSername1.replaceAll(">", "");
			By SelectBCO = By.xpath("(//*[contains(@aria-label,'" + BCOUSername + "')])");
			click1(SelectBCO);
			ExtentTestManager.getTest().log(Status.PASS, "BCO user selected");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "unable to select the BCO user");
		}

	}

	public void GetBCOUserCredentials() throws InterruptedException {
		try {
//			>Ahmu
			driver.findElement(MyDeskDashboardRepo.BCOUserNameInput).sendKeys(BCOUSername);
			click(MyDeskDashboardRepo.Search);
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "BCO Search operation done successfully");
			click(MyDeskDashboardRepo.Action);
			click(MyDeskDashboardRepo.ResetPassword);
			WaitLoader();
			String text = driver.findElement(MyDeskDashboardRepo.passwordresetsuccessfully).getText();

			Pattern pattern = Pattern.compile("IBU\\d+"); // Matches User ID (IBU0001573)
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
			SaveConfigFileBCO(userId, password);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "BCO search operation failed");
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

	public void verifyTheAccountType() {

		String AccountType = driver.findElement(MyDeskDashboardRepo.AccountType).getText();
		if (AccountType.contains("Not Allocated")) {
			ExtentTestManager.getTest().log(Status.PASS, "Account type matched in the Search grid result");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Account type matched in the Search grid result");
		}
	}

	public void Filedownloadedsuccessfully() {
		Common.fluentWait("Filedownloadedsuccessfully", MyDeskDashboardRepo.Filedownloadedsuccessfully);
		if (driver.findElement(MyDeskDashboardRepo.Filedownloadedsuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "File downloaded successfully successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to download File ");
		}
	}

	public void EnterallfieldsinLoanAtRisk() {
		Common.fluentWait("EnterallfieldsinLoanAtRisk", MyDeskDashboardRepo.EnterallfieldsinLoanAtRisk);
		if (driver.findElement(MyDeskDashboardRepo.EnterallfieldsinLoanAtRisk).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Enter all fields in LoanAtRisk displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to display Enter all fields in Loan At Risk ");
		}
	}

	public void SavedSuccessfully() {
		Common.fluentWait("SavedSuccessfully", MyDeskDashboardRepo.SavedSuccessfully);
		if (driver.findElement(MyDeskDashboardRepo.SavedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Disposition Saved Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to save disposition ");
		}
	}

	public void PleaseEnterSuccessfully() {
		Common.fluentWait("PleaseEnter", MyDeskDashboardRepo.PleaseEnter);
		if (driver.findElement(MyDeskDashboardRepo.PleaseEnter).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Please Enter all the details shown Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Please Enter all the details  ");
		}
	}

	public void Assignedsuccessfully() {
		Common.fluentWait("Assignedsuccessfully", MyDeskDashboardRepo.Assignedsuccessfully);
		if (driver.findElement(MyDeskDashboardRepo.Assignedsuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Assigned successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Assigned");
		}
	}

	public void DPD_Error() {
		Common.fluentWait("DPDError", MyDeskDashboardRepo.DPDError);
		if (driver.findElement(MyDeskDashboardRepo.DPDError).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "DPDError Displayed ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "DPD Error ");
		}
	}

	public void AllocationTypesShouldNotBeEmpty() {
		Common.fluentWait("AllocationTypesShouldNotBeEmpty", MyDeskDashboardRepo.AllocationTypesShouldNotBeEmpty);
		if (driver.findElement(MyDeskDashboardRepo.AllocationTypesShouldNotBeEmpty).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Allocation Types Should Not Be Empty displyed successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable Allocation Types Should Not BeEmpty");
		}
	}

	public int GetInitialdownloadCount() {
		int initialFileCount = getFileCount(downloadPath);
		return initialFileCount;
	}

	public void clickDashboard() {
		try {
			Common.fluentWait("Dashboard", MyDeskDashboardRepo.Dashboard);
			click1(MyDeskDashboardRepo.Dashboard);
			ExtentTestManager.getTest().log(Status.PASS, "Dashboard is selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Dashboard Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickMyDesk() {
		try {
			Common.fluentWait("My Desk", MyDeskDashboardRepo.MyDesk);
			click1(MyDeskDashboardRepo.MyDesk);
			ExtentTestManager.getTest().log(Status.PASS, "My Desk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select My Desk ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void FilterAllocationBCO() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.Allocated, "Allocated");
			click(MyDeskDashboardRepo.AssignedToDropDown, "AssignedTo");
			SelectBCOUser();

			click(MyDeskDashboardRepo.Search, "Search");
			WaitLoader();
			// Wait for the element if it's there
			List<WebElement> noRecords = driver.findElements(MyDeskDashboardRepo.NoRecordsToDisplay);
			if (!noRecords.isEmpty() && noRecords.get(0).isDisplayed()) {
				click(MyDeskDashboardRepo.reset, "reset");
				WaitLoader();
				FilterUnallocatedAccountsDownBCO();
			}
			ExtentTestManager.getTest().log(Status.PASS, "Filter allocation done successfully");

		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void FilterUnallocatedAccountsDownBCO() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");

			ExtentTestManager.getTest().log(Status.PASS, "not allocated accounts selected ");

			click(MyDeskDashboardRepo.Search, "Search");
			WaitLoader();
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");
			SelectBCOUser();
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");
			click(MyDeskDashboardRepo.Assign, "Assign");
			// assigned successfully
			Assignedsuccessfully();
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Filter allocation done successfully of unallocated ");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void click(By locator, String elementName) {
		try {
			Common.fluentWait(elementName, locator);
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
		}
	}

	public void IsElementEnabled(By locator, String elementName) {
		try {
//			Common.fluentWait(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + "is Active after the Reset(Parameter Activation)");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive (Parameter Activation) ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void IsElementDisabled(By locator, String elementName) {
		try {
//			Common.fluentWait(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName + "is Active before Reset (Parameter activation) ");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive before Reset");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void SelectAgency() {
		try {

			By SelectAgency = By.xpath("//*[contains(@aria-label,'" + AgencyName + "')]");
			moveToElementAndClick(driver, SelectAgency);
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Selecte Agency ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void selectSMAcategory() throws InterruptedException {

		click1(MyDeskDashboardRepo.SMACate0);

		click1(MyDeskDashboardRepo.SMACate1);

		click1(MyDeskDashboardRepo.SMACate2);
		ExtentTestManager.getTest().log(Status.PASS, "selected all the types of SMA accounts");

	}

	public void SelectCurrentDate() throws InterruptedException, ParseException {

		try {
			click(MyDeskDashboardRepo.ActionDateFrom);
			String today = String.valueOf(LocalDate.now().getDayOfMonth());

			// Construct a dynamic XPath using today's date
			String xpath = "//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
					+ today + "']";

			// Locate the element and click it
			WebElement currentDate = driver.findElement(By.xpath(xpath));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", currentDate);
			ExtentTestManager.getTest().log(Status.PASS, "Current date for Action from selected");

			click(MyDeskDashboardRepo.ActionDateto);
			String xpath2 = "//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
					+ today + "']";
			WebElement currentDate2 = driver.findElement(By.xpath(xpath));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", currentDate2);
			ExtentTestManager.getTest().log(Status.PASS, "Current date for Action To selected");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Faied to select the current date");

		}

	}

	public void isAccountAllocatedSuccessfully() {
		Common.fluentWait("AccountAllocatedSuccessfully", MyDeskDashboardRepo.AccountAllocatedSuccessfully);
		if (driver.findElement(MyDeskDashboardRepo.AccountAllocatedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Account Allocated Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Account Allocated ");
		}
	}

	public void AccessDeniedError() {
		Common.fluentWait("AccessDenied", MyDeskDashboardRepo.AccessDenied);
		if (driver.findElement(MyDeskDashboardRepo.AccessDenied).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Access Denied Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Access Denied message not displayed");
		}
	}

	public void AssetCategoryRequired() {
		Common.fluentWait("AssetCategoryRequired", MyDeskDashboardRepo.AssetCategoryRequired);
		if (driver.findElement(MyDeskDashboardRepo.AssetCategoryRequired).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "AssetCategoryRequired message displayed ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "AssetCategoryRequired message not displayed");
		}
	}

	public void DPDDaysOperatorShouldNotBeEmpty() {
		Common.fluentWait("DPDDaysOperatorShouldNotBeEmpty", MyDeskDashboardRepo.DPDDaysOperatorShouldNotBeEmpty);
		if (driver.findElement(MyDeskDashboardRepo.DPDDaysOperatorShouldNotBeEmpty).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "DPDDaysOperatorShouldNotBeEmpty successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Account Allocated ");
		}
	}

	public void getTotalAccount() throws Exception, SQLException, IOException {
		try {
			count = driver.findElement(MyDeskDashboardRepo.TotalAccountSelected).getText();
			if (count.contains("0")) {
				String query = "DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
				DBUtils.executeSQLStatement(query);
				clickMyDesk();
				clickDashboard();
				WaitLoader();
				clickUnassignedAccountsSMA();
				WaitLoader();
				Common.fluentWait("Dashboard", MyDeskDashboardRepo.Download);
				WaitLoader();
			}
			ExtentTestManager.getTest().log(Status.PASS, "Total account selected " + count);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Total account selected");

		}
	}

	public void getTotalAccountAllocation() throws Exception, SQLException, IOException {
		countAlloaction = driver.findElement(MyDeskDashboardRepo.Noofallocations).getText();
		if (count.equalsIgnoreCase(countAlloaction)) {
			ExtentTestManager.getTest().log(Status.PASS, "Total account selected " + count);
			try {
				if (driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()) {
//					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Total account selected " + count);
			try {
				if (driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()) {
//					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getTotalAccountAllocation_withClose() throws Exception, SQLException, IOException {
		countAlloaction = driver.findElement(MyDeskDashboardRepo.Noofallocations).getText();
		if (count.equalsIgnoreCase(countAlloaction)) {
			ExtentTestManager.getTest().log(Status.PASS, "Total account selected " + count);
			try {
				if (driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()) {
					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Total account selected " + count);
			try {
				if (driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()) {
					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void checkNewExcelDownloadedorNot(int count) {

		File downloadedFile = waitForNewFile(downloadPath, count, 15);
		if (downloadedFile != null) {
			System.out.println("✅ File downloaded: " + downloadedFile.getName());

			// Open the Excel file and read content
			readExcelFile(downloadedFile);
			ExtentTestManager.getTest().log(Status.PASS, "File download done ");
		} else {
			System.out.println("❌ File download failed!");
			ExtentTestManager.getTest().log(Status.FAIL, "File download failed!");
		}
		readExcelFile(downloadedFile);
	}

	// Method to open and read an Excel file
	public static void readExcelFile(File excelFile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(excelFile);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0); // Read first sheet

			System.out.println("📖 Reading Excel File: " + excelFile.getName());
			for (Row row : sheet) {
				for (Cell cell : row) {
					System.out.print(cell.toString() + "\t");
				}
				System.out.println();
			}
			ExtentTestManager.getTest().log(Status.PASS, "Downloaded Excel File read completed ");
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Error reading the Excel file.");
			ExtentTestManager.getTest().log(Status.FAIL, "Downloaded Excel File read failed ");
		}
	}

	public static int getFileCount(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		return (files != null) ? files.length : 0;
	}

	// Method to wait for a new file to be downloaded
	public static File waitForNewFile(String dirPath, int oldCount, int timeoutSeconds) {
		int elapsedTime = 0;
		while (elapsedTime < timeoutSeconds) {
			File[] files = new File(dirPath).listFiles((d, name) -> name.endsWith(".xlsx")); // Only check .xlsx files
			if (files != null && files.length > oldCount) {
				return getLatestFile(dirPath, ".xlsx"); // Return latest file
			}
			try {
				Thread.sleep(1000);
				elapsedTime++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null; // No new file detected
	}

	public void ExcelRead() {
		try {
			String downloadDir = System.getProperty("user.home") + "\\Downloads"; // Change as needed
			// Get the latest downloaded file
			File excelFile = getLatestFile(downloadDir, ".xlsx");
			if (excelFile != null) {
				System.out.println("Downloaded file: " + excelFile.getName());
				ExtentTestManager.getTest().log(Status.PASS, "Downloaded file: " + excelFile.getName());
				readExcelAndValidate(excelFile);
			} else {
				System.out.println("No Excel file found in the download directory.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error message " + e.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "No Excel file found in the download directory.");
		}
	}

	public void ExcelReadAndStoreForCompare() {
		try {
			// Get the latest downloaded file
			File excelFile1 = getLatestFile(downloadPath, ".xlsx");
			if (excelFile1 != null) {
				System.out.println("Downloaded file: " + excelFile1.getName());

				List<String> columnData1 = readExcelColumn(excelFile1.getAbsolutePath(), 1);
				for (String item : columnData1) {
					System.out.println(item);
				}
				storeColumnData(columnData1, "column1.txt"); // Store for later comparison
				ExtentTestManager.getTest().log(Status.PASS, "Downloaded file: ");
			} else {
				System.out.println("No Excel file found in the download directory.");
				ExtentTestManager.getTest().log(Status.FAIL, "No Excel file found in the download directory.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error message: " + e.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "No Excel file found in the download directory.");
		}
	}

	// Step 2: Read & Compare Second File Data
	public void ExcelRead2AndStoreForCompare() {
		try {
			// Get the latest downloaded file
			File excelFile2 = getLatestFile(downloadPath, ".xlsx");
			if (excelFile2 != null) {
				System.out.println("Downloaded file: " + excelFile2.getName());

				// Read the column from the new file
				List<String> columnData2 = readExcelColumn(excelFile2.getAbsolutePath(), 7);
				for (String item : columnData2) {
					System.out.println("columnData2" + item);
				}
				// Read stored column1.txt data
				File storedFile = new File(downloadPath + File.separator + "column1.txt");
				if (storedFile.exists()) {
					List<String> columnData1 = Files.readAllLines(storedFile.toPath());

					// Compare the columns
					compareColumns(columnData1, columnData2);
				} else {
					System.out.println("Stored column data (column1.txt) not found!");
				}
			} else {
				System.out.println("No Excel file found in the download directory.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error message: " + e.getMessage());
			ExtentTestManager.getTest().log(Status.FAIL, "No Excel file found in the download directory.");
		}
	}

	// Method to Get Latest File in a Directory
	public static File getLatestFile(String dirPath, String fileExtension) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles((d, name) -> name.endsWith(fileExtension));

		if (files == null || files.length == 0) {
			return null;
		}

		File latestFile = files[0];
		for (File file : files) {
			if (file.lastModified() > latestFile.lastModified()) {
				latestFile = file;
			}
		}
		ExtentTestManager.getTest().log(Status.PASS, "latest file present in the directory returned");
		return latestFile;
	}

	// Read Specific Column from Excel File
	public static List<String> readExcelColumn(String filePath, int columnIndex) throws Exception {
		FileInputStream file = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		List<String> columnData = new ArrayList<>();
		for (Row row : sheet) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				columnData.add(cell.toString().trim()); // Trim whitespace
			}
		}
		workbook.close();
		return columnData;
	}

	// Store Column Data in a File
	public static void storeColumnData(List<String> data, String fileName) throws Exception {
		File file = new File(downloadPath + File.separator + fileName);
		Files.write(file.toPath(), data);
	}

	// Compare Two Columns
	public static void compareColumns(List<String> col1, List<String> col2) {
		if (col1.equals(col2)) {
			System.out.println("✅ Columns match!");
			ExtentTestManager.getTest().log(Status.PASS, "Columns match!");
		} else {
			System.out.println("❌ Columns do NOT match.");
			ExtentTestManager.getTest().log(Status.FAIL, "Columns do NOT match.");
		}
	}

	public static void readExcelAndValidate(File file) {
		try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

			// Read Column Names (Header Row)
			Row headerRow = sheet.getRow(0);
			System.out.print("Column Names from Excel: ");

			List<String> columnNamesUIList = new ArrayList<>();
			for (Cell cell : headerRow) {
				String columnName = cell.getStringCellValue().trim().replaceAll("\\s+", " "); // Trim & normalize spaces
				columnNamesUIList.add(columnName);
			}
			System.out.println();

			// Expected column names
			List<String> columnNames = Arrays.asList("Sl No", "Account No", "Account Name", "Branch /Sol ID",
					"Branch Name", "Account Type", "Product", "O/s Amount as of Date", "Overdue Amount as of Date",
					"Collection Amount", "Corporate BCBF", "Contact No", "OverDue Date", "Current O/s Amt as of Date",
					"Current Overdue Amount As on Date", "Current Collection Amount", "High Risk", "Last Contact Date",
					"Last Contact Disposition", "Next Action Date", "Next Action Owner", "SMS Sent",
					"Co Borrower Contact Details", "Co-Borrower Address", "Guarantor Contact Details",
					"Guarantor Address", "Other Contact Details", "DPD", "Assigned To", "Assignee Name",
					"Asset Category", "Account Category", "Sector", "Scheme Type", "Scheme");

			System.out.println("Expected Column Names: " + columnNames);

//			// Compare lists
//			if (columnNames.equals(columnNamesUIList)) {
//				System.out.println("Coulmns names match of downloaded Excel sheet");
//				ExtentTestManager.getTest().log(Status.PASS, "Total count is matched ");
//			} else {
//				System.out.println("❌ Lists are different.");
//				System.out.println("Excel Column Names  : " + columnNamesUIList);
//				System.out.println("Expected Column Names: " + columnNames);
//				ExtentTestManager.getTest().log(Status.FAIL, "Coulmns names match of downloaded Excel sheet");
//			}
//			Collections.sort(columnNamesUIList);
//			Collections.sort(columnNames);
//
//			if (columnNames.equals(columnNamesUIList)) {
//				System.out.println("Coulmns names match of downloaded Excel sheet");
//				ExtentTestManager.getTest().log(Status.PASS, "Total count is matched ");
//			} else {
//				System.out.println("❌ Lists are different.");
//				System.out.println("Excel Column Names  : " + columnNamesUIList);
//				System.out.println("Expected Column Names: " + columnNames);
//				ExtentTestManager.getTest().log(Status.FAIL, "Coulmns names match of downloaded Excel sheet");
//			}
			// Read Last Row Values
			int lastRowNum = sheet.getLastRowNum();
			Row lastRow = sheet.getRow(lastRowNum);

			System.out.print("Last Row Values: ");
			for (Cell cell : lastRow) {
				System.out.print(getCellValue(cell) + " | ");
			}
			System.out.println();

			if (lastRow != null) {
				// Get the first column value (Column Index 0)
				Cell firstColumnCell = lastRow.getCell(0);
				String firstColumnValue = getCellValue(firstColumnCell);
				double firstColumnNumber = Double.parseDouble(firstColumnValue.trim());
				double countNumber = Double.parseDouble(count.trim());
				if (firstColumnNumber == countNumber) {
					ExtentTestManager.getTest().log(Status.PASS, "Total count is matched ");
				} else {
					ExtentTestManager.getTest().log(Status.FAIL, "Total count is not matched");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCellValue(Cell cell) {
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
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

	public void isDisplayedActive(By locator, String elementName) {
		try {
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, " Element is displayed.");
				ExtentTestManager.getTest().log(Status.PASS, " Dropdown is Active After Reset is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void CheckCount(String elementName, String elementName1) {
		try {

			By locator = By.xpath("//*[contains(text(),'" + elementName + "')]/..//*[contains(text(),'" + elementName1
					+ "')]/..//*[@class='tile-total']");

			WebElement element = driver.findElement(locator);
			String count = element.getText();
			if (count.isBlank()) {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName1 + " is not displayed." + " Count present is:" + count);

			} else {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is displayed.");
				ExtentTestManager.getTest().log(Status.PASS,
						elementName1 + " is displayed." + " Count present is:" + count);

			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void CheckCountWithAllocatedCount(String elementName, String elementName1) {
		try {

			By locator = By.xpath("//*[contains(text(),'" + elementName + "')]/..//*[contains(text(),'" + elementName1
					+ "')]/..//*[@class='tile-total']");

			WebElement element = driver.findElement(locator);
			String count1 = element.getText();
			if (count1.isBlank()) {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName1 + " is not displayed." + " Count present is:" + count);

			} else {
				if (count1.contains(count))

					ExtentTestManager.getTest().log(Status.PASS,
							elementName1 + " is displayed." + " Count present is:" + count);
				ExtentTestManager.getTest().log(Status.PASS,
						" Accounts allocated present in the Branch Attention Required Accounts SMA.");

			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String generateAlphanumericUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10); // 10-character alphanumeric
	}

	public void FilterAllocation() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.Allocated, "Allocated");
			click(MyDeskDashboardRepo.AssignedToDropDown, "AssignedTo");

			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + AgencyName + "')])[2]");
			moveToElementAndClick(driver, SelectAgency);
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");

			click(MyDeskDashboardRepo.Search, "Search");
			WaitLoader();
			// Wait for the element if it's there
			List<WebElement> noRecords = driver.findElements(MyDeskDashboardRepo.NoRecordsToDisplay);
			if (!noRecords.isEmpty() && noRecords.get(0).isDisplayed()) {
				click(MyDeskDashboardRepo.reset, "reset");
				WaitLoader();
				FilterUnallocatedAccountsDown();
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void FilterAllocation_1() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.Allocated, "Allocated");
//			click(MyDeskDashboardRepo.AssignedToDropDown, "AssignedTo");
//
//			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
//			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
//			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + AgencyName + "')])[2]");
//			moveToElementAndClick(driver, SelectAgency);
//			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");

			click(MyDeskDashboardRepo.Search, "Search");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void FilterAllocation_1Unallocated() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");
//			click(MyDeskDashboardRepo.AssignedToDropDown, "AssignedTo");
//
//			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
//			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
//			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + AgencyName + "')])[2]");
//			moveToElementAndClick(driver, SelectAgency);
//			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");

			click(MyDeskDashboardRepo.Search, "Search");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String getNewAccountCount() {
		String value = driver.findElement(MyDeskDashboardRepo.NewAccountDashboard).getText();
		System.out.println(value);

		// Define a regex pattern to extract the number after "No:"
		Pattern pattern = Pattern.compile("No:\\s*(\\d+)");
		Matcher matcher = pattern.matcher(value);

		if (matcher.find()) { // Check if there's a match before accessing group(1)
			ExtentTestManager.getTest().log(Status.PASS, "Match found!");
			return matcher.group(1); // Extract and return the number

		} else {
			System.out.println("No match found!"); // Debugging statement
			ExtentTestManager.getTest().log(Status.FAIL, "No match found!");
			return "0"; // Or handle this case appropriately
		}

	}

	public String getNewAccountCount_new(By xpath) {
		String value = driver.findElement(xpath).getText();
		System.out.println(value);

		// Define a regex pattern to extract the number after "No:"
		Pattern pattern = Pattern.compile("No:\\s*(\\d+)");
		Matcher matcher = pattern.matcher(value);

		if (matcher.find()) { // Check if there's a match before accessing group(1)
			ExtentTestManager.getTest().log(Status.PASS, "Account count Match found!");
			return matcher.group(1); // Extract and return the number
		} else {
			System.out.println("No match found!"); // Debugging statement
			ExtentTestManager.getTest().log(Status.FAIL, "Account count No match found!");
			return "0"; // Or handle this case appropriately
		}

	}

	public void compareDataWithExcel() throws InterruptedException {

		// Wait for the file to download (add explicit wait or sleep)
		Thread.sleep(5000);

		// Step 2: Locate the table rows and extract the required data
		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

		List<String> webTableData = new ArrayList<>();
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			if (cells.size() >= 4) { // Ensure we have at least 4 columns
				String rowData = cells.get(0).getText() + " " + cells.get(1).getText() + " " + cells.get(2).getText()
						+ " " + cells.get(3).getText();
				webTableData.add(rowData);
			}
		}

		// Step 3: Read data from Excel

		String filePath = getLatestExcelFile(downloadPath);
		if (filePath == null) {
			System.out.println("❌ No Excel file found in Downloads.");
			return;
		}
		List<String> excelData = readExcelData(filePath);

		// Step 3: Print Web Table Data
		System.out.println("\n🔹 Web Table Data:");
		for (String data : webTableData) {
			System.out.println(data);
		}

		// Step 4: Print Excel Data
		System.out.println("\n📄 Excel Data:");
		for (String data : excelData) {
			System.out.println(data);
		}
		// Step 4: Compare Web Table Data with Excel Data
		if (excelData.containsAll(webTableData)) {
			System.out.println("✅ Data matches!");
			ExtentTestManager.getTest().log(Status.PASS, "Data matches with Excel");
		} else {
			System.out.println("❌ Data does not match.");
			ExtentTestManager.getTest().log(Status.FAIL, "Data matches with Excel");
		}
		for (int i = 0; i < webTableData.size(); i++) {
			String[] webParts = webTableData.get(i).split(" ");
			String[] excelParts = excelData.get(i).split(" ");

			if (!webParts[0].equals(excelParts[0]) || !webParts[1].equalsIgnoreCase(excelParts[1])
					|| !webParts[2].equals(excelParts[2])) {
				System.out.println("❌ Mismatch at row " + i);
			}
		}
	}

	public int compareDataWithExcelDownload() throws InterruptedException, IOException {

		// Path to your downloaded Excel file

		// Expected column names
		String[] expectedColumns = { "Zone", "Region", "Branch", "Loan No", "Customer Name", "Loan Amount",
				"Outstanding Amount", "SMA Category", "NPA Category", "DPD", "Sector", "Scheme Type", "Scheme",
				"Assigned To", "Assignee ID", "Assignee Name"

		};
		String filePath = getLatestExcelFile(downloadPath);
		if (filePath == null) {
			System.out.println("❌ No Excel file found in Downloads.");
//			    return;
		}
		// Read the Excel file
		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
		Row headerRow = sheet.getRow(0); // First row contains headers

		// Iterate through header cells and validate
		Iterator<Cell> cellIterator = headerRow.cellIterator();
		int index = 0;
		boolean isValid = true;

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String columnName = cell.getStringCellValue().trim(); // Read cell value

			if (index >= expectedColumns.length || !columnName.equals(expectedColumns[index])) {
				System.out.println("Mismatch at column " + (index + 1) + ": Expected '" + expectedColumns[index]
						+ "', Found '" + columnName + "'");
				isValid = false;
			}
			index++;
		}

		// Close workbook and file stream
		workbook.close();
		fis.close();

		if (isValid) {
			System.out.println("Column names are correct!");
			ExtentTestManager.getTest().log(Status.PASS, "Column names are correct!");
		} else {
			System.out.println("Column names do not match!");
			ExtentTestManager.getTest().log(Status.FAIL, "Column names do not match!");
		}

		// how many rows

		int rowCount = sheet.getLastRowNum(); // Total number of rows (excluding header)
		System.out.println("Total Number of Rows (excluding header): " + rowCount);

		// contains SMA Accounts
		boolean isValidColumn = true;

		// Iterate through rows starting from row index 1 (to skip header)
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue; // Skip if the row is empty

			Cell smaCell = row.getCell(7); // Column H (index starts from 0)

			if (smaCell != null && smaCell.getCellType() == CellType.STRING) {
				String smaValue = smaCell.getStringCellValue().trim();

				// Check if value contains "SMA"
				if (!smaValue.startsWith("SMA")) {
					System.out.println("Invalid value at row " + (i + 1) + ": " + smaValue);
					isValidColumn = false;
				}
			} else {
				System.out.println("Empty or invalid cell at row " + (i + 1));
				isValidColumn = false;
			}
		}

		// Close workbook and file stream
		workbook.close();
		fis.close();

		if (isValidColumn) {
			System.out.println("All values in Column H are valid SMA values.");
			ExtentTestManager.getTest().log(Status.PASS, "All values in Column H are valid SMA values.");
		} else {
			System.out.println("There are invalid values in Column H.");
			ExtentTestManager.getTest().log(Status.FAIL, "There are invalid values in Column H.");
		}

		return rowCount;
	}

	public void checkExcelDataFormats() {
		String filePath;

		filePath = getLatestExcelFile(downloadPath);

		if (filePath == null) {
			System.out.println("❌ No Excel file found in Downloads.");
			ExtentTestManager.getTest().log(Status.FAIL, " No Excel file found in Downloads.");
			return;
		}
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0); // Read first sheet
			System.out.println("📄 Checking Data Formats in Excel...");

			for (Row row : sheet) {
				for (Cell cell : row) {
					CellType cellType = cell.getCellType(); // Get data type

					// Print cell data & format
//                    System.out.print("[" + cell.toString() + "] - Format: ");
					switch (cellType) {
					case STRING:
						System.out.println("TEXT");
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.println("DATE");
						} else {
							System.out.println("NUMBER");
						}
						break;
					case BOOLEAN:
						System.out.println("BOOLEAN");
						break;
					case FORMULA:
						System.out.println("FORMULA");
						break;
					case BLANK:
						System.out.println("BLANK");
						break;
					default:
						System.out.println("UNKNOWN FORMAT");
					}
				}
//                System.out.println("------------------------");
			}
		} catch (IOException e) {
			System.out.println("❌ Error reading Excel file: " + e.getMessage());
		}
	}

	public static List<String> readExcelData(String filePath) {
		List<String> data = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(new File(filePath)); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0); // Read first sheet
			for (Row row : sheet) {
				StringBuilder rowData = new StringBuilder();
				for (Cell cell : row) {
					rowData.append(cell.toString()).append(" ");
				}
				data.add(rowData.toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String getNewAccountCount_FromGrid() {
		List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

		// Get the number of rows
		int rowCount = rows.size();
		if (rowCount == 1) {
			if (driver.findElement(MyDeskDashboardRepo.NoRecordsToDisplay).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.FAIL, "No record found");
			} else {
				String value = String.valueOf(rowCount);
				System.out.println("Total number of rows in the table: " + rowCount);
				ExtentTestManager.getTest().log(Status.PASS, "One record found");
			}
		}
		String value = String.valueOf(rowCount);
		System.out.println("Total number of rows in the table: " + rowCount);
		ExtentTestManager.getTest().log(Status.PASS, "No.of record found are" + value);
		return value;
	}

	public String countFromBCOLoanAtRiskGrid() {
		String count = driver.findElement(MyDeskDashboardRepo.GridAccount).getText();
		return count;
	}

	public int checktheCount(int ExpectedValue, int ValueFromApplication) {

		return ExpectedValue - ValueFromApplication;

	}

	public void VerifyCount() {
		String GridCount = getNewAccountCount_FromGrid();
		// this count should be one
		int Expectedvalue = 1;
		int Ans = checktheCount(Integer.parseInt(GridCount), Expectedvalue);
		if (Ans == 0) {
			ExtentTestManager.getTest().log(Status.PASS, "One record found");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "No record found");
		}
	}

	public String getNewAccountCount_FromGridMonthly() {
		List<WebElement> rows = driver.findElements(By.xpath("(//tbody)[3]/tr"));

		// Get the number of rows
		int rowCount = rows.size();
		String value = String.valueOf(rowCount);
		System.out.println("Total number of rows in the table: " + rowCount);
		return value;
	}

	public void DispositionHistoryVerification() {

		isDisplayed(MyDeskDashboardRepo.ActionDoneBy, "ActionDoneBy");
		isDisplayed(MyDeskDashboardRepo.ActionDoneByName, "ActionDoneByName");

	}

	public void compareBothTheValues(String value1, String value2) {
		String firstStr = value1;
		String secondStr = value2; // This should be 1 greater than the first

		int firstNumber = Integer.parseInt(firstStr);
		int secondNumber = Integer.parseInt(secondStr);

		if (secondNumber == firstNumber + 1) {
			System.out.println("The second number is exactly 1 greater than the first.");
			ExtentTestManager.getTest().log(Status.PASS,
					"After assigning the account my accounts New accounts count is increased by 1");
		} else {
			System.out.println("The condition is not met.");
			ExtentTestManager.getTest().log(Status.FAIL, "Count is not increased");
		}
	}

	public void compareBothTheValuesFirstGreater(String value1, String value2) {
		String firstStr = value1;
		String secondStr = value2; // This should be 1 greater than the first

		int firstNumber = Integer.parseInt(firstStr);
		int secondNumber = Integer.parseInt(secondStr);
		if (firstNumber == secondNumber + 1) {
			System.out.println("The second number is exactly 1 greater than the first.");
			ExtentTestManager.getTest().log(Status.PASS,
					"After assigning the account my accounts New accounts count is increased by 1");
		} else {
			System.out.println("The condition is not met.");
			ExtentTestManager.getTest().log(Status.FAIL, "Count is not increased");
		}
	}

	public void ClickonLastActionbuttonFromGrid() {
		try {
			List<WebElement> elements = driver.findElements(By.xpath("//*[@id='dropdownMenu2']"));
			int count = elements.size();
			By ActionLast = By.xpath("(//*[@id='dropdownMenu2'])[" + count + "]");
			click(ActionLast, "ActionLast");
			ExtentTestManager.getTest().log(Status.PASS, +count + "th action button selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.PASS, "unable to select action button selected");
		}
	}

	public void compareBothTheValuesEqual(String value1, String value2) {
		String firstStr = value1;
		String secondStr = value2; // This should be 1 greater than the first

		int firstNumber = Integer.parseInt(firstStr);
		int secondNumber = Integer.parseInt(secondStr);

		if (secondNumber == firstNumber) {
			System.out.println("both the numbers are same, count is successfully matched");
			ExtentTestManager.getTest().log(Status.PASS,
					"Dashboard and grid count both the numbers are same, count is successfully matched");
		} else {
			System.out.println("Dashboard and grid count both the numbers are different");
			ExtentTestManager.getTest().log(Status.FAIL, "Dashboard and grid count both the numbers are different");
		}
	}

	public static int extractNumber(String input) {
		String[] lines = input.split("\n");
		for (String line : lines) {
			if (line.startsWith("No:")) {
				return Integer.parseInt(line.replaceAll("[^0-9]", "").trim());
			}
		}
		throw new IllegalArgumentException("No number found in input!");
	}

	public void VerifyTheGrid_Allocated() {

		String AsginedGrid = driver.findElement(MyDeskDashboardRepo.AssignedToGrid).getText();
		String AssigneeName = driver.findElement(MyDeskDashboardRepo.AssigneeName).getText();
		if (AsginedGrid.isEmpty()) {
			ExtentTestManager.getTest().log(Status.FAIL, " is not displayed.");
		} else
			ExtentTestManager.getTest().log(Status.PASS, " is not displayed.");

		if (AssigneeName.isEmpty()) {
			ExtentTestManager.getTest().log(Status.FAIL, " is not displayed.");
		} else
			ExtentTestManager.getTest().log(Status.PASS, " is not displayed.");

	}

	public void VerifyTheGrid_NotAllocated() {

		String AsginedGrid = driver.findElement(MyDeskDashboardRepo.AssignedToGrid).getText();
		String AssigneeName = driver.findElement(MyDeskDashboardRepo.AssigneeName).getText();
		if (AsginedGrid.isEmpty()) {
			ExtentTestManager.getTest().log(Status.PASS, " is not displayed.");
		} else
			ExtentTestManager.getTest().log(Status.FAIL, " is not displayed.");

		if (AssigneeName.isEmpty()) {
			ExtentTestManager.getTest().log(Status.PASS, " is not displayed.");
		} else
			ExtentTestManager.getTest().log(Status.FAIL, " is not displayed.");

	}

	public void AssignedOperation() throws InterruptedException {
		try {
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");
//indore branch

			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
			By SelectAgency = By.xpath("//*[contains(@aria-label,'" + AgencyName + "')]");
			moveToElementAndClick(driver, SelectAgency);
			click(MyDeskDashboardRepo.Assign, "Assign");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReAssignedOperation() throws InterruptedException {
		try {
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
//			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");
//indore branch

			String BranchName = driver.findElement(MyDeskDashboardRepo.BranchName).getText();
			String firstWord = BranchName.split(" ")[0];
			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + firstWord + "')])[3]");
			moveToElementAndClick(driver, SelectAgency);
			click(MyDeskDashboardRepo.Assign, "Assign");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AssignedOperation_1() throws InterruptedException {
		try {
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");

			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + AgencyName + "')])[2]");
			moveToElementAndClick(driver, SelectAgency);
			click(MyDeskDashboardRepo.Assign, "Assign");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyHeaders() {
		List<WebElement> headers = driver
				.findElements(By.xpath("//table[contains(@class, 'rz-grid-table')]//thead/tr/th"));

		// Print all header names
		System.out.println("Table Headers:");
		for (WebElement header : headers) {
			System.out.println(header.getText());
		}

		// Verify expected headers (Example: "Name", "Age", "City")
		String[] expectedHeaders = { "", "CUSTOMER NAME", "ACCOUNT NO", "AMOUNT", "PRN DUE", "INT DUE", "TOTAL DUE",
				"TOTAL OUTSTANDING", "ASSIGNED TO", "ASSIGNEE NAME", "LOAN TYPE", "ADDRESS", "ACTION" }; // Modify as
																											// needed
		boolean allHeadersMatch = true;

		for (int i = 0; i < expectedHeaders.length; i++) {
			if (!headers.get(i).getText().trim().equals(expectedHeaders[i])) {
				allHeadersMatch = false;
				System.out.println(
						"❌ Mismatch: Expected '" + expectedHeaders[i] + "', Found '" + headers.get(i).getText() + "'");
			}
		}

		if (allHeadersMatch) {
			System.out.println("✅ All headers match the expected names.");
		} else {
			System.out.println("⚠️ Header names do not match the expected values.");
		}
	}

	public void verifyHeadersNewAccounts() {
		List<WebElement> headers = driver
				.findElements(By.xpath("//table[contains(@class, 'rz-grid-table')]//thead/tr/th"));

		// Print all header names
		System.out.println("Table Headers:");
		for (WebElement header : headers) {
			System.out.println(header.getText());
		}

		// Verify expected headers (Example: "Name", "Age", "City")
		String[] expectedHeaders = { "", "Customer Name", "Account No", "Sanctioned Amount", "Prn Over Due",
				"Int Over Due", "Other Dues", "Total over Due", "Action" }; // Modify as
																			// needed
		boolean allHeadersMatch = true;

		for (int i = 0; i < expectedHeaders.length; i++) {
			if (!headers.get(i).getText().trim().equalsIgnoreCase(expectedHeaders[i])) {
				allHeadersMatch = false;
				System.out.println(
						"❌ Mismatch: Expected '" + expectedHeaders[i] + "', Found '" + headers.get(i).getText() + "'");
			}
		}

		if (allHeadersMatch) {
			System.out.println("✅ All headers match the expected names.");
		} else {
			System.out.println("⚠️ Header names do not match the expected values.");
		}
	}

	public void verifyHeadersNewAccountsBCO() {
		List<WebElement> headers = driver
				.findElements(By.xpath("//table[contains(@class, 'rz-grid-table')]//thead/tr/th"));

		// Print all header names
		System.out.println("Table Headers:");
		for (WebElement header : headers) {
			System.out.println(header.getText());
		}

		// Verify expected headers (Example: "Name", "Age", "City")
		String[] expectedHeaders = { "SL NO", "CUSTOMER NAME", "ACCOUNT NO", "SANCTIONED AMOUNT", "PRN OVER DUE",
				"INT OVER DUE", "OTHER DUES", "TOTAL OVER DUE", "ACTION" }; // Modify as
																			// needed
		boolean allHeadersMatch = true;

		for (int i = 0; i < expectedHeaders.length; i++) {
			if (!headers.get(i).getText().trim().equals(expectedHeaders[i])) {
				allHeadersMatch = false;
				System.out.println(
						"❌ Mismatch: Expected '" + expectedHeaders[i] + "', Found '" + headers.get(i).getText() + "'");
			}
		}

		if (allHeadersMatch) {
			System.out.println("✅ All headers match the expected names.");
			ExtentTestManager.getTest().log(Status.PASS, "All headers match the expected names.");
		} else {
			System.out.println("⚠️ Header names do not match the expected values.");
			ExtentTestManager.getTest().log(Status.FAIL, "Header names do not match the expected values.");
		}
	}

	public void SaveDisposition(String date) {
		try {
			click(MyDeskDashboardRepo.NextActionOwner, "NextActionOwner");
			WaitLoader();
			click(MyDeskDashboardRepo.NextActionOwnerValue, "NextActionOwnerValue");
			ExtentTestManager.getTest().log(Status.PASS, "Next Action Owner selected");

			click(MyDeskDashboardRepo.Disposition, "Disposition");
			WaitLoader();
			click(MyDeskDashboardRepo.NewBeaconIDs, "DispositionValue");
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Disposition selected");
			click(MyDeskDashboardRepo.SubDisposition, "SubDisposition");
			WaitLoader();
			click(MyDeskDashboardRepo.NewProduc, "SubDispositionValue");
			ExtentTestManager.getTest().log(Status.PASS, "Sub Disposition Value selected");
			// select the date
			click(MyDeskDashboardRepo.NextActionDateCalender, "NextActionDateCalender");

//		By Date = By.xpath("(//*[contains(text(),'" + date + "')])[7]");
			By Date = By.xpath(
					"//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
							+ date + "']");
			click(Date, "Date");
			driver.findElement(MyDeskDashboardRepo.Remarks).sendKeys("Comment");
			// code for date
			click(MyDeskDashboardRepo.Save, "Save");
			WaitLoader();
			click(MyDeskDashboardRepo.close, "close");

			SavedSuccessfully();
			ExtentTestManager.getTest().log(Status.PASS, "Disposition completed");
			WaitLoader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Disposition failed");
		}
	}

	public void SelectDate(String date) {
		try {
			By Date = By.xpath(
					"//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
							+ date + "']");
			click(Date, "Date");
			ExtentTestManager.getTest().log(Status.PASS, "Date Selected in the calnder");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select the date");
		}
	}

	public void SaveDispositionBCO(String date) {
		try {
			click(MyDeskDashboardRepo.NextActionOwner, "NextActionOwner");
			WaitLoader();
			click(MyDeskDashboardRepo.NextActionOwnerValue, "NextActionOwnerValue");
			ExtentTestManager.getTest().log(Status.PASS, "Next Action Owner selected");
			click(MyDeskDashboardRepo.Disposition, "Disposition");
			WaitLoader();
			click(MyDeskDashboardRepo.NewBeaconIDs, "DispositionValue");
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Disposition selected");
			click(MyDeskDashboardRepo.SubDisposition, "SubDisposition");
			WaitLoader();
			click(MyDeskDashboardRepo.NewProduc, "SubDispositionValue");
			ExtentTestManager.getTest().log(Status.PASS, "Sub Disposition Value selected");
			// select the date
			click(MyDeskDashboardRepo.NextActionDateCalender, "NextActionDateCalender");
			By Date = By.xpath(
					"//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
							+ date + "']");
			click(Date, "Date");
			driver.findElement(MyDeskDashboardRepo.Remarks).sendKeys("Comment");			
			click(MyDeskDashboardRepo.Save, "Save");
		click(MyDeskDashboardRepo.close, "close");
			SavedSuccessfully();
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Disposition completed");
		} catch (Exception e) {		
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Disposition failed");
		}
	}

	public void FilterNotAllocation() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Not Allocated");
			click(MyDeskDashboardRepo.Search, "Search");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void VerifyTheAccount() {
		try {

			Common.fluentWait("Category", MyDeskDashboardRepo.Category);
			String CategoryofAccounts = driver.findElement(MyDeskDashboardRepo.CategoryType).getText();
			if (CategoryofAccounts.contains("SMA")) {
				ExtentTestManager.getTest().log(Status.PASS, "SMA type of accounts are displayed in disposition page");
				ExtentTestManager.getTest().log(Status.PASS, "Accounts allocated successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "SMA type of accounts are displayed in disposition page");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void VerifyTheAccountSMA2() {
		try {

			Common.fluentWait("Category", MyDeskDashboardRepo.Category);
			String CategoryofAccounts = driver.findElement(MyDeskDashboardRepo.CategoryType).getText();
			if (CategoryofAccounts.contains("SMA 2")) {
				ExtentTestManager.getTest().log(Status.PASS, "SMA type of accounts are displayed in disposition page");
				ExtentTestManager.getTest().log(Status.PASS, "Accounts allocated successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "SMA type of accounts are displayed in disposition page");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void VerifyTheAccountSMA1() {
		try {

			Common.fluentWait("Category", MyDeskDashboardRepo.Category);
			String CategoryofAccounts = driver.findElement(MyDeskDashboardRepo.CategoryType).getText();
			if (CategoryofAccounts.contains("SMA 1")) {
				ExtentTestManager.getTest().log(Status.PASS, "SMA type of accounts are displayed in disposition page");
				ExtentTestManager.getTest().log(Status.PASS, "Accounts allocated successfully");
			} else
				ExtentTestManager.getTest().log(Status.FAIL, "SMA type of accounts are displayed in disposition page");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void FilterUnallocatedAccounts() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");

			ExtentTestManager.getTest().log(Status.PASS, "not allocated accounts selected ");

			click(MyDeskDashboardRepo.Search, "Search");
			WaitLoader();
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");
			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
			By SelectAgency = By.xpath("//*[contains(@aria-label,'" + AgencyName + "')]");
			moveToElementAndClick(driver, SelectAgency);// ******** no agency name came
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");
			click(MyDeskDashboardRepo.Assign, "Assign");
			// assigned successfully
			Assignedsuccessfully();
			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Allocvation completed");
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void FilterUnallocatedAccountsDown() {
		try {
			click(MyDeskDashboardRepo.AllocationTypeofAccountDropDown, "AllocationTypeofAccountDropDown");
			click(MyDeskDashboardRepo.NotAllocated, "NotAllocated");

			ExtentTestManager.getTest().log(Status.PASS, "not allocated accounts selected ");

			click(MyDeskDashboardRepo.Search, "Search");
			WaitLoader();
			click(MyDeskDashboardRepo.SelectAccount, "SelectAccount");
			AccountName = driver.findElement(MyDeskDashboardRepo.SelectAccountName).getText();
			click(MyDeskDashboardRepo.ReassignTo, "ReassignTo");
			Common.fluentWait("AgencyName", MyDeskDashboardRepo.AgencyName);
			AgencyName = driver.findElement(MyDeskDashboardRepo.AgencyName).getAttribute("title");
			By SelectAgency = By.xpath("(//*[contains(@aria-label,'" + AgencyName + "')])[2]");
			moveToElementAndClick(driver, SelectAgency);// ******** no agency name came
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator Selected Agency");
			click(MyDeskDashboardRepo.Assign, "Assign");
			// assigned successfully
			Assignedsuccessfully();
			WaitLoader();
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void ReadTableandVerify() {
		WebElement table = driver.findElement(By.xpath("//table[contains(@class, 'rz-grid-table')]"));
		List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
		boolean dataFound = false;
		// Iterate through each row
		for (WebElement row : rows) {
			// Get all columns (td elements) in the row
			List<WebElement> columns = row.findElements(By.tagName("td"));

			// Check if any column contains the required text
			for (WebElement column : columns) {
				if (column.getText().contains(AccountName)) {
					dataFound = true;
					break;
				}
			}

			if (dataFound)
				break;
		}

		// Print the result
		if (dataFound) {
			System.out.println("✅ Data is present in the table.");
			ExtentTestManager.getTest().log(Status.PASS,
					"Details are verified from my accounts page Customer is present");
		} else {
			System.out.println("❌ Data not found in the table.");
			ExtentTestManager.getTest().log(Status.FAIL,
					"Unable to  verify details from my accounts page Customer is not present ");
		}

	}

	public void clickAgentManagement() {
		try {
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			click1(AgentListPageRepo.AgentManagement);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent Management Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public String getLatestExcelFile(String folderPath) {
		File dir = new File(folderPath);
		File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx"));
		if (files == null || files.length == 0)
			return null;

		Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
		return files[0].getAbsolutePath();
	}

}