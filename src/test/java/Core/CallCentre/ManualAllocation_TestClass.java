package Core.CallCentre;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import Core.CallCentre.CoreAutoAllocationPage;
import Core.CallCentre.CoreAutoAllocationPage.ProcedureResult;
import Core.CallCentre.CoreManualAllocationPage;
import bsh.ParseException;
import com.listeners.TestListener;


public class ManualAllocation_TestClass {
	private String totalAccounts;
	private String accountNumber;
	private List<WebDriver> drivers = new ArrayList<>();
	private static String accountNumberFromExcel;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreManualAllocationPage coremanualallocationpage;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CallCenterLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Core_Manual_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		 baseclass = new Base_Class();
		    driver = baseclass.getDriver();
		    drivers.add(driver);
		    coremanualallocationpage = new CoreManualAllocationPage(driver);
		    callcenterlogin = new Login_Class();
		    // Update the ScreenShot object with the new driver
		    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre ManualAllocation");
    }
	
	@Test(priority = 1)
    public void Login_to_call_centre_application_and_take_account_filtration_page() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
			ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
        //Navigate to Call Centre Main Menu
			coremanualallocationpage.navigateToMainMenu();
		ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");
        //Click on Account Filtration sub-menu
		coremanualallocationpage.navigateToAccountFiltration();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Account Filtration sub-menu.");
        // Expected Result: User is navigated to Account Filtration page
        // URL shows CallCentre/CallCentreLeadFiltration
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("CallCentre/CallCentreLeadFiltration"), "Not navigated to Account Filtration page.");
        ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Account Filtration page, URL shows CallCentre/CallCentreLeadFiltration.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 2)
    public void Allocated_To_dropdown_mandatory_checking() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category and Allocated To fields empty.");
       //Click on the Search button
			coremanualallocationpage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
        // Verify the warning message
        String expectedMessage = "Allocated To is Required";
        Assert.assertEquals(coremanualallocationpage.getWarningMessage(), expectedMessage, "Warning message not displayed as expected");
        ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Allocated to is required\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
    }
	
	@Test(priority = 3, dataProvider = "TestData")
    public void Asset_category_dropdown_mandatory_checking(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
        
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocatedTo").toString();
	     // Select any value in Allocated To field
	        coremanualallocationpage.selectAllocatedTo(value);
	        ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category field empty and selected a value in the Allocated To field.");
	    }
	// Click Search button
	 coremanualallocationpage.clickSearchButtonaftergivingvalueforallocatedto();
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	// Verify the warning message
     Assert.assertEquals(coremanualallocationpage.getWarningMessageaftergivingvalueforallocatedto(), "Asset Category is Required");
        ExtentTestManager.getTest().log(Status.PASS, "Displays warning message  \"asset category is required\".");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000); 
        
 }
	
	@Test(priority = 4)
    public void Select_SMA_and_NPA_Categories() throws InterruptedException {
		 try {
        // Open Asset Category dropdown and selecting all values
			 coremanualallocationpage.openAssetCategoryDropdown();
		ExtentTestManager.getTest().log(Status.PASS, "Opened the Asset Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected the SMA category and NPA category.");
		ExtentTestManager.getTest().log(Status.PASS, "SMA and NPA categories are selected.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 5)
    public void SMA_Category_Selection() throws InterruptedException {
		 try {
        // Open SMA Category dropdown and selecting all values
			 coremanualallocationpage.openSMACategoryDropdown(); 
		ExtentTestManager.getTest().log(Status.PASS, "Opened the SMA Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected SMA 0, SMA 1, and SMA 2 from the dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "SMA 0, SMA 1, SMA 2 should be selected.");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 6)
    public void NPA_Category_Selection_() throws InterruptedException {
		 try {
        // Open SMA Category dropdown and selecting all values
			 coremanualallocationpage.openNPACategoryDropdown(); 
		ExtentTestManager.getTest().log(Status.PASS, "Opened the NPA Category dropdown.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected sub-standard, doubtful-1, doubtful-2, doubtful-3, and loss asset.");
		ExtentTestManager.getTest().log(Status.PASS, "Selected NPA categories sub-standard , doubtful-1 , doubtful-2,doubtful-3,loss asset should be selected from the dropdown");
	 }
        catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	 Thread.sleep(3000);
    }
	
	@Test(priority = 7, dataProvider = "TestData")
  public void Allocation_Type_Selection_Manual_allocation(Map<Object, Object> testdata) throws InterruptedException {
	 try {
      
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocationType").toString();
	        coremanualallocationpage.selectAllocationType(value); 
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the Allocation Type dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Manual Allocation.");
	    }
      ExtentTestManager.getTest().log(Status.PASS, "Manual Allocation is selected.");
	 }
      catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	 Thread.sleep(3000); 
      
}
	
	@Test(priority = 8)
  public void Perform_Search_with_Valid_Data() throws Throwable {
	try {
		// Step 1: Execute the TRUNCATE query before performing search
      String truncateQuery = "TRUNCATE TABLE mst_callcentre_accounts";
      String truncateResult = DBUtils.executeSQLStatement(truncateQuery);  // Call the method from DBUtils
      System.out.println(truncateResult); 
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		ExtentTestManager.getTest().log(Status.PASS, "Ensured that the Asset Category, SMA Category, NPA Category, and Allocated To mandatory fields are filled.");
	 WebElement downloadbutton = driver.findElement(CoreAutoAllocationRepo.downloadbutton);
  	JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].scrollIntoView(true);", downloadbutton);
      Thread.sleep(500);
      // Perform search
      coremanualallocationpage.clickSearchButton(); 
		ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button successfully.");
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 String gridText = coremanualallocationpage.getResultGridText();
   if (gridText.contains("No records to display.")) {
  	 Assert.assertEquals(gridText, "No records to display.", "Expected no records to display");
		ExtentTestManager.getTest().log(Status.PASS, "Verified that the grid displays the message \"No records to display\" when no data is available.");
   } else {
  	 System.out.println("Total accounts allocated");
  	 Assert.fail("Test failed because grid text did not contain 'No records to display'. Instead, it contains: records");
   }
	}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	Thread.sleep(3000);  
  }
//	
	@Test(priority = 9)
  public void  Login_and_Navigation_to_Manual_Allocation() throws Exception {
		try {
		callcenterlogin.CoreLogin();
		driver = baseclass.getDriver(); // Update the driver after CoreLogin
		drivers.add(driver); // Add the new driver to the list
		coremanualallocationpage = new CoreManualAllocationPage(driver);
		
		
      Assert.assertTrue(coremanualallocationpage.isManualAllocationPageLoaded(), "Manual Allocation page not loaded correctly.");
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
	
	 @Test(priority = 10) 
   public void Verify_Fields_and_Buttons_on_Manual_Allocation_Page() throws InterruptedException {
   	try {
       
       Assert.assertTrue(coremanualallocationpage.areFieldsAndButtonsPresent(), "Fields or Add button not present on Manual Allocation page.");
       ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the following fields: Allocation Name\",\"Zone/CO\",\"Region\",\"Branch\",\"Branch ID\",\"Vertical\",\"Scheme Type\",\"Product Type\",\"Scheme Code\",\"Asset Tagging Type\",\"Asset Category\",\"SMA Category\",\"NPA Category\",\"DPD\",\"O/S Balance\",\"%Overdue to EMI\",\"Action Owner\",\"Action Date From\" ,\"Action Date to \",\"Types of Account\",\"Not Allocated\",\"To\",\"Is PFTNPA\",\"Is FTNPA\",\"Save This Allocation Criteria\" , Search button and reset button and edit allocation criteria button, assign button, download in excel button , assigned list button.");
       ExtentTestManager.getTest().log(Status.PASS, "All fields and Add button are present on the page");
   	}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
           throw e;
	 }
	Thread.sleep(3000);
   }
	 
	 @Test(priority = 11)
	    public void Mandatory_Field_Validation_for_Asset_Category() throws InterruptedException {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        // Test case for mandatory field validation
		 ExtentTestManager.getTest().log(Status.PASS, "Asset Category and To fields were left blank");
		 coremanualallocationpage.clickSearchbutton(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Search button was clicked");
	        String warning = coremanualallocationpage.getWarningmessage();
	        Assert.assertEquals(warning, "Asset Category Required");
	        ExtentTestManager.getTest().log(Status.PASS, "After leaving the Asset Category and To fields blank and clicking the search button, a warning message 'Asset category is Required' appeared.");
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 12)
	    public void Asset_Category_SMA_and_NPA_Selection() throws InterruptedException {
	    	try {
	        // Test case for asset category selection
	    	coremanualallocationpage.selectAssetCategory(); 
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'NPA' and 'SMA' category from the Asset Category dropdown.");
	    	 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 13)
	    public void SMA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	        // Test case for SMA category dropdown selection
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the SMA category dropdown.");
	    	coremanualallocationpage.selectSmaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'SMA 0', 'SMA 1', and 'SMA 2'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 14)
	    public void NPA_Category_Dropdown_Selection() throws InterruptedException {
	    	try {
	    		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the NPA category dropdown.");
	        // Test case for NPA category dropdown selection
	    	coremanualallocationpage.selectNpaCategories();
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'Sub-standard', 'Doubtful-1', 'Doubtful-2', 'Doubtful-3', and 'Loss asset'.");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 15, dataProvider = "TestData")
	    public void OS_Balance_Field_Validation(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("OutstandingBalanceOperator").toString();
		        String value2 = testdata.get("OutstandingBalance").toString();
	    	coremanualallocationpage.clickOsBalanceField();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the O/S Balance field.");
	    	coremanualallocationpage.selectEqualFinancialOperator(value1);
	    	coremanualallocationpage.enterOsBalance(value2);
	    	ExtentTestManager.getTest().log(Status.PASS, "Selected '=' from the 'Select Financial Operator' dropdown, and entered '1234' in the O/S Balance field.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 16, dataProvider = "TestData")
	    public void To_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("To").toString();
		        coremanualallocationpage.selectCallCentreFromToDropdown(value1);
		        ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown.");
	    	}
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }

	    @Test(priority = 17)
	    public void Search_with_Mandatory_Fields() throws InterruptedException {
	    	try {
	    	coremanualallocationpage.clickSearchBtn();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
            WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
	    	 // Verify if the text for "Total account selected" is displayed and is not empty
	    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
	    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
	    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
	    	Assert.assertFalse(totalAccountSelected.getText().isEmpty(),"Total account selected text is empty");
            Assert.assertFalse(totalOutstandingAmount.getText().isEmpty(),"Total outstanding amount text is empty");
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
            
	    }

	    @Test(priority = 18)
	    public void Download_Excel_Functionality() throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	coremanualallocationpage.clickDownloadExcelButton();
	    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the 'Download in Excel' button.");
	    	ExtentTestManager.getTest().log(Status.PASS, "Excel file is downloaded");
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.downloadedmsg));
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	    }
	    
	    @Test(priority = 19)
	    public void Excel_Account_Count_Verification() throws IOException, InterruptedException {
	    	try {
	        // Step 1: Verify the account count in Excel matches the grid account count
	        String gridAccountCount = coremanualallocationpage.getAccountGridCount();
	        DownloadedExcelReader.DataSummary dataSummary = DownloadedExcelReader.getAccountNumberSummary();
	        int excelAccountCount = dataSummary.getRowCount();

	        // Assertion to verify that the account count matches
	        Assert.assertEquals(Integer.parseInt(gridAccountCount), excelAccountCount,
	                "Account count in grid and Excel should match.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the account count in the Excel file matches the account count displayed in the grid.");
	        ExtentTestManager.getTest().log(Status.PASS, "The account count matches in both the grid and the downloaded Excel file.");
	     // Step 2: Get all account numbers from the Excel file
	        List<String> excelAccountNumbers = dataSummary.getAccountNumbers();

	        // Optional: Log the account numbers or perform additional validations
	        System.out.println("Account numbers from Excel: " + excelAccountNumbers);
	        
	        // Example: Assert that the list is not empty
	        Assert.assertFalse(excelAccountNumbers.isEmpty(), "Excel account numbers list should not be empty.");
	        
	     // Save one account number to the shared variable
	        accountNumberFromExcel = excelAccountNumbers.get(0); // Get the first account number
	    	}
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
		Thread.sleep(3000);
	        
	    }
	    
	    @Test(priority = 20)
	    public void Login_to_call_centre_application_and_take_updation_of_disposition_page() throws Exception {
	  		callcenterlogin.CallCenterLogin();
	  		driver = baseclass.getDriver(); // Update the driver after CoreLogin
	  		coremanualallocationpage = new CoreManualAllocationPage(driver);
	  		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	  		try {
	  			
	  			
	        //Navigate to Call Centre Main Menu
	  			coremanualallocationpage.navigateTodispostionMainMenu();
	  			coremanualallocationpage.enterAccountNumber(accountNumberFromExcel);
	  			ExtentTestManager.getTest().log(Status.PASS, "Entered the account number in the search tab.");
	  			coremanualallocationpage.clickSearchButon(); 
	  			ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
	  	        String warningMessage = coremanualallocationpage.getWarnMessage(); 
	  	        Assert.assertEquals("You are not authorized to do the disposition of this account number", warningMessage);
	  	      ExtentTestManager.getTest().log(Status.PASS, "After entering the account number in the search tab and clicking the search button, the warning message 'You are not authorized to do the disposition of this account number' appeared.");
	  }
	  		
	  		catch (AssertionError | Exception e) {
	  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
	        }
	  		Thread.sleep(3000);
	    }
	    
		@Test(priority = 21)
		  public void  Login_and_Navigation_to_Manual_Allocation_Core_Application() throws Exception { 
				try {
				callcenterlogin.CoreLogin();
				driver = baseclass.getDriver(); // Update the driver after CoreLogin
				drivers.add(driver); // Add the new driver to the list
				coremanualallocationpage = new CoreManualAllocationPage(driver);
				
				
		      Assert.assertTrue(coremanualallocationpage.isManualAllocationPageLoaded(), "Manual Allocation page not loaded correctly.");
		      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Call Centre menu.");
		      ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Manual Allocation submenu successfully.");
		      ExtentTestManager.getTest().log(Status.PASS, "The Manual Allocation page is displayed with the URL 'CallCentre/ManualAllocationConfiguration'.");
				}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000); 
		  }
		
		 @Test(priority = 22)
		    public void Mandatory_Field_Validation_for_Asset_Category_() throws InterruptedException {
			 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			 WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		    	js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", Searchbtn);
		    	Thread.sleep(1000);		  
		    	ExtentTestManager.getTest().log(Status.PASS, "Did not enter values for the Asset Category and To fields.");
		    	// Test case for mandatory field validation
			 coremanualallocationpage.clickSearchbutton(); 
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button");
		        String warning = coremanualallocationpage.getWarningmessage();
		        Assert.assertEquals(warning, "Asset Category Required");
		        ExtentTestManager.getTest().log(Status.PASS, "After not entering values for the Asset Category and To fields and clicking the search button, the warning message 'Asset category is Required' appeared.");
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));
			 }
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 23)
		    public void Asset_Category_SMA_and_NPA_Selection_() throws InterruptedException {
		    	try {
		        // Test case for asset category selection
		    	coremanualallocationpage.selectAssetCategory(); 
		    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'NPA' category from the Asset Category dropdown and 'SMA' category from the dropdown.");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 24)
		    public void SMA_Category_Dropdown_Selection_() throws InterruptedException {
		    	try {
		        // Test case for SMA category dropdown selection
		    	coremanualallocationpage.selectSmaCategories();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the SMA category dropdown");
		    	ExtentTestManager.getTest().log(Status.PASS, "selected 'SMA 0', 'SMA 1', and 'SMA 2'.");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 25)
		    public void NPA_Category_Dropdown_Selection_() throws InterruptedException {
		    	try {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		        // Test case for NPA category dropdown selection
		    	coremanualallocationpage.selectNpaCategories();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the NPA category dropdown");
		    	ExtentTestManager.getTest().log(Status.PASS, "Selected 'Sub-standard', 'Doubtful-1', 'Doubtful-2', 'Doubtful-3', and 'Loss asset'.");
		    	coremanualallocationpage.clickSearchbutton(); 
		    	String warning = coremanualallocationpage.getWarningmessageforTofieldmandatorychecking(); 
		        Assert.assertEquals(warning, "To is Required");
		        ExtentTestManager.getTest().log(Status.PASS, "After selecting values from the NPA category dropdown and clicking the search button, the warning message 'To is Required' appeared.");
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.Tofieldmandatorywarningmsg));
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			Thread.sleep(3000);
		    }
		    
		    @Test(priority = 26, dataProvider = "TestData")
		    public void OS_Balance_Field_Validation_(Map<Object, Object> testdata) throws InterruptedException {
		    	try {
		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value1 = testdata.get("OutstandingBalanceOperator").toString();
			        String value2 = testdata.get("OutstandingBalance").toString();
		    	coremanualallocationpage.clickOsBalanceField();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the O/S Balance field.");
		    	coremanualallocationpage.selectEqualFinancialOperator(value1);
		    	coremanualallocationpage.enterOsBalance(value2);
		    	ExtentTestManager.getTest().log(Status.PASS, "Selected '=' from the 'Select Financial Operator' dropdown, and entered '1234' in the O/S Balance field.");
		    	}
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
		    }

		    @Test(priority = 27, dataProvider = "TestData")
		    public void To_Field_Dropdown_Selection_(Map<Object, Object> testdata) throws InterruptedException {
		    	try {
		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value1 = testdata.get("To").toString();
			        coremanualallocationpage.selectCallCentreFromToDropdown(value1);
			        ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown.");
		    	}
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
		    }   
		    
		    @Test(priority = 28)
		    public void Search_with_Mandatory_Fields_() throws InterruptedException {
		    	try {
		    	coremanualallocationpage.clickSearchBtn();
		    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
		    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
	            WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
		    	 // Verify if the text for "Total account selected" is displayed and is not empty
		    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
		    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
		    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
		    	Assert.assertFalse(totalAccountSelected.getText().isEmpty(),"Total account selected text is empty");
	            Assert.assertFalse(totalOutstandingAmount.getText().isEmpty(),"Total outstanding amount text is empty");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
	            
		    }
		    
		 // Test case for 'Allocate to Field Dropdown Selection'
		    @Test(priority = 29, dataProvider = "TestData")
		    public void Allocate_to_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
		    	try {
		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value = testdata.get("AllocateTo").toString();
		    	coremanualallocationpage.selectCallCentreFromAllocateToDropdown(value);
		    	ExtentTestManager.getTest().log(Status.PASS, "Successfully selected 'Call Centre' from the 'Allocate To' dropdown.");
		    	}
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
		    }
		    
		    @Test(priority = 30, dataProvider = "TestData")
		    public void Select_Call_Centre_Field_Dropdown_Selection(Map<Object, Object> testdata) throws InterruptedException {
		    	try {
		    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    		String value = testdata.get("SelectCallCentre").toString();
		        // Select "CallCentre 1" from dropdown
		    		coremanualallocationpage.selectCallCentre(value);
		    		ExtentTestManager.getTest().log(Status.PASS, "Successfully selected 'CallCentre 1' from the 'Select Call Centre' dropdown.");
		    	}
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
		       
		    }
		    
		    @Test(priority = 31)
		    public void Assign_Button_Validation_Message() throws InterruptedException {
		    	try {
		        // Click the assign button
		    	coremanualallocationpage.clickAssignButton();
		    	ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked the 'Assign' button.");
		        // Assert the validation message is "Assigned successfully"
		        Assert.assertEquals(coremanualallocationpage.getValidationMessage(), "Assigned Successfully",
		                "Validation message should be 'Assigned Successfully' after allocation.");
		        ExtentTestManager.getTest().log(Status.PASS, "Validation message 'Assigned successfully' appears after allocation.");
		    	}
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		           throw e;
			 }
			Thread.sleep(3000);
		    }
		    
		    @Test(priority = 32)
		    public void Successful_Login_and_Navigation() throws Exception {
		    	callcenterlogin.CallCenterLogin();
		  		driver = baseclass.getDriver(); // Update the driver after CoreLogin
		  		coremanualallocationpage = new CoreManualAllocationPage(driver);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
				try {
					ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
					ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
		        //Navigate to Call Centre Main Menu
					coremanualallocationpage.navigateToMainMenu();
				ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");
		        //Click on Account Filtration sub-menu
				coremanualallocationpage.navigateToAccountFiltration();
				ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Account Filtration sub-menu.");
		        // Expected Result: User is navigated to Account Filtration page
		        // URL shows CallCentre/CallCentreLeadFiltration
		        String currentUrl = driver.getCurrentUrl();
		        Assert.assertTrue(currentUrl.contains("CallCentre/CallCentreLeadFiltration"), "Not navigated to Account Filtration page.");
		        ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Account Filtration page, URL shows CallCentre/CallCentreLeadFiltration.");
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
				}
				
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
		        }
				Thread.sleep(3000);
		    }
		    
		    @Test(priority = 33)
		    public void Verify_Mandatory_Fields() throws InterruptedException {
		    	try {
		        // Test to confirm that the 'Asset Category' dropdown is marked as mandatory
		        Assert.assertTrue(coremanualallocationpage.isAssetCategoryMandatory(), "'Asset Category' field should be mandatory");
		        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Asset Category' dropdown is marked mandatory.");
		     // Test to confirm that the 'Allocated To' field is marked as mandatory
		        Assert.assertTrue(coremanualallocationpage.isAllocatedToMandatory(), "'Allocated To' field should be mandatory");
		        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Allocated To' field is marked mandatory.");
		        ExtentTestManager.getTest().log(Status.PASS, "Both 'Asset Category' and 'Allocated To' fields are marked as mandatory.");
		    	}
				
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
		        }
				Thread.sleep(3000);
		    }
		   
		    @Test(priority = 34)
		    public void Form_Submission_Without_Mandatory_Fields() throws InterruptedException {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
				try {
					ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category and Allocated To fields empty.");
		       //Click on the Search button
					coremanualallocationpage.clickSearchButton();
				ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
		        // Verify the warning message
		        String expectedMessage = "Allocated To is Required";
		        Assert.assertEquals(coremanualallocationpage.getWarningMessage(), expectedMessage, "Warning message not displayed as expected");
		        ExtentTestManager.getTest().log(Status.PASS, "Displays warning message \"Allocated to is required\".");
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
		}
				
				catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
		        }
				Thread.sleep(3000);
		    }
		    
		    @Test(priority = 35)
		    public void Asset_Category_Selection() throws InterruptedException {
				 try {
		        // Open Asset Category dropdown and selecting all values
					 coremanualallocationpage.openAssetCategoryDropdown();
				ExtentTestManager.getTest().log(Status.PASS, "Opened the Asset Category dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "Selected the SMA category and NPA category.");
				ExtentTestManager.getTest().log(Status.PASS, "SMA and NPA categories are selected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
		    }
		    
			@Test(priority = 36)
		    public void SMA_Category_Selection_() throws InterruptedException {
				 try {
		        // Open SMA Category dropdown and selecting all values
					 coremanualallocationpage.openSMACategoryDropdown(); 
				ExtentTestManager.getTest().log(Status.PASS, "Opened the SMA Category dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "Selected SMA 0, SMA 1, and SMA 2 from the dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "SMA 0, SMA 1, SMA 2 should be selected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
		    }
			
			@Test(priority = 37)
		    public void NPA_Category_Selection() throws InterruptedException {
				 try {
		        // Open SMA Category dropdown and selecting all values
					 coremanualallocationpage.openNPACategoryDropdown(); 
				ExtentTestManager.getTest().log(Status.PASS, "Opened the NPA Category dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "Selected sub-standard, doubtful-1, doubtful-2, doubtful-3, and loss asset.");
				ExtentTestManager.getTest().log(Status.PASS, "Selected NPA categories sub-standard , doubtful-1 , doubtful-2,doubtful-3,loss asset should be selected from the dropdown");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
		    }
			
			@Test(priority = 38, dataProvider = "TestData")
		  public void Allocation_Type_Selection(Map<Object, Object> testdata) throws InterruptedException {
			 try {
		      
			 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value = testdata.get("AllocationType").toString();
			        coremanualallocationpage.selectAllocationType(value); 
			        ExtentTestManager.getTest().log(Status.PASS, "Opened the Allocation Type dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "Selected Manual Allocation.");
			    }
		      ExtentTestManager.getTest().log(Status.PASS, "Manual Allocation is selected.");
			 }
		      catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		          throw e;
			 }
			 Thread.sleep(3000); 
		      
		}
			
			@Test(priority = 39, dataProvider = "TestData")
		    public void Allocated_To_Selection_Call_Centre(Map<Object, Object> testdata) throws InterruptedException {
				//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			 try {
		        
			 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value = testdata.get("AllocatedTo").toString();
			     // Select any value in Allocated To field
			        coremanualallocationpage.selectAllocatedTo(value);
			    }
			 ExtentTestManager.getTest().log(Status.PASS, "Successfully clicked on the 'Allocated To' dropdown.");
			 ExtentTestManager.getTest().log(Status.PASS, "Successfully selected 'Call Centre' from the 'Allocated To' dropdown.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000); 
		        
		 }
			
			 @Test(priority = 40)
			    public void Search_Functionality_with_Filters() throws InterruptedException { 
			    	try {
			    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
			    	coremanualallocationpage.clickSearchButton();
			    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the search button.");
			    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected); // Update with the actual locator
		            WebElement totalOutstandingAmount = driver.findElement(CoreManualAllocationRepo.TotalOutStandingAmount); // Update with the actual locator
			    	 // Verify if the text for "Total account selected" is displayed and is not empty
			    	Assert.assertTrue(totalAccountSelected.isDisplayed(),"Total account selected is not displayed" );
			    	Assert.assertTrue(totalOutstandingAmount.isDisplayed(),"Total outstanding amount is not displayed");
			    	ExtentTestManager.getTest().log(Status.PASS, "The accounts count is displayed with the columns 'Total Account Selected' and 'Total Outstanding Amount'.");
			    	Assert.assertFalse(totalAccountSelected.getText().isEmpty(),"Total account selected text is empty");
		            Assert.assertFalse(totalOutstandingAmount.getText().isEmpty(),"Total outstanding amount text is empty");
			    	}
					catch (AssertionError | Exception e) {
						ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			           throw e;
				 }
				Thread.sleep(3000);
		            
			    }
			 
			 @Test(priority = 41, dataProvider = "TestData")
			    public void Download_File__List_of_Accounts(Map<Object, Object> testdata) throws InterruptedException {
				 try {
			        // Step 1: Click on Download File dropdown
					 coremanualallocationpage.clickDownloadDropdown();
				 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Download File dropdown.");
				 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				    	
				        String value = testdata.get("DownloadFile").toString();
			        // Step 2: Select List of Accounts
				        coremanualallocationpage.selectListOfAccounts(value);
				 ExtentTestManager.getTest().log(Status.PASS, "Selected List of Accounts from the dropdown.");
				 }
			        // Step 3: Click on Download button
				 coremanualallocationpage.clickDownloadButton();
				 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Download button successfully.");
				 ExtentTestManager.getTest().log(Status.PASS, "Verified that the file is downloaded successfully.");
				 }
			        catch (AssertionError | Exception e) {
						ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			          throw e;
				 }
				 
				 Thread.sleep(3000);
			    }
			 
			 @Test(priority = 42)
			 public void Verify_Downloaded_File() throws InterruptedException {
				    try {
				    	WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected);
				    	totalAccounts=totalAccountSelected.getText();
				        // Call the verifyAccountsAllocation method and get the result
				        Map<String, String> resultMap = coremanualallocationpage.verifyAccountsAllocation(totalAccounts);

				        // Log expected and actual data in Extent Report
				        String expectedData = resultMap.get("expectedData"); 
				        String actualData = resultMap.get("actualData");
				        String result = resultMap.get("result");
				        accountNumber = resultMap.get("accountNumber");

				        ExtentTestManager.getTest().log(Status.INFO, "Expected Data: " + expectedData);
				        ExtentTestManager.getTest().log(Status.INFO, "Actual Data: " + actualData);
				        ExtentTestManager.getTest().log(Status.INFO, "Account Number: " + accountNumber);
				        // Assert the result
				        Assert.assertEquals(result, "PASS", "Accounts do not match those indicated in the grid.");
				        ExtentTestManager.getTest().log(Status.PASS, "Data match successful: Expected data matches the actual data.");
				    } catch (AssertionError | Exception e) {
				        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				        throw e;
				    }

				    Thread.sleep(3000);
				}
			 
			 @Test(priority = 43)
			    public void Verify_the_URL_after_navigating_to_the_Updation_of_Disposition_page() throws Exception { 

			  		try {
			  			
			        //Navigate to Call Centre Main Menu
			  			coremanualallocationpage.navigateTodispostionMainMenu();
			  			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the main menu \"Disposition.\"");
			  			ExtentTestManager.getTest().log(Status.PASS, "Clicked on the sub-menu \"Updation of Disposition.\"");
			  			String currentUrl = driver.getCurrentUrl();
		          Assert.assertTrue(currentUrl.contains("CallCentre/DispositionByAgent"), "Not navigated to Account Filtration page.");
		          ExtentTestManager.getTest().log(Status.PASS, "The application URL is \"CallCentre/DispositionByAgent.\"");
			  }
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			 
			// Test case to validate presence of all fields and buttons
			    @Test(priority = 44)
			    public void Validate_presence_of_fields_and_buttons_on_the_Updation_of_Disposition_page() throws InterruptedException { 
			    	try {
			        Assert.assertTrue(coremanualallocationpage.isAccountNumberFieldPresent(), "Account number field is missing");
			        ExtentTestManager.getTest().log(Status.PASS, "Checked for the presence of the \"Account Number\" field.");
			        Assert.assertTrue(coremanualallocationpage.isSearchButtonPresent(), "Search button is missing");
			        ExtentTestManager.getTest().log(Status.PASS, "Checked for the \"Search\" button.");
			        Assert.assertTrue(coremanualallocationpage.isCheckLiveBalanceButtonPresent(), "Check live balance button is missing");
			        ExtentTestManager.getTest().log(Status.PASS, "Checked for the \"Check Live Balance\" button.");
			        Assert.assertTrue(coremanualallocationpage.isTransactionDetailsSectionPresent(), "Transaction details button is missing");
			        ExtentTestManager.getTest().log(Status.PASS, "Checked for the \"Transaction Details.\"");
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    } 
			    
			    @Test(priority = 45, dataProvider = "TestData")
			    public void Account_Number_Field_ECP_Validations(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				    	
				        String Alphabets = testdata.get("Alphabets").toString();
				        String Numeric = testdata.get("Numeric").toString();
				        String Alphanumeric = testdata.get("Alphanumeric").toString();
				        // Test with Alphabets
				        coremanualallocationpage.enterAccountNumbers(Alphabets);
				        Assert.assertTrue(coremanualallocationpage.isAccountNumberFieldValid(Alphabets), "Alphabets are incorrectly allowed");
				        ExtentTestManager.getTest().log(Status.PASS, "Tested the \"Account Number\" field with alphabetic and input got restricted");
				        // Test with Numeric input
				        coremanualallocationpage.enterAccountNumbers(Numeric);
				        Assert.assertTrue(coremanualallocationpage.isAccountNumberFieldValid(Numeric), "Numeric inputs are not allowed");
				        ExtentTestManager.getTest().log(Status.PASS, "Tested the \\\"Account Number\\\" field with numeric and inputs are allowed");
				        // Test with Alphanumeric input
				        coremanualallocationpage.enterAccountNumbers(Alphanumeric); 
				        Assert.assertTrue(coremanualallocationpage.isAccountNumberFieldValid(Alphanumeric), "Alphanumeric incorrectly allowed");
				        ExtentTestManager.getTest().log(Status.PASS, "Tested the \\\"Account Number\\\" field with alphanumeric  and input got restricted");
			    	}  
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			    @Test(priority = 46)
			    public void Search_with_Invalid_Account_Number() throws InterruptedException {
			    	try {
			    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			        // Clicking the search button
			    	coremanualallocationpage.clickSearchButon(); 
			    	ExtentTestManager.getTest().log(Status.PASS, "Entered an invalid account number in the \"Account Number\" field.");
			    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button.");
			        // Verifying the validation message
			        String expectedMessage = "Invalid Account Number";
			        String actualMessage = coremanualallocationpage.getValidationMessageafterenteringinvalidaccountnumber();
			        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch");
			        ExtentTestManager.getTest().log(Status.PASS, "Validation message displayed: \"Invalid Account Number\".");
			        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.validationMessageforinvalidaccountnumber));
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			    @Test(priority = 47)
			    public void Search_with_Valid_Account_Number() throws InterruptedException {
			    	try {
			    		
			        // Enter a valid account number
			    	coremanualallocationpage.enterAccountNumbers(accountNumber);
			    	ExtentTestManager.getTest().log(Status.PASS, "Entered the valid account number in the \"Account Number\" field");
			        // Click on search button
			    	coremanualallocationpage.clickSearchButon();
			    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button.");
			        // Validate the account details display with "Add interaction details" field
			        Assert.assertTrue(coremanualallocationpage.isInteractionDetailsDisplayed(), 
			                          "Interaction details field should be displayed.");
			        ExtentTestManager.getTest().log(Status.PASS, "Verified that the account details were displayed along with the \"Add Interaction Details\" field");
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			    @Test(priority = 48)
			    public void Verify_Fields_in_Add_Interaction_Details_Section() throws InterruptedException {
			    	try {
			        // Verify all fields are present
			        Assert.assertTrue(coremanualallocationpage.areAllFieldsPresent(), "Not all fields are present in the Add Interaction Details section.");
			        ExtentTestManager.getTest().log(Status.PASS, "Checked for the presence of the \"Action Owner,\" \"Disposition Type,\" \"Sub Disposition,\" \"Next Action Date,\" \"Remark,\" \"Save,\" and \"Cancel\" buttons.");
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			 // Test to verify Internal User can be selected
			    @Test(priority = 49, dataProvider = "TestData")
			    public void Select_Internal_User_from_Action_Owner_Dropdown(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				    	
				        String value = testdata.get("ActionOwnerDropdownValue").toString();
			        // Action: Select Internal User from Action Owner dropdown
				        coremanualallocationpage.selectInternalUser(value);
				        ExtentTestManager.getTest().log(Status.PASS, "Selected "+value+" from the \"Action Owner\" dropdown.");
			    	}
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    	
			    }
			    
			    @Test(priority = 50, dataProvider = "TestData")
			    public void Select_Disposition_Type(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    		String value = testdata.get("Disposition").toString();	
			        // Select "Connected" from Disposition Type dropdown and assert
			    		coremanualallocationpage.selectConnectedDispositionType(value);
			    		ExtentTestManager.getTest().log(Status.PASS, "Selected "+value+" from the \"Disposition Type\" dropdown.");
			    	}
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			    @Test(priority = 51, dataProvider = "TestData")
			    public void Select_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				    	
				        String value = testdata.get("SubDisposition").toString();
			        // Select "Call Back" from Sub Disposition dropdown and assert
				        coremanualallocationpage.selectCallBackSubDisposition(value);
				        ExtentTestManager.getTest().log(Status.PASS, "Selected "+value+" from the \"Sub Disposition\" dropdown");  
			    	}
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }

			    @Test(priority = 52, dataProvider = "TestData")
			    public void Select_Date_in_Next_Action_Date_DatePicker(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    		String value = testdata.get("ActionDate").toString();
			        // Select date using the date picker and assert
			    		coremanualallocationpage.selectNextActionDate(value);
			    		ExtentTestManager.getTest().log(Status.PASS, "Selected "+value+" using the \"Next Action Date\" date picker.");  	
			    	}
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }

			    @Test(priority = 53, dataProvider = "TestData")
			    public void Enter_Remarks_and_Save_Interaction(Map<Object, Object> testdata) throws InterruptedException {
			    	try {
			    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    		String value = testdata.get("Remarks").toString();
			        // Enter remarks and save
			    		coremanualallocationpage.enterRemarks(value); 
			    		ExtentTestManager.getTest().log(Status.PASS, "Entered "+value+" in the \"Remark\" field.");  	
			    	}
			    	coremanualallocationpage.clickSaveButton();
			    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Save\" button."); 
			    	String expectedMessage = "Saved Successfully";
			        String actualMessage = coremanualallocationpage.getValidationMessageaftersavingInteractionDetails();
			        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch");
			        ExtentTestManager.getTest().log(Status.PASS, "Verified that the success message \"Saved Successfully\" was displayed.");
			        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreManualAllocationRepo.validationMessageforInteractionDetails));
			    	}
			  		
			  		catch (AssertionError | Exception e) {
			  			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			            throw e;
			        }
			  		Thread.sleep(3000);
			    }
			    
			 // Test method to verify saved account interaction details are displayed
			    @Test(priority = 54)
			    public void Search_for_Previously_Saved_Account_Interaction_Details() throws InterruptedException { 
			    	try {
			    	// Enter a valid account number
			    	coremanualallocationpage.enterAccountNumbers(accountNumber);
			    	ExtentTestManager.getTest().log(Status.PASS, "Entered the same account number "+accountNumber+" in the \"Account Number\" field.");
			        // Click on search button
			    	coremanualallocationpage.clickSearchButon();
			    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Search\" button.");
			        // Assert that interaction history is displayed
			        Assert.assertTrue(coremanualallocationpage.isInteractionHistoryDisplayed(), 
			                "Interaction history should be displayed.");
			        ExtentTestManager.getTest().log(Status.PASS, "Verified that the saved interaction details were displayed under \"History of Interaction Details.\""); 
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

