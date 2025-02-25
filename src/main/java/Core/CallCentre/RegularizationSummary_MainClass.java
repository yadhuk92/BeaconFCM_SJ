package Core.CallCentre;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.BasePackage.ExecuteStoredProcedure;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.CoreRegularizationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

import io.netty.handler.timeout.TimeoutException;

public class RegularizationSummary_MainClass {
	
private WebDriver driver;
	
	public RegularizationSummary_MainClass(WebDriver driver) {
		Log.info("Initializing CallCenterAccountFiltrationPage...");
        this.driver = driver;
        Log.info("WebDriver instance assigned.");
        Log.info("Initializing Web elements using PageFactory...");
        PageFactory.initElements(driver, this);
        Log.info("Web elements initialized using PageFactory.");
        Log.info("CallCenterAccountFiltrationPage initialization completed.");
    }
	
	public boolean isManualAllocationPageLoaded() throws InterruptedException {
    	Log.info("Starting the process to verify if the Auto Allocation page is loaded...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	Log.info("Locating the Call Center menu...");
    	WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);
    	Log.info("Call Center menu located successfully.");
    	Log.info("Clicking on the Call Center menu...");
    	callcentermenu.click();
    	Log.info("Call Center menu clicked successfully.");
    	Log.info("Locating the Auto Allocation submenu...");
    	WebElement manaualallocationsubmenu = driver.findElement(CoreManualAllocationRepo.manualallocationsubmenu);
    	Log.info("Manual Allocation submenu located successfully.");
    	Log.info("Clicking on the Manual Allocation submenu...");
    	manaualallocationsubmenu.click();
    	Log.info("Manual Allocation submenu clicked successfully.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared successfully.");
    	Log.info("Pausing for a brief moment...");
    	Log.info("Waiting for the 'Search' button to become clickable.");
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(CoreManualAllocationRepo.Searchbtn));
            Log.info("The 'Search' button is now clickable.");
        } catch (Exception e) { // Catch all exceptions including TimeoutException
            Log.error("Search Button not enabled, so the page is not loaded properly.");
            throw new RuntimeException("Search Button not enabled, so the page is not loaded properly."); // Ensure failure message is logged
        }
    	Log.info("The 'Search' button is now clickable.");
    	Log.info("Checking if the current URL ends with 'CallCentre/ManualAllocationConfiguration'...");
        boolean isPageLoaded = driver.getCurrentUrl().endsWith("CallCentre/ManualAllocationConfiguration");

        if (isPageLoaded) {
            Log.info("Manual Allocation page loaded successfully.");
        } else {
            Log.warn("Failed to load the Manual Allocation page.");
        }

        Log.info("Process to verify Manual Allocation page load completed.");
        return isPageLoaded;
    }
	
	// Method to select categories from asset category dropdown
    public void selectAssetCategory() throws InterruptedException {
    	// Log before initializing WebDriverWait
        Log.info("Initializing WebDriverWait with a timeout of 120 seconds.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // Log before locating the allocation name element
        Log.info("Attempting to locate the allocation name element.");
        WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);
        Log.info("Allocation name element located successfully.");

        // Log before scrolling to the allocation name element
        Log.info("Scrolling to the allocation name element using JavaScript.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", allocationName);
        Log.info("Scroll action completed.");

        // Log before adding a delay
        Log.info("Waiting for 5 seconds to ensure proper loading.");
        Thread.sleep(5000);
        Log.info("Wait of 5 seconds completed.");

        // Log before waiting for spinner to disappear
        Log.info("Waiting for the spinner to become invisible.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        // Log before locating the asset category element
        Log.info("Attempting to locate the asset category element.");
        WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);
        Log.info("Asset category element located successfully.");

        // Log before clicking on the asset category element
        Log.info("Clicking on the asset category element.");
        assetcategory.click();
        Log.info("Asset category element clicked successfully.");

        // Log before locating the select all element in the asset category
        Log.info("Attempting to locate the 'Select All' option in the asset category.");
        WebElement assetcategorysellectall = driver.findElement(CoreManualAllocationRepo.assetcategorysellectall);
        Log.info("'Select All' option located successfully.");

        // Log before clicking on the 'Select All' option
        Log.info("Clicking on the 'Select All' option in the asset category.");
        assetcategorysellectall.click();
        Log.info("'Select All' option clicked successfully.");

        // Log before waiting for spinner to disappear again
        Log.info("Waiting for the spinner to become invisible after selecting 'Select All'.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible after selecting 'Select All'.");
    }
    
 // Method to select SMA categories
    public void selectSmaCategories() throws InterruptedException {
    	// Log before locating the SMA category element
        Log.info("Attempting to locate the SMA category element.");
        WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);
        Log.info("SMA category element located successfully.");

        // Log before clicking the SMA category element
        Log.info("Clicking on the SMA category element.");
        smacategory.click();
        Log.info("SMA category element clicked successfully.");

        // Log before adding a delay
        Log.info("Waiting for 1 second to ensure proper loading.");
        Thread.sleep(1000);
        Log.info("Wait of 1 second completed.");

        // Log before locating the 'Select All' option in SMA categories
        Log.info("Attempting to locate the 'Select All' option in SMA categories.");
        WebElement smacategorysellectall = driver.findElement(CoreManualAllocationRepo.smacategorysellectall);
        Log.info("'Select All' option located successfully.");

        // Log before clicking the 'Select All' option
        Log.info("Clicking on the 'Select All' option in SMA categories.");
        smacategorysellectall.click();
        Log.info("'Select All' option in SMA categories clicked successfully.");
    }
    
 // Method to select NPA categories
    public void selectNpaCategories() {
    	// Log before locating the NPA category element
        Log.info("Attempting to locate the NPA category element.");
        WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);
        Log.info("NPA category element located successfully.");

        // Log before clicking the NPA category element
        Log.info("Clicking on the NPA category element.");
        npacategory.click();
        Log.info("NPA category element clicked successfully.");

        // Log before locating the 'Select All' option in NPA categories
        Log.info("Attempting to locate the 'Select All' option in NPA categories.");
        WebElement npacategorysellectall = driver.findElement(CoreManualAllocationRepo.npacategorysellectall);
        Log.info("'Select All' option in NPA categories located successfully.");

        // Log before clicking the 'Select All' option
        Log.info("Clicking on the 'Select All' option in NPA categories.");
        npacategorysellectall.click();
        Log.info("'Select All' option in NPA categories clicked successfully.");
    }
    
    public void clickOsBalanceField() {
    	// Log before locating the OS balance operators dropdown element
        Log.info("Attempting to locate the OS balance operators dropdown element.");
        WebElement osbalanceoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);
        Log.info("OS balance operators dropdown element located successfully.");

        // Log before clicking the OS balance operators dropdown element
        Log.info("Clicking on the OS balance operators dropdown element.");
        osbalanceoperatorsdropdown.click();
        Log.info("OS balance operators dropdown element clicked successfully.");
    }

    public void selectEqualFinancialOperator(String value) {
    	// Log before locating the dropdown value element
        Log.info("Attempting to locate the dropdown value element for the financial operator with value: " + value);
        WebElement OutstandingBalLimitOperatordropdownvalue = driver.findElement(CoreManualAllocationRepo.OutstandingBalLimitOperatordropdownvalue(value));
        Log.info("Dropdown value element for the financial operator with value: " + value + " located successfully.");

        // Log before clicking the dropdown value element
        Log.info("Clicking on the dropdown value element for the financial operator with value: " + value);
        OutstandingBalLimitOperatordropdownvalue.click();
        Log.info("Dropdown value element for the financial operator with value: " + value + " clicked successfully.");
    }

    public void enterOsBalance(String balance) {
    	// Log before locating the OS balance text field element
        Log.info("Attempting to locate the OS balance text field element.");
        WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);
        Log.info("OS balance text field element located successfully.");

        // Log before clearing the text field
        Log.info("Clearing any existing value in the OS balance text field.");
        osbalancetextfield.clear();
        Log.info("Existing value in the OS balance text field cleared successfully.");

        // Log before entering the balance value
        Log.info("Entering the balance value: " + balance + " into the OS balance text field.");
        osbalancetextfield.sendKeys(balance);
        Log.info("Balance value: " + balance + " entered successfully into the OS balance text field.");
    }
    public void selectCallCentreFromToDropdown(String value) {
    	// Log before locating and clicking the 'To' dropdown element
        Log.info("Attempting to locate the 'To' dropdown element.");
        WebElement To = driver.findElement(CoreManualAllocationRepo.To);
        Log.info("'To' dropdown element located successfully.");

        Log.info("Clicking on the 'To' dropdown element.");
        To.click();
        Log.info("'To' dropdown element clicked successfully.");

        // Log before locating and clicking the specific value in the 'To' dropdown
        Log.info("Attempting to locate the value: " + value + " in the 'To' dropdown.");
        WebElement tovalue = driver.findElement(CoreManualAllocationRepo.tovalue(value));
        Log.info("Value: " + value + " located successfully in the 'To' dropdown.");

        Log.info("Clicking on the value: " + value + " in the 'To' dropdown.");
        tovalue.click();
        Log.info("Value: " + value + " clicked successfully in the 'To' dropdown.");
    }
    
    public void clickSearchBtn() throws InterruptedException {
   	 // Log before creating WebDriverWait instance
       Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
       Log.info("WebDriverWait instance created successfully.");

       // Log before locating and clicking the Search button
       Log.info("Attempting to locate the Search button.");
       WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
       Log.info("Search button located successfully.");
       
       Log.info("Clicking on the Search button.");
       Searchbtn.click();
       Log.info("Search button clicked successfully.");

       // Log before waiting for the spinner to disappear
       Log.info("Waiting for the spinner to become invisible.");
       wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
       Log.info("Spinner is now invisible.");

       // Log before locating the Assigned List element
       Log.info("Attempting to locate the Assigned List element.");
       WebElement AssignedList = driver.findElement(CoreManualAllocationRepo.AssignedList);
       Log.info("Assigned List element located successfully.");

       // Log before scrolling to the Assigned List element
       Log.info("Scrolling to the Assigned List element.");
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", AssignedList);
       Log.info("Successfully scrolled to the Assigned List element.");

       // Log before adding a delay
       Log.info("Waiting for 5 seconds to ensure proper loading.");
       Thread.sleep(5000);
       Log.info("Wait of 5 seconds completed.");
   }
    
 // Method to select 'Call Centre' from 'Allocate To' dropdown
    public void selectCallCentreFromAllocateToDropdown(String value) {
    	Log.info("Starting the process to select a value from 'Allocate To' dropdown...");
        
        Log.info("Locating the 'Allocate To' dropdown element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement AllocateTo = driver.findElement(CoreManualAllocationRepo.AllocateTo);
        
        Log.info("Clicking on the 'Allocate To' dropdown...");
        AllocateTo.click();
        Log.info("'Allocate To' dropdown clicked successfully.");
        
        Log.info("Waiting for the value '" + value + "' to become visible in the dropdown...");
        WebElement tovalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.tovalue(value)));
        
        Log.info("Clicking on the value '" + value + "'...");
        tovalue.click();
        Log.info("Value '" + value + "' selected successfully from the 'Allocate To' dropdown.");
    }
    
    public void selectCallCentre(String callCentreName) {
    	Log.info("Starting the process to select a call centre: " + callCentreName + "...");
        
        Log.info("Waiting for the 'Select Call Centre' dropdown to become visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement SelectCallCentre = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.SelectCallCentre));
        Log.info("'Select Call Centre' dropdown is visible.");
        
        Log.info("Clicking on the 'Select Call Centre' dropdown...");
        SelectCallCentre.click();
        Log.info("'Select Call Centre' dropdown clicked successfully.");
        
        Log.info("Waiting for the call centre value '" + callCentreName + "' to become visible...");
        WebElement SelectCallCentrevalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.SelectCallCentrevalue(callCentreName)));
        Log.info("Call centre value '" + callCentreName + "' is visible.");
        
        Log.info("Clicking on the call centre value '" + callCentreName + "'...");
        SelectCallCentrevalue.click();
        Log.info("Call centre value '" + callCentreName + "' selected successfully.");
    }
    
    public void clickAssignButton() {
   	 Log.info("Starting the process to click the 'Assign' button...");
   	    
   	    Log.info("Waiting for the 'Assign' button to become clickable...");
   	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
   	    WebElement Assignbtn = wait.until(ExpectedConditions.elementToBeClickable(CoreManualAllocationRepo.Assignbtn));
   	    Log.info("'Assign' button is clickable.");
   	    
   	    Log.info("Clicking on the 'Assign' button...");
   	    Assignbtn.click();
   	    Log.info("'Assign' button clicked successfully.");
   }

   // Method to get the validation message text
   public String getValidationMessage() {
   	Log.info("Starting the process to fetch the validation message...");

       Log.info("Waiting for the validation message element to become visible...");
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
       WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.validationMessage));
       Log.info("Validation message element is visible.");

       String messageText = validationMessage.getText();
       Log.info("Fetched validation message: '" + messageText + "'");

       return messageText;
   }
   
   // Method to update assignment date
   public String  updateAssignmentDate() throws SQLException, ClassNotFoundException, IOException {
	   String UPDATE_ASSIGNMENT_DATE_QUERY = 
	"UPDATE mst_callcentre_accounts SET assignment_date = TRUNC(SYSDATE - 1) WHERE TRUNC(assignment_date) = TRUNC(SYSDATE)";
	// Log the start of the method
	    Log.info("Starting the process to update assignment date...");

	    // Log the query being executed
	    Log.info("Executing the query: " + UPDATE_ASSIGNMENT_DATE_QUERY);

	    try {
	        // Log before executing the update query
	        Log.info("Executing the update query...");

	        // Call the method to execute the query and capture the result
	        String result = DBUtils.executeUpdateQuery(UPDATE_ASSIGNMENT_DATE_QUERY);

	        // Log the successful execution of the update query
	        Log.info("Query executed successfully.");

	        // Return the result after execution
	        return result;
	    } catch (SQLException e) {
	        // Log SQL exceptions with error message
	        Log.error("SQL Exception occurred: " + e.getMessage());
	        e.printStackTrace();
	        return "SQL Exception: " + e.getMessage();
	    } catch (ClassNotFoundException e) {
	        // Log ClassNotFoundException with error message
	        Log.error("Database driver class not found: " + e.getMessage());
	        e.printStackTrace();
	        return "Database driver class not found: " + e.getMessage();
	    } catch (IOException e) {
	        // Log I/O exceptions with error message
	        Log.error("I/O error occurred: " + e.getMessage());
	        e.printStackTrace();
	        return "I/O error occurred: " + e.getMessage();
	    } catch (Exception e) {
	        // Log unexpected exceptions with error message
	        Log.error("Unexpected error occurred: " + e.getMessage());
	        e.printStackTrace();
	        return "Unexpected error: " + e.getMessage();
	    } finally {
	        // Log that the method has finished
	        Log.info("Finished updating assignment date.");
	    }
	}

    //Method to create an entry for yesterday's and today's dates
   public String createEntryForYesterdayandtoday() throws SQLException, IOException {
	// Log the start of the method execution
	    Log.info("Starting the process to create entry for yesterday and today...");

	    // Log the stored procedure name that will be executed
	    String storedProcName = "SP_INSERT_TRN_AC_MOVEMENT";
	    Log.info("Executing stored procedure: " + storedProcName);

	    try {
	        // Call the stored procedure and capture the result
	        String result = ExecuteStoredProcedure.executeStoredProc(storedProcName);

	        // Log the result from the stored procedure execution
	        Log.info("Stored procedure executed successfully. Result: " + result);

	        // Print the response
	        System.out.println("Stored Procedure Response: " + result);

	        // Return the result
	        return result;
	    } catch (IOException e) {
	        // Log any I/O exceptions
	        Log.error("I/O error occurred while executing stored procedure: " + e.getMessage());
	        e.printStackTrace();
	        return "I/O error occurred: " + e.getMessage();
	    } catch (Exception e) {
	        // Log any unexpected exceptions
	        Log.error("Unexpected error occurred while executing stored procedure: " + e.getMessage());
	        e.printStackTrace();
	        return "Unexpected error: " + e.getMessage();
	    } finally {
	        // Log the end of the method execution
	        Log.info("Finished executing the stored procedure for creating entry for yesterday and today.");
	    }
   }

   // Method to run package
   public String  runPackage() throws SQLException, IOException {
	// Log the start of the method execution
	    Log.info("Starting the process to run the package ALLOCATION_DASHBOARD_DATA_LOAD_PKG.SPPROCESSCALLCENETERREGULARIZATIONSUMMARY...");

	    // Define the stored procedure name
	    String storedProcName = "ALLOCATION_DASHBOARD_DATA_LOAD_PKG.SPPROCESSCALLCENETERREGULARIZATIONSUMMARY";
	    
	    // Log the stored procedure name that will be executed
	    Log.info("Executing stored procedure: " + storedProcName);

	    try {
	        // Call the stored procedure and capture the result
	        String result = ExecuteStoredProcedure.executeStoredProce(storedProcName);

	        // Log the result from the stored procedure execution
	        Log.info("Stored procedure executed successfully. Result: " + result);

	        // Print the response (optional)
	        System.out.println("Stored Procedure Response: " + result);

	        // Return the result
	        return result;

	    } catch (IOException e) {
	        // Log any I/O exceptions
	        Log.error("I/O error occurred while executing stored procedure: " + e.getMessage());
	        e.printStackTrace();
	        return "I/O error occurred: " + e.getMessage();
	    } catch (Exception e) {
	        // Log any unexpected exceptions
	        Log.error("Unexpected error occurred while executing stored procedure: " + e.getMessage());
	        e.printStackTrace();
	        return "Unexpected error: " + e.getMessage();
	    } finally {
	        // Log the end of the method execution
	        Log.info("Finished executing the stored procedure for ALLOCATION_DASHBOARD_DATA_LOAD_PKG.SPPROCESSCALLCENETERREGULARIZATIONSUMMARY.");
	    }
   }
   
