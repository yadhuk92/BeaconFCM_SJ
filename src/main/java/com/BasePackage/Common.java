package com.BasePackage;

import java.util.NoSuchElementException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;

public class Common {
	
	 private static WebDriver driver; // Make WebDriver a global variable

	    // Method to set the driver
	    public static void setDriver(WebDriver webDriver) {
	        driver = webDriver;
	    }

    public static void fluentWait(String WebElementName, By element) {
        try {
            System.out.println("Fluent wait started for: " + WebElementName);
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofMinutes(10))
                    .ignoring(NullPointerException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofMillis(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Fluent wait ended element is clickable: " + WebElementName);
        } catch (Exception e) {
            System.out.println("Error during fluent wait: " + e.getMessage());
        }
    }
}