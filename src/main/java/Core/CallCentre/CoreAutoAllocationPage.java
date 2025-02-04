package Core.CallCentre;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

//import io.netty.handler.timeout.TimeoutException;

public class CoreAutoAllocationPage {
	
	private WebDriver driver;
	public CoreAutoAllocationPage(WebDriver driver) {
		Log.info("Initializing CallCenterAccountFiltrationPage...");
        this.driver = driver;
        Log.info("WebDriver instance assigned.");
        Log.info("Initializing Web elements using PageFactory...");
        PageFactory.initElements(driver, this);
        Log.info("Web elements initialized using PageFactory.");
        Log.info("CallCenterAccountFiltrationPage initialization completed.");
    }
	
	// Custom class to store the result
    public static class ProcedureResult {
        private String accountNo;
        private String mobileNo;
        private String message;

        public ProcedureResult(String accountNo, String mobileNo, String message) {
            this.accountNo = accountNo;
            this.mobileNo = mobileNo;
            this.message = message;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public String getMessage() {
            return message;
        }
    }
	
	 // Method to navigate to Call Centre Main Menu
    public void navigateToMainMenu() {
    	Log.info("Starting navigation to the Call Centre Main Menu...");
    	Log.info("Locating the Call Centre Main Menu element...");
    	WebElement callcenter = driver.findElement(CoreAutoAllocationRepo.callcentermainmenu);
    	Log.info("Call Centre Main Menu element located successfully.");
    	Log.info("Clicking on the Call Centre Main Menu...");
    	callcenter.click();
    	Log.info("Clicked on the Call Centre Main Menu. Navigation complete.");
    }

    // Method to navigate to Account Filtration page
    public void navigateToAccountFiltration() {
    	Log.info("Starting navigation to the Account Filtration submenu...");
    	Log.info("Locating the Account Filtration submenu element...");
    	WebElement accountfiltration = driver.findElement(CoreAutoAllocationRepo.accountfiltrationsubmenu);
    	Log.info("Account Filtration submenu element located successfully.");
    	Log.info("Clicking on the Account Filtration submenu...");
    	accountfiltration.click();
    	 Log.info("Clicked on the Account Filtration submenu. Navigation complete.");
    }
    
 // Method to click the Search button
    public void clickSearchButton() throws InterruptedException {
    	Log.info("Starting the process to click the Search button...");
    	Log.info("Waiting for the Search button to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.searchbutton));
    	Log.info("Scrolling the Search button into view...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", searchbutton); 
        Log.info("Search button scrolled into view."); 
        Log.info("Waiting for 500ms to ensure smooth scrolling...");
        Thread.sleep(500);  // Add a short wait for smooth scrolling
        Log.info("Wait completed.");
        Log.info("Clicking the Search button...");
    	searchbutton.click();
    	Log.info("Search button clicked successfully."); 
    }
    
    public String getResultGridText() {
    	 try {
    	        Log.info("Starting the process to locate the 'No records' message element...");
    	        
    	        // Try to locate the element
    	        List<WebElement> norecordsmsg = driver.findElements(CoreAutoAllocationRepo.norecordsmsg);
    	        
    	        // Log if element is found or not
    	        if (!norecordsmsg.isEmpty()) {
    	            Log.info("'No records' message element found.");
    	            String resultText = norecordsmsg.get(0).getText();
    	            Log.info("Returning the text of the 'No records' message: " + resultText);
    	            return resultText;
    	        } else {
    	            Log.info("'No records' message element not found.");
    	            return "No records message element not found.";
    	        }
    	        
    	    } catch (Exception e) {
    	        // Handle any unexpected errors
    	        Log.error("An unexpected error occurred: " + e.getMessage()+ e);
    	        return "An unexpected error occurred: " + e.getMessage();
    	    }
    }

    // Method to get the text of the warning message
    public String getWarningMessage() {
    	 Log.info("Starting the process to retrieve the warning message...");
    	 Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
    	Log.info("Retrieving the text from the warning message...");
        String message = warningMessage.getText();
        Log.info("Warning message retrieved successfully: " + message); // Log after retrieving the message

        return message;
    }
    
 // Method to select any value in Allocated To field
    public void selectAllocatedTo(String value) {
    	Log.info("Starting the process to select a value from the 'Allocated To' dropdown...");
    	Log.info("Waiting for the Allocated To dropdown to be visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement allocatedtodropdown = driver.findElement(CoreAutoAllocationRepo.allocatedtodropdown);
    	Log.info("Clicking on the Allocated To dropdown...");
    	allocatedtodropdown.click();
    	Log.info("Allocated To dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the value '" + value + "' to be visible...");
    	WebElement allocatedtovalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocatedtovalue(value)));
    	Log.info("Clicking on the value '" + value + "'...");
    	allocatedtovalue.click();
    	Log.info("Value '" + value + "' selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selecting the value.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
 // Method to click the Search button
    public void clickSearchButtonaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to click the Search button after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the Search button to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement searchbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.searchbutton));
    	Log.info("Clicking the Search button...");
    	searchbutton.click();
    	Log.info("Search button clicked successfully.");
    }

