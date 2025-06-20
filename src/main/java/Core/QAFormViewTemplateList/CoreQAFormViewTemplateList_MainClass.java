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


public class CoreQAFormViewTemplateList_MainClass extends Base_Class {
	private WebDriver driver;
	 CoreQAFormViewTemplateList_MainClass page;
	 
	public CoreQAFormViewTemplateList_MainClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
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
	public void waitForSpinnerToDisappear() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='spinner']")));
	        System.out.println("Spinner has disappeared.");
	    } catch (Exception e) {
	        System.out.println("Spinner did not disappear: " + e.getMessage());
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
			isDisplayed(CoreQAFormViewTemplateListPageRepo.Valuesfield1, "Values");
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

public void verifyValuesFieldNonEditableForTextBox() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click on Expected Answer Type dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerType));
        dropdown.click();

        // Wait for and select the TextBox option
        boolean optionSelected = false;
        for (int i = 0; i < 3; i++) { 
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.textBoxOption));
                WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.textBoxOption));
                textBox.click();
                optionSelected = true;
                break;
            } catch (Exception e) {
                Thread.sleep(1000); 
                dropdown.click(); 
            }
        }

        if (!optionSelected) {
            throw new AssertionError("Failed to select TextBox option after multiple attempts.");
        }

        Log.info("Selected Expected Answer Type: TextBox");

        // Check Values field is non-editable
        WebElement valuesInput = wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.Valuesfield1));
        boolean enabled = valuesInput.isEnabled();
        boolean readOnly = valuesInput.getAttribute("readonly") != null;

        if (enabled && !readOnly) {
            throw new AssertionError("Values field should be non-editable for TextBox but it is editable.");
        }

        System.out.println("Test Passed: Values field is non-editable for TextBox.");

    } catch (Exception e) {
        throw new AssertionError("Test failed during 'Values field non-editable check for TextBox': " + e.getMessage(), e);
    }
}


public void verifyValuesFieldIsEditableWhenDropDownSelected() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Step 1: Click on the Expected Answer Type dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.ExpectedAnswerType));
        dropdown.click();
        Log.info("Clicked on Expected Answer Type dropdown.");

        // Step 2: Wait for and click the "Drop Down" option
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                WebElement dropDownOption = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.dropdownvalue));
                dropDownOption.click();
                Log.info("Selected 'Drop Down' option from dropdown.");
                break;
            } catch (Exception e) {
                Thread.sleep(1000); 
                dropdown.click(); 
            }
        }

        // Step 3: Wait for the Values field
        WebElement valuesField = wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.Valuesfield1));
        Thread.sleep(1000); 

        // Step 4: Check if editable
        boolean isEnabled = valuesField.isEnabled();
        boolean isReadOnly = valuesField.getAttribute("readonly") != null;

        if (!isEnabled || isReadOnly) {
            throw new AssertionError("Values field should be editable when Expected Answer Type = Drop Down");
        }

        System.out.println("Test Passed: Values field is editable when 'Drop Down' is selected.");

    } catch (Exception e) {
        throw new AssertionError("Test failed during 'Drop Down editable check': " + e.getMessage(), e);
    }
}

//TC 14
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
        CoreQAFormViewTemplateListPageRepo.Valuesfield1));
    valuesField.clear();
    valuesField.sendKeys(commaSeparatedValues);
    Log.info("Entered values: " + commaSeparatedValues);
}

public String getEnteredValues() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement valuesField = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.Valuesfield1));
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
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement questionField = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.Question));
questionField.clear();
questionField.sendKeys("Q2");
Log.info("Entered value into Question field: Q2");

WebElement clearbutton = driver.findElement(CoreQAFormViewTemplateListPageRepo.clearbtn);
clearbutton.click();
Thread.sleep(2000);
String questionAfterClear = getTextBoxValue(CoreQAFormViewTemplateListPageRepo.Question);
return questionAfterClear.isEmpty();
}

//TC 21: output grid column verification
public boolean isGridColumnDisplayed(By columnLocator) throws InterruptedException {
        try {
            return ElementDisplayed(columnLocator);
        } catch (Exception e) {
            Log.error("Column not displayed: " + e.getMessage());
            return false;
        }
    }

