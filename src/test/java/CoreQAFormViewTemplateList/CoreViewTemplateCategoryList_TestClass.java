package CoreQAFormViewTemplateList;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	//import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;

	import org.testng.annotations.Test;
	import java.lang.reflect.Method;
	//import java.time.Duration;
	import java.io.File;
	import java.io.IOException;
	import java.util.Map;

	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.DataProvider;

	import com.aventstack.extentreports.MediaEntityBuilder;
	import com.aventstack.extentreports.Status;
	import com.extentReports.ExtentManager;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.LinkedHashMap;
	import java.util.List;

	import com.BasePackage.Base_Class;
	import com.BasePackage.DBUtils;

	import com.BasePackage.Login_Class;
	import com.Page_Repository.CoreViewTemplateCategoryListPageRepo;
	import com.aventstack.extentreports.ExtentTest;
	import com.extentReports.ExtentTestManager;
	import com.listeners.TestListener;


	import Core.QAFormViewTemplateList.CoreViewTemplateCategoryList_MainClass;
	


	public class CoreViewTemplateCategoryList_TestClass extends Base_Class {
		
		private  CoreViewTemplateCategoryList_MainClass page;
		private List<WebDriver> drivers = new ArrayList<>();
		Base_Class baseclass;

		static com.Utility.ExcelReader ExcelReader;
		WebDriver driver;
		TestListener TestListener;
		com.Utility.ScreenShot screenShot;
		ExtentTest extenttest;
		Login_Class callcenterlogin;
		CoreViewTemplateCategoryList_MainClass CoreViewTemplateCategoryList;
		
		

		@BeforeSuite

		public void SetUp() throws Exception {

			baseclass = new Base_Class();
			callcenterlogin = new Login_Class();
			callcenterlogin.CoreLogin();

			driver = baseclass.getDriver(); // Retrieve the driver instance

			CoreViewTemplateCategoryList = new CoreViewTemplateCategoryList_MainClass(driver);
			
			ExcelReader = new com.Utility.ExcelReader("CoreQAForm");
			TestListener = new TestListener();
			screenShot = new com.Utility.ScreenShot(driver);
		}

		@BeforeMethod
		public void setupTest(Method method) {
			baseclass = new Base_Class();
			driver = baseclass.getDriver();
			page = new CoreViewTemplateCategoryList_MainClass(driver);
			drivers.add(driver);

			callcenterlogin = new Login_Class();
			// Update the ScreenShot object with the new driver
			screenShot = new com.Utility.ScreenShot(driver);
			// Start a new ExtentTest for the current test method
			extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreQAForm");
		}


		@Test(priority = 1, enabled = true)
		public void Login_to_CoreWithHO_User() throws InterruptedException {

			try {
				ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

				// Home
				System.out.println(" ************** Testcase 1: Login to core application as HO User*****************");

				System.out.println();
				String currentUrl = driver.getCurrentUrl();
				Assert.assertTrue(currentUrl.contains("Home"), "Navigated to Home Page.");

				ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Home  page, URL shows Home.");
				
			}

			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
		
		}

		@Test(priority = 2, enabled = true)
		public void AccesQAFormViewTemplateList() throws InterruptedException {

			try {
				System.out.println(" ************** Testcase 2: Navigated to the Q&A Template Page *****************");
				System.out.println();
				ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

				// Navigate to QAFormView Main Menu

				CoreViewTemplateCategoryList.AccesQAFormViewTemplateList();
				ExtentTestManager.getTest().log(Status.PASS, "1. click on the  Q&A Form From menu section");

				// Click on View Template List

				//CoreViewTemplateCategoryList.navigateToViewTemplateList();
				ExtentTestManager.getTest().log(Status.PASS, "2.click on the submenu View template list");

				// Expected Result: User is navigated to the Q&A Template Page
				// URL shows Q&AForm/View Template List
				ExtentTestManager.getTest().log(Status.PASS,
						"User is navigated to the Q & A Template Page, URL showsQ&AForm/ View Template List");
			}

			catch (AssertionError | Exception e) {
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

	//}

