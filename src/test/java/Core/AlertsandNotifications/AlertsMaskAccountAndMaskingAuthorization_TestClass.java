package Core.AlertsandNotifications;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAlertsMaskAccountAndMaskingAuthorizationRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsMaskAccountAndMaskingAuthorization_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsMaskAccountAndMaskingAuthorization_MainClass alertsmaskacandmaskingautho_mainclass;
	String account_number,zoneUserID,password;
	List<WebElement>  elements; 
			
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		alertsmaskacandmaskingautho_mainclass = new AlertsMaskAccountAndMaskingAuthorization_MainClass(driver);
	    screenShot = new com.Utility.ScreenShot(driver);
	    ExcelReader = new com.Utility.ExcelReader("CoreAlertsMaskAcAndMaskingAutho");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core Alerts Mask Account and Masking Authorization");
    }
	
	 @Test(priority = 1)
	    public void Login_and_Navigate_to_Mask_Account_Window() throws Exception {  
		 try {
	        // Login into the core application
		 Login_Class.CoreLogin();
		 driver = baseclass.getDriver();
		 alertsmaskacandmaskingautho_mainclass = new AlertsMaskAccountAndMaskingAuthorization_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Successfully logged into the core application.");
	        // Navigate to the alerts and notification menu
		 alertsmaskacandmaskingautho_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to Alerts and Notifications from the left-side menu.");
		 elements = driver.findElements(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountnumberfromdashboard);
		 account_number = elements.get(1).getText(); 
	        // Click on the mask account window link
		 alertsmaskacandmaskingautho_mainclass.clickOnMaskAccountWindow();
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Mask Account window.");
	        // Assert the mask account window is displayed
	        Assert.assertTrue(alertsmaskacandmaskingautho_mainclass.isMaskAccountWindowDisplayed(), "Mask account window is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "The Mask Account window is displayed successfully.");
	        alertsmaskacandmaskingautho_mainclass.deleteMaskedAccount(account_number);
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 2, dataProvider = "TestData")
	    public void Mask_Account_with_Valid_Details(Map<Object, Object> testdata) throws Throwable {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 String value1 = testdata.get("Status").toString();
			 String value2 = testdata.get("Reason").toString();
	        // Test steps based on manual test case
		 alertsmaskacandmaskingautho_mainclass.enterAccountNumber(account_number);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid account number in the account number field.");
		 alertsmaskacandmaskingautho_mainclass.clickGetButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Get' button.");
		 alertsmaskacandmaskingautho_mainclass.selectMaskStatus(value1);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected 'MASK' from the Status field.");
		 alertsmaskacandmaskingautho_mainclass.selectAllAccountLife();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected 'All Account Life' radio button for the masking period.");
		 alertsmaskacandmaskingautho_mainclass.enterReason(value2);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered a reason.");
		 }
		 
		 alertsmaskacandmaskingautho_mainclass.clickSaveButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Save' button.");
		 alertsmaskacandmaskingautho_mainclass.confirmPopup();
		 ExtentTestManager.getTest().log(Status.PASS, "Confirmed in the popup by clicking 'OK'.");
	        // Assert the expected result
	        String expectedMessage = "Masking saved successfully.";
	        Assert.assertEquals(alertsmaskacandmaskingautho_mainclass.getSuccessMessage(), expectedMessage, "Success message validation");
	        ExtentTestManager.getTest().log(Status.PASS, "The success message 'Masking saved successfully.' was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void Approve_Masking_Request() throws Exception {
		 try {
			 List<Object> results = alertsmaskacandmaskingautho_mainclass.createZoneUser(); 

		        // You can use the results if needed
		        zoneUserID = (String) results.get(0);
		        password = (String) results.get(1);
		       // String statusMessage = (String) results.get(2);
	        // Step 1: Login as another user
		 Login_Class.CoreLoginWithInputs(zoneUserID, password);
		 driver = baseclass.getDriver();
		 drivers.add(driver);
		 alertsmaskacandmaskingautho_mainclass = new AlertsMaskAccountAndMaskingAuthorization_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "The user logged in as another user.");
		// Step 2: Click on alerts and notification
		 alertsmaskacandmaskingautho_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "The user clicked on \"Alerts and Notification\" in the left-side menu.");
	        // Step 3: Click on Mask Authorization
		 alertsmaskacandmaskingautho_mainclass.navigateToMaskAuthorization();  
		 ExtentTestManager.getTest().log(Status.PASS, "The user accessed \"Mask Authorization.\"");
	        // Step 4: Enter masked account number and search
		 alertsmaskacandmaskingautho_mainclass.searchMaskedAccount(account_number);
		 ExtentTestManager.getTest().log(Status.PASS, "The user entered the masked account number and clicked the \"Search\" button.");
	        // Step 5: Select the account number from the output grid
	        // Step 6: Click on Approval button
		 alertsmaskacandmaskingautho_mainclass.approveRequest();
		 ExtentTestManager.getTest().log(Status.PASS, "The user selected the account from the output grid.");
		 ExtentTestManager.getTest().log(Status.PASS, "The user clicked the \"Approval\" button.");
	        // Expected Result: Success message "Request approved successfully." is displayed
	        String successMessage = alertsmaskacandmaskingautho_mainclass.getapprovalSuccessMessage();
	        Assert.assertEquals(successMessage, "Request approved successfully.");
	        ExtentTestManager.getTest().log(Status.PASS, "The success message \"Request approved successfully.\" is displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4, dataProvider = "TestData")
	    public void Validate_Masked_Account_in_Notification_Management(Map<Object, Object> testdata) throws Exception {
		 try {
		 Login_Class.CoreLogin();
		 driver = baseclass.getDriver();
		 alertsmaskacandmaskingautho_mainclass = new AlertsMaskAccountAndMaskingAuthorization_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 String value1 = testdata.get("NotificationType").toString();
			 String value2 = testdata.get("Template").toString();
		 
	        // Step 1: Navigate to Notification Management
		 alertsmaskacandmaskingautho_mainclass.navigateToNotificationManagement();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Alerts and Notification in the left-side menu.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Notification Management.");
	        // Step 2-4: Configure notification
		 alertsmaskacandmaskingautho_mainclass.configureNotification(value1, value2); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Configure button.");
		 ExtentTestManager.getTest().log(Status.PASS, "Selected the Notification Type and Template.");
		 }
	        
	        // Step 5: Validate masked account number
	        String warningMessage = alertsmaskacandmaskingautho_mainclass.validateMaskedAccountNumber(account_number);
	        ExtentTestManager.getTest().log(Status.PASS, "Entered the Masked Account Number and clicked on the Validate button.");
	        // Assert the expected warning message
	        Assert.assertEquals(warningMessage, "Masked Account Number.");
	        ExtentTestManager.getTest().log(Status.PASS, "A warning message, \"Masked Account Number,\" was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void Unmask_Account(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 String value1 = testdata.get("Status2").toString();
			 String value2 = testdata.get("Reason").toString();
	        // Test steps based on manual test case
		 alertsmaskacandmaskingautho_mainclass.enterMaskedAccountNumber(account_number);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered the Masked Account Number in the Mask Account window.");
		 alertsmaskacandmaskingautho_mainclass.clickGetButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Get button.");
		 alertsmaskacandmaskingautho_mainclass.selectMaskStatus(value1);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected UNMASK from the Status field.");
		 alertsmaskacandmaskingautho_mainclass.enterReason(value2);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered the Reason.");
		 }
		 alertsmaskacandmaskingautho_mainclass.clickSaveButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Save button.");
		 alertsmaskacandmaskingautho_mainclass.confirmPopup();
		 ExtentTestManager.getTest().log(Status.PASS, "Confirmed in the popup by clicking OK.");
	        // Assert the expected result
	        String expectedMessage = "Masking saved successfully.";
	        Assert.assertEquals(alertsmaskacandmaskingautho_mainclass.getSuccessMessage(), expectedMessage, "Success message validation");
	        ExtentTestManager.getTest().log(Status.PASS, "A success message, \"Masking saved successfully,\" was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	    public void Approve_Unmask_Request() throws Exception {
		 try {
	        // Step 1: Login as another user 
		 Login_Class.CoreLoginWithInputs(zoneUserID, password);
		 driver = baseclass.getDriver();
		 drivers.add(driver);
		 alertsmaskacandmaskingautho_mainclass = new AlertsMaskAccountAndMaskingAuthorization_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Logged in as another user.");
		// Step 2: Click on alerts and notification
		 alertsmaskacandmaskingautho_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Alerts and Notification in the left-side menu.");
	        // Step 3: Click on Mask Authorization
		 alertsmaskacandmaskingautho_mainclass.navigateToMaskAuthorization();  
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Mask Authorization.");
	        // Step 4: Enter masked account number and search
		 alertsmaskacandmaskingautho_mainclass.searchMaskedAccount(account_number);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered the Unmasked Account Number and clicked on the Search button.");
	        // Step 5: Select the account number from the output grid
	        // Step 6: Click on Approval button
		 alertsmaskacandmaskingautho_mainclass.approveRequest();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected the Account Number from the output grid.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Approval button.");
	        // Expected Result: Success message "Request approved successfully." is displayed
	        String successMessage = alertsmaskacandmaskingautho_mainclass.getapprovalSuccessMessage(); 
	        Assert.assertEquals(successMessage, "Request approved successfully.");
	        ExtentTestManager.getTest().log(Status.PASS, "A success message, \"Request approved successfully,\" was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Account_Number_Field_BVA_Validations() throws InterruptedException {
		 try {
	        // Navigate to the alerts and notification menu
		 alertsmaskacandmaskingautho_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Alerts and Notification in the left-side menu.");
	        // Click on the mask account window link
		 alertsmaskacandmaskingautho_mainclass.clickOnMaskAccountWindow();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Mask Authorization.");
	        // Test steps based on manual test case
		 // Perform BVA validation on account number
		    boolean isBVAValid = alertsmaskacandmaskingautho_mainclass.validateAccountNumberBVA();
		    ExtentTestManager.getTest().log(Status.PASS, "Checked the string length for Max, Max-1, and Max+1 in the Account Number field.");
		    // Assert the result - If validation fails, the test should fail
		    Assert.assertTrue(isBVAValid, "Boundary Value Analysis (BVA) for Account Number failed.");
		    ExtentTestManager.getTest().log(Status.PASS, "Inputs with Max and Max-1 length were allowed");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Account_Number_Field_ECP_Validations() throws InterruptedException {
		 try {
		// Perform ECP validation on account number
		    boolean isECPValid = alertsmaskacandmaskingautho_mainclass.validateAccountNumberECP();
		    ExtentTestManager.getTest().log(Status.PASS, "Tested the Account Number field with alphabetic, numeric, and alphanumeric inputs.");
		    // Assert the result - If validation fails, the test should fail
		    Assert.assertTrue(isECPValid, "Equivalence Class Partitioning (ECP) for Account Number failed.");
		    ExtentTestManager.getTest().log(Status.PASS, "Only Numeric inputs were allowed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9, dataProvider = "TestData")
	    public void Enter_Invalid_Account_Number(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 String expectedErrorMessage = "Invalid account.";
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("InvalidAccountNumber").toString();
	        

	        // Step 1: Enter an invalid account number
	        alertsmaskacandmaskingautho_mainclass.enterAccountNumber(value1);
		 }
	        ExtentTestManager.getTest().log(Status.PASS, "Entered an invalid account number.");
	        // Step 2: Click on get button
	        alertsmaskacandmaskingautho_mainclass.clickGetButtonforInvalidaccountNumber();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Get button.");
	        // Validate the error message
	        String actualErrorMessage = alertsmaskacandmaskingautho_mainclass.getErrorMessage();
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
	        ExtentTestManager.getTest().log(Status.PASS, "An error message indicated an invalid account number.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMessage));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10)
	    public void Leave_Account_Number_Empty() throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 alertsmaskacandmaskingautho_mainclass.enterAccountNumber();
		 ExtentTestManager.getTest().log(Status.PASS, "Did not enter an account number.");
		 alertsmaskacandmaskingautho_mainclass.clickGetButtonforInvalidaccountNumber(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Get button.");
	        Assert.assertTrue(alertsmaskacandmaskingautho_mainclass.isErrorMessageDisplayed(), "Error message should be displayed");
	        Assert.assertEquals(alertsmaskacandmaskingautho_mainclass.getErrorMessageText(), "Account Number Required", "Error message text should match");
	        ExtentTestManager.getTest().log(Status.PASS, "An error message prompted to enter an account number.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMsg));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 11, dataProvider = "TestData")
	    public void Confirm_Masking_Without_Reason(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 String value1 = testdata.get("Status").toString();

	        // Test steps based on manual test case
		 alertsmaskacandmaskingautho_mainclass.enterMaskedAccountNumber(account_number);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered the account number.");
		 alertsmaskacandmaskingautho_mainclass.clickGetButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Get button");
		 alertsmaskacandmaskingautho_mainclass.selectMaskStatus(value1);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected MASK from the Status field.");
		 alertsmaskacandmaskingautho_mainclass.selectAllAccountLife();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected 'All Account Life' radio button for the masking period.");
		 }
		 alertsmaskacandmaskingautho_mainclass.clickSaveButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Did not enter a Reason.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Save button.");
		 // Validate error message is as expected
	        String expectedErrorMessage = "Reason is required.";
	        Assert.assertEquals(alertsmaskacandmaskingautho_mainclass.getErrorMegText(), expectedErrorMessage, "Error message mismatch!");
	        ExtentTestManager.getTest().log(Status.PASS, "An error message prompted to enter a Reason.");
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
