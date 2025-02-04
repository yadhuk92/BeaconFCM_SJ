package com.BasePackage;

import java.util.NoSuchElementException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
	
	 private static WebDriver driver; // Make WebDriver a global variable

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
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofMillis(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Fluent wait ended element is clickable: " + WebElementName);
        } catch (Exception e) {
            System.out.println("Error during fluent wait: " + e.getMessage());
        }
    }
    
    public static void waitForSpinnerToDisappear(String WebElementName, By element) {
    	if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null");
        }
        // Define the FluentWait
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30)) // Maximum wait time
                .pollingEvery(Duration.ofMillis(500)) // Polling interval
                .ignoring(Exception.class); // Ignore exceptions like NoSuchElementException

        // Wait until the spinner disappears
        wait.until(driverInstance -> {
            try {
                WebElement spinner = driverInstance.findElement(element);
                return !spinner.isDisplayed(); // Return true if spinner is not displayed
            } catch (Exception e) {
                return true; // Return true if spinner is not found
            }
        });
    }
    
    public static void waitForSpinnerToDisappear2(WebDriver driver, String WebElementName, By element) {
        // Define the FluentWait
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60)) // Maximum wait time
                .pollingEvery(Duration.ofMillis(500)) // Polling interval
                .ignoring(Exception.class); // Ignore exceptions like NoSuchElementException

        // Wait until the spinner disappears
        wait.until(driverInstance -> {
            try {
                WebElement spinner = driverInstance.findElement(element);
                return !spinner.isDisplayed(); // Return true if spinner is not displayed
            } catch (Exception e) {
                return true; // Return true if spinner is not found
            }
        });
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
}