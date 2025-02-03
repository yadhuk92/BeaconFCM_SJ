package Core.Configuration;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.LoginBannerConfiRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.Configurations.LoginbannerPage;
 

public class LoginbannerPageTest {
	    
	    private WebDriver driver;
		com.Utility.ExcelReader ExcelReader;
		Base_Class baseclass;
		Log log;
		TestListener TestListener;
		com.Utility.ScreenShot screenShot;
		LoginbannerPageTest configurationPage;
		 LoginbannerPage loginbanner;
		LoginBannerConfiRepo PageRepository;
		LoginPageRepo LoginPageRepositry;
		ExtentTest extenttest;
		Login_Class CoreAppLogin;
		
		
		@BeforeSuite
		public void reference() throws Exception {
			baseclass = new Base_Class();
			CoreAppLogin = new Login_Class();
			ExcelReader = new com.Utility.ExcelReader("Login_banner_confi");
			log = new Log();
			TestListener = new TestListener();
			CoreAppLogin.CoreLogin();
			driver = baseclass.getDriver();
			if (driver == null) {
			    throw new RuntimeException("WebDriver is not initialized!");
			}
			screenShot = new com.Utility.ScreenShot(driver);
			loginbanner= new LoginbannerPage(driver);
			PageRepository= new LoginBannerConfiRepo();
		}

		@BeforeMethod
	    public void ExtentTestManagerStartTest(Method method) throws InterruptedException {
			baseclass = new Base_Class();
			driver = baseclass.getDriver();
			loginbanner= new LoginbannerPage(driver);
	        // Start a new ExtentTest for the current test method
	        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Login Banner Confi");
	        Log.info("****" + method.getName() + "****");
	        Thread.sleep(500);
	    }

	 //Navigation to Login Banner Configuration Page
	    @Test(priority=1)
	    public void NavigationToLoginBannerConfigurationPage() throws Throwable {
//	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {
	//CoreAppLogin.CoreLogin();
				ExtentTestManager.getTest().log(Status.INFO, "Login successful");
				Log.info("Login successfull !");
				
				Log.info("***Navigation to Configuration menu***");
				boolean Flag1=loginbanner.clickOnConfigurationMenu();
				ExtentTestManager.getTest().log(Status.PASS,"1.Click on the 'Configuration' from the main menu.");
				Log.info("1.Click on the 'Configuration' from the main menu."+Flag1);
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
				
				boolean Flag2=loginbanner.clickOnLoginBannerConfigMenu();
				ExtentTestManager.getTest().log(Status.PASS, "2. Click on 'Login Banner Config' from the sub-menu");
				Log.info("2. Click on 'Login Banner Config' from the sub-menu"+Flag2);
				
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(90));
				String currentUrl= driver.getCurrentUrl();
				
				System.out.println(currentUrl);
				
