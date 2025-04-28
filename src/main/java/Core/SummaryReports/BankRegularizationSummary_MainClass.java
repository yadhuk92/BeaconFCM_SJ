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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.PropertiesFileUtil;
import com.Page_Repository.CoreSummaryReportsBankAllocationSummaryRepo;
import com.Page_Repository.CoreSummaryReportsBankRegularizationSummaryRepo;
import com.Page_Repository.CoreAlertsPlaceholderManagementRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class BankRegularizationSummary_MainClass { 
	
private WebDriver driver;
	
	public BankRegularizationSummary_MainClass(WebDriver driver) {  
		Log.info("Initializing BankRegularizationSummary_MainClass...");
	    
	    this.driver = driver;
	    
	    Log.info("Initializing WebElements using PageFactory...");
	    PageFactory.initElements(driver, this);
	    
	    Log.info("BankRegularizationSummary_MainClass initialized successfully.");
    }
	
	public void navigateToBankRegularizationSummary() throws InterruptedException { 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

	    Log.info("Waiting for visibility of SummaryReports element...");
	    WebElement SummaryReports = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.SummaryReports));
	    Log.info("SummaryReports element is visible.");

	    Log.info("Clicking on SummaryReports...");
	    SummaryReports.click();
	    Log.info("Clicked on SummaryReports.");

	    Log.info("Waiting for visibility of BankRegularizationSummary link...");
	    WebElement bankRegularizationSummaryLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.bankRegularizationSummaryLink)); 
	    Log.info("BankRegularizationSummary link is visible.");

	    Log.info("Clicking on BankRegularizationSummary link...");
	    bankRegularizationSummaryLink.click();
	    Log.info("Clicked on BankRegularizationSummary link.");

	    Log.info("Waiting for spinner to disappear (1st wait)...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner disappeared (1st wait).");

	    Log.info("Waiting for visibility of Branch element...");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
	    Log.info("Branch element is visible.");

	    Log.info("Waiting for spinner to disappear (2nd wait)...");
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	    Log.info("Spinner disappeared (2nd wait).");
    }
	
	public List<Object> removeACMovementForMyDeskDashboard(String acMovementId) throws IOException {
		Log.info("Preparing input and output parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(acMovementId);
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
	    Log.info("Input and output parameters prepared.");

	    Log.info("Calling stored procedure: SP_INSERT_TRN_AC_MOVEMENT_REMOVED_ForMyDeskDashBoard...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure( 
	        "SP_INSERT_TRN_AC_MOVEMENT_REMOVED_ForMyDeskDashBoard",
	        inputParams,
	        outputTypes
	    );
	    Log.info("Stored procedure executed successfully.");

	    Log.info("Printing results from stored procedure...");
	    System.out.println("Removed entry AC_MOVEMENT DATE: " + results.get(0));
	    System.out.println("Status Message: " + results.get(1));
	    Log.info("Results printed.");

	    Log.info("Returning results from removeACMovementForMyDeskDashboard method.");
	    return results;
	}
	
	public List<Object> runBranchRegularizationDashboardLoad() throws IOException {
		Log.info("Preparing input and output parameters for stored procedure...");
	    List<Object> inputParams = null; // No input parameters
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR);
	    Log.info("Input and output parameters prepared.");

	    Log.info("Calling stored procedure: SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure( 
	        "SP_RUN_PACKAGE_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD",
	        inputParams,
	        outputTypes
	    );
	    Log.info("Stored procedure executed successfully.");

	    Log.info("Printing result from stored procedure...");
	    System.out.println("Procedure Execution Result: " + results.get(0));
	    Log.info("Result printed.");

	    Log.info("Returning results from runBranchRegularizationDashboardLoad method.");
	    return results;
	}
	
	public List<Object> checkBranchRegularizationDashboardData(String branchCode) throws IOException {
		Log.info("Preparing input and output parameters for stored procedure...");
	    List<Object> inputParams = Arrays.asList(branchCode);
	    List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR);
	    Log.info("Input and output parameters prepared.");

	    Log.info("Calling stored procedure: SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD_DATA...");
	    List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure(
	        "SP_CHECK_ALLOCATION_DASHBOARD_DATA_LOAD_PKG_SPPROCESSBRANCHREGULARIZATIONDASHBOARD_DATA",
	        inputParams,
	        outputTypes
	    );
	    Log.info("Stored procedure executed successfully.");

	    Log.info("Printing results from stored procedure...");
	    System.out.println("DW_BRANCH_REGULARIZATION_SUMMARY table entry: " + results.get(0));
	    System.out.println("DW_BRANCH_REGULARIZATION_DETAILS table entry: " + results.get(1));
	    Log.info("Results printed.");

	    Log.info("Returning results from checkBranchRegularizationDashboardData method.");
	    return results; 
	}
	
	 // Method to select a region
    public void selectRegion(String region) {
    	Log.info("Waiting for visibility of Region dropdown...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Region = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Region));
        Log.info("Region dropdown is visible.");

        Log.info("Clicking on Region dropdown...");
        Region.click();
        Log.info("Clicked on Region dropdown.");

        Log.info("Waiting for visibility of dropdown value: " + region + "...");
        WebElement Dropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.Dropdownvalues(region)));
        Log.info("Dropdown value " + region + " is visible.");

        Log.info("Clicking on dropdown value: " + region + "...");
        Dropdownvalues.click();
        Log.info("Clicked on dropdown value: " + region + ".");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");
    }

    // Method to select a branch
    public void selectBranch(String branch) {
    	Log.info("Waiting for visibility of Branch dropdown...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Branch = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
        Log.info("Branch dropdown is visible.");

        Log.info("Clicking on Branch dropdown...");
        Branch.click();
        Log.info("Clicked on Branch dropdown.");

        Log.info("Waiting for visibility of branch search space...");
        WebElement branchsearchspace = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.branchsearchspace));
        Log.info("Branch search space is visible.");

        Log.info("Entering branch name: " + branch + " in search space...");
        branchsearchspace.sendKeys(branch);
        Log.info("Branch name entered.");

        Log.info("Waiting for visibility of dropdown value for branch: " + branch + "...");
        WebElement Dropdownvaluesforbranch = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.Dropdownvaluesforbranch(branch)));
        Log.info("Dropdown value for branch is visible.");

        Log.info("Clicking on dropdown value for branch: " + branch + "...");
        Dropdownvaluesforbranch.click();
        Log.info("Clicked on dropdown value for branch.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");
    }

    // Method to set 'From Date'
    public void setFromDate(String date) {
    	Log.info("Waiting for visibility of FromDate element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.FromDate));
        Log.info("FromDate element is visible.");

        Log.info("Clicking on FromDate element...");
        FromDate.click();
        Log.info("Clicked on FromDate element.");

        Log.info("Waiting for visibility of date dropdown value: " + date + "...");
        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.DateDropdownvalues(date)));
        Log.info("Date dropdown value for " + date + " is visible.");

        Log.info("Clicking on date dropdown value: " + date + "...");
        DateDropdownvalues.click();
        Log.info("Clicked on date dropdown value.");
    }

    // Method to set 'To Date'
    public void setToDate(String date) {
    	Log.info("Waiting for visibility of ToDate element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.ToDate));
        Log.info("ToDate element is visible.");

        Log.info("Clicking on ToDate element...");
        ToDate.click();
        Log.info("Clicked on ToDate element.");

        Log.info("Waiting for visibility of date dropdown value: " + date + "...");
        WebElement DateDropdownvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.DateDropdownvalues(date)));
        Log.info("Date dropdown value for " + date + " is visible.");

        Log.info("Clicking on date dropdown value: " + date + "...");
        DateDropdownvalues.click();
        Log.info("Clicked on date dropdown value.");
    }

    // Method to click the search button
    public void clickSearch() {
    	Log.info("Waiting for visibility of Search button...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.searchButton));
        Log.info("Search button is visible.");

        Log.info("Clicking on Search button...");
        searchButton.click();
        Log.info("Clicked on Search button.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");
    }

    // Method to get the number of accounts after search
    public String getdetailsfromgrid() {
    	Log.info("Waiting for visibility of all grid elements...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        List<WebElement> gridElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CoreSummaryReportsBankRegularizationSummaryRepo.gridvalues));
        Log.info("Grid elements are visible.");

        Log.info("Limiting to 6 elements and collecting their text...");
        List<String> texts = gridElements.stream()
            .limit(6)
            .map(WebElement::getText)
            .collect(Collectors.toList());
        Log.info("Collected texts from grid elements.");

        Log.info("Joining collected texts with commas...");
        String result = String.join(", ", texts);
        Log.info("Texts joined.");

        Log.info("Returning joined texts from getdetailsfromgrid method.");
        return result;
        
    }
    
 // Method to click on the search button
    public void clickSearchButton() {
    	Log.info("Waiting for visibility of Reset button...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement resetButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.resetButton));
        Log.info("Reset button is visible.");

        Log.info("Clicking on Reset button...");
        resetButton.click();
        Log.info("Clicked on Reset button.");

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");
        
        Log.info("Waiting for visibility of Branch element...");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankAllocationSummaryRepo.Branch));
	    Log.info("Branch element is visible.");
	    
	    Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared.");

        Log.info("Waiting for visibility of Search button...");
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.searchButton));
        Log.info("Search button is visible.");

        Log.info("Clicking on Search button...");
        searchButton.click();
        Log.info("Clicked on Search button.");
    }

    // Method to get the error message text
    public String getErrorMessageText() {
    	Log.info("Waiting for visibility of error message element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.errorMessage));
        Log.info("Error message element is visible.");

        Log.info("Getting the text from the error message element...");
        String errorText = errorMessage.getText();
        Log.info("Error message text retrieved: " + errorText);

        return errorText; 
    }
    
    public String getAccountCategory() {
    	Log.info("Waiting for visibility of Account Category element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement AccountCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.AccountCategory));
        Log.info("Account Category element is visible.");

        Log.info("Getting the text from the Account Category element...");
        String accountCategoryText = AccountCategory.getText();
        Log.info("Account Category text retrieved: " + accountCategoryText);

        return accountCategoryText; 
    }
    
    public String getNoOfAccountsReceived() {
    	Log.info("Waiting for visibility of Accounts Received element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement accountsReceived = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.accountsReceived));
        Log.info("Accounts Received element is visible.");

        Log.info("Getting the text from the Accounts Received element...");
        String accountsReceivedText = accountsReceived.getText();
        Log.info("Accounts Received text retrieved: " + accountsReceivedText);

        return accountsReceivedText;
    }

    public String getTotalOSAmountReceived() {
    	Log.info("Waiting for visibility of Total OS Amounts Received element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement totalOSAmountsReceived = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.totalOSAmountsReceived));
        Log.info("Total OS Amounts Received element is visible.");

        Log.info("Getting the text from the Total OS Amounts Received element...");
        String totalOSAmountText = totalOSAmountsReceived.getText();
        Log.info("Total OS Amounts Received text retrieved: " + totalOSAmountText);

        return totalOSAmountText; 
    }

  public String getNoOfAccountsRegularized() {
	  Log.info("Waiting for visibility of Accounts Regularized element...");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	    WebElement accountsRegularized = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.accountsRegularized));
	    Log.info("Accounts Regularized element is visible.");

	    Log.info("Getting the text from the Accounts Regularized element...");
	    String accountsRegularizedText = accountsRegularized.getText();
	    Log.info("Accounts Regularized text retrieved: " + accountsRegularizedText);

	    return accountsRegularizedText; 
    }

    public String getTotalOSAmountRegularized() {
    	Log.info("Waiting for visibility of Total OS Amount Regularized element...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement totalOSAmountsRegularized = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.totalOSAmountsRegularized));
        Log.info("Total OS Amount Regularized element is visible.");

        Log.info("Getting the text from the Total OS Amount Regularized element...");
        String totalOSAmountRegularizedText = totalOSAmountsRegularized.getText();
        Log.info("Total OS Amount Regularized text retrieved: " + totalOSAmountRegularizedText);

        return totalOSAmountRegularizedText; 
    }
    
    public void clickDownloadButton() {
    	Log.info("Waiting for the visibility of the Download Button...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.downloadButton));
        Log.info("Download Button is visible.");

        Log.info("Clicking the Download Button...");
        downloadButton.click();
        Log.info("Download Button clicked.");

        Log.info("Waiting for the success message to be visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.downloadsuccessmessage));
        Log.info("Download success message is visible.");

        Log.info("Waiting for the spinner to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible.");

        Log.info("Waiting for the success message to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreSummaryReportsBankRegularizationSummaryRepo.downloadsuccessmessage));
        Log.info("Download success message is now invisible.");
    }

    public boolean validateACSummaryWithUI(String uiInitialCategoriesStr, String uiTotalAccountsStr, String uiTotalOutstandingInLakhsStr) throws IOException {
    	Log.info("Extracting AC Summary details from the downloaded Excel...");
        Map<String, Object> excelData = DownloadedExcelReader.extractACSummaryDetails();
        Log.info("Excel data extracted successfully.");

        int excelAccounts = (int) excelData.get("TotalACNumbers");
        double excelOutstanding = (double) excelData.get("TotalOutstandingInLakhs");
        List<String> categories = (List<String>) excelData.get("InitialAccountCategories");
        String excelCategoriesObject = String.join(", ", categories);

        boolean isValid = true;

        Log.info("Parsing UI parameters...");
        int uiTotalAccounts = Integer.parseInt(uiTotalAccountsStr.trim());
        double uiTotalOutstandingInLakhs = Double.parseDouble(uiTotalOutstandingInLakhsStr.trim());

        List<String> uiInitialCategories = Arrays.asList(uiInitialCategoriesStr.split(","));
        Log.info("UI parameters parsed successfully.");

        Log.info("Starting validation logic...");

        if (excelAccounts != uiTotalAccounts) {
            Log.error("Mismatch: expected TotalACNumbers = " + excelAccounts + ", but found " + uiTotalAccounts);
            isValid = false;
        }

        if (Math.abs(excelOutstanding - uiTotalOutstandingInLakhs) > 0.01) { // small tolerance for decimal comparison
            Log.error("Mismatch: expected TotalOutstandingInLakhs = " + excelOutstanding + ", but found " + uiTotalOutstandingInLakhs);
            isValid = false;
        }

        Set<String> excelCategorySet = new HashSet<>(Arrays.asList(excelCategoriesObject.split(",")));
        Set<String> uiCategorySet = new HashSet<>(uiInitialCategories);
        if (!excelCategorySet.equals(uiCategorySet)) {
            Log.error("Mismatch: expected InitialAccountCategories = " + excelCategorySet + ", but found " + uiCategorySet);
            isValid = false;
        }

        if (isValid) {
            Log.info("Validation successful: all data matches.");
        } else {
            Log.warn("Validation failed: some mismatches found.");
        }

        return isValid; 
    }
}
