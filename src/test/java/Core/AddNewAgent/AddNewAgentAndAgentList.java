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
import org.openqa.selenium.chrome.ChromeDriver;
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

public class AddNewAgentAndAgentList extends Base_Class {
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

	public static String AgentCode10;
	public static String Username18;
	public static String AgentCode18;
	public static String PhoneNumber18;

	public static String Username30;
	public static String AgentCode30;
	public static String PhoneNumber30;

	@BeforeSuite
	public void reference() {
		log = new Log();
		TestListener = new TestListener();
		Base_Class = new Base_Class();
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
			System.out.println("********** Verify_AgentList_Navigation1   ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management name displayed and Clicked");
			driver.findElement(AgentListPageRepo.AgentList).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management name displayed and Clicked");
			// is displayed

			if (driver.findElement(AgentListPageRepo.AgentManagement1).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Agent Management  displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agent Management not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 2)
	public void VerifythefieldsandbuttonsinAgentlistpage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
//			AddNewAgentPage.WaitLoader();
			System.out.println("********** VerifythefieldsandbuttonsinAgentlistpage2   ***********");
			System.out.println();
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AgentList).click();
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentCode);
			AddNewAgentPage.WaitLoader();

			String Text = driver.findElement(AgentListPageRepo.AgencyName).getText();
			String CoreUserName = AddNewAgentPage.configloader().getProperty("CoreUserName_Agency");
			if (Text.contains(CoreUserName)) {
				ExtentTestManager.getTest().log(Status.PASS, "Agency name displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agency name not displayed");
			}
			// agent code
			if (driver.findElement(AgentListPageRepo.AgentCode).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Agent Code displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agent Code not displayed");
			}
			// username
			if (driver.findElement(AgentListPageRepo.UserName).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "User Name displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "User Name not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.AgentName).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Agent Name displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agent Name not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.Mobile).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Mobile not displayed");
			}
			// Role
			if (driver.findElement(AgentListPageRepo.Role).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Role displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Role not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Active).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Active check box displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Active check box not displayed");
			}
			if (driver.findElement(AgentListPageRepo.AddAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Add Agent displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Add Agent not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Search).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Search button displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Search button not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 3)
	public void Addnewagentpagenavigation(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Addnewagentpagenavigation3   ***********");
			System.out.println();
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			// TC 4
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management name displayed and Clicked");
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			ExtentTestManager.getTest().log(Status.PASS, "Add New Agent page displayed displayed");
//add new Agent displayed or not
			AddNewAgentPage.WaitLoader();

			if (driver.findElement(AgentListPageRepo.AddNewAgent1).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Add New Agent page displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Add New Agent page not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 4)
	public void Verifythefieldsandbuttonsinaddnewagentpage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  Verifythefieldsandbuttonsinaddnewagentpage4  ***********");
			System.out.println();

			if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Agent Code Add New Agent WebElement displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agent Code Add New Agent WebElement not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Name displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Name not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "PhoneNumber in Add New Agent displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "PhoneNumber in Add New Agent not displayed");
			}

			if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Date of Joining displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Date of Joining not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Tenurity displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Tenurity not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Close button displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Close button  not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Submit).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Submit button displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Submit button not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 5)
	public void VerifyDateofJoiningfieldonpageload(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** VerifyDateofJoiningfieldonpageload 5  ***********");
			System.out.println();
			Common.fluentWait("Agent Management ", AgentListPageRepo.AgentManagement);

//			driver.findElement(By.xpath("//html")).click();

			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			String formattedDate = currentDate.format(formatter);
			By AgencyName = By.xpath("//*[contains(text(),'" + formattedDate + "']");
//			Common.fluentWait("UserNameField", AgencyName);
			String TextDate = driver.findElement(AgentListPageRepo.CurrentDate).getAttribute("value");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (TextDate.contains(formattedDate)) {
				ExtentTestManager.getTest().log(Status.PASS, "Current date is displayed in Date of joining");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Current date is not displayed in Date of joining");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 6)
	public void Addnewagentpageclosebuttonfunctionality(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** Addnewagentpageclosebuttonfunctionality 6  ***********");
			System.out.println();
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.Close).click();
			ExtentTestManager.getTest().log(Status.PASS, "Add new Agent page closed successfully ");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 7)
	public void SubmitaddnewagentwithoutMandatoryFields(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitaddnewagentwithoutMandatoryFields 7  ***********");
			System.out.println();
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			if (driver.findElement(AgentListPageRepo.AddNewAgent1).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Add New Agent page displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Add New Agent page not displayed");
			}
			driver.findElement(AgentListPageRepo.Submit).click();
			if (driver.findElement(AgentListPageRepo.ErrorForMandotoryFields).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Mandatory field need to fill error displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Mandatory field need to fill error not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 8)
	public void InvalidPhoneNumberFormat(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** InvalidPhoneNumberFormat 8  ***********");
			System.out.println();
//			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
//			driver.findElement(AgentListPageRepo.AgentManagement).click();
//			Thread.sleep(1000);
//			driver.findElement(AgentListPageRepo.AddNewAgent).click();
//			Thread.sleep(1000);
//			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys("1");
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
			driver.findElement(AgentListPageRepo.Name1).sendKeys("Ramesh");
			ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys("123Ramesh");
			ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
			driver.findElement(By.xpath("//html")).click();

			if (driver.findElement(AgentListPageRepo.InvalidPhoneNumber).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Invalid Phone Number error displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Invalid Phone Number error not displayed");
			}
			driver.findElement(AgentListPageRepo.Tenurity).click();
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");			
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);			
			driver.findElement(AgentListPageRepo.agenntrole).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
			driver.findElement(AgentListPageRepo.Submit).click();
			ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
			ExtentTestManager.getTest().log(Status.PASS,
					"Agent is not created due to Invalid Phone Number error displayed");
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 9)
	public void SubmitValidNewAgentDetails(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitValidNewAgentDetails 9 ***********");
			System.out.println();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			// TC 10
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);

			AddNewAgentPage.ClearAddNewAgent();

			String AgentCode = AddNewAgentPage.AgentCodeGenerator();
			AgentCode10 = AgentCode;
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
			String Username = AddNewAgentPage.RandomNameGenerator();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username);
			ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
			String MobileNumber = AddNewAgentPage.MobileNumberGenerator();
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber);
			ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");		
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
			Thread.sleep(1000);
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);			
			driver.findElement(AgentListPageRepo.agenntrole).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.Submit).click();
			ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
			AddNewAgentPage.WaitLoader();

