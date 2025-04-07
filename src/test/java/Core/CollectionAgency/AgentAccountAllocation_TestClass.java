package Core.CollectionAgency;

import static org.junit.Assert.fail;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;
import com.BasePackage.Login_Class;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.AgencyAccountAllocation_Repository;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;


public class AgentAccountAllocation_TestClass extends Base_Class {
	public String HOUserID;
	public String HOUserPassword;
	
	String UserID;
	String Password;
	
	public static String ActualValue;
	public static String ActualValueOutstandingAmt;
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
		screenShot = new com.Utility.ScreenShot(driver);
		CollectionAgency= new AgencyAccountAllocation_mainClass(driver);
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("AgencyAccountAllocation");
        Log.info("****" + method.getName() + "****");
        Thread.sleep(500);
    }

	
  @Test(priority=1)
    public void testUpdateProperties() {
        // Simulate the result from executeHOUserCreation
        String[] result = CollectionAgency.executeHOUserCreation();
        
        ExtentTestManager.getTest().log(Status.PASS, "Create new user in database");

        // Create the updates map
        Map<String, String> updates = Map.of(
            "HOUserID", result[0],
            "HOUserPassword", result[1]
        );

        // Initialize the PropertiesFileUtil
        String fileName = "CoreAgentAccountAllocation.properties";
        PropertiesFileUtil util;
        try {
            util = new PropertiesFileUtil(fileName);
        } catch (IOException e) {
            fail("Failed to initialize PropertiesFileUtil: " + e.getMessage());
            return;
        }

        // Update the properties file
        try {
            util.saveProperties(updates);
        } catch (IOException e) {
            fail("Failed to update properties: " + e.getMessage());
            return;
        }
 	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
        
    }

		    
	@Test(priority=2)
public void UpdateDataInDB() {
		try
		{
			ExtentTestManager.getTest().log(Status.PASS, "Update data in database");
			CollectionAgency.UPDATE_MST_account();
		}
		
    	catch(AssertionError|Exception e) {
    		
    		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
    	}
        
    }
	
		
@Test(priority=3)
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
	
@Test(priority=4)
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

@Test(priority=5)
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
		
	}
	
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}

@Test(priority=6)
public void AccountTypeDropdownBasicFunctionality() throws Throwable {
	try
	{
	CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);

		 
		click(PageRepository.TypesOfAccdrp);
		
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(90));
		   ExtentTestManager.getTest().log(Status.PASS, "Verify visibility of the 'Types of Account' dropdown");
			CollectionAgency.isTypesOfAccountDropdownVisible();
			WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(90));
			 ExtentTestManager.getTest().log(Status.PASS, "Allocated and Not Allocated option should be listed");
			 CollectionAgency.ValuesOfTypesOfDropdown();

		 WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(60));
	
		 click(PageRepository.NotAllocatedValue);

	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}
	
@Test(priority=7)
public void AssetCategoryDropdownBasicFunctionality() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify visibility of the 'Asset Category' dropdown.");
		CollectionAgency.isAssetCategoryvisible();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(90));
		CollectionAgency.clickassetCategory();
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofMillis(90));
		ExtentTestManager.getTest().log(Status.PASS, "Select NPA from 'Asset Category' dropdown.");
		CollectionAgency.SelectNPA();
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		Thread.sleep(1000);
		CollectionAgency.cancelAssetValue();
		
		ExtentTestManager.getTest().log(Status.PASS, "Select SMA from 'Asset Category' dropdown.");
		CollectionAgency.SelectSMA();
	}

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
}
Thread.sleep(3000);
}

@Test(priority=8)
public void SMACategoryActivation() throws Throwable {
	try
	{
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		ExtentTestManager.getTest().log(Status.PASS, "Verify activation of 'SMA Category' dropdown.");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
		click(PageRepository.SMAdropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'sma0', 'sma1', 'sma2'.");
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		click (PageRepository.SMA0);
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.SMA1);
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.SMA2);
			
	}

catch(AssertionError|Exception e) {
	
	String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
    throw e;
}
Thread.sleep(3000);
}

