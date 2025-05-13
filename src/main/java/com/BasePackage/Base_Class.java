package com.BasePackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Page_Repository.LoginPageRepo;
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

	public void SetUp() throws IOException, InterruptedException, Exception {
		
		String Browser = configloader().getProperty("Browser");
		String Url = configloader().getProperty("URL");
		
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

	public static Connection OracleDBConnection() throws IOException {
		
		Connection connection = null;
        try {
        	String DB_URL = configloader().getProperty("DatabaseURL");
        	String DB_UserName = configloader().getProperty("DB_UserName");
        	String DB_Password = configloader().getProperty("DB_Password");
        	
            // JDBC URL for Oracle database
            String URL = "jdbc:oracle:thin:@"+ DB_URL.trim();
            String username = DB_UserName.trim();
            String password = DB_Password.trim();
            // Establish connection
            //System.out.println("URL="+URL);
            //System.out.println("username="+username);
            //System.out.println("password="+password);
            connection = DriverManager.getConnection(URL, username, password);
            
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        } 
		return connection;
		
	}
	
	public static  void click(By element) throws InterruptedException {

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Updated constructor
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(2000);

	}
	public static  void click1(By element) throws InterruptedException {

		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Updated constructor
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(1000);
//		Thread.sleep(2000);

	}
	public static void moveToElementAndClick(WebDriver driver, By locator) throws InterruptedException {
		Thread.sleep(2000);
        WebElement element = driver.findElement(locator); 
        JavascriptExecutor js = (JavascriptExecutor) driver;       
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);           
        js.executeScript("arguments[0].click();", element);
    }
	public static void ForLoopClick(By ClickElement) {
		try
		{
			for(int i=0; i<60; i++)
			{
				try
				{
					WebElement element = driver.findElement(ClickElement);
					if (element.isDisplayed() == true)
					{
						element.click();
						element.click();
						System.out.println("ForLoopWaitPlusClick: Element clicked");
						break;
					}
					else
					{
						System.out.println("Element to be click is not found");
					}
				}
				catch(Exception e1)
				{
					System.out.println("Catch exception");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Error occurred: " + e);
		}
		
	}
	
	public WebElement waitVisibility(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static boolean ElementDisplayed(By locator)
	{   
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		WebElement element = driver.findElement(locator);
		Boolean flag = element.isDisplayed();
		return flag;
	}
	
	public static void input(By element, String Value) throws InterruptedException {
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait2.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(Value);
	}
	
	public void SelectActiveDropdown(By by, String value) throws InterruptedException {

		if (ElementDisplayed(by)) {
			//JavascriptExecutor Js=driver;
			//Js.executeScript("arguments[0].click();", by);
            click(by);

			Thread.sleep(3000); 

			By options = By.xpath("//*[text()='"+ value +"']//parent::li");

			if (ElementDisplayed(options)) {

				click(options);

				Log.info("Successfully user found and clicked on the " + value + " value inside the dropdown ");

			}else {
				waitVisibility(options);
				if (ElementDisplayed(options)) {

					click(options);

					Log.info("Successfully user found and clicked on the " + value + " value inside the dropdown ");

				}else {
					Log.error("Unsuccessfully user not able to find and clicked on the " + value + " value inside the dropdown ");
				}
				
			}

		} else {

			Log.info("UnSuccessfully user not found and clicked on the " + value + " value inside the dropdown ");

		}

	}

	public static void clear(By element)throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
		Thread.sleep(2000);
	}
	
	public static boolean isCheckboxSelectedByDefault(WebDriver driver, By locator) {
        WebElement checkbox = driver.findElement(locator);
        return checkbox.isSelected();
    }
	
	public String GetElementText(By locator) throws InterruptedException {    	 

        String stxt = null;

        waitVisibility(locator);

        WebElement element = driver.findElement(locator);

        if (element.isDisplayed()) {

               stxt = element.getText();

               Log.info("System able to found the element :" + locator);

        } else {

               Log.error("System not able to found the element :" + locator);

        }

        return stxt;
	}
	
	public static String generateRandomSpecialWord(int length) {
	     // Define the special characters
	     String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	     StringBuilder randomWord = new StringBuilder();
	    
	     Random random = new Random();
	    
	     // Generate the random word by choosing from special characters
	     for (int i = 0; i < length; i++) {
	         int index = random.nextInt(specialCharacters.length());
	         randomWord.append(specialCharacters.charAt(index));
	     }
	    
	     return randomWord.toString();
	 }
	public static void writeData(String sheetName, int rowNum, int cellNum, String value) throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";
	    FileInputStream fis = new FileInputStream(filePath);
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheet(sheetName);

	    XSSFRow row = sheet.getRow(rowNum);
	    if (row == null) {
	        row = sheet.createRow(rowNum);
	    }

	    XSSFCell cell = row.getCell(cellNum);
	    if (cell == null) {
	        cell = row.createCell(cellNum);
	    }

	    cell.setCellValue(value);

	    fis.close();

	    FileOutputStream fos = new FileOutputStream(filePath);
	    workbook.write(fos);
	    fos.close();
	    workbook.close();
	}
	
}