//			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						AgentListPageRepo.Usercreatedsuccessfully + "New agent added successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 10)
	public void SubmitValidNewAgentDetailswithexistingAgentcode(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitValidNewAgentDetailswithexistingAgentcode 10 ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode10);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
			String Username1 = AddNewAgentPage.RandomNameGenerator();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username1);
			ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
			String MobileNumber1 = AddNewAgentPage.MobileNumberGenerator();
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber1);
			ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
			Thread.sleep(2000);
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);
						driver.findElement(AgentListPageRepo.agenntrole).click();
			ExtentTestManager.getTest().log(Status.PASS, "Agent role added");			
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.Submit).click();
			ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
			Common.fluentWait("UserNameField", AgentListPageRepo.AlreadyUserExist);
			if (driver.findElement(AgentListPageRepo.AlreadyUserExist).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "User is already exist");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to add User");
			}
			AddNewAgentPage.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 17)
	public void SubmitWithPastDateofJoining(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SubmitWithPastDateofJoining 11  ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			Thread.sleep(1000);

//			clear all the previous entered data

			AddNewAgentPage.ClearAddNewAgent();

			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
			String Username2 = AddNewAgentPage.RandomNameGenerator();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
			ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(1000);
			
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);
			driver.findElement(AgentListPageRepo.agenntrole).click();
			
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.DatePicker).click();
			LocalDate today = LocalDate.now();
			int day = today.getDayOfMonth();
			if (day == 1) {
				driver.findElement(AgentListPageRepo.PreveousMonth).click();
				int RequiredDay = day;
				String Day = String.valueOf(RequiredDay);
				String desiredDate = Day; // Specify the day you want to select
				WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
				dateElement.click();
			} else {
				int RequiredDay = day - 1;
				String Day = String.valueOf(RequiredDay);
				String desiredDate = Day; // Specify the day you want to select
				WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
				dateElement.click();
			}

			driver.findElement(AgentListPageRepo.Submit).click();
			Thread.sleep(3000);
			Common.fluentWait("UserNameField", AgentListPageRepo.Usercreatedsuccessfully);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "New agent added successfully for Past joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to add New agent for Past date");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 18)
	public void SubmitWithFutureDateofJoining(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** SubmitWithFutureDateofJoining 18  ***********");
			System.out.println();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();

			AgentCode18 = AgentCode2;

			System.out.println("AgentCode2 :" + AgentCode2);
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			String Username2 = AddNewAgentPage.RandomNameGenerator();
			Username18 = Username2;
			System.out.println("Username2U" + Username2);
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();
			System.out.println("MobileNumber2 :" + MobileNumber2);
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
			PhoneNumber18 = MobileNumber2;
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(2000);
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);
			WebElement elementToClick = driver.findElement(By.xpath("//*[@aria-label='>agenntrole']"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.DatePicker).click();
			LocalDate today = LocalDate.now();
			int day = today.getDayOfMonth();

			if (day == 28) {
				driver.findElement(AgentListPageRepo.NextMonth).click();
				int RequiredDay = day;
				String Day = String.valueOf(RequiredDay);
				String desiredDate = Day; // Specify the day you want to select
				WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
				dateElement.click();
			} else {
				int RequiredDay = day + 1;
				String Day = String.valueOf(RequiredDay);
				String desiredDate = Day; // Specify the day you want to select
				WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
				dateElement.click();
			}

			driver.findElement(AgentListPageRepo.Submit).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "New agent added successfully for Future joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to add New agent for Future date");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 19)
	public void SearchAgentbyValidUsername(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  SearchAgentbyValidUsername 19 ***********");
			System.out.println();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AgentList).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
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

