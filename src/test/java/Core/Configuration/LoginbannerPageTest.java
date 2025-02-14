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
        
        @Test(priority=4,enabled=false)
        public void SelectInternalUserType() throws Throwable {
        	 
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
        	try
       	{
    		ExtentTestManager.getTest().log(Status.PASS,"1.Click on Usertype dropdown");
        		loginbanner.clickuserdropdown();
        		ExtentTestManager.getTest().log(Status.PASS,"2.Internal user , call centre user , collection agency user displayed in dropdown ");

        		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
        		loginbanner.selectinternaluser();
        		ExtentTestManager.getTest().log(Status.PASS, "3.\"Internal user\" is selected in the user type field.");
                
           
          	}
           catch(AssertionError|Exception e) {
               		
       	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
       		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
       		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
       		        throw e;
       	    	}
               }
	// Select Banner Type(Information)
        @Test (priority=5,enabled=false)
        public void SelectInformationtype() throws InterruptedException {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
        	try
        	{
           ExtentTestManager.getTest().log(Status.PASS, "1.Click on \"Bannertype dropdown\"");
        	   loginbanner.clickBannertypedropdown();
        
        //select Information from bannertype dropdown	
        	   ExtentTestManager.getTest().log(Status.PASS, "Expected result-2. Select \"Information\" from the ' Banner Type' dropdown.");
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
        @Test(priority=6,enabled=false)
        public void SelectHeader1() throws InterruptedException {
        	
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(200));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
//        	Common.fluentWait("wait till spinner to dissappear", PageRepository.spinner);
      	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "1.Click on Section Dropdown");
        	loginbanner.clickSectiondropdown();
        	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
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
        		        String ExpectedAlphaNumericInput = testdata.get("HeadingField").toString();
        		        System.out.println(ExpectedAlphaNumericInput);
        		       String ActualInput =  loginbanner.Headingfieldinput(ExpectedAlphaNumericInput);
        		       
        	  ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Heading' field with max length of 35");
        	        	       
        	  Assert.assertEquals("Heading text does not match expected text",ExpectedAlphaNumericInput,ActualInput);   
        	  ExtentTestManager.getTest().log(Status.PASS, "Actaul result-1.Alphanumeric line of data is entered in the heading field ");
        	
        	  // Assert that the entered text length does not exceed 35 characters
        	
              Assert.assertTrue(ActualInput.length()<=35); 
              ExtentTestManager.getTest().log(Status.PASS, "Actaul result-2.Entered input in Headingfield  is less than 35 character");
          
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
        @Test(priority=8,dataProvider= "TestData",enabled=false)
        public void InputDetailField( Map<Object,Object> testdata) {
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
        			// Step 2: Retrieve the "AlphaNumericInput" data from the test data
    		        String ExpectedAlphaNumericInput = testdata.get("DetailField").toString();
        			String ActualInput=loginbanner.Detailfieldinput(ExpectedAlphaNumericInput);
        		
        			ExtentTestManager.getTest().log(Status.PASS, "Expected result-Input an alphanumeric  line in the 'Detail' field with max length of 35");
        			Assert.assertEquals("Detailtextinput does not match expected text",ExpectedAlphaNumericInput,ActualInput);
        			ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.Alphanumeric line of data is entered in the detail field ");
        			// Assert that the entered text length does not exceed 35 characters
                	
                    Assert.assertTrue(ActualInput.length()<=35); 
                    ExtentTestManager.getTest().log(Status.PASS, "Actual result-2.Entered input for detailfield is less than 35 character");
                
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
        @Test(priority=9,enabled=false)
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
	        
	    }
 //Verify Data Post Logout(Usertype=Internal user,bannertype=Information,Section=Header1)
        
       @Test(priority=10,enabled=false)
        public void CheckdataPostLogout() {

        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "1.Navigate to profile icon");
        	loginbanner.Clickprofiledropdown();
        	
        	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(300));
        	ExtentTestManager.getTest().log(Status.PASS, "2.Click on Logout");
        	loginbanner.Clicklogout();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Expected Result-The data entered in the 'Heading' and 'Details'should display in the internal user's login page.");
        	WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(120));
        	loginbanner.profilepagetext();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the internal user's login page.");
        	}
   	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
        
  
  //Click on usertype=> select internal user
       @Test(priority=3,enabled=false)
       public void selectinternaluser() throws Throwable {
    	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
    	   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
    	   try
    	   {
    		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
    		   loginbanner.clickusertypedropdown();
    		   
    		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
    		   loginbanner.selectinternaluser();
    		   
    		   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Internal user is selected in Usertypedropdown");
    	   }
  	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
       
        
        
  //Select hyperlink from banner type dropdown
        @Test(priority=4,enabled=false)
        public void Selecthyperlink() throws Throwable {
        	WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "1.Click on Bannertype dropdown.");
        	loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait1 =new WebDriverWait(driver,Duration.ofSeconds(90));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "2.Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
        	ExtentTestManager.getTest().log(Status.PASS, "Actual result-\"hyperlink\" selected from the 'Banner Type' dropdown.");
        	}
  	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
        
 //select link1 from section dropdown
        @Test(priority=5,enabled=false)
        public void Selectlink1() throws Throwable {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	try
        	{
        	ExtentTestManager.getTest().log(Status.PASS, "1.Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "2.Select link1 from section dropdown");
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
        }
  
        
//Input Headerlink
        @Test(priority=6,dataProvider="TestData",enabled=false)
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
        @Test(priority=7,dataProvider="TestData",enabled=false)
        public void InputDetailLink( Map<Object,Object> testdata) {
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
     }       
   
 //Verify Data Post Logout(Usertype=Internal user,bannertype=Hyperlink,Section=link1)  
        
        @Test(priority=8,enabled=false)
        
        public void ClickonLink1PostLogout() {
        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(300));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	try
        	{
        		ExtentTestManager.getTest().log(Status.PASS, "1.Navigate to profile icon");
            	loginbanner.Clickprofiledropdown();
            	
            	WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(400));
            	ExtentTestManager.getTest().log(Status.PASS, "2.Click on Logout");
            	loginbanner.Clicklogout();
            	
            	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(600));
        		ExtentTestManager.getTest().log(Status.PASS, "Expected-1.Data entered for hyperlink should be displayed on login page");
        		loginbanner.dataforlink1();
        		
        		WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(600));
        		ExtentTestManager.getTest().log(Status.PASS, "2.User should be able to click on link1");
        		loginbanner.clickonProfilePagelink1();
        		
        		ExtentTestManager.getTest().log(Status.PASS, "Actual-Data entered for hyperlink is displayed on Internal User's login page and user is able to click on link1 and the page is opened");
        	}	
        		catch(AssertionError|Exception e) {
    	    		
    	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    		        throw e;
    	 	}
    }
 //Test case-1. Select "internal user" from 'User Type'.2. Select "hyperlink" from 'Banner Type'. 3. Enter "link1" in 'Section'.4. Click on 'Search'.
  //Search entered details for link1      
 
        @Test(priority=4,enabled=false)
        public void VerifyInputdataUsingSearch2() throws Throwable {
           WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(200));
       	   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
        	try
        	{
        		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
        		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
        		   loginbanner.clickusertypedropdown();
        		   
        		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
        		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
        		   loginbanner.selectinternaluser();
        		   
        		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
        		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
                   loginbanner.clickBannertypedropdown();
                	
                	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
                	loginbanner.hyperlink();
                	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
                	
                	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
                	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
                	
                	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
                	loginbanner.clickSectiondropdown();
                	
                	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
                	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
                	loginbanner.selectlink1FromSectiondropdown();
                	
                	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(200));
                	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
                	loginbanner.ClickSearchbutton();
                	
                	ExtentTestManager.getTest().log(Status.PASS, "8.The saved data should populate in the 'Heading' and 'Details' fields.");
                	
                	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The saved data populated in the 'Heading' and 'Details' fields.");
        	}
 	catch(AssertionError|Exception e) {
	    		
	    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
	    	}
        }
        
        
 //Test case-1. Select "internal user" from 'User Type'.2. Select "information" from 'Banner Type'. 3. Enter "Header1" in 'Section'.4. Click on 'Search'.
 //Search entered details for Header1      
        
    @Test(priority=4,enabled=false)
    public void VerifydataUsingSearch1() throws Throwable {
    	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(200));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
    	try
    	{
    	 WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
   		 ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
   		 loginbanner.clickusertypedropdown();
   		   
   		 WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
   		 ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
   		 loginbanner.selectinternaluser();
   		   
   		 WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));
   		 ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
         loginbanner.clickBannertypedropdown();
         
         WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(90));  
         loginbanner.clickInfo();
    	 ExtentTestManager.getTest().log(Status.PASS, "4.Select\" Information\"in the banner type field.");
    	 
    	 WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
    	 
    	 ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
       	 loginbanner.clickSectiondropdown();
       	 
       	 
       	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(220));
        ExtentTestManager.getTest().log(Status.PASS, "6.Select Header1 from Section dropdown");
       	loginbanner.selectheader1();
       	
      	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(400));
    	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
    	loginbanner.ClickSearchbutton();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "8.The saved data should populate in the 'Heading' and 'Details' fields.");
    	
    	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The saved data populated in the 'Heading' and 'Details' fields.");
    	 }
    	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
    }
    