//TC22: Output Grid Columns Verification
//Method to check if all column headers are displayed
public boolean areAllGridHeadersDisplayed() {
    try {
        return ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderOrderNumber)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderQuestion)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderExpectedAnswerType)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderValues)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderParentQuestion)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderParentValue)
            && ElementDisplayed(CoreQAFormViewTemplateListPageRepo.gridHeaderIsMandatory);
    } catch (Exception e) {
        Log.error("Error in checking grid headers visibility: " + e.getMessage());
        return false;
    }
}

// Method to validate specific column header is visible
public boolean isGridColumnVisible(By columnHeader) {
    try {
        return ElementDisplayed(columnHeader);
    } catch (Exception e) {
        Log.error("Column not visible: " + e.getMessage());
        return false;
    }

}
//TC 22: Output Grid Columns data verification

public boolean verifyGridColumnsAndQuestionRowValue(Map<By, String> columnsToVerify, By questionCellLocator, String expectedQuestionValue) {
    try {
        // Verify column headers
        for (Map.Entry<By, String> entry : columnsToVerify.entrySet()) {
            WebElement header = waitVisibility(entry.getKey());
            if (!header.isDisplayed()) {
                System.out.println("Column not visible: " + entry.getValue());
                return false;
            }
        }

        // Verify first row "Question" cell value
        WebElement questionCell = waitVisibility(questionCellLocator);
        String actualValue = questionCell.getText().trim();
        System.out.println("Question column first row value: " + actualValue);
        return actualValue.equals(expectedQuestionValue);

    } catch (Exception e) {
        System.out.println("Exception during grid verification: " + e.getMessage());
        return false;
    }
}


public void createQuestion() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement questionField2 = wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.Question));
        questionField2.clear();
        questionField2.sendKeys("Q2");
        click (CoreQAFormViewTemplateListPageRepo.ExpectedAnswerTypeDropdown);
        click (CoreQAFormViewTemplateListPageRepo.textBoxOption);
        click(CoreQAFormViewTemplateListPageRepo.Addbtn);
        waitForSpinnerToDisappear();
       
        Log.info("Question Q2 created with TextBox type and added to grid.");
}


public void CancelButtonNavigation() throws InterruptedException {
    Log.info("Verifying Cancel Button Navigation");
    waitForSpinnerToDisappear();

    // Optional: wait for success message to disappear if it's visible
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.successMessage));
        Log.info("Success message disappeared.");
    } catch (Exception e) {
        Log.warn("Success message not found or already gone.");
    }

    // Wait until Cancel button is visible and clickable
    Common.fluentWait("Cancel", CoreQAFormViewTemplateListPageRepo.Cancel);

    WebElement cancelButton = driver.findElement(CoreQAFormViewTemplateListPageRepo.Cancel);
    try {
        cancelButton.click();
        Log.info("Clicked Cancel button.");
    } catch (Exception e) {
        Log.warn("Standard click failed. Trying JS click.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cancelButton);
    }

    // Wait for spinner after navigation
    waitForSpinnerToDisappear();

    // Confirm QA Template page loaded by waiting for Action button
    Common.fluentWait("Action", CoreQAFormViewTemplateListPageRepo.actionButton);

    Log.info("Successfully navigated to QA Template page.");
}

	//TC 27: Page loads with all available Template

public void verifyTemplateVisibleInGrid() {
    String templateName = "Sample Template";
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

    // Wait for the grid to be visible
    wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.templateGrid));

    // Wait for spinner to disappear
  //  waitForSpinnerToDisappear();

    // Check grid repeatedly until the template is listed (max 30 seconds)
    boolean found = false;
    long endTime = System.currentTimeMillis() + 30000;

    while (System.currentTimeMillis() < endTime) {
        List<WebElement> nameCells = getDriver().findElements(CoreQAFormViewTemplateListPageRepo.templateNameCells);
        for (WebElement cell : nameCells) {
            String name = cell.getText().trim();
            if (name.equalsIgnoreCase(templateName)) {
                System.out.println("Template '" + templateName + "' found in the grid.");
                found = true;
                break;
            }
        }

        if (found) break;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    assert found : "Template '" + templateName + "' not found in the grid.";
    Log.info("Verified 'Sample Template' is visible in the Q&A Template grid.");
}

	

