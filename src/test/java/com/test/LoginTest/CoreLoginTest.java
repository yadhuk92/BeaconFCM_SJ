package com.test.LoginTest;

import java.io.File;
import java.io.IOException;
import org.testng.ITestContext;
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

public class CoreLoginTest extends Base_Class {

    Base_Class Base_Class;
    Login_Class AllLoginCases;
    Log log;
    TestListener TestListener;

    @BeforeSuite
    public void reference() {
        log = new Log();
        TestListener = new TestListener();
        Base_Class = new Base_Class();
        AllLoginCases = new Login_Class();
    }

    @Test
    public void RUNALL(ITestContext context) throws IOException, InterruptedException {
        try {
            ExtentTestManager.startTest("Core application login");
            Log.info("*** Running test method Core application login...");
            context.setAttribute("fileName", "Login");
            AllLoginCases.CoreLogin();
            ExtentTestManager.getTest().log(Status.PASS,
                    "Login successful! " + com.BasePackage.Login_Class.Pagetitle);
            Log.info("Login successful!");
			
			Thread.sleep(2000);
			driver.quit();

			// EndTest
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			
        } catch (Exception e) {
            System.out.println("*** Test execution LoginTest failed...");
            Log.error("*** Test execution LoginTest failed...");
            Log.error("" + e.getMessage());
            String fileName = (String) context.getAttribute("fileName");

            try {
                File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
                ExtentTestManager.getTest().fail(e.getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
            } catch (Exception e1) {
                System.out.println("File not found " + e1);
            }
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

            // Logout
            context.setAttribute("fileName", "Logout");
            driver.quit();
            ExtentTestManager.getTest().log(Status.PASS, "Application Logout");
            Log.info("Logout is done");

            // End Test
            System.out.println("*** Test Suite LoginTest ending ***");
            ExtentTestManager.endTest();
            ExtentManager.getInstance().flush();
            Log.info("*** Test Suite LoginTest ending ***");
        }
    }
}