//Modify data in 'Heading' and 'Details' fields and submit for Internal user
    
   @Test(priority=4,dataProvider="TestData",enabled=false)
   public void modifydata_link1( Map<Object,Object> testdata) throws Throwable {
	   WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
	   try
	   {
   		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
        	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
        	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
        	loginbanner.ClickSearchbutton();
        	
        	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	
        	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
          		{
        			ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data");
        			
        			String modifiedlink1 = testdata.get("Modifiedlink1").toString();
        			System.out.println(modifiedlink1);
        			loginbanner.modifiedDataForlink1(modifiedlink1);
        		}
        		
     //   		return;	
        	
   	    WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(400));
        ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit");
   	    loginbanner.clicksubmit();
   	    
   	   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is updated successfully.");
	   }

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
    }
} 
    
 //Verify Updated Data Post Logout.Click on modified link1  
   
   @Test(priority=4,dataProvider="TestData",enabled=false)
   public void VerifyUpdatedDataPostLogout(Map<Object,Object> testdata) throws Throwable {
	   WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
	   try
	   {
   		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
        	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
        	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
        	loginbanner.ClickSearchbutton();
        	
        	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
        	
        	
        	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
          		{
        			ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data");
        			
        			String modifiedlink1 = testdata.get("Modifiedlink1").toString();
        			System.out.println(modifiedlink1);
        			loginbanner.modifiedDataForlink1(modifiedlink1);
        		}
    	
   	    WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(400));
        ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit");
   	    loginbanner.clicksubmit();
   	    
 	   WebDriverWait wait11= new WebDriverWait(driver, Duration.ofSeconds(200));
 	   wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
		ExtentTestManager.getTest().log(Status.PASS, "10.Navigate to profile icon");
    	loginbanner.Clickprofiledropdown();
    	
    	WebDriverWait wait12=new WebDriverWait(driver, Duration.ofSeconds(400));
    	ExtentTestManager.getTest().log(Status.PASS, "11.Click on Logout");
    	loginbanner.Clicklogout();
    	
    	WebDriverWait wait13=new WebDriverWait(driver, Duration.ofSeconds(800));
		ExtentTestManager.getTest().log(Status.PASS, "12.Data entered for hyperlink should be displayed on login page");
		loginbanner.dataforlink1();
		
		WebDriverWait wait14=new WebDriverWait(driver, Duration.ofSeconds(500));
		ExtentTestManager.getTest().log(Status.PASS, "13.User should be able to click on link1");
		loginbanner.clickonProfilePagelink1();
		
		ExtentTestManager.getTest().log(Status.PASS, "Actual-Data entered for hyperlink is displayed on Internal User's login page and user is able to click on link1 and the page is opened");
	   }

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
    }
} 
     
