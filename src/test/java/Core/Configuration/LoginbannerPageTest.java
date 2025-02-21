package Core.Configuration;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.Page_Repository.LoginBannerConfiRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.Configurations.LoginbannerPage;
 

public class LoginbannerPageTest extends Base_Class{
	    
	    WebDriver driver;
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
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {

				ExtentTestManager.getTest().log(Status.INFO, "Login successful");
				Log.info("Login successfull !");
				
				Log.info("***Navigation to Configuration menu***");
				boolean Flag1=loginbanner.clickOnConfigurationMenu();
				ExtentTestManager.getTest().log(Status.PASS,"Click on the 'Configuration' from the main menu.");
				Log.info("1.Click on the 'Configuration' from the main menu."+Flag1);
				
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
				
				boolean Flag2=loginbanner.clickOnLoginBannerConfigMenu();
				ExtentTestManager.getTest().log(Status.PASS, "Click on 'Login Banner Config' from the sub-menu");
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
	    	 loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);	 
	     try {
	 // Verify fields are empty
	    	 boolean Flag1=loginbanner.areFieldsEmpty();
	    	 Assert.assertTrue("Fields are not empty",loginbanner.areFieldsEmpty());
	         ExtentTestManager.getTest().log(Status.PASS, "Check Usertype,Bannertype,Section dropdown fields are Empty");
             Log.info("Check Usertype,Bannertype,Section dropdown fields are Empty"+Flag1);
             
	 // Verify buttons are displayed
             boolean Flag2=loginbanner.areButtonsDisplayed();
	         Assert.assertTrue("Buttons are not displayed",loginbanner.areButtonsDisplayed());
	         ExtentTestManager.getTest().log(Status.PASS, "search and reset buttons are displayed");
	         Log.info("search and reset buttons are displayed"+Flag2);
	     }
	    	
		    catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
	     Thread.sleep(3000);
		    }
	  //Verify Mandatory Fields Warning
      @Test(priority=3)
        public void MandatoryFieldsWarning() throws InterruptedException {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
 //       	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner)); 
           try 
           {
        	
        	WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(PageRepository.submitbutton));
        	submit.click();
        	ExtentTestManager.getTest().log(Status.PASS, "Leave Usertype,Bannertype,Section,Heading,Detail fields empty");   
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Submit button");
        	
        	
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
           Thread.sleep(3000);
	    	}
        
       //Click on Usertypedropdown and validate the dropdown values
        
        @Test(priority=4)
        public void SelectInternalUserType() throws Throwable {
        	 
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	    	 wait.until(ExpectedConditions.invisibilityOf((WebElement) PageRepository.warningmsg));
        	try
       	{
    		   ExtentTestManager.getTest().log(Status.PASS,"Click on Usertype dropdown");
    		   Thread.sleep(3000);
        		loginbanner.clickuserdropdown();
        		ExtentTestManager.getTest().log(Status.PASS,"Internal user , call centre user , collection agency user displayed in dropdown ");
        		
        		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));

        		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
        		Thread.sleep(3000);
        		loginbanner.selectinternaluser();
        		ExtentTestManager.getTest().log(Status.PASS, "\"Internal user\" is selected in the user type field.");
                
           
          	}
           catch(AssertionError|Exception e) {
               		
       	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
       		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
       		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
       		        throw e;
       	    	}
               }
	// Select Banner Type(Information)
       @Test (priority=5)
        public void SelectInformationtype() throws InterruptedException {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
        	try
        	{
           ExtentTestManager.getTest().log(Status.PASS, "Click on \"Bannertype dropdown\"");
        	   loginbanner.clickBannertypedropdown();
        	   
        	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
        
        //select Information from bannertype dropdown	
        	   ExtentTestManager.getTest().log(Status.PASS, "Expected result- Select \"Information\" from the ' Banner Type' dropdown.");
        	   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(200));
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
        @Test(priority=6)
        public void SelectHeader1() throws InterruptedException {
        	
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(200));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
//        	Common.fluentWait("wait till spinner to dissappear", PageRepository.spinner);
      	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Section Dropdown");
        	loginbanner.clickSectiondropdown();
        	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
        	
        	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
        	//select Header1 
        	ExtentTestManager.getTest().log(Status.PASS, "Expected Result-Select Header1 from Section dropdown");
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
       @Test(priority=7,dataProvider="TestData")
        public void InputHeadingField(Map<Object, Object> testdata) {
        	try {
        		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
        			// Step 2: Retrieve the "AlphaNumericInput" data from the test data
        		        String ExpectedAlphaNumericInput = testdata.get("HeadingField").toString();
        		        System.out.println(ExpectedAlphaNumericInput);
        		       String ActualInput =  loginbanner.Headingfieldinput(ExpectedAlphaNumericInput);
        		       
        	  ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Heading' field with max length of 35");
        	        	       
        	  Assert.assertEquals("Heading text does not match expected text",ExpectedAlphaNumericInput,ActualInput);   
        	  ExtentTestManager.getTest().log(Status.PASS, "Actual result-Alphanumeric line of data is entered in the heading field ");
        	
        	  // Assert that the entered text length does not exceed 35 characters
        	
              Assert.assertTrue(ActualInput.length()<=35); 
              ExtentTestManager.getTest().log(Status.PASS, "Actual result-Entered input in Headingfield  is less than 35 character");
          
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
        @Test(priority=8,dataProvider= "TestData")
        public void InputDetailField( Map<Object,Object> testdata) {
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
        			// Step 2: Retrieve the "AlphaNumericInput" data from the test data
    		        String ExpectedAlphaNumericInput = testdata.get("DetailField").toString();
        			String ActualInput=loginbanner.Detailfieldinput(ExpectedAlphaNumericInput);
        		
        			ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Detail' field with max length of 35");
        			Assert.assertEquals("Detailtextinput does not match expected text",ExpectedAlphaNumericInput,ActualInput);
        			ExtentTestManager.getTest().log(Status.PASS, "Actual result-Alphanumeric line of data is entered in the detail field ");
        			// Assert that the entered text length does not exceed 35 characters
                	
                    Assert.assertTrue(ActualInput.length()<=35); 
                    ExtentTestManager.getTest().log(Status.PASS, "Actual result-Entered input for detailfield is less than 35 character");
                
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
        //(Usertype=Internal user,bannertype=Information,Section=Header1)
       @Test(priority=9)
        public void Clicksubmitbutton() throws InterruptedException {
        	try
        	{
        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Submit button");
        	loginbanner.clicksubmit();
        	} 	
    	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
	      Thread.sleep(3000);  
	    }
 //Verify Data Post Logout(Usertype=Internal user,bannertype=Information,Section=Header1)
        
      @Test(priority=10)
        public void CheckdataPostLogout() throws Throwable {

        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        	 loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);	
        	
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "Navigate to profile icon");
        	loginbanner.Clickprofiledropdown();
        	
        	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(300));
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Logout");
        	loginbanner.Clicklogout();
        	
        	Thread.sleep(3000);
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Expected Result-The data entered in the 'Heading' and 'Details'should display in the internal user's login page.");
        	WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(320));
        	loginbanner.profilepagetext();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the internal user's login page.");
        	}
   	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
   	driver.quit();
   	Thread.sleep(5000);
		        }
        
        
  
  //Click on usertype=> select internal user
       @Test(priority=11)
       public void selectinternaluser() throws Throwable {
    	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
    	   
    	   try
    	   {
   	   
   		   CoreAppLogin.CoreLogin();
    		   
    		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
    		   loginbanner.clickOnConfigurationMenu();
    		   
    		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(120));
    		   loginbanner.clickOnLoginBannerConfigMenu();
  //  		   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(PageRepository.spinner)));
    		   
    		   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
   
    		   Thread.sleep(3000); 
    		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
    		   loginbanner.clickusertypedropdown();
    		   
    		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
    		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
    
    		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(30));
    		   ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
    		   Thread.sleep(3000);
    		   loginbanner.selectinternaluser();
    		   
    		   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Internal user is selected in Usertypedropdown");
    	   }
  	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
    	   Thread.sleep(3000);
        }
       
        
        
  //Select hyperlink from banner type dropdown
       @Test(priority=12)
        public void Selecthyperlink() throws Throwable {
        	WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
        	loginbanner.clickBannertypedropdown();
        	
 		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
        	
        	WebDriverWait wait1 =new WebDriverWait(driver,Duration.ofSeconds(90));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Actual result-\"hyperlink\" selected from the 'Banner Type' dropdown.");
        	}
  	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        	Thread.sleep(3000);
        }
        
