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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsPlaceholderManagement_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsPlaceholderManagement_MainClass alertsplaceholdermanagement_mainclass;
	String firstRowText,searchValue;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
	    alertsplaceholdermanagement_mainclass = new AlertsPlaceholderManagement_MainClass(driver); 
	    TestListener = new TestListener();
	    screenShot = new com.Utility.ScreenShot(driver);
	    ExcelReader = new com.Utility.ExcelReader("CoreAlertsPlaceholderManagement");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreAlertsPlaceholderManagement");
    }
	
	 @Test(priority = 1)
	    public void Insert_New_Entry_All_Valid_Fields() throws IOException, InterruptedException { 
		 try {
		    // Prepare and execute the insert query
		    String insertQuery = "Check_Insert_ALT_Template_Placeholder";
		    ExtentTestManager.getTest().log(Status.PASS, "An INSERT query was prepared with valid data for all fields.");
		    List<String> result = alertsplaceholdermanagement_mainclass.prepareAndExecuteInsertQuery(insertQuery);
		    
		    // Ensure result is not null
		    Assert.assertNotNull(result, "Query result should not be null");
		    
		    // Extract values
		    String executionMessage = result.get(0);
		    searchValue = result.get(1);

		    // Print values for debugging
		    System.out.println("Search Value: " + searchValue);
		    System.out.println("Execution Message: " + executionMessage);

		    // Assertions to validate expected outcomes
		    Assert.assertNotNull(searchValue, "Search Value should not be null");
		    Assert.assertNotNull(executionMessage, "Execution Message should not be null");
		    ExtentTestManager.getTest().log(Status.PASS, "The query was executed successfully.");
		    ExtentTestManager.getTest().log(Status.PASS, "The data was committed to the database.");
		    ExtentTestManager.getTest().log(Status.PASS, "The record has been successfully inserted into the Alt_Template_Placeholders table. SP execution message : "+executionMessage+" and Value = "+searchValue);
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }

	
	 @Test(priority = 2)
	    public void Successful_Login() throws Exception {
		 try {
		 Login_Class.CoreLogin();
			driver = baseclass.getDriver();
			drivers.add(driver);
		    alertsplaceholdermanagement_mainclass = new AlertsPlaceholderManagement_MainClass(driver);
		    TestListener = new TestListener();
		    screenShot = new com.Utility.ScreenShot(driver);
		    ExtentTestManager.getTest().log(Status.PASS, "Successfully navigated to the login page.");
			 ExtentTestManager.getTest().log(Status.PASS, "Entered valid username and password.");
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked the login button.");
	        // Expected result: User is logged in
	        String expectedUrl = "http://192.168.32.33:8599/Home";
	        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User is not logged in successfully");
	        ExtentTestManager.getTest().log(Status.PASS, "User has successfully logged into the Core application.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void Access_Alerts_and_Notifications() throws InterruptedException {
		 try {
		 alertsplaceholdermanagement_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Observed the left-side menu for \"Alerts and Notifications\".");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on \"Alerts and Notifications\".");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
		 
	    }
	 
	 @Test(priority = 4)
	    public void Access_Placeholder_Management() throws InterruptedException { 
		 try {
	        // Step 1: Locate and click on Placeholder Management link
		 alertsplaceholdermanagement_mainclass.clickPlaceholderManagement(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Located \"Placeholder Management\" under Alerts and Notifications.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on \"Placeholder Management\".");
	        // Step 2: Verify navigation to Placeholder Management page
	        Assert.assertTrue(alertsplaceholdermanagement_mainclass.isPlaceholderManagementPageDisplayed(), "Failed to navigate to Placeholder Management page");
	        ExtentTestManager.getTest().log(Status.PASS, "User is successfully navigated to the Placeholder Management page.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5)
	    public void Pagination_Controls_Display() throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 alertsplaceholdermanagement_mainclass.scrollToBottomOfTable(); 
		 ExtentTestManager.getTest().log(Status.PASS, "User scrolls to the bottom of the placeholder details table.");
		 List<WebElement> recordTable = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsPlaceholderManagementRepo.recordTablecontent));
		// Check if the table contains data
		 if (!recordTable.isEmpty()) {
		     // Get the first row data
		     WebElement firstRow = recordTable.get(0); 
		     firstRowText = firstRow.getText();
		     
		     System.out.println("First Row Data: " + firstRowText);
		 } else {
		     System.out.println("No records found in the table.");
		 }
	        boolean paginationVisible = alertsplaceholdermanagement_mainclass.arePaginationControlsVisible();
	        ExtentTestManager.getTest().log(Status.PASS, "Pagination controls are verified as present.");
	        
	        Assert.assertTrue(paginationVisible, "Pagination controls should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Pagination controls are visible and operational.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	    public void Paginate_to_Next_Page() throws InterruptedException {
		 try {
	        // Test method to verify pagination to the next page
		 alertsplaceholdermanagement_mainclass.clickNextPage(); // Step 1: Click on "Next Page" button
		 ExtentTestManager.getTest().log(Status.PASS, "User clicks on the \"Next Page\" button");
	        boolean recordsDisplayed = alertsplaceholdermanagement_mainclass.areRecordsDisplayed(); // Step 2: Verify table content
	        ExtentTestManager.getTest().log(Status.PASS, "observes the table content.");
	        Assert.assertTrue(recordsDisplayed, "The next set of records should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The next set of records is displayed successfully.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Paginate_to_Previous_Page() throws InterruptedException { 
		 try {
	        // Step 1: Click on the "Previous Page" button
		 alertsplaceholdermanagement_mainclass.clickPreviousPage();
		 ExtentTestManager.getTest().log(Status.PASS, "The \"Previous Page\" button is clicked.");
	        // Step 2: Observe the table content
	        String tableContent = alertsplaceholdermanagement_mainclass.getTableContent();
	        ExtentTestManager.getTest().log(Status.PASS, "The table content is observed.");
	        // Expected Result: The previous set of records is displayed
	        Assert.assertTrue(tableContent.contains(firstRowText), "The records from the previous page should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The previous set of records is displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Search_Placeholder() throws InterruptedException {	 
		 try {
	        // Perform search
	        alertsplaceholdermanagement_mainclass.enterPlaceholderName(searchValue);
	        ExtentTestManager.getTest().log(Status.PASS, "Entered a placeholder name in the search field.");
	        alertsplaceholdermanagement_mainclass.clickSearchButton(); 
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	        // Verify table updates with search results
	        String tableContents = alertsplaceholdermanagement_mainclass.getTableContent();
	        ExtentTestManager.getTest().log(Status.PASS, "Observed the table content.");
	        Assert.assertTrue(tableContents.contains(searchValue), "Table does not contain the expected search results.");
	        ExtentTestManager.getTest().log(Status.PASS, "The table updated to show records matching the search criteria.");
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