//Submit Without Heading
   @Test(priority=4,dataProvider="TestData",enabled=false)
   public void SubmitWithoutHeading(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
           	WebDriverWait wait5 =new WebDriverWait(driver,Duration.ofSeconds(60));
           	wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
           	
        	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "7.Leave \"Heading\"field empty");
        	
        	ExtentTestManager.getTest().log(Status.PASS, "8.Enter the details in \"Detail\" field");
    		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
    		{
    			
    			String Detaillink = testdata.get("Detailfield1link").toString();
    			loginbanner.detaillink(Detaillink);
    		}
 	    WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(400));
            ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit");
       	    loginbanner.clicksubmit();
       	    
       	   WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
       	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
 
       	   ExtentTestManager.getTest().log(Status.PASS, "10.Warning msg should be display");
       	   
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
	} 
   
 //Submit Without Detail field
   @Test(priority=4,dataProvider="TestData",enabled=false)
   public void SubmitWithoutDetailField(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(60));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select Internal User");
		   loginbanner.selectinternaluser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
           loginbanner.clickBannertypedropdown();
        	
        	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
        	loginbanner.hyperlink();
        	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
        	
           	WebDriverWait wait5 =new WebDriverWait(driver,Duration.ofSeconds(60));
           	wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
           	
        	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
        	loginbanner.clickSectiondropdown();
        	
        	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
        	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
        	loginbanner.selectlink1FromSectiondropdown();
        	
        	ExtentTestManager.getTest().log(Status.PASS, "7.Leave \"Detail\"field empty");
        	
        	ExtentTestManager.getTest().log(Status.PASS, "8.Enter the details in \"Heading\" field");
    		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
    		{
    			
    			String Headerlink = testdata.get("Headerfield1link").toString();
    			loginbanner.headerlink(Headerlink);
    		}
    	
       	    WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(400));
            ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit");
       	    loginbanner.clicksubmit();
       	    
       	   WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
       	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
 
       	   ExtentTestManager.getTest().log(Status.PASS, "10.Warning msg should be display");
       	   
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
	}   
   
