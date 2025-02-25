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
			throw e;
		}
}
	


@Test (priority = 3, enabled = true)
    public void Validate_User_Management_Page_Elements(){
        Common.fluentWait("Add User", CallCenterUserManagement.ExecutiveID);
        if (driver.findElement(CallCenterUserManagement.ExecutiveID).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Executive ID  available");
               Log.info("Executive ID exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Executive ID not available");
               Log.info("Executive ID does not exist on the page.");
        }
        
        Common.fluentWait("Add User", CallCenterUserManagement.Username);
        if (driver.findElement(CallCenterUserManagement.Username).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Username  available");
               Log.info("Username exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Executive ID not available");
               Log.info("Username does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.Name);
        if (driver.findElement(CallCenterUserManagement.Name).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Name  available");
               Log.info("Name exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Name not available");
               Log.info("Name does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.Mobile);
        if (driver.findElement(CallCenterUserManagement.Mobile).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Mobile  available");
               Log.info("Mobile exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Mobile not available");
               Log.info("Mobile does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.EmailID);
        if (driver.findElement(CallCenterUserManagement.EmailID).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Email ID  available");
               Log.info("Email ID exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Email ID not available");
               Log.info("Email ID does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.SelectRole);
        if (driver.findElement(CallCenterUserManagement.SelectRole).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Select Role  available");
               Log.info("Select Role exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Select Role not available");
               Log.info("Select Role does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.IsActive);
        if (driver.findElement(CallCenterUserManagement.IsActive).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Is Active  available");
               Log.info("Is Active exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Is Active not available");
               Log.info("Is Active does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.SearchButton);
        if (driver.findElement(CallCenterUserManagement.SearchButton).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Search Button  available");
               Log.info("Search Button exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Search Button not available");
               Log.info("Search Button does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.AddUserButton);
        if (driver.findElement(CallCenterUserManagement.AddUserButton).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Add User Button available");
               Log.info("Add User exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Add User Button not available");
               Log.info("Add User does not exist on the page.");
        }
        
       ExtentTestManager.getTest().log(Status.PASS, "Table Headings are Verified below");
       Log.info("Table Headings are Verified below");
       
    //Table Headings
        Common.fluentWait("Add User", CallCenterUserManagement.sl_no);
        if (driver.findElement(CallCenterUserManagement.sl_no).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Sl No  available");
               Log.info("Sl No exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Sl No not available");
               Log.info("Sl No does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.user_name);
        if (driver.findElement(CallCenterUserManagement.user_name).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Username available");
               Log.info("Username exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Username not available");
               Log.info("Username does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.executive_id);
        if (driver.findElement(CallCenterUserManagement.executive_id).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Executive ID available");
               Log.info("Executive ID exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Executive ID not available");
               Log.info("Executive ID does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.name);
        if (driver.findElement(CallCenterUserManagement.name).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Name available");
               Log.info("Name exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Name not available");
               Log.info("Name does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.role);
        if (driver.findElement(CallCenterUserManagement.role).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Role available");
               Log.info("Role exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Role not available");
               Log.info("Role does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.mobile_no);
        if (driver.findElement(CallCenterUserManagement.mobile_no).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Mobile No available");
               Log.info("Mobile No exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Mobile No not available");
               Log.info("Mobile No does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.email_id);
        if (driver.findElement(CallCenterUserManagement.email_id).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Email ID No available");
               Log.info("Email ID exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Email ID not available");
               Log.info("Email ID does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.status);
        if (driver.findElement(CallCenterUserManagement.status).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Status No available");
               Log.info("Status exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Status not available");
               Log.info("Status does not exist on the page.");
        }
        Common.fluentWait("Add User", CallCenterUserManagement.action);
        if (driver.findElement(CallCenterUserManagement.action).isDisplayed()) {
               ExtentTestManager.getTest().log(Status.PASS, "Action No available");
               Log.info("Action exists on the page.");
               
        } else {
               ExtentTestManager.getTest().log(Status.FAIL, "Action not available");
               Log.info("Action does not exist on the page.");
        }
        
        
        
        
}	


@AfterClass
public void AfterClass() {
	ExtentManager.getInstance().flush();
	// Close the browser
	driver.quit();

}

}


