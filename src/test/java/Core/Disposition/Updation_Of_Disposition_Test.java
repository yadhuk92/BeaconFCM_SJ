package Core.Disposition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.BasePackage.SeleniumLogToFile;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import Core.Disposition.UpdationofDispositionPage;
import com.listeners.TestListener;

public class Updation_Of_Disposition_Test extends Base_Class {
	
	UpdationofDispositionPage updationofdispositionMasterPage;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BOLD = "\u001B[1;34m";
	
	@BeforeClass
	public void SetUp() throws Exception {
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		//SeleniumLogToFile.startLogging();
		Login_Class.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		} else {
			System.out.println("Driver is not null");
		}
		updationofdispositionMasterPage = new UpdationofDispositionPage(driver);
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
		System.out.println("Before class in updation of disposition");
	}
	
	@BeforeMethod
    public void setupTest(Method method) throws Exception {
		//System.out.println("Before method in updation of disposition");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Updation of Disposition");
        Log.info(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
        System.out.println(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
    }
	
	@Test(priority = 1)
    public void Open_Updation_of_Disposition_Screen() throws Exception {
		System.out.println("Test priority 1 in updation of disposition");
		//extenttest = ExtentTestManager.startTest("Updation of disposition module test cases").assignCategory("Updation of Disposition");
		ExcelReader = new com.Utility.ExcelReader("Updation_of_Disposition");
		//corelogin = new Login_Class();
		//driver = baseclass.getDriver();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
		updationofdispositionMasterPage.navigateToUpdationOfDisposition();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		// Find the account number field element
	    WebElement accountNumberField = driver.findElement(UpdationofDispositionRepo.accountNumberField);
        Assert.assertTrue(accountNumberField.isDisplayed());
		// Find the search button element
	    WebElement SearchbuttonField = driver.findElement(UpdationofDispositionRepo.searchButton);
        Assert.assertTrue(SearchbuttonField.isDisplayed());
        ExtentTestManager.getTest().log(Status.PASS, "Disposition update screen with Account number text field and Search button is opened.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.transactiondetails));
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 2, dataProvider = "TestData")
    public void Account_Number_Field__ECP_Validations(Map<Object, Object> testdata) throws InterruptedException {
	 try {
        
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String[] InvalidAccountNumbers = testdata.get("InvalidAccountNumbers").toString().split("\\|");;
	        
	        for (String InvalidAccountNumber : InvalidAccountNumbers) {
	            InvalidAccountNumber = InvalidAccountNumber.trim(); // Remove any leading/trailing spaces
	            updationofdispositionMasterPage.enterAccountNumber(InvalidAccountNumber);
	        }
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Should not possible to type Alphabets and special characters in account number field.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 3, dataProvider = "TestData")
    public void Attempt_to_Enter_More_than_25_Characters(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String InvalidAccountNumber = testdata.get("AccountNumberwithmorethan25characters").toString();
	        
	        updationofdispositionMasterPage.enterAccountNumberwithmore25characters(InvalidAccountNumber);
	    
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Entry is restricted to 25 characters.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }	
	
	@Test(priority = 4, dataProvider = "TestData")
    public void Enter_Invalid_Account_Number_and_Search(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        
        // Step 1: Select multiple values
	// Step 1: Check if the test needs to be executed
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	    	// Step 2: Retrieve the "Account Numbers" data from the test data
	        String InvalidAccountNumber = testdata.get("InvalidAccountNumber").toString(); 
	        
	        updationofdispositionMasterPage.enterInvalidAccountNumber(InvalidAccountNumber);   
	        
	        updationofdispositionMasterPage.clickSearchButton();
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessage(), "Invalid Account Number");
	    }
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 5)
    public void Search_with_Empty_Account_Number_Field() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
		 	updationofdispositionMasterPage.withoutAccountNumber();
	        updationofdispositionMasterPage.clickSearchButton();
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageforemptysearch(), "Invalid account number.");
	    
        ExtentTestManager.getTest().log(Status.PASS, "An error message is displayed \"Invalid account number\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	@Test(priority = 6)
    public void Enter_invalid_Account_Number_which_is_user_not_assigned_branches_and_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.enterinvalidAccountNumbernotassigned(); 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		updationofdispositionMasterPage.clickSearchButton();
		Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageforinvalidAccountNumbernotassigned(), "You are not authorized to do the disposition of this account number");
        ExtentTestManager.getTest().log(Status.PASS, "Should display error message \"You are not authorized to do the disposition of this account number.\"");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage7));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	@Test(priority = 7)
    public void Enter_Valid_Account_Number_and_Search() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.entervalidAccountNumber();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		updationofdispositionMasterPage.clickSearchButton();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.customername));
        Assert.assertTrue(driver.getPageSource().contains("cardsheadings"));
        ExtentTestManager.getTest().log(Status.PASS, "Account details are displayed along with options for New Interaction, Interaction History, Other and Additional details.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	@Test(priority = 8)
    public void Add_New_Interaction_Details__All_Fields_Missing_Validation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		updationofdispositionMasterPage.clickSaveButton();
        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessagewithoutvalue(), "Please Enter All Fields In The Interaction Details");
        ExtentTestManager.getTest().log(Status.PASS, "Error message \"Please Enter All Fields In The Interaction Details\" is displayed.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
    }
	
	 @Test(priority = 9, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Disposition_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException 
	    {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	 
		    	// Step 2: Retrieve the "Action Onwer" data from the test data
		        String Actionowner = testdata.get("ActionOwner").toString();
		        updationofdispositionMasterPage.selectNextActionOwner(Actionowner);
		        updationofdispositionMasterPage.clickSaveButton();
		 }
	        Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenteronlyactionowner(), "Select Disposition");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Disposition\" is displayed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage3));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Sub_Disposition_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String disposition = testdata.get("Disposition").toString();
			 
			 updationofdispositionMasterPage.selectDisposition(disposition);
		        updationofdispositionMasterPage.clickSaveButton();
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenterdisposition(), "Select Sub Disposition");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Sub Disposition\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage4));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 11, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__NextAction_Date_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String subdisposition = testdata.get("Subdisposition").toString();
			 
			 updationofdispositionMasterPage.selectsubDisposition(subdisposition);
		        updationofdispositionMasterPage.clickSaveButton();
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterentersubdisposition(), "Select Next Action Date");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Select Next action date\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage5));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 12, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Remark_Missing_Validation(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 //String date = testdata.get("Date").toString(); 
			 
			 LocalDate currentDate = LocalDate.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			 String formattedDate = currentDate.format(formatter);
			 String dayStr = formattedDate.split("-")[0];
			 int day = Integer.parseInt(dayStr);
			 int date = day + 2;
			 System.out.println("EnterNextActionDate: " + date);
			 
			 updationofdispositionMasterPage.enterNextActionDate(date);
		        updationofdispositionMasterPage.clickSaveButton();
		 }
		 Assert.assertEquals(updationofdispositionMasterPage.getErrorMessageafterenterdate(), "Remarks is required");
		 ExtentTestManager.getTest().log(Status.PASS, "Error message \"Remark is required\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.errorMessage6));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13, dataProvider = "TestData")
	    public void Add_New_Interaction_Details__Successful_Form_Submission(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String remarks = testdata.get("Remarks").toString(); 
			  
			 updationofdispositionMasterPage.enterRemarks(remarks);
		        updationofdispositionMasterPage.clickSaveButton();
		 }
		 Assert.assertTrue(updationofdispositionMasterPage.isSuccessMessageDisplayed(), "Success message not displayed");
		 ExtentTestManager.getTest().log(Status.PASS, "Message \"Saved successfully\" is displayed");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(UpdationofDispositionRepo.successMessage));
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Verify_successfully_saved_interaction_in_history_of_interaction_details_section(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginusername));
		 WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
		// Get the current date
	        LocalDate currentDate = LocalDate.now();
	     // Define the custom date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	        // Format the current date
	        String formattedDate = currentDate.format(formatter);
	        if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	 
		        String Actionowner = testdata.get("ActionOwner").toString();
		        String Disposition = testdata.get("Disposition").toString();
		        String Subdisposition = testdata.get("Subdisposition").toString();
		        String Remarks = testdata.get("Remarks").toString();
		        String date = testdata.get("Interactiondetailsdate").toString();
		        String actualdateformat = date.replace("-", "/");
		        String userName = username.getText();
		        String userId = userid.getText();
		        
		        LocalDate currentDate1 = LocalDate.now();
		        // Format the date (e.g., 2025-05-06 to 06-May-2025)
		        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		        String formattedDate2 = currentDate1.format(formatter1);
		        String actualdateformat1 = formattedDate2.replace("-", "/");
		        System.out.println("formattedDate2: "+formattedDate2);
		        System.out.println("actualdateformat1: "+actualdateformat1);
		        
		        String day = formattedDate2.split("-")[0];
		        System.out.println("Day: " + day);
		        
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 updationofdispositionMasterPage.enterAccountNumber();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 updationofdispositionMasterPage.clickSearchButton();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.transactiondetails));
		 Common.fluentWait("transactiondetails", UpdationofDispositionRepo.transactiondetails);
		 Assert.assertTrue(updationofdispositionMasterPage.isTransactionDisplayedWithExpectedDetails(formattedDate2,Actionowner), 
                 "The newly added interaction details are not displayed as expected.");
		 // Verify all interaction details
		 updationofdispositionMasterPage.verifyInteractionDetails(Disposition, Subdisposition, 
				 Remarks, userName, userId, Actionowner, day);
	        }
			 ExtentTestManager.getTest().log(Status.PASS, "Displays newly added interaction details with transaction date plus next action owner as heading and shows Disposition, Sub disposition, Remarks, Action done by, User EIN, Next Action Owner, Next Action Date details");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	
	 /*@AfterMethod
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
		    
		    ExtentManager.getInstance().flush();
			  // Close the browser
			        if (driver != null) {
			            driver.quit();
			        }
		}*/
	 
	 @AfterMethod
	 public void takeScreenshotOnFailure(ITestResult result) {
	     if (result.getStatus() == ITestResult.FAILURE && driver != null) {
	         try {
	             // Take screenshot only if driver session is still active
	             TakesScreenshot ts = (TakesScreenshot) driver;
	             File src = ts.getScreenshotAs(OutputType.FILE);
	             FileUtils.copyFile(src, new File("./screenshots/" + result.getName() + ".png"));
	             
	         } catch (Exception e) {
	             System.out.println("Screenshot capture failed: " + e.getMessage());
	         }
	     }

	 }
	 
	 @AfterClass
	 public void CloseBrowser() {
		 ExtentManager.getInstance().flush();
		// Quit driver after screenshot
	     if (driver != null) {
	         try {
	             driver.quit();
	         } catch (Exception e) {
	             System.out.println("Driver quit failed: " + e.getMessage());
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
	 
	 /*@AfterSuite
	 public void afterEachTest() {
	     ExtentManager.getInstance().flush();
	  // Close the browser
	        if (driver != null) {
	            driver.quit();
	        }
	 }*/


}