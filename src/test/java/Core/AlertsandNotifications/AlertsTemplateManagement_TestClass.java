package Core.AlertsandNotifications;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
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
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsTemplateManagement_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsTemplateManagement_MainClass alertstemplatemanagement_mainclass;
	String firstRowText,searchValue,templatename;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		alertstemplatemanagement_mainclass = new AlertsTemplateManagement_MainClass(driver);
	    screenShot = new com.Utility.ScreenShot(driver);
	    ExcelReader = new com.Utility.ExcelReader("AlertsTemplateManagement");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core Alerts TemplateManagement");
    }
	
	 @Test(priority = 1)
	    public void Test_Login_with_Valid_Credentials() throws Exception { 
		 try {
		 Login_Class.CoreLogin();
		 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid username.");
		 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid password.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the login button.");
		 driver = baseclass.getDriver();
		 alertstemplatemanagement_mainclass = new AlertsTemplateManagement_MainClass(driver);
		 screenShot = new com.Utility.ScreenShot(driver);
	        // Assertion to verify that login is successful
	        Assert.assertEquals(driver.getCurrentUrl(), "http://192.168.32.33:8599/Home", "User is not logged into the Core application");
	        ExtentTestManager.getTest().log(Status.PASS, "The user successfully logged into the Core application.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 2)
	    public void Display_Alerts_and_Notifications_Menu_Option() throws InterruptedException {
		 try {
	        boolean isVisible = alertstemplatemanagement_mainclass.isAlertsAndNotificationsMenuVisible();
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the page after login.");
	        Assert.assertTrue(isVisible, "\"Alerts and Notifications\" menu should be visible after login.");
	        ExtentTestManager.getTest().log(Status.PASS, "\"Alerts and Notifications\" menu is visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	        
	    }
	 
	 @Test(priority = 3)
	    public void Access_Alerts_and_Notifications_Page() throws InterruptedException { 
		 try {
	        // Click on the "Alerts and Notifications" menu
		 alertstemplatemanagement_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Alerts and Notifications\" menu.");
	        // Assert expected outcome: Sub-menus are displayed
	        Assert.assertTrue(alertstemplatemanagement_mainclass.areSubMenusDisplayed(), "Sub-menus are not displayed as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, " Application displays all related sub-menus under the \"Alerts and Notifications\" menu.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4)
	    public void Navigate_to_Template_Management() throws InterruptedException {
		 try {
	        // Click on "Template Management"
		 alertstemplatemanagement_mainclass.clickTemplateManagement(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Template Management\" option.");
	        // Assert the user navigated to Template Management page
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isDisplayed(), "User did not navigate to Template Management page");
	        ExtentTestManager.getTest().log(Status.PASS, "User successfully navigated to the Template Management page.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5, dataProvider = "TestData")
	    public void Interact_with_Template_Management_Fields(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("NotificationType").toString();
		        String value2 = testdata.get("CurrentStatus").toString();
		        String value3 = testdata.get("Category").toString();
	        // Interacting with form fields
		        alertstemplatemanagement_mainclass.selectNotificationType(value1);
		        alertstemplatemanagement_mainclass.enterCurrentStatus(value2);
		        alertstemplatemanagement_mainclass.enterCategory(value3);
		        ExtentTestManager.getTest().log(Status.PASS, " Interacted with Notification Type, Current Status, and Category fields.");
		 }
	        // Clicking on filter button
		 alertstemplatemanagement_mainclass.clickFilterButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Applied filter based on selected values.");
	        // Validate that filtered templates are shown
	        Assert.assertTrue(driver.findElements(CoreAlertsTemplateManagementRepo.templateResults).size() > 1);
	        ExtentTestManager.getTest().log(Status.PASS, "Filtered templates are displayed successfully.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);

	    }
	 
	 @Test(priority = 6)
	    public void Clear_Input_Field_Test() throws InterruptedException {
		 try {
	        // Step 1: Click cross button
		 alertstemplatemanagement_mainclass.clickCrossButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Entered value in Notification Type.");
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the cross button.");
	        // Verify the field is cleared
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isNotificationTypeCleared(), "Notification Type field is not cleared!");
	        ExtentTestManager.getTest().log(Status.PASS, "The field is cleared.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Display_Template_Details_Table() throws InterruptedException {
		 try {
	        // Verify if the table structure is as expected
	        boolean isTableStructureCorrect = alertstemplatemanagement_mainclass.verifyTableStructure(); 
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the table structure.");
	        // Assert the expected result
	        Assert.assertTrue(isTableStructureCorrect, "Table structure is not as expected!");
	        ExtentTestManager.getTest().log(Status.PASS, "The table displays the Template Name, Status, Last Modified Date, along with options to Edit, View, and View History.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 8)
	    public void Test_Template_Pagination() throws InterruptedException { 
		 try {
	        int expectedRecordsPerPage = 10; // assuming 10 records per page
	        alertstemplatemanagement_mainclass.clickNextPage();
	        ExtentTestManager.getTest().log(Status.PASS, "Navigated through table pages.");
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isRecordCountCorrect(expectedRecordsPerPage), 
	                "The record count per page is incorrect");
	        ExtentTestManager.getTest().log(Status.PASS, "Pagination is working correctly, displaying the appropriate number of records per page.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9, dataProvider = "TestData")
	    public void SMS_Template_Body_Character_Limit(Map<Object, Object> testdata) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
		        String value1 = testdata.get("InitialStatusfortemplatecreation").toString();
		        String value2 = testdata.get("Categoryfortemplatecreation").toString();
		        String value3 = testdata.get("TemplateNamefortemplatecreation").toString();
		        String value4 = testdata.get("TemplateBody").toString();
	        // Step 1: Click "Add Template"
		        alertstemplatemanagement_mainclass.clickAddTemplate();

	        // Step 2: Enter mandatory fields
		        alertstemplatemanagement_mainclass.templateenterInitialStatus(value1);
		        alertstemplatemanagement_mainclass.templateenterCategory(value2);
		        
		        alertstemplatemanagement_mainclass.enterTemplateName(value3);
		        alertstemplatemanagement_mainclass.enterTemplatebodyforSMS(value4); 
		        ExtentTestManager.getTest().log(Status.PASS, "\"Attempted to enter more characters.\"");
	 }
		// Expected Result: Only 160 characters allowed, indicator shown
	        String actualCharCount = alertstemplatemanagement_mainclass.getCharCountIndicator(); 
	        Assert.assertEquals(actualCharCount, "160/160 characters", "Character count indicator should display '160 / 160'");
	        ExtentTestManager.getTest().log(Status.PASS, "Only 160 characters are allowed; an indicator is displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 10, dataProvider = "TestData")
	    public void Additional_Fields_for_Email_or_Notice(Map<Object, Object> testdata) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
			 
			 String value1 = testdata.get("NotificationTypefortemplatecreation").toString();
			 String value2 = testdata.get("TemplateBody").toString();
			 alertstemplatemanagement_mainclass.templateselectNotificationType(value1);
			 ExtentTestManager.getTest().log(Status.PASS, "\"Selected Email.");
			 alertstemplatemanagement_mainclass.enterTemplatebody(value2);
	 }
		
		 Assert.assertTrue(alertstemplatemanagement_mainclass.isSubjectFieldDisplayed(), "Subject field is not displayed");
		 ExtentTestManager.getTest().log(Status.PASS, "\"Observed additional fields.\"");
	        Assert.assertFalse(alertstemplatemanagement_mainclass.getBodyFieldTextLimit(), "Body field has a character limit");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Subject' field is available for Email, and there is no character limit for the Body.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    } 
	 
	 @Test(priority = 11, dataProvider = "TestData")
	    public void Add_New_Template(Map<Object, Object> testdata) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("NotificationTypefortemplatecreation").toString();
		        String value2 = testdata.get("InitialStatusfortemplatecreation").toString();
		        String value3 = testdata.get("Categoryfortemplatecreation").toString();
		        templatename = testdata.get("TemplateNamefortemplatecreation").toString();
		        String insertQuery = "Check_Insert_ALT_Template_Placeholder";

	        // Step 2: Enter mandatory fields
		        alertstemplatemanagement_mainclass.templateselectNotificationType(value1);
		        alertstemplatemanagement_mainclass.templateenterInitialStatus(value2);
		        alertstemplatemanagement_mainclass.templateenterCategory(value3);
		        
		        alertstemplatemanagement_mainclass.enterTemplateName(templatename);
		        List<String> result = alertstemplatemanagement_mainclass.prepareAndExecuteInsertQuery(insertQuery);
		        searchValue = result.get(1);
		        alertstemplatemanagement_mainclass.enterTemplatebody(searchValue);
	        
		        alertstemplatemanagement_mainclass.deleteAlertTemplate(templatename); 
		        ExtentTestManager.getTest().log(Status.PASS, "\"Entered mandatory fields.\"");
		     // Step 3: Click Save
		        alertstemplatemanagement_mainclass.clickSave();
		        ExtentTestManager.getTest().log(Status.PASS, "\"Clicked 'Save'.\"");
	 }
	        // Validating the expected result
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isSuccessMessageDisplayed(), "Success message not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Pop-up closed, success message displayed, and template listed.\"");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 12)
	    public void Save_New_Template() throws InterruptedException {
		 try {
	        // Verify the template is listed
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isTemplateListed(templatename), "Template is not listed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Pop-up closed, success message displayed, and template listed.\"");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13)
	    public void View_Template_Details() throws InterruptedException {
		 try {
		 alertstemplatemanagement_mainclass.clickViewButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked the View button for a template.");
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isTemplateDetailsDisplayed(), "Template details should be displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Details displayed as created.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    } 
	 
	 @Test(priority = 14, dataProvider = "TestData")
	    public void Edit_Existing_Template(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
		        String value1 = testdata.get("EditInitialStatusfortemplatemodify").toString();
		        String value2 = testdata.get("EditReason").toString();
	        // Step 1: Click Edit button
		 alertstemplatemanagement_mainclass.clickEditButton();
		 ExtentTestManager.getTest().log(Status.PASS, "\"Clicked the Edit button.\"");
	        // Step 2: Edit fields
		 alertstemplatemanagement_mainclass.editFields(value1);   
		 ExtentTestManager.getTest().log(Status.PASS, "\"Edited the fields.\"");
	        // Step 3: Enter reason for editing
		 alertstemplatemanagement_mainclass.enterEditReason(value2);
		 ExtentTestManager.getTest().log(Status.PASS, "\"Entered a reason for editing.\"");
		 }

	        // Step 4: Save changes
		 alertstemplatemanagement_mainclass.saveChanges(); 
		 ExtentTestManager.getTest().log(Status.PASS, "\"Saved the changes.\"");
	        // Expected Result: Validate changes saved and reason recorded (mocked by example)
	        Assert.assertTrue(driver.getPageSource().contains("Template modified successfully."), "Changes were not saved successfully.");
	       
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isTemplateStatusInactive(templatename), "Template '"+templatename+"' status is not 'Inactive'");
	        ExtentTestManager.getTest().log(Status.PASS, "\"Changes saved, and reason recorded.\"");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	 }
	  
	 @Test(priority = 15)
	    public void View_Template_History() throws InterruptedException {
		 try {
	        // Step 1: Click "View History"
		 alertstemplatemanagement_mainclass.clickViewHistory(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked 'View History'.");
	        // Expected Result: Validate pop-up displays history and remarks
	        Assert.assertTrue(alertstemplatemanagement_mainclass.isHistoryPopupDisplayed(), "History pop-up did not appear as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, "Pop-up displays history, fields, and remarks.");
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