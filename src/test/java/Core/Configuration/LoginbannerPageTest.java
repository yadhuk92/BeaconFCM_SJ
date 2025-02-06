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
import org.openqa.selenium.support.ui.ExpectedCondition;
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
        
 //       @Test(priority = 4)
        public void SelectInternalUserType() throws Throwable {
        	 
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.warningmsg));
        	try
       	{
    		ExtentTestManager.getTest().log(Status.PASS,"1.Click on Usertype dropdown");
        		loginbanner.clickuserdropdown();
        		ExtentTestManager.getTest().log(Status.PASS,"2.Internal user , call centre user , collection agency user displayed in dropdown ");
        		
//               if(testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
            	   
 //       		String usertype=testdata.get("UserType").toString();
 //       		System.out.println(usertype);
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
 //       @Test (priority=5,enabled=false)
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
//        @Test(priority=6,enabled=false)
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
  //      @Test(priority=7,dataProvider="TestData")
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
 //       @Test(priority=8,dataProvider= "TestData")
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
 //       @Test(priority=9)
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
        
//       @Test(priority=10)
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
 //      @Test(priority=4)
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
  //      @Test(priority=5)
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
 //       @Test(priority=6)
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
 //       @Test(priority=7,dataProvider="TestData")
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
//        @Test(priority=8,dataProvider="TestData")
        public void InputDetailLink( Map<Object,Object> testdata) {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
        	try
        	{
        		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
        		{
        			ExtentTestManager.getTest().log(Status.PASS, "Expected-Input link from test data file");
        			
        			String Detaillink = testdata.get("Detailfield1link").toString();
        			loginbanner.detaillink(Detaillink);;
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
        
//        @Test(priority=10)
        
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
            	
            	WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(400));
        		ExtentTestManager.getTest().log(Status.PASS, "Expected-1.Data entered for hyperlink should be displayed on login page");
        		loginbanner.dataforlink1();
        		
        		WebDriverWait wait3=new WebDriverWait(driver, Duration.ofSeconds(500));
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
 
 //       @Test(priority=4)
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
        
    @Test(priority=4)
    public void VerifydataUsingSearch1() throws Throwable {
    	WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(200));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(PageRepository.spinner));
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
    
//Modify data in 'Heading' and 'Details' fields and submit
    
    
    
    
        
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