// select link1 from section dropdown
        @Test(priority=13)
        public void Selectlink1() throws Throwable {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
 		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
        	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Actual result-\"link1\"selected from section dropdown");
        	}
 	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        	Thread.sleep(3000);
        }
  
        
//Input Headerlink
       @Test(priority=14,dataProvider="TestData")
        public void InputHeaderLink( Map<Object,Object> testdata) {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
        		{
        			ExtentTestManager.getTest().log(Status.PASS, "Expected-Input link from test data file");
        			
        			String Headerlink = testdata.get("Headerfield1link").toString();
        			loginbanner.headerlink(Headerlink);
        		}
        		ExtentTestManager.getTest().log(Status.PASS, "Actual result-Link is entered from test data file");
        		return;
        	}
        	   catch(AssertionError|Exception e) {
           		
   	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
   		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
   		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
   		        throw e;
   	    	}
     }  	
        
      //Input Detaillink
       @Test(priority=15,dataProvider="TestData")
        public void InputDetailLink( Map<Object,Object> testdata)throws Throwable {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
        		{
        			ExtentTestManager.getTest().log(Status.PASS, "Expected-Input link from test data file");
        			
        			String Detaillink = testdata.get("Detailfield1link").toString();
        			loginbanner.detaillink(Detaillink);
        		}
        		ExtentTestManager.getTest().log(Status.PASS, "Actual result-Link is entered from test data file");
        		return;
        	}
        	   catch(AssertionError|Exception e) {
           		
   	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
   		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
   		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
   		        throw e;
   	    	}
