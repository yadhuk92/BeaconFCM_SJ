package Core.AlertsandNotifications;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.Types;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.CoreAlertsMaskAccountAndMaskingAuthorizationRepo;
import com.Page_Repository.CoreAlertsNotificationManagementRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;

public class AlertsMaskAccountAndMaskingAuthorization_MainClass {
	
private WebDriver driver;
	
	public AlertsMaskAccountAndMaskingAuthorization_MainClass(WebDriver driver) { 
		Log.info("Initializing AlertsMaskAcAndMaskingAutho_MainClass...");  

	    this.driver = driver;  

	    Log.info("Initializing WebElements using PageFactory...");  
	    PageFactory.initElements(driver, this);  

	    Log.info("AlertsMaskAcAndMaskingAutho_MainClass initialized successfully."); 
    }
	
	// Method to click on alerts and notifications
    public void clickOnAlertsAndNotifications() throws InterruptedException {
    	Log.info("Waiting for Alerts and Notifications menu to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));

        Log.info("Scrolling to Alerts and Notifications menu...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);

        Log.info("Waiting for 5 seconds...");
        Thread.sleep(5000);

        Log.info("Waiting for Alerts and Notifications main menu to be visible...");
        WebElement AlertsandNotificationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu));

        Log.info("Clicking on Alerts and Notifications main menu...");
        AlertsandNotificationsmainmenu.click();

        Log.info("Successfully clicked on Alerts and Notifications main menu.");
    }

    // Method to navigate to the mask account window
    public void clickOnMaskAccountWindow() {
    	Log.info("Waiting for Mask Account Window link to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement maskAccountWindowLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.maskAccountWindowLink));

        Log.info("Clicking on Mask Account Window link...");
        maskAccountWindowLink.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Successfully clicked on Mask Account Window.");
    }

    // Method to verify mask account window is displayed
    public boolean isMaskAccountWindowDisplayed() {
    	Log.info("Retrieving the current URL...");
        String currentUrl = driver.getCurrentUrl();

        Log.info("Checking if the URL contains 'MaskAccount'...");
        boolean isDisplayed = currentUrl.contains("MaskAccount");

        Log.info("Mask Account Window displayed status: " + isDisplayed);
        return isDisplayed; 
    }
    
    // Page Actions
    public void enterAccountNumber(String accountNumber) throws InterruptedException { 
    	Log.info("Waiting for spinner to disappear...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Pausing for 5 seconds...");
        Thread.sleep(5000);

        Log.info("Waiting for spinner to disappear again...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for account number field to be visible...");
        WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountNumberField));

        Log.info("Clearing the account number field...");
        accountNumberField.clear();

        Log.info("Entering account number: " + accountNumber);
        accountNumberField.sendKeys(accountNumber);

