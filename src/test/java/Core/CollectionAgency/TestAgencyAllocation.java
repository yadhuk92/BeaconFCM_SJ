package Core.CollectionAgency;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.AgencyAccountAllocation_Repository;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class TestAgencyAllocation extends Base_Class{
	public String HOUserID;
	public String HOUserPassword;
	
	String UserID;
	String Password;
	
	public static String PreviouslyAllocatedAgency;

	
    WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	AgentAccountAllocation_TestClass collectionAgency;
	AgencyAccountAllocation_mainClass CollectionAgency;

	AgencyAccountAllocation_Repository PageRepository;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	
	@BeforeSuite
	public void reference() throws Exception {
	
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		ExcelReader = new com.Utility.ExcelReader("Agency_Account_Allocation");
		log = new Log();
		TestListener = new TestListener();

		PageRepository= new AgencyAccountAllocation_Repository();
	}

	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) throws InterruptedException {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		CollectionAgency= new AgencyAccountAllocation_mainClass(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("AgencyAccountAllocation");
        Log.info("****" + method.getName() + "****");
        Thread.sleep(500);
    }
	
//	@Test(priority=1)
	public void DeleteDataInDB() {
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Delete data in database");
			CollectionAgency.DeleteAccAllocated();
			
			CollectionAgency.DeleteHistory();
			
			CollectionAgency.DeleteActivityMaster();
		}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
	}
	

	
	@Test(priority=2)
	public void LogInToApp() throws Throwable {

		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Login with HOUser and HOPassword");
			CoreAppLogin.CoreLoginWithInputs(HOUserID, HOUserPassword);
		}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
	}

	@Test(priority=3)
	public void NavigationToAgencyAccountAllocation() throws Throwable {
		try
		{
			
	   	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	   	  CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
	   	 
	    	ExtentTestManager.getTest().log(Status.PASS, "Click on Collection Agency main menu");
			CollectionAgency.clickCollectionAgencyMenu();
			
			 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(90));
			 ExtentTestManager.getTest().log(Status.PASS, "Click on Agency Account Allocation sub menu");
			 CollectionAgency.clickAgencyAccountAllocationMenu();
			 
			 CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
			
		}
		
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
		Thread.sleep(3000);
	}
	
	@Test(priority=4)
	public void VerifyPopupOpenswhenclickonAllocatedListbutton() throws Throwable {
		try
		{
	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.AllocatedListButton));
			ExtentTestManager.getTest().log(Status.PASS, "Click on the Allocated List button");
			click(PageRepository.AllocatedListButton);
			
			CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
			
			CollectionAgency.popup();
			Assert.assertTrue(CollectionAgency.popup(), "Pop up not displayed");
			
			ExtentTestManager.getTest().log(Status.PASS, "A popup opens displaying the input parameters and output grid with");
		}
			catch(AssertionError|Exception e) {
				
				String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
			    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
			    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
			    throw e;
			}
			Thread.sleep(5000);
			}

//	 @Test (priority=5)
	public void AgencyNameDropdownBasicFunctionality() throws Throwable {
		String PreviouslyAllocatedAgency="qwer";
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Click on the Agency Name dropdown");
			click(PageRepository.AgencyNameDropdown);
			
//			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
//			click(PageRepository.AgencyNameSearch);
		
			ExtentTestManager.getTest().log(Status.PASS, "Select previously allocated agency from the list");	
			WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));

			WebElement wb =driver.findElement(PageRepository.AgencyNameSearch);
			wb.sendKeys(PreviouslyAllocatedAgency);
			
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.AllocatedAgency);
		}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
		}
	
//	@Test(priority=6)
	public void ActionDateFromPicker() throws Throwable  {
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Click on the Action Date From date picker");

			
			ExtentTestManager.getTest().log(Status.PASS, "Select a date");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			

            	CollectionAgency.FromDate();
			
			
		}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
		}

