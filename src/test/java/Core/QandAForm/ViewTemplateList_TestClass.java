package Core.QandAForm;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.Page_Repository.LoginBannerConfiRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.ViewTemplateList_Repository;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

import Core.Configurations.LoginbannerPage;



public class ViewTemplateList_TestClass extends Base_Class{
    WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ViewTemplateList_TestClass TestClass;
	ViewTemplateList_MainClass MainClass;
	ViewTemplateList_Repository Repository;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	
	@BeforeSuite
	public void reference() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		ExcelReader = new com.Utility.ExcelReader("ViewTemplateList");
		log = new Log();
		TestListener = new TestListener();
        Repository= new ViewTemplateList_Repository();
	}
	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) throws InterruptedException {
		baseclass = new Base_Class();
		driver = baseclass.getDriver();
		screenShot = new com.Utility.ScreenShot(driver);
		MainClass= new ViewTemplateList_MainClass(driver);
		
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("ViewTemplateList");
        Log.info("****" + method.getName() + "****");
        Thread.sleep(500);
    }
@Test(priority=1)
public void ClearalltheDatabaseTablesRelatedtoQandAForm(){
	try {
		ExtentTestManager.getTest().log(Status.PASS, "Execute the Deletion Query");
	MainClass.DeleteAnsDetails();
	MainClass.DeleteTempDetails();
	MainClass.DeleteAnsMaster();
	MainClass.DeleteTemplateDetails();
	MainClass.DeleteTemplateHistory();
	MainClass.DeleteCategoryMapping();
	MainClass.DeleteTemplateMaster();
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
}

@Test(priority=2)
public void LogintocoreapplicationasHOUser() throws Throwable {
	try {
		ExtentTestManager.getTest().log(Status.PASS, "Login with HOUser and HOPassword");
	 CoreAppLogin.CoreLogin();
}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}

@Test(priority=3)
public void NavigatetoViewTemplateList() throws Throwable {
	try {
		ExtentTestManager.getTest().log(Status.PASS, "Click on the  Q&A Form From menu section");
		WebElement QandAForm=driver.findElement(Repository.QandAMenu);
		Actions actions = new Actions(driver);
		actions.moveToElement(QandAForm).perform();
		QandAForm.click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Click on the submenu\"View template list");
		click(Repository.ViewTemplateListSubmenu);
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
public void NavigatetoTemplateCreationpage() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify Create New button is present in the page");
		MainClass.IsCreateNewPresent();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Click on the Create New Button");
		click(Repository.createNewButton);
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}
@Test(priority=5)
public void VerifytheFieldsandButtons() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify field\"Template name\" is present");
		MainClass.IsTemplateNamePresent();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Question\" is present");
		MainClass.IsQuestionPresent();
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Expected Answer Type\" is present");
		MainClass.IsExpectedAnswerTypePresent();
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Values\" is present");
		MainClass.IsValuesPresent();
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Parent Question\" is present");
		MainClass.IsParentQuestionPresent();
		
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Parent Value\" is present");
		MainClass.IsParentValuePresent();
		
		WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify field \"Is Mandatory checkbox\" is present");
		MainClass.IsMandatoryPresent();
		
		WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Button \"Add\" is present");
		MainClass.IsAddButtonPresent();
		
		WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Button \"Clear\" is present");
		MainClass.IsClearButtonPresent();
		
		WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Button \"Cancel\" is present");
		MainClass.IsCancelButtonPresent();
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
public void VerifytheColumnsShowingIntheGridsection() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Ordernumber\" is present");
		MainClass.IsCol_OrdernumberPresent();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Question\" is present");
		MainClass.IsCol_QuestionPresent();
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Expected Ans Type\" is present");
		MainClass.IsCol_ExpectedAnsTypePresent();
		
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Values\" is present");
		MainClass.IsCol_ValuesPresent();
		
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Parent Question\" is present");
		MainClass.IsCol_ParentQuestionPresent();
		
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Parent Values\" is present");
		MainClass.IsCol_ParentValuePresent();
		
		WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Is mandatory\" is present");
		MainClass.IsCol_IsMandatoryPresent();
		
		WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
		ExtentTestManager.getTest().log(Status.PASS, "Verify Column \"Action\" is present");
		MainClass.IsCol_ActionPresent();
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
public void VerifytheMandatoryfieldValidation() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Take template creation page");
		ExtentTestManager.getTest().log(Status.PASS, "Leave all the fields");
		ExtentTestManager.getTest().log(Status.PASS, "Click on the add button");
		click(Repository.Button_Add);
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.warningmsg();
		Assert.assertTrue(MainClass.warningmsg(),"Mandatory field validation msg is not displayed");
		ExtentTestManager.getTest().log(Status.PASS, "Verify mandatory field validation is showing");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}

