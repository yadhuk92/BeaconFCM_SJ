package Core.SummaryReports;

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
import java.time.LocalDate;

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreCollectionOfficerConfiguration;
import com.Page_Repository.UpdationofDispositionRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class BankAllocationSummary_TestClass { 
	
	Base_Class baseclass;
	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	BankAllocationSummary_MainClass bankallocationsummary;
	static String  userid,password,userName,userId;
	List<String> account_numbers_count;
	
	String TodaysDate = String.valueOf(LocalDate.now().getDayOfMonth());
	String YesterdaysDate = String.valueOf(LocalDate.now().minusDays(1).getDayOfMonth()); 
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		bankallocationsummary = new BankAllocationSummary_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - BankAllocationSummary");
        Log.info("****" + method.getName() + "****");
    }
	
	@Test(priority = 1, dataProvider = "TestData")
    public void Verify_zone_user_able_to_allocate_accounts_to_branch(Map<Object, Object> testdata) throws Exception { 
		try { 
	// Login into the core application  
	 Login_Class.CoreLogin(); 
	 driver = baseclass.getDriver();
	 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
	 screenShot = new com.Utility.ScreenShot(driver);
	 ExtentTestManager.getTest().log(Status.PASS, "Logged in as a Zone user.");
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value1 = testdata.get("AssetCategory").toString();
	        String value2 = testdata.get("SMACategory").toString();
	        String value3 = testdata.get("Region").toString();
	        String value4 = testdata.get("Branch").toString();
	        String value5 = testdata.get("OsBalanceOperator").toString(); 
	        String value6 = testdata.get("OsBalanceAmount").toString();
	        String value7 = testdata.get("AllocateTo").toString();
	        
	        List<Object> results = bankallocationsummary.createBranchUser(value4);  
			 
	        // You can use the results if needed
		 userid = (String) results.get(0);
	     password = (String) results.get(1);
	     String statusMessage = (String) results.get(2);
	     ExtentTestManager.getTest().log(Status.PASS, "The stored procedure 'SP_PICK_EXISTING_CORE_BRANCH_USER' was executed successfully to get a branch user."+statusMessage);
	     
	     List<Object> status = bankallocationsummary.deleteZoneUserToBranchUserAccountAllocation(value4,value7,userid);
	     ExtentTestManager.getTest().log(Status.PASS, "Status: " + status.toString());
	     
	     
	     List<Object> status2 = bankallocationsummary.insertBCOUser(userid);   
	     ExtentTestManager.getTest().log(Status.PASS, "Status: " + status2.toString());
	 
	        bankallocationsummary.selectSmaTile();
	        ExtentTestManager.getTest().log(Status.PASS, "Navigated to My Desk.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected the SMA tile from Unassigned Accounts.");
	        bankallocationsummary.selectAssetCategory(value1);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Asset Category.");
	        bankallocationsummary.selectSmaCategory(value2);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected SMA Category.");
	        bankallocationsummary.selectRegion(value3);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Region.");
	        bankallocationsummary.selectBranch(value4);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Branch.");
	        bankallocationsummary.selectDpdFinancialOperation(value5,value6);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Financial values.");
	        bankallocationsummary.clickSearch();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button.");
	        bankallocationsummary.selectAllocateToBranch(value7);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected a Branch from the \"Allocate To\" dropdown.");
	 }
	 		bankallocationsummary.clickAllocate();
	 		ExtentTestManager.getTest().log(Status.PASS, "Clicked the Allocate button.");
        // Assert the confirmation message is displayed
	 		Assert.assertTrue(bankallocationsummary.isConfirmationMessageDisplayed());
	 		ExtentTestManager.getTest().log(Status.PASS, "Accounts allocated successfully, confirmation message displayed");
		}
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
           throw e;
	 }
		Thread.sleep(3000);
    }
	
	 @Test(priority = 2) 
	    public void Verify_branch_user_able_to_Login_with_Valid_Credentials() throws Exception{  
		 try {
		 Login_Class.CoreLoginWithInputs(userid,password);  
		 driver = baseclass.getDriver();
		 drivers.add(driver);
		 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in using valid branch user credentials.");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		// Assertion for successful login
	        String expectedURL = "http://192.168.32.33:8599/Home";
	        Assert.assertEquals( driver.getCurrentUrl(),expectedURL);
	        ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in, and the user was navigated to the dashboard.");
	        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.username));
			 userName = username.getText(); 
			 WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
			 userId = userid.getText();
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void Successful_Account_Allocation_to_My_accounts_in_my_desk() throws InterruptedException { 
		 try {
		 bankallocationsummary.navigateToMyDesk();
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to my desk section.");
		 bankallocationsummary.selectSmaFromBranchAttention();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected SMA from the 'Branch Attention Required' section.");
		 bankallocationsummary.selectAllAccountsFromFirstPage(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Selected all accounts from the first page.");
		 account_numbers_count=bankallocationsummary.getAccountNumbersFromGrid();
		 bankallocationsummary.assignToBranch(userName);
		 ExtentTestManager.getTest().log(Status.PASS, "Chose 'Branch' from the 'Assign/Re-assign to' dropdown.");
		 bankallocationsummary.clickAssign();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Assign' button.");
	        Assert.assertTrue(bankallocationsummary.isConfirmationDisplayed(), "Accounts not Assigned successfully.");
	        ExtentTestManager.getTest().log(Status.PASS, "Accounts were Assigned successfully, and a confirmation message was displayed."); 
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4) 
	    public void Validate_Data_Execution_for_Bank_Allocation_Summary_in_database() throws IOException, InterruptedException { 
		 try {
		 String statusMessage = bankallocationsummary.insertAccountsForUser(userId); 
		 ExtentTestManager.getTest().log(Status.PASS, statusMessage); 

		    String expectedMessage = "Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=1 and MOVEMENT_TYPE_CATEGORY=NEW";
		    
		    Assert.assertTrue(statusMessage.equalsIgnoreCase(expectedMessage),
		            "Test failed - Expected message: '" + expectedMessage + "', but got: '" + expectedMessage + "'");
		    ExtentTestManager.getTest().log(Status.PASS, "Accounts were successfully inserted into the TRN_AC_MOVEMENT table with MOVEMENT_TYPE = '1' and MOVEMENT_TYPE_CATEGORY = 'NEW'.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void Verify_Accounts_in_Todays_Branch_Allocation_Summary(Map<Object, Object> testdata) throws Exception {  
		 try {
		 Login_Class.CoreLogin(); 
		 driver = baseclass.getDriver();
		 bankallocationsummary = new BankAllocationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 
		 bankallocationsummary.openAllocationSummaryWindow(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Branch Allocation Summary window.");
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Region").toString();
		        String value2 = testdata.get("Branch").toString();
		        
		        bankallocationsummary.fillMandatoryFields(value1, value2, TodaysDate, TodaysDate);  
	        
		 }
		 bankallocationsummary.clickSearchButton();
	        
	        // Verifying that today's allocations are displayed correctly
	        Assert.assertTrue(bankallocationsummary.areTodaysAllocationsDisplayed(account_numbers_count), "Today's allocated accounts are not displayed correctly.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected the branch and verified that today's allocated accounts were listed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }  
	 
	 @Test(priority = 6, dataProvider = "TestData")
	    public void Verify_Accounts_in_Yesterdays_date(Map<Object, Object> testdata) throws IOException, InterruptedException { 
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) { 
		    	
		        String value1 = testdata.get("AllocateTo").toString();
		        String value2 = testdata.get("Branch").toString();
	        
		        String statusMessage = bankallocationsummary.updateAllocatedDate(value2,value1,userId);  
		        
		        ExtentTestManager.getTest().log(Status.PASS, statusMessage);	
		        
		        Assert.assertTrue(statusMessage.equalsIgnoreCase("Update successful."), 
		                "Test failed - Expected message 'Update successful.' but got: " + statusMessage); 
		        ExtentTestManager.getTest().log(Status.PASS, statusMessage);
		        
		        String statusMessage2 = bankallocationsummary.runBranchAllocDashboard();  	        
		        
		        Assert.assertTrue(statusMessage2.equalsIgnoreCase("Success"), 
		                "Test failed - Expected message 'Success' but got: " + statusMessage2);
		        ExtentTestManager.getTest().log(Status.PASS, statusMessage2);      
		 }
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
		 
	    }
	 
	 @Test(priority = 7)
	    public void Verify_the_tables_DW_BRANCH_ALLOCATIONS_SUMMARY_and_DW_BRANCH_ALLOCATIONS_DASHBOARD_are_updated_after_the_package_execution() throws IOException, InterruptedException {
		 try {
	        // Step 1: Execute the stored procedure
		 List<Object> statusMessage = bankallocationsummary.checkDashboardDataLoadExecution(userId);  

	        // Step 2: Check the outputs
	        String resultSummary = (String) statusMessage.get(0);
	        String resultDashboard = (String) statusMessage.get(1); 

	        // Expected Results
	        Assert.assertEquals(resultSummary, "Yes", "DW_BRANCH_ALLOCATIONS_SUMMARY table entry check failed");
	        ExtentTestManager.getTest().log(Status.PASS, "DW_BRANCH_ALLOCATIONS_SUMMARY table entry : " +resultSummary);
	        Assert.assertEquals(resultDashboard, "Yes", "DW_BRANCH_ALLOCATIONS_DASHBOARD table entry check failed");
	        ExtentTestManager.getTest().log(Status.PASS, "DW_BRANCH_ALLOCATIONS_DASHBOARD table entry : " +resultDashboard);
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 
	 
	 @Test(priority = 8, dataProvider = "TestData")
	    public void Verify_Grid_Details_Accuracy(Map<Object, Object> testdata ) throws InterruptedException {  
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		        
		        bankallocationsummary.selectyesterdaysdate(YesterdaysDate, YesterdaysDate);   
		        ExtentTestManager.getTest().log(Status.PASS, "Selected date from date picker : "+YesterdaysDate);  
		 }
		 bankallocationsummary.clickSearchButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on Search button.");
	        // Verifying that today's allocations are displayed correctly
	        Assert.assertTrue(bankallocationsummary.areYesterdaysAllocationsDisplayed(account_numbers_count), "Yesterday's's allocated accounts are not displayed correctly.");
	        ExtentTestManager.getTest().log(Status.PASS, "Accounts were displayed properly");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9)
	    public void Verify_Download_Functionality_as_Excel() throws InterruptedException { 
		 try {
		 bankallocationsummary.downloadAsExcel(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on 'Download as Excel'");  
	     Assert.assertTrue(bankallocationsummary.isExcelvaluesMatchingWithUI(), "Excel file is not matching with UI displayed values");
	     ExtentTestManager.getTest().log(Status.PASS, "Opened the downloaded Excel file and found the details as per in grid.");
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
		 ExcelReader = new com.Utility.ExcelReader("CoreBankAllocationSummary");
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
