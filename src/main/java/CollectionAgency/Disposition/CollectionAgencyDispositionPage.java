package CollectionAgency.Disposition;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.CollectionAgencyDispositionRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CollectionAgencyDispositionPage extends Base_Class {
	
	private WebDriver driver;
	public static String AppType;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CORE_LOGIN_BANNER_DETAILS;
	
	public CollectionAgencyDispositionPage(WebDriver driver) {
		Log.info("Initializing CollectionAgencyDispositionPage...");
	    
	    this.driver = driver;
	    Log.info("Driver instance assigned.");

	    Log.info("Initializing web elements using PageFactory...");
	    PageFactory.initElements(driver, this);
	    
	    Log.info("CollectionAgencyDispositionPage initialized successfully.");
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
            Log.info("Table truncated successfully. Result : "+result+"");
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
        dpdDaysField.clear();
        dpdDaysField.sendKeys(days);
        Log.info("DPD Days value '" + days + "' entered successfully.");
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

    public String  clickDownloadButton() throws IOException {
    	Log.info("Starting the process to click the Download button...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        try {
            Log.info("Locating the Download button...");
            WebElement downloadButton = driver.findElement(CollectionAgencyDispositionRepo.downloadButton);

            Log.info("Checking if the Download button is enabled...");
            if (!downloadButton.isEnabled()) {
                String errorMessage = "Download button is present but not clickable. No data found in grid, hence Download button not enabled.";
                Log.error(errorMessage);
                Assert.fail(errorMessage); // Fails the test case
            }

            Log.info("Clicking on the Download button...");
            downloadButton.click();
            Log.info("Download button clicked successfully.");

            Log.info("Waiting for the download message to become visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.downloadmsg));
            Log.info("Download message is now visible.");

            Log.info("Waiting for the download message to disappear...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CollectionAgencyDispositionRepo.downloadmsg));
            Log.info("Download message disappeared, indicating completion.");

            Log.info("Retrieving an account number from the downloaded file...");
            String account_number = DownloadedExcelReader.getOneAccountNumber();
            Log.info("Account number retrieved: " + account_number);

            return account_number;
        } catch (NoSuchElementException e) {
            Log.error("Download button not found. It may not be present on the page.");
            Assert.fail("Download button not found. It may not be present on the page.");
        } catch (ElementNotInteractableException e) {
            String errorMessage = "Download button is present but not clickable. No data found in grid, hence Download button not enabled.";
            Log.error(errorMessage);
            Assert.fail(errorMessage);
        } catch (TimeoutException e) {
            Log.error("Timed out while waiting for download message.");
            Assert.fail("Timed out while waiting for download message.");
        } catch (Exception e) {
            Log.ErrorWithException("An unexpected error occurred while clicking the Download button.", e);
            Assert.fail("An unexpected error occurred while clicking the Download button: " + e.getMessage());
        }
        return null; // Return null in case of failure
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

    // Method to get the fetched account number
    public String getFetchedAccountNumber() throws SQLException, ClassNotFoundException, IOException {
    	Log.info("Fetching an account number from the database...");

        String selectQuery = "SELECT ACCOUNT_NO FROM mst_account " +
                             "WHERE IS_ACTIVE=1 AND ACCOUNT_NO NOT IN " +
                             "(SELECT ACCOUNT_NO FROM mst_col_agency_acc_allocated) " +
                             "ORDER BY 1 DESC FETCH FIRST 1 ROWS ONLY";
        
        try {
            Log.info("Executing SQL query: " + selectQuery);
            String result = DBUtils.executeSQLStatement(selectQuery);
            
            Log.info("Fetched account number: " + result);
            return result;
        } catch (SQLException e) {
            Log.ErrorWithException("Error occurred while fetching account number: " + e.getMessage(), e);
            throw new SQLException("Error occurred while fetching account number", e);
        }
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
                    Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);
                    
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

            SomeErrorOccuredHandling(userId,password);
            Thread.sleep(6000);
        } catch (Exception e) {
            Log.error("An error occurred in CollectionAgencyLogin: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    public void SomeErrorOccuredHandling(String userId, String password) {
        try {
            WebElement SomeErrorOccured = Common.waitForElementToBeClickable(
                driver, 
                LoginPageRepo.LoginPageSomeErrorOccurred, Duration.ofSeconds(20));

            if (SomeErrorOccured != null) {
                Log.info("Showing 'Some error occurred' message, reloading the application");
                driver.navigate().refresh();

                if (AppType.equals("CollectionAgency")) {
                    Common.fluentWait(CollectionAgency_BANNER_DETAILS, 
                        LoginPageRepo.CollectionAgencyLoginBannerDetails(CollectionAgency_BANNER_DETAILS));
                }

                Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
                Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
                Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

                driver.findElement(LoginPageRepo.UserNameField).sendKeys(userId);
                driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
                driver.findElement(LoginPageRepo.LoginButton).click();

                try {
                    WebElement clickableElement = Common.waitForElementToBeClickable(
                        driver, 
                        LoginPageRepo.AlreadyLoginPopupYesButton, 
                        Duration.ofSeconds(20)
                    );

                    if (clickableElement != null) {
                        clickableElement.click();
                        Common.waitForSpinnerToDisappear("Loading Spinner", LoginPageRepo.Spinner);

                        Common.fluentWait("UserNameField", LoginPageRepo.UserNameField);
                        Common.fluentWait("PasswordField", LoginPageRepo.PasswordField);
                        Common.fluentWait("LoginButton", LoginPageRepo.LoginButton);

                        driver.findElement(LoginPageRepo.UserNameField).sendKeys(userId);
                        driver.findElement(LoginPageRepo.PasswordField).sendKeys(password);
                        driver.findElement(LoginPageRepo.LoginButton).click();

                        Log.info("Clicked on already login yes button and logged in again with valid credentials");
                    } else {
                        System.out.println("Element not clickable within the timeout.");
                    }
                } catch (Exception e) {
                    System.out.println("Exception occurred while waiting for the element: " + e.getMessage());
                    System.out.println("Already login pop-up did not appear");
                }
            } else {
                System.out.println("'Some error occurred' message did not appear");
            }
        } catch (Exception e) {
            System.out.println("Exception in handling 'Some error occurred' scenario: " + e.getMessage());
        }
    }
    
 // Navigate to Disposition functionality
    public void navigateToDisposition() {
    	Log.info("Starting navigation to Disposition page...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Locating the Collection Agency menu...");
        WebElement CollectionAgency = driver.findElement(CollectionAgencyDispositionRepo.CollectionAgency);

        Log.info("Clicking on the Collection Agency menu...");
        CollectionAgency.click();
        Log.info("Collection Agency menu clicked successfully.");

        Log.info("Waiting for the Disposition option to become visible...");
        WebElement Disposition = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.Disposition));

        Log.info("Clicking on the Disposition option...");
        Disposition.click();
        Log.info("Disposition option clicked successfully. Navigation complete.");
    }
    
 // Method to verify if the updated disposition label is displayed
    public boolean isDispositionLabelDisplayed() {
    	Log.info("Checking if the Disposition label is displayed...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

        Log.info("Waiting for the Disposition label to become visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.UpdationofDispositionlabel));

        boolean isDisplayed = driver.findElement(CollectionAgencyDispositionRepo.UpdationofDispositionlabel).isDisplayed();
        Log.info("Disposition label displayed: " + isDisplayed);

        return isDisplayed;
    }
    
 // Method to enter account number
    public void enterAccountNumber(String accountNumber) {
    	Log.info("Starting the process to enter the account number...");

        Log.info("Waiting for the spinner to disappear, indicating the page is fully loaded...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        try {
            Log.info("Waiting for the Transaction Details button to become visible...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.TransactionDetails));
            Log.info("Transaction Details button is visible.");
        } catch (Exception e) {
        	Log.error("Transaction Details button not found. The page may not be loaded completely. Please try again.");
            throw new RuntimeException("Transaction Details button not found. The page may not be loaded completely. Please try again.", e);
        }

        Log.info("Waiting for the spinner to disappear again...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Locating the account number input field...");
        WebElement accountNumberElement = driver.findElement(CollectionAgencyDispositionRepo.AccountNumberfield);

        Log.info("Clearing the existing value in the account number field...");
        accountNumberElement.clear();

        Log.info("Entering the new account number...");
        accountNumberElement.sendKeys(accountNumber);

        Log.info("Account number entered successfully.");
    }

    // Method to click on the Search button
    public void clickSearchbtn() {
    	Log.info("Starting the process to click the Search button...");

        Log.info("Locating the Search button...");
        WebElement searchBtnElement = driver.findElement(CollectionAgencyDispositionRepo.Searchbutton);

        Log.info("Clicking the Search button...");
        searchBtnElement.click();

        Log.info("Search button clicked successfully.");
    }

    // Method to verify if the customer details grid is displayed
    public boolean isCustomerDetailsGridDisplayed() {
        Log.info("Starting the process to check if the Customer Details grid is displayed...");

        Log.info("Waiting for the spinner to disappear, indicating the page is fully loaded...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Waiting for the Account Number details element to become visible...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.Accountnumberdetails));

        Log.info("Checking if the Account Number details grid is displayed...");
        boolean isDisplayed = driver.findElement(CollectionAgencyDispositionRepo.Accountnumberdetails).isDisplayed();

        if (isDisplayed) {
            Log.info("Customer Details grid is displayed.");
        } else {
            Log.info("Customer Details grid is NOT displayed.");
        }

        return isDisplayed;
    }
    
    // Method to get validation message
    public String getValidationMessage() {
    	Log.info("Starting the process to retrieve the validation message...");

        Log.info("Waiting for the warning message for invalid account number to become visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.warningforinvalidaccountnumber));

        Log.info("Warning message for invalid account number is now visible. Retrieving the message text...");
        String validationMessage = driver.findElement(CollectionAgencyDispositionRepo.warningforinvalidaccountnumber).getText();

        Log.info("Validation message retrieved successfully: " + validationMessage);

        return validationMessage;
    }
    
 // Method to verify the presence of all required columns
    public boolean verifyAllColumnsPresent() {
    	Log.info("Starting the process to verify all columns are present...");

        boolean isCustomerNameDisplayed = driver.findElement(CollectionAgencyDispositionRepo.CustomerName).isDisplayed();
        Log.info("Customer Name column displayed: " + isCustomerNameDisplayed);

        boolean isAccountNumberDisplayed = driver.findElement(CollectionAgencyDispositionRepo.AccountNumber).isDisplayed();
        Log.info("Account Number column displayed: " + isAccountNumberDisplayed);

        boolean isDayPastDueDisplayed = driver.findElement(CollectionAgencyDispositionRepo.DayPastDue).isDisplayed();
        Log.info("Day Past Due column displayed: " + isDayPastDueDisplayed);

        boolean isProductTypeDisplayed = driver.findElement(CollectionAgencyDispositionRepo.ProductType).isDisplayed();
        Log.info("Product Type column displayed: " + isProductTypeDisplayed);

        boolean isTotalOverdueDisplayed = driver.findElement(CollectionAgencyDispositionRepo.TotalOverdue).isDisplayed();
        Log.info("Total Overdue column displayed: " + isTotalOverdueDisplayed);

        boolean isCriticalAmountDisplayed = driver.findElement(CollectionAgencyDispositionRepo.CriticalAmount).isDisplayed();
        Log.info("Critical Amount column displayed: " + isCriticalAmountDisplayed);

        boolean isOSAmountDisplayed = driver.findElement(CollectionAgencyDispositionRepo.OSAmount).isDisplayed();
        Log.info("OS Amount column displayed: " + isOSAmountDisplayed);

        boolean isOverdueDateDisplayed = driver.findElement(CollectionAgencyDispositionRepo.OverdueDate).isDisplayed();
        Log.info("Overdue Date column displayed: " + isOverdueDateDisplayed);

        boolean isRiskDisplayed = driver.findElement(CollectionAgencyDispositionRepo.Risk).isDisplayed();
        Log.info("Risk column displayed: " + isRiskDisplayed);

        boolean isSecurityDetailsDisplayed = driver.findElement(CollectionAgencyDispositionRepo.SecurityDetails).isDisplayed();
        Log.info("Security Details column displayed: " + isSecurityDetailsDisplayed);

        // Return the result of checking all columns
        boolean allColumnsDisplayed = isCustomerNameDisplayed &&
                                      isAccountNumberDisplayed &&
                                      isDayPastDueDisplayed &&
                                      isProductTypeDisplayed &&
                                      isTotalOverdueDisplayed &&
                                      isCriticalAmountDisplayed &&
                                      isOSAmountDisplayed &&
                                      isOverdueDateDisplayed &&
                                      isRiskDisplayed &&
                                      isSecurityDetailsDisplayed;

        Log.info("All columns displayed: " + allColumnsDisplayed);

        return allColumnsDisplayed;
    }
    
 // Methods to interact with the page
    public void clickViewButton() {
    	Log.info("Starting the process to click the View button...");

        Log.info("Waiting for the spinner to disappear, indicating the page is ready...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Locating the View button...");
        WebElement viewButton = driver.findElement(CollectionAgencyDispositionRepo.Viewlink);

        Log.info("Clicking the View button...");
        viewButton.click();

        Log.info("View button clicked. Waiting for the spinner to disappear...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Spinner is now invisible. View button action completed.");
    }

    public boolean isSecurityDetailsGridDisplayed() {
    	Log.info("Starting the process to check if the Security Details grid is displayed...");

        Log.info("Waiting for the Security Details grid to become visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.securityDetailsGridLocator));

        Log.info("Security Details grid is now visible. Checking if it is displayed...");
        boolean isDisplayed = driver.findElement(CollectionAgencyDispositionRepo.securityDetailsGridLocator).isDisplayed();

        if (isDisplayed) {
            Log.info("Security Details grid is displayed.");
        } else {
            Log.info("Security Details grid is NOT displayed.");
        }

        return isDisplayed;
    }

    public boolean verifyFieldsInGrid() {
    	Log.info("Starting the process to verify fields in the grid...");

        WebElement closebutton = driver.findElement(CollectionAgencyDispositionRepo.closebutton);

        String[] expectedFields = {"SECURITY ID", "SECURITY TYPE", "SECURITY DESCRIPTION", "SECURITY VALUE", 
                                   "PRESENT VALUE", "SECURITY VALUATION DATE", "IS COLLATERAL"};
        
        Log.info("Verifying the following fields in the grid: " + String.join(", ", expectedFields));

        // Loop through each expected field and verify its presence in the grid
        for (String field : expectedFields) {
            Log.info("Checking if the field '" + field + "' is present in the grid...");
            if (driver.findElements(CollectionAgencyDispositionRepo.FieldsInGrid(field)).isEmpty()) {
                Log.error("Field '" + field + "' is missing from the grid.");
                return false; // Return false if any field is missing
            }
            Log.info("Field '" + field + "' is present in the grid.");
        }

        Log.info("All expected fields are present in the grid. Proceeding to click the close button.");
        
        // Click the close button after verification
        closebutton.click();
        
        Log.info("Close button clicked. Process completed successfully.");
        
        return true; // Return true if all fields are present
    }
    
    // Method to select Next Action Owner
    public void selectNextActionOwner(String option) {
    	Log.info("Starting the process to select the next action owner...");

        Log.info("Waiting for the spinner to disappear, indicating the page is ready...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Locating the Next Action Owner dropdown...");
        WebElement nextActionOwnerDropdown = driver.findElement(CollectionAgencyDispositionRepo.nextActionOwnerDropdown);

        Log.info("Clicking the Next Action Owner dropdown...");
        nextActionOwnerDropdown.click();

        Log.info("Waiting for the option '" + option + "' to become visible in the dropdown...");
        WebElement nextActionOwnerDropdownvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.accountType_and_collectionAgency_value(option)));

        Log.info("Selecting the option '" + option + "' from the dropdown...");
        nextActionOwnerDropdownvalue.click();

        Log.info("Option '" + option + "' selected successfully.");
    }

    // Method to fetch the selected option text from the dropdown for verification
    public String getSelectedNextActionOwner() {
    	Log.info("Starting the process to get the selected next action owner...");

        Log.info("Locating the Next Action Owner dropdown...");
        WebElement nextActionOwnerDropdown = driver.findElement(CollectionAgencyDispositionRepo.nextActionOwnerDropdown);

        Log.info("Retrieving the selected value from the Next Action Owner dropdown...");
        String selectedNextActionOwner = nextActionOwnerDropdown.getText();

        Log.info("Selected Next Action Owner: " + selectedNextActionOwner);

        return selectedNextActionOwner;
    }
    
    public void selectDisposition(String disposition) {
    	Log.info("Starting the process to select a disposition...");

        Log.info("Locating the Disposition dropdown...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement DispositionDropdown = driver.findElement(CollectionAgencyDispositionRepo.DispositionDropdown);

        Log.info("Clicking the Disposition dropdown...");
        DispositionDropdown.click();

        Log.info("Waiting for the disposition option '" + disposition + "' to become visible...");
        WebElement Disposition_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.accountType_and_collectionAgency_value(disposition)));

        Log.info("Selecting the disposition option '" + disposition + "'...");
        Disposition_value.click();

        Log.info("Waiting for the spinner to disappear, indicating the page is ready...");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        Log.info("Disposition '" + disposition + "' selected successfully.");
    }

    public void selectSubDisposition(String subDisposition) {
    	Log.info("Starting the process to select a sub-disposition...");

        Log.info("Locating the Sub-Disposition dropdown...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement SubDispositionDropdown = driver.findElement(CollectionAgencyDispositionRepo.SubDispositionDropdown);

        Log.info("Clicking the Sub-Disposition dropdown...");
        SubDispositionDropdown.click();

        Log.info("Waiting for the sub-disposition option '" + subDisposition + "' to become visible...");
        WebElement Sub_Disposition_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.accountType_and_collectionAgency_value(subDisposition)));

        Log.info("Selecting the sub-disposition option '" + subDisposition + "'...");
        Sub_Disposition_value.click();

        Log.info("Sub-disposition '" + subDisposition + "' selected successfully.");
    }

    public void enterNextActionDate(String date) {
    	Log.info("Starting the process to enter the next action date...");

        Log.info("Locating the Next Action Date picker...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement NextActionDatePicker = driver.findElement(CollectionAgencyDispositionRepo.NextActionDatePicker);

        Log.info("Clicking the Next Action Date picker...");
        NextActionDatePicker.click();

        Log.info("Waiting for the date '" + date + "' to become visible...");
        WebElement date_value = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.FieldsInGrid(date)));

        Log.info("Selecting the date '" + date + "' from the date picker...");
        date_value.click();

        Log.info("Next action date '" + date + "' entered successfully.");
    }

    public void enterRemarks(String remarks) {
    	Log.info("Starting the process to enter remarks...");

        Log.info("Entering the following remarks: " + remarks);
        driver.findElement(CollectionAgencyDispositionRepo.Remarks).clear();
        driver.findElement(CollectionAgencyDispositionRepo.Remarks).sendKeys(remarks);

        Log.info("Remarks entered successfully.");
    }

    public void clickSaveButton() {
    	Log.info("Starting the process to click the Save button...");

        Log.info("Clicking the Save button...");
        driver.findElement(CollectionAgencyDispositionRepo.Savebutton).click();

        Log.info("Save button clicked successfully.");
    }

    public String getConfirmationMessage() {
    	Log.info("Starting the process to get the confirmation message...");

        Log.info("Waiting for the success message to become visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement Successmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.Successmsg));

        Log.info("Success message is visible. Retrieving the message text...");
        String confirmationMessage = Successmsg.getText();

        Log.info("Confirmation message retrieved: " + confirmationMessage);
        return confirmationMessage;
    }
    
    // Method to click cancel button
    public void clickCancel() {
    	Log.info("Starting the process to click the Cancel button...");

        Log.info("Clicking the Cancel button...");
        driver.findElement(CollectionAgencyDispositionRepo.Cancelbutton).click();

        Log.info("Cancel button clicked successfully.");
    }

    // Method to verify fields are reset
    public boolean areFieldsEmptyOrDefault() {
    	Log.info("Starting the process to check if any fields are empty or set to their default value...");

        WebElement nextActionOwnerDropdown = driver.findElement(CollectionAgencyDispositionRepo.nextActionOwnerDropdown);
        WebElement DispositionDropdown = driver.findElement(CollectionAgencyDispositionRepo.DispositionDropdown);
        WebElement SubDispositionDropdown = driver.findElement(CollectionAgencyDispositionRepo.SubDispositionDropdown);
        WebElement datePickerField = driver.findElement(CollectionAgencyDispositionRepo.NextActionDatePicker);
        WebElement textField = driver.findElement(CollectionAgencyDispositionRepo.Remarks);
        WebElement[] elements = {nextActionOwnerDropdown, DispositionDropdown, SubDispositionDropdown, datePickerField, textField};

        for (WebElement element : elements) {
            String text = element.getText().trim(); // Get the visible text of the element
            Log.info("Checking field: " + element.toString());
            Log.info("Field text: '" + text + "'");

            // Check if the field is empty or contains "Select" or contains "DD-MM-YYYY"
            if (text.isEmpty() || text.equalsIgnoreCase("Select") || text.equalsIgnoreCase("DD-MM-YYYY")) {
                Log.info("Field is empty or contains a default value (e.g., 'Select' or 'DD-MM-YYYY').");
                return true; // Return true if any field is empty or contains a default value
            }
        }

        Log.info("All fields have valid values. None are empty or set to default.");
        return false; // Return false only if all fields have valid values
    }
    
 // Method to set value in Committed Amount field
    public void setCommittedAmount(String amount) {
    	Log.info("Starting the process to set the committed amount...");

        Log.info("Locating the Committed Amount field...");
        WebElement CommittedAmountfield = driver.findElement(CollectionAgencyDispositionRepo.CommittedAmountfield);

        Log.info("Clearing the Committed Amount field...");
        CommittedAmountfield.clear();

        Log.info("Entering the committed amount: " + amount);
        CommittedAmountfield.sendKeys(amount);

        Log.info("Committed amount set to: " + amount);
    }
    
    public String getvalidationMessage() {
    	Log.info("Starting the process to get the validation message...");

        Log.info("Locating the Committed Amount field to retrieve validation message...");
        WebElement CommittedAmountfield = driver.findElement(CollectionAgencyDispositionRepo.CommittedAmountfield);

        Log.info("Retrieving the validation message for the Committed Amount field...");
        String validationmsg = CommittedAmountfield.getAttribute("validationMessage");

        Log.info("Validation message retrieved: " + validationmsg);
        return validationmsg;
    }
    
 // Methods to get the text of the elements
    public String getCustomerId() throws InterruptedException {
    	Log.info("Starting the process to retrieve the customer ID...");
        Log.info("Locating the customer ID element...");
        WebElement customerId = driver.findElement(CollectionAgencyDispositionRepo.customerId);
        
        Log.info("Scrolling the customer ID element into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", customerId);
        
        Log.info("Customer ID element scrolled into view successfully.");
        Log.info("Waiting for 5 seconds to ensure the customer ID is fully visible...");
        Thread.sleep(5000);
        
        Log.info("Retrieving the customer ID text...");
        String customerIdText = customerId.getText();
        
        Log.info("Customer ID retrieved successfully: " + customerIdText);
        return customerIdText;
    }

    public String getZone() {
    	Log.info("Starting the process to retrieve the zone...");
        Log.info("Locating the zone element...");
        WebElement zone = driver.findElement(CollectionAgencyDispositionRepo.zone);
        
        Log.info("Retrieving the zone text...");
        String zoneText = zone.getText();
        
        Log.info("Zone retrieved successfully: " + zoneText);
        return zoneText;
    }

    public String getRegion() {
    	Log.info("Starting the process to retrieve the region...");
        Log.info("Locating the region element...");
        WebElement region = driver.findElement(CollectionAgencyDispositionRepo.region);
        
        Log.info("Retrieving the region text...");
        String regionText = region.getText();
        
        Log.info("Region retrieved successfully: " + regionText);
        return regionText;
    }

    public String getBranch() {
    	Log.info("Starting the process to retrieve the branch...");
        Log.info("Locating the branch element...");
        WebElement branch = driver.findElement(CollectionAgencyDispositionRepo.branch);
        
        Log.info("Retrieving the branch text...");
        String branchText = branch.getText();
        
        Log.info("Branch retrieved successfully: " + branchText);
        return branchText;
    }

    public String getSolId() {
    	Log.info("Starting the process to retrieve the Sol ID...");
        Log.info("Locating the Sol ID element...");
        WebElement solId = driver.findElement(CollectionAgencyDispositionRepo.solId);
        
        Log.info("Retrieving the Sol ID text...");
        String solIdText = solId.getText();
        
        Log.info("Sol ID retrieved successfully: " + solIdText);
        return solIdText;
    }

    public String getPrincipalOverdue() {
    	Log.info("Starting the process to retrieve the Principal Overdue...");
        Log.info("Locating the Principal Overdue element...");
        WebElement principalOverdue = driver.findElement(CollectionAgencyDispositionRepo.principalOverdue);
        
        Log.info("Retrieving the Principal Overdue text...");
        String principalOverdueText = principalOverdue.getText();
        
        Log.info("Principal Overdue retrieved successfully: " + principalOverdueText);
        return principalOverdueText;
    }

    public String getInterestOverdue() {
    	Log.info("Starting the process to retrieve the Interest Overdue...");
        Log.info("Locating the Interest Overdue element...");
        WebElement interestOverdue = driver.findElement(CollectionAgencyDispositionRepo.interestOverdue);
        
        Log.info("Retrieving the Interest Overdue text...");
        String interestOverdueText = interestOverdue.getText();
        
        Log.info("Interest Overdue retrieved successfully: " + interestOverdueText);
        return interestOverdueText;
    }

    public String getChargesDue() {
    	Log.info("Starting the process to retrieve the Charges Due...");
        Log.info("Locating the Charges Due element...");
        WebElement chargesDue = driver.findElement(CollectionAgencyDispositionRepo.chargesDue);
        
        Log.info("Retrieving the Charges Due text...");
        String chargesDueText = chargesDue.getText();
        
        Log.info("Charges Due retrieved successfully: " + chargesDueText);
        return chargesDueText;
    }

    public String getAccountType() {
    	Log.info("Starting the process to retrieve the Account Type...");
        Log.info("Locating the Account Type element...");
        WebElement accountType = driver.findElement(CollectionAgencyDispositionRepo.accountType);
        
        Log.info("Retrieving the Account Type text...");
        String accountTypeText = accountType.getText();
        
        Log.info("Account Type retrieved successfully: " + accountTypeText);
        return accountTypeText;
    }

    public String getCategory() {
    	Log.info("Starting the process to retrieve the Category...");
        Log.info("Locating the Category element...");
        WebElement category = driver.findElement(CollectionAgencyDispositionRepo.category);
        
        Log.info("Retrieving the Category text...");
        String categoryText = category.getText();
        
        Log.info("Category retrieved successfully: " + categoryText);
        return categoryText;
    }

    public String getVertical() {
    	Log.info("Starting the process to retrieve the Vertical...");
        Log.info("Locating the Vertical element...");
        WebElement vertical = driver.findElement(CollectionAgencyDispositionRepo.vertical);
        
        Log.info("Retrieving the Vertical text...");
        String verticalText = vertical.getText();
        
        Log.info("Vertical retrieved successfully: " + verticalText);
        return verticalText;
    }

    public String getTotalCollection() {
    	Log.info("Starting the process to retrieve the Total Collection...");
        Log.info("Locating the Total Collection element...");
        WebElement totalCollection = driver.findElement(CollectionAgencyDispositionRepo.totalCollection);
        
        Log.info("Retrieving the Total Collection text...");
        String totalCollectionText = totalCollection.getText();
        
        Log.info("Total Collection retrieved successfully: " + totalCollectionText);
        return totalCollectionText;
    }

    public String getContactNumber() {
    	Log.info("Starting the process to retrieve the Contact Number...");
        Log.info("Locating the Contact Number element...");
        WebElement contactNumber = driver.findElement(CollectionAgencyDispositionRepo.contactNumber);
        
        Log.info("Retrieving the Contact Number text...");
        String contactNumberText = contactNumber.getText();
        
        Log.info("Contact Number retrieved successfully: " + contactNumberText);
        return contactNumberText;
    }

    public String getAlternateNumber() {
    	Log.info("Starting the process to retrieve the Alternate Number...");
        Log.info("Locating the Alternate Number element...");
        WebElement alternateNumber = driver.findElement(CollectionAgencyDispositionRepo.alternateNumber);
        
        Log.info("Retrieving the Alternate Number text...");
        String alternateNumberText = alternateNumber.getText();
        
        Log.info("Alternate Number retrieved successfully: " + alternateNumberText);
        return alternateNumberText;
    }

    public String getAddress() {
    	Log.info("Starting the process to retrieve the Address...");
        Log.info("Locating the Address element...");
        WebElement address = driver.findElement(CollectionAgencyDispositionRepo.address);
        
        Log.info("Retrieving the Address text...");
        String addressText = address.getText();
        
        Log.info("Address retrieved successfully: " + addressText);
        return addressText;
    }

    public String getProfitNpaStatus() {
    	Log.info("Starting the process to retrieve the Profit NPA Status...");
        Log.info("Locating the Profit NPA Status element...");
        WebElement profitNpaStatus = driver.findElement(CollectionAgencyDispositionRepo.profitNpaStatus);
        
        Log.info("Retrieving the Profit NPA Status text...");
        String profitNpaStatusText = profitNpaStatus.getText();
        
        Log.info("Profit NPA Status retrieved successfully: " + profitNpaStatusText);
        return profitNpaStatusText;
    }
    
    // Methods to verify the presence and relevancy of each column
    public boolean isAccountNumberColumnDisplayed() throws InterruptedException {
    	Log.info("Starting the process to check if the Account Number column is displayed...");
        
        Log.info("Locating the Committed Amount field element...");
        WebElement CommittedAmountfield = driver.findElement(CollectionAgencyDispositionRepo.CommittedAmountfield);
        
        Log.info("Scrolling the Committed Amount field element into view...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", CommittedAmountfield);
        
        Log.info("Waiting for 5 seconds to ensure the page is fully loaded...");
        Thread.sleep(5000);
        
        Log.info("Locating the Account Number column element...");
        WebElement accountNumberColumn = driver.findElement(CollectionAgencyDispositionRepo.accountNumberColumn);
        
        Log.info("Checking if the Account Number column is displayed...");
        boolean isDisplayed = accountNumberColumn.isDisplayed();
        
        Log.info("Account Number column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isProductColumnDisplayed() {
    	Log.info("Starting the process to check if the Product column is displayed...");
        
        Log.info("Locating the Product column element...");
        WebElement productColumn = driver.findElement(CollectionAgencyDispositionRepo.productColumn);
        
        Log.info("Checking if the Product column is displayed...");
        boolean isDisplayed = productColumn.isDisplayed();
        
        Log.info("Product column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isOutstandingBalanceColumnDisplayed() {
    	Log.info("Starting the process to check if the Outstanding Balance column is displayed...");
        
        Log.info("Locating the Outstanding Balance column element...");
        WebElement outstandingBalanceColumn = driver.findElement(CollectionAgencyDispositionRepo.outstandingBalanceColumn);
        
        Log.info("Checking if the Outstanding Balance column is displayed...");
        boolean isDisplayed = outstandingBalanceColumn.isDisplayed();
        
        Log.info("Outstanding Balance column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isTotalOverdueColumnDisplayed() {
    	Log.info("Starting the process to check if the Total Overdue column is displayed...");
        
        Log.info("Locating the Total Overdue column element...");
        WebElement totalOverdueColumn = driver.findElement(CollectionAgencyDispositionRepo.totalOverdueColumn);
        
        Log.info("Checking if the Total Overdue column is displayed...");
        boolean isDisplayed = totalOverdueColumn.isDisplayed();
        
        Log.info("Total Overdue column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isTotalCollectionColumnDisplayed() {
    	Log.info("Starting the process to check if the Total Collection column is displayed...");
        
        Log.info("Locating the Total Collection column element...");
        WebElement totalCollectionColumn = driver.findElement(CollectionAgencyDispositionRepo.totalCollectionColumn);
        
        Log.info("Checking if the Total Collection column is displayed...");
        boolean isDisplayed = totalCollectionColumn.isDisplayed();
        
        Log.info("Total Collection column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isOverdueDateColumnDisplayed() {
    	Log.info("Starting the process to check if the Overdue Date column is displayed...");
        
        Log.info("Locating the Overdue Date column element...");
        WebElement overdueDateColumn = driver.findElement(CollectionAgencyDispositionRepo.overdueDateColumn);
        
        Log.info("Checking if the Overdue Date column is displayed...");
        boolean isDisplayed = overdueDateColumn.isDisplayed();
        
        Log.info("Overdue Date column is displayed: " + isDisplayed);
        return isDisplayed;
    }

    public boolean isDaysPastDueColumnDisplayed() {
    	Log.info("Starting the process to check if the Days Past Due column is displayed...");
        
        Log.info("Locating the Days Past Due column element...");
        WebElement daysPastDueColumn = driver.findElement(CollectionAgencyDispositionRepo.daysPastDueColumn);
        
        Log.info("Checking if the Days Past Due column is displayed...");
        boolean isDisplayed = daysPastDueColumn.isDisplayed();
        
        Log.info("Days Past Due column is displayed: " + isDisplayed);
        return isDisplayed;
    }
    
    // Method to get the error message text
    public String getRemarksErrorMessage() {
    	Log.info("Starting the process to retrieve the Remarks error message...");
        
        Log.info("Waiting for the Remarks validation message element to become visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        WebElement remarksvalidationmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgencyDispositionRepo.remarksvalidationmsg));
        
        Log.info("Retrieving the Remarks error message text...");
        String remarksErrorMessage = remarksvalidationmsg.getText();
        
        Log.info("Remarks error message retrieved successfully: " + remarksErrorMessage);
        return remarksErrorMessage;
    }
    
 // Method to get the text of the Risk column
    public String getRiskColumnValue() {
    	Log.info("Starting the process to retrieve the Risk column value...");
        
        Log.info("Locating the Risk column element...");
        WebElement riskColumn = driver.findElement(CollectionAgencyDispositionRepo.riskColumn);
        
        Log.info("Retrieving the Risk column text...");
        String riskColumnValue = riskColumn.getText().trim();
        
        Log.info("Risk column value retrieved successfully: " + riskColumnValue);
        return riskColumnValue;
    }
}
