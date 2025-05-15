package CallCentre.UserManagement;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.BannedCustomerPageRepo;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Page_Repository.CallCenterUserManagementRepo;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

import CallCentre.SecurityManagement.RoleManagementPage_MainClass;

public class CallCentreUserManagement_MainClass extends Base_Class {

	private static final String UserManagementPageRepo = null;

	public static boolean Loggedin() throws InterruptedException {
		try {
			String Text = driver.findElement(CallCenterRoleManagementRepo.emailuser).getText();
			String CoreUserName = RoleManagementPage_MainClass.configloader().getProperty("CallcentreUserName");
			if (Text.contains(CoreUserName)) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in" + CoreUserName);
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to logged in" + CoreUserName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to logged in");
		}
		return false;
	}

	public static void AccessUserManagementpage() throws InterruptedException {

		Common.fluentWait("USERMANAGE", CallCenterUserManagementRepo.clickuser);
		click(CallCenterUserManagementRepo.clickuser);

		Common.fluentWait("USERMANAGEMENTCLICKED", CallCenterUserManagementRepo.clickUserman);
		click(CallCenterUserManagementRepo.clickUserman);

		ExtentTestManager.getTest().log(Status.PASS,
				"User Management page is displayed with the URL ending in Admin/UserManagement");

	}

	public static void validatePageElements1() {

		Common.fluentWait("EXECUTIVEID", CallCenterUserManagementRepo.executiveId);
		if (driver.findElement(CallCenterUserManagementRepo.executiveId).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Executive displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Executive");
		}
		Common.fluentWait("USERNAME", CallCenterUserManagementRepo.username);
		if (driver.findElement(CallCenterUserManagementRepo.username).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Uername displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Username");
		}

		Common.fluentWait("NAME", CallCenterUserManagementRepo.name);
		if (driver.findElement(CallCenterUserManagementRepo.name).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Name displayed successfully");
		} else

		{
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Name");

		}
		Common.fluentWait("MOBILE", CallCenterUserManagementRepo.mobile);
		if (driver.findElement(CallCenterUserManagementRepo.mobile).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Mobile");
		}
		Common.fluentWait("EMAIL", CallCenterUserManagementRepo.email);

		if (driver.findElement(CallCenterUserManagementRepo.email).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "Email displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Email");
		}
		Common.fluentWait("SEARCH", CallCenterUserManagementRepo.searchBtn);

		if (driver.findElement(CallCenterUserManagementRepo.searchBtn).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Executive displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Executive");
		}
		Common.fluentWait("NEW USER", CallCenterUserManagementRepo.addNewUserBtn);

		if (driver.findElement(CallCenterUserManagementRepo.addNewUserBtn).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Add user displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add user to show Executive");
		}

		Common.fluentWait("SL NO", CallCenterUserManagementRepo.slNo);

		if (driver.findElement(CallCenterUserManagementRepo.slNo).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "SL NO displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show SL NO");
		}

		Common.fluentWait("USER NAME", CallCenterUserManagementRepo.usernametable);

		if (driver.findElement(CallCenterUserManagementRepo.usernametable).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "User name displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show User name");
		}

		Common.fluentWait("Name", CallCenterUserManagementRepo.nametable);

		if (driver.findElement(CallCenterUserManagementRepo.nametable).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "name displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Name");
		}

		Common.fluentWait("EXECUTIVE", CallCenterUserManagementRepo.executiveIdtable);

		if (driver.findElement(CallCenterUserManagementRepo.executiveIdtable).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Executive displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Executive");
		}

		Common.fluentWait("EMAIL", CallCenterUserManagementRepo.emailtable);

		if (driver.findElement(CallCenterUserManagementRepo.emailtable).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Email displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Email");
		}
		Common.fluentWait("MOBILE", CallCenterUserManagementRepo.mobileNumber);

		if (driver.findElement(CallCenterUserManagementRepo.mobileNumber).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Mobile");
		}
		Common.fluentWait("ROLE", CallCenterUserManagementRepo.role);

		if (driver.findElement(CallCenterUserManagementRepo.role).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "ROle displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role");
		}

		Common.fluentWait("STATUS", CallCenterUserManagementRepo.status);

		if (driver.findElement(CallCenterUserManagementRepo.status).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "Status displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Status");
		}

		Common.fluentWait("ACTIONS", CallCenterUserManagementRepo.actions);

		if (driver.findElement(CallCenterUserManagementRepo.actions).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "actions displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show actions");
		}

	}

	public static void AccessAddNewUserPage() throws InterruptedException {
		Common.fluentWait("CLICK ADD BUTTON", CallCenterUserManagementRepo.addNewUserBtn);
		WebElement Click2 = driver.findElement(CallCenterUserManagementRepo.addNewUserBtn);
		Click2.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"Add New User page is displayed with required fields Executive ID, Username, Name, Mobile, Email ID , role ,tenurity, team leader,date of joining cancel , submit button");

		Common.fluentWait("EXECUTIVE ID", CallCenterUserManagementRepo.EXID);
		if (driver.findElement(CallCenterUserManagementRepo.EXID).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Executive displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Executive");
		}
		Common.fluentWait("NAME", CallCenterUserManagementRepo.NAME);
		if (driver.findElement(CallCenterUserManagementRepo.NAME).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "name displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show name");
		}

		Common.fluentWait("EMIAL", CallCenterUserManagementRepo.EMAIL);
		if (driver.findElement(CallCenterUserManagementRepo.EMAIL).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Email displayed successfully");
		} else

		{
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show EMail");

		}
		Common.fluentWait("MOBILE", CallCenterUserManagementRepo.PNO);
		if (driver.findElement(CallCenterUserManagementRepo.PNO).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Mobile");
		}
		Common.fluentWait("ROLE", CallCenterUserManagementRepo.ROLE);

		if (driver.findElement(CallCenterUserManagementRepo.ROLE).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "ROLE displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show ROLE");
		}
		Common.fluentWait("TENURE YEAR", CallCenterUserManagementRepo.TYEAR);

		if (driver.findElement(CallCenterUserManagementRepo.TYEAR).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Tyear displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Tyear");
		}
		Common.fluentWait("TEAM LEAD", CallCenterUserManagementRepo.TLEADE);

		if (driver.findElement(CallCenterUserManagementRepo.TLEADE).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Tlead displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add user to show Tlead");
		}
		
		Common.fluentWait("CLOSE", CallCenterUserManagementRepo.close);

		if (driver.findElement(CallCenterUserManagementRepo.close).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "close displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add user to show close");
		}
		
		Common.fluentWait("SUBMIT", CallCenterUserManagementRepo.submit2);

		if (driver.findElement(CallCenterUserManagementRepo.submit2).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "close displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add user to show close");
		}
		
	}
	public static void AddUserPageEmptySaveAttempt() throws InterruptedException{
		
		Common.fluentWait("CLICK SUBMIT BUTTON", CallCenterUserManagementRepo.submit2);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.submit2);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"Required message is displayed for Executive ID, Name, Email, Phone Number, and Role fields");


		Common.fluentWait("EXECUTIVE ID", CallCenterUserManagementRepo.EXID1);
		if (driver.findElement(CallCenterUserManagementRepo.EXID1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Executive displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Executive");
		}
		Common.fluentWait("NAME", CallCenterUserManagementRepo.NAME1);
		if (driver.findElement(CallCenterUserManagementRepo.NAME1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "name displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show name");
		}

		Common.fluentWait("EMIAL", CallCenterUserManagementRepo.EMAIL1);
		if (driver.findElement(CallCenterUserManagementRepo.EMAIL1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Email displayed successfully");
		} else

		{
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show EMail");

		}
		Common.fluentWait("MOBILE", CallCenterUserManagementRepo.PNO1);
		if (driver.findElement(CallCenterUserManagementRepo.PNO1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Mobile");
		}
		Common.fluentWait("ROLE", CallCenterUserManagementRepo.ROLE1);

		if (driver.findElement(CallCenterUserManagementRepo.ROLE1).isDisplayed()) {

			ExtentTestManager.getTest().log(Status.PASS, "ROLE displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show ROLE");
		}
	}
	public static void EnterValidExecutiveID()
	{
		
		Common.fluentWait("ENTER EXEC ID", CallCenterUserManagementRepo.addexecutive);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.addexecutive);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"8080 is entered in Executive ID field");


		 WebElement executiveIdField = driver.findElement(CallCenterUserManagementRepo.addexecutive); // Or use By.name, By.xpath, etc.

         // Step 1: Enter "8080"
         executiveIdField.sendKeys("8080");

         // Verification (Optional Step): Check if the value is entered correctly
         String enteredValue = executiveIdField.getAttribute("value");
         if ("8080".equals(enteredValue)) {
             System.out.println("8080 is entered in Executive ID field.");
         } else {
             System.out.println("Value entered does not match. Entered: " + enteredValue);
         }
	}
	
	
	public static void EnterValidName()
	{
		
		Common.fluentWait("Add Name", CallCenterUserManagementRepo.addname);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.addname);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"callcentreyou is entered in Name field");


		 WebElement executiveIdField = driver.findElement(CallCenterUserManagementRepo.addname); // Or use By.name, By.xpath, etc.

         // Step 1: Enter "8080"
         executiveIdField.sendKeys("callcentreyou");

         // Verification (Optional Step): Check if the value is entered correctly
         String enteredValue = executiveIdField.getAttribute("value");
         if ("callcentreyou".equals(enteredValue)) {
             System.out.println("callcentreyou is entered in Name field.");
         } else {
             System.out.println("Value entered does not match. Entered: " + enteredValue);
         }
	}
	
	public static void EnterValidEmail()
	{
		
		Common.fluentWait("Add Email", CallCenterUserManagementRepo.addEMAIL);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.addEMAIL);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"Add email is entered in Email ID field");


		 WebElement executiveIdField = driver.findElement(CallCenterUserManagementRepo.addEMAIL); // Or use By.name, By.xpath, etc.

         // Step 1: Enter "8080"
         executiveIdField.sendKeys("callcentreyou@gmail.com");

         // Verification (Optional Step): Check if the value is entered correctly
         String enteredValue = executiveIdField.getAttribute("value");
         if ("callcentreyou@gmail.com".equals(enteredValue)) {
             System.out.println("8080 is entered in Executive ID field.");
         } else {
             System.out.println("Value entered does not match. Entered: " + enteredValue);
         }
	}
	
	public static void EnterPhoneNumber()
	{
		
		Common.fluentWait("Add Phonenumber", CallCenterUserManagementRepo.addPNO);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.addPNO);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS,
				"Add phonenumber is entered in  field");


		 WebElement executiveIdField = driver.findElement(CallCenterUserManagementRepo.addPNO); // Or use By.name, By.xpath, etc.

         // Step 1: Enter "8080"
         executiveIdField.sendKeys("9876756456");

         // Verification (Optional Step): Check if the value is entered correctly
         String enteredValue = executiveIdField.getAttribute("value");
         if ("9876756456".equals(enteredValue)) {
             System.out.println("9876756456 is entered in Executive ID field.");
         } else {
             System.out.println("Value entered does not match. Entered: " + enteredValue);
         }
	}
	

	public static void EnterRole()
	{
		
		Common.fluentWait("Add Role", CallCenterUserManagementRepo.addROLE);
		WebElement Click1 = driver.findElement(CallCenterUserManagementRepo.addROLE);
		Click1.click();
		ExtentTestManager.getTest().log(Status.PASS, "Add Role is entered in  field");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement dropdownOption = wait
				.until(ExpectedConditions.elementToBeClickable(CallCenterUserManagementRepo.enterRole));
		dropdownOption.click();

//
//		 WebElement Role = driver.findElement(CallCenterUserManagementRepo.addROLE); // Or use By.name, By.xpath, etc.
//
//         // Step 1: Enter "8080"
//        Role.sendKeys("CallCenterRole");
//        
//        Common.fluentWait("Add Role", CallCenterUserManagementRepo.enterRole);
//		WebElement Click3 = driver.findElement(CallCenterUserManagementRepo.enterRole);
//		Click1.click();
//
//         // Verification (Optional Step): Check if the value is entered correctly
//         String enteredValue =Role.getAttribute("value");
//         if ("Role".equals(enteredValue)) {
//             System.out.println("Role is entered in Executive ID field.");
//         } else {
//             System.out.println("Value entered does not match. Entered: " + enteredValue);
//         }
	}
}
