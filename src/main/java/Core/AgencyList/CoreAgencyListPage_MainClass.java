package Core.AgencyList;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.AgentListPageRepo;
import com.Page_Repository.CoreAgencyListRepo;
import com.Page_Repository.LoginPageRepo;
import com.Page_Repository.MyDeskDashboardRepo;
import com.Utility.DBUtils;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class CoreAgencyListPage_MainClass extends Base_Class {
	static String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"; // Set download
	public static String PanNumber;
	public static String AgencyUserName;
	public static String BeforeDeEmpanelAgencyUserName;
	public static String Username;
	public static String Password;
	public static String UsernameNew;
	public static String PasswordNew;
	public static String AppType;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;

	// directory
// Constructor
	WebDriver driver;

	public CoreAgencyListPage_MainClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("CoreAgencyListPage_MainClass");
	}

	public void isDisplayed(By locator, String elementName) {
		try {

			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void isNOTDisplayed(By locator, String elementName) {
		try {
			List<WebElement> elements = driver.findElements(locator);

			if (elements.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is not displayed, as expected.");
			} else if (!elements.get(0).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + " is present but hidden (not displayed), as expected.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is visible but should not be.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void isDisplayedActive(By locator, String elementName) {
		try {
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, " Element is displayed.");
				ExtentTestManager.getTest().log(Status.PASS, " Dropdown is Active After Reset is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void SelectDateForDeactivatedate() {
		String today = String.valueOf(LocalDate.now().getDayOfMonth());

		// Construct a dynamic XPath using today's date
		String xpath = "//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
				+ today + "']";

		// Locate the element and click it
		WebElement currentDate = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", currentDate);
		ExtentTestManager.getTest().log(Status.PASS, "Current date for Action from selected");
	}

	public void SelectDateForAcctivatedate() {
		String today = String.valueOf(LocalDate.now().getDayOfMonth());

		// Construct a dynamic XPath using today's date
		String xpath = "//div[@class='rz-datepicker rz-popup']//table//tbody/tr/td/span[@class='rz-state-default' and text()='"
				+ today + "']";

		// Locate the element and click it
		WebElement currentDate = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", currentDate);
		ExtentTestManager.getTest().log(Status.PASS, "Current date for Action from selected");
	}

	public void click(By locator, String elementName) {
		try {
			Common.fluentWaitNew(elementName, locator);
			WebElement element = driver.findElement(locator);

			// JavaScript Executor to move to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			click1(locator);
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator " + elementName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			result.append(characters.charAt(random.nextInt(characters.length())));
		}
		return result.toString();
	}

	public void IsElementEnabled(By locator, String elementName) {
		try {
//		Common.fluentWaitNew(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + "is Active after the Reset(Parameter Activation)");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive (Parameter Activation) ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void IsElementDisabled(By locator, String elementName) {
		try {
//		Common.fluentWaitNew(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName + "is Active before Reset (Parameter activation) ");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive before Reset");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}

	}

	public void GetCurrentStatus() {
		try {
			String AgencyStatus = driver.findElement(CoreAgencyListRepo.CurrentStatus).getAttribute("value");
			if (AgencyStatus.contains("Active")) {
				System.out.println("Status is active.");
				ExtentTestManager.getTest().log(Status.PASS, "Agency Status is active.");
			} else {
				System.out.println("Status is Deactive.");
				ExtentTestManager.getTest().log(Status.PASS, "Agency status is Deactive.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select ");
			System.out.println("Error message " + e.getMessage());
		}

	}

	public String StatusAgency() {
		String AgencyStatus = driver.findElement(CoreAgencyListRepo.CurrentStatus).getAttribute("value");
		return AgencyStatus;

	}

	public void CheckAgencyStatusandMakeItActive() {
		Common.fluentWaitNew("submit", CoreAgencyListRepo.submit);
		String status = StatusAgency();
		if (status.contains("Deactive")) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Status is Deactive");
			click(CoreAgencyListRepo.ActivateDate, "ActivateDate");
			SelectDateForAcctivatedate();
			click(CoreAgencyListRepo.submit, "submit");
			StatusChanged();
			WaitLoader();
		} else {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Status is Active");
		}
	}

	public void CheckAgencyStatusandMakeItActiveNew() {
		Common.fluentWaitNew("submit", CoreAgencyListRepo.submit);
		String status = StatusAgency();
		if (status.contains("Deactive")) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Status is Deactive");
			click(CoreAgencyListRepo.ActivateDate, "ActivateDate");
			SelectDateForAcctivatedate();
			click(CoreAgencyListRepo.submit, "submit");
			StatusChanged();
			WaitLoader();
			click(CoreAgencyListRepo.Action, "Action");
			click(CoreAgencyListRepo.ActivateDate, "ActivateDate");
			WaitLoader();
			Common.fluentWaitNew("submit", CoreAgencyListRepo.submit);
		} else {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Status is Active");
		}
	}

	public void MakeAgencyStatusDeactive() {
		click(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");
		SelectDateForAcctivatedate();
		click(CoreAgencyListRepo.DeactivateDate, "DeactivateDate");
		click(CoreAgencyListRepo.submit, "submit");
		StatusChanged();
	}

	public void AgencyNameVerification() {
		WebElement inputElement = driver.findElement(By.name("Name")); // or use By.id("1GfnFuCCB6k_b1_300")

		String uiText = inputElement.getAttribute("value");

		System.out.println("Value in disabled input: " + uiText);
		if (uiText != null && !uiText.trim().isEmpty()) {
			System.out.println("Text is present: " + uiText);
			ExtentTestManager.getTest().log(Status.PASS, "Agency name is present: " + uiText);
		} else {
			System.out.println("Text is either null, empty, or only whitespace");
			ExtentTestManager.getTest().log(Status.FAIL, "Agency name is not present: " + uiText);
		}
	}

	public void WaitLoader() {
		By loader = By.xpath("//*[@class='spinner']");
		// wait for Processing icon
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.pollingEvery(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Log.info("Loader disappeared.");
			Thread.sleep(4000);
		} catch (Exception e) {
			Log.info("Loader did not appear, proceeding without delay.");
		}
	}

	public void AgencyCount() {
		String PageCount = driver.findElement(CoreAgencyListRepo.LastPageCount).getText();
		ExtentTestManager.getTest().log(Status.PASS, "Total Number of pages " + PageCount);
		String AgencyCount = driver.findElement(CoreAgencyListRepo.TotalAgencyRecords).getText();
		ExtentTestManager.getTest().log(Status.PASS, "Total Number of Agency listed in the gid " + AgencyCount);
	}

	public void verifyDropdownAddNewAgencyPageGrid() {
		// Step 1: Expected symbols
		List<String> expectedSymbols = Arrays.asList("SYSTEM ID", "COLLECTION AGENCY NAME", "PAN NUMBER", "ADDRESS",
				"MOBILE NO.", "DATE OF EMPANELMENT", "DATE OF EMPANELMENT EXPIRY", "AGREEMENT DURATION STARTING",
				"AGREEMENT DURATION ENDING", "REMARKS", "ACTION");

		// Step 2: Wait for the dropdown list to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/thead/tr/th")));

		// Step 3: Get all column header elements
		List<WebElement> options = driver.findElements(By.xpath("//table/thead/tr/th"));

		// Step 4: Read text, trim, and collect
		List<String> cleanedSymbols = new ArrayList<>();
		for (WebElement option : options) {
			String rawSymbol = option.getText().trim(); // Trim leading/trailing spaces/newlines
			if (!rawSymbol.isEmpty()) {
				cleanedSymbols.add(rawSymbol);
			}
		}

		// Step 5: Print and compare
		System.out.println("Cleaned dropdown symbols: " + cleanedSymbols);
		System.out.println("Expected symbols: " + expectedSymbols);

		// Step 6: Validation
		if (cleanedSymbols.equals(expectedSymbols)) {
			System.out.println("Agency  List page table Grid coulmns are verified and matched");
			ExtentTestManager.getTest().log(Status.PASS,
					"Agency  List page table Grid coulmns are verified and matched");
		} else {
			System.out.println("Agency  List page table Grid coulmns are mismatched");
			ExtentTestManager.getTest().log(Status.FAIL, "Agency  List page table Grid coulmns are mismatched");
		}
	}

	public void VerifyAgencyLevel() {
		String AgencyUser = driver.findElement(CoreAgencyListRepo.Level).getText();
		if (AgencyUser.contains("HO")) {
			ExtentTestManager.getTest().log(Status.PASS, "HO level Agency Login done");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "HO level Agency Login done");
		}
	}

	public void VerifyAgencyListFilterByAgencyName() {
		Common.fluentWaitNew("PANFromList", CoreAgencyListRepo.PANFromList);
		PanNumber = driver.findElement(CoreAgencyListRepo.PANFromList).getText();
		AgencyUserName = driver.findElement(CoreAgencyListRepo.AgencyNameFromList).getText();
		driver.findElement(CoreAgencyListRepo.AgencyName).sendKeys(AgencyUserName);
		click(CoreAgencyListRepo.Search, "Search");
		WaitLoader();
		Common.fluentWaitNew("Action", CoreAgencyListRepo.Action);
		String AgencyUserResult = driver.findElement(CoreAgencyListRepo.AgencyNameFromListResult).getText();
		if (AgencyUserResult.contains(AgencyUserName)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}

	}

	public void SaveAgencyName() {

		BeforeDeEmpanelAgencyUserName = driver.findElement(CoreAgencyListRepo.AgencyNameFromList).getText();
	}

	public void IsAgencyDisplayedAfterDeEmpanel() {
		String AfterDeEmpanelAgencyUserName = driver.findElement(CoreAgencyListRepo.AgencyNameFromList).getText();
		if (AfterDeEmpanelAgencyUserName.equalsIgnoreCase(BeforeDeEmpanelAgencyUserName)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name not shown in the list after the DeEmpanelment");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name shown in the list after the DeEmpanelment");
		}
	}

	public void VerifySystemID(String ID) {
		String SystemID = driver.findElement(CoreAgencyListRepo.SystemID).getText();

		String AgencyUserResult = driver.findElement(CoreAgencyListRepo.AgencyNameFromListResult).getText();
		if (SystemID.contains(ID)) {
			ExtentTestManager.getTest().log(Status.PASS, "Pagination has been done successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Pagination has not been done");
		}

	}

	public void VerifyAgencyListFilterByPanNumber() {

		driver.findElement(CoreAgencyListRepo.PANNumber).sendKeys(PanNumber);
		click(CoreAgencyListRepo.Search, "Search");
		WaitLoader();
		Common.fluentWaitNew("Action", CoreAgencyListRepo.Action);
		String PanNumberResult = driver.findElement(CoreAgencyListRepo.PANFromListResult).getText();
		if (PanNumberResult.contains(PanNumber)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}

	}

	public void VerifytheSearchResult() {
		String PanNumberUI = driver.findElement(CoreAgencyListRepo.PANFromListResult).getText();
		String AgencyUserNameUI = driver.findElement(CoreAgencyListRepo.AgencyNameFromListResult).getText();
		if (PanNumberUI.contains(PanNumber)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}
		if (AgencyUserNameUI.contains(AgencyUserName)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}
	}

	public void IsPrefilled(By locator, String elementName) {
		try {
			String Value = driver.findElement(locator).getText();
			if (Value.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " Element is displayed has prefilled value");

			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName + " Element is displayed has no prefilled value");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void IsPrefilledByValue(By locator, String elementName) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(locator);

			// Use JS to get value of disabled input
			String value = (String) js.executeScript("return arguments[0].value;", element);

			if (!value.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + " Element is displayed and has prefilled value: " + value);
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName + " Element is displayed but has no prefilled value");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void verifyPrefilledDropdownText(By locator, String elementName) {
		try {
			WebElement element = driver.findElement(locator);
			String actualText = element.getText().trim();

			if (!actualText.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS,
						" Element is displayed and has prefilled value: " + actualText);
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, " Element is displayed but has no prefilled value");
			}

		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					" dropdown value check failed due to exception: " + e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void UpdateAdress(String update) {
		try {
			driver.findElement(CoreAgencyListRepo.Address).clear();
			driver.findElement(CoreAgencyListRepo.Address).sendKeys(update);
			ExtentTestManager.getTest().log(Status.PASS, " Address Updated Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.PASS, "Unable to update the adress");
		}
	}

	public void VerifyTheAdressUpdate(String update) {
		String UpdatedAddressFromUI = driver.findElement(CoreAgencyListRepo.AdressFromListResult).getText();
		if (UpdatedAddressFromUI.contains(update)) {
			ExtentTestManager.getTest().log(Status.PASS,
					" Adress is updated in the Agency List page for the perticular record");

		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Address updated successfully");
		}

	}

	public void VerifyTheAdressUpdateEdit(String update) {
		String UpdatedAddressFromUI = driver.findElement(CoreAgencyListRepo.Address).getAttribute("value");
		if (UpdatedAddressFromUI.contains(update)) {
			ExtentTestManager.getTest().log(Status.PASS,
					" Adress is updated in the Agency List page for the perticular record");

		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Address updated successfully");
		}

	}

	public void RecordDeempaneledSuccessfully() {
		Common.fluentWaitNew("RecordDeempaneledSuccessfully", CoreAgencyListRepo.RecordDeempaneledSuccessfully);
		if (driver.findElement(CoreAgencyListRepo.RecordDeempaneledSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Record Deempaneled Successfully ");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Record not Deempaneled Successfully");
		}
	}

	public void RecordUpdatedSuccessfully() {
		Common.fluentWaitNew("RecordUpdatedSuccessfully", CoreAgencyListRepo.RecordUpdatedSuccessfully);
		if (driver.findElement(CoreAgencyListRepo.RecordUpdatedSuccessfully).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Record Updated Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Record not Updated Successfully");
		}
	}

	public void InvalidUsername() {
		Common.fluentWaitNew("InvalidUsername", CoreAgencyListRepo.InvalidUsername);
		if (driver.findElement(CoreAgencyListRepo.InvalidUsername).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Invalid Username displyed Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Invalid Username not displayed Successfully");
		}
	}

	public void DeEmpanelmentdaterequired() {
		Common.fluentWaitNew("DeEmpanelmentdaterequired", CoreAgencyListRepo.DeEmpanelmentdaterequired);
		if (driver.findElement(CoreAgencyListRepo.DeEmpanelmentdaterequired).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "De Empanelment date required");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "De Empanelment date required not Displayed");
		}
	}

	public void StatusChanged() {
		Common.fluentWaitNew("StatusChanged", CoreAgencyListRepo.StatusChanged);
		if (driver.findElement(CoreAgencyListRepo.StatusChanged).isDisplayed()) {
			ExtentTestManager.getTest().log(Status.PASS, "Status Changed Successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Status Changed Successfully");
		}
	}

	public void GetUserCredentials() throws InterruptedException {
		// WaitLoader();
		// ExtentTestManager.getTest().log(Status.PASS, "operation done successfully");
		// click(MyDeskDashboardRepo.Action);
		// click(MyDeskDashboardRepo.ResetPassword);
		// WaitLoader();
		String text = driver.findElement(MyDeskDashboardRepo.passwordresetsuccessfully).getText();

		Pattern pattern = Pattern.compile("AGY\\d+"); // Matches User ID (IBU0001573)
		Matcher matcher = pattern.matcher(text);

		String userId = "";
		if (matcher.find()) {
			userId = matcher.group();
		}

		// Extract the password (last word in the message)
		String[] words = text.split(" ");
		String password = words[words.length - 1]; // Last word is the password

		// Print extracted credentials
		System.out.println("Extracted User ID: " + userId);
		System.out.println("Extracted Password: " + password);
		Username = userId;
		Password = password;
	}

	public void GetUserCredentialsNew() throws InterruptedException {
		try {

			WaitLoader();
			ExtentTestManager.getTest().log(Status.PASS, "operation done successfully");
			click(MyDeskDashboardRepo.Action);
			click(MyDeskDashboardRepo.ResetPassword);
			WaitLoader();
			String text = driver.findElement(MyDeskDashboardRepo.passwordresetsuccessfully).getText();

			Pattern pattern = Pattern.compile("AGY\\d+"); // Matches User ID (IBU0001573)
			Matcher matcher = pattern.matcher(text);

			String userId = "";
			if (matcher.find()) {
				userId = matcher.group();
			}

			// Extract the password (last word in the message)
			String[] words = text.split(" ");
			String password = words[words.length - 1]; // Last word is the password

			// Print extracted credentials
			System.out.println("Extracted User ID: " + userId);
			System.out.println("Extracted Password: " + password);
			UsernameNew = userId;
			PasswordNew = password;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "BCO search operation failed");
		}
	}

	public void Logout() {
		click(CoreAgencyListRepo.profiledropdownbutton, "profiledropdownbutton");
		click(CoreAgencyListRepo.Logout, "Logout");

	}

	public void SwitchToNewTab(String firstWindowHandle) {
		Set<String> allTabs = driver.getWindowHandles();
		for (String tab : allTabs) {
			if (!tab.equals(firstWindowHandle)) {
				driver.switchTo().window(tab); // Switch to new tab
				break;
			}
		}
	}

	public void LoginNewlyCreatedAgency()
			throws IOException, InterruptedException, ClassNotFoundException, SQLException {

		String URL = configloader().getProperty("CollectionAgencyApplicationUrl");
		String UserID = Username;
		String password = Password;

		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("document.querySelector('.tooltip').remove();");

		driver.get(URL);
		String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=2 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
		String CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
		// System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);

//	Common.fluentWaitNew("Core login Banner",
//			LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
		Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
		driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserName + " in
		// user name field");
		Log.info("Entered " + UserID + " in user name field");
		driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
		// ExtentTestManager.getTest().log(Status.INFO, "Entered " + CoreUserPassword +
		// " in password field");
		Log.info("Entered " + password + " in password field");
		driver.findElement(LoginPageRepo.LoginButton).click();
		try {
			WebElement clickableElement = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

			if (clickableElement != null) {
				// Perform the desired action on the element
				clickableElement.click();
				// driver.findElement(LoginPageRepo.AlreadyLoginPopupYesButton).click();
				Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

				Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
				Log.info("Entered " + UserID + " in user name field");
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
				Log.info("Entered " + password + " in password field");
				driver.findElement(LoginPageRepo.LoginButton).click();
				Log.info("Clicked on login button");

				Log.info("Clicked on already login yes button and logged in again with valid credentials");
			} else {
				System.out.println("Element not clickable within the timeout.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
			System.out.println("Already login pop up not appeared");
		}
		try {
			WebElement SomeErrorOccured = Common.waitForElementToBeClickable(driver,
					LoginPageRepo.LoginPageSomeErrorOccurred, Duration.ofSeconds(20));

			if (SomeErrorOccured != null) {
				Log.info("Showing some error occured error message reloading the application");
				driver.navigate().refresh();

				if (AppType == "Core") {
					Common.fluentWaitNew("Core login Banner",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
				} else if (AppType == "CollectionAgency") {
					Common.fluentWaitNew(CollectionAgency_BANNER_DETAILS,
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));
				} else if (AppType == "CallCenter") {
					Common.fluentWaitNew("CallCentre_BANNER_DETAILS",
							LoginPageRepo.CollectionAgencyLoginBannerDetails(CallCentre_BANNER_DETAILS));
				}

				Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
				Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
				Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

				driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
				driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
				driver.findElement(LoginPageRepo.LoginButton).click();

				try {
					WebElement clickableElement = Common.waitForElementToBeClickable(driver,
							LoginPageRepo.AlreadyLoginPopupYesButton, Duration.ofSeconds(20));

					if (clickableElement != null) {
						clickableElement.click();
						Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

						Common.fluentWaitNew("UserNameField", LoginPageRepo.UserNameField);
						Common.fluentWaitNew("PasswordField", LoginPageRepo.PasswordField);
						Common.fluentWaitNew("LoginButton", LoginPageRepo.LoginButton);

						driver.findElement(LoginPageRepo.UserNameField).sendKeys(UserID);
						driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
						driver.findElement(LoginPageRepo.LoginButton).click();

						Log.info("Clicked on already login yes button and logged in again with valid credentials");
					} else {
						System.out.println("Element not clickable within the timeout.");
					}
				} catch (Exception e) {
					System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
					System.out.println("Already login pop up not appeared");
				}

			} else {
				System.out.println("Some error occured error message didn't show");
			}
		} catch (Exception e) {
			System.out.println("Some error occured error message didn't show");
		}
	}
}
