package Core.AlertsandNotifications;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Page_Repository.CoreAlertsNotificationManagementRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AlertsNotificationManagement_MainClass {
	
private WebDriver driver;
	
	public AlertsNotificationManagement_MainClass(WebDriver driver) {
		Log.info("Initializing AlertsNotificationManagement_MainClass...");

	    this.driver = driver;

	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);

	    Log.info("AlertsNotificationManagement_MainClass initialized successfully.");
    }
	
	public boolean isAlertsAndNotificationsMenuVisible() throws InterruptedException {
    	Log.info("Verifying if Alerts and Notifications menu is visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));

        Log.info("Alerts and Notifications menu found. Scrolling to the element...");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);

        Thread.sleep(5000); // Keeping it as per your request

        boolean isVisible = alertsAndNotificationsMenu.isDisplayed();
        
        Log.info("Alerts and Notifications menu visibility status: " + isVisible);

        return isVisible;
    }
	
	public void clickOnAlertsAndNotifications() throws InterruptedException {
		 Log.info("Attempting to click on 'Alerts and Notifications' menu...");

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		    
		    WebElement AlertsandNotificationsmainmenu = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu)
		    );

		    Log.info("Clicking on 'Alerts and Notifications' main menu...");
		    AlertsandNotificationsmainmenu.click();

		    Log.info("'Alerts and Notifications' menu clicked successfully.");
	    }
   
   public boolean areSubMenusDisplayed() {
   	 Log.info("Verifying if Alerts and Notifications sub-menus are displayed...");

   	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
   	    WebElement alertsAndNotificationssubMenus = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.alertsAndNotificationssubMenus));

   	    boolean isVisible = alertsAndNotificationssubMenus.isDisplayed();
   	    
   	    Log.info("Alerts and Notifications sub-menus visibility status: " + isVisible);

   	    return isVisible;
   }
   
   // Method to click on the Notification Management menu
   public void clickNotificationManagementMenu() {
	   Log.info("Waiting for Notification Management menu to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement notificationManagementMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationManagementMenu));

	    Log.info("Clicking on Notification Management menu...");
	    notificationManagementMenu.click();

	    try {
	        Log.info("Waiting for page navigation to be displayed...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.pagination));
	        Log.info("Page navigation displayed. Page loaded successfully.");
	    } catch (TimeoutException e) {
	        Log.ErrorWithException("Page navigation not displayed. Page did not load completely.", e);
	        throw new RuntimeException("Page navigation not displayed. Page did not load completely. Please try again.", e);
	    }
   }
   
   // Method to verify the Notification Management menu is displayed
   public boolean isNotificationManagementMenuDisplayed() {
	   Log.info("Waiting for spinner to disappear...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Checking if the current URL contains 'ViewNotificationSchedules'...");
	    boolean isDisplayed = driver.getCurrentUrl().contains("ViewNotificationSchedules");

	    Log.info("Notification Management menu display status: " + isDisplayed);
	    return isDisplayed;
   }
   
   // Method to verify Notification Management Type field is displayed
   public boolean isNotificationTypeFieldDisplayed() {
	   Log.info("Waiting for Notification Type field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement notificationTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationTypeField));

	    boolean isDisplayed = notificationTypeField.isDisplayed();
	    Log.info("Notification Type field display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to verify SMS option is available
   public boolean isSMSOptionAvailable() {
	   Log.info("Waiting for Notification Type field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement notificationTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationTypeField));

	    Log.info("Clicking on Notification Type field...");
	    notificationTypeField.click();

	    Log.info("Waiting for SMS option to be visible...");
	    WebElement smsOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.smsOption));

	    boolean isDisplayed = smsOption.isDisplayed();
	    Log.info("SMS option display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to verify Email option is available
   public boolean isEmailOptionAvailable() {
	   Log.info("Waiting for Email option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement emailOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.emailOption));

	    boolean isDisplayed = emailOption.isDisplayed();
	    Log.info("Email option display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Method to verify Category Type field is displayed
   public boolean isCategoryTypeFieldDisplayed() {
	   Log.info("Waiting for Category Type field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement categoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.categoryDropdown));

	    boolean isDisplayed = categoryDropdown.isDisplayed();
	    Log.info("Category Type field display status: " + isDisplayed);

	    return isDisplayed;
   }
   
// Method to verify Promotions option is available
   public boolean isPromotionsOptionAvailable() {
	   Log.info("Waiting for Category dropdown to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement categoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.categoryDropdown));

	    Log.info("Clicking on Category dropdown...");
	    categoryDropdown.click();

	    Log.info("Waiting for Promotions option to be visible...");
	    WebElement promotionsOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.PromotionsOption));

	    boolean isDisplayed = promotionsOption.isDisplayed();
	    Log.info("Promotions option display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to verify Notification option is available
   public boolean isNotificationOptionAvailable() {
	   Log.info("Waiting for Notification option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement notificationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.NotificationOption));

	    boolean isDisplayed = notificationOption.isDisplayed();
	    Log.info("Notification option display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Check if the "Schedule type" field is displayed
   public boolean isScheduleTypeFieldDisplayed() {
	   Log.info("Waiting for Schedule Type field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement scheduleTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleTypeField));

	    boolean isDisplayed = scheduleTypeField.isDisplayed();
	    Log.info("Schedule Type field display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Check for "One time" option
   public boolean isOneTimeOptionDisplayed() {
	   Log.info("Waiting for Schedule Type field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement scheduleTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleTypeField));

	    Log.info("Clicking on Schedule Type field...");
	    scheduleTypeField.click();

	    Log.info("Waiting for One-Time option to be visible...");
	    WebElement oneTimeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.oneTimeOption));

	    boolean isDisplayed = oneTimeOption.isDisplayed();
	    Log.info("One-Time option display status: " + isDisplayed);

	    return isDisplayed; 
   }

   // Check for "Daily" option
   public boolean isDailyOptionDisplayed() {
	   Log.info("Waiting for Daily option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement dailyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.dailyOption));

	    boolean isDisplayed = dailyOption.isDisplayed();
	    Log.info("Daily option display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Check for "Weekly" option
   public boolean isWeeklyOptionDisplayed() {
	   Log.info("Waiting for Weekly option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement weeklyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.weeklyOption));

	    boolean isDisplayed = weeklyOption.isDisplayed();
	    Log.info("Weekly option display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Check for "Monthly" option
   public boolean isMonthlyOptionDisplayed() {
	   Log.info("Waiting for Monthly option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement monthlyOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.monthlyOption));

	    boolean isDisplayed = monthlyOption.isDisplayed();
	    Log.info("Monthly option display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Method to check if Status field is present
   public boolean isStatusFieldPresent() {
	   Log.info("Waiting for Status field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement statusField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.statusField));

	    boolean isDisplayed = statusField.isDisplayed();
	    Log.info("Status field display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if Active option is available
   public boolean isActiveOptionAvailable() {
	   Log.info("Waiting for Status field to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement statusField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.statusField));

	    Log.info("Clicking on Status field...");
	    statusField.click();

	    Log.info("Waiting for Active option to be visible...");
	    WebElement activeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.activeOption));

	    boolean isDisplayed = activeOption.isDisplayed();
	    Log.info("Active option display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if Inactive option is available
   public boolean isInactiveOptionAvailable() {
	   Log.info("Waiting for Inactive option to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement inactiveOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.inactiveOption));

	    boolean isDisplayed = inactiveOption.isDisplayed();
	    Log.info("Inactive option display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Method to verify if the "Schedule type" column is displayed
   public boolean isScheduleTypeColumnDisplayed() {
	   Log.info("Waiting for Schedule Type column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement scheduleTypeColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleTypeColumn));

	    boolean isDisplayed = scheduleTypeColumn.isDisplayed();
	    Log.info("Schedule Type column display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Method to check if the "Template name" column is displayed
   public boolean isTemplateNameColumnDisplayed() {
	   Log.info("Waiting for Template Name column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement templateNameColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.templateNameColumn));

	    boolean isDisplayed = templateNameColumn.isDisplayed();
	    Log.info("Template Name column display status: " + isDisplayed);

	    return isDisplayed;
   }
   
// Method to verify if the Schedule Start column is displayed
   public boolean isScheduleStartColumnDisplayed() {
	   Log.info("Waiting for Schedule Start column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement scheduleStartColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleStartColumn));

	    boolean isDisplayed = scheduleStartColumn.isDisplayed();
	    Log.info("Schedule Start column display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to get the text of the Schedule Start column header
   public boolean areScheduleStartDatesValid() {
	   Log.info("Waiting for Schedule Start column dates to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    List<WebElement> scheduleStartColumns = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsNotificationManagementRepo.scheduleStartColumndateformat));

	    Log.info("Extracting and validating date formats...");
	    List<String> columnTexts = new ArrayList<>();

	    for (WebElement column : scheduleStartColumns) {
	        String dateText = column.getText().trim(); // Trim to remove unnecessary spaces
	        columnTexts.add(dateText);

	        if (!isDateInExpectedFormat(dateText)) {
	            Log.error("Invalid date format found: " + dateText);
	            return false; // Return false immediately if any date is invalid
	        }
	    }

	    Log.info("All schedule start dates are in the expected format.");
	    return true; // Return true if all dates are valid
	}
   
   public boolean isDateInExpectedFormat(String dateText) {
	   Log.info("Validating date format for: " + dateText);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    dateFormat.setLenient(false); // Ensures strict format checking

	    try {
	        dateFormat.parse(dateText); // Try to parse the given date
	        Log.info("Date format is valid: " + dateText);
	        return true; // If parsing succeeds, format is correct
	    } catch (ParseException e) {
	        Log.ErrorWithException("Invalid date format: " + dateText, e);
	        return false; // If parsing fails, format is incorrect
	    }
	}
   
   // Method to check if Schedule End column is visible
   public boolean isScheduleEndColumnDisplayed() {
	   Log.info("Waiting for Schedule End column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement scheduleEndColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleEndColumn));

	    boolean isDisplayed = scheduleEndColumn.isDisplayed();
	    Log.info("Schedule End column display status: " + isDisplayed);

	    return isDisplayed;
   }
   
// Method to check the format of Schedule End column value
   public boolean isScheduleEndDateCorrectlyFormatted() {
	   Log.info("Waiting for Schedule End column dates to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    List<WebElement> scheduleEndColumndateformat = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsNotificationManagementRepo.scheduleEndColumndateformat));

	    Log.info("Extracting and validating date formats...");
	    List<String> columnTexts = new ArrayList<>();

	    for (WebElement column : scheduleEndColumndateformat) {
	        String dateText = column.getText().trim(); // Trim to remove unnecessary spaces
	        columnTexts.add(dateText);

	        if (!isDateInExpectedFormat(dateText)) {
	            Log.error("Invalid date format found: " + dateText);
	            return false; // Return false immediately if any date is invalid
	        }
	    }

	    Log.info("All schedule end dates are in the expected format.");
	    return true; // Return true if all dates are valid
   }
   
// Method to check if the "Time" column is displayed
   public boolean isTimeColumnDisplayed() {
	   Log.info("Waiting for the Time column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement timeColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.timeColumn));

	    boolean isDisplayed = timeColumn.isDisplayed();
	    Log.info("Time column display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if the "Recurrence" column is displayed
   public boolean isRecurrenceColumnDisplayed() {
	   Log.info("Waiting for the Recurrence column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement recurrenceColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.recurrenceColumn));

	    boolean isDisplayed = recurrenceColumn.isDisplayed();
	    Log.info("Recurrence column display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if the "Day of week" column is displayed
   public boolean isDayOfWeekColumnDisplayed() {
	   Log.info("Waiting for the Day of the Week column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement dayOfWeekColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.dayOfWeekColumn));

	    boolean isDisplayed = dayOfWeekColumn.isDisplayed();
	    Log.info("Day of the Week column display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if the "Day of month" column is displayed
   public boolean isDayOfMonthColumnDisplayed() {
	   Log.info("Waiting for the Day of the Month column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement dayOfMonthColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.dayOfMonthColumn));

	    boolean isDisplayed = dayOfMonthColumn.isDisplayed();
	    Log.info("Day of the Month column display status: " + isDisplayed);

	    return isDisplayed;
   }

   // Method to check if the "Status" column is displayed
   public boolean isStatusColumnDisplayed() {
	   Log.info("Waiting for the Status column to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement statusColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.statusColumn));

	    boolean isDisplayed = statusColumn.isDisplayed();
	    Log.info("Status column display status: " + isDisplayed);

	    return isDisplayed;
   }
   
   // Method to click on the relevant section
   public void clickOnRelevantSection() throws InterruptedException {
	   Log.info("Waiting for the spinner to disappear before proceeding...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Initial wait complete. Introducing a short delay...");
	    Thread.sleep(5000);

	    Log.info("Rechecking spinner visibility...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Scrolling to the bottom of the page...");
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	    Log.info("Introducing another short delay...");
	    Thread.sleep(5000);

	    Log.info("Final check for spinner disappearance...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Waiting for the cancel button to be clickable...");
	    WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(CoreAlertsNotificationManagementRepo.cancelButton));
	    
	    Log.info("Clicking the cancel button...");
	    cancelButton.click();

	    Log.info("Waiting for the spinner to disappear after clicking cancel...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Waiting for the navigation section to be visible...");
	    WebElement navigationSection = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.navigationSection));
	    
	    Log.info("Clicking on the navigation section...");
	    navigationSection.click();

	    Log.info("Successfully clicked on the relevant section.");
   }
   
   // Method to click on the edit button
   public void clickEditButton() {
	   Log.info("Waiting for the edit button to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.editButton));

	    Log.info("Edit button is visible. Clicking the edit button...");
	    editButton.click();

	    Log.info("Successfully clicked the edit button.");
   }

   // Method to verify if the edit interface opens
   public boolean isEditInterfaceDisplayed() {
	   Log.info("Waiting for the edit interface to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement editInterface = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.editInterface));

	    boolean isDisplayed = editInterface.isDisplayed();
	    Log.info("Edit interface visibility status: " + isDisplayed);

	    return isDisplayed;
   }
   
// Method to check the visibility of pagination controls
   public boolean isPaginationAvailable() {
	   Log.info("Checking if pagination controls are available...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    List<WebElement> paginationControls = wait.until(
	        ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsNotificationManagementRepo.paginationControls)
	    );

	    for (WebElement element : paginationControls) {
	        if (!element.isDisplayed()) {
	            Log.warn("Pagination element not displayed: " + element.getText());
	            return false;
	        }
	    }

	    Log.info("Pagination controls are displayed.");
	    return true;
   }
   
   // Method to enter a keyword in the search box
   public void enterSearchKeyword(String keyword) {
	   Log.info("Attempting to enter search keyword: " + keyword);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for schedule type field to be visible...");
	    WebElement scheduleTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleTypeField));
	    scheduleTypeField.click();
	    Log.info("Clicked on the schedule type field.");

	    Log.info("Waiting for dropdown values to be visible for keyword: " + keyword);
	    WebElement dropdownValues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.Dropdownvalues(keyword)));
	    dropdownValues.click();
	    Log.info("Selected the dropdown value: " + keyword);
   }

   // Method to click the search button
   public void clickSearchButton() {
	   Log.info("Attempting to click the search button...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for the search button to be visible...");
	    WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.searchButton));
	    
	    Log.info("Clicking the search button...");
	    searchButton.click();
	    
	    Log.info("Waiting for the spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Search button clicked successfully, and loading spinner is gone.");
   }

   // Method to verify the search results
   public boolean areResultsDisplayed(String expectedText) {
	   Log.info("Checking if search results contain the expected text: " + expectedText);

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for search results to be visible...");
	    List<WebElement> searchResults = wait.until(
	        ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsNotificationManagementRepo.searchResults)
	    );

	    Log.info("Search results retrieved. Checking for matching text...");

	    for (WebElement result : searchResults) {
	        String actualText = result.getText().trim();
	        Log.info("Found result: " + actualText);

	        if (actualText.equalsIgnoreCase(expectedText)) {
	            Log.info("Matching result found: " + actualText);
	            return true; // Matching result found
	        }
	    }

	    Log.warn("No matching search result found. Listing available results:");
	    for (WebElement result : searchResults) {
	        Log.warn("Result: " + result.getText());
	    }

	    return false;
   }
   
   // Method to click the cross button
   public void clickCrossButton() {
	   Log.info("Attempting to click on the cross button...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for the cross button to be visible...");
	    WebElement crossButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.crossButton));

	    Log.info("Cross button is visible. Clicking the button now...");
	    crossButton.click();

	    Log.info("Cross button clicked successfully.");
   }

   // Method to clear the input field and check if focused
   public boolean isInputFieldClearedAndFocused() {
	   Log.info("Checking if the input field is cleared and focused...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for the schedule type field to be visible...");
	    WebElement scheduleTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.scheduleTypeField));

	    String fieldText = scheduleTypeField.getText().trim();
	    Log.info("Retrieved text from the input field: '" + fieldText + "'");

	    boolean isCleared = fieldText.isEmpty() || fieldText.equals("Select");

	    if (isCleared) {
	        Log.info("Input field is cleared or contains the default placeholder 'Select'.");
	    } else {
	        Log.warn("Input field is not cleared. Current text: '" + fieldText + "'");
	    }

	    return isCleared;
   }
   
   // Method to click Configure button
   public void clickConfigureButton() {
	   Log.info("Waiting for the Configure button to be visible...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement configureButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.configureButton));

	    Log.info("Configure button is visible. Clicking the button...");
	    configureButton.click();

	    Log.info("Configure button clicked successfully.");
   }
   
// Method to check presence of Notification Management Type dropdown
   public boolean isNotificationManagementTypeDropdownPresent() throws InterruptedException {
	   Log.info("Checking if Notification Management Type dropdown is present...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Thread.sleep(5000);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	    Log.info("Waiting for the Notification Management Type dropdown to be visible...");
	    WebElement notificationTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationTypeField));

	    boolean isDisplayed = notificationTypeField.isDisplayed();
	    Log.info("Notification Management Type dropdown is " + (isDisplayed ? "present." : "not present."));

	    return isDisplayed;
   }

   // Method to check presence of Template Name dropdown
   public boolean isTemplateNameDropdownPresent() {
	   Log.info("Checking if Template Name dropdown is present...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for the Template Name dropdown to be visible...");
	    WebElement templateNameDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.templateNameDropdown));

	    boolean isDisplayed = templateNameDropdown.isDisplayed();
	    Log.info("Template Name dropdown is " + (isDisplayed ? "present." : "not present."));

	    return isDisplayed;
   }

   // Method to check presence of View Template button
   public boolean isViewTemplateButtonPresent() {
	   Log.info("Checking if View Template button is present...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for the View Template button to be visible...");
	    WebElement viewTemplateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.viewTemplateButton));

	    boolean isDisplayed = viewTemplateButton.isDisplayed();
	    Log.info("View Template button is " + (isDisplayed ? "present." : "not present."));

	    return isDisplayed;
   }

}