public void searchAndVerifyTemplateInGrid(String templateName) {
    System.out.println("Searching and verifying template in grid for: " + templateName);

    try {
        // Enter the template name into the search box
    	 
        enterText(CoreQAFormViewTemplateListPageRepo.searchButton, templateName);
        System.out.println("Entered template name in search box: " + templateName);
        click(CoreQAFormViewTemplateListPageRepo.searchButton);
        waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.templateNameCells);
        String actualTemplateName = GetElementText(CoreQAFormViewTemplateListPageRepo.templateNameCells);
        System.out.println("Template listed in grid: " + actualTemplateName);

        // Assertion (fail explicitly if not matching)
        if (!actualTemplateName.equalsIgnoreCase(templateName)) {
            throw new AssertionError("Template not found based on search input.");
        }

    } catch (Exception e) {
        System.out.println("Exception in searchAndVerifyTemplateInGrid: " + e.getMessage());
        throw new AssertionError("Template not found based on search input.", e);
    }
}

private void enterText(By searchButton, String templateName) {
	// TODO Auto-generated method stub
	
}

//TC 28 Grid Column Verification
public boolean verifyGridColumnPresence() {
    System.out.println("Verifying grid column headers...");

    try {
        return driver.findElement(CoreQAFormViewTemplateListPageRepo.header_TemplateName).isDisplayed()
            && driver.findElement(CoreQAFormViewTemplateListPageRepo.header_Status).isDisplayed()
            && driver.findElement(CoreQAFormViewTemplateListPageRepo.header_LastModified).isDisplayed()
            && driver.findElement(CoreQAFormViewTemplateListPageRepo.header_Action).isDisplayed();

    } catch (Exception e) {
        System.out.println("Exception while verifying grid columns: " + e.getMessage());
        return false;
    }
}

// TC 30 Active Status green tick  column verification
public boolean isActiveStatusIconDisplayed() {
    try {
        WaitLoader(); // Wait for loader to disappear
        waitVisibility(CoreQAFormViewTemplateListPageRepo.templateGrid);

        List<WebElement> icons = driver.findElements(CoreQAFormViewTemplateListPageRepo.statusGreenTickIcon);
        return !icons.isEmpty(); // At least one green tick icon present
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
//TC 31 Last modified column verification
public void verifyLastModifiedColumnIsDisplayed() {
    waitVisibility(CoreQAFormViewTemplateListPageRepo.templateGrid);

    WebElement lastModifiedElement = waitVisibility(CoreQAFormViewTemplateListPageRepo.lastModifiedColumn);
    String lastModifiedText = lastModifiedElement.getText().trim();

    Assert.assertFalse(lastModifiedText.isEmpty(), "Last Modified date is not displayed.");
    System.out.println("Last Modified date displayed: " + lastModifiedText);
}

//TC 32 Action Button Options

public boolean verifyActionOptionsDisplayed() throws InterruptedException {
    click(CoreQAFormViewTemplateListPageRepo.actionButton);

    boolean isEditDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.action_Edit);
    boolean isViewDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.action_View);
    boolean isViewHistoryDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.action_ViewHistory);

    return isEditDisplayed && isViewDisplayed && isViewHistoryDisplayed;
}
public static void JavascriptClick(By by, WebDriver driver) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", driver.findElement(by));
}
//TC 33 Edit Button Functionality
public void clickEditForFirstTemplate() throws InterruptedException {
	  //try {
	 WaitLoader();
    click(CoreQAFormViewTemplateListPageRepo.actionButton);
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.dropdown-menu.show")));
   //wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(CoreQAFormViewTemplateListPageRepo.action_Edit)));
   //click(CoreQAFormViewTemplateListPageRepo.action_Edit);
    JavascriptClick(CoreQAFormViewTemplateListPageRepo.action_Edit, driver);
    Thread.sleep(Duration.ofSeconds(10));
  
   
   /*try {
 
    click(CoreQAFormViewTemplateListPageRepo.action_Edit);
} 
   catch (Exception clickEx) {
       WebElement editButton = driver.findElement(CoreQAFormViewTemplateListPageRepo.action_Edit);
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
   }
	  } catch (Exception e) {
	        throw new AssertionError("Edit option not found or clickable: " + e.getMessage());
	    }*/
	}
