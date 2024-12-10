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
			 //.assignAuthor("QA Team");
	extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
	return test;
}

public static void addPreconditions(String... preconditions) {
    ExtentTest currentTest = getTest(); // Get the current test
    if (currentTest == null) {
        throw new IllegalStateException("No test started. Please start a test before adding preconditions.");
    }
    
    ExtentTest preconditionNode = currentTest.createNode("Preconditions");
    for (String precondition : preconditions) {
        preconditionNode.info(precondition);
    }
}
}