//Select "callcentre user" from the 'User Type' dropdown.  
   @Test(priority=4,enabled=false)
   public void SelectCallCenter() throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
       	   
	   }
	   catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		    }
	} 
   
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
   
  @Test(priority=4,dataProvider="TestData",enabled=false)
  public void SubmitCallCentreData1(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
           ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
    	   loginbanner.clickBannertypedropdown();
    
 	
    	   ExtentTestManager.getTest().log(Status.PASS, "4. Select \"Information\" from the Banner Type dropdown.");
    	   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(200));
    	   loginbanner.clickInfo();
    	   
    	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
    	   
       	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
       	loginbanner.clickSectiondropdown();
       	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(120));
        
       	ExtentTestManager.getTest().log(Status.PASS, "6.Select \"Header1\" from Section dropdown");
       	loginbanner.selectheader1();
       	
        ExtentTestManager.getTest().log(Status.PASS, "7.Input an alphanumeric  line in the 'Heading' field with max length of 35");
     	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
     	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String AlphaNumericInput = testdata.get("CallCenterHeadingtext").toString();
		        loginbanner.Headingfieldinput(AlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "8.Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
 		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
 		{
		
	        String AlphaNumericInput = testdata.get("CallCenterDetailtext").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
		
		
       }
 		
    	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
    	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
    	loginbanner.clicksubmit();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "10.Data should be submitted successfully.");
    	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
    	} 	
	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
        
    }
 
  
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
//7.Logout and Verify the data on login page of Call Centre user
  
  @Test(priority=4,dataProvider="TestData",enabled=false)
  public void VerifydataPostLogoutCC1(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
           ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
    	   loginbanner.clickBannertypedropdown();
    
 	
    	   ExtentTestManager.getTest().log(Status.PASS, "4. Select \"Information\" from the Banner Type dropdown.");
    	   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(200));
    	   loginbanner.clickInfo();
    	   
    	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
    	   
       	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
       	loginbanner.clickSectiondropdown();
       	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(120));
        
       	ExtentTestManager.getTest().log(Status.PASS, "6.Select \"Header1\" from Section dropdown");
       	loginbanner.selectheader1();
       	
        ExtentTestManager.getTest().log(Status.PASS, "7.Input an alphanumeric  line in the 'Heading' field with max length of 35");
     	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
     	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String ExpectedAlphaNumericInput = testdata.get("CallCenterHeadingtext").toString();
		        loginbanner.Headingfieldinput(ExpectedAlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "8.Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
 		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
 		{
		
	        String AlphaNumericInput = testdata.get("CallCenterDetailtext").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
		
		
       }
 		
    	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
    	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
    	loginbanner.clicksubmit();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "10.Data should be submitted successfully.");
    	
    	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(200));
    	ExtentTestManager.getTest().log(Status.PASS, "11.Navigate to profile icon");
    	loginbanner.Clickprofiledropdown();
    	
    	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(300));
    	ExtentTestManager.getTest().log(Status.PASS, "12.Click on Logout");
    	loginbanner.Clicklogout();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "13.The data entered in the 'Heading' and 'Details'should display in the Call Centre user's login page.");
    	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(120));
    	loginbanner.profilepagetext();
    	
    	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the Call Centre user's login page.");
    	
    	
    	} 	
	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
        
    } 

  
//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select "Hyperlink" from banner type dropdown 3.Select "link1" from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit  

  @Test(priority=4,dataProvider="TestData",enabled=false)
  
public void SubmitCallCentreData2(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
         ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
  	    loginbanner.clickBannertypedropdown();
  
	
    	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(90));
    	loginbanner.hyperlink();
    	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
  	   
  	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
  	   
    	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
   	   loginbanner.clickSectiondropdown();
   	
     	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
    	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
    	loginbanner.selectlink1FromSectiondropdown();
     	
		ExtentTestManager.getTest().log(Status.PASS, "7.Input data for HeaderField");
    	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
   	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
 			String Headerlink = testdata.get("CallCenterHeaderLink1").toString();
 			loginbanner.headerlink(Headerlink);
	    }
		 
		ExtentTestManager.getTest().log(Status.PASS, "8.Input data for DetailField");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
        	String Detaillink = testdata.get("CallCenterDetailLink1").toString();
			loginbanner.detaillink(Detaillink);
	
     }
		
  	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
  	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
  	loginbanner.clicksubmit();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "10.Data should be submitted successfully.");
  	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
  	} 	
	catch(AssertionError|Exception e) {
  		
  		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
  	}
      
  }  

