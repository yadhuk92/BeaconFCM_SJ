package com.extentReports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
static ExtentReports extent = ExtentManager.getInstance();

public static ExtentTest getTest() {
	return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
}

public static void endTest() {
	extent.flush();
}

public static ExtentTest startTest(String testName) {
	ExtentTest test = extent.createTest(testName);
	extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
	return test;
}
}
