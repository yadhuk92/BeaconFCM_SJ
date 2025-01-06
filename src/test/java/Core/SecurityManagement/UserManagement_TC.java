package Core.SecurityManagement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.UserManagement_Locators;
import Core.SecurityManagement.UserManagement_Page;
import com.Utility.FakerValue;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;
import static org.junit.Assert.assertTrue;

public class UserManagement_TC extends Base_Class {
	WebDriver driver;
	com.Utility.ExcelReader ExcelReader;
	Base_Class baseclass;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;
	UserManagement_Page User_UserManagementPage;
	UserManagement_Locators PageRepositry;
	LoginPageRepo LoginPageRepositry;
	ExtentTest extenttest;
	Login_Class CoreAppLogin;
	
	@BeforeSuite
	public void reference() throws Exception {
		baseclass = new Base_Class();
		CoreAppLogin = new Login_Class();
		ExcelReader = new com.Utility.ExcelReader("UserManagementModule");
		log = new Log();
		TestListener = new TestListener();
		CoreAppLogin.CoreLogin();
		driver = baseclass.getDriver();
		if (driver == null) {
		    throw new RuntimeException("WebDriver is not initialized!");
		}
		screenShot = new com.Utility.ScreenShot(driver);
		User_UserManagementPage= new UserManagement_Page();
		PageRepositry= new UserManagement_Locators();
	}
	
	@BeforeMethod
    public void ExtentTestManagerStartTest(Method method) {
        // Start a new ExtentTest for the current test method
        extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("Core - User management");
        Log.info("****" + method.getName() + "****");
    }
	
	@Test(priority = 1)
	public void Navigation_to_User_Management_Page() throws Throwable {
			try {
				Log.info("***Navigation to User Management Page***");
				//CoreAppLogin.CoreLogin();
				ExtentTestManager.getTest().log(Status.INFO, "Login successful");
				Log.info("Login successful !");
				User_UserManagementPage.SelectSecurityManagementMenu();
				ExtentTestManager.getTest().log(Status.PASS, "1. Click on Security Management menu");
				Log.info("1. Click on Security Management menu");
				boolean flag2 = User_UserManagementPage.SelectUserManagementMenu();
				ExtentTestManager.getTest().log(Status.PASS, "2. Click on User Management submenu");
				Log.info("2. Click on User Management submenu"+flag2);
				
				boolean flag3=User_UserManagementPage.LocatorDisplayed();
				Log.info("All elements in UserManagementPage displayed : " + flag3);
				//boolean flag4=User_UserManagementPage.FetchTableHeaderName();
				//Log.info("Table headers is displayed : " + flag4);
				ExtentTestManager.getTest().log(Status.PASS, "User management page is displayed with search inputs,buttons and active roles grid.\n"
						+ "\n"
						+ "Confirm presence of User Name, Name, Mobile Number, Email ID, Role drop down, Is Active checkbox, Search button, Add User button.\n"
						+ "Confirm list columns: SL NO, USER NAME, NAME, ROLE, MOBILE NO, EMAIL ID, STATUS, ACTION: " + flag3);
			}catch (AssertionError | Exception e) {
				String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
		        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
		        throw e;
			}
	}	
				
	@Test(priority = 2)
	public void User_search_page__Default_State_of_IsActive_Checkbox() throws Throwable {
		try {
			Log.info("Check the default state of 'Is Active' checkbox.");
			ExtentTestManager.getTest().log(Status.INFO, "Check the default state of 'Is Active' checkbox.");
			//User_UserManagementPage.isIsActiveCheckboxSelected();
			
			//boolean flag5 = User_UserManagementPage.isIsActiveCheckboxSelectedOrNot();
			//By userManagementLocator = UserManagement_Locators.UserManagementPageIsActive;

	        // Use the helper method
	        boolean isActive = User_UserManagementPage.isIsActiveCheckboxSelectedOrNot(driver);
			
			//boolean isIsActiveCheckboxSelectedOrNot = User_UserManagementPage.isIsActiveCheckboxSelectedOrNot();
			if (isActive) {
                Log.info("IsActive Checkbox Selected by default");
                ExtentTestManager.getTest().log(Status.PASS, "IsActive Checkbox Selected by default: " + isActive);
            } else {
                Log.info("IsActive Checkbox is not Selected by default");
                ExtentTestManager.getTest().log(Status.FAIL, "IsActive Checkbox is not Selected by default: "+isActive);
            }
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}		
	}
	