//Test case-1.Select "Call Centre" from Usertype dropdown 2.Select "Hyperlink" from banner type dropdown 3.Select "link1" from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit  
//7.Logout and Verify the data on login page of Call Centre user. 8.Click on link1 to load the page

@Test(priority=4,dataProvider="TestData",enabled=false)

public void VerifydataPostLogoutCC2(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
       ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
	    loginbanner.clickBannertypedropdown();

	
  	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(90));
  	loginbanner.hyperlink();
  	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
	   
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
	   
  	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
 	   loginbanner.clickSectiondropdown();
 	
   	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
  	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
  	loginbanner.selectlink1FromSectiondropdown();
   	
		ExtentTestManager.getTest().log(Status.PASS, "7.Input data for HeaderField");
  	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
 	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
			String Headerlink = testdata.get("CallCenterHeaderLink1").toString();
			loginbanner.headerlink(Headerlink);
	    }
		 
		ExtentTestManager.getTest().log(Status.PASS, "8.Input data for DetailField");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
      	String Detaillink = testdata.get("CallCenterDetailLink1").toString();
			loginbanner.detaillink(Detaillink);
	
   }
		
	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
	loginbanner.clicksubmit();
	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	
	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(200));
	ExtentTestManager.getTest().log(Status.PASS, "10.Click on Profile icon");
	loginbanner.Clickprofiledropdown();
	
	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(200));
	ExtentTestManager.getTest().log(Status.PASS, "11.Click on LogOut");
	loginbanner.Clicklogout();
	
	WebDriverWait wait11=new WebDriverWait(driver, Duration.ofSeconds(90));
	ExtentTestManager.getTest().log(Status.PASS, "12.The data entered in the 'Heading' and 'Details' should display in the call centre user's login page.");
	loginbanner.dataforlink1();
	
	WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(90));
	ExtentTestManager.getTest().log(Status.PASS, "13.Navigate to Call Centre app URL");
	loginbanner.callCentreURL();
	
	WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(400));
	wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
	
	WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(500));
	ExtentTestManager.getTest().log(Status.PASS, "13.Click on provided Link1");
	loginbanner.clickonProfilePagelink1();
	
	ExtentTestManager.getTest().log(Status.PASS, "Actual result-The Call Center user should be redirected to given page");
	} 	
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
	}
    
}


//Test case-Edit and Update Data(Link1) for Call Centre user and submit  

@Test(priority=4,dataProvider="TestData",enabled=false)

public void ModifydataforCallCentre( Map<Object,Object> testdata) throws Throwable {
  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
  wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
  try
  {
	  WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
	   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
	   loginbanner.clickusertypedropdown();
	   
	   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	   ExtentTestManager.getTest().log(Status.PASS, "2.Select\" Call Centre\" User");
	   loginbanner.selectCallCentre();
	   
	   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
	   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
      loginbanner.clickBannertypedropdown();
   	
   	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
   	loginbanner.hyperlink();
   	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
   	
   	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
   	
   	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
   	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
   	loginbanner.clickSectiondropdown();
   	
   	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
   	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
   	loginbanner.selectlink1FromSectiondropdown();
   	
   	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
   	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
   	loginbanner.ClickSearchbutton();
   	
   	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
   	
  	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
		{
		ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data for HeaderField");
		
		String modifiedHeader = testdata.get("CallCenterModifiedHeader").toString();
		System.out.println(modifiedHeader);
		loginbanner.modifiedDataForHeader(modifiedHeader);
	}
   	
  	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(600));
  	
   	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
     		{
   			ExtentTestManager.getTest().log(Status.PASS, "9.Input modified data for DetailField");
   			
   			String modifiedlink1 = testdata.get("CallCenterModifiedDetail").toString();
   			System.out.println(modifiedlink1);
   			loginbanner.modifiedDataForlink1(modifiedlink1);
   		}
	
	    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(400));
       ExtentTestManager.getTest().log(Status.PASS, "10.Click on Submit");
	    loginbanner.clicksubmit();
	    
	   ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
  }

catch(AssertionError|Exception e) {

String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
throw e;
}
}
 

//Test case-Verify Updated Data Post Logout for Call Centre User  

@Test(priority=4,dataProvider="TestData",enabled=false)

