package Core.AlertsandNotifications;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreAlertsTemplateManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;
import java.sql.Types;

public class AlertsTemplateManagement_MainClass {
	
private WebDriver driver;
	
	public AlertsTemplateManagement_MainClass(WebDriver driver) {
		Log.info("Initializing AlertsTemplateManagement_MainClass...");

	    this.driver = driver;

	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);

	    Log.info("AlertsTemplateManagement_MainClass initialized successfully.");
    }
	
	 // Method to check if "Alerts and Notifications" menu is visible
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
    
    // Method to click on Template Management option
    public void clickTemplateManagement() {
    	Log.info("Attempting to click on Template Management sub-menu...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement TemplateManagementsubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateManagementsubMenu));
        
        TemplateManagementsubMenu.click();
        Log.info("Clicked on Template Management sub-menu.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner has disappeared. Template Management page is now loading.");
    }
    
    // Method to validate that the user is on the Template Management page
    public boolean isDisplayed() {
    	Log.info("Checking if the ViewTemplate page is displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        boolean isDisplayed = driver.getCurrentUrl().contains("ViewTemplate");

        Log.info("ViewTemplate page display status: " + isDisplayed);
        return isDisplayed;
    }
    
    // Method to interact with Notification Type field
    public void selectNotificationType(String value) {
    	Log.info("Selecting notification type: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement notificationTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.notificationTypeDropdown));
        notificationTypeDropdown.click();

        Log.info("Dropdown opened. Selecting value: " + value);

        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.Dropdownvalues(value)));
        Dropdownvalues.click();

        Log.info("Notification type selected successfully: " + value);
    }

    // Method to interact with Current Status field
    public void enterCurrentStatus(String value) {
    	Log.info("Entering current status: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement currentStatusField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.currentStatusField));
        currentStatusField.click();

        Log.info("Dropdown opened. Selecting value: " + value);

        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.Dropdownvalues(value)));
        Dropdownvalues.click();

        Log.info("Current status selected successfully: " + value);
    }

    // Method to interact with Category field
    public void enterCategory(String value) {
    	Log.info("Entering category: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement categoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.categoryField));
        categoryField.click();

        Log.info("Dropdown opened. Selecting value: " + value);

        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.Dropdownvalues(value)));
        Dropdownvalues.click();

        Log.info("Category selected successfully: " + value);
    }

    // Method to click on the filter button
    public void clickFilterButton() {
    	Log.info("Attempting to click the Filter button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement filterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.filterButton));
        filterButton.click();

        Log.info("Filter button clicked. Waiting for spinner to disappear...");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Filter applied successfully.");
    }
    
    // Method to click the cross button
    public void clickCrossButton() {
    	Log.info("Attempting to click the Cross button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement crossButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.crossButton));
        crossButton.click();

        Log.info("Cross button clicked successfully.");
    }

    // Method to check if Notification Type input field is cleared
    public boolean isNotificationTypeCleared() {
    	Log.info("Checking if the Notification Type dropdown is cleared...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement notificationTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.notificationTypeDropdown));
        boolean isCleared = notificationTypeDropdown.getText().isEmpty() || notificationTypeDropdown.getText().equals("Select");

        Log.info("Notification Type dropdown cleared status: " + isCleared);
        return isCleared;
    }
    
 // Method to verify table structure
    public boolean verifyTableStructure() {
    	Log.info("Verifying table structure...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for table columns to be visible...");
        WebElement columnName = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnName));
        WebElement columnStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnStatus));
        WebElement columnLastModifiedDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnLastModifiedDate));
        WebElement columnEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnEdit));
        WebElement columnView = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnView));
        WebElement columnViewHistory = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.columnViewHistory));

        boolean isTableStructureCorrect = columnName.isDisplayed() &&
                                          columnStatus.isDisplayed() &&
                                          columnLastModifiedDate.isDisplayed() &&
                                          columnEdit.isDisplayed() &&
                                          columnView.isDisplayed() &&
                                          columnViewHistory.isDisplayed();

        Log.info("Table structure verification result: " + isTableStructureCorrect);
        return isTableStructureCorrect;
    }
    
    // Method to click next page button
    public void clickNextPage() {
    	Log.info("Attempting to click the Next Page button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for Next Page button to be visible...");
        WebElement nextPageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.nextPageButton));

        Log.info("Clicking the Next Page button...");
        nextPageButton.click();

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Successfully navigated to the next page.");
    }

    // Method to get the count of records per page
    public int getRecordCountPerPage() {
    	Log.info("Fetching record count per page...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for table records to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.tableRecords));

        int recordCount = driver.findElements(CoreAlertsTemplateManagementRepo.tableRecords).size();
        Log.info("Total records found on the current page: " + recordCount);

        return recordCount;
    }

    // Method to verify expected number of records per page
    public boolean isRecordCountCorrect(int expectedRecords) {
    	Log.info("Verifying if the record count matches the expected value: " + expectedRecords);

        int actualRecords = getRecordCountPerPage();
        boolean isCorrect = actualRecords == expectedRecords;

        Log.info("Expected records: " + expectedRecords + ", Actual records: " + actualRecords);
        Log.info("Record count verification result: " + isCorrect);

        return isCorrect;
    }
    
 // Method to click on 'Add Template' button
    public void clickAddTemplate() {
    	Log.info("Attempting to click the Add Template button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Add Template button to be visible...");
        WebElement addTemplateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.addTemplateButton));

        Log.info("Clicking the Add Template button...");
        addTemplateButton.click();

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Successfully clicked the Add Template button.");
    }
    
 // Method to interact with Notification Type field
    public void templateselectNotificationType(String value) {
    	Log.info("Selecting notification type: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for the Notification Type dropdown to be visible...");
        WebElement templatenotificationTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatenotificationTypeDropdown));

        Log.info("Clicking the Notification Type dropdown...");
        templatenotificationTypeDropdown.click();

        Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));

        Log.info("Selecting the dropdown value: " + value);
        Dropdownvalues.click();

        Log.info("Notification type '" + value + "' selected successfully.");
    }

    // Method to interact with Current Status field
    public void templateenterInitialStatus(String value) {
    	Log.info("Selecting initial status: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Initial Status dropdown to be visible...");
        WebElement templateInitialStatusDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateInitialStatusDropdown));

        Log.info("Clicking the Initial Status dropdown...");
        templateInitialStatusDropdown.click();

        try {
            Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));
            Log.info("Values available in dropdown. Page loaded successfully.");
        } catch (TimeoutException e) {
            Log.ErrorWithException("Values not available in dropdown. Page did not load completely.", e);
            throw new RuntimeException("Values not available in dropdown. The page is not fully loaded. Please try again.", e);
        }

        Log.info("Selecting the dropdown value: " + value);
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));
        Dropdownvalues.click();

        Log.info("Initial status '" + value + "' selected successfully.");
    }

    // Method to interact with Category field
    public void templateenterCategory(String value) {
    	Log.info("Selecting template category: " + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Category dropdown to be visible...");
        WebElement templateCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateCategoryDropdown));

        Log.info("Clicking the Template Category dropdown...");
        templateCategoryDropdown.click();

        Log.info("Waiting for the dropdown value '" + value + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.dropdownvaluesfortemplatecreation(value)));

        Log.info("Selecting the dropdown value: " + value);
        Dropdownvalues.click();

        Log.info("Template category '" + value + "' selected successfully.");
    }

    // Method to enter template name
    public void enterTemplateName(String templateName) {
    	Log.info("Entering template name: " + templateName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Name field to be visible...");
        WebElement templateNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateNameField));

        Log.info("Clearing the Template Name field...");
        templateNameField.clear();

        Log.info("Entering the Template Name: " + templateName);
        templateNameField.sendKeys(templateName);

        Log.info("Template name '" + templateName + "' entered successfully.");
    }
    
    public List<String> prepareAndExecuteInsertQuery(String query) throws IOException {
    	Log.info("Starting execution of stored procedure with query: " + query);

        List<Object> inputParams = Arrays.asList();
        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);

        Log.info("Calling ExecuteAnyOracleSQLStoredProcedure with input parameters: " + inputParams);
        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(query, inputParams, outputTypes);

        if (results == null || results.size() < 2) {
            Log.error("Stored procedure execution failed or returned insufficient results.");
            return Collections.emptyList(); // Return empty list if no results
        }

        String searchValue = (String) results.get(0);
        String executionMessage = (String) results.get(1);

        Log.info("Stored procedure executed successfully.");
        Log.info("Search Value: " + searchValue);
        Log.info("Execution Message: " + executionMessage);

        Log.info("Returning stored procedure results.");
        return Arrays.asList(searchValue, executionMessage);
    }
    
 // Method to enter template body
    public void enterTemplatebody(String templateName) {
    	Log.info("Entering template body: " + templateName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Template Body field to be visible...");
        WebElement TemplateBodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateBodyField));

        Log.info("Clearing the Template Body field...");
        TemplateBodyField.clear();

        Log.info("Entering the Template Body: " + templateName);
        TemplateBodyField.sendKeys(templateName);

        Log.info("Template body entered successfully.");
    }
    
    public void deleteAlertTemplate(String templatename) throws IOException {
    	Log.info("Starting deletion of alert template: " + templatename);

        String SPname = "SP_DELETE_ALERT_TEMPLATES";
        List<Object> inputParams = Arrays.asList(templatename);
        List<Integer> outputParamTypes = Arrays.asList(Types.VARCHAR);

        Log.info("Executing stored procedure '" + SPname + "' with input parameters: " + inputParams);

        DBUtils.ExecuteAnyOracleSQLStoredProcedure(SPname, inputParams, outputParamTypes);

        Log.info("Alert template '" + templatename + "' deleted successfully.");
    }

    // Method to click on 'Save' button
    public void clickSave() {
    	Log.info("Initiating click action on Save button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Save button to be visible...");
        WebElement templatesaveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatesaveButton));

        Log.info("Clicking the Save button...");
        templatesaveButton.click();

        Log.info("Waiting for the confirmation popup OK button to be visible...");
        WebElement popupokbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.popupokbutton));

        Log.info("Clicking the OK button on the popup...");
        popupokbutton.click();

        Log.info("Save action completed successfully.");
    }

    // Method to verify if success message is displayed
    public boolean isSuccessMessageDisplayed() {
    	Log.info("Checking if the success message is displayed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the success message to be visible...");
        WebElement templatecreationsuccessmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatecreationsuccessmsg));

        boolean isDisplayed = templatecreationsuccessmsg.isDisplayed();
        Log.info("Success message displayed: " + isDisplayed);

        return isDisplayed;
    }
    
    // Method to enter template body
    public void enterTemplatebodyforSMS(String templateName) {
    	Log.info("Starting to enter template body for SMS: " + templateName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the SMS Template Body field to be visible...");
        WebElement TemplateBodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateBodyFieldforsms));

        Log.info("Clearing the existing text in the SMS Template Body field...");
        TemplateBodyField.clear();

        Log.info("Entering template body text for SMS...");
        TemplateBodyField.sendKeys(templateName);

        Log.info("Template body for SMS entered successfully.");
    }
    
    public String getCharCountIndicator() {
    	Log.info("Fetching character count indicator text.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the character count indicator to be visible...");
        WebElement charCountIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.charCountIndicator));

        String charCountText = charCountIndicator.getText();
        Log.info("Character count indicator text retrieved: " + charCountText);

        return charCountText;
    }
    
 // Method to check if Subject field is displayed
    public boolean isSubjectFieldDisplayed() {
    	Log.info("Checking if the Subject field is displayed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Subject field to be visible...");
        WebElement subjectField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.subjectField));

        boolean isDisplayed = subjectField.isDisplayed();
        Log.info("Subject field displayed: " + isDisplayed);

        return isDisplayed;
    }

    // Method to get the Body field text limit
    public boolean getBodyFieldTextLimit() {
    	Log.info("Checking if the body field text limit indicator is displayed.");

        List<WebElement> elements = driver.findElements(CoreAlertsTemplateManagementRepo.charCountIndicator);

        if (!elements.isEmpty()) {
            boolean isDisplayed = elements.get(0).isDisplayed();
            Log.info("Body field text limit indicator found and displayed: " + isDisplayed);
            return isDisplayed;
        }

        Log.warn("Body field text limit indicator not found.");
        return false; // Return false if element is not found
    }
    
    public boolean isTemplateListed(String value) {
    	Log.info("Checking if the template with name '" + value + "' is listed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatecreationsuccessmsg));

        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsTemplateManagementRepo.templateRecords));

        for (WebElement element : elements) {
            if (element.getText().trim().equalsIgnoreCase(value)) { 
                Log.info("Template '" + value + "' is found in the list.");
                return true; 
            }
        }

        Log.warn("Template '" + value + "' is not found in the list.");
        return false; 
    }
    
    // Method to click the 'View' button
    public void clickViewButton() {
    	Log.info("Attempting to click the View button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.viewButton));

        Log.info("View button is visible. Clicking the button.");
        viewButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("View action completed. Spinner is no longer visible.");
    }
    
    // Method to verify template details are displayed
    public boolean isTemplateDetailsDisplayed() { 
    	Log.info("Checking if template details are displayed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait until spinner disappears
        Log.info("Waiting for spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Wait until elements are visible
        Log.info("Waiting for template detail fields to be visible.");
        WebElement templateDetailNotificationType = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateDetailNotificationType));
        WebElement templateDetailStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateDetailStatus));
        WebElement templateDetailTemplateName = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateDetailTemplateName));
        WebElement templateDetailCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templateDetailCategory));
        WebElement TemplateBodyField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.TemplateBodyField));

        // Retrieve text using JavaScript (since getText() and getAttribute() might not work)
        String notificationTypeText = (String) js.executeScript("return arguments[0].value;", templateDetailNotificationType);
        String statusText = (String) js.executeScript("return arguments[0].value;", templateDetailStatus);
        String templateNameText = (String) js.executeScript("return arguments[0].value;", templateDetailTemplateName);
        String categoryText = (String) js.executeScript("return arguments[0].value;", templateDetailCategory);
        String TemplateBodyFieldText = TemplateBodyField.getText();

        boolean allFieldsPopulated = true;

        // Check if any field is empty and print which one
        if (notificationTypeText == null || notificationTypeText.trim().isEmpty()) {
            Log.warn("Notification Type field is empty.");
            allFieldsPopulated = false;
        }
        if (statusText == null || statusText.trim().isEmpty()) {
            Log.warn("Status field is empty.");
            allFieldsPopulated = false;
        }
        if (templateNameText == null || templateNameText.trim().isEmpty()) {
            Log.warn("Template Name field is empty.");
            allFieldsPopulated = false;
        }
        if (categoryText == null || categoryText.trim().isEmpty()) {
            Log.warn("Category field is empty.");
            allFieldsPopulated = false;
        }
        if (TemplateBodyFieldText == null || TemplateBodyFieldText.trim().isEmpty()) {
            Log.warn("Template body field is empty.");
            allFieldsPopulated = false;
        }

        if (allFieldsPopulated) {
            Log.info("All template details are successfully displayed.");
        } else {
            Log.warn("Some template details are missing.");
        }

        return allFieldsPopulated;
    }
    
    // Click the Edit button
    public void clickEditButton() {
    	Log.info("Attempting to click the Edit button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the view dialog box close button to be visible.");
        WebElement viewdialogboxclosebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.viewdialogboxclosebutton));

        Log.info("Clicking the view dialog box close button.");
        viewdialogboxclosebutton.click();

        Log.info("Waiting for the Edit button to be visible.");
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.editButton));

        Log.info("Clicking the Edit button.");
        editButton.click();

        Log.info("Waiting for the spinner to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Edit button clicked successfully.");
    }

    // Edit the template fields
    public void editFields(String newValues) throws InterruptedException {
    	 Log.info("Starting the edit process for fields with new value: " + newValues);

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    Log.info("Waiting for the spinner to disappear.");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));  
    	    Thread.sleep(5000);
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Waiting for the edit initial status dropdown to be visible.");
    	    WebElement editInitialStatusdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.editInitialStatusdropdown));

    	    Log.info("Clicking the edit initial status dropdown.");
    	    editInitialStatusdropdown.click();

    	    Log.info("Waiting for the dropdown value to be visible.");
    	    WebElement editInitialStatusdropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.editInitialStatusdropdownvalue(newValues)));

    	    Log.info("Selecting the dropdown value: " + newValues);
    	    editInitialStatusdropdownvalue.click(); 

    	    Log.info("Edit process completed successfully.");
    }

    // Enter the reason for editing the template
    public void enterEditReason(String reason) {
    	Log.info("Starting to enter edit reason: " + reason);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the edit reason field to be visible.");
        WebElement editreason = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.editreason));

        Log.info("Clearing the existing text in the edit reason field.");
        editreason.clear();

        Log.info("Entering the new edit reason.");
        editreason.sendKeys(reason);

        Log.info("Edit reason entered successfully.");
    }

    // Save changes made to the template
    public void saveChanges() throws InterruptedException {
    	Log.info("Starting the process to save changes.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the edit template section to be visible.");
        WebElement edittemplate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.edittemplate));

        Log.info("Scrolling down inside the edit template section.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", edittemplate);

        Log.info("Waiting for a short delay before interacting with the update button.");
        Thread.sleep(5000);

        Log.info("Waiting for the update button to be visible.");
        WebElement updatebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.updatebutton));

        Log.info("Clicking the update button.");
        updatebutton.click();

        Log.info("Waiting for the confirmation popup OK button to be visible.");
        WebElement popupokbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.popupokbutton));

        Log.info("Clicking the confirmation popup OK button.");
        popupokbutton.click();

        Log.info("Waiting for the success message indicating modifications were successful.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.modifiedsuccessmsg));

        Log.info("Changes saved successfully.");
    }
    
    public boolean isTemplateStatusInactive(String templateName) {
    	Log.info("Checking if the template '" + templateName + "' has status 'Inactive'.");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

            Log.info("Waiting for any loading spinners to disappear.");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsTemplateManagementRepo.modifiedsuccessmsg));

            Log.info("Waiting for the template table to be visible.");
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.templatetable));

            Log.info("Locating all rows within the template table.");
            List<WebElement> rows = table.findElements(CoreAlertsTemplateManagementRepo.templatetablerows);

            Log.info("Iterating through the table rows to find the template.");
            for (WebElement row : rows) {
                List<WebElement> columns = row.findElements(CoreAlertsTemplateManagementRepo.templatetablecolumns);

                if (columns.size() >= 2) { // Ensure the row has at least two columns (Template Name & Status)
                    String currentTemplateName = columns.get(0).getText().trim(); // Template Name (1st column)
                    String currentStatus = columns.get(1).getText().trim(); // Status (2nd column)

                    Log.info("Checking template: '" + currentTemplateName + "' with status: '" + currentStatus + "'.");

                    if (currentTemplateName.equalsIgnoreCase(templateName)) {
                        if (currentStatus.equalsIgnoreCase("Inactive")) {
                            Log.info("Template '" + templateName + "' is inactive.");
                            return true;
                        } else {
                            Log.info("Template '" + templateName + "' has status '" + currentStatus + "' instead of 'Inactive'.");
                            return false;
                        }
                    }
                }
            }

            Log.info("Template '" + templateName + "' was not found in the table.");
            return false;

        } catch (TimeoutException e) {
            Log.ErrorWithException("Table not found within the timeout period.", e);
            return false;
        }
    }
    
    // Method to click on the "View History" button
    public void clickViewHistory() {
    	Log.info("Clicking on the 'View History' button.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for loading spinners to disappear.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAlertsTemplateManagementRepo.modifiedsuccessmsg));

        Log.info("Waiting for the 'View History' button to be visible.");
        WebElement historyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsTemplateManagementRepo.historyButton));

        Log.info("Clicking the 'View History' button.");
        historyButton.click();

        Log.info("Waiting for any loading spinners to disappear after clicking 'View History'.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("'View History' action completed successfully.");
    }

    // Method to verify if the history pop-up is displayed correctly
    public boolean isHistoryPopupDisplayed() { 
    	 Log.info("Verifying if the History popup is displayed.");

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    try {
    	        Log.info("Waiting for history table headers to be visible.");
    	        List<WebElement> historyTableFields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsTemplateManagementRepo.historytablefields));

    	        // Expected column headers
    	        List<String> expectedHeaders = Arrays.asList("TEMPLATE NAME", "STATUS", "MODIFIED DATE", "REMARKS");

    	        Log.info("Extracting actual history table headers.");
    	        List<String> actualHeaders = historyTableFields.stream()
    	                                                       .map(WebElement::getText)
    	                                                       .map(String::trim)
    	                                                       .collect(Collectors.toList());

    	        Log.info("Validating history table headers.");
    	        for (String expectedHeader : expectedHeaders) {
    	            if (!actualHeaders.contains(expectedHeader)) {
    	                Log.warn("Missing header: " + expectedHeader);
    	                return false;
    	            }
    	        }

    	        Log.info("Waiting for history table data to be visible.");
    	        List<WebElement> historyTableData = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreAlertsTemplateManagementRepo.historytabledata));

    	        Log.info("Checking if history table contains data.");
    	        boolean isTableDataPresent = historyTableData.stream()
    	                                                     .map(WebElement::getText)
    	                                                     .map(String::trim)
    	                                                     .anyMatch(text -> !text.isEmpty());

    	        if (!isTableDataPresent) {
    	            Log.warn("History table data is empty.");
    	            return false;
    	        }

    	        Log.info("History popup validation successful.");
    	        return true;

    	    } catch (TimeoutException e) {
    	        Log.error("Timeout occurred while waiting for elements: " + e.getMessage());
    	        return false;
    	    } catch (Exception e) {
    	        Log.error("Unexpected error: " + e.getMessage());
    	        return false;
    	    }
    }

}
