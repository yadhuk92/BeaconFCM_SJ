package Core.AddNewAgent;

import java.io.File;

import Core.AddNewAgentMethods.AddNewAgentPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
//import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
import java.lang.reflect.Method;
import com.listeners.TestListener;

import Core.Disposition.DispositionMasterPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;

public class AgentListTC_01 extends Base_Class {
	Log log;
	Base_Class Base_Class;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	DispositionMasterPage dispositionMasterPage;
	ExtentTest extenttest;
	Login_Class corelogin;
	Login_Class AllLoginCases;
	AddNewAgentPage AddNewAgentPage;

	@BeforeSuite
	public void reference() {
		log = new Log();
		TestListener = new TestListener();
		Base_Class = new Base_Class();
//	        AllLoginCases = new Login_Class();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeTest
	public void SetUp() throws Exception {
		ExcelReader = new com.Utility.ExcelReader("AddNewAgent");
		baseclass = new Base_Class();
		TestListener = new TestListener();
		// baseclass.SetUp();
		corelogin = new Login_Class();
		corelogin.CollectionAgencyLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		screenShot = new com.Utility.ScreenShot(driver);
		dispositionMasterPage = new DispositionMasterPage(driver);
		AddNewAgentPage = new AddNewAgentPage(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws Exception {
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("AddNewAgent master");
		System.out.println("Before method in disposition master");
	}

	@Test(priority = 1)
	public void Verify_AgentList_Navigation(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AgentList).click();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentCode);
			AddNewAgentPage.WaitLoader();

			String Text = driver.findElement(AgentListPageRepo.AgencyName).getText();
			String CoreUserName = AddNewAgentPage.configloader().getProperty("CoreUserName_Agency");
			if (Text.contains(CoreUserName)) {
				ExtentTestManager.getTest().log(Status.PASS, "Agency name displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "User is logged into the beacon fcm core application!");
			}
			// agent code
			if (driver.findElement(AgentListPageRepo.AgentCode).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.AgentCode + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.AgentCode + "WebElement not displayed");
			}
			// username
			if (driver.findElement(AgentListPageRepo.UserName).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.UserName + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.UserName + "WebElement not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.AgentName).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.AgentName + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.AgentName + "WebElement not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.Mobile).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Mobile + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Mobile + "WebElement not displayed");
			}
			// Role
			if (driver.findElement(AgentListPageRepo.Role).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Role + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Role + "WebElement not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Active).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Active + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Active + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.AddAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.AddAgent + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.AddAgent + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Search).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Search + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Search + "WebElement not displayed");
			}
		} catch (AssertionError | Exception e) {
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		}
	}

	@Test(priority = 4)
	public void Verify_AgentList_Navigation4(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation4").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			// TC 4
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "Add New Agent page displayed displayed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	@Test(priority = 5)
	public void Verify_AgentList_Navigation5(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation5").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			// TC 5

			if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.AgentCodeAddNewAgent + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.AgentCodeAddNewAgent + "WebElement not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Name + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Name + "WebElement not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.PhoneNumberAddNewAgent + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.PhoneNumberAddNewAgent + "WebElement not displayed");
			}

			if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.DateofJoining + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.DateofJoining + "WebElement not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Tenurity + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Tenurity + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Close + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Close + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Submit).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Submit + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Submit + "WebElement not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	public void Verify_AgentList_Navigation6(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation6").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			//// TC 6
			driver.findElement(By.xpath("//html")).click();
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			String formattedDate = currentDate.format(formatter);
			By AgencyName = By.xpath("//*[contains(text(),'" + formattedDate + "']");
			Common.fluentWait("UserNameField", AgencyName);
			String TextDate = driver.findElement(AgentListPageRepo.CurrentDate).getAttribute("value");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (TextDate.contains(formattedDate)) {
				ExtentTestManager.getTest().log(Status.PASS, "Current date is displayed in Date of joining");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Current date is displayed in Date of joining");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	@Test(priority = 7)
	public void Verify_AgentList_Navigation7(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation7").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			// TC 7
			driver.findElement(AgentListPageRepo.Close).click();
			ExtentTestManager.getTest().log(Status.PASS, "Add new Agent page closed successfully ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	@Test(priority = 8)
	public void Verify_AgentList_Navigation8(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation8").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			// TC 8
			driver.findElement(AgentListPageRepo.Submit).click();
			if (driver.findElement(AgentListPageRepo.ErrorForMandotoryFields).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Mandatory field need to fill error displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Mandatory field need to fill error not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

//TC 9
	@Test(priority = 9)
	public void Verify_AgentList_Navigation9(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation9").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys("1");
			driver.findElement(AgentListPageRepo.Name1).sendKeys("Ramesh");
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys("123Ramesh");
			driver.findElement(By.xpath("//html")).click();

			if (driver.findElement(AgentListPageRepo.InvalidPhoneNumber).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Invalid Phone Number error displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Invalid Phone Number error not displayed");
			}
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			driver.findElement(AgentListPageRepo.agenntrole).click();
			driver.findElement(AgentListPageRepo.Submit).click();
			ExtentTestManager.getTest().log(Status.PASS,
					"Agent is not created due to Invalid Phone Number error displayed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	@Test(priority = 10)
	public void Verify_AgentList_Navigation10(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation10").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			// TC 10
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);

			driver.findElement(AgentListPageRepo.NameAgentCode).clear();
			driver.findElement(AgentListPageRepo.Name1).clear();
			driver.findElement(AgentListPageRepo.MobileInput).clear();
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).clear();
			driver.findElement(AgentListPageRepo.ClearRole).click();

			String AgentCode = AddNewAgentPage.AgentCodeGenerator();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode);
			String Username = AddNewAgentPage.RandomNameGenerator();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username);
			String MobileNumber = AddNewAgentPage.MobileNumberGenerator();
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber);
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.agenntrole).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.Submit).click();
			AddNewAgentPage.WaitLoader();

			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.Usercreatedsuccessfully + "New agent added successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent");
			}

			//// TC 11
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode);
			String Username1 = AddNewAgentPage.RandomNameGenerator();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username1);
			String MobileNumber1 = AddNewAgentPage.MobileNumberGenerator();
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber1);
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(2000);
			driver.findElement(AgentListPageRepo.agenntrole).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.Submit).click();

			AddNewAgentPage.WaitLoader();
			Common.fluentWait("UserNameField", AgentListPageRepo.AlreadyUserExist);
			if (driver.findElement(AgentListPageRepo.AlreadyUserExist).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.AlreadyUserExist + "User is already exist");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.AlreadyUserExist + "Unable to add User");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}
	}

	@Test(priority = 18)
	public void Verify_AgentList_Navigation13(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Verify_AgentList_Navigation13").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);
			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			String Username2 = AddNewAgentPage.RandomNameGenerator();

			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();

			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);

			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.agenntrole).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.DatePicker).click();
			LocalDate today = LocalDate.now();
			int day = today.getDayOfMonth();
			int RequiredDay = day + 1;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
			driver.findElement(AgentListPageRepo.Submit).click();
			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Usercreatedsuccessfully
						+ "New agent added successfully for future joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent for Future date");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}
	}

	@Test(priority = 19)
	public void Verify_AgentList_Navigation14(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Beacon FCM core application
			// login").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();
			System.out.println("AgentCode2 :" + AgentCode2);
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			String Username2 = AddNewAgentPage.RandomNameGenerator();
			System.out.println("Username2U" + Username2);
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();
			System.out.println("MobileNumber2 :" + MobileNumber2);
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);

			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(2000);

			WebElement elementToClick = driver.findElement(By.xpath("//*[@aria-label='>agenntrole']"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.DatePicker).click();
			LocalDate today = LocalDate.now();
			int day = today.getDayOfMonth();
			int RequiredDay = day - 1;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
			driver.findElement(AgentListPageRepo.Submit).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Usercreatedsuccessfully
						+ "New agent added successfully for past joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent for past date");
			}

			// TC 20

			// ExtentTestManager.startTest("Beacon FCM core application
			// login").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AgentList).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username2);
			driver.findElement(AgentListPageRepo.Search1).click();
			AddNewAgentPage.WaitLoader();
			// table/thead/tr
			// Find elements with the specified locator
			List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr"));
			// Create a list to store the text values
			List<String> textValues = new ArrayList<>();
			// Iterate over the WebElements and extract text
			for (WebElement element : elements) {
				textValues.add(element.getText());
				String text = element.getText().trim(); // Trim to remove extra spaces
				if (!text.isEmpty()) { // Check if the text is not empty
					textValues.add(text);
					ExtentTestManager.getTest().log(Status.PASS, "Text is present after Search the Agent" + text);
				}
			}
			// Print the list of text values
			System.out.println("Text values: " + textValues);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	// TC21
	@Test(priority = 21)
	public void SearchAgentByInvalidUsername(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Beacon FCM core application
			// login").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AgentList).click();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).clear();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys("InvalidUser");
			driver.findElement(AgentListPageRepo.Search1).click();
			AddNewAgentPage.WaitLoader();

			if (driver.findElement(AgentListPageRepo.NoRecordToDisplay).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.NoRecordToDisplay + "No Record to Display shown successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.NoRecordToDisplay + "No Record statement is not displayed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}
	}

