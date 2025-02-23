package Core.CallCentre;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import Core.CallCentre.CoreAutoAllocationPage;
import Core.CallCentre.CoreAutoAllocationPage.ProcedureResult;
import Core.CallCentre.CoreManualAllocationPage;
import bsh.ParseException;
import com.listeners.TestListener;

public class CoreAllocationSummary_TestClass {
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreManualAllocationPage coremanualallocationpage;

	@BeforeSuite

	public void SetUp() throws Exception {

		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CoreLogin();
		;
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coremanualallocationpage = new CoreManualAllocationPage(driver);

		ExcelReader = new com.Utility.ExcelReader("Core_Manual_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		drivers.add(driver);
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		callcenterlogin = new Login_Class();
		// Update the ScreenShot object with the new driver
		screenShot = new com.Utility.ScreenShot(driver);
		// Start a new ExtentTest for the current test method
		extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre ManualAllocation");
	}

	@Test(priority = 1)
	public void Login_to_call_centre_application_and_take_account_filtration_page() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
			ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
			// Navigate to Call Centre Main Menu
			coremanualallocationpage.navigateToMainMenu();
			ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");

			// Click on Manual Allocation option
			coremanualallocationpage.navigateToManualAllocationOption();
			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Manual Allocation sub-menu.");

			// Expected Result: User is navigated to Manual Allocation page
			// URL shows CallCentre/CallCentreLeadFiltration
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("CallCentre/ManualAllocationConfiguration"),
					"Not navigated to Manual Allocation page.");
			ExtentTestManager.getTest().log(Status.PASS,
					"User is navigated to Manual Allocation page, URL shows CallCentre/ManualAllocationConfiguration.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void Mandatory_Field_Validation_for_AssetCategory_and_ToField() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {

			ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category");
			String warningMsg = coremanualallocationpage.validateWarningMessageWithoutAssetCetegory();

			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
			// Verify the warning message
			String expectedMessage = "Asset Category Required";

			Assert.assertEquals(warningMsg, expectedMessage, "Warning message not displayed as expected");
			ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Assigned Categoty  Required\".");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
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
		for (WebDriver driverInstance : drivers) {
			if (driverInstance != null) {
				driverInstance.quit();
			}
		}

		// Clear the list of drivers
		drivers.clear();

		System.out.println("All browser instances have been closed.");
	}

}