//        Thread.sleep(3000);	
     }       
   
 //Verify Data Post Logout(Usertype=Internal user,bannertype=Hyperlink,Section=link1)  
        
       @Test(priority=16)
        
        public void ClickonLink1PostLogout() throws Throwable {
        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(90));
 //       	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	  // Declare and initialize the initial window handle
            String initialWindowHandle = driver.getWindowHandle();
            
        	try
        	{
        		Thread.sleep(1000);
        		loginbanner.clicksubmit();
        		loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
        		
        		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(90));
        		ExtentTestManager.getTest().log(Status.PASS, "Navigate to profile icon");
            	loginbanner.Clickprofiledropdown();
            	
            	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(120));
            	ExtentTestManager.getTest().log(Status.PASS, "Click on Logout");
            	loginbanner.Clicklogout();
            	
            	Thread.sleep(3000);
            	
            	WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(200));
        		ExtentTestManager.getTest().log(Status.PASS, "Expected-1.Data entered for hyperlink should be displayed on login page");
        		loginbanner.dataforlink1();
        		
        		WebDriverWait wait4=new WebDriverWait(driver, Duration.ofSeconds(200));
        		ExtentTestManager.getTest().log(Status.PASS, "User should be able to click on link1");
       // 		Thread.sleep(3000);
        		loginbanner.clickonProfilePagelink1();
    	
        		ExtentTestManager.getTest().log(Status.PASS, "Actual-Data entered for hyperlink is displayed on Internal User's login page and user is able to click on link1 and the page is opened");
        	
        	}	
        	
        		catch(AssertionError|Exception e) {
    	    		
    	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    		        throw e;
    	 	}
        	finally {
                // Close the second browser window if it exists
                Set<String> allWindowHandles = driver.getWindowHandles();
                for (String windowHandle : allWindowHandles) {
                    if (!windowHandle.equals(initialWindowHandle)) {
                        driver.switchTo().window(windowHandle);
                        driver.close(); // Close second browser window
                    }
                }

                // Switch back to the initial window and close it as well
                driver.switchTo().window(initialWindowHandle);
                driver.close(); // Close the initial browser window
            }
        	
        	Thread.sleep(5000);
        }
 //Test case-1. Select "internal user" from 'User Type'.2. Select "hyperlink" from 'Banner Type'. 3. Enter "link1" in 'Section'.4. Click on 'Search'.
  //Search entered details for link1      
        @Test(priority=17)
        public void VerifyInputdataUsingSearch2() throws Throwable {
        	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
    	try
        	{
        		
     		 CoreAppLogin.CoreLogin();
    		   
     		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(100));
     		   loginbanner.clickOnConfigurationMenu();
     		   
     		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(100));
     		   loginbanner.clickOnLoginBannerConfigMenu();
     		   
     		  loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
   
    		   Thread.sleep(3000);
  
        		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
        		   loginbanner.clickusertypedropdown();
        		   
        		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
        		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
        		   
        		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
        		   ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
        		Thread.sleep(3000);   
        		   loginbanner.selectinternaluser();
        		   
        		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(90));
        		   ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
                   loginbanner.clickBannertypedropdown();
                   
                   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
                   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
                	
                	WebDriverWait wait7 =new WebDriverWait(driver,Duration.ofSeconds(60));
                	loginbanner.hyperlink();
                	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
                	
                	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(100));
                	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(PageRepository.spinner)));
                	
                	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
                	loginbanner.clickSectiondropdown();
                	
         		   WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
        		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
                	
                	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
                	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
                	loginbanner.selectlink1FromSectiondropdown();
                	
                	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(90));
                	ExtentTestManager.getTest().log(Status.PASS, "Click on Search");
                	loginbanner.ClickSearchbutton();
                	
                	ExtentTestManager.getTest().log(Status.PASS, "The saved data should populate in the 'Heading' and 'Details' fields.");
                	
                	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The saved data populated in the 'Heading' and 'Details' fields.");
        	}
 	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
    	Thread.sleep(3000);
        }
        
        
 //Test case-1. Select "internal user" from 'User Type'.2. Select "information" from 'Banner Type'. 3. Enter "Header1" in 'Section'.4. Click on 'Search'.
 //Search entered details for Header1      
        
    @Test(priority=18)
    public void VerifydataUsingSearch1() throws Throwable {
    	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(100));
    	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
    	try
    	{
    		wait.until(ExpectedConditions.presenceOfElementLocated(PageRepository.headingtext));
    		wait.until(ExpectedConditions.presenceOfElementLocated(PageRepository.detailtext));
    		
    		loginbanner.clickResetbutton();
        	WebDriverWait wait1 =new WebDriverWait(driver, Duration.ofSeconds(90));
        	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
    		
    	 WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
   		 ExtentTestManager.getTest().log(Status.PASS, ".Click on Usertype dropdown");
   		 Thread.sleep(3000);
   		 loginbanner.clickusertypedropdown();
   		   
   		 WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
   		 ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
   		 Thread.sleep(3000);
   		 loginbanner.selectinternaluser();
   		   
   		 WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(90));
   		 ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
         loginbanner.clickBannertypedropdown();
         
         WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));  
         loginbanner.clickInfo();
    	 ExtentTestManager.getTest().log(Status.PASS, "Select\" Information\"in the banner type field.");
    	 
    	 WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(100));
    	 loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
    	 
    	 ExtentTestManager.getTest().log(Status.PASS, "Click on Section Dropdown");
       	 loginbanner.clickSectiondropdown();
       	 
       	 
       	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(90));
        ExtentTestManager.getTest().log(Status.PASS, "Select Header1 from Section dropdown");
       	loginbanner.selectheader1();
       	
      	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Search");
    	loginbanner.ClickSearchbutton();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "The saved data should populate in the 'Heading' and 'Details' fields.");
    	
    	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The saved data populated in the 'Heading' and 'Details' fields.");
    	 }
    	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
    	Thread.sleep(3000);
    }
    
