package com.BasePackage;

import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.BasePackage.Base_Class;
import com.Page_Repositary.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Class extends Base_Class {
	
	public void CoreLogin() throws IOException, InterruptedException {
		
		String Browser = configloader().getProperty("Browser");
		String CoreAppUrl = configloader().getProperty("CoreApplicationUrl");
		String CoreUserName = configloader().getProperty("CoreUserName");
		String CoreUserPassword = configloader().getProperty("CoreUserPassword");
		
		switch (Browser.toUpperCase()) {

		case "CHROME":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();			
			break;
		default:
			System.err.println("The Driver is not defined");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		ExtentTestManager.getTest().log(Status.PASS,
				Browser +" opened successfully!");
		Log.info("Driver has initialized successfully for "+Browser+" browser");
		driver.get(CoreAppUrl);
        Common.setDriver(driver);
        Common.fluentWait("LoginHyperlink2Banner",LoginPageRepo.LoginHyperlink2Banner);
        
        ExtentTestManager.getTest().log(Status.PASS,
				CoreAppUrl +" loaded successfully!");
        
		Thread.sleep(9000);
		Pagetitle = driver.getTitle();
		Log.info("Title is displayed : "+Pagetitle);
		
		Common.fluentWait("UserNameField",LoginPageRepo.UserNameField);
		Common.fluentWait("PasswordField",LoginPageRepo.PasswordField);
		Common.fluentWait("LoginButton",LoginPageRepo.LoginButton);
		
		try {		
			driver.findElement(LoginPageRepo.UserNameField).sendKeys(CoreUserName);
			 ExtentTestManager.getTest().log(Status.PASS,"Entered "+CoreUserName+" in user name field");
			 Log.info("Entered "+CoreUserName+" in user name field");
			driver.findElement(LoginPageRepo.PasswordField).sendKeys(CoreUserPassword);
			ExtentTestManager.getTest().log(Status.PASS,"Entered "+CoreUserPassword+" in password field");
			Log.info("Entered "+CoreUserPassword+" in password field");
			driver.findElement(LoginPageRepo.LoginButton).click();
			ExtentTestManager.getTest().log(Status.PASS,"Clicked on login button");
			Log.info("Clicked on login button");
			
		}catch(Exception e) {
			
			
		}
	}

}
