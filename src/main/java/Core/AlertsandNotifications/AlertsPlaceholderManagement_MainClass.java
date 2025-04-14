package Core.AlertsandNotifications;


import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import io.netty.handler.timeout.TimeoutException;
import java.sql.Types;

public class AlertsPlaceholderManagement_MainClass {
	
	private WebDriver driver;
	
	public AlertsPlaceholderManagement_MainClass(WebDriver driver) {
		Log.info("Initializing AlertsPlaceholderManagement_MainClass...");

	    this.driver = driver;

	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);

	    Log.info("AlertsPlaceholderManagement_MainClass initialized successfully.");
    }
	
	 // Method to insert query
    public List<String> prepareAndExecuteInsertQuery(String query) throws IOException {
        Log.info("Preparing to execute stored procedure with query: " + query);

        List<Object> inputParams = Arrays.asList();
        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);

        Log.info("Calling ExecuteAnyOracleSQLStoredProcedure with input parameters: " + inputParams);
        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(query, inputParams, outputTypes);

        if (results == null || results.size() < 2) {
            Log.error("Stored procedure did not return expected results.");
            return Collections.emptyList(); // Return empty list if no results
        }

        String searchValue = (String) results.get(0);
        String executionMessage = (String) results.get(1);

        Log.info("Stored procedure executed successfully. SearchValue: " + searchValue + ", ExecutionMessage: " + executionMessage);

        return Arrays.asList(searchValue, executionMessage);
    }
	
	 public void clickOnAlertsAndNotifications() throws InterruptedException {
		 Log.info("Attempting to click on 'Alerts and Notifications' menu...");

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		    Log.info("Waiting for 'Alerts and Notifications' menu to be visible...");
		    WebElement alertsAndNotificationsMenu = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu)
		    );

		    Log.info("Scrolling to the bottom of the 'Alerts and Notifications' menu...");
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);

		    Thread.sleep(5000);

		    Log.info("Waiting for 'Alerts and Notifications' main menu to be visible...");
		    WebElement AlertsandNotificationsmainmenu = wait.until(
		        ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.AlertsandNotificationsmainmenu)
		    );

		    Log.info("Clicking on 'Alerts and Notifications' main menu...");
		    AlertsandNotificationsmainmenu.click();

		    Log.info("'Alerts and Notifications' menu clicked successfully.");
	    }
	 
	// Method to click on the Placeholder Management link
	    public void clickPlaceholderManagement() {
	    	Log.info("Attempting to click on 'Placeholder Management' submenu...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Placeholder Management' submenu to be visible...");
	        WebElement PlaceholderManagementsubmenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.PlaceholderManagementsubmenu)
	        );

	        Log.info("Clicking on 'Placeholder Management' submenu...");
	        PlaceholderManagementsubmenu.click();

	        Log.info("Waiting for spinner to disappear after clicking 'Placeholder Management'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("'Placeholder Management' submenu clicked successfully.");
	    }

	    // Method to verify if the user is navigated to the Placeholder Management page
	    public boolean isPlaceholderManagementPageDisplayed() {
	    	Log.info("Checking if the 'Placeholder Management' page is displayed...");
	        
	        String currentUrl = driver.getCurrentUrl();
	        Log.info("Current URL: " + currentUrl);

	        boolean isDisplayed = currentUrl.contains("ViewTemplatePlaceholders");
	        
	        if (isDisplayed) {
	            Log.info("'Placeholder Management' page is displayed successfully.");
	        } else {
	            Log.warn("'Placeholder Management' page is NOT displayed.");
	        }
	        
	        return isDisplayed;
	    }
	    
	    // Method to scroll to the bottom of the placeholder details table
	    public void scrollToBottomOfTable() throws InterruptedException {
	    	Log.info("Scrolling to the bottom of the table...");
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

	        Thread.sleep(5000); // Consider using WebDriverWait instead of Thread.sleep for better synchronization

	        Log.info("Scroll action completed.");
	    }
	    
	    // Method to verify the presence of pagination controls
	    public boolean arePaginationControlsVisible() {
	    	Log.info("Checking if pagination controls are visible...");

	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            WebElement paginationControls = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.paginationControlsLocator));

	            boolean isVisible = paginationControls.isDisplayed();
	            Log.info("Pagination controls visibility: " + isVisible);
	            return isVisible;

	        } catch (TimeoutException e) {
	            Log.error("Pagination controls were not visible within the timeout period.");
	            return false;
	        }
	    }
	    
	    // Method to click the "Next Page" button
	    public void clickNextPage() {
	    	Log.info("Attempting to click the 'Next Page' button...");

	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            WebElement nextPageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.nextPageButton));
	            
	            Log.info("'Next Page' button is visible. Clicking now...");
	            nextPageButton.click();

	            // Wait for the spinner to disappear after clicking
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	            Log.info("'Next Page' button clicked successfully. Waiting for page load to complete.");
	            
	        } catch (TimeoutException e) {
	            Log.error("TimeoutException: 'Next Page' button was not visible within the expected time.");
	        } catch (Exception e) {
	            Log.error("Exception occurred while clicking the 'Next Page' button: " + e.getMessage());
	        }
	    }

	    // Method to check if records are displayed
	    public boolean areRecordsDisplayed() {
	    	Log.info("Checking if records are displayed in the table...");
	        
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            WebElement recordTable = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.recordTable));
	            
	            Log.info("Record table is visible.");
	            return recordTable.isDisplayed();
	            
	        } catch (TimeoutException e) {
	            Log.warn("TimeoutException: Record table was not visible within the expected time.");
	        } catch (Exception e) {
	            Log.error("Exception occurred while checking record table visibility: " + e.getMessage());
	        }
	        
	        return false; // Return false if records are not displayed
	    }
	    
	 // Method to click the "Previous Page" button
	    public void clickPreviousPage() {
	    	Log.info("Attempting to click the 'Previous Page' button...");
	        
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            WebElement previousPageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.previousPageButton));
	            
	            Log.info("'Previous Page' button is visible. Clicking now...");
	            previousPageButton.click();
	            
	            Log.info("'Previous Page' button clicked successfully.");
	        } catch (TimeoutException e) {
	            Log.warn("TimeoutException: 'Previous Page' button was not visible within the expected time.");
	        } catch (Exception e) {
	            Log.error("Exception occurred while clicking 'Previous Page' button: " + e.getMessage());
	        }
	    }
	    
	 // Method to get the content of the record table
	    public String getTableContent() {
	    	Log.info("Fetching table content...");
	        
	        StringBuilder tableData = new StringBuilder();

	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	            List<WebElement> recordTable = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CoreAlertsPlaceholderManagementRepo.recordTablecontent));

	            if (recordTable.isEmpty()) {
	                Log.warn("No records found in the table.");
	                return "No records available.";
	            }

	            // Loop through each row (or cell) and extract text
	            for (WebElement row : recordTable) {
	                String rowText = row.getText().trim();
	                tableData.append(rowText).append("\n");
	                Log.debug("Row Data: " + rowText);
	            }

	        } catch (TimeoutException e) {
	            Log.error("TimeoutException: Table content did not load within the expected time.");
	            return "Table content not loaded.";
	        } catch (Exception e) {
	            Log.error("Exception occurred while fetching table content: " + e.getMessage());
	            return "Error fetching table content.";
	        }

	        Log.info("Table content fetched successfully.");
	        return tableData.toString().trim(); // Trim to remove extra newlines
	    }
	    
	    // Method to enter text in the search field
	    public void enterPlaceholderName(String placeholderName) {
	    	Log.info("Waiting for the search field to be visible...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.searchField));

	        Log.info("Search field is visible. Clearing existing text...");
	        searchField.clear();

	        Log.info("Entering placeholder name: " + placeholderName);
	        searchField.sendKeys(placeholderName);

	        Log.info("Placeholder name entered successfully.");
	    }
	    
	    // Method to click on the search button
	    public void clickSearchButton() {
	    	Log.info("Waiting for the search button to be visible...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.searchButton));

	        Log.info("Search button is visible. Clicking the button...");
	        searchButton.click();

	        Log.info("Waiting for the loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	        Log.info("Search action completed successfully.");
	    }

}