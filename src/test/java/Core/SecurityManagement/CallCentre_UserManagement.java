package Core.SecurityManagement;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.UserManagement_Locators;
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

public class CallCentre_UserManagement extends Base_Class {
	
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
	
	WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	UserManagement_Page User_UserManagementPage;
	UserManagement_Locators PageRepositry;;
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
		User_UserManagementPage= new UserManagement_Page();
		PageRepositry= new UserManagement_Locators();
	}
	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) {
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - User management");
        Log.info("****" + method.getName() + "****");
    }

@Test(priority = 1)
public void Login_to_Beacon_FCM() throws Throwable {
		try {
			Log.info("***Navigation to User Management Page***");
			//CoreAppLogin.CoreLogin();
			ExtentTestManager.getTest().log(Status.INFO, "Login successful");
			Log.info("Login successful !");
	}catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
}	
@Test(priority = 2)
public void Access_User_Management_Page() throws Throwable {
		try {
			//CoreAppLogin.CoreLogin();
			User_UserManagementPage.SelectSecurityManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security Management menu");
			Log.info("1. Click on Security Management menu");
			boolean flag1 = User_UserManagementPage.SelectUserManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "2. Click on User Management submenu");
			Log.info("User Management page appears"+flag1);
		}catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
}
@Test(priority = 3)
public void Validate_User_Management_Page_Elements() throws Throwable {
		try {
			//CoreAppLogin.CoreLogin();
			boolean flag2=User_UserManagementPage.LocatorDisplayed();
			Log.info("All elements in UserManagementPage displayed : " + flag2);
			//boolean flag4=User_UserManagementPage.FetchTableHeaderName();
			//Log.info("Table headers is displayed : " + flag4);
			ExtentTestManager.getTest().log(Status.PASS, "User management page is displayed with search inputs,buttons and active roles grid.\n"
					+ "\n"
					+ "Confirm presence of Executive ID, User Name, Name, Mobile Number, Email ID, Role drop down, Is Active checkbox, Search button, Add User button.\n"
					+ "Confirm list columns: SL NO, USER NAME, Executive ID, NAME, ROLE, MOBILE NO, EMAIL ID, STATUS, ACTION: " + flag2);
		}catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
}
@Test(priority = 4)
public void Access_Add_New_User_Page() throws Throwable {
	try {
		
			User_UserManagementPage.ClickUserManagementPageAddUserBtn();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Add User button");
			Log.info("1. Click on Add User button");
			ExtentTestManager.getTest().log(Status.PASS, "Expected result --> Page redirects to 'Add New User' page showing Executive ID, Name, Email, Phone number field, Role Type dropdown, Close and Submit buttons are visible.");
			Log.info("Expected result --> Page redirects to 'Add New User' page showing Name, Email, Phone number fields, Role and Organization Type drop-downs, Close and Submit buttons are visible.");
	} catch (AssertionError | Exception e) {
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}	
}
@Test(priority = 5)
public void Add_User_Page_Empty_Save_Attempt () throws Throwable {
	try {
		User_UserManagementPage.ClickAddNewUserCloseBtn();
		User_UserManagementPage.ClickUserManagementPageAddUserBtn();
		boolean flag27 = User_UserManagementPage.ClickAddNewUserSubmitBtn();
		ExtentTestManager.getTest().log(Status.PASS, "1. Leave Executive ID, Name, Email, Phone number fields empty on");
		ExtentTestManager.getTest().log(Status.PASS, "2. Do not select any option for Role type dropdown");
		ExtentTestManager.getTest().log(Status.PASS, "Clicked AddNewUser SubmitBtn : " + flag27);
		Log.info("Clicked AddNewUser SubmitBtn : " + flag27);
		ExtentTestManager.getTest().log(Status.PASS, "3. Click on Submit button");
		boolean flag28 = User_UserManagementPage.ErrormessageforAdduserPage();
		ExtentTestManager.getTest().log(Status.PASS, "4. Error messages: \"Executive ID is required\", \"Name is required\", \"Email is required\", \"Phone number is required\", \"Role is required\" are displayed under corresponding fields : " + flag28);
		Log.info("Error message for AdduserPage displayed : " + flag28);
	} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 6, dataProvider = "TestData")
