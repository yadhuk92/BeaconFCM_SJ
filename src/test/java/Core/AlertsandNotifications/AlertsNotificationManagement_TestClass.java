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
import com.BasePackage.Base_Class;
import com.BasePackage.Login_Class;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;

public class AlertsNotificationManagement_TestClass {
	
	Base_Class baseclass;
	com.Utility.ExcelReader ExcelReader;
	WebDriver driver;
	private List<WebDriver> drivers = new ArrayList<>();
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	ExtentTest extenttest;
	Login_Class corelogin;
	AlertsNotificationManagement_MainClass alertsnotificationmanagement_mainclass;
	String firstRowText,searchValue,templatename,keyword;
	
	@BeforeClass

	public void SetUp() throws Exception {
		
		baseclass = new Base_Class();
		TestListener = new TestListener();
	}
	
	@BeforeMethod
    public void setupTest(Method method) {
		driver = baseclass.getDriver(); 
		drivers.add(driver);
		alertsnotificationmanagement_mainclass = new AlertsNotificationManagement_MainClass(driver);
	    screenShot = new com.Utility.ScreenShot(driver);
	    ExcelReader = new com.Utility.ExcelReader("AlertsNotificationManagement");
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreAlertsNotificationManagement");
    }
	
	@Test(priority = 1)
    public void Test_Login_with_Valid_Credentials() throws Exception { 
	 try {
	 Login_Class.CoreLogin();
	 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid username.");
	 ExtentTestManager.getTest().log(Status.PASS, "Entered a valid password.");
	 ExtentTestManager.getTest().log(Status.PASS, "Clicked the login button.");
	 driver = baseclass.getDriver();
	 alertsnotificationmanagement_mainclass = new AlertsNotificationManagement_MainClass(driver); 
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
	        boolean isVisible = alertsnotificationmanagement_mainclass.isAlertsAndNotificationsMenuVisible();
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
			 alertsnotificationmanagement_mainclass.clickOnAlertsAndNotifications();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the \"Alerts and Notifications\" menu.");
	        // Assert expected outcome: Sub-menus are displayed
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.areSubMenusDisplayed(), "Sub-menus are not displayed as expected.");
	        ExtentTestManager.getTest().log(Status.PASS, " Application displays all related sub-menus under the \"Alerts and Notifications\" menu.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 4)
	    public void Verify_Notification_Management_Menu_Display() throws InterruptedException {
		 try {
	        // Click on the Notification Management menu
		 alertsnotificationmanagement_mainclass.clickNotificationManagementMenu();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the Notification Management menu.");
	        // Verify the menu is displayed
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isNotificationManagementMenuDisplayed(), "Notification Management menu should be displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "The Notification Management menu was displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 5)
	    public void Verify_Notification_Management_Type_Fields() throws InterruptedException {
		 try {
	        
	        // Assert Notification type field is visible
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isNotificationTypeFieldDisplayed(), 
	                          "Notification management type field is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked for the 'Notification Management type' field.");
	        // Assert SMS option is available
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isSMSOptionAvailable(), "SMS option is not available.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'SMS' was available.");
	        // Assert Email option is available
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isEmailOptionAvailable(), "Email option is not available.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Email' was available.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'SMS' and 'Email' options were correctly displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 6)
	    public void Verify_Category_Fields() throws InterruptedException {
		 try {
	        // Assert Category type field is visible
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isCategoryTypeFieldDisplayed(), 
	                          "Category field is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked for the 'Category' field.");
	        // Assert Promotions option is available
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isPromotionsOptionAvailable(), "Promotions option is not available.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Promotion' was available.");
	        // Assert Notification option is available
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isNotificationOptionAvailable(), "Notification option is not available.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Notification' was available.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Promotion' and 'Notification' options were correctly displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 7)
	    public void Verify_Schedule_Type_Fields() throws InterruptedException {
		 try {
	        // Step 1: Verify the "Schedule type" field is displayed
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isScheduleTypeFieldDisplayed(), "Schedule type field is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked for the 'Schedule type' field.");
	        // Step 2: Verify that the options "One time", "Daily", "Weekly", and "Monthly" are available
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isOneTimeOptionDisplayed(), "\"One time\" option is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'One time' was available.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isDailyOptionDisplayed(), "\"Daily\" option is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Daily' was available.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isWeeklyOptionDisplayed(), "\"Weekly\" option is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Weekly' was available.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isMonthlyOptionDisplayed(), "\"Monthly\" option is not displayed");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Monthly' was available.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'One time', 'Daily', 'Weekly', and 'Monthly' options were correctly displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    } 
	 
	 @Test(priority = 8)
	    public void Verify_Status_Fields() throws InterruptedException { 
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isStatusFieldPresent(), "Status field is not present");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked for the 'Status' field.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isActiveOptionAvailable(), "Active option is not available");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Active' was available.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isInactiveOptionAvailable(), "Inactive option is not available");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the option 'Inactive' was available.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Active' and 'Inactive' options were correctly displayed.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 9)
	    public void Verify_Schedule_Type_Column_Display() throws InterruptedException {
		 try {
	        // Validate that the "Schedule type" column is visible
	        boolean isDisplayed = alertsnotificationmanagement_mainclass.isScheduleTypeColumnDisplayed();
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Schedule type' column was displayed.");
	        Assert.assertTrue(isDisplayed, "The 'Schedule type' column should be visible and correctly labeled.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Schedule type' column was visible and correctly labeled.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 10)
	    public void Verify_Template_Name_Column_Display() throws InterruptedException {
		 try {
	        // Assert that the "Template name" column is displayed
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isTemplateNameColumnDisplayed(), 
	                          "Template name column should be visible and correctly labeled.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Template name' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Template name' column was visible and correctly labeled.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);  
	    }
	 
	 @Test(priority = 11)
	    public void Verify_Schedule_Start_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isScheduleStartColumnDisplayed(), "Schedule start column is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Schedule start' column was displayed in dd/mm/yyyy format.");
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.areScheduleStartDatesValid(), "Schedule start column is not in the format dd-mm-yyyy.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Schedule start' column was visible and correctly formatted as dd/mm/yyyy.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);  
	    }
	 
	 @Test(priority = 12)
	    public void Verify_Schedule_End_Column_Display() throws InterruptedException { 
		 try {
	        // Verify Schedule End column is displayed
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isScheduleEndColumnDisplayed(), 
	                "Schedule End column is not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Schedule end' column was displayed in dd/mm/yyyy format.");
	        // Verify Schedule End column format is dd-mm-yyyy
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isScheduleEndDateCorrectlyFormatted(), 
	                "Schedule End column is not in dd-mm-yyyy format.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Schedule end' column was visible and correctly formatted as dd/mm/yyyy.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 13)
	    public void Verify_Time_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isTimeColumnDisplayed(), "Time column should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Time' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Time' column was visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 14)
	    public void Verify_Recurrence_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isRecurrenceColumnDisplayed(), "Recurrence column should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Recurrence' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Recurrence' column was visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);  
	    }
	 
	 @Test(priority = 15)
	    public void Verify_Day_of_Week_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isDayOfWeekColumnDisplayed(), "Day of week column should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Day of week' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Day of week' column was visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);  
	    }
	 
	 @Test(priority = 16)
	    public void Verify_Day_of_Month_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isDayOfMonthColumnDisplayed(), "Day of month column should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Day of month' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Day of month' column was visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);   
	    }
	 
