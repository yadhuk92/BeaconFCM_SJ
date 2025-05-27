package Core.CallCentre;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Utility.ExcelReader;
import com.Utility.Log;
import com.Utility.ScreenShot;
import com.CollectionAgency.AllocationSummary.CA_Allocation_Summary_Page;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.listeners.TestListener;

public class All_Scenrios_Core_Allocation_Summary_Test {

	Base_Class baseclass;
	static ExcelReader excelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener testListener;
	ScreenShot screenShot;
	ExtentTest extentTest;
	Core_Allocation_Summary_Page allocationSummaryPage;

	@BeforeClass
	public void setUp() throws Exception {
		baseclass = new Base_Class();
		testListener = new TestListener();
		excelReader = new ExcelReader("Core-AllocationSummary");
		screenShot = new com.Utility.ScreenShot(driver);
		allocationSummaryPage = new Core_Allocation_Summary_Page(driver);
	}

	@BeforeMethod
	public void setupTest(Method method) throws IOException, InterruptedException, Exception {
		driver = baseclass.getDriver();
		drivers.add(driver);
		allocationSummaryPage = new Core_Allocation_Summary_Page(driver);
		screenShot = new ScreenShot(driver);
		extentTest = ExtentTestManager.startTest(method.getName()).assignCategory("Core-AllocationSummary");

	}

	@Test(priority = 1)
	public void testLogin() throws Exception {
		Login_Class.CoreLogin(); // Assuming login is in baseclass; if not, use a dedicated login class
		extentTest.log(Status.PASS, "Login successful");
		Log.info("Login successful");
	}

	@Test(priority = 2, dependsOnMethods = "testLogin")
	public void testVerifyAllocationSummarySubmenu() {
		allocationSummaryPage.VerifyAllocationSummaryDisplayingasSubmenu();
		extentTest.log(Status.PASS, "Verified Allocation Summary submenu displayed");
		Log.info("Verified Allocation Summary submenu displayed");
	}

	@Test(priority = 3, dependsOnMethods = "testVerifyAllocationSummarySubmenu")
	public void testClickOnAllocationSummarySubmenu() throws InterruptedException {
		allocationSummaryPage.ClickonAllocationSummarysubmenu();
		extentTest.log(Status.PASS, "Clicked on Allocation Summary submenu");
		Log.info("Clicked on Allocation Summary submenu");
	}

	@Test(priority = 4, dependsOnMethods = "testClickOnAllocationSummarySubmenu")
	public void testVerifyFieldsAndButtonsOnAllocationSummary() throws InterruptedException {
		allocationSummaryPage.VerifyFieldsandButtononAllocationSummary();
		extentTest.log(Status.PASS, "Verified fields and buttons on Allocation Summary");
		Log.info("Verified fields and buttons on Allocation Summary");
	}

	@Test(priority = 5, dependsOnMethods = "testVerifyFieldsAndButtonsOnAllocationSummary")
	public void testSearchWithoutSelectingCallCentreDropdown() throws InterruptedException {
		allocationSummaryPage.SearchWithoutSelectingSelectCallCentredropdown();
		extentTest.log(Status.PASS, "Tested search without selecting Call Centre dropdown");
		Log.info("Tested search without selecting Call Centre dropdown");
	}

	@Test(priority = 6, dependsOnMethods = "testSearchWithoutSelectingCallCentreDropdown")
	public void testSearchWithSelectedCallCentre() throws InterruptedException {
		allocationSummaryPage.SearchwithSelectedCallCentre();
		extentTest.log(Status.PASS, "Tested search with selected Call Centre");
		Log.info("Tested search with selected Call Centre");
	}

	@Test(priority = 7, dependsOnMethods = "testSearchWithSelectedCallCentre")
	public void testDownloadMonthlyAllocationSummary() throws InterruptedException, AWTException, IOException {
		allocationSummaryPage.DownloadMonthlyAllocationSummary();
		extentTest.log(Status.PASS, "Downloaded Monthly Allocation Summary");
		Log.info("Downloaded Monthly Allocation Summary");

	}

	@Test(priority = 8, dependsOnMethods = "testDownloadMonthlyAllocationSummary")
	public void testDataVerificationInDownloadedFile() {
		allocationSummaryPage.DataVerificationinDownloadedFile();
		extentTest.log(Status.PASS, "Verified data in downloaded file");
		Log.info("Verified data in downloaded file");
	}

	@Test(priority = 9, dependsOnMethods = "testDataVerificationInDownloadedFile")
	public void testDownloadDailyAllocationFromMonthlySummary() throws InterruptedException, AWTException {
		allocationSummaryPage.DownloadDailyAllocationfromMonthlySummary();
		extentTest.log(Status.PASS, "Downloaded Daily Allocation from Monthly Summary");
		Log.info("Downloaded Daily Allocation from Monthly Summary");
	}

	@Test(priority = 10, dependsOnMethods = "testDownloadDailyAllocationFromMonthlySummary")
	public void testDataVerificationInDailyDownloadedFile() {
		allocationSummaryPage.DataVerificationinnDownloadedFile();
		extentTest.log(Status.PASS, "Verified data in daily downloaded file");
		Log.info("Verified data in daily downloaded file");
	}

	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			try {
				File image = screenShot.takeScreenShot(methodName, "Failure");
				extentTest.log(Status.FAIL, "Test Failed. Screenshot below:",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());
			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}

	@AfterClass
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