// Method to check if 'Call Centre' is listed in the side menu
   public boolean isCallCentreListed() {
	   Log.info("Starting the process to check if Call Centre is listed...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Clicking on the Dashboard button...");
	    WebElement dashboardbutton = driver.findElement(CoreRegularizationSummaryRepo.dashboardbutton);
	    dashboardbutton.click();
	    Log.info("Dashboard button clicked successfully.");

	    Log.info("Waiting for the spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner is no longer visible.");

	    Log.info("Locating the Call Centre menu...");
	    WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);

	    boolean isDisplayed = callcentermenu.isDisplayed();
	    Log.info("Call Centre menu is displayed: " + isDisplayed);

	    return isDisplayed;
   }
   
// Method to verify the presence of Regularization Summary menu
   public boolean isRegularizationSummaryDisplayed() {
	   Log.info("Starting the process to check if Regularization Summary is displayed...");

	    Log.info("Locating and clicking the Call Centre menu...");
	    WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);
	    callcentermenu.click();
	    Log.info("Call Centre menu clicked successfully.");

	    Log.info("Locating the Regularization Summary element...");
	    WebElement regularizationSummary = driver.findElement(CoreRegularizationSummaryRepo.RegularizationSummary);

	    boolean isDisplayed = regularizationSummary.isDisplayed();
	    Log.info("Regularization Summary is displayed: " + isDisplayed);

	    return isDisplayed;
   }
   
   
    //Clicks on the Regularization Summary link in the submenu.
   public void clickRegularizationSummary() {
	   Log.info("Starting the process to click the Regularization Summary...");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Locating the Regularization Summary element...");
	    WebElement regularizationSummary = driver.findElement(CoreRegularizationSummaryRepo.RegularizationSummary);

	    Log.info("Clicking the Regularization Summary...");
	    regularizationSummary.click();
	    Log.info("Regularization Summary clicked successfully.");

	    Log.info("Waiting for the loading spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner disappeared. Page has loaded successfully.");
   }
   
