package Core.Configurations;

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
import java.sql.SQLException;
import java.time.Duration;
import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
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

public class CollectionOfficerConfig_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	CollectionOfficerConfiguration_MainClass corecollectionofficerconfigpage;
	String userid,userId,userName;
	
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		corelogin = new Login_Class();
		Login_Class.CoreLogin();
		driver = baseclass.getDriver(); // Retrieve the driver instance
		corecollectionofficerconfigpage = new CollectionOfficerConfiguration_MainClass(driver);
		//ExcelReader = new com.Utility.ExcelReader("CollectionAgencyAgentAcAllocatP");
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(driver);
		Log.info("**** " + new Object() {}.getClass().getEnclosingClass().getSimpleName() + " ****");
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		corecollectionofficerconfigpage = new CollectionOfficerConfiguration_MainClass(driver);
		TestListener = new TestListener();
	    screenShot = new com.Utility.ScreenShot(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core-Collection Officer Configuration");
        Log.info("****" + method.getName() + "****");
    }
	
	 @Test(priority = 1)
	    public void Add_core_users_to_mst_employee_table () throws Throwable {
		 try {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		 WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
		 userId = userid.getText();
		 WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.username));
		 userName = username.getText(); 
		 
	        // Step 1: Execute the stored procedure to insert the employee
		 String Result = corecollectionofficerconfigpage.executeSPInsertEmployee(userId); 
		 ExtentTestManager.getTest().log(Status.PASS, "Core users were added to the mst_employee table with IS_BCO = 0. Query execution Result : "+Result+"");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 2)
	    public void Test_Collection_Officer_Configurations_Page_Basic_Accessibility() throws InterruptedException {
		 try {
	        // Step 1: Click on Security Management
		 corecollectionofficerconfigpage.clickSecurityManagement();
		 ExtentTestManager.getTest().log(Status.PASS, "Security Management was clicked from the dashboard menu.");
	        // Step 2: Click on Role Management
		 corecollectionofficerconfigpage.clickRoleManagement();
		 ExtentTestManager.getTest().log(Status.PASS, "The Role Management page was navigated to.");
	        // Step 3: Click on Add New Role
		 corecollectionofficerconfigpage.clickAddNewRole();
		 ExtentTestManager.getTest().log(Status.PASS, "Add New Role was clicked.");
	        // Step 4: Verify Collection Officer Configurations is available
	        Assert.assertTrue(corecollectionofficerconfigpage.isCollectionOfficerConfigurationsAvailable(),
	                "Collection Officer Configurations functionality should be accessible under Add New Role."); 
	        ExtentTestManager.getTest().log(Status.PASS, "it was verified that 'Collection Officer Configurations' was available under the Configurations functionality.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 3)
	    public void Test_Collection_Officer_Configurations_Page_Parameters() throws InterruptedException {
		 try {
	        // Navigate to Collection Officer Configurations page
		 corecollectionofficerconfigpage.clickConfigurationsMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Configurations' main menu was clicked.");
		 corecollectionofficerconfigpage.clickCollectionOfficerConfigSubmenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Collection Officer Config' submenu was clicked.");
	        // Verify CO field is mandatory
	        Assert.assertTrue(corecollectionofficerconfigpage.isCOFieldMandatory(), "Zone/CO field should be mandatory.");
	        ExtentTestManager.getTest().log(Status.PASS, "It was verified that the 'CO' field is mandatory.");
	        // Verify presence of specified buttons
	        Assert.assertTrue(corecollectionofficerconfigpage.areButtonsPresent(), "All specified buttons should be present.");
	        ExtentTestManager.getTest().log(Status.PASS, "The presence of 'Region', 'Branch', 'Search', 'Reset', 'Save Configurations', and 'Download in Excel' buttons was checked.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    } 
	 
	 @Test(priority = 4)
	    public void Test_Search_Functionality() throws InterruptedException {
		 try {
		 corecollectionofficerconfigpage.clickSearch(); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Search' button was clicked.");
	        Assert.assertTrue(corecollectionofficerconfigpage.isUserGridDisplayed(userId), "Users matching the search criteria should appear in the grid.");
	        ExtentTestManager.getTest().log(Status.PASS, "It was verified that the grid displayed the correct users.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	  
	 @Test(priority = 5) 
	    public void Test_Save_Configurations_Functionality() throws ClassNotFoundException, SQLException, IOException, InterruptedException {
		 try {
	        // Step 1: Select checkboxes
		 corecollectionofficerconfigpage.getDBValue(userId);
	        
	        // Step 2: Click Save Configurations
		 corecollectionofficerconfigpage.clickIsActiveCheckboxIfUserFound(userId); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Collection Officer' and 'Is Active' checkboxes were selected.");
		 corecollectionofficerconfigpage.clickSaveConfigurationsButton();  
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Save Configurations' button was clicked and The 'Yes' button in the confirmation popup was clicked.");
	        Assert.assertTrue(corecollectionofficerconfigpage.verifyDBUpdate(userId), "Configuration not saved in the database.");
	        ExtentTestManager.getTest().log(Status.PASS, "The selected configurations were saved and reflected in the database.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	    public void Test_Excel_Download_Functionality() throws InterruptedException, IOException {
		 try {
		 corecollectionofficerconfigpage.clickDownloadExcelButton();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Download in Excel' button was clicked.");
		 Assert.assertTrue(DownloadedExcelReader.isEmployeePresent(userId, userName), 
	                "Employee ID and Name not found in the Excel file."); 
		 ExtentTestManager.getTest().log(Status.PASS, "The downloaded file was opened and It was verified that the columns 'Sl No', 'Employee ID', 'Name', 'Designation', and 'Mobile No' were present.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Test_Failed_Save_Configurations_without_Selections() throws InterruptedException {
		 try {
	        // Step 1: Do not select any user (by default)
	        // Step 2: Click on Save Configurations
		 corecollectionofficerconfigpage.clickSaveConfigurationButton();
		 ExtentTestManager.getTest().log(Status.PASS, "No user was selected and The 'Save Configurations' button was clicked.");
	        // Step 3: Observe error message
	        String actualErrorMessage = corecollectionofficerconfigpage.getErrorMessage();
	        
	        // Assertion for expected result
	        Assert.assertEquals("Select atleast one record to process.", actualErrorMessage);
	        ExtentTestManager.getTest().log(Status.PASS, "The error message was observed and message was displayed : "+actualErrorMessage+".");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Test_Reset_Functionality() throws InterruptedException { 
		 try {
		 corecollectionofficerconfigpage.clickResetButton();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Reset' button was clicked.");
		 Assert.assertTrue(corecollectionofficerconfigpage.areFiltersReset(),"Filters should be reset to default state.");
		 ExtentTestManager.getTest().log(Status.PASS, "It was verified that all filters and search criteria were cleared and All filters were reset, returning to the default state.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9)
	    public void Verify_My_Desk_Unassigned_accounts_Tile() throws InterruptedException {
		 try {
	        // Click on My Desk main menu
		 corecollectionofficerconfigpage.clickMyDeskMainMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'My Desk' main menu was clicked.");
	        // Click on Dashboard sub menu
		 corecollectionofficerconfigpage.clickDashboardSubMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Dashboard' submenu was clicked.");
	        // Click on any tile of Unassigned accounts
		 corecollectionofficerconfigpage.clickUnassignedAccountsTile(); 
		 ExtentTestManager.getTest().log(Status.PASS, "A tile under 'Unassigned Accounts' was clicked.");
	        // Select 'BCO' from Allocate To dropdown
		 corecollectionofficerconfigpage.selectBCOFromAllocateTo();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Allocate To' dropdown was clicked, and the 'BCO' value was selected.");
	        // Assert that users are listed correctly under 'BCO'
	        Assert.assertTrue(corecollectionofficerconfigpage.areUsersListedUnderBCO(), "Expected users as Collection Officers under BCO.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Select BCO' dropdown was clicked. and It was observed that users were listed under 'BCO' based on the logged-in user's role (Region users saw region collection officers, CO users saw CO officers, and Branch users saw branch officers).");
	        ExtentTestManager.getTest().log(Status.PASS, "Users configured as Collection Officers were listed correctly.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10)
	    public void Verify_My_Desk_Branch_Attention_Required_Tile() throws InterruptedException {
		 try {
	        // Click on My Desk main menu
		 corecollectionofficerconfigpage.clickMyDeskMainMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'My Desk' main menu was clicked.");
	        // Click on Dashboard sub menu
		 corecollectionofficerconfigpage.clickDashboardSubMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Dashboard' submenu was clicked.");
		 corecollectionofficerconfigpage.clickBranchAttentionTile();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Branch Attention Required' tile was clicked.");
		 corecollectionofficerconfigpage.clickAssignReassignDropdown(); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Assign/Reassign' dropdown was clicked.");
	        // Verify the expected users are listed
		 Assert.assertTrue(corecollectionofficerconfigpage.getListedUsers(), "Expected users as Collection Officers under Assign/Reassign.");
		 ExtentTestManager.getTest().log(Status.PASS, "The listed users were observed and Users configured as Collection Officers were listed correctly.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 11)
	    public void other_branch_user_is_not_showing_My_Desk_Unassigned_accounts_Tile() throws ClassNotFoundException, IOException, InterruptedException, SQLException {
		 try {
		 corecollectionofficerconfigpage.createHOUser(userId); 
		 ExtentTestManager.getTest().log(Status.PASS, "A new 'Other Branch User' was created with login credentials, and successfully logged into the application using those credentials.");
		 driver = baseclass.getDriver(); // Update the driver
		 drivers.add(driver);
		 corecollectionofficerconfigpage = new CollectionOfficerConfiguration_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
		 corecollectionofficerconfigpage.clickMyDeskMainMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'My Desk' main menu was clicked.");
	        // Click on Dashboard sub menu
		 corecollectionofficerconfigpage.clickDashboardSubMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Dashboard' submenu was clicked.");
	        // Click on any tile of Unassigned accounts
		 corecollectionofficerconfigpage.clickUnassignedAccountsTile(); 
		 ExtentTestManager.getTest().log(Status.PASS, "A tile under 'Unassigned Accounts' was clicked.");
	        // Select 'BCO' from Allocate To dropdown
		 corecollectionofficerconfigpage.selectBCOFromAllocateTo();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Allocate To' dropdown was clicked, and the 'BCO' value was selected.");
	        // Assert that users are listed correctly under 'BCO'
	        Assert.assertFalse(corecollectionofficerconfigpage.areUsersListedUnderBCOforotherbrnach(userName), "Expected user should not available under BCO.");
	        ExtentTestManager.getTest().log(Status.PASS, "Only logged-in branch users are displayed in the BCO dropdown.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    } 
	 
	 @Test(priority = 12)
	    public void other_branch_user_is_not_showing_My_Desk_Branch_Attention_Required_Tile() throws InterruptedException {
		 try {
	        // Click on My Desk main menu
		 corecollectionofficerconfigpage.clickMyDeskMainMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'My Desk' main menu was clicked.");
	        // Click on Dashboard sub menu
		 corecollectionofficerconfigpage.clickDashboardSubMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Dashboard' submenu was clicked.");
		 corecollectionofficerconfigpage.clickBranchAttentionTile();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Branch Attention Required' tile was clicked.");
		 corecollectionofficerconfigpage.clickAssignReassignDropdown(); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Assign/Reassign' dropdown was clicked.");
	        // Verify the expected users are listed
		 Assert.assertFalse(corecollectionofficerconfigpage.areUsersListedUnderAssignReassignforotherbrnach(userName), "Expected users as Collection Officers under Assign/Reassign.");
		 ExtentTestManager.getTest().log(Status.PASS, "Only logged-in branch users are displayed under assign/reassign dropdown");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage()); 
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13) 
	    public void Test_Uncheck_Collection_Officer_Functionality() throws Throwable {
		 try {
			 Login_Class.CoreLogin();
				driver = baseclass.getDriver();
				drivers.add(driver);
				corecollectionofficerconfigpage = new CollectionOfficerConfiguration_MainClass(driver);
			    TestListener = new TestListener();
			    screenShot = new com.Utility.ScreenShot(driver);
		 corecollectionofficerconfigpage.clickConfigurationMenu(); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Configurations' main menu was clicked.");
		 corecollectionofficerconfigpage.clickCollectionOfficerConfigSubmenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Collection Officer Config' submenu was clicked.");
		 corecollectionofficerconfigpage.clickSearch();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Search' button was clicked.");
		 Assert.assertTrue(corecollectionofficerconfigpage.isUserGridDisplayed(userId), "Users matching the search criteria should appear in the grid.");
		 ExtentTestManager.getTest().log(Status.PASS, "User was fond in the grid.");
	        // Step 2: Click Save Configurations
		 corecollectionofficerconfigpage.clickIsActiveCheckboxIfUserFound(userId); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Collection Officer' checkbox was unchecked.");
		 corecollectionofficerconfigpage.clickSaveConfigurationsButton();  
		 ExtentTestManager.getTest().log(Status.PASS, "The configurations were saved.");
		 corecollectionofficerconfigpage.clickMyDeskMainMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'My Desk' main menu was clicked.");
	        // Click on Dashboard sub menu
		 corecollectionofficerconfigpage.clickDashboardSubMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Dashboard' submenu was clicked.");
		 corecollectionofficerconfigpage.clickBranchAttentionTile(); 
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Branch Attention Required' tile was clicked.");
		 corecollectionofficerconfigpage.clickAssignReassignDropdown();  
		 ExtentTestManager.getTest().log(Status.PASS, "The 'Assign/Reassign' dropdown was clicked.");
	        // Verify the expected users are listed
		 Assert.assertFalse(corecollectionofficerconfigpage.getListedUser(), "The expected user should not be visible under Assign/Reassign.");
		 ExtentTestManager.getTest().log(Status.PASS, "The listed users were observed and Unchecked users no longer appeared in the 'Assign/Reassign' dropdown.");
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