@Test(priority=9)
public void NPACategoryActivation() throws Throwable {
	try
	{
		click(PageRepository.closeAssetCategoryValue);
		
		ExtentTestManager.getTest().log(Status.PASS, "Verify activation of 'NPA Category' dropdown.");
		click(PageRepository.AssetCategory);
		click(PageRepository.NPACategory);
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Sub Standard', 'D1', 'D2','D3','Loss Asset'.");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.NPAdropdown);
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.SubStandard);
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D1);
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D2);
		
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.D3);
		
		WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.LossAsset);
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

@Test(priority=10)
public void zoneDropdownBasicFunctionality() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify visibility of the 'zone' dropdown.");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		CollectionAgency.Iszone_codVisible();
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Mumbai' Value from the dropdown.");
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.zone_co);
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.zoneMumbai);
		
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

@Test(priority=11)
public void zoneandRegionDropdownDependencyTest() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Select region.");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.regiondropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Mumabi1'region.");
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.regionMumbai1);
		
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

@Test(priority=12)
public void RegionandBranchDependency() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on region.");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.branchdropdown);
		
		ExtentTestManager.getTest().log(Status.PASS, "Select 'Amravati' region.");
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.branchSearch);
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(30));
		CollectionAgency.selectAmravati("Amar");
		
		click(PageRepository.AmravatiBranch);
		
		CollectionAgency.waitForSpinnerToStop(driver, PageRepository.spinner);
		
		click(PageRepository.branchdropdown);
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}


	@Test (priority=13, dataProvider= "TestData")
public void  SetDPDValue ( Map < Object,Object > testdata) throws Throwable {
	 
	try
	{
		 System.out.println("********test dpd*************");
		 if(testdata.get("Run").toString().equalsIgnoreCase("Yes")){ 

			 int dpdValue = Integer.parseInt(testdata.get("DPD").toString());
  			System.out.println(dpdValue);
 
		CollectionAgency.SetDPD(dpdValue);
		}	
  		
		ExtentTestManager.getTest().log(Status.PASS, "Set DPD value");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.DPDDropdown);
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.equalOperator);
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.DPDValue);
		
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			
  			String dpdValue = (testdata.get("DPD").toString());
  			System.out.println(dpdValue);
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		CollectionAgency.DPDValue(dpdValue);
		}
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}



@Test(priority=14)
public void SearchButtonFunctionality() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on Search button");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));

    	WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		click(PageRepository.searchButton);
		
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

 @Test(priority=15,dataProvider="TestData")
public void OutputGridDataVerification(Map<Object, Object> testdata) throws Throwable {
//	String ActualValue;
	String ExpectedValue;
//	String ActualValueOutstandingAmt;
	String ExpectedOutstandingAmt;
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify total account selected");
		ActualValue=CollectionAgency.TotalAccount();
	    System.out.println(ActualValue);

	    if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			
  			int dpdValue = Integer.parseInt(testdata.get("DPD").toString());
  			System.out.println(dpdValue);

		ExpectedValue=CollectionAgency.DBValueTotalAccountSelected(dpdValue);
		System.out.println(ExpectedValue);
		
		Assert.assertEquals(ActualValue, ExpectedValue);
		ExtentTestManager.getTest().log(Status.PASS, "Verified 'Total account selected'");
		}
		
		ExtentTestManager.getTest().log(Status.PASS, "Verify Outstanding amount");
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		
		ActualValueOutstandingAmt=CollectionAgency.OutstandingAmount();
		System.out.println(ActualValueOutstandingAmt);
		
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
			
  			int dpdValue = Integer.parseInt(testdata.get("DPD").toString());
  			System.out.println(dpdValue);

  			ExpectedOutstandingAmt=CollectionAgency.DBValueOutstandingAmount(dpdValue);
  			System.out.println(ExpectedOutstandingAmt);
		Assert.assertEquals(ActualValueOutstandingAmt.split("\\.")[0], ExpectedOutstandingAmt);
		}
		ExtentTestManager.getTest().log(Status.PASS, "Verified 'Outstanding amount'");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

