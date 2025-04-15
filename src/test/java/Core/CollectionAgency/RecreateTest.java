package Core.CollectionAgency;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.AgencyAccountAllocation_Repository;

import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.Utility.ScreenShot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;



public class RecreateTest extends Base_Class  {
	
	public String HOUserID;
	public String HOUserPassword;
	
	String UserID;
	String Password;
	
	public static String PreviouslyAllocatedAgency;
	
	 WebDriver driver;
		com.Utility.ExcelReader ExcelReader;
		Base_Class baseclass;
		Log log;
		TestListener TestListener;
		com.Utility.ScreenShot screenShot;
		
		AgentAccountAllocation_TestClass collectionAgency;
		AgencyAccountAllocation_mainClass CollectionAgency;
		AgencyAccountAllocation_Repository PageRepository;
		
		LoginPageRepo LoginPageRepositry;
		ExtentTest extenttest;
		Login_Class CoreAppLogin;

		
		@BeforeSuite
		public void reference() throws Exception {
		
			baseclass = new Base_Class();
			CoreAppLogin = new Login_Class();
			ExcelReader = new com.Utility.ExcelReader("Agency_Account_Allocation");
			log = new Log();
			TestListener = new TestListener();

			PageRepository= new AgencyAccountAllocation_Repository();
			
		}

		@BeforeMethod
	    public void ExtentTestManagerStartTest(Method method) throws InterruptedException {
			baseclass = new Base_Class();
			driver = baseclass.getDriver();
			screenShot = new com.Utility.ScreenShot(driver);
			CollectionAgency= new AgencyAccountAllocation_mainClass(driver);
	        // Start a new ExtentTest for the current test method
	        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("AgencyAccountAllocation");
	        Log.info("****" + method.getName() + "****");
	        Thread.sleep(500);
	    }


@Test(priority=1)
public void LogInToApp() throws Throwable {

	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Login with HOUser and HOPassword");
		CoreAppLogin.CoreLoginWithInputs(HOUserID, HOUserPassword);
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
}

@Test(priority=2)
public void NavigationToAgencyAccountAllocation() throws Throwable {
	try
	{
		
   	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
   	  CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
   	 
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Collection Agency main menu");
		CollectionAgency.clickCollectionAgencyMenu();
		
		 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(90));
		 ExtentTestManager.getTest().log(Status.PASS, "Click on Agency Account Allocation sub menu");
		 CollectionAgency.clickAgencyAccountAllocationMenu();
		 
		 CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
	}
	
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}

@Test (priority= 3, dataProvider= "TestData")
public void  SetDPDValue ( Map < Object,Object > testdata) throws Throwable {
 
try
{
	 System.out.println("********test dpd*************");
	 if(testdata.get("Run").toString().equalsIgnoreCase("Yes")){ 

		 int dpdValue = Integer.parseInt(testdata.get("DPD").toString());
			System.out.println(dpdValue);

	CollectionAgency.SetDPD(dpdValue);
	}	
		
	ExtentTestManager.getTest().log(Status.PASS, "Set DPD value");
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	click(PageRepository.DPDDropdown);
	
	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
	click(PageRepository.equalOperator);
	
	WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	click(PageRepository.DPDValue);
	
	if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
	{
		
			String dpdValue = (testdata.get("DPD").toString());
			System.out.println(dpdValue);
	
	WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
	CollectionAgency.DPDValue(dpdValue);
	}
}
catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
}
Thread.sleep(3000);
}


@Test(priority=4)
public void AccountTypeDropdownBasicFunctionality() throws Throwable {
	try
	{
	CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
	click(PageRepository.TypesOfAccdrp);
		
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "Verify visibility of the 'Types of Account' dropdown");
			CollectionAgency.isTypesOfAccountDropdownVisible();
			WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(90));
			 ExtentTestManager.getTest().log(Status.PASS, "Allocated and Not Allocated option should be listed");
			 CollectionAgency.ValuesOfTypesOfDropdown();

		 WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(60));
	//	 CollectionAgency.selectNotAllocated();
		 click(PageRepository.NotAllocatedValue);
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//       wait.until(ExpectedConditions.elementToBeSelected(PageRepository.NotAllocatedValue));
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
      ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
      Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
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
	public void AfterSuite() {
	     ExtentManager.getInstance().flush();
	  // Close the browser
//	        if (driver != null) {
//	            driver.quit();
	        }
}

