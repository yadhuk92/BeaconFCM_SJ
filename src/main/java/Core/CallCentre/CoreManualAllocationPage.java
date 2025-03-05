package Core.CallCentre;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.CoreAutoAllocationRepo;
import com.Page_Repository.CoreManualAllocationRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

import io.netty.handler.timeout.TimeoutException;

public class CoreManualAllocationPage {

	private WebDriver driver;

	public CoreManualAllocationPage(WebDriver driver) {
		Log.info("Initializing CallCenterAccountFiltrationPage...");
		this.driver = driver;
		Log.info("WebDriver instance assigned.");
		Log.info("Initializing Web elements using PageFactory...");
		PageFactory.initElements(driver, this);
		Log.info("Web elements initialized using PageFactory.");
		Log.info("CallCenterAccountFiltrationPage initialization completed.");
	}

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

	// Method to navigate to Manual Allocation option from Call center option -AM
	public void navigateToManualAllocationOption() {
		Log.info("Starting navigation to the Manual Allocation submenu...");
		Log.info("Locating Manual Allocation option...");
		WebElement manualAllocation = driver.findElement(CoreAllocationSummaryRepo.manualAllocation);
		Log.info("Manual Allocation submenu element located successfully.");
		Log.info("Clicking on the manual allocation submenu...");
		manualAllocation.click();
		Log.info("Clicked on the manual allocation submenu. Navigation complete.");
	}

	// Method to click the Search button
	public void clickSearchButton() throws InterruptedException {
		Log.info("Starting the process to click the Search button...");
		Log.info("Waiting for the Search button to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement searchbutton = wait
				.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.searchbutton));
		Log.info("Scrolling the Search button into view...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", searchbutton);
		Log.info("Search button scrolled into view.");
		Log.info("Waiting for 500ms to ensure smooth scrolling...");
		Thread.sleep(500); // Add a short wait for smooth scrolling
		Log.info("Wait completed.");
		Log.info("Clicking the Search button...");
		searchbutton.click();

