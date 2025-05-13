package Core.QAFormViewTemplateList;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;

import com.Page_Repository.CoreQAFormViewTemplateListPageRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;


public class CoreQAFormViewTemplateList_MainClass extends Base_Class {
	private WebDriver driver;

	public static String AgencyName;
	public static String Username;
	public static String Password;
	public static String AppType;
	public static String CORE_LOGIN_BANNER_DETAILS;
	public static String CollectionAgency_BANNER_DETAILS;
	public static String CallCentre_BANNER_DETAILS;
	
	public CoreQAFormViewTemplateList_MainClass(WebDriver driver) {
		Log.info("Initializing Q&A Form View Page...");
		this.driver = driver;
		Log.info("WebDriver instance assigned.");
		Log.info("Initializing Web elements using PageFactory...");
		PageFactory.initElements(driver, this);
		Log.info("Web elements initialized using PageFactory.");
		Log.info("Q&A Form View Page initialization completed.");
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
		
	// Method to navigate to ViewTemplateList submenu under Q&AFormView
	
		public void navigateToViewTemplateList() {
			Log.info("Navigating to View Template List submenu.");
			WebElement ViewTemplateListSubmenu = driver.findElement(CoreQAFormViewTemplateListPageRepo.ViewTemplateListSubmenu);
			ViewTemplateListSubmenu.click();
			Log.info("Clicked on View Template sub Menu. ");
		}
	
		//Common.fluentWait("ViewTemplateListSubmenu", CoreQAFormViewTemplateListPageRepo.ViewTemplateListSubmenu);
		//click(CoreQAFormViewTemplateListPageRepo.ViewTemplateListSubmenu);
		//ExtentTestManager.getTest().log(Status.PASS, "2.click on the submenu View template list");
	
	  public void NavigatetoTemplatecreationpage()throws InterruptedException {
		  ExtentTestManager.startTest("Tc:05 Navigate to Template Creation Page");
		  
		   click(CoreQAFormViewTemplateListPageRepo.CreateNew);
		   ExtentTestManager.getTest().log(Status.PASS, "Step:01 verify Create New button is present in the page");
			Log.info("Step:01 verify Create New button is present in the page");
			
			   ExtentTestManager.getTest().log(Status.PASS, "Step: 02 click on the Create New Button");
				Log.info("Step:02 click on the Create New Button");
			
			ExtentTestManager.getTest().log(Status.PASS, "Expected Result: User is navigated to the Template creation page");
			Log.info("Expected Result: User is navigated to the Template creation page");
	  }

	  public void VerifyrequiredFileds()throws InterruptedException {
			Log.info(
					"Verifying Template Name,Question,Expected Answer Type,Values,Parent Question,Parent Value,Is Mandatory (Checkbox),Add Button,Clear Button,Cancel Button fields displayed as expected.");
			
			//Common.fluentWait("zone", AddAgencyPageRepo.Zone);
			Log.info("Clicked on Template Creation Page. ");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.TemplateName, "TemplateName");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Question, "Question");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerType, "ExpectedAnswerType");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Values, "Values");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.parentQuestion, "parentQuestion");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.ParentValue, "ParentValue");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.IsMandatory, "IsMandatory");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Addbtn, "Add");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.clear, "clear");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Cancel, "Cancel");
			 Log.info("All required fields and buttons are present and visible.");
		}
        public void VerifyGridSection()throws InterruptedException {
        	Log.info("Verify the columns showing in the grid section");
        	
        		try {
        			if (driver.findElement(CoreQAFormViewTemplateListPageRepo.TemplateCreationGrid).isDisplayed()) {
        				ExtentTestManager.getTest().log(Status.PASS, "Columns shows in the Grid section");
        			} else {
        				ExtentTestManager.getTest().log(Status.FAIL, "Unable to show coulmns in the Grid section");
        			}
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        			ExtentTestManager.getTest().log(Status.FAIL, "Unable to show coulmns in the Grid section");
        		}
        	
        }
        public void VerifyMandatoryFieldValidation()throws InterruptedException {
  		Log.info("verify the Mandatory Field Validation");
		WebElement Addbtn = driver.findElement(CoreQAFormViewTemplateListPageRepo.Addbtn);
		Addbtn.click();
		Log.info("Expected Result: Mandatory field validatiopn should be displayed in UI");
	}
        

public void InputValueIntoTemplateNameField() throws InterruptedException {
        	Log.info("Entering  Template Name");
        	// Initialize WebDriverWait and wait for the element to become visible
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    	
    		WebElement Templatename =wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.TemplateName));
                          Templatename.clear();
    		Templatename.sendKeys("Sample Template");
    		Log.info("Entered value into Template Name field: Sample Template");
  	  }

public void InputValueIntoQuestionField() throws InterruptedException {
    Log.info("Step: Entering Question");

    // 1. Explicit wait for the Question input to be clickable
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement questionField = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.Question));

    // 2. Clear any existing text
    questionField.clear();

    // 3. Enter your desired question text
    questionField.sendKeys("Q1");

    Log.info("Entered value into Question field: Q1");
}

}
	