        Log.info("Account number entered successfully.");
    }

    public void clickGetButton() {
    	Log.info("Waiting for Get button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement getButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.getButton));

        Log.info("Clicking on Get button...");
        getButton.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Get button clicked successfully.");
    }

    public void selectMaskStatus(String value) {
    	Log.info("Waiting for Mask Status dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement statusFieldDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.statusFieldDropdown));

        Log.info("Clicking on Mask Status dropdown...");
        statusFieldDropdown.click();

        Log.info("Waiting for dropdown value: " + value + " to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.Dropdownvalues(value)));

        Log.info("Selecting dropdown value: " + value);
        Dropdownvalues.click();

        Log.info("Mask Status selected successfully.");
    }

    public void selectAllAccountLife() {
    	Log.info("Waiting for 'All Account Life' radio button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement allAccountLifeRadioButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.allAccountLifeRadioButton));

        Log.info("Clicking on 'All Account Life' radio button...");
        allAccountLifeRadioButton.click();

        Log.info("'All Account Life' radio button selected successfully.");
    }

    public void enterReason(String reason) {
    	Log.info("Waiting for Reason field to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement reasonField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.reasonField));

        Log.info("Clearing the Reason field...");
        reasonField.clear();

        Log.info("Entering reason: " + reason);
        reasonField.sendKeys(reason);

        Log.info("Reason entered successfully.");
    }
    
    public void deleteMaskedAccount(String accountNumber) throws IOException, ClassNotFoundException, SQLException {
    	Log.info("Preparing to delete masked account with account number: " + accountNumber);
        
        String query = "delete from MST_MASKED_ACCOUNTS where account_no='" + accountNumber + "'";
        
        Log.info("Executing SQL delete statement...");
        DBUtils.executeSQLStatement(query);
        
        Log.info("Successfully deleted masked account with account number: " + accountNumber);
    }

    public void clickSaveButton() {
    	Log.info("Waiting for Save button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.saveButton));

        Log.info("Clicking on Save button...");
        saveButton.click();

        Log.info("Save button clicked successfully.");
    }

    public void confirmPopup() {
    	Log.info("Waiting for confirmation popup OK button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement confirmationPopupOkButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.confirmationPopupOkButton));

        Log.info("Clicking on confirmation popup OK button...");
        confirmationPopupOkButton.click();

        Log.info("Confirmation popup OK button clicked successfully.");
    }

    public String getSuccessMessage() {
    	Log.info("Waiting for success message to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.successMessage));
            String messageText = successMessage.getText();
            
            Log.info("Success message retrieved: " + messageText);
            return messageText;
        } catch (TimeoutException e) {
            Log.error("Success message not displayed within the timeout period.");
            throw new RuntimeException("Success message not displayed within the timeout period. Please try again.", e);
        }
    }
    
    public List<Object> createZoneUser() throws IOException, InterruptedException, ClassNotFoundException, SQLException {

    	Log.info("Reading Zone User Credentials from properties file...");

        String loginUserId;
        String loginPassword;

        String fileName = "CoreZoneUserCredentials_CoreUserManagement_Zone_User_Creation.properties";
        Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
        loginUserId = properties.getProperty("Zone_User_ID");

        Log.info("Initializing input parameters for stored procedure...");
        List<Object> inputParams = Arrays.asList(loginUserId, "Mumbai", "Manal metha", "metha@example.com", 1122889900);
        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

        Log.info("Executing stored procedure: ZoneUserIDGeneratorWithInputZoneName...");
        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ZoneUserIDGeneratorWithInputZoneName", inputParams, outputTypes);

        if (results == null || results.size() < 2) {
            Log.warn("Stored procedure returned null or incomplete results.");
            return results; // Return the results (which might be null or incomplete)
        }

        loginUserId = (String) results.get(0);
        loginPassword = (String) results.get(1);
        //String SP_ExecutionMessage = (String) results.get(2);

        Log.info("Updating properties file with generated credentials...");
        Map<String, String> updates = new HashMap<>();
        updates.put("HO_User_ID", loginUserId);
        updates.put("HO_User_Password", loginPassword);

        if (properties.isEmpty()) {
            Log.info("Properties file is empty, updating with new credentials...");
            PropertiesFileUtil.updateProperties(fileName, updates);
        } else {
            Log.info("Properties file already contains values. Skipping update.");
            loginUserId = properties.getProperty("HO_User_ID");
            loginPassword = properties.getProperty("HO_User_Password");
        }

        Log.info("User ID: " + loginUserId);
        Log.info("Password: " + loginPassword);

        Log.info("Returning generated credentials...");
        return Arrays.asList(loginUserId, loginPassword);
    }
    
 // Method to navigate to the Mask Authorization section
    public void navigateToMaskAuthorization() {
    	Log.info("Waiting for Mask Authorization option to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement maskAuthorizationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.maskAuthorizationOption));

        Log.info("Clicking on Mask Authorization option...");
        maskAuthorizationOption.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Navigated to Mask Authorization successfully.");
    }

    // Method to search for a masked account number
    public void searchMaskedAccount(String accountNumber) {
    	Log.info("Waiting for Masked Account Number field to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement maskedAccountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.maskedAccountNumberField));

        Log.info("Clearing the Masked Account Number field...");
        maskedAccountNumberField.clear();

        Log.info("Entering masked account number: " + accountNumber);
        maskedAccountNumberField.sendKeys(accountNumber);

        Log.info("Waiting for Search button to be visible...");
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.searchButton));

        Log.info("Clicking on Search button...");
        searchButton.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Search for masked account completed successfully.");
    }

    // Method to approve the masking request
    public void approveRequest() throws InterruptedException {
    	Log.info("Waiting for spinner to disappear before proceeding...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for first result to be visible...");
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.firstResult));

        Log.info("Clicking on the first result...");
        firstResult.click();

        Log.info("Waiting for spinner to disappear after selecting the first result...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Pausing for 5 seconds...");
        Thread.sleep(5000);

        Log.info("Waiting for Approval button to be visible...");
        WebElement approvalButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.approvalButton));

        Log.info("Clicking on Approval button...");
        approvalButton.click();

        Log.info("Request approval process completed successfully.");
    }

    // Method to get the success message
    public String getapprovalSuccessMessage() {
    	Log.info("Waiting for approval success message to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        try {
            WebElement approvalSuccessMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.approvalsuccessMessage)
            );

            Log.info("Approval success message displayed: " + approvalSuccessMessage.getText());
            return approvalSuccessMessage.getText();
        } catch (Exception e) {
            Log.ErrorWithException("Approval success message not displayed within the expected time.", e);
            throw new RuntimeException("Approval success message not displayed within the expected time. Please try again.", e);
        }
    }
    
    // Method to navigate to Notification Management
    public void navigateToNotificationManagement() {
    	Log.info("Navigating to Alerts and Notifications main menu...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        WebElement alertsAndNotificationsMainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu));
        Log.info("Clicking on Alerts and Notifications main menu...");
        alertsAndNotificationsMainMenu.click();

        Log.info("Waiting for Notification Management menu to be visible...");
        WebElement notificationManagementMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationManagementMenu));
        Log.info("Clicking on Notification Management menu...");
        notificationManagementMenu.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        try {
            Log.info("Waiting for pagination element to confirm successful page load...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.pagination));
            Log.info("Notification Management page loaded successfully.");
        } catch (TimeoutException e) {
            Log.ErrorWithException("Page navigation not displayed. Notification Management page did not load completely.", e);
            throw new RuntimeException("Page navigation not displayed. Notification Management page did not load completely. Please try again.", e);
        }
    }

    // Method to configure notification
    public void configureNotification(String notificationType, String template) throws InterruptedException {
    	Log.info("Starting configuration of notification with type: " + notificationType + " and template: " + template);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Locating and clicking on 'Configure' button...");
        WebElement configureButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.configureButton));
        configureButton.click();

        Log.info("Waiting for spinner to disappear after clicking 'Configure'...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        try {
            Log.info("Waiting for 'Validate' button to be visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.validateButton));
            Log.info("'Validate' button is displayed successfully.");
        } catch (TimeoutException e) {
            Log.ErrorWithException("Validate button not displayed. Page did not load completely.", e);
            throw new RuntimeException("Validate button not displayed. Page did not load completely. Please try again.", e);
        }

        Log.info("Waiting for spinner to disappear before selecting notification type...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Thread.sleep(5000);

        Log.info("Selecting notification type: " + notificationType);
        WebElement notificationTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.notificationTypeField));
        notificationTypeField.click();
        WebElement dropdownValuesForNotificationType = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.Dropdownvalues(notificationType)));
        dropdownValuesForNotificationType.click();

        Log.info("Waiting for spinner to disappear before selecting template...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Thread.sleep(5000);

        Log.info("Selecting template: " + template);
        WebElement templateNameDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.templateNameDropdown));
        templateNameDropdown.click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        WebElement dropdownValuesForTemplate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsNotificationManagementRepo.Dropdownvalues(template)));
        dropdownValuesForTemplate.click();

        Log.info("Notification configuration completed successfully.");
    }

    // Method to validate masked account number and check message
    public String validateMaskedAccountNumber(String accountNumber) {
    	Log.info("Starting validation for masked account number: " + accountNumber);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for masked account input field to be visible...");
        WebElement maskedAccountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.maskedAccountInput));
        Log.info("Masked account input field located. Entering account number...");
        maskedAccountInput.sendKeys(accountNumber);

        Log.info("Waiting for 'Validate' button to be visible...");
        WebElement validateButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.validateButton));
        Log.info("'Validate' button located. Clicking the button...");
        validateButton.click();

        Log.info("Waiting for warning message to be displayed...");
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.warningMessage));
        Log.info("Warning message displayed: " + warningMessage.getText());

        Log.info("Masked account number validation completed successfully.");
        return warningMessage.getText();
    }
    
    // Methods to interact with the page
    public void enterMaskedAccountNumber(String accountNumber) {
    	Log.info("Starting process to enter masked account number: " + accountNumber);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for 'Alerts and Notifications' main menu to be visible...");
        WebElement AlertsandNotificationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu));
        Log.info("'Alerts and Notifications' main menu located. Clicking...");
        AlertsandNotificationsmainmenu.click();

        Log.info("Waiting for 'Mask Account Window' link to be visible...");
        WebElement maskAccountWindowLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.maskAccountWindowLink));
        Log.info("'Mask Account Window' link located. Clicking...");
        maskAccountWindowLink.click();

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for account number field to be visible...");
        WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountNumberField));
        Log.info("Account number field located. Clearing existing text...");
        accountNumberField.clear();

        Log.info("Entering masked account number...");
        accountNumberField.sendKeys(accountNumber);

        Log.info("Masked account number entered successfully.");
    }
    
    public boolean validateAccountNumberBVA() throws InterruptedException {
    	Log.info("Starting Boundary Value Analysis (BVA) validation for account number field...");

        // Define boundary values
        String[] testCases = {
            "123456789012345678901234",    // Max-1 (24 digits) - should be entered
            "1234567890123456789012345",   // Max (25 digits) - should be entered
            "12345678901234567890123456"   // Max+1 (26 digits) - should NOT be entered fully
        };

        boolean allPass = true; // Track overall test result

        for (String accountNumber : testCases) {
            Log.info("Testing with account number: " + accountNumber);

            // Enter account number
            enterAccountNumber(accountNumber);

            // Fetch the actual entered value using JavaScript
            Log.info("Fetching the actual entered value from the account number field...");
            WebElement accountNumberField = driver.findElement(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountNumberField);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String enteredValue = (String) js.executeScript("return arguments[0].value;", accountNumberField);

            // Validate the entered value
            if (accountNumber.length() > 25) {
                // Max+1 case: User should NOT be able to enter more than 25 digits
                if (enteredValue.length() > 25) {
                    Log.error("Test failed for Max+1 (26 digits) - More than 25 digits entered.");
                    allPass = false;
                } else {
                    Log.info("Test passed for Max+1 (26 digits) - Application restricted input.");
                }
            } else {
                // Max and Max-1 case: It should allow entering the full input
                if (!enteredValue.equals(accountNumber)) {
                    Log.error("Test failed for " + accountNumber.length() + " digits - Incorrect input stored.");
                    allPass = false;
                } else {
                    Log.info("Test passed for " + accountNumber.length() + " digits - Input accepted correctly.");
                }
            }
        }

        Log.info("BVA validation for account number field completed. Overall result: " + (allPass ? "PASS" : "FAIL"));
        return allPass;
    }
    
    public boolean validateAccountNumberECP() throws InterruptedException {
    	Log.info("Starting Equivalence Class Partitioning (ECP) validation for account number field...");

        // Define test cases: Numeric (Valid), Alphabetic (Invalid), Alphanumeric (Invalid)
        String[] testCases = {
            "12345678901234567890",   // Numeric (Valid)
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",  // Alphabetic (Invalid)
            "12345ABCDE67890FGHIJ"  // Alphanumeric (Invalid)
        };

        boolean allPass = true; // Track overall test result

        for (String accountNumber : testCases) {
            Log.info("Testing with account number: " + accountNumber);

            // Enter account number
            enterAccountNumber(accountNumber);

            // Fetch the actual entered value using JavaScript
            Log.info("Fetching the actual entered value from the account number field...");
            WebElement accountNumberField = driver.findElement(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountNumberField);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String enteredValue = (String) js.executeScript("return arguments[0].value;", accountNumberField);

            // Validation Logic
            if (accountNumber.matches("\\d+")) { 
                // Numeric Input: Should be fully entered
                if (!enteredValue.equals(accountNumber)) {
                    Log.error("Test failed for valid numeric input: " + accountNumber);
                    allPass = false;
                } else {
                    Log.info("Test passed for valid numeric input: " + accountNumber);
                }
            } else {
                // Alphabetic & Alphanumeric Inputs: Should be restricted
                if (!enteredValue.isEmpty()) {
                    Log.error("Test failed for invalid input: " + accountNumber + " (Entered: " + enteredValue + ")");
                    
                    // If alphabets are entered, return false and print the entered value
                    if (enteredValue.matches(".*[a-zA-Z!@#$%^&*(),.?\":{}|<> ].*")) {
                        Log.error("Error: Alphabets were entered when they should be restricted! Entered Value: " + enteredValue);
                        allPass = false;
                    }
                } else {
                    Log.info("Test passed for invalid input restriction: " + accountNumber);
                }
            }
        }

        Log.info("ECP validation for account number field completed. Overall result: " + (allPass ? "PASS" : "FAIL"));
        return allPass;
    }
    
    public void clickGetButtonforInvalidaccountNumber() {
    	Log.info("Waiting for the 'Get' button to be visible...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement getButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.getButton));

        Log.info("'Get' button is visible. Clicking the button...");
        getButton.click();
        
        Log.info("'Get' button clicked successfully.");
    }
    
 // Method to get error message text
    public String getErrorMessage() {
    	Log.info("Waiting for the error message to be visible...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMessage));

        Log.info("Error message is visible. Retrieving text...");
        String errorText = errorMessage.getText();

        Log.info("Error message retrieved: " + errorText);
        return errorText;
    }
    
    public void enterAccountNumber() throws InterruptedException { 
    	Log.info("Waiting for any loading spinner to disappear...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        Log.info("Spinner disappeared. Adding wait time for stability.");
        Thread.sleep(5000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for account number field to be visible...");
        WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.accountNumberField));

        Log.info("Account number field is visible. Clearing existing input...");
        accountNumberField.clear();

        Log.info("Account number field cleared successfully.");
    }
    
    // Method to verify the visibility of the error message
    public boolean isErrorMessageDisplayed() {
    	Log.info("Waiting for error message to be displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMsg));

        boolean isDisplayed = errorMsg.isDisplayed();
        
        Log.info("Error message display status: " + isDisplayed);
        
        return isDisplayed;
    }
    
    // Method to get the text of the error message
    public String getErrorMessageText() {
    	Log.info("Waiting for error message to be displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMsg));

        String errorText = errorMsg.getText();
        
        Log.info("Captured error message: " + errorText);
        
        return errorText;
    }
    
    // Method to get error message text
    public String getErrorMegText() {
    	Log.info("Waiting for error message to be displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement errorMssg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsMaskAccountAndMaskingAuthorizationRepo.errorMssg));

        String errorText = errorMssg.getText();
        
        Log.info("Captured error message: " + errorText);
        
        return errorText;
    }
}