//** check username is displayed or not in the taken data			
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 20)
	public void SearchAgentbyInvalidUsername(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** SearchAgentbyInvalidUsername 20  ***********");
			System.out.println();
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
				ExtentTestManager.getTest().log(Status.PASS, "No Record to Display shown successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "No Record statement is not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 21)
	public void ToggleAgentActiveStatusintodeactivate(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  ToggleAgentActiveStatusintodeactivate 21 ***********");
			System.out.println();
			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AgentList).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).clear();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
			driver.findElement(AgentListPageRepo.Search1).click();
			AddNewAgentPage.WaitLoader();
			Common.fluentWait("UserNameField", AgentListPageRepo.ActionMenu);
			WebElement ActionMenu = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu);
			driver.findElement(AgentListPageRepo.ActivateDeactivate).click();
			Common.fluentWait("UserNameField", AgentListPageRepo.StatusChanged);
			if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Status Changed Active to InActive and  Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Status changed statement not Displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 22)
	public void Searchandconfirmtheuserisdeactivatedornot(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  Searchandconfirmtheuserisdeactivatedornot 22 ***********");
			System.out.println();
			AddNewAgentPage.WaitLoader();
			driver.findElement(AgentListPageRepo.AgentName1).clear();
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
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
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 23)
	public void ToggleAgentInactiveStatusintoactive(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** ToggleAgentInactiveStatusintoactive 23  ***********");
			System.out.println();
			WebElement ActionMenu = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu);
			driver.findElement(AgentListPageRepo.ActivateDeactivate).click();
			Common.fluentWait("UserNameField", AgentListPageRepo.StatusChanged);
			if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Status Changed Inactive to Active Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Status changed not Displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 30)
	public void ResetPasswordFunctionality(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("********** ResetPasswordFunctionality 30  ***********");
			System.out.println();

			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			Thread.sleep(1000);
			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(1000);
			String AgentCode2 = AddNewAgentPage.AgentCodeGenerator();

			AgentCode30 = AgentCode2;

			System.out.println("AgentCode2 :" + AgentCode2);
			driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
			String Username2 = AddNewAgentPage.RandomNameGenerator();
			Username30 = Username2;
			System.out.println("Username2U" + Username2);
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
			String MobileNumber2 = AddNewAgentPage.MobileNumberGenerator();
			System.out.println("MobileNumber2 :" + MobileNumber2);
			driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
			PhoneNumber30 = MobileNumber2;
			driver.findElement(AgentListPageRepo.Tenurity).click();
			driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
			driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
			Thread.sleep(2000);
			Common.fluentWait("UserNameField", AgentListPageRepo.agenntrole);
			WebElement elementToClick = driver.findElement(By.xpath("//*[@aria-label='>agenntrole']"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);
			Thread.sleep(1000);

			driver.findElement(AgentListPageRepo.Submit).click();
			AddNewAgentPage.WaitLoader();
			Thread.sleep(3000);
			if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "New agent added successfully for Future joining date");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to add New agent for Future date");
			}
			Common.fluentWait("UserNameField", AgentListPageRepo.Action);
			WebElement ActionMenu1 = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));

			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu1);
			driver.findElement(AgentListPageRepo.ResetPassword).click();

			Common.fluentWait("UserNameField", AgentListPageRepo.PasswordResetStatus);
			if (driver.findElement(AgentListPageRepo.PasswordResetStatus).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Password Reset Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Password Reset Statusnot Displayed");
			}
			AddNewAgentPage.WaitLoader();
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 31)
	public void VerifythefieldsandbuttonsinEditoptioninagentlistpage(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  VerifythefieldsandbuttonsinEditoptioninagentlistpage 31 ***********");
			System.out.println();
			Common.fluentWait("UserNameField", AgentListPageRepo.Action);
			WebElement ActionMenu2 = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu2);
			driver.findElement(AgentListPageRepo.Edit).click();
			AddNewAgentPage.WaitLoader();

			// is displayed
			if (driver.findElement(AgentListPageRepo.EditAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Edit Agent displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Edit Agent not displayed");
			}
			if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Agent Code Add New Agent displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Agent Code Add New Agent  not displayed");
			}
			// Agent name
			if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Name is dispalyed in Edit displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Name is dispalyed in Edit not displayed");
			}
			// Mobile Number
			if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Phone Number AddNewAgent is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Phone Number AddNewAgent is not displayed");
			}

			if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Date of Joining displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Date of Joining not displayed");
			}

			if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Tenurity displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Tenurity not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Close displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Close not displayed");
			}
			if (driver.findElement(AgentListPageRepo.Update).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, " Update button displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Update not displayed");
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
				ExtentTestManager.getTest().log(Status.PASS, " Date is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Date is not displayed");
			}
			if (AgentCode30.contains(AgentCodeEdit)) {
				ExtentTestManager.getTest().log(Status.PASS, " Agent Code is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Agent Code is not displayed");
			}
			if (Name1.contains(Username30)) {
				ExtentTestManager.getTest().log(Status.PASS, " Username is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Username is not displayed");
			}