// Method to check if the Call Centre Dropdown is displayed
   public boolean isCallCentreDropdownDisplayed() {
	   Log.info("Checking if the 'Select Call Centre' dropdown is displayed...");

	    try {
	        WebElement selectCallCentreDropdown = driver.findElement(CoreRegularizationSummaryRepo.SelectCallCentredropdown);
	        boolean isDisplayed = selectCallCentreDropdown.isDisplayed();
	        
	        if (isDisplayed) {
	            Log.info("'Select Call Centre' dropdown is visible.");
	        } else {
	            Log.warn("'Select Call Centre' dropdown is not visible.");
	        }
	        
	        return isDisplayed;
	    } catch (NoSuchElementException e) {
	        Log.error("'Select Call Centre' dropdown element not found! -->" + e);
	        return false;
	    }
   }

   // Method to check if the Search Button is displayed
   public boolean isSearchButtonDisplayed() {
	   Log.info("Checking if the 'Search' button is displayed...");

	    try {
	        WebElement searchButton = driver.findElement(CoreRegularizationSummaryRepo.searchbutton);
	        boolean isDisplayed = searchButton.isDisplayed();

	        if (isDisplayed) {
	            Log.info("'Search' button is visible.");
	        } else {
	            Log.warn("'Search' button is not visible.");
	        }

	        return isDisplayed;
	    } catch (NoSuchElementException e) {
	        Log.error("'Search' button element not found! -->"+ e);
	        return false;
	    }
   }
   
   // Method to click the Search button
   public void clickSearchButton() {
	   Log.info("Attempting to click the 'Search' button...");

	    try {
	        WebElement searchButton = driver.findElement(CoreRegularizationSummaryRepo.searchbutton);

	        if (searchButton.isDisplayed() && searchButton.isEnabled()) {
	            Log.info("'Search' button is visible and enabled. Clicking now...");
	            searchButton.click();
	            Log.info("'Search' button clicked successfully.");
	        } else {
	            Log.warn("'Search' button is either not visible or not enabled!");
	        }

	    } catch (NoSuchElementException e) {
	        Log.error("'Search' button element not found! -->"+ e);
	    } catch (Exception e) {
	        Log.error("An unexpected error occurred while clicking the 'Search' button. -->"+ e);
	    }
   }

   // Method to get the validation message text
   public String getValidationMessageText() {
	   Log.info("Waiting for the validation message to appear...");

	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	        WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreRegularizationSummaryRepo.validationMessage));

	        String messageText = validationMessage.getText();
	        Log.info("Validation message retrieved successfully: " + messageText);

	        return messageText;

	    } catch (TimeoutException e) {
	        Log.error("Timeout: Validation message did not appear within 180 seconds. --> "+ e);
	    } catch (NoSuchElementException e) {
	        Log.error("Validation message element not found! --> "+ e);
	    } catch (Exception e) {
	        Log.error("An unexpected error occurred while retrieving the validation message. --> "+ e);
	    }

	    return "";
   }
   