	 @Test(priority = 17)
	    public void Verify_Status_Column_Display() throws InterruptedException {
		 try {
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isStatusColumnDisplayed(), "Status column should be visible.");
	        ExtentTestManager.getTest().log(Status.PASS, "Checked if the 'Status' column was displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "The 'Status' column was visible.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);   
	    } 
	 
	 @Test(priority = 18)
	    public void Verify_the_pagination() throws InterruptedException {
		 try {
	        // Verify that pagination controls are present on the page
	        boolean isPagAvailable = alertsnotificationmanagement_mainclass.isPaginationAvailable();
	        ExtentTestManager.getTest().log(Status.PASS, "Verified if the pagination controls functionality was available or not.");
	        Assert.assertTrue(isPagAvailable, "Pagination should be available.");
	        ExtentTestManager.getTest().log(Status.PASS, "Pagination was available.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);   
	    }
	 
	 @Test(priority = 19, dataProvider = "TestData")
	    public void Verify_the_functionality_of_search_button(Map<Object, Object> testdata) throws InterruptedException {
		 try {
		 if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
		    	
			 keyword = testdata.get("ScheduleType").toString();
	        // Enter a keyword in the search box
			 alertsnotificationmanagement_mainclass.enterSearchKeyword(keyword);
			 ExtentTestManager.getTest().log(Status.PASS, "Entered a keyword in the search box.");
	        // Click on the search button
			 alertsnotificationmanagement_mainclass.clickSearchButton();
			 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the search button.");
	        // Assert that search results are displayed 
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.areResultsDisplayed(keyword), "Search results are not displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the search results.");
	        ExtentTestManager.getTest().log(Status.PASS, "Search results relevant to the entered keyword were displayed.");
		 }
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 20)
	    public void Verify_the_functionality_of_cross_button_in_input_fields() throws InterruptedException {
		 try {
	        // Step 1: Click on the cross button.
		 alertsnotificationmanagement_mainclass.clickCrossButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the cross button.");
	        // Step 2: Verify the field is cleared and focus remains.
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isInputFieldClearedAndFocused(), "Input field not cleared or focus not retained.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified that the field was cleared and the focus remained.");
	        ExtentTestManager.getTest().log(Status.PASS, "The input field was cleared, and the focus remained on the field.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 21)
	    public void Verify_Configure_Button_Click() throws InterruptedException {
		 try {
	        // Step 1: Click on the Configure button
		 alertsnotificationmanagement_mainclass.clickConfigureButton();
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Configure' button.");
	        // Step 2: Verify if the template selection interface opens
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isEditInterfaceDisplayed(),
	                "Template selection interface should be displayed.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified if the template selection interface opened.");
	        ExtentTestManager.getTest().log(Status.PASS, "Clicking the 'Configure' button opened the template selection interface.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000); 
	    }
	 
	 @Test(priority = 22)
	    public void Verify_UI_Elements_Presence() throws InterruptedException { 
		 try {
	        // Verify Notification Management Type dropdown is present
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isNotificationManagementTypeDropdownPresent(), "Notification Management Type dropdown should be present.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the Notification Management Type dropdown.");
	        // Verify Template Name dropdown is present
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isTemplateNameDropdownPresent(), "Template Name dropdown should be present.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the Template Name dropdown.");
	     // Verify View Template button is present
	        Assert.assertTrue(alertsnotificationmanagement_mainclass.isViewTemplateButtonPresent(), "View Template button should be present.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified the presence of the View Template button.");
	        ExtentTestManager.getTest().log(Status.PASS, "All elements were present: Notification Management Type dropdown, Template Name dropdown, and View Template button.");
		 }
			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	           throw e;
		 }
			Thread.sleep(3000);
	    }
	 
	 @Test(priority = 23)
	    public void Verify_Edit_Button_Functionality() throws InterruptedException {  
		 try {
	        // Step 1: Click on relevant section
		 alertsnotificationmanagement_mainclass.clickOnRelevantSection(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Navigated to the relevant section.");
	        // Step 2: Click on the "Edit" button of a row
		 alertsnotificationmanagement_mainclass.clickEditButton(); 
		 ExtentTestManager.getTest().log(Status.PASS, "Clicked on the 'Edit' button of a row.");
	        // Step 3: Verify if the editing interface opens correctly 
	        Assert.assertTrue( alertsnotificationmanagement_mainclass.isEditInterfaceDisplayed(),"Editing interface did not open.");
	        ExtentTestManager.getTest().log(Status.PASS, "Verified if the editing interface opened correctly.");
	        ExtentTestManager.getTest().log(Status.PASS, "The editing interface opened for the selected row.");
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