public boolean isTemplateCreationPageLoaded() {
    waitVisibility(CoreQAFormViewTemplateListPageRepo.templateNameField);
    return ElementDisplayed(CoreQAFormViewTemplateListPageRepo.templateNameField) &&
           ElementDisplayed(CoreQAFormViewTemplateListPageRepo.updateButton);
}

//TC 34 Template Edit Page-Data Prefilled Case

public void verifyPrefilledTemplateName(String expectedTemplateName) {
    WebElement templateField = driver.findElement(CoreQAFormViewTemplateListPageRepo.templateNameField);
    String actualValue = templateField.getAttribute("value");
    
    Assert.assertEquals(actualValue, expectedTemplateName, "Template Name is not prefilled correctly.");
}

//Tc 35 Template Edit Page-Template name Update
public void updateTemplateName(String newName, String remark) throws InterruptedException {
    try {
        // Wait for template name field to be clickable and clear it
        Common.fluentWait("Template Name Field", CoreQAFormViewTemplateListPageRepo.templateNameField);
        WebElement nameField = driver.findElement(CoreQAFormViewTemplateListPageRepo.templateNameField);
        nameField.clear();
        nameField.sendKeys(newName);

        // Enter remark
        Common.fluentWait("Remark Field", CoreQAFormViewTemplateListPageRepo.remarkField);
        WebElement remarkField = driver.findElement(CoreQAFormViewTemplateListPageRepo.remarkField);
        remarkField.clear();
        remarkField.sendKeys(remark);

        // Click update button
        Common.fluentWait("Update Button", CoreQAFormViewTemplateListPageRepo.updateButton);
        WebElement updateBtn = driver.findElement(CoreQAFormViewTemplateListPageRepo.updateButton);
        updateBtn.click();

        System.out.println("Template name updated to: " + newName);
    } catch (Exception e) {
        System.err.println("Error while updating template name: " + e.getMessage());
        throw e;
    }
}


//36
public boolean performAddQuestionFlow(String questionText, String expectedAnswerType) {
    try {
       // click(CoreQAFormViewTemplateListPageRepo.UpdatedscreenAddQuestioncheckbox);
    	Thread.sleep(Duration.ofSeconds(10));
        input(CoreQAFormViewTemplateListPageRepo.updatedscreenQuestionfieldtextbox, questionText);
        click(CoreQAFormViewTemplateListPageRepo.updatedscreenexpectedAnswerTypeDropdown);
        JavascriptClick(CoreQAFormViewTemplateListPageRepo.questionTypeOption(expectedAnswerType), driver);

        click(CoreQAFormViewTemplateListPageRepo.updatedscreenIsMandatory);
        click(CoreQAFormViewTemplateListPageRepo.updatedscreenAddbtn);

       return true;

    } catch (Exception e) {
        System.out.println("Exception in performAddQuestionFlow: " + e.getMessage());
        return false;
    }
}

