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
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Common;
import com.BasePackage.DBUtils;
import com.BasePackage.DownloadedExcelReader;
import com.Page_Repository.CollectionAgency_AllocationSummaryPageRepo;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;

public class CA_Allocation_Summary_Page {

	private WebDriver driver;

	public CA_Allocation_Summary_Page(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void navigateToAllocationSummary() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		By dashboardTest1 = By.xpath("//span[@class='text nav-text' and text()='Dash board']");
		By dashboardTest2 = By.xpath("//a[.//span[contains(text(),'Dashboard')]]");

		List<WebElement> dashboardMenus = driver.findElements(dashboardTest1);

		WebElement dashboardMenu = null;

		try {
			dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTest1));
			Log.info("Test 1 Dashboard Menu is present.");
		} catch (TimeoutException e1) {
			try {
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		WebElement allocationSummaryLabel = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocationSummaryLabel));
		Log.info("Allocation Summary page opens, showing the grid with the specified columns");
		WebElement grid = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.grid));
		Log.info("Page label name  allocation summary should be displayed ");
		return allocationSummaryLabel.isDisplayed() && grid.isDisplayed();
	}

	// Method to verify Date column is displayed
	public boolean isDateColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			WebElement dateColumn = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dateColumn));
			Log.info("Date column found using Test 1 XPath.");
			return dateColumn.isDisplayed();

		} catch (TimeoutException e1) {

			try {

				WebElement dateColumn1 = wait.until(ExpectedConditions
						.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.dateColumn1));
				return dateColumn1.isDisplayed();

			} catch (TimeoutException e2) {
				Log.error("Date column not found in either Test 1 or Test 2.");
				return false;
			}
		}
	}

	// Method to verify Total Accounts Received column is displayed
	public boolean isTotalAccountsReceivedColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			WebElement totalAccountsReceivedColumn = wait.until(ExpectedConditions.visibilityOfElementLocated(
					CollectionAgency_AllocationSummaryPageRepo.totalAccountsReceivedColumn));
			return totalAccountsReceivedColumn.isDisplayed();

		} catch (TimeoutException e1) {

			try {
				// Fallback to Test 2 XPath
				WebElement totalAccountsReceivedColumn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
						CollectionAgency_AllocationSummaryPageRepo.totalAccountsReceivedColumn1));
				return totalAccountsReceivedColumn1.isDisplayed();

			} catch (TimeoutException e2) {
				Log.error("Total Accounts Received column not found in either Test 1 or Test 2.");
				return false;
			}
		}
	}

	// Method to verify Allocated to Agents column is displayed
	public boolean isAllocatedToAgentsColumnDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {

			WebElement allocatedToAgentsColumn = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.allocatedToAgentsColumn));
			return allocatedToAgentsColumn.isDisplayed();

		} catch (TimeoutException e1) {

			try {
				WebElement allocatedToAgentsColumn1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
						CollectionAgency_AllocationSummaryPageRepo.allocatedToAgentsColumn1));
				return allocatedToAgentsColumn1.isDisplayed();

			} catch (TimeoutException e2) {
				Log.error("Allocated to Agents column not found in either Test 1 or Test 2.");
				return false;
			}
		}
	}

	public boolean isGridValuesDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			WebElement gridValues = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.gridValues));

			return gridValues.isDisplayed();
		} catch (TimeoutException e1) {
			try {
				WebElement gridValues1 = wait.until(ExpectedConditions
						.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.gridValues1));
				return gridValues1.isDisplayed();

			} catch (TimeoutException e2) {
				Log.error("Grid values are not found in either Test 1 or Test 2.");
				return false;
			}
		}
	}

	public void clickDownloadButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			WebElement button = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.downloadButton));
			button.click();
		} catch (TimeoutException e1) {

			try {
				WebElement downloadButton1 = wait.until(ExpectedConditions
						.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.downloadButton1));
				downloadButton1.click();

			} catch (TimeoutException e2) {
				Log.error("Download button is not clicked in either Test 1 or Test 2.");
			}
		}
	}

	public boolean isNoDataMessageDisplayed() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement errorMsg = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.noDataMessage));
			return errorMsg.isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	public void navigateToAddAgentPage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement agentManagment = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentManagement));
		agentManagment.click();

		WebElement addNewAgent = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.addNewAgent));
		addNewAgent.click();
	}

	public void createAgent(String code, String name, String phone) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.agentCode))
				.sendKeys(code);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.name))
				.sendKeys(name);

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.phoneNumber))
				.sendKeys(phone);

		wait.until(ExpectedConditions.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.role))
				.click();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.roleDropdown)).click();
		} catch (TimeoutException e1) {
			try {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.roleDropdown1)).click();

			} catch (TimeoutException e2) {
				Log.error("Role dropdown is not clicked in either Test 1 or Test 2.");
			}
		}

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));

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
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		Common.waitForSpinnerToDisappear2(driver, "Types of account Spinner", DispositionMasterPageRepo.spinner);

		wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccount))
				.click();
		Log.info("Clicked Types of account field.");

		wait.until(ExpectedConditions
				.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.typesOfAccountDropdown)).click();
		Log.info("Selected Value Types of account field.");

		Common.waitForSpinnerToDisappear2(driver, "Asset Category Spinner", DispositionMasterPageRepo.spinner);
		// Thread.sleep(20000);

		Common.fluentWait("Asset Category", CollectionAgency_AllocationSummaryPageRepo.assetCoreCategory);
		WebElement assetCategory = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategory);
		assetCategory.click();
		Log.info("Clicked Asset Category field.");

		Common.fluentWait("Asset Category1", CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown1);
		WebElement assetCoreCategoryDropdown1 = driver
				.findElement(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown1);
		assetCoreCategoryDropdown1.click();
		Log.info("Selected Asset Category field.");