	@Test(priority = 3)
	public void User_search_page__Pagination_of_Active_users() throws Throwable {
		try {
			ExtentTestManager.getTest().log(Status.INFO, "Observe the pagination showing under active users grid");
			
			boolean flag6 = User_UserManagementPage.DisplayUserManagementPreviousBtn();
			Log.info("Displayed UserManagement PreviousBtn : " + flag6);
			ExtentTestManager.getTest().log(Status.INFO, "Displayed previous button : " + flag6);
			
			boolean flag7 = User_UserManagementPage.DisplayUserManagementFirstPageBtn();
			ExtentTestManager.getTest().log(Status.INFO, "Displayed UserManagement FirstPageBtn : " + flag7);
			Log.info("Displayed UserManagement FirstPageBtn : " + flag7);
			
			boolean flag8 = User_UserManagementPage.DisplayUserManagementSecondPageBtn();
			Log.info("Displayed UserManagement SecondPageBtn : " + flag8);
			ExtentTestManager.getTest().log(Status.INFO, "Displayed UserManagement SecondPageBtn : " + flag8);
			
			boolean flag9 = User_UserManagementPage.DisplayUserManagementNextBtn();
			Log.info("Displayed UserManagement NextBtn : " + flag9);
			ExtentTestManager.getTest().log(Status.INFO, "Displayed UserManagement NextBtn : " + flag9);
			
			boolean flag10 = User_UserManagementPage.DisplayUserManagementLastArrowBtn();
			Log.info("Displayed User search page - Pagination of Active users : " + flag10);
			ExtentTestManager.getTest().log(Status.INFO, "Displayed User search page - Pagination of Active users : " + flag10);
			
			ExtentTestManager.getTest().log(Status.PASS, "Pagination option should be displayed, showing 10 users per page and should show Next, 1,2(Indicating page numbers) and >> (Indication of redirecting to last page) buttons: " + flag10);
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
		
	}
	
	@Test(priority = 4)
	public void User_search_page_Pagination_of_Active_users_Second_page_button_functionality_and_data_checking() throws Throwable {
		try {
	
				boolean flag11 = User_UserManagementPage.ClickUserManagementSecondPageBtn();
				Log.info("Click on the '2' button in pagination showing under active users grid :" + flag11);
				ExtentTestManager.getTest().log(Status.INFO, "Click on the '2' button in pagination showing under active users grid");
				ExtentTestManager.getTest().log(Status.PASS, "Should navigate into 2nd page of the active users list : " + flag11);
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
		
	}
	
	@Test(priority = 5)
	public void User_search_page_Pagination_of_Active_users_Last_page_button_functionality_and_data_checking() throws Throwable {
		try {
				
				boolean flag12 = User_UserManagementPage.ClickUserManagementLastArrowBtn();
				ExtentTestManager.getTest().log(Status.PASS, "Click on the '>>' button in pagination showing under active users grid");
				ExtentTestManager.getTest().log(Status.PASS, "Should navigate into last page of the active users list: " + flag12);
				Log.info("Clicked on the '>>' button in pagination" + flag12);
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
	}
	
	@Test(priority = 6)
	public void User_search_page_Pagination_of_Active_users_Previous_page_button_functionality_and_data_checking() throws Throwable {
		try {
				boolean flag13 = User_UserManagementPage.ClickUserManagementPreviousArrowBtn();
				ExtentTestManager.getTest().log(Status.PASS, "Click on the 'Previous' button in pagination showing under active users grid");
				ExtentTestManager.getTest().log(Status.PASS, "Should navigate into 1st page of the active users list : " + flag13);
				Log.info("Should navigate into 1st page of the active users list : " + flag13);
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
		
	}
	
	@Test(priority = 7)
	public void Test_Redirect_to_Add_New_User_Page() throws Throwable {
		try {
			
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				ExtentTestManager.getTest().log(Status.PASS, "1. Click on Add User button");
				Log.info("1. Click on Add User button");
				
				ExtentTestManager.getTest().log(Status.PASS, "Expected result --> Page redirects to 'Add New User' page showing Name, Email, Phone number fields, Role and Organization Type drop-downs, Close and Submit buttons are visible.");
				Log.info("Expected result --> Page redirects to 'Add New User' page showing Name, Email, Phone number fields, Role and Organization Type drop-downs, Close and Submit buttons are visible.");
		} catch (AssertionError | Exception e) {
			String testName = new Object(){}.getClass().getEnclosingMethod().getName(); // Dynamically fetch test method name
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed in method: " + testName + " --> " + e.getMessage());
	        Log.info("****Test Failed in method: " + testName + " --> " + e.getMessage());
	        throw e;
		}
		
	}

				
			
			/*	
				
				String AddNewUserNameBtn = testdata.get("Name").toString();
				String AddNewUserEmailBtn = testdata.get("Email").toString();
				String AddNewUserPhoneNumberBtn = testdata.get("Phonenumber").toString();
				String AddNewUserRole = testdata.get("Testrole").toString();
				String AddNewUserOrganizationType = testdata.get("OrganizationType").toString();
				String AddNewUserOrganizationType2 = testdata.get("OrganizationType2").toString();
				String AddNewUserOrganizationType3 = testdata.get("OrganizationType3").toString();
				String AddNewUserOrganizationType4 = testdata.get("OrganizationType4").toString();
				String AddNewUserHeadOffice = testdata.get("HeadOffice").toString();
				String InvalidEmailId = testdata.get("InvalidEmailId").toString(); 
				String InvalidEmailIdWithoutDomain = testdata.get("EmailWithoutDomain").toString();
				String InvalidPhoneNumberWithLetters = testdata.get("NumberWithLetters").toString();
				String InvalidPhoneNumberWithEightDigits = testdata.get("NumberEightDigits").toString();
				String NamewithNumericInput = testdata.get("NumericInputName").toString();
				String NamewithAlphaNumericInput = testdata.get("AlphaNumericInputName").toString();
				String AddNewUserZoneCO = testdata.get("ZoneCO").toString();
				String AddNewUserRegion = testdata.get("Region").toString(); 
				String InvalidUserName = testdata.get("InvalidUserName").toString();
				String AddNewUserBranch = testdata.get("Branch").toString(); 
				FakerValue newData = new FakerValue();
				String username = newData.getFirstName();
				String UserManagementPageRole= testdata.get("UserManagementPageRole").toString(); 
				String UsernameInUNPage = testdata.get("UsernameInUNPage").toString();  
				String UNOriginal= testdata.get("UNOriginal").toString(); 
				String PwdOriginal= testdata.get("PwdOriginal").toString(); 
				
				ExtentTestManager.startTest("Add new user page - Organization Type Selection as Head office - Display Head Office Dropdown ");
				boolean EnterName = User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter name=kishan");
                Log.info("Entered valid Name is showing : " + EnterName);
                
                boolean EnterEmail = User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
                ExtentTestManager.getTest().log(Status.PASS, "2.Enter email=kishan@gmail.com");
                Log.info("Entered valid Email is showing : " + EnterEmail);
                
                boolean EnterPhonenumber = User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
                ExtentTestManager.getTest().log(Status.PASS, "3.Enter phone number=8089889900");
                Log.info("Entered valid Phonenumber is showing : " + EnterPhonenumber);
                
                boolean SelectTestrole = User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
                ExtentTestManager.getTest().log(Status.PASS, "4. Select role=TestRole");
                Log.info("Selected valid Testrole : " + SelectTestrole);
                
                boolean SelectOrganizationtype = User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
                ExtentTestManager.getTest().log(Status.PASS, "5. Select organization type=Head office");
                Log.info("Head office dropdown is selected : " + SelectOrganizationtype);
                
                boolean flag16 = User_UserManagementPage.DisplayAddNewUserHeadofficeBtn();
                ExtentTestManager.getTest().log(Status.PASS, " 6. Observe if there is any new field is appeared in the page");
                ExtentTestManager.getTest().log(Status.PASS, "Should show Head office drop down field in the add new user page : " + flag16);
                Log.info("Displayed Add New User Headoffice option : " + flag16);
                
                ExtentTestManager.startTest("Add new user page - Organization Type Selection as Zone/Co - Display Head office and Zone/CO drop down fields ");
                User_UserManagementPage.SelectOrganizationTypeDropdown2(AddNewUserOrganizationType2);
                ExtentTestManager.getTest().log(Status.PASS, "1.Enter name=kishan");
                ExtentTestManager.getTest().log(Status.PASS, "2.Enter email=kishan@gmail.com");
                ExtentTestManager.getTest().log(Status.PASS, "3.Enter phone number=8089889900");
                ExtentTestManager.getTest().log(Status.PASS, "4. Select role=TestRole");
                ExtentTestManager.getTest().log(Status.PASS, "5. Select organization type=Zone/CO");
                boolean flag18 = User_UserManagementPage.DisplayAddNewUserHeadofficeBtn();
                ExtentTestManager.getTest().log(Status.INFO, " Head office field is displayed");
                Log.info("Head office should display: " + flag18);
                boolean flag19 = User_UserManagementPage.DisplayAddNewUserZoneCOBtn();
                ExtentTestManager.getTest().log(Status.PASS, " 6. Observe if there is any new field is appeared in the page");
                ExtentTestManager.getTest().log(Status.PASS, "Head office and Zone/CO dropdown fields should display : " + flag19);
                Log.info("Displayed AddNewUser ZoneCOField : " + flag19);
                
				ExtentTestManager.startTest("Add new user page - Organization Type Selection as Region - Display Head office, Zone/CO and Region drop down fields");
                User_UserManagementPage.SelectOrganizationTypeDropdown3(AddNewUserOrganizationType3);
                ExtentTestManager.getTest().log(Status.PASS, "1.Enter name=kishan");
                ExtentTestManager.getTest().log(Status.PASS, "2.Enter email=kishan@gmail.com");
                ExtentTestManager.getTest().log(Status.PASS, "3.Enter phone number=8089889900");
                ExtentTestManager.getTest().log(Status.PASS, "4. Select role=TestRole");
                ExtentTestManager.getTest().log(Status.PASS, "5. Select organization type=Region");
				boolean flag20 = User_UserManagementPage.DisplayAddNewUserHeadofficeBtn();
				Log.info("Displayed AddNewUser HeadofficeField : " + flag20);
				ExtentTestManager.getTest().log(Status.INFO, " Head office field is displayed");
				boolean flag21 = User_UserManagementPage.DisplayAddNewUserZoneCOBtn();
				ExtentTestManager.getTest().log(Status.INFO, " Zone/CO field is displayed");
				Log.info("Displayed AddNewUser ZoneCOField : " + flag21);
				boolean flag22 = User_UserManagementPage.DisplayAddNewUserRegionBtn();
				ExtentTestManager.getTest().log(Status.PASS, " 6. Observe if there is any new field is appeared in the page");
				ExtentTestManager.getTest().log(Status.PASS, "Head office, Zone/CO and Region dropdown fields should display : " + flag22);
				Log.info("Displayed AddNewUser RegionField : " + flag22);
				
				ExtentTestManager.startTest("Add new user page - Organization Type Selection as Branch - Display Head office, Zone/CO, Region and Branch drop down fields");
				//Base_Class.input(L_signout, InvalidUserName);
				User_UserManagementPage.SelectOrganizationTypeDropdown4(AddNewUserOrganizationType4);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter name=kishan");
	            ExtentTestManager.getTest().log(Status.PASS, "2.Enter email=kishan@gmail.com");
	            ExtentTestManager.getTest().log(Status.PASS, "3.Enter phone number=8089889900");
	            ExtentTestManager.getTest().log(Status.PASS, "4. Select role=TestRole");
				ExtentTestManager.getTest().log(Status.PASS, "5. Select organization type=Branch");
				boolean flag23 = User_UserManagementPage.DisplayAddNewUserHeadofficeBtn();
				Log.info("Displayed AddNewUser HeadofficeField : " + flag23);
				ExtentTestManager.getTest().log(Status.INFO, " Head office field is displayed");
				boolean flag24 = User_UserManagementPage.DisplayAddNewUserZoneCOBtn();
				ExtentTestManager.getTest().log(Status.INFO, " Zone/CO field is displayed");
				Log.info("Displayed AddNewUser ZoneCOField : " + flag24);
				boolean flag25 = User_UserManagementPage.DisplayAddNewUserRegionBtn();
				Log.info("Displayed AddNewUser RegionField : " + flag25);
				ExtentTestManager.getTest().log(Status.INFO, " Region field is displayed");
				boolean flag26 = User_UserManagementPage.DisplayAddNewUserBranchBtn();
				ExtentTestManager.getTest().log(Status.PASS, " 6. Observe if there is any new field is appeared in the page");
				ExtentTestManager.getTest().log(Status.PASS, "Head office, Zone/CO, Region and Branch dropdown fields should display : " + flag26);
				Log.info("Displayed AddNewUser BranchField : " + flag26);
				
				ExtentTestManager.startTest("Add new user page - Submit Without Inputs ");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				boolean flag27 = User_UserManagementPage.ClickAddNewUserSubmitBtn();
				ExtentTestManager.getTest().log(Status.PASS, "1. Leave Name, Email, Phone number fields empty on");
				ExtentTestManager.getTest().log(Status.PASS, "2. Do not select any option for Role and Organization type dropdowns");
				ExtentTestManager.getTest().log(Status.PASS, "Clicked AddNewUser SubmitBtn : " + flag27);
				Log.info("Clicked AddNewUser SubmitBtn : " + flag27);
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Submit button");
				boolean flag28 = User_UserManagementPage.ErrormessageforAdduserPage();
				ExtentTestManager.getTest().log(Status.PASS, "Error messages: \"Name is required\", \"Email is required\", \"Phone number is required\", \"Role is required\", \"Organization type is required\" are displayed under corresponding fields : " + flag28);
				Log.info("Error message for AdduserPage displayed : " + flag28);
			
				ExtentTestManager.startTest("Add new user page - Enter and select inputs for all fields except Name Field and click on submit button");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				ExtentTestManager.getTest().log(Status.PASS, "1. Keep the Name field blank");
                User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
                ExtentTestManager.getTest().log(Status.PASS, "2.Enter valid inputs for Email and Phone number");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid option for Role");
                User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
                ExtentTestManager.getTest().log(Status.PASS, "4. Select option for Organization type=Head office");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "5. Select option for Head office=Mumbai");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "6.Click on Submit button");
				boolean flag29  = User_UserManagementPage.ErrormessageforNameField();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Error message: \"Name is required\" is displayed under Name field : " + flag29);
				
		        ExtentTestManager.startTest("Add new user page - Enter and select inputs for all fields except Email Field and click on submit button");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter a valid name");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "2.Leave the Email field blank");
				ExtentTestManager.getTest().log(Status.PASS, "3. Enter a valid phone number");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select valid option for Role");
                User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
                ExtentTestManager.getTest().log(Status.PASS, "5. Select option for Organization type=Head office.");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "6. Select option for Head office=Mumbai");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "7.Click on Submit button");
				boolean flag30  = User_UserManagementPage.ErrormessageforEmailField();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Error message for EmailField" + " is displayed under Email field.: " + flag30);
				Log.info("Error message for EmailField displayed : " + flag30);
			
				ExtentTestManager.startTest("Add new user page - Enter and select inputs for all fields except Phone number Field and click on submit button");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter a valid name and Email");
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Leave the Phone number field");
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid option for Role");
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				 ExtentTestManager.getTest().log(Status.PASS, "4. Select option for Organization type=Head office");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "5. Select option for Head office=Mumbai");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "6.Click on Submit button");
				boolean flag31  = User_UserManagementPage.ErrormessageforPhoneNumberField();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Error message: \"Phone number is required\" is displayed under Phone number field : " + flag31);
				Log.info("Error message for PhoneNumberField displayed : " + flag31);
				
				ExtentTestManager.startTest("Add new user page - Enter and select inputs for all fields except role drop down and click on submit button");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name, Email, and Phone number");
				ExtentTestManager.getTest().log(Status.PASS, "2.Do not select any option for Role");
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select option for Organization type=Head office");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "4. Select option for Head office=Mumbai");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "6.Click on Submit button");
				boolean flag32  = User_UserManagementPage.ErrormessageforRoleField();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Error message: \"Role is required\" is displayed under Role dropdown: " + flag32);
				Log.info("Error message for RoleField displayed : " + flag32);
			
			
				ExtentTestManager.startTest("Add new user page - Enter and select inputs for all fields except organization type drop down and click on submit button");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name, Email, and Phone number");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select valid option for Role");
				ExtentTestManager.getTest().log(Status.PASS, "3. Do not select any option for Organization type");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "4.Click on Submit button");
				boolean flag33  = User_UserManagementPage.ErrormessageforOrganizationTypeField();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Error message: \"Organization type is required\" is displayed under Organization type dropdown : " + flag33);
				Log.info("Error message for OrganizationTypeField displayed : " + flag33);
				
				
				ExtentTestManager.startTest("Add new user page - Invalid Email Format-missing '@ and domain'");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(InvalidEmailId);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name and Phone number");
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter an invalid email format (e.g., missing '@' )");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "4.Click on Submit button");
				boolean flag34  = User_UserManagementPage.ErrormessageforInvalidEmailId();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Should show error message 'Invalid Email Id with @': " + flag34);
				Log.info("Error message for Invalid Email Id is displayed : " + flag34);
				
		
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(InvalidEmailIdWithoutDomain);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name and Phone number");
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter an invalid email format (e.g., missing 'domain' )");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "4.Click on Submit button");
				boolean flag35  = User_UserManagementPage.ErrormessageforInvalidEmailId();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Should show error message 'Invalid Email Id' with domain : " + flag35);
				Log.info("Error message for Invalid Email Format is displayed : " + flag35);
				
				ExtentTestManager.startTest("Add new user page -Invalid Phone Number Format- with letters and digits");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name and Email");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(InvalidPhoneNumberWithLetters);
				ExtentTestManager.getTest().log(Status.PASS, " 2. Enter an invalid phone number format (e.g:letters)");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type.");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "4.Click on Submit button");
				boolean flag36  = User_UserManagementPage.ClickAddNewUserSubmitBtn();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Should show error message \"\"Invalid Mobile Number\" with letters: " + flag36);
				Log.info("Error message for Invalid PhoneNumber with letters is displayed : " + flag36);
				
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs for Name and Email");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(InvalidPhoneNumberWithEightDigits);
				ExtentTestManager.getTest().log(Status.PASS, " 2. Enter an invalid phone number format (e.g:8 digits)");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type.");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "4.Click on Submit button");
				boolean flag37  = User_UserManagementPage.ErrormessageforInvalidPhoneNumber();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Should show error message 'Invalid Mobile Number' with 8 digits : " + flag37);
				Log.info("Error message for Invalid PhoneNumber is displayed : " + flag37);
				
				
				ExtentTestManager.startTest("Add new user - Name Field - ECP Validations(Alphabetic inputs)");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(username);
				User_UserManagementPage.EnterAddNewUserEmail(newData.getEmail());
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid Email");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter an valid phone number format.");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type.");
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "4. For the Name field, test inputs with Alphabets characters");
                Thread.sleep(5000);
                Common.fluentWait("submit btn", PageRepositry.AddNewUserSubmitBtn);
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
				boolean flag38  = User_UserManagementPage.ClickAddNewUserSubmitBtn();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Alphabetic inputs are allowed.<br>Other inputs prompt error message \"Invalid Name\": " + flag38);
				Log.info("User created with Alphabetic inputs successfully: " + flag38);
				
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(NamewithNumericInput);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid Email");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter an valid phone number format.");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type.");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "4. For the Name field, test inputs with  Numeric characters");
                Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
				boolean flag39  = User_UserManagementPage.ErrormessageforInvalidName();
				User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Numeric inputs prompt error message 'Invalid Name': " + flag39);
				Log.info("Error message Invalid Name for numeric inputs is displayed: " + flag39);
				
				
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(NamewithAlphaNumericInput);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid Email");
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter an valid phone number format.");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select valid options for Role and Organization type.");
                User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
                ExtentTestManager.getTest().log(Status.PASS, "4. For the Name field, test inputs with  AlphaNumeric characters");
                Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
                Thread.sleep(5000);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
				boolean flag40  = User_UserManagementPage.ErrormessageforInvalidName();
				ExtentTestManager.getTest().log(Status.PASS, "Alpha Numeric inputs prompt error message 'Invalid Name' : " + flag40);
				Log.info("Error message Invalid Name for alphanumeric inputs is displayed: " + flag40);		
				
				ExtentTestManager.startTest("Create Head Office User - No Head Office Selected - Error message validation");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs in Name, Email, and Phone number fields");
                User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				Thread.sleep(5000);
				Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select 'Head Office' from Organization type dropdown");
                ExtentTestManager.getTest().log(Status.PASS, "3. Do not select any value from Head office dropdown");
                ExtentTestManager.getTest().log(Status.PASS, "4. Select a valid input from Role dropdown");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
				boolean flag41  = User_UserManagementPage.ErrormessageforEmptyHeadoffice();
				ExtentTestManager.getTest().log(Status.PASS, "Error message \"Head office is Required\" should show and the user is not created : " + flag41);
				Log.info("Error message for Empty HeadOffice is displayed: " + flag41);
				
				ExtentTestManager.startTest("Create Head Office User - Valid Inputs - Successful user creation ");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
			    User_UserManagementPage.EnterAddNewUserName(username);
				User_UserManagementPage.EnterAddNewUserEmail(newData.getEmail());
				User_UserManagementPage.EnterAddNewUserPhoneNumber(newData.getPhone());
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select 'Head Office' from Organization type dropdown");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select a valid value from Head office dropdown");
				ExtentTestManager.getTest().log(Status.PASS, "4. Select a valid input from Role dropdown");
				Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
                boolean flag43  = User_UserManagementPage.SuccessMessage();
				ExtentTestManager.getTest().log(Status.PASS, "Page redirects to user search page and should show Success message displays: \"User created successfully. Present password for the user: IBU000xxxx is xxxxxx\" and Newly created user appears at the top of the search results : " + flag43);
				Log.info("Success message for user creation is displayed: " + flag43);
				assertTrue("The table name value does not match the expected button name", 
				           User_UserManagementPage.FetchTableNameValue().equals(username));
				String message = User_UserManagementPage.GetUserNameandPassowrd(); 
		
				ExtentTestManager.startTest("Add new user - User already exists case");
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid same inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown(AddNewUserOrganizationType);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select 'Head Office' from Organization type dropdown");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select a valid value from Head office dropdown");
				ExtentTestManager.getTest().log(Status.PASS, "4. Select a valid input from Role dropdown");
				Common.fluentWait("SubmitBtn", PageRepositry.AddNewUserSubmitBtn);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "5.Click on Submit button");
                boolean flag42  = User_UserManagementPage.ErrorMessageExistUserCreation();
                User_UserManagementPage.AddNewUserSpinner();
				ExtentTestManager.getTest().log(Status.PASS, "Should show error message 'Already User Exist' " + flag42);
				Log.info("Error message on creating existing user is displayed: " + flag42);				 
		
				ExtentTestManager.startTest("Create Head Office User - Login with newly created user");
				User_UserManagementPage.ClickLogoutOption();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Common.fluentWait("L_SignIn", LoginPageRepo.LoginButton);
				Thread.sleep(5000);
			    String UNHeadoffice = message.substring(message.lastIndexOf(":") + 1, message.indexOf("is")).replaceAll(" ", "");
			    String PwdHeadoffice = message.substring(message.lastIndexOf("is") + 2).replaceAll(" ", "");
			    System.out.println(UNHeadoffice);
			    System.out.println(PwdHeadoffice);
				User_UserManagementPage.EnterLoginPageCredential(UNHeadoffice, PwdHeadoffice);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter user Name of Head office user in User Name field");
				ExtentTestManager.getTest().log(Status.PASS, "2.Enter Password of Head office user in Password field");
				User_UserManagementPage.ClickLoginBtn();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				Thread.sleep(5000);
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button");
				User_UserManagementPage.ModuleSelectionEltDisplayed();
				assertTrue("The UserName value does not match the expected button name", 
				           User_UserManagementPage.FetchUserName().equals(UNHeadoffice));
				assertTrue("The UserID value does not match the expected button name", 
				           User_UserManagementPage.UserId().equals(username));
                boolean flag44  = User_UserManagementPage.SetasDefaulNotSelected();
				ExtentTestManager.getTest().log(Status.PASS, "Should redirect to module selection page and should show Go Collection button, Set as Default checkbox, Go Recovery button and Set as Default checkbox. Also should show User ID and User name on top of left side: " + flag44);
				Log.info("Successfully redirect to module selection page with Headoffice credentials " + flag44);

				ExtentTestManager.startTest("Create Zone/CO User - No Zone/Co Selected - Error message validation");
				User_UserManagementPage.GoCollectionModule();
				User_UserManagementPage.SelectSecurityManagementMenu();
				User_UserManagementPage.SelectUserManagementMenu();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid same inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown2(AddNewUserOrganizationType2);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select 'Zone/CO' from Organization type dropdown");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select any value from Head office dropdown");
				ExtentTestManager.getTest().log(Status.PASS, "4. Do not select any value from Zone/CO dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "5. Select a valid input from Role dropdown");
				Common.fluentWait("Zone/CO", PageRepositry.ZoneCO);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "6.Click on Submit button");
                boolean flag45  = User_UserManagementPage.ErrorMessageForZoneCO();
				ExtentTestManager.getTest().log(Status.PASS, "Error message 'Zone/CO is Required' should show and the user is not created : " + flag45);
				Log.info("Error message Zone/CO Required is displayed: " + flag45);
			
				
				ExtentTestManager.startTest("Create Zone/CO User - Valid Inputs - Successful user creation");
				Log.info("***Create Zone/CO User - Valid Inputs - Successful user creation***");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(username);
				User_UserManagementPage.EnterAddNewUserEmail(newData.getEmail());
				User_UserManagementPage.EnterAddNewUserPhoneNumber(newData.getPhone());
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				User_UserManagementPage.SelectOrganizationTypeDropdown2(AddNewUserOrganizationType2); 
				ExtentTestManager.getTest().log(Status.PASS, "2. Select 'Zone/CO' from Organization type dropdown.");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select any value from Head office dropdown.");
				User_UserManagementPage.SelectAddNewUserZoneCO(AddNewUserZoneCO);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select any value from Zone/CO dropdown");
				ExtentTestManager.getTest().log(Status.PASS, "5. Select a valid input from Role dropdown");
				Common.fluentWait("Submit btn",PageRepositry.AddNewUserSubmitBtn);
				ExtentTestManager.getTest().log(Status.PASS, "6.Click on Submit button");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                boolean flag46  = User_UserManagementPage.SuccessMessage();
				ExtentTestManager.getTest().log(Status.PASS, "Page redirects to user search page and should show Success message displays: \"User created successfully. Present password for the user: IBU000xxxx is xxxxxx\" and Newly created user appears at the top of the search results. : " + flag46);
				String ZONECOmessage = User_UserManagementPage.GetUserNameandPassowrd();
				Log.info("Success message for user creation is displayed with Zone/CO inputs: " + flag46);
				assertTrue("The table name value does not match the expected button name", 
				           User_UserManagementPage.FetchTableNameValue().equals(username));
		
				ExtentTestManager.startTest("Create Zone/CO User - Login with newly created user ");
				User_UserManagementPage.ClickLogoutOption();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Common.fluentWait("L_SignIn", LoginPageRepo.LoginButton);
				Thread.sleep(5000);
			    String UNZoneCO = ZONECOmessage.substring(ZONECOmessage.lastIndexOf(":") + 1, ZONECOmessage.indexOf("is")).replaceAll(" ", "");
			    String PwdZoneCO = ZONECOmessage.substring(ZONECOmessage.lastIndexOf("is") + 2).replaceAll(" ", "");
			    System.out.println(UNZoneCO);
			    System.out.println(PwdZoneCO);
			    Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
			    Common.fluentWait("L_SignIn", LoginPageRepo.LoginButton);
				Thread.sleep(5000);
				User_UserManagementPage.EnterLoginPageCredential(UNZoneCO, PwdZoneCO);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter user Name of Zone/Co user in User Name field.");
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter Password of Zone/CO user in Password field");
				User_UserManagementPage.ClickLoginBtn();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				Thread.sleep(5000);
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button.");
				Thread.sleep(2000);
				User_UserManagementPage.ModuleSelectionEltDisplayed();
				assertTrue("The UserName value does not match the expected button name", 
				           User_UserManagementPage.FetchUserName().equals(UNZoneCO));
				assertTrue("The UserID value does not match the expected button name", 
				           User_UserManagementPage.UserId().equals(username));
                boolean flag47 = User_UserManagementPage.SetasDefaulNotSelected();
				ExtentTestManager.getTest().log(Status.PASS, "Should redirect to module selection page and should show Go Collection button, Set as Default checkbox, Go Recovery button and Set as Default checkbox. Also should show User ID and User name on top of left side. " + flag47);
  			    Log.info("Successfully redirect to module selection page with ZoneCO credentials" + flag47);
				
				ExtentTestManager.startTest("Create Region User - No Region Selected - Error message validation ");
				User_UserManagementPage.GoCollectionModule();
				User_UserManagementPage.SelectSecurityManagementMenu();
				User_UserManagementPage.SelectUserManagementMenu();   
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select a valid input from Role dropdown");
				User_UserManagementPage.SelectOrganizationTypeDropdown3(AddNewUserOrganizationType3);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select 'Region' from Organization type dropdown.");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select any value from Head office dropdown.");
				User_UserManagementPage.SelectAddNewUserZoneCO(AddNewUserZoneCO);
				ExtentTestManager.getTest().log(Status.PASS, "5. Select any value from Zone/CO dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "6. Do not select any value from Region drop down.");
				Common.fluentWait("Region", PageRepositry.Region);
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "7. Click on Submit button");
                boolean flag48  = User_UserManagementPage.ErrorMessageForRegion();
				ExtentTestManager.getTest().log(Status.PASS, "Error message 'Region is Required' should show and the user is not created. " + flag48);
				Log.info("Error message Region Required is displayed: " + flag48);
			
				ExtentTestManager.startTest("Create Region User - Valid Inputs - Successful user creation");
				User_UserManagementPage.ClickAddNewUserCloseBtn(); 
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(username);
				User_UserManagementPage.EnterAddNewUserEmail(newData.getEmail());
				User_UserManagementPage.EnterAddNewUserPhoneNumber(newData.getPhone());
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select a valid input from Role dropdown");
				User_UserManagementPage.SelectOrganizationTypeDropdown3(AddNewUserOrganizationType3);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select 'Region' from Organization type dropdown.");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select any value from Head office dropdown.");
				User_UserManagementPage.SelectAddNewUserZoneCO(AddNewUserZoneCO);
				ExtentTestManager.getTest().log(Status.PASS, "5. Select any value from Zone/CO dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "6. Do not select any value from Region drop down.");
				User_UserManagementPage.SelectAddNewUserRegion(AddNewUserRegion);
				Common.fluentWait("Submit btn",PageRepositry.AddNewUserSubmitBtn);
				ExtentTestManager.getTest().log(Status.PASS, "7. Click on Submit button");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                boolean flag49  = User_UserManagementPage.SuccessMessage();
				ExtentTestManager.getTest().log(Status.PASS, "Page redirects to user search page and should show Success message displays: \"User created successfully. Present password for the user: IBU000xxxx is xxxxxx\" and Newly created user appears at the top of the search results.: " + flag49);
				String Regionmessage = User_UserManagementPage.GetUserNameandPassowrd();
				Log.info("Success message for user creation is displayed with Region inputs: " + flag49);
				assertTrue("The table name value does not match the expected button name", 
				           User_UserManagementPage.FetchTableNameValue().equals(username));
				
				ExtentTestManager.startTest("Create Region User - Login with newly created user ");
				User_UserManagementPage.ClickLogoutOption();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Common.fluentWait("L_SignIn", LoginPageRepo.LoginButton);
				Thread.sleep(5000);
			    String UNRegion = Regionmessage.substring(Regionmessage.lastIndexOf(":") + 1, Regionmessage.indexOf("is")).replaceAll(" ", "");
			    String PwdRegion = Regionmessage.substring(Regionmessage.lastIndexOf("is") + 2).replaceAll(" ", "");
			    System.out.println(UNRegion);
			    System.out.println(PwdRegion);
				User_UserManagementPage.EnterLoginPageCredential(UNRegion, PwdRegion);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter user Name of Region user in User Name field");
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter Password of Region user in Password field.");
				User_UserManagementPage.ClickLoginBtn();
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button.");
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				Thread.sleep(5000);
				User_UserManagementPage.ModuleSelectionEltDisplayed();
				assertTrue("The UserName value does not match the expected button name", 
				           User_UserManagementPage.FetchUserName().equals(UNRegion));
				assertTrue("The UserID value does not match the expected button name", 
				           User_UserManagementPage.UserId().equals(username));
                boolean flag50 = User_UserManagementPage.SetasDefaulNotSelected();
				ExtentTestManager.getTest().log(Status.PASS, "Should redirect to module selection page and should show Go Collection button, Set as Default checkbox, Go Recovery button and Set as Default checkbox. Also should show User ID and User name on top of left side." + flag50);
				Log.info("Successfully redirect to module selection page with Region credentials " + flag50);
			
				ExtentTestManager.startTest("Create Branch User - No Branch Selected - Error message validation ");
				User_UserManagementPage.GoCollectionModule();
				User_UserManagementPage.SelectSecurityManagementMenu();
				User_UserManagementPage.SelectUserManagementMenu();   
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(AddNewUserNameBtn);
				User_UserManagementPage.EnterAddNewUserEmail(AddNewUserEmailBtn);
				User_UserManagementPage.EnterAddNewUserPhoneNumber(AddNewUserPhoneNumberBtn);
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select a valid input from Role dropdown");
				User_UserManagementPage.SelectOrganizationTypeDropdown4(AddNewUserOrganizationType4);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select 'Region' from Organization type dropdown.");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select any value from Head office dropdown.");
				Common.fluentWait("ZoneCO", PageRepositry.ZoneCO);
				User_UserManagementPage.SelectAddNewUserZoneCO(AddNewUserZoneCO);
				ExtentTestManager.getTest().log(Status.PASS, "5. Select any value from Zone/CO dropdown.");
				ExtentTestManager.getTest().log(Status.PASS, "6. Select any value from Region drop down.");
				Common.fluentWait("Region", PageRepositry.Region);
				User_UserManagementPage.SelectAddNewUserRegion(AddNewUserRegion);
				ExtentTestManager.getTest().log(Status.PASS, "7. Do not select any value from Region drop down.");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                ExtentTestManager.getTest().log(Status.PASS, "8. Click on Submit button");
                boolean flag51  = User_UserManagementPage.ErrorMessageForBranch();
				ExtentTestManager.getTest().log(Status.PASS, "Error message Branch Required should show and the user is not created: " + flag51);
				Log.info("Error message Branch Required is displayed: " + flag51);
		
				ExtentTestManager.startTest("Create Branch User - Valid Inputs - Successful user creation");
				User_UserManagementPage.ClickAddNewUserCloseBtn();
				User_UserManagementPage.ClickUserManagementPageAddUserBtn();
				User_UserManagementPage.EnterAddNewUserName(username);
				User_UserManagementPage.EnterAddNewUserEmail(newData.getEmail());
				User_UserManagementPage.EnterAddNewUserPhoneNumber(newData.getPhone());
				ExtentTestManager.getTest().log(Status.PASS, "1.Enter valid inputs in Name, Email, and Phone number fields");
				User_UserManagementPage.SelectRoleDropdown(AddNewUserRole);
				ExtentTestManager.getTest().log(Status.PASS, "2. Select a valid input from Role dropdown");
				User_UserManagementPage.SelectOrganizationTypeDropdown4(AddNewUserOrganizationType4);
				ExtentTestManager.getTest().log(Status.PASS, "3. Select 'Branch' from Organization type dropdown.");
				User_UserManagementPage.SelectHeadOfficeDropdown(AddNewUserHeadOffice);
				ExtentTestManager.getTest().log(Status.PASS, "4. Select any value from Head office dropdown.");
				Common.fluentWait("ZoneCO", PageRepositry.ZoneCO);
				User_UserManagementPage.SelectAddNewUserZoneCO(AddNewUserZoneCO);
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(Status.PASS, "5. Select any value from Zone/CO dropdown.");
				Common.fluentWait("Region", PageRepositry.Region);
				User_UserManagementPage.SelectAddNewUserRegion(AddNewUserRegion);
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(Status.PASS, "6. Select any value from Region drop down.");
				Common.fluentWait("Branch", PageRepositry.Branch);
				User_UserManagementPage.SelectAddNewUserBranch(AddNewUserBranch);
				Thread.sleep(2000);
				ExtentTestManager.getTest().log(Status.PASS, "7. Select any value from Branch drop down.");
				Common.fluentWait("Submit btn",PageRepositry.AddNewUserSubmitBtn);
				ExtentTestManager.getTest().log(Status.PASS, "8. Click on Submit button");
                User_UserManagementPage.ClickAddNewUserSubmitBtn();
                boolean flag52  = User_UserManagementPage.SuccessMessage();
                String Branchmessage = User_UserManagementPage.GetUserNameandPassowrd();
				ExtentTestManager.getTest().log(Status.PASS, "Page redirects to user search page and should show Success message displays: \"User created successfully. Present password for the user: IBU000xxxx is xxxxxx\" and Newly created user appears at the top of the search results: " + flag52);
				Log.info("Success message for user creation is displayed with Branch inputs: " + flag52);
				assertTrue("The table name value does not match the expected button name", 
				           User_UserManagementPage.FetchTableNameValue().equals(username));
			
				ExtentTestManager.startTest("Create Branch User - Login with newly created user ");
				User_UserManagementPage.ClickLogoutOption();
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Common.fluentWait("L_SignIn", LoginPageRepo.LoginButton);
				Thread.sleep(8000);
			    String UNBranch = Branchmessage.substring(Branchmessage.lastIndexOf(":") + 1, Branchmessage.indexOf("is")).replaceAll(" ", "");
			    String PwdBranch = Branchmessage.substring(Branchmessage.lastIndexOf("is") + 2).replaceAll(" ", "");
			    System.out.println(UNBranch);
			    System.out.println(PwdBranch);
			    Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				Thread.sleep(6000);
				User_UserManagementPage.EnterLoginPageCredential(UNBranch, PwdBranch);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter user Name of Branch user in User Name field.");
				ExtentTestManager.getTest().log(Status.PASS, "2. Enter Password of Branch user in Password field.");
				User_UserManagementPage.ClickLoginBtn();
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Login button.");
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Thread.sleep(5000);
				User_UserManagementPage.ModuleSelectionEltDisplayed();
				assertTrue("The UserName value does not match the expected button name", 
				           User_UserManagementPage.FetchUserName().equals(UNBranch));
				assertTrue("The UserID value does not match the expected button name", 
				           User_UserManagementPage.UserId().equals(username));
				boolean flag53 = User_UserManagementPage.SetasDefaulNotSelected();
                ExtentTestManager.getTest().log(Status.PASS, "Should redirect to module selection page and should show Go Collection button, Set as Default checkbox, Go Recovery button and Set as Default checkbox. Also should show User ID and User name on top of left side. : " + flag53);
				Log.info("Successfully redirect to module selection page with Branch credentials :" + flag53);
				
				ExtentTestManager.startTest("User search page - Search with Valid User Name");
				User_UserManagementPage.ClickLogoutOption();
				ExtentTestManager.getTest().log(Status.PASS, "Click logout button");
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.loginSelectionSpinner);
				Thread.sleep(6000);
				User_UserManagementPage.EnterLoginPageCredential(UNOriginal, PwdOriginal);
				User_UserManagementPage.ClickLoginBtn();
				ExtentTestManager.getTest().log(Status.PASS, "Click login button");
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				Thread.sleep(5000);
				User_UserManagementPage.SelectSecurityManagementMenu();
				Thread.sleep(1000);
				User_UserManagementPage.SelectUserManagementMenu(); 
				ExtentTestManager.getTest().log(Status.PASS, "Land to UserManagement page");
				Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.moduleSelectionSpinner);
				User_UserManagementPage.EnterUsernameInUNPage(UsernameInUNPage);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"IBU0001192\" into User Name field");
				User_UserManagementPage.ClickUserManagementPageSearchBtn();
				ExtentTestManager.getTest().log(Status.PASS, "2. Click on Search button");
				boolean flag54 = User_UserManagementPage.FetchTableUsername();
                ExtentTestManager.getTest().log(Status.PASS, "Search result displays user with columns: SL NO, USER NAME, NAME, ROLE, MOBILE NO, EMAIL ID, STATUS, ACTION with status green tick.: " + flag54);
				Log.info("User search page - Valid Username row is displayed" + flag54);
				
				ExtentTestManager.startTest("User search page - Role Dropdown Selection ");
				User_UserManagementPage.ClearUsernameInUNPage(); 
				Thread.sleep(1000);
				User_UserManagementPage.SelectUserManagementPageRole(UserManagementPageRole);
				ExtentTestManager.getTest().log(Status.PASS, "1. Select Admin Role from the role drop-down.");
				ExtentTestManager.getTest().log(Status.PASS, "2. Leave all other fields empty");
				User_UserManagementPage.ClickUserManagementPageSearchBtn();
				ExtentTestManager.getTest().log(Status.PASS, "3. Click on Search button");
				boolean flag55 = User_UserManagementPage.FetchHeaderRole();
                ExtentTestManager.getTest().log(Status.PASS, "Users matching the selected role are displayed. : " + flag55);
				Log.info("User search page - Role DropDown Selection is displayed" + flag55);			
			
				ExtentTestManager.startTest("User search page - Search with Invalid User Name ");
				User_UserManagementPage.ClearUserManagementPageRole();
				User_UserManagementPage.UsernameInput(InvalidUserName);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"InvalidUser\" into User Name field");
				User_UserManagementPage.UserManagementSearchBtn();
				ExtentTestManager.getTest().log(Status.PASS, "2. Click on Search button");
                boolean flag56  = User_UserManagementPage.UserManagementInvalidSearchMsg();
				ExtentTestManager.getTest().log(Status.PASS, "System displays \"No records to display.\" message. : " + flag56);
				Log.info("Error message No records to display is displayed: " + flag56);
							
				ExtentTestManager.startTest("User search page - Activate/Deactivate selection of an Active User ");
				User_UserManagementPage.ClearUsernameInUNPage(); 
				User_UserManagementPage.EnterUsernameInUNPage(UsernameInUNPage);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"IBU0001192\" into User Name field and click on Search button.");
				User_UserManagementPage.ClickUserManagementPageSearchBtn();
				boolean flag57  = User_UserManagementPage.StepsOnDeactivateOptn();
				Log.info("Success Status changed message is displayed: " + flag57);
                boolean flag58  = User_UserManagementPage.DisplayDeactivateRedStatus(); ;
				ExtentTestManager.getTest().log(Status.PASS, "\"Success Status Changed\" message appears. User is deactivated. Status changes to red cross on re-search with \"Is Active\" unchecked. : " + flag58);
				Log.info("Record deactivated, Red cross is displayed: " + flag58);
				Thread.sleep(1000);
				
				ExtentTestManager.startTest("User search page  - Activate/Deactivate selection of an Inactive User ");
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"IBU0001192\" into User Name field and click on Search button with \"Is Active\" unchecked\"");
				boolean flag59  = User_UserManagementPage.StepsOnActivateOptn(); 
				Log.info("Success Status changed message is displayed: " + flag59);
                boolean flag60  = User_UserManagementPage.DisplayActivateGreenStatus(); ;
				ExtentTestManager.getTest().log(Status.PASS, "Record Activated, Green cross" + " is displayed : " + flag60);
				Log.info("Record Activated, Green cross is displayed: " + flag60);
				
				ExtentTestManager.startTest("User search page  - Reset Password");
				User_UserManagementPage.ClearUsernameInUNPage();
				User_UserManagementPage.EnterUsernameInUNPage(UsernameInUNPage);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"IBU0001192\" into User Name field and click on Search button.");
				User_UserManagementPage.ClickUserManagementPageSearchBtn();
				boolean flag61  = User_UserManagementPage.ResetPassword(); 
				ExtentTestManager.getTest().log(Status.PASS, "'The password has been reset successfully. Present password for the user IBU0001192 is xxxxxxx' message is displayed : " + flag61);
				Log.info("Reset Password changed Status message is displayed: " + flag61);
				
				ExtentTestManager.startTest("User search page  -  Edit User Details");
				User_UserManagementPage.ClearUsernameInUNPage(); 
				User_UserManagementPage.EnterUsernameInUNPage(UsernameInUNPage);
				ExtentTestManager.getTest().log(Status.PASS, "1. Enter \"IBU0001192\" into User Name field and click on Search button.");
				User_UserManagementPage.ClickUserManagementPageSearchBtn();
				boolean flag62  = User_UserManagementPage.EditUserDetails(); 
				ExtentTestManager.getTest().log(Status.PASS, "User is redirected to edit page showing Name, Email, Phone number fields, Role and Organization Type drop-downs, Close and Update buttons. : " + flag62);
				Log.info("Edit User Details elements is displayed:" + flag62);
				
               	 
			// Logout  
			//ExtentTestManager.startTest("Application Logout Initiated.");
			context.setAttribute("fileName", "Logout");
			click(userDropDown);
			click(L_signout);
			Thread.sleep(2000);
			driver.quit();
			//ExtentTestManager.getTest().log(Status.PASS, "Application Logout");
			Log.info("Logout is done!");

			// EndTest
			System.out.println(("*** Test Suite " + testdata.get("TestScenario").toString() + " ending ***"));
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			Log.info("*** Test Suite " + testdata.get("TestScenario").toString() + " ending ***");

			}

		} catch (Exception e) {
			System.out.println("*** Test execution " + testdata.get("TestScenario").toString() + " failed...");
			Log.error("*** Test execution " + testdata.get("TestScenario").toString() + " failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName,
						testdata.get("TestScenario").toString());
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

			// Logout
			context.setAttribute("fileName", "Logout");
			driver.quit();
			ExtentTestManager.getTest().log(Status.PASS, "Application Logout");
			Log.info("Logout is done");

			// EndTest
			System.out.println(("*** Test Suite " + testdata.get("TestScenario").toString() + " ending ***"));
			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			Log.info("*** Test Suite " + testdata.get("TestScenario").toString() + " ending ***");
			
		} catch (AssertionError e) {
			System.out.println("*** Test execution " + testdata.get("TestScenario").toString() + " failed...");
			Log.error("*** Test execution " + testdata.get("TestScenario").toString() + " failed...");
			Log.error("" + e.getMessage());
			String fileName = (String) context.getAttribute("fileName");

			try {
				File file = new com.Utility.ScreenShot(driver).takeScreenShot(fileName,
						testdata.get("TestScenario").toString());
				ExtentTestManager.getTest().fail(e.getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(file.toString()).build());
			} catch (Exception e1) {
				System.out.println("File not found " + e1);
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");

			//Logout
			context.setAttribute("fileName", "Logout");
			driver.quit();
			ExtentTestManager.getTest().log(Status.PASS, "Application Logout");
			Log.info("Logout is done");

			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();
			Log.info("*** Test Suite " + testdata.get("TestScenario").toString() + " ending ***");
		}

	}*/
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.FAILURE) {
		        if (screenShot == null) {
		            System.err.println("ScreenShot object is not initialized!");
		            return;
		        }
	
		        String methodName = result.getMethod().getMethodName();
		        try {
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
	public void AfterClass() {
	     ExtentManager.getInstance().flush();
	  // Close the browser
	        if (driver != null) {
	            driver.quit();
	        }
	 }
	
	

}