//Modify data in 'Heading' and 'Details' fields and submit for Internal user
       @Test(priority=19,dataProvider="TestData")
   public void modifydata_link1( Map<Object,Object> testdata) throws Throwable {
   	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(100));
   	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	   try
	   {
		wait.until(ExpectedConditions.presenceOfElementLocated(PageRepository.headingtext));
		wait.until(ExpectedConditions.presenceOfElementLocated(PageRepository.detailtext));
		
   		loginbanner.clickResetbutton();
    	WebDriverWait wait1 =new WebDriverWait(driver, Duration.ofSeconds(100));
    	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
    	
//   		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
//   		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(PageRepository.spinner)));
   		Thread.sleep(3000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
		   Thread.sleep(3000);
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait5 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
        	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(100));
        	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
        	
        	WebDriverWait wait7 =new WebDriverWait(driver,Duration.ofSeconds(60));
       	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(100));
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Search");  
        	loginbanner.ClickSearchbutton();
        	
        	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(100));
        	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
        	
//        	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
//        	loginbanner.clearHeader();
        	
        	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.clearDetail();
        	
        	
        	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
          		{
        			ExtentTestManager.getTest().log(Status.PASS, "Input modified data");
        			
        			String modifiedlink1 = testdata.get("Modifiedlink1").toString();
        			System.out.println(modifiedlink1);
        			loginbanner.modifiedDataForlink1(modifiedlink1);
        		}
        		
        		
        WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(60));
        ExtentTestManager.getTest().log(Status.PASS, "Click on Submit");
   	    loginbanner.clicksubmit();
   	    
   	   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is updated successfully.");
	   }

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
    }
	   Thread.sleep(3000);
} 
    
 //Verify Updated Data Post Logout.Click on modified link1  
   
   @Test(priority=20,dataProvider="TestData")
   public void VerifyUpdatedDataPostLogout(Map<Object,Object> testdata) throws Throwable {
	   WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	   
	   // Declare and initialize the initial window handle
	    String initialWindowHandle = driver.getWindowHandle();

	   try
	   {
	
		   WebDriverWait wait11= new WebDriverWait(driver, Duration.ofSeconds(100));
	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
		ExtentTestManager.getTest().log(Status.PASS, "Navigate to profile icon");
    	loginbanner.Clickprofiledropdown();
    	
    	WebDriverWait wait12=new WebDriverWait(driver, Duration.ofSeconds(100));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Logout");
    	loginbanner.Clicklogout();
    	
    	Thread.sleep(3000);
    	
    	WebDriverWait wait13=new WebDriverWait(driver, Duration.ofSeconds(100));
		ExtentTestManager.getTest().log(Status.PASS, "Data entered for hyperlink should be displayed on login page");
		loginbanner.dataforlink1();
		
		WebDriverWait wait14=new WebDriverWait(driver, Duration.ofSeconds(100));
		ExtentTestManager.getTest().log(Status.PASS, "User should be able to click on link1");
	
		loginbanner.clickonProfilePagelink1();
		
		ExtentTestManager.getTest().log(Status.PASS, "Actual-Data entered for hyperlink is displayed on Internal User's login page and user is able to click on link1 and the page is opened");

	   }
	   

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
    }
	  
   	finally {
        // Close the second browser window if it exists
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close(); // Close second browser window
            }
        }

        // Switch back to the initial window and close it as well
        driver.switchTo().window(initialWindowHandle);
        driver.close(); // Close the initial browser window
    }
	   Thread.sleep(5000);
}
     
//Submit Without Heading
   @Test(priority=21,dataProvider="TestData")
   public void SubmitWithoutHeading(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(60));
	 
	   try
	   {
  		 CoreAppLogin.CoreLogin();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		   loginbanner.clickOnConfigurationMenu();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
		   loginbanner.clickOnLoginBannerConfigMenu();
		   
		   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);

		   Thread.sleep(3000);
//		   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
		   Thread.sleep(3000);
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
           
		   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
        	
        	WebDriverWait wait7 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
           	WebDriverWait wait8 =new WebDriverWait(driver,Duration.ofSeconds(60));
           	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
           	
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
 		   WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(90));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
        	
        	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Leave \"Heading\"field empty");
        	loginbanner.clearHeader();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Enter the details in \"Detail\" field");
    		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
    		{
    			
    			String Detaillink = testdata.get("Detailfield1link").toString();
    			loginbanner.detaillink(Detaillink);
    		}
 	    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(400));
            ExtentTestManager.getTest().log(Status.PASS, "Click on Submit");
       	    loginbanner.clicksubmit();
       	    
       	   WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(100));
       	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
 
       	   ExtentTestManager.getTest().log(Status.PASS, "Warning msg should be display");
       	   
        	loginbanner.getWarningMessageText();
           Assert.assertTrue("Warning message not displayed",loginbanner.getWarningMessageText()); 
           ExtentTestManager.getTest().log(Status.PASS, "Actual result-Warning message\"Please fill mandatory details\" is displayed");
       	   
	   }
	   catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		    }
	   Thread.sleep(3000);
	} 
   
 //Submit Without Detail field
   @Test(priority=22,dataProvider="TestData")
   public void SubmitWithoutDetailField(Map<Object,Object>testdata) throws Throwable {
   try
	   {
		   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(PageRepository.warningmsg)));
		   loginbanner.clickResetbutton();
		   
			loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
			
		   Thread.sleep(3000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "Select Internal User");
		   Thread.sleep(3000);
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
           
		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
        	
        	WebDriverWait wait5 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
           	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
           	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
           	
        	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
 		   WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(90));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
        	
        	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
    	
        	ExtentTestManager.getTest().log(Status.PASS, "Enter the details in \"Heading\" field");
    		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
    		{
    			
    			String Headerlink = testdata.get("Headerfield1link").toString();
    			loginbanner.headerlink(Headerlink);
    		}
    		
        	ExtentTestManager.getTest().log(Status.PASS, "Leave \"Detail\"field empty");
        	loginbanner.clearDetail();
    	
       	    WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(100));
            ExtentTestManager.getTest().log(Status.PASS, "Click on Submit");
       	    loginbanner.clicksubmit();
       	    
       	   WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
       	loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
 
       	   ExtentTestManager.getTest().log(Status.PASS, "Warning msg should be display");
       	   
        	loginbanner.getWarningMessageText();
           Assert.assertTrue("Warning message not displayed",loginbanner.getWarningMessageText()); 
           ExtentTestManager.getTest().log(Status.PASS, "Actual result-Warning message\"Please fill mandatory details\" is displayed");
       	   
	   }
	   catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		    }
   Thread.sleep(3000);
	}   
   
