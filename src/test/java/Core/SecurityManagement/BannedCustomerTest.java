package Core.SecurityManagement;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Utility.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import Core.SecurityManagement.BannedCustomer;

public class BannedCustomerTest extends Base_Class {

    Base_Class Base_Class;
    Login_Class AllLoginCases;
    Log log;
    TestListener TestListener;
    com.Utility.ScreenShot screenShot;
    BannedCustomer BannedCustomer;

    @BeforeSuite
    public void reference() {
        log = new Log();
        TestListener = new TestListener();
        Base_Class = new Base_Class();
        AllLoginCases = new Login_Class();
        screenShot = new com.Utility.ScreenShot(driver);
        BannedCustomer =  new BannedCustomer();
    }

    @Test(priority = 1)
    public void AccessBannedCustomerPage() throws InterruptedException {
        try {
            ExtentTestManager.startTest("Access Banned Customer Page").assignCategory("Banned Customer");
            Log.info("*** Running Banned Customer Module testcase...");

            AllLoginCases.CoreLogin(); //--> Core login test case
            
            ExtentTestManager.getTest().log(Status.INFO,
                    "Precondition: User should be logged into core application");
            Log.info("Precondition: User should be logged into core application");
            
            ExtentTestManager.getTest().log(Status.INFO,
                    "Action: 1. Click on Security management main menu \n"+
                    "2. Click on Banned customer sub menu");
            Log.info("Action: 1. Click on Security management main menu \n"+
                    "2. Click on Banned customer sub menu");
            
            BannedCustomer.AccesBannedCustomerPage();
            
            ExtentTestManager.getTest().log(Status.INFO,
                    "Expected result: Banned Customer page is displayed with link Admin/BannedCustomer.");
            
            String currentUrl = driver.getCurrentUrl();
            String actualPageName = currentUrl.substring(currentUrl.lastIndexOf("/") + 1);
            String expectedPageName = "BannedCustomer";
            Assert.assertEquals(actualPageName, expectedPageName, 
            		"Expected page name: "+expectedPageName+"\n"+
            		"Actual page name: "+actualPageName);
            
			driver.quit();
			
			// EndTest
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			
        } catch (Exception e) {
            System.out.println("Error.."+e.getMessage());
        	Log.error("" + e.getMessage());
        	
        	try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot("AccesBannedCustomerPage");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			
			System.out.println(("Test is failed"));
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			Log.info("Test is failed");
        }
    }

	@AfterSuite
	public void takeScreenshotOnFailure(ITestResult result) {
	    if (result.getStatus() == ITestResult.FAILURE) { // Check if the test has failed
	        String methodName = result.getMethod().getMethodName();
	        try {
	            // Capture the screenshot for the failed test
	            File image = screenShot.takeScreenShot(methodName, "Failure");
	
	            // Log the screenshot to the Extent Report
	            ExtentTestManager.getTest().log(
	                Status.FAIL,
	                "Screenshot captured for failed test: " + methodName,
	                MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build()
	            );
	        } catch (IOException e) {
	            System.err.println("Failed to capture screenshot: " + e.getMessage());
	        }
	    }
	}
	
	@AfterSuite
	 public void afterEachTest() {
	     ExtentManager.getInstance().flush();
	 }
	
}