//37 
public boolean deleteQuestionFromTemplate() {
    try {
        // Click action button
    	JavascriptClick(CoreQAFormViewTemplateListPageRepo.actionButtonAgainstQuestion, driver);
        Log.info("Clicked action button against a question");

        // Click delete option
        //Thread.sleep(Duration.ofSeconds(10));
        click(CoreQAFormViewTemplateListPageRepo.deleteOption);
        Log.info("Clicked Delete option from action menu");

        // Confirm deletion by clicking 'Yes'
        click(CoreQAFormViewTemplateListPageRepo.yesButtonOnPopup);
        Log.info("Confirmed deletion by clicking Yes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.deleteSuccessMessage)).isDisplayed();
    } catch (Exception e) {
        Log.error("Error while deleting question: " + e.getMessage());
        return false;
    }
}

//38 Template Edit Page -Edit Question Data Prefill check
public boolean verifyPrefilledTemplateName1(String expectedTemplateName) {
    try {
        WebElement templateField = driver.findElement(CoreQAFormViewTemplateListPageRepo.templateNameField);
        String actualValue = templateField.getAttribute("value");
        Log.info("Actual Template Name: " + actualValue);
        return actualValue.equals(expectedTemplateName);
    } catch (Exception e) {
        Log.error("Error verifying prefilled template name: " + e.getMessage());
        return false;
    }
}

//39
public void editTemplateQuestionAndVerifyUpdate(String newQuestionText) throws InterruptedException {
	
	Thread.sleep(Duration.ofSeconds(5));
	 click(CoreQAFormViewTemplateListPageRepo.actionButtonAgainstQuestion);
	 Thread.sleep(Duration.ofSeconds(5));
    JavascriptClick(CoreQAFormViewTemplateListPageRepo.ActionbuttonagainstEditbutton, driver);
    
    Thread.sleep(Duration.ofSeconds(5));
    clear(CoreQAFormViewTemplateListPageRepo.questionField);
    input(CoreQAFormViewTemplateListPageRepo.questionField, newQuestionText);
    Thread.sleep(Duration.ofSeconds(5));

    click(CoreQAFormViewTemplateListPageRepo.expectedAnswerTypeDropdown);
    click(CoreQAFormViewTemplateListPageRepo.Datepickerdropdown);
    click (CoreQAFormViewTemplateListPageRepo.ismandatoryuncheck);

    click(CoreQAFormViewTemplateListPageRepo.updatebutton);

    boolean isSuccessDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.successToastMessage);
    Assert.assertTrue(isSuccessDisplayed, "Success message not displayed after update.");
}

//40,

public void markTemplateInactive() throws InterruptedException {
    click(CoreQAFormViewTemplateListPageRepo.Cancel);
    click (CoreQAFormViewTemplateListPageRepo.searchButton);
    click(CoreQAFormViewTemplateListPageRepo.actionButton);
    click(CoreQAFormViewTemplateListPageRepo.action_Edit);
    
   /* // Check and untick the 'Active' checkbox if currently ticked
    WebElement checkbox = driver.findElement(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
    String checkboxClass = checkbox.getAttribute("class");
    if (checkboxClass.contains("rz-state-active")) {
        click(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
    } else {
        System.out.println("Checkbox already unticked");
    }

    click(CoreQAFormViewTemplateListPageRepo.updatebutton)*/
    
 // Wait for the checkbox to reappear in DOM to avoid stale element issue
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox));
    wait.until(ExpectedConditions.elementToBeClickable(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox));

    // Now fetch fresh element reference
    WebElement checkbox = driver.findElement(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
    String checkboxClass = checkbox.getAttribute("class");

    if (checkboxClass.contains("rz-state-active")) {
        click(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
        System.out.println("Checkbox was ticked. Now unticked.");
    } else {
        System.out.println("Checkbox already unticked.");
    }

    click(CoreQAFormViewTemplateListPageRepo.updatebutton);
    System.out.println("Clicked on Update button.");
}


//TC 41 
public void markTemplateActive() throws InterruptedException {
	Thread.sleep(1000);
    JavascriptClick(CoreQAFormViewTemplateListPageRepo.Cancel, driver);
    Thread.sleep(Duration.ofSeconds(5));
    click (CoreQAFormViewTemplateListPageRepo.searchButton);
    click(CoreQAFormViewTemplateListPageRepo.actionButton);
    click(CoreQAFormViewTemplateListPageRepo.action_Edit);
 // Wait for the checkbox to appear fresh after the page load
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox));
    
    // Re-locate it again just before use to avoid staleness
    checkbox = driver.findElement(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
    String checkboxClass = checkbox.getAttribute("class");
    
    if (!checkboxClass.contains("rz-state-active")) {
        click(CoreQAFormViewTemplateListPageRepo.tickactivecheckbox);
    } else {
        System.out.println("Checkbox already ticked");
    }

    click(CoreQAFormViewTemplateListPageRepo.updatebutton);
    //waitForSpinnerToDisappear();
  wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.activestatustoastmessage));
    System.out.println("PASS: Toast message appeared - Template Updated Successfully.");
}