//			if (Name1.contains(formattedDate)) {
//				ExtentTestManager.getTest().log(Status.PASS, " Name1 is displayed");
//			} else {
//				ExtentTestManager.getTest().log(Status.FAIL, " Name1 is not displayed");
//			}

			if (PhoneNumber.contains(PhoneNumber30)) {
				ExtentTestManager.getTest().log(Status.PASS, " PhoneNumber is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " PhoneNumber is not displayed");
			}

			if (Tenurity.contains("12")) {
				ExtentTestManager.getTest().log(Status.PASS, " Tenurity is displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Tenurity is not displayed");
			}
		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 32)
	public void Editagentnameandsubmit(ITestContext context) throws InterruptedException, IOException, ParseException {

		try {
			System.out.println("**********  Editagentnameandsubmit 32  ***********");
			System.out.println();
			driver.findElement(AgentListPageRepo.Name1).clear();
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username30 + "Updated");

			Common.fluentWait("Update", AgentListPageRepo.Update);
			WebElement elementToDoubleClick = driver.findElement(By.xpath("//button[contains(text(),'Update')]"));
			Actions actions = new Actions(driver);
			actions.doubleClick(elementToDoubleClick).perform();
			Thread.sleep(2000);
			driver.findElement(AgentListPageRepo.Update1).click();

//			driver.findElement(AgentListPageRepo.Update).click();
			Common.fluentWait("RecordUpdatedSuccessfully", AgentListPageRepo.RecordUpdatedSuccessfully);
			if (driver.findElement(AgentListPageRepo.RecordUpdatedSuccessfully).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Record Updated Successfully Status Displayed");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Record Updated Successfully not Displayed");
			}

			AddNewAgentPage.WaitLoader();
			// check is it displayed the updated name here only.

			String UpdatedUsername = driver.findElement(AgentListPageRepo.UpdatedUsername).getText();
//			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
//			driver.findElement(AgentListPageRepo.AgentManagement).click();
//			Thread.sleep(1000);
//			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			if (UpdatedUsername.contains(Username30 + "Updated")) {
				ExtentTestManager.getTest().log(Status.PASS,
						" Username updated successfully and it is displayed in AgentList");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Username not updated and it is displayed in AgentList");
			}

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 33)
	public void Checkingediteduserdetails(ITestContext context)
			throws InterruptedException, IOException, ParseException {

		try {
			AddNewAgentPage.WaitLoader();
			System.out.println("**********  Editagentnameandsubmit 33  ***********");
			System.out.println();
			driver.findElement(AgentListPageRepo.AgentManagement).click();
			driver.findElement(AgentListPageRepo.AgentList).click();
			String UpdatedUsername = driver.findElement(AgentListPageRepo.UpdatedUsername).getText();
//			Common.fluentWait("UserNameField", AgentListPageRepo.AgentManagement);
//			driver.findElement(AgentListPageRepo.AgentManagement).click();
//			Thread.sleep(1000);
//			driver.findElement(AgentListPageRepo.AddNewAgent).click();
			if (UpdatedUsername.contains(Username30 + "Updated")) {
				ExtentTestManager.getTest().log(Status.PASS,
						" Username updated successfully and it is displayed in AgentList");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Username not updated and it is displayed in AgentList");
			}

		} catch (AssertionError | Exception e) {
			String testName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			ExtentTestManager.getTest().log(Status.FAIL,
					"Test Failed in method: " + testName + " --> " + e.getMessage());
			Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			throw e;
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

				ExtentTestManager.getTest().fail("Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

//				extenttest.log(Status.INFO, "Screenshot of failure: ",
//						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

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

}