public void VerifyModifieddataforCallCentre( Map<Object,Object> testdata) throws Throwable {
WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
try
{
	  WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
	   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
	   loginbanner.clickusertypedropdown();
	   
	   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Call Centre\" User");
	   loginbanner.selectCallCentre();
	   
	   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
	   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
    loginbanner.clickBannertypedropdown();
 	
 	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
 	loginbanner.hyperlink();
 	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
 	
 	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
 	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
 	
 	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
 	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
 	loginbanner.clickSectiondropdown();
 	
 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
 	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
 	loginbanner.selectlink1FromSectiondropdown();
 	
 	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
 	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
 	loginbanner.ClickSearchbutton();
 	
 	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
 	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
 	
	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
		{
		ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data for HeaderField");
		
		String modifiedHeader = testdata.get("CallCenterModifiedHeader").toString();
		System.out.println(modifiedHeader);
		loginbanner.modifiedDataForHeader(modifiedHeader);
	}
 	
	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(600));
	
 	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
   		{
 			ExtentTestManager.getTest().log(Status.PASS, "9.Input modified data for DetailField");
 			
 			String modifiedlink1 = testdata.get("CallCenterModifiedDetail").toString();
 			System.out.println(modifiedlink1);
 			loginbanner.modifiedDataForlink1(modifiedlink1);
 		}
	
	    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(400));
        ExtentTestManager.getTest().log(Status.PASS, "10.Click on Submit");
	    loginbanner.clicksubmit();
	    
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	    
	    WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(400));
        ExtentTestManager.getTest().log(Status.PASS, "11.Click on profile icon");
	    loginbanner.Clickprofiledropdown();
	    
	    WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(400));
        ExtentTestManager.getTest().log(Status.PASS, "12.Click on LogOut");
	    loginbanner.Clicklogout();
	    
		WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(600));
		ExtentTestManager.getTest().log(Status.PASS, "13.Navigate to Call Centre app URL");
		loginbanner.callCentreURL();
		
		WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(500));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
	   
		WebDriverWait wait16=new WebDriverWait(driver, Duration.ofSeconds(200));
		ExtentTestManager.getTest().log(Status.PASS, "14.The data entered in the 'Heading' and 'Details' should display in the call centre user's login page.");
		loginbanner.dataforlink1();
	
		WebDriverWait wait17=new WebDriverWait(driver,Duration.ofSeconds(500));
		ExtentTestManager.getTest().log(Status.PASS, "15.Click on provided Link1");
		loginbanner.clickonProfilePagelink1();
		
		ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.The data entered in the 'Heading' and 'Details'is displayed in the call centre user's login page.2.The Call Center user is redirected to given link");
	   
}

catch(AssertionError|Exception e) {

String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
throw e;
}
}
   
//Test case-1. verify usertype dropdown 2. Select "Collection Agency " from the 'User Type' dropdown. 3.Select "Information" from banner type dropdown 4.Select "Header1" from Section dropdown 5.Enter Heading field
//6.Enter Detail field 7.Click on Submit
  
@Test(priority=4,dataProvider="TestData",enabled=false)

public void SubmitDataForAgencyUser(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Agency User\" from Usertype dropdown");
		   loginbanner.selectAgencyUser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
         ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
 	     loginbanner.clickBannertypedropdown();
 
	
 	    ExtentTestManager.getTest().log(Status.PASS, "4. Select \"Information\" from the Banner Type dropdown.");
 	    WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(200));
 	    loginbanner.clickInfo();
 	   
 	    wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
 	   
    	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
    	loginbanner.clickSectiondropdown();
    	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(120));
     
    	ExtentTestManager.getTest().log(Status.PASS, "6.Select \"Header1\" from Section dropdown");
    	loginbanner.selectheader1();
    	
     ExtentTestManager.getTest().log(Status.PASS, "7.Input an alphanumeric  line in the 'Heading' field with max length of 35");
  	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
  	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String AlphaNumericInput = testdata.get("AgencyUserHeader1").toString();
		        loginbanner.Headingfieldinput(AlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "8.Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		
	        String AlphaNumericInput = testdata.get("AgencyUserDetail1").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
	
    }
		
 	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
 	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
 	loginbanner.clicksubmit();
 	
 	ExtentTestManager.getTest().log(Status.PASS, "10.Data should be submitted successfully.");
 	ExtentTestManager.getTest().log(Status.PASS, "Actual result-Data is submitted successfully.");
 	} 	
	catch(AssertionError|Exception e) {
 		
 		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
 	}
     
 }


//Test case-1.Select "Collection Agency" from Usertype dropdown 2.Select Information from banner type dropdown 3.Select Header1 from Section dropdown 4.Enter Heading details.5.Enter Details  6.Click on Submit   
//7.Logout and Verify the data on login page of Collection Agency user

