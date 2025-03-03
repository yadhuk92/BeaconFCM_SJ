package Core.MyDesk.Dashboard;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.time.Duration;
import java.util.List;

public class MyDeskDashboardPage extends Base_Class {
	private WebDriver driver;
	public static String count;
	public static String countAlloaction;
	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\MyDeskDashboardconfig2";

	// Constructor
	public MyDeskDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("MyDeskDashboardPage");
	}

	public void SelectFunctionalities() {

	}

	public void SelectFunctionalitiesSelectedornot() {

	}

	public void DeSelectFunctionalities() {

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
//	public void SaveConfigFileZone(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("ZoneUserID", Username);
//			properties.setProperty("Zonepassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void SaveConfigFileRegion(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("RegionUserID", Username);
//			properties.setProperty("Regionpassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void SaveConfigFileBranch(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("Core_BranchUserID", Username);
//			properties.setProperty("Core_Branchpassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

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
		} catch (IOException e) {
			e.printStackTrace();
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
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Region User ID from config2 file" + RegionUserID);

			String Regionpassword = MyDeskDashboardPage.configloader().getProperty("Regionpassword").trim();
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
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User ID config2 file is " + Core_BranchUserID);
			String Core_Branchpassword = MyDeskDashboardPage.configloader().getProperty("Core_Branchpassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User password  " + Core_Branchpassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());
		}
	}

	public void VerifyConfigBCOUser() throws IOException {
		// Define procedure name

		String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
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
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
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
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
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
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
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
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
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
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
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
			String ZoneUserID = MyDeskDashboardPage.configloader().getProperty("ZoneUserID").trim();
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
		String CallCenterURL = MyDeskDashboardPage.configloader1().getProperty("CallCenterURL").trim();		
		String CallCenterUserName = MyDeskDashboardPage.configloader1().getProperty("CallCenterUserName").trim();
		String CallCenterPassword = MyDeskDashboardPage.configloader1().getProperty("CallCenterPassword").trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		click1(MyDeskDashboardRepo.Logout);
		driver.get(CallCenterURL);
		  String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
          String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
          //System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);
          
          Common.fluentWait("Core login Banner", LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
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
	public void LogintotheNewlyCreatedUserBranch() throws IOException, InterruptedException {
		String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
		String Core_Branchpassword = MyDeskDashboardPage.configloader().getProperty("Core_Branchpassword").trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		click1(MyDeskDashboardRepo.Logout);

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

	}

	public void LogintotheNewlyCreatedUserBranch_Region() throws IOException, InterruptedException {
		String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
		String Regionpassword = MyDeskDashboardPage.configloader().getProperty("Regionpassword").trim();
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

	}

	public void LogintotheNewlyCreatedUserZone() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		
		String CoreApplicationUrl = MyDeskDashboardPage.configloader1().getProperty("CoreApplicationUrl").trim();
		String ZoneUserID = MyDeskDashboardPage.configloader().getProperty("ZoneUserID").trim();
		String Zonepassword = MyDeskDashboardPage.configloader().getProperty("Zonepassword").trim();
		click1(MyDeskDashboardRepo.profiledropdown);
		Thread.sleep(2000);
//		click1(MyDeskDashboardRepo.Logout);
		moveToElementAndClick(driver, MyDeskDashboardRepo.Logout);
		driver.get(CoreApplicationUrl);
		 String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
         String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
         //System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);
         
         Common.fluentWait("Core login Banner", LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
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
			Common.fluentWait("Collection", MyDeskDashboardRepo.UnassignedAccountsSMA);

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
	
//	public void SelectAgency() {
//		try {
//			 By SelectAgency  = By.xpath("//*[contains(@aria-label,'agency')]");
//			WebElement element = driver.findElement();
//
//			// JavaScript Executor to move to element
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
//			click1(locator);
//			ExtentTestManager.getTest().log(Status.PASS, "click done on locator " + elementName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
//			System.out.println("Error message " + e.getMessage());
//		}
//	}
	public void selectSMAcategory() throws InterruptedException {
		for(int i=0; i<3;i++) {
		By SMACate  = By.xpath("//*[contains(@aria-label,'SMA "+i+"')]");
		click1(MyDeskDashboardRepo.SMACate);
		}
	}
	
	
	public void SelectCurrentDate() throws InterruptedException, ParseException {

		try {
			click(MyDeskDashboardRepo.ActionDateFrom);
			String today = String.valueOf(LocalDate.now().getDayOfMonth());

	        // Construct a dynamic XPath using today's date
	        String xpath = "//span[@class='rz-state-default' and text()='" + today + "']";

	        // Locate the element and click it
	        WebElement currentDate = driver.findElement(By.xpath(xpath));
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", currentDate);
	        
			
			click(MyDeskDashboardRepo.ActionDateto);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public void isAccountAllocatedSuccessfully() {
		Common.fluentWait("AccountAllocatedSuccessfully", MyDeskDashboardRepo.AccountAllocatedSuccessfully);
		if (driver.findElement(MyDeskDashboardRepo.AccountAllocatedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS,
					MyDeskDashboardRepo.AccountAllocatedSuccessfully + "Account Allocated Successfully successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					MyDeskDashboardRepo.AccountAllocatedSuccessfully + "Unable to Account Allocated ");
		}
	}

	public void getTotalAccount() throws Exception, SQLException, IOException {
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
	}
	public void getTotalAccountAllocation() throws Exception, SQLException, IOException {
		countAlloaction = driver.findElement(MyDeskDashboardRepo.Noofallocations).getText();
		if(count.equalsIgnoreCase(countAlloaction)) {
			ExtentTestManager.getTest().log(Status.PASS, "Total account selected " + count);
			try {
				if(driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()){
					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL, "Total account selected " + count);
			try {
				if(driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).isDisplayed()){
					driver.findElement(MyDeskDashboardRepo.AllocationPopUpClose).click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void ExcelRead() {
		try {
			String downloadDir = System.getProperty("user.home") + "\\Downloads"; // Change as needed
			// Get the latest downloaded file
			File excelFile = getLatestFile(downloadDir, ".xlsx");
			if (excelFile != null) {
				System.out.println("Downloaded file: " + excelFile.getName());
				readExcelAndValidate(excelFile);
			} else {
				System.out.println("No Excel file found in the download directory.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("Error message " + e.getMessage());
		}
	}

	public static File getLatestFile(String directoryPath, String fileExtension) {
		File dir = new File(directoryPath);
		File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(fileExtension));

		if (files == null || files.length == 0) {
			return null;
		}

		File latestFile = files[0];
		for (File file : files) {
			if (file.lastModified() > latestFile.lastModified()) {
				latestFile = file;
			}
		}
		return latestFile;
	}

//	public static void readExcelAndValidate(File file) {
//		try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {
//
//			Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
//
//			// Read Column Names (Header Row)
//			Row headerRow = sheet.getRow(0);
//			System.out.print("Column Names: ");
//			
//			for (Cell cell : headerRow) {
//				System.out.print(cell.getStringCellValue() + " | ");
//			}
//			System.out.println();
//			List<String> columnNamesUI = new ArrayList<>();
//
//			for (Cell cell : headerRow) {
//				columnNamesUI.add(cell.getStringCellValue()); // Store column name in List
//			}
//			List<String> columnNames = Arrays.asList("Sl No", "Account No", "Account Name", "Branch /Sol ID",
//					"Branch Name", "Account Type", "Product", "O/s Amount as of Date", "Overdue Amount as of Date",
//					"Collection Amount", "Corporate BCBF", "Contact No", "OverDue Date", "Current O/s Amt as of Date",
//					"Current Overdue Amount As on Date", "Current Collection Amount", "High Risk", "Last Contact Date",
//					"Last Contact Disposition", "Next Action Date", "Next Action Owner", "SMS Sent",
//					"Co Borrower Contact Details", "Co-Borrower Address", "Guarantor Contact Details",
//					"Guarantor Address", "Other Contact Details", "DPD", "Assigned To", "Assignee Name",
//					"Asset Category", "Account Category", "Sector", "Scheme Type", "Scheme");
//			System.out.print("Column Names code: ");
//			System.out.println(columnNames);
//			 
//			List<String> columnNamesUIList = Arrays.stream(columnNamesUI.split("\\|")) // Split by "|"
//	                .map(String::trim) // Trim extra spaces
//	                .collect(Collectors.toList()); // Convert to List
//			// Check if both lists are the same
//			if (columnNames.equals(columnNamesUI)) {
//				System.out.println("✅ Lists are the same.");
//			} else {
//				System.out.println("❌ Lists are different.");
//			}
//			// Read Last Row Values
//			int lastRowNum = sheet.getLastRowNum();
//			Row lastRow = sheet.getRow(lastRowNum);
//
//			System.out.print("Last Row Values: ");
//			for (Cell cell : lastRow) {
//				System.out.print(getCellValue(cell) + " | ");
//			}
//			System.out.println();
//			if (lastRow != null) {
//				// Get the first column value (Column Index 0)
//				Cell firstColumnCell = lastRow.getCell(0);
//				String firstColumnValue = getCellValue(firstColumnCell);
//				double firstColumnNumber = Double.parseDouble(firstColumnValue.trim());
//			    double countNumber = Double.parseDouble(count.trim());
//				if (firstColumnNumber == countNumber) {
//					ExtentTestManager.getTest().log(Status.PASS, "Total count is matched ");
//				} else {
//					ExtentTestManager.getTest().log(Status.FAIL, "Total count is not matched");
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
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

			// Compare lists
			if (columnNames.equals(columnNamesUIList)) {
				System.out.println("Coulmns names match of downloaded Excel sheet");
				ExtentTestManager.getTest().log(Status.PASS, "Total count is matched ");
			} else {
				System.out.println("❌ Lists are different.");
				System.out.println("Excel Column Names  : " + columnNamesUIList);
				System.out.println("Expected Column Names: " + columnNames);
				ExtentTestManager.getTest().log(Status.FAIL, "Coulmns names match of downloaded Excel sheet");
			}

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

}