// TC 42
public void clickCancelButtonOnEditPage()throws InterruptedException   {
	try {
        waitForSpinnerToDisappear();

        Thread.sleep(Duration.ofSeconds(5));

        //click(CoreQAFormViewTemplateListPageRepo.Cancel);
        
        moveToElementAndClick(driver, CoreQAFormViewTemplateListPageRepo.QuestionAction);
       click(CoreQAFormViewTemplateListPageRepo.ActionbuttonagainstEditbutton);
     click(CoreQAFormViewTemplateListPageRepo.Cancel);
        System.out.println("Clicked on Cancel button to navigate back to Q&A Template Page.");
    } catch (Exception e) {
        throw new AssertionError("Cancel navigation failed: " + e.getMessage());
    }
}
public boolean isQATemplatePageLoaded() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.searchButton));
        return ElementDisplayed(CoreQAFormViewTemplateListPageRepo.searchButton);
    } catch (Exception e) {
        System.out.println("Q&A Template page not loaded: " + e.getMessage());
        return false;
    }
}



//43 
/*public void verifyIsActiveStatusReflectedInGrid(String templateName, boolean shouldBeActive) {
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.QATemplategrid);
    List<WebElement> templates = driver.findElements(CoreQAFormViewTemplateListPageRepo.QATemplategrid);

    for (WebElement row : templates) {
        if (row.getText().equals(templateName)) {
            WebElement statusElement = row.findElement(By.xpath("following-sibling::td[normalize-space()='"
                    + (shouldBeActive ? "Yes" : "No") + "']"));
            if (statusElement.isDisplayed()) {
                System.out.println("Status correctly reflected in grid: " + (shouldBeActive ? "Active" : "Inactive"));
            } else {
                Assert.fail("Status not reflected as expected in grid.");
            }
            return;
        }
    }
    Assert.fail("Template with name '" + templateName + "' not found in grid.");
}*/

private void waitForElementToBeVisible(By qATemplategrid) {
	// TODO Auto-generated method stub
	
}

public void UpdatedtemplateRefelectedinGridcheck() throws InterruptedException {
    String templateName = "UpdatedTemplate123";
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

    // Wait for the grid to be visible
    wait.until(ExpectedConditions.visibilityOfElementLocated(CoreQAFormViewTemplateListPageRepo.templateGrid));

    // Wait for spinner to disappear
  //  waitForSpinnerToDisappear();

    // Check grid repeatedly until the template is listed (max 30 seconds)
    boolean found = false;
    long endTime = System.currentTimeMillis() + 30000;

    while (System.currentTimeMillis() < endTime) {
        List<WebElement> nameCells = getDriver().findElements(CoreQAFormViewTemplateListPageRepo.templateNameCells);
        for (WebElement cell : nameCells) {
            String name = cell.getText().trim();
            if (name.equalsIgnoreCase(templateName)) {
                System.out.println("Template '" + templateName + "' found in the grid.");
                found = true;
                break;
            }
        }

        if (found) break;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    assert found : "Template '" + templateName + "' not found in the grid.";
    Log.info("Verified 'UpdatedTemplate123' is visible in the Q&A Template grid.");
}

	
//TC 44
public void clickViewButtonOnTemplate() throws InterruptedException {
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.actionButton);
    click(CoreQAFormViewTemplateListPageRepo.actionButton);

    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.QATemplategridviewbutton);
    click(CoreQAFormViewTemplateListPageRepo.QATemplategridviewbutton);

    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.ViewTemplatescreenexpectedcondition);
    boolean isViewPageDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.ViewTemplatescreenexpectedcondition);

  //waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.elementofthespecificrecord);
  //  boolean isViewPageDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.elementofthespecificrecord);

    if (!isViewPageDisplayed) {
        Assert.fail("View Template page did not open.");
    }
}

//TC 45

