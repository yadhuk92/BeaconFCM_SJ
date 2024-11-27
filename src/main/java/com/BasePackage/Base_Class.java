package com.BasePackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Page_Repositary.LoginPageRepo;
import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Class {

	public static RemoteWebDriver driver = null;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public static String Pagetitle;

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

	public void SetUp() throws IOException, InterruptedException {
		
		String Browser = configloader().getProperty("Browser");
		String Url = configloader().getProperty("URL");
		//String UserName = configloader().getProperty("UserName");
		//String Password = configloader().getProperty("Password");
		
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
		Log.info("Driver has initialized successfully for "+Browser+" browser");
		driver.get(Url);
        Common.setDriver(driver);
        Common.fluentWait("LoginHyperlink2Banner",LoginPageRepo.LoginHyperlink2Banner);
        
		Thread.sleep(9000);
		Pagetitle = driver.getTitle();
		Log.info("Title is displayed : "+Pagetitle);
	}

	public static  void input(By element, String Value) throws InterruptedException {

		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(Value);

	}

	public static  void click(By element) throws InterruptedException {

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(2000);

	}

	public static void select(String value,By element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select selWeekDayDropDown = new Select(driver.findElement(element));
		selWeekDayDropDown.selectByVisibleText(value);
		
	}

	public static void clear(By element)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		Thread.sleep(2000);
	}

	public static void AcceptAlert()
	{
		driver.switchTo().alert().accept();
		//driver.switchTo().alert().dismiss();

		
	}

	public static  void INclick(By element) throws InterruptedException {

		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		//Thread.sleep(2000);

	}
	
	public static void SwitchToFrame(By frameName)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}
	
	public static void ScrollUntilElementVisible(By locator)
	{ 
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static boolean ElementDisplayed(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isDisplayed();
		return flag;
	}
	
	public static boolean ElementEnabled(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();
		return flag;
	}
	
	public static void UploadFile(By locator, String path)
	{
		WebElement uploadElement = driver.findElement(locator);
		String path1=System.getProperty("user.dir");
		Log.info("path is :" + path1);
		uploadElement.sendKeys(path1 + path);
		
	}

	public static boolean ElementEnableOrDisable(By locator)
	{
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();
		return flag;
	}
	
	public static boolean CheckElementDisable(By locator)
	{  
		//disable
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isEnabled();	
		
		if (flag==false) {
			flag=true;
		}else if(flag==true)
			flag=false;
		
		return flag;
	}
	

}
