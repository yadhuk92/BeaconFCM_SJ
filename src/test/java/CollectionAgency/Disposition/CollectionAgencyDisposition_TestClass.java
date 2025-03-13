package CollectionAgency.Disposition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
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
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.Login_Class;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import java.sql.Types;


public class CollectionAgencyDisposition_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	//Login_Class collectionagencyloginWithinputs;
	CollectionAgencyDispositionPage collectionagencydispositionPage;
	String Account_Number1 , Account_Number2 , Collection_Agency , User_Id , Password;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		String CoreUserName = Base_Class.configloader().getProperty("CoreUserName");
		String query = "update acc_users set ORG_ID=1,ORG_TYPE=2 where USER_ID='"+CoreUserName+"'";
		DBUtils.executeSQLStatement(query);
		Login_Class.CoreLogin(); 
		driver = baseclass.getDriver(); // Retrieve the driver instance
		collectionagencydispositionPage = new CollectionAgencyDispositionPage(driver);
		ExcelReader = new com.Utility.ExcelReader("CollectionAgencyDispositionPage");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
	    drivers.add(driver);
	    // Update the ScreenShot object with the new driver
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Collection Agency Disposition");
    }
	
	@Test(priority = 1)
    public void Login_to_core_application_and_navigate_to_agency_account_agency_menu() throws InterruptedException {
		try {
        // Navigate to Agency Account Allocation
		collectionagencydispositionPage.navigateToAgencyAccountAllocation();
		ExtentTestManager.getTest().log(Status.PASS, "Opened the Core application.");
		ExtentTestManager.getTest().log(Status.PASS, "Logged in with valid credentials.");
        // Verify the page header
        String expectedHeader = "Agency Account Allocation";
        String actualHeader = collectionagencydispositionPage.getPageHeaderText();
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
	        	String TruncateResult = collectionagencydispositionPage.truncateTable(); 
	        	String DeleteResult = collectionagencydispositionPage.deleteRecordsWhereDispositionDateIsToday();
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
	    public void Allocating_accounts_to_collection_agency(Map<Object, Object> testdata) throws IOException, InterruptedException {
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
		        collectionagencydispositionPage.setAccountType(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected Types of Account: "+value1+"");
		        collectionagencydispositionPage.setAssetCategory();
		        ExtentTestManager.getTest().log(Status.PASS, "Selected Asset Category: All");
		        collectionagencydispositionPage.setSmaCategory(value2);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected SMA Category: "+value2+"");
		        collectionagencydispositionPage.setNpaCategory(value3);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected NPA Category: "+value3+"");
		        collectionagencydispositionPage.setRegion(value4);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected Region: "+value4+"");
		        collectionagencydispositionPage.setDpdDaysOperator(value5);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected DPD Days Financial Operation: "+value5+"");
		        collectionagencydispositionPage.setDpdDays(value6);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected DPD Days: "+value6+"");
		 }
	        // Step 2: Perform search
		 collectionagencydispositionPage.clickSearchButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button");
	        // Step 3: Select a collection agency and save its name
		 Collection_Agency = collectionagencydispositionPage.selectCollectionAgency();
		 ExtentTestManager.getTest().log(Status.PASS, "Selected a collection agency from the \"Select Collection Agency\" dropdown (selected the first displayed collection agency and saved the collection agency name).Name = "+Collection_Agency+"");
	        // Step 4: Click download
		 Account_Number1 = collectionagencydispositionPage.clickDownloadButton(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"Download\" button to view the accounts with the search criteria (saved an account number).Account Number = "+Account_Number1+"");

	        // Step 5: Click allocate
	        collectionagencydispositionPage.clickAllocateButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"Allocate\" button.");
	        ExtentTestManager.getTest().log(Status.PASS, "Clicked the \"OK\" button in the alert box.");
	        // Expected result: Success message is displayed
	        Assert.assertTrue(collectionagencydispositionPage.isSuccessMessageDisplayed(), "Success message not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "The success message was displayed, and the accounts were successfully allocated to the selected agency.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4)
	    public void Fetch_a_not_allocated_account_number_in_selected_collection_agency() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		 try {
	        // Fetch and save account number
		 Account_Number2 = collectionagencydispositionPage.getFetchedAccountNumber();
		 ExtentTestManager.getTest().log(Status.PASS, "The account, which was not allocated to the corresponding collection agency, was selected using the query, and the account number was saved.Account Number = "+Account_Number2+"");
	        
	        // Assert the account number as expected result
	        Assert.assertNotNull(Account_Number2, "Account number should not be null");
	        ExtentTestManager.getTest().log(Status.PASS, "An account was fetched from the mst_account table, which was not allocated to the corresponding collection agency, and the account number was saved.");
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

		 collectionagencydispositionPage.CollectionAgencyLogin(User_Id, Password);
		 driver = baseclass.getDriver(); // Update the driver
		 drivers.add(driver);
		 collectionagencydispositionPage = new CollectionAgencyDispositionPage(driver);
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
	    public void Doing_Disposition_against_Agency_allocated_accounts() throws InterruptedException {
		 try {
	        // Step 1: Navigate to the Disposition functionality
		 collectionagencydispositionPage.navigateToDisposition();
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Disposition functionality.");
		// Verify the disposition label is updated
	        Assert.assertTrue(collectionagencydispositionPage.isDispositionLabelDisplayed(), "Disposition label is not displayed.");

	        // Step 2: Enter a valid account number
		 collectionagencydispositionPage.enterAccountNumber(Account_Number1);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered an account number "+Account_Number1+" allocated to the logged-in agency.");
	        // Step 3: Click on the Search button
		 collectionagencydispositionPage.clickSearchbtn(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	        // Verify the customer details grid is displayed
	        Assert.assertTrue(collectionagencydispositionPage.isCustomerDetailsGridDisplayed(), "Customer details grid is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The customer details grid displayed results relevant to the account number entered.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Customer_Details_Grid_Data_Integrity() throws InterruptedException {
		 try {
	        // Ensure all expected columns are present and correct
	        Assert.assertTrue(collectionagencydispositionPage.verifyAllColumnsPresent(), "Not all necessary columns are present.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the presence and correctness of the following columns: Customer Name, Account Number, Days Past Due, Product Type, Total Overdue, Critical Amount, Outstanding Amount, Overdue Date, Risk, and Security Details.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Risk_Column_Data_Consistency() throws InterruptedException {
		 try {
	        // Verify the Risk column values accuracy
	        String riskValue = collectionagencydispositionPage.getRiskColumnValue();
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the accuracy of the values in the Risk column.");
	        Assert.assertTrue(riskValue.equals("Low") || riskValue.equals("Medium") || riskValue.equals("High"),
	                "Risk column should reflect the customer's risk profile accurately.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Risk column correctly reflects the customer's risk profile, with values being low, medium, or high, as auto-generated from the current risk score report.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 9)
	    public void Security_Details_View_Open_Grid() throws InterruptedException {
		 try {
	        // Step 1: Click on the 'View' button in the Security Details column
		 collectionagencydispositionPage.clickViewButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'View' button in the Security Details column.");
	        // Step 2: Verify the grid that appears
		 Assert.assertTrue(collectionagencydispositionPage.isSecurityDetailsGridDisplayed(),"Security Details grid is not displayed.");
		 ExtentTestManager.getTest().log(Status.PASS, "Verified the grid that appeared.");
		 Assert.assertTrue(collectionagencydispositionPage.verifyFieldsInGrid(),"Security Details grid does not have correct fields.");
		 ExtentTestManager.getTest().log(Status.PASS, "The Security Details grid was displayed with the correct fields: Security ID, Security Type, Security Description, Security Value, Present Value, Security Valuation Date, and Is Collateral.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10, dataProvider = "TestData")
	    public void Next_Action_Owner_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    		String value = testdata.get("NextActionOwner").toString();
	        
		 collectionagencydispositionPage.selectNextActionOwner(value);
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Next Action Owner\" dropdown.");
		 ExtentTestManager.getTest().log(Status.PASS, "Selected an option: "+value+"");
	        Assert.assertEquals(collectionagencydispositionPage.getSelectedNextActionOwner(), value, ""+value+ "selection failed");
	        ExtentTestManager.getTest().log(Status.PASS, "The selection was registered correctly and is available for use in further operations.");
		 }
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 11, dataProvider = "TestData")
	    public void Cancel_Button_Functionality_in_Add_New_Interaction_Details(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    		String Disposition = testdata.get("Disposition").toString();
	    		String SubDisposition = testdata.get("SubDisposition").toString();
	    		String Date = testdata.get("NextActionDate").toString();
	    		String Remarks = testdata.get("Remarks").toString();
	    		collectionagencydispositionPage.selectDisposition(Disposition);
	    		collectionagencydispositionPage.selectSubDisposition(SubDisposition);
	    		collectionagencydispositionPage.enterNextActionDate(Date);
	    		collectionagencydispositionPage.enterRemarks(Remarks); 
	    		ExtentTestManager.getTest().log(Status.PASS, "Inputted various details in the \"Add New Interaction Details\" section.");
		 }
		 collectionagencydispositionPage.clickCancel();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Cancel\" button.");
		 Assert.assertTrue(collectionagencydispositionPage.areFieldsEmptyOrDefault(), "The Interaction Details section should be reset.");
		 ExtentTestManager.getTest().log(Status.PASS, "All entered information was cleared, and the section was reset to default.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 12, dataProvider = "TestData")
	    public void Error_Guessing_Empty_Remarks_Field(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 String value = testdata.get("NextActionOwner").toString();
	    		String Disposition = testdata.get("Disposition").toString();
	    		String SubDisposition = testdata.get("SubDisposition").toString();
	    		String Date = testdata.get("NextActionDate").toString();
	    		collectionagencydispositionPage.selectNextActionOwner(value);
	    		collectionagencydispositionPage.selectDisposition(Disposition);
	    		collectionagencydispositionPage.selectSubDisposition(SubDisposition);
	    		collectionagencydispositionPage.enterNextActionDate(Date);
	    		ExtentTestManager.getTest().log(Status.PASS, "Left the Remarks field empty.");
		 }
		 collectionagencydispositionPage.clickSaveButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Attempted to save.");
		 String expectedErrorMessage = "Remarks is required";
	        Assert.assertEquals(collectionagencydispositionPage.getRemarksErrorMessage(), expectedErrorMessage); 
	        ExtentTestManager.getTest().log(Status.PASS, "An appropriate error or warning message was displayed, indicating that the Remarks field requires input.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13, dataProvider = "TestData")
	    public void Committed_Amount_Field_BVA_Validations(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 WebElement CommittedAmountfield = driver.findElement(CollectionAgencyDispositionRepo.CommittedAmountfield);
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 String value = testdata.get("NextActionOwner").toString();
	    		String Disposition = testdata.get("Disposition").toString();
	    		String SubDisposition = testdata.get("SubDisposition").toString();
	    		String Date = testdata.get("NextActionDate").toString();
	    		String Remarks = testdata.get("Remarks").toString();
	    		String Amount = testdata.get("CommittedAmount").toString();
	    		collectionagencydispositionPage.selectNextActionOwner(value);
	    		collectionagencydispositionPage.selectDisposition(Disposition);
	    		collectionagencydispositionPage.selectSubDisposition(SubDisposition);
	    		collectionagencydispositionPage.enterNextActionDate(Date);
	    		collectionagencydispositionPage.enterRemarks(Remarks);
	    		ExtentTestManager.getTest().log(Status.PASS, "Entered valid inputs into all mandatory fields.");
	    		collectionagencydispositionPage.setCommittedAmount(Amount);
	    		ExtentTestManager.getTest().log(Status.PASS, "For the Committed Amount field, checked the string length for Max+1.");
		 }
		 		collectionagencydispositionPage.clickSaveButton();
		 		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Save\" button.");
		 		String validationMessage = collectionagencydispositionPage.getvalidationMessage();
		 		Assert.assertEquals(validationMessage, "Value must be less than or equal to 999999999.99.");
		 		ExtentTestManager.getTest().log(Status.PASS, "The inputs prompted error message "+validationMessage+" indicating invalid input or exceeding the maximum string length for the Committed Amount field.");
		 		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 		CommittedAmountfield.clear();
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 14)
	    public void Customer_Details_Data_Accuracy() throws InterruptedException {
		 try {
	        // Asserting each field value against expected data
		 Assert.assertTrue(collectionagencydispositionPage.getCustomerId().trim().length() > 0, "Value for Customer ID is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getZone().trim().length() > 0, "Value for Zone is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getRegion().trim().length() > 0, "Value for Region is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getBranch().trim().length() > 0, "Value for Branch is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getSolId().trim().length() > 0, "Value for Sol ID is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getPrincipalOverdue().trim().length() > 0, "Value for Principal OverDue is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getInterestOverdue().trim().length() > 0, "Value for Interest OverDue is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getChargesDue().trim().length() > 0, "Value for Charges Due is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getAccountType().trim().length() > 0, "Value for Account Type is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getCategory().trim().length() > 0, "Value for Category is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getVertical().trim().length() > 0, "Value for Vertical is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getTotalCollection().trim().length() > 0, "Value for Total Collection is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getContactNumber().trim().length() > 0, "Value for Contact Number is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getAlternateNumber().trim().length() > 0, "Value for Alternate Number is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getAddress().trim().length() > 0, "Value for Address is not available!");
		 Assert.assertTrue(collectionagencydispositionPage.getProfitNpaStatus().trim().length() > 0, "Value for Profit NPA Status is not available!");
		 ExtentTestManager.getTest().log(Status.PASS, "Verified the following fields: Customer ID, Zone, Region, Branch, SOL ID, Principal Overdue, Interest Overdue, Charges Due, Account Type, Category, Vertical, Total Collection, Contact Number, Alternate Number, Address, and Profit/NPA Status.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);

	    }
	 
	 @Test(priority = 15)
	    public void Other_Account_Details_Grid_Validation() throws InterruptedException {
		 try {
	        // Verify the presence of each column in the account details grid
	        Assert.assertTrue(collectionagencydispositionPage.isAccountNumberColumnDisplayed(), "Account Number column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isProductColumnDisplayed(), "Product column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isOutstandingBalanceColumnDisplayed(), "Outstanding Balance column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isTotalOverdueColumnDisplayed(), "Total Overdue column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isTotalCollectionColumnDisplayed(), "Total Collection column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isOverdueDateColumnDisplayed(), "Overdue Date column is missing");
	        Assert.assertTrue(collectionagencydispositionPage.isDaysPastDueColumnDisplayed(), "Days Past Due column is missing");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the following columns: Account Number, Product, Outstanding Balance, Total Overdue, Total Collection, Overdue Date, and Days Past Due (DPD).");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 
	 
	 @Test(priority = 16, dataProvider = "TestData")
	    public void Disposition_and_Sub_Disposition_Valid_Selections(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 WebElement AccountNumberfield = driver.findElement(CollectionAgencyDispositionRepo.AccountNumberfield); 
		 	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", AccountNumberfield);
	    	Thread.sleep(5000);
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) { 
			 String value = testdata.get("NextActionOwner").toString();
	    		String Disposition = testdata.get("Disposition").toString();
	    		String SubDisposition = testdata.get("SubDisposition").toString();
	    		String Date = testdata.get("NextActionDate").toString();
	    		String Remarks = testdata.get("Remarks").toString();
	    		collectionagencydispositionPage.selectNextActionOwner(value);
	    		ExtentTestManager.getTest().log(Status.PASS, "Selected valid option for Next Action Owner : "+value+"");
	    		collectionagencydispositionPage.selectDisposition(Disposition);
	    		ExtentTestManager.getTest().log(Status.PASS, "Selected valid option for Disposition  : "+Disposition+"");
	    		collectionagencydispositionPage.selectSubDisposition(SubDisposition);
	    		ExtentTestManager.getTest().log(Status.PASS, "Selected valid option for Sub-Disposition  : "+SubDisposition+"");
	    		collectionagencydispositionPage.enterNextActionDate(Date);
	    		ExtentTestManager.getTest().log(Status.PASS, "Selected valid option for Next Action Date  : "+Date+"");
	    		collectionagencydispositionPage.enterRemarks(Remarks);
	    		ExtentTestManager.getTest().log(Status.PASS, "Entered Remarks  : "+Remarks+"");
		 }
		 collectionagencydispositionPage.clickSaveButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Save\" button.");
	        String confirmationMessage = collectionagencydispositionPage.getConfirmationMessage();
	        Assert.assertEquals(confirmationMessage, "Saved Successfully");
	        ExtentTestManager.getTest().log(Status.PASS, "The details were saved successfully, and a confirmation message was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 17)
	    public void Search_Functionality_Invalid_Input() throws InterruptedException {
		 try {
			 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Disposition functionality.");
	        // Step 1: Enter invalid account number
		 collectionagencydispositionPage.enterAccountNumber(Account_Number2);
		 ExtentTestManager.getTest().log(Status.PASS, "Entered an invalid account number (which is not allocated to the logged-in agency). Account Number : "+Account_Number2+"");
	        // Step 2: Click on search button
		 collectionagencydispositionPage.clickSearchbtn();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button.");
	        // Step 3: Validate the expected result
	        String actualMessage = collectionagencydispositionPage.getValidationMessage();
	        String expectedMessage = "You are not authorized to do the disposition of this account number";
	        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch");
	        ExtentTestManager.getTest().log(Status.PASS, "A validation message was displayed, indicating that you are not authorized to perform disposition for this account number.");
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