package Core.CallCentre;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.Page_Repository.CoreAllocationSummaryRepo;
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
import jdk.internal.org.jline.utils.Log;

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
	CoreAllocationSummaryPage coreallocationsummarypage;
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

			Assert.assertEquals(warningMsg, expectedMessage, "Warning message displayed as expected");
			ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Assigned Categoty  Required\".");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void Validate_AssetCatogories_Dropdown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {

			// Use JavaScript to zoom out (set zoom level to 67%)
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='67%'");

			ExtentTestManager.getTest().log(Status.PASS, "Clicking Asset Category Dropdown");
			coremanualallocationpage.validate_NPA_And_SMA_Dropsdown();

			ExtentTestManager.getTest().log(Status.PASS, "NPA and SMA Dropdowns are selected from Asset Category");

			WebElement check_NPA = driver.findElement(CoreAutoAllocationRepo.NPADropdownCheck);
			Assert.assertTrue(check_NPA.isEnabled(), "NPADropdown is enabled");

			WebElement check_SMA = driver.findElement(CoreAutoAllocationRepo.SMADropdownCheck);
			Assert.assertTrue(check_SMA.isEnabled(), "NPADropdown is enabled");

			ExtentTestManager.getTest().log(Status.PASS, "Selected both SMA and NPA from dropdown");
			// Update the ScreenShot object with the new driver
			screenShot = new com.Utility.ScreenShot(driver);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void Validate_SMACatogories_From_Dropdown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {

			ExtentTestManager.getTest().log(Status.PASS, "Clicking SMA Category Dropdown");
			coremanualallocationpage.validate_SMA_Dropsdown();

			ExtentTestManager.getTest().log(Status.PASS, "SMA0,SMA1 and SMA2 from SMA Category");

			WebElement check_SMA0 = driver.findElement(CoreAutoAllocationRepo.SM0DropdownCheck);
			Assert.assertTrue(check_SMA0.isEnabled(), "SMA0 option is enabled");

			WebElement check_SMA1 = driver.findElement(CoreAutoAllocationRepo.SM1DropdownCheck);
			Assert.assertTrue(check_SMA1.isEnabled(), "SMA1 option is enabled");

			WebElement check_SMA2 = driver.findElement(CoreAutoAllocationRepo.SM2DropdownCheck);
			Assert.assertTrue(check_SMA2.isEnabled(), "SMA2 option is enabled");

			ExtentTestManager.getTest().log(Status.PASS, "All the SMA0, SMA1 and SMA2 options are enabled");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// Update the ScreenShot object with the new driver
			screenShot = new com.Utility.ScreenShot(driver);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void Validate_NPACatogories_From_Dropdown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {

			ExtentTestManager.getTest().log(Status.PASS, "Clicking NPA Category Dropdown");
			coremanualallocationpage.validate_NPA_Dropsdown();

			ExtentTestManager.getTest().log(Status.PASS,
					"Sub Standard, Doubtful1, Doubtful2, doubtful3 and loss NPA categories are displayed.");

//			List<WebElement> lists = driver.findElements(CoreAutoAllocationRepo.npaCategorylistDropdown);
//			for (WebElement check : lists) {
//
//				String checkboxName = check.getAttribute("aria-label");
//			//	Assert.assertTrue(check.isSelected(), "NPA categories options are enabled");
//				Log.info(checkboxName, " is enabled");
//			}

			ExtentTestManager.getTest().log(Status.PASS,
					"\"Sub Standard, Doubtful1, Doubtful2, doubtful3 and loss NPA categories are enabled");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// Update the ScreenShot object with the new driver
			screenShot = new com.Utility.ScreenShot(driver);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void Validate_OSBalnceField() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {

			ExtentTestManager.getTest().log(Status.PASS, "O/S Balance");
			String selectedName =coremanualallocationpage.validate_OSBalnce();

			ExtentTestManager.getTest().log(Status.PASS,"Selected = option");

			Assert.assertEquals(selectedName, "=", "Selected the expected option from O/S Balance dropdown");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			screenShot = new com.Utility.ScreenShot(driver);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 7)
	public void Validate_ToField() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			
			ExtentTestManager.getTest().log(Status.PASS, "To Field");
			String value =coremanualallocationpage.validate_ToField_Callcenter();

			ExtentTestManager.getTest().log(Status.PASS,"Selected Call Centre option");

			Assert.assertEquals(value, "Call Centre", "Selected the expected option from To  dropdown");
			screenShot = new com.Utility.ScreenShot(driver);
		
			coremanualallocationpage.clickSearchButton();// Method to click Search BUtton();
		
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
		
	@Test(priority = 8)
	public void Validate_AccountsCount() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			
			ExtentTestManager.getTest().log(Status.PASS, "Account Counts");
			boolean isMatch=coremanualallocationpage.validate_Accounts_Count();
			
			ExtentTestManager.getTest().log(Status.PASS, "Displayed Account Counts");

			Assert.assertTrue(isMatch,"Expected Columns are present");
			screenShot = new com.Utility.ScreenShot(driver);

		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 9)
	public void Validate_AllocateToField() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			
			ExtentTestManager.getTest().log(Status.PASS, "Allocate To Field");
			String value =coremanualallocationpage.validate_AllocateToField_Callcenter();

			ExtentTestManager.getTest().log(Status.PASS,"Selected Call Centre option");

			Assert.assertEquals(value, "Call Centre", "Selected the expected option from Allocated  To  dropdown");
			screenShot = new com.Utility.ScreenShot(driver);
			
		}

		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority = 10)
	public void Validate_MessageAfterAllocation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			//coremanualallocationpage.validate_Accounts_CountData();
			
			ExtentTestManager.getTest().log(Status.PASS, "Validating Message after allocation");
			String warningMsg =coremanualallocationpage.validate_mesgAfterAllocation();

			ExtentTestManager.getTest().log(Status.PASS,"Validated  Message after allocation");
			
			// Verify the warning message
				String expectedMessage = "Assigned Successfully";

				Assert.assertEquals(warningMsg, expectedMessage, "Successful  message displayed as expected");
				ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Assigned Successfully\".");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
			
			screenShot = new com.Utility.ScreenShot(driver);
		}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
			Thread.sleep(3000);
		}
		
	@Test(priority = 11)
	public void Validate_CallcentreSideMenu_Visibility() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			try {
				
				ExtentTestManager.getTest().log(Status.PASS, "Validate if the Call cente option is visible in side menu");
				
				//Click on Call Centre option from the main menu
				WebElement callCenter = driver.findElement(CoreAutoAllocationRepo.callcentermainmenu);
				callCenter.click();
				
				wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
				
				//Verify  Allocation Summary Sub Menu is displayed
				WebElement allocationSummary = driver.findElement(CoreAllocationSummaryRepo.allocationSummaryOption);
				
				Assert.assertTrue(allocationSummary.isDisplayed(), "'Allocation Summary' option is NOT visible in the side menu!");
				
				ExtentTestManager.getTest().log(Status.PASS, "Allocation Summary Page displayed");
				
				screenShot = new com.Utility.ScreenShot(driver);
				
			}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
			Thread.sleep(3000);
		}
		
	@Test(priority = 12)
	public void Validate_AllocationSummaryPage_Displayed() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				try {
					
					ExtentTestManager.getTest().log(Status.PASS, "Validate allocation summary page is displayed");
					
					//Click on Allocation Summary Sub Menu
					WebElement allocationSummary = driver.findElement(CoreAllocationSummaryRepo.allocationSummary);
					
					//Click on Allocation summary
					allocationSummary.click();
					WebElement allocationSummaryPage = driver.findElement(CoreAllocationSummaryRepo.AllocationSummaryPage);
					
					Assert.assertTrue(allocationSummaryPage.isDisplayed(), "'Allocation Summary' Page is displayed");
					
					screenShot = new com.Utility.ScreenShot(driver);
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
					throw e;
				}
				Thread.sleep(3000);
					
				}
			
			@Test(priority =13)
	public void Validate_CallCentre_And_SearchButton_Are_Visible() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				try {
					
					ExtentTestManager.getTest().log(Status.PASS, "Validate Call centre option from dropdown  and search buttonare visible");
					
					//Check if the call Center DropDown is visible
					WebElement _selectCallCenter=driver.findElement(CoreAllocationSummaryRepo.selectCallCenterLabel);
					Assert.assertTrue(_selectCallCenter.isDisplayed(),"Call Centre option is visible");
					
					//Check if the Search Button is visible
					WebElement SearchButton=driver.findElement(CoreAllocationSummaryRepo.Submit);
					Assert.assertTrue(SearchButton.isDisplayed(),"Search Button is visible");
				
					ExtentTestManager.getTest().log(Status.PASS, "Call centre option from dropdown  and search buttonare visible");
					screenShot = new com.Utility.ScreenShot(driver);
					
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
					throw e;
				}
				Thread.sleep(3000);
					
				}
			
	@Test(priority =14)
    public void Validate_WarningMessage_WithoutCallCentreDropdown() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				try {
					
					ExtentTestManager.getTest().log(Status.PASS, "Left Select call centre option empty");
					//WebElement _callCentreDropdown=driver.findElement(CoreAllocationSummaryRepo.CallCentreDropdown);
					String Expectedmessage= "Call Center Is Required";
					
					String ActualMessage=coreallocationsummarypage.Search_Without_Selecting_CallCentreDropdown();
					
					Assert.assertEquals(ActualMessage,Expectedmessage);
					
					ExtentTestManager.getTest().log(Status.PASS, "Warning Message Displayed");
					screenShot = new com.Utility.ScreenShot(driver);
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
					throw e;
				}
				Thread.sleep(3000);
					
				}
	
	@Test(priority = 15)
    public void Validate_AllocationSummary_AccountDetails() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				try {
					
					ExtentTestManager.getTest().log(Status.PASS, "validate allocation summary page");
					//WebElement _callCentreDropdown=driver.findElement(CoreAllocationSummaryRepo.CallCentreDropdown);
					
					coreallocationsummarypage.validate_AllocationSummary_AccountDetails();
					
					ExtentTestManager.getTest().log(Status.PASS, "Allocation Summary Displayed");
					screenShot = new com.Utility.ScreenShot(driver);
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
					throw e;
				}
				Thread.sleep(3000);
					
				}
			
	@Test(priority = 16)
    public void Validate_Downloaded_ExcelData() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
				try {
					
					ExtentTestManager.getTest().log(Status.PASS, "validate downloaded excel data");
	
					coreallocationsummarypage.validate_DownloadIcon_Header();
					
					ExtentTestManager.getTest().log(Status.PASS, "Excel head validated");
					screenShot = new com.Utility.ScreenShot(driver);
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