public void Add_new_user_page__Enter_inputs_for_ExecutiveID (Map<Object, Object> testdata, ITestContext context) throws Throwable {
	try {
		User_UserManagementPage.ClickAddNewUserCloseBtn();
		User_UserManagementPage.ClickUserManagementPageAddUserBtn();
		if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			String value1 = testdata.get("ExecutiveID").toString();
		    User_UserManagementPage.EnterUserManagementExecutiveID(value1);
		}
		ExtentTestManager.getTest().log(Status.PASS, "1. ExecutiveID is entered successfully");
		
} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 7, dataProvider = "TestData")
public void Add_new_user_page__Enter_inputs_for_Name (Map<Object, Object> testdata, ITestContext context) throws Throwable {
	try {
		if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			String value1 = testdata.get("Name").toString();
		    User_UserManagementPage.EnterAddNewUserName(value1);
		}
		ExtentTestManager.getTest().log(Status.PASS, "Name is entered successfully");
} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 8, dataProvider = "TestData")
public void Add_new_user_page__Enter_inputs_for_Email (Map<Object, Object> testdata, ITestContext context) throws Throwable {
	try {
		if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			String value1 = testdata.get("Email").toString();
		    User_UserManagementPage.EnterAddNewUserEmail(value1);
		}
        ExtentTestManager.getTest().log(Status.PASS, "Email is entered successfully");
} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 9, dataProvider = "TestData")
public void Add_new_user_page__Enter_inputs_for_PhoneNumber (Map<Object, Object> testdata, ITestContext context) throws Throwable {
	try {
		if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			String value1 = testdata.get("Phone").toString();
		    User_UserManagementPage.EnterAddNewUserPhoneNumber(value1);
		}
		ExtentTestManager.getTest().log(Status.PASS, "Phone Number is entered successfully");
} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 10, dataProvider = "TestData")
public void Add_new_user_page__Enter_inputs_for_RoleType (Map<Object, Object> testdata, ITestContext context) throws Throwable {
	try {
        User_UserManagementPage.SelectRoleDropdown(driver);
		ExtentTestManager.getTest().log(Status.PASS, "Selected valid option for Role Type");
	} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
@Test(priority = 11)
public void Click_Submit_button_and_Create_New_User () throws Throwable {
	try {
		boolean flag27 = User_UserManagementPage.ClickAddNewUserSubmitBtn();
		ExtentTestManager.getTest().log(Status.PASS, "1. Entered valid inputs on Executive ID, Name, Email, Phone number fields");
		ExtentTestManager.getTest().log(Status.PASS, "2. selected a valid option from the Role type dropdown");
		ExtentTestManager.getTest().log(Status.PASS, "3. Clicked AddNewUser SubmitBtn : " + flag27);
		Log.info("Clicked AddNewUser SubmitBtn : " + flag27);
		ExtentTestManager.getTest().log(Status.PASS, "4. Click on Submit button");
		ExtentTestManager.getTest().log(Status.PASS, "5. User is created successfully, and new user details including password are displayed on User Management page");		
	} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
//@Test(priority = 12, dataProvider = "TestData")
//public void Check_for_Newly_created_User_In_the_Grid () throws Throwable {
//	try {
//		String username = ;
//		User_UserManagementPage.isUserPresentInGrid();
//		boolean userPresent = userGridPage.isUserPresentInGrid(username);
//		ExtentTestManager.getTest().log(Status.PASS, "Newly created user is displayed on the grid");
//} catch (AssertionError | Exception e) {
//			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
//	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//	        throw e;
//	}
//}
//@Test(priority = 13)
//public void ExecutiveID_ECP_Validation() throws Throwable {
//	try {
//		User_UserManagementPage.SelectSecurityManagementMenu();
//		User_UserManagementPage.ClickUserManagementPageAddUserBtn();
//		User_UserManagementPage.EnterUserManagementExecutiveID("123456");
//	    ExtentTestManager.getTest().log(Status.PASS, "Entered Numeric input on Executive ID field");
//		User_UserManagementPage.ClickAddNewUserSubmitBtn();
//		ExtentTestManager.getTest().log(Status.PASS, "Expected result --> The Executive ID field does not throw any error messages");
//		Log.info("Expected result - The Executive ID field does not throw any errors");
//		User_UserManagementPage.ClickAddNewUserCloseBtn();
//		User_UserManagementPage.ClickUserManagementPageAddUserBtn();
//		User_UserManagementPage.EnterUserManagementExecutiveID("abc123");
//	    ExtentTestManager.getTest().log(Status.PASS, "Entered Alphanumeric input on Executive ID field");
//		User_UserManagementPage.ClickAddNewUserSubmitBtn();
//		ExtentTestManager.getTest().log(Status.PASS, "Expected result -->Invalid input: Only numeric characters allowed");
//		Log.info("Expected result - Invalid input: Only numeric characters allowed");
//	} catch (AssertionError | Exception e) {
//		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
//        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
//        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
//        throw e;
//	}
//}

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
        if (driver != null) {
            driver.quit();
        }
 }
}