//	@Test(priority=7)
	public void ActionDateToPicker() throws Throwable  {
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Click on the Action Date To date picker");
			
           ExtentTestManager.getTest().log(Status.PASS, "Select a date");
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			
     	CollectionAgency.ToDate();
			
			}
		
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
		}

//	@Test(priority=8)
	public void SearchButtonBasicFunctionality() throws Throwable {
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Click on the Search button");
			click(PageRepository.Search);
		}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
		}
	
	
//@Test(priority=9)
public void ValidateOutputColumnsInGrid() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Validate output columns in the grid");
				
		CollectionAgency.isSelectColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isSelectColumnDisplayed(), "Select Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Select Column is displayed");
		
		CollectionAgency.isAllocationIdColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isAllocationIdColumnDisplayed(), "AllocationID Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "AllocationID Column is displayed");
		
		CollectionAgency.isEinNumberColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isEinNumberColumnDisplayed(), "EIN No Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "EIN No Column is displayed");
		
		CollectionAgency.isOfficerNameColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isOfficerNameColumnDisplayed(), "Officer Name Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Officer Name Column is displayed");
		
		CollectionAgency.isDateColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isDateColumnDisplayed(), "Date Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Date Column is displayed");
		
		CollectionAgency.isNoOfAllocationsColumnDisplayed();
		Assert.assertTrue(CollectionAgency.isNoOfAllocationsColumnDisplayed(), "No of allocations Column is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "No of allocations Column is displayed");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

//@Test(priority=10)
public void DownloadExcelButtonEnabledOnlyWhenSelected_ValidateDataExcelFilename() throws Throwable{
	try
	{
	
	ExtentTestManager.getTest().log(Status.PASS, "Check Data is displayed");
	CollectionAgency.CheckBoxDispalyed();
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	ExtentTestManager.getTest().log(Status.PASS, "Select 1st checkbox");
	click(PageRepository.CheckBox);
	
	ExtentTestManager.getTest().log(Status.PASS, "Click On Download In Excel Button");
	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
	CollectionAgency.DownLoadExcelEnabled();
	click(PageRepository.DownloadInExcelButton);

	WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
    boolean isFileNameValid = CollectionAgency.isExcelFileNameValid("Allocation_List");
    Assert.assertTrue(isFileNameValid, "Expected valid Excel file name is missing or incorrect");
    ExtentTestManager.getTest().log(Status.PASS, "Validate the excel file name");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

//@Test(priority=11)
public void ResetButtonClearFilters() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click the Reset button at the bottom of the grid");
		click(PageRepository.ResetButton);
		Thread.sleep(2000);
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

//@Test(priority=12)
public void DownloadExcelDisabledWhenNoSelection() throws Throwable {
	try
	{
		CollectionAgency.DownLoadExcelDisabled();
		Assert.assertFalse(CollectionAgency.DownLoadExcelDisabled(), "Download in Excel button is not disabled");
		ExtentTestManager.getTest().log(Status.PASS, "Download in Excel button is disabled");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

//@Test(priority=13)
public void InvalidSearchwithEmptyFields() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on the Search button with no inputs");
		click(PageRepository.Search);
		CollectionAgency.WarningMsgForInvalidSearch();
		Assert.assertTrue(CollectionAgency.WarningMsgForInvalidSearch(), "Warning msg not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Warning msg displayed");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

//@Test(priority=14)
public void CloseAllocatedListPopup() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on the close button");
		click(PageRepository.CloseButton);
		ExtentTestManager.getTest().log(Status.PASS, "Verify popup is closed");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		CollectionAgency.isAgencyAllocationPageDisplayed();
		ExtentTestManager.getTest().log(Status.PASS, "Validate the page displayed after close");
		Assert.assertTrue(CollectionAgency.isAgencyAllocationPageDisplayed(), "The Page not redirected to Previous page");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

@Test (priority=15,dataProvider= "TestData")
public void VerifyDeallocatedAccountsFunctionality ( Map < Object,Object > testdata) throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Allocates' from 'Types of Account' dropdown");
		
		
//		WebElement TypesOfAccountDropdown=driver.findElement(PageRepository.TypesOfAccdrp);
		Actions actions = new Actions(driver);
//		actions.moveToElement(TypesOfAccountDropdown).perform();
//		TypesOfAccountDropdown.click();
		
		click(PageRepository.TypesOfAccdrp);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		click(PageRepository.Allocated);
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		CollectionAgency.clickassetCategory();
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
		ExtentTestManager.getTest().log(Status.PASS, "Select NPA from 'Asset Category' dropdown.");
		CollectionAgency.SelectNPA();
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Sub Standard', 'D1', 'D2','D3','Loss Asset'.");
		WebDriverWait wait30=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.NPAdropdown);
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.SubStandard);
		
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D1);
		
		WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D2);
		
		WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D3);
		
		WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.LossAsset);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Mumbai' Value from the dropdown.");
		WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.zone_co);
		
		WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.zoneMumbai);
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select region.");
		WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.regiondropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Mumabi1'region.");
		WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.regionMumbai1);
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		ExtentTestManager.getTest().log(Status.PASS, "Click on region.");
		WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.branchdropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Amravati' region.");
		WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.branchSearch);
		
		WebDriverWait wait14=new WebDriverWait(driver,Duration.ofSeconds(30));
		CollectionAgency.selectAmravati("Amar");
		
		click(PageRepository.AmravatiBranch);
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		click(PageRepository.branchdropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Set DPD value");
		WebDriverWait wait15=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.DPDDropdown);
		
		WebDriverWait wait16=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.equalOperator);
		
		WebDriverWait wait17=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.DPDValue);
		
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			
  			String dpdValue = (testdata.get("DPD").toString());
  			System.out.println(dpdValue);
		
		WebDriverWait wait18=new WebDriverWait(driver,Duration.ofSeconds(60));
		CollectionAgency.DPDValue(dpdValue);
		
		}
		ExtentTestManager.getTest().log(Status.PASS, "Click on Search button");
		WebDriverWait wait19=new WebDriverWait(driver,Duration.ofSeconds(60));

    	WebDriverWait wait20=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.searchButton);
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		WebDriverWait wait21=new WebDriverWait(driver,Duration.ofSeconds(60));
		WebElement CollectionAgencyDropdown=driver.findElement(PageRepository.SelectCollectionAgency);
		Actions actions1 = new Actions(driver);
		actions.moveToElement(CollectionAgencyDropdown).perform();
		CollectionAgencyDropdown.click();
		
		ExtentTestManager.getTest().log(Status.PASS, "Select first value form Collection Agency dropdown");
		WebDriverWait wait22=new WebDriverWait(driver,Duration.ofSeconds(90));
	    click(PageRepository.FirstCollectionAgencyName);
	    
		WebElement wb = driver.findElement(PageRepository.FirstCollectionAgencyName);
		 String PreviouslyAllocated= wb.getAttribute("aria-label");
		 System.out.println(PreviouslyAllocated);
		 
		 PreviouslyAllocatedAgency=PreviouslyAllocated.stripLeading().replaceFirst("^>", "");
		 System.out.println(PreviouslyAllocatedAgency);
		 
		 ExtentTestManager.getTest().log(Status.PASS, "Click on De-alloate button");	
		 click(PageRepository.DeAllocateButton);
			
			// Switch to the alert
			Alert alert = driver.switchTo().alert();
			WebDriverWait wait23=new WebDriverWait(driver,Duration.ofSeconds(90));

			// Click "Yes" (OK)
			alert.accept();
			
			WebDriverWait wait24=new WebDriverWait(driver,Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.SuccessMsgForAllocation));
			
			CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
	}
		catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
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
	public void AfterSuite() {
	     ExtentManager.getInstance().flush();
	  // Close the browser
//	        if (driver != null) {
//	            driver.quit();
	        }
}