@Test(priority=8,dataProvider="TestData")
public void InputvalueintoTemplateNamefield(Map<Object, Object> testdata) throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on Template Name Field");
		click(Repository.Field_TemplateName);
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		    String TemplateName = (testdata.get("Template Name").toString());
			System.out.println(TemplateName);
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.InputTemplateName(TemplateName);
		}
		ExtentTestManager.getTest().log(Status.PASS, "Enter Template name as \"sample template\"");
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}
@Test(priority=9,dataProvider="TestData")
public void InputvalueintoQuestionfield(Map<Object, Object> testdata) throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Click on Question Field");
		click(Repository.Field_Question);
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		    String Question = (testdata.get("Question").toString());
			System.out.println(Question);
		
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.InputQuestion(Question);
		}
		ExtentTestManager.getTest().log(Status.PASS, "Enter Question as \"Q1\"");
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
public void ExpectedAnswerTypeFieldValues() throws Throwable {
	try
	{
		click(Repository.Field_ExpectedAnsType);
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
		ExtentTestManager.getTest().log(Status.PASS, "Verify the presence of Expected Answer Type Fields values such as Textbox,DropDown,Date Picker,Number input field,Amount field,percentage field");
		MainClass.presenceofExpectedAnswerTypeFieldsvalues();
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
public void ValuesFieldNonEditablecheck() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Textbox\"from \"Expected answer type\"dropdown -Check Values field is non editable ");
		click(Repository.textbox);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(90));
		MainClass.NonEditablilityOfField_Values();
		Assert.assertTrue(MainClass.NonEditablilityOfField_Values());
		click(Repository.ClearIcon);
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
		
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Date Picker\"from \"Expected answer type\"dropdown -Check Values field is non editable ");
		click(Repository.Field_ExpectedAnsType);
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	    click(Repository.datepicker);
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.NonEditablilityOfField_Values();
		Assert.assertTrue(MainClass.NonEditablilityOfField_Values());
		click(Repository.ClearIcon);
		WebDriverWait wait4=new WebDriverWait(driver,Duration.ofSeconds(90));
		
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Number Input Field\"from \"Expected answer type\"dropdown -Check Values field is non editable ");
		click(Repository.Field_ExpectedAnsType);
		WebDriverWait wait5=new WebDriverWait(driver,Duration.ofSeconds(60));
	    click(Repository.numberInputField);
		WebDriverWait wait6=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.NonEditablilityOfField_Values();
		Assert.assertTrue(MainClass.NonEditablilityOfField_Values());
		click(Repository.ClearIcon);
		WebDriverWait wait7=new WebDriverWait(driver,Duration.ofSeconds(90));
		
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Amount Field\"from \"Expected answer type\"dropdown -Check Values field is non editable ");
		click(Repository.Field_ExpectedAnsType);
		WebDriverWait wait8=new WebDriverWait(driver,Duration.ofSeconds(60));
	    click(Repository.amountField);
		WebDriverWait wait9=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.NonEditablilityOfField_Values();
		Assert.assertTrue(MainClass.NonEditablilityOfField_Values());
		click(Repository.ClearIcon);
		WebDriverWait wait10=new WebDriverWait(driver,Duration.ofSeconds(90));
		
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Percentage Field\"from \"Expected answer type\"dropdown -Check Values field is non editable ");
		click(Repository.Field_ExpectedAnsType);
		WebDriverWait wait11=new WebDriverWait(driver,Duration.ofSeconds(60));
	    click(Repository.percentageField);
		WebDriverWait wait12=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.NonEditablilityOfField_Values();
		Assert.assertTrue(MainClass.NonEditablilityOfField_Values());
		click(Repository.ClearIcon);
		WebDriverWait wait13=new WebDriverWait(driver,Duration.ofSeconds(90));
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
public void ValuesFieldEditablecheck() throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Select \"Dropdown\"from \"Expected answer type\"dropdown -Check Values field is editable ");
		click(Repository.Field_ExpectedAnsType);
		WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	    click(Repository.dropdown);
		WebDriverWait wait3=new WebDriverWait(driver,Duration.ofSeconds(60));
		MainClass.EditablilityOfField_Values();
		Assert.assertTrue(MainClass.EditablilityOfField_Values());
		
	}
	catch(AssertionError|Exception e) {
		
		String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
        throw e;
	}
	Thread.sleep(3000);
}
@Test(priority=13,dataProvider="TestData")
public void ValuesFieldCommaSeperationCheck(Map<Object, Object> testdata)throws Throwable {
	try
	{
		ExtentTestManager.getTest().log(Status.PASS, "Select Dropdown from Expected answer type");
		ExtentTestManager.getTest().log(Status.PASS, "Enter values in the Values field with comma seperated");
        click(Repository.Field_Value);
		if(testdata.get("Run").toString().equalsIgnoreCase("Yes"))
		{
		    String Values = (testdata.get("Values").toString());
			System.out.println(Values);
			
			MainClass.SendValues(Values);
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
public void ExpectedAnswerTypeFieldSelect() throws Throwable{
	try
	{
		click(Repository.ClearIcon);
		WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(90));
	    click(Repository.Field_ExpectedAnsType);
	    WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(60));
	    ExtentTestManager.getTest().log(Status.PASS, "Select \"Textbox\" from Expected answer type dropdown");
	    click(Repository.textbox);

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
//        if (driver != null) {
//            driver.quit();
        }
        
}