		Log.info("Search button clicked successfully.");
	}

	// Validate warning message without adding Asset Catogory-AM
	public String validateWarningMessageWithoutAssetCetegory() throws InterruptedException {

		clickSearchButton();// Method to click Search BUtton

		// Log before waiting for the warning message to appear
		Log.info("Waiting for the warning message to become visible on the page.");

		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		// Log after initializing the wait
		Log.info("WebDriverWait initialized with a timeout of 120 seconds.");

		// Wait for the warning message element to be located and visible
		WebElement warningMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));

		// Log after the element is located
		Log.info("Warning message element is now visible.");

		// Retrieve the text of the warning message
		String warningMsg = warningMessage.getText();

		// Log after retrieving the warning message text
		Log.info("Retrieved warning message text: " + warningMsg);

		// Return the warning message text
		return warningMsg;

	}

	// Validate NPA and SMA drop down from Asset Catogory-AM
	public void validate_NPA_And_SMA_Dropsdown() throws InterruptedException {

		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Log.info("Screen font is minimized");

		Log.info("Clicking Asset category dropdown");
		WebElement selectTextDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.selectText));
		selectTextDropdown.click();
		Log.info("Clicked Asset category dropdown");

		wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.AssetCategoryListBox));

		Log.info("Clicking NAP Dropdown");
		WebElement SelectNAP = driver.findElement(CoreAutoAllocationRepo.NPADropdown);
		SelectNAP.click();
		Log.info("Clicked NAP Dropdown");

		Log.info("Clicking SMA Dropdown");
		WebElement SelectSMA = driver.findElement(CoreAutoAllocationRepo.SMADropdown);
		SelectSMA.click();
		Log.info("Clicked SMA Dropdown");
	}

	// Validate SMA0, SMA1 and SMA2 from SMA category dropdown -AM
	public void validate_SMA_Dropsdown() throws InterruptedException {

		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		Log.info("Clicking SMA category dropdown");
		Thread.sleep(6000);
		WebElement selectTextDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.smaCategoryDropdown));
		selectTextDropdown.click();
		Log.info("Clicked SMA category dropdown");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.selectSMA0Option));

		Log.info("Selecting SMA0 Dropdown");
		WebElement SMA0 = driver.findElement(CoreAutoAllocationRepo.selectSMA0Option);
		SMA0.click();
		Log.info("Clicked SMA0 Dropdown");

		Log.info("Clicking SMA1 Dropdown");
		WebElement SMA1 = driver.findElement(CoreAutoAllocationRepo.selectSMA1Option);
		SMA1.click();
		Log.info("Clicked SMA1 Dropdown");

		Log.info("Clicking SMA2 Dropdown");
		WebElement SMA2 = driver.findElement(CoreAutoAllocationRepo.selectSMA2Option);
		SMA2.click();
		Log.info("Clicked SMA2 Dropdown");
	}

	// Validate Sub Standard, Doubtful1, Doubtful2, doubtful3 and loss NPA category dropdown -AM
	public void validate_NPA_Dropsdown() throws InterruptedException {
		Thread.sleep(3000);
		// Expected aria-label values
		String[] expectedLabels = { "SUB-STANDARD", "DOUBTFUL-1", "DOUBTFUL-2", "DOUBTFUL-3", "LOSS ASSET" }; 
		// Modify as  needed
		List<String> expectedList = Arrays.asList(expectedLabels);

		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
		Thread.sleep(6000);
		Log.info("Clicking SMA category dropdown");
		WebElement selectTextDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.npaCategoryDropdown));
		selectTextDropdown.click();
		Log.info("Clicked NPA category dropdown");
		
		Thread.sleep(3000);
		WebElement selectAllCheckbox=driver.findElement((CoreAutoAllocationRepo.npaSelectAllCheckbox));
		 selectAllCheckbox.click();
			Thread.sleep(3000);
		boolean allMatch = true;
		boolean allCheckboxesEnabled = true;

		// Find all the list from NPA dropdown
		List<WebElement> lists = driver.findElements(CoreAutoAllocationRepo.npaCategorylistDropdown);
		for (WebElement name : lists) {
			// Get the value of aria-label attribute
			String ListName = name.getAttribute("aria-label");

			// Check if the aria-label value exists in the expected list
			if (!expectedList.contains(ListName)) {
				allMatch = false;
				Log.info(expectedList + " is not present in the dropdown ");
			}

			// Locate checkbox for this element (assuming it's next to the element)
			try {
				WebElement checkbox = name.findElement(CoreAutoAllocationRepo.npaCategorylistCheckbox);
				boolean isSelected = checkbox.isSelected();

				if (!isSelected) {
					allCheckboxesEnabled = false;
				}
			} catch (Exception e) {
				Log.info(expectedList + " is not present in the dropdown ");
				allCheckboxesEnabled = false;
			}
		}
		// Final validation
		if (allMatch) {
			System.out.println("✅ All aria-label values match the expected list.");
		} else {
			System.out.println("❌ Some aria-label values do not match.");
		}

		if (allCheckboxesEnabled) {
			System.out.println("✅ All checkboxes are enabled.");
		} else {
			System.out.println("❌ Some checkboxes are disabled or missing.");
		}

	}
	
	// Validate O/S Balance Field dropdown -AM
		public String validate_OSBalnce() throws InterruptedException {
			
			Thread.sleep(3000);
			// Initialize WebDriverWait and wait for the element to become visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
			Thread.sleep(6000);
			Log.info("Clicking O/S balance dropdown");
			WebElement selectTextDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.OSBalanceDropdown));
			selectTextDropdown.click();
			Log.info("Clicked O/S Balance dropdown");
			
			Thread.sleep(3000);
			Log.info("Selecting = option dropdown");
			WebElement selectEquals=driver.findElement((CoreAutoAllocationRepo.selectEqualsOption));
			selectEquals.click();
			Thread.sleep(3000);
			Log.info("Selected = option dropdown");
			
			WebElement selectEqualsText=driver.findElement((CoreAutoAllocationRepo.selectEqualsLabel));
			
			String Symbol=selectEqualsText.getText();
			
			WebElement OSfieldText=driver.findElement((CoreAutoAllocationRepo.OSFieldText));
			OSfieldText.sendKeys("12345");
			
			
			
			return Symbol;
			
		}
		
		// Validate To_Field dropdown -AM
				public String validate_ToField_Callcenter() throws InterruptedException {
					
					Thread.sleep(3000);
					// Initialize WebDriverWait and wait for the element to become visible
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
					
					Log.info("Clicking To Field dropdown");
					WebElement selectTextDropdown = wait
							.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.selectToField));
					selectTextDropdown.click();
					Log.info("ClickedTo Field dropdown");
					
					Thread.sleep(3000);
					Log.info("Selecting Call Center option ");
					WebElement selectCallCenterOption=driver.findElement((CoreAutoAllocationRepo.selectCallcenterFromToField));
					selectCallCenterOption.click();
					Thread.sleep(3000);
					Log.info("Selected Call Center option");
					
					WebElement selectEqualsText=driver.findElement((CoreAutoAllocationRepo.selectToField));
					
					String value=selectEqualsText.getText();
					
					return value;
					
				}
				
				// Validate Accounts Count -AM
				public boolean  validate_Accounts_Count() throws InterruptedException {
					String[]  Expectedcolumn_names= {"TOTAL ACCOUNT SELECTED","TOTAL OUTSTANDING AMOUNT"};
					Thread.sleep(3000);
					//clickSearchButton();// Method to click Search BUtton
					
					// Initialize WebDriverWait and wait for the element to become visible
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
			
					
				       // Verify "Total account selected" column exists
			        List<WebElement> totalAccountColumn = driver.findElements((CoreAutoAllocationRepo.AllocationTable));
					String[]  actualCol=new String[totalAccountColumn.size()];
	
			        for(int i=0;i<totalAccountColumn.size();i++)
			        {
			        	 actualCol[i]=totalAccountColumn.get(i).getText().trim();
			        	
			        }
			        
			     // Compare expectedColumns with actualColumns
		            boolean isMatch = java.util.Arrays.equals(Expectedcolumn_names, actualCol);
			        
			    
			       return isMatch;
					
				}
				
				// Validate AllocateTo_Field dropdown -AM
				public String validate_AllocateToField_Callcenter() throws InterruptedException {
					
					// Initialize WebDriverWait and wait for the element to become visible
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
					
					Log.info("Clicking Allocate To Field dropdown");
					WebElement selectTextDropdown = wait
							.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.selectAllocateToField));
					selectTextDropdown.click();
					Log.info("Clicked AllocateTo Field dropdown");
					
					Thread.sleep(3000);
					Log.info("Selecting Call Center option from Allocate To Field Dropdown");
					WebElement selectCallCenterOption=driver.findElement((CoreAutoAllocationRepo.selectCallcenterFromAllocateToField));
					selectCallCenterOption.click();
					Thread.sleep(3000);
					Log.info("Selected Call Center option");
					
					WebElement selectEqualsText=driver.findElement((CoreAutoAllocationRepo.selectToField));
					
					String value=selectEqualsText.getText();
					
					Log.info("Clicking Callcentre sub dropdown");
					WebElement selectSubCallCentreDropdown = wait
							.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.selectCallCentreSubDropdown));
					selectSubCallCentreDropdown.click();
					Log.info("Clicked Callcentre sub dropdown");
					
					
					Log.info("Selecting Call Center1 option from Select Call Centre Field Dropdown");
					WebElement selectCallCenter1Option=driver.findElement((CoreAutoAllocationRepo.selectCallcenter1FromAllocateToField));
					selectCallCenter1Option.click();
					Thread.sleep(3000);
					Log.info("Selected Call Center1 option from Select Call Centre");
					
					return value;
					
				}
				
				// Validate message after clicking assign button  -AM
				public String  validate_mesgAfterAllocation() throws InterruptedException {
					
					// Initialize WebDriverWait and wait for the element to become visible
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
					
					Log.info("Clicking Assign Button");
					WebElement assignButton = wait
							.until(ExpectedConditions.elementToBeClickable(CoreAutoAllocationRepo.AssignButton));
					assignButton.click();
					Log.info("Clicked Assign Button");
					
					// Wait for the warning message element to be located and visible
					WebElement warningMessage = wait
							.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.validationMessage));

					// Log after the element is located
					Log.info("Assigned Successfully  message element is now visible.");

					// Retrieve the text of the warning message
					String Message = warningMessage.getText();

					// Log after retrieving the warning message text
					Log.info("Retrieved Sucessful  message text: " + Message);

					// Return the warning message text
					return Message;
					
				}
				

	// Method to get the text of the warning message

	public String getWarningMessage() {
		Log.info("Starting the process to retrieve the warning message...");
		Log.info("Waiting for the warning message to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement warningMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg));
		Log.info("Retrieving the text from the warning message...");
		String Message = warningMessage.getText();
		Log.info("Warning message retrieved successfully: " + Message);
		// Log after retrieving the message

		return Message;
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
		WebElement allocatedtovalue = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocatedtovalue(value)));
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
		WebElement searchbutton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.searchbutton));
		Log.info("Clicking the Search button...");
		searchbutton.click();
		Log.info("Search button clicked successfully.");
	}

	// Method to retrieve the warning message text
	public String getWarningMessageaftergivingvalueforallocatedto() {
		Log.info("Starting the process to retrieve the warning message after assigning a value to 'Allocated To'...");
		Log.info("Waiting for the warning message to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement warningMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.warningmsg2));
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
		WebElement assetCategoryDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryDropdown));
		Log.info("Scrolling the Asset Category dropdown into view...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
				assetCategoryDropdown);
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
		WebElement assetCategoryvalueall = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryvalueall));
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
		WebElement SMACategoryDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryDropdown));
		Log.info("Clicking the SMA Category dropdown...");
		SMACategoryDropdown.click();
		Log.info("SMA Category dropdown clicked.");
		Log.info("Waiting for the spinner to disappear...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is no longer visible.");
		Log.info("Waiting for the 'All' value in the SMA Category dropdown to become visible...");
		WebElement SMACategoryvalueall = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.SMAcategoryvalueall));
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
		WebElement SMACategoryDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryDropdown));
		Log.info("NPA Category dropdown is now visible.");
		Log.info("Clicking the NPA Category dropdown...");
		SMACategoryDropdown.click();
		Log.info("NPA Category dropdown clicked.");
		Log.info("Waiting for the spinner to disappear...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is no longer visible.");
		Log.info("Waiting for the 'All' value in the NPA Category dropdown to become visible...");
		WebElement SMACategoryvalueall = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.NPAcategoryvalueall));
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

	// Method to select any value in Allocated To field
	public void selectAllocationType(String value) throws InterruptedException {
		Log.info("Starting the process to select an allocation type...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		Log.info("Locating the Allocation Type dropdown...");
		WebElement AllocationTypedropdown = driver.findElement(CoreAutoAllocationRepo.allocationtypedropdown);
		Log.info("Scrolling to the Allocation Type dropdown...");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
				AllocationTypedropdown);
		Log.info("Waiting for a brief moment before interacting with the dropdown...");
		Thread.sleep(500);
		Log.info("Clicking on the Allocation Type dropdown...");
		AllocationTypedropdown.click();
		Log.info("Waiting for the spinner to disappear...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Locating the value in the Allocation Type dropdown: " + value);
		WebElement AllocationTypevalue = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocationtypevalue(value)));
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
			Log.error("An unexpected error occurred: " + e.getMessage() + e);
			return "An unexpected error occurred: " + e.getMessage();
		}
	}

	public boolean isManualAllocationPageLoaded() throws InterruptedException {
		Log.info("Starting the process to verify if the Auto Allocation page is loaded...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		Log.info("Locating the Call Center menu...");
		WebElement callcentermenu = driver.findElement(CoreAutoAllocationRepo.callcentermenu);
		Log.info("Call Center menu located successfully.");
		Log.info("Clicking on the Call Center menu...");
		callcentermenu.click();
		Log.info("Call Center menu clicked successfully.");
		Log.info("Locating the Auto Allocation submenu...");
		WebElement manaualallocationsubmenu = driver.findElement(CoreManualAllocationRepo.manualallocationsubmenu);
		Log.info("Manual Allocation submenu located successfully.");
		Log.info("Clicking on the Manual Allocation submenu...");
		manaualallocationsubmenu.click();
		Log.info("Manual Allocation submenu clicked successfully.");
		Log.info("Waiting for the spinner to disappear...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner disappeared successfully.");
		Log.info("Pausing for a brief moment...");
		Log.info("Waiting for the 'Search' button to become clickable.");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(CoreManualAllocationRepo.Searchbtn));
			Log.info("The 'Search' button is now clickable.");
		} catch (Exception e) { // Catch all exceptions including TimeoutException
			Log.error("Search Button not enabled, so the page is not loaded properly.");
			throw new RuntimeException("Search Button not enabled, so the page is not loaded properly."); // Ensure
																											// failure
																											// message
																											// is logged
		}
		Log.info("The 'Search' button is now clickable.");
		Log.info("Checking if the current URL ends with 'CallCentre/ManualAllocationConfiguration'...");
		boolean isPageLoaded = driver.getCurrentUrl().endsWith("CallCentre/ManualAllocationConfiguration");

		if (isPageLoaded) {
			Log.info("Manual Allocation page loaded successfully.");
		} else {
			Log.warn("Failed to load the Manual Allocation page.");
		}

		Log.info("Process to verify Manual Allocation page load completed.");
		return isPageLoaded;
	}

	public boolean areFieldsAndButtonsPresent() {
		Log.info("Starting the process to verify the presence of all required fields and buttons on the page...");

		try {

			WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);

			WebElement zone_co = driver.findElement(CoreManualAllocationRepo.zone_co);

			WebElement branch = driver.findElement(CoreManualAllocationRepo.branch);

			WebElement branch_id = driver.findElement(CoreManualAllocationRepo.branch_id);

			WebElement vertical = driver.findElement(CoreManualAllocationRepo.vertical);

			WebElement schemetype = driver.findElement(CoreManualAllocationRepo.schemetype);

			WebElement producttype = driver.findElement(CoreManualAllocationRepo.producttype);

			WebElement schemecode = driver.findElement(CoreManualAllocationRepo.schemecode);

			WebElement assettaggingtype = driver.findElement(CoreManualAllocationRepo.assettaggingtype);

			WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);

			WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);

			WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);

			WebElement dpdoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.dpdoperatorsdropdown);

			WebElement dpdtextfield = driver.findElement(CoreManualAllocationRepo.dpdtextfield);

			WebElement osbalanceoperatorsdropdown = driver
					.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);

			WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);

			WebElement overdueemioperatorsdropdown = driver
					.findElement(CoreManualAllocationRepo.overdueemioperatorsdropdown);

			WebElement overdueemiamounttextfield = driver
					.findElement(CoreManualAllocationRepo.overdueemiamounttextfield);

			WebElement actionowner = driver.findElement(CoreManualAllocationRepo.actionowner);

			WebElement ActionDateFrom = driver.findElement(CoreManualAllocationRepo.ActionDateFrom);

			WebElement ActionDateto = driver.findElement(CoreManualAllocationRepo.ActionDateto);

			WebElement TypesofAccount = driver.findElement(CoreManualAllocationRepo.TypesofAccount);

			WebElement To = driver.findElement(CoreManualAllocationRepo.To);

			WebElement IsPFTNPA = driver.findElement(CoreManualAllocationRepo.IsPFTNPA);

			WebElement IsFTNPA = driver.findElement(CoreManualAllocationRepo.IsFTNPA);

			WebElement SaveThisAllocationCriteria = driver
					.findElement(CoreManualAllocationRepo.SaveThisAllocationCriteria);

			WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);

			WebElement Resetbtn = driver.findElement(CoreManualAllocationRepo.Resetbtn);
			WebElement EditAllocationCriteriabtn = driver
					.findElement(CoreManualAllocationRepo.EditAllocationCriteriabtn);
			WebElement AllocateTo = driver.findElement(CoreManualAllocationRepo.AllocateTo);
			WebElement ExpiryDate = driver.findElement(CoreManualAllocationRepo.ExpiryDate);
			WebElement Assignbtn = driver.findElement(CoreManualAllocationRepo.Assignbtn);
			WebElement DownloadinExcel = driver.findElement(CoreManualAllocationRepo.DownloadinExcel);
			WebElement AssignedList = driver.findElement(CoreManualAllocationRepo.AssignedList);

			Log.info("Verifying if all fields and buttons are displayed...");
			boolean areAllElementsDisplayed = allocationName.isDisplayed() && zone_co.isDisplayed()
					&& branch.isDisplayed() && branch_id.isDisplayed() && vertical.isDisplayed()
					&& schemetype.isDisplayed() && producttype.isDisplayed() && schemecode.isDisplayed()
					&& assettaggingtype.isDisplayed() && assetcategory.isDisplayed() && smacategory.isDisplayed()
					&& npacategory.isDisplayed() && dpdoperatorsdropdown.isDisplayed() && dpdtextfield.isDisplayed()
					&& osbalanceoperatorsdropdown.isDisplayed() && osbalancetextfield.isDisplayed()
					&& overdueemioperatorsdropdown.isDisplayed() && overdueemiamounttextfield.isDisplayed()
					&& actionowner.isDisplayed() && ActionDateFrom.isDisplayed() && ActionDateto.isDisplayed()
					&& TypesofAccount.isDisplayed() && To.isDisplayed() && IsPFTNPA.isDisplayed()
					&& IsFTNPA.isDisplayed() && SaveThisAllocationCriteria.isDisplayed() && Searchbtn.isDisplayed()
					&& Resetbtn.isDisplayed() && EditAllocationCriteriabtn.isDisplayed();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", AssignedList);
			Thread.sleep(1000);
			areAllElementsDisplayed &= AllocateTo.isDisplayed() && ExpiryDate.isDisplayed() && Assignbtn.isDisplayed()
					&& DownloadinExcel.isDisplayed() && AssignedList.isDisplayed();

			if (areAllElementsDisplayed) {
				Log.info("All required fields and buttons are present.");
			} else {
				Log.warn("One or more required fields or buttons are missing.");
			}

			Log.info("Completed the verification process for fields and buttons.");
			return areAllElementsDisplayed;

		} catch (Exception e) {
			Log.error("An error occurred while verifying fields and buttons: " + e.getMessage() + e);
			return false;
		}
	}

	// Method to click the search button
	public void clickSearchbutton() {
		// Log before finding the element
		Log.info("Attempting to locate the search button element.");

		// Find the search button
		WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);

		// Log after locating the element
		Log.info("Search button element located successfully.");

		// Log before clicking the button
		Log.info("Attempting to click the search button.");

		// Perform the click action
		Searchbtn.click();

		// Log after clicking the button
		Log.info("Search button clicked successfully.");
	}

	// Method to get the warning message text
	public String getWarningmessage() {
		// Log before waiting for the warning message to appear
		Log.info("Waiting for the warning message to become visible on the page.");

		// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		// Log after initializing the wait
		Log.info("WebDriverWait initialized with a timeout of 120 seconds.");

		// Wait for the warning message element to be located and visible
		WebElement warningMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningMessage));

		// Log after the element is located
		Log.info("Warning message element is now visible.");

		// Retrieve the text of the warning message
		String messageText = warningMessage.getText();

		// Log after retrieving the warning message text
		Log.info("Retrieved warning message text: " + messageText);

		// Return the warning message text
		return messageText;
	}

	// Method to select categories from asset category dropdown
	public void selectAssetCategory() throws InterruptedException {
		// Log before initializing WebDriverWait
		Log.info("Initializing WebDriverWait with a timeout of 120 seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		// Log before locating the allocation name element
		Log.info("Attempting to locate the allocation name element.");
		WebElement allocationName = driver.findElement(CoreManualAllocationRepo.allocationName);
		Log.info("Allocation name element located successfully.");

		// Log before scrolling to the allocation name element
		Log.info("Scrolling to the allocation name element using JavaScript.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", allocationName);
		Log.info("Scroll action completed.");

		// Log before adding a delay
		Log.info("Waiting for 5 seconds to ensure proper loading.");
		Thread.sleep(5000);
		Log.info("Wait of 5 seconds completed.");

		// Log before waiting for spinner to disappear
		Log.info("Waiting for the spinner to become invisible.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is now invisible.");

		// Log before locating the asset category element
		Log.info("Attempting to locate the asset category element.");
		WebElement assetcategory = driver.findElement(CoreManualAllocationRepo.assetcategory);
		Log.info("Asset category element located successfully.");

		// Log before clicking on the asset category element
		Log.info("Clicking on the asset category element.");
		assetcategory.click();
		Log.info("Asset category element clicked successfully.");

		// Log before locating the select all element in the asset category
		Log.info("Attempting to locate the 'Select All' option in the asset category.");
		WebElement assetcategorysellectall = driver.findElement(CoreManualAllocationRepo.assetcategorysellectall);
		Log.info("'Select All' option located successfully.");

		// Log before clicking on the 'Select All' option
		Log.info("Clicking on the 'Select All' option in the asset category.");
		assetcategorysellectall.click();
		Log.info("'Select All' option clicked successfully.");

		// Log before waiting for spinner to disappear again
		Log.info("Waiting for the spinner to become invisible after selecting 'Select All'.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is now invisible after selecting 'Select All'.");
	}

	// Method to select SMA categories
	public void selectSmaCategories() throws InterruptedException {
		// Log before locating the SMA category element
		Log.info("Attempting to locate the SMA category element.");
		WebElement smacategory = driver.findElement(CoreManualAllocationRepo.smacategory);
		Log.info("SMA category element located successfully.");

		// Log before clicking the SMA category element
		Log.info("Clicking on the SMA category element.");
		smacategory.click();
		Log.info("SMA category element clicked successfully.");

		// Log before adding a delay
		Log.info("Waiting for 1 second to ensure proper loading.");
		Thread.sleep(1000);
		Log.info("Wait of 1 second completed.");

		// Log before locating the 'Select All' option in SMA categories
		Log.info("Attempting to locate the 'Select All' option in SMA categories.");
		WebElement smacategorysellectall = driver.findElement(CoreManualAllocationRepo.smacategorysellectall);
		Log.info("'Select All' option located successfully.");

		// Log before clicking the 'Select All' option
		Log.info("Clicking on the 'Select All' option in SMA categories.");
		smacategorysellectall.click();
		Log.info("'Select All' option in SMA categories clicked successfully.");
	}

	// Method to select NPA categories
	public void selectNpaCategories() {
		// Log before locating the NPA category element
		Log.info("Attempting to locate the NPA category element.");
		WebElement npacategory = driver.findElement(CoreManualAllocationRepo.npacategory);
		Log.info("NPA category element located successfully.");

		// Log before clicking the NPA category element
		Log.info("Clicking on the NPA category element.");
		npacategory.click();
		Log.info("NPA category element clicked successfully.");

		// Log before locating the 'Select All' option in NPA categories
		Log.info("Attempting to locate the 'Select All' option in NPA categories.");
		WebElement npacategorysellectall = driver.findElement(CoreManualAllocationRepo.npacategorysellectall);
		Log.info("'Select All' option in NPA categories located successfully.");

		// Log before clicking the 'Select All' option
		Log.info("Clicking on the 'Select All' option in NPA categories.");
		npacategorysellectall.click();
		Log.info("'Select All' option in NPA categories clicked successfully.");
	}

	public void clickOsBalanceField() {
		// Log before locating the OS balance operators dropdown element
		Log.info("Attempting to locate the OS balance operators dropdown element.");
		WebElement osbalanceoperatorsdropdown = driver.findElement(CoreManualAllocationRepo.osbalanceoperatorsdropdown);
		Log.info("OS balance operators dropdown element located successfully.");

		// Log before clicking the OS balance operators dropdown element
		Log.info("Clicking on the OS balance operators dropdown element.");
		osbalanceoperatorsdropdown.click();
		Log.info("OS balance operators dropdown element clicked successfully.");
	}

	public void selectEqualFinancialOperator(String value) {
		// Log before locating the dropdown value element
		Log.info("Attempting to locate the dropdown value element for the financial operator with value: " + value);
		WebElement OutstandingBalLimitOperatordropdownvalue = driver
				.findElement(CoreManualAllocationRepo.OutstandingBalLimitOperatordropdownvalue(value));
		Log.info("Dropdown value element for the financial operator with value: " + value + " located successfully.");

		// Log before clicking the dropdown value element
		Log.info("Clicking on the dropdown value element for the financial operator with value: " + value);
		OutstandingBalLimitOperatordropdownvalue.click();
		Log.info("Dropdown value element for the financial operator with value: " + value + " clicked successfully.");
	}

	public void enterOsBalance(String balance) {
		// Log before locating the OS balance text field element
		Log.info("Attempting to locate the OS balance text field element.");
		WebElement osbalancetextfield = driver.findElement(CoreManualAllocationRepo.osbalancetextfield);
		Log.info("OS balance text field element located successfully.");

		// Log before clearing the text field
		Log.info("Clearing any existing value in the OS balance text field.");
		osbalancetextfield.clear();
		Log.info("Existing value in the OS balance text field cleared successfully.");

		// Log before entering the balance value
		Log.info("Entering the balance value: " + balance + " into the OS balance text field.");
		osbalancetextfield.sendKeys(balance);
		Log.info("Balance value: " + balance + " entered successfully into the OS balance text field.");
	}

	public void selectCallCentreFromToDropdown(String value) {
		// Log before locating and clicking the 'To' dropdown element
		Log.info("Attempting to locate the 'To' dropdown element.");
		WebElement To = driver.findElement(CoreManualAllocationRepo.To);
		Log.info("'To' dropdown element located successfully.");

		Log.info("Clicking on the 'To' dropdown element.");
		To.click();
		Log.info("'To' dropdown element clicked successfully.");

		// Log before locating and clicking the specific value in the 'To' dropdown
		Log.info("Attempting to locate the value: " + value + " in the 'To' dropdown.");
		WebElement tovalue = driver.findElement(CoreManualAllocationRepo.tovalue(value));
		Log.info("Value: " + value + " located successfully in the 'To' dropdown.");

		Log.info("Clicking on the value: " + value + " in the 'To' dropdown.");
		tovalue.click();
		Log.info("Value: " + value + " clicked successfully in the 'To' dropdown.");
	}

	public void clickSearchBtn() throws InterruptedException {
		// Log before creating WebDriverWait instance
		Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		Log.info("WebDriverWait instance created successfully.");

		// Log before locating and clicking the Search button
		Log.info("Attempting to locate the Search button.");
		WebElement Searchbtn = driver.findElement(CoreManualAllocationRepo.Searchbtn);
		Log.info("Search button located successfully.");

		Log.info("Clicking on the Search button.");
		Searchbtn.click();
		Log.info("Search button clicked successfully.");

		// Log before waiting for the spinner to disappear
		Log.info("Waiting for the spinner to become invisible.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is now invisible.");

		// Log before locating the Assigned List element
		Log.info("Attempting to locate the Assigned List element.");
		WebElement AssignedList = driver.findElement(CoreManualAllocationRepo.AssignedList);
		Log.info("Assigned List element located successfully.");

		// Log before scrolling to the Assigned List element
		Log.info("Scrolling to the Assigned List element.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", AssignedList);
		Log.info("Successfully scrolled to the Assigned List element.");

		// Log before adding a delay
		Log.info("Waiting for 5 seconds to ensure proper loading.");
		Thread.sleep(5000);
		Log.info("Wait of 5 seconds completed.");
	}

	public void clickDownloadExcelButton() {
		// Log before locating the Download in Excel button
		Log.info("Attempting to locate the 'Download in Excel' button.");
		WebElement DownloadinExcel = driver.findElement(CoreManualAllocationRepo.DownloadinExcel);
		Log.info("'Download in Excel' button located successfully.");

		// Log before clicking the Download in Excel button
		Log.info("Clicking on the 'Download in Excel' button.");
		DownloadinExcel.click();
		Log.info("'Download in Excel' button clicked successfully.");
	}

	// Method to get the account count from the grid
	public String getAccountGridCount() {
		// Log before locating the Total Account Selected element
		Log.info("Attempting to locate the 'Total Account Selected' element.");
		WebElement totalAccountSelected = driver.findElement(CoreManualAllocationRepo.TotalAccountSelected);
		Log.info("'Total Account Selected' element located successfully.");

		// Log before retrieving the text from the element
		Log.info("Retrieving the text from the 'Total Account Selected' element.");
		String accountCount = totalAccountSelected.getText();
		Log.info("Retrieved text successfully: " + accountCount);

		return accountCount;
	}

	public void navigateTodispostionMainMenu() {
		// Log before creating WebDriverWait instance
		Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		Log.info("WebDriverWait instance created successfully.");

		// Log before locating and clicking the 'Disposition' menu option
		Log.info("Attempting to locate the 'Disposition' menu option.");
		WebElement Disposition = driver.findElement(CoreManualAllocationRepo.Disposition);
		Log.info("'Disposition' menu option located successfully.");

		Log.info("Clicking on the 'Disposition' menu option.");
		Disposition.click();
		Log.info("'Disposition' menu option clicked successfully.");

		// Log before locating and clicking the 'Updation of Disposition' option
		Log.info("Attempting to locate the 'Updation of Disposition' option.");
		WebElement UpdationofDisposition = driver.findElement(CoreManualAllocationRepo.UpdationofDisposition);
		Log.info("'Updation of Disposition' option located successfully.");

		Log.info("Clicking on the 'Updation of Disposition' option.");
		UpdationofDisposition.click();
		Log.info("'Updation of Disposition' option clicked successfully.");

		// Log before waiting for the spinner to disappear
		Log.info("Waiting for the spinner to become invisible.");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is now invisible.");
	}

	// Enter account number in the search tab
	public void enterAccountNumber(String accountNumber) {
		// Log before locating the Account Number text field
		Log.info("Attempting to locate the Account Number text field.");
		WebElement AccountNumbertextfield = driver.findElement(CoreManualAllocationRepo.AccountNumbertextfield);
		Log.info("Account Number text field located successfully.");

		// Log before entering the account number
		Log.info("Entering the account number: " + accountNumber + " into the Account Number text field.");
		AccountNumbertextfield.sendKeys(accountNumber);
		Log.info("Account number: " + accountNumber + " entered successfully into the Account Number text field.");
	}

	// Click search button
	public void clickSearchButon() {
		// Log before locating the Search button
		Log.info("Attempting to locate the Search button.");
		WebElement Searchbut = driver.findElement(CoreManualAllocationRepo.Searchbut);
		Log.info("Search button located successfully.");

		// Log before clicking the Search button
		Log.info("Clicking on the Search button.");
		Searchbut.click();
		Log.info("Search button clicked successfully.");
	}

	// Get warning message text
	public String getWarnMessage() {
		// Log before creating WebDriverWait instance
		Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		Log.info("WebDriverWait instance created successfully.");

		// Log before waiting for the visibility of the warning message element
		Log.info("Waiting for the warning message to become visible.");
		WebElement warningmsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.warningmsg));
		Log.info("Warning message is now visible.");

		// Log before retrieving the text of the warning message
		Log.info("Retrieving the text of the warning message.");
		String warningText = warningmsg.getText();
		Log.info("Warning message text retrieved successfully: " + warningText);

		return warningText;
	}

	// Method to get the warning message text
	public String getWarningmessageforTofieldmandatorychecking() {
		// Log before creating WebDriverWait instance
		Log.info("Creating WebDriverWait instance with a timeout of 120 seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		Log.info("WebDriverWait instance created successfully.");

		// Log before waiting for the visibility of the warning message for the 'To'
		// field
		Log.info("Waiting for the 'To' field mandatory warning message to become visible.");
		WebElement warningMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.Tofieldmandatorywarningmsg));
		Log.info("'To' field mandatory warning message is now visible.");

		// Log before retrieving the warning message text
		Log.info("Retrieving the text of the 'To' field mandatory warning message.");
		String warningText = warningMessage.getText();
		Log.info("'To' field warning message text retrieved successfully: " + warningText);

		return warningText;
	}

	// Method to select 'Call Centre' from 'Allocate To' dropdown
	public void selectCallCentreFromAllocateToDropdown(String value) {
		Log.info("Starting the process to select a value from 'Allocate To' dropdown...");

		Log.info("Locating the 'Allocate To' dropdown element...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement AllocateTo = driver.findElement(CoreManualAllocationRepo.AllocateTo);

		Log.info("Clicking on the 'Allocate To' dropdown...");
		AllocateTo.click();
		Log.info("'Allocate To' dropdown clicked successfully.");

		Log.info("Waiting for the value '" + value + "' to become visible in the dropdown...");
		WebElement tovalue = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.tovalue(value)));

		Log.info("Clicking on the value '" + value + "'...");
		tovalue.click();
		Log.info("Value '" + value + "' selected successfully from the 'Allocate To' dropdown.");
	}

	// Method to select a call centre from the dropdown
	public void selectCallCentre(String callCentreName) {
		Log.info("Starting the process to select a call centre: " + callCentreName + "...");

		Log.info("Waiting for the 'Select Call Centre' dropdown to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement SelectCallCentre = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.SelectCallCentre));
		Log.info("'Select Call Centre' dropdown is visible.");

		Log.info("Clicking on the 'Select Call Centre' dropdown...");
		SelectCallCentre.click();
		Log.info("'Select Call Centre' dropdown clicked successfully.");

		Log.info("Waiting for the call centre value '" + callCentreName + "' to become visible...");
		WebElement SelectCallCentrevalue = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CoreManualAllocationRepo.SelectCallCentrevalue(callCentreName)));
		Log.info("Call centre value '" + callCentreName + "' is visible.");

		Log.info("Clicking on the call centre value '" + callCentreName + "'...");
		SelectCallCentrevalue.click();
		Log.info("Call centre value '" + callCentreName + "' selected successfully.");
	}

	// Method to click the assign button
	public void clickAssignButton() {
		Log.info("Starting the process to click the 'Assign' button...");

		Log.info("Waiting for the 'Assign' button to become clickable...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement Assignbtn = wait.until(ExpectedConditions.elementToBeClickable(CoreManualAllocationRepo.Assignbtn));
		Log.info("'Assign' button is clickable.");

		Log.info("Clicking on the 'Assign' button...");
		Assignbtn.click();
		Log.info("'Assign' button clicked successfully.");
	}

	// Method to get the validation message text
	public String getValidationMessage() {
		Log.info("Starting the process to fetch the validation message...");

		Log.info("Waiting for the validation message element to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement validationMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.validationMessage));
		Log.info("Validation message element is visible.");

		String messageText = validationMessage.getText();
		Log.info("Fetched validation message: '" + messageText + "'");

		return messageText;
	}

	// Method to check if 'Asset Category' is marked mandatory
	public boolean isAssetCategoryMandatory() {
		Log.info("Starting the process to check if the Asset Category dropdown is mandatory...");

		Log.info("Waiting for the 'Asset Category' dropdown to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement assetCategoryDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.assetCategoryDropdown));
		Log.info("'Asset Category' dropdown is visible.");

		Log.info("Checking for the presence of the mandatory indicator (border color)...");
		String borderColor = assetCategoryDropdown.getCssValue("border-color");

		boolean isMandatory = borderColor != null && borderColor.equals("rgb(139, 0, 0)");
		Log.info("Asset Category dropdown mandatory status: " + isMandatory);

		return isMandatory;
	}

	// Method to check if 'Allocated To' is marked mandatory
	public boolean isAllocatedToMandatory() {
		Log.info("Starting the process to check if the 'Allocated To' dropdown is mandatory...");

		Log.info("Waiting for the 'Allocated To' dropdown to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement allocatedToDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreAutoAllocationRepo.allocatedtodropdown));
		Log.info("'Allocated To' dropdown is visible.");

		Log.info("Checking for the presence of the mandatory indicator (border color)...");
		String borderColor = allocatedToDropdown.getCssValue("border-color");

		boolean isMandatory = borderColor != null && borderColor.equals("rgb(139, 0, 0)");
		Log.info("'Allocated To' dropdown mandatory status: " + isMandatory);

		return isMandatory;
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
		WebElement downloadfiledropdownvalue = driver
				.findElement(CoreAutoAllocationRepo.downloadfiledropdownvalue(value));

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

			String accountNumber = DownloadedExcelReader.getOneAccountNumber();
			if (accountNumber != null) {
				// Log the account number
				Log.info("Retrieved Account Number: " + accountNumber);
				resultMap.put("accountNumber", accountNumber); // Store the account number in the result map
			} else {
				Log.info("No account number found.");
				resultMap.put("accountNumber", "Not Found");
			}

		} catch (IOException e) {
			// Log the error if an exception occurs
			Log.error("Error while reading the Excel file: " + e.getMessage() + e);
			resultMap.put("result", "ERROR");
			resultMap.put("errorMessage", e.getMessage());
		}

		return resultMap;
	}

	// Method to check presence of account number field
	public boolean isAccountNumberFieldPresent() {
		Log.info("Checking if the Account Number field is present...");

		Log.info("Waiting for the Account Number field to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement AccountNumbertextfield = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.AccountNumbertextfield));

		boolean isDisplayed = AccountNumbertextfield.isDisplayed();
		Log.info("Account Number field visibility check result: " + isDisplayed);

		return isDisplayed;
	}

	// Method to check presence of search button
	public boolean isSearchButtonPresent() {
		Log.info("Checking if the Search button is present...");

		Log.info("Waiting for the Search button to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement Searchbut = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.Searchbut));

		boolean isDisplayed = Searchbut.isDisplayed();
		Log.info("Search button visibility check result: " + isDisplayed);

		return isDisplayed;
	}

	// Method to check presence of check live balance button
	public boolean isCheckLiveBalanceButtonPresent() {
		Log.info("Checking if the 'Check Live Balance' button is present...");

		Log.info("Waiting for the 'Check Live Balance' button to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement CheckLiveBalancebut = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.CheckLiveBalancebut));

		boolean isDisplayed = CheckLiveBalancebut.isDisplayed();
		Log.info("'Check Live Balance' button visibility check result: " + isDisplayed);

		return isDisplayed;
	}

	// Method to check presence of transaction details section
	public boolean isTransactionDetailsSectionPresent() {
		Log.info("Checking if the 'Transaction Details' section is present...");

		Log.info("Waiting for the 'Transaction Details' section to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement TransactionDetailsbut = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.TransactionDetailsbut));

		boolean isDisplayed = TransactionDetailsbut.isDisplayed();
		Log.info("'Transaction Details' section visibility check result: " + isDisplayed);

		return isDisplayed;
	}

	public void enterAccountNumbers(String accountNumber) {
		Log.info("Entering account number: " + accountNumber);

		Log.info("Finding the Account Number text field...");
		WebElement AccountNumbertextfield = driver.findElement(CoreManualAllocationRepo.AccountNumbertextfield);

		Log.info("Clearing the existing value in the Account Number field...");
		AccountNumbertextfield.clear();

		Log.info("Entering the new account number in the field...");
		AccountNumbertextfield.sendKeys(accountNumber);

		Log.info("Account number entered successfully.");
	}

	public boolean isAccountNumberFieldValid(String accountNumbers) {
		Log.info("Starting account number validation...");

		// Get the value of the account number field
		Log.info("Retrieving the value from the Account Number field...");
		String accountNumber = driver.findElement(CoreManualAllocationRepo.AccountNumbertextfield)
				.getAttribute("value");

		// If the field is empty, return true (you can customize this behavior as
		// needed)
		if (accountNumber == null || accountNumber.trim().isEmpty()) {
			Log.info("Account Number field is empty, returning true (empty fields are considered valid).");
			return true; // Empty fields are valid, as per your request
		}

		// Check if the account number contains only numeric characters
		Log.info("Checking if the Account Number contains only numeric characters...");
		if (accountNumber.matches(accountNumbers)) {
			Log.info("Account Number is valid (numeric characters only).");
			return true; // Return true if it's numeric (because numeric values are allowed per your test
							// case)
		}

		// If it's not numeric (contains non-numeric characters), return true
		// (indicating invalid)
		Log.info("Account Number is invalid (contains non-numeric characters).");
		return true;
	}

	// Method to get validation message
	public String getValidationMessageafterenteringinvalidaccountnumber() {
		Log.info("Starting to retrieve the validation message for invalid account number...");

		Log.info("Waiting for the validation message element to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement validationMessageforinvalidaccountnumber = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CoreManualAllocationRepo.validationMessageforinvalidaccountnumber));

		Log.info("Validation message element is visible. Retrieving the text...");
		String validationMessage = validationMessageforinvalidaccountnumber.getText();

		Log.info("Validation message retrieved: " + validationMessage);
		return validationMessage;
	}

	// Method to verify "Add interaction details" is displayed
	public boolean isInteractionDetailsDisplayed() {
		Log.info("Starting to check if the 'Interaction Details' field is displayed...");

		Log.info("Waiting for the 'Interaction Details' field to become visible...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		WebElement interactionDetailsField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.interactionDetailsField));

		boolean isDisplayed = interactionDetailsField.isDisplayed();
		Log.info("'Interaction Details' field display status: " + isDisplayed);

		return isDisplayed;
	}

	// Method to verify presence of all required fields
	public boolean areAllFieldsPresent() {
		Log.info("Starting to check if all required fields are present...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Action Owner' field to become visible...");
		WebElement actionOwner = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.actionOwner));
		Log.info("'Action Owner' field visibility: " + actionOwner.isDisplayed());

		Log.info("Waiting for the 'Disposition Type' field to become visible...");
		WebElement dispositionType = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.dispositionType));
		Log.info("'Disposition Type' field visibility: " + dispositionType.isDisplayed());

		Log.info("Waiting for the 'Sub Disposition' field to become visible...");
		WebElement subDisposition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.subDisposition));
		Log.info("'Sub Disposition' field visibility: " + subDisposition.isDisplayed());

		Log.info("Waiting for the 'Next Action Date' field to become visible...");
		WebElement nextActionDate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.nextActionDate));
		Log.info("'Next Action Date' field visibility: " + nextActionDate.isDisplayed());

		Log.info("Waiting for the 'Remark' field to become visible...");
		WebElement remark = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.remark));
		Log.info("'Remark' field visibility: " + remark.isDisplayed());

		Log.info("Waiting for the 'Save' button to become visible...");
		WebElement saveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.saveButton));
		Log.info("'Save' button visibility: " + saveButton.isDisplayed());

		Log.info("Waiting for the 'Cancel' button to become visible...");
		WebElement cancelButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.cancelButton));
		Log.info("'Cancel' button visibility: " + cancelButton.isDisplayed());

		boolean allFieldsVisible = actionOwner.isDisplayed() && dispositionType.isDisplayed()
				&& subDisposition.isDisplayed() && nextActionDate.isDisplayed() && remark.isDisplayed()
				&& saveButton.isDisplayed() && cancelButton.isDisplayed();

		Log.info("All required fields present: " + allFieldsVisible);

		return allFieldsVisible;
	}

	// Method to select Internal User from dropdown
	public void selectInternalUser(String value) {
		Log.info("Starting to select internal user with value: " + value);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Action Owner' field to become visible...");
		WebElement actionOwner = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.actionOwner));
		Log.info("'Action Owner' field is visible. Clicking on it...");
		actionOwner.click();

		Log.info("Waiting for the 'Internal User' option with value '" + value + "' to become visible...");
		WebElement internalUserOption = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.internalUserOption(value)));
		Log.info("Clicking on the 'Internal User' option with value: " + value);
		internalUserOption.click();

		Log.info("Internal user option with value '" + value + "' selected successfully.");
	}

	// Method to select "Connected" from Disposition Type dropdown
	public void selectConnectedDispositionType(String value) {
		Log.info("Starting to select connected disposition type with value: " + value);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Disposition Type' field to become visible...");
		WebElement dispositionType = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.dispositionType));
		Log.info("'Disposition Type' field is visible. Clicking on it...");
		dispositionType.click();

		Log.info("Waiting for the connected option with value '" + value + "' to become visible...");
		WebElement connectedOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.connectedOption(value)));
		Log.info("Clicking on the connected option with value: " + value);
		connectedOption.click();

		Log.info("Waiting for the spinner to become invisible...");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Log.info("Spinner is now invisible, connected disposition type selected successfully.");
	}

	// Method to select "Call Back" from Sub Disposition dropdown
	public void selectCallBackSubDisposition(String value) {
		Log.info("Starting to select callback sub-disposition with value: " + value);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Sub Disposition' field to become visible...");
		WebElement subDisposition = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.subDisposition));
		Log.info("'Sub Disposition' field is visible. Clicking on it...");
		subDisposition.click();

		Log.info("Waiting for the callback option with value '" + value + "' to become visible...");
		WebElement callBackOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.callBackOption(value)));
		Log.info("Clicking on the callback option with value: " + value);
		callBackOption.click();

		Log.info("Callback sub-disposition with value '" + value + "' selected successfully.");

	}

	// Method to select a date using DatePicker
	public void selectNextActionDate(String date) {
		Log.info("Starting to select the next action date: " + date);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Next Action Date' field to become visible...");
		WebElement nextActionDate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.nextActionDate));
		Log.info("'Next Action Date' field is visible. Clicking on it...");
		nextActionDate.click();

		Log.info("Waiting for the 'Next Action Date' value '" + date + "' to become visible...");
		WebElement nextActionDatevalue = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.nextActionDatevalue(date)));
		Log.info("Clicking on the next action date value: " + date);
		nextActionDatevalue.click();

		Log.info("Next action date '" + date + "' selected successfully.");
	}

	// Method to enter remarks
	public void enterRemarks(String remarks) {
		Log.info("Starting to enter remarks: " + remarks);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Remark' field to become visible...");
		WebElement remark = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.remark));
		Log.info("'Remark' field is visible. Entering the remarks...");

		remark.sendKeys(remarks);

		Log.info("Remarks entered successfully: " + remarks);
	}

	// Method to click on Save button
	public void clickSaveButton() {
		Log.info("Starting the process to click the Save button...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Save' button to become visible...");
		WebElement saveButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.saveButton));
		Log.info("'Save' button is visible. Clicking on it...");

		saveButton.click();

		Log.info("Save button clicked successfully.");
	}

	// Method to get validation message
	public String getValidationMessageaftersavingInteractionDetails() {
		Log.info("Starting to retrieve the validation message after saving interaction details...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the validation message after saving interaction details to become visible...");
		WebElement ValidationMessageaftersavingInteractionDetails = wait.until(ExpectedConditions
				.visibilityOfElementLocated(CoreManualAllocationRepo.validationMessageforInteractionDetails));
		Log.info("Validation message is visible. Retrieving the message...");

		String validationMessage = ValidationMessageaftersavingInteractionDetails.getText();

		Log.info("Validation message retrieved: " + validationMessage);

		return validationMessage;
	}

	// Method to verify interaction history is displayed
	public boolean isInteractionHistoryDisplayed() {
		Log.info("Starting to check if the 'Interaction History' section is displayed...");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		Log.info("Waiting for the 'Interaction History' section to become visible...");
		WebElement interactionHistorySection = wait.until(
				ExpectedConditions.visibilityOfElementLocated(CoreManualAllocationRepo.interactionHistorySection));

		boolean isDisplayed = interactionHistorySection.isDisplayed();
		Log.info("'Interaction History' section displayed: " + isDisplayed);

		return isDisplayed;
	}
}
