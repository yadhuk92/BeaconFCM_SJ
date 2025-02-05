package Core.AddNewAgentMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.BannedCustomerPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class AddNewAgentPage extends Base_Class {
	private WebDriver driver;
	public static String AgentCode10;
	public static String Username18;
	public static String AgentCode18;
	public static String PhoneNumber18;

	public static String Username30;
	public static String AgentCode30;
	public static String PhoneNumber30;

// Constructor
	public AddNewAgentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("AddNewAgentPage initialized.");
	}

	public void clickAgentManagement() {
		try {
			Common.fluentWait("Agent Management", AgentListPageRepo.AgentManagement);
			click1(AgentListPageRepo.AgentManagement);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent Management Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	
	
	public void clickEdit() {
		try {
			Common.fluentWait("Edit", AgentListPageRepo.Edit);
			click1(AgentListPageRepo.Edit);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent Management Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	
	public void clickResetPassword() {
		try {
			Common.fluentWait("Reset Password", AgentListPageRepo.ResetPassword);
			click1(AgentListPageRepo.ResetPassword);
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent Management Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	
	public void clickAction() {
		try {
			WebElement ActionMenu1 = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu1);
			ExtentTestManager.getTest().log(Status.PASS, "Action Menu Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Action Men Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	public void clickUpdate() {
		try {
			Common.fluentWait("Update", AgentListPageRepo.Update);
			WebElement elementToDoubleClick = driver.findElement(By.xpath("//button[contains(text(),'Update')]"));
			Actions actions = new Actions(driver);
			actions.doubleClick(elementToDoubleClick).perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Action Men Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	public void clearAndSendUsername() {
		try {
			driver.findElement(AgentListPageRepo.Name1).clear();
			ExtentTestManager.getTest().log(Status.PASS, "cleared the username");
			driver.findElement(AgentListPageRepo.Name1).sendKeys(Username30 + "Updated");
			ExtentTestManager.getTest().log(Status.PASS, "Updated the Username");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Action Men Functionality ");
			System.out.println("Error message " + e.getMessage());
		}
	}
	
	
	
	public void clickAgentList() {
		try {
			Common.fluentWait("Agent List", AgentListPageRepo.AgentList);
			click1(AgentListPageRepo.AgentList);
			ExtentTestManager.getTest().log(Status.PASS, "Agent List Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent List Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickActionClickAndAvtivateDectivate() {
		try {
			Common.fluentWait("Action Menu", AgentListPageRepo.ActionMenu);
			WebElement ActionMenu = driver.findElement(By.xpath("//*[@id='dropdownMenu2']//span"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", ActionMenu);
			ExtentTestManager.getTest().log(Status.PASS, "Action Menu selected");
			driver.findElement(AgentListPageRepo.ActivateDeactivate).click();
			ExtentTestManager.getTest().log(Status.PASS, "Activate Deactivate selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent List Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void Search1() {
		try {
			Common.fluentWait("Search1", AgentListPageRepo.Search1);
			click1(AgentListPageRepo.Search1);
			ExtentTestManager.getTest().log(Status.PASS, "Agent List Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Agent List Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void GetTheDetailsforValidUser() {
		// table/thead/tr
		// Find elements with the specified locator
		List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr"));
		// Create a list to store the text values
		List<String> textValues = new ArrayList<>();
		// Iterate over the WebElements and extract text
		for (WebElement element : elements) {
			textValues.add(element.getText());
			String text = element.getText().trim(); // Trim to remove extra spaces
			if (!text.isEmpty()) { // Check if the text is not empty
				textValues.add(text);
				ExtentTestManager.getTest().log(Status.PASS, "Text is present after Search the Agent" + text);
			}
		}
		// Print the list of text values
		System.out.println("Text values: " + textValues);
	}

	public void clickAddNewAgent() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.AddNewAgent);
			click1(AgentListPageRepo.AddNewAgent);
			ExtentTestManager.getTest().log(Status.PASS, "Add New Agent Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Add New Agent  Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickClose() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.Close);
			click1(AgentListPageRepo.Close);
			ExtentTestManager.getTest().log(Status.PASS, "Close Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Close Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickSubmit() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.Submit);
			click1(AgentListPageRepo.Submit);
			ExtentTestManager.getTest().log(Status.PASS, "Submit Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Submit  Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickhtml() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.html);
			click1(AgentListPageRepo.html);
//				ExtentTestManager.getTest().log(Status.PASS, "html element clicked selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//				ExtentTestManager.getTest().log(Status.FAIL, "Unable to click html  Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickTenurity() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.Tenurity);
			click1(AgentListPageRepo.Tenurity);
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Tenurity Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void clickagenntrole() {
		try {
			Common.fluentWait("SecurityManagementMainMenu", AgentListPageRepo.agenntrole);
			click1(AgentListPageRepo.agenntrole);
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity Functionality selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select Tenurity Functionality");
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void AgentManagementDisplay() {
		// is displayed
		String CurrentURL = driver.getCurrentUrl();
		if (CurrentURL.contains("Agent/AgentManagement")) {
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management is contained in the current URL");
			ExtentTestManager.getTest().log(Status.PASS, "Currnet url is:" + CurrentURL);
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agent Management not displayed");
			ExtentTestManager.getTest().log(Status.FAIL, "Currnet url is:" + CurrentURL);
		}
		if (driver.findElement(AgentListPageRepo.AgentManagement1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Agent Management  displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agent Management not displayed");
		}

	}

	public void AgentListPageIsDisplayed() throws IOException {
		String Text = driver.findElement(AgentListPageRepo.AgencyName).getText();
		String CoreUserName = AddNewAgentPage.configloader().getProperty("CoreUserName_Agency");
		if (Text.contains(CoreUserName)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency name displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency name not displayed");
		}
		// agent code
		if (driver.findElement(AgentListPageRepo.AgentCode).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agent Code not displayed");
		}
		// username
		if (driver.findElement(AgentListPageRepo.UserName).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "User Name displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "User Name not displayed");
		}
		// Agent name
		if (driver.findElement(AgentListPageRepo.AgentName).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Agent Name displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agent Name not displayed");
		}
		// Mobile Number
		if (driver.findElement(AgentListPageRepo.Mobile).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Mobile displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Mobile not displayed");
		}
		// Role
		if (driver.findElement(AgentListPageRepo.Role).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Role displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Role not displayed");
		}

		if (driver.findElement(AgentListPageRepo.Active).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Active check box displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Active check box not displayed");
		}
		if (driver.findElement(AgentListPageRepo.AddAgent).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Add Agent displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add Agent not displayed");
		}
		if (driver.findElement(AgentListPageRepo.Search).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Search button displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Search button not displayed");
		}
	}

	public void ErrorMandatoryField() {
		if (driver.findElement(AgentListPageRepo.ErrorForMandotoryFields).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Mandatory field need to fill error displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Mandatory field need to fill error not displayed");
		}
	}

	public void InvalidPhoneNumber() {

		driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys("1");
		ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
		driver.findElement(AgentListPageRepo.Name1).sendKeys("Ramesh");
		ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
		driver.findElement(AgentListPageRepo.MobileInput).sendKeys("123Ramesh");
		ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
		driver.findElement(By.xpath("//html")).click();

		if (driver.findElement(AgentListPageRepo.InvalidPhoneNumber).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Invalid Phone Number error displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Invalid Phone Number error not displayed");
		}
		driver.findElement(AgentListPageRepo.Tenurity).click();
		ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
		driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
		driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
		Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
		driver.findElement(AgentListPageRepo.agenntrole).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
		driver.findElement(AgentListPageRepo.Submit).click();
		ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
		ExtentTestManager.getTest().log(Status.PASS,
				"Agent is not created due to Invalid Phone Number error displayed");
	}

	public String AgentCodeGenerator() {
		Random random = new Random();
		// Generate a 4-digit random number
		int min = 1000; // Smallest 4-digit number
		int max = 9999; // Largest 4-digit number

		// Generate random number in range [1000, 9999]
		int randomNumber = random.nextInt(max - min + 1) + min;
		String stringNum = Integer.toString(randomNumber);
		return stringNum;
	}

	public void ClearAddNewAgent() {
		driver.findElement(AgentListPageRepo.NameAgentCode).clear();
		driver.findElement(AgentListPageRepo.Name1).clear();
		driver.findElement(AgentListPageRepo.MobileInput).clear();
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).clear();
		driver.findElement(AgentListPageRepo.ClearRole).click();
	}

	public void AddNewAgentWithPastDateofJoining() throws InterruptedException {
		String AgentCode2 = AgentCodeGenerator();
		driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
		ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
		String Username2 = RandomNameGenerator();
		driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
		ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
		String MobileNumber2 = MobileNumberGenerator();
		driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
		ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
		ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
		driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
		Thread.sleep(1000);
		ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
		Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
		driver.findElement(AgentListPageRepo.agenntrole).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
		Thread.sleep(1000);
		driver.findElement(AgentListPageRepo.DatePicker).click();
		LocalDate today = LocalDate.now();
		int day = today.getDayOfMonth();
		if (day == 1) {
			driver.findElement(AgentListPageRepo.PreveousMonth).click();
			int RequiredDay = day;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
		} else {
			int RequiredDay = day - 1;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
		}
	}

	public void AddNewAgentWithFutureDate() throws InterruptedException {
		String AgentCode2 = AgentCodeGenerator();

		AgentCode18 = AgentCode2;

		System.out.println("AgentCode2 :" + AgentCode2);
		driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
		ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
		String Username2 = RandomNameGenerator();
		Username18 = Username2;
		System.out.println("Username2U" + Username2);
		driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
		ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
		String MobileNumber2 = MobileNumberGenerator();
		System.out.println("MobileNumber2 :" + MobileNumber2);
		driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
		ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
		PhoneNumber18 = MobileNumber2;
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
		ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
		driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
		Thread.sleep(2000);
		Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
		WebElement elementToClick = driver.findElement(By.xpath("(//*[@role='listbox']/..//li[1])[3]"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);
		ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
		Thread.sleep(1000);
		driver.findElement(AgentListPageRepo.DatePicker).click();
		LocalDate today = LocalDate.now();
		int day = today.getDayOfMonth();

		if (day == 28) {
			driver.findElement(AgentListPageRepo.NextMonth).click();
			int RequiredDay = day;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
		} else {
			int RequiredDay = day + 1;
			String Day = String.valueOf(RequiredDay);
			String desiredDate = Day; // Specify the day you want to select
			WebElement dateElement = driver.findElement(By.xpath("//td[normalize-space()='" + desiredDate + "']"));
			dateElement.click();
		}
	}

	public void CreateNewAgent9() throws InterruptedException {
		String AgentCode = AgentCodeGenerator();
		AgentCode10 = AgentCode;
		driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode);
		ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
		String Username = RandomNameGenerator();
		driver.findElement(AgentListPageRepo.Name1).sendKeys(Username);
		ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
		String MobileNumber = MobileNumberGenerator();
		driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber);
		ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
		ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
		driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
		Thread.sleep(1000);
		Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
		driver.findElement(AgentListPageRepo.agenntrole).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
		Thread.sleep(1000);
		driver.findElement(AgentListPageRepo.Submit).click();
		ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
		WaitLoader();
	}

	public void isUserCreatedSuccessfully() {
		Common.fluentWait("Usercreatedsuccessfully", AgentListPageRepo.Usercreatedsuccessfully);
		if (driver.findElement(AgentListPageRepo.Usercreatedsuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS,
					AgentListPageRepo.Usercreatedsuccessfully + "New agent added successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					AgentListPageRepo.Usercreatedsuccessfully + "Unable to add New agent");
		}
	}
	public void isUserUpdated() {
		Common.fluentWait("RecordUpdatedSuccessfully", AgentListPageRepo.RecordUpdatedSuccessfully);
		if (driver.findElement(AgentListPageRepo.RecordUpdatedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Record Updated Successfully Status Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Record Updated Successfully not Displayed");
		}
	}
	
	public void PasswordReserStatus() {
		Common.fluentWait("Password Reset Status", AgentListPageRepo.PasswordResetStatus);
		if (driver.findElement(AgentListPageRepo.PasswordResetStatus).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Password Reset Status Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Password Reset Statusnot Displayed");
		}
	}
	
	
	
	public void IsNoRecordToDisplay() {
		if (driver.findElement(AgentListPageRepo.NoRecordToDisplay).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "No Record to Display shown successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "No Record statement is not displayed");
		}
	}

	public void AlreadyUserExistDisplayed() {
		if (driver.findElement(AgentListPageRepo.AlreadyUserExist).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "User is already exist");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to add User");
		}
	}

	public String MobileNumberGenerator() {
		Random random = new Random();

		// Define the range for a 10-digit number (starting with 6, 7, 8, or 9)
		long min = 6000000000L; // Smallest 10-digit number starting with 6
		long max = 9999999999L; // Largest 10-digit number

		// Generate random number in range [min, max]
		long mobileNumber = min + (long) (random.nextDouble() * (max - min));
		String mobileNumberStr = String.valueOf(mobileNumber);

		System.out.println("Random 10-digit mobile number: " + mobileNumberStr);
		return mobileNumberStr;
	}

	public String RandomNameGenerator() {
		Random random = new Random();

		// Define the length of the name
		int nameLength = 10;

		// Generate the first letter as uppercase
		char firstLetter = (char) ('A' + random.nextInt(26));
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(firstLetter);

		// Generate the remaining 9 letters as lowercase
		for (int i = 1; i < nameLength; i++) {
			char letter = (char) ('a' + random.nextInt(26));
			nameBuilder.append(letter);
		}

		// Convert StringBuilder to String and return
		return nameBuilder.toString();
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
	
	public void verifyUpdatedUser() {
		String UpdatedUsername = driver.findElement(AgentListPageRepo.UpdatedUsername).getText();
		if (UpdatedUsername.contains(Username30 + "Updated")) {
			ExtentTestManager.getTest().log(Status.PASS,
					" Username updated successfully and it is displayed in AgentList");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " Username not updated and it is displayed in AgentList");
		}
	}
	
	
	
	
	public void EditAgentVerification() {
		// is displayed
					if (driver.findElement(AgentListPageRepo.EditAgent).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Edit Agent displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Edit Agent not displayed");
					}
					if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Agent Code Add New Agent displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Agent Code Add New Agent  not displayed");
					}
					// Agent name
					if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Name is dispalyed in Edit displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Name is dispalyed in Edit not displayed");
					}
					// Mobile Number
					if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Phone Number AddNewAgent is displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Phone Number AddNewAgent is not displayed");
					}

					if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Date of Joining displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Date of Joining not displayed");
					}

					if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Tenurity displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Tenurity not displayed");
					}
					if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, "Close displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, "Close not displayed");
					}
					if (driver.findElement(AgentListPageRepo.Update).isDisplayed()) {
						ExtentTestManager.getTest().log(Status.PASS, " Update button displayed");
					} else {
						ExtentTestManager.getTest().log(Status.FAIL, " Update not displayed");
					}
	}
	
	public void GetAttributeAndVerify() {
		String TextDate = driver.findElement(AgentListPageRepo.CurrentDate).getAttribute("value");
		String name = driver.findElement(AgentListPageRepo.Name).getAttribute("value");
		String Name1 = driver.findElement(AgentListPageRepo.Name1).getAttribute("value");
		String PhoneNumber = driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).getAttribute("value");
		String Tenurity = driver.findElement(AgentListPageRepo.Tenurity).getAttribute("value");
		String AgentCodeEdit = driver.findElement(AgentListPageRepo.AgentCodeEdit).getAttribute("value");

		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = currentDate.format(formatter);

		if (TextDate.contains(formattedDate)) {
			ExtentTestManager.getTest().log(Status.PASS, " Date is displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " Date is not displayed");
		}
		if (AgentCode30.contains(AgentCodeEdit)) {
			ExtentTestManager.getTest().log(Status.PASS, " Agent Code is displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " Agent Code is not displayed");
		}
		if (Name1.contains(Username30)) {
			ExtentTestManager.getTest().log(Status.PASS, " Username is displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " Username is not displayed");
		}

//		if (Name1.contains(formattedDate)) {
//			ExtentTestManager.getTest().log(Status.PASS, " Name1 is displayed");
//		} else {
//			ExtentTestManager.getTest().log(Status.FAIL, " Name1 is not displayed");
//		}

		if (PhoneNumber.contains(PhoneNumber30)) {
			ExtentTestManager.getTest().log(Status.PASS, " PhoneNumber is displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " PhoneNumber is not displayed");
		}

		if (Tenurity.contains("12")) {
			ExtentTestManager.getTest().log(Status.PASS, " Tenurity is displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, " Tenurity is not displayed");
		}
	}
	
	
	
	
	
	
public void creatNewAgent() throws InterruptedException {
	String AgentCode2 = AgentCodeGenerator();

	AgentCode30 = AgentCode2;

	System.out.println("AgentCode2 :" + AgentCode2);
	driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode2);
	String Username2 = RandomNameGenerator();
	Username30 = Username2;
	System.out.println("Username2U" + Username2);
	driver.findElement(AgentListPageRepo.Name1).sendKeys(Username2);
	String MobileNumber2 = MobileNumberGenerator();
	System.out.println("MobileNumber2 :" + MobileNumber2);
	driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber2);
	PhoneNumber30 = MobileNumber2;
	driver.findElement(AgentListPageRepo.Tenurity).click();
	driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
	driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
	Thread.sleep(2000);
	Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
	WebElement elementToClick = driver.findElement(By.xpath("(//*[@role='listbox']/..//li[1])[3]"));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("arguments[0].style.display='block'; arguments[0].click();", elementToClick);
	Thread.sleep(1000);

	driver.findElement(AgentListPageRepo.Submit).click();
}
	public void SearchUser() {
		driver.findElement(AgentListPageRepo.AgentName1).clear();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Name is cleared");
		driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
		ExtentTestManager.getTest().log(Status.PASS, " Username added");
		driver.findElement(AgentListPageRepo.Search1).click();
		ExtentTestManager.getTest().log(Status.PASS, "Started to search the user");
	}

	public void SearchUserWithIsActive() {
		driver.findElement(AgentListPageRepo.AgentName1).clear();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Name is cleared");
		driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
		ExtentTestManager.getTest().log(Status.PASS, " Username added");
		driver.findElement(AgentListPageRepo.IsActive).click();
		driver.findElement(AgentListPageRepo.Search1).click();
		ExtentTestManager.getTest().log(Status.PASS, "Started to search the user");
	}

	public void SatusChanged() {
		Common.fluentWait("Status Changed", AgentListPageRepo.StatusChanged);
		if (driver.findElement(AgentListPageRepo.StatusChanged).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Status Changed Active to InActive and  Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status changed statement not Displayed");
		}
	}

	public void InactiveStatus() {
		if (driver.findElement(AgentListPageRepo.InactiveStatus).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS,
					AgentListPageRepo.InactiveStatus + "Inactive Status Displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					AgentListPageRepo.InactiveStatus + "Inactive Status not Displayed");
		}
	}

	public void searchInvalidUser() {
		try {
			driver.findElement(AgentListPageRepo.AgentName1).clear();
			ExtentTestManager.getTest().log(Status.PASS, "Agent Name is cleared");
			driver.findElement(AgentListPageRepo.AgentName1).sendKeys("InvalidUser");
			ExtentTestManager.getTest().log(Status.PASS, "Invalid Username added");
			driver.findElement(AgentListPageRepo.Search1).click();
			ExtentTestManager.getTest().log(Status.PASS, "Started to search the Invalid user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SearchPreviousUser() {

		driver.findElement(AgentListPageRepo.AgentName1).sendKeys(Username18);
		clickAgentList();
		driver.findElement(AgentListPageRepo.Search1).click();
	}

	public void Fillthedetails10() throws InterruptedException {
		driver.findElement(AgentListPageRepo.NameAgentCode).sendKeys(AgentCode10);
		ExtentTestManager.getTest().log(Status.PASS, "Agent Code is added");
		String Username1 = RandomNameGenerator();
		driver.findElement(AgentListPageRepo.Name1).sendKeys(Username1);
		ExtentTestManager.getTest().log(Status.PASS, "User name entered Successfully");
		String MobileNumber1 = MobileNumberGenerator();
		driver.findElement(AgentListPageRepo.MobileInput).sendKeys(MobileNumber1);
		ExtentTestManager.getTest().log(Status.PASS, "Mobile Number is added");
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).sendKeys("12");
		ExtentTestManager.getTest().log(Status.PASS, "Tenurity added");
		driver.findElement(AgentListPageRepo.RoleAddNewAgent).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent Role Selected");
		Thread.sleep(2000);
		Common.fluentWait("agenntrole", AgentListPageRepo.agenntrole);
		driver.findElement(AgentListPageRepo.agenntrole).click();
		ExtentTestManager.getTest().log(Status.PASS, "Agent role added");
		Thread.sleep(1000);
		driver.findElement(AgentListPageRepo.Submit).click();
		ExtentTestManager.getTest().log(Status.PASS, "All the details for add New Agent submitted");
	}

	public void AddNewAgentIsDisplayed() {
		if (driver.findElement(AgentListPageRepo.AddNewAgent1).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Add New Agent page displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Add New Agent page not displayed");
		}
	}

	public void DateOfJoiningField() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		String formattedDate = currentDate.format(formatter);
		By AgencyName = By.xpath("//*[contains(text(),'" + formattedDate + "']");
	
	Common.fluentWait("UserNameField", AgencyName);
		String TextDate = driver.findElement(AgentListPageRepo.CurrentDate).getAttribute("value");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (TextDate.contains(formattedDate)) {
			ExtentTestManager.getTest().log(Status.PASS, "Current date is displayed in Date of joining");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Current date is not displayed in Date of joining");
		}
	}

	public void AddNewAgentPageFieldsVerification() {
		if (driver.findElement(AgentListPageRepo.AgentCodeAddNewAgent).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Agent Code Add New Agent WebElement displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agent Code Add New Agent WebElement not displayed");
		}
		// Agent name
		if (driver.findElement(AgentListPageRepo.Name).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Name displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Name not displayed");
		}
		// Mobile Number
		if (driver.findElement(AgentListPageRepo.PhoneNumberAddNewAgent).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "PhoneNumber in Add New Agent displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "PhoneNumber in Add New Agent not displayed");
		}

		if (driver.findElement(AgentListPageRepo.DateofJoining).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Date of Joining displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Date of Joining not displayed");
		}

		if (driver.findElement(AgentListPageRepo.Tenurity).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Tenurity displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Tenurity not displayed");
		}
		if (driver.findElement(AgentListPageRepo.Close).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Close button displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Close button  not displayed");
		}
		if (driver.findElement(AgentListPageRepo.Submit).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Submit button displayed");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Submit button not displayed");
		}
	}

	public String PastDate() throws ParseException {
		// take last 60 days coverage date from current system date
		LocalDate currentDate = LocalDate.now();
		LocalDate currentDateMinus2Months = currentDate.minusDays(10);
		String Date = currentDateMinus2Months.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat output = new SimpleDateFormat("mm/dd/yyyy");
		Date data = sdf.parse(Date);
		String CoverageDate = output.format(data);
		return CoverageDate;
	}

	public String FutureDate() throws ParseException {
		// take last 60 days coverage date from current system date
		LocalDate currentDate = LocalDate.now();
		LocalDate currentDateMinus2Months = currentDate.plusDays(10);
		String Date = currentDateMinus2Months.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat output = new SimpleDateFormat("mm/dd/yyyy");
		Date data = sdf.parse(Date);
		String CoverageDate = output.format(data);
		return CoverageDate;
	}

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

}
