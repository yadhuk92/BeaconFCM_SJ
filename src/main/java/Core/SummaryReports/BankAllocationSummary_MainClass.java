package Core.SummaryReports;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.CoreSummaryReportsBankAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class BankAllocationSummary_MainClass {
	
private WebDriver driver;
	
	public BankAllocationSummary_MainClass(WebDriver driver) {  
		Log.info("Initializing BankAllocationSummary_MainClass...");
        
        this.driver = driver;
        
        Log.info("Initializing WebElements using PageFactory...");
        PageFactory.initElements(driver, this);
        
        Log.info("BankAllocationSummary_MainClass initialized successfully.");
    }
	
	public List<Object> deleteZoneUserToBranchUserAccountAllocation(String branchname, String branch, String userId) throws IOException {
		Log.info("Preparing input parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(branchname, branch, userId);
	    Log.info("Input parameters prepared: " + inputParams.toString());

	    Log.info("Defining output types for stored procedure...");
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	    Log.info("Output types defined: " + outputTypes.toString());

	    Log.info("Executing stored procedure 'SP_DeleteDataOfZoneUserToBranchUserAccountAllocation'...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_DeleteDataOfZoneUserToBranchUserAccountAllocation", inputParams, outputTypes);
	    Log.info("Stored procedure executed successfully. Results: " + results.toString());

	    return results; 
	}
	
	public List<Object> insertBCOUser(String userid) throws IOException { 
		Log.info("Preparing input parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(userid);
	    Log.info("Input parameters prepared: " + inputParams.toString());

	    Log.info("Defining output types for stored procedure...");
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	    Log.info("Output types defined: " + outputTypes.toString());

	    Log.info("Executing stored procedure 'SP_InsertBCOUserIn_MSTEMPLOYEE'...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_InsertBCOUserIn_MSTEMPLOYEE", inputParams, outputTypes);
	    Log.info("Stored procedure executed successfully. Results: " + results.toString());

	    return results; 
	}
	
	// Methods for interacting with the My Desk Page
    public void selectSmaTile() throws InterruptedException {
    	Log.info("Waiting for Alerts and Notifications menu to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
        Log.info("Alerts and Notifications menu is visible.");

        Log.info("Scrolling to bottom of Alerts and Notifications menu...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
        Log.info("Scroll complete.");
        
        Log.info("Waiting for 'My Desk' element to be visible...");
        Thread.sleep(5000);
        WebElement MyDesk = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.MyDesk));
        Log.info("'My Desk' is visible.");

        Log.info("Clicking on 'My Desk'...");
        MyDesk.click();
        Log.info("'My Desk' clicked.");

        Log.info("Waiting for 'Dashboard' element to be visible...");
        WebElement Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dashboard));
        Log.info("'Dashboard' is visible.");

        Log.info("Clicking on 'Dashboard'...");
        Dashboard.click();
        Log.info("'Dashboard' clicked.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        Log.info("Scrolling to bottom of the page...");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Log.info("Scroll complete.");

        Log.info("Waiting for 'Unassigned Accounts' to be visible...");
        Thread.sleep(5000);
        WebElement UnassignedAccounts = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.UnassignedAccounts));
        Log.info("'Unassigned Accounts' is visible.");

        Log.info("Clicking on 'Unassigned Accounts'...");
        UnassignedAccounts.click();  
        Log.info("'Unassigned Accounts' clicked.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        Log.info("Waiting for 'Download' to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Download));
        Log.info("'Download' is visible.");

        Log.info("Waiting for spinner to disappear again...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        Log.info("Waiting for 'Reset' to be visible...");
        WebElement Reset = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Reset));
        Log.info("'Reset' is visible.");

        Log.info("Clicking on 'Reset'...");
        Reset.click();
        Log.info("'Reset' clicked.");

        Log.info("Waiting for final spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Final spinner disappeared. 'selectSmaTile' completed.");
    }

    public void selectAssetCategory(String category) {
    	Log.info("Waiting for 'Asset Category' dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement AssetCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.AssetCategory));
        Log.info("'Asset Category' dropdown is visible.");

        Log.info("Clicking on 'Asset Category' dropdown...");
        AssetCategory.click();
        Log.info("'Asset Category' dropdown clicked.");

        Log.info("Waiting for dropdown value '" + category + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dropdownvalues(category)));
        Log.info("Dropdown value '" + category + "' is visible.");

        Log.info("Clicking on dropdown value '" + category + "'...");
        Dropdownvalues.click();
        Log.info("Dropdown value '" + category + "' clicked.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared. Asset category selection complete.");
    }

    public void selectSmaCategory(String category) {
    	Log.info("Waiting for 'SMA Category' dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement SMACategory = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.SMACategory));
        Log.info("'SMA Category' dropdown is visible.");

        Log.info("Clicking on 'SMA Category' dropdown...");
        SMACategory.click();
        Log.info("'SMA Category' dropdown clicked.");

        Log.info("Waiting for dropdown value '" + category + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dropdownvalues(category)));
        Log.info("Dropdown value '" + category + "' is visible.");

        Log.info("Clicking on dropdown value '" + category + "'...");
        Dropdownvalues.click();
        Log.info("Dropdown value '" + category + "' clicked.");
    }

    public void selectRegion(String region) {
    	Log.info("Waiting for 'Region' dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Region));
        Log.info("'Region' dropdown is visible.");

        Log.info("Clicking on 'Region' dropdown...");
        Region.click();
        Log.info("'Region' dropdown clicked.");

        Log.info("Waiting for dropdown value '" + region + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dropdownvalues(region)));
        Log.info("Dropdown value '" + region + "' is visible.");

        Log.info("Clicking on dropdown value '" + region + "'...");
        Dropdownvalues.click();
        Log.info("Dropdown value '" + region + "' clicked.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared. Region selection complete.");
    }

    public void selectBranch(String name) { 
    	Log.info("Waiting for 'Branch' dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
        Log.info("'Branch' dropdown is visible.");

        Log.info("Clicking on 'Branch' dropdown...");
        Branch.click();
        Log.info("'Branch' dropdown clicked.");

        Log.info("Waiting for branch search input field to be visible...");
        WebElement Branchsearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branchsearchspace));
        Log.info("Branch search input field is visible.");

        Log.info("Clearing existing text in branch search input field...");
        Branchsearchspace.clear();
        Log.info("Branch search input field cleared.");

        Log.info("Typing branch name '" + name + "' into search input...");
        Branchsearchspace.sendKeys(name);
        Log.info("Branch name '" + name + "' entered.");

        Log.info("Waiting for dropdown value '" + name + "' to be visible...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dropdownvalues(name)));
        Log.info("Dropdown value '" + name + "' is visible.");

        Log.info("Clicking on dropdown value '" + name + "'...");
        Dropdownvalues.click();
        Log.info("Dropdown value '" + name + "' clicked.");
    }

    public void selectDpdFinancialOperation(String operator , String amount) {
    	Log.info("Waiting for 'OS Balance Operators' dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement OsBalance_Operatorsdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.OsBalance_Operatorsdropdown));
        Log.info("'OS Balance Operators' dropdown is visible.");

        Log.info("Clicking on 'OS Balance Operators' dropdown...");
        OsBalance_Operatorsdropdown.click();
        Log.info("'OS Balance Operators' dropdown clicked.");

        Log.info("Waiting for dropdown value '" + operator + "' to be visible...");
        WebElement OsBalance_OperatorsdropdownDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.OsBalance_OperatorsdropdownDropdownvalues(operator)));
        Log.info("Dropdown value '" + operator + "' is visible.");

        Log.info("Clicking on dropdown value '" + operator + "'...");
        OsBalance_OperatorsdropdownDropdownvalues.click();
        Log.info("Dropdown value '" + operator + "' clicked.");

        Log.info("Waiting for 'Amount' input field to be visible...");
        WebElement OsBalance_textamountfield = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.OsBalance_textamountfield));
        Log.info("'Amount' input field is visible.");

        Log.info("Clearing existing value in 'Amount' input field...");
        OsBalance_textamountfield.clear();
        Log.info("'Amount' input field cleared.");

        Log.info("Entering amount: " + amount);
        OsBalance_textamountfield.sendKeys(amount);
        Log.info("Amount '" + amount + "' entered.");
    }

    public void clickSearch() {
    	Log.info("Waiting for 'Search' button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Search = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Search));
        Log.info("'Search' button is visible.");

        Log.info("Clicking on 'Search' button...");
        Search.click();
        Log.info("'Search' button clicked.");

        Log.info("Waiting for 'Download' button to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Download));
        Log.info("'Download' button is visible. Search operation completed.");
    }

    public void selectAllocateToBranch(String branch) throws InterruptedException {
    	Log.info("Scrolling to bottom of the page...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Log.info("Scrolled to bottom of the page.");

        Log.info("Waiting for 5 seconds after scroll...");
        Thread.sleep(5000);
        Log.info("Wait completed.");

        Log.info("Waiting for 'Allocate To' dropdown to be visible...");
        WebElement AllocateTo = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.AllocateTo));
        Log.info("'Allocate To' dropdown is visible.");

        Log.info("Clicking on 'Allocate To' dropdown...");
        AllocateTo.click();
        Log.info("'Allocate To' dropdown clicked.");

        Log.info("Waiting for dropdown value '" + branch + "' to be visible...");
        WebElement AllocateToDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.AllocateToDropdownvalues(branch)));
        Log.info("Dropdown value '" + branch + "' is visible.");

        Log.info("Clicking on dropdown value '" + branch + "'...");
        AllocateToDropdownvalues.click();
        Log.info("Dropdown value '" + branch + "' clicked.");
    }

    public void clickAllocate() {
    	Log.info("Waiting for 'Allocate' button to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Allocate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Allocate));
        Log.info("'Allocate' button is visible.");

        Log.info("Clicking on 'Allocate' button...");
        Allocate.click();
        Log.info("'Allocate' button clicked.");
    }

    public boolean isConfirmationMessageDisplayed() {
    	Log.info("Waiting for allocation success message to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Allocationsuccessmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Allocationsuccessmessage));
        Log.info("Allocation success message is visible.");

        Log.info("Checking if allocation success message is displayed...");
        boolean isDisplayed = Allocationsuccessmessage.isDisplayed();
        Log.info("Allocation success message displayed status: " + isDisplayed);

        return isDisplayed;
    }
	
	public List<Object> createBranchUser(String branch) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		Log.info("Reading Branch User Credentials from properties file..."); 
	    String loginUserId; 
	    String loginPassword;
	    String statusMessage;

	    String fileName = "CoreBranchUserCredentials_CoreUserManagement_Branch_User_Creation.properties";
	    Properties properties = PropertiesFileUtil.ReadFromPropertiesFile(fileName);
	    Log.info("Properties file read successfully.");

	    loginUserId = properties.getProperty("Branch_User_ID");
	    Log.info("Branch_User_ID retrieved from properties: " + loginUserId);

	    Log.info("Initializing input parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(loginUserId,branch); 
	    Log.info("Input parameters initialized.");

	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
	    Log.info("Output parameter types defined.");

	    Log.info("Executing stored procedure: SP_PICK_EXISTING_CORE_BRANCH_USER...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_PICK_EXISTING_CORE_BRANCH_USER",
	        inputParams,
	        outputTypes
	    );
	    Log.info("Stored procedure execution completed.");

	    if (results == null || results.size() < 2) {
	        Log.warn("Stored procedure returned null or incomplete results.");
	        return results;
	    }

	    loginUserId = (String) results.get(0);
	    loginPassword = (String) results.get(1);
	    statusMessage = (String) results.get(2);
	    Log.info("Stored procedure returned values - User ID: " + loginUserId + ", Password: [PROTECTED], Status: " + statusMessage);

	    Log.info("Updating properties file with generated Branch user credentials...");
	    Map<String, String> updates = new HashMap<>();
	    updates.put("Branch_User_ID", loginUserId);
	    updates.put("Branch_User_Password", loginPassword);
	    Log.info("Credential map prepared for update.");

	    if (properties.isEmpty()) {
	        Log.info("Properties file is empty. Updating with new credentials...");
	        PropertiesFileUtil.updateProperties(fileName, updates);
	        Log.info("Properties file updated successfully.");
	    } else {
	        Log.info("Properties file already contains values. Skipping update.");
	        loginUserId = properties.getProperty("Branch_User_ID");
	        loginPassword = properties.getProperty("Branch_User_Password");
	        Log.info("Using existing credentials from properties file.");
	    }

	    Log.info("Branch User ID: " + loginUserId);
	    Log.info("Password: [PROTECTED]");
	    Log.info("Status message: " + statusMessage);

	    Log.info("Returning generated credentials...");
	    return Arrays.asList(loginUserId, loginPassword, statusMessage); 
	}
	
	public void navigateToMyDesk() throws InterruptedException {
		Log.info("Waiting for Alerts and Notifications menu to be visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
		Log.info("Alerts and Notifications menu is visible.");

		Log.info("Scrolling Alerts and Notifications menu to the bottom...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
		Thread.sleep(5000);
		Log.info("Scroll action completed.");

		Log.info("Waiting for 'My Desk' menu item to be visible...");
		WebElement MyDesk = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.MyDesk));
		Log.info("'My Desk' is visible.");

		Log.info("Clicking on 'My Desk'...");
		MyDesk.click();
		Log.info("'My Desk' clicked successfully.");

		Log.info("Waiting for 'Dashboard' to be visible...");
		WebElement Dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dashboard));
		Log.info("'Dashboard' is visible.");

		Log.info("Clicking on 'Dashboard'...");
		Dashboard.click();
		Log.info("'Dashboard' clicked successfully.");

		Log.info("Waiting for loading spinner to disappear...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Loading spinner disappeared. Navigation to 'My Desk' completed.");
    }
	
	public void selectSmaFromBranchAttention() {
		Log.info("Waiting for 'Branch Attention Required Accounts' element to be visible...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement BranchAttentionRequiredAccounts = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.BranchAttentionRequiredAccounts));
	    Log.info("'Branch Attention Required Accounts' element is visible.");

	    Log.info("Clicking on 'Branch Attention Required Accounts'...");
	    BranchAttentionRequiredAccounts.click();
	    Log.info("Clicked on 'Branch Attention Required Accounts'.");

	    Log.info("Waiting for spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner disappeared.");

	    try {
	        Log.info("Waiting for pagination element to be visible...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Pagination));
	        Log.info("Pagination element is visible.");
	    } catch (Exception e) {
	        Log.ErrorWithException("Accounts not found. Please try again.", e);
	        throw new RuntimeException("Accounts not found. Please try again.", e);
	    }

	    Log.info("Waiting again for spinner to disappear...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner disappeared again.");
    }
	
	 public void selectAllAccountsFromFirstPage() throws InterruptedException {
		 Log.info("Creating WebDriverWait instance...");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		    
		    Log.info("Scrolling to bottom of the page using JavaScript...");
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		    Log.info("Scroll to bottom completed.");

		    Log.info("Waiting for 5 seconds...");
		    Thread.sleep(5000);
		    Log.info("Wait completed.");

		    Log.info("Waiting for 'Select all from grid' element to be visible...");
		    WebElement Selectallfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Selectallfromgrid));
		    Log.info("'Select all from grid' element is visible.");

		    Log.info("Clicking on 'Select all from grid'...");
		    Selectallfromgrid.click();
		    Log.info("Clicked on 'Select all from grid'.");
	    }
	 
	 public void assignToBranch(String username) {
		 Log.info("Creating WebDriverWait instance...");
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		    Log.info("Waiting for 'Assign/Reassign To' element to be visible...");
		    WebElement Assign_ReassignTo = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Assign_ReassignTo));
		    Log.info("'Assign/Reassign To' element is visible.");

		    Log.info("Clicking on 'Assign/Reassign To'...");
		    Assign_ReassignTo.click();
		    Log.info("Clicked on 'Assign/Reassign To'.");

		    Log.info("Waiting for 'Assign/Reassign To' search space to be visible...");
		    WebElement Assign_ReassignTosearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Assign_ReassignTosearchspace));
		    Log.info("'Assign/Reassign To' search space is visible.");

		    Log.info("Clearing the 'Assign/Reassign To' search space...");
		    Assign_ReassignTosearchspace.clear();
		    Log.info("Cleared the search space.");

		    Log.info("Entering username into 'Assign/Reassign To' search space...");
		    Assign_ReassignTosearchspace.sendKeys(username);
		    Log.info("Entered username: " + username);

		    Log.info("Waiting for dropdown value matching the username to be visible...");
		    WebElement AllocateTo_and_AssignReassignToDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.AssignReassignToDropdownvalues(username)));
		    Log.info("Dropdown value is visible.");

		    Log.info("Clicking on the dropdown value for username...");
		    AllocateTo_and_AssignReassignToDropdownvalues.click();
		    Log.info("Clicked on the dropdown value for username.");
	    }
	    
	    public void clickAssign() {
	    	Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Assign' button to be visible...");
	        WebElement Assign = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Assign));
	        Log.info("'Assign' button is visible.");

	        Log.info("Clicking on 'Assign' button...");
	        Assign.click();
	        Log.info("Clicked on 'Assign' button.");
	    }
	    
	    public boolean isConfirmationDisplayed() {
	    	Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Assign Success Message' element to be visible...");
	        WebElement Assignsuccessmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Assignsuccessmessage));
	        Log.info("'Assign Success Message' element is visible.");

	        Log.info("Checking if 'Assign Success Message' is displayed...");
	        boolean isDisplayed = Assignsuccessmessage.isDisplayed();
	        Log.info("'Assign Success Message' displayed status: " + isDisplayed);

	        return isDisplayed;
	    }
	    
	    public List<String> getAccountNumbersFromGrid() {
	    	Log.info("Fetching list of account number elements from the grid...");
	        List<WebElement> accountNumberElements = driver.findElements(CoreSummaryReportsBankAllocationSummaryRepo.AccountNumbersfromGrid);
	        Log.info("Fetched " + accountNumberElements.size() + " account number elements.");

	        Log.info("Extracting text from each account number element...");
	        List<String> accountNumbers = new ArrayList<>();

	        for (WebElement element : accountNumberElements) {
	            String accountNumber = element.getText().trim();
	            accountNumbers.add(accountNumber);
	            Log.info("Extracted account number: " + accountNumber);
	        }

	        Log.info("All account numbers extracted successfully.");
	        return accountNumbers; 
	    } 
	    
	    public String insertAccountsForUser(String userId) throws IOException { 
	    	Log.info("Preparing input parameters for stored procedure call...");
	        List<Object> inputParams = Arrays.asList(userId);
	        Log.info("Input parameters prepared: userId = " + userId);

	        Log.info("Preparing output parameter types...");
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	        Log.info("Output parameter types prepared.");

	        Log.info("Calling stored procedure 'SP_INSERT_TRN_AC_MOVEMENT_ForMyDeskDashBoard'...");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_INSERT_TRN_AC_MOVEMENT_ForMyDeskDashBoard", inputParams, outputTypes);
	        Log.info("Stored procedure executed successfully.");

	        String statusMessage = results.get(0).toString();
	        Log.info("Received status message from stored procedure: " + statusMessage);

	        return statusMessage; 
	    }
	    
	    public void openAllocationSummaryWindow() throws InterruptedException {
	    	Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Alerts and Notifications' menu to be visible...");
	        WebElement alertsAndNotificationsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAlertsPlaceholderManagementRepo.alertsAndNotificationsMenu));
	        Log.info("'Alerts and Notifications' menu is visible.");

	        Log.info("Scrolling through 'Alerts and Notifications' menu...");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", alertsAndNotificationsMenu);
	        Log.info("Scroll action completed.");

	        Log.info("Waiting for 5 seconds...");
	        Thread.sleep(5000);
	        Log.info("Wait completed.");

	        Log.info("Waiting for 'Summary Reports' to be visible...");
	        WebElement SummaryReports = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.SummaryReports));
	        Log.info("'Summary Reports' is visible.");

	        Log.info("Clicking on 'Summary Reports'...");
	        SummaryReports.click();
	        Log.info("Clicked on 'Summary Reports'.");

	        Log.info("Waiting for 'Bank Allocation Summary' to be visible...");
	        WebElement BankAllocationSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.BankAllocationSummary));
	        Log.info("'Bank Allocation Summary' is visible.");

	        Log.info("Clicking on 'Bank Allocation Summary'...");
	        BankAllocationSummary.click();
	        Log.info("Clicked on 'Bank Allocation Summary'.");

	        Log.info("Waiting for 'Branch' element to be visible...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
	        Log.info("'Branch' element is visible.");

	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared.");
	    }

	    public void fillMandatoryFields(String region, String branch, String fromDate, String toDate) {
	    	Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Region' dropdown to be visible...");
	        WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Region));
	        Log.info("'Region' dropdown is visible.");

	        Log.info("Clicking on 'Region' dropdown...");
	        Region.click();
	        Log.info("Clicked on 'Region' dropdown.");

	        Log.info("Waiting for region dropdown value: " + region);
	        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Dropdownvalues(region)));
	        Log.info("Region dropdown value is visible.");

	        Log.info("Selecting region: " + region);
	        Dropdownvalues.click();
	        Log.info("Selected region: " + region);

	        Log.info("Waiting for spinner to disappear after selecting region...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared.");

	        Log.info("Waiting for 'Branch' field to be visible...");
	        WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
	        Log.info("'Branch' field is visible.");

	        Log.info("Clicking on 'Branch'...");
	        Branch.click();
	        Log.info("Clicked on 'Branch'.");

	        Log.info("Waiting for 'Assign/Reassign To' search space to be visible...");
	        WebElement Assign_ReassignTosearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Assign_ReassignTosearchspace));
	        Log.info("'Assign/Reassign To' search space is visible.");

	        Log.info("Clearing 'Assign/Reassign To' search space...");
	        Assign_ReassignTosearchspace.clear();
	        Log.info("Cleared search space.");

	        Log.info("Entering branch name: " + branch);
	        Assign_ReassignTosearchspace.sendKeys(branch);
	        Log.info("Entered branch name: " + branch);

	        Log.info("Waiting for branch dropdown value to be visible...");
	        WebElement Dropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.AllocateToDropdownvalues(branch)));
	        Log.info("Branch dropdown value is visible.");

	        Log.info("Selecting branch: " + branch);
	        Dropdownvalue.click();
	        Log.info("Selected branch: " + branch);

	        Log.info("Waiting for spinner to disappear after selecting branch...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared.");

	        Log.info("Waiting for 'From Date' field to be visible...");
	        WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.FromDate));
	        Log.info("'From Date' field is visible.");

	        Log.info("Clicking on 'From Date'...");
	        FromDate.click();
	        Log.info("Clicked on 'From Date'.");

	        Log.info("Waiting for 'From Date' dropdown value: " + fromDate);
	        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.DateDropdownvalues(fromDate)));
	        Log.info("'From Date' dropdown value is visible.");

	        Log.info("Selecting 'From Date': " + fromDate);
	        DateDropdownvalues.click();
	        Log.info("Selected 'From Date': " + fromDate);

	        Log.info("Waiting for 'To Date' field to be visible...");
	        WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.ToDate));
	        Log.info("'To Date' field is visible.");

	        Log.info("Clicking on 'To Date'...");
	        ToDate.click();
	        Log.info("Clicked on 'To Date'.");

	        Log.info("Waiting for 'To Date' dropdown value: " + toDate);
	        WebElement DateDropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.DateDropdownvalues(toDate)));
	        Log.info("'To Date' dropdown value is visible.");

	        Log.info("Selecting 'To Date': " + toDate);
	        DateDropdownvalue.click();
	        Log.info("Selected 'To Date': " + toDate);
	    }

	    public void clickSearchButton() {
	    	Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Search' button to be visible...");
	        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.search));
	        Log.info("'Search' button is visible.");

	        Log.info("Clicking on 'Search' button...");
	        search.click();
	        Log.info("Clicked on 'Search' button.");

	        Log.info("Waiting for spinner to disappear after clicking 'Search'...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared.");
	    }

	    public boolean areTodaysAllocationsDisplayed(List<String> accountNumbers) {
	    	Log.info("Calculating expected account count...");
	        int count = accountNumbers.size();
	        Log.info("Expected account count: " + count);

	        Log.info("Getting current system date in format dd-MM-yyyy...");
	        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	        Log.info("Current system date: " + currentDate);

	        Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Date from grid' to be visible...");
	        WebElement Datefromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Datefromgrid));
	        Log.info("'Date from grid' is visible.");

	        Log.info("Retrieving displayed date from grid...");
	        String displayedDate = Datefromgrid.getText().trim();
	        Log.info("Displayed date from grid: " + displayedDate);

	        Log.info("Waiting for 'Count of Accounts from grid' to be visible...");
	        WebElement CountofAccountsfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.CountofAccountsfromgrid));
	        Log.info("'Count of Accounts from grid' is visible.");

	        Log.info("Retrieving displayed account count from grid...");
	        String displayedCountStr = CountofAccountsfromgrid.getText().trim();
	        Log.info("Displayed account count string: " + displayedCountStr);

	        Log.info("Parsing displayed account count to integer...");
	        int displayedCount;
	        try {
	            displayedCount = Integer.parseInt(displayedCountStr);
	            Log.info("Parsed displayed account count: " + displayedCount);
	        } catch (NumberFormatException e) {
	            Log.ErrorWithException("Displayed account count is not a valid number: " + displayedCountStr, e);
	            return false;
	        }

	        Log.info("Comparing expected and displayed dates...");
	        boolean isDateMatching = currentDate.equals(displayedDate);
	        if (!isDateMatching) {
	            Log.warn("Date mismatch -> Expected: " + currentDate + ", Found: " + displayedDate);
	        } else {
	            Log.info("Date matches.");
	        }

	        Log.info("Comparing expected and displayed account counts...");
	        boolean isCountMatching = count == displayedCount;
	        if (!isCountMatching) {
	            Log.warn("Count mismatch -> Expected: " + count + ", Found: " + displayedCount);
	        } else {
	            Log.info("Account count matches.");
	        }

	        boolean result = isDateMatching && isCountMatching;
	        Log.info("Final result of today's allocation check: " + result);
	        return result; 
	    }
	    
	    public String updateAllocatedDate(String regionName, String branchName, String userId) throws IOException {
	    	Log.info("Preparing input parameters for stored procedure 'SP_UPDATE_ALLOCATED_DATE'...");
	        List<Object> inputParams = Arrays.asList(regionName, branchName, userId);
	        Log.info("Input parameters prepared: Region = " + regionName + ", Branch = " + branchName + ", User ID = " + userId);

	        Log.info("Preparing output parameter types...");
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	        Log.info("Output parameter types prepared.");

	        Log.info("Calling stored procedure 'SP_UPDATE_ALLOCATED_DATE' using DBUtils...");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_UPDATE_ALLOCATED_DATE", inputParams, outputTypes);
	        Log.info("Stored procedure executed successfully.");

	        Log.info("Retrieving result from stored procedure...");
	        String result = results.get(0).toString();
	        Log.info("Stored procedure result: " + result);

	        return result;  
	    }
	    
	    public String runBranchAllocDashboard() throws IOException {
	        Log.info("Preparing input parameters for stored procedure 'SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG'...");
	        List<Object> inputParams = null; // No input parameters

	        Log.info("Preparing output parameter types...");
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	        Log.info("Output parameter types prepared.");

	        Log.info("Calling stored procedure 'SP_RUN_BRANCH_ALLOC_DASHBOARD' using DBUtils...");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG", inputParams, outputTypes);
	        Log.info("Stored procedure executed successfully.");

	        Log.info("Retrieving result from stored procedure...");
	        String result = results.get(0).toString();
	        Log.info("Stored procedure result: " + result);

	        return result;
	    }
	    
	    public List<Object> checkDashboardDataLoadExecution(String userId) throws IOException {
	        Log.info("Preparing input parameters for stored procedure 'SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_EXECUTION_DATA'...");
	        List<Object> inputParams = Arrays.asList(userId);
	        Log.info("Input parameter prepared: User ID = " + userId);

	        Log.info("Preparing output parameter types...");
	        List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);
	        Log.info("Output parameter types prepared.");

	        Log.info("Calling stored procedure 'SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_EXECUTION_DATA' using DBUtils...");
	        List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_EXECUTION_DATA", inputParams, outputTypes);
	        Log.info("Stored procedure executed successfully."); 

	        Log.info("Retrieved results:");
	        Log.info("DW_BRANCH_ALLOCATIONS_SUMMARY table entry: " + results.get(0));
	        Log.info("DW_BRANCH_ALLOCATIONS_DASHBOARD table entry: " + results.get(1));

	        return results;
	    }
	    
	    // Method to click download as Excel
	    public void downloadAsExcel() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for the download button to become visible...");
	        WebElement Downloadbuttonfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Downloadbuttonfromgrid));
	        Log.info("Download button is visible.");

	        Log.info("Clicking on the download button to initiate Excel download...");
	        Downloadbuttonfromgrid.click();
	        Log.info("Download button clicked.");

	        Log.info("Waiting for download success message to appear...");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Downloadsuccessmessage));
	        Log.info("Download success message appeared.");

	        Log.info("Waiting for download success message to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Downloadsuccessmessage));
	        Log.info("Download success message disappeared.");

	        Log.info("Waiting for spinner to disappear...");
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	        Log.info("Spinner disappeared. Excel download process is complete.");
	    }
	    
	    public boolean isExcelvaluesMatchingWithUI() {
	    	try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	            Log.info("Waiting for 'Count of Accounts' value from UI...");
	            WebElement CountofAccountsfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.CountofAccountsfromgrid));
	            Log.info("'Count of Accounts' value retrieved from UI.");
	            String displayedCountStr = CountofAccountsfromgrid.getText().trim();
	            int displayedCount = Integer.parseInt(displayedCountStr);
	            Log.info("Parsed UI count of accounts: " + displayedCount);

	            Log.info("Waiting for 'TOTAL_OS_AMOUNT_IN_LAKHS' value from UI...");
	            WebElement TOTAL_OS_AMOUNT_IN_LAKHSfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.TOTAL_OS_AMOUNT_IN_LAKHSfromgrid));
	            Log.info("'TOTAL_OS_AMOUNT_IN_LAKHS' value retrieved from UI.");
	            String displayedOutBalStr = TOTAL_OS_AMOUNT_IN_LAKHSfromgrid.getText().trim();
	            double displayedOutBal = Double.parseDouble(displayedOutBalStr);
	            String displayedFormatted = String.format("%.2f", displayedOutBal);
	            Log.info("Parsed and formatted UI outstanding balance: " + displayedFormatted);

	            Log.info("Reading data from downloaded Excel...");
	            Map<String, Object> excelData = DownloadedExcelReader.getACCountAndOutBalInLakhs(); 
	            Log.info("Excel data read successfully.");

	            int excelACCount = (int) excelData.get("TotalACNumbers");
	            double excelOutBalInLakhs = (double) excelData.get("TotalOutBalInLakhs");
	            String excelFormatted = String.format("%.2f", excelOutBalInLakhs);
	            Log.info("Parsed Excel count: " + excelACCount + ", formatted outstanding balance: " + excelFormatted);

	            boolean isCountMatching = displayedCount == excelACCount;
	            boolean isOutBalMatching = displayedFormatted.equals(excelFormatted);

	            if (!isCountMatching) {
	                Log.warn("Mismatch in account count: UI = " + displayedCount + ", Excel = " + excelACCount);
	            } else {
	                Log.info("Account count matches between UI and Excel.");
	            }

	            if (!isOutBalMatching) {
	                Log.warn("Mismatch in outstanding balance: UI = " + displayedFormatted + ", Excel = " + excelFormatted);
	            } else {
	                Log.info("Outstanding balance matches between UI and Excel.");
	            }

	            boolean finalResult = isCountMatching && isOutBalMatching;
	            Log.info("Final comparison result: " + finalResult);
	            return finalResult;

	        } catch (Exception e) {
	            Log.ErrorWithException("Exception while comparing Excel and UI values: " + e.getMessage(), e);
	            return false;
	        }
	    }
	    
	    public void selectyesterdaysdate(String fromDate, String toDate) {
	    	
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'From Date' field to be visible...");
	        WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.FromDate));
	        Log.info("'From Date' field is visible.");

	        Log.info("Clicking on 'From Date'...");
	        FromDate.click();
	        Log.info("Clicked on 'From Date'.");

	        Log.info("Waiting for 'From Date' dropdown value: " + fromDate);
	        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.DateDropdownvalues(fromDate)));
	        Log.info("'From Date' dropdown value is visible.");

	        Log.info("Selecting 'From Date': " + fromDate);
	        DateDropdownvalues.click();
	        Log.info("Selected 'From Date': " + fromDate);

	        Log.info("Waiting for 'To Date' field to be visible...");
	        WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.ToDate));
	        Log.info("'To Date' field is visible.");

	        Log.info("Clicking on 'To Date'...");
	        ToDate.click();
	        Log.info("Clicked on 'To Date'.");

	        Log.info("Waiting for 'To Date' dropdown value: " + toDate);
	        WebElement DateDropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.DateDropdownvalues(toDate)));
	        Log.info("'To Date' dropdown value is visible.");

	        Log.info("Selecting 'To Date': " + toDate);
	        DateDropdownvalue.click();
	        Log.info("Selected 'To Date': " + toDate);
	    }
	    
	    public boolean areYesterdaysAllocationsDisplayed(List<String> accountNumbers) {
	    	Log.info("Calculating expected account count...");
	        int count = accountNumbers.size();
	        Log.info("Expected account count: " + count);

	        Log.info("Getting yesterdays system date in format dd-MM-yyyy...");
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE, -1); // Subtract 1 day
	        String yesterdayDate = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
	        Log.info("Yesterdays system date: " + yesterdayDate); 

	        Log.info("Creating WebDriverWait instance...");
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	        Log.info("Waiting for 'Date from grid' to be visible...");
	        WebElement Datefromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Datefromgrid));
	        Log.info("'Date from grid' is visible.");

	        Log.info("Retrieving displayed date from grid...");
	        String displayedDate = Datefromgrid.getText().trim();
	        Log.info("Displayed date from grid: " + displayedDate);

	        Log.info("Waiting for 'Count of Accounts from grid' to be visible...");
	        WebElement CountofAccountsfromgrid = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.CountofAccountsfromgrid));
	        Log.info("'Count of Accounts from grid' is visible.");

	        Log.info("Retrieving displayed account count from grid...");
	        String displayedCountStr = CountofAccountsfromgrid.getText().trim();
	        Log.info("Displayed account count string: " + displayedCountStr);

	        Log.info("Parsing displayed account count to integer...");
	        int displayedCount;
	        try {
	            displayedCount = Integer.parseInt(displayedCountStr);
	            Log.info("Parsed displayed account count: " + displayedCount);
	        } catch (NumberFormatException e) {
	            Log.ErrorWithException("Displayed account count is not a valid number: " + displayedCountStr, e);
	            return false;
	        }

	        Log.info("Comparing expected and displayed dates...");
	        boolean isDateMatching = yesterdayDate.equals(displayedDate);
	        if (!isDateMatching) {
	            Log.warn("Date mismatch -> Expected: " + yesterdayDate + ", Found: " + displayedDate); 
	        } else {
	            Log.info("Date matches.");
	        }

	        Log.info("Comparing expected and displayed account counts...");
	        boolean isCountMatching = count == displayedCount;
	        if (!isCountMatching) {
	            Log.warn("Count mismatch -> Expected: " + count + ", Found: " + displayedCount);
	        } else {
	            Log.info("Account count matches.");
	        }

	        boolean result = isDateMatching && isCountMatching;
	        Log.info("Final result of today's allocation check: " + result);
	        return result; 
	    }

}
