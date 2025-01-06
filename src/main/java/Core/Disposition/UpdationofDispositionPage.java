package Core.Disposition;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.Utility.DBUtils;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.UpdationofDispositionRepo;
import com.Utility.Log;

public class UpdationofDispositionPage {
	
	private WebDriver driver;
	private String AccoutNumber;
	public UpdationofDispositionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Log.info("UpdationofDispositionPage initialized.");
    }
	
	// Navigate to the Disposition Updation screen
    public void navigateToUpdationOfDisposition() {
		WebElement disposition = driver.findElement(UpdationofDispositionRepo.dispositionMenu);
		Log.info("Clicking on the Disposition.");
		disposition.click();
		Log.info("Successfully clicked on the Disposition.");
		WebElement UpdationofDisposition = driver.findElement(UpdationofDispositionRepo.updationOfDispositionMenu);
		Log.info("Clicking on the Updation of Disposition.");
		UpdationofDisposition.click();
		Log.info("Successfully clicked on the Updation of Disposition.");
    }
    
 // Interact with Account Number Field
    public void enterAccountNumber(String accountNumber) {
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
            Log.info("Should not possible to type Alphabets and special characters in account number field.");
    }
  }
    
 // Interact with Account Number Field
    public void enterAccountNumberwithmore25characters(String accountNumber) {
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
            Log.info("Entry is restricted to 25 characters.");
    }
  }
    
 // Interact with Account Number Field
    public void enterInvalidAccountNumber(String accountNumber) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	accountnumber.sendKeys(accountNumber);
    	// Verify if the value is actually entered (optional, for additional validation)
        if (accountnumber.getAttribute("value").equals(accountNumber)) {
            System.out.println("Account number entered successfully.");
            Log.info("Account number entered successfully.");
        } else {
            System.out.println("Not able to enter the account number.");
    }
  }
    
    public void withoutAccountNumber() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	accountnumber.clear();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
  }
    
 // Click Search
    public void clickSearchButton() {
    	WebElement searchButton = driver.findElement(UpdationofDispositionRepo.searchButton);
        searchButton.click();
    }
    
    // Get Error Message Text
    public String getErrorMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage8));
        return msg.getText();
    }
    
    // Get Error Message Text
    public String getErrorMessageforemptysearch() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage));
        return msg.getText();
    }

 // Interact with Account Number Field
    public String entervalidAccountNumber() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
    	String procedureCall = "{CALL SPGETBRANCHALLOCATEDACCOUNTS(?, ?)}";
        String userId = userid.getText(); // Input parameter
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        try {
            List<String> AcNo = DBUtils.callStoredProcedureWithRefCursor(procedureCall, userId);
            AccoutNumber = String.join(", ", AcNo);
            System.out.println("User Branch Account Number: " + AccoutNumber);
            accountnumber.clear();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
            accountnumber.sendKeys(AccoutNumber);
        	// Verify if the value is actually entered (optional, for additional validation)
            if (accountnumber.getAttribute("value").equals(AccoutNumber)) {
                System.out.println("Account number entered successfully.");
                Log.info("Account number entered successfully.");
            } else {
                System.out.println("Not able to enter the account number.");
        }
             
        } catch (Exception e) {
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }
        
        return AccoutNumber;
  }
    
    public void clickSaveButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement savebutton = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.savebutton));
    	savebutton.click();
    }
    
    public String getErrorMessagewithoutvalue() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage2));
        return errormessage.getText();
    }
    
    public void selectNextActionOwner(String owner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement actionOwnerdropdown = driver.findElement(UpdationofDispositionRepo.nextActionOwnerDropdown);
    	actionOwnerdropdown.click();
    	WebElement actionowner = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.nextActionOwnerDropdownvalues(owner)));
    	actionowner.click();
    }
    public String getErrorMessageafterenteronlyactionowner() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage3));
        return errormessage.getText();
    }
    
    public void selectDisposition(String disposition) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dispositiondropdown = driver.findElement(UpdationofDispositionRepo.dispositionDropdown);
    	dispositiondropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dispositionvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.dispositionDropdownvalues(disposition)));
    	dispositionvalue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
    public String getErrorMessageafterenterdisposition() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage4));
        return errormessage.getText();
    }
    
    public void selectsubDisposition(String subdisposition) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement subdispositiondropdown = driver.findElement(UpdationofDispositionRepo.subdispositionDropdown);
    	subdispositiondropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dispositionvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.dispositionDropdownvalues(subdisposition)));
    	dispositionvalue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
    public String getErrorMessageafterentersubdisposition() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage5));
        return errormessage.getText();
    }
    
    public void enterNextActionDate(String date) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement datepicker = driver.findElement(UpdationofDispositionRepo.datepicker);
    	datepicker.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.day(date)));
    	day.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    public String getErrorMessageafterenterdate() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement popupclose = wait.until(
                ExpectedConditions.elementToBeClickable(UpdationofDispositionRepo.deviationrequestpopupclose));
    	popupclose.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement errormessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage6));
        return errormessage.getText();
    }
    
    public void enterRemarks(String remarks) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement remark = driver.findElement(UpdationofDispositionRepo.remarks);
    	remark.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	remark.sendKeys(remarks);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
    public boolean isSuccessMessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement popupclose = wait.until(
        ExpectedConditions.elementToBeClickable(UpdationofDispositionRepo.deviationrequestpopupclose));
    	popupclose.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.successMessage));
        return successMessage.isDisplayed();
    }
    
    public void enterAccountNumber() {
    	
    	if (AccoutNumber == null) {
            System.err.println("Account number is not fetched yet. Ensure 'entervalidAccountNumber()' is executed first.");
            return;
        }
        WebElement accountnumberField = driver.findElement(UpdationofDispositionRepo.accountNumberField);
        accountnumberField.clear();
        accountnumberField.sendKeys(AccoutNumber); // Use the stored account number
        Log.info("Account number entered using the previously fetched value.");
    }
    
    public boolean isTransactionDisplayedWithExpectedDetails(String date, String userType) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	
        // Create a dynamic By locator using the provided date and userType
        By transactionHeadingLocator = UpdationofDispositionRepo.transactionHeading(date, userType);
        wait.until(ExpectedConditions.visibilityOfElementLocated(transactionHeadingLocator));
        try {
            // Check if the element is displayed
            return driver.findElement(transactionHeadingLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            // Handle the case where the element is not found
            return false;
        }
    }
    
    public void verifyInteractionDetails(String disposition, String subDisposition, String remarks, 
            String actionDoneBy, String userEIN, 
            String nextActionOwner, String nextActionDate) {
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    		
    		 // Verify Disposition
    	    Assert.assertTrue(
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsDisposition(disposition))).isDisplayed(),
    	        "Disposition value is not displayed: " + disposition);

    		// Verify Sub-Disposition
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsSubDisposition(subDisposition))).isDisplayed(),
    				"Sub-Disposition value is not displayed: " + subDisposition);

    		// Verify Remarks
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsRemarks(remarks))).isDisplayed(),
    				"Remarks value is not displayed: " + remarks);
    		
    		// Verify Action Done By
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsActionDoneBy(actionDoneBy))).isDisplayed(),
    				"Action Done By value is not displayed: " + actionDoneBy);

    		
    		// Verify User EIN
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsUserEIN(userEIN))).isDisplayed(),
    				"User EIN value is not displayed: " + userEIN);
    		
    		// Verify Next Action Owner
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsNextActionOwner(nextActionOwner))).isDisplayed(),
    				"Next Action Owner value is not displayed: " + nextActionOwner);

    		// Verify Next Action Date
    		Assert.assertTrue(
    				wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.InteractionDetailsNextActionDate(nextActionDate))).isDisplayed(),
    				"Next Action Date value is not displayed: " + nextActionDate);



    }
    
    public void enterinvalidAccountNumbernotassigned() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.loginuserid));
    	String procedureCall = "{CALL SP_GET_USER_OTHERBRANCH_ACCOUNTS(?, ?)}";
        String userId = userid.getText(); // Input parameter
    	WebElement accountnumber = driver.findElement(UpdationofDispositionRepo.accountNumberField);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        try {
            List<String> AcNo = DBUtils.callStoredProcedureWithRefCursor(procedureCall, userId);
            AccoutNumber = String.join(", ", AcNo);
            System.out.println("User Branch Account Number: " + AccoutNumber);
            accountnumber.sendKeys(AccoutNumber);
        	// Verify if the value is actually entered (optional, for additional validation)
            if (accountnumber.getAttribute("value").equals(AccoutNumber)) {
                System.out.println("Account number entered successfully.");
                Log.info("Account number entered successfully.");
            } else {
                System.out.println("Not able to enter the account number.");
        }
             
        } catch (Exception e) {
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }
    }
    
    public String getErrorMessageforinvalidAccountNumbernotassigned() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    	WebElement errormsg = wait.until(ExpectedConditions.visibilityOfElementLocated(UpdationofDispositionRepo.errorMessage7));
        return errormsg.getText();
    }
}