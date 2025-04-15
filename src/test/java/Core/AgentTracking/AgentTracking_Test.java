package Core.AgentTracking;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.AgentTracking_Repository;
import com.Page_Repository.LoginBannerConfiRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.AgentTracking.AgentTracking;
 

public class AgentTracking_Test {
	    
	    private WebDriver driver;
		com.Utility.ExcelReader ExcelReader;
		Base_Class baseclass;
		Log log;
		TestListener TestListener;
		com.Utility.ScreenShot screenShot;
		AgentTracking AgentTracking;
		AgentTracking Agent_Tracking;
		AgentTracking_Repository PageRepository;
		LoginPageRepo LoginPageRepositry;
		ExtentTest extenttest;
		Login_Class CoreAppLogin;
		
		
		@BeforeSuite
		public void reference() throws Exception {
			baseclass = new Base_Class();
			CoreAppLogin = new Login_Class();
//			ExcelReader = new com.Utility.ExcelReader("Login_banner_confi");
			log = new Log();
			TestListener = new TestListener();
			CoreAppLogin.CoreLogin();
			driver = baseclass.getDriver();
			if (driver == null) {
			    throw new RuntimeException("WebDriver is not initialized!");
			}
			screenShot = new com.Utility.ScreenShot(driver);
			AgentTracking= new AgentTracking(driver);
			PageRepository= new AgentTracking_Repository();
		}

		@BeforeMethod
	    public void ExtentTestManagerStartTest(Method method) throws InterruptedException {
			baseclass = new Base_Class();
			driver = baseclass.getDriver();
			AgentTracking= new AgentTracking(driver);
	        // Start a new ExtentTest for the current test method
	        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Login Banner Confi");
	        Log.info("****" + method.getName() + "****");
	        Thread.sleep(500);
	    }

//TC=Display of Agent Tracking Module		
	    @Test(priority=1)
	    public void NavigationToAgentTrackingMainMenu() throws Throwable {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {
	//CoreAppLogin.CoreLogin();
				ExtentTestManager.getTest().log(Status.INFO, "Login successful");
				Log.info("Login successfull !");
				
				Log.info("***Navigation to Agent Tracking main menu***");
				ExtentTestManager.getTest().log(Status.PASS,"Expected-\"Agent Tracking\" should be displayed in the main menu.");
				
			  boolean Flag1=AgentTracking.isAgentTrackingModuleDisplayed();
			  Log.info("Agent Tracking module is displayed in the main menu."+Flag1);
			  
			  Assert.assertTrue("Agent Tracking module is not visible in taskbar", AgentTracking.isAgentTrackingModuleDisplayed());
		
			 ExtentTestManager.getTest().log(Status.PASS,"Actual result-\"Agent Tracking\" module is displayed in the main menu.");

	    	}		 	
	    	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
	    }
	
//TC=Presence of Sub-Modules Under Agent Tracking
	    @Test(priority=2)
public void AreSubmodulesDisplayed() {
	    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	    	try
	    	{
	        ExtentTestManager.getTest().log(Status.PASS, "1.Click on \"Agent Tracking \" main menu");		
	    	 boolean Flag1=AgentTracking.clickAgentTrackingMainMenu();
	    	 log.info("1.Click on \"Agent Tracking \" main menu" +Flag1);
	    	 
	    	 WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
	 	    ExtentTestManager.getTest().log(Status.PASS, "2.Sub-modules \"Agent tracking\" and \"Cash or cheque settlement\" should be visible");		
	    	 boolean Flag2=AgentTracking.areSubmodulesVisible();
	    	 log.info("Actual result-Sub_modules \"Agent tracking\" and \"Cash or cheque settlement\" are visible" +Flag2);

	    	 ExtentTestManager.getTest().log(Status.PASS, "Actual result-Sub_modules \"Agent tracking\" and \"Cash or cheque settlement\" are visible"); 
	    	}		 	
	    	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
	    }	    
	    
//TC=1. Click on "Agent Tracking" sub-module
@Test(priority=3)	
public void ClickOnAgentTrackingSubMenu() {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
	 try
	 {
		 ExtentTestManager.getTest().log(Status.PASS, "1.Click on \"Agent Tracking \" Submenu"); 
		 boolean Flag3=AgentTracking.clickAgentTrackingSubMenu();
		 log.info("1.Click on \"Agent Tracking \" Submenu" +Flag3);
		
		 ExtentTestManager.getTest().log(Status.PASS, "Actual result-\"Agent Tracking\" window opens"); 
 	}		 	
 	catch(AssertionError|Exception e) {
 		
 		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
 	}
 }
	    
//TC=Filters for Officer Type, Zone, Region, Branch, Agency Name are present
@Test(priority=4)
public void AreFiltersPresents() {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	 try
	 {
		 WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(600));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='accordion-body']")));
		 ExtentTestManager.getTest().log(Status.PASS, "1.View available filters"); 
		 boolean Flag4=AgentTracking.checkFiltersPresent();
		 log.info("1.View available filters" +Flag4);
		
		 ExtentTestManager.getTest().log(Status.PASS, "Actual result-Filters for Officer Type, Zone, Region, Branch, Agency Name are present");
	 	}		 	
	 	catch(AssertionError|Exception e) {
	 		
	 		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	 	}
	 }
	    
 		@AfterSuite
 		public void AfterClass() {
 		     ExtentManager.getInstance().flush();  
	    
	    }
}
