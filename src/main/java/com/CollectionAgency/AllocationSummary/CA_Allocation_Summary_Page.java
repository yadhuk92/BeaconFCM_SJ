package com.CollectionAgency.AllocationSummary;

import java.io.IOException;
import java.sql.Types;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.CollectionAgency_AllocationSummaryPageRepo;
import com.Page_Repository.CoreAllocationSummaryRepo;
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

		By dashboardTest1 = By.xpath("//span[@class='text nav-text' and text()='Dash board']");
		By dashboardTest2 = By.xpath("//a[.//span[contains(text(),'Dashboard')]]");

		WebElement dashboardMenu;

		try {
			// Check if Test 1 dashboard is visible
			dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTest1));
			Log.info("Test 1 Dashboard Menu is present.");
		} catch (TimeoutException e1) {
			try {
				// If not, check Test 2 dashboard
				dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTest2));
				Log.info("Test 2 Dashboard Menu is present.");
			} catch (TimeoutException e2) {
				throw new NoSuchElementException("Dashboard Menu not found using either Test 1 or Test 2 XPath.");
			}
		}
		dashboardMenu.click();
		Log.info("Successfully clicked on the Dashboard Menu");

		WebElement allocationSummaryLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLink));
		allocationSummaryLink.click();
		Log.info("Successfully clicked on the Allocation Summary SubMenu");

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
		Log.info("Sucessfully clicked on the Download Icon");

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
/*
	public void navigateToAddAgentPage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		WebElement agentManagment = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentManagement));
		agentManagment.click();

		WebElement addNewAgent = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.addNewAgent));
		addNewAgent.click();
	}

	public void createAgent(String code, String name, String phone) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentCode))
				.sendKeys(code);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.name))
				.sendKeys(name);

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.phoneNumber))
				.sendKeys(phone);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.role))
				.click();

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.roleDropdown))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.submit))
				.click();

	}

	public boolean VerifyAgencyAccountAllocationDisplayingasSubmenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement accountAllocationMenu = wait
				.until(ExpectedConditions.elementToBeClickable(CoreAllocationSummaryRepo.collectionAgency));
		Log.info("Agency Account Allocation Menu display sucessfully");
		return accountAllocationMenu.isDisplayed();

	}

	public void allocateAccounts(String Balance) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		try {

			WebElement parentMenu = wait
					.until(ExpectedConditions.elementToBeClickable(CoreAllocationSummaryRepo.collectionAgency));

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentMenu);
			Log.info("Clicked Collection Agency menu.");

			WebElement agencyAccountAllocationSubMenu = wait.until(
					ExpectedConditions.visibilityOfElementLocated(CoreAllocationSummaryRepo.agentAccountCollection));
			agencyAccountAllocationSubMenu.click();
			Log.info("Clicked Agency Account Allocation sub menu.");
			Thread.sleep(2000);

		} catch (TimeoutException e) {
			Log.error("Timeout: 'Collection Agency' menu in Core App not clickable within 30 seconds.");
			e.printStackTrace();
		} catch (Exception e) {
			Log.error("Unexpected error while clicking 'Collection Agency' menu in Core App: " + e.getMessage());
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccount))
				.click();
		Log.info("Clicked Types of account field.");

		wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccountDropdown)).click();
		Log.info("Selected Value Types of account field.");

		wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategory))
				.click();
		Log.info("Clicked Asset Category field.");

		wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown1)).click();
		Log.info("Selected Asset Category field.");

		wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown2)).click();
		Log.info("Selected Asset Category field.");

		wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategoryDropdown))
				.click();
		Log.info("Clicked SMA Category field.");

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategory))
				.click();
		Log.info("Selected SMA Category1 field.");

		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.smaCategory2))
				.click();
		Log.info("Selected SMA Category2 field.");

		wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategoryDropdown))
				.click();
		Log.info("Selected NPA Category field.");

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategory))
				.click();
		Log.info("Selected NPA Category1 field.");

		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.npaCategory2))
				.click();
		Log.info("Selected NPA2 Category2 field.");

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperator))
				.click();
		Log.info("Clicked on the select operator field.");

		wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperatorValue))
				.click();
		Log.info("Opearor value is selected");

		WebElement balanceField = wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectOperatorValueField));
		balanceField.clear();
		balanceField.sendKeys(Balance);
		Log.info("Enter the value in balance field");

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.searchCore))
				.click();
		Log.info("Clicked on the Search button");

		WebElement element = wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.selectCollectionAgency));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		Log.info("Scrolled to and clicked on the select collection agency field");

		WebElement drodownValue = wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.collectionAgencyDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
		drodownValue.click();
		Log.info("Selected collection agency value from the dropdown");

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.allocate))
				.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Log.info("Clicked on the allocate button");

	}

	public boolean VerifyAccountAllocationDisplayingasSubmenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement agentAccountAllocationMenu = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Accounts Allocation']")));
		Log.info("Agent Account Allocation Menu display sucessfully");
		return agentAccountAllocationMenu.isDisplayed();

	}

	public void allocateAgentAccounts() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement parentAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Accounts Allocation']")));

		parentAccount.click();
		Log.info("Clicked Account Allocation menu.");

		WebElement childSubMenu = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Agent Account Allocation')]")));
		childSubMenu.click();
		Log.info("Clicked Agent Account Allocation submenu.");

		WebElement assetCategory = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategory));
		Thread.sleep(3000);
		assetCategory.click();
		Log.info("Clicked on the Asset Category Field.");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		WebElement assetCategoryDropdown = wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown));
		assetCategoryDropdown.click();
		Log.info("Asset Category Dropdown value is selected");

		// WebElement assetCategoryDropdown2 =
		// wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown));
		// assetCategoryDropdown2.click();
		// Log.info("Asset Category Dropdown2 value is selected");
		// Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement smaCategory = wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategoryCollection));
		Thread.sleep(5000);
		smaCategory.click();
		Log.info("Clicked on the SMA Category Field.");

		Thread.sleep(5000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement smaCategoryDropdown1 = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategory1));
		Thread.sleep(5000);
		smaCategoryDropdown1.click();
		Log.info("SMA Category1 Dropdown value is selected.");

		WebElement smaCategoryDropdown2 = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.SMACategory2));
		Thread.sleep(5000);
		smaCategoryDropdown2.click();
		Log.info("SMA Category2 Dropdown value is selected.");

		// WebElement npaCategory =
		// wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory));
		// smaCategory.click();
		// Log.info("Clicked on the NPA Category Field.");

//		WebElement npaCategoryDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory1));
//		smaCategoryDropdown1.click();
//		Log.info("NPA Category1 Dropdown value is selected.");
//		
//		WebElement npaCategoryDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NpaCategory2));
//		smaCategoryDropdown2.click();
//		Log.info("NPA Category2 Dropdown value is selected.");

		WebElement typeDropdown = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.TypesofAccount));
		typeDropdown.click();
		Log.info("Clicked on the Type of Account Field.");

		WebElement selectTypeDropdown = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.NotAllocated));
		selectTypeDropdown.click();
		Log.info("Type of Account Dropdown value is selected.");

		WebElement searchBtn = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.Search));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBtn);
		Thread.sleep(500);
		searchBtn.click();
		Log.info("Sucessfully clicked on the search button");

		Thread.sleep(3000);

		// Select the accounts
		WebElement selectAccount = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun1));
		selectAccount.click();
		Log.info("Sucessfully clicked on the account1 checkbox");

		WebElement selectAccount1 = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun2));
		selectAccount1.click();
		Log.info("Sucessfully clicked on the account2 checkbox");

		WebElement selectAccount2 = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun3));
		selectAccount2.click();
		Log.info("Sucessfully clicked on the account3 checkbox");

		// Select the agent from dropdown

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgent));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		Log.info("Scrolled to and clicked on the select collection agent field");

		WebElement drodownValue = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgentDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
		drodownValue.click();
		Log.info("Selected collection agent value from the dropdown");

		WebElement allocateButton = wait.until(
				ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.allocateAgent));
		allocateButton.click();
		Log.info("Clicked on the allocate button");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		try {
			WebElement successMsg = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Allocated Successfully')]")));
			Log.info("Allocation success message appeared: " + successMsg.getText());
		} catch (TimeoutException e) {
			Log.error("Timeout: Allocation success message did not appear.");
		}

	}

	public boolean verifyAllocation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

		try {

			WebElement dashboardMenu = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dashboardMenu));
			dashboardMenu.click();
			WebElement allocationSummaryLink = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLink));
			allocationSummaryLink.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

			String cardText = driver.findElement(By.xpath("(//span[@class = 'rz-cell-data'])[4]")).getText();
			Log.info("Raw text from grid cell: " + cardText);

			String actualCountStr = cardText.replaceAll("\\D+", ""); // extract only numbers
			int actualCount = actualCountStr.isEmpty() ? 0 : Integer.parseInt(actualCountStr);

			Log.info("Actual allocation count: " + actualCount);
			return actualCount > 0;

		} catch (Exception e) {
			Log.error("Exception occurred during allocation summary verification: " + e.getMessage());
			return false;
		}
	}
*/	

	public void clickFileDownloadButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//i[@class = 'rzi d-inline-flex justify-content-center align-items-center'])[2]")));
		button.click();
		Log.info("Sucessfully clicked on the Download button");

	}
}