//	TC 22 23 24
	@Test(priority = 22)
	public void InActiveTheEgentAndverify(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			// ExtentTestManager.startTest("Beacon FCM core application
			// login").assignCategory("Login");
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);
			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();
			System.out.println("AgentCode2 :" + AgentCode2);
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			String Username2 = AddNewAgentPage.RandomNameGenerator();
			System.out.println("Username2U" + Username2);
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();
			System.out.println("MobileNumber2 :" + MobileNumber2);
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);

			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(2000);
			AddNewAgentPage.WaitLoader();
			WebElement elementToClick = driver.findElement(By.xpath("//*[@aria-label='>agenntrole']"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);

			driver.findElement(AgentListPageRepo.Submit).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(3000);
			AddNewAgentPage.WaitLoader();
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Usercreatedsuccessfully
						+ "New agent added successfully for past joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent for past date");
			}
			WebElement ActionMenu = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu);
			driver.findElement(AgentListPageRepo.ActivateDeactivate).click();
			Common.fluentWait("UserNameField", AgentListPageRepo.StatusChanged);
			if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.StatusChanged + "Status Changed Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.StatusChanged + "Status changed not Displayed");
			}
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username2);
			driver.findElement(AgentListPageRepo.IsActive).click();
			driver.findElement(AgentListPageRepo.Search1).click();
			AddNewAgentPage.WaitLoader();
			if (driver.findElement(AgentListPageRepo.InactiveStatus).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.InactiveStatus + "Inactive Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.InactiveStatus + "Inactive Status not Displayed");
			}

			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu);
			driver.findElement(AgentListPageRepo.ActivateDeactivate).click();
			Common.fluentWait("UserNameField", AgentListPageRepo.StatusChanged);
			if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.StatusChanged + "Status Changed Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.StatusChanged + "Status changed not Displayed");
			}
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.IsActive).click();
			driver.findElement(AgentListPageRepo.Search1).click();
			AddNewAgentPage.WaitLoader();
			if (driver.findElement(AgentListPageRepo.ActiveStatus).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.ActiveStatus + " Active Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.ActiveStatus + " Active Status not Displayed");
			}
			Thread.sleep(2000);
			Common.fluentWait("UserNameField", AgentListPageRepo.Action);
			WebElement ActionMenu1 = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu1);
			driver.findElement(AgentListPageRepo.ResetPassword).click();

			Common.fluentWait("UserNameField", AgentListPageRepo.PasswordResetStatus);
			if (driver.findElement(AgentListPageRepo.PasswordResetStatus).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.PasswordResetStatus + "Password Reset Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.PasswordResetStatus + "Password Reset Statusnot Displayed");
			}