    // Method to retrieve the warning message text
    public String getWarningMessageaftergivingvalueforallocatedto() {
    	Log.info("Starting the process to retrieve the warning message after assigning a value to 'Allocated To'...");
    	Log.info("Waiting for the warning message to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
    	Log.info("Retrieving the text from the warning message...");
        String message = warningMessage.getText();
        Log.info("Warning message retrieved successfully: " + message); // Log after retrieving the message

        return message;
    }
    
 // Method to open the asset category dropdown
    public void openAssetCategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the Asset Category dropdown...");
    	 Log.info("Waiting for the Asset Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement assetCategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryDropdown));
    	Log.info("Scrolling the Asset Category dropdown into view...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", assetCategoryDropdown); 
    	Log.info("Asset Category dropdown scrolled into view.");
    	Log.info("Waiting for 1 second for smooth scrolling...");
        Thread.sleep(500);
        Log.info("Wait completed.");
        Log.info("Clicking the Asset Category dropdown...");
        assetCategoryDropdown.click();
        Log.info("Asset Category dropdown clicked.");
        Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	 Log.info("Waiting for the 'All' value in the Asset Category dropdown to become visible...");
    	WebElement assetCategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryvalueall));
    	Log.info("Clicking the 'All' value in the Asset Category dropdown...");
    	assetCategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
 // Method to open the SMA category dropdown
    public void openSMACategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the SMA Category dropdown...");
    	Log.info("Waiting for the SMA Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryDropdown));
    	Log.info("Clicking the SMA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("SMA Category dropdown clicked.");
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the SMA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryvalueall));
    	Log.info("Clicking the 'All' value in the SMA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    
 // Method to open the SMA category dropdown
    public void openNPACategoryDropdown() throws InterruptedException {
    	Log.info("Starting the process to open the NPA Category dropdown...");
    	Log.info("Waiting for the NPA Category dropdown to become visible...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement SMACategoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryDropdown));
    	Log.info("NPA Category dropdown is now visible.");
    	Log.info("Clicking the NPA Category dropdown...");
    	SMACategoryDropdown.click();
    	Log.info("NPA Category dropdown clicked.");
    	 Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible.");
    	Log.info("Waiting for the 'All' value in the NPA Category dropdown to become visible...");
    	WebElement SMACategoryvalueall = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryvalueall));
    	Log.info("'All' value is now visible in the dropdown.");
    	Log.info("Clicking the 'All' value in the NPA Category dropdown...");
    	SMACategoryvalueall.click();
    	Log.info("'All' value selected from the dropdown.");
    	Log.info("Waiting for the spinner to disappear after selecting the 'All' value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after selection.");
    	Log.info("Clicking outside the dropdown to close it...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	outside.click();
    	Log.info("Clicked outside the dropdown to close it.");
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner is no longer visible after closing the dropdown.");
    }
    
 // Method to select operator from the dropdown
    public void selectOutstandingBalanceOperator(String value) {
    	// Log the start of the process
        Log.info("Starting the process to select the Outstanding Balance Operator...");

        // Log before locating and clicking the dropdown for Outstanding Balance Operator
        Log.info("Locating the Outstanding Balance Operator dropdown...");
        WebElement OutstandingBalLimitOperatordropdown = driver.findElement(CoreAutoAllocationRepo.OutstandingBalLimitOperatordropdown);
        Log.info("Clicking the Outstanding Balance Operator dropdown...");
        OutstandingBalLimitOperatordropdown.click();

        // Log before locating and clicking the dropdown value for the selected operator
        Log.info("Locating the dropdown value for operator: " + value);
        WebElement OutstandingBalLimitOperatordropdownvalue = driver.findElement(CoreAutoAllocationRepo.OutstandingBalLimitOperatordropdownvalue(value));
        Log.info("Clicking the dropdown value for the selected operator: " + value);
        OutstandingBalLimitOperatordropdownvalue.click();

        // Log after completing the process
        Log.info("Successfully selected the Outstanding Balance Operator: " + value);
    }
    
    // Method to enter value in the outstanding balance input field
    public void enterOutstandingBalance(String balance) {
    	// Log the start of the process
        Log.info("Starting the process to enter the outstanding balance...");

        // Log before locating the input field for outstanding balance
        Log.info("Locating the input field for Outstanding Balance...");
        WebElement inputField = driver.findElement(CoreAutoAllocationRepo.OutstandingBalLimit);
        
        // Log before clearing the input field
        Log.info("Clearing the existing value in the Outstanding Balance input field...");
        inputField.clear();
        
        // Log before entering the balance value
        Log.info("Entering the balance value: " + balance + " into the Outstanding Balance input field...");
        inputField.sendKeys(balance);

        // Log after completing the process
        Log.info("Successfully entered the outstanding balance.");
    }
    
    
 // Method to select any value in Allocated To field
    public void selectAllocationType(String value) throws InterruptedException {
    	Log.info("Starting the process to select an allocation type...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	Log.info("Locating the Allocation Type dropdown...");
    	WebElement AllocationTypedropdown = driver.findElement(CoreAutoAllocationRepo.allocationtypedropdown);
    	Log.info("Scrolling to the Allocation Type dropdown...");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", AllocationTypedropdown); 
    	Log.info("Waiting for a brief moment before interacting with the dropdown...");
        Thread.sleep(500);
        Log.info("Clicking on the Allocation Type dropdown...");
    	AllocationTypedropdown.click();
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Locating the value in the Allocation Type dropdown: " + value);
    	WebElement AllocationTypevalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocationtypevalue(value)));
    	Log.info("Clicking on the value in the Allocation Type dropdown...");
    	AllocationTypevalue.click();
    	Log.info("Waiting for the spinner to disappear after selecting the value...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Locating the area outside the dropdown...");
    	WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
    	Log.info("Clicking outside to close the dropdown...");
    	outside.click();
    	Log.info("Waiting for the spinner to disappear after clicking outside...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Completed the process to select an allocation type.");
    }
    
    public boolean isAutoAllocationPageLoaded() throws InterruptedException {
    	Log.info("Starting the process to verify if the Auto Allocation page is loaded...");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
    	Log.info("Locating the Call Center menu...");
    	WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);
    	Log.info("Call Center menu located successfully.");
    	Log.info("Clicking on the Call Center menu...");
    	callcentermenu.click();
    	Log.info("Call Center menu clicked successfully.");
    	Log.info("Locating the Auto Allocation submenu...");
    	WebElement autoallocationsubmenu = driver.findElement(CoreAutoAllocationRepo.autoallocationsubmenu);
    	Log.info("Auto Allocation submenu located successfully.");
    	Log.info("Clicking on the Auto Allocation submenu...");
    	autoallocationsubmenu.click();
    	Log.info("Auto Allocation submenu clicked successfully.");
    	Log.info("Pausing for a brief moment...");
    	Thread.sleep(1000);
    	Log.info("Waiting for the spinner to disappear...");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Log.info("Spinner disappeared successfully.");
    	Log.info("Checking if the current URL ends with 'CallCentre/AutoAllocationConfiguration'...");
        boolean isPageLoaded = driver.getCurrentUrl().endsWith("CallCentre/AutoAllocationConfiguration");

        if (isPageLoaded) {
            Log.info("Auto Allocation page loaded successfully.");
        } else {
            Log.warn("Failed to load the Auto Allocation page.");
        }

