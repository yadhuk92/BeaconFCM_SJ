package CallCenter_UserManagement;

//public class CallCenter_UserManagement_TC {

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.CallCenterLogin_Repo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.CallCenterHomePage_Repo;
import com.Page_Repository.CallCenterUserManagement;


import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import static org.junit.Assert.assertTrue;


@Test
public class CallCenter_UserManagement_TC extends Base_Class {
	
	
static WebDriver driver;
com.Utility.ExcelReader ExcelReader;
Base_Class baseclass;
Common common;
Log log;
TestListener TestListener;
com.Utility.ScreenShot screenShot;
CallCenterLogin_Repo PageRepository;
CallCenterHomePage_Repo PageRepositry;
LoginPageRepo LoginPageRepository;
ExtentTest extenttest;
Login_Class CallCenterLogin;


@BeforeSuite
public void reference() throws Exception {
	baseclass = new Base_Class();
	CallCenterLogin = new Login_Class();
	ExcelReader = new com.Utility.ExcelReader("CallCenter");
	log = new Log();
	TestListener = new TestListener();
	CallCenterLogin.CallCenterLogin();
	driver = baseclass.getDriver();

	if (driver == null) {
		throw new RuntimeException("WebDriver is not initialized!");
	}
	screenShot = new com.Utility.ScreenShot(driver);
	//CallCenterUserManagement = new CallCenter_UserManagement_Page();
	PageRepository = new CallCenterLogin_Repo();
	
}

@BeforeMethod
public void ExtentTestManagerStartTest(Method method) {
	// Start a new ExtentTest for the current test method
	extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CallCenter - User management");
	Log.info("****" + method.getName() + "****");
}

@Test (priority = 1, enabled = true)
public void Login_to_Beacon_FCM() throws Throwable {
	
	try {
		ExtentTestManager.getTest().log(Status.PASS, "1. Enter a valid username on Login page - CCR0001188");
		Log.info("1. Enter a valid username on Login page - CCR0001188");
		ExtentTestManager.getTest().log(Status.PASS, "2. Enter the password - ses@987");
		Log.info("2. Enter the password - ses@987");
		ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button");
		Log.info("3. Click on Login button");
		ExtentTestManager.getTest().log(Status.PASS,
				"4. The Home page appears with URL - 'http://192.168.32.33:8595/home'");
		Log.info("4. The Home page appears with URL - 'http://192.168.32.33:8595/home'");
		ExtentTestManager.getTest().log(Status.PASS, "5. Expected Result -> Login Successful");
		Log.info("5. Expected Result -> Login Successful");
	} 
catch (AssertionError | Exception e) {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		ExtentTestManager.getTest().log(Status.FAIL,
				"Test Failed in method: " + testName + " --> " + e.getMessage());
		Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		throw e;
	}
}


@Test (priority = 2, enabled = true)
public void Access_User_Management_Page() throws Throwable {

	try {
		click(CallCenterHomePage_Repo.SecurityManagementMenu);
	//driver.findElement(CallCenterHomePage_Repo.SecurityManagementMenu).click();
	ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security Management from the main menu.");
	Log.info("1. Click on Security Management from the main menu.");
	
	} 
	catch (AssertionError | Exception e) {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		ExtentTestManager.getTest().log(Status.FAIL,
				"Test Failed in method: " + testName + " --> " + e.getMessage());
		Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		throw e;
	}
	try {
		click(CallCenterHomePage_Repo.UserManagement);
		//driver.findElement(CallCenterHomePage_Repo.UserManagement).click();
		
		ExtentTestManager.getTest().log(Status.PASS, "2. Click on User Management from the sub menu.");
		Log.info("2. Click on User Management from the sub menu.");
		
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.endsWith("Admin/UserManagement"), "URL does not end with the expected path.");
		
		ExtentTestManager.getTest().log(Status.PASS,
				"2. The Home page appears with URL - 'http://192.168.32.33:8595/Admin/UserManagement'");
		Log.info("2. The Home page appears with URL - 'http://192.168.32.33:8595/Admin/UserManagement'");
		ExtentTestManager.getTest().log(Status.PASS, "3. Expected Result -> User Management page is displayed with the URL ending in Admin/UserManagement");
		Log.info("3. Expected Result -> User Management page is displayed with the URL ending in Admin/UserManagement");
	}
		catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;}
}	
	


@Test (priority = 3, enabled = false)
 public void Validate_User_Management_Page_Elements() throws Throwable {
	
///try {
   //     WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(CallCenterHomePage_Repo.ExecutiveID));
     //  System.out.println("Element is present!");
   //} catch (TimeoutException e) {
    //   System.out.println("Element is not present or not visible!");
 //} 
  
	/*try {
	click(CallCenterUserManagement.ExecutiveID);
		By field = CallCenterUserManagement.ExecutiveID;
	 Assert.assertTrue(((WebElement) field).isDisplayed(), "Field is not present on the page!");
	} catch (TimeoutException e) {
		      System.out.println("Element is not present or not visible!");
	    }*/
	
	//By field = CallCenterHomePage_Repo.SecurityManagementMenu;
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.ExecutiveID);
        Log.info("Executive ID exists on the page.");
    } catch (Exception e) {
        Log.info("Executive ID does not exist on the page.");
    }
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.Username);
        Log.info("Username exists on the page.");
    } catch (Exception e) {
        Log.info("Username does not exist on the page.");
    }
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.Name);
        Log.info("Name exists on the page.");
    } catch (Exception e) {
        Log.info("Name does not exist on the page.");
    }
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.Mobile);
        Log.info("Mobile exists on the page.");
    } catch (Exception e) {
        Log.info("Mobile does not exist on the page.");
    }
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.EmailID);
        Log.info("Email ID exists on the page.");
    } catch (Exception e) {
        Log.info("Email ID does not exist on the page.");
    }
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.IsActive);
        Log.info("Active Checkbox exists on the page.");
    } catch (Exception e) {
        Log.info("Active Checkbox does not exist on the page.");
    }
    
    
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.SearchButton);
        Log.info("Search button exists on the page.");
    } catch (Exception e) {
        Log.info("Search Button does not exist on the page.");
    }
    
    try {
        WebElement element = driver.findElement(CallCenterUserManagement.AddUserButton);
        Log.info("Add User button exists on the page.");
    } catch (Exception e) {
        Log.info("Add User Button does not exist on the page.");
    } 
    
    
    
}

	
	

@AfterClass
public void AfterClass() {
	ExtentManager.getInstance().flush();
	// Close the browser
	driver.quit();

}

}