//		Common.fluentWait("Asset Category2", CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown2);
//		WebElement assetCoreCategoryDropdown2 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.assetCoreCategoryDropdown2);
//		assetCoreCategoryDropdown2.click();
//		Log.info("Selected Asset Category field.");

		Common.fluentWait("SMA Category", CollectionAgency_AllocationSummaryPageRepo.smaCategoryDropdown);
		WebElement smaCategoryDropdown = driver
				.findElement(CollectionAgency_AllocationSummaryPageRepo.smaCategoryDropdown);
		smaCategoryDropdown.click();
		Log.info("Clicked SMA Category field.");

		Common.fluentWait("SMA Category1", CollectionAgency_AllocationSummaryPageRepo.smaCategory1);
		WebElement smaCategory1 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.smaCategory1);
		smaCategory1.click();
		Log.info("Selected SMA Category1 field.");

		// Thread.sleep(2000);
		Common.fluentWait("SMA Category2", CollectionAgency_AllocationSummaryPageRepo.smaCategory2);
		WebElement smaCategory2 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.smaCategory2);
		smaCategory2.click();
		Log.info("Selected SMA Category2 field.");

		// Thread.sleep(2000);
//		Common.fluentWait("NPA Category", CollectionAgency_AllocationSummaryPageRepo.npaCategoryDropdown);
//		WebElement npaCategoryField = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.npaCategoryDropdown);
//		npaCategoryField.click();	
//		Log.info("Selected NPA Category field.");