        Log.info("Process to verify Auto Allocation page load completed.");
        return isPageLoaded;
    }
    public boolean areFieldsAndButtonsPresent() {
    	 Log.info("Starting the process to verify the presence of all required fields and buttons on the page...");

    	    try {
    	        Log.info("Locating the Allocation Name field...");
    	        WebElement allocationName = driver.findElement(CoreAutoAllocationRepo.allocationName);
    	        Log.info("Allocation Name field located successfully.");

    	        Log.info("Locating the Effective Date field...");
    	        WebElement effectDate = driver.findElement(CoreAutoAllocationRepo.effectDate);
    	        Log.info("Effective Date field located successfully.");

    	        Log.info("Locating the Asset Category field...");
    	        WebElement assetCategory = driver.findElement(CoreAutoAllocationRepo.assetCategory);
    	        Log.info("Asset Category field located successfully.");

    	        Log.info("Locating the SMA Category field...");
    	        WebElement smaCategory = driver.findElement(CoreAutoAllocationRepo.smaCategory);
    	        Log.info("SMA Category field located successfully.");

    	        Log.info("Locating the NPA Category field...");
    	        WebElement npaCategory = driver.findElement(CoreAutoAllocationRepo.npaCategory);
    	        Log.info("NPA Category field located successfully.");

    	        Log.info("Locating the Zone field...");
    	        WebElement zone = driver.findElement(CoreAutoAllocationRepo.zone);
    	        Log.info("Zone field located successfully.");

    	        Log.info("Locating the Vertical field...");
    	        WebElement vertical = driver.findElement(CoreAutoAllocationRepo.vertical);
    	        Log.info("Vertical field located successfully.");

    	        Log.info("Locating the Scheme Type field...");
    	        WebElement schemeType = driver.findElement(CoreAutoAllocationRepo.schemeType);
    	        Log.info("Scheme Type field located successfully.");

    	        Log.info("Locating the Product Type field...");
    	        WebElement productType = driver.findElement(CoreAutoAllocationRepo.productType);
    	        Log.info("Product Type field located successfully.");

    	        Log.info("Locating the Scheme Code field...");
    	        WebElement schemeCode = driver.findElement(CoreAutoAllocationRepo.schemeCode);
    	        Log.info("Scheme Code field located successfully.");

    	        Log.info("Locating the Asset Tagging Type field...");
    	        WebElement assetTaggingType = driver.findElement(CoreAutoAllocationRepo.assetTaggingType);
    	        Log.info("Asset Tagging Type field located successfully.");

    	        Log.info("Locating the Outstanding Balance field...");
    	        WebElement outstandingBalance = driver.findElement(CoreAutoAllocationRepo.outstandingBalance);
    	        Log.info("Outstanding Balance field located successfully.");

    	        Log.info("Locating the Outstanding Balance value field...");
    	        WebElement outstandingBalancevalue = driver.findElement(CoreAutoAllocationRepo.outstandingBalancevalue);
    	        Log.info("Outstanding Balance value field located successfully.");

    	        Log.info("Locating the Total Overdue field...");
    	        WebElement totalOverdue = driver.findElement(CoreAutoAllocationRepo.totalOverdue);
    	        Log.info("Total Overdue field located successfully.");

    	        Log.info("Locating the Total Overdue value field...");
    	        WebElement totalOverduevalue = driver.findElement(CoreAutoAllocationRepo.totalOverduevalue);
    	        Log.info("Total Overdue value field located successfully.");

    	        Log.info("Locating the DPD field...");
    	        WebElement dpd = driver.findElement(CoreAutoAllocationRepo.dpd);
    	        Log.info("DPD field located successfully.");

    	        Log.info("Locating the DPD value field...");
    	        WebElement dpdvalue = driver.findElement(CoreAutoAllocationRepo.dpdvalue);
    	        Log.info("DPD value field located successfully.");

    	        Log.info("Locating the Percent Overdue To EMI field...");
    	        WebElement percentOverdueToEMI = driver.findElement(CoreAutoAllocationRepo.percentOverdueToEMI);
    	        Log.info("Percent Overdue To EMI field located successfully.");

    	        Log.info("Locating the Percent Overdue To EMI value field...");
    	        WebElement percentOverdueToEMIvalue = driver.findElement(CoreAutoAllocationRepo.percentOverdueToEMIvalue);
    	        Log.info("Percent Overdue To EMI value field located successfully.");

    	        Log.info("Locating the Corporate BCBF field...");
    	        WebElement corporateBCBF = driver.findElement(CoreAutoAllocationRepo.corporateBCBF);
    	        Log.info("Corporate BCBF field located successfully.");

    	        Log.info("Locating the Processing Interval field...");
    	        WebElement processingInterval = driver.findElement(CoreAutoAllocationRepo.processingInterval);
    	        Log.info("Processing Interval field located successfully.");

    	        Log.info("Locating the Expiry Date field...");
    	        WebElement expiryDate = driver.findElement(CoreAutoAllocationRepo.expiryDate);
    	        Log.info("Expiry Date field located successfully.");

    	        Log.info("Locating the To field...");
    	        WebElement to = driver.findElement(CoreAutoAllocationRepo.to);
    	        Log.info("To field located successfully.");

    	        Log.info("Locating the Add button...");
    	        WebElement addbutton = driver.findElement(CoreAutoAllocationRepo.addbutton);
    	        Log.info("Add button located successfully.");

    	        Log.info("Locating the Reset button...");
    	        WebElement resetbutton = driver.findElement(CoreAutoAllocationRepo.resetbutton);
    	        Log.info("Reset button located successfully.");

    	        Log.info("Locating the Edit button...");
    	        WebElement editbutton = driver.findElement(CoreAutoAllocationRepo.editbutton);
    	        Log.info("Edit button located successfully.");

    	        Log.info("Locating the Activate/Deactivate button...");
    	        WebElement activate_deactivatebutton = driver.findElement(CoreAutoAllocationRepo.activate_deactivatebutton);
    	        Log.info("Activate/Deactivate button located successfully.");

    	        Log.info("Verifying if all fields and buttons are displayed...");
    	        boolean areAllElementsDisplayed = 
    	                allocationName.isDisplayed() &&
    	                effectDate.isDisplayed() &&
    	                assetCategory.isDisplayed() &&
    	                smaCategory.isDisplayed() &&
    	                npaCategory.isDisplayed() &&
    	                zone.isDisplayed() &&
    	                vertical.isDisplayed() &&
    	                schemeType.isDisplayed() &&
    	                productType.isDisplayed() &&
    	                schemeCode.isDisplayed() &&
    	                assetTaggingType.isDisplayed() &&
    	                outstandingBalance.isDisplayed() &&
    	                outstandingBalancevalue.isDisplayed() &&
    	                totalOverdue.isDisplayed() &&
    	                totalOverduevalue.isDisplayed() &&
    	                dpd.isDisplayed() &&
    	                dpdvalue.isDisplayed() &&
    	                percentOverdueToEMI.isDisplayed() &&
    	                percentOverdueToEMIvalue.isDisplayed() &&
    	                corporateBCBF.isDisplayed() &&
    	                processingInterval.isDisplayed() &&
    	                expiryDate.isDisplayed() &&
    	                to.isDisplayed() &&
    	                addbutton.isDisplayed() &&
    	                resetbutton.isDisplayed() &&
    	                editbutton.isDisplayed() &&
    	                activate_deactivatebutton.isDisplayed();

    	        if (areAllElementsDisplayed) {
    	            Log.info("All required fields and buttons are present.");
    	        } else {
    	            Log.warn("One or more required fields or buttons are missing.");
    	        }

    	        Log.info("Completed the verification process for fields and buttons.");
    	        return areAllElementsDisplayed;

    	    } catch (Exception e) {
    	    	Log.error("An error occurred while verifying fields and buttons: " + e.getMessage()+ e);
    	        return false;
    	    }
    }
 // Method to enter Allocation Name
    public void enterAllocationName(String name) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        Log.info("Waiting for the 'All' value in the Zone dropdown to become visible.");
        
        try {
            // Wait for the 'All' value in the Zone dropdown to be present
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(CoreAutoAllocationRepo.zone));
            
            String expectedText = "All";
            try {
                // Wait for the expected text to be present in the element
                wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            } catch (TimeoutException e) {
                // Throw a new TimeoutException if the expected text is not found within the wait time
                throw new TimeoutException("The 'All' value in the 'Zone' dropdown is not present within the expected time. So Page not Loaded properly", e);
            }

            Log.info("The 'All' value in the Zone dropdown is now visible.");
            
        } catch (TimeoutException e) {
            Log.error("Timeout: The 'All' value in the Zone dropdown did not become visible after waiting for 180 seconds. Page load check failed. " + e);
            throw e; // Fail the test with the timeout exception
        } catch (Exception e) {
            Log.error("An unexpected error occurred while waiting for the 'All' value in the Zone dropdown: " + e.getMessage()+ e);
            throw e; // Re-throw for higher-level handling
        }

    	Log.info("Starting the process to enter the Allocation Name...");

        try {
            Log.info("Locating the Allocation Name field...");
            WebElement allocationName = driver.findElement(CoreAutoAllocationRepo.allocationName);
            Log.info("Allocation Name field located successfully.");

            Log.info("Entering the value '" + name + "' into the Allocation Name field...");
            allocationName.sendKeys(name);
            Log.info("Value '" + name + "' entered into the Allocation Name field successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while entering the Allocation Name: " + e.getMessage()+ e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }

        Log.info("Process to enter Allocation Name completed successfully.");
    }

    // Method to click Add button
    public void clickAddButton() {
    	 Log.info("Starting the process to click the Add button...");

    	    try {
    	        Log.info("Locating the Add button...");
    	        WebElement addbutton = driver.findElement(CoreAutoAllocationRepo.addbutton);
    	        Log.info("Add button located successfully.");

    	        Log.info("Clicking the Add button...");
    	        addbutton.click();
    	        Log.info("Add button clicked successfully.");

    	    } catch (Exception e) {
    	        Log.error("An error occurred while clicking the Add button: " + e.getMessage() + e);
    	        throw e; 
    	    }

    	    Log.info("Process to click the Add button completed successfully.");
    }
    
 // Method to click on Effect Date field
    public void clickEffectDateField() {
    	Log.info("Starting the process to click the Effect Date field...");

        try {
            Log.info("Locating the Effect Date field...");
            WebElement effectDate = driver.findElement(CoreAutoAllocationRepo.effectDate);
            Log.info("Effect Date field located successfully.");

            Log.info("Clicking the Effect Date field...");
            effectDate.click();
            Log.info("Effect Date field clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while clicking the Effect Date field: " + e.getMessage() + e);
            throw e; 
        }

        Log.info("Process to click the Effect Date field completed successfully.");
    }
    
    public void selectDateFromDatePicker(String date) {
    	Log.info("Starting the process to select the date from the date picker...");

        try {
            Log.info("Locating the date value '" + date + "' in the date picker...");
            WebElement effectDatevalue = driver.findElement(CoreAutoAllocationRepo.effectDatevalue(date));
            Log.info("Date value '" + date + "' located successfully in the date picker.");

            Log.info("Selecting the date '" + date + "' from the date picker...");
            effectDatevalue.click();
            Log.info("Date '" + date + "' selected successfully from the date picker.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting the date '" + date + "' from the date picker: " + e.getMessage() + e);
            throw e;
        }

        Log.info("Process to select the date from the date picker completed successfully.");
    }
    
 // Method to get the value from Effect Date field
    public String getEffectDateFieldValue() {
    	Log.info("Starting the process to retrieve the value from the Effect Date field...");

        try {
            Log.info("Locating the Effect Date field...");
            WebElement effectDate = driver.findElement(CoreAutoAllocationRepo.effectDate);
            Log.info("Effect Date field located successfully.");

            Log.info("Retrieving the value from the Effect Date field...");
            String value = effectDate.getAttribute("value");
            Log.info("Retrieved value from the Effect Date field: " + value);

            return value;

        } catch (Exception e) {
            Log.error("An error occurred while retrieving the value from the Effect Date field: " + e.getMessage() + e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }
    }
    
    // Method to verify date format
    public boolean isDateFormatDDMMYYY(String date) {
    	Log.info("Starting the process to verify if the date format is 'dd-MM-yyyy' for the date: " + date);

        try {
            Log.info("Attempting to parse the date '" + date + "' using the 'dd-MM-yyyy' format...");
            new SimpleDateFormat("dd-MM-yyyy").parse(date);
            Log.info("Date '" + date + "' matches the 'dd-MM-yyyy' format.");
            return true;
        } catch (Exception e) {
            Log.error("An error occurred while verifying the date format for '" + date + "': " + e.getMessage() + e);
            return false;
        }
    }
    
    // Method to select 'All' in Asset Category Dropdown
    public void selectAllInAssetCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in Asset Category...");

        try {
        	Thread.sleep(10000);
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Locating the Asset Category field...");
            WebElement assetCategory = driver.findElement(CoreAutoAllocationRepo.assetCategory); 
            Log.info("Asset Category field located successfully.");

            Log.info("Clicking on the Asset Category field...");
            assetCategory.click();
            Log.info("Asset Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(10000);

            Log.info("Locating the 'Select All' option in Asset Category...");
            WebElement assetCategoryselectall = driver.findElement(CoreAutoAllocationRepo.assetCategoryselectall);
            Log.info("'Select All' option in Asset Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            assetCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(10000);

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CoreAutoAllocationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in Asset Category: " + e.getMessage()+ e);
            throw e; 
        }

        Log.info("Process to select all in Asset Category completed successfully.");
    }

    // Method to select 'All' in SMA Category Dropdown
    public void selectAllInSmaCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in SMA Category...");

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the SMA Category field...");
            WebElement smaCategory = driver.findElement(CoreAutoAllocationRepo.smaCategory);
            Log.info("SMA Category field located successfully.");

            Log.info("Clicking on the SMA Category field...");
            smaCategory.click();
            Log.info("SMA Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the 'Select All' option in SMA Category...");
            WebElement smaCategoryselectall = driver.findElement(CoreAutoAllocationRepo.smaCategoryselectall);
            Log.info("'Select All' option in SMA Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            smaCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CoreAutoAllocationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in SMA Category: " + e.getMessage()+ e);
            throw e; 
        }

        Log.info("Process to select all in SMA Category completed successfully.");
    }

    // Method to select 'All' in NPA Category Dropdown
    public void selectAllInNpaCategory() throws InterruptedException {
    	Log.info("Starting the process to select all in NPA Category...");

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the NPA Category field...");
            WebElement npaCategory = driver.findElement(CoreAutoAllocationRepo.npaCategory);
            Log.info("NPA Category field located successfully.");

            Log.info("Clicking on the NPA Category field...");
            npaCategory.click();
            Log.info("NPA Category clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the 'Select All' option in NPA Category...");
            WebElement npaCategoryselectall = driver.findElement(CoreAutoAllocationRepo.npaCategoryselectall);
            Log.info("'Select All' option in NPA Category located successfully.");

            Log.info("Clicking the 'Select All' option...");
            npaCategoryselectall.click();
            Log.info("'Select All' option clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the outside area element...");
            WebElement outarea2 = driver.findElement(CoreAutoAllocationRepo.outarea2);
            Log.info("Outside area element located successfully.");

            Log.info("Clicking the outside area element...");
            outarea2.click();
            Log.info("Outside area clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while selecting all in NPA Category: " + e.getMessage()+ e);
            throw e; 
        }

        Log.info("Process to select all in NPA Category completed successfully.");
    }
    
 // Method to select Mumbai from Zone dropdown
    public void selectZone(String zoneName) throws InterruptedException {
    	Log.info("Starting the process to select zone: " + zoneName);

        try {
            Log.info("Waiting for spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            Log.info("Locating the Zone field...");
            WebElement zone = driver.findElement(CoreAutoAllocationRepo.zone);
            Log.info("Zone field located successfully.");

            Log.info("Clicking on the Zone field...");
            zone.click();
            Log.info("Zone field clicked successfully.");

            Log.info("Waiting for spinner to be invisible again...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Thread.sleep(5000);

            Log.info("Locating the Zone value field for zone name: " + zoneName);
            WebElement zonevalue = driver.findElement(CoreAutoAllocationRepo.zonevalue(zoneName));
            Log.info("Zone value for '" + zoneName + "' located successfully.");

            Log.info("Clicking on the Zone value...");
            zonevalue.click();
            Log.info("Zone value clicked successfully.");

            Log.info("Waiting for spinner to be invisible once more...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        } catch (Exception e) {
        	Log.error("An error occurred while selecting the zone: " + zoneName+ e);
            throw e; // Re-throwing the exception for higher-level handling, if needed.
        }

        Log.info("Process to select zone '" + zoneName + "' completed successfully.");
    }
    
 // Method to select Daily from Processing Interval dropdown
    public void selectProcessingInterval(String interval) throws InterruptedException {
    	Log.info("Starting the process to select the processing interval...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        // Locating the processing interval element
        Log.info("Locating the processing interval element...");
        WebElement processingInterval = driver.findElement(CoreAutoAllocationRepo.processingInterval);
        Log.info("Processing interval element located successfully.");
        
        // Waiting for spinner to be invisible
        Log.info("Waiting for spinner to be invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible.");
        
        // Clicking the processing interval element
        Log.info("Clicking the processing interval element...");
        processingInterval.click();
        Log.info("Processing interval element clicked successfully.");
        
        // Waiting for spinner to be invisible again
        Log.info("Waiting for spinner to be invisible after clicking...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible after click.");
        
        // Sleeping for a brief moment (this may not be necessary if the spinner visibility is sufficient)
        Thread.sleep(5000);
        
        // Locating the processing interval value based on the provided interval
        Log.info("Locating the processing interval value for: " + interval);
        WebElement processingIntervalvalue = driver.findElement(CoreAutoAllocationRepo.processingIntervalvalue(interval));
        Log.info("Processing interval value for '" + interval + "' located successfully.");
        
        // Clicking the selected processing interval value
        Log.info("Clicking the selected processing interval value...");
        processingIntervalvalue.click();
        Log.info("Processing interval value clicked successfully.");
        
        // Waiting for spinner to be invisible after click
        Log.info("Waiting for spinner to be invisible after selecting the processing interval...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is invisible after selecting the processing interval.");
        
        Log.info("Processing interval selection process completed successfully.");
    }
    
 // Method to select Call Centre from To dropdown 
    public void selectTo(String toOption) throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    // Log before locating the 'To' element
    	    Log.info("Locating the 'To' field...");

    	    WebElement to = driver.findElement(CoreAutoAllocationRepo.to);

    	    // Log after locating the 'To' element
    	    Log.info("'To' field located successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log before clicking the 'To' field
    	    Log.info("Clicking on the 'To' field...");

    	    to.click();

    	    // Log after clicking the 'To' field
    	    Log.info("'To' field clicked successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Adding a delay
    	    Thread.sleep(5000);

    	    // Log before locating the 'To' value element
    	    Log.info("Locating the 'To' value field for option: " + toOption);

    	    WebElement tovalue = driver.findElement(CoreAutoAllocationRepo.tovalue(toOption));

    	    // Log after locating the 'To' value element
    	    Log.info("'To' value for located successfully." + toOption);
    	    

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    // Log before clicking the 'To' value field
    	    Log.info("Clicking on the 'To' value...");

    	    tovalue.click();

    	    // Log after clicking the 'To' value field
    	    Log.info("'To' value clicked successfully.");

    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

    	    Log.info("Process to select 'To' option '{}' completed successfully." +toOption);
    }
    
 // Method to click the add button
    public void clickAddButtonaftergivingrequiredvalues() {
    	 Log.info("Starting the process to click the Add button after providing required values...");

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

    	    Log.info("Locating the Add button...");
    	    WebElement addbutton = driver.findElement(CoreAutoAllocationRepo.addbutton);
    	    Log.info("Add button located successfully.");

    	    Log.info("Locating the first row in the grid...");
    	    WebElement firstrowingrid = driver.findElement(CoreAutoAllocationRepo.firstrowingrid);
    	    Log.info("First row in the grid located successfully.");

    	    Log.info("Clicking on the Add button...");
    	    addbutton.click();
    	    Log.info("Add button clicked successfully.");

    	    Log.info("Waiting for spinner to become invisible...");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Spinner is now invisible.");

    	    Log.info("Scrolling to the first row in the grid...");
    	    JavascriptExecutor js = (JavascriptExecutor) driver;
    	    js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", firstrowingrid);
    	    Log.info("Scrolled to the first row in the grid.");

    	    Log.info("Process to click Add button and scroll completed successfully.");
    }
    
    public boolean allocationNameInGrid(String text) {
    	Log.info("Starting the process to check for the visibility of allocation name in the grid with text: {}" +text);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the spinner to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        try {
            Log.info("Waiting for the visibility of grid element with text: {}" +text);
            // Wait for the visibility of the grid element with the given text
            WebElement allocationName = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.gridvalues(text)));
            Log.info("Element with text '{}' is visible." +text);

            // If the element is visible, return true
            return allocationName.isDisplayed();
        } catch (TimeoutException e) {
            // Log or handle the timeout scenario as needed
            Log.error("Element with the text '{}' is not visible within the timeout period." +text);
            return false;
        }
    }
    
    public boolean zoneNameInGrid(String text) {
    	 Log.info("Starting the process to check for the visibility of zone name in the grid with text: {}" +text);

    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

    	    Log.info("Waiting for the spinner to become invisible...");
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	    Log.info("Spinner is now invisible.");

    	    try {
    	        Log.info("Waiting for the visibility of grid element with text: {}" +text);
    	        // Wait for the visibility of the grid element with the given text
    	        WebElement zoneName = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.gridvalues(text)));
    	        Log.info("Element with text '{}' is visible." +text);

    	        // If the element is visible, return true
    	        return zoneName.isDisplayed();
    	    } catch (TimeoutException e) {
    	        // Log or handle the timeout scenario as needed
    	        Log.error("Element with the text '{}' is not visible within the timeout period." +text);
    	        return false;
    	    }
    }
    
    // Method to check if status is Active (green tick)
    public boolean isStatusActive() throws InterruptedException {
    	Log.info("Starting the process to check if the status is active.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Waiting for the spinner to become invisible...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is now invisible.");

        Log.info("Locating the status icon...");
        WebElement statusIcon = driver.findElement(CoreAutoAllocationRepo.statusColumnactive);

        Log.info("Scrolling the status icon into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", statusIcon);

        // Optional: You can log any additional information like style attribute
        // String style = statusIcon.getAttribute("style");
        // Log.info("Status icon style attribute: {}", style);
        
        Thread.sleep(1000);
        Log.info("Checking the status icon's style to determine if it's active...");
        
        boolean isActive = statusIcon.getAttribute("style").contains("color: green");
        
        if (isActive) {
            Log.info("Status is active (green color).");
        } else {
            Log.info("Status is not active.");
        }

        return isActive;
    }
    
 // Method to select an allocation name
    public void selectAllocationName() throws InterruptedException {
    	Log.info("Starting the process to select allocation name.");

        // Locate the allocation name checkbox
        Log.info("Locating the allocation name checkbox...");
        WebElement allocationNameCheckbox = driver.findElement(CoreAutoAllocationRepo.allocationNameCheckbox);

        // Scroll the checkbox into view
        Log.info("Scrolling the allocation name checkbox into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", allocationNameCheckbox);

        // Optional: You can add log to indicate the checkbox is in view now.
        Log.info("Allocation name checkbox is now in view.");

        // Sleep to allow UI adjustments
        Log.info("Waiting for 1 second before clicking...");
        Thread.sleep(1000);

        // Click the checkbox
        Log.info("Clicking the allocation name checkbox...");
        allocationNameCheckbox.click();

        Log.info("Allocation name checkbox clicked successfully.");
    }
    
    // Method to verify Deactivate button is enabled
    public boolean isDeactivateButtonEnabled() {
    	Log.info("Starting the process to check if the deactivate button is enabled.");

        // Locate the deactivate button
        Log.info("Locating the deactivate button...");
        WebElement deactivateButton = driver.findElement(CoreAutoAllocationRepo.deactivateButton);

        // Check if the button is enabled
        Log.info("Checking if the deactivate button is enabled...");
        boolean isEnabled = deactivateButton.isEnabled();

        // Log the result
        if (isEnabled) {
            Log.info("Deactivate button is enabled.");
        } else {
            Log.info("Deactivate button is disabled.");
        }

        return isEnabled;
    }
    
    // Method to click Deactivate button
    public void clickDeactivateButton() {
    	Log.info("Starting the process to click the deactivate button.");

        try {
            // Locate the deactivate button
            Log.info("Locating the deactivate button...");
            WebElement deactivateButton = driver.findElement(CoreAutoAllocationRepo.deactivateButton);
            
            // Click the deactivate button
            Log.info("Clicking the deactivate button...");
            deactivateButton.click();
            Log.info("Deactivate button clicked successfully.");

        } catch (Exception e) {
            Log.error("An error occurred while clicking the deactivate button."+ e);
            throw e; // Rethrow the exception for further handling if needed
        }

        Log.info("Process to click the deactivate button completed.");
    }
 // Method to get validation message text
    public String getValidationMessage() {
    	Log.info("Starting the process to get the validation message.");

        try {
            // Wait for the validation message element to be visible
            Log.info("Waiting for the validation message to be visible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.validationMessage));
            Log.info("Validation message element is now visible.");

            // Get the text of the validation message
            Log.info("Retrieving the validation message text...");
            String message = validationMessage.getText();
            Log.info("Validation message retrieved: {}" +message);
            Log.info("Process to get validation message completed.");
            return message;

        } catch (Exception e) {
            Log.error("An error occurred while retrieving the validation message."+ e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }
    
    // Method to check if status is Active (green tick)
    public boolean isStatusInActive() throws InterruptedException {
    	Log.info("Starting the process to check if the status is inactive.");

        try {
            // Waiting for the validation message and spinner to be invisible
            Log.info("Waiting for the validation message and spinner to be invisible...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreAutoAllocationRepo.validationMessage));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            Log.info("Validation message and spinner are now invisible.");

            // Locate the status icon
            Log.info("Locating the status icon for inactive status...");
            WebElement statusIcon = driver.findElement(CoreAutoAllocationRepo.statusColumninactive);
            Log.info("Status icon located successfully.");

            // Scroll into view
            Log.info("Scrolling the status icon into view...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", statusIcon);
            Log.info("Status icon scrolled into view successfully.");

            // Wait for a moment for the UI to update
            Log.info("Waiting for 1 second to allow UI updates...");
            Thread.sleep(1000);

            // Check if the status is inactive (color: red)
            Log.info("Checking if the status is inactive (color: red)...");
            boolean isInactive = statusIcon.getAttribute("style").contains("color: red");
            Log.info("Status inactive check completed. Result: {}" +isInactive);

            return isInactive;
        } catch (Exception e) {
            Log.error("An error occurred while checking if the status is inactive."+ e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }
    
 // Method to verify Activate button is enabled
    public boolean isActivateButtonEnabled() {
    	Log.info("Starting the process to check if the 'Activate' button is enabled.");

        try {
            // Locate the activate button
            Log.info("Locating the 'Activate' button...");
            WebElement activateButton = driver.findElement(CoreAutoAllocationRepo.activateButton);
            Log.info("'Activate' button located successfully.");

            // Check if the activate button is enabled
            Log.info("Checking if the 'Activate' button is enabled...");
            boolean isEnabled = activateButton.isEnabled();
            Log.info("'Activate' button enabled status: {}" +isEnabled);

            return isEnabled;
        } catch (Exception e) {
            Log.error("An error occurred while checking if the 'Activate' button is enabled."+ e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }
    }

    // Method to click Activate button
    public void clickActivateButton() {
    	Log.info("Starting the process to click the 'Activate' button.");

        try {
            // Locate the 'Activate' button
            Log.info("Locating the 'Activate' button...");
            WebElement activateButton = driver.findElement(CoreAutoAllocationRepo.activateButton);
            Log.info("'Activate' button located successfully.");

            // Click on the 'Activate' button
            Log.info("Clicking on the 'Activate' button...");
            activateButton.click();
            Log.info("'Activate' button clicked successfully.");
            
        } catch (Exception e) {
            Log.error("An error occurred while clicking the 'Activate' button."+ e);
            throw e; // Rethrow the exception for higher-level handling, if needed
        }

        Log.info("Process to click the 'Activate' button completed successfully.");
    }
    
 // Method to get the total accounts count from the grid
    public String getTotalAccountsCount() {
    	Log.info("Starting the process to get the total accounts count...");
    	Log.info("Locating the element that contains the total accounts count...");
    	WebElement accontsselected = driver.findElement(CoreAutoAllocationRepo.totalaccountselectedingrid);
    	String totalAccountsCount = accontsselected.getText();
        Log.info("Successfully retrieved the total accounts count: " + totalAccountsCount);

        return totalAccountsCount;
    }

    // Method to get the total outstanding amount from the grid
    public String getTotalOutstandingAmount() {
    	Log.info("Starting the process to get the total outstanding amount...");
    	Log.info("Locating the element that contains the total outstanding amount...");
    	WebElement outstandingamount = driver.findElement(CoreAutoAllocationRepo.totaloutstandingamountingrid);
    	String totalOutstandingAmount = outstandingamount.getText();
        Log.info("Successfully retrieved the total outstanding amount: " + totalOutstandingAmount);

        return totalOutstandingAmount;
    }
    
 // Method to click on the Download File dropdown
    public void clickDownloadDropdown() {
    	Log.info("Starting the process to click the Download Dropdown...");
    	Log.info("Locating the Download Dropdown element...");
    	WebElement downloadfiledropdown = driver.findElement(CoreAutoAllocationRepo.downloadfiledropdown);
    	 // Log before clicking the element
        Log.info("Clicking the Download Dropdown...");
        downloadfiledropdown.click();
        
        // Log after clicking the element
        Log.info("Successfully clicked the Download Dropdown.");
    }

    // Method to select the List of Accounts option
    public void selectListOfAccounts(String value) {
    	 // Log the start of the process
        Log.info("Starting the process to select a list of accounts with value: " + value);

        // Log before finding the dropdown value element
        Log.info("Locating the dropdown value element for: " + value);
        WebElement downloadfiledropdownvalue = driver.findElement(CoreAutoAllocationRepo.downloadfiledropdownvalue(value));
        
        // Log before clicking the dropdown value
        Log.info("Clicking the dropdown value: " + value);
        downloadfiledropdownvalue.click();
        
        // Log before finding the outside area element
        Log.info("Locating the outside area element to click...");
        WebElement outside = driver.findElement(CoreAutoAllocationRepo.outarea);
        
        // Log before clicking the outside area
        Log.info("Clicking the outside area to close the dropdown...");
        outside.click();

        // Log after completing the action
        Log.info("Successfully selected the list of accounts and closed the dropdown.");
    }

    // Method to click the Download button
    public void clickDownloadButton() {
    	   // Log the start of the process
        Log.info("Starting the process to click the Download Button...");

        // Log before locating the download button
        Log.info("Locating the Download Button...");
        WebElement downloadbutton = driver.findElement(CoreAutoAllocationRepo.downloadbutton);
        
        // Log before clicking the download button
        Log.info("Clicking the Download Button...");
        downloadbutton.click();
        
        // Log before waiting for the spinner to disappear
        Log.info("Waiting for the spinner to disappear...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofDays(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Log after the spinner disappears
        Log.info("Successfully waited for the spinner to disappear. Download Button click process completed.");
    }
    
 // Method to verify accounts allocation
    public Map<String, String> verifyAccountsAllocation(String expectedData) {
        Log.info("Starting the process to verify account allocation...");

        Map<String, String> resultMap = new HashMap<>();
        try {
            // Log before reading the last Sr No. from the downloaded file
            Log.info("Getting the last Sr No. from the latest downloaded file...");
            int lastSrNo = DownloadedExcelReader.getCountOfRows();

            // Convert the integer to a String to match expectedData type
            String actualData = String.valueOf(lastSrNo);

            // Log before comparing the expectedData with actualData
            Log.info("Comparing expectedData: " + expectedData + " with actualData: " + actualData);

            // Add expected and actual data to the map
            resultMap.put("expectedData", expectedData);
            resultMap.put("actualData", actualData);

            // Compare expectedData with actualData and log the result
            if (expectedData.equals(actualData)) {
                Log.info("Data match successful: Expected data matches the actual data.");
                resultMap.put("result", "PASS");
            } else {
                Log.info("Data mismatch: Expected data does not match the actual data.");
                resultMap.put("result", "FAIL");
            }

        } catch (IOException e) {
            // Log the error if an exception occurs
            Log.error("Error while reading the Excel file: " + e.getMessage()+ e);
            resultMap.put("result", "ERROR");
            resultMap.put("errorMessage", e.getMessage());
        }

        return resultMap;
    }
    
 // Method to execute the stored procedure
    public ProcedureResult executeStoredProcedure(String mobileNumber, String OSBAL) throws IOException {
        Connection con = null;
        CallableStatement callableStatement = null;
        String accountNo = null;
        String updatedMobileNo = null;
        String resultMessage = "";

        try {
            // Get the database connection
            con = Base_Class.OracleDBConnection();

            // Prepare the stored procedure call
            String procedureCall = "{call SP_CHECK_INVALID_MOBILE_ACCOUNT_FILTERATION(?, ?, ?, ?)}";
            callableStatement = con.prepareCall(procedureCall);

            // Set the input parameter
            callableStatement.setString(1, mobileNumber);
            callableStatement.setString(2, OSBAL);

            // Register the output parameters
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR); // o_account_no
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR); // o_mob_no

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the output parameters
            accountNo = callableStatement.getString(3);
            updatedMobileNo = callableStatement.getString(4);

            if (accountNo != null && updatedMobileNo != null) {
                resultMessage = "Procedure executed successfully.";
            } else {
                resultMessage = "No data found for the provided mobile number.";
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            resultMessage = "SQL Exception occurred: " + e.getMessage();
        } finally {
            // Close resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                resultMessage = "Error while closing resources: " + e.getMessage();
            }
        }

        // Return results encapsulated in the custom object
        return new ProcedureResult(accountNo, updatedMobileNo, resultMessage);
    }

    public DataSummary verifyInvalidMobileNumberAccounts() {
    	Log.info("Starting the process to verify invalid mobile number accounts.");

        try {
            // Log before calling the method from DownloadedExcelReader class
            Log.info("Calling DownloadedExcelReader.getCountAndAccountNumbers() to fetch account numbers and count.");
            
            // Call the method from DownloadedExcelReader class
            DataSummary dataSummary = DownloadedExcelReader.getCountAndAccountNumbers();

            // Check if dataSummary is null or contains no valid data
            if (dataSummary == null || dataSummary.getRowCount() == 0 || dataSummary.getAccountNumbers().isEmpty()) {
                throw new Exception("DataSummary is empty or invalid, test case failed.");
            }

            // Log after the method call
            Log.info("Data retrieved successfully from Excel: " + dataSummary.getRowCount() + " rows, " 
                      + dataSummary.getAccountNumbers().size() + " account numbers.");

            // Returning the dataSummary object if valid
            return dataSummary;

        } catch (Exception e) {
            Log.error("Error occurred: " + e.getMessage());
            // Handle the exception appropriately, depending on your needs
            return null;
        }
    }
    
    // Method to click on Get Download History button
    public void clickGetDownloadHistory() {
    	 Log.info("Attempting to find the 'Get Download History' button.");

    	    try {
    	        // Find the WebElement and click it
    	        WebElement getdownloadhistorybutton = driver.findElement(CoreAutoAllocationRepo.getdownloadhistorybutton);
    	        
    	        // Log after the button is found but before clicking
    	        Log.info("Found the 'Get Download History' button.");

    	        // Click the button
    	        getdownloadhistorybutton.click();

    	        // Log after the button is clicked
    	        Log.info("'Get Download History' button clicked successfully.");

    	    } catch (NoSuchElementException e) {
    	        // Log the error if the button is not found
    	        Log.error("The 'Get Download History' button was not found: " + e.getMessage()+ e);
    	    } catch (Exception e) {
    	        // Log any other exceptions that occur
    	        Log.error("An unexpected error occurred while clicking the 'Get Download History' button: " + e.getMessage()+ e);
    	    }
    }

    // Method to enter From Date
    public void enterFromDate(String fromDate) {
    	Log.info("Attempting to click the 'From Date' field.");

        try {
            // Click the 'From Date' field
            WebElement fromDateField = driver.findElement(CoreAutoAllocationRepo.fromDateField);
            fromDateField.click();
            // Log after clicking the 'From Date' field
            Log.info("'From Date' field clicked successfully.");

            // Log before selecting the specific 'From Date' value
            Log.info("Attempting to select the 'From Date' value: " + fromDate);

            // Find and click the 'From Date' value
            WebElement fromandtodatevalue = driver.findElement(CoreAutoAllocationRepo.fromdatevalue(fromDate));
            fromandtodatevalue.click();

            // Log after selecting the 'From Date' value
            Log.info("'From Date' value (" + fromDate + ") selected successfully.");

        } catch (NoSuchElementException e) {
            // Log error if elements are not found
            Log.error("The 'From Date' field or the 'From Date' value was not found: " + e.getMessage()+ e);
        } catch (Exception e) {
            // Log any other unexpected errors
            Log.error("An unexpected error occurred while entering the 'From Date': " + e.getMessage()+ e);
        }
    }

    // Method to enter To Date
    public void enterToDate(String toDate) {
    	Log.info("Attempting to click the 'To Date' field.");

        try {
            // Click the 'To Date' field
            WebElement toDateField = driver.findElement(CoreAutoAllocationRepo.toDateField);
            toDateField.click();
            // Log after clicking the 'To Date' field
            Log.info("'To Date' field clicked successfully.");

            // Log before selecting the specific 'To Date' value
            Log.info("Attempting to select the 'To Date' value: " + toDate);

            // Find and click the 'To Date' value
            WebElement fromandtodatevalue = driver.findElement(CoreAutoAllocationRepo.todatevalue(toDate));
            fromandtodatevalue.click();

            // Log after selecting the 'To Date' value
            Log.info("'To Date' value (" + toDate + ") selected successfully.");

        } catch (NoSuchElementException e) {
            // Log error if elements are not found
            Log.error("The 'To Date' field or the 'To Date' value was not found: " + e.getMessage()+ e);
        } catch (Exception e) {
            // Log any other unexpected errors
            Log.error("An unexpected error occurred while entering the 'To Date': " + e.getMessage()+ e);
        }
    }

    // Method to click Search button
    public void clickSearch() {
    	 Log.info("Attempting to click the 'Search' button.");

    	    try {
    	        // Find and click the 'Search' button
    	        WebElement searchbtn = driver.findElement(CoreAutoAllocationRepo.searchbtn);
    	        searchbtn.click();
    	        
    	        // Log after the 'Search' button is clicked
    	        Log.info("'Search' button clicked successfully.");
    	    } catch (NoSuchElementException e) {
    	        // Log an error if the 'Search' button is not found
    	        Log.error("The 'Search' button was not found: " + e.getMessage()+ e);
    	    } catch (Exception e) {
    	        // Log any other unexpected errors
    	        Log.error("An unexpected error occurred while clicking the 'Search' button: " + e.getMessage()+ e);
    	    }
    }
    
    public boolean isDownloadHistoryDisplayed() {
    	Log.info("Starting to verify if download history is displayed.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofDays(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        try {
            // Log before locating the table element
            Log.info("Locating the table element.");

            // Locate the table element
            WebElement table = driver.findElement(CoreAutoAllocationRepo.table);

            // Log after locating the table
            Log.info("Table element located.");

            // Verify that the table is displayed
            if (!table.isDisplayed()) {
                Log.info("Table is not displayed.");
                return false;
            }

            // Log before locating the rows
            Log.info("Locating the rows in the table.");

            // Locate rows of the table body
            List<WebElement> rows = driver.findElements(CoreAutoAllocationRepo.rows);

            // Log after locating rows
            Log.info("Rows located in the table.");

            // Verify that there are rows present
            if (rows.isEmpty()) {
                Log.info("No rows found in the table.");
                return false;
            }

            // Limit to first 3 rows (or any specific number)
            int rowsToCheck = Math.min(3, rows.size()); // Ensure we don't exceed available rows

            // Log before checking row contents
            Log.info("Verifying the content of the first " + rowsToCheck + " rows.");

            // Loop through rows and verify content
            for (int i = 0; i < rowsToCheck; i++) {
                WebElement row = rows.get(i);
                List<WebElement> cells = row.findElements(CoreAutoAllocationRepo.cells);

                // Log before verifying cell content
                Log.info("Verifying the content of row " + (i + 1));

                // Ensure the row has the expected number of columns (should be greater than zero)
                if (cells.size() == 0) {
                    Log.info("Row " + (i + 1) + " has no cells.");
                    return false;
                }
                if(i==0)
                {
                // Log content of the row
                String id = cells.get(0).getText(); // ID column
                String date = cells.get(1).getText(); // Date column
                String userName = cells.get(2).getText(); // User Name column
                String downloadType = cells.get(3).getText(); // Download Type column
              String totalCount = cells.get(4).getText(); // Total Count column
              String totalOs = cells.get(5).getText(); // Total OS column

                // Log the cell values
                Log.info("Row " + (i + 1) + " content: ID=" + id + ", Date=" + date + ", UserName=" + userName + ", DownloadType=" + downloadType + ", Total Count=" + totalCount + ", Total OS=" + totalOs);

                // Verify if any cell value is empty
                if (id.isEmpty() || date.isEmpty() || userName.isEmpty() || downloadType.isEmpty() || totalCount.isEmpty() || totalOs.isEmpty()) {
                    Log.info("One or more cells in row " + (i + 1) + " are empty. Validation failed.");
                    return false;
                }
                }
                if(i==1)
                {
                // Log content of the row
                String id = cells.get(7).getText(); // ID column
                String date = cells.get(8).getText(); // Date column
                String userName = cells.get(9).getText(); // User Name column
                String downloadType = cells.get(10).getText(); // Download Type column
              String totalCount = cells.get(11).getText(); // Total Count column
              String totalOs = cells.get(12).getText(); // Total OS column

                // Log the cell values
                Log.info("Row " + (i + 1) + " content: ID=" + id + ", Date=" + date + ", UserName=" + userName + ", DownloadType=" + downloadType + ", Total Count=" + totalCount + ", Total OS=" + totalOs);

                // Verify if any cell value is empty
                if (id.isEmpty() || date.isEmpty() || userName.isEmpty() || downloadType.isEmpty() || totalCount.isEmpty() || totalOs.isEmpty()) {
                    Log.info("One or more cells in row " + (i + 1) + " are empty. Validation failed.");
                    return false;
                }
                }
                
                if(i==2)
                {
                // Log content of the row
                String id = cells.get(14).getText(); // ID column
                String date = cells.get(15).getText(); // Date column
                String userName = cells.get(16).getText(); // User Name column
                String downloadType = cells.get(17).getText(); // Download Type column
              String totalCount = cells.get(18).getText(); // Total Count column
              String totalOs = cells.get(19).getText(); // Total OS column

                // Log the cell values
                Log.info("Row " + (i + 1) + " content: ID=" + id + ", Date=" + date + ", UserName=" + userName + ", DownloadType=" + downloadType + ", Total Count=" + totalCount + ", Total OS=" + totalOs);

                // Verify if any cell value is empty
                if (id.isEmpty() || date.isEmpty() || userName.isEmpty() || downloadType.isEmpty() || totalCount.isEmpty() || totalOs.isEmpty()) {
                    Log.info("One or more cells in row " + (i + 1) + " are empty. Validation failed.");
                    return false;
                }
                }
                
            }

            // Log after all checks pass
            Log.info("All validations passed. Download history is displayed.");

            // If all validations pass, return true
            return true;

        } catch (NoSuchElementException e) {
            // Log error if table or elements are not found
            Log.error("NoSuchElementException occurred while verifying download history: " + e.getMessage()+ e);
            return false;
        } catch (Exception e) {
            // Log any other unexpected errors
            Log.error("An unexpected error occurred while verifying download history: " + e.getMessage()+ e);
            return false;
        }
    }
    
}

