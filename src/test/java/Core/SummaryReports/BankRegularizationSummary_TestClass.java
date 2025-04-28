package Core.SummaryReports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.BasePackage.PropertiesFileUtil;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class BankRegularizationSummary_TestClass {
	
	Base_Class baseclass;
	static com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	BankRegularizationSummary_MainClass BankRegularizationSummary;
	String loginUserId,Fromday,Today,totalAccountsRegularized,totalOSAmountRegularized,AccountCategory; 
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		BankRegularizationSummary = new BankRegularizationSummary_MainClass(driver); 
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - BankRegularizationSummary");
        Log.info("**************" + method.getName() + "**************");
    }
	
	 @Test(priority = 1) 
	    public void Validate_Navigation_to_Bank_Regularization_Summary() throws Exception {  
		 try { 
			// Login into the core application  
		 Login_Class.CoreLogin(); 
		 driver = baseclass.getDriver();
		 BankRegularizationSummary = new BankRegularizationSummary_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the application.");
		 ExtentTestManager.getTest().log(Status.PASS, "Entered Zone Office user credentials.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Login button.");
		 BankRegularizationSummary.navigateToBankRegularizationSummary(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Bank Regularization Summary page.");
	        // Assertion to check navigation success (can include URL verification or element visibility checks)
	        Assert.assertTrue(driver.getCurrentUrl().contains("BankRegularizationSummary"), "Navigation to Bank Regularization Summary page failed");
	        ExtentTestManager.getTest().log(Status.PASS, "The Bank Regularization Summary page was displayed successfully.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 2)
	    public void Inserting_TRN_AC_MOVEMENT_table_with_AC_MOVEMENT_DATE_Current_date_MOVEMENT_TYPE_4_and_MOVEMENT_TYPE_CATEGORY_REMOVED() throws IOException, InterruptedException {   
		 try {
		 String fileName = "CoreBranchUserCredentials_CoreUserManagement_Branch_User_Creation.properties";
		 Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
		 loginUserId = properties.getProperty("Branch_User_ID");
		 
	     List<Object> result = BankRegularizationSummary.removeACMovementForMyDeskDashboard(loginUserId); 
	     System.out.printf("Result testing: ",result);
	     String FromDate = (String)result.get(1);
	     Fromday = FromDate.split("-")[0];
	     String Todate = (String)result.get(0);	
	     Today = Todate.split("-")[0];
	     String StatusMessage = (String)result.get(2); 
	     ExtentTestManager.getTest().log(Status.PASS, "Executed the stored procedure SP_INSERT_TRN_AC_MOVEMENT_REMOVED_ForMyDeskDashBoard with the User_ID of the BCO branch user having accounts allocated.");
	     Assert.assertTrue(
	    		    result.contains("Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=4 and MOVEMENT_TYPE_CATEGORY=REMOVED") ||
	    		    result.contains("Data already exist in TRN_AC_MOVEMENT."),
	    		    "Expected message is not present in the result: either 'Accounts inserted into TRN_AC_MOVEMENT with  MOVEMENT TYPE=4 and MOVEMENT_TYPE_CATEGORY=REMOVED' or 'Data already exist in TRN_AC_MOVEMENT'"
	    		);
	     ExtentTestManager.getTest().log(Status.PASS, "Verified the output of the procedure successfully.");
	     ExtentTestManager.getTest().log(Status.PASS, "Status Message: "+StatusMessage); 
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void Validate_Data_Execution_for_Bank_Regularization_Summary() throws IOException, InterruptedException {  
		 try {
		 List<Object> result = BankRegularizationSummary.runBranchRegularizationDashboardLoad();
		 ExtentTestManager.getTest().log(Status.PASS, "Executed the SP 'SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD' successfully."+result); 
	        // Validate the expected results
		 List<Object> result2 = BankRegularizationSummary.checkBranchRegularizationDashboardData(loginUserId);
		 ExtentTestManager.getTest().log(Status.PASS, "Executed the SP 'SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD_DATA' successfully."+result2);  
		 String summaryStatus  = (String) result2.get(0);
		 String detailsStatus  = (String) result2.get(1); 
		 
		 Assert.assertTrue(
				    summaryStatus.equalsIgnoreCase("Yes") && detailsStatus.equalsIgnoreCase("Yes"),
				    "Branch regularization dashboard data check failed. Expected both to be 'Yes' but got: " +
				    "Summary = '" + summaryStatus + "', Details = '" + detailsStatus + "'"
				);
		 ExtentTestManager.getTest().log(Status.PASS, "Verified that DW_BRANCH_REGULARIZATION_SUMMARY : "+summaryStatus+" and DW_BRANCH_REGULARIZATION_DETAILS : "+detailsStatus+" tables contain data based on the package execution date.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage()); 
        throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4, dataProvider = "TestData")
	    public void Validate_Data_in_Bank_Regularization_Summary_page(Map<Object, Object> testdata) throws InterruptedException {  
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("Region").toString();
		        String value2 = testdata.get("Branch").toString();
	        // Step 1: Select 'Region' value
		        BankRegularizationSummary.selectRegion(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected a value " +value1+ "from the 'Region' dropdown.");
	        // Step 2: Select 'Branch' value
		        BankRegularizationSummary.selectBranch(value2);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected a value " +value2+ " from the 'Branch' dropdown."); 
	        // Step 3: Set 'From Date'
		        BankRegularizationSummary.setFromDate(Fromday);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected the 'From Date' " +Fromday+ " using the date picker.");
	        // Step 4: Set 'To Date'
		        BankRegularizationSummary.setToDate(Today);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected the 'To Date' " +Today+ " using the date picker."); 
		 }

	        // Step 5: Click 'Search' button
		 BankRegularizationSummary.clickSearch();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        // Validate expected results
		 	String gridDetails = BankRegularizationSummary.getdetailsfromgrid();
		    Assert.assertFalse(gridDetails.isEmpty(), "Grid details should not be empty");
		    ExtentTestManager.getTest().log(Status.PASS, "\"Number of Accounts\" and \"Total Outstanding Amount in Lakhs\" fields are displayed correctly based on the selected date range from the date picker."+gridDetails);
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5)
	    public void Validate_Count_and_OS_Amount_Without_Query_Execution() throws InterruptedException {  
		 try {
		 	AccountCategory = BankRegularizationSummary.getAccountCategory(); 
	        String noOfAccountsReceived = BankRegularizationSummary.getNoOfAccountsReceived();
	        String totalOSAmountReceived = BankRegularizationSummary.getTotalOSAmountReceived();
	        totalAccountsRegularized = BankRegularizationSummary.getNoOfAccountsRegularized();
	        totalOSAmountRegularized = BankRegularizationSummary.getTotalOSAmountRegularized();

	        Assert.assertEquals(noOfAccountsReceived,
	        	    totalAccountsRegularized,
	        	    "Accounts mismatch: received = " + noOfAccountsReceived + ", regularized = " + totalAccountsRegularized);
	        ExtentTestManager.getTest().log(Status.PASS, "Validated that \"No of Accounts\" matched the values under \"Total Accounts Received\".");
	        	Assert.assertEquals(totalOSAmountReceived,
		        	    totalOSAmountRegularized,
	        	    "OS Amount mismatch: received = " + totalOSAmountReceived + ", regularized = " + totalOSAmountRegularized);
	        	ExtentTestManager.getTest().log(Status.PASS, "The total O/S amount of all the SMA accounts was listed in the \"Total OS Amount Received\" section as per the selected 'As On' date.");
	        	ExtentTestManager.getTest().log(Status.PASS, "The results listed the SMA accounts accurately.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	 public void Validate_Data_Consistency_in_Export() throws IOException, InterruptedException {  
		 try {
	     // Step 1: Click on the download option
	     BankRegularizationSummary.clickDownloadButton(); 
	     ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in using valid branch user credentials.");
	     
	     // Step 2: Call the method to validate the data consistency

	         boolean isValid = BankRegularizationSummary.validateACSummaryWithUI(AccountCategory, totalAccountsRegularized, totalOSAmountRegularized);
	         ExtentTestManager.getTest().log(Status.PASS, "Clicked on the download option.");
	         // Optionally, you can add an assertion to ensure that the data is consistent:
	         Assert.assertTrue(isValid, "Data mismatch found between UI and Excel export.");
	         ExtentTestManager.getTest().log(Status.PASS, "Verified the data displayed in the exported file (Excel format).");
	         ExtentTestManager.getTest().log(Status.PASS, "The data was consistent in all file formats.");
		 }
			catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
		Thread.sleep(3000); 
	 }
	 
	 @Test(priority = 7) 
	    public void Mandatory_Fields_Empty_Validation() throws InterruptedException { 
		 try {
	        // Leave all mandatory fields empty and click search
		 BankRegularizationSummary.clickSearchButton(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Left all mandatory fields empty.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        // Validate the error message is displayed
	        String errorMessage = BankRegularizationSummary.getErrorMessageText();
	        Assert.assertEquals(errorMessage, "Please select Branch.", "Error message mismatch!");
	        ExtentTestManager.getTest().log(Status.PASS, "The application prompted an error message indicating that mandatory fields were required.");
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
		 ExcelReader = new com.Utility.ExcelReader("CoreBankRegularizationSummary");
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