public void verifyTemplateNameOnViewPage() {
    // Get expected template name from the grid
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.elementofthespecificrecord);
    String expectedTemplateName = getText(CoreQAFormViewTemplateListPageRepo.elementofthespecificrecord).trim();

    // Wait for View Template screen to load
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.ViewTemplatescreenexpectedcondition);

    // Get actual template name from the View page
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.Viewtemplateheadertile);
    String actualTemplateName = getText(CoreQAFormViewTemplateListPageRepo.Viewtemplateheadertile).trim();

    // Validate template name
    if (!actualTemplateName.equals(expectedTemplateName)) {
        Assert.fail("Template name mismatch. Expected: " + expectedTemplateName + ", but found: " + actualTemplateName);
    }
}

//TC 46
public void verifyViewTemplateGridHeaders() {

    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderOrderNumber);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderQuestion);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderExpectedAnswerType);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderValues);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderParentQuestion);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderParentValue);
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.gridHeaderIsMandatory);
}

//TC 47
public boolean verifyGridQuestionDisplayed() {
    try {
    	 waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.ViewTemplateQuestiongrid);
        String questionText = getText(CoreQAFormViewTemplateListPageRepo.ViewTemplateQuestiongrid).trim();
        System.out.println("Grid Question Cell Text: " + questionText);
        return !questionText.isEmpty();
    } catch (Exception e) {
        System.out.println("Exception while verifying grid question: " + e.getMessage());
        return false;
    }
}

//TC 48
public void verifyCloseButtonRedirectsToQATemplatePage() throws InterruptedException {
    System.out.println("******** Test Case: Close button functionality ********");

    // Wait for close icon to be clickable and click it
    click(CoreQAFormViewTemplateListPageRepo.ViewTemplategridcancelicon);
   
    // Wait for Q&A Template title to be visible after redirection
    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.QATemplatetile);
    boolean isRedirected = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.QATemplatetile);

    if (isRedirected) {
        System.out.println("Successfully redirected to Q&A Template Page.");
    } else {
        System.out.println(" Redirection to Q&A Template Page failed.");
    }
}

//TC 49 View History Button Functionality
public void verifyViewHistoryPopup() throws InterruptedException {
   
    click(CoreQAFormViewTemplateListPageRepo.actionButton);
    click(CoreQAFormViewTemplateListPageRepo.Viewhistorybutton);

    // Verify the Template History popup title
    boolean isPopupDisplayed = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.templateHistoryPopupTitle);
    if (isPopupDisplayed) {
        System.out.println("Template History popup is displayed successfully.");
    } else {
        System.out.println("Template History popup is NOT displayed.");
    }

    assert isPopupDisplayed : "Template History popup not displayed after clicking View History.";
}

//TC 50 View History -Template Name field presence

public void verifyTemplateNameFieldPresence() {
    boolean isPresent = ElementDisplayed(CoreQAFormViewTemplateListPageRepo.TemplateNamewrittentext);

    if (isPresent) {
        System.out.println("Template Name field is displayed as expected.");
    } else {
        System.out.println("Template Name field is NOT displayed.");
    }

    assert isPresent : "Template Name field should be present in the popup.";
}



//TC 51 View history -Template Name field data prefiled

public void verifyTemplateNameIsPrefilled(String expectedTemplateName) {
    System.out.println("Verifying prefilled Template Name in Template History popup...");

    String actualValue = driver.findElement(CoreQAFormViewTemplateListPageRepo.input_TemplateName_HistoryPopup).getAttribute("value");

    System.out.println("Expected Template Name: " + expectedTemplateName);
    System.out.println("Actual Template Name in field: " + actualValue);

    assert actualValue.equals(expectedTemplateName) : "Template Name is not prefilled correctly!";
}

