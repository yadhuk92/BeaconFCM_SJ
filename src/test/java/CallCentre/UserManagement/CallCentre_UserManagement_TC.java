package CallCentre.UserManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.CallCentre_Locators;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.CallCentre_Locators;
import Core.SecurityManagement.UserManagement_Page;
import com.Utility.FakerValue;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import static org.junit.Assert.assertTrue;

public class CallCentre_UserManagement_TC extends Base_Class {

	public String AddNewUserNameBtn;
	public String UserManagementExecutiveID;
	public String AddNewUserEmailBtn;
	public String AddNewUserPhoneNumberBtn;
	public String AddNewUserRole;
	public String AddNewUserOrganizationType;
	public String AddNewUserOrganizationType2;
	public String AddNewUserOrganizationType3;
	public String AddNewUserOrganizationType4;
	public String AddNewUserHeadOffice;
	public String InvalidEmailId;
	public String InvalidEmailIdWithoutDomain;
	public String InvalidPhoneNumberWithLetters;
	public String InvalidPhoneNumberWithEightDigits;
	public String NamewithNumericInput;
	public String NamewithAlphaNumericInput;
	public String AddNewUserZoneCO;
	public String AddNewUserRegion;
	public String InvalidUserName;
	public String AddNewUserBranch;
	public String username;
	public String UserManagementPageRole;
	public String UsernameInUNPage;
	public String UNOriginal;
	public String PwdOriginal;
	public String AddNewUserRoleType;
	public String userGridPage;

	static WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	CallCentre_UserManagement_Page CallCentreUserManagement;
	CallCentre_Locators PageRepositry;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;

	@BeforeSuite
	public void reference() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		ExcelReader = new com.Utility.ExcelReader("CallCentre");
		log = new Log();
		TestListener = new TestListener();
		CoreAppLogin.CoreLogin();
		driver = baseclass.getDriver();
//		userGridPage = new UserGridPage(driver);
		if (driver == null) {
			throw new RuntimeException("WebDriver is not initialized!");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		CallCentreUserManagement = new CallCentre_UserManagement_Page();
		PageRepositry = new CallCentre_Locators();
	}

	@BeforeMethod
	public void ExtentTestManagerStartTest(Method method) {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CallCentre - User management");
		Log.info("****" + method.getName() + "****");
	}

