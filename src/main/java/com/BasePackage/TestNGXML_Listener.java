package com.BasePackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGXML_Listener implements ITestListener {
	
	@Override
	public void onFinish(ITestContext contextFinish) {
	System.out.println("Execution Completed for TestCase: "+contextFinish.getName());

	}

	@Override
	public void onStart(ITestContext contextStart) {
	System.out.println("Execution Started for TestCase: "+contextStart.getName());
	System.out.println("Calling startLogging...");
	SeleniumLogToFile.startLogging();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	System.out.println(" Method failed with certain success percentage"+ result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
	System.err.println(" 	Method failed: "+ result.getName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	System.out.println(" Method skipped: "+ result.getName());

	}

	@Override
	public void onTestStart(ITestResult result) {
	System.out.println(" Method Started: "+result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String green = "\u001B[32m";
		String reset = "\u001B[0m";
	System.out.println(green+" 	***Method passed: "+ result.getName()+" ******"+reset);

	}

}
