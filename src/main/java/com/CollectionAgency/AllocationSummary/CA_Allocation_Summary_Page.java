package com.CollectionAgency.AllocationSummary;

import java.io.IOException;
import java.sql.Types;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.CollectionAgency_AllocationSummaryPageRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;

public class CA_Allocation_Summary_Page {

	private WebDriver driver;

	public CA_Allocation_Summary_Page(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void navigateToAllocationSummary() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement dashboardMenu = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dashboardMenu));
		dashboardMenu.click();
		WebElement allocationSummaryLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLink));
		allocationSummaryLink.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
	}

	public boolean isAllocationSummaryPageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement allocationSummaryLabel = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLabel));
		WebElement grid = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.grid));
		return allocationSummaryLabel.isDisplayed() && grid.isDisplayed();
	}

	// Method to verify Date column is displayed
	public boolean isDateColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement dateColumn = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dateColumn));
		return dateColumn.isDisplayed();
	}

	// Method to verify Total Accounts Received column is displayed
	public boolean isTotalAccountsReceivedColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement totalAccountsReceivedColumn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.totalAccountsReceivedColumn));
		return totalAccountsReceivedColumn.isDisplayed();
	}

	// Method to verify Allocated to Agents column is displayed
	public boolean isAllocatedToAgentsColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement allocatedToAgentsColumn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocatedToAgentsColumn));
		return allocatedToAgentsColumn.isDisplayed();
	}

	public boolean isGridValuesDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement gridValues = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.gridValues));
		return gridValues.isDisplayed();
	}

	public void clickDownloadButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement button = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.downloadButton));
		button.click();
	}

	public boolean isNoDataMessageDisplayed() {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement errorMsg = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.noDataMessage));
		return errorMsg.isDisplayed();
		 } catch (TimeoutException e) {
		        return false;
	  }
   }
	
	public void navigateToAddAgentPage() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		
		WebElement agentManagment = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentManagement));
		agentManagment.click();
		
		WebElement addNewAgent = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.addNewAgent));
		addNewAgent.click();
    }

	public void createAgent(String code, String name, String phone) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentCode)).sendKeys(code);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.name)).sendKeys(name);
		//agentNameField.sendKeys(name);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.phoneNumber)).sendKeys(phone);
		//phoneNumberField.sendKeys(phone);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.role)).click();
		//RoleField.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.roleDropdown)).click();
		//selectRoleField.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.submit)).click();
		//submitButton.click();
		
	}
	
	public boolean VerifyAgencyAccountAllocationDisplayingasSubmenu() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement accountAllocationMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(text(),'Collection Agency')]]")));
        Log.info("Agency Account Allocation Menu display sucessfully");
    	return accountAllocationMenu.isDisplayed();

    }
	
	public void allocateAccounts(String Balance) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		WebElement parentMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(text(),'Collection Agency')]]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentMenu);
		Log.info("Clicked Collection Agency menu.");

			
		WebElement agencyAccountAllocationSubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title = 'Agency Account Allocation']")));
		agencyAccountAllocationSubMenu.click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccount)).click();
		Log.info("Clicked Types of account field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccountDropdown)).click();
		Log.info("Selected Value Types of account field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategory)).click();
		Log.info("Clicked Asset Category field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown1)).click();
		Log.info("Selected Asset Category field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown2)).click();
		Log.info("Selected Asset Category field.");
				
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategoryDropdown)).click();
		Log.info("Clicked SMA Category field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategory)).click();
		Log.info("Selected SMA Category1 field.");
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategory2)).click();
		Log.info("Selected SMA Category2 field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategoryDropdown)).click();
		Log.info("Selected NPA Category field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategory)).click();
		Log.info("Selected NPA Category1 field.");
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategory2)).click();
		Log.info("Selected NPA2 Category2 field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperator)).click();
		Log.info("Clicked on the select operator field.");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperatorValue)).click();
		Log.info("Opearor value is selected");
		
		WebElement balanceField = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperatorValueField));
		balanceField.clear();
		balanceField.sendKeys(Balance);
		Log.info("Enter the value in balance field");
		
			
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.searchCore)).click();
		Log.info("Clicked on the Search button");
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectCollectionAgency));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		Log.info("Scrolled to and clicked on the select collection agency field");
		
		WebElement drodownValue = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.collectionAgencyDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
		drodownValue.click();
		Log.info("Selected collection agency value from the dropdown");
		
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.allocate)).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Log.info("Clicked on the allocate button");
		
    }
	
	public boolean VerifyAccountAllocationDisplayingasSubmenu() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement agentAccountAllocationMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Accounts Allocation']")));
        Log.info("Agent Account Allocation Menu display sucessfully");
    	return agentAccountAllocationMenu.isDisplayed();

    }
	
	/*public void navigateToAccountAllocationPage() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	    
	    try {
	        Log.info("Waiting for main menu container...");
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("menu-bar"))); // Use real ID or wrapper
	        
	        Log.info("Waiting for 'Accounts Allocation' menu to be clickable...");
	        WebElement accountAllocationMenu = wait.until(
	            ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(text(),'Accounts Allocation')]]"))
	        );

	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accountAllocationMenu);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountAllocationMenu);
	        Log.info("Clicked 'Accounts Allocation' menu via JavaScript.");

	        WebElement agentAccountAllocation = wait.until(
	            ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Agent Account Allocation')]"))
	          //a[@class='dropdown-item' and @href='Collection/Admin/AgentAccountAllocation']
	        );
	        agentAccountAllocation.click();
	        Log.info("Clicked 'Agent Account Allocation' link.");
	    } catch (TimeoutException e) {
	        Log.error("Timeout: Could not locate 'Accounts Allocation' menu. " + e.getMessage());
	        throw e;
	    }
	}*/

  

		
	
	public void allocateAgentAccounts() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement parentAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Accounts Allocation']")));
		
		
		//js.executeScript("arguments[0].scrollIntoView(true);", parentAccount);
		parentAccount.click();
		//Thread.sleep(500);
		//js.executeScript("arguments[0].click();", parentAccount);
		Log.info("Clicked Account Allocation menu.");
		
		WebElement childSubMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Agent Account Allocation')]")));
		childSubMenu.click();
		Log.info("Clicked Agent Account Allocation submenu.");
		//Thread.sleep(2000);
		
		WebElement assetCategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategory));
		assetCategory.click();
		Log.info("Clicked on the Asset Category Field.");
		
		WebElement assetCategoryDropdown = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown));
		assetCategoryDropdown.click();
		Log.info("Asset Category Dropdown value is selected");
		
		//WebElement assetCategoryDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown));
		//assetCategoryDropdown2.click();
		//Log.info("Asset Category Dropdown2 value is selected");
		//Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement smaCategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategoryCollection));
		smaCategory.click();
		Log.info("Clicked on the SMA Category Field.");
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement smaCategoryDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategory1));
		smaCategoryDropdown1.click();
		Log.info("SMA Category1 Dropdown value is selected.");
		
		WebElement smaCategoryDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategory2));
		smaCategoryDropdown2.click();
		Log.info("SMA Category2 Dropdown value is selected.");
		
		//WebElement npaCategory = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory));
		//smaCategory.click();
		//Log.info("Clicked on the NPA Category Field.");
		