//Select "callcentre user" from the 'User Type' dropdown.  
   @Test(priority=23)
   public void SelectCallCenter() throws Throwable {
 
	   try
	   {
		   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
		   loginbanner.clickResetbutton();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		  loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
		  
		   Thread.sleep(3000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(30));
		   ExtentTestManager.getTest().log(Status.PASS, "Select \"Call Centre\" from Usertype dropdown");
		   Thread.sleep(3000);
		   loginbanner.selectCallCentre();
       	   
	   }
	   catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		    }
	   Thread.sleep(3000);
	} 
   
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
   
  @Test(priority=24,dataProvider="TestData")
  public void SubmitCallCentreData1(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(30));
	   
	   try
	   {
	   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
           ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown");
    	   loginbanner.clickBannertypedropdown();
    	   
    	   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
 		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
    
 	
    	   ExtentTestManager.getTest().log(Status.PASS, "Select \"Information\" from the Banner Type dropdown.");
    	   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
    	   loginbanner.clickInfo();
    	   
    	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
    	   
       	ExtentTestManager.getTest().log(Status.PASS, "Click on Section Dropdown");
       	loginbanner.clickSectiondropdown();
       	WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
       	
 	   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(90));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
        
       	ExtentTestManager.getTest().log(Status.PASS, "Select \"Header1\" from Section dropdown");
       	loginbanner.selectheader1();
       	
        ExtentTestManager.getTest().log(Status.PASS, "Input an alphanumeric  line in the 'Heading' field with max length of 35");
     	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(100));
     	
     	loginbanner.clearHeader();
     	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String AlphaNumericInput = testdata.get("CallCenterHeadingtext").toString();
		        loginbanner.Headingfieldinput(AlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(100));
	 	
	 	loginbanner.clearDetail();
	 	
 		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
 		{
		
	        String AlphaNumericInput = testdata.get("CallCenterDetailtext").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
		
		
       }
 		
    	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Submit button");
    	loginbanner.clicksubmit();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "Data should be submitted successfully.");
    	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
    	} 	
	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
	   Thread.sleep(3000);
        
    }
 
  
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
//7.Logout and Verify the data on login page of Call Centre user
  
  @Test(priority=25,dataProvider="TestData")
  public void VerifydataPostLogoutCC1(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	   
	   // Declare and initialize the initial window handle
	    String initialWindowHandle = driver.getWindowHandle();
	
	   try
	   {
  	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(100));
    	ExtentTestManager.getTest().log(Status.PASS, ".Navigate to profile icon");
    	loginbanner.Clickprofiledropdown();
    	
    	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(100));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Logout");
    	loginbanner.Clicklogout();
    	
    	Thread.sleep(3000);
    	
    	ExtentTestManager.getTest().log(Status.PASS, "The data entered in the 'Heading' and 'Details'should display in the Call Centre user's login page.");
    	WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(100));
    	loginbanner.callCentreURL();
    	
    	WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(100));
    	loginbanner.profilepagetext();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the Call Centre user's login page.");
    	
    
    	} 	
	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
   
   	finally {
        // Close the second browser window if it exists
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close(); // Close second browser window
            }
        }

        // Switch back to the initial window and close it as well
        driver.switchTo().window(initialWindowHandle);
        driver.close(); // Close the initial browser window
    }
	   Thread.sleep(3000);
}

  
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select "Hyperlink" from banner type dropdown 3.Select "link1" from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit  
 @Test(priority=26,dataProvider="TestData")
  
