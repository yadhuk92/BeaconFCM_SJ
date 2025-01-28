package Core.SecurityManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.UserManagement_Locators;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class CoreUserManagement_HO_User_Creation_Test extends Base_Class {
	
	public String AddNewUserNameBtn;
	public String AddNewUserEmailBtn;
	public String AddNewUserPhoneNumberBtn;
	public String AddNewUserRole;
	public String AddNewUserOrganizationType;
	public String AddNewUserHeadOffice;
	
	WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	UserManagement_Page User_UserManagementPage;
	UserManagement_Locators PageRepositry;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	
	@BeforeSuite
	public void reference() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		ExcelReader = new com.Utility.ExcelReader("CoreHOUserCreation");
		log = new Log();
		TestListener = new TestListener();
		driver = baseclass.getDriver();
		screenShot = new com.Utility.ScreenShot(driver);
		User_UserManagementPage= new UserManagement_Page();
		PageRepositry= new UserManagement_Locators();
	}
	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) {
        // Start a new ExtentTest for the current test method
        //extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - User management");
        Log.info("*****" + method.getName() + "*****");
    }
	
	@Test(dataProvider = "TestData")
	public void Navigation_to_User_Management_Page(Map<Object, Object> testdata, ITestContext context) throws Throwable {
		try {
			AddNewUserNameBtn = testdata.get("Name").toString();
			AddNewUserEmailBtn = testdata.get("Email").toString();
			AddNewUserPhoneNumberBtn = testdata.get("Phonenumber").toString();
			AddNewUserRole = testdata.get("Testrole").toString();
			AddNewUserOrganizationType = testdata.get("OrganizationType").toString();
			AddNewUserHeadOffice = testdata.get("HeadOffice").toString();
			
			CoreAppLogin.CoreLogin();
			User_UserManagementPage.CoreUserManagement_HO_User_Creation(AddNewUserNameBtn,AddNewUserEmailBtn,AddNewUserPhoneNumberBtn
					,AddNewUserRole,AddNewUserOrganizationType,AddNewUserHeadOffice);
		}catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        //ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e);
	        throw e;
		}
	}
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
		        if (screenShot == null) {
		            System.err.println("ScreenShot object is not initialized!");
		            return;
		        }
	
		        String methodName = result.getMethod().getMethodName();
		        try {
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
	
	@AfterClass
	public void AfterClass() {
	     //ExtentManager.getInstance().flush();
		baseclass = new Base_Class();
	    driver = baseclass.getDriver();  // Initializing driver
	  // Close the browser
		try {
			if (driver != null) {
		        System.out.println("Driver initialized successfully: " + driver);
		        driver.close();
		        driver.quit();
		    } else {
		        System.err.println("Driver is null after baseclass.getDriver()");
		    }
		} catch (Exception e) {
		    System.out.println("Exception during driver.quit(): " + e.getMessage());
		}
	 }

}
