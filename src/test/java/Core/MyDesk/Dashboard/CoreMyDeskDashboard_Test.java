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
import com.BasePackage.Login_Class;
import com.Page_Repository.CallCenterRoleManagementRepo;
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
		corelogin.CallCenterLogin();
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
			System.out.println(" ************** 1 *****************");
			System.out.println();
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String ZoneUserID = MyDeskDashboardPage.configloader().getProperty("ZoneUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + ZoneUserID);
			List<Object> inputParams = Arrays.asList(ZoneUserID, "John Doe", "john.doe@example.com", 9876543210L);
//			List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ZoneUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);

			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message
						.contains("UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.")) {
					ExtentTestManager.getTest().log(Status.PASS,
							"UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.");
				}
			} else {
				System.out.println("Warning: Message is null");
				MyDeskDashboardPage.SaveConfigFileZone(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
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

	@Test(priority = 2)
	public void Creating_a_core_Region_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 2 *****************");
			System.out.println();
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + RegionUserID);
			List<Object> inputParams = Arrays.asList(RegionUserID, "John Doe", "john.doe@example.com", 9876543210L);
//			List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("RegionUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);

			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message
						.contains("UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.")) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS,
							"UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.");
				}
			} else {
				System.out.println("Warning: Message is null");
				MyDeskDashboardPage.SaveConfigFileRegion(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
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

	@Test(priority = 3)
	public void Creating_a_core_branch_user_with_the_mydesk_functionality_enabled_role(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 3 *****************");
			System.out.println();
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS,
					"My Desk Dashboard config2 file is present:" + Core_BranchUserID);
			List<Object> inputParams = Arrays.asList(Core_BranchUserID, "John Doe", "john.doe@example.com",
					9876543210L);
//			List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("Core_BranchUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);

			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message
						.contains("UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.")) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS,
							"UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.");
				}
			} else {
				System.out.println("Warning: Message is null");
				MyDeskDashboardPage.SaveConfigFileBranch(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
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

	@Test(priority = 4)
	public void Configuring_the_core_branch_user_as_BCO_user_through_backend_for_listing(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println(" ************** 4 *****************");
			System.out.println();
			// Define procedure name
			String procedureName = "InsertToMSTEmployeeForCoreMyDeskDashboard";

			// Input parameters
			List<Object> inputParams = new ArrayList<>();
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS,
					"My Desk Dashboard config2 file is present:" + Core_BranchUserID);
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
				
				ExtentTestManager.getTest().log(Status.PASS,
						"Stored Procedure Output: " + output.get(0));
			} else {
				System.out.println("No output received.");
				ExtentTestManager.getTest().log(Status.FAIL,
						"Stored Procedure Output: " + output.get(0));
			}
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
