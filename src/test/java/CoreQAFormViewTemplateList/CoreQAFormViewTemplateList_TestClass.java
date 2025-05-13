package CoreQAFormViewTemplateList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDate;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;

import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreQAFormViewTemplateListPageRepo;

import com.Page_Repository.DispositionMasterPageRepo;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.QAFormViewTemplateList.CoreQAFormViewTemplateList_MainClass;

//updated code
public class CoreQAFormViewTemplateList_TestClass extends Base_Class {
	private static final int Map = 0;
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;

	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreQAFormViewTemplateList_MainClass CoreQAFormViewTemplate;

	@BeforeSuite

	public void SetUp() throws Exception {

		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CoreLogin();

		driver = baseclass.getDriver(); // Retrieve the driver instance

		CoreQAFormViewTemplate = new CoreQAFormViewTemplateList_MainClass(driver);
		ExcelReader = new com.Utility.ExcelReader("CoreQAForm");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		drivers.add(driver);

		callcenterlogin = new Login_Class();
		// Update the ScreenShot object with the new driver
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreQAForm");
	}

	@Test(priority = 1, enabled = true)
	public void Execute_the_Deletion_Query() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			String DeleteQuery = "DELETE FROM QAF_OPTION_ANSWER_DETAILS";
			String Result = DBUtils.executeSQLStatement(DeleteQuery);
			System.out.println("Delete Query Result: " + Result);

			String DeleteQueryTemplate = "DELETE FROM QAF_OPTION_TEMPLATE_DETAILS";
			String TemplateDetails = DBUtils.executeSQLStatement(DeleteQueryTemplate);
			System.out.println("Delete Query Result: " + TemplateDetails);

			String DeleteQueryAnswerMaster = "DELETE FROM QAF_OPTION_ANSWER_MASTER";
			String AnswerMaster = DBUtils.executeSQLStatement(DeleteQueryAnswerMaster);
			System.out.println("Delete Query Result: " + AnswerMaster);

			String DeleteQueryTemplateDetails = "DELETE FROM QAF_QSTN_TEMPLATE_DETAILS";
			String TemplateDetail = DBUtils.executeSQLStatement(DeleteQueryTemplateDetails);
			System.out.println("Delete Query Result: " + TemplateDetail);

			String DeleteQueryTemplateHistory = "DELETE FROM QAF_QSTN_TEMPLATE_HISTORY";
			String TemplateHistory = DBUtils.executeSQLStatement(DeleteQueryTemplateHistory);
			System.out.println("Delete Query Result: " + TemplateHistory);

			String DeleteQueryCategoryMapping = "DELETE FROM QAF_TEMPLATE_CATEGORY_MAPPING";
			String CategoryMapping = DBUtils.executeSQLStatement(DeleteQueryCategoryMapping);
			System.out.println("Delete Query Result: " + CategoryMapping);

