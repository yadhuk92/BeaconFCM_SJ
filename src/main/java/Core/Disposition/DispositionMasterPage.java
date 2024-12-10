package Core.Disposition;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.BasePackage.Common;
import com.Utility.Log;

public class DispositionMasterPage {
	
	WebDriver driver;
	
	// Constructor
	public DispositionMasterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements
        Log.info("DispositionMasterPage initialized.");
    }
	
	@FindBy(xpath = "//span[contains(text(), 'Disposition')]")
	private WebElement dispositionMainMenu;

	@FindBy(xpath = "//a[@title='Disposition Master']")
	private WebElement dispositionMasterSubMenu;

	// Web element locator for checking if "Disposition Master" page is active
	@FindBy(xpath = "//div[@class='main']")
	WebElement dispositionMasterHeader;

	@FindBy(xpath = "//span[contains(text(),'Disposition')]")
	private WebElement Disposition;

	@FindBy(xpath = "//span[contains(text(),'Sub-Disposition')]")
	private WebElement Sub_Disposition;

	@FindBy(xpath = "((//div[contains(text(),'Action Owner')])[1]//following::div//div)[1]")
	private WebElement actionOwnerDropdown;

	@FindBy(xpath = "(//li[contains(@aria-label,'Call Centre')])[2]")
	private WebElement callCentreOption;

	@FindBy(xpath = "(//li[contains(@aria-label,'Collection Agency')])[2]")
	private WebElement collectionAgencyOption;

	@FindBy(xpath = "(//li[contains(@aria-label,'Internal User')])[2]")
	private WebElement internalUserOption;

	By isActiveCheckbox = By.xpath("(//input[@type='checkbox' and @value='true'])[1]");

	@FindBy(xpath = "(//button[@id='dropdownMenu2'])[1]")
	private WebElement actionColumnButton;

	@FindBy(xpath = "//tr[contains(@class,'rz-datatable')]//td[3]//span//i") List<WebElement> statusIcons;


	@FindBy(xpath="(//div[@class='table-footerpagination'])[1]//div//nav")
    private WebElement pagination;
	
	@FindBy(xpath="(//li[@class='page-item  '])[2]//span")
    private WebElement paginationNextButton;
	public static By paginationNextButton1 = By.xpath("(//li[@class='page-item  '])[2]//span");
	
	@FindBy(xpath = "(//li[@class='page-item  '])[1]//span")
    private WebElement page2;
	
	@FindBy(xpath="(//li[@class='page-item  ']//span)[2]")
    private WebElement previousButton;
	
	@FindBy(xpath="(//li[@class='page-item  '])[3]//span")
    private WebElement lastPageButton;
	
	@FindBy(xpath="(//li[@class='page-item disabled '])[1]")
    private WebElement nextButton;
	
	@FindBy(xpath="(//li[@class='page-item  '])[2]")
    private WebElement previouspage;
	
	@FindBy(xpath="(//li[@class='page-item  '])[1]")
    private WebElement firstpage;
	
	@FindBy(id = "//button[contains(text(),'Add Disposition')]")
    private WebElement addDispositionButton;

    @FindBy(id = "//label[contains(text(),'Action Owner')]//following::div[1]")
    private WebElement actionOwnerField;

    @FindBy(id = "(//input[@name='Name'])[1]")
    private WebElement nameField;

    @FindBy(id = "(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]")
    private WebElement assetCategoryField;

    @FindBy(id = "//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    @FindBy(id = "//div[@class='rz-dialog-titlebar']//a")
    private WebElement closeButton;
    
    
    
	public WebElement getDispositionMainMenu() {
	    return dispositionMainMenu;
	}
	

	public void clickOnDispositionMainMenu() throws InterruptedException {
		Log.info("Clicking on the Disposition.");
		dispositionMainMenu.click();
		Log.info("Successfully clicked on the Disposition.");
		Thread.sleep(10000);
	}

	public void clickOnDispositionMasterSubMenu() throws InterruptedException {
		Log.info("Clicking on the Disposition Master.");
		dispositionMasterSubMenu.click();
		Log.info("Successfully clicked on the Disposition Master.");
		Thread.sleep(30000);
	}

	//Method to verify if the Disposition Master page is loaded
	public boolean isDispositionMasterPageDisplayed() {
		return dispositionMasterHeader.isDisplayed();
	}

	public boolean isDispositionDisplayed() {
		return Disposition.isDisplayed();
	}

	public boolean isSubDispositionDisplayed() {
		return Sub_Disposition.isDisplayed();
	}
	public int getActiveDispositionsCount() throws InterruptedException {
		// Replace the locator below with the actual locator for active disposition rows
		Thread.sleep(30000);
		String activeDispositionsXpath = "//div[@class='rz-data-grid-data']//tr[contains(@class, 'rz-datatable-even')]";

		// Find all active disposition rows
		return driver.findElements(By.xpath(activeDispositionsXpath)).size();
	}

	//Method to select Action Owner from dropdown
	public void selectActionOwnerOptions() {
		Log.info("Opening the Action Owner dropdown.");
		actionOwnerDropdown.click();
		Log.info("Selecting 'Call Centre' option.");
		callCentreOption.click();
		Log.info("Selecting 'Collection Agency' option.");
		collectionAgencyOption.click();
		Log.info("Selecting 'Internal User' option.");
		internalUserOption.click();
		Log.info("Action Owner options selected successfully.");
	}

	public boolean isIsActiveCheckboxChecked() {
		return driver.findElement(isActiveCheckbox).isSelected();
	}

	// Method to verify action column options
	public boolean verifyActionOptions() {


		actionColumnButton.click();
		// Check for Edit and Activate/Deactivate options
		By editOption = By.xpath("(//button[contains(text(),'Edit')])[1]");
		By activateDeactivateOption = By.xpath("(//button[contains(text(),'Activate/De-activate')])[1]");

		// Wait for the options to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement editElement = wait.until(ExpectedConditions.presenceOfElementLocated(editOption));
			WebElement activateDeactivateElement = wait.until(ExpectedConditions.presenceOfElementLocated(activateDeactivateOption));

			// Verify both options are displayed
			return editElement.isDisplayed() && activateDeactivateElement.isDisplayed();
		} catch (Exception e) {
			System.out.println("Required options not found: " + e.getMessage());
			return false;
		}
	}

	public boolean areAllStatusIconsGreenTicks() {
		//List<WebElement> statusIcons = driver.findElements(By.xpath("//tr[contains(@class,'rz-datatable')]//td[3]//span//i"));
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
		WebElement previousButton = pagination.findElement(By.xpath("(//li[@class='page-item disabled '])[1]"));
        WebElement page1Button = pagination.findElement(By.xpath("(//li[@class='page-item  active'])[1]//span"));
        WebElement nextButton = pagination.findElement(By.xpath("(//li[@class='page-item  '])[2]//span"));
        WebElement doubleArrowButton = pagination.findElement(By.xpath("(//li[@class='page-item  '])[3]//span"));
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
		WebElement page2Button = paginationNextButton.findElement(By.xpath("(//li[@class='page-item  '])[1]//span"));
		WebElement previousButton = paginationNextButton.findElement(By.xpath("(//li[@class='page-item  ']//span)[2]"));
		WebElement nextButton = paginationNextButton.findElement(By.xpath("(//li[@class='page-item  '])[2]//span"));
        WebElement nextdoubleArrowButton = paginationNextButton.findElement(By.xpath("(//li[@class='page-item  '])[3]//span"));
        WebElement previousdoubleArrowButton = paginationNextButton.findElement(By.xpath("(//li[@class='page-item  '])[1]"));
        
        Common.fluentWait("paginationNextButton", paginationNextButton1);
        Thread.sleep(500);
        driver.findElement(paginationNextButton1).click();
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", paginationNextButton);*/
        //Thread.sleep(500);
        //paginationNextButton.click();
        Thread.sleep(500);
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
		WebElement page1 = previousButton.findElement(By.xpath("(//li[@class='page-item  active'])[1]//span"));
		WebElement previousBut = previousButton.findElement(By.xpath("(//li[@class='page-item disabled '])[1]"));
		WebElement nextButton = previousButton.findElement(By.xpath("(//li[@class='page-item  '])[2]//span"));
        WebElement nextdoubleArrowButton = previousButton.findElement(By.xpath("(//li[@class='page-item  '])[3]//span"));
        //WebElement previousdoubleArrowButton = previousButton.findElement(By.xpath("(//li[@class='page-item  '])[1]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", paginationNextButton);
        previousButton.click();
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
        List<WebElement> previousdoubleArrowButtons = driver.findElements(By.xpath("//li[@class='page-item  ']//span[contains(text(),'<<')]"));
        if (previousdoubleArrowButtons.isEmpty()) {
            // Element is not in the DOM
            System.out.println("The '<<' button is not present in the DOM.");
        } else {
            // Element exists; check visibility
            Assert.assertFalse(previousdoubleArrowButtons.get(0).isDisplayed(), "'<<' button is visible, but it should not be.");
        }

	}
	
	public void clickLastPageButton() {
        lastPageButton.click();
     // Verify Next button is enabled
        String classAttribute = nextButton.getAttribute("class");
        // Check if the class contains the specific class name
           boolean containsClassName = classAttribute.contains("page-item disabled ");
        // Verify Previous button is disabled
        // Assert that the class does contain the specific class name
           Assert.assertTrue(containsClassName, "Element contains the undesired class");
           
           List<WebElement> lastpageArrowButtons = driver.findElements(By.xpath("(//li[@class='page-item  '])[3]//span[contains(text(),'>>')]"));
           if (lastpageArrowButtons.isEmpty()) {
               // Element is not in the DOM
               System.out.println("The '>>' button is not present in the DOM.");
           } else {
               // Element exists; check visibility
               Assert.assertFalse(lastpageArrowButtons.get(0).isDisplayed(), "'>>' button is visible, but it should not be.");
           }
           
        // Verify Previous button is enabled
           Assert.assertTrue(previouspage.isEnabled(), "Previous button is not enabled."); 
           
        // Verify << button is enabled
           Assert.assertTrue(firstpage.isEnabled(), "Previous button is not enabled."); 
     
    }
	
	public void clickFirstPageButton() {
		firstpage.click();
		WebElement previousBut = previousButton.findElement(By.xpath("(//li[@class='page-item disabled '])[1]"));
		// Verify Previous button is enabled
        String classAttribute = previousBut.getAttribute("class");
        // Check if the class contains the specific class name
           boolean containsClassName = classAttribute.contains("page-item disabled ");
        // Verify Previous button is disabled
        // Assert that the class does contain the specific class name
           Assert.assertTrue(containsClassName, "Element contains the undesired class");
           
           List<WebElement> previousdoubleArrowButtons = driver.findElements(By.xpath("//li[@class='page-item  ']//span[contains(text(),'<<')]"));
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
		
		By addDispositionButton =By.xpath("//button[contains(text(),'Add Disposition')]");
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//WebElement editElement = wait.until(ExpectedConditions.presenceOfElementLocated(addDispositionButton));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);"); 
		 WebElement addDispositionButton2 =  driver.findElement(addDispositionButton);
		 addDispositionButton2.click();
    }
	
	public boolean isPopupDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-dialog-content']")));
	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='Name'])[1]")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-dialog-titlebar']//a")));
	        return true;
	    } catch (Exception e) {
	        System.err.println("Popup elements not displayed: " + e.getMessage());
	        return false;
	    }
}
	
	// Method to close the Add Disposition popup
    public void closeAddDispositionPopup() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-dialog-titlebar']//a")));
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-dialog-titlebar']//a")));
    	close.click();
    }

    // Method to verify that popup is closed
    public boolean isPopupClosed() {
        try {
            return !closeButton.isDisplayed(); // Assumes the close button is not visible after closing
        } catch (Exception e) {
            return true; // If element not found, it's assumed to be closed
        }
    }
    
 // Method to select action owner
    public void selectActionOwner(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	By addDispositionButton =By.xpath("//button[contains(text(),'Add Disposition')]");
    	WebElement addDispositionButton2 =  driver.findElement(addDispositionButton);
		 addDispositionButton2.click();
    	By actionownerpath =By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]");
    	WebElement dropdown =  driver.findElement(actionownerpath);
        dropdown.click();
        WebElement ccelement =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rz-multiselect-items-wrapper'])[5]//ul//li[@aria-label='" + actionOwner + "']")));
        ccelement.click();
    }

    // Method to enter disposition name
    public void enterName(String name) {
    	By namepath =By.xpath("(//input[@name='Name'])[1]");
        driver.findElement(namepath).sendKeys(name);
    }

    // Method to select asset category
    public void selectAssetCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	By assetpath =By.xpath("(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]");
        WebElement dropdown = driver.findElement(assetpath);
        dropdown.click();
        WebElement ccelement =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + category + "']")));
        ccelement.click();
    }

    // Method to click the submit button
    public void clickSubmit() {
    	
    	By submitbuttonpath =By.xpath("//button[contains(text(),'Submit')]");
    	WebElement submit =  driver.findElement(submitbuttonpath);
    	submit.click();
    	
    }
    
    public void clickOnActionOwnerDropdown() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	//By addDispositionButton =By.xpath("//button[contains(text(),'Add Disposition')]");
    	WebElement addDispositionButton2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Disposition')]")));
		 addDispositionButton2.click();
		 By spinner = By.xpath("//div[@class='spinner']");
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    	By actionownerpath =By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]");
    	WebElement dropdown =  driver.findElement(actionownerpath);
        dropdown.click();
    }
    
    public void selectActionOwners(String actionOwner) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Locate the dropdown option by visible text or a unique attribute
        //WebElement option = driver.findElement(By.xpath("(//div[@class='rz-multiselect-items-wrapper'])[5]//ul//li[@aria-label='" + actionOwner + "']"));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='rz-multiselect-items-wrapper'])[5]//ul//li[@aria-label='" + actionOwner + "']")));
        option.click(); // Select the option
    }
    
    public void clickAssetCategoryDropdown() {
    	
    	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	By assetpath =By.xpath("(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]");
        WebElement dropdown = driver.findElement(assetpath);
        dropdown.click();
        
    }
    
    public void AssetCategory(String category) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	 WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + category + "']")));
         option.click(); // Select the option
        
        }
    
    public void clickActionOwnerDropdown() {
    	By popclosebuttonpath =By.xpath("//div[@class='rz-dialog-titlebar']//a");
    	WebElement popclose =  driver.findElement(popclosebuttonpath);
    	popclose.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	WebElement addDispositionButton2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Disposition')]")));
    	addDispositionButton2.click();
    	//By actionownerpath =By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]");
    	WebElement dropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]")));
        dropdown.click();
    }
    
    public void selectAllActionOwners() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    	By spinner = By.xpath("//div[@class='spinner']");
    	By selectAllOptionpath = By.xpath("(//div//div[@class='rz-chkbox'])[5]"); 
    	WebElement selectAllOption =  driver.findElement(selectAllOptionpath);	
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    	selectAllOption.click();
    	
    }
    
    public void deselectAllActionOwners() {
    	By deselectAllOptionpath = By.xpath("(//div//div[@class='rz-chkbox'])[5]"); 
    	WebElement deselectAllOption =  driver.findElement(deselectAllOptionpath);
    	deselectAllOption.click();
    }
    
 }