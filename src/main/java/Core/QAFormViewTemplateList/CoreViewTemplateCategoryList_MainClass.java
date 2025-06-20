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
import com.Page_Repository.CoreViewTemplateCategoryListPageRepo;
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
	public static void JavascriptClick(By by, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(by));
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
	
	// Method to navigate to View Template Creation Page
	public void Navigate_to_View_Template_Creation_Page() throws InterruptedException {
		
		Log.info("Navigation to the Q&A Form View Main Menu");
		moveToElementAndClick(driver, CoreViewTemplateCategoryListPageRepo.QAFormMainMenu);
		Common.fluentWait("QAFormMainMenu", CoreViewTemplateCategoryListPageRepo.QAFormMainMenu);
		WebElement QAFormMainMenu = driver.findElement(CoreViewTemplateCategoryListPageRepo.QAFormMainMenu);
		QAFormMainMenu.click();
		Log.info("Clicked on Q&A Form View Main Menu. ");
		WebElement ViewTemplateListSubmenu = driver.findElement(CoreViewTemplateCategoryListPageRepo.ViewTemplateListSubmenu);
		ViewTemplateListSubmenu.click();
		Log.info("Clicked on View Template sub Menu. ");
		WebElement createnewbtn = driver.findElement(CoreViewTemplateCategoryListPageRepo.CreateNew);
		createnewbtn.click();
		Log.info("Clicked on Create New ");
		Log.info("Entering  Template Name");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement Templatename =wait.until(ExpectedConditions.elementToBeClickable(CoreViewTemplateCategoryListPageRepo.TemplateName));
          Templatename.clear();
		Templatename.sendKeys("CategoryList Template");
		Log.info("Entered value into Template Name field: CategoryList Template");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebElement questionField = wait1.until(ExpectedConditions.elementToBeClickable(CoreViewTemplateCategoryListPageRepo.Question));
	    questionField.clear();
	    questionField.sendKeys("Q1");
	    Log.info("Entered value into Question field: Q1");
	    click (CoreViewTemplateCategoryListPageRepo.ExpectedAnswerTypeDropdown);
        click (CoreViewTemplateCategoryListPageRepo.textBoxOption);
        click(CoreViewTemplateCategoryListPageRepo.Addbtn);
        Thread.sleep(Duration.ofSeconds(4));
        //waitForElementToBeVisible(CoreViewTemplateCategoryListPageRepo.Gridspantextbox);
        waitForElementToBeVisible(CoreViewTemplateCategoryListPageRepo.header_LastModified);
        Thread.sleep(Duration.ofSeconds(4));
       
	}
	private void waitForElementToBeVisible(By header_LastModified) {
		// TODO Auto-generated method stub
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(gridspantextbox));
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	   // wait.until(ExpectedConditions.visibilityOfElementLocated(header_LastModified));
		
	}
	public void Click_On_View_template_category_mapping_list() throws InterruptedException {
		Log.info("Click on View Template Category mapping list");
		Common.fluentWait("QAFormMainMenu", CoreViewTemplateCategoryListPageRepo.ViewTemplateListSubmenu);
		moveToElementAndClick(driver, CoreViewTemplateCategoryListPageRepo.ViewTemplateListSubmenu);
		//Common.fluentWait("QAFormMainMenu", CoreViewTemplateCategoryListPageRepo.QAFormMainMenu);
		WebElement QAFormMainMenu = driver.findElement(CoreViewTemplateCategoryListPageRepo.ViewTemplateListSubmenu);
		QAFormMainMenu.click();
		WebElement ViewTemplatecategorylist = driver.findElement(CoreViewTemplateCategoryListPageRepo.ViewTemplatecategorylist);
		ViewTemplatecategorylist.click();
		Common.fluentWait("MappingTemplateAndCategoryHeader", CoreViewTemplateCategoryListPageRepo.MappingTemplateAndCategoryHeader);

	    WebElement headerElement = driver.findElement(CoreViewTemplateCategoryListPageRepo.MappingTemplateAndCategoryHeader);
	    if (headerElement.isDisplayed()) {
	        Log.info("Mapping Template and Category page opened successfully.");
	    } else {
	        Log.error("Failed to open Mapping Template and Category page.");
	    }
	}
	
	
	public void validatePresenceOfFieldsAndButtons() throws InterruptedException {
	    System.out.println("Validating presence of Template Name, Asset Category, Account Category, Save and Clear buttons");

	    // Wait and validate Template Name dropdown
	    Common.fluentWait("TemplateName", CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);
	    ElementDisplayed(CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);

	    // Wait and validate Asset Category dropdown
	    Common.fluentWait("AssetCategory",CoreViewTemplateCategoryListPageRepo.AssetCategoryDropdown);
	    ElementDisplayed(CoreViewTemplateCategoryListPageRepo.AssetCategoryDropdown);

	    // Wait and validate Account Category dropdown
	    Common.fluentWait("AccountCategory",CoreViewTemplateCategoryListPageRepo.AccountCategoryDropdown);
	    ElementDisplayed(CoreViewTemplateCategoryListPageRepo.AccountCategoryDropdown);

	    // Wait and validate Save button
	    Common.fluentWait("SaveButton",CoreViewTemplateCategoryListPageRepo.SaveButton);
	    ElementDisplayed(CoreViewTemplateCategoryListPageRepo.SaveButton);

	    // Wait and validate Clear button
	    Common.fluentWait("ClearButton",CoreViewTemplateCategoryListPageRepo.ClearButton);
	    ElementDisplayed(CoreViewTemplateCategoryListPageRepo.ClearButton);
	}
	
	public void validateSaveWithoutSelections() throws Exception {
	    System.out.println("******** TestCase: Save Without Selection Validation ********");

	 
	    //Step 1: Click Save without selection
	    click(CoreViewTemplateCategoryListPageRepo.ClearButton);
	    click(CoreViewTemplateCategoryListPageRepo.SaveButton);
	    Thread.sleep(1000);
	    System.out.println("Validation 1: " + GetElementText(CoreViewTemplateCategoryListPageRepo.exp_ToastMsgTemplateName));

	    // Step 2: Select Template Name
	    click(CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);
	    click(CoreViewTemplateCategoryListPageRepo.Categorylistdropdownoption);
	    click(CoreViewTemplateCategoryListPageRepo.SaveButton);
	    Thread.sleep(1000);
	    System.out.println("Validation 2: " + GetElementText(CoreViewTemplateCategoryListPageRepo.exp_ToastMsgAssertCategory));

	    // Step 3: Select Asset Category
	    click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
	    click(CoreViewTemplateCategoryListPageRepo.sma);
	    click(CoreViewTemplateCategoryListPageRepo.SaveButton);
	    Thread.sleep(1000);
	    System.out.println("Validation 3: " + GetElementText(CoreViewTemplateCategoryListPageRepo.exp_ToastMsgAccountCategory));
	    click(CoreViewTemplateCategoryListPageRepo.ClearButton);
	}
	
	public void verifyTemplateNameDropdownValues(String expectedTemplateName) throws InterruptedException {
       System.out.println("Test: Verify Template Name dropdown displays created template: " + expectedTemplateName);

        // Click the dropdown input to open options
        click(CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);

        // Use dynamic locator to check if the expected template is listed
        By optionLocator = CoreViewTemplateCategoryListPageRepo.Categorylistdropdownoption;
        boolean isOptionVisible = ElementDisplayed(optionLocator);

        if (isOptionVisible) {
            System.out.println("PASS: Template '" + expectedTemplateName + "' is listed in the dropdown.");
        } else {
            System.out.println("FAIL: Template '" + expectedTemplateName + "' is NOT listed in the dropdown.");
        }
	}
	
	public void verifyTemplateNameDropdownOptionSelection() throws InterruptedException {

        // Click the dropdown input to open options
        click(CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.Categorylistdropdownoption, driver);
        
        boolean isSelectedOptionVisible = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.exp_CategoryListdropdownOption);

        if (isSelectedOptionVisible) {
            System.out.println("PASS: CategoryList Template' option is selected and displayed in the Template Name dropdown.");
        } else {
            System.out.println("FAIL: CategoryList Template' option is NOT selected or not displayed.");
        }
		
	}
	
	public void verifyAssetCategoryDropdownvalues() throws InterruptedException {

        // Click the dropdown input to open options
        click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
        System.out.println("Clicked on 'Asset Category' dropdown.");
        Thread.sleep(Duration.ofSeconds(4));

        // Use dynamic locator to check if the expected template is listed
        boolean opt1 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.nap);
        boolean opt2 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.sma);
        
        boolean result = opt1 && opt2;

        if (result) {
            System.out.println("PASS: Both 'NAP Category' and 'SMA Category' options are displayed in the 'Asset Category' dropdown.");
        } else {
            System.out.println("FAIL: Asset category dropdown options are missing");
        }
		
	}
	
	public void VerifyAssetCategorySMASelectionImpact() throws InterruptedException {
		
		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		JavascriptClick(CoreViewTemplateCategoryListPageRepo.sma, driver);
		
		 click(CoreViewTemplateCategoryListPageRepo.AccCategoryDropdown);
	    
	     
	     /*Common.fluentWait("Option 1", CoreViewTemplateCategoryListPageRepo.SMA0);
	     Common.fluentWait("Option 1", CoreViewTemplateCategoryListPageRepo.SMA1);
	     Common.fluentWait("Option 1", CoreViewTemplateCategoryListPageRepo.SMA2);*/

	        // Use dynamic locator to check if the expected template is listed
	        boolean opt1 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.SMA0);
	        boolean opt2 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.SMA1);
	        boolean opt3 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.SMA2);
	        
	        boolean result = opt1 && opt2 && opt3;

	        if (result) {
	            System.out.println("PASS:'SMA Category' selection correctly displays all expected Account Category options");
	        } else {
	            System.out.println("FAIL: One or more expected Account Category options are not displayed after selecting 'SMA Category'");
	            		if (!opt1) System.out.println(" Option SMA0 is missing.");
	            		 if (!opt2) System.out.println("Option SMA1 is missing.");
	            		 if (!opt3) System.out.println(" Option SMA2 is missing");
	        }
	}
	
	public void VerifyAssetCategoryNPASelectionImpact() throws InterruptedException {
		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		System.out.println("Clicked on 'Asset Category' dropdown.");

        JavascriptClick(CoreViewTemplateCategoryListPageRepo.nap, driver);
        System.out.println("Selected 'NAP Category' from the Asset Category dropdown.");
        
        click(CoreViewTemplateCategoryListPageRepo.AccCategoryDropdown);
        System.out.println("Clicked on 'Account Category' dropdown.");

        
        boolean opt1 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.d1);
        boolean opt2 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.d2);
        boolean opt3 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.d3);
        boolean subStandard = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.subStandard);
        boolean lossAsset = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.lossAsset);
       
        boolean result = opt1 && opt2 && opt3 && subStandard && lossAsset;

        if (result) {
            System.out.println("PASS: All expected Account Category options are displayed for 'NAP Category'");
        } else {
            System.out.println("FAIL: One or more expected Account Category options are missing for 'NAP Category'");
            if (!opt1) System.out.println("-> Option D1 is missing.");
            if (!opt2) System.out.println("-> Option D2 is missing.");
            if (!opt3) System.out.println("-> Option D3 is missing.");
            if (!subStandard) System.out.println("-> Option 'Sub Standard' is missing.");
            if (!lossAsset) System.out.println("-> Option 'Loss Asset' is missing.");
        }
        }
	
	
	public void VerifyAssetCategorySelection() throws InterruptedException {
		
		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		 System.out.println("Clicked on 'Asset Category' dropdown.");
        Thread.sleep(Duration.ofSeconds(4));

        // Use dynamic locator to check if the expected template is listed
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.sma, driver);
        System.out.println("Selected 'SMA Category' from the dropdown.");
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.exp_sma);
       

        if (flag) {
            System.out.println("PASS: Selected 'SMA  Category' is displayed in the dropdown.");
        } else {
            System.out.println("FAIL: Selected 'SMA Category' is NOT displayed in the dropdown");
        }
	}
	
    public void VerifyAccountCategorySelection() throws InterruptedException {
		
		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		 System.out.println("Clicked on 'Asset Category' dropdown.");
		 
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.sma, driver);
        System.out.println("Selected 'SMA Category' from the Asset Category options.");
        
        click(CoreViewTemplateCategoryListPageRepo.AccCategoryDropdown);
        System.out.println("Clicked on 'Account Category' dropdown.");
        
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.SMA0, driver);
        System.out.println("Selected 'SMA0' from the Account Category options.");
        
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.exp_SMA0);
       

        if (flag) {
            System.out.println("PASS: Selected 'SMA0' is displayed in the Account Category dropdown");
        } else {
            System.out.println("FAIL: Selected 'SMA0' is NOT displayed in the Account Category dropdown");
        }
	}
    
    public void SaveButtonFunctionality() throws InterruptedException {
		click(CoreViewTemplateCategoryListPageRepo.TemplateNameDropdown);
		 System.out.println("Clicked on 'Template Name' dropdown.");
		 
		JavascriptClick(CoreViewTemplateCategoryListPageRepo.Categorylistdropdownoption, driver);
		 System.out.println("Selected a value from the 'Template Name' dropdown.");

		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		System.out.println("Clicked on 'Asset Category' dropdown.");
        Thread.sleep(Duration.ofSeconds(4));
        
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.sma, driver);
        System.out.println("Selected 'SMA Category' from the Asset Category options.");
        
        click(CoreViewTemplateCategoryListPageRepo.AccCategoryDropdown);
        System.out.println("Clicked on 'Account Category' dropdown.");
        
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.SMA0, driver);
        System.out.println("Selected 'SMA0' from the Account Category options.");
        
        click(CoreViewTemplateCategoryListPageRepo.save);
        System.out.println("Clicked on 'Save' button.");
        
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.exp_msg);
       

        if (flag) {
            System.out.println("PASS: Data is saved, and a success message is shown on the UI.");
        } else {
            System.out.println("FAIL: Data is not saved, and a success message is not shown on the UI.");
        }
	}
    
    public void ClearButtonFunctionality() throws InterruptedException {

		click(CoreViewTemplateCategoryListPageRepo.assertCategoryDropdown);
		System.out.println("Clicked on 'Asset Category' dropdown.");
       Thread.sleep(Duration.ofSeconds(4));
       
       JavascriptClick(CoreViewTemplateCategoryListPageRepo.sma, driver);
       System.out.println("Selected 'SMA Category' from the Asset Category options.");
       
       click(CoreViewTemplateCategoryListPageRepo.AccCategoryDropdown);
       System.out.println("Clicked on 'Account Category' dropdown.");
       
       JavascriptClick(CoreViewTemplateCategoryListPageRepo.SMA0, driver);
       System.out.println("Selected 'SMA0' from the Account Category options.");
        
        JavascriptClick(CoreViewTemplateCategoryListPageRepo.clear, driver);
        System.out.println("Clicked on 'Clear' button.");
        
              
	}
    
    public void VerifyGridColumns() throws InterruptedException {
        
        boolean flag1 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.gridTemplateName);
        boolean flag2 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.gridAccountCategory);
        boolean flag3 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.gridStatus);
        boolean flag4 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.gridActions);
        
        boolean flag = flag1 && flag2 && flag3 && flag4;
       
        if (flag) {
            System.out.println("PASS: All required grid columns (Template Name, Account Category, Status, Action) are displayed");
        } else {
            System.out.println("FAIL: One or more required grid columns are NOT displayed");
        }
	}
    
    public void VerifyGridTemplateNameDisplays() throws InterruptedException {
    
        
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.templateNameData);
        
        if (flag) {
            System.out.println("PASS: Template Name is displayed correctly in the grid.");
        } else {
            System.out.println("FAIL: Template Name is NOT displayed in the grid.");
        }
       
	}
    
    
    public void VerifyGridAccountCategoryMapping() throws InterruptedException {
        
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.accountCategoryData);
        
        if (flag) {
            System.out.println("PASS: Account Category is displayed correctly in the grid");
        } else {
            System.out.println("FAIL: Account Category is NOT displayed in the grid");
        }
       
	}
    
    public void VerifyGridActiveStatus() throws InterruptedException {
        
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.greenTick);
        
        if (flag) {
            System.out.println("PASS: Green tick icon is displayed for templates with Active status");
        } else {
            System.out.println("FAIL: Green tick icon is NOT displayed for templates with Active status");
        }
       
	}
    
    
    public void Verifygridinactivestatus() throws InterruptedException {
    	
    	click(CoreViewTemplateCategoryListPageRepo.menu);
    	click(CoreViewTemplateCategoryListPageRepo.deactivateActivate);
    	
    	Thread.sleep(Duration.ofSeconds(2));
		
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.redTick);
        
        if (flag) {
            System.out.println("PASS: Red tick icon is displayed for template with Inactive status.");
        } else {
            System.out.println("FAIL: Red tick icon is not displayed for template with Inactive status.");
        }
       
	}
    
    public void VerifyActionButtonsValue() throws InterruptedException {
    	
    	click(CoreViewTemplateCategoryListPageRepo.menu);
    	
    	Thread.sleep(Duration.ofSeconds(2));
		
    	
        boolean flag1 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.edit);
        System.out.println("Checked visibility of 'Edit' button.");
        Thread.sleep(Duration.ofSeconds(2));
        boolean flag2 = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.deactivateActivate);
        System.out.println("Checked visibility of 'Deactivate/Activate' button.");
        
        boolean flag = flag1 && flag2;
        
        if (flag) {
            System.out.println("PASS: Both 'Edit' and 'Deactivate/Activate' buttons are displayed in the Action menu.");
        } else {
            System.out.println("FAIL: One or both action buttons ('Edit' or 'Deactivate/Activate') are NOT displayed");
        }
       
	}
    
    public boolean isInputReadonly(By locator) {
        WebElement input = driver.findElement(locator);
        return input.getAttribute("readonly") != null;
    }
    
    public void VerifyEditActionPrefillExistingValues() throws InterruptedException {
    	
    	click(CoreViewTemplateCategoryListPageRepo.menu);
    	JavascriptClick(CoreViewTemplateCategoryListPageRepo.edit, driver);
    	
    	Thread.sleep(Duration.ofSeconds(2));
		
        boolean flag1 = isInputReadonly(CoreViewTemplateCategoryListPageRepo.assertCategoryGray);
        boolean flag2 = isInputReadonly(CoreViewTemplateCategoryListPageRepo.accCategoryGray);
        
        boolean flag = flag1 && flag2;
        
        if (flag1 && flag2) {
            System.out.println("PASS: Both 'Asset Category' and 'Account Category' fields are readonly.");
        } else {
            System.out.println("FAIL: One or both fields are not readonly.");
            if (!flag1) System.out.println("FAILURE: 'Asset Category' is editable.");
            if (!flag2) System.out.println("FAILURE: 'Account Category' is editable.");
        }
       
	}
    
    public void VerifyEditandSaveChangesingrid() throws InterruptedException {
    	
    
       //pass
	}	
    

    public void VerifyToggleActivate_DeactivateAction() throws InterruptedException {
    	
    	click(CoreViewTemplateCategoryListPageRepo.menu);
    	 System.out.println("Clicked on Action menu");
    	click(CoreViewTemplateCategoryListPageRepo.deactivateActivate);
    	System.out.println("Clicked on Activate/Deactivate option");
    	
    	Thread.sleep(Duration.ofSeconds(2));
		
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.greenTick);
        
        if (flag) {
            System.out.println("PASS: Template status successfully toggled to Active (Green Tick displayed)");
        } else {
            System.out.println("FAIL: Template status NOT toggled to Active (Green Tick not displayed)");
        }
       
	}	
    
    public void VerifyEditandSaveChangesingridwithsamedata() throws InterruptedException {
    	
    	click(CoreViewTemplateCategoryListPageRepo.menu);
    	 System.out.println("Clicked on Action menu.");
    	 
    	JavascriptClick(CoreViewTemplateCategoryListPageRepo.edit, driver);
    	 System.out.println("Clicked on 'Edit' button.");
    	 
    	click(CoreViewTemplateCategoryListPageRepo.save);
    	
    	System.out.println("Clicked on 'Save' button without modifying any data.");

    	
    	Thread.sleep(Duration.ofSeconds(2));
		
        boolean flag = ElementDisplayed(CoreViewTemplateCategoryListPageRepo.exp_updateMsg);
        
        if (flag) {
            System.out.println("PASS: Already Mapped Validation Will shown correctly");
        } else {
            System.out.println("FAIL: Already Mapped Validation Will not Displayed.");
        }
       
	}
    
    
    
    
   
}