@Test(priority=4,dataProvider="TestData",enabled=false)
public void VerifyPostLogoutdataAgencyUser(Map<Object,Object>testdata) throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Collection Agency\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
         ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
  	   loginbanner.clickBannertypedropdown();
  
	
  	   ExtentTestManager.getTest().log(Status.PASS, "4. Select \"Information\" from the Banner Type dropdown.");
  	   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(200));
  	   loginbanner.clickInfo();
  	   
  	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
  	   
     	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
     	loginbanner.clickSectiondropdown();
     	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(120));
      
     	ExtentTestManager.getTest().log(Status.PASS, "6.Select \"Header1\" from Section dropdown");
     	loginbanner.selectheader1();
     	
      ExtentTestManager.getTest().log(Status.PASS, "7.Input an alphanumeric  line in the 'Heading' field with max length of 35");
   	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(200));
   	
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		 {
			
		        String AlphaNumericInput = testdata.get("AgencyUserHeader1").toString();
		        loginbanner.Headingfieldinput(AlphaNumericInput);
	    }
		 
	  ExtentTestManager.getTest().log(Status.PASS, "8.Input an alphanumeric  line in the 'Detail' field with max length of 35");
	 	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(220));
	 	
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		
	        String AlphaNumericInput = testdata.get("AgencyUserDetail1").toString();
			loginbanner.Detailfieldinput(AlphaNumericInput);
	
     }
		
  	WebDriverWait wait8=new WebDriverWait(driver, Duration.ofSeconds(90));
  	ExtentTestManager.getTest().log(Status.PASS, "9.Click on Submit button");
  	loginbanner.clicksubmit();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "10.Data should be submitted successfully.");
  	
  	WebDriverWait wait9=new WebDriverWait(driver, Duration.ofSeconds(200));
  	ExtentTestManager.getTest().log(Status.PASS, "11.Navigate to profile icon");
  	loginbanner.Clickprofiledropdown();
  	
  	WebDriverWait wait10=new WebDriverWait(driver, Duration.ofSeconds(300));
  	ExtentTestManager.getTest().log(Status.PASS, "12.Click on Logout");
  	loginbanner.Clicklogout();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "13.The data entered in the 'Heading' and 'Details'should display in the Agency user's login page.");
  	WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(120));
  	loginbanner.profilepagetext();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "Actual Result-The data entered in the 'Heading' and 'Details'displayed in the Agency user's login page.");
	
  	} 	
	catch(AssertionError|Exception e) {
  		
  		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
  	}
      
  }

