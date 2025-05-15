package Core.SecurityManagement;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreRoleManagementRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.UserManagement_Locators;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class RoleManagementTest extends Base_Class {
	
	
	public String TestRole;
	
	WebDriver driver;
	static com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	UserManagement_Page User_UserManagementPage;
	RoleManagement RolemanagementPage;
	CoreRoleManagementRepo PageRepositry;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	
	
	
	@BeforeSuite
	public void reference() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		//ExcelReader = new com.Utility.ExcelReader("CoreRoleManagement");
		log = new Log();
		TestListener = new TestListener();
		Login_Class.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		RolemanagementPage= new RoleManagement();
		User_UserManagementPage= new UserManagement_Page();
		PageRepositry = new CoreRoleManagementRepo();
	}
	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) {
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - Role management");
        Log.info("****" + method.getName() + "****");
    }

	@Test(priority = 1)
	public void Navigation_to_Role_Management_Page() throws Throwable {
		try {
			Log.info("***Navigation to Role Management Page***");
			//CoreAppLogin.CoreLogin();
			ExtentTestManager.getTest().log(Status.INFO, "Login successful");
			Log.info("Login successful !");
			RolemanagementPage.SelectSecurityManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security Management menu");
			Log.info("1. Click on Security Management menu");
			boolean flag2 = RolemanagementPage.SelectRoleManagementMenu();
			ExtentTestManager.getTest().log(Status.PASS, "2. Click on Role Management submenu");
			Log.info("2. Click on Role Management submenu"+flag2);
			boolean flag3 = RolemanagementPage.AddNewRole();
			ExtentTestManager.getTest().log(Status.PASS, "2. Click on Add New Role");
			Log.info("2. Click on Add New Role button"+flag3);

			
//				boolean flag3=User_UserManagementPage.LocatorDisplayed();
//				Log.info("All elements in UserManagementPage displayed : " + flag3);
//				//boolean flag4=User_UserManagementPage.FetchTableHeaderName();
//				//Log.info("Table headers is displayed : " + flag4);
//				ExtentTestManager.getTest().log(Status.PASS, "User management page is displayed with search inputs,buttons and active roles grid.\n"
//						+ "\n"
//						+ "Confirm presence of User Name, Name, Mobile Number, Email ID, Role drop down, Is Active checkbox, Search button, Add User button.\n"
//						+ "Confirm list columns: SL NO, USER NAME, NAME, ROLE, MOBILE NO, EMAIL ID, STATUS, ACTION: " + flag3);
			}catch (AssertionError | Exception e) {
				String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
			}
	
	
	}



	
	@Test(priority = 2 , dataProvider = "TestData")
	public void EnterTestRole(Map<Object, Object> testdata, ITestContext context) throws Throwable {
		try {
			TestRole = testdata.get("TestRole").toString();
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String TestRole = testdata.get("TestRole").toString();
			
			
			  boolean EnterTestRole = RolemanagementPage.EnterTestRole(TestRole);
              if (EnterTestRole) {
                  ExtentTestManager.getTest().log(Status.PASS, "Enter role name" + TestRole + ": " + EnterTestRole);
                  Log.info("Entered Role name" + EnterTestRole);
              } else {
                  ExtentTestManager.getTest().log(Status.FAIL, "4. Failed to enter role name=" + TestRole);
                  Log.error("Failed to enter role name " + TestRole);
              
              }
			}	} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
}
	
	 @DataProvider(name = "TestData")
		public static Object[][] gettestdate() throws IOException {
		 ExcelReader = new com.Utility.ExcelReader("CoreRoleManagement");
			Object[][] objectarry = null;
			java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

			objectarry = new Object[completedata.size()][1];

			for (int i = 0; i < completedata.size(); i++) {
				objectarry[i][0] = completedata.get(i);
			}
			return objectarry;
		}
	
}
		
		


		
	

		

   

	
	
	
	