public void SubmitCallCentreData2(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   
	   try
	   {
 		 CoreAppLogin.CoreLogin();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(100));
		   loginbanner.clickOnConfigurationMenu();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
		   loginbanner.clickOnLoginBannerConfigMenu();
		   
		   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);

		   Thread.sleep(3000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "Select \"Call Centre\" from Usertype dropdown");
		   Thread.sleep(3000);
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
         ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown");
  	    loginbanner.clickBannertypedropdown();
  	    
		   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
  
	
    	WebDriverWait wait7 =new WebDriverWait(driver,Duration.ofSeconds(60));
    	loginbanner.hyperlink();
    	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
  	   
    	 loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
  	   
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
   	   loginbanner.clickSectiondropdown();
   	   
	   WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
   	
     	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
    	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
    	loginbanner.selectlink1FromSectiondropdown();
     	
		ExtentTestManager.getTest().log(Status.PASS, "Input data for HeaderField");
    	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(100));
   	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
 			String Headerlink = testdata.get("CallCenterHeaderLink1").toString();
 			loginbanner.headerlink(Headerlink);
	    }
		 
		ExtentTestManager.getTest().log(Status.PASS, "Input data for DetailField");
	 	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(100));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
        	String Detaillink = testdata.get("CallCenterDetailLink1").toString();
			loginbanner.detaillink(Detaillink);
	
     }
		
  	WebDriverWait wait12=new WebDriverWait(driver, Duration.ofSeconds(90));
  	ExtentTestManager.getTest().log(Status.PASS, "Click on Submit button");
  	loginbanner.clicksubmit();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "Data should be submitted successfully.");
  	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
  	} 	
	catch(AssertionError|Exception e) {
  		
  		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
  	}
   Thread.sleep(3000);   
  }  

//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select "Hyperlink" from banner type dropdown 3.Select "link1" from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit  
//7.Logout and Verify the data on login page of Call Centre user. 8.Click on link1 to load the page

@Test(priority=27,dataProvider="TestData")

public void VerifydataPostLogoutCC2(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	   
	   // Declare and initialize the initial window handle
	    String initialWindowHandle = driver.getWindowHandle();
	
	   try
	   {

	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(100));
	ExtentTestManager.getTest().log(Status.PASS, "Click on Profile icon");
	loginbanner.Clickprofiledropdown();
	
	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(100));
	ExtentTestManager.getTest().log(Status.PASS, "Click on LogOut");
	loginbanner.Clicklogout();
	
	Thread.sleep(3000);
	
	WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(90));
	ExtentTestManager.getTest().log(Status.PASS, "The data entered in the 'Heading' and 'Details' should display in the call centre user's login page.");
	loginbanner.dataforlink1();
	
	WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(90));
	ExtentTestManager.getTest().log(Status.PASS, "Navigate to Call Centre app URL");
	loginbanner.callCentreURL();
	
	WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(100));
	wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
	
	WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(100));
	ExtentTestManager.getTest().log(Status.PASS, "Click on provided Link1");
//	Thread.sleep(3000);
	loginbanner.clickonProfilePagelink1();

	
	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The Call Center user should be redirected to given page");
	} 	
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
   	finally {
        // Close the second browser window if it exists
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close(); // Close second browser window
            }
        }

        // Switch back to the initial window and close it as well
        driver.switchTo().window(initialWindowHandle);
        driver.close(); // Close the initial browser window
    }
	   Thread.sleep(3000);
}


//Test case-Edit and Update Data(Link1) for Call Centre user and submit  

@Test(priority=28,dataProvider="TestData")

public void ModifydataforCallCentre( Map<Object,Object> testdata) throws Throwable {
  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));

  try
  {
  CoreAppLogin.CoreLogin();
	   
	   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(100));
	   loginbanner.clickOnConfigurationMenu();
	   
	   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
	   loginbanner.clickOnLoginBannerConfigMenu();
	   
	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	   


	  Thread.sleep(3000);
	   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
	   loginbanner.clickusertypedropdown();
	   
	   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
	   
	   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(30));
	   ExtentTestManager.getTest().log(Status.PASS, "Select\" Call Centre\" User");
	   Thread.sleep(3000);
	   loginbanner.selectCallCentre();
	   
	   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
	   ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown.");
      loginbanner.clickBannertypedropdown();
      
	   WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
   	
   	WebDriverWait wait8 =new WebDriverWait(driver,Duration.ofSeconds(60));
   	loginbanner.hyperlink();
   	ExtentTestManager.getTest().log(Status.PASS, "Select \"hyperlink\" from the 'Banner Type' dropdown.");
   	
   	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(100));
    loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
   	
   	WebDriverWait wait10 =new WebDriverWait(driver,Duration.ofSeconds(60));
   	ExtentTestManager.getTest().log(Status.PASS, "Click on Section dropdown");
   	loginbanner.clickSectiondropdown();
   	
	   WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
   	
   	WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(60));
   	ExtentTestManager.getTest().log(Status.PASS, "Select link1 from section dropdown");
   	loginbanner.selectlink1FromSectiondropdown();
   	
   	WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(100));
   	ExtentTestManager.getTest().log(Status.PASS, "Click on Search");
   	loginbanner.ClickSearchbutton();
   	
   	WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(100));
    loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
   	
   	loginbanner.clearHeader();
   	
  	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
		{
		ExtentTestManager.getTest().log(Status.PASS, "Input modified data for HeaderField");
		
		String modifiedHeader = testdata.get("CallCenterModifiedHeader").toString();
		System.out.println(modifiedHeader);
		loginbanner.modifiedDataForHeader(modifiedHeader);
	}
   	
  	WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(60));
  	loginbanner.clearDetail();
  	
   	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
     		{
   			ExtentTestManager.getTest().log(Status.PASS, "Input modified data for DetailField");
   			
   			String modifiedlink1 = testdata.get("CallCenterModifiedDetail").toString();
   			System.out.println(modifiedlink1);
   			loginbanner.modifiedDataForlink1(modifiedlink1);
   		}
	
	    WebDriverWait wait16=new WebDriverWait(driver,Duration.ofSeconds(60));
       ExtentTestManager.getTest().log(Status.PASS, "Click on Submit");
	    loginbanner.clicksubmit();
	    
	   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
  }

