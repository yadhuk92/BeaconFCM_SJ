package Core.SecurityManagement;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.UserManagement_Locators;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class UserManagement_Page extends Base_Class
{
	 UserManagement_Locators PageRepository= new UserManagement_Locators();
	 private WebDriver driver;
	 
	 /*public UserManagement_Page(WebDriver driver) {
	        this.driver = driver;
	        Log.info("WebDriver assigned to UserManagement_Page.");
	        PageFactory.initElements(driver, this); // Initialize WebElements
	        Log.info("DispositionMasterPage initialization completed.");
	    }*/

	public boolean GoCollectionModule() throws InterruptedException 
	{
		//Thread.sleep(10000);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.loginSelectionSpinner);
		Common.fluentWait("GoCollection", UserManagement_Locators.GoCollection);
		Thread.sleep(5000);
		click(UserManagement_Locators.GoCollection);
		Thread.sleep(1000);
		return true;
	}
	
	public boolean SelectSecurityManagementMenu() throws InterruptedException 
	{
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.moduleSelectionSpinner);
		Common.fluentWait("SecurityManagementMenu", PageRepository.SecurityManagementMenu);
		click(PageRepository.SecurityManagementMenu);
		return true;
	}
	public boolean SelectUserManagementMenu() throws InterruptedException 
	{
		Common.fluentWait("UserManagementMenu", PageRepository.UserManagementMenu);
		click(PageRepository.UserManagementMenu);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.moduleSelectionSpinner);
		return true;
	}
	
	
	public boolean LocatorDisplayed() throws InterruptedException {
	    	Common.fluentWait("UserManagementNextBtn", PageRepository.UserManagementNextBtn);
	    	Thread.sleep(5000);
		    waitVisibility(UserManagement_Locators.UserManagementPageUsername);
	        ElementDisplayed(UserManagement_Locators.UserManagementPageUsername);
	        ElementDisplayed(PageRepository.UserManagementPageName);
	        ElementDisplayed(PageRepository.UserManagementPageMobilenumber);
	        ElementDisplayed(PageRepository.UserManagementPageEmailId);
	        ElementDisplayed(PageRepository.UserManagementPageRole);
	        ElementDisplayed(PageRepository.UserManagementPageIsActive);
	        ElementDisplayed(PageRepository.UserManagementPageSearch);
	        ElementDisplayed(UserManagement_Locators.UserManagementPageAddUser);

	        return true; 
	}
	public boolean FetchTableHeaderName() throws InterruptedException {
		List<WebElement> tds = driver.findElements(PageRepository.tableHeaderName);

    	for(WebElement td : tds){
    	 String name = td.getText();
    	 System.out.println(name);
    	}
    	ElementDisplayed(PageRepository.ActivateGreenStatus);
		ElementDisplayed(PageRepository.tableHeaderName);
		return true;
    }
	public boolean UsernameInput(String username) throws InterruptedException 
	{
		input(UserManagement_Locators.UserManagementPageUsername,username);

		return true;
	}
	public boolean UserManagementSearchBtn() throws InterruptedException 
	{
		click(PageRepository.UserManagementPageSearch);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
		Common.fluentWait("Previous button", PageRepository.UserPrevious);
		return true;
	}
	
	public boolean UserManagementInvalidSearchMsg() throws InterruptedException 
	{
		click(PageRepository.UserManagementInvalidSearchMsg);
		return true;
	}

	
	/*public boolean isIsActiveCheckboxSelectedOrNot() {
		driver.findElement(UserManagement_Locators.UserManagementPageIsActive).isSelected();
		 return true;
	}*/
	
	public boolean isIsActiveCheckboxSelectedOrNot(WebDriver driver) {
        try {
            driver.findElement(UserManagement_Locators.UserManagementPageIsActive);
            return true;
        } catch (Exception e) {
            System.out.println("Error checking if element is selected: " + e.getMessage());
            return false;
        }
    }

	public boolean DisplayUserManagementPreviousBtn() throws InterruptedException {
		Common.fluentWait("UserManagementPreviousBtn", PageRepository.UserManagementPreviousBtn);
        ElementDisplayed(PageRepository.UserManagementPreviousBtn);
        return true;

    }
	
	public boolean DisplayUserManagementFirstPageBtn() throws InterruptedException {
		Common.fluentWait("UserManagementFirstPageBtn", PageRepository.UserManagementFirstPageBtn);
        ElementDisplayed(PageRepository.UserManagementFirstPageBtn);
        return true;

    }
	public boolean DisplayUserManagementSecondPageBtn() throws InterruptedException {
		Common.fluentWait("UserManagementSecondPageBtn", PageRepository.UserManagementSecondPageBtn);
        ElementDisplayed(PageRepository.UserManagementSecondPageBtn);
        return true;

    }
	
	public boolean DisplayUserManagementNextBtn() throws InterruptedException {
		Common.fluentWait("UserManagementNextBtn", PageRepository.UserManagementNextBtn);
        ElementDisplayed(PageRepository.UserManagementNextBtn);
        return true;

    }
	
	public boolean DisplayUserManagementLastArrowBtn() throws InterruptedException {
		Common.fluentWait("UserManagementNextArrowBtn", PageRepository.UserManagementNextArrowBtn);
        ElementDisplayed(PageRepository.UserManagementNextArrowBtn);
        //Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
        return true;
    }
	
	public boolean ClickUserManagementLastArrowBtn() throws InterruptedException {
        Common.fluentWait("UserManagementNextArrowBtn", PageRepository.UserManagementNextArrowBtn);
		ElementDisplayed(PageRepository.UserManagementNextArrowBtn);
        //Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
        return true;

    }
	
	public boolean ClickUserManagementSecondPageBtn() throws InterruptedException 
	{
		Common.fluentWait("UserManagementSecondPageBtn", PageRepository.UserManagementSecondPageBtn);
		click(PageRepository.UserManagementSecondPageBtn);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
		ExtentTestManager.getTest().log(Status.PASS, "Click on the '2' button in pagination showing under active users grid");
		return true;
	}
	
	public boolean ClickUserManagementPreviousArrowBtn() throws InterruptedException 
	{
		Common.fluentWait("UserManagementPreviousArrowBtn", PageRepository.UserManagementPreviousArrowBtn);
		click(PageRepository.UserManagementPreviousArrowBtn);
		Common.fluentWait("UserManagementFirstPageBtn", PageRepository.UserManagementFirstPageBtn);
		Thread.sleep(6000);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
		return true;
	}
	
	public boolean ClickUserManagementPageAddUserBtn() throws InterruptedException 
	{
		Common.fluentWait("UserManagementPageAddUser", UserManagement_Locators.UserManagementPageAddUser);
		click(UserManagement_Locators.UserManagementPageAddUser);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		Common.fluentWait("OrganisationFirstOption", PageRepository.HeadOfficeOption);
		Thread.sleep(10000);
		Common.fluentWait("AddNewUserOrganizationType", PageRepository.AddNewUserOrganizationType);
		click(PageRepository.AddNewUserOrganizationType);
		Common.fluentWait("OrganisationFirstOption", PageRepository.HeadOfficeOption);
		Common.fluentWait("AddNewUserOrganizationType", PageRepository.AddNewUserOrganizationType);
		click(PageRepository.AddNewUserOrganizationType);
		return true;
	}
	public boolean AddNewUserPageElementsDisplayed() throws InterruptedException {
	  
	    
	        ElementDisplayed(PageRepository.AddNewUserNameBtn);
	        ElementDisplayed(PageRepository.AddNewUserEmailBtn);
	        ElementDisplayed(PageRepository.AddNewUserPhoneNumberBtn);
	        ElementDisplayed(PageRepository.AddNewUserRole);
	        ElementDisplayed(PageRepository.AddNewUserOrganizationType);
	        ElementDisplayed(PageRepository.AddNewUserCloseBtn);
	        ElementDisplayed(PageRepository.AddNewUserSubmitBtn);
	        ExtentTestManager.getTest().log(Status.INFO, " All elements of Add new user page displayed");
	        return true;
	}

	public boolean EnterAddNewUserName(String AddNewUserNameBtn) throws InterruptedException 
	{
	
		input(PageRepository.AddNewUserNameBtn,AddNewUserNameBtn );
		return true;
	}
	   
	public boolean EnterAddNewUserEmail(String AddNewUserEmailBtn) throws InterruptedException 
	{
		
		input(PageRepository.AddNewUserEmailBtn, AddNewUserEmailBtn);
		
		return true;
	}
	
	public boolean EnterAddNewUserPhoneNumber(String AddNewUserPhoneNumberBtn) throws InterruptedException 
	{
		
		input(PageRepository.AddNewUserPhoneNumberBtn , AddNewUserPhoneNumberBtn);
		
		return true;
	}
	
	public boolean SelectRoleDropdown(String value) throws InterruptedException {
		SelectActiveDropdown(PageRepository.AddNewUserRole, value);
		
		return true;
	}
	public boolean SelectOrganizationTypeDropdown(String value) throws InterruptedException {
		SelectActiveDropdown(PageRepository.AddNewUserOrganizationType, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		
		return true;
	}
	
	public boolean SelectOrganizationTypeDropdown2(String value ) throws InterruptedException 
	{
		
		SelectActiveDropdown( PageRepository.AddNewUserOrganizationType, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		return true;
	}
	public boolean SelectOrganizationTypeDropdown3(String value ) throws InterruptedException 
	{
		
		SelectActiveDropdown(PageRepository.AddNewUserOrganizationType, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		return true;
	}
	public boolean SelectOrganizationTypeDropdown4(String value ) throws InterruptedException 
	{
		
		SelectActiveDropdown(PageRepository.AddNewUserOrganizationType, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		return true;
	}
	public boolean SelectHeadOfficeDropdown(String value ) throws InterruptedException 
	{
		
		SelectActiveDropdown(PageRepository.HeadOfficeDropdown, value);
		Thread.sleep(2000);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		return true;
	}
	
	public boolean DisplayAddNewUserHeadofficeBtn() throws InterruptedException {
	       ElementDisplayed(PageRepository.AddNewUserHeadofficeBtn);
	       
	       return true;
	}
	
	public boolean DisplayAddNewUserZoneCOBtn() throws InterruptedException {
	 
	    
	        ElementDisplayed(PageRepository.AddNewUserZoneCOBtn);
	        return true;
	}
	
	public boolean DisplayAddNewUserRegionBtn() throws InterruptedException 
	{
		
		   ElementDisplayed(PageRepository.AddNewUserRegionBtn);
		   
		   return true;
	}
	
	public boolean DisplayAddNewUserBranchBtn() throws InterruptedException 
	{
		
		   ElementDisplayed(PageRepository.AddNewUserBranchOption);
		 
		   return true;
	}
	
	public boolean ClickAddNewUserSubmitBtn() throws InterruptedException 
	{ 
              
		click(PageRepository.AddNewUserSubmitBtn);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
        return true;
	}
	
	public boolean ClearAddNewUserElements() throws InterruptedException 
	{
		clear(PageRepository.AddNewUserNameBtn);
		clear(PageRepository.AddNewUserEmailBtn);
		clear(PageRepository.AddNewUserPhoneNumberBtn);
		clear(PageRepository.AddNewUserRole);
		clear(PageRepository.AddNewUserOrganizationType);
		return true;
	}
	
	
	public boolean ErrormessageforAdduserPage() throws InterruptedException {
	    
	        ElementDisplayed(PageRepository.NameErrorMessage);
	        ElementDisplayed(PageRepository.EmailErrorMessage);
	        ElementDisplayed(PageRepository.phonenumberErrorMessage);
	        ElementDisplayed(PageRepository.roleErrorMessage);
	        ElementDisplayed(PageRepository.OrganizationTypeErrorMessage);
	        
	        return true;
	}

    public boolean ErrormessageforNameField() throws InterruptedException {
           ElementDisplayed(PageRepository.NameErrorMessage);
           return true;
    }
    
    public boolean ClickAddNewUserCloseBtn() throws InterruptedException 
	{
    	Common.fluentWait("AddNewUserCloseBtn",PageRepository.AddNewUserCloseBtn);
    	Common.fluentWait("AddNewUserCloseBtn",PageRepository.UserManagementNextBtn);
    	click(PageRepository.AddNewUserCloseBtn);
    	Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
    	ExtentTestManager.getTest().log(Status.INFO, " Close button is clicked");
		return true;
	}
    public boolean ErrormessageforEmailField() throws InterruptedException {
        ElementDisplayed(PageRepository.EmailErrorMessage);
        return true;
    }
    public boolean ErrormessageforPhoneNumberField() throws InterruptedException {
        ElementDisplayed(PageRepository.phonenumberErrorMessage);
        return true;
    }
    public boolean ErrormessageforRoleField() throws InterruptedException {
        ElementDisplayed(PageRepository.roleErrorMessage);
        return true;
    }
    public boolean ErrormessageforOrganizationTypeField() throws InterruptedException {
        ElementDisplayed(PageRepository.OrganizationTypeErrorMessage);
        return true;
    }
    public boolean ErrormessageforInvalidEmailId() throws InterruptedException {
        ElementDisplayed(PageRepository.InvalidEmailId);
        return true;
    }
    public boolean ErrormessageforInvalidPhoneNumber() throws InterruptedException {
        ElementDisplayed(PageRepository.InvalidPhoneNumber);
        return true;
    }
    public boolean ErrormessageforInvalidName() throws InterruptedException {
        ElementDisplayed(PageRepository.InvalidName);
        return true;
    }
    public boolean ErrormessageforEmptyHeadoffice() throws InterruptedException {
        ElementDisplayed(PageRepository.EmptyHeadofficeErrorMsg);
        return true;
    }
   
    public boolean SuccessMessage() throws InterruptedException 
   	{
    	ElementDisplayed(PageRepository.SuccessMessage);
   		return true;
   	}
    public String FetchTableNameValue() throws InterruptedException {
        String Name = driver.findElement(PageRepository.TableNameValue).getText(); // Assign value to 'Name'
        System.out.println(Name); // Print value to console, if necessary
        ExtentTestManager.getTest().log(Status.INFO, "Fetch table header value");
        return Name; // Return the fetched value
    }
    public boolean ErrorMessageExistUserCreation() throws InterruptedException {
        ElementDisplayed(PageRepository.ErrorMessageExistUserCreation);
        return true;
    }
    
    public String GetUserNameandPassowrd() {
    	String message = driver.findElement(PageRepository.SuccessMessage).getText();
    	ExtentTestManager.getTest().log(Status.INFO, "Getting username and password");
    	return message;
    }
    public boolean ErrorMessageForZoneCO() throws InterruptedException {
        ElementDisplayed(PageRepository.EmptyZoneCOErrorMsg);
        return true;
    }
    public boolean ErrorMessageForRegion() throws InterruptedException {
        ElementDisplayed(PageRepository.EmptyRegionErrorMsg);
        return true;
    }
    public boolean ErrorMessageForBranch() throws InterruptedException {
        ElementDisplayed(PageRepository.EmptyBranchErrorMsg);
        return true;
    }
    public boolean SelectAddNewUserZoneCO(String value) throws InterruptedException {
    	click(PageRepository.AddNewUserZoneCO);
    	Common.fluentWait("Ahmedabad", PageRepository.Ahmedabad);
    	click(PageRepository.AddNewUserZoneCO);
		SelectActiveDropdown(PageRepository.AddNewUserZoneCO, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		Thread.sleep(5000);
		return true;
	}
    public boolean SelectAddNewUserRegion(String value) throws InterruptedException {
    	click(PageRepository.AddNewUserRegion);
    	Common.fluentWait("Indore", PageRepository.Indore);
    	click(PageRepository.AddNewUserRegion);
		SelectActiveDropdown(PageRepository.AddNewUserRegion, value);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
		Thread.sleep(5000);

		return true;
	}
    public boolean SelectAddNewUserBranch(String value) throws InterruptedException {
    	click(PageRepository.AddNewUserBranch);
    	Common.fluentWait("Indore I", PageRepository.IndoreI);
    	click(PageRepository.AddNewUserBranch);
  		SelectActiveDropdown(PageRepository.AddNewUserBranch, value);
  		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.AddUserSpinner);
        //Thread.sleep(10000);
  		return true;
  	}
    public boolean ClickLogoutOption() throws InterruptedException {
        click(PageRepository.userDropDown);
        click(PageRepository.L_signout);
        return true;
    }
    public boolean EnterLoginPageCredential(String username, String password ) throws InterruptedException 
   	{
   	
   		input(PageRepository.username,username );
   		input(PageRepository.password,password);
   		return true;
   	}
    public boolean ClickLoginBtn() throws InterruptedException {
        click(PageRepository.SignIn);
        return true;
    }
    public boolean ModuleSelectionEltDisplayed() throws InterruptedException 
	{
	
		ElementDisplayed(UserManagement_Locators.GoCollection);
		ElementDisplayed(PageRepository.RecoverySectionSetasDefault);
		ElementDisplayed(PageRepository.UserName);
		ElementDisplayed(PageRepository.UserId);
		ElementDisplayed(PageRepository.UserName);
		return true;
	}
    
    public boolean SetasDefaulNotSelected() {
        return !driver.findElement(PageRepository.SetasDefault).isSelected();
     
    }
    public String FetchUserName() throws InterruptedException {
        String Name = driver.findElement(PageRepository.UserName).getText(); 
        System.out.println(Name);
        return Name; 
    }
    public String UserId() throws InterruptedException {
        String Name = driver.findElement(PageRepository.UserId).getText(); 
        System.out.println(Name);
        return Name; 
    }
    public boolean SelectUserManagementPageRole(String value) throws InterruptedException {
		SelectActiveDropdown(PageRepository.UserManagementPageRole, value);
		return true;
	}
    
    public boolean ClearUserManagementPageRole() throws InterruptedException {
		click(PageRepository.ClearUserManagementPageRole);

		return true;
	}
    public boolean ClickUserManagementPageSearchBtn() throws InterruptedException {
		click(PageRepository.UserManagementPageSearch);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
		Common.fluentWait("Previous button", PageRepository.UserPrevious);
		Thread.sleep(6000);
		return true;
	}
    
    public boolean FetchHeaderRole() throws InterruptedException {
    	List<WebElement> tds = driver.findElements(PageRepository.tableElts);

    	for(WebElement td : tds){
    	 String name = td.getText();
    	 System.out.println(name);
    	}
    	ElementDisplayed(PageRepository.tableRoleHeader);

		return true;
	}
    public boolean EnterUsernameInUNPage(String UN) throws InterruptedException 
	{
    	input(UserManagement_Locators.UserManagementPageUsername,UN);
		return true;
	}
    
    public boolean ClearUsernameInUNPage() throws InterruptedException 
	{
        clear(UserManagement_Locators.UserManagementPageUsername);
       
		return true;
	}
    public boolean FetchTableUsername() throws InterruptedException {
    	List<WebElement> tds = driver.findElements(PageRepository.tableUsernameElts);

    	for(WebElement td : tds){
    	 String name = td.getText();
    	 System.out.println(name);
    	}
    	ElementDisplayed(PageRepository.ActivateGreenStatus);
    	ElementDisplayed(PageRepository.tableHeaderName);
    	ElementDisplayed(PageRepository.tableUsername);

		return true;
	}
    public boolean StepsOnDeactivateOptn() throws InterruptedException {
        click(PageRepository.UserManagementPageThreeDotBtn);
        Thread.sleep(1000);
        ExtentTestManager.getTest().log(Status.PASS, "2. Click on three-dot menu in ACTION column");
        click(PageRepository.activateDeactivateBtn);
        ExtentTestManager.getTest().log(Status.PASS, "3. Select Activate/De-activate");
        ElementDisplayed(PageRepository.SuccessMessageUserCreation);
        ExtentTestManager.getTest().log(Status.INFO, "SuccessMessage");
        Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
        Common.fluentWait("Previous button", PageRepository.UserPrevious);
        Thread.sleep(5000);
        return true;
    }
    public boolean DisplayDeactivateRedStatus() throws InterruptedException {
    	 click(PageRepository.UserManagementPageActiveUnCheck);
    	 click(PageRepository.UserManagementPageSearch);
         Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
         Thread.sleep(2000);
    	 ElementDisplayed(PageRepository.DeactivateRedStatus);
    	 ExtentTestManager.getTest().log(Status.PASS, "Red Status is displayed");
		 return true;
	}
    public boolean StepsOnActivateOptn() throws InterruptedException {
    	 click(PageRepository.UserManagementPageThreeDotBtn);
         Thread.sleep(1000);
         ExtentTestManager.getTest().log(Status.PASS, "2. Click on three-dot menu in ACTION column");
         click(PageRepository.activateDeactivateBtn);
         ExtentTestManager.getTest().log(Status.PASS, "3. Select Activate/De-activate");
         ElementDisplayed(PageRepository.SuccessMessageUserCreation);
         ExtentTestManager.getTest().log(Status.INFO, "SuccessMessage");
         Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
         Common.fluentWait("Previous button", PageRepository.UserPrevious);
         Thread.sleep(5000);
         return true;
    }
    public boolean DisplayActivateGreenStatus() throws InterruptedException {
    	 click(PageRepository.UserManagementPageActiveCheck);
    	 click(PageRepository.UserManagementPageSearch);
    	 Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
    	 ElementDisplayed(PageRepository.ActivateGreenStatus);
    	 ExtentTestManager.getTest().log(Status.PASS, "Green Status is displayed");
		 return true;
	}
    public boolean ResetPassword() throws InterruptedException {
    	click(PageRepository.UserManagementPageThreeDotBtn);
    	ExtentTestManager.getTest().log(Status.PASS, "2. Click on three-dot menu in ACTION column");
    	click(PageRepository.ResetPassword);
    	Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
    	ElementDisplayed(PageRepository.ResetPasswordMsg);
    	Common.fluentWait("ResetPasswordMsg", PageRepository.ResetPasswordMsg);
    	ExtentTestManager.getTest().log(Status.PASS, "3. Select Reset Password");
    	Thread.sleep(2000);
		return true;
	}
    public boolean EditUserDetails() throws InterruptedException {
    	click(PageRepository.UserManagementPageThreeDotBtn);
    	ExtentTestManager.getTest().log(Status.PASS, "2. Click on three-dot menu in ACTION column");
    	click(PageRepository.EditBtn);
    	ExtentTestManager.getTest().log(Status.PASS, "3. Select Edit");
    	Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepository.UserManagementSpinner);
    	Thread.sleep(2000);
    	ElementDisplayed(PageRepository.EditPageName);
    	ElementDisplayed(PageRepository.EditPageEmail);
    	ElementDisplayed(PageRepository.EditPagePhNumber);
    	ElementDisplayed(PageRepository.EditPageRole);
    	ElementDisplayed(PageRepository.EditPageOrganizationType);
    	ElementDisplayed(PageRepository.EditPageHeadoffice);
    	ElementDisplayed(PageRepository.CloseBtn);
    	ElementDisplayed(PageRepository.UpdateBtn);

		return true;
	}
    
    public void AddNewUserSpinner() {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//*[contains(@class,card-header)]//*[@class='spinner']) [1]"))); 
	}

}