//		WebElement npaCategoryDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory1));
//		smaCategoryDropdown1.click();
//		Log.info("NPA Category1 Dropdown value is selected.");
//		
//		WebElement npaCategoryDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory2));
//		smaCategoryDropdown2.click();
//		Log.info("NPA Category2 Dropdown value is selected.");
		
		
		
		WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.TypesofAccount));
		typeDropdown.click();
		Log.info("Clicked on the Type of Account Field.");
		
		WebElement selectTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NotAllocated));
		selectTypeDropdown.click();
		Log.info("Type of Account Dropdown value is selected.");
		
		WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.Search));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBtn);
		Thread.sleep(500);
		searchBtn.click();
		Log.info("Sucessfully clicked on the search button");
		
		Thread.sleep(3000);
		
		//Select the accounts
		WebElement selectAccount = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun1));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAccount);
		selectAccount.click();
		Log.info("Sucessfully clicked on the account1 checkbox");
		
		WebElement selectAccount1 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun2));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAccount1);
		selectAccount1.click();
		Log.info("Sucessfully clicked on the account2 checkbox");
		
		WebElement selectAccount2 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun3));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAccount2);
		selectAccount2.click();
		Log.info("Sucessfully clicked on the account3 checkbox");
		
		WebElement selectAccount3 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun4));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAccount3);
		selectAccount3.click();
		Log.info("Sucessfully clicked on the account4 checkbox");
		
		
		// Select the agent from dropdown
        
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgent));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		Log.info("Scrolled to and clicked on the select collection agent field");
		
		WebElement drodownValue = wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgentDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
		drodownValue.click();
		Log.info("Selected collection agent value from the dropdown");
		
		Thread.sleep(5000);
        // Allocate to agent
        //wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocateAgent)).click();
        //Alert alert = driver.switchTo().alert();
		//alert.accept();
		Log.info("Clicked on the allocate button");
	}
	
	public boolean verifyAllocation(String expectedCount) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement dashboardMenu = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dashboardMenu));
		dashboardMenu.click();
		WebElement allocationSummaryLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLink));
		allocationSummaryLink.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		
		String cardText = driver.findElement(By.xpath("(//td[contains(@class,'rz-composite-cell')])[17]")).getText();
	    String actualCount = cardText.replaceAll("\\D+", ""); // extract only numbers

	    Log.info("Expected count: " + expectedCount + ", Actual count: " + actualCount);
	    return actualCount.equals(expectedCount);
    }
}



	

