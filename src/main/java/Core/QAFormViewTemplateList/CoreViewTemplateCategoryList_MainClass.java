package Core.QAFormViewTemplateList;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;

import com.Page_Repository.CoreQAFormViewTemplateListPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class CoreViewTemplateCategoryList_MainClass extends Base_Class {
	
	private WebDriver driver;
	CoreViewTemplateCategoryList_MainClass page;
	 
	public CoreViewTemplateCategoryList_MainClass(WebDriver driver) {
		//Log.info("Initializing Q&A Form View Page...");
		this.driver = driver;
		//Log.info("WebDriver instance assigned.");
		//Log.info("Initializing Web elements using PageFactory...");
		PageFactory.initElements(driver, this);
		//Log.info("Web elements initialized using PageFactory.");
		//Log.info("Q&A Form View Page initialization completed.");
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
	
	
	public void isDisplayed(By locator, String fieldName) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	        WebElement element = driver.findElement(locator);

	        if (element.isDisplayed()) {
	            Log.info(fieldName + " is displayed.");
	            ExtentTestManager.getTest().log(Status.PASS, fieldName + " is displayed.");
	        } else {
	            Log.error(fieldName + " is NOT displayed.");
	            ExtentTestManager.getTest().log(Status.FAIL, fieldName + " is NOT displayed.");
	        }

	    } catch (Exception e) {
	        Log.error("Error verifying element: " + fieldName + " - " + e.getMessage());
	        ExtentTestManager.getTest().log(Status.FAIL, "Error verifying " + fieldName);
	        throw new RuntimeException(e);
	    }
	}
	
	// Method to navigate to CoreQAFormViewTemplate Main menu
	public void AccesQAFormViewTemplateList() throws InterruptedException {
		
		Log.info("Starting navigation to the Q&A Form View Main Menu..");
		moveToElementAndClick(driver, CoreQAFormViewTemplateListPageRepo.QAFormMainMenu);
		Common.fluentWait("QAFormMainMenu", CoreQAFormViewTemplateListPageRepo.QAFormMainMenu);
		WebElement QAFormMainMenu = driver.findElement(CoreQAFormViewTemplateListPageRepo.QAFormMainMenu);
		QAFormMainMenu.click();
		Log.info("Clicked on Q&A Form View Main Menu. ");
	}

}
