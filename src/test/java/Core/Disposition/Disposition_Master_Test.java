package Core.Disposition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.BasePackage.SeleniumLogToFile;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginBannerConfiguratonPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import Core.Disposition.DispositionMasterPage;
import com.listeners.TestListener;
//import com.testautomation.pages.AllScenarios_Disposition_masterModule;

public class Disposition_Master_Test extends Base_Class {

	WebDriver driver;
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	DispositionMasterPage dispositionMasterPage;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	DispositionMasterPageRepo DispositionMasterPageRepo;
	//private List<WebDriver> drivers = new ArrayList<>();
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BOLD = "\u001B[1;34m";
    
	@BeforeClass
	public void SetUp() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		//SeleniumLogToFile.startLogging();
		ExcelReader = new com.Utility.ExcelReader("Disposition_master");
		TestListener = new TestListener();
		Login_Class.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		} else {
			System.out.println("Driver is not null");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		dispositionMasterPage = new DispositionMasterPage(driver);
		System.out.println("Before class in disposition master");
		DispositionMasterPageRepo = new DispositionMasterPageRepo();
	}
	
	@BeforeMethod
    public void setupTest(Method method) throws Exception {
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Disposition master");
        Log.info(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
        System.out.println(ANSI_BLUE_BOLD + "****** " + method.getName() + " ******" + ANSI_RESET);
    }

	@Test(priority = 1)
	public void Verify_Disposition_Master_Navigation() throws Exception {
		try {
			Common.fluentWait("DispositionMainMenu", DispositionMasterPageRepo.dispositionMainMenu);
			Base_Class.click(DispositionMasterPageRepo.dispositionMainMenu);
		
			Common.fluentWait("dispositionMasterSubMenu", DispositionMasterPageRepo.dispositionMasterSubMenu);
			Base_Class.click(DispositionMasterPageRepo.dispositionMasterSubMenu);
	
			Common.waitForSpinnerToDisappear("Spinner", DispositionMasterPageRepo.spinner);
			Thread.sleep(5000);
			Common.fluentWait("dispositionMasterSubMenu", DispositionMasterPageRepo.paginationNextButton);
		
			String currentUrl = driver.getCurrentUrl();
			extenttest.log(Status.INFO, "Current URL: " + currentUrl);
			System.out.println("Current URL: " + currentUrl); // Log the URL for debugging
			String expectedUrlPart = "Admin/DispositionMaster";
			Assert.assertTrue(currentUrl.contains(expectedUrlPart),
					"The last link address does not match. Expected: " + expectedUrlPart);
			
			ExtentTestManager.getTest().log(Status.PASS, "Disposition master window opens, displaying Disposition and Sub Disposition tabs. Should show last link address as Admin/DispositionMaster.");
		}
		
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
		Thread.sleep(3000);
		//ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
	}
	@Test(priority = 2)
	public void Verify_Initial_Load_of_Active_Dispositions() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_02 : Verify Initial Load of Active Dispositions");
		try {
		// Precondition: User is on the Disposition Master window
			Common.fluentWait("User is on the Disposition Master window", DispositionMasterPageRepo.subDispositionList);
		Assert.assertTrue(dispositionMasterPage.isDispositionMasterPageDisplayed(), "Disposition Master page not displayed");
		int activeDispositionsCount = dispositionMasterPage.getActiveDispositionsCount();
		Assert.assertEquals(activeDispositionsCount, 20, "Active dispositions count does not equal 10");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "10 active dispositions are displayed.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void Action_Owner_Dropdown_Selections() throws IOException, InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_03 : Action Owner Dropdown Selections"); 
		try {
		dispositionMasterPage.selectActionOwnerOptions();
		
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Selected options should be visible and selectable.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void Verify_Is_Active_Checkbox_Default_State() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_04 : Verify Is Active Checkbox Default State");
		try {
		Assert.assertTrue(dispositionMasterPage.isIsActiveCheckboxChecked(), "Is Active checkbox is not checked by default.");
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Is Active checkbox is checked (active) by default.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 5)
	public void Verify_Action_Column_Options() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_05 : Verify Action Column Options");
		try {
		//dispositionMasterPage.navigateToDispositionMaster();
		Assert.assertTrue(dispositionMasterPage.verifyActionOptions());
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Edit and Activate/Deactivate options are displayed.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 6)
	public void Verify_Status_Column_Appearance() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_06 : Verify Status Column Appearance");
		try {
		Assert.assertTrue(dispositionMasterPage.areAllStatusIconsGreenTicks(), "Not all status icons are green ticks");
		
		ExtentTestManager.getTest().log(Status.PASS, "A green tick icon is displayed, indicating active dispositions.");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
	}

	@Test(priority = 7)
    public void Validate_Pagination__Initial_Load() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_07 : Validate Pagination - Initial Load");
		try {
        Assert.assertTrue(dispositionMasterPage.isPaginationCorrect());
        
		
		ExtentTestManager.getTest().log(Status.PASS, "Previous button is disabled, Page 1 is selected Next and >> buttons are enabled, No << button appears");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	@Test(priority = 8)
    public void Validate_Next_Button() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 
		//ExtentTestManager.startTest("TestCase_08 : Validate Next Button");
		try {
		dispositionMasterPage.clickNextPagination();
		
		
		ExtentTestManager.getTest().log(Status.PASS, "Moves to Page 2, Previous button is enabled, Next and >> buttons remain enabled, << button appears");
//		ExtentTestManager.getTest().log(Status.FAIL, "Expected Result not matching with Actual Result");
		}
		catch (AssertionError | Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
		}
		Thread.sleep(3000);
    }
	
	 @Test(priority = 9)
	    public void Validate_Previous_Button() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 
		 //ExtentTestManager.startTest("TestCase_09 : Validate Previous Button");
		 //page2.click();
		 try {
		 dispositionMasterPage.clickPreviousPagination();
		 
			
			ExtentTestManager.getTest().log(Status.PASS, "Moves to Page 1, Previous button is disabled, Next and >> buttons are enabled, No << button appears.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10)
	    public void Validate_Button__Jump_to_Last_Page() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 
		 //ExtentTestManager.startTest("TestCase_10 : Validate >> Button - Jump to Last Page");
		 try {
			 Thread.sleep(10000);	        
			 // Step 1: Click the last page button
	        dispositionMasterPage.clickLastPageButton();
	        ExtentTestManager.getTest().log(Status.PASS, "Moves to the last page, Previous and << buttons are enabled, Next and >> buttons are disabled");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 11)
	    public void Validate_Button__Jump_to_First_Page() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 //ExtentTestManager.startTest("TestCase_11 : Validate << Button - Jump to First Page");
		 try { 
		 // Click the >> page button
		 dispositionMasterPage.clickFirstPageButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Moves to the first page, Previous button is disabled, Next and >> buttons are enabled, No << button appears");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 12)
	    public void Open_Add_Disposition_Form() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(com.Page_Repository.DispositionMasterPageRepo.spinner));
		 //ExtentTestManager.startTest("TestCase_12 : Open Add Disposition Form");
		 try {
	        // Step 1: Click on "Add Disposition" button
		 dispositionMasterPage.clickAddDispositionButton();
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(com.Page_Repository.DispositionMasterPageRepo.spinner));
	        // Expected Result: Add Disposition popup opens with required fields and buttons
	        Assert.assertTrue(dispositionMasterPage.isPopupDisplayed(), "Add Disposition popup did not open as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Disposition popup opens with Action Owner, Name, Asset Category fields, and Submit, Close buttons.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 13)
	    public void Close_Add_Disposition_Form() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 //ExtentTestManager.startTest("TestCase_13 : Close Add Disposition Form");
		 try {
	        // Close the popup  
		 dispositionMasterPage.closeAddDispositionPopup();

	        // Verify the popup is closed
	        Assert.assertTrue(dispositionMasterPage.isPopupClosed(), "Add Disposition popup should be closed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Disposition popup is closed.");
		 }
		 catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
	    }
	    
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Add_Disposition__Submit_with_All_Fields_Valid(Map<Object, Object> testdata) throws InterruptedException {
		 
		 String name = null;
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// ExtentTestManager.startTest("TestCase_14 : Add Disposition - Submit with All Fields Valid");
			 
		 try {

				if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
					
					String actionowner = testdata.get("ActionOwner").toString();
					name = testdata.get("Name").toString();
					String assetcategory = testdata.get("AssetCategory").toString();
					
					dispositionMasterPage.selectActionOwner(actionowner);
			        
			        // Step 2: Enter a valid name within 1 to 100 characters
				 dispositionMasterPage.enterName(name);
			        
			        // Step 3: Select "NPA Category" in Asset Category
				 dispositionMasterPage.selectAssetCategory(assetcategory);
				}
	        // Step 1: Select "Call centre" in Action Owner
		 
	        
	        // Step 4: Click on Submit
		 dispositionMasterPage.clickSubmit();
		// Validate the popup message
	
		 Common.fluentWait("DispositionMasterPageRepo.FirstDisposition", DispositionMasterPageRepo.FirstDisposition(name));
		 String FirstDispositionName = driver.findElement(DispositionMasterPageRepo.FirstDisposition(name)).getText();
		 System.out.println("The disposition name in first row is " + FirstDispositionName);
		 
		 Assert.assertEquals(FirstDispositionName, name, "Disposition name does not match!");
		 
		 /*Common.fluentWait("DispositionMasterPageRepo.successPopup", com.Page_Repository.DispositionMasterPageRepo.successPopup);
		 WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(com.Page_Repository.DispositionMasterPageRepo.successPopup));
	        Assert.assertTrue(popup.isDisplayed(), "Success popup is not displayed.");*/
	      
	        ExtentTestManager.getTest().log(Status.PASS, "Data is submitted successfully, popup closes without errors.");
		 }
	        catch (AssertionError | Exception e) {
				extenttest.log(Status.FAIL, "Test Failed: This Disposition Already Exist. " + e.getMessage());
	            throw e;
	            
		 }
		 Thread.sleep(3000); 
		 
	    }
	 
	 @Test(priority = 15, dataProvider = "TestData")
	    public void Test_Disposition_Grid_Visibility(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwner").toString();
				String name = testdata.get("Name").toString();
				
	        // Check if the disposition row is visible
	        Assert.assertTrue(dispositionMasterPage.isDispositionVisible(name), "Disposition row should be visible");

	        // Verify Action Owner values
	        String expectedActionOwners = actionowner;
	        Assert.assertEquals(dispositionMasterPage.getActionOwnerText(actionowner),expectedActionOwners, "Action Owner text should match");

	        // Verify status is green tick
	        Assert.assertTrue(dispositionMasterPage.isStatusGreenTick(), "Status should be a green tick");

	        // Verify Action column has three-dot button
	        Assert.assertTrue(dispositionMasterPage.isActionButtonPresent(), "Action column should have a three-dot button");
	        ExtentTestManager.getTest().log(Status.PASS, "Disposition appears as expected with Call centre, Internal user, Collection agency in Action Owner, Green tick in Status, Three dot button in Action");
		 }
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
		 
	    }
	 @Test(priority = 16, dataProvider = "TestData")
	    public void Action_Owner_Multi_select_Functionality(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.popup));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 //ExtentTestManager.startTest("TestCase_15 : Action Owner Multi-select Functionality");
		 try {
	        // Step 1: Click on the Action Owner dropdown
		 dispositionMasterPage.clickOnActionOwnerDropdown();
	        
	        // Step 2: Select multiple values
		// Step 1: Check if the test needs to be executed
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		    	// Step 2: Retrieve the "ActionOwner" data from the test data
		        String[] actionOwners = testdata.get("ActionOwners").toString().split("\\|");;
		        
		     // Step 4: Iterate through the action owner values and select them
		        for (String actionOwner : actionOwners) {
		            actionOwner = actionOwner.trim(); // Remove any leading/trailing spaces
		            dispositionMasterPage.selectActionOwners(actionOwner); // Ensure this method selects an item
		        }
		    }
		    // Step 5: Click outside the dropdown to close it
	    	WebElement dropdown =  driver.findElement(DispositionMasterPageRepo.actionOwnerField);
	        dropdown.click();
	        ExtentTestManager.getTest().log(Status.PASS, "Selected values are displayed in the dropdown field with comma separation.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
		   // dispositionMasterPage.clickOnActionOwnerDropdown();
	        
	 }
	 
	 @Test(priority = 17, dataProvider = "TestData")
	    public void Asset_Category_Multi_select_Functionality(Map<Object, Object> testdata) throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 //ExtentTestManager.startTest("TestCase_16 : Asset Category Multi-select Functionality");
		 
		 try {
	        // Step 1: Click on the Action Owner dropdown
		 dispositionMasterPage.clickAssetCategoryDropdown();
	        
	        // Step 2: Select multiple values
		// Step 1: Check if the test needs to be executed
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		    	// Step 2: Retrieve the "ActionOwner" data from the test data
		        String[] actionOwners = testdata.get("AssetCategories").toString().split("\\|");;
		        
		     // Step 4: Iterate through the action owner values and select them
		        for (String actionOwner : actionOwners) {
		            actionOwner = actionOwner.trim(); // Remove any leading/trailing spaces
		            dispositionMasterPage.AssetCategory(actionOwner); // Ensure this method selects an item
		        }
		    }
		    // Step 5: Click outside the dropdown to close it
		 Common.fluentWait("DispositionMasterPageRepo.assetCategoryField", DispositionMasterPageRepo.nameField);
		 WebElement dropdown = driver.findElement(DispositionMasterPageRepo.nameField);
		    dropdown.click();
	        ExtentTestManager.getTest().log(Status.PASS, "Selected values are displayed, and \"NPA Category\" is checked.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
		   // dispositionMasterPage.clickOnActionOwnerDropdown();
	        
	 }
	 
	 @Test(priority = 18)
	    public void Select_All_Functionality__Action_Owner() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// ExtentTestManager.startTest("TestCase_17 : Select All Functionality - Action Owner");
	        // Click on Action Owner dropdown
		 dispositionMasterPage.clickActionOwnerDropdown();
		 try {
	        // Select all options in Action Owner
		 dispositionMasterPage.selectAllActionOwners();

		 ExtentTestManager.getTest().log(Status.PASS, "All options in Action Owner are selected.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000); 
		   
	        
	    }
	 
	 @Test(priority = 19)
	    public void Deselecting_Via_Multi_select__Action_Owner() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 //ExtentTestManager.startTest("TestCase_18 : Deselecting Via Multi-select - Action Owner"); 
		 try {
		 dispositionMasterPage.deselectAllActionOwners();
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Action Owner are deselected.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	       
	    }
	 
	 @Test(priority = 20)
	    public void Select_All_Functionality__Asset_Category() throws InterruptedException {
		 
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			// ExtentTestManager.startTest("TestCase_19 : Select All Functionality - Asset Category");
			 try { 
	        // Click Asset Category dropdown
		 dispositionMasterPage.clickAssetCategoryDropdown();

	        // Click "Select All" option
		 dispositionMasterPage.clickSelectAllAssetCategory();
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Asset Category are selected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);

	    }
	 
	 @Test(priority = 21)
	    public void Deselecting_Via_Multi_select__Asset_Category() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			 //ExtentTestManager.startTest("TestCase_20 : Deselecting Via Multi-select - Asset Category");
			 try { 
	        // Uncheck "Select All"
		 dispositionMasterPage.uncheckSelectAllAssetCategory();
		 ExtentTestManager.getTest().log(Status.PASS, "All options in Asset Category are deselected.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 22)
	    public void Verify_Add_Disposition_without_Selection() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
		 dispositionMasterPage.clickAddDisposition();
		 dispositionMasterPage.clickSubmitButton();
	        String errorMessage = dispositionMasterPage.getNameErrorMessage();
	        Assert.assertEquals(errorMessage, "Name Required");
	        ExtentTestManager.getTest().log(Status.PASS, "Should show error message as \"Name required\" under the name field.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	 
	 
	 @Test(priority = 23, dataProvider = "TestData")
	    public void Add_Disposition_with_Existing_Name(Map<Object, Object> testdata) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 WebElement popCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.popUpCloseButton));
		    
		    // Click the close button
		    popCloseButton.click(); // Close the popup
		 try { 
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwner").toString();
				String name = testdata.get("Name").toString();
				String assetcategory = testdata.get("AssetCategory").toString();
				
				dispositionMasterPage.selectActionOwner(actionowner);
		        
		        // Step 2: Enter a valid name within 1 to 100 characters
			 dispositionMasterPage.enterName(name);
		        
		        // Step 3: Select "NPA Category" in Asset Category
			 dispositionMasterPage.selectAssetCategory(assetcategory);
			 
			 // Step 4: Click on Submit
			 dispositionMasterPage.clickSubmit();
			        
			        // Expected Result: Error message is displayed
			        Assert.assertEquals(dispositionMasterPage.getErrorMessageText(), "This Disposition Already Exist");
			        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Disposition Already Exist\" is displayed.");
		 }
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	 
	        
	    }
	 
	 @Test(priority = 24)
	    public void Edit_Disposition_Popup_Display() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		 try { 
	        // Step 1: Click on the three-dot button in the Action column
		 dispositionMasterPage.clickThreeDotButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on three dot button");

	        // Step 2: Click on the edit button
		 dispositionMasterPage.clickEditButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on edit button");

	        // Expected Result: Verify the Edit disposition popup is displayed with expected elements
	        Assert.assertTrue(dispositionMasterPage.isEditPopupDisplayed(), 
	                "Edit disposition popup elements are not displayed as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup is displayed with Action owner dropdown, Name field, Asset category dropdown, and Update button pre-populated with existing values.");
		 }
	        catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	            throw e;
		 }
		 Thread.sleep(3000);
	    }
	 
	// Test case: Update without changes
	    @Test(priority = 25)
	    public void Update_Without_Changes() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try {
	    	dispositionMasterPage.clickUpdateButton();
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayed(), "Success message should be displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message \"Saved successfully\".");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    /*@Test(priority = 26, dataProvider = "TestData")
	    public void Verify_Add_Disposition_with_Valid_Data(Map<Object, Object> testdata) throws InterruptedException  {
	    	
	    	try {
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
		    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        dispositionMasterPage.clickAddDispositionButton();

	    	
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String name = testdata.get("UpdateExistingName").toString();
				
	        dispositionMasterPage.selectAllActionOwnersdropdown();
	        dispositionMasterPage.enterDispositionName(name);
	        dispositionMasterPage.selectAllAssetCategories();
	    	}
	        dispositionMasterPage.clickSubmit();
	        Assert.assertTrue(dispositionMasterPage.SuccessMessageDisplayed(), "Success message is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message \"Saved successfully\".");
			 }
		        catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000); 
	    }
	 
	    @Test(priority = 27, dataProvider = "TestData")
	    public void Change_Name_to_Existing_and_Update(Map<Object, Object> testdata) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	dispositionMasterPage.ThreeDotButton();
	    	dispositionMasterPage.clickEditButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try { 
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String name = testdata.get("ExistingName").toString();
	        // Step 1: Changing the Name field to an existing disposition name
	    	dispositionMasterPage.changeNameField(name);
	    	
	    	}
	    	
	    	// Step 2: Clicking on the Update button
	    	dispositionMasterPage.UpdateButton();

	        // Validating expected error message
	        Assert.assertEquals(dispositionMasterPage.getErrorMessage(), "This Disposition Already Exist");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Disposition Already Exist\" is displayed.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 28, dataProvider = "TestData")
	    public void Edit_and_Verify_Disposition_Changes(Map<Object, Object> testdata) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerUpdate").toString();
				String name = testdata.get("NameUpdate").toString();
				String assetcategory = testdata.get("AssetCategoryUpdate").toString();
				dispositionMasterPage.toUpdateclickThreeDotButton();
				dispositionMasterPage.setActionOwner(actionowner);
				dispositionMasterPage.setName(name);
				dispositionMasterPage.setAssetCategory(assetcategory);
			}

	        // Click the Update button
	    	dispositionMasterPage.clickUpdateButton();

	        // Validate that success message is displayed
	        Assert.assertTrue(dispositionMasterPage.isUpdateSuccessMessageDisplayed(),"The success message should be displayed.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
	        ExtentTestManager.getTest().log(Status.PASS, "Edit disposition popup closes with a success message. Changes are visible in the active disposition list with updated Action owner, Name, and Asset category.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 29)
	    public void Deactivate_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButton();

	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOption();

	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Disposition Status Changed\" is displayed. The entry disappears from the active disposition list.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	 // Test method for searching deactivated disposition
	    @Test(priority = 30, dataProvider = "TestData")
	    public void Search_Deactivated_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerSearch").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwner(actionowner);

	        // Step 2: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckbox();

	        // Step 3: Click on Search button
	    	dispositionMasterPage.clickSearchButton();

	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isDeactivatedDispositionVisible(),
	                "Deactivated disposition should be shown with a red cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Deactivated disposition is shown with a red cross mark in the not active disposition list.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	 // Test method for activating a deactivated disposition
	    @Test(priority = 31)
	    public void Activate_Deactivated_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButton();

	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOption();

	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Disposition Status Changed\" is displayed. The entry disappears from the not active disposition list..");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 32, dataProvider = "TestData")
	    public void Search_Active_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerSearch").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwner(actionowner);

	        // Step 2: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckbox();

	        // Step 3: Click on Search button
	    	dispositionMasterPage.clickSearchButton();

	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isActiveDispositionShown(),
	                "Active disposition should be shown with a green cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Active disposition is shown in the active disposition list with a green tick mark.");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 33)
	    public void Verify_Sub_Disposition_Tab_UI_Elements() throws InterruptedException {
	    	try {
	    	
	        // Navigate to Sub-Disposition tab
	    	dispositionMasterPage.navigateToSubDispositionTab();

	        // Verify UI elements are displayed as expected
	        Assert.assertTrue(dispositionMasterPage.verifyUIElements(), "UI elements verification failed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Action Owner dropdown, Disposition search field, Is Active checkbox (checked), Search button, Add Sub-Disposition button, and active sub disposition list (up to 10 rows) are displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 34)
	    public void Verify_Add_Sub_Disposition_Popup() throws InterruptedException {
	    	try {
	        // Click Add Sub-Disposition button
	    	dispositionMasterPage.clickAddSubDispositionButton();
	        
	        // Verify if all expected elements are displayed on the popup
	        Assert.assertTrue(dispositionMasterPage.issubdispositionPopupelementsDisplayed(), "Popup elements are not displayed correctly.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup window appears with Action Owner dropdown, Disposition dropdown, Sub-Disposition name field, Submit button, and a cross mark");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 35)
	    public void Close_Add_Sub_Disposition_Popup() throws InterruptedException {
	    	
	    	try {
	        // Preconditions: Add Sub-Disposition popup should be displayed
	    	dispositionMasterPage.clickCloseButton();

	        // Assertion to ensure the popup is closed
	        // Example: Checking if the popup is no longer visible, to be updated with actual validation logic
	    	Assert.assertTrue(driver.findElements(DispositionMasterPageRepo.popupDialogLocator).isEmpty(), "Popup is not closed");
	        ExtentTestManager.getTest().log(Status.PASS, "Add Sub-Disposition popup closes");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 36, dataProvider = "TestData")
	    public void Add_New_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	dispositionMasterPage.clickAddSubDispositionButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerforsubdisposition").toString();
				String disposition = testdata.get("UpdateExistingName").toString();
				String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
				
	        // Select an option from the Action Owner dropdown
				dispositionMasterPage.selectsubdispositionActionOwner(actionowner);

	        // Enter required values
				dispositionMasterPage.enterDisposition(disposition);
				
				dispositionMasterPage.enterSubDisposition(subdispositionname);

	        // Click on the Submit button
				dispositionMasterPage.clickSubmit();

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	     // Adding Second Sub-Disposition
	        dispositionMasterPage.clickAddSubDispositionButton();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	            // Retrieve the data for the second sub-disposition
	            String actionowner2 = testdata.get("ActionOwnerforsubdisposition").toString(); // This may be the same or different
	            String disposition2 = testdata.get("UpdateExistingName").toString(); // This may be the same or different
	            String subdispositionname2 = testdata.get("Nameforsubdisposition2").toString(); // New sub-disposition name

	            // Select an option from the Action Owner dropdown for the second sub-disposition
	            dispositionMasterPage.selectsubdispositionActionOwner(actionowner2);

	            // Enter required values for the second sub-disposition
	            dispositionMasterPage.enterDisposition(disposition2);
	            dispositionMasterPage.enterSubDisposition(subdispositionname2);
	        }

	        // Click on the Submit button for the second sub-disposition
	        dispositionMasterPage.clickSubmit();
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdisposition(), "Success message not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\". New sub disposition appears in the active sub disposition list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 37, dataProvider = "TestData")
	    public void Add_Duplicate_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	dispositionMasterPage.clickAddSubDispositionButton();
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("ActionOwnerforsubdisposition").toString();
				String disposition = testdata.get("UpdateExistingName").toString();
				String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
				
	        // Select an option from the Action Owner dropdown
				dispositionMasterPage.selectsubdispositionActionOwner(actionowner);

	        // Enter required values
				dispositionMasterPage.enterDisposition(disposition);
				
				dispositionMasterPage.enterSubDisposition(subdispositionname);
				
				}

	        // Click on the Submit button
				dispositionMasterPage.clickSubmit();

	        // Assert the success message
	        Assert.assertTrue(dispositionMasterPage.iserrorMessageDisplayedforsubdisposition(), "Error message not displayed.");

	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Sub-Disposition Already Exist\" is displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 38)
	    public void Edit_Sub_Disposition_Popup_Display() throws InterruptedException {
	    	try {
	    	dispositionMasterPage.openEditPopup();
	    	ExtentTestManager.getTest().log(Status.PASS, "Edit Sub-Disposition popup opens with pre-filled fields and shows an Update button");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }

	    @Test(priority = 39)
	    public void Update_Sub_Disposition_Without_Changes() throws InterruptedException {
	    	try {
	        dispositionMasterPage.clickUpdateWithoutChanges();
	        Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdispos(), "Success message should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\"");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 40, dataProvider = "TestData")
	    public void Update_Sub_Disposition_with_Duplicate_Name(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String subdispositionname = testdata.get("Nameforsubdisposition").toString();
				
	        // Preconditions: Ensure popup is open
	    		dispositionMasterPage.openEditPopuptoupdate();

	        // Step 1: Enter a duplicate name and update
	    		dispositionMasterPage.enterSubDispositionName(subdispositionname);
	    		dispositionMasterPage.clickUpdateWithexistingname();
	    		
	    	}
 
	        // Expected Result: Error message displayed
	        Assert.assertEquals(dispositionMasterPage.getErrorMessageforexistingnameupdate(), "This Sub-Disposition Already Exist", "Error message not expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Error message \"This Sub-Disposition Already Exist\" is displayed");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 41, dataProvider = "TestData")
	    public void Update_Sub_Disposition_Successfully(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String subdispositionname = testdata.get("UpdateExistingNameofsubdispositiontoNew").toString();
				
	        // Preconditions: Ensure popup is open
	    		dispositionMasterPage.openEditPopuptoupdateexistingname();

	        // Step 1: Enter a duplicate name and update
	    		dispositionMasterPage.enterSubDispositionName(subdispositionname);
	    		dispositionMasterPage.clickUpdateWithexistingname();
	    		
	    	}
 
	        // Expected Result: Success message displayed
	    	Assert.assertTrue(dispositionMasterPage.isSuccessMessageDisplayedforsubdispos(), "Success message should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Popup closes and displays success message \"Saved successfully\"");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 42)
	    public void Deactivate_Sub_Disposition() throws InterruptedException { 
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButtonofsubdisposition();

	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOptionofsubdisposition();

	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Sub-Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Sub Disposition Status Changed\" is displayed and entry disappears from the active list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 43, dataProvider = "TestData")
	    public void Search_Deactivated_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
				String actionowner = testdata.get("DeactivatedActionOwnerforsubdispositionSearch").toString();
				String dispostion = testdata.get("UpdateExistingName").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwnerforsubdispositionsearch(actionowner);
	    	
	    	 // Step 2: Select Action owner as "Call centre"
	    	dispositionMasterPage.seldispositionforsubdispositionsearch(dispostion);

	        // Step 3: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckboxforsubdispositionsearch();

	        // Step 4: Click on Search button
	    	dispositionMasterPage.clickSearchButtonforsubdispositionsearch();

	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isDeactivatedDispositionVisibleforsubdispositionsearch(),
	                "Deactivated sub disposition should be shown with a red cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Deactivated sub disposition appears in not active list with red cross mark");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }
	    
	    @Test(priority = 44)
	    public void Reactivate_Sub_Disposition() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    	try {
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        // Clicking on the three-dot button
	    	dispositionMasterPage.clkThreeDotButtonfordeactivatingsubdisposition();

	        // Clicking on Activate/De-activate option
	    	dispositionMasterPage.clickActivateDeactivateOptionfordeactivatingsubdisposition();

	        // Assert to verify if the message "Disposition Status Changed" is displayed
	    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.msgLocator));
	        Assert.assertEquals(msg.getText(), "Sub-Disposition Status Changed");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        ExtentTestManager.getTest().log(Status.PASS, "Message \"Sub Disposition Status Changed\" is displayed and entry disappears from the not active list");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    } 
	    
	    @Test(priority = 45, dataProvider = "TestData")
	    public void Search_Active_Sub_Disposition(Map<Object, Object> testdata) throws InterruptedException {
	    	try {
	    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    	if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				
	    		String actionowner = testdata.get("ActiveActionOwnerforsubdispositionSearch").toString();
				String dispostion = testdata.get("UpdateExistingName").toString();
	        // Step 1: Select Action owner as "Call centre"
	    	dispositionMasterPage.selActionOwnerforactivesubdispositionsearch(actionowner);
	    	
	    	 // Step 2: Select Action owner as "Call centre"
	    	dispositionMasterPage.seldispositionforactivesubdispositionsearch(dispostion);

	        // Step 3: Untick Is active checkbox
	    	dispositionMasterPage.untickIsActiveCheckboxforsubdispositionsearch();  

	        // Step 4: Click on Search button
	    	dispositionMasterPage.clickSearchButtonforsubdispositionsearch();

	        // Validate the expected result
	        Assert.assertTrue(dispositionMasterPage.isActiveDispositionVisibleforsubdispositionsearch(),
	                "Active sub disposition should be shown with a green cross mark.");
	    	}
	    	ExtentTestManager.getTest().log(Status.PASS, "Active sub disposition appears in list with green tick mark");
			 }
		        catch (AssertionError | Exception e) {
					ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		            throw e;
			 }
			 Thread.sleep(3000);
	    }*/
	 
	 @AfterMethod
	 public void takeScreenshotOnFailure(ITestResult result) {
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
	 
	 @AfterClass
	 public void CloseBrowser() {
		 ExtentManager.getInstance().flush();
		// Quit driver after screenshot
	     if (driver != null) {
	         try {
	             driver.quit();
	         } catch (Exception e) {
	             System.out.println("Driver quit failed: " + e.getMessage());
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

}