//		Common.fluentWait("NPA Category1", CollectionAgency_AllocationSummaryPageRepo.npaCategory);
//		WebElement  npaCategoryDropdonw1 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.npaCategory);
//		npaCategoryDropdonw1.click();	
//		Log.info("Selected NPA Category1 field.");
//
//		Thread.sleep(2000);
//		
//		Common.fluentWait("NPA Category2", CollectionAgency_AllocationSummaryPageRepo.npaCategory2);
//		WebElement npaCategoryDropdonw2 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.npaCategory2);
//		npaCategoryDropdonw2.click();
//		Log.info("Selected NPA2 Category2 field.");

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
		try {
			WebElement drodownValue = wait.until(ExpectedConditions
					.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.collectionAgencyDropdown));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
			drodownValue.click();
			Log.info("Selected collection agency value from the dropdown Test 1");

		} catch (TimeoutException e1) {
			try {
				WebElement dropdownValue1 = wait.until(ExpectedConditions
						.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.collectionAgencyDropdown1));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownValue1);
				dropdownValue1.click();
				Log.info("Selected collection agency value from the dropdown Test 2");
			} catch (TimeoutException e2) {
				throw new NoSuchElementException(
						"Collection agency dropdown value not found for either Test 1 or Test 2.");
			}
		}

		WebElement allocateButton = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.allocate));
		allocateButton.click();
		Thread.sleep(500);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Log.info("Clicked on the allocate button");

	}

	public void Logout() throws Exception {
		WebElement user = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.userIcon);
		user.click();

		WebElement logOut = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.logOut);
		logOut.click();
		Log.info("Logout successful");

	}

	public boolean VerifyAccountAllocationDisplayingasSubmenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement agentAccountAllocationMenu = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Accounts Allocation']")));
		Log.info("Agent Account Allocation Menu display sucessfully");
		return agentAccountAllocationMenu.isDisplayed();

	}

	public void allocateAgentAccounts() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement parentAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Accounts Allocation']")));
		js.executeScript("arguments[0].scrollIntoView(true);", parentAccount);
		wait.until(ExpectedConditions.elementToBeClickable(parentAccount));

		parentAccount.click();
		Log.info("Clicked Account Allocation menu.");

		WebElement childSubMenu = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Agent Account Allocation')]")));
		childSubMenu.click();
		Log.info("Clicked Agent Account Allocation submenu.");

		Common.waitForSpinnerToDisappear2(driver, "Asset Category Spinner", DispositionMasterPageRepo.spinner);
		Thread.sleep(20000);

		Common.fluentWait("Asset Category", CollectionAgency_AllocationSummaryPageRepo.assetCategory);
		WebElement assetCategory = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.assetCategory);
		assetCategory.click();
		Log.info("Clicked on the Asset Category Field.");

		Thread.sleep(10000);

		// Common.waitForSpinnerToDisappear2(driver, "Asset Category Spinner",
		// DispositionMasterPageRepo.spinner);

		Common.fluentWait("Asset Category Dropdown", CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown);
		WebElement assetCategoryDropdown = driver
				.findElement(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown);
		assetCategoryDropdown.click();
		Log.info("Asset Category Dropdown value is selected");

		// Thread.sleep(20000);

		// WebElement assetCategoryDropdown2 =
		// wait.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.assetCategoryDropdown));
		// assetCategoryDropdown2.click();
		// Log.info("Asset Category Dropdown2 value is selected");
		// Thread.sleep(5000);

		Common.fluentWait("SMA Category", CollectionAgency_AllocationSummaryPageRepo.SMACategoryCollection);
		WebElement smaCategory = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.SMACategoryCollection);
		smaCategory.click();
		Log.info("Clicked on SMA Category Field");

		Common.fluentWait("SMA Category Dropdown1", CollectionAgency_AllocationSummaryPageRepo.SMACategory1);
		WebElement smaCategoryDropdown1 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.SMACategory1);
		smaCategoryDropdown1.click();
		Log.info("SMA Category1 Dropdown value is selected.");

		Common.fluentWait("SMA Category Dropdown2", (CollectionAgency_AllocationSummaryPageRepo.SMACategory2));
		WebElement smaCategoryDropdown2 = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.SMACategory2);
		smaCategoryDropdown2.click();
		Log.info("SMA Category2 Dropdown value is selected.");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ESCAPE).build().perform();
		Log.info("Closed SMA Category dropdown using ESC key.");

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

		Common.fluentWait("Types of Account", (CollectionAgency_AllocationSummaryPageRepo.TypesofAccount));
		WebElement typeDropdown = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.TypesofAccount);
		typeDropdown.click();
		Log.info("Clicked on the Type of Account Field.");

		Common.fluentWait("Not Allocated", (CollectionAgency_AllocationSummaryPageRepo.NotAllocated));
		WebElement selectTypeDropdown = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.NotAllocated);
		selectTypeDropdown.click();
		Log.info("Type of Account Dropdown value is selected.");

		Common.fluentWait("Search", (CollectionAgency_AllocationSummaryPageRepo.Search));
		WebElement searchBtn = driver.findElement(CollectionAgency_AllocationSummaryPageRepo.Search);
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

		Thread.sleep(1000);

		WebElement selectAccount2 = wait
				.until(ExpectedConditions.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.accoun3));
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", selectAccount2);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", selectAccount2);
		Thread.sleep(1000);

		selectAccount2.click();
		Log.info("Sucessfully clicked on the account3 checkbox");

		// Select the agent from dropdown

		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgent));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		Log.info("Scrolled to and clicked on the select collection agent field");

		try {

			WebElement drodownValue = wait.until(ExpectedConditions
					.visibilityOfElementLocated(CollectionAgency_AllocationSummaryPageRepo.collectionAgentDropdown));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drodownValue);
			drodownValue.click();
			Log.info("Selected collection agent value from the dropdown Test 1");

		} catch (TimeoutException e1) {
			try {
				WebElement dropdownValue1 = wait.until(ExpectedConditions
						.elementToBeClickable(CollectionAgency_AllocationSummaryPageRepo.collectionAgentDropdown1));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownValue1);
				dropdownValue1.click();
				Log.info("Selected collection agency value from the dropdown Test 2");
			} catch (TimeoutException e2) {
				throw new NoSuchElementException(
						"Collection agent dropdown value not found for either Test 1 or Test 2.");
			}
		}

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

			By dashboardTest1 = By.xpath("//span[@class='text nav-text' and text()='Dash board']");
			By dashboardTest2 = By.xpath("//a[.//span[contains(text(),'Dashboard')]]");

			List<WebElement> dashboardMenus = driver.findElements(dashboardTest1);

			WebElement dashboardMenu = null;

			try {
				dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTest1));
				Log.info("Test 1 Dashboard Menu is present.");
			} catch (TimeoutException e1) {
				try {
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

			// try {
			String cardText = "";
			try {
				cardText = driver.findElement(By.xpath("(//span[@class = 'rz-cell-data'])[4]")).getText();
				Log.info("Test 1 - Raw text from grid cell: " + cardText);
			} catch (Exception e) {
			}
			if (cardText == null || cardText.trim().isEmpty()) {
				try {
					cardText = driver.findElement(By.xpath("(//span[@class = 'rz-cell-data rz-text-truncate'])[10]"))
							.getText();
					Log.info("Test 2 - Raw text from grid cell: " + cardText);
				} catch (Exception e2) {
				}
			}

			String actualCountStr = cardText.replaceAll("\\D+", ""); // extract only numbers
			int actualCount = actualCountStr.isEmpty() ? 0 : Integer.parseInt(actualCountStr);

			Log.info("Actual allocation count: " + actualCount);
			return actualCount > 0;

		} catch (Exception e) {
			Log.error("Exception occurred during allocation summary verification: " + e.getMessage());
			return false;
		}

	}

	public void clickFileDownloadButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		try {
			WebElement button = null;
			try {
				button = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("(//i[@class = 'rzi d-inline-flex justify-content-center align-items-center'])[2]")));
				button.click();
				Log.info("Test 1 - Sucessfully clicked on the Download button");
			} catch (Exception e1) {
			}
			if (button == null) {
				try {
					WebElement fallbackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("(//span[@class='rz-cell-data rz-text-truncate'])[12]")));
					fallbackButton.click();
					Log.info("Test 2 - Successfully clicked on the fallback Download button using alternate XPath.");
				} catch (Exception e2) {
					Log.error("Test 2 - Failed to click fallback download button: " + e2.getMessage());
				}
			}

		} catch (Exception e) {
			Log.error("Exception occurred while clicking the download button: " + e.getMessage());
		}
	}
}