//Search Existing Data-For Agency User
@Test(priority=4,enabled=false)
public void SearchDataForAgencyUser() throws Throwable {
	   WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(90));
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.warningmsg));
	   try
	   {
		   WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(200));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select \"Collection Agency\" from Usertype dropdown");
		   loginbanner.selectCallCentre();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
      ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown");
	   loginbanner.clickBannertypedropdown();

	
	   ExtentTestManager.getTest().log(Status.PASS, "4. Select \"Information\" from the Banner Type dropdown.");
	   WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(200));
	   loginbanner.clickInfo();
	   
	   wait.until(ExpectedConditions.invisibilityOfElementLocated( PageRepository.spinner));
	
	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(120)); 
  	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section Dropdown");
  	loginbanner.clickSectiondropdown();
  	
  	WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(120));
  	ExtentTestManager.getTest().log(Status.PASS, "6.Select \"Header1\" from Section dropdown");
  	loginbanner.selectheader1();
  	
  	
  	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(120));
	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
  	loginbanner.ClickSearchbutton();
  	
  	ExtentTestManager.getTest().log(Status.PASS, "8.The saved data should populate in the 'Heading' and 'Details' fields.");
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
@Test(priority=4,dataProvider="TestData",enabled=false)
public void ModifydataforAgencyUser( Map<Object,Object> testdata) throws Throwable {
	  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
	  try
	  {
		  WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select\"Collection Agency\" User");
		   loginbanner.selectAgencyUser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
	      loginbanner.clickBannertypedropdown();
	   	
	   	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
	   	loginbanner.hyperlink();
	   	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
	   	
	   	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	   	
	   	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
	   	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
	   	loginbanner.clickSectiondropdown();
	   	
	   	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
	   	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
	   	loginbanner.selectlink1FromSectiondropdown();
	   	
	   	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
	   	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
	   	loginbanner.ClickSearchbutton();
	   	
	   	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	   	
	  	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
			{
			ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data for HeaderField");
			
			String modifiedHeader = testdata.get("AgencyUpdatedHeader1").toString();
			System.out.println(modifiedHeader);
			loginbanner.modifiedDataForHeader(modifiedHeader);
		}
	   	
	  	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(600));
	  	
	   	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
	     		{
	   			ExtentTestManager.getTest().log(Status.PASS, "9.Input modified data for DetailField");
	   			
	   			String modifiedlink1 = testdata.get("AgencyUpdatedDetail1").toString();
	   			System.out.println(modifiedlink1);
	   			loginbanner.modifiedDataForlink1(modifiedlink1);
	   		}
		
		    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(400));
	       ExtentTestManager.getTest().log(Status.PASS, "10.Click on Submit");
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
@Test(priority=4,dataProvider="TestData")
public void VerifyModifiedDataforAgencyUser( Map<Object,Object> testdata) throws Throwable {
	  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
	  try
	  {
		  WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "1.Click on Usertype dropdown");
		   loginbanner.clickusertypedropdown();
		   
		   WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(120));
		   ExtentTestManager.getTest().log(Status.PASS, "2.Select\"Collection Agency\" User");
		   loginbanner.selectAgencyUser();
		   
		   WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		   ExtentTestManager.getTest().log(Status.PASS, "3.Click on Bannertype dropdown.");
	      loginbanner.clickBannertypedropdown();
	   	
	   	WebDriverWait wait4 =new WebDriverWait(driver,Duration.ofSeconds(60));
	   	loginbanner.hyperlink();
	   	ExtentTestManager.getTest().log(Status.PASS, "4.Select \"hyperlink\" from the 'Banner Type' dropdown.");
	   	
	   	WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(200));
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	   	
	   	WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(60));
	   	ExtentTestManager.getTest().log(Status.PASS, "5.Click on Section dropdown");
	   	loginbanner.clickSectiondropdown();
	   	
	   	WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
	   	ExtentTestManager.getTest().log(Status.PASS, "6.Select link1 from section dropdown");
	   	loginbanner.selectlink1FromSectiondropdown();
	   	
	   	WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(300));
	   	ExtentTestManager.getTest().log(Status.PASS, "7.Click on Search");
	   	loginbanner.ClickSearchbutton();
	   	
	   	WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(400));
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
	   	
	  	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
			{
			ExtentTestManager.getTest().log(Status.PASS, "8.Input modified data for HeaderField");
			
			String modifiedHeader = testdata.get("AgencyUpdatedHeader1").toString();
			System.out.println(modifiedHeader);
			loginbanner.modifiedDataForHeader(modifiedHeader);
		}
	   	
	  	WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(600));
	  	
	   	if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) 
	     		{
	   			ExtentTestManager.getTest().log(Status.PASS, "9.Input modified data for DetailField");
	   			
	   			String modifiedlink1 = testdata.get("AgencyUpdatedDetail1").toString();
	   			System.out.println(modifiedlink1);
	   			loginbanner.modifiedDataForlink1(modifiedlink1);
	   		}
		
		    WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(400));
	       ExtentTestManager.getTest().log(Status.PASS, "10.Click on Submit");
		    loginbanner.clicksubmit();
		    
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
		    
		    WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(400));
		    ExtentTestManager.getTest().log(Status.PASS, "11.Click on Profile dropdown");
			loginbanner.Clickprofiledropdown();
			
		    WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(400));
		    ExtentTestManager.getTest().log(Status.PASS, "12.Click on LogOut");
			loginbanner.Clicklogout();
			
			
		    WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(400));
		    ExtentTestManager.getTest().log(Status.PASS, "13.Navigate to Collection Agency URL");
			loginbanner.CollectionAgencyURL();
			
			WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(500));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.ProfilePagewithHeaderandDetaillink));
		   
			WebDriverWait wait16=new WebDriverWait(driver, Duration.ofSeconds(400));
			ExtentTestManager.getTest().log(Status.PASS, "14.The data entered in the 'Heading' and 'Details' should display in the Collection Agency user's login page.");
			loginbanner.dataforlink1();
		
			WebDriverWait wait17=new WebDriverWait(driver,Duration.ofSeconds(500));
			ExtentTestManager.getTest().log(Status.PASS, "15.Click on provided Link1");
			loginbanner.clickonProfilePagelink1();
			
			ExtentTestManager.getTest().log(Status.PASS, "Actual result-1.The data entered in the 'Heading' and 'Details'is displayed in the Collection Agency user's login page.2.The user is redirected to given link");
			
			
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
// 		        if (driver != null) {
// 		            driver.quit();
 		        }
 //}
}    