//TC 32		
			Common.fluentWait("UserNameField", AgentListPageRepo.Action);
			WebElement ActionMenu2 = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu2);
			driver.findElement(AgentListPageRepo.Edit).click();
			AddNewAgentPage.WaitLoader();

			// is displayed
			if (driver.findElement(AgentListPageRepo.EditAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.EditAgent + "Edit Agent displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.EditAgent + "Edit Agent not displayed");
			}
			if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.AgentCodeAddNewAgent + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.AgentCodeAddNewAgent + "WebElement not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Name + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Name + "WebElement not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.PhoneNumberAddNewAgent + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.PhoneNumberAddNewAgent + "WebElement not displayed");
			}

			if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.DateofJoining + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.DateofJoining + "WebElement not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Tenurity + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Tenurity + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Close + "WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Close + "WebElement not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Update).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Update + " Update button displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Update + " Update not displayed");
			}

			String TextDate = driver.findElement(AgentListPageRepo.CurrentDate).getAttribute("value");
			String name = driver.findElement(AgentListPageRepo.Name).getAttribute("value");
			String Name1 = driver.findElement(AgentListPageRepo.Name1).getAttribute("value");
			String PhoneNumber = driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).getAttribute("value");
			String Tenurity = driver.findElement(AgentListPageRepo.Tenurity).getAttribute("value");
			String AgentCodeEdit = driver.findElement(AgentListPageRepo.AgentCodeEdit).getAttribute("value");
			
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);

			if (TextDate.contains(formattedDate)) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Update + " Date is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Update + " Date is not displayed");
			}
			if (AgentCode2.contains(AgentCodeEdit)) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Update + " Date is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Update + " Date is not displayed");
			}
			if (Name1.contains(Username2)) {
				ExtentTestManager.getTest().log(Status.PASS, AgentListPageRepo.Update + " name is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, AgentListPageRepo.Update + " name is not displayed");
			}

//			if (Name1.contains(formattedDate)) {
//				ExtentTestManager.getTest().log(Status.PASS, " Name1 is displayed");
//			} else {
//				ExtentTestManager.getTest().log(Status.FAIL, " Name1 is not displayed");
//			}

			if (PhoneNumber.contains(MobileNumber2)) {
				ExtentTestManager.getTest().log(Status.PASS, " PhoneNumber is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " PhoneNumber is not displayed");
			}

			if (Tenurity.contains("12")) {
				ExtentTestManager.getTest().log(Status.PASS, " Date is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Date is not displayed");
			}

			driver.findElement(AgentListPageRepo.Name1).clear();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2 + "Updated");
			driver.findElement(AgentListPageRepo.Update).click();

			Common.fluentWait("UserNameField", AgentListPageRepo.RecordUpdatedSuccessfully);
			if (driver.findElement(AgentListPageRepo.RecordUpdatedSuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.RecordUpdatedSuccessfully + "Record Updated Successfully Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.RecordUpdatedSuccessfully + "Record Updated Successfully not Displayed");
			}

			AddNewAgentPage.WaitLoader();
			// check is it displayed the updated name here only.

			String UpdatedUsername = driver.findElement(AgentListPageRepo.UpdatedUsername).getText();
//			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
//			driver.findElement(AgentListPageRepo.AgentManagement).click();
//			Thread.sleep(1000);
//			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			if (UpdatedUsername.contains(Username2 + "Updated")) {
				ExtentTestManager.getTest().log(Status.PASS,
						" Username updated successfully and it is displayed in AgentList");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Username not updated and it is displayed in AgentList");
			}
		} catch (InterruptedException e) {
			
			// TODO Auto-generated catch block
			System.out.println("*** Test execution LoginTest failed...");
			Log.error("*** Test execution LoginTest failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName, "LoginTest");
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		// Check if the test case failed
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			try {
				// Take the screenshot for the failed test
				File image = screenShot.takeScreenShot(methodName, "Failure");

		            ExtentTestManager.getTest()
	                .fail("Screenshot of failure: ",
	                      MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

				extenttest.log(Status.INFO, "Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	@AfterClass
	public void afterclass() {
		ExtentManager.getInstance().flush();
		// Close the browser
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {

		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

//	 @AfterSuite
//	 public void afterEachTest() {
//	     ExtentManager.getInstance().flush();
//	  // Close all tracked browser instances
//	        for (WebDriver driverInstance : drivers) {
//	            if (driverInstance != null) {
//	                driverInstance.quit();
//	            }
//	        }
//
//	        // Clear the list of drivers
//	        drivers.clear();
//
//	        System.out.println("All browser instances have been closed.");
//	    }

}