catch(AssertionError|Exception e) {

String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
throw e;
}
  Thread.sleep(3000);
}
 

//Test case-Verify Updated Data Post Logout for Call Centre User  

@Test(priority=29,dataProvider="TestData")

public void VerifyModifieddataforCallCentre( Map<Object,Object> testdata) throws Throwable {
WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);

// Declare and initialize the initial window handle
String initialWindowHandle = driver.getWindowHandle();

try
{
    WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(100));
        ExtentTestManager.getTest().log(Status.PASS, "Click on profile icon");
	    loginbanner.Clickprofiledropdown();
	    
	    WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(100));
        ExtentTestManager.getTest().log(Status.PASS, "Click on LogOut");
	    loginbanner.Clicklogout();
	    
	    Thread.sleep(3000);
	    
		WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(100));
		ExtentTestManager.getTest().log(Status.PASS, "Navigate to Call Centre app URL");
		loginbanner.callCentreURL();
		
		WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(100));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
	   
		WebDriverWait wait16=new WebDriverWait(driver, Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "The data entered in the 'Heading' and 'Details' should display in the call centre user's login page.");
		loginbanner.dataforlink1();
	
		WebDriverWait wait17=new WebDriverWait(driver,Duration.ofSeconds(90));
		ExtentTestManager.getTest().log(Status.PASS, "Click on provided Link1");
		loginbanner.clickonProfilePagelink1();
		
		ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.The data entered in the 'Heading' and 'Details'is displayed in the call centre user's login page.2.The Call Center user is redirected to given link");

}

catch(AssertionError|Exception e) {

String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
throw e;
}
finally {
    // Close the second browser window if it exists
    Set<String> allWindowHandles = driver.getWindowHandles();
    for (String windowHandle : allWindowHandles) {
        if (!windowHandle.equals(initialWindowHandle)) {
            driver.switchTo().window(windowHandle);
            driver.close(); // Close second browser window
        }
    }

    // Switch back to the initial window and close it as well
    driver.switchTo().window(initialWindowHandle);
    driver.close(); // Close the initial browser window
}
Thread.sleep(3000);
}
   
//Test case-1. verify usertype dropdown 2. Select "Collection Agency " from the 'User Type' dropdown. 3.Select "Information" from banner type dropdown 4.Select "Header1" from Section dropdown 5.Enter Heading field
//6.Enter Detail field 7.Click on Submit
  
@Test(priority=30,dataProvider="TestData")

public void SubmitDataForAgencyUser(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));

	   try
	   {
  CoreAppLogin.CoreLogin();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(100));
		   loginbanner.clickOnConfigurationMenu();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(100));
		   loginbanner.clickOnLoginBannerConfigMenu();
		   
		   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
		   

		   Thread.sleep(2000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(30));
		   ExtentTestManager.getTest().log(Status.PASS, "Select \"Agency User\" from Usertype dropdown");
		   Thread.sleep(2000);
		   loginbanner.selectAgencyUser();
		   
		   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
         ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown");
 	     loginbanner.clickBannertypedropdown();
 	     
		   WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));
 
	
 	    ExtentTestManager.getTest().log(Status.PASS, "Select \"Information\" from the Banner Type dropdown.");
 	    WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(90));
 	    loginbanner.clickInfo();
 	   
 	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
 	   
 	   WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Section Dropdown");
    	loginbanner.clickSectiondropdown();
    	
		  
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
    	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
     
    	ExtentTestManager.getTest().log(Status.PASS, "Select \"Header1\" from Section dropdown");
    	loginbanner.selectheader1();
    	
     ExtentTestManager.getTest().log(Status.PASS, "Input an alphanumeric  line in the 'Heading' field with max length of 35");
  	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
  	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String AlphaNumericInput = testdata.get("AgencyUserHeader1").toString();
		        loginbanner.Headingfieldinput(AlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(60));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		
	        String AlphaNumericInput = testdata.get("AgencyUserDetail1").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
	
    }
		
 	WebDriverWait wait13=new WebDriverWait(driver, Duration.ofSeconds(90));
 	ExtentTestManager.getTest().log(Status.PASS, "Click on Submit button");
 	loginbanner.clicksubmit();
 	
 	ExtentTestManager.getTest().log(Status.PASS, "Data should be submitted successfully.");
 	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
 	} 	
	catch(AssertionError|Exception e) {
 		
 		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
 	}
  Thread.sleep(3000);   
 }


//Test case-1.Select "Collection Agency" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
//7.Logout and Verify the data on login page of Collection Agency user

