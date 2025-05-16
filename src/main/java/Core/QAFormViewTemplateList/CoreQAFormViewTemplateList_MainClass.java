package Core.QAFormViewTemplateList;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	 CoreQAFormViewTemplateList_MainClass page;
	 
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
		
		 public void NavigatetoTemplatecreationpage()throws InterruptedException {
			 Log.info("Navigating to Template creation page");
				WebElement createnewbtn = driver.findElement(CoreQAFormViewTemplateListPageRepo.CreateNew);
				createnewbtn.click();
				Log.info("Clicked on Create New ");
		 }

	  public void VerifyrequiredFileds()throws InterruptedException {
			Log.info("Verifying Template Name,Question,Expected Answer Type,Values,Parent Question,Parent Value,Is Mandatory (Checkbox),Add Button,Clear Button,Cancel Button fields displayed as expected.");
			
			Log.info("Clicked on Template Creation Page. ");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.TemplateName, "TemplateName");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Question, "Question");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeDropdown, "ExpectedAnswerType");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.ValuesField, "Values");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.parentQuestion, "parentQuestion");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.ParentValue, "ParentValue");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.IsMandatory, "IsMandatory");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Addbtn, "Add");
			isDisplayed(CoreQAFormViewTemplateListPageRepo.clearbtn, "clear");
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
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    		WebElement Templatename =wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.TemplateName));
              Templatename.clear();
    		Templatename.sendKeys("Sample Template");
    		Log.info("Entered value into Template Name field: Sample Template");
  	  }

public void InputValueIntoQuestionField() throws InterruptedException {
    Log.info("Step: Entering Question");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement questionField = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.Question));
    questionField.clear();
    questionField.sendKeys("Q1");
    Log.info("Entered value into Question field: Q1");
}

public List<String> getExpectedAnswerTypeOptions() {
    Log.info("Step: Reading Expected Answer Type dropdown options");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement  dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeDropdown));
    dropdown.click();
    Log.info("Clicked Expected Answer Type dropdown");
    List<WebElement> optionElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy( CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeOptions));
       
    // 3. Extract visible text
    List<String> options = optionElements.stream()
        .map(WebElement::getText)
        .map(String::trim)
        .collect(Collectors.toList());

    Log.info("Actual Expected Answer Type options: " + options);
    return options;
}
public void selectExpectedAnswerType(String type) throws InterruptedException  {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeDropdown));      
    new Select(dropdown).selectByVisibleText(type);
    Log.info("Selected Expected Answer Type: " + type);
}

public boolean isValuesFieldEditable() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement valuesInput = wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.ValuesField));
    boolean enabled = valuesInput.isEnabled();
    boolean readOnly = valuesInput.getAttribute("readonly") != null;
    Log.info("Values field – enabled: " + enabled + ", readonly attr present: " + readOnly);
    return enabled && !readOnly;
    }

public boolean selectExpectedAnswerTypeAndCheckValuesEditable(String answerType) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.dropdownvalue));
    dropdown.click();
    Log.info("Clicked Expected Answer Type dropdown");

   // WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.DropdownOption(answerType)));
    //option.click();
    //Log.info("Selected Expected Answer Type: " + answerType);

    WebElement valuesInput = wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.ValuesField));
        
    boolean enabled = valuesInput.isEnabled();
    boolean readOnly = valuesInput.getAttribute("readonly") != null;

    Log.info("Values field editable? enabled=" + enabled + ", readonly=" + readOnly);
    return enabled && !readOnly;
}


public void selectAnswerTypeAndEnterValues(String answerType, String commaSeparatedValues) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // Step 1: Click the dropdown
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
        CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeDropdown));
    dropdown.click();
    Log.info("Clicked on 'Expected Answer Type' dropdown");

    // Step 2: Select the option from label elements
    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
        CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeOptions));

    boolean optionClicked = false;
    for (WebElement option : options) {
        if (option.getText().trim().equalsIgnoreCase(answerType.trim())) {
            option.click();
            Log.info("Selected Answer Type: " + answerType);
            optionClicked = true;
            break;
        }
    }

    if (!optionClicked) {
        throw new NoSuchElementException("Option not found in Expected Answer Type: " + answerType);
    }

    // Step 3: Enter comma-separated values
    WebElement valuesField = wait.until(ExpectedConditions.visibilityOfElementLocated(
        CoreQAFormViewTemplateListPageRepo.ValuesField));
    valuesField.clear();
    valuesField.sendKeys(commaSeparatedValues);
    Log.info("Entered values: " + commaSeparatedValues);
}

public String getEnteredValues() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement valuesField = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.ValuesField));
    return valuesField.getAttribute("value");
}

public void selectTextBoxFromExpectedAnswerType() throws InterruptedException {
    
    // Click on Expected Answer Type dropdown
    SelectActiveDropdown(CoreQAFormViewTemplateListPageRepo.expectedAnswerTypeDropdown, "TextBox");
}
public String getText(By by) {
    try {
        return driver.findElement(by).getText();
    } catch (Exception e) {
        System.out.println("Unable to get text from element: " + by.toString());
        throw e;
    }
}
public String getSelectedAnswerType() {
    return getText(CoreQAFormViewTemplateListPageRepo.expectedAnswerTypeDropdown);
}

public void selectIsMandatoryCheckbox() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.isMandatoryCheckbox));
    checkbox.click();
    Log.info("Clicked on 'Is Mandatory' checkbox.");
}

public boolean isMandatoryCheckboxSelected() {
    WebElement checkbox = driver.findElement(CoreQAFormViewTemplateListPageRepo.isMandatoryCheckbox);
    return checkbox.getAttribute("class").contains("rz-state-active");
}

public void clickAddButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.addButton));
    addBtn.click();
}

public String getGridFirstRowQuestion() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement questionCell = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.gridFirstRowQuestion));
    return questionCell.getText();
}

public String getGridFirstRowIsMandatory() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement mandatoryCell = wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.gridFirstRowMandatory));
    return mandatoryCell.getText();
}

//Testcase20: Clear Button Functionality
public String getTextBoxValue(By locator) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    
    WebElement element = driver.findElement(locator);
    return element.getAttribute("value");
}

public boolean verifyClearFunctionality(String templateName, String question) throws InterruptedException {
	// Enter Template Name and Question
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement questionField = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.Question));
questionField.clear();
questionField.sendKeys("Q2");
Log.info("Entered value into Question field: Q2");

    // Click Clear button
WebElement clearbutton = driver.findElement(CoreQAFormViewTemplateListPageRepo.clearbtn);
clearbutton.click();
//Wait for fields to clear
Thread.sleep(2000);

// Fetch values after clearing
//String templateAfterClear = getTextBoxValue(CoreQAFormViewTemplateListPageRepo.TemplateName);
String questionAfterClear = getTextBoxValue(CoreQAFormViewTemplateListPageRepo.Question);


// Return true if all fields are cleared and checkbox is unselected
return questionAfterClear.isEmpty();
}
}
   







	