	@Test(priority = 1)
	public void Login_to_Beacon_FCM() throws Throwable {
		try {
			// CoreAppLogin.CoreLogin();
			ExtentTestManager.getTest().log(Status.PASS, "1. Enter a valid username on Login page - CCR0001144");
			Log.info("1. Enter a valid username on Login page - CCR0001144");
			ExtentTestManager.getTest().log(Status.PASS, "2. Enter the password - lr9&45");
			Log.info("2. Enter the password - lr9&45");
			ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button");
			Log.info("3. Click on Login button");
			ExtentTestManager.getTest().log(Status.PASS,
					"4. The Home page appears with URL - 'http://192.168.32.33:8595/home'");
			Log.info("4. The Home page appears with URL - 'http://192.168.32.33:8595/home'");
			ExtentTestManager.getTest().log(Status.PASS, "5. Expected Result -> Login Successful");
			Log.info("5. Expected Result -> Login Successful");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 2)
	public void Access_User_Management_Page() throws Throwable {
		try {
			CallCentreUserManagement.SelectSecurityManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security Management menu");
			Log.info("1. Click on Security Management menu");
			boolean flag1 = CallCentreUserManagement.SelectUserManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "2. Click on User Management submenu");
			Log.info("2. Click on User Management submenu");
			ExtentTestManager.getTest().log(Status.PASS,
					"3. Expected Result -> User Management page appears with URL - 'http://192.168.32.33:8595/Admin/UserManagement' "
							+ flag1);
			Log.info(
					"3. Expected Result -> User Management page appears with URL - 'http://192.168.32.33:8595/Admin/UserManagement' "
							+ flag1);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 3)
	public void Validate_User_Management_Page_Elements() throws Throwable {
		try {
			boolean flag2 = CallCentreUserManagement.LocatorDisplayed();
			ExtentTestManager.getTest().log(Status.PASS,
					"1. User management page is displayed with search button, Add User button and grid.\n" + "\n"
							+ "2. Confirm presence of Executive ID, User Name, Name, Mobile Number, Email ID, Select Role dropdown, Is Active checkbox\n"
							+ "3. Confirm list columns: SL NO, USER NAME, Executive ID, NAME, ROLE, MOBILE NO, EMAIL ID, STATUS, ACTION");
			ExtentTestManager.getTest().log(Status.PASS,
					"4. Expected Result -> The User Management page must possess the following elements: " + flag2);
			Log.info("All elements in UserManagementPage displayed : " + flag2);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 4)
	public void Access_Add_New_User_Page() throws Throwable {
		try {
			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Add User button");
			Log.info("1. Click on Add User button");
			ExtentTestManager.getTest().log(Status.PASS,
					"2. Expected result --> Page redirects to 'Add New User' page displaying - Executive ID, Name, Email, Phone number field, Role Type dropdown, Close and Submit buttons");
			Log.info(
					"2. Expected result --> Page redirects to 'Add New User' page showing Name, Email, Phone number fields, Role and Organization Type drop-downs, Close and Submit buttons are visible.");
			ExtentTestManager.getTest().log(Status.PASS,
					"3. The page hangs the URL - 'http://192.168.32.33:8595/Admin/AddNewUser'");
			Log.info("3. The page hangs the URL - 'http://192.168.32.33:8595/Admin/AddNewUser'");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5)
	public void Add_User_Page_Empty_Save_Attempt() throws Throwable {
		try {
			CallCentreUserManagement.ClickAddNewUserCloseBtn();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click Close button from Add New user page");
			Log.info("1. Click Close button from Add New user page");
			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
			boolean flag5 = CallCentreUserManagement.ClickAddNewUserSubmitBtn();
			ExtentTestManager.getTest().log(Status.PASS,
					"2. Leave mandatory fields blank - Executive ID, Name, Email, Phone number field, RoleType");
			Log.info("2. Leave mandatory fields blank - Executive ID, Name, Email, Phone number field, RoleType");
			ExtentTestManager.getTest().log(Status.PASS, "3 .Clicked AddNewUser SubmitBtn : " + flag5);
			Log.info("3. Clicked AddNewUser SubmitBtn : " + flag5);
			boolean flag6 = CallCentreUserManagement.ErrormessageforAdduserPage();
			ExtentTestManager.getTest().log(Status.PASS,
					"4. Expected Result -> Validation Message is displayed on all the mandatory input fields: \"Executive ID is required\", \"Name is required\", \"Email is required\", \"Phone number is required\", \"Role is required\" "
							+ flag6);
			Log.info("4. Expected Result -> Validation Message is displayed on all the mandatory input fields: "
					+ flag6);
			CallCentreUserManagement.ClickAddNewUserCloseBtn();
			ExtentTestManager.getTest().log(Status.PASS, "Click Close button from Add New user page");
			Log.info("Click Close button from Add New user page");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 6, dataProvider = "TestData")
	public void Add_new_user_page__Enter_inputs_for_ExecutiveID(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
		ExtentTestManager.getTest().log(Status.PASS, "1. Click on Add User button");
		Log.info("1. Click on Add User button");
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				String value1 = testdata.get("ExecutiveID").toString();
				CallCentreUserManagement.EnterUserManagementExecutiveID(value1);
				ExtentTestManager.getTest().log(Status.PASS,
						"2. Expected Result -> ExecutiveID is entered successfully - " + value1);
				Log.info("2. Expected Result -> ExecutiveID is entered successfully - " + value1);
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7, dataProvider = "TestData")
	public void Add_new_user_page__Enter_inputs_for_Name(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				String value1 = testdata.get("Name").toString();
				CallCentreUserManagement.EnterAddNewUserName(value1);
				ExtentTestManager.getTest().log(Status.PASS,
						"Expected Result -> Name is entered successfully - " + value1);
				Log.info("Expected Result -> Name is entered successfully - " + value1);
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

	@Test(priority = 8, dataProvider = "TestData")
	public void Add_new_user_page__Enter_inputs_for_Email(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				String value1 = testdata.get("Email").toString();
				CallCentreUserManagement.EnterAddNewUserEmail(value1);
				ExtentTestManager.getTest().log(Status.PASS,
						"Expected Result -> Email ID is entered successfully - " + value1);
				Log.info("Expected Result -> Email ID is entered successfully - " + value1);
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 9, dataProvider = "TestData")
	public void Add_new_user_page__Enter_inputs_for_PhoneNumber(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				String value1 = testdata.get("Phone").toString();
				CallCentreUserManagement.EnterAddNewUserPhoneNumber(value1);
				ExtentTestManager.getTest().log(Status.PASS,
						"Expected Result -> Phone Number is entered successfully - " + value1);
				Log.info("Expected Result -> Phone Number is entered successfully - " + value1);
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 10, dataProvider = "TestData")
	public void Add_new_user_page__Enter_inputs_for_RoleType(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		try {
			CallCentreUserManagement.SelectRoleDropdown(driver);
			ExtentTestManager.getTest().log(Status.PASS,
					"Expected Result -> Opted valid option for Role Type" + driver);
			Log.info("Expected Result -> Opted valid option for Role Type - " + driver);
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 11)
	public void Click_Submit_button_and_Create_New_User() throws Throwable {
		try {
			boolean flag27 = CallCentreUserManagement.ClickAddNewUserSubmitBtn();
			ExtentTestManager.getTest().log(Status.PASS,
					"1. Entered valid inputs on Executive ID, Name, Email, Phone number & RoleType fields");
			Log.info("1. Entered valid inputs on Executive ID, Name, Email, Phone number & RoleType fields");
			ExtentTestManager.getTest().log(Status.PASS, "2. Clicked Submit Button : " + flag27);
			Log.info("2. Clicked Submit Button : " + flag27);
			ExtentTestManager.getTest().log(Status.PASS,
					"3. Expected Result -> User is created successfully, and new user details including password and Username are displayed on User Management page");
			Log.info(
					"3. Expected Result -> User is created successfully, and new user details including password are Username are displayed on User Management page");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 12, dataProvider = "TestData")
	public void Check_for_Newly_created_User_In_the_Grid(Map<Object, Object> testdata, ITestContext context)
			throws Throwable {
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				String Value2 = testdata.get("ExecutiveID").toString();
				CallCentreUserManagement.isUserPresentInGrid(Value2);
				ExtentTestManager.getTest().log(Status.PASS,
						"Expected Result -> Newly created user is displayed on the grid with Executive ID - " + Value2);
				Log.info(
						"Expected Result -> Newly created user is displayed on the grid with Executive ID - " + Value2);
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}
	}

//	@Test(priority = 13, dataProvider = "TestData")
//	public void ExecutiveID_ECP_Validations(Map<Object, Object> testdata) throws Throwable {
//		try {
//			CallCentreUserManagement.ClickAddNewUserCloseBtn();
//			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
//			CallCentreUserManagement.ECPValidationExecutiveID(driver);
//			ExtentTestManager.getTest().log(Status.PASS, "1 Entered Numeric input on Executive ID field");
//			Log.info("1 Entered Numeric input on Executive ID field");
//			CallCentreUserManagement.SelectRoleDropdown(driver);
//			ExtentTestManager.getTest().log(Status.PASS,
//					"2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			Log.info("2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//			ExtentTestManager.getTest().log(Status.PASS, "3. Click Submit button");
//			Log.info("3. Click Submit button");
//			ExtentTestManager.getTest().log(Status.PASS,
//					"4. Expected Result ->. The new user has been added to the grid when the user provides numeric input on Executive ID field");
//			Log.info(
//					"4. Expected Result ->. The new user has been added to the grid when the user provides numeric input on Executive ID field");
//			CallCentreUserManagement.ClickAddNewUserCloseBtn();
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 14, dataProvider = "TestData")
//	public void Name_Field_ECP_Validations(Map<Object, Object> testdata) throws Throwable {
//		try {
//			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
//			CallCentreUserManagement.ECPValidationName(driver);
//			ExtentTestManager.getTest().log(Status.PASS, "1 Entered Alphabetic input on Name field");
//			Log.info("1 Entered Alphabetic input on Name field");
//			CallCentreUserManagement.SelectRoleDropdown(driver);
//			ExtentTestManager.getTest().log(Status.PASS,
//					"2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			Log.info("2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//			ExtentTestManager.getTest().log(Status.PASS, "3. Click Submit button");
//			Log.info("3. Click Submit button");
//			ExtentTestManager.getTest().log(Status.PASS,
//					"4. Expected Result ->. The new user has been added to the grid when the user provides numeric input on Name field");
//			Log.info(
//					"4. Expected Result ->. The new user has been added to the grid when the user provides numeric input on Name field");
//			CallCentreUserManagement.ClickAddNewUserCloseBtn();
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 15, dataProvider = "TestData")
//	public void Email_Field_ECP_Validations(Map<Object, Object> testdata) throws Throwable {
//		try {
//			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
//			CallCentreUserManagement.ECPValidationEmail(driver);
//			ExtentTestManager.getTest().log(Status.PASS, "1 Entered Valid input on Email field");
//			Log.info("1 Entered Valid input on Email field");
//			CallCentreUserManagement.SelectRoleDropdown(driver);
//			ExtentTestManager.getTest().log(Status.PASS,
//					"2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			Log.info("2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//			ExtentTestManager.getTest().log(Status.PASS, "3. Click Submit button");
//			Log.info("3. Click Submit button");
//			ExtentTestManager.getTest().log(Status.PASS,
//					"4. Expected Result ->. The new user has been added to the grid when the user provides valid input on Email ID field");
//			Log.info(
//					"4. Expected Result ->. The new user has been added to the grid when the user provides valid input on Email ID field");
//			CallCentreUserManagement.ClickAddNewUserCloseBtn();
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 16, dataProvider = "TestData")
//	public void PhoneNumber_Field_ECP_Validations(Map<Object, Object> testdata) throws Throwable {
//		try {
//			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
//			CallCentreUserManagement.ECPValidationPhone(driver);
//			ExtentTestManager.getTest().log(Status.PASS, "1 Entered Valid input on Phone Number field");
//			Log.info("1 Entered Valid input on Phone Number field");
//			CallCentreUserManagement.SelectRoleDropdown(driver);
//			ExtentTestManager.getTest().log(Status.PASS,
//					"2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			Log.info("2 Entered inputs on Name, Email ID, Phone Number, RoleType fields");
//			CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//			ExtentTestManager.getTest().log(Status.PASS, "3. Click Submit button");
//			Log.info("3. Click Submit button");
//			ExtentTestManager.getTest().log(Status.PASS,
//					"4. Expected Result ->. The new user has been added to the grid when the user provides valid input on Phone Number field");
//			Log.info(
//					"4. Expected Result ->. The new user has been added to the grid when the user provides valid input on Phone Number field");
//			CallCentreUserManagement.ClickAddNewUserCloseBtn();
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 17, dataProvider = "TestData")
//	public void Error_Guessing_Invalid_Email(Map<Object, Object> testdata, ITestContext context) throws Throwable {
//		try {
//			CallCentreUserManagement.ClickUserManagementPageAddUserBtn();
//			if (testdata.get("Run").toString().equalsIgnoreCase("Invalid")) {
//				String value1 = testdata.get("Invalid Email").toString();
//				CallCentreUserManagement.EnterAddNewUserEmail(value1);
//				ExtentTestManager.getTest().log(Status.PASS, "1. Invalid Email ID is being entered - " + value1);
//				Log.info("1. Invalid Email ID is being entered, the system throws an error message " + value1);
//				CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//				ExtentTestManager.getTest().log(Status.PASS,
//						"2. Expected Result -> Click Submit button - The system throws an error on Email ID field");
//				Log.info("2. Expected Result -> Click Submit button - The system throws an error on Email ID field");
//
//			}
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 18, dataProvider = "TestData")
//	public void Error_Guessing_Invalid_PhoneNumber_Format(Map<Object, Object> testdata, ITestContext context)
//			throws Throwable {
//		try {
//			if (testdata.get("Run").toString().equalsIgnoreCase("Invalid")) {
//				String value1 = testdata.get("Invalid Phone").toString();
//				CallCentreUserManagement.EnterAddNewUserPhoneNumber(value1);
//				ExtentTestManager.getTest().log(Status.PASS, "1. Invalid Email ID is being entered - " + value1);
//				Log.info("1. Invalid Email ID is being entered, the system throws an error message " + value1);
//				CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//				ExtentTestManager.getTest().log(Status.PASS,
//						"2. Expected Result -> Click Submit button - The system throws an error on Email ID field");
//				Log.info("2. Expected Result -> Click Submit button - The system throws an error on Email ID field");
//
//			}
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Test(priority = 19, dataProvider = "TestData")
//	public void Error_Guessing_Duplicate_ExecutiveID(Map<Object, Object> testdata, ITestContext context)
//			throws Throwable {
//		try {
//			if (testdata.get("Run").toString().equalsIgnoreCase("Dup")) {
//				String value0 = testdata.get("Name").toString();
//				CallCentreUserManagement.EnterAddNewUserNameDup(value0);
//				ExtentTestManager.getTest().log(Status.PASS, "1.Name is entered successfully - " + value0);
//				Log.info("1. Name is entered successfully - " + value0);
//				String value1 = testdata.get("Email").toString();
//				CallCentreUserManagement.EnterAddNewUserEmailDup(value1);
//				ExtentTestManager.getTest().log(Status.PASS, "2. Email ID is entered successfully - " + value1);
//				Log.info("2. Email ID is entered successfully - " + value1);
//				String value2 = testdata.get("Phone").toString();
//				CallCentreUserManagement.EnterAddNewUserPhoneNumberDup(value2);
//				ExtentTestManager.getTest().log(Status.PASS, "3. Phone Number is entered successfully - " + value2);
//				Log.info("3. Phone Number is entered successfully - " + value2);
//				CallCentreUserManagement.SelectRoleDropdown(driver);
//				ExtentTestManager.getTest().log(Status.PASS, "4. Opted valid option for Role Type - " + driver);
//				Log.info("4. Opted valid option for Role Type - " + driver);
//				String value3 = testdata.get("ExecutiveID").toString();
//				CallCentreUserManagement.EnterUserManagementExecutiveIDDup(value3);
//				ExtentTestManager.getTest().log(Status.PASS, "5. Executive ID is entered successfully - " + value3);
//				Log.info("5. Executive ID is entered successfully - " + value3);
//				CallCentreUserManagement.ClickAddNewUserSubmitBtn();
//				ExtentTestManager.getTest().log(Status.PASS, "6. Click on Submit button");
//				Log.info("6. Click on Submit button");
//				ExtentTestManager.getTest().log(Status.PASS,
//						"7. Expected Result -> The Add User page throws an error message stating the Executive ID is existing in the DB");
//				Log.info("The Add User page throws an error message stating the Executive ID is existing in the DB");
//			}
//		} catch (AssertionError | Exception e) {
//			String testName = new Object() {
//			}.getClass().getEnclosingMethod().getName();
//			ExtentTestManager.getTest().log(Status.FAIL,
//					"Test Failed in method: " + testName + " --> " + e.getMessage());
//			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//			throw e;
//		}
//	}

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

	@AfterClass
	public void AfterClass() {
		ExtentManager.getInstance().flush();
		// Close the browser
//        if (driver != null) {
//            driver.quit();
//        }
	}

}
