package Core.CallCentre;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

import com.BasePackage.Base_Class;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import com.Utility.ScreenShot;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;
import org.openqa.selenium.StaleElementReferenceException;
import io.netty.handler.timeout.TimeoutException;

public class Core_Allocation_Summary_Page extends Base_Class {
	WebDriver driver;

	public static File downloadedAllocationFile = null; // ✅ Moved outside the method

	// ✅ Moved this method outside as well
	private File waitForFileDownload(String downloadDir, String fileNameStartsWith, String fileExtension,
			int timeoutSeconds) throws InterruptedException {
		File dir = new File(downloadDir);
		Set<String> beforeDownload = Arrays
				.stream(dir.listFiles((d, name) -> name.startsWith(fileNameStartsWith) && name.endsWith(fileExtension)))
				.map(File::getName).collect(Collectors.toSet());

		long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

		while (System.currentTimeMillis() < endTime) {
			File[] files = dir
					.listFiles((d, name) -> name.startsWith(fileNameStartsWith) && name.endsWith(fileExtension));

			if (files != null) {
				for (File file : files) {
					if (!beforeDownload.contains(file.getName()) && file.exists() && file.length() > 0) {
						return file; // Found new file
					}
				}
			}
			Thread.sleep(1000);
		}

		return null;
	}