			String DeleteQueryTemplateMaster = "DELETE FROM QAF_QSTN_TEMPLATE_MASTER";
			String TemplateMaster = DBUtils.executeSQLStatement(DeleteQueryTemplateMaster);
			System.out.println("Delete Query Result: " + TemplateMaster);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 2, enabled = true)
	public void Login_to_CoreWithHO_User() throws InterruptedException {

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

			// Home
			System.out.println(" ************** 2 *****************");

			System.out.println();
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("Home"), "Navigated to Home Page.");

			ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Home  page, URL shows Home.");
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// Common.fluentWait("SMA", AddAgencyPageRepo.SMA);
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	
	}

	@Test(priority = 3, enabled = true)
	public void AccesQAFormViewTemplateList() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 3 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

			// Navigate to QAFormView Main Menu

			CoreQAFormViewTemplate.AccesQAFormViewTemplateList();
			ExtentTestManager.getTest().log(Status.PASS, "1. click on the  Q&A Form From menu section");

			// Click on View Template List

			CoreQAFormViewTemplate.navigateToViewTemplateList();
			ExtentTestManager.getTest().log(Status.PASS, "2.click on the submenu View template list");

			// Expected Result: User is navigated to the Q&A Template Page
			// URL shows Q&AForm/View Template List

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("QAformview"), "User is not navigated to the Q&A Template Page");
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to the Q & A Template Page, URL showsQ&AForm/ View Template List");
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		
	}

	@Test(priority = 4, enabled = true)
	public void NavigatetoTemplatecreationpage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 4 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Navigate to Template Creation Page");

			// Verify Create New Button

			CoreQAFormViewTemplate.NavigatetoTemplatecreationpage();
			ExtentTestManager.getTest().log(Status.PASS, "1. verify Create New button is present in the page");

			// Click on Create New Button

			CoreQAFormViewTemplate.NavigatetoTemplatecreationpage();
			ExtentTestManager.getTest().log(Status.PASS, "2.click on the Create New Button");

			// Expected Result: User is navigated to the Q&A Template Page
			// URL shows Q&AForm/View Template List

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("QAformview"), "User is not navigated to the Template creation page");
			ExtentTestManager.getTest().log(Status.PASS, "User is navigated to the Template creation page");
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
	
	}

	@Test(priority = 5, enabled = true)
	public void Validate_Fields_From_TemplateCreation_Page() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 5 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Template Creation page loaded");

			// Navigate to Template Creation Page

			CoreQAFormViewTemplate.VerifyrequiredFileds();
			ExtentTestManager.getTest().log(Status.PASS,
					"1. verify following fields and buttons  are present in the page Template Name,Question,Expected Answer Type,Values,Parent Question,Parent Value,Is Mandatory (Checkbox),Add Button,Clear Button,Cancel Button");

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("QAForm/Templatecreationpage"),
					"Following Fields and Buttons not Displayed on the Template creation Page");
			ExtentTestManager.getTest().log(Status.PASS,
					"Following Fields and Buttons Displayed on the Template creation Page");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	@Test(priority = 6, enabled = true)
	public void verify_the_columns_showing_in_the_grid_section() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 6 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Verify Columns Showing in the Grid Section");

			// Verify Grid

			CoreQAFormViewTemplate.VerifyGridSection();
			ExtentTestManager.getTest().log(Status.PASS,
					"1. verify following columns  are present in the Question grid section Order Number,Question,Expected Answer Type,Values,Parent Question,Parent Value,Is Mandatory,Action");

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("QAForm/Verifying Grid"),
					"Following columns are not Displayed on the Grid Section");
			ExtentTestManager.getTest().log(Status.PASS, "Following columns are present in the question grid section");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}

	}

	// 7th Testcase not Possible to Automate

	@Test(priority = 8, enabled = true)
	public void verify_the_Mandatory_Filed_Validation() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		try {
			System.out.println(" ************** 8 *****************");
			System.out.println();
			ExtentTestManager.getTest().log(Status.PASS, "Verify Columns Showing in the Grid Section");

			// Verify the mandatory field validation

			CoreQAFormViewTemplate.VerifyMandatoryFieldValidation();
			ExtentTestManager.getTest().log(Status.PASS,
					"1. verify following columns  are present in the Question grid section Order Number,Question,Expected Answer Type,Values,Parent Question,Parent Value,Is Mandatory,Action");

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("QAForm/Verifying Grid"),
					"Following columns are not Displayed on the Grid Section");
			ExtentTestManager.getTest().log(Status.PASS, "Following columns are present in the question grid section");

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		
	}

	@Test(priority = 9, enabled = true)
	public void Validate_Value_Into_Template_Name_Field() throws InterruptedException {
		try {
			System.out.println(" ************** 9 *****************");
			ExtentTestManager.getTest().log(Status.PASS, "Template Creation page loaded");

			CoreQAFormViewTemplate.InputValueIntoTemplateNameField();

			ExtentTestManager.getTest().log(Status.PASS, "Template Name field validated successfully.");
		} catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}	
	}
	@Test(priority = 10, enabled = true)
	public void Validate_Value_Into_Question_Field() throws InterruptedException {
	    try {
	        System.out.println(" ************** 10 *****************");
	        ExtentTestManager.getTest().log(Status.PASS, "Template Creation page loaded");

	        // Call your action
	        CoreQAFormViewTemplate.InputValueIntoQuestionField();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on Question field and entered 'Q1'");

	        // Optionally, assert that the value was entered
	        WebElement questionField = driver.findElement(CoreQAFormViewTemplateListPageRepo.Question);
	        String entered = questionField.getAttribute("value");
	        Assert.assertEquals(entered, "Q1", "Question field did not contain expected text");
	        ExtentTestManager.getTest().log(Status.PASS, "Question field value verified as 'Q1'");

	    } catch (AssertionError | Exception e) {
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
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

				extenttest.log(Status.INFO, "Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {
		ExcelReader = new com.Utility.ExcelReader("CoreQAForm");
		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

	@AfterSuite
	public void afterEachTest() {
		ExtentManager.getInstance().flush();
		// Close all tracked browser instances
		// for (WebDriver driverInstance : drivers) {
		// if (driverInstance != null) {
		// driverInstance.quit();
	}
	// }

	// Clear the list of drivers
	// drivers.clear();

	// System.out.println("All browser instances have been closed.");
}

// }