				Assert.assertTrue("Current URL is not matching with expected",currentUrl.contains("Configurations/loginBannerConfiguration"));
				ExtentTestManager.getTest().log(Status.PASS, "Expected URL-(\"..Configurations/loginBannerConfiguration\") is matching with Actual URL-(\"..Configurations/loginBannerConfiguration\")");
	    	}		 	
	    	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
	        
	    }
	 //Verify Fields and Buttons on Login Banner Confi Page
	    @Test(priority=2)
	    public void FieldsandButtonsAreCorrect() throws Throwable {
	    	
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));	 
	     try {
	 // Verify fields are empty
	    	 boolean Flag1=loginbanner.areFieldsEmpty();
	    	 Assert.assertTrue("Fields are not empty",loginbanner.areFieldsEmpty());
	         ExtentTestManager.getTest().log(Status.PASS, "1.Check Usertype,Bannertype,Section dropdown fields are Empty");
             Log.info("Check Usertype,Bannertype,Section dropdown fields are Empty"+Flag1);
             
	 // Verify buttons are displayed
             boolean Flag2=loginbanner.areButtonsDisplayed();
	         Assert.assertTrue("Buttons are not displayed",loginbanner.areButtonsDisplayed());
	         ExtentTestManager.getTest().log(Status.PASS, "2.search and reset buttons are displayed");
	         Log.info("search and reset buttons are displayed"+Flag2);
	     }
	    	
		    catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
		    }
	  //Verify Mandatory Fields Warning
        @Test(priority=3)
        public void MandatoryFieldsWarning() throws InterruptedException {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner)); 
           try 
           {
        	
        	WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(PageRepository.submitbutton));
        	submit.click();
        	ExtentTestManager.getTest().log(Status.PASS, "1.Leave Usertype,Bannertype,Section,Heading,Detail fields empty");   
        	ExtentTestManager.getTest().log(Status.PASS, "2.Click on Submit button");
        	
        	
        	loginbanner.getWarningMessageText();
       // Validate if the warning message is as expected
            Assert.assertTrue("Warning message not displayed",loginbanner.getWarningMessageText()); 
            ExtentTestManager.getTest().log(Status.PASS, "Warning message\"Please fill mandatory details\" is displayed");
            
        }
		    catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;}
	    	}
        
       //Click on Usertypedropdown and validate the dropdown values
        
        @Test(priority = 4,dataProvider="TestData")
        public void SelectInternalUserType(Map<Object, Object> testdata) throws Throwable {
        	 
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
        	try
       	{
        		
       	
       		ExtentTestManager.getTest().log(Status.PASS,"1.Click on Usertype dropdown");
        		loginbanner.clickuserdropdown();
        		ExtentTestManager.getTest().log(Status.PASS,"2.Internal user , call centre user , collection agency user displayed in dropdown ");
        		
               if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
            	   
        		String usertype=testdata.get("UserType").toString();
        		System.out.println(usertype);
        		loginbanner.selectinternaluser(usertype);
        		ExtentTestManager.getTest().log(Status.PASS, "3.\"Internal user\" is selected in the user type field.");
                
            }
          	}
           catch(AssertionError|Exception e) {
               		
       	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
       		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
       		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
       		        throw e;
       	    	}
               }
 

	// Select Banner Type
        @Test (priority=5,enabled=false)
        public void SelectInformationtype() throws InterruptedException {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        	try
        	{
           ExtentTestManager.getTest().log(Status.PASS, "1.Click on \"Bannertype dropdown\"");
        	   loginbanner.clickBannertypedropdown();
        
        //select Information from bannertype dropdown	
        	   ExtentTestManager.getTest().log(Status.PASS, "Expected result-2. Select \"Information\" from the ' Banner Type' dropdown.");
        	   loginbanner.clickInfo();
        	   ExtentTestManager.getTest().log(Status.PASS, "Actual result-\"Information\" is selected in the banner type field.");
        	}
           catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
     
       //select Header1 from Section dropdown
        @Test(priority=6,enabled=false)
        public void SelectHeader1() throws InterruptedException {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "1.Click on Section Dropdown");
        	loginbanner.clickSectiondropdown();
        	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
        	//select Header1 
        	ExtentTestManager.getTest().log(Status.PASS, "Expected Result-2.Select Header1 from Section dropdown");
        	loginbanner.selectheader1();
        	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-Header1 is selected from section dropdown");
            }
        catch(AssertionError|Exception e) {
    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }      	
        //Enter Heading
        @Test(priority=7,dataProvider="TestData",enabled=false)
        public void InputHeadingField(Map<Object, Object> testdata) {
        	try {
        		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
        			// Step 2: Retrieve the "AlphaNumericInput" data from the test data
        		        String ExpectedAlphaNumericInput = testdata.get("AlphaNumericInput").toString();
        		        System.out.println(ExpectedAlphaNumericInput);
        		       String ActualInput =  loginbanner.Headingfieldinput(ExpectedAlphaNumericInput);
        		       
        	  ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Heading' field with max length of 35");
        	        	       
        	  Assert.assertEquals("Heading text does not match expected text",ExpectedAlphaNumericInput,ActualInput);   
        	  ExtentTestManager.getTest().log(Status.PASS, "Actaul result-1.Alphanumeric line of data is entered in the heading field ");
        	
        	  // Assert that the entered text length does not exceed 35 characters
        	
              Assert.assertTrue(ActualInput.length()<=35); 
              ExtentTestManager.getTest().log(Status.PASS, "Actaul result-2.Entered Headingfield input is less than 35 character");
          
        }
        	}
        
            catch(AssertionError|Exception e) {
        		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
        
        //Enter Details
        @Test(priority=8,dataProvider="TestData",enabled=false)
        public void InputDetailField( Map<Object,Object> testdata) {
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
        			// Step 2: Retrieve the "AlphaNumericInput" data from the test data
    		        String ExpectedAlphaNumericInput = testdata.get("AlphaNumericInput").toString();
        			String ActualInput=loginbanner.Detailfieldinput(ExpectedAlphaNumericInput);
        		
        			ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Detail' field with max length of 35");
        			Assert.assertEquals("Detailtextinput does not match expected text",ExpectedAlphaNumericInput,ActualInput);
        			ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.Alphanumeric line of data is entered in the heading field ");
        			// Assert that the entered text length does not exceed 35 characters
                	
                    Assert.assertTrue(ActualInput.length()<=35); 
                    ExtentTestManager.getTest().log(Status.PASS, "Actual result-2.Entered Headingfield input is less than 35 character");
                
        	}
        }
     catch(AssertionError|Exception e) {
        		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
        
       //Submit the details by clicking on Submit button 
        @Test(priority=9,enabled=false)
        public void clicksubmitbutton() throws InterruptedException {
        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        	loginbanner.clicksubmit();
             	
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
 		public void AfterClass() {
 		     ExtentManager.getInstance().flush();
 		  // Close the browser
// 		        if (driver != null) {
// 		            driver.quit();
 		        }
 //}
}    
