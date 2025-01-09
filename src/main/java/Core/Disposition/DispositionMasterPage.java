package Core.Disposition;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.BasePackage.Common;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class DispositionMasterPage {
	
	private WebDriver driver;
	
	// Constructor
	public DispositionMasterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements
        Log.info("DispositionMasterPage initialized.");
    }
	
	public WebElement getDispositionMainMenu() {
		WebElement mainmenu = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
	    return mainmenu;
	}

	public void clickOnDispositionMainMenu() throws InterruptedException {
		WebElement mainmenu = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
		Log.info("Clicking on the Disposition.");
		mainmenu.click();
		Log.info("Successfully clicked on the Disposition.");
		Thread.sleep(10000);
	}

	public void clickOnDispositionMasterSubMenu() throws InterruptedException {
		WebElement submenu = driver.findElement(DispositionMasterPageRepo.dispositionMasterSubMenu);
		Log.info("Clicking on the Disposition Master.");
		submenu.click();
		Log.info("Successfully clicked on the Disposition Master.");
		Thread.sleep(30000);
	}

	//Method to verify if the Disposition Master page is loaded
	public boolean isDispositionMasterPageDisplayed() {
		WebElement masterheader = driver.findElement(DispositionMasterPageRepo.dispositionMasterHeader);
		return masterheader.isDisplayed();
	}

	public boolean isDispositionDisplayed() {
		WebElement disposition = driver.findElement(DispositionMasterPageRepo.dispositionMainMenu);
		return disposition.isDisplayed();
	}

	public boolean isSubDispositionDisplayed() {
		WebElement subdisposition = driver.findElement(DispositionMasterPageRepo.Sub_Disposition);
		return subdisposition.isDisplayed();
	}
	public int getActiveDispositionsCount() throws InterruptedException {
		// Replace the locator below with the actual locator for active disposition rows
		Thread.sleep(30000);

		// Find all active disposition rows
		return driver.findElements(DispositionMasterPageRepo.subDispositionList).size();
	}

	//Method to select Action Owner from dropdown
	public void selectActionOwnerOptions() {
		WebElement actionownerdropdown = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);
		Log.info("Opening the Action Owner dropdown.");
		actionownerdropdown.click();
		WebElement callCentreOption = driver.findElement(DispositionMasterPageRepo.callCentreOption);
		WebElement collectionAgencyOption = driver.findElement(DispositionMasterPageRepo.collectionAgencyOption);
		WebElement internalUserOption = driver.findElement(DispositionMasterPageRepo.internalUserOption);
		Log.info("Selecting 'Call Centre' option.");
		callCentreOption.click();
		Log.info("Selecting 'Collection Agency' option.");
		collectionAgencyOption.click();
		Log.info("Selecting 'Internal User' option.");
		internalUserOption.click();
		Log.info("Action Owner options selected successfully.");
	}

	public boolean isIsActiveCheckboxChecked() {
		WebElement activecheckbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox);
		return activecheckbox.isSelected();
	}

	// Method to verify action column options
	public boolean verifyActionOptions() {
		WebElement actioncolumnbutton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);

		actioncolumnbutton.click();
		// Check for Edit and Activate/Deactivate options

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			// Wait for the options to appear
			WebElement editElement = wait.until(ExpectedConditions.presenceOfElementLocated(DispositionMasterPageRepo.editOption));
			WebElement activateDeactivateElement = wait.until(ExpectedConditions.presenceOfElementLocated(DispositionMasterPageRepo.activateDeactivateOption));
			// Verify both options are displayed
			return editElement.isDisplayed() && activateDeactivateElement.isDisplayed();
		} catch (Exception e) {
			System.out.println("Required options not found: " + e.getMessage());
			return false;
		}
	}

	public boolean areAllStatusIconsGreenTicks() {
		
		List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
		
		for (WebElement icon : statusIcons) {
			if (!icon.getAttribute("style").contains("color: green")) {
				System.out.println("Icon is not in a green tick: " + icon.getAttribute("style"));
				return false;
			}
			else {
				System.out.println("All items are in a green tick: ");
				
			}

		}
		return true;

	}
	
	public boolean isPaginationCorrect() {
		WebElement pagination = driver.findElement(DispositionMasterPageRepo.pagination);
		
		WebElement previousButton = pagination.findElement(DispositionMasterPageRepo.nextButton);
        WebElement page1Button = pagination.findElement(DispositionMasterPageRepo.page1Button);
        WebElement nextButton = pagination.findElement(DispositionMasterPageRepo.paginationNextButton);
        WebElement doubleArrowButton = pagination.findElement(DispositionMasterPageRepo.lastPageButton);
     // Get the 'class' attribute of the element
        String classAttribute = previousButton.getAttribute("class");
     // Check if the class contains the specific class name
        boolean containsClassName = classAttribute.contains("page-item disabled ");
     // Verify Previous button is disabled
     // Assert that the class does contain the specific class name
        Assert.assertTrue(containsClassName, "Element contains the undesired class");
     // Verify Page 1 is selected
        Assert.assertTrue(page1Button.isDisplayed(), "Page 1 button is not selected.");
     // Verify Next button is enabled
        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
     // Verify Double Arrow (>>) button is enabled
        Assert.assertTrue(doubleArrowButton.isEnabled(), "Double Arrow button is not enabled.");
        return pagination.getText().contains("Previous") && pagination.getText().contains("1") && !pagination.getText().contains("<<");
        
    }
	
	public void clickNextPagination() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement paginationnextbutton = driver.findElement(DispositionMasterPageRepo.paginationNextButton);
		WebElement page2Button = paginationnextbutton.findElement(DispositionMasterPageRepo.page2);
        WebElement previousButton = paginationnextbutton.findElement(DispositionMasterPageRepo.previousButton);
        WebElement nextButton = paginationnextbutton.findElement(DispositionMasterPageRepo.paginationNextButton);
        WebElement nextdoubleArrowButton = paginationnextbutton.findElement(DispositionMasterPageRepo.lastPageButton);
        WebElement previousdoubleArrowButton = paginationnextbutton.findElement(DispositionMasterPageRepo.firstpage);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", paginationnextbutton);
        Thread.sleep(500);
        paginationnextbutton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
     // Verify Page 2 is selected
        Assert.assertTrue(page2Button.isDisplayed(), "Page 2 button is not selected.");
     // Verify Previous button is enabled
        Assert.assertTrue(previousButton.isEnabled(), "Previous button is not enabled.");
     // Verify Next button is enabled
        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
     // Verify Double Arrow (>>) button is enabled
        Assert.assertTrue(nextdoubleArrowButton.isEnabled(), "Next Double Arrow button is not enabled.");
     // Verify Double Arrow (<<) button is enabled
        Assert.assertTrue(previousdoubleArrowButton.isEnabled(), "Previous Double Arrow button is not enabled.");

	}

	public void clickPreviousPagination() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement previousbutton = driver.findElement(DispositionMasterPageRepo.previousButton);
		WebElement paginationNextButton = driver.findElement(DispositionMasterPageRepo.paginationNextButton);
		WebElement page1 = previousbutton.findElement(DispositionMasterPageRepo.page1Button);
        WebElement previousBut = previousbutton.findElement(DispositionMasterPageRepo.nextButton);
        WebElement nextButton = previousbutton.findElement(DispositionMasterPageRepo.paginationNextButton);
        WebElement nextdoubleArrowButton = previousbutton.findElement(DispositionMasterPageRepo.lastPageButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", paginationNextButton);
        previousbutton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
     // Verify Page 1 is selected
        Assert.assertTrue(page1.isDisplayed(), "Page 1 is not selected.");
     // Verify Previous button is enabled
        String classAttribute = previousBut.getAttribute("class");
        // Check if the class contains the specific class name
           boolean containsClassName = classAttribute.contains("page-item disabled ");
        // Verify Previous button is disabled
        // Assert that the class does contain the specific class name
           Assert.assertTrue(containsClassName, "Element contains the undesired class");
     // Verify Next button is enabled
        Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
     // Verify Double Arrow (>>) button is enabled
        Assert.assertTrue(nextdoubleArrowButton.isEnabled(), "Next Double Arrow button is not enabled.");
     /// Verify Double Arrow (<<) button is not visible
        List<WebElement> previousdoubleArrowButtons = driver.findElements(DispositionMasterPageRepo.previousDoubleArrowButtons);
        if (previousdoubleArrowButtons.isEmpty()) {
            // Element is not in the DOM
            System.out.println("The '<<' button is not present in the DOM.");
        } else {
            // Element exists; check visibility
            Assert.assertFalse(previousdoubleArrowButtons.get(0).isDisplayed(), "'<<' button is visible, but it should not be.");
        }

	}
	
	public void clickLastPageButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement lastPageButton = driver.findElement(DispositionMasterPageRepo.lastPageButton);
        lastPageButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement nextButton = driver.findElement(DispositionMasterPageRepo.nextButton);
		WebElement previouspage = driver.findElement(DispositionMasterPageRepo.previouspage);
		WebElement firstpage = driver.findElement(DispositionMasterPageRepo.firstpage);
     // Verify Next button is enabled
        String classAttribute = nextButton.getAttribute("class");
        // Check if the class contains the specific class name
           boolean containsClassName = classAttribute.contains("page-item disabled ");
        // Verify Previous button is disabled
        // Assert that the class does contain the specific class name
           Assert.assertTrue(containsClassName, "Element contains the undesired class");
           
           List<WebElement> lastPageArrowButtons = driver.findElements(DispositionMasterPageRepo.lastPageArrowButton);
           if (lastPageArrowButtons.isEmpty()) {
               // Element is not in the DOM
               System.out.println("The '>>' button is not present in the DOM.");
           } else {
               // Element exists; check visibility
               Assert.assertFalse(lastPageArrowButtons.get(0).isDisplayed(), "'>>' button is visible, but it should not be.");
           }
           
        // Verify Previous button is enabled
           Assert.assertTrue(previouspage.isEnabled(), "Previous button is not enabled."); 
           
        // Verify << button is enabled
           Assert.assertTrue(firstpage.isEnabled(), "Previous button is not enabled."); 
     
    }
	
	public void clickFirstPageButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement firstpage = driver.findElement(DispositionMasterPageRepo.firstpage);
		WebElement previousButton = driver.findElement(DispositionMasterPageRepo.previousButton);
		WebElement nextButton = driver.findElement(DispositionMasterPageRepo.nextButton);
		WebElement lastPageButton = driver.findElement(DispositionMasterPageRepo.lastPageButton);
		firstpage.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement previousBut = previousButton.findElement(DispositionMasterPageRepo.nextButton);
		// Verify Previous button is enabled
        String classAttribute = previousBut.getAttribute("class");
        // Check if the class contains the specific class name
           boolean containsClassName = classAttribute.contains("page-item disabled ");
        // Verify Previous button is disabled
        // Assert that the class does contain the specific class name
           Assert.assertTrue(containsClassName, "Element contains the undesired class");
           
           List<WebElement> previousdoubleArrowButtons = driver.findElements(DispositionMasterPageRepo.previousdoubleArrowButtons);
           if (previousdoubleArrowButtons.isEmpty()) {
               // Element is not in the DOM
               System.out.println("The '<<' button is not present in the DOM.");
           } else {
               // Element exists; check visibility
               Assert.assertFalse(previousdoubleArrowButtons.get(0).isDisplayed(), "'<<' button is visible, but it should not be.");
           }
           
        // Verify Next button is enabled
           Assert.assertTrue(nextButton.isEnabled(), "Next button is not enabled.");
           
        // Verify Double Arrow (>>) button is enabled
           Assert.assertTrue(lastPageButton.isEnabled(), "Next Double Arrow button is not enabled.");
    }
	
	public void clickAddDispositionButton() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,-500);");
		 driver.findElement(DispositionMasterPageRepo.addDispositionButton).click();
    }
	
	public boolean isPopupDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    try {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerField));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.nameField));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryField));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.submitButton));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
	        return true;
	    } catch (Exception e) {
	        System.err.println("Popup elements not displayed: " + e.getMessage());
	        return false;
	    }
}
	
	// Method to close the Add Disposition popup
    public void closeAddDispositionPopup() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
    	close.click();
    }

    // Method to verify that popup is closed
    public boolean isPopupClosed() {
        // Check if the close button is visible, or use other checks if necessary
        List<WebElement> closeButtonList = driver.findElements(DispositionMasterPageRepo.closeButton);

        // If the close button is not found in the DOM, it is assumed the popup is closed
        if (closeButtonList.isEmpty()) {
            return true;
        }

        // If the close button is found, check if it's visible
        WebElement closeButton = closeButtonList.get(0);
        return !closeButton.isDisplayed();
    }
    
 // Method to select action owner
    public void selectActionOwner(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement addDisposition = driver.findElement(DispositionMasterPageRepo.addDispositionButton);
    	addDisposition.click();
    	WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
    	actionOwnerElement.click();
    	WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption(actionOwner)));
        ccelement.click();
    }

    // Method to enter disposition name
    public void enterName(String name) {
    	WebElement nameInputField = driver.findElement(DispositionMasterPageRepo.nameField);
    	nameInputField.sendKeys(name);
    }

    // Method to select asset category
    public void selectAssetCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement assetCategoryElement = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
        assetCategoryElement.click();
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption(category)));
        ccelement.click();
    }

    // Method to click the submit button
    public void clickSubmit() {
    	Common.fluentWait("submitButton", DispositionMasterPageRepo.submitButton);
    	WebElement submitButton = driver.findElement(DispositionMasterPageRepo.submitButton);
    	submitButton.click();
    	
    }
    
    public void clickOnActionOwnerDropdown() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	 WebElement addDispositionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.addDispositionButton));
    	 addDispositionButton.click();
    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
    	 actionOwnerElement.click();
    }
    
    public void selectActionOwners(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        // Locate the dropdown option by visible text or a unique attribute
    	 WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption(actionOwner)));
    	    option.click();  // Select the option
    }
    
    public void clickAssetCategoryDropdown() {
    	
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement assetCategoryElement = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
        assetCategoryElement.click();
        
    }
    
    public void AssetCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption(category)));
        option.click(); // Select the option
        
        }
    
    public void clickActionOwnerDropdown() {
    	WebElement popCloseButton = driver.findElement(DispositionMasterPageRepo.closeButton);
    	popCloseButton.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement addDispositionButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.addDispositionButton));
    	addDispositionButton.click();
    	WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerField));
        dropdown.click();
    }
    
    public void selectAllActionOwners() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath); 
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	selectAllOption.click();
    	
    }
    
    public void deselectAllActionOwners() {
    	WebElement deselectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);
    	deselectAllOption.click();
    }
 
 // Method to click on "Select All" option
    public void clickSelectAllAssetCategory() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	 WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath); 	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	selectAllOption.click();
    	
    }
    
 // Method to uncheck the "Select All" checkbox
    public void uncheckSelectAllAssetCategory() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement deselectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath);	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	deselectAllOption.click();
    }
    
 // Method to click on Add Disposition button
    public void clickAddDisposition() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
    	close.click();
    	
    	WebElement addDispositionButtonElement = driver.findElement(DispositionMasterPageRepo.addDispositionButton);
    	addDispositionButtonElement.click();
    }
    
    public void clickSubmitButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement submitButtonElement = driver.findElement(DispositionMasterPageRepo.submitButton);
    	submitButtonElement.click();
    }
    
 // Method to get error message for name field
    public String getNameErrorMessage() {
    	WebElement nameErrorMessage = driver.findElement(DispositionMasterPageRepo.nameErrorMessage);
        return nameErrorMessage.getText();
        
    }
    
    // Method to verify if a disposition row is visible
    public boolean isDispositionVisible(String name) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 return driver.findElement(DispositionMasterPageRepo.dispositionByName(name)).isDisplayed();
    }

    // Method to get action owner text
    public String getActionOwnerText(String ActionOwner) {
    	
    	return driver.findElement(DispositionMasterPageRepo.actionOwnerText(ActionOwner)).getText();
    }

    // Method to verify if status is a green tick
    public boolean isStatusGreenTick() {
    	WebElement statusIcon = driver.findElement(DispositionMasterPageRepo.statusIcon);
        return statusIcon.isDisplayed();
    }

    // Method to check the presence of the action button
    public boolean isActionButtonPresent() {
    	WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
        return actionButton.isDisplayed();
    }
    
 // Method to get the error message text
    public String getErrorMessageText() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errorPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
        return errorPopUp.getText();
    }
    
 // Method to click the three-dot button
    public void clickThreeDotButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
    	WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
    	close.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    }

    // Method to click the edit button
    public void clickEditButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement editButton = driver.findElement(DispositionMasterPageRepo.editButton);
        editButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to verify the popup elements are displayed
    public boolean isEditPopupDisplayed() {
    	WebElement actionOwnerDropdowninpop = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdowninpop);
    	WebElement nameFieldinpop = driver.findElement(DispositionMasterPageRepo.nameFieldinpop);
    	WebElement assetCategoryDropdowninpop = driver.findElement(DispositionMasterPageRepo.assetCategoryDropdowninpop);
    	WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
        return actionOwnerDropdowninpop.isDisplayed() &&
        		nameFieldinpop.isDisplayed() &&
        		assetCategoryDropdowninpop.isDisplayed() &&
               updateButton.isDisplayed();
    }
 
 // Method to click on the update button
    public void clickUpdateButton() {
    	WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
        updateButton.click();
    }
    
    // Method to verify success message
    public boolean isSuccessMessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement successPopupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
        return successPopupElement.isDisplayed() && successPopupElement.getText().equals("Saved Successfully");
    }
    
    public void selectAllActionOwnersdropdown() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerDropdown));
        dropdown.click();
        Common.fluentWait("Action Owner Dropdown", DispositionMasterPageRepo.selectAllOptionpath);
        WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath); 
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	selectAllOption.click();
        }
    
    public void enterDispositionName(String name) {
    	WebElement nameField = driver.findElement(DispositionMasterPageRepo.nameField);
    	nameField.sendKeys(name);
    }
    
    public void selectAllAssetCategories() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement assetField = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
    	assetField.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement selectAllOption = driver.findElement(DispositionMasterPageRepo.selectAllOptionpath); 
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	selectAllOption.click();
    	
        }
    public boolean SuccessMessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement successpop = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
        return successpop.isDisplayed() && successpop.getText().equals("Saved Successfully");
    }
    
    public void ThreeDotButton() {
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    	
    }
    
 // Method to change the name field to a given value
    public void changeNameField(String name) {
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement nameFieldElement = driver.findElement(DispositionMasterPageRepo.nameField);
    	nameFieldElement.clear();
    	nameFieldElement.sendKeys(name);
    	WebElement outsideElementWebElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
    	outsideElementWebElement.click();
    }
    
    public void UpdateButton() {
    	WebElement updateButton = driver.findElement(DispositionMasterPageRepo.updateButton);
        updateButton.click();
    }
    
 // Method to get the error message text
    public String getErrorMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement existingmsgpop =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
        return existingmsgpop.getText();
    }
    
    public void toUpdateclickThreeDotButton() {
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
    	WebElement editButton = driver.findElement(DispositionMasterPageRepo.editButton);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage));
    	WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.closeButton));
    	close.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    	 editButton.click();
    	 wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to change Action Owner
    public void setActionOwner(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement clearValue = driver.findElement(DispositionMasterPageRepo.clearValueIcon);
    	clearValue.click();
    	WebElement actionOwnerElement = driver.findElement(DispositionMasterPageRepo.actionOwnerField);
    	actionOwnerElement.click();
    	 WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption4(actionOwner)));
    	    ccelement.click();
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
        outsideElement.click();
    }

    // Method to change Name
    public void setName(String name) {
    	WebElement nameField = driver.findElement(DispositionMasterPageRepo.nameField);
        nameField.clear();
        nameField.sendKeys(name);
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
        outsideElement.click();
    }

    // Method to change Asset Category
    public void setAssetCategory(String assetCategory) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath);
    	clearValueButton.click();
    	WebElement assetLabel = driver.findElement(DispositionMasterPageRepo.assetCategoryField);
    	assetLabel.click();
    	WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.assetCategoryOption2(assetCategory)));
        ccelement.click();
        WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElement); // Assuming the <body> tag is safe to click
        outsideElement.click();
    }

    // Method to verify success message
    public boolean isUpdateSuccessMessageDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage));
        return successMessage.isDisplayed();
    }
    
 // Method to click on the three-dot action button
    public void clkThreeDotButton() {
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    }

    // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOption() {
    	WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption);
        activateDeactivateOption.click();
    }
    
 // Method to select action owner as Call centre
    public void selActionOwner(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,-500);");
    	WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath2);
    	clearValueButton.click();
    	WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.actionownerdropdownpath);
    	actionOwnerDropdown.click();
    	WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.actionOwnerOption3(actionOwner)));
        ccelement.click();
      WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
      outsideElement.click();
    }
    
 // Method to untick Is active checkbox
    public void untickIsActiveCheckbox() {
    	WebElement checkbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox2);
       checkbox.click();

        }
    
 // Method to click on the search button
    public void clickSearchButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement searchButton = driver.findElement(DispositionMasterPageRepo.searchButton);
        searchButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to verify the deactivated disposition icon
    public boolean isDeactivatedDispositionVisible() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
    	List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
		for (WebElement icon : statusIcons) {
			if (!icon.getAttribute("style").contains("color: red")) {
				System.out.println("Icon is not in a red tick: " + icon.getAttribute("style"));
				return false;
			}
			else {
				System.out.println("All items are in a red tick: ");
				
			}

		}
		return true;
    }
    
    public boolean isActiveDispositionShown() {
    	Common.fluentWait("Status icon list", DispositionMasterPageRepo.statusIconslist);
    	List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist);
		for (WebElement icon : statusIcons) {
			if (!icon.getAttribute("style").contains("color: green")) {
				System.out.println("Icon is not in a green tick: " + icon.getAttribute("style"));
				return false;
			}
			else {
				System.out.println("All items are in a green tick: ");
				
			}

		}
		return true;
    }
    
 // Method to navigate to Sub-Disposition tab
    public void navigateToSubDispositionTab() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,-500);");
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement subDispositionElement = driver.findElement(DispositionMasterPageRepo.subDispositionOption);
    	subDispositionElement.click();
    }
    
    // Method to verify UI elements
    public boolean verifyUIElements() {
    	List<WebElement> subDispositionList = driver.findElements(DispositionMasterPageRepo.subDispositionList);
    	WebElement subActionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subactionOwnerDropdown);
    	WebElement dispositionSearchField = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
    	WebElement subIsActiveCheckbox = driver.findElement(DispositionMasterPageRepo.subisActiveCheckbox);
    	WebElement subSearchButton = driver.findElement(DispositionMasterPageRepo.subsearchButton);
    	WebElement addSubDispositionButton = driver.findElement(DispositionMasterPageRepo.addSubDispositionButton);
    	
        return subActionOwnerDropdown.isDisplayed() &&
               dispositionSearchField.isDisplayed() &&
               subIsActiveCheckbox.isSelected() &&
               subSearchButton.isDisplayed() &&
               addSubDispositionButton.isDisplayed() &&
               subDispositionList.size() <= 10;
    }
    
 // Method to click on Add Sub-Disposition button
    public void clickAddSubDispositionButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Common.fluentWait("addSubDispositionButton", DispositionMasterPageRepo.addSubDispositionButton);
    	WebElement addSubDispositionButton = driver.findElement(DispositionMasterPageRepo.addSubDispositionButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        addSubDispositionButton.click();
    }

    // Method to verify popup elements are present
    public boolean issubdispositionPopupelementsDisplayed() {
    	
    	WebElement subPopupActionOwnerDropdownElement = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);
    	WebElement subPopupDispositionDropdownElement = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
    	WebElement subPopupSubDispositionNameFieldElement = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);
    	WebElement subPopupSubmitButtonElement = driver.findElement(DispositionMasterPageRepo.submitButton);
    	WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
    	
        return subPopupActionOwnerDropdownElement.isDisplayed()
            && subPopupDispositionDropdownElement.isDisplayed()
            && subPopupSubDispositionNameFieldElement.isDisplayed()
            && subPopupSubmitButtonElement.isDisplayed()
            && subPopupClosePopupButtonElement.isDisplayed();
    }
    
 // Method to click the close button in the popup
    public void clickCloseButton() {
    	WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
    	subPopupClosePopupButtonElement.click();
    }
    
 // Method to select an action owner
    public void selectsubdispositionActionOwner(String owner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        // Implement logic to select the required option
    	Common.fluentWait("actionOwnerDropdown", DispositionMasterPageRepo.actionOwnerDropdown);
    	WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.actionOwnerDropdown);
    	subPopupClosePopupButtonElement.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Common.fluentWait("subDispositionActionOwnerOption", DispositionMasterPageRepo.subDispositionActionOwnerOption(owner));
        By subpopupactionOwnerDropdownvaluepath = DispositionMasterPageRepo.subDispositionActionOwnerOption(owner);
        WebElement subpopupactionOwnervalue = driver.findElement(subpopupactionOwnerDropdownvaluepath);
        subpopupactionOwnervalue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to enter disposition
    public void enterDisposition(String disposition) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        // Implement logic to select the required option
    	Common.fluentWait("subPopupDispositionDropdown", DispositionMasterPageRepo.subPopupDispositionDropdown);
    	WebElement subpopupdispositionDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
    	subpopupdispositionDropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Common.fluentWait("subPopupDispositionOption", DispositionMasterPageRepo.subPopupDispositionOption(disposition));
        By subpopupDispositionOption = DispositionMasterPageRepo.subPopupDispositionOption(disposition);
        WebElement subpopupactionOwnervalue = driver.findElement(subpopupDispositionOption);
        subpopupactionOwnervalue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to enter sub-disposition
    public void enterSubDisposition(String subDisposition) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Common.fluentWait("subpopupsubDispositionNameField", DispositionMasterPageRepo.subpopupsubDispositionNameField);
    	WebElement subpopupsubDispositionNameField = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);
    	subpopupsubDispositionNameField.sendKeys(subDisposition);
    }

    // Method to click submit button
    public void addsubdisposistionSubmit() {
    	WebElement submitButton = driver.findElement(DispositionMasterPageRepo.submitButton);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        submitButton.click();
    }
    
 // Method to verify if success message is displayed
    public boolean isSuccessMessageDisplayedforsubdisposition() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement successmsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
        return successmsg.getText().equals("Saved Successfully");
    }
    
 
    // Method to verify if success message is displayed
    public boolean iserrorMessageDisplayedforsubdisposition() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement errormsg =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessageforsubdispostion));
        return errormsg.getText().equals("This Sub-Disposition Already Exist");
    }
    
 // Method to open edit dialog
    public void openEditPopup() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessageforsubdispostion));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement subpopupclosePopupButton = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
    	subpopupclosePopupButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionButton.click();
    	WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	subdispoeditButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }

    // Method to click update without any changes
    public void clickUpdateWithoutChanges() {
    	WebElement subdispoupdateButton = driver.findElement(DispositionMasterPageRepo.subdispoupdateButton);
    	subdispoupdateButton.click();
    }
    
 // Method to verify success message
    public boolean isSuccessMessageDisplayedforsubdispos() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
        return successMessage.isDisplayed();
    }
    
    public void openEditPopuptoupdate() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	Common.fluentWait("actionButton", DispositionMasterPageRepo.actionButton);
    	WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionButton.click();
    	Common.fluentWait("subdispoeditButton", DispositionMasterPageRepo.subdispoeditButton);
    	WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	subdispoeditButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
    public void enterSubDispositionName(String name) {
    	Common.fluentWait("subpopupsubDispositionNameField", DispositionMasterPageRepo.subpopupsubDispositionNameField);
    	WebElement nameFieldElement = driver.findElement(DispositionMasterPageRepo.subpopupsubDispositionNameField);
    	nameFieldElement.clear();
    	nameFieldElement.sendKeys(name);
    	Common.fluentWait("outsideElementPath3", DispositionMasterPageRepo.outsideElementPath3);
    	WebElement outsideElementWebElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath3); // Assuming the <body> tag is safe to click
    	outsideElementWebElement.click();
    }
    
    public void clickUpdateWithexistingname() {
    	Common.fluentWait("subdispoupdateButton", DispositionMasterPageRepo.subdispoupdateButton);
    	WebElement subdispoupdateButton = driver.findElement(DispositionMasterPageRepo.subdispoupdateButton);
    	subdispoupdateButton.click();
    }
    
    public String getErrorMessageforexistingnameupdate() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement existingmsgpop =  wait.until(ExpectedConditions.visibilityOfElementLocated(DispositionMasterPageRepo.errorMessage2));
        return existingmsgpop.getText();
    }
    
    public void openEditPopuptoupdateexistingname() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.errorMessage2));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Common.fluentWait("subpopupclosePopupButton", DispositionMasterPageRepo.subpopupclosePopupButton);
    	WebElement subPopupClosePopupButtonElement = driver.findElement(DispositionMasterPageRepo.subpopupclosePopupButton);
    	subPopupClosePopupButtonElement.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	Common.fluentWait("actionButton", DispositionMasterPageRepo.actionButton);
    	WebElement actionButton = driver.findElement(DispositionMasterPageRepo.actionButton);
    	actionButton.click();
    	Common.fluentWait("subdispoeditButton", DispositionMasterPageRepo.subdispoeditButton);
    	WebElement subdispoeditButton = driver.findElement(DispositionMasterPageRepo.subdispoeditButton);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	subdispoeditButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
    public void clkThreeDotButtonofsubdisposition() {
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton2);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.successMessage2));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    }
    
 // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOptionofsubdisposition() {
    	WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption2);
        activateDeactivateOption.click();
    }
    
 // Method to select action owner as Call centre
    public void selActionOwnerforsubdispositionsearch(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
    	actionOwnerDropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 By actionOwnerOption = DispositionMasterPageRepo.actionOwnerOption3(actionOwner);
    	    WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(actionOwnerOption));
    	    ccelement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
      WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
      outsideElement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to select action owner as Call centre
    public void seldispositionforsubdispositionsearch(String dispostion) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dispositionDropdown = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
    	dispositionDropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	 By dispositionOption = DispositionMasterPageRepo.subDispositionOption(dispostion);
    	    WebElement subpopupDispositionValue = driver.findElement(dispositionOption);
    	    subpopupDispositionValue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
      WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
      outsideElement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to untick Is active checkbox
    public void untickIsActiveCheckboxforsubdispositionsearch() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement checkbox = driver.findElement(DispositionMasterPageRepo.isActiveCheckbox3);
       checkbox.click();
       wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

        } 
    
 // Method to click on the search button
    public void clickSearchButtonforsubdispositionsearch() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement searchButton = driver.findElement(DispositionMasterPageRepo.searchButton2);
        searchButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to verify the deactivated disposition icon
    public boolean isDeactivatedDispositionVisibleforsubdispositionsearch() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
    	List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist2);
		for (WebElement icon : statusIcons) {
			if (!icon.getAttribute("style").contains("color: red")) {
				System.out.println("Icon is not in a red tick: " + icon.getAttribute("style"));
				return false;
			}
			else {
				System.out.println("All items are in a red tick: ");
				
			}

		}
		return true;
    }
    
 // Method to click on the three-dot action button
    public void clkThreeDotButtonfordeactivatingsubdisposition() {
    	Common.fluentWait("actionColumnButton2", DispositionMasterPageRepo.actionColumnButton2);
    	WebElement actionColumnButton = driver.findElement(DispositionMasterPageRepo.actionColumnButton2);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	actionColumnButton.click();
    }
    
 // Method to click on the Activate/De-activate option
    public void clickActivateDeactivateOptionfordeactivatingsubdisposition() {
    	Common.fluentWait("activateDeactivateOption2", DispositionMasterPageRepo.activateDeactivateOption2);
    	WebElement activateDeactivateOption = driver.findElement(DispositionMasterPageRepo.activateDeactivateOption2);
        activateDeactivateOption.click();
    }
    
 // Method to select action owner as Call centre
    public void selActionOwnerforactivesubdispositionsearch(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	WebElement clearValueButton = driver.findElement(DispositionMasterPageRepo.clearvaluepath3);
    	clearValueButton.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement actionOwnerDropdown = driver.findElement(DispositionMasterPageRepo.subPopupDispositionDropdown);
    	actionOwnerDropdown.click();
    	By actionOwnerLocator = DispositionMasterPageRepo.actionOwnerOption3(actionOwner);
        WebElement ccelement = wait.until(ExpectedConditions.visibilityOfElementLocated(actionOwnerLocator));
        ccelement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
      WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
      outsideElement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to select action owner as Call centre
    public void seldispositionforactivesubdispositionsearch(String dispostion) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	WebElement dispositionDropdown = driver.findElement(DispositionMasterPageRepo.dispositionSearchField);
    	dispositionDropdown.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    	By dispositionLocator = DispositionMasterPageRepo.dispositionOption(dispostion);
        WebElement subpopupactionOwnervalue = driver.findElement(dispositionLocator);
        subpopupactionOwnervalue.click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
      WebElement outsideElement = driver.findElement(DispositionMasterPageRepo.outsideElementPath2); // Assuming the <body> tag is safe to click
      outsideElement.click();
      wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
    }
    
 // Method to verify the deactivated disposition icon
    public boolean isActiveDispositionVisibleforsubdispositionsearch() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
        
    	List<WebElement> statusIcons = driver.findElements(DispositionMasterPageRepo.statusIconslist2);
		for (WebElement icon : statusIcons) {
			if (!icon.getAttribute("style").contains("color: green")) {
				System.out.println("Icon is not in a green tick: " + icon.getAttribute("style"));
				return false;
			}
			else {
				System.out.println("All items are in a green tick: ");
				
			}

		}
		return true;
    }
}