package Core.CallCenterRoleManagementMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class CallCenterRoleManagementPage extends Base_Class {
	private WebDriver driver;
	public static String RoleName7;
	// Constructor
	public CallCenterRoleManagementPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("Call center role management initialized.");
	}

	public void addRoleName7() {
		try {
			Common.fluentWait("RoleName7", CallCenterRoleManagementRepo.RoleName);
			driver.findElement(CallCenterRoleManagementRepo.RoleName).sendKeys(RoleName7);
			ExtentTestManager.getTest().log(Status.PASS, "Entered role name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to enter the role name");
		}
	}
	
	public void SelectFunctionalities() {
		// Get all checkbox elements
		List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));

		// JavaScriptExecutor to click on checkboxes
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (WebElement checkbox : checkboxes) {
			// Scroll into view
			js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
			// Click the checkbox
			js.executeScript("arguments[0].click();", checkbox);
		}

	}

	public void SelectFunctionalitiesSelectedornot() {
		// Get all checkbox elements
		List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@type='checkbox']"));

		// JavaScriptExecutor to click on checkboxes
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				System.out.println("Checkbox is already selected.");
				String text = (String) js.executeScript("return arguments[0].innerText;", checkbox);
				ExtentTestManager.getTest().log(Status.PASS, "Functionality selected " + text);
			} else {
				System.out.println("Checkbox is not selected.");

			}
		}

	}

	public void DeSelectFunctionalities() {
		// Get all checkbox elements
		List<WebElement> elements = driver.findElements(By.xpath("//*[@type='checkbox']"));
		elements.get(1).click(); // Clicks the second element
		ExtentTestManager.getTest().log(Status.PASS, "Functionality Deselected ");
		elements.get(3).click(); // Clicks the fourth element
		ExtentTestManager.getTest().log(Status.PASS, "Functionality Deselected ");
		
	}

	public String RoleNameGenrator() {
		Random random = new Random();

		// Define the length of the name
		int nameLength = 4;

		// Generate the first letter as uppercase
		char firstLetter = (char) ('A' + random.nextInt(26));
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(firstLetter);

		// Generate the remaining 9 letters as lowercase
		for (int i = 1; i < nameLength; i++) {
			char letter = (char) ('a' + random.nextInt(26));
			nameBuilder.append(letter);
		}
		String Rolename = "callcentrerole" + nameBuilder.toString();
		// Convert StringBuilder to String and return
		return Rolename;
	}

	public void WaitLoader() {
		By loader = By.xpath("//*[@class='spinner']");
		// wait for Processing icon
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.pollingEvery(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Log.info("Loader disappeared.");
		} catch (Exception e) {
			Log.info("Loader did not appear, proceeding without delay.");
		}
	}


	
	public void VerifyTheText() throws IOException {
		try {
			String Text = driver.findElement(CallCenterRoleManagementRepo.emailuser).getText();
			String CoreUserName = CallCenterRoleManagementPage.configloader().getProperty("CallCenterUserName");
			if (Text.contains(CoreUserName)) {
				ExtentTestManager.getTest().log(Status.PASS, "Successfully logged in" + CoreUserName);
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Failed to logged in" + CoreUserName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Failed to logged in");
		}
	}
	
	public void clickSecurityManagement() {
		try {
			Common.fluentWait("Security Management", CallCenterRoleManagementRepo.SecurityManagement);
			driver.findElement(CallCenterRoleManagementRepo.SecurityManagement).click();
			ExtentTestManager.getTest().log(Status.PASS, "Security Management button selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Security Management button not selected");
		}
	}
	public void clickRoleManagement() {
		try {
			Common.fluentWait("RoleManagement", CallCenterRoleManagementRepo.RoleManagement);
			driver.findElement(CallCenterRoleManagementRepo.RoleManagement).click();
			ExtentTestManager.getTest().log(Status.PASS, "Role Management button selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Role Management button not selected");
		}
	}
	
	public void RoleManagementHeaderIsDisplayed() {
		try {
			if (driver.findElement(CallCenterRoleManagementRepo.RoleManagementHeader).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Role Management Header displayed successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Management Header");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Management Header");
		}

	}
	
	public void AddNewRoleIsDisplayed() {
		try {
			if (driver.findElement(CallCenterRoleManagementRepo.AddNewRole).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, "Add New Role displayed successfully");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Add New Role");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Add New Role");
		}

	}
	public void checkURL() {
		String CurrentURL = driver.getCurrentUrl();
		if (CurrentURL.contains("Admin/LevelPermissions")) {
			ExtentTestManager.getTest().log(Status.PASS, "Admin/LevelPermissions is contained in the current URL");
			ExtentTestManager.getTest().log(Status.PASS, "Currnet url is:" + CurrentURL);
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Admin/LevelPermissions not displayed");
			ExtentTestManager.getTest().log(Status.FAIL, "Currnet url is:" + CurrentURL);
		}
	}
	public void RoleManagementFieldsVerification() {
		if (driver.findElement(CallCenterRoleManagementRepo.RoleManagementHeader).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Role Management Header displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Management Header");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.AddNewRole).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Add New Role displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Add New Role");
		}

		if (driver.findElement(CallCenterRoleManagementRepo.search).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Search Text displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Search Text");
		}

		if (driver.findElement(CallCenterRoleManagementRepo.searchsubmit).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Search submit button displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Search submit button");
		}
	}
	
	public void clickAddNewRole() {
		try {
			Common.fluentWait("AddNewRole", CallCenterRoleManagementRepo.AddNewRole);
			driver.findElement(CallCenterRoleManagementRepo.AddNewRole).click();
			ExtentTestManager.getTest().log(Status.PASS, "Add New Role buttom clicked successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "unable to Add New Role button");
		}
	}
	
	public void FunctionalityPopUPVerification() {
		if (driver.findElement(CallCenterRoleManagementRepo.RolePermission).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Role Permission displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Permission submit button");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.RoleName).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Role Name button displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Name submit button");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.FunctionalityLevel).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Functionality Level displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Functionality Level");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.Functionalities).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Functionalities button displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Functionalities button");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.Save).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Save button displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Save button");
		}
		if (driver.findElement(CallCenterRoleManagementRepo.Cancel).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Cancel  button displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Cancel  button");
		}

	}
	
	public void clickSave() {
		
		try {
			Common.fluentWait("Save", CallCenterRoleManagementRepo.Save);
			driver.findElement(CallCenterRoleManagementRepo.Save).click();
			ExtentTestManager.getTest().log(Status.PASS, "Save buttom clicked successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "unable to Save");
		}
		
	}
	
	public void isDisplayedRoleName() {
		Common.fluentWait("Role Name ", CallCenterRoleManagementRepo.RoleName);
		if (driver.findElement(CallCenterRoleManagementRepo.EnterRoleName).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Enter Role Name message displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Enter Role Name  error");
		}
	}
	
	public void AddRoleName7() {
		String Rolename = RoleNameGenrator();
		RoleName7 = Rolename;
		System.out.println("Role name for test case 7 " + RoleName7);
		driver.findElement(CallCenterRoleManagementRepo.RoleName).sendKeys(Rolename);
	}
	
	public void IsRecordSavedSuccessfully() {
		Common.fluentWait("RecordSavedSuccessfully", CallCenterRoleManagementRepo.RecordSavedSuccessfully);
		if (driver.findElement(CallCenterRoleManagementRepo.RecordSavedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Record Saved Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to save Record");
		}
	}
	
	public void RoleManagemnentHeader() {
		if (driver.findElement(CallCenterRoleManagementRepo.RoleManagementHeader).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Returned Role Management page successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Returned Role Managemen page");
		}
	}
	
	public void SearchFunction7() {
		try {
			driver.findElement(CallCenterRoleManagementRepo.search).sendKeys(RoleName7);
			ExtentTestManager.getTest().log(Status.PASS, "Role Name entered in the search");
			Common.fluentWait("searchsubmit", CallCenterRoleManagementRepo.searchsubmit);
			driver.findElement(CallCenterRoleManagementRepo.searchsubmit).click();
			ExtentTestManager.getTest().log(Status.PASS, "search submitt button selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to complete the Search function");
		}
	}
	
	public void ActionMenuDisplayed() {
		if (driver.findElement(CallCenterRoleManagementRepo.ActionMenu).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Action Menu displyed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Action Menu not displayed");
		}
	}
	
	public void clickAction() {
		try {
			Common.fluentWait("ActionMenu", CallCenterRoleManagementRepo.ActionMenu);
			driver.findElement(CallCenterRoleManagementRepo.ActionMenu).click();
			ExtentTestManager.getTest().log(Status.PASS, "ActionMenu buttom clicked successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "unable to ActionMenu");
		}
	}
	
	
	public void clickEdit() {
		try {
			Common.fluentWait("Edit", CallCenterRoleManagementRepo.Edit);
			driver.findElement(CallCenterRoleManagementRepo.Edit).click();
			ExtentTestManager.getTest().log(Status.PASS, "Edit buttom clicked successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "unable to Edit");
		}
	}
	
	public void RolePermissionPopUp() {
		if (driver.findElement(CallCenterRoleManagementRepo.RolePermission).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Role Permission displayed successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show Role Permission submit button");
		}
	}
	
	public void RecordUpdatedSuccessfully() {
		Common.fluentWait("RecordSavedSuccessfully", CallCenterRoleManagementRepo.RecordupdatedSuccessfully);
		if (driver.findElement(CallCenterRoleManagementRepo.RecordupdatedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Record updated Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to update Record");
		}
	}
	
	public void checkIsDisplayed() {
		try {
			if (driver.findElement(CallCenterRoleManagementRepo.closeRolePermissionPopup).isDisplayed()) {
				driver.findElement(CallCenterRoleManagementRepo.closeRolePermissionPopup).click();
				ExtentTestManager.getTest().log(Status.PASS, "closed Role Permission Popup");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void LevelExist() throws InterruptedException {
		Common.fluentWait("LevelExistAlready", CallCenterRoleManagementRepo.LevelExistAlready);
		if (driver.findElement(CallCenterRoleManagementRepo.LevelExistAlready).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Level Exist Already  Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Level Exist Already not displayed");
		}
		Thread.sleep(5000);
	}
	
	public void PleaseSelectFunctionality() {
		Common.fluentWait("PleaseSelectFunctionality", CallCenterRoleManagementRepo.PleaseSelectFunctionality);
		if (driver.findElement(CallCenterRoleManagementRepo.PleaseSelectFunctionality).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Please Select Functionality Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Please Select Functionality not Displayed");
		}
	}
	
	public void EnterRoleName() {
		try {
			String Rolename12 = RoleNameGenrator();
			driver.findElement(CallCenterRoleManagementRepo.RoleName).sendKeys("Rolename12");
			ExtentTestManager.getTest().log(Status.PASS, "Role Name entered in the search");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Role Name not entered in the search");
		}
	}
	public void RoleManagementHeader() {
		if (driver.findElement(CallCenterRoleManagementRepo.RoleManagementHeader).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Returned Role Management page successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to Returned Role Managemen page");
		}
	}
	
	
	
	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}
}
