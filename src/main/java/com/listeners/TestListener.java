package com.listeners;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Utility.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;


public class TestListener implements  ITestListener {
	
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
		Log.info("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		Log.info("*** Test Suite " + context.getName() + " ending ***");
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		Log.info("*** Running test method " + result.getMethod().getMethodName() + "...");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		Log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		Log.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        Log.error(""+result.getThrowable());

		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		String device = (String) context.getAttribute("device");
		String description = result.getThrowable().getMessage();
		String fileName= (String) context.getAttribute("fileName");

		try {
		File file= new com.Utility.ScreenShot(driver).takeScreenShot(fileName,device);		
		ExtentTestManager.getTest().fail(description,
				MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
		}
		catch(Exception e)
		{
			System.out.println("File not found "+e);
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
		 Log.error("*** Test " + result.getMethod().getMethodName() + " skipped...");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
		 Log.error("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}