package Core.Configurations;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;
import com.BasePackage.ExecuteStoredProcedure;
import com.BasePackage.Login_Class;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.CoreCollectionOfficerConfiguration;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;
import java.sql.Types;

public class CollectionOfficerConfiguration_MainClass extends Base_Class {
	
	private WebDriver driver;
	public static String CORE_LOGIN_BANNER_DETAILS;
	private String dbValue;
	public static String orgName;
    public static String orgTypeName;
    String username ,UserIDInDashboard;
    
	public CollectionOfficerConfiguration_MainClass(WebDriver driver) {
		Log.info("Initializing CollectionOfficerConfig_MainClass...");
	    
	    this.driver = driver;
	    
	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);
	    
	    Log.info("CollectionOfficerConfig_MainClass initialized successfully.");
    }
	
	 public String executeSPInsertEmployee(String userid) {
		 Log.info("Starting execution of stored procedure: executeSPInsertEmployee");
		    
		    try {
		        Log.info("Received User ID: " + userid);
		        
		        //String result = ExecuteStoredProcedure.executeInsertEmployeeSP(userid);
		        
		        List<Object> inputParams = Arrays.asList(userid);
				List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
				  
				  List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_INSERT_EMPLOYEE", inputParams, outputTypes);
				  String result = (String) results.get(0);
				  System.out.println("Message: " + result);
		        
		        Log.info("Stored Procedure executed successfully. Output: " + result);
		        
		        return result; // Returning the result
		    } catch (IOException e) {
		        Log.ErrorWithException("Error occurred while executing stored procedure: " + e.getMessage(), e);
		        return null; // Or return an appropriate error message
		    }
	 }
	 
	 public void clickSecurityManagement() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		 UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
		 
		 Log.info("Starting the process to click on 'Security Management'...");

		    Log.info("Waiting for spinner to disappear...");
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		    Log.info("Waiting for the 'Security Management' main menu to be visible...");
		    WebElement SecurityManagementmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.SecurityManagementmainmenu));

		    Log.info("Clicking on 'Security Management' main menu...");
		    SecurityManagementmainmenu.click();

		    Log.info("'Security Management' clicked successfully.");
	    }

	    public void clickRoleManagement() {
	    	Log.info("Starting the process to click on 'Role Management' submenu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Role Management' submenu to be visible...");
	        WebElement RoleManagementsubmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.RoleManagementsubmenu));

	        Log.info("Clicking on 'Role Management' submenu...");
	        RoleManagementsubmenu.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Role Management'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Role Management' submenu clicked successfully.");
	    }

	    public void clickAddNewRole() {
	    	Log.info("Starting the process to click on 'Add New Role' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Add New Role' button to be visible...");
	        WebElement AddNewRolebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.AddNewRolebutton));

	        Log.info("Clicking on 'Add New Role' button...");
	        AddNewRolebutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Add New Role'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Add New Role' button clicked successfully.");
	    }

	    public boolean isCollectionOfficerConfigurationsAvailable() {
	    	Log.info("Checking if 'Collection Officer Configurations' checkbox is available...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Collection Officer Configurations' checkbox to be visible...");
	        WebElement CollectionOfficerConfigcheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.CollectionOfficerConfigcheckbox));

	        boolean isDisplayed = CollectionOfficerConfigcheckbox.isDisplayed();
	        
	        Log.info("'Collection Officer Configurations' checkbox is " + (isDisplayed ? "visible." : "not visible."));
	        
	        return isDisplayed;
	    }
	    
	    // Method to click on Configurations main menu
	    public void clickConfigurationsMenu() {
	    	Log.info("Starting the process to click on 'Configurations' menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Clicking on the close button if present...");
	        WebElement closebutton = driver.findElement(CoreCollectionOfficerConfiguration.closebutton);
	        closebutton.click();
	        
	        Log.info("Waiting for spinner to disappear after clicking close button...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for 'Configurations' main menu to be visible...");
	        WebElement Configurationsmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Configurationsmainmenu));

	        Log.info("Clicking on 'Configurations' main menu...");
	        Configurationsmainmenu.click();

	        Log.info("'Configurations' menu clicked successfully.");
	    }

	    // Method to click on Collection Officer Config submenu
	    public void clickCollectionOfficerConfigSubmenu() {
	    	Log.info("Starting the process to click on 'Collection Officer Config' submenu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Collection Officer Config' submenu to be visible...");
	        WebElement CollectionOfficerConfigsubmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.CollectionOfficerConfigsubmenu));

	        Log.info("Clicking on 'Collection Officer Config' submenu...");
	        CollectionOfficerConfigsubmenu.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Collection Officer Config'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Collection Officer Config' submenu clicked successfully.");
	    }
	    
	    // Method to check if CO field is mandatory
	    public boolean isCOFieldMandatory() {
	    	Log.info("Checking if 'CO Field' is mandatory...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'CO Field' to be visible...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.ZoneCOfield));

	        Log.info("Retrieving border color of 'CO Field'...");
	        String borderColor = driver.findElement(CoreCollectionOfficerConfiguration.ZoneCOfield)
	                                   .getCssValue("border-color");

	        boolean isMandatory = borderColor.equals("rgb(139, 0, 0)");
	        Log.info("'CO Field' mandatory status: " + (isMandatory ? "Yes" : "No"));

	        return isMandatory;
	    }
	    
	    // Method to verify presence of buttons
	    public boolean areButtonsPresent() {
	    	Log.info("Checking if all required buttons and fields are present...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Region' field to be visible...");
	        WebElement Regionfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Regionfield));

	        Log.info("Waiting for 'Branch' field to be visible...");
	        WebElement Branchfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Branchfield));

	        Log.info("Waiting for 'Search' button to be visible...");
	        WebElement Searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Searchbutton));

	        Log.info("Waiting for 'Reset' button to be visible...");
	        WebElement Resetbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Resetbutton));

	        Log.info("Waiting for 'Save Configuration' button to be visible...");
	        WebElement SaveConfigurationbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.SaveConfigurationbutton));

	        Log.info("Waiting for 'Download in Excel' button to be visible...");
	        WebElement DownloadinExcelbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.DownloadinExcelbutton));

	        boolean allPresent = Regionfield.isDisplayed() &&
	                             Branchfield.isDisplayed() &&
	                             Searchbutton.isDisplayed() &&
	                             Resetbutton.isDisplayed() &&
	                             SaveConfigurationbutton.isDisplayed() &&
	                             DownloadinExcelbutton.isDisplayed();

	        Log.info("All required buttons and fields are " + (allPresent ? "present." : "not present."));
	        
	        return allPresent;
	    }

	    // Method to click the search button
	    public void clickSearch() {
	    	Log.info("Starting the process to click on 'Search' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Region' field to be visible before proceeding...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Regionfield));

	        Log.info("Waiting for 'Search' button to be visible...");
	        WebElement Searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Searchbutton));

	        Log.info("Clicking on 'Search' button...");
	        Searchbutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Search'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Search' button clicked successfully.");
	    }

	    // Method to verify the grid displays users
	    public boolean isUserGridDisplayed(String searchText) {
	    	Log.info("Starting the search for '" + searchText + "' in the user grid...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        Log.info("Waiting for spinner to disappear after clicking 'Search'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        while (true) {
	            Log.info("Locating all rows inside the table...");
	            List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfiguration.GridRows);

	            Log.info("Iterating through each row...");
	            for (WebElement row : rows) {
	                List<WebElement> columns = row.findElements(CoreCollectionOfficerConfiguration.GridColumns);

	                for (WebElement column : columns) {
	                    String columnText = column.getText().trim();
	                    if (columnText.equalsIgnoreCase(searchText)) {
	                        Log.info("Match found: '" + columnText + "' matches the search text.");
	                        return true; // Value found
	                    }
	                }
	            }

	            Log.info("Search text not found on the current page. Checking for 'Next' button...");

	            List<WebElement> nextButton = driver.findElements(CoreCollectionOfficerConfiguration.GridNextbutton);

	            if (nextButton.isEmpty() || !nextButton.get(0).isEnabled()) {
	                Log.info("No more pages left to search. Search text not found.");
	                return false; // Value not found, and no more pages left
	            }

	            Log.info("Clicking on the 'Next' button to navigate to the next page...");
	            nextButton.get(0).click();

	            Log.info("Waiting for spinner to disappear...");
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	            try {
	                Thread.sleep(2000); // Temporary wait for stability, consider replacing with WebDriverWait
	            } catch (InterruptedException e) {
	                Log.error("Thread sleep interrupted: " + e.getMessage());
	            }
	        }
	    }
	    
	    // Method to click the Reset button
	    public void clickResetButton() throws InterruptedException {
	    	Log.info("Starting the process to click the 'Reset' button...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Scrolling to the top of the page before clicking 'Reset'...");
	        js.executeScript("window.scrollTo(0, 0);");

	        Log.info("Waiting for 5 seconds to stabilize the UI...");
	        Thread.sleep(5000); // Consider replacing with an explicit wait if possible

	        Log.info("Waiting for 'Reset' button to be visible...");
	        WebElement Resetbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Resetbutton));

	        Log.info("Clicking on 'Reset' button...");
	        Resetbutton.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Reset'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Reset' button clicked successfully.");
	    }

	    // Method to verify filters are reset to default state
	    public boolean areFiltersReset() {
	    	Log.info("Checking if filters have been reset...");

	        List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfiguration.GridRows);

	        Log.info("Number of rows found in the grid: " + rows.size());

	        // If no rows are found, assume the filters have been reset
	        if (rows.isEmpty()) {
	            Log.info("No rows found in the grid. Filters are reset.");
	            return true;
	        }

	        // Check if the first row contains the "No records to display." message
	        String firstRowText = rows.get(0).getText().trim();
	        Log.info("Text in the first row: '" + firstRowText + "'");

	        boolean isReset = firstRowText.equalsIgnoreCase("No records to display.");
	        Log.info("Filters reset status: " + (isReset ? "Yes" : "No"));

	        return isReset;
	    }

	    
	 // Method to fetch value from DB based on user ID
	    public String getDBValue(String userid) throws SQLException, ClassNotFoundException, IOException {
	    	Log.info("Fetching database value for user ID: " + userid);

	        String query = "SELECT is_bco FROM mst_employee WHERE employeeid='" + userid + "'";
	        Log.info("Executing SQL query: " + query);

	        dbValue = DBUtils.executeSQLStatement(query); // Store the value in a string

	        Log.info("Database Value Retrieved: " + dbValue);
	        return dbValue;
	    }

	    // Method to perform UI action
	    public void clickIsActiveCheckboxIfUserFound(String searchText) throws InterruptedException {
	    	Log.info("Starting search for user: " + searchText + " to click the 'Is Active' checkbox.");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        // Scroll to the Download in Excel button before proceeding
	        WebElement downloadInExcelButton = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.DownloadinExcelbutton));
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Log.info("Scrolling to the 'Download in Excel' button...");
	        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", downloadInExcelButton);
	        
	        Thread.sleep(5000); // Consider replacing this with an explicit wait if possible

	        while (true) { // Loop to iterate through paginated results
	            List<WebElement> rows = driver.findElements(CoreCollectionOfficerConfiguration.GridRows);
	            Log.info("Total rows found: " + rows.size());

	            boolean isFound = false;

	            for (int i = 0; i < rows.size(); i++) {
	                WebElement row = driver.findElements(CoreCollectionOfficerConfiguration.GridRows).get(i);
	                Log.info("Processing Row " + (i + 1));

	                List<WebElement> columns = row.findElements(CoreCollectionOfficerConfiguration.GridColumnsrelative);
	                Log.info("Total columns found in Row " + (i + 1) + ": " + columns.size());

	                if (columns.size() >= 2) { // Ensure it has at least 2 columns
	                    String columnText = columns.get(1).getText().trim();
	                    Log.info("Checking Row " + (i + 1) + " (Column 2 Value: " + columnText + ")");

	                    if (columnText.equalsIgnoreCase(searchText)) {
	                        Log.info("Match found in Row " + (i + 1) + ", clicking 'Is Active' checkbox...");

	                        columns.get(5).click();
	                        Log.info("Successfully clicked 'Is Active' checkbox in Row " + (i + 1) + " (Column 6).");

	                        isFound = true;
	                        break; // Exit after clicking the correct row
	                    }
	                }
	            }

	            if (isFound) {
	                Log.info("User found and checkbox clicked. Exiting method.");
	                return; // Stop execution if found
	            }

	            // Locate the 'Next' button
	            List<WebElement> nextButton = driver.findElements(CoreCollectionOfficerConfiguration.GridNextbutton);

	            // Check if the 'Next' button is available for pagination
	            if (nextButton.isEmpty() || !nextButton.get(0).isEnabled()) {
	                Log.warn("Search text '" + searchText + "' not found in any page. Exiting method.");
	                return;
	            }

	            // Click the 'Next' button to continue searching in the next page
	            Log.info("Moving to the next page...");
	            nextButton.get(0).click();

	            // Ensure the old table is replaced before continuing
	            wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.GridRows));
	        }
	    }
	    
	    // Method to click Save Configurations button
	    public void clickSaveConfigurationsButton() {
	    	Log.info("Initiating 'Save Configurations' process...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Save Configuration' button to be visible...");
	        WebElement SaveConfigurationbutton = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.SaveConfigurationbutton)
	        );

	        Log.info("Clicking 'Save Configuration' button...");
	        SaveConfigurationbutton.click();

	        Log.info("Waiting for confirmation popup...");
	        WebElement popup_yes_button = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.popup_yes_button)
	        );

	        Log.info("Clicking 'Yes' on the confirmation popup...");
	        popup_yes_button.click();

	        Log.info("Waiting for success message to appear...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.successmsg));
	        
	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Save Configurations' process completed successfully.");
	    }

	    // Method to verify if DB value has changed after UI update
	    public boolean verifyDBUpdate(String userid) throws SQLException, ClassNotFoundException, IOException {
	        
	    	Log.info("Verifying database update for User ID: " + userid);

	        // Step 1: Get value before UI action
	        String beforeValue = dbValue;
	        Log.info("Before UI Change: " + beforeValue);

	        // Step 2: Get value after UI action
	        String afterValue = getDBValue(userid);
	        Log.info("After UI Change: " + afterValue);

	        // Step 3: Compare values and return result
	        boolean isUpdated = beforeValue != null && afterValue != null && !beforeValue.equals(afterValue);
	        
	        if (isUpdated) {
	            Log.info("Database value updated successfully.");
	        } else {
	            Log.warn("Database value did not change.");
	        }

	        return isUpdated;
	    }
	    
	 // Method to click on My Desk main menu
	    public void clickMyDeskMainMenu() {
	    	Log.info("Initiating click action on 'My Desk' main menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'My Desk' main menu to be visible...");
	        WebElement myDeskMainMenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.myDeskMainMenu)
	        );

	        Log.info("Clicking on 'My Desk' main menu...");
	        myDeskMainMenu.click();

	        Log.info("'My Desk' main menu clicked successfully.");
	    }

	    // Method to click on Dashboard sub menu
	    public void clickDashboardSubMenu() {
	    	Log.info("Initiating click action on 'Dashboard' sub-menu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Dashboard' sub-menu to be visible...");
	        WebElement dashboardSubMenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.dashboardSubMenu)
	        );

	        Log.info("Clicking on 'Dashboard' sub-menu...");
	        dashboardSubMenu.click();

	        Log.info("Waiting for the loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Dashboard' sub-menu clicked successfully.");
	    }

	    // Method to click on any tile of Unassigned accounts
	    public void clickUnassignedAccountsTile() throws InterruptedException {
	    	Log.info("Initiating click action on 'Unassigned Accounts' tile...");

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        Log.info("Scrolling down to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        Thread.sleep(5000); // Consider replacing with explicit wait for better stability

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Unassigned Accounts' tile to be visible...");
	        WebElement unassignedAccountsTile = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.unassignedAccountsTile)
	        );

	        Log.info("Clicking on 'Unassigned Accounts' tile...");
	        unassignedAccountsTile.click();

	        Log.info("Waiting for loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        try {
	            Log.info("Waiting for 'ZoneCO' field to become visible...");
	            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.ZoneCO));
	            Log.info("'ZoneCO' field is now visible. Page loaded successfully.");
	        } catch (TimeoutException e) {
	            Log.ErrorWithException("ZoneCO field not found. Page did not load completely.", e);
	            throw new RuntimeException("ZoneCO field not found. The page is not fully loaded. Please try again.", e);
	        }

	        Log.info("Final wait for spinner to disappear before proceeding...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Unassigned Accounts' tile clicked successfully, and the page loaded.");
	    }

	    // Method to select BCO value from 'Allocate To' dropdown
	    public void selectBCOFromAllocateTo() throws InterruptedException {
	    	Log.info("Initiating selection of BCO from 'Allocate To' dropdown...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Scrolling down to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        Thread.sleep(5000); // Consider replacing with explicit wait for better stability

	        try {
	            Log.info("Waiting for 'Allocate To' dropdown to become visible...");
	            WebElement allocateToDropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.allocateToDropdown)
	            );
	            
	            Log.info("Clicking on 'Allocate To' dropdown...");
	            allocateToDropdown.click();

	            Log.info("Waiting for dropdown value (BCO) to become visible...");
	            WebElement allocateToDropdownValue = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.allocateToDropdownvalue)
	            );

	            Log.info("Selecting 'BCO' from the dropdown...");
	            allocateToDropdownValue.click();
	            
	            Log.info("BCO selection successful from 'Allocate To' dropdown.");
	        } catch (TimeoutException e) {
	            Log.ErrorWithException("Failed to locate 'Allocate To' dropdown or its value within the timeout.", e);
	            throw new RuntimeException("Dropdown element not found. Please check if the page is fully loaded.", e);
	        }
	    }

	    // Method to check if users are listed under BCO dropdown
	    public boolean areUsersListedUnderBCO() throws InterruptedException {
	    	Log.info("Verifying if users are listed under BCO...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	        	Log.info("Retrieving the logged-in username...");
	            WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.username));
	            username = usernameElement.getText().trim();
	            // Fetch the username
	            
	            Log.info("Fetched Username: " + username);

	            // Scroll to the dropdown
	            Log.info("Scrolling to the bottom of the page...");
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(5000); // Consider using explicit waits instead of Thread.sleep

	            // Locate and click the dropdown
	            Log.info("Waiting for 'Select BCO' dropdown to be visible...");
	            WebElement selectBCODropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.selectBCODropdown)
	            );
	            Log.info("Clicking on 'Select BCO' dropdown...");
	            selectBCODropdown.click();

	            // Locate and select the user from the dropdown
	            Log.info("Waiting for user (" + username + ") to appear in the dropdown...");
	            WebElement selectBCODropdownValue = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Branch_BCO_value(username))
	            );

	            Log.info("Selecting user (" + username + ") from the dropdown...");
	            selectBCODropdownValue.click();

	            // Verify if the correct username is displayed after selection
	            boolean isUserListed = selectBCODropdown.getText().trim().equals(username);
	            Log.info("User " + (isUserListed ? "successfully" : "not") + " listed under BCO.");

	            return isUserListed;

	        } catch (TimeoutException e) {
	            Log.ErrorWithException("Timeout while waiting for an element. User not listed under BCO.", e);
	        } catch (NoSuchElementException e) {
	            Log.ErrorWithException("Element not found. Possibly an incorrect locator or missing data.", e);
	        } catch (Exception e) {
	            Log.ErrorWithException("Unexpected error occurred while checking users under BCO.", e);
	        }

	        return false; // Return false in case of any failure
	    }
	    
	 // Click on Branch Attention Required tile
	    public void clickBranchAttentionTile() {
	    	Log.info("Attempting to click on 'Branch Attention Required Accounts' tile...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        try {
	            // Wait for the tile to be clickable
	            WebElement branchAttentionTile = wait.until(
	                ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfiguration.BranchAttentionRequiredAccounts));

	            Log.info("Branch Attention tile is clickable. Clicking now...");
	            branchAttentionTile.click();
	            Log.info("Successfully clicked on 'Branch Attention Required Accounts' tile.");

	        } catch (TimeoutException e) {
	            Log.ErrorWithException("Timeout: 'Branch Attention Required Accounts' tile did not become clickable within 180 seconds.", e);
	            throw new RuntimeException("Branch Attention tile not clickable.", e);
	        } catch (NoSuchElementException e) {
	            Log.ErrorWithException("Error: 'Branch Attention Required Accounts' tile not found on the page.", e);
	            throw new RuntimeException("Branch Attention tile not found.", e);
	        } catch (Exception e) {
	            Log.ErrorWithException("Unexpected error while clicking on 'Branch Attention Required Accounts' tile.", e);
	            throw new RuntimeException("Unexpected error while interacting with Branch Attention tile.", e);
	        }
	    }

	    // Click on Assign/Reassign dropdown
	    public void clickAssignReassignDropdown() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        Log.info("Waiting for search button to be present...");
	        wait.until(ExpectedConditions.presenceOfElementLocated(CoreCollectionOfficerConfiguration.searchbutton));

	        Log.info("Waiting for search button to become disabled...");
	        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfiguration.searchbutton)));

	        WebElement searchbutton = driver.findElement(CoreCollectionOfficerConfiguration.searchbutton);
	        if (!searchbutton.isEnabled()) {
	            Log.info("Search button is correctly disabled.");
	        } else {
	            Log.error("Search button is not disabled. The page might not be fully loaded.");
	            throw new RuntimeException("Search button is not disabled. Try again.");
	        }

	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Scrolling to the bottom of the page...");
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        Thread.sleep(5000);

	        Log.info("Waiting for spinner to disappear again...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for Assign/Reassign dropdown to be clickable...");
	        wait.until(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfiguration.AssignReassignTodropdown));

	        Log.info("Clicking Assign/Reassign dropdown...");
	        driver.findElement(CoreCollectionOfficerConfiguration.AssignReassignTodropdown).click();
	        Log.info("Assign/Reassign dropdown clicked successfully.");

	    }

	    // Get the text of listed users in the dropdown
	    public boolean getListedUsers() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    	Log.info("Retrieving the logged-in username...");
            WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.username));
            username = usernameElement.getText().trim();
	 
	        try {
	            Log.info("Fetching Username: " + username);

	            Log.info("Waiting for 'Assign/Reassign To' dropdown value to be visible for user: " + username);
	            WebElement Branch_BCO_AssignReassignTo_value = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.AssignReassignTo_value(username))
	            );

	            Log.info("Clicking 'Assign/Reassign To' dropdown value...");
	            Branch_BCO_AssignReassignTo_value.click();
	            Log.info("Clicked successfully.");

	            Log.info("Waiting for 'Assign/Reassign To' dropdown to be visible...");
	            WebElement AssignReassignTodropdown = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.AssignReassignTodropdown)
	            );

	            boolean isUserListed = AssignReassignTodropdown.getText().trim().equals(username);
	            Log.info("Verification result: " + isUserListed);

	            return isUserListed;
	        } catch (TimeoutException | NoSuchElementException e) {
	            Log.ErrorWithException("Failed to find the expected element. Returning false.", e);
	            return false;
	        }
        }
	    
	    public void clickConfigurationMenu() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the spinner to disappear before clicking 'Configuration' menu...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	        Log.info("Waiting for 'Configuration' menu to be visible...");
	        WebElement Configurationsmainmenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Configurationsmainmenu)
	        );

	        Log.info("Clicking on 'Configuration' menu...");
	        Configurationsmainmenu.click();
	        Log.info("'Configuration' menu clicked successfully.");
	    }
	    
	    public boolean getListedUser() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the dropdown options to become visible...");
	        
	        List<WebElement> options = new ArrayList<>();
	        try {
	            options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreCollectionOfficerConfiguration.AssignReassignTo_Present_values));
	        } catch (TimeoutException e) {
	            Log.warn("No elements found in the dropdown within the timeout.");
	            return false;
	        }

	        Log.info("Dropdown options retrieved. Checking for username: " + username);

	        // Check if the username is present in the dropdown options
	        for (WebElement option : options) {
	            Log.debug("Checking option: " + option.getText().trim());
	            if (option.getText().trim().equalsIgnoreCase(username)) {
	                Log.info("Username found: " + username);
	                return true; // Username exists in the dropdown
	            }
	        }

	        Log.warn("Username not found: " + username);
	        return false; // Username not found
	    }
	    // Method to click on Download in Excel button
	    public void clickDownloadExcelButton() throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        
	        Log.info("Waiting for spinner to disappear before proceeding.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        
	        Log.info("Scrolling to the bottom of the page.");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        
	        Thread.sleep(5000); // Consider replacing this with an explicit wait for better stability
	        
	        Log.info("Ensuring the spinner is not visible before clicking the download button.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Waiting for the Download Excel button to be clickable.");
	        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(CoreCollectionOfficerConfiguration.DownloadinExcelbutton));
	        
	        Log.info("Clicking the Download Excel button.");
	        downloadButton.click();

	        Log.info("Waiting for the success message to appear.");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.DownloadinExcelsuccessmsg));

	        Log.info("Waiting for the success message to disappear.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreCollectionOfficerConfiguration.DownloadinExcelsuccessmsg));

	        Log.info("Ensuring spinner is no longer visible after download action.");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Excel download process completed successfully.");
	    }
	    
	    public void clickSaveConfigurationButton() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the Save Configuration button to be visible.");
	        WebElement saveConfigurationButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.SaveConfigurationbutton));

	        Log.info("Clicking the Save Configuration button.");
	        saveConfigurationButton.click();

	        Log.info("Save Configuration button clicked successfully.");
	    }
	    
	 // Method to retrieve error message text
	    public String getErrorMessage() {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    	    Log.info("Waiting for the Save Configuration warning message to be visible.");
	    	    WebElement saveConfigurationWarningMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.Saveconfigurationwarningmsg));

	    	    String errorMessage = saveConfigurationWarningMsg.getText();
	    	    Log.info("Captured error message: " + errorMessage);

	    	    return errorMessage;
	    }
	    
	    public void createHOUser() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
	    	Log.info("Starting HO User creation process...");

	        String loginUserId;
	        String loginPassword;

	        String fileName = "CoreHOUserCredentials_CoreUserManagement_HO_User_Creation.properties";
	        Log.info("Reading user credentials from properties file: " + fileName);

	        Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
	        loginUserId = properties.getProperty("HO_User_ID");

	        Log.info("Preparing input parameters for stored procedure...");
	        List<Object> inputParams = Arrays.asList(UserIDInDashboard,loginUserId, "John Doe", "john.doe@example.com", 9876543210L);
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

	        Log.info("Executing stored procedure 'HOUserIDGenerator'...");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ZoneUserIDGeneratorUsingAnotherUserID", inputParams, outputTypes);

	        if (results == null || results.size() < 2) {
	            Log.error("Stored procedure did not return expected results.");
	            return;
	        }

	        loginUserId = (String) results.get(0); 
	        loginPassword = (String) results.get(1);
	        String SP_ExecutionMessage = (String) results.get(2);

	        Log.info("Stored procedure execution message: " + SP_ExecutionMessage);

	        Map<String, String> updates = new HashMap<>();
	        updates.put("HO_User_ID", loginUserId);
	        updates.put("HO_User_Password", loginPassword);

	        if (properties.isEmpty()) {
	            Log.info("Properties file is empty. Updating properties file...");
	            PropertiesFileUtil.updateProperties(fileName, updates);
	            Log.info("Properties updated successfully.");
	        } else {
	            Log.info("Properties file already contains values. Skipping update.");
	            loginUserId = properties.getProperty("HO_User_ID");
	            loginPassword = properties.getProperty("HO_User_Password");
	        }

	        Log.info("User ID: " + loginUserId);
	        Log.info("Password: " + loginPassword);

	        Log.info("Initializing WebDriver...");
	        String Browser = configloader().getProperty("Browser");
	        String CoreAppUrl = configloader().getProperty("CoreApplicationUrl");

	        switch (Browser.toUpperCase()) {
	            case "CHROME":
	                Log.info("Setting up Chrome WebDriver...");
	                ChromeOptions options = new ChromeOptions();
	                Map<String, Object> prefs = new HashMap<>();
	                String userHome = System.getProperty("user.home");
	                String downloadDirectory = userHome + File.separator + "Downloads";

	                prefs.put("download.default_directory", downloadDirectory);
	                prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
	                prefs.put("profile.default_content_setting_values.mixed_script", 1);
	                prefs.put("profile.default_content_settings.popups", 0);
	                prefs.put("download.prompt_for_download", false);
	                options.setExperimentalOption("prefs", prefs);

	                options.addArguments("--allow-running-insecure-content");
	                options.addArguments("--ignore-certificate-errors");
	                options.addArguments("--disable-extensions");
	                options.addArguments("--start-maximized");
	                options.addArguments("--disable-popup-blocking");

	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver(options);
	                break;
	            case "FIREFOX":
	                Log.info("Setting up Firefox WebDriver...");
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            default:
	                Log.error("The Driver is not defined for browser: " + Browser);
	                throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
	        }
	        Log.info("Driver initialized successfully for " + Browser + " browser");

	        Base_Class.driver = (RemoteWebDriver) driver;
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        Log.info("Navigating to application URL: " + CoreAppUrl);
	        driver.get(CoreAppUrl);
	        Common.setDriver(driver);
	        String LoginBannerQuery = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=1 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
            CORE_LOGIN_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(LoginBannerQuery);
            //System.out.println("BANNER_DETAILS: " + CORE_LOGIN_BANNER_DETAILS);
            
            Common.fluentWait("Core login Banner", LoginPageRepo.CollectionAgencyLoginBannerDetails(CORE_LOGIN_BANNER_DETAILS));
	        Thread.sleep(9000);

	        Log.info("Performing login...");
	        Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
	        Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
	        Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

	        Log.info("Entering credentials...");
	        driver.findElement(LoginPageRepo.UserNameField).sendKeys(loginUserId);
	        Log.info("Entered " + loginUserId + " in user name field");
	        driver.findElement(LoginPageRepo.PasswordField).sendKeys(loginPassword);
	        Log.info("Entered " + loginPassword + " in password field");
	        driver.findElement(LoginPageRepo.LoginButton).click();
	        Log.info("Clicked on login button");

	        Log.info("Checking for 'Already Login' popup...");
	        try {
	            WebElement clickableElement = Common.waitForElementToBeClickable(
	                driver,
	                LoginPageRepo.AlreadyLoginPopupYesButton,
	                Duration.ofSeconds(20)
	            );

	            if (clickableElement != null) {
	                Log.info("Handling 'Already Login' popup...");
	                clickableElement.click();
	                Common.waitForSpinnerToDisappear2(driver, "Loading Spinner", LoginPageRepo.Spinner);

	                Log.info("Re-entering credentials...");
	                Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
	                Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
	                Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

	                driver.findElement(LoginPageRepo.UserNameField).sendKeys(loginUserId);
	                Log.info("Re-entered " + loginUserId + " in user name field");
	                driver.findElement(LoginPageRepo.PasswordField).sendKeys(loginPassword);
	                Log.info("Re-entered " + loginPassword + " in password field");
	                driver.findElement(LoginPageRepo.LoginButton).click();
	                Log.info("Clicked on login button after popup");
	            }
	        } catch (Exception e) {
	            Log.info("Already login popup did not appear.");
	        }

	        Log.info("Handling possible login errors...");
	        Login_Class.SomeErrorOccuredHandling(loginUserId,loginPassword);

	        Log.info("Clicking on dashboard icon...");
	        driver.findElement(CoreCollectionOfficerConfiguration.dashboardicon).click();

	        Log.info("Fetching UserID from Dashboard...");
	        Common.fluentWait("AccountCategoryLabelInDashboard", LoginPageRepo.AccountCategoryLabelInDashboard);
	        UserIDInDashboard = driver.findElement(LoginPageRepo.UserIDInDashboard).getText();
	        Log.info("UserID in Dashboard: " + UserIDInDashboard);

	        Log.info("Fetching user organization details from DB...");
	        Login_Class.GetUserORGDetailsFromDB(UserIDInDashboard);

	        Log.info("HO User creation process completed successfully.");
	    } 
	    
	    public boolean areUsersListedUnderBCOforotherbrnach(String username) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            Log.info("Starting verification of user '" + username + "' in BCO dropdown.");

	            // Scroll to the dropdown
	            Log.info("Scrolling to the bottom of the page...");
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(5000); // Consider replacing this with an explicit wait.

	            // Locate and click the dropdown
	            Log.info("Waiting for BCO dropdown to be visible...");
	            WebElement selectBCODropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreCollectionOfficerConfiguration.selectBCODropdown));
	            
	            Log.info("Clicking on BCO dropdown...");
	            selectBCODropdown.click();

	            // Fetch dropdown options
	            List<WebElement> options = new ArrayList<>();
	            try {
	                Log.info("Waiting for BCO dropdown options to be visible...");
	                options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreCollectionOfficerConfiguration.BCOvalues));
	                Log.info("BCO dropdown options loaded successfully.");
	            } catch (TimeoutException e) {
	                Log.warn("Timeout while waiting for BCO dropdown options. Returning false.");
	                return false;
	            }

	            // Check if the username is present in the dropdown options
	            Log.info("Checking if user '" + username + "' exists in BCO dropdown options...");
	            for (WebElement option : options) {
	                Log.debug("Checking option: " + option.getText().trim());
	                if (option.getText().trim().equalsIgnoreCase(username)) {
	                    Log.info("User '" + username + "' found in BCO dropdown.");
	                    return true;
	                }
	            }

	            Log.info("User '" + username + "' not found in BCO dropdown.");
	            return false;

	        } catch (Exception e) {
	            Log.error("Exception occurred while verifying user in BCO dropdown: " + e.getMessage());
	            return false;
	        }
	    }
	    
	    public boolean areUsersListedUnderAssignReassignforotherbrnach(String username) throws InterruptedException {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            Log.info("Starting verification of user '" + username + "' in Assign/Reassign dropdown.");

	            // Scroll to the dropdown
	            Log.info("Scrolling to the bottom of the page...");
	            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	            Thread.sleep(5000); // Consider replacing this with an explicit wait.

	            // Fetch dropdown options
	            List<WebElement> options = new ArrayList<>();
	            try {
	                Log.info("Waiting for Assign/Reassign dropdown options to be visible...");
	                options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreCollectionOfficerConfiguration.AssignReassignTo_Present_values));
	                Log.info("Assign/Reassign dropdown options loaded successfully.");
	            } catch (TimeoutException e) {
	                Log.warn("Timeout while waiting for Assign/Reassign dropdown options. Returning false.");
	                return false;
	            }

	            // Check if the username is present in the dropdown options
	            Log.info("Checking if user '" + username + "' exists in Assign/Reassign dropdown options...");
	            for (WebElement option : options) {
	                Log.debug("Checking option: " + option.getText().trim());
	                if (option.getText().trim().equalsIgnoreCase(username)) {
	                    Log.info("User '" + username + "' found in Assign/Reassign dropdown.");
	                    return true;
	                }
	            }

	            Log.info("User '" + username + "' not found in Assign/Reassign dropdown.");
	            return false;

	        } catch (Exception e) {
	            Log.error("Exception occurred while verifying user in Assign/Reassign dropdown: " + e.getMessage());
	            return false;
	        }
	    }

}