@Test(priority=31,dataProvider="TestData")
public void VerifyPostLogoutdataAgencyUser(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	   
	   // Declare and initialize the initial window handle
	    String initialWindowHandle = driver.getWindowHandle();
	    
	   try
	   {
 	
  	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(100));
  	ExtentTestManager.getTest().log(Status.PASS, "Navigate to profile icon");
  	loginbanner.Clickprofiledropdown();
  	
  	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(100));
  	ExtentTestManager.getTest().log(Status.PASS, "Click on Logout");
  	loginbanner.Clicklogout();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "The data entered in the 'Heading' and 'Details'should display in the Agency user's login page.");
  	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(90));
  	loginbanner.CollectionAgencyURL();
  	
  	loginbanner.profilepagetext();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the Agency user's login page.");

  	} 	
	catch(AssertionError|Exception e) {
  		
  		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
  	}
   	finally {
        // Close the second browser window if it exists
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close(); // Close second browser window
            }
        }

        // Switch back to the initial window and close it as well
        driver.switchTo().window(initialWindowHandle);
        driver.close(); // Close the initial browser window
    }
	   Thread.sleep(3000);
}
//Search Existing Data-For Agency User
@Test(priority=32)
public void SearchDataForAgencyUser() throws Throwable {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	   try
	   {
  CoreAppLogin.CoreLogin();
		   
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(100));
		   loginbanner.clickOnConfigurationMenu();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(100));
		   loginbanner.clickOnLoginBannerConfigMenu();
		   
		   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
		   

		   Thread.sleep(3000);
		   ExtentTestManager.getTest().log(Status.PASS, "Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.UserTypeallvalues));
		   
		   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(30));
		   ExtentTestManager.getTest().log(Status.PASS, "Select \"Collection Agency\" from Usertype dropdown");
		   Thread.sleep(3000);
		   loginbanner.selectAgencyUser();
		   
		   WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
      ExtentTestManager.getTest().log(Status.PASS, "Click on Bannertype dropdown");
	   loginbanner.clickBannertypedropdown();
	   
	   WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Bannertypeallvalue));

	
	   ExtentTestManager.getTest().log(Status.PASS, " Select \"Information\" from the Banner Type dropdown.");
	   WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
	   loginbanner.clickInfo();
	   
	   loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	
	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(90)); 
  	ExtentTestManager.getTest().log(Status.PASS, "Click on Section Dropdown");
  	loginbanner.clickSectiondropdown();
  	
	   WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PageRepository.Sectionallvalue));
  	
  	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
  	ExtentTestManager.getTest().log(Status.PASS, "Select \"Header1\" from Section dropdown");
  	loginbanner.selectheader1();
  	
  	
  	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
	ExtentTestManager.getTest().log(Status.PASS, "Click on Search");
  	loginbanner.ClickSearchbutton();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "The saved data should populate in the 'Heading' and 'Details' fields.");
  	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The saved data is populating in the 'Heading' and 'Details' fields.");
}
		catch(AssertionError|Exception e) {
	  		
	  		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	  	}
	      
	  }

//Edit and update the data for Agency User
@Test(priority=33,dataProvider="TestData")
public void ModifydataforAgencyUser( Map<Object,Object> testdata) throws Throwable {
	  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	  loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
	
	  try
	  {

		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		loginbanner.clearHeader();

		if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
			{
			ExtentTestManager.getTest().log(Status.PASS, "Input modified data for HeaderField");
			
			String modifiedHeader = testdata.get("AgencyUpdatedHeader1").toString();
			System.out.println(modifiedHeader);
			loginbanner.modifiedDataForHeader(modifiedHeader);
		}
	   	
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		loginbanner.clearDetail();
	  	
	   	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
	     		{
	   			ExtentTestManager.getTest().log(Status.PASS, "Input modified data for DetailField");
	   			
	   			String modifiedlink1 = testdata.get("AgencyUpdatedDetail1").toString();
	   			System.out.println(modifiedlink1);
	   			loginbanner.modifiedDataForlink1(modifiedlink1);
	   		}
		
		    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
	       ExtentTestManager.getTest().log(Status.PASS, "Click on Submit");
		    loginbanner.clicksubmit();
		    
		    ExtentTestManager.getTest().log(Status.PASS, "Expected result-Data should be submitted successfully.");
		    ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
	  }

	catch(AssertionError|Exception e) {

	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	throw e;
	}
	}


//Verify updated data post logout for Agency User
@Test(priority=34,dataProvider="TestData")
public void VerifyModifiedDataforAgencyUser( Map<Object,Object> testdata) throws Throwable {
	  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));

	  try
	  {
		  loginbanner.waitForSpinnerToDisappear("Loading Spinner", PageRepository.spinner);
		    
		    WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(100));
		    ExtentTestManager.getTest().log(Status.PASS, "Click on Profile dropdown");
			loginbanner.Clickprofiledropdown();
			
		    WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(100));
		    ExtentTestManager.getTest().log(Status.PASS, "Click on LogOut");
			loginbanner.Clicklogout();
			
			
		    WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(90));
		    ExtentTestManager.getTest().log(Status.PASS, "Navigate to Collection Agency URL");
			loginbanner.CollectionAgencyURL();
			
			WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(90));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
		   
			WebDriverWait wait16=new WebDriverWait(driver, Duration.ofSeconds(90));
			ExtentTestManager.getTest().log(Status.PASS, "The data entered in the 'Heading' and 'Details' should display in the Collection Agency user's login page.");
			loginbanner.dataforlink1();
		
			WebDriverWait wait17=new WebDriverWait(driver,Duration.ofSeconds(60));
			ExtentTestManager.getTest().log(Status.PASS, "Click on provided Link1");
			loginbanner.clickonProfilePagelink1();
			
			ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.The data entered in the 'Heading' and 'Details'is displayed in the Collection Agency user's login page.2.The user is redirected to given link");
			
			driver.close();
	  }

	catch(AssertionError|Exception e) {

	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	throw e;
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
 		public void AfterClass() {
 		     ExtentManager.getInstance().flush();
 		  // Close the browser
 		        if (driver != null) {
 		            driver.quit();
 		        }
 }
}    
