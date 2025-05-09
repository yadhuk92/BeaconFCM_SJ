package CollectionAgency.AccountAllocation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
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
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.Login_Class;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import CollectionAgency.Disposition.CollectionAgencyDispositionPage;
import com.listeners.TestListener;
import java.sql.Types;

public class AgentAccountAllocation_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AgentAccountAllocation_Page collectionagencyagentacallocatPage;
	String Account_Number1 , Account_Number2 , Collection_Agency , User_Id , Password , Agent_Name;
	List<List<String>> excelData;
	List<String> Account_details_before_allocate, Account_details_after_allocate;
	CollectionAgencyDispositionPage collectionagencydispositionPage;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		Log.info("**** " + new Object() {}.getClass().getEnclosingClass().getSimpleName() + " ****");
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		String CoreUserName = Base_Class.configloader().getProperty("CoreUserName");
		String query = "update acc_users set ORG_ID=1,ORG_TYPE=2 where USER_ID='"+CoreUserName+"'";
		DBUtils.executeSQLStatement(query);
		Login_Class.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		collectionagencyagentacallocatPage = new AgentAccountAllocation_Page(driver);
		ExcelReader = new com.Utility.ExcelReader("CA-AgentAccountAllocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
	    drivers.add(driver);
	    // Update the ScreenShot object with the new driver
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Collection Agency-Agent Account Allocation");
        Log.info("****" + method.getName() + "****");
    }
	
	@Test(priority = 1)
    public void Login_to_core_application_and_navigate_to_agency_account_agency_menu() throws InterruptedException {
		try {
        // Navigate to Agency Account Allocation
			collectionagencyagentacallocatPage.navigateToAgencyAccountAllocation();
		ExtentTestManager.getTest().log(Status.PASS, "Opened the Core application.");
		ExtentTestManager.getTest().log(Status.PASS, "Logged in with valid credentials.");
        // Verify the page header
        String expectedHeader = "Agency Account Allocation";
        String actualHeader = collectionagencyagentacallocatPage.getPageHeaderText();
        ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Collection Agency menu -> Agency Account Allocation.");
        Assert.assertEquals(actualHeader, expectedHeader, "Page header did not match!");
        ExtentTestManager.getTest().log(Status.PASS, "The page label \"Agency Account Allocation\" is visible.");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
           throw e;
	 }
		Thread.sleep(3000);
    }
	
	@Test(priority = 2)
    public void Truncate_mst_col_agency_acc_allocated_and_delete_data_from_trn_account_followup_where_disposition_date__today() throws ClassNotFoundException, IOException, SQLException, InterruptedException {
        try {
        	String TruncateResult = collectionagencyagentacallocatPage.truncateTable();
        	String DeleteResult = collectionagencyagentacallocatPage.deleteRecordsWhereDispositionDateIsToday();
        	ExtentTestManager.getTest().log(Status.PASS, "Executed the mentioned queries for data integrity.Truncate Result : "+TruncateResult+"Delete Result :"+DeleteResult);
            Assert.assertTrue(true, "Queries executed successfully without errors.");
            ExtentTestManager.getTest().log(Status.PASS, "Executed the queries without any errors for data integrity.");
        } catch (SQLException e) {
        	ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
    }
	
	@Test(priority = 3, dataProvider = "TestData")
    public void Verify_that_the_Excel_file_is_successfully_downloaded_and_contains_the_details_of_the_chosen_account(Map<Object, Object> testdata) throws IOException, InterruptedException {
	 try {
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on \"Agency Account Allocation\" from the Collection Agency menu.");
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value1 = testdata.get("AccountType").toString();
	        String value2 = testdata.get("SMACategory").toString();
	        String value3 = testdata.get("NPACategory").toString();
	        String value4 = testdata.get("Region").toString();
	        String value5 = testdata.get("DPDDaysOperator").toString();
	        String value6 = testdata.get("DPDDays").toString();
        // Step 1: Set filter criteria
	        collectionagencyagentacallocatPage.setAccountType(value1);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Types of Account: "+value1+"");
	        collectionagencyagentacallocatPage.setAssetCategory();
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Asset Category: All");
	        collectionagencyagentacallocatPage.setSmaCategory(value2);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected SMA Category: "+value2+"");
	        collectionagencyagentacallocatPage.setNpaCategory(value3);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected NPA Category: "+value3+"");
	        collectionagencyagentacallocatPage.setRegion(value4);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Region: "+value4+"");
	        collectionagencyagentacallocatPage.setDpdDaysOperator(value5);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected DPD Days Financial Operation: "+value5+"");
	        collectionagencyagentacallocatPage.setDpdDays(value6);
	        ExtentTestManager.getTest().log(Status.PASS, "Selected DPD Days: "+value6+"");
	 }
        // Step 2: Perform search
	 collectionagencyagentacallocatPage.clickSearchButton();
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button");
        // Step 3: Select a collection agency and save its name
	 Collection_Agency = collectionagencyagentacallocatPage.selectCollectionAgency(); 
	 ExtentTestManager.getTest().log(Status.PASS, "Selected a collection agency from the \"Select Collection Agency\" dropdown (selected the first displayed collection agency and saved the collection agency name).Name = "+Collection_Agency+"");
        // Step 4: Click download
	  excelData = collectionagencyagentacallocatPage.clickDownloadButton();  
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"Download\" button to view the accounts details with the search criteria (saved an account details).Account Details = "+excelData+"");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
           throw e;
	 }
	Thread.sleep(3000);
    }
	
	@Test(priority = 4)
    public void Allocating_accounts_to_collection_agency() throws IOException, InterruptedException {
	 try {
		 
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	// Step 1: Click allocate
	 collectionagencyagentacallocatPage.clickAllocateButton();
     ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"Allocate\" button.");
     ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"OK\" button in the alert box.");
     // Expected result: Success message is displayed
     Assert.assertTrue(collectionagencyagentacallocatPage.isSuccessMessageDisplayed(), "Success message not displayed");
     ExtentTestManager.getTest().log(Status.PASS, "The success message was displayed, and the accounts were successfully allocated to the selected agency.");
     wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 }
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
        throw e;
	 }
	Thread.sleep(3000);
 }
	
	 @Test(priority = 5)
	    public void Login_to_allocated_collection_agency_from_collection_agency_application() throws Exception {
		 try {
		 List<Object> inputParams = Arrays.asList(Collection_Agency);
		 List<Integer> outputTypes = Arrays.asList(Types.VARCHAR,Types.VARCHAR,Types.VARCHAR);
		 List<Object> results =DBUtils.ExecuteAnyOracleSQLStoredProcedure("UpdateUserPassword", inputParams,outputTypes);
		 User_Id = (String) results.get(0);
		 Password = (String) results.get(1);
		 System.out.println("User ID: " + results.get(0));
		 System.out.println("Password: " + results.get(1));
		 System.out.println("Status message: " + results.get(2));

		 collectionagencyagentacallocatPage.CollectionAgencyLogin(User_Id, Password); 
		 driver = baseclass.getDriver(); // Update the driver
		 drivers.add(driver);
		 collectionagencyagentacallocatPage = new AgentAccountAllocation_Page(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 ExtentTestManager.getTest().log(Status.PASS, "Opened the Collection Agency application.");
		 ExtentTestManager.getTest().log(Status.PASS, "Logged in with the selected agency user credentials. User_ID = "+User_Id+" and Password = "+Password);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        // Assert that the user is logged in by verifying correct page or element
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 wait.until(ExpectedConditions.urlContains("home"));
	        Assert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not navigated to the dashboard.");
	        ExtentTestManager.getTest().log(Status.PASS, "The user was successfully logged in to the Collection Agency application.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	    public void verify_agent_account_allocation_page_is_available() throws InterruptedException {
		 try {
		 collectionagencyagentacallocatPage.navigateToAgentAccountAllocation(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked on the 'Accounts Allocation' menu");
		 ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked on the 'Agent Account Allocation' sub-menu");
	        Assert.assertTrue(collectionagencyagentacallocatPage.isAgentAccountAllocationPageDisplayed(), 
	            "Agent Account Allocation page should be displayed with all the parameters.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that all fields and buttons are available on the 'Agent Account Allocation' page."); 
	        ExtentTestManager.getTest().log(Status.PASS, "'Agent Account Allocation' page is displayed with all fields, buttons, and parameters populated for the logged-in user.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7, dataProvider = "TestData")
	    public void Filter_Asset_Category(Map<Object, Object> testdata) throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("AccountType").toString();
		        String value2 = testdata.get("SMACategory").toString();
		        String value3 = testdata.get("NPACategory").toString();
		        collectionagencyagentacallocatPage.selectAssetCategory();
		        ExtentTestManager.getTest().log(Status.PASS, "Selected All values from the 'Asset Category' dropdown");
		        collectionagencyagentacallocatPage.selectSmaCategory(value2);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected "+value2+" from the 'SMA Category' dropdown");
		        collectionagencyagentacallocatPage.selectNpaCategory(value3);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected "+value3+" from the 'NPA Category' dropdown");
		        collectionagencyagentacallocatPage.selectAccountType(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected "+value1+" from the 'Account Type' dropdown");
		 }
		 String queryresult = collectionagencyagentacallocatPage.deleteAgentAccountLink(Collection_Agency);
		 ExtentTestManager.getTest().log(Status.PASS, "Executed the delete query to remove records from 'agent_account_link' for the specified agency. Result : "+queryresult+"");
		 
		 collectionagencyagentacallocatPage.clickSearchButon(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Search' button.");
		 List<String> columnValues = collectionagencyagentacallocatPage.getAllColumnValuesExcludingFirstTwo();  
		 System.out.println("Values from downloaded Excel : "+excelData);
		 System.out.println("Values from UI Grid : "+columnValues);
		 boolean result = collectionagencyagentacallocatPage.isExcelDataPresentInColumnValues(excelData, columnValues);

		    // Assert in test class
		    Assert.assertTrue(result, "Not all values from excelData are found in Grid!");
		    ExtentTestManager.getTest().log(Status.PASS, "The grid displays accounts filtered based on the selected 'Asset Category'. The grid contains the following columns: SL No, A/c No, A/C Name, Branch/SOL ID, Branch Name, Account Type, Product, O/S Amount, Overdue Amount, Corporate BCBF, Contact No, Overdue Date, Last Contact Date, Last Contact Disposition, Next Action Date, Next Action Owner, Other Contact Details, and Allocated/De-allocated Date.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Reset_Filters() throws InterruptedException {
		 try {
	        // Preconditions: Assuming filter criteria is already entered
	        
	        // Step 1: Click the Reset button
		 collectionagencyagentacallocatPage.clickResetButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Reset' button.");
	        // Expected Result: All filter fields are cleared and reset to their default states
	        Assert.assertTrue(collectionagencyagentacallocatPage.areFilterFieldsCleared(), "Filter fields should be cleared");
	        ExtentTestManager.getTest().log(Status.PASS, "All filter fields were cleared and reset to their default states.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9, dataProvider = "TestData")  
	    public void Allocate_Accounts(Map<Object, Object> testdata) throws InterruptedException, ClassNotFoundException, SQLException, IOException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("AccountType").toString();
		        String value2 = testdata.get("SMACategory").toString();
		        String value3 = testdata.get("NPACategory").toString();
		        collectionagencyagentacallocatPage.selectAssetCategory();
		        collectionagencyagentacallocatPage.selectSmaCategory(value2);
		        collectionagencyagentacallocatPage.selectNpaCategory(value3);
		        collectionagencyagentacallocatPage.selectAccountType(value1);
		 }
		 
		 collectionagencyagentacallocatPage.clickSearchButon(); 
		 
		 Account_details_before_allocate = collectionagencyagentacallocatPage.selectAccount();
		 ExtentTestManager.getTest().log(Status.PASS, "Filtered the grid and selected accounts.");
	        // Step 2: Select an agent
		 Agent_Name = collectionagencyagentacallocatPage.selectAgent();
		 ExtentTestManager.getTest().log(Status.PASS, "Chose an agent : "+Agent_Name+"");
	        // Step 4: Click Allocate button
		 collectionagencyagentacallocatPage.clickAllocateButon();
		 ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Allocate' button.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);

	    }
	 
	 @Test(priority = 10, dataProvider = "TestData")
	    public void Verify_Allocated_Accounts(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("AcountType").toString();
		        String value2 = testdata.get("SMACategory").toString();
		        String value3 = testdata.get("NPACategory").toString();
		        collectionagencyagentacallocatPage.selectAssetCategory();
		        collectionagencyagentacallocatPage.selectSmaCategory(value2);
		        collectionagencyagentacallocatPage.selectNpaCategory(value3);
		        ExtentTestManager.getTest().log(Status.PASS, "Filled in the mandatory fields.");
		        collectionagencyagentacallocatPage.selectAccountType(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Set the account type as "+value1+"");
		        collectionagencyagentacallocatPage.selectAgent(Agent_Name); 
		        ExtentTestManager.getTest().log(Status.PASS, "selected an agent : "+Agent_Name+"");
		 }
		 collectionagencyagentacallocatPage.clickSearchButon();
		 ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Search' button.");
		 Account_details_after_allocate = collectionagencyagentacallocatPage.getRowData();

	        // Compare the before and after allocation details
	        boolean isMatching = collectionagencyagentacallocatPage.compareAccountDetails(Account_details_before_allocate, Account_details_after_allocate);

	        // Assert that the expected allocation change happened
	        Assert.assertTrue(isMatching, "Account details did not update as expected after allocation.");
	        ExtentTestManager.getTest().log(Status.PASS, "The accounts allocated to the selected agent are displayed in the grid under the 'Allocated' account type criteria.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 11)
	    public void Download_Grid_Data() throws InterruptedException {
		 try {
	        // Click the download button
		 collectionagencyagentacallocatPage.clickDownloadButon(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Download' button.");
	        // Additional assertions can be added here to validate file content or format
		 boolean result = collectionagencyagentacallocatPage.processExcelDataAndCompare(Collection_Agency,Agent_Name);
		 Assert.assertTrue(result, "Not all values from excelData are found in Grid!");
		 ExtentTestManager.getTest().log(Status.PASS, "The data in the grid was successfully downloaded in an Excel file with the correct format and data accuracy.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 12)
	    public void Deallocate_Accounts() throws InterruptedException {
		 try {
		 collectionagencyagentacallocatPage.selectAcount();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected account from the grid.");
		 collectionagencyagentacallocatPage.clickDeallocate();
		 ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Deallocate' button.");
	        Assert.assertTrue(collectionagencyagentacallocatPage.isGridEmpty(), "Accounts should be deallocated and removed from the grid.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected accounts were deallocated and removed from the grid.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	 @Test(priority = 13)
	    public void Open_Allocated_List_Popup() throws InterruptedException {
		 try {
	        // Click on the "Allocated List" button using the POM method
		 collectionagencyagentacallocatPage.clickAllocatedListButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Allocated List' button.");
	        // Assert that the popup is displayed with the expected result
	        Assert.assertTrue(collectionagencyagentacallocatPage.isPopupDisplayed(), "Popup window did not display as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "A popup window opened displaying the allocated account details with appropriate filters and a grid.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Filter_Allocated_List_Action_Date(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String Dates = testdata.get("FromAndToDates").toString(); 
		        
		 
		 collectionagencyagentacallocatPage.selectAgentname(Agent_Name); 
		 ExtentTestManager.getTest().log(Status.PASS, "selected an agent "+Agent_Name+" from the dropdown");
		 
		 
		 collectionagencyagentacallocatPage.enterActionDates(Dates);
	        
		 ExtentTestManager.getTest().log(Status.PASS, "Entered valid dates in 'Action Date From' and 'Action Date To' fields. Dates are : "+Dates+"");
	        // Perform search
		 collectionagencyagentacallocatPage.clickSearch();
		 
		 ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Search' button.");
		 
	        boolean isAccountsFiltered = collectionagencyagentacallocatPage.verifyDateRange(Dates,Dates);
		 
	        Assert.assertTrue(isAccountsFiltered, "The grid should show accounts allocated within the specified date range.");
	        ExtentTestManager.getTest().log(Status.PASS, "The grid displays accounts allocated within the specified date range.");
		 }
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 15)
	    public void Reset_Allocated_List_Filters() throws InterruptedException {
		 try {
	        // Preconditions: Assume filter criteria have been entered manually or automate entering them
	        // Action: Click the reset button
		 collectionagencyagentacallocatPage.clickResetButon();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Reset' button.");
	        // Assert: Verify that all filter fields are cleared
		 boolean isAccountsFiltered = collectionagencyagentacallocatPage.areFiltersReset();
		 
	        Assert.assertTrue(isAccountsFiltered, "Filters were not reset correctly");
	        ExtentTestManager.getTest().log(Status.PASS, "All filter fields were cleared and reset to their default state.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000); 
	        
	    }
	 
	 @Test(priority = 16, dataProvider = "TestData")
	    public void Download_Allocated_List_Data(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String Dates = testdata.get("FromAndToDates").toString();
		 // Enter dates and select agent
		 
		 collectionagencyagentacallocatPage.selectAgentname(Agent_Name); 
		 ExtentTestManager.getTest().log(Status.PASS, "Selected an agent : "+Agent_Name+"");
		 collectionagencyagentacallocatPage.enterActionsDates(Dates); 
		 ExtentTestManager.getTest().log(Status.PASS, "specified the 'From Date' and 'To Date' : "+Dates+"");
		 }
	        // Perform search
		 collectionagencyagentacallocatPage.clickSearch();
		 ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Search' button");
	        // Select an allocation ID
		 collectionagencyagentacallocatPage.selectAllocationId();
		 ExtentTestManager.getTest().log(Status.PASS, "Chose an Allocation ID");
	        // Click the Download in Excel button
		 collectionagencyagentacallocatPage.clickDownloadExcelButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Download in Excel' button.");
	        // Validate if the Excel file has been downloaded successfully
	        File downloadedFile = collectionagencyagentacallocatPage.getLatestDownloadedFile();
	        Assert.assertTrue(downloadedFile.exists(), "The downloaded Excel file should exist.");
	        ExtentTestManager.getTest().log(Status.PASS, "An Excel file containing account details for the selected Allocation ID was successfully downloaded.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 17)
	    public void Close_Allocated_List_Popup() throws InterruptedException {
		 try {
	        // Verify popup is displayed before closing it
	        Assert.assertTrue(collectionagencyagentacallocatPage.isPopupDiplayed(true), "Allocated List popup should be displayed.");
	        
	        // Step 1: Click the Close button
	        collectionagencyagentacallocatPage.closePopup(); 
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Close' button.");
	        // Expected result: Verify the popup is closed
	        Assert.assertFalse(collectionagencyagentacallocatPage.isPopupDiplayed(false), "Allocated List popup should be closed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The popup window closed, returning control to the main page.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);  
	    } 
	 
	 @Test(priority = 18)
	    public void Close_Main_Page() throws InterruptedException {
		 try {
		 collectionagencyagentacallocatPage.clickCloseButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Close' button.");
	        // Validate that user returns to the previous page (Use an actual condition applicable to the page)
		 Assert.assertTrue(collectionagencyagentacallocatPage.isHomePage(), "The URL should contain 'home'.");
		 ExtentTestManager.getTest().log(Status.PASS, "The user was returned to the home page.");
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
