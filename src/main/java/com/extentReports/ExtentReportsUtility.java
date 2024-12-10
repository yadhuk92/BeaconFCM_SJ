package com.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;

public class ExtentReportsUtility {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ExtentTest test;

    // Start a new test
    public static void startTest(String testName, String testDescription) {
        test = extent.createTest(testName, testDescription);
    }

    // Log information
    public static void logInfo(String message) {
        test.info(message);
    }

    // Log a successful step
    public static void logPass(String message) {
        test.pass(message);
    }

    // Log a failed step
    public static void logFail(String message) {
        test.fail(message);
    }

    // Log a failure with exception details
    public static void logError(String message, MediaEntityModelProvider throwable) {
        test.fail(message, throwable);  // This overload should work properly for logging errors
    }

    // End the current test
    public static void endTest() {
        extent.flush();
    }

    // Close the report after all tests are complete
    public static void closeReport() {
        extent.flush(); // Ensure all data is written to the report
    }

    // Get the current instance of ExtentReports
    public static ExtentReports getExtentInstance() {
        return extent;
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test;
    }
}