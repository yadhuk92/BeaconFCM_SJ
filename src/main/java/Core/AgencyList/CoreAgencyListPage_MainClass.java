package Core.AgencyList;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.CoreAgencyListRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class CoreAgencyListPage_MainClass extends Base_Class {
	static String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"; // Set download
	public static String PanNumber;
	public static String AgencyUserName;
	
	// directory
// Constructor
	WebDriver driver;

	public CoreAgencyListPage_MainClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("CoreAgencyListPage_MainClass");
	}

	public void isDisplayed(By locator, String elementName) {
		try {
			Thread.sleep(2000);
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void isNOTDisplayed(By locator, String elementName) {
		try {
			List<WebElement> elements = driver.findElements(locator);

			if (elements.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS, elementName + " is not displayed, as expected.");
			} else if (!elements.get(0).isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + " is present but hidden (not displayed), as expected.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is visible but should not be.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void isDisplayedActive(By locator, String elementName) {
		try {
			WebElement element = driver.findElement(locator);
			if (element.isDisplayed()) {
				ExtentTestManager.getTest().log(Status.PASS, " Element is displayed.");
				ExtentTestManager.getTest().log(Status.PASS, " Dropdown is Active After Reset is displayed.");
			} else {
				ExtentTestManager.getTest().log(Status.FAIL, elementName + " is not displayed.");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void click(By locator, String elementName) {
		try {
			Common.fluentWait(elementName, locator);
			WebElement element = driver.findElement(locator);

			// JavaScript Executor to move to element
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
			click1(locator);
			ExtentTestManager.getTest().log(Status.PASS, "click done on locator " + elementName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void IsElementEnabled(By locator, String elementName) {
		try {
//		Common.fluentWait(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.PASS,
						elementName + "is Active after the Reset(Parameter Activation)");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive (Parameter Activation) ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}
	}

	public void IsElementDisabled(By locator, String elementName) {
		try {
//		Common.fluentWait(elementName, locator);
			WebElement element = driver.findElement(locator);

			if (element.isEnabled()) {
				System.out.println("Element is active.");
				ExtentTestManager.getTest().log(Status.FAIL,
						elementName + "is Active before Reset (Parameter activation) ");
			} else {
				System.out.println("Element is inactive.");
				ExtentTestManager.getTest().log(Status.PASS, elementName + "is Inactive before Reset");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Unable to select " + elementName);
			System.out.println("Error message " + e.getMessage());
		}

	}

	public void WaitLoader() {
		By loader = By.xpath("//*[@class='spinner']");
		// wait for Processing icon
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.pollingEvery(Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			Log.info("Loader disappeared.");
			Thread.sleep(4000);
		} catch (Exception e) {
			Log.info("Loader did not appear, proceeding without delay.");
		}
	}

	public void VerifyAgencyLevel() {
		String AgencyUser = driver.findElement(CoreAgencyListRepo.Level).getText();
		if (AgencyUser.contains("HO")) {
			ExtentTestManager.getTest().log(Status.PASS, "HO level Agency Login done");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "HO level Agency Login done");
		}
	}

	public void VerifyAgencyListFilterByAgencyName() {
		AgencyUserName = driver.findElement(CoreAgencyListRepo.AgencyNameFromList).getText();
		driver.findElement(CoreAgencyListRepo.AgencyNameFromList).sendKeys(AgencyUserName);
		click(CoreAgencyListRepo.Search, "Search");
		WaitLoader();
		Common.fluentWait("Action", CoreAgencyListRepo.Action);
		String AgencyUserResult = driver.findElement(CoreAgencyListRepo.AgencyNameFromListResult).getText();
		if (AgencyUserResult.contains(AgencyUserName)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}

	}
	public void VerifySystemID(String ID) {
		String SystemID = driver.findElement(CoreAgencyListRepo.SystemID).getText();
		
		String AgencyUserResult = driver.findElement(CoreAgencyListRepo.AgencyNameFromListResult).getText();
		if (SystemID.contains(ID)) {
			ExtentTestManager.getTest().log(Status.PASS, "Pagination has been done successfully");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Pagination has not been done");
		}

	}
	
	public void VerifyAgencyListFilterByPanNumber() {
		PanNumber = driver.findElement(CoreAgencyListRepo.PANFromList).getText();
		driver.findElement(CoreAgencyListRepo.PANNumber).sendKeys(PanNumber);
		click(CoreAgencyListRepo.Search, "Search");
		WaitLoader();
		Common.fluentWait("Action", CoreAgencyListRepo.Action);
		String PanNumberResult = driver.findElement(CoreAgencyListRepo.PANFromListResult).getText();
		if (PanNumberResult.contains(PanNumber)) {
			ExtentTestManager.getTest().log(Status.PASS, "Agency Name Filter is working fine");
		} else {
			ExtentTestManager.getTest().log(Status.FAIL, "Agency Name Filter is not working");
		}

	}
	public void IsPrefilled(By locator, String elementName) {
		try {
			String Value = driver.findElement(locator).getText();
			if (Value.isEmpty()) {
				ExtentTestManager.getTest().log(Status.PASS,elementName+ " Element is displayed has prefilled value");
				
			} else {
				ExtentTestManager.getTest().log(Status.FAIL,elementName+ " Element is displayed has no prefilled value");
			}
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.FAIL,
					elementName + " check failed due to exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