@Test(priority=16)
public void AllocationDateNonEditability() throws Throwable {
	String Actual;
	String Expected;
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify the 'allocation date' field");
		Actual=CollectionAgency.AllocationDate();
		System.out.println(Actual);
		Expected=CollectionAgency.GetCurrentDate();
		Assert.assertEquals(Actual, Expected);
		
		ExtentTestManager.getTest().log(Status.PASS, "Date displayed as current date");
		
		 boolean isEditable = CollectionAgency.isAllocationDateEditable();
	        Assert.assertTrue(isEditable, "Allocation date field should not be editable.");
	        ExtentTestManager.getTest().log(Status.PASS, "Allocation date field should not be editable.");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}

@Test(priority=17)
public void AllocateButtonFunctionality() throws Throwable {
	try
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		WebElement CollectionAgencyDropdown=driver.findElement(PageRepository.SelectCollectionAgency);
		Actions actions = new Actions(driver);
		actions.moveToElement(CollectionAgencyDropdown).perform();
		CollectionAgencyDropdown.click();
		
		ExtentTestManager.getTest().log(Status.PASS, "Select first value form Collection Agency dropdown");
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
	    click(PageRepository.FirstCollectionAgencyName);
	    
		WebElement wb = driver.findElement(PageRepository.FirstCollectionAgencyName);
		 String PreviouslyAllocated= wb.getAttribute("aria-label");
		 System.out.println(PreviouslyAllocated);
		 
		 PreviouslyAllocatedAgency=PreviouslyAllocated.stripLeading().replaceFirst("^>", "");
		 System.out.println(PreviouslyAllocatedAgency);
	
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
    	ExtentTestManager.getTest().log(Status.PASS, "Click on Allocate button");

    	click(PageRepository.AllocateButton);
		
		// Switch to the alert
		Alert alert = driver.switchTo().alert();
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(90));

		// Click "Yes" (OK)
		alert.accept();
		
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageRepository.SuccessMsgForAllocation));
		
		CollectionAgency.AllocatedMsg();
		Assert.assertTrue(CollectionAgency.AllocatedMsg(),"Msg not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Success msg displayed after allocation");
		
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

@Test(priority=18)
public void VerifyAllocatedAccountsInDBTable() throws Throwable {
	try
	{

		String[] result=CollectionAgency.CompareDataInDB();
		
		    String ExpectedAccountCount = result[0];
		    System.out.println(ExpectedAccountCount);
	        String ExpectedSumOfOutstandingAmount = result[1];
	        System.out.println(ExpectedSumOfOutstandingAmount);
	        
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	    	ExtentTestManager.getTest().log(Status.PASS, "Verify total allocated account");
	    	System.out.println(ActualValue);
	        Assert.assertEquals(ActualValue, ExpectedAccountCount);
	        
	        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
	    	ExtentTestManager.getTest().log(Status.PASS, "Verify total sum of Outstanding amount");
	    	System.out.println(ActualValueOutstandingAmt);
	        Assert.assertEquals(ActualValueOutstandingAmt.split("\\.")[0], ExpectedSumOfOutstandingAmount);

	}catch(AssertionError|Exception e) {
			
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		    throw e;
		}
		Thread.sleep(3000);
		}

@Test(priority=19)
public void VerifyAllocatedListButtonispresent() throws Throwable {
	try
	{
		CollectionAgency.AllocatedListButton();
		ExtentTestManager.getTest().log(Status.PASS, "Verify Allocated List button is present");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	    ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	    Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	    throw e;
	}
	Thread.sleep(3000);
	}


@Test(priority=20)
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

@Test(priority=21)
public void AgencyNameDropdownBasicFunctionality() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on the Agency Name dropdown");
		click(PageRepository.AgencyNameDropdown);
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(120));
		click(PageRepository.AgencyNameSearch);
	
		ExtentTestManager.getTest().log(Status.PASS, "Select previously allocated agency from the list");	
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));

		WebElement wb =driver.findElement(PageRepository.AgencyNameSearch);
		wb.sendKeys(PreviouslyAllocatedAgency);

	WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(90));
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

@Test(priority=22)
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

@Test(priority=23)
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

@Test(priority=24)
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

@Test(priority=25)
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
@Test(priority=26)
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
		public void AfterClass() {
		     ExtentManager.getInstance().flush();
		  // Close the browser
//		        if (driver != null) {
//		            driver.quit();
		        
}
}
