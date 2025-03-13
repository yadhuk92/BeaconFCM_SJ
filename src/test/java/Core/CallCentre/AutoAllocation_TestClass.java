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
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import Core.CallCentre.CoreAutoAllocationPage;
import Core.CallCentre.CoreAutoAllocationPage.ProcedureResult;

import bsh.ParseException;
import io.netty.handler.timeout.TimeoutException;

import com.listeners.TestListener;

public class AutoAllocation_TestClass {
	
	private String totalAccounts;
	private List<WebDriver> drivers = new ArrayList<>();
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class callcenterlogin;
	CoreAutoAllocationPage callcenteraccountfiltrationPage;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		//corelogin = new Login_Class();
		callcenterlogin = new Login_Class();
		callcenterlogin.CallCenterLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		callcenteraccountfiltrationPage = new CoreAutoAllocationPage(driver);
		ExcelReader = new com.Utility.ExcelReader("Call_Centre_Auto_Allocation");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		 baseclass = new Base_Class();
		    driver = baseclass.getDriver();
		    drivers.add(driver);
		    callcenteraccountfiltrationPage = new CoreAutoAllocationPage(driver);
		    callcenterlogin = new Login_Class();
		    // Update the ScreenShot object with the new driver
		    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Call Centre AutoAllocation");
    }
	
	@Test(priority = 1)
    public void Login_to_call_centre_application_and_take_account_filtration_page() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
			ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
        //Navigate to Call Centre Main Menu
		callcenteraccountfiltrationPage.navigateToMainMenu();
		ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");
        //Click on Account Filtration sub-menu
		callcenteraccountfiltrationPage.navigateToAccountFiltration();
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
		callcenteraccountfiltrationPage.clickSearchButton();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
        // Verify the warning message
        String expectedMessage = "Allocated To is Required";
        Assert.assertEquals(callcenteraccountfiltrationPage.getWarningMessage(), expectedMessage, "Warning message not displayed as expected");
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
	        callcenteraccountfiltrationPage.selectAllocatedTo(value);
	        ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category field empty and selected a value in the Allocated To field.");
	    }
	// Click Search button
	 callcenteraccountfiltrationPage.clickSearchButtonaftergivingvalueforallocatedto();
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Search button.");
	// Verify the warning message
     Assert.assertEquals(callcenteraccountfiltrationPage.getWarningMessageaftergivingvalueforallocatedto(), "Asset Category is Required");
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
		callcenteraccountfiltrationPage.openAssetCategoryDropdown();
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
		callcenteraccountfiltrationPage.openSMACategoryDropdown(); 
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
    public void NPA_Category_Selection() throws InterruptedException {
		 try {
        // Open SMA Category dropdown and selecting all values
		callcenteraccountfiltrationPage.openNPACategoryDropdown(); 
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
    public void Allocation_Type_Selection__Auto(Map<Object, Object> testdata) throws InterruptedException {
	 try {
        
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocationType").toString();
	        callcenteraccountfiltrationPage.selectAllocationType(value); 
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the Allocation Type dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Auto Allocation.");
	    }
        ExtentTestManager.getTest().log(Status.PASS, "Auto Allocation is selected.");
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
		ExtentTestManager.getTest().log(Status.PASS, "Verified that the Asset Category, SMA Category, NPA Category, and Allocated To mandatory fields are filled.");
		ExtentTestManager.getTest().log(Status.PASS, "Confirmed that the Allocation Date is autofilled.");
	 WebElement downloadbutton = driver.findElement(CoreAutoAllocationRepo.downloadbutton);
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", downloadbutton);
        Thread.sleep(500);
        // Perform search
	 callcenteraccountfiltrationPage.clickSearchButton(); 
		ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button successfully.");
	 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	 String gridText = callcenteraccountfiltrationPage.getResultGridText();
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
	
	@Test(priority = 9)
    public void  Login_to_beacon_fcm_core_application_Verify_Auto_Allocation_Page_Load() throws Exception {
		try {
		callcenterlogin.CoreLogin();
		driver = baseclass.getDriver(); // Update the driver after CoreLogin
		callcenteraccountfiltrationPage = new CoreAutoAllocationPage(driver);
		
		
        Assert.assertTrue(callcenteraccountfiltrationPage.isAutoAllocationPageLoaded(), "Auto Allocation page not loaded correctly.");
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Call Centre menu.");
        ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Auto Allocation submenu successfully.");
        ExtentTestManager.getTest().log(Status.PASS, "Auto Allocation page is displayed with URL ending in `CallCentre/AutoAllocationConfiguration`");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }

    @Test(priority = 10) 
    public void Verify_Fields_and_Buttons_on_Auto_Allocation_Page() throws InterruptedException {
    	try {
        
        Assert.assertTrue(callcenteraccountfiltrationPage.areFieldsAndButtonsPresent(), "Fields or Add button not present on Auto Allocation page.");
        ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the following fields: Allocation Name, Effect Date, Asset Category, SMA Category, NPA Category, Zone, Vertical, Scheme Type, Product Type, Scheme Code, Asset Tagging Type, Outstanding Balance, Total Overdue, DPD, % Overdue to EMI, Corporate BCBF, Processing Interval, Expiry Date, and To and Confirmed the availability of the Add, Edit, Modify, and Activate/Deactivate buttons.");
        ExtentTestManager.getTest().log(Status.PASS, "All fields and Add button are present on the page");
    	}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
    
    @Test(priority = 11, dataProvider = "TestData")
    public void Enter_Allocation_Name_with_Alphabets(Map<Object, Object> testdata) throws InterruptedException {
    	try {
    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
  	
    		String value = testdata.get("AllocationName").toString();
       // Step 1: Enter "TestAllocation" in Allocation Name field
    			callcenteraccountfiltrationPage.enterAllocationName(value);
                ExtentTestManager.getTest().log(Status.PASS, "Entered value in the Allocation Name field.");
                ExtentTestManager.getTest().log(Status.PASS, "Left all other fields empty.");
        
        // Step 3: Click Add button
    		callcenteraccountfiltrationPage.clickAddButton();
    		ExtentTestManager.getTest().log(Status.PASS, "Clicked the Add button successfully.");

    		WebElement allocationName = driver.findElement(CoreAutoAllocationRepo.allocationName);
        Assert.assertEquals(allocationName.getAttribute("value"), value);
        ExtentTestManager.getTest().log(Status.PASS, "Allocation Name field accepts alphabets and no error shown.");
    	}
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }    
    @Test(priority = 12, dataProvider = "TestData")
    public void Select_effect_Date_from_Date_Picker(Map<Object, Object> testdata) throws ParseException, InterruptedException {
    	try {
        // Step 1: Click on Effect Date field
    	callcenteraccountfiltrationPage.clickEffectDateField();
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Effect Date field.");
        if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
          	
    		String date = testdata.get("effectDate").toString();

    		 // Step 2: Select a date from the date picker
    		callcenteraccountfiltrationPage.selectDateFromDatePicker(date);
    		ExtentTestManager.getTest().log(Status.PASS, "Selected a date from the date picker successfully.");
    	}
       
        // Verify: Selected date appears in the Effect Date field
        String actualDate = callcenteraccountfiltrationPage.getEffectDateFieldValue();

        // Verify: Date format is proper in effect date field DD-MM-YYY
        Assert.assertTrue(callcenteraccountfiltrationPage.isDateFormatDDMMYYY(actualDate), "Date format is incorrect in the Effect Date field.");
        ExtentTestManager.getTest().log(Status.PASS, "Confirmed that the selected date appears in the Effect Date field and Verified that the date format in the Effect Date field is correct and displayed as DD-MM-YYYY.");
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
    
    @Test(priority = 13)
    public void Select_NPA_and_SMA_Category_Checkbox() throws InterruptedException {
    	try {
    	callcenteraccountfiltrationPage.selectAllInAssetCategory(); 
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Asset Category dropdown.");
    	ExtentTestManager.getTest().log(Status.PASS, "Selected NPA Category from the dropdown.");
    	ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of SMA Category in the dropdown");
    	callcenteraccountfiltrationPage.selectAllInSmaCategory(); 
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Asset Category dropdown.");
    	callcenteraccountfiltrationPage.selectAllInNpaCategory();
    	ExtentTestManager.getTest().log(Status.PASS, "Selected the Select All checkbox from the NPA Category dropdown.");
    	ExtentTestManager.getTest().log(Status.PASS, "Selected the Select All option from the SMA Category dropdown.");
    	ExtentTestManager.getTest().log(Status.PASS, "Confirmed that SMA Category and NPA Category are selected under the Asset Category dropdown , Verified that the Select All option is selected under the SMA Category dropdown and Verified that the Select All option is selected under the NPA Category dropdown.");
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
    
    // Test case to validate selection of Zone
    @Test(priority = 14, dataProvider = "TestData")
    public void Select_Zone_from_Dropdown(Map<Object, Object> testdata) throws InterruptedException {
    	try {
    	
    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
          	
    		String value = testdata.get("Zone").toString();
    		callcenteraccountfiltrationPage.selectZone(value);
    		ExtentTestManager.getTest().log(Status.PASS, "Selected 'Mumbai' from the Zone dropdown successfully.");
    		ExtentTestManager.getTest().log(Status.PASS, "Mumbai is selected in Zone field.");
    	}
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }

    // Test case to validate selection of Processing Interval
    @Test(priority = 15, dataProvider = "TestData")
    public void Select_Processing_Interval_from_Dropdown(Map<Object, Object> testdata) throws InterruptedException {
    	try {
    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
          	
    		String value = testdata.get("ProcessingInterval").toString();
    		callcenteraccountfiltrationPage.selectProcessingInterval(value);
    		ExtentTestManager.getTest().log(Status.PASS, "Selected 'Daily' from the Processing Interval dropdown successfully.");
    		ExtentTestManager.getTest().log(Status.PASS, "Daily is selected in Processing Interval field");
    	}
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }

    // Test case to validate selection of 'To' Dropdown
    @Test(priority = 16, dataProvider = "TestData")
    public void Select_To_Dropdown(Map<Object, Object> testdata) throws InterruptedException { 
    	try {
    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
          	
    		String value = testdata.get("To").toString();
    		callcenteraccountfiltrationPage.selectTo(value); 
    		ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre' from the 'To' dropdown successfully.");
    		ExtentTestManager.getTest().log(Status.PASS, "Selected 'Call Centre is selected in 'To' field.");
    	}
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
   
    @Test(priority = 17, dataProvider = "TestData")
    public void Add_New_Allocation_Name(Map<Object, Object> testdata) throws InterruptedException {
    	try {
    		
    		ExtentTestManager.getTest().log(Status.PASS, "Filled in the Allocation Name, Effect Date, Asset Category, SMA Category, NPA Category, Zone, Processing Interval, and To fields.");

        // Click the Add button
    	callcenteraccountfiltrationPage.clickAddButtonaftergivingrequiredvalues();
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the Add button successfully.");
    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
          	
    		String allocationname = testdata.get("AllocationName").toString();
    		String zonename = testdata.get("Zone").toString();
        // Assertions to check if the data is displayed in the grid
        // Implement assertions based on the application's UI response
    	boolean isAllocationNamePresent = callcenteraccountfiltrationPage.allocationNameInGrid(allocationname);
    	Assert.assertTrue(isAllocationNamePresent, "The allocation name is not present in the grid.");
    	boolean isZoneNamePresent = callcenteraccountfiltrationPage.allocationNameInGrid(zonename);
    	Assert.assertTrue(isZoneNamePresent, "The Zone name is not present in the grid.");
    	ExtentTestManager.getTest().log(Status.PASS, "Verified that the Allocation Name is displayed in the grid along with the Effect Date, Asset Category, SMA Category, NPA Category, Zone, Processing Interval, and To fields and Confirmed that the Effect Date follows the correct date format of DD-MM-YYYY.");
    	}
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
     
    @Test(priority = 18)
    public void Verify_Allocation_Name_Status_After_Creation() throws InterruptedException {
    	try {
    	
        Assert.assertTrue(callcenteraccountfiltrationPage.isStatusActive(), "The status should be a green tick (Active).");
        ExtentTestManager.getTest().log(Status.PASS, "Checked the Status column of the newly added Allocation Name in the grid.");
        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Status under the Status column is displayed as a green tick (Active)."); 
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    } 
    
    @Test(priority = 19)
    public void Deactivate_Allocation_Name() throws InterruptedException { 
    	try {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	callcenteraccountfiltrationPage.selectAllocationName();
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the checkbox against one Allocation Name.");
        Assert.assertTrue(callcenteraccountfiltrationPage.isDeactivateButtonEnabled(), "Deactivate button should be enabled.");
        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Deactivate button is enabled.");
        callcenteraccountfiltrationPage.clickDeactivateButton();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked the Deactivate button for the selected Allocation Name.");
        Assert.assertEquals(callcenteraccountfiltrationPage.getValidationMessage(), "Updated Successfully", 
                "Validation message should display correctly.");
        ExtentTestManager.getTest().log(Status.PASS, "Ensured that the validation message \"Updated Successfully\" is displayed.");
        Assert.assertTrue(callcenteraccountfiltrationPage.isStatusInActive(), "The status should now be a red cross (Inactive).");
        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Active Allocation Name status changes to 'Inactive' and Confirmed that the Status column against the Allocation Name is updated to a red cross (Inactive).");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
    
    @Test(priority = 20) 
    public void Reactivate_Allocation_Name() throws InterruptedException { 
    	try {
    	callcenteraccountfiltrationPage.selectAllocationName();
    	ExtentTestManager.getTest().log(Status.PASS, "Clicked the checkbox against one Allocation Name.");
        Assert.assertTrue(callcenteraccountfiltrationPage.isActivateButtonEnabled(), "Activate button should be enabled.");
        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Activate button is enabled.");
        callcenteraccountfiltrationPage.clickActivateButton();
        ExtentTestManager.getTest().log(Status.PASS, "Clicked the Activate button for the selected Allocation Name.");
        Assert.assertEquals(callcenteraccountfiltrationPage.getValidationMessage(), "Updated Successfully", 
                "Validation message should display correctly.");
        ExtentTestManager.getTest().log(Status.PASS, "Ensured that the validation message \"Updated Successfully\" is displayed.");
        Assert.assertTrue(callcenteraccountfiltrationPage.isStatusActive(), "The status should now be a green tick (Active).");
        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Active Allocation Name status changes to 'Active' and Confirmed that the Status column against the Allocation Name is updated to a green tick (Active).");
    	}
    	catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
	 }
	Thread.sleep(3000);
    }
	
	@Test(priority = 21)
    public void Run_Database_Package_and_Verify_Accounts_Insertion() throws IOException, InterruptedException {
		 try {
	            // Call the method that executes the stored procedure
	            String procedureOutput = ExecuteStoredProcedure.callLoadAndValidateAccountsSP();
	            ExtentTestManager.getTest().log(Status.PASS, "Ran the query SELECT * FROM RUN_LOG ORDER BY 1 DESC; successfully."); 
	            ExtentTestManager.getTest().log(Status.PASS, "Retrieved the latest run number.");
	            ExtentTestManager.getTest().log(Status.PASS, "Executed the procedure BEGIN callcentre_pkg.callcentre_accounts_load_proc(latest run number); END; successfully.");
	            // Print the output (you can remove this in production)
	            System.out.println("Procedure Output:\n" + procedureOutput);
	            
	            // Verify if the procedure output contains the expected success message
	            // You can adjust the assertion based on your expected output
	            Assert.assertTrue(procedureOutput.contains("PL/SQL procedure successfully completed"), 
	                    "Procedure execution failed or did not complete successfully.");
	            ExtentTestManager.getTest().log(Status.PASS, "the package should be excuted with successfull message \"PL/SQL procedure successfully completed\"");
	        } catch (IOException e) {
	            e.printStackTrace();
	            Assert.assertTrue(false, "Exception occurred while executing the stored procedure: " + e.getMessage());
	            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
	        }
		 Thread.sleep(3000);
	    }

    
	@Test(priority = 22)
  public void Login_to_call_centre_application_and_take_account_filtration_pages() throws Exception {
		callcenterlogin.CallCenterLogin();
		driver = baseclass.getDriver(); // Update the driver after CoreLogin
		callcenteraccountfiltrationPage = new CoreAutoAllocationPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		try {
			ExtentTestManager.getTest().log(Status.PASS, "Opened the FCM Call Centre application.");
			ExtentTestManager.getTest().log(Status.PASS, "Entered valid credentials and logged in.");
      //Navigate to Call Centre Main Menu
		callcenteraccountfiltrationPage.navigateToMainMenu();
		ExtentTestManager.getTest().log(Status.PASS, "Navigated to the Call Centre Main Menu.");
      //Click on Account Filtration sub-menu
		callcenteraccountfiltrationPage.navigateToAccountFiltration();
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Account Filtration sub-menu.");
      // Expected Result: User is navigated to Account Filtration page
      // URL shows CallCentre/CallCentreLeadFiltration
      String currentUrl = driver.getCurrentUrl();
      Assert.assertTrue(currentUrl.contains("CallCentre/CallCentreLeadFiltration"), "Not navigated to Account Filtration page.");
      ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Account Filtration page, URL shows CallCentre/CallCentreLeadFiltration.");
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
      wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocatedtodropdown));
}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
      }
		Thread.sleep(3000);
  }
	
	@Test(priority = 23)
  public void Select_SMA_and_NPA_Categorie() throws InterruptedException {
		 try {
      // Open Asset Category dropdown and selecting all values
		callcenteraccountfiltrationPage.openAssetCategoryDropdown(); 
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
	
	@Test(priority = 24)
  public void SMA_Category_Selections() throws InterruptedException {
		 try {
      // Open SMA Category dropdown and selecting all values
		callcenteraccountfiltrationPage.openSMACategoryDropdown(); 
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
	@Test(priority = 25, dataProvider = "TestData")
  public void NPA_Category_Selections(Map<Object, Object> testdata) throws InterruptedException {
		 try {
			 
			 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			    	
			        String value = testdata.get("OutstandingBalanceOperator").toString();
			        String balance = testdata.get("OutstandingBalance").toString();
			 
      // Open SMA Category dropdown and selecting all values
		callcenteraccountfiltrationPage.openNPACategoryDropdown(); 
		// Select '=' operator from the outstanding balance operator dropdown
		callcenteraccountfiltrationPage.selectOutstandingBalanceOperator(value);
		// Enter data in outstanding balance input field
		callcenteraccountfiltrationPage.enterOutstandingBalance(balance);
			 }
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
	@Test(priority = 26, dataProvider = "TestData")
  public void Allocation_Type_Selections__Auto(Map<Object, Object> testdata) throws InterruptedException {
	 try {
      
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocationType").toString();
	        callcenteraccountfiltrationPage.selectAllocationType(value); 
	        ExtentTestManager.getTest().log(Status.PASS, "Opened the Allocation Type dropdown.");
	        ExtentTestManager.getTest().log(Status.PASS, "Selected Auto Allocation.");
	    }
      ExtentTestManager.getTest().log(Status.PASS, "Auto Allocation is selected.");
	 }
      catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	 Thread.sleep(3000); 
      
}

	@Test(priority = 27, dataProvider = "TestData")
  public void Allocated_To_Selection__Call_Centre(Map<Object, Object> testdata) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	 try {
      
	 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
	    	
	        String value = testdata.get("AllocatedTo").toString();
	     // Select any value in Allocated To field
	        callcenteraccountfiltrationPage.selectAllocatedTo(value);
	        ExtentTestManager.getTest().log(Status.PASS, "Left the Asset Category field empty and selected a value in the Allocated To field.");
	    }

      ExtentTestManager.getTest().log(Status.PASS, "Displays warning message  \"asset category is required\".");
      wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
	 }
      catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
          throw e;
	 }
	 Thread.sleep(3000); 
      
}
	 @Test(priority = 28)
	    public void Perform_Search_with_Valid_Datas() throws InterruptedException { 
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 ExtentTestManager.getTest().log(Status.PASS, "Ensured that the Asset Category, SMA Category, NPA Category, and Allocated To mandatory fields are filled.");
		 WebElement downloadbutton = driver.findElement(CoreAutoAllocationRepo.downloadbutton);
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", downloadbutton);
	        Thread.sleep(500);
	        // Perform search
		 callcenteraccountfiltrationPage.clickSearchButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Search button successfully.");
	        // Verify expected result
		 WebElement gridDataRow = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.griddatarow));
		 boolean areResultsDisplayed = gridDataRow.isDisplayed();
	        Assert.assertTrue(areResultsDisplayed, "Total accounts allocated via Auto Allocation should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Total Accounts and Amount Allocated via Auto Allocation are displayed in the grid.");
	        WebElement totalaccountselectedingrid = driver.findElement(CoreAutoAllocationRepo.totalaccountselectedingrid);
	        WebElement totaloutstandingamountingrid = driver.findElement(CoreAutoAllocationRepo.totaloutstandingamountingrid);
	        String Value1 = totalaccountselectedingrid.getAttribute("title");
	        String value2 = totaloutstandingamountingrid.getText();
	        System.out.println("Total Account Selected: " + Value1);
	        System.out.println("Total Outstanding Amount: " + value2);
		 }
	      catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 29)
	    public void Verify_Total_Account_and_Amount_in_Grid() throws InterruptedException {
		 try {
	        // Retrieve total accounts count and amount from the grid
	        totalAccounts = callcenteraccountfiltrationPage.getTotalAccountsCount();
	        String totalAmount = callcenteraccountfiltrationPage.getTotalOutstandingAmount();
	        ExtentTestManager.getTest().log(Status.PASS, "Reviewed the grid for the Total Accounts count and Total Outstanding Amount.");
	        System.out.println("Total Accounts: " + totalAccounts);
	        System.out.println("Total Outstanding Amount: " + totalAmount);
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the Total Accounts count and Total Outstanding Amount are displayed in the grid."); 
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 30, dataProvider = "TestData")
	    public void Download_File__List_of_Accounts(Map<Object, Object> testdata) throws InterruptedException {
		 try {
	        // Step 1: Click on Download File dropdown
		 callcenteraccountfiltrationPage.clickDownloadDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Download File dropdown.");
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value = testdata.get("DownloadFile").toString();
	        // Step 2: Select List of Accounts
		 callcenteraccountfiltrationPage.selectListOfAccounts(value);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected List of Accounts from the dropdown.");
		 }
	        // Step 3: Click on Download button
		 callcenteraccountfiltrationPage.clickDownloadButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Download button successfully.");
		 ExtentTestManager.getTest().log(Status.PASS, "Verified that the file is downloaded successfully.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 31)
	 public void Verify_Downloaded_File() throws InterruptedException {
		    try {
		        // Call the verifyAccountsAllocation method and get the result
		        Map<String, String> resultMap = callcenteraccountfiltrationPage.verifyAccountsAllocation(totalAccounts);

		        // Log expected and actual data in Extent Report
		        String expectedData = resultMap.get("expectedData");
		        String actualData = resultMap.get("actualData");
		        String result = resultMap.get("result");

		        ExtentTestManager.getTest().log(Status.INFO, "Expected Data: " + expectedData);
		        ExtentTestManager.getTest().log(Status.INFO, "Actual Data: " + actualData);

		        // Assert the result
		        Assert.assertEquals(result, "PASS", "Accounts do not match those indicated in the grid.");
		        ExtentTestManager.getTest().log(Status.PASS, "Data match successful: Expected data matches the actual data.");
		    } catch (AssertionError | Exception e) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		        throw e;
		    }

		    Thread.sleep(3000);
		}
	 
	 @Test(priority = 32, dataProvider = "TestData")
	    public void Download_Dialer_File(Map<Object, Object> testdata) throws InterruptedException {
		 try {
	        // Step 1: Click on Download File dropdown
		 callcenteraccountfiltrationPage.clickDownloadDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Download File dropdown.");
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value = testdata.get("DialerFile").toString();
	        // Step 2: Select List of Accounts
		 callcenteraccountfiltrationPage.selectListOfAccounts(value);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected List of Accounts from the dropdown.");
		 }
	        // Step 3: Click on Download button
		 callcenteraccountfiltrationPage.clickDownloadButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Download button successfully.");
		 ExtentTestManager.getTest().log(Status.PASS, "Verified that the file is downloaded successfully.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 33)
	    public void Verify_Dialer_Downloaded_File() throws InterruptedException {
		 try {
		        // Call the verifyAccountsAllocation method and get the result
		        Map<String, String> resultMap = callcenteraccountfiltrationPage.verifyAccountsAllocation(totalAccounts);

		        // Extract expected and actual data
		        String expectedData = resultMap.get("expectedData");
		        String actualData = resultMap.get("actualData");
		        String result = resultMap.get("result");

		        // Log expected and actual data in Extent Report
		        ExtentTestManager.getTest().log(Status.INFO, "Expected Data: " + expectedData);
		        ExtentTestManager.getTest().log(Status.INFO, "Actual Data: " + actualData);

		        // Assert that the result is PASS
		        Assert.assertEquals(result, "PASS", "Accounts do not match those indicated in the grid.");
		        ExtentTestManager.getTest().log(Status.PASS, "Data match successful: Expected data matches the actual data.");
		    } catch (AssertionError | Exception e) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		        throw e;
		    }

		    Thread.sleep(3000);
	    }
	 
	 @Test(priority = 34, dataProvider = "TestData")
	    public void Download_Invalid_MobileNo_Excel_File(Map<Object, Object> testdata) throws Throwable {
		 try {
			 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			// Step 1: Execute stored procedure and get the result
		        String mobileNumber = testdata.get("MobileNumber").toString();
		        String osbalance = testdata.get("OutstandingBalance").toString();
		        ProcedureResult procedureResult = callcenteraccountfiltrationPage.executeStoredProcedure(mobileNumber,osbalance);
		         
		        // Log the procedure results 
		     // Print the procedure results to the console
	            System.out.println("Stored Procedure Result: " + procedureResult.getMessage());
	            System.out.println("Account No: " + procedureResult.getAccountNo());
	            System.out.println("Mobile No: " + procedureResult.getMobileNo());
		        
		        // Validate the result before proceeding (optional)
		        if (procedureResult.getAccountNo() == null || procedureResult.getMobileNo() == null) {
		            throw new AssertionError("No valid data returned from the stored procedure for the provided mobile number.");
		        }
			 
	        // Step 2: Click on Download File dropdown
		 callcenteraccountfiltrationPage.clickDownloadDropdown();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Download File dropdown.");
		    	
		        String value = testdata.get("InvalidMobileNoExcel").toString();
	        // Step 3: Select List of Accounts
		 callcenteraccountfiltrationPage.selectListOfAccounts(value);
		 ExtentTestManager.getTest().log(Status.PASS, "Selected List of Accounts from the dropdown.");
		 }
	        // Step 4: Click on Download button
		 callcenteraccountfiltrationPage.clickDownloadButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the Download button successfully.");
		 ExtentTestManager.getTest().log(Status.PASS, "Verified that the file is downloaded successfully.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 35)
	    public void Verify_Invalid_Mobile_Number_Downloaded_File() throws Throwable {
		 try {
			// Capture the returned DataSummary
		        DataSummary dataSummary = callcenteraccountfiltrationPage.verifyInvalidMobileNumberAccounts();
		        
		        if (dataSummary == null) {
		            throw new Exception("DataSummary is null, test case failed.");
		        }
		        
	        ExtentTestManager.getTest().log(Status.PASS, "Account number and mobile number match in the downloaded Excel file.");
	        ExtentTestManager.getTest().log(Status.INFO, "Total Rows with Data: " + dataSummary.getRowCount());
	        ExtentTestManager.getTest().log(Status.INFO, "Account Numbers: " + dataSummary.getAccountNumbers());
	        ExtentTestManager.getTest().log(Status.INFO, "Contact Numbers: " + dataSummary.getContactNumbers());
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	          throw e;
		 }
		 
		 Thread.sleep(3000);
	    }
//	 
	 @Test(priority = 36, dataProvider = "TestData")
	    public void Verify_Download_History(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				// Step 1: Execute stored procedure and get the result
			        String fromdate = testdata.get("effectDate").toString();
			        String todate = testdata.get("effectDate").toString();
		 
	        // Step 1: Click on Get Download History button
			        callcenteraccountfiltrationPage.clickGetDownloadHistory();
			        ExtentTestManager.getTest().log(Status.PASS, "'Get Download History' button clicked successfully.");
	        // Step 2: Select From Date and To Date
			        callcenteraccountfiltrationPage.enterFromDate(fromdate);
			        callcenteraccountfiltrationPage.enterToDate(todate);
			        ExtentTestManager.getTest().log(Status.PASS, "'From Date' and 'To Date' values selected successfully.");
		 }
	        // Step 3: Click Search button
			        callcenteraccountfiltrationPage.clickSearch();
			        ExtentTestManager.getTest().log(Status.PASS, "'Search' button clicked successfully.");
	        // Expected result: Verify if the download history is displayed in the grid
			        Assert.assertTrue(callcenteraccountfiltrationPage.isDownloadHistoryDisplayed(), 
			                "Download history not displayed as expected.");
			        ExtentTestManager.getTest().log(Status.PASS, "Download Excel file details successfully displayed in the grid.");
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


