package CollectionAgency.AccountAllocation;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.Login_Class;
import com.Page_Repository.CollectionAgencyAgentAccountAllocationRepo;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;

public class AgentAccountAllocation_Page extends Base_Class {
	
private WebDriver driver;
String secondColumnValue;
public static String AppType;
public static String CollectionAgency_BANNER_DETAILS;
	
	public AgentAccountAllocation_Page(WebDriver driver) {
		Log.info("Initializing CollectionAgencyAgentAcAllocatPage...");

	    this.driver = driver;
	    
	    Log.info("Initializing web elements using PageFactory...");
	    PageFactory.initElements(driver, this);
	    
	    Log.info("CollectionAgencyAgentAcAllocatPage initialized successfully.");
	    
    }
	
	// Method to navigate to Agency Account Allocation page
    public void navigateToAgencyAccountAllocation() {
        Log.info("Navigating to Agency Account Allocation...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Waiting for the Collection Agency menu to become visible...");
        WebElement collectionAgencyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.collectionAgencyMenu));
        
        Log.info("Clicking on the Collection Agency menu...");
        collectionAgencyMenu.click();
        Log.info("Collection Agency menu clicked successfully.");

        Log.info("Waiting for the Agency Account Allocation link to become visible...");
        WebElement agencyAccountAllocationLink = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.agencyAccountAllocationLink));
        
        Log.info("Clicking on the Agency Account Allocation link...");
        agencyAccountAllocationLink.click();
        Log.info("Agency Account Allocation link clicked successfully.");
        
        Log.info("Navigation to Agency Account Allocation completed.");
    }

    // Method to get page header text
    public String getPageHeaderText() throws InterruptedException {
    	Log.info("Retrieving page header text...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        try {
            Log.info("Waiting for ZoneCO field to become visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.ZoneCO));
            Log.info("ZoneCO field is now visible.");
        }  catch (Exception e) {
        	Log.error("Zone field not found. Page is not loaded completely. Throwing an exception.");
            throw new RuntimeException("Zone field not found. So the page is not loaded completely. Try it again", e);
        }
        
        Log.info("Waiting for spinner to disappear again...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for 10 seconds to ensure stability...");
        Thread.sleep(10000);

        Log.info("Locating page header element...");
        WebElement pageHeader = driver.findElement(CollectionAgencyDispositionRepo.pageHeader);
        
        String headerText = pageHeader.getText();
        Log.info("Retrieved page header text: " + headerText);
        
        return headerText;
    }
    
 // Method to truncate 'mst_col_agency_acc_allocated' table
    public String truncateTable() throws SQLException, ClassNotFoundException, IOException {
    	String truncateQuery = "TRUNCATE TABLE mst_col_agency_acc_allocated";
        
        Log.info("Starting table truncation: " + truncateQuery);
        
        try {
            String result = DBUtils.executeSQLStatement(truncateQuery);
            Log.info("Table truncated successfully.");
            return result;
        } catch (SQLException e) {
            Log.error("Error occurred while truncating the table: " + e.getMessage());
            throw new SQLException("Error occurred while truncating table", e);
        }
    }

    // Method to delete records from 'trn_account_followup' where disposition_date is today
    public String deleteRecordsWhereDispositionDateIsToday() throws SQLException, ClassNotFoundException, IOException {
        String deleteQuery = "DELETE FROM trn_account_followup WHERE disposition_date = CURRENT_DATE";
        
        Log.info("Starting deletion of records where disposition_date is today: " + deleteQuery);
        
        try {
            String result = DBUtils.executeSQLStatement(deleteQuery);
            Log.info("Records deleted successfully.");
            return result;
        } catch (SQLException e) {
            Log.error("Error occurred while deleting records: " + e.getMessage());
            throw new SQLException("Error occurred while deleting records", e);
        }
    }
    
    public void setAccountType(String type) {
    	Log.info("Starting the process to select account type: " + type);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Locating the account type dropdown...");
        WebElement accountTypeSelect = driver.findElement(CollectionAgencyDispositionRepo.accountTypeSelect);
        
        Log.info("Clicking on the account type dropdown...");
        accountTypeSelect.click();
        
        Log.info("Waiting for the account type option '" + type + "' to become visible...");
        WebElement accountTypevalue = wait.until(
            ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.accountType_and_collectionAgency_value(type))
        );
        
        Log.info("Clicking on the account type option '" + type + "'...");
        accountTypevalue.click();
        
        Log.info("Account type '" + type + "' selected successfully.");
    }

    public void setAssetCategory() {
    	Log.info("Starting the process to set asset category...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Asset Category dropdown...");
        WebElement assetCategorySelect = driver.findElement(CollectionAgencyDispositionRepo.assetCategorySelect);
        
        Log.info("Clicking on the Asset Category dropdown...");
        assetCategorySelect.click();
        Log.info("Asset Category dropdown clicked successfully.");

        Log.info("Locating 'Select All' option for Asset Category...");
        WebElement assetCategorySelectAll = driver.findElement(CollectionAgencyDispositionRepo.assetCategorySelectAll);
        
        Log.info("Clicking on 'Select All' option for Asset Category...");
        assetCategorySelectAll.click();
        Log.info("'Select All' option clicked successfully.");

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared. Asset category selection process completed.");
    }

    public void setSmaCategory(String category) {
    	Log.info("Starting the process to set SMA category to: " + category);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the SMA Category dropdown...");
        WebElement smaCategorySelect = driver.findElement(CollectionAgencyDispositionRepo.smaCategorySelect);

        Log.info("Clicking on the SMA Category dropdown...");
        smaCategorySelect.click();
        Log.info("SMA Category dropdown clicked successfully.");

        Log.info("Waiting for the SMA category value '" + category + "' to become visible...");
        WebElement sma_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.sma_npa_region_values(category)));

        Log.info("Clicking on SMA category value: " + category);
        sma_value.click();
        Log.info("SMA category value '" + category + "' selected successfully.");
    }

    public void setNpaCategory(String category) {
    	Log.info("Starting the process to set NPA category to: " + category);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the NPA Category dropdown...");
        WebElement npaCategorySelect = driver.findElement(CollectionAgencyDispositionRepo.npaCategorySelect);

        Log.info("Clicking on the NPA Category dropdown...");
        npaCategorySelect.click();
        Log.info("NPA Category dropdown clicked successfully.");

        Log.info("Waiting for the NPA category value '" + category + "' to become visible...");
        WebElement npa_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.sma_npa_region_values(category)));

        Log.info("Clicking on NPA category value: " + category);
        npa_value.click();
        Log.info("NPA category value '" + category + "' selected successfully.");
    }

    public void setRegion(String region) {
        Log.info("Starting the process to set Region to: " + region);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Region dropdown...");
        WebElement regionSelect = driver.findElement(CollectionAgencyDispositionRepo.regionSelect);

        Log.info("Clicking on the Region dropdown...");
        regionSelect.click();
        Log.info("Region dropdown clicked successfully.");

        Log.info("Waiting for the Region value '" + region + "' to become visible...");
        WebElement region_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.sma_npa_region_values(region)));

        Log.info("Clicking on Region value: " + region);
        region_value.click();
        Log.info("Region value '" + region + "' selected successfully.");

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared. Region selection process completed.");
    }
    
    public void setDpdDaysOperator(String operator) {
        Log.info("Starting the process to set DPD Days Operator to: " + operator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the DPD Days Operator dropdown...");
        WebElement dpdDaysOperationSelect = driver.findElement(CollectionAgencyDispositionRepo.dpdDaysOperationSelect);

        Log.info("Clicking on the DPD Days Operator dropdown...");
        dpdDaysOperationSelect.click();
        Log.info("DPD Days Operator dropdown clicked successfully.");

        Log.info("Waiting for the DPD Days Operator value '" + operator + "' to become visible...");
        WebElement dpdoperatorvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.dpdoperatorvalue(operator)));

        Log.info("Clicking on DPD Days Operator value: " + operator);
        dpdoperatorvalue.click();
        Log.info("DPD Days Operator value '" + operator + "' selected successfully.");
    }

    public void setDpdDays(String days) {
    	Log.info("Starting the process to set DPD Days to: " + days);

        Log.info("Locating the DPD Days input field...");
        WebElement dpdDaysField = driver.findElement(CollectionAgencyDispositionRepo.dpdDaysField);

        Log.info("Entering DPD Days value: " + days);
        dpdDaysField.sendKeys(days);
        Log.info("DPD Days value '" + days + "' entered successfully.");
    }

    	public List<List<String>> clickDownloadButton() throws IOException {
    	Log.info("Starting the process to click the Download button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Download button...");
        WebElement downloadButton = driver.findElement(CollectionAgencyDispositionRepo.downloadButton);

        Log.info("Clicking on the Download button...");
        downloadButton.click();
        Log.info("Download button clicked successfully.");

        Log.info("Waiting for the download message to become visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.downloadmsg));
        Log.info("Download message is now visible.");

        Log.info("Waiting for the download message to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyDispositionRepo.downloadmsg));
        Log.info("Download message disappeared, indicating completion.");

        Log.info("Retrieving all records from the downloaded file...");
        List<List<String>> allData = DownloadedExcelReader.readRequiredColumns();

        Log.info("Total records retrieved: " + allData.size());
        return allData;
    }
    
    public void clickSearchButton() {
    	Log.info("Starting the process to click the Search button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Search button...");
        WebElement searchButton = driver.findElement(CollectionAgencyDispositionRepo.searchButton);

        Log.info("Clicking on the Search button...");
        searchButton.click();
        Log.info("Search button clicked successfully.");

        Log.info("Waiting for total accounts and outstanding amounts to become visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.totalaccountsandoutstandingamounts));
        Log.info("Total accounts and outstanding amounts are now visible.");

        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner disappeared. Search process completed.");
    }

    public String selectCollectionAgency() throws InterruptedException {
    	Log.info("Starting the process to select a Collection Agency...");

        String value = "";
        WebElement collectionAgencyDropdown = driver.findElement(CollectionAgencyDispositionRepo.collectionAgencyDropdown);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Log.info("Scrolling into view for the Collection Agency dropdown...");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", collectionAgencyDropdown);

        Log.info("Waiting for a few seconds before interacting with the dropdown...");
        Thread.sleep(5000); // Consider replacing with explicit wait for better stability

        Log.info("Clicking on the Collection Agency dropdown...");
        collectionAgencyDropdown.click();
        Log.info("Collection Agency dropdown clicked successfully.");

        Log.info("Fetching available Collection Agency values...");
        List<WebElement> collectionAgencyValues = driver.findElements(CollectionAgencyDispositionRepo.collectionAgencyvalue);

        if (!collectionAgencyValues.isEmpty()) {
            value = collectionAgencyValues.get(0).getText();
            Log.info("Selecting Collection Agency: " + value);

            collectionAgencyValues.get(0).click();
            Log.info("Collection Agency '" + value + "' selected successfully.");
        } else {
            Log.warn("No Collection Agency values found.");
        }

        return value;
    }

    public void clickAllocateButton() {
    	Log.info("Starting the process to click the Allocate button...");

        Log.info("Locating the Allocate button...");
        WebElement allocate = driver.findElement(CollectionAgencyDispositionRepo.allocate);

        Log.info("Clicking on the Allocate button...");
        allocate.click();
        Log.info("Allocate button clicked successfully.");

        Log.info("Switching to the alert...");
        Alert alert = driver.switchTo().alert();

        Log.info("Accepting the alert...");
        alert.accept();
        Log.info("Alert accepted successfully.");
    }

    public boolean isSuccessMessageDisplayed() {
    	Log.info("Checking if the success message is displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the success message to become visible...");
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.successMessage));
        
        boolean isDisplayed = successMessage.isDisplayed();
        Log.info("Success message displayed: " + isDisplayed);

        return isDisplayed;
    }
    
    public void CollectionAgencyLogin(String userId, String password) throws Exception {
        try {
            AppType = "CollectionAgency";
            String Browser = configloader().getProperty("Browser");
            String CollectionAppUrl = configloader().getProperty("CollectionAgencyApplicationUrl");

            // Initialize WebDriver based on browser type
            switch (Browser.toUpperCase()) {
                case "CHROME":
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                    prefs.put("profile.default_content_setting_values.mixed_script", 1);
                    prefs.put("profile.default_content_settings.popups", 0);
                    prefs.put("download.prompt_for_download", false);
                    String userHome = System.getProperty("user.home");
                    String downloadDirectory = userHome + File.separator + "Downloads";
                    prefs.put("download.default_directory", downloadDirectory);
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
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("The Driver is not defined for browser: " + Browser);
            }
            Base_Class.driver = (RemoteWebDriver) driver;
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            Log.info("Driver has initialized successfully for " + Browser + " browser");

            // Load the application URL
            driver.get(CollectionAppUrl);
            Common.setDriver(driver);

            String query = "select BANNER_DETAILS from SET_LOGINPAGE_BANNER_DETAILS where IS_ACTIVE=1 and banner_user_type=3 order by banner_section desc FETCH FIRST 1 ROWS ONLY";
            CollectionAgency_BANNER_DETAILS = DBUtils.fetchSingleValueFromDB(query);
            System.out.println("BANNER_DETAILS: " + CollectionAgency_BANNER_DETAILS);

            Common.fluentWait(CollectionAgency_BANNER_DETAILS, LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));

            Pagetitle = driver.getTitle();
            Log.info("Title is displayed: " + Pagetitle);

            // Perform login actions
            Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
            Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
            Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

            driver.findElement(LoginPageRepo.UserNameField).sendKeys(userId);
            Log.info("Entered " + userId + " in user name field");
            driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
            Log.info("Entered password in password field");
            driver.findElement(LoginPageRepo.LoginButton).click();
            Log.info("Clicked on login button");

            try {
                WebElement clickableElement = Common.waitForElementToBeClickable(
                    driver, 
                    LoginPageRepo.AlreadyLoginPopupYesButton, 
                    Duration.ofSeconds(20)
                );

                if (clickableElement != null) {
                    clickableElement.click();
                    Common.waitForSpinnerToDisappear2(driver, "Loading Spinner", LoginPageRepo.Spinner);
                    
                    Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
                    Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
                    Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);
                    
                    driver.findElement(LoginPageRepo.UserNameField).sendKeys(userId);
                    Log.info("Entered " + userId + " in user name field");
                    driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
                    Log.info("Entered password in password field");
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

            Login_Class.SomeErrorOccuredHandling(userId,password);
            Thread.sleep(6000);
        } catch (Exception e) {
            Log.error("An error occurred in CollectionAgencyLogin: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
 // Method to navigate to Agent Account Allocation page
    public void navigateToAgentAccountAllocation() {
    	Log.info("Navigating to Agent Account Allocation...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for 'Accounts Allocation' main menu to be visible...");
        WebElement AccountsAllocationmainmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.AccountsAllocationmainmenu));
        
        Log.info("Clicking on 'Accounts Allocation' main menu...");
        AccountsAllocationmainmenu.click();

        Log.info("Waiting for 'Agent Account Allocation' submenu to be visible...");
        WebElement AgentAccountAllocationsubmenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.AgentAccountAllocationsubmenu));
        
        Log.info("Clicking on 'Agent Account Allocation' submenu...");
        AgentAccountAllocationsubmenu.click();

        try {
            Log.info("Waiting for 'Branch' field to be visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.Branch));
            Log.info("'Branch' field is visible. Page loaded successfully.");
        } catch (Exception e) {
            Log.ErrorWithException("Branch field not found. Page is not loaded completely. Throwing an exception.", e);
            throw new RuntimeException("Branch field not found. So the page is not loaded completely. Try again.", e);
        }

        Log.info("Waiting for spinner to disappear after page load...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Successfully navigated to Agent Account Allocation.");
    }
    
 // Method to validate the presence of fields on Agent Account Allocation page
    public boolean isAgentAccountAllocationPageDisplayed() {
    	Log.info("Checking if the Agent Account Allocation page is displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating page header and form elements...");
        WebElement AgentAccountAllocationpageheader = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AgentAccountAllocationpageheader);
        WebElement AssetCategory = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AssetCategory);
        WebElement SMACategory = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.SMACategory);
        WebElement NPACategory = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.NPACategory);
        WebElement Zone_CO = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Zone_CO);
        WebElement Region = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Region);
        WebElement Branch = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Branch);
        WebElement SchemeType = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.SchemeType);
        WebElement ProductType = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ProductType);
        WebElement Os_Balance_Operator = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Os_Balance_Operator);
        WebElement Os_Balance_text_field = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Os_Balance_text_field);
        WebElement Overdue_to_EMI_Operator = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Overdue_to_EMI_Operator);
        WebElement Overdue_to_EMI_text_field = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Overdue_to_EMI_text_field);
        WebElement Overdue_Balance_Operator = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Overdue_Balance_Operator);
        WebElement Overdue_Balance_text_field = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Overdue_Balance_text_field);
        WebElement CollectionAgent = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.CollectionAgent);
        WebElement TypesofAccount = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.TypesofAccount);
        WebElement Searchbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Searchbutton);
        WebElement Resetbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Resetbutton);

        boolean elementsBeforeScrollVisible =
                AgentAccountAllocationpageheader.isDisplayed() &&
                AssetCategory.isDisplayed() &&
                SMACategory.isDisplayed() &&
                NPACategory.isDisplayed() &&
                Zone_CO.isDisplayed() &&
                Region.isDisplayed() &&
                Branch.isDisplayed() &&
                SchemeType.isDisplayed() &&
                ProductType.isDisplayed() &&
                Os_Balance_Operator.isDisplayed() &&
                Os_Balance_text_field.isDisplayed() &&
                Overdue_to_EMI_Operator.isDisplayed() &&
                Overdue_to_EMI_text_field.isDisplayed() &&
                Overdue_Balance_Operator.isDisplayed() &&
                Overdue_Balance_text_field.isDisplayed() &&
                CollectionAgent.isDisplayed() &&
                TypesofAccount.isDisplayed() &&
                Searchbutton.isDisplayed() &&
                Resetbutton.isDisplayed();

        if (!elementsBeforeScrollVisible) {
            Log.warn("Some elements before scrolling are not visible. Page might not be loaded properly.");
            return false;
        }

        Log.info("All elements before scrolling are visible.");

        // Scroll down after Resetbutton is displayed
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement AllocatedListbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AllocatedListbutton);
        Log.info("Scrolling to 'Allocated List' button...");
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", AllocatedListbutton);

        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Checking visibility of elements after scrolling...");
        WebElement CollectionAgent2 = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.CollectionAgent2);
        WebElement AllocationDate = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AllocationDate);
        WebElement Allocatebutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Allocatebutton);
        WebElement DeAllocatebutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.DeAllocatebutton);
        WebElement Downloadbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Downloadbutton);
        WebElement Closebutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Closebutton);

        boolean elementsAfterScrollVisible =
                CollectionAgent2.isDisplayed() &&
                AllocationDate.isDisplayed() &&
                Allocatebutton.isDisplayed() &&
                DeAllocatebutton.isDisplayed() &&
                Downloadbutton.isDisplayed() &&
                Closebutton.isDisplayed() &&
                AllocatedListbutton.isDisplayed();

        if (elementsAfterScrollVisible) {
            Log.info("All elements after scrolling are visible. Page is displayed correctly.");
        } else {
            Log.warn("Some elements after scrolling are not visible.");
        }

        return elementsAfterScrollVisible;
    }

    // Methods to interact with WebElements
    public void selectAssetCategory() throws InterruptedException {
    	Log.info("Selecting an Asset Category...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        Log.info("Scrolling to the top of the page...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        Log.info("Waiting for any loading spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        Log.info("Locating the Asset Category dropdown...");
        WebElement AssetCategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.AssetCategory));

        Log.info("Clicking on the Asset Category dropdown...");
        AssetCategory.click();

        Log.info("Waiting for Asset Category values to become visible...");
        WebElement AssetCategoryvalues = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.AssetCategoryvalues));

        Log.info("Selecting an Asset Category value...");
        AssetCategoryvalues.click();

        Log.info("Waiting for spinner to disappear after selection...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Asset Category selection completed successfully.");
    }

    public void selectSmaCategory(String smaCategory) {
    	Log.info("Selecting SMA Category: " + smaCategory);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        WebElement SMACategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.SMACategory));
        SMACategory.click();
        Log.info("Clicked on SMA Category dropdown.");

        WebElement smaCategoryOption = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.SMA_Category_NPA_Category_CollectionAgent_value(smaCategory)));
        smaCategoryOption.click();
        Log.info("Selected SMA Category: " + smaCategory);
    }
    
    public void selectNpaCategory(String NpaCategory) {
    	Log.info("Selecting NPA Category: " + NpaCategory);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        WebElement NPACategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.NPACategory));
        NPACategory.click();
        Log.info("Clicked on NPA Category dropdown.");

        WebElement npaCategoryOption = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.SMA_Category_NPA_Category_CollectionAgent_value(NpaCategory)));
        npaCategoryOption.click();
        Log.info("Selected NPA Category: " + NpaCategory);
    }

    public void selectAccountType(String accountType) {
    	Log.info("Selecting Account Type: " + accountType);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        WebElement TypesofAccount = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.TypesofAccount));
        TypesofAccount.click();
        Log.info("Clicked on 'Types of Account' dropdown.");

        WebElement accountTypeOption = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Types_of_Account_Role_CollectionAgent_value(accountType)));
        accountTypeOption.click();
        Log.info("Selected Account Type: " + accountType);
    }

    public void clickSearchButon() throws InterruptedException {
    	Log.info("Clicking the Search button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Searchbutton));
        searchButton.click();
        Log.info("Search button clicked successfully.");

        Log.info("Waiting for loading spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Scrolling to 'Allocated List' button...");
        WebElement allocatedListButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.AllocatedListbutton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", allocatedListButton);
        Thread.sleep(5000);
        Log.info("Successfully scrolled to 'Allocated List' button.");
    }
    
    public List<String> getAllColumnValuesExcludingFirstTwo() {
    	Log.info("Retrieving all column values, excluding the first two...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        List<String> columnValues = new ArrayList<>();

        // Wait for all table cells to be present
        List<WebElement> columns = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CollectionAgencyAgentAccountAllocationRepo.Gridvalues));
            for (int i = 0; i < columns.size(); i++) { 
                WebElement column = columns.get(i);
                wait.until(ExpectedConditions.visibilityOf(column)); // Ensure visibility before extracting text
                columnValues.add(column.getText().trim());
            }

        Log.info("Extracted column values (excluding first two): " + columnValues);
        return columnValues;
    }
    
    public boolean isExcelDataPresentInColumnValues(List<List<String>> excelData, List<String> columnValues) {
    	Log.info("Starting validation: Checking if all column values are present in the Excel data...");

        // Convert Excel data into a Set for fast lookups
        Log.info("Normalizing Excel data for comparison...");
        Set<String> normalizedExcelData = excelData.stream()
                .flatMap(List::stream) // Flatten 2D list to a single stream of values
                .map(value -> value.trim().replace(",", "").toLowerCase()) // Normalize and lowercase
                .collect(Collectors.toSet());
        Log.info("Excel data normalization completed.");

        for (String columnValue : columnValues) {
            String normalizedValue = columnValue.trim().replace(",", "").toLowerCase(); // Normalize column value
            Log.info("Checking if column value '" + columnValue + "' (normalized: '" + normalizedValue + "') is present in Excel data...");

            if (!normalizedExcelData.contains(normalizedValue)) {
                Log.warn("Column value missing in Excel data: " + columnValue);
                return false; // If any column value is missing, return false
            }
            Log.info("Column value '" + columnValue + "' found in Excel data.");
        }

        Log.info("Validation successful: All column values are present in Excel data.");
        return true;
    }
    
    // Method to click the Reset button
    public void clickResetButton() throws InterruptedException {
    	Log.info("Starting the process to click the Reset button...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        Log.info("Scrolling to the top of the page...");
        js.executeScript("window.scrollTo(0, 0);");
        
        Log.info("Waiting for 5 seconds to ensure stability...");
        Thread.sleep(5000);
        
        Log.info("Waiting for the Reset button to become clickable...");
        WebElement Resetbutton = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Resetbutton));
        
        Log.info("Clicking the Reset button...");
        Resetbutton.click();
        
        Log.info("Waiting for the loading spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        Log.info("Reset button clicked successfully and spinner disappeared.");
    }

    // Method to check if all filter fields are cleared
    public boolean areFilterFieldsCleared() {
    	Log.info("Verifying if all filter fields are cleared...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
            WebElement assetCategory = wait.until(ExpectedConditions.presenceOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.AssetCategory));
            WebElement smaCategory = wait.until(ExpectedConditions.presenceOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.SMACategory));
            WebElement npaCategory = wait.until(ExpectedConditions.presenceOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.NPACategory));
            WebElement typesOfAccount = wait.until(ExpectedConditions.presenceOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.TypesofAccount));

            boolean isCleared = 
                (assetCategory.getText().isEmpty() || assetCategory.getText().equals("Select")) &&
                (smaCategory.getText().isEmpty() || smaCategory.getText().equals("Select")) &&
                (npaCategory.getText().isEmpty() || npaCategory.getText().equals("Select")) &&
                (typesOfAccount.getText().isEmpty() || typesOfAccount.getText().equals("Select"));

            Log.info("Filter fields cleared status: " + isCleared);
            return isCleared;
            
        } catch (NoSuchElementException e) {
            Log.ErrorWithException("One or more filter fields were not found on the page!", e);
            return false;
        }
    }
    
    // Method to select accounts from the grid
    public List<String> selectAccount() throws InterruptedException {
    	Log.info("Starting process to select an account and extract row data...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        List<String> rowData = new ArrayList<>(); // Declare rowData outside the try block

        try {
            // Find all columns (td elements) within the row
            Log.info("Waiting for account details to be visible...");
            List<WebElement> columns = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CollectionAgencyAgentAccountAllocationRepo.accountdetailsoffirstrow));

            // Iterate through each column and store the text
            for (WebElement column : columns) {
                String text = column.getText().trim();
                rowData.add(text);
            }

            if (rowData.isEmpty()) {
                Log.warn("No data extracted from the row!");
                return rowData;
            }

            Log.info("Extracted row data: " + rowData);

            // Click on the checkbox
            Log.info("Waiting for account checkbox to become clickable...");
            WebElement accountCheckbox = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.firstrecordaccountCheckbox));

            Log.info("Clicking the account checkbox...");
            WebElement Resetbutton = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Resetbutton));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", Resetbutton);
            Thread.sleep(5000);
            accountCheckbox.click();

            Log.info("Account checkbox clicked successfully.");

        } catch (TimeoutException e) {
            Log.ErrorWithException("Timeout while waiting for elements: " + e.getMessage(), e);
        } catch (StaleElementReferenceException e) {
            Log.ErrorWithException("Element went stale while interacting: " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            Log.ErrorWithException("Unable to locate an element: " + e.getMessage(), e);
        }

        return rowData; // Now it will always return, even if an exception occurs
    }

    // Method to select an agent from the dropdown
    public String selectAgent() throws InterruptedException {
    	Log.info("Starting process to select an agent...");
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Wait for the dropdown to be clickable and then click it
            Log.info("Waiting for Collection Agent dropdown to be clickable...");
            WebElement collectionAgentDropdown = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.CollectionAgent2));
            collectionAgentDropdown.click();
            Log.info("Collection Agent dropdown clicked.");

            // Wait for collection agent options to appear
            Log.info("Waiting for collection agent values to be visible...");
            List<WebElement> collectionAgentValues = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CollectionAgencyAgentAccountAllocationRepo.collectionagentvalues));

            if (!collectionAgentValues.isEmpty()) {
                WebElement firstElement = collectionAgentValues.get(0);
                String firstValue = firstElement.getText().trim();

                if (firstValue.isEmpty()) {
                    firstValue = firstElement.getAttribute("innerText").trim(); // Fallback in case getText() fails
                }

                if (!firstValue.isEmpty()) {
                    Log.info("Selecting the first available agent: " + firstValue);
                    firstElement.click();
                    Log.info("Agent '" + firstValue + "' selected successfully.");
                    return firstValue;
                } else {
                    Log.warn("First agent value is empty. Not selecting.");
                }
            } else {
                Log.warn("No elements found for the given XPath.");
            }
        } catch (TimeoutException e) {
            Log.ErrorWithException("Timeout while waiting for elements: " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            Log.ErrorWithException("Element not found: " + e.getMessage(), e);
        } catch (Exception e) {
            Log.ErrorWithException("Unexpected error occurred: " + e.getMessage(), e);
        }

        Log.warn("Returning null as no agent was selected.");
        return null; // Return null if no agent was selected
    }
    
 // Method to click the Allocate button
    public void clickAllocateButon() {
    	Log.info("Starting process to click the Allocate button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        try {
            // Wait for Allocate button to be clickable
            Log.info("Waiting for Allocate button to become clickable...");
            WebElement allocateButton = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Allocatebutton));

            Log.info("Clicking the Allocate button...");
            allocateButton.click();

            // Wait for spinner to disappear
            Log.info("Waiting for loading spinner to disappear...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

            // Wait for allocation message to disappear
            Log.info("Waiting for allocation confirmation message to disappear...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.Allocatedmsg));

            Log.info("Allocate button clicked successfully and messages have disappeared.");
            
        } catch (TimeoutException e) {
            Log.ErrorWithException("Timeout while waiting for elements: " + e.getMessage(), e);
        } catch (NoSuchElementException e) {
            Log.ErrorWithException("Element not found: " + e.getMessage(), e);
        } catch (Exception e) {
            Log.ErrorWithException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public String deleteAgentAccountLink(String agencyName) throws ClassNotFoundException, SQLException, IOException {
        Log.info("Starting process to delete agent account link for agency: " + agencyName);

        // Construct query to delete from agent_account_link
        String deleteAgentAccountLinkQuery = "DELETE FROM agent_account_link WHERE col_agency_id = " +
                "(SELECT COLLECTION_AGENCY_ID FROM MST_COLLECTION_AGENCY " +
                "WHERE COLLECTION_AGENCY_NAME = '" + agencyName + "' FETCH FIRST 1 ROW ONLY)";

        // Construct query to delete from agent_account_link_history
        String deleteAgentAccountLinkHistoryQuery = "DELETE FROM agent_account_link_history WHERE col_agency_id = " +
                "(SELECT COLLECTION_AGENCY_ID FROM MST_COLLECTION_AGENCY " +
                "WHERE COLLECTION_AGENCY_NAME = '" + agencyName + "' FETCH FIRST 1 ROW ONLY)";

        Log.info("Executing SQL query: " + deleteAgentAccountLinkQuery);
        String result1 = DBUtils.executeSQLStatement(deleteAgentAccountLinkQuery);
        Log.info("SQL execution result (agent_account_link): " + result1);

        Log.info("Executing SQL query: " + deleteAgentAccountLinkHistoryQuery);
        String result2 = DBUtils.executeSQLStatement(deleteAgentAccountLinkHistoryQuery);
        Log.info("SQL execution result (agent_account_link_history): " + result2);

        // Return both results
        return "Result for agent_account_link: " + result1 + " | Result for agent_account_link_history: " + result2;
    }
    
    public void selectAgent(String agentName) {
    	Log.info("Starting process to select agent: " + agentName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Click on the Collection Agent dropdown
        Log.info("Clicking the Collection Agent dropdown...");
        WebElement CollectionAgent = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.CollectionAgent);
        CollectionAgent.click();
        Log.info("Collection Agent dropdown clicked.");

        // Wait for the agent name to become visible and then click it
        Log.info("Waiting for agent '" + agentName + "' to appear in the list...");
        WebElement AgentName = wait.until(ExpectedConditions.visibilityOfElementLocated(
            CollectionAgencyAgentAccountAllocationRepo.SMA_Category_NPA_Category_CollectionAgent_value(agentName)
        ));

        Log.info("Clicking on agent: " + agentName);
        AgentName.click();
        Log.info("Agent '" + agentName + "' selected successfully.");
    }
    
    public List<String> getRowData() {
    	Log.info("Starting process to retrieve row data...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Find all column elements in the row
        Log.info("Waiting for account details to be present...");
        List<WebElement> columns = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CollectionAgencyAgentAccountAllocationRepo.accountdetails));

        // Create a list to store extracted text data
        List<String> rowData = new ArrayList<>();

        // Iterate through each column and store the text
        for (int i = 0; i < columns.size(); i++) {
            String cellText = columns.get(i).getText().trim(); // Trim to remove extra spaces
            rowData.add(cellText);
            Log.info("Column " + (i + 1) + ": " + cellText);

            // Store the second column value (index 1, as index starts from 0)
            if (i == 1) {
                secondColumnValue = cellText;
            }
        }

        Log.info("Second Column Value: " + secondColumnValue);

        // Return the extracted row data
        Log.info("Row data extracted successfully: " + rowData);
        return rowData;
    }
    
    public boolean compareAccountDetails(List<String> beforeAllocation, List<String> afterAllocation) {
    	Log.info("Starting comparison of account details before and after allocation...");

        // Check if both lists have the same number of columns
        if (beforeAllocation.size() != afterAllocation.size()) {
            Log.warn("Mismatch in number of columns. Before: " + beforeAllocation.size() + ", After: " + afterAllocation.size());
            return false;
        }

        // Get today's date in the required format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = dateFormat.format(new Date());
        Log.info("Today's date: " + todayDate);

        // Iterate through columns and compare values
        for (int i = 0; i < beforeAllocation.size(); i++) {
            String beforeValue = beforeAllocation.get(i).trim();
            String afterValue = afterAllocation.get(i).trim();

            // Check for the expected change in the 6th column (index 5)
            if (i == 5) { 
                if (!beforeValue.equals("Not Allocated") || !afterValue.equals("Allocated")) {
                    Log.warn("Mismatch in 6th column: Expected 'Not Allocated' → 'Allocated', but found '" 
                             + beforeValue + "' → '" + afterValue + "'");
                    return false;
                }
            } 
            // Check for the expected change in the 18th column (index 17)
            else if (i == 17) { 
                if (!beforeValue.isEmpty() && !beforeValue.equals("null") && !beforeValue.equals(todayDate)) {
                    Log.warn("Mismatch in 18th column: Expected null/empty before allocation, but found '" + beforeValue + "'");
                    return false;
                }
                if (!afterValue.equals(todayDate)) {
                    Log.warn("Mismatch in 18th column: Expected today's date '" + todayDate + "', but found '" + afterValue + "'");
                    return false;
                }
            } 
            // For all other columns, values should remain unchanged
            else {
                if (!beforeValue.equals(afterValue)) {
                    Log.warn("Mismatch at column " + (i + 1) + ": Before = '" + beforeValue + "', After = '" + afterValue + "'");
                    return false;
                }
            }
        }

        Log.info("Account details match successfully, with the expected allocation and date update.");
        return true;
    }
    
    public void clickDownloadButon() {
    	Log.info("Starting process to download the file...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Locate and click the Download button
        Log.info("Locating the Download button...");
        WebElement Downloadbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Downloadbutton);
        
        Log.info("Clicking the Download button...");
        Downloadbutton.click();
        Log.info("Download button clicked successfully.");

        // Wait for the spinner to disappear
        Log.info("Waiting for spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
        // Wait for the downloaded message to disappear
        Log.info("Waiting for download confirmation message to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.downloadedmsg));

        // Ensure spinner is gone after download
        Log.info("Verifying that spinner is no longer present...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Download process completed successfully.");
    }
    
    public boolean processExcelDataAndCompare(String Collection_Agency, String Agent_Name) {
    	Log.info("Starting Excel data processing and comparison...");

        try {
            Log.info("Reading allocated account details from the downloaded Excel file...");
            List<List<String>> data = DownloadedExcelReader.readAllocatedaccountdetails();

            if (data.isEmpty()) {
                Log.warn("No relevant data found in the Excel file.");
                return false; // Return false if no data is available
            }

            // Get today's date in the required format
            String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            Log.info("Today's date: " + todayDate);

            for (List<String> row : data) {
                if (row.size() < 7) continue; // Ensure row has enough columns before accessing indexes

                String a_cNumber = row.get(0); // A/c Number
                String agencyName = row.get(4); // Agency Name
                String agentName = row.get(6); // Agent Name
                String status = row.get(3); // Allocated/De Allocated Status
                String allocatedDate = row.get(2); // Allocated/De Allocated Date

                Log.info("Processing row: " + row);

                // Track mismatched values
                List<String> mismatchedFields = new ArrayList<>();

                if (!a_cNumber.equals(secondColumnValue)) {
                    mismatchedFields.add("A/c Number Expected: " + secondColumnValue + ", Found: " + a_cNumber);
                }
                if (!agencyName.equals(Collection_Agency)) {
                    mismatchedFields.add("Agency Name Expected: " + Collection_Agency + ", Found: " + agencyName);
                }
                if (!agentName.equals(Agent_Name)) {
                    mismatchedFields.add("Agent Name Expected: " + Agent_Name + ", Found: " + agentName);
                }
                if (!status.equals("Allocated")) {
                    mismatchedFields.add("Status Expected: Allocated, Found: " + status);
                }
                if (!allocatedDate.equals(todayDate)) {
                    mismatchedFields.add("Allocated Date Expected: " + todayDate + ", Found: " + allocatedDate);
                }

                // If no mismatches, return true
                if (mismatchedFields.isEmpty()) {
                    Log.info("Row matches expected data. Returning true.");
                    return true;
                } else {
                    Log.warn("Mismatch found in row: " + mismatchedFields);
                }
            }
        } catch (IOException e) {
            Log.ErrorWithException("Error reading Excel file: " + e.getMessage(), e);
        }

        Log.warn("No matching row found. Returning false.");
        return false; // Return false if no matching row is found
    }
    
    // Method to select an account
    public void selectAcount() {
    	Log.info("Starting the process to select an account...");

        // Locate the account checkbox
        Log.info("Locating the account checkbox...");
        WebElement accountCheckbox = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.accountCheckbox);
        
        // Click the checkbox
        Log.info("Clicking the account checkbox...");
        accountCheckbox.click();
        
        Log.info("Account checkbox clicked successfully.");
    }

    // Method to click the deallocate button
    public void clickDeallocate() {
    	Log.info("Starting the process to click the Deallocate button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Locate the Deallocate button
        Log.info("Locating the Deallocate button...");
        WebElement DeAllocatebutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.DeAllocatebutton);

        // Click the Deallocate button
        Log.info("Clicking the Deallocate button...");
        DeAllocatebutton.click();

        // Wait for spinner to disappear
        Log.info("Waiting for processing spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        // Wait for deallocation message to disappear
        Log.info("Waiting for the deallocated message to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.deallocatedmsg));

        // Wait again for spinner to disappear (if needed)
        Log.info("Ensuring no additional spinners are present...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Deallocate button clicked and process completed successfully.");
    }
    
    // Method to check if account grid is empty after deallocation
    public boolean isGridEmpty() {
    	Log.info("Checking if the grid is empty...");

        try {
            Log.info("Attempting to locate the account checkbox...");
            WebElement accountCheckbox = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.accountCheckbox);

            boolean isDisplayed = accountCheckbox.isDisplayed();
            Log.info("Account checkbox found. Grid is not empty.");
            return !isDisplayed; // Returns false if checkbox is displayed (grid is not empty)
        } catch (Exception e) {
            Log.warn("Account checkbox not found or not visible. Assuming the grid is empty.");
            return true; // Handle case where element is not present
        }
    }
    
    // Method to click on the "Allocated List" button
    public void clickAllocatedListButton() {
    	Log.info("Starting the process to click the Allocated List button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Locate the Allocated List button
        Log.info("Locating the Allocated List button...");
        WebElement AllocatedListbutton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AllocatedListbutton);

        // Click the Allocated List button
        Log.info("Clicking the Allocated List button...");
        AllocatedListbutton.click();

        // Wait for the spinner to disappear
        Log.info("Waiting for the processing spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Allocated List button clicked successfully and process completed.");
    }

    // Method to verify if the popup window is displayed
    public boolean isPopupDisplayed() {
    	Log.info("Checking if the Allocated List popup is displayed...");

        try {
            Log.info("Attempting to locate the Allocated List popup...");
            WebElement allocatedlistpopup = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.allocatedlistpopup);

            boolean isDisplayed = allocatedlistpopup.isDisplayed();
            Log.info("Allocated List popup is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            Log.warn("Allocated List popup not found in the DOM. Assuming it is not displayed.");
            return false; // The popup was not found in the DOM
        }
    }
    
    // Method to select an agent from dropdown
    public void selectAgentname(String agentName) {
    	Log.info("Starting the process to select agent: " + agentName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Locate and click the Agent Name dropdown
        Log.info("Locating and clicking the Agent Name dropdown...");
        WebElement AgentNamedropdown = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AgentName);
        AgentNamedropdown.click();

        // Wait for and select the specified agent
        Log.info("Waiting for the agent '" + agentName + "' to be visible...");
        WebElement AgentName = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.Agent_Name(agentName)));

        Log.info("Clicking on the agent '" + agentName + "'...");
        AgentName.click();

        Log.info("Agent '" + agentName + "' selected successfully.");
    }
    
    // Method to enter action dates
    public void enterActionDates(String Date) {
    	Log.info("Starting the process to enter action dates: " + Date);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        // Selecting 'From' Date
        Log.info("Locating and clicking 'Action Date From' field...");
        WebElement ActionDateFrom = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateFrom);
        ActionDateFrom.click();

        Log.info("Waiting for 'From' date (" + Date + ") to be visible...");
        WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.ActionDate_From_and_To_value(Date)));

        Log.info("Selecting 'From' date: " + Date);
        FromDate.click();

        // Selecting 'To' Date
        Log.info("Locating and clicking 'Action Date To' field...");
        WebElement ActionDateTo = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateTo);
        ActionDateTo.click();

        Log.info("Waiting for 'To' date (" + Date + ") to be visible...");
        WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.ActionDate_From_and_To_value(Date)));

        Log.info("Selecting 'To' date: " + Date);
        ToDate.click();

        Log.info("Action dates entered successfully: From " + Date + " To " + Date);
    }
    
 // Method to click the search button
    public void clickSearch() {
    	Log.info("Starting the process to click the Search button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Search button...");
        WebElement Search = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Search);

        Log.info("Clicking the Search button...");
        Search.click();

        Log.info("Waiting for the loading spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Search button clicked successfully, and loading completed.");
    }
    
    public boolean verifyDateRange(String fromDate, String toDate) {
    	Log.info("Starting verification of date range from " + fromDate + " to " + toDate);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Get current month and year
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        // Convert single-day input to "dd-MM-yyyy"
        fromDate = convertToFullDate(fromDate, currentMonth, currentYear);
        toDate = convertToFullDate(toDate, currentMonth, currentYear);

        Log.info("Converted From Date: " + fromDate);
        Log.info("Converted To Date: " + toDate);

        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);

        Log.info("Waiting for allocation date elements to be visible...");
        List<WebElement> dateElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CollectionAgencyAgentAccountAllocationRepo.Allocationdates));

        Log.info("Checking if all extracted dates fall within the specified range...");
        for (WebElement element : dateElements) {
            String dateText = element.getText().trim();
            LocalDate extractedDate = LocalDate.parse(dateText, formatter);

            if (extractedDate.isBefore(from) || extractedDate.isAfter(to)) {
                Log.warn("Date out of range: " + extractedDate);
                return false;
            }
        }

        Log.info("All dates are within the specified range.");
        return true;
    }
    private String convertToFullDate(String day, int month, int year) {
    	Log.info("Converting input date: " + day + " for month: " + month + " and year: " + year);

        if (day.matches("\\d{1,2}")) { // If input is only a day (like "28")
            String formattedDate = String.format("%02d-%02d-%d", Integer.parseInt(day), month, year);
            Log.info("Converted date: " + formattedDate);
            return formattedDate;
        }

        Log.info("Date already in full format: " + day);
        return day; // Return unchanged if already in "dd-MM-yyyy" format
    }
    
    // Method to click the Reset button
    public void clickResetButon() {
    	Log.info("Clicking on the Reset button...");
        
        WebElement ResetButton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Resetbuton);
        ResetButton.click();
        
        Log.info("Reset button clicked successfully.");
    }

    // Method to verify that all filter fields are cleared (sample implementation)
    public boolean areFiltersReset() {
    	Log.info("Verifying if all filters are reset...");

        // Locate elements
        WebElement AgentName = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AgentName);
        WebElement ActionDateFrom = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateFrom);
        WebElement ActionDateTo = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateTo);
        WebElement Gridresetvalue = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.Gridresetvalue);

        // Extract text
        String agentNameText = AgentName.getText().trim();
        String actionDateFromText = ActionDateFrom.getText().trim();
        String actionDateToText = ActionDateTo.getText().trim();
        String gridResetValueText = Gridresetvalue.getText().trim();

        // Expected values
        String expectedAgentName = "Select";
        String expectedGridResetValue = "No records to display.";

        // Validation checks
        boolean isAgentNameValid = agentNameText.equals(expectedAgentName);
        boolean isActionDateFromEmpty = actionDateFromText.isEmpty(); // Check if empty
        boolean isActionDateToEmpty = actionDateToText.isEmpty(); // Check if empty
        boolean isGridResetValueValid = gridResetValueText.equals(expectedGridResetValue);

        // Logging details
        Log.info("Agent Name: '" + agentNameText + "', Expected: '" + expectedAgentName + "'");
        Log.info("Action Date From: '" + actionDateFromText + "', Is Empty: " + isActionDateFromEmpty);
        Log.info("Action Date To: '" + actionDateToText + "', Is Empty: " + isActionDateToEmpty);
        Log.info("Grid Reset Value: '" + gridResetValueText + "', Expected: '" + expectedGridResetValue + "'");

        boolean result = isAgentNameValid && isActionDateFromEmpty && isActionDateToEmpty && isGridResetValueValid;

        Log.info("Filters reset validation result: " + result);
        return result;
    }
    
    public void selectAllocationId() {
    	Log.info("Attempting to select Allocation ID...");

        WebElement AllocationID = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.AllocationID);
        AllocationID.click();

        Log.info("Allocation ID selected successfully.");
    }
    
 // Method to click on the Download in Excel button
    public void clickDownloadExcelButton() {
    	Log.info("Attempting to click the Download Excel button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement downloadExcelButton = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.downloadExcelButton);
        
        downloadExcelButton.click();
        Log.info("Download Excel button clicked successfully.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        Log.info("Spinner is no longer visible.");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.downloadedmsg));
        Log.info("Downloaded message is no longer visible. Excel file should be downloaded.");
    }
    
    public String getDefaultDownloadPath() {
    	String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        Log.info("Default download path retrieved: " + downloadPath);
        return downloadPath;
    }

    // Get the latest downloaded file from the downloads folder
    public File getLatestDownloadedFile() {
    	String downloadPath = getDefaultDownloadPath();
        File dir = new File(downloadPath);

        Log.info("Checking download directory: " + downloadPath);

        // Ensure the directory exists
        if (!dir.exists() || !dir.isDirectory()) {
            Log.error("Download directory does not exist: " + downloadPath);
            throw new RuntimeException("Download directory does not exist: " + downloadPath);
        }

        // Get the latest file
        File latestFile = Arrays.stream(dir.listFiles())
            .filter(File::isFile) // Only consider files
            .max(Comparator.comparingLong(File::lastModified)) // Get the most recent file
            .orElse(null);

        if (latestFile != null) {
            Log.info("Latest downloaded file: " + latestFile.getName());
        } else {
            Log.warn("No downloaded files found in directory: " + downloadPath);
        }

        return latestFile;
    }
    
    public void enterActionsDates(String Date) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        
        Log.info("Entering action dates: " + Date);

        try {
            WebElement ActionDateFrom = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateFrom);
            ActionDateFrom.click();
            Log.info("Clicked on 'Action Date From' field.");

            WebElement FromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.ActionDate_From_and_To_values(Date)));
            FromDate.click();
            Log.info("Selected From Date: " + Date);

            WebElement ActionDateTo = driver.findElement(CollectionAgencyAgentAccountAllocationRepo.ActionDateTo);
            ActionDateTo.click();
            Log.info("Clicked on 'Action Date To' field.");

            WebElement ToDate = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.ActionDate_From_and_To_value(Date)));
            ToDate.click();
            Log.info("Selected To Date: " + Date);

        } catch (Exception e) {
            Log.error("Error while entering action dates: " + e.getMessage());
            throw e; // Re-throw exception after logging
        } 
    }

    // Check if the latest file matches expected name & extension
    public boolean isLatestFileDownloaded(String expectedFilePrefix, String expectedExtension) {
    	File latestFile = getLatestDownloadedFile();

        if (latestFile == null) {
            Log.warn("No files found in the download directory.");
            return false;
        }

        String latestFileName = latestFile.getName();
        Log.info("Latest downloaded file: " + latestFileName);

        // Check if the latest file name matches the expected pattern
        boolean isPrefixMatch = latestFileName.startsWith(expectedFilePrefix);
        boolean isExtensionMatch = latestFileName.endsWith(expectedExtension);

        if (!isPrefixMatch) {
            Log.warn("File prefix mismatch: Expected '" + expectedFilePrefix + "', but found '" + latestFileName + "'");
        }

        if (!isExtensionMatch) {
            Log.warn("File extension mismatch: Expected '" + expectedExtension + "', but found '" + latestFileName + "'");
        }

        return isPrefixMatch && isExtensionMatch;
    }
    
 // Method to click the Close button and close the popup
    public void closePopup() {
    	try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
            WebElement popupCloseBtn = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.allocatedlistpopupclosebtn));
            popupCloseBtn.click();
            Log.info("Popup closed successfully.");
        } catch (TimeoutException e) {
            Log.warn("Popup close button was not clickable within the expected time.");
        } catch (NoSuchElementException e) {
            Log.warn("Popup close button was not found.");
        }
    }

    // Method to check if the popup is displayed
    public boolean isPopupDiplayed(boolean waitForClose) { 
    	try {
            if (waitForClose) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust time as needed
                return wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyAgentAccountAllocationRepo.allocatedlistpopup)).isDisplayed();
            } else {
                return !driver.findElements(CollectionAgencyAgentAccountAllocationRepo.allocatedlistpopup).isEmpty();
            }
        } catch (TimeoutException e) {
            Log.warn("Popup did not appear within the expected time.");
            return false;
        } catch (NoSuchElementException e) {
            Log.warn("Popup element was not found.");
            return false;
        }
    }
    
    // Method to click the Close button
    public void clickCloseButton() {
    	Log.info("Starting the process to click the Close button...");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Close button to become visible...");
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgencyAgentAccountAllocationRepo.Closebutton));

        Log.info("Clicking the Close button...");
        closeButton.click();
        
        Log.info("Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Close button clicked successfully and the spinner is no longer visible.");
    }
    
    public boolean isHomePage() {
    	Log.info("Checking if the current page is the Home Page...");

        String currentUrl = driver.getCurrentUrl(); // Get the current URL
        Log.info("Current URL: " + currentUrl);

        boolean isHome = currentUrl.toLowerCase().contains("home");

        if (isHome) {
            Log.info("User is on the Home Page.");
        } else {
            Log.warn("User is NOT on the Home Page.");
        }

        return isHome;
    }
}
