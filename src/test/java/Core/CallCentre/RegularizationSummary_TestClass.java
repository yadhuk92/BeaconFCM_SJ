package Core.CallCentre;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.CoreRegularizationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.testautomation.pages.CoreRegularizationSummaryPage;
import com.listeners.TestListener;

public class RegularizationSummary_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	Login_Class callcenterlogin;
	CoreRegularizationSummaryPage coreregularizationsummarypage;
	
	@BeforeSuite

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		corelogin.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coreregularizationsummarypage = new CoreRegularizationSummaryPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Core_Regularization_Summary");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		baseclass = new Base_Class();
	    driver = baseclass.getDriver();
	    drivers.add(driver);
	    coreregularizationsummarypage = new CoreRegularizationSummaryPage(driver);
	    callcenterlogin = new Login_Class();
	    // Update the ScreenShot object with the new driver
	    screenShot = new com.Utility.ScreenShot(driver);
    // Start a new ExtentTest for the current test method
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core Regularization Summary");
        Log.info("****" + method.getName() + "****");
    }

	@Test(priority = 1)
	  public void  Login_and_Navigation_to_Manual_Allocation() throws Exception {
			try {
			
			
	      Assert.assertTrue(coreregularizationsummarypage.isManualAllocationPageLoaded(), "Manual Allocation page not loaded correctly.");
	      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Call Centre menu.");
	      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Manual Allocation submenu successfully.");
	      ExtentTestManager.getTest().log(Status.PASS, "Manual Allocation page is displayed with URL ending in `CallCentre/ManualAllocationConfiguration`");
			} 
			catch (AssertionError | Exception e) { 
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		Thread.sleep(3000); 
	  }
	
	 @Test(priority = 2)
	    public void Asset_Category_SMA_and_NPA_Selection() throws InterruptedException {
	    	try {
	        // Test case for asset category selection
	    		coreregularizationsummarypage.selectAssetCategory(); 
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'NPA' and 'SMA' category from the Asset Category dropdown.");
	    	 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void SMA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	        // Test case for SMA category dropdown selection
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the SMA category dropdown.");
	    		coreregularizationsummarypage.selectSmaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'SMA 0', 'SMA 1', and 'SMA 2'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4)
	    public void NPA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the NPA category dropdown.");
	        // Test case for NPA category dropdown selection
	    		coreregularizationsummarypage.selectNpaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'Sub-standard', 'Doubtful-1', 'Doubtful-2', 'Doubtful-3', and 'Loss asset'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void OS_Balance_Field_Validation(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("OutstandingBalanceOperator").toString();
		        String value2 = testdata.get("OutstandingBalance").toString();
		        coreregularizationsummarypage.clickOsBalanceField();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the O/S Balance field.");
	    	coreregularizationsummarypage.selectEqualFinancialOperator(value1);
	    	coreregularizationsummarypage.enterOsBalance(value2);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected '=' from the 'Select Financial Operator' dropdown, and entered '1234' in the O/S Balance field.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6, dataProvider = "TestData")
	    public void To_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("To").toString();
		        coreregularizationsummarypage.selectCallCentreFromToDropdown(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Search_with_Mandatory_Fields() throws Throwable { 
	    	try {
	    		String truncateQuery = "TRUNCATE TABLE mst_callcentre_accounts";
	    	      String truncateResult = DBUtils.executeSQLStatement(truncateQuery);  // Call the method from DBUtils
	    	      System.out.println(truncateResult);
	    		coreregularizationsummarypage.clickSearchBtn();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
         WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
	    	 // Verify if the text for "Total account selected" is displayed and is not empty
	    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
	    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
	    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
         
	    }
	 
	 @Test(priority = 8, dataProvider = "TestData")
	    public void Allocate_to_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value = testdata.get("AllocateTo").toString();
		        coreregularizationsummarypage.selectCallCentreFromAllocateToDropdown(value);
	    	ExtentTestManager.getTest().log(Status.PASS, "Successfully selected 'Call Centre' from the 'Allocate To' dropdown.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 9, dataProvider = "TestData")
	    public void Select_Call_Centre_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    		String value = testdata.get("SelectCallCentre").toString();
	        // Select "CallCentre 1" from dropdown
	    		coreregularizationsummarypage.selectCallCentre(value);
	    		ExtentTestManager.getTest().log(Status.PASS, "Successfully selected 'CallCentre 1' from the 'Select Call Centre' dropdown.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	       
	    }
	    
	    @Test(priority = 10)
	    public void Assign_Button_Validation_Message() throws InterruptedException {
	    	try {
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        // Click the assign button
	    		coreregularizationsummarypage.clickAssignButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked the 'Assign' button.");
	        // Assert the validation message is "Assigned successfully"
	        Assert.assertEquals(coreregularizationsummarypage.getValidationMessage(), "Assigned Successfully",
	                "Validation message should be 'Assigned Successfully' after allocation.");
	        ExtentTestManager.getTest().log(Status.PASS, "Validation message 'Assigned successfully' appears after allocation.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.validationMessage));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 11)
	    public void Create_entry_in_table_database() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
	    	try {
	    		System.out.println("Debug 1");
	        // Update assignment date
	    	String updateResult = coreregularizationsummarypage.updateAssignmentDate(); 
	    	ExtentTestManager.getTest().log(Status.PASS, updateResult);
	    	//System.out.println(updateResult);
	    	
	    	// Create entry for yesterday
	    	String result = coreregularizationsummarypage.createEntryForYesterdayandtoday();
	    	ExtentTestManager.getTest().log(Status.PASS, result);
	    	//System.out.println(result);

	     // Run the package and capture the result
	        String packageExecutionResult = coreregularizationsummarypage.runPackage();
	        ExtentTestManager.getTest().log(Status.PASS, packageExecutionResult);
	        //System.out.println(packageExecutionResult);
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	  //Test to verify 'Call Centre' listing in side menu
	    @Test(priority = 12)
	    public void Side_Menu_Call_Centre_Listing() throws InterruptedException { 
	    	try {
	        boolean isListed = coreregularizationsummarypage.isCallCentreListed();
	        // Assertion to validate 'Call Centre' is listed
	        Assert.assertTrue(isListed, "'Call Centre' should be listed in the side menu.");
	        ExtentTestManager.getTest().log(Status.PASS, "Successfully navigated to the side menu.");
	        ExtentTestManager.getTest().log(Status.PASS, "'Call Centre' is listed in the side menu");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	 // Test method to verify the Regularization Summary is listed
	    @Test(priority = 13)
	    public void Sub_Menu_Regularization_Summary_Listing() throws InterruptedException {
	    	try {
	        // Assert to check if Regularization Summary is displayed in the sub menu
	        Assert.assertTrue(coreregularizationsummarypage.isRegularizationSummaryDisplayed(), "'Regularization Summary' should be listed in the sub menu.");
	        ExtentTestManager.getTest().log(Status.PASS, "Successfully navigated to the sub-menu under 'Call Centre'.");
	        ExtentTestManager.getTest().log(Status.PASS, "'Regularization Summary' is listed in the sub menu");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 14)
	    public void Click_Regularization_Summary_() throws InterruptedException {
	    	try {
	        // Click the Regularization Summary link
	    	coreregularizationsummarypage.clickRegularizationSummary();
	    	ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked on 'Regularization Summary' in the sub-menu.");
	        // Assert that the page title is as expected
	    	String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("CallCentre/RegularizationSummary"), "Not navigated to Regularization Summary page.");
	        ExtentTestManager.getTest().log(Status.PASS, "Regularization Summary page is displayed");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	 // Test to verify the presence of Call Centre Dropdown and Search Button
	    @Test(priority = 15)
	    public void Regularization_Summary_Page_Dropdown_and_Search() throws InterruptedException {
	    	try {
	    		
	    		ExtentTestManager.getTest().log(Status.PASS, "Observed the fields displayed on the page.");
	        Assert.assertTrue(coreregularizationsummarypage.isCallCentreDropdownDisplayed(), "Call Centre Dropdown is not displayed");
	        Assert.assertTrue(coreregularizationsummarypage.isSearchButtonDisplayed(), "Search Button is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Dropdown  call centre  name with 'Search' button is displayed");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 16)
	    public void Click_Search_Without_Selecting_Dropdown() throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        // Click the Search button without selecting any dropdown option
	    	ExtentTestManager.getTest().log(Status.PASS, "Did not select any option in the dropdown");
	    	coreregularizationsummarypage.clickSearchButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Search' button.");
	        // Validate the expected validation message
	        String actualMessage = coreregularizationsummarypage.getValidationMessageText();
	        String expectedMessage = "Please Select CallCentre";
	        Assert.assertEquals(actualMessage, expectedMessage, "Validation message did not match");
	        ExtentTestManager.getTest().log(Status.PASS, "Validation message prompts to select call centre name");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreRegularizationSummaryRepo.validationMessage));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 17, dataProvider = "TestData")
	    public void Dropdown_Callcentre_Name(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    		String value = testdata.get("SelectCallCentre").toString();
	    		// Click the Callcentre Name dropdown
	    		coreregularizationsummarypage.clickCallCentreDropdown();
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked the dropdown on the Regularization Summary page.");
	            // Assert that the dropdown is displayed
	            Assert.assertTrue(coreregularizationsummarypage.isCallCentreDropdownvalueDisplayed(value),
	                "CallCentre 1 is not displayed in the dropdown.");
	            ExtentTestManager.getTest().log(Status.PASS, "Callcentre name(s) is displayed in the dropdown");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    	
	    }
	    
	 // Test method to verify the regularization summary report is displayed
	    @Test(priority = 18)
	    public void Search_Regularization_Summary() throws InterruptedException { 
	    	
	    	try {
	        // Click search button
	    		ExtentTestManager.getTest().log(Status.PASS, "Selected a call centre name from the dropdown");	
	    	coreregularizationsummarypage.clickSearchButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "clicked the 'Search' button.");
	        // Assert that the summary is displayed
	        Assert.assertTrue(coreregularizationsummarypage.isSummaryDisplayed(), "The regularization summary report is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Regularization Summary report is displayed with Account Category, Received, and Regularized columns.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 19)
	    public void Download_the_regularization_summary_report() throws InterruptedException {
	    	try {
	    	coreregularizationsummarypage.clickDownloadIcon(); 
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the download icon against the Regularization Summary title.");
	    	ExtentTestManager.getTest().log(Status.PASS, "File with Account Category,Received,regularized data is downloaded.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 20)
	    public void Data_Verification_in_Downloaded_File_() throws IOException, InterruptedException {
	    	try {
	    	// Get actual values from the downloaded file
	        List<String> actualTotalAccountsReceived = DownloadedExcelReader.getTotalACReceivedCount();
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the downloaded file");
	        ExtentTestManager.getTest().log(Status.INFO,"'Total Account Received' from the downloaded file - " +actualTotalAccountsReceived+" ");
	    	// Get expected values from the grid
	    	List<String> expectedTotalAccountsReceived = coreregularizationsummarypage.getGridTotalAccountsReceived(); 
	    	ExtentTestManager.getTest().log(Status.PASS, "Verified the data with the grid data.");
	    	ExtentTestManager.getTest().log(Status.INFO,"'Received' count from the grid - " +expectedTotalAccountsReceived+" ");
	    	
	        // Validate the data
	        Assert.assertEquals(actualTotalAccountsReceived, expectedTotalAccountsReceived, "Total account received does not match with grid data.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the 'Total Account Received' in the downloaded file"+actualTotalAccountsReceived+" matches the 'Received' count in the grid."+expectedTotalAccountsReceived+"");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid. " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 21)
	    public void Download_the_regularization_summary_report_() throws InterruptedException {
	    	try {
	    	coreregularizationsummarypage.clickDownloadIconfromAccountCategory();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the download icon against an account category.");
	    	ExtentTestManager.getTest().log(Status.PASS, "File is downloaded for the account categoryselected. ");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);

	    }
	    
	    @Test(priority = 22)
	    public void Validate_Downloaded_File_() throws IOException, InterruptedException {
	    	try {
	        // Read downloaded file content
	        int fileContent = DownloadedExcelReader.getTotalAccountNumberCount();
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the downloaded file");
	        ExtentTestManager.getTest().log(Status.INFO,"'Total Account Received' from the downloaded file - " +fileContent+" ");
	        // Assume getGridData() returns the grid data
	        List<WebElement> elements = driver.findElements(CoreRegularizationSummaryRepo.TOTAL_AC_RECEIVEDcount);
	        String  gridData = elements.get(0).getText();
	        ExtentTestManager.getTest().log(Status.INFO,"'Received' count from the grid - " +gridData+" ");
	        ExtentTestManager.getTest().log(Status.PASS, "Compared the data with the grid data.");
	        // Verify file content with grid data
	        Assert.assertTrue(coreregularizationsummarypage.verifyFileDataWithGridData(fileContent, gridData), 
	                          "Downloaded file data does not match grid data.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the 'Total Account Received' in the downloaded file"+fileContent+" matches the 'Received' count in the grid" +gridData+" ");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid." + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 23)
	    public void Login_to_call_centre_application() throws Exception {
	    	try {
	  		callcenterlogin.CallCenterLogin();
	  		driver = baseclass.getDriver(); // Update the driver after CoreLogin
	  		coreregularizationsummarypage = new CoreRegularizationSummaryPage(driver);
	  		ExtentTestManager.getTest().log(Status.PASS, "Successfully opened the FCM Call Centre application and logged in with valid credentials");
	  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	  		coreregularizationsummarypage.navigateToMainMenu();
	  		ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Main Menu on the dashboard");
	  		coreregularizationsummarypage.clickRegularizationSumary();
	  		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Regularization Summary sub-menu.");
	        // Verify that the regularization summary report is displayed
	        Assert.assertTrue(coreregularizationsummarypage.isRegularizationSumaryDisplayed(), "Regularization summary report is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "The Regularization Summary report was successfully displayed, showing the account category along with the 'Received' and 'Regularized' columns.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid." + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 24)
	    public void Click_Regularization_Summary() throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        // Preconditions: Ensure regularization summary is displayed
	    	coreregularizationsummarypage.clickDownlodIcon(); // Step 1: Click on the download icon
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the download icon against the Regularization Summary title.");
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(CoreRegularizationSummaryRepo.downloadedMessage));
	    	ExtentTestManager.getTest().log(Status.PASS, "File with Account Category,Received,regularized column data is downloaded.");
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreRegularizationSummaryRepo.downloadedMessage));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid." + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 25)
	    public void Data_Verification_in_Downloaded_File() throws IOException, InterruptedException {
	    	try {
	    	// Get actual values from the downloaded file
	        List<String> actualTotalAccountsReceived = DownloadedExcelReader.getTotalACReceivedCount();
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the downloaded file");
	        ExtentTestManager.getTest().log(Status.INFO,"'Total Account Received' from the downloaded file - " +actualTotalAccountsReceived+" ");
	    	// Get expected values from the grid
	    	List<String> expectedTotalAccountsReceived = coreregularizationsummarypage.getGridTotalAccountsReceived();
	    	ExtentTestManager.getTest().log(Status.PASS, "Verified the data with the grid data.");
	    	ExtentTestManager.getTest().log(Status.INFO,"'Received' count from the grid - " +expectedTotalAccountsReceived+" ");
	    	
	        // Validate the data
	        Assert.assertEquals(actualTotalAccountsReceived, expectedTotalAccountsReceived, "Total account received does not match with grid data.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the 'Total Account Received' in the downloaded file"+actualTotalAccountsReceived+" matches the 'Received' count in the grid."+expectedTotalAccountsReceived+"");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid. " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 26)
	    public void Download_regularization_Summary() throws InterruptedException {
	    	try {
	    	coreregularizationsummarypage.clickDownloadIconfromAccountCategory();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the download icon against an account category.");
	    	ExtentTestManager.getTest().log(Status.PASS, "File is downloaded for the account categoryselected. ");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);

	    }
	    
	    @Test(priority = 27)
	    public void Validate_Downloaded_File() throws IOException, InterruptedException {
	    	try {
	        // Read downloaded file content
	        int fileContent = DownloadedExcelReader.getTotalAccountNumberCount();
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the downloaded file");
	        ExtentTestManager.getTest().log(Status.INFO,"'Total Account Received' from the downloaded file - " +fileContent+" ");
	        // Assume getGridData() returns the grid data
	        List<WebElement> elements = driver.findElements(CoreRegularizationSummaryRepo.TOTAL_AC_RECEIVEDcount);
	        String  gridData = elements.get(0).getText();
	        ExtentTestManager.getTest().log(Status.INFO,"'Received' count from the grid - " +gridData+" ");
	        ExtentTestManager.getTest().log(Status.PASS, "Compared the data with the grid data.");
	        // Verify file content with grid data
	        Assert.assertTrue(coreregularizationsummarypage.verifyFileDataWithGridData(fileContent, gridData), 
	                          "Downloaded file data does not match grid data.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the 'Total Account Received' in the downloaded file"+fileContent+" matches the 'Received' count in the grid" +gridData+" ");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: 'Total Account Received' from the downloaded file not matching with the 'Received' count in the grid." + e.getMessage());
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