//TC 52
public void verifyTemplateHistoryGridColumns() {
    System.out.println("Verifying grid columns in Template History popup...");

    boolean isStatusDisplayed = driver.findElement(CoreQAFormViewTemplateListPageRepo.col_Status).isDisplayed();
    boolean isLastModifiedDisplayed = driver.findElement(CoreQAFormViewTemplateListPageRepo.col_LastModified).isDisplayed();
    boolean isRemarkDisplayed = driver.findElement(CoreQAFormViewTemplateListPageRepo.col_Remark).isDisplayed();

    assert isStatusDisplayed : "Status column is not displayed.";
    assert isLastModifiedDisplayed : "Last Modified column is not displayed.";
    assert isRemarkDisplayed : "Remark column is not displayed.";

    System.out.println("All expected columns are present: Status, Last Modified, Remark");
}

//TC 53
public void verifyTemplateHistoryGridData() {

    waitForElementToBeVisible(CoreQAFormViewTemplateListPageRepo.historyGridRow);

    // Validate at least one row is visible
    List<WebElement> rows = driver.findElements(CoreQAFormViewTemplateListPageRepo.historyGridRow);
    if (rows.size() > 0) {
        System.out.println("Grid rows are visible. Validating column data...");
        
        for (WebElement row : rows) {
            String status = row.findElement(CoreQAFormViewTemplateListPageRepo.statusCell).getText().trim();
            String modifiedDate = row.findElement(CoreQAFormViewTemplateListPageRepo.lastModifiedCell).getText().trim();
            String remark = row.findElement(CoreQAFormViewTemplateListPageRepo.remarkCell).getText().trim();

            System.out.println("Row => Status: " + status + " | Last Modified: " + modifiedDate + " | Remark: " + remark);
            
            if (status.isEmpty() || modifiedDate.isEmpty()) {
                throw new AssertionError("Grid row has missing data.");
            }
        }
    } else {
        throw new AssertionError("No grid rows found in Template History popup.");
    }
}

//TC 54

public void verifyTemplateStatusIconsInHistoryGrid() {
  
    List<WebElement> rows = driver.findElements(CoreQAFormViewTemplateListPageRepo.historyGridRow);
    
    if (rows.size() == 0) {
        throw new AssertionError("No rows found in the Template History grid.");
    }

    for (WebElement row : rows) {
        WebElement statusIcon = row.findElement(CoreQAFormViewTemplateListPageRepo.statusIconImg);
        String iconSrc = statusIcon.getAttribute("src");

        System.out.println("Icon source: " + iconSrc);

        if (iconSrc.contains("green-check") || iconSrc.contains("greentick") || iconSrc.contains("success")) {
            System.out.println(" Active status confirmed (green tick).");
        } else if (iconSrc.contains("red-cross") || iconSrc.contains("redx") || iconSrc.contains("fail")) {
            System.out.println("Inactive status confirmed (red cross).");
        } else {
            throw new AssertionError("Unrecognized status icon: " + iconSrc);
        }
    }
}

//TC 55
public void verifyLastModifiedColumnInTemplateHistory() {
   
    List<WebElement> rows = driver.findElements(CoreQAFormViewTemplateListPageRepo.historyGridRow);

    if (rows.isEmpty()) {
        throw new AssertionError("No rows found in the Template History grid.");
    }

    for (WebElement row : rows) {
        WebElement lastModifiedCell = row.findElement(CoreQAFormViewTemplateListPageRepo.lastModifiedCell);
        String modifiedText = lastModifiedCell.getText().trim();
        System.out.println("Last Modified Value: " + modifiedText);

        if (modifiedText.isEmpty()) {
            throw new AssertionError("Last Modified value is empty for one or more rows.");
        }

    }
}

//TC 56

public void verifyOtherTemplateNameSearchFunctionality(String templateName) throws InterruptedException {
    System.out.println("Searching for template: " + templateName);

    clear(CoreQAFormViewTemplateListPageRepo.templateNameSearchField);
    input(CoreQAFormViewTemplateListPageRepo.templateNameSearchField, templateName);
    click(CoreQAFormViewTemplateListPageRepo.templateSearchButton);
    WaitLoader();

    // Verify grid result
    List<WebElement> rows = driver.findElements(CoreQAFormViewTemplateListPageRepo.historyGridRow);
    if (rows.isEmpty()) {
        throw new AssertionError("No results found for template name: " + templateName);
    }

    System.out.println("Results found for template: " + templateName);
}
}





















	