// Method to click on the Callcentre Name dropdown
   public void clickCallCentreDropdown() {
	   Log.info("Attempting to click the 'Select Call Centre' dropdown...");

	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180)); // Adjust timeout as needed
	        WebElement selectCallCentreDropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreRegularizationSummaryRepo.SelectCallCentredropdown));

	        selectCallCentreDropdown.click();
	        Log.info("'Select Call Centre' dropdown clicked successfully.");

	    } catch (TimeoutException e) {
	        Log.error("Timeout: 'Select Call Centre' dropdown was not clickable within the given time. --> "+ e);
	    } catch (NoSuchElementException e) {
	        Log.error("Error: 'Select Call Centre' dropdown element not found! --> "+ e);
	    } catch (Exception e) {
	        Log.error("An unexpected error occurred while clicking the 'Select Call Centre' dropdown. --> "+ e);
	    }
   }

   // Method to verify that the Callcentre Name dropdown is displayed
   public boolean isCallCentreDropdownvalueDisplayed(String callCentreName) {
	   try {
	        Log.info("Checking if Call Centre dropdown value is displayed for: " + callCentreName);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        Log.info("Waiting for the Call Centre value to be visible...");

	        WebElement CallCentrevalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.SelectCallCentrevalue(callCentreName)));

	        if (CallCentrevalue.isDisplayed()) {
	            Log.info("Call Centre value is visible. Clicking on it...");
	            CallCentrevalue.click();
	            Log.info("Successfully clicked on the Call Centre value.");
	            return true;
	        } else {
	            Log.warn("Call Centre value is present but not visible.");
	            return false;
	        }
	    } catch (Exception e) {
	        Log.error("Exception occurred while checking Call Centre dropdown value: " + e.getMessage());
	        return false;
	    }
   }
   