	public Core_Allocation_Summary_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method to verify Allocation Summary submenu is displayed
	public boolean VerifyAllocationSummaryDisplayingasSubmenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement allocationSummaryMenu = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(text(),'Call Centre')]]")));
		Log.info("Call Centre Menu display sucessfully");
		return allocationSummaryMenu.isDisplayed();

	}

	// Click on Allocation Summary submenu
	public void ClickonAllocationSummarysubmenu() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-spinner")));
		WebElement allocationSummaryMenu = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[.//span[contains(text(),'Call Centre')]]")));
		allocationSummaryMenu.click();
		Log.info("Click on the Call Centre sucessfully");
		WebElement allocationSummarySubMenu = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Allocation  Summary']")));
		allocationSummarySubMenu.click();
		Log.info("Click on the Allocation Summary sucessfully");
	}

	// Verify fields and buttons are displayed on Allocation Summary page
	public boolean VerifyFieldsandButtononAllocationSummary() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		WebElement callCentreDropdown = driver.findElement(CoreAllocationSummaryRepo.Select_CallCentre);
		boolean isDropdownDisplayed = callCentreDropdown.isDisplayed();
		Log.info("Select Call Centre Field Display");

		WebElement searchButton = driver.findElement(CoreAllocationSummaryRepo.Search);
		boolean isSearchButtonDisplayed = searchButton.isDisplayed();
		Log.info("Search button Display");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

		// Add other elements validation here as needed

		return isDropdownDisplayed && isSearchButtonDisplayed;
	}

	// Search without selecting call centre (simulate clicking search with empty
	// dropdown)
	public void SearchWithoutSelectingSelectCallCentredropdown() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
		Thread.sleep(40000);
		WebElement SelectCallCentre = driver.findElement(CoreAllocationSummaryRepo.Select_CallCentre);
		Thread.sleep(10000);
		SelectCallCentre.click();
		Log.info("Click on the Select Call Centre Field");

		WebElement searchButton = driver.findElement(CoreAllocationSummaryRepo.Search);
		searchButton.click();
		Log.info("Click on the Search button");

		// Add any waits or validations after search if needed
		WebElement validationMessage = null;

		By xpath1 = By.xpath("(//p[normalize-space()='Call Center Is Required'])[1]");
		By xpath2 = By.xpath("//*[contains(text(), 'Call Center Is Required')]");

		// Use findElements() which returns an empty list if no elements found — no
		// exception!
		List<WebElement> elements2 = driver.findElements(xpath2);
		List<WebElement> elements1 = driver.findElements(xpath1);

		if (!elements2.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath2));
			validationMessage = elements2.get(0);
			Log.info("Validation message found using fallback XPath (xpath2).");
		} else if (!elements1.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpath1));
			validationMessage = elements1.get(0);
			Log.info("Validation message found using primary XPath (xpath1).");
		} else {
			Assert.fail("Validation message 'Call Center Is Required' not found with any known XPath.");
		}

		Assert.assertTrue(validationMessage.isDisplayed(),
				"Validation message 'Call Center Is Required' is not displayed.");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));

	}

	public void SearchwithSelectedCallCentre() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
		try {

			wait.until(ExpectedConditions.invisibilityOfElementLocated(DispositionMasterPageRepo.spinner));
			wait.until(ExpectedConditions.presenceOfElementLocated(CoreAllocationSummaryRepo.Select_CallCentre));
			WebElement SelectCallCentreDropdown = wait
					.until(ExpectedConditions.elementToBeClickable(CoreAllocationSummaryRepo.Select_CallCentre));
			SelectCallCentreDropdown.click();

			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// SelectCallCentreDropdown);
			Log.info("Clicked on Select Call Centre dropdown");
			By dropdownOption = By
					.xpath("//li[contains(@class,'rz-dropdown-item')]//span[normalize-space()='CallCentre 1']");
			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOption));
			option.click();
			Log.info("Selected 'CallCentre 1' option successfully.");
		} catch (TimeoutException te) {
			Log.info("Timeout occurred while trying to interact with the Select Call Centre dropdown: "
					+ te.getMessage());
			te.printStackTrace();
		} catch (Exception e) {
			Log.info("An unexpected exception occurred: " + e.getMessage());
			e.printStackTrace();
		}

		WebElement searchButton = driver.findElement(CoreAllocationSummaryRepo.Search);
		searchButton.click();
		Log.info("Click on the Search button");

		WebElement allocationSummary = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='card-wrapper'])[1]")));
		Assert.assertTrue(allocationSummary.isDisplayed(), "Allocation summary is not displayed after search.");
	}

	// Download Monthly Allocation Summary file
	public void DownloadMonthlyAllocationSummary() throws InterruptedException, AWTException, IOException {

		String downloadFilepath = System.getProperty("user.home") + "/Downloads";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		WebElement downloadIcon = null;

		try {
			By primaryXPath = By
					.xpath("(//i[contains(text(),'download_for_offline') and contains(@style,'green')])[2]");
			downloadIcon = wait.until(ExpectedConditions.elementToBeClickable(primaryXPath));
			downloadIcon.click();
			Log.info("Clicked using primary XPath.");
		} catch (Exception e1) {
			Log.warn("Primary XPath not found or not clickable. Trying fallback XPath...");

			try {
				By fallbackXPath = By
						.xpath("(//i[contains(text(),'download_for_offline') and contains(@style,'green')])[2]");
				downloadIcon = wait.until(ExpectedConditions.elementToBeClickable(fallbackXPath));
				downloadIcon.click();
				Log.info("Clicked using fallback XPath.");
			} catch (Exception e2) {
				Assert.fail("Download icon not found using any known XPath.");
			}
		}

		ExtentTestManager.getTest().log(Status.PASS,
				"Step:01 - Click on the download icon  displayed against  the monthly allocation summary title ");
		Log.info("Step:01 - Click on the download icon  displayed against  the monthly allocation summary title ");

		File downloadedFile = waitForFileDownload(downloadFilepath, "Allocation_List_", ".xlsx", 90);

		Assert.assertNotNull(downloadedFile, "Downloaded file not found.");
		Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist in the expected location.");
		Log.info("File downloaded successfully: " + downloadedFile.getAbsolutePath());

		downloadedAllocationFile = downloadedFile;

		ExtentTestManager.getTest().log(Status.PASS, "Expected Result: Allocation file downloaded successfully.");
		Log.info("Expected Result: Allocation file downloaded successfully.");

	}

	public int getExcelRowCount(String filePath) {
		try {
			// ✅ Set the max byte size to handle large files (up to 300MB)
			IOUtils.setByteArrayMaxOverride(300 * 1024 * 1024);

			// ✅ Relax ZIP bomb detection for large files
			ZipSecureFile.setMinInflateRatio(0.00001);

			File file = new File(filePath);

			// ✅ Wait in case the file is still being written
			Thread.sleep(3000); // Adjust the wait time as needed

			if (!file.exists() || !file.canRead()) {
				System.out.println("File not found or not readable: " + filePath);
				return -1;
			}

			try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {

				Sheet sheet = workbook.getSheetAt(0);
				if (sheet == null) {
					System.out.println("No sheet found in Excel file.");
					return -1;
				}

				int rowCount = 0;
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row != null && !isRowEmpty(row)) {
						rowCount++;
					}
				}

				return rowCount;

			}
		} catch (IOException e) {
			System.out.println("Error reading Excel file (IO): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("General error reading Excel file: " + e.getMessage());
			e.printStackTrace();
		}

		return -1;
	}

	private boolean isRowEmpty(Row row) {
		for (Cell cell : row) {
			if (cell != null && cell.getCellType() != CellType.BLANK && !cell.toString().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	// Data verification in downloaded file
	public boolean DataVerificationinDownloadedFile() {
		// Implement file read & verification logic here

		String downloadFilepath = System.getProperty("user.home") + "/downloads";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		By primaryXPath = By.xpath("(//span[contains(@title,'98035')][normalize-space()='98035'])[1]");
		By fallbackXPath = By.xpath("(//span[contains(@title,'2')][normalize-space()='2'])[1]");

		WebElement accountCountElement;

		List<WebElement> primaryElements = driver.findElements(primaryXPath);
		List<WebElement> fallbackElements = driver.findElements(fallbackXPath);

		if (!primaryElements.isEmpty()) {
			accountCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(primaryXPath));
			Log.info("Found account count using primary XPath.");
		} else if (!fallbackElements.isEmpty()) {
			accountCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fallbackXPath));
			Log.info("Found account count using fallback XPath.");
		} else {
			Log.error("Neither primary nor fallback XPath matched any element.");
			Assert.fail("Element not found using any known XPath.");
			return false;
		}

		int uiAccountCount = Integer.parseInt(accountCountElement.getText().trim());
		Log.info("UI Account Count: " + uiAccountCount);

		File downloadedFile = downloadedAllocationFile;
		Assert.assertNotNull(downloadedFile, "Previously downloaded file not found.");
		Log.info("File exists: " + downloadedFile.exists());
		Log.info("Readable: " + downloadedFile.canRead());

		ExtentTestManager.getTest().log(Status.PASS, "Step:01 - Open the downloaded file.");
		Log.info("Step:01 - Open the downloaded file.");

		// Step 3: Read Excel row count
		int excelAccountCount = getExcelRowCount(downloadedFile.getAbsolutePath());
		Log.info("Excel Account Count: " + excelAccountCount);

		ExtentTestManager.getTest().log(Status.PASS, "Step:02 - Verify the data with grid data.");
		Log.info("Step:02 - Verify the data with grid data.");

		// Step 4: Compare counts
		Assert.assertEquals(excelAccountCount, uiAccountCount, "Mismatch between UI and Excel account counts");

		ExtentTestManager.getTest().log(Status.PASS,
				"Expected Result: No of accounts  in the downloaded file should matches no of accounts in the the grid data.");
		Log.info(
				"Expected Result: No of accounts  in the downloaded file should matches no of accounts in the the grid data.");

		return true;
	}

	// Download Daily Allocation from Monthly Summary
	public void DownloadDailyAllocationfromMonthlySummary() throws InterruptedException, AWTException {
		// Implement logic to click and download daily allocation
		String downloadFilepath = System.getProperty("user.home") + "/Downloads";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		By primaryXPath = By.xpath("(//span[@class = 'rz-cell-data'])[18]");
		By fallbackXPath = By.xpath("(//span[@class='rz-cell-data rz-text-truncate'])[9]");

		WebElement downloadIcon = null;

		List<WebElement> primaryElements = driver.findElements(primaryXPath);
		List<WebElement> fallbackElements = driver.findElements(fallbackXPath);

		if (!primaryElements.isEmpty()) {
			downloadIcon = wait.until(ExpectedConditions.elementToBeClickable(primaryXPath));
			Log.info("Found element using primary XPath.");
		} else if (!fallbackElements.isEmpty()) {
			downloadIcon = wait.until(ExpectedConditions.elementToBeClickable(fallbackXPath));
			Log.info("Found element using fallback XPath.");
		} else {
			Log.error("Neither primary nor fallback XPath matched any element.");
			Assert.fail("Download icon not found using any known XPath.");
			return;
		}
		if (downloadIcon != null) {
			downloadIcon.click();

			ExtentTestManager.getTest().log(Status.PASS,
					"Step:01 - Clicked on download icon against an allocation type (manual allocation) under monthly allocation summary.");
			Log.info(
					"Step:01 - Clicked on download icon against an allocation type (manual allocation) under monthly allocation summary.");
		}

		File downloadedFile = waitForFileDownload(downloadFilepath, "Allocation_List_", ".xlsx", 90);
		Assert.assertNotNull(downloadedFile, "Downloaded file not found.");
		Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist in the expected location.");

		Log.info("File downloaded successfully: " + downloadedFile.getAbsolutePath());
		downloadedAllocationFile = downloadedFile;

		ExtentTestManager.getTest().log(Status.PASS,
				"Expected Result: File with allocation summary for the selected allocation type should be downloaded.");
		Log.info(
				"Expected Result: File with allocation summary for the selected allocation type should be downloaded.");

	}

	// Data verification in downloaded daily allocation file
	public boolean DataVerificationinnDownloadedFile() {
		// Implement verification logic

		String downloadFilepath = System.getProperty("user.home") + "/downloads";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		By primaryXPath = By.xpath("(//span[@title='98035'][normalize-space()='98035'])[2]");
		By fallbackXPath = By.xpath("(//span[contains(@title,'2')][normalize-space()='2'])[2]");

		WebElement accountCountElement = null;

		List<WebElement> primaryElements = driver.findElements(primaryXPath);
		List<WebElement> fallbackElements = driver.findElements(fallbackXPath);

		if (!primaryElements.isEmpty()) {
			accountCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(primaryXPath));
			Log.info("Found account count using primary XPath.");
		} else if (!fallbackElements.isEmpty()) {
			accountCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fallbackXPath));
			Log.info("Primary XPath not found. Found account count using fallback XPath.");
		} else {
			Log.error("Neither primary nor fallback XPath found any matching element.");
			Assert.fail("Account count element not found using any XPath.");
			return false;
		}

		int uiAccountCount = Integer.parseInt(accountCountElement.getText().trim());
		Log.info("UI Account Count: " + uiAccountCount);

		File downloadedFile = downloadedAllocationFile;
		Assert.assertNotNull(downloadedFile, "Previously downloaded file not found.");
		Log.info("File exists: " + downloadedFile.exists());
		Log.info("Readable: " + downloadedFile.canRead());

		ExtentTestManager.getTest().log(Status.PASS, "Step:01 - Open the downloaded file.");
		Log.info("Step:01 - Open the downloaded file.");
		ExtentTestManager.getTest().log(Status.PASS,
				"Expected Result: Cust Id, A/c Number, A/c Name should display in the downloaded file");
		Log.info("Expected Result: Cust Id, A/c Number, A/c Name should display in the downloaded file");

		// Step 3: Read Excel row count
		int excelAccountCount = getExcelRowCount(downloadedFile.getAbsolutePath());
		Log.info("Excel Account Count: " + excelAccountCount);
		ExtentTestManager.getTest().log(Status.PASS, "Step:02 - Verify the data with grid data.");
		Log.info("Step:02 - Verify the data with grid data.");

		// Step 4: Compare counts
		Assert.assertEquals(excelAccountCount, uiAccountCount, "Mismatch between UI and Excel account counts");
		ExtentTestManager.getTest().log(Status.PASS,
				"Expected Result: No of accounts in the downloaded file should match the grid data.");
		Log.info("Expected Result: No of accounts in the downloaded file should match the grid data.");

		return true;
	}
}