// Method to check if the summary report is displayed
   public boolean isSummaryDisplayed() {
	   try {
	        Log.info("Checking if the Regularization Summary Report is displayed...");
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        Log.info("Waiting for the Regularization Summary Report to become visible...");

	        WebElement Regularizationsummaryreport = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreRegularizationSummaryRepo.Regularizationsummaryreport));

	        if (Regularizationsummaryreport.isDisplayed()) {
	            Log.info("Regularization Summary Report is visible.");
	            return true;
	        } else {
	            Log.warn("Regularization Summary Report is present but not visible.");
	            return false;
	        }
	    } catch (Exception e) {
	        Log.error("Exception occurred while checking Regularization Summary Report visibility: " + e.getMessage());
	        return false;
	    }
   }
   
// Method to click on the download icon
   public void clickDownloadIcon() {
	   try {
	        Log.info("Initiating the process to click the Download icon...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

	        Log.info("Locating the Download button...");
	        WebElement downloadbtn = driver.findElement(CoreRegularizationSummaryRepo.downloadbtn);

	        Log.info("Clicking the Download button...");
	        downloadbtn.click();
	        Log.info("Download button clicked successfully.");

	        Log.info("Waiting for the download confirmation message to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreRegularizationSummaryRepo.downloadedMessage));
	        Log.info("Download confirmation message is no longer visible.");

	        Log.info("Waiting for the loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Loading spinner is no longer visible. Download process completed.");
	        
	    } catch (Exception e) {
	        Log.error("Exception occurred while clicking the Download icon: " + e.getMessage());
	    }
   	
   }
   
// Method to fetch total account received from grid
   public List<String> getGridTotalAccountsReceived() {
	    Log.info("Fetching the total accounts received from the grid...");
	    
	    List<String> texts = new ArrayList<>();

	    // Initial extraction of data from the first page
	    List<WebElement> elements = driver.findElements(CoreRegularizationSummaryRepo.TOTAL_AC_RECEIVEDcount);
	    Log.info("Found " + elements.size() + " elements for total accounts received on the first page.");

	    // Extract text from elements on the current page
	    for (WebElement element : elements) {
	        String text = element.getText();
	        texts.add(text);
	        Log.info("Extracted text: " + text);
	    }

	    // Use findElements() instead of findElement() to avoid NoSuchElementException
	    List<WebElement> nextButtonList = driver.findElements(CoreRegularizationSummaryRepo.nextbutton);

	    // Check if the "Next" button is present
	    if (!nextButtonList.isEmpty()) {
	        WebElement nextButton = nextButtonList.get(0); // Get the first (and only) element
	        
	        // Extract the total page count (assuming you have an element that shows total pages)
	        int totalPages = getTotalPages(); // Get the total number of pages
	        
	        Log.info("Total pages available: " + totalPages);

	        if (totalPages > 1) { // Proceed only if there is more than one page
	            // Wait until the "Next" button is visible and enabled
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as necessary
	            try {
	                // Check if the "Next" button is visible and enabled initially
	                if (wait.until(ExpectedConditions.and(
	                        ExpectedConditions.visibilityOf(nextButton), 
	                        ExpectedConditions.elementToBeClickable(nextButton))) != null) {
	                    Log.info("Next button is clickable. Proceeding with pagination...");
	                    
	                    int currentPage = 1; // Start from the first page

	                    // Keep clicking the "Next" button until all pages are processed
	                    while (currentPage < totalPages) {
	                        // Re-check if the "Next" button is clickable before clicking
	                        if (wait.until(ExpectedConditions.and(
	                                ExpectedConditions.visibilityOf(nextButton), 
	                                ExpectedConditions.elementToBeClickable(nextButton))) != null) {
	                            Log.info("Next button is clickable. Clicking next page...");

	                            // Click the "Next" button
	                            nextButton.click();

	                            // Wait for the page to load (adjust this as needed)
	                            try {
	                                Thread.sleep(2000); // Adjust as per the app's load time
	                            } catch (InterruptedException e) {
	                                e.printStackTrace();
	                            }

	                            // Extract data from the new page
	                            elements = driver.findElements(CoreRegularizationSummaryRepo.TOTAL_AC_RECEIVEDcount);
	                            Log.info("Found " + elements.size() + " elements for total accounts received on the next page.");
	                            
	                            // Extract text from elements on the current page
	                            for (WebElement element : elements) {
	                                String text = element.getText();
	                                texts.add(text);
	                                Log.info("Extracted text: " + text);
	                            }

	                            // Update the "Next" button WebElement in case it has changed
	                            nextButtonList = driver.findElements(CoreRegularizationSummaryRepo.nextbutton);
	                            if (!nextButtonList.isEmpty()) {
	                                nextButton = nextButtonList.get(0);  // Re-fetch the "Next" button for the new page
	                            }

	                            currentPage++; // Increment the page count
	                        } else {
	                            // If the "Next" button is not clickable, break the loop
	                            Log.info("Next button is not clickable. No more pages to load.");
	                            break; // Exit the loop
	                        }
	                    }
	                } else {
	                    Log.info("Next button is not clickable initially. No more pages to load.");
	                }
	            } catch (Exception e) {
	                Log.info("Next button is not clickable or an error occurred. No more pages to load.");
	            }
	        } else {
	            Log.info("Only one page available. No pagination needed.");
	        }
	    } else {
	        Log.info("Next button not found. Assuming no more pages. Returning the data collected so far.");
	    }

	    Log.info("Total accounts received retrieved successfully: " + texts);
	    return texts;
	}

	// Helper method to extract the total number of pages
	private int getTotalPages() {
	    int totalPages = 1; // Default to 1 if pagination is not found
	    
	    try {
	        // Locate the pagination controls, e.g., the total number of pages (could be the last page element)
	        List<WebElement> pageElements = driver.findElements(CoreRegularizationSummaryRepo.pagescount);
	        
	        // Check if the element with the total pages exists
	        if (!pageElements.isEmpty()) {
	            WebElement totalPageElement = pageElements.get(pageElements.size() - 1); // Assuming the last element is the page count
	            String totalPageText = totalPageElement.getText();
	            
	            // Convert the text to an integer (assuming it's a number)
	            totalPages = Integer.parseInt(totalPageText);
	        }
	    } catch (Exception e) {
	        Log.info("Error while extracting total pages: " + e.getMessage());
	    }
	    
	    return totalPages;
	}
   
// Method to click the download icon
   public void clickDownloadIconfromAccountCategory() {
	   try {
	        Log.info("Initiating the process to click the Download icon from Account Category...");

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

	        Log.info("Locating the Account Category Download button...");
	        WebElement downloadbtn2 = driver.findElement(CoreRegularizationSummaryRepo.downloadbtn2);

	        Log.info("Clicking the Account Category Download button...");
	        downloadbtn2.click();
	        Log.info("Download button clicked successfully.");

	        Log.info("Waiting for the download confirmation message to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreRegularizationSummaryRepo.downloadedMessage));
	        Log.info("Download confirmation message is no longer visible.");

	        Log.info("Waiting for the loading spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Loading spinner is no longer visible. Download process completed.");
	        
	    } catch (Exception e) {
	        Log.error("Exception occurred while clicking the Account Category Download icon: " + e.getMessage());
	    }
   }
   
   // Method to verify downloaded file data with grid
   public boolean verifyFileDataWithGridData(int downloadedData, String gridData) {
	   try {
	        Log.info("Verifying file data with grid data...");
	        Log.info("Downloaded Data: " + downloadedData);
	        Log.info("Grid Data (Raw): " + gridData);

	        int gridValue = Integer.parseInt(gridData.trim()); // Convert gridData to int
	        Log.info("Grid Data (Parsed to Integer): " + gridValue);

	        boolean isMatching = downloadedData == gridValue;
	        Log.info("Comparison Result: " + isMatching);

	        return isMatching;
	    } catch (NumberFormatException e) {
	        Log.error("Error parsing grid data to integer: " + gridData + " | Exception: " + e.getMessage());
	        return false;
	    }
   }
   
// Method to navigate to main menu
   public void navigateToMainMenu() {
	   Log.info("Starting navigation to the Main Menu...");
	    
	    Log.info("Locating the Dashboard element...");
	    WebElement Dashboard = driver.findElement(CoreRegularizationSummaryRepo.Dashboard);
	    
	    Log.info("Clicking on the Dashboard...");
	    Dashboard.click();
	    
	    Log.info("Successfully navigated to the Main Menu.");
   }

   // Method to click on regularization summary
   public void clickRegularizationSumary() {
	   Log.info("Starting the process to click on 'Regularization Summary'...");

	    Log.info("Locating the 'Regularization Summary' element...");
	    WebElement RegularizationSumary = driver.findElement(CoreRegularizationSummaryRepo.RegularizationSumary);

	    Log.info("Clicking on 'Regularization Summary'...");
	    RegularizationSumary.click();

	    Log.info("'Regularization Summary' clicked successfully.");
   }

   // Method to verify regularization summary report
   public boolean isRegularizationSumaryDisplayed() {
	   Log.info("Checking if 'Regularization Summary Report' is displayed...");

	    try {
	        Log.info("Waiting for 'Regularization Summary Report' element to be visible...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        WebElement Regularizationsummaryreport = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(CoreRegularizationSummaryRepo.Regularizationsummaryreport)
	        );

	        boolean isDisplayed = Regularizationsummaryreport.isDisplayed();
	        Log.info("'Regularization Summary Report' visibility status: " + isDisplayed);
	        return isDisplayed;
	    } catch (TimeoutException e) {
	        Log.error("'Regularization Summary Report' was not displayed within the expected time. --> "+ e);
	        return false;
	    }
   }
   
// Method to click on the download icon
   public void clickDownlodIcon() {
	   Log.info("Starting the process to click on the 'Download' icon...");

	    try {
	        Log.info("Locating the 'Download' button...");
	        WebElement downloadbtn = driver.findElement(CoreRegularizationSummaryRepo.downloadbtn);

	        Log.info("Clicking on the 'Download' button...");
	        downloadbtn.click();

	        Log.info("'Download' button clicked successfully.");
	    } catch (NoSuchElementException e) {
	        Log.error("Failed to locate the 'Download' button. It may not be present on the page. --> "+ e);
	    } catch (Exception e) {
	        Log.error("An unexpected error occurred while clicking the 'Download' button. --> "+ e);
	    }
   }
   
}