package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCentre_Locators {
	public static By GoCollection = By.xpath("//button[text()='Go Collection']");
	public static By SecurityManagementMenu = By.xpath("//span[text()= 'Security Management']");
	public static By UserManagementMenu = By.xpath("//a[@href='Admin/UserManagement']");
	public static By UserManagementExecutiveID = By
			.xpath("//label[text()='Executive ID']//following-sibling::input[@type='search']");
	public static By UserManagementaddExecutiveID = By
			.xpath("//label[text()='Executive ID']//following-sibling::input[@name='Name']");
	public static By UserManagementPageUsername = By.xpath("(//input[@class='searchinput form-control'])[1]");
	public static By UserManagementPageRoleType = By.xpath("(//span[normalize-space()=CallCentreRole]");
	public static By UserManagementPageName = By.xpath("(//input[@class='searchinput form-control']) [2]");
	public static By UserManagementPageMobilenumber = By
			.xpath("//input[@class='rz-textbox valid searchinput form-control']");
	public static By UserManagementPageEmailId = By.xpath("(//input[@class='searchinput form-control']) [3]");
	public static By UserManagementPageRole = By.xpath("//div[@class='rz-dropdown valid rz-clear form-control']");
	public static By UserManagementPageIsActive = By.xpath("//span[@class='rz-chkbox-icon rzi rzi-check']");
	public static By UserManagementPageSearch = By.xpath("//button[text()='Search']");
	public static By UserManagementPageAddUser = By.xpath("//a[text()='Add User']");
	public static By UserManagementPageGrid = By
			.xpath("//div[@class=\"rz-data-grid rz-has-paginator rz-datatable  rz-datatable-scrollable \"]");
	public By UserManagementPreviousBtn = By.xpath("//span[@class='page-link' and text()='Previous']");
	public By UserManagementFirstPageBtn = By.xpath("//li[@class='page-item  active']");
	public By UserManagementSecondPageBtn = By.xpath("//span[@class='page-link' and text()='2']");
	public By UserManagementThirdPageBtn = By.xpath("//li/span[text()='3']");
	public By UserManagementNextBtn = By.xpath("(//span[@class='page-link']) [4]");
	public By UserManagementNextArrowBtn = By.xpath("//li/span[text()='>>']");
	public By UserManagementPreviousArrowBtn = By.xpath("//li/span[text()='<<']");
	public By AddNewUserNameBtn = By.xpath("//label[.='Name']/following::input[1]");
	public By AddNewUserEmailBtn = By.xpath("//input[@name='Email']");
	public By AddNewUserPhoneNumberBtn = By.xpath("//input[@name='Mobile']");
	public By AddNewUserRole = By
			.xpath("(//div[@class='rz-dropdown valid rz-clear form-control mandatory-color']) [1]");
	public By AddNewUserOrganizationType = By.xpath("//label[contains(.,'Organization Type')]/following-sibling::div");
	public By AddNewUserCloseBtn = By.xpath("//button[text()='Close']");
	public static By AddNewUserSubmitBtn = By.xpath("//button[text()='Submit']");
	public By AddNewUserHeadofficeBtn = By.xpath("//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder']");
	public By AddNewUserZoneCOBtn = By.xpath("//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder']");
	public By AddNewUserRegionBtn = By.xpath("(//label[contains(.,'Region')]/following-sibling::div)[2]");
	public By AddNewUserBranchOption = By.xpath("(//label[contains(.,'Branch')]/following-sibling::div) [2]");
	public By ExecutiveIDErrorMessage = By.xpath("//div[normalize-space()=\"Executive id is required\"]");
	public By NameErrorMessage = By.xpath("//div[normalize-space()=\"Name is required\"]");
	public By EmailErrorMessage = By.xpath("//div[normalize-space()=\"Email is required\"]");
	public By phonenumberErrorMessage = By.xpath("//div[normalize-space()=\"Phone number is required\"]");
	public By roleErrorMessage = By.xpath("//div[normalize-space()=\"Role is required\"]");
	// public By OrganizationTypeErrorMessage= By.xpath("//*[@class='my-form-group
	// form-group']//div[.='Organization type is required']");
	public By HeadOfficeDropdown = By.xpath("(//label[contains(.,'Head office')]/following-sibling::div) [2]");
	public By InvalidEmailId = By.xpath("//*[@class='rz-notification']//p[.='Invalid Email Id']");
	public By InvalidPhoneNumber = By.xpath("//*[@class='rz-notification']//p[.='Invalid Mobile Number']");
	public By InvalidName = By.xpath("//*[@class='rz-notification']//p[.='Invalid Name']");
	public By EmptyHeadofficeErrorMsg = By.xpath("//*[@class='rz-growl-message']//p[.='Head office is Required']");
	public By UserManagementPageThreeDotBtn = By.xpath("(//button[@class='btn btn-light morebtn']) [1]");
	public By EdituserPageEmailField = By.xpath("(//input[@class='form-control mandatory-color valid']) [2]");
	public By EdituserPagePhoneNumberField = By
			.xpath("//input[@class='rz-textbox valid form-control mandatory-color']");
	public By SuccessMessage = By
			.xpath("//strong[contains(text(), 'User created successfully . Present password for the user')]");
	public By SuccessMessageUserCreation = By.xpath("//span[.='Success ']/following-sibling::p[.='Status Changed']");
	public By TableNameValue = By.xpath("(//tr[@class='rz-datatable-even  ']//td[3])[1]//span");
	public By ErrorMessageExistUserCreation = By.xpath("//*[@class='rz-growl-message']//p[.='Already User Exist ']");
	public By EmptyZoneCOErrorMsg = By.xpath("//*[@class='rz-growl-message']//p[.='Zone/CO is Required']");
	public By EmptyRegionErrorMsg = By.xpath("//*[@class='rz-growl-message']//p[.='Region is Required']");
	public By EmptyBranchErrorMsg = By.xpath("//*[@class='rz-growl-message']//p[.='Branch is Required']");
	public By AddNewUserZoneCO = By.xpath("(//label[contains(.,'Zone/CO')]/following-sibling::div)[2]");
	public By AddNewUserRegion = By.xpath("(//label[contains(.,'Region')]/following-sibling::div)[2]");
	public By AddNewUserBranch = By.xpath("(//label[contains(.,'Branch')]/following-sibling::div)[2]");
	public By UserManagementInvalidSearchMsg = By.xpath("//span[.='No records to display.']");
	public By userDropDown = By.xpath("//button[@class='btn dropdown-toggle']");
	public By L_signout = By.xpath("//a[text()='Logout']");
	public By username = By.xpath("//input[@placeholder='User Name']");
	public By password = By.xpath("//input[@placeholder='Password']");
	public By SignIn = By.xpath("//button[text()='LOGIN']");
	public By SetasDefault = By.xpath("//div[@class='rz-chkbox-box']");
	public By GoRecoveryBtn = By.xpath("//button[text()='Go Recovery']");
	public By RecoverySectionSetasDefault = By.xpath("//div[@class='rz-chkbox rz-state-disabled']");
	public By UserId = By.xpath("(//span[@class='name']) [1]");
	public By UserName = By.xpath("//span[@class='emailuser']");
	public By tableHeaderName = By.xpath("//thead/tr");
	public By tableRoleHeader = By.xpath("(//span[@class = 'rz-cell-data']) [4]");
	public By tableUsername = By.xpath("//span[@title='IBU0001192']");
	public By activateDeactivateBtn = By.xpath("//button[text()='Activate/De-activate']");
	public By activateDeactivateSuccessMsg = By.xpath("//div[@class='rz-growl-message']//p[.='Status Changed']");
	public By DeactivateRedStatus = By.xpath("//i[@style='color:red']");
	public By ActivateGreenStatus = By.xpath("//i[@style='color:green']");
	public By ResetPasswordOptn = By.xpath("//button[text()='Reset Password']");
	public By UserManagementPageActiveUnCheck = By.xpath("//div[@class='rz-chkbox-box rz-state-active']");
	public By UserManagementPageActiveCheck = By.xpath("//div[@class='rz-chkbox-box']");
	public By ResetPassword = By.xpath("//button[text()='Reset Password']");
	public By ResetPasswordMsg = By.xpath("//strong[contains(text(), 'The password has been reset successfully')]");
	public By EditBtn = By.xpath("(//button[text()='Edit']) [1]");
	public By EditPageName = By.xpath("(//input[@class='form-control mandatory-color valid']) [1]");
	public By EditPageEmail = By.xpath("(//input[@class='form-control mandatory-color valid']) [2]");
	public By EditPagePhNumber = By.xpath("//input[@class='rz-textbox valid form-control mandatory-color']");
	public By EditPageRole = By.xpath("(//div[@class='rz-dropdown valid rz-clear form-control mandatory-color']) [1]");
	public By EditPageOrganizationType = By.xpath("(//label[@class='rz-dropdown-label rz-inputtext '])[2]");
	public By EditPageHeadoffice = By.xpath("(//label[@class='rz-dropdown-label rz-inputtext ']) [3]");
	public By CloseBtn = By.xpath("//button[text()='Close']");
	public By UpdateBtn = By.xpath("//button[text()='Update']");
	public By tableElts = By.xpath("//span[text()='Admin']/../../td");
	public By tableUsernameElts = By.xpath("//span[text()='Renuka']/../../td");
	public By ClearUserManagementPageRole = By.xpath("//i[@class='rz-dropdown-clear-icon rzi rzi-times']");
	public By AddUserSpinner = By.xpath("//*[contains(@class,'mycard-header')]//*[@class='spinner']");
	public static By UserMgmtSpinner = By.xpath("//div[@class=\"table-wrapper-inner\"]//div[@class=\"spinner\"]");
	public static By AddUserMgmtSpinner = By.xpath("//body/div[@id=\"app\"]/div[2]");
	public By UserManagementSpinner = By.xpath("(//*[contains(@class,card-header)]//*[@class='spinner']) [1]");
	public By loginSelectionSpinner = By.xpath("//div[@class='spinner']");
	public By RoleFirstOption = By.xpath("(//li[@aria-label='>!@']) [1]");
	public By OrganisationFirstOption = By
			.xpath("//div[@class=\"col-sm-12 col-md-4 col-lg-3 formcol\"]//label[text()=\"Organization Type\"]");
	public By HeadOfficeOption = By.xpath("//span[contains(.,'Head office')]");
	public By HeadOffice = By.xpath("(//label[contains(.,'Head office')]) [2]");
	public By ZoneCO = By.xpath("(//label[contains(.,'Zone/CO')]) [2]");
	public By Region = By.xpath("(//label[contains(.,'Region')]) [2]");
	public By Branch = By.xpath("(//label[contains(.,'Branch')]) [2]");
	public By UserPrevious = By.xpath("(//li[@class='page-item disabled ']) [1]");
	public By moduleSelectionSpinner = By.cssSelector("div[id='dvbody'] div[class='spinner']");
	public By IndoreI = By.xpath("//span[text()='Indore I']");
	public By Branchdropdown = By.xpath("(//label[text()='Branch']) [2]");
	public By Regiondropdown = By.xpath("(//label[text()='Region']) [3]");
	public By Indore = By.xpath("//span[text()='Indore']");
	public By ZoneCodropdown = By.xpath("(//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder']) [2]");
	public By Ahmedabad = By.xpath("//li/span[text()='Ahmedabad']");
	public By AhmedabadValue = By.xpath("//li/span[text()='Ahmedabad']");
	public By OrganizationType = By.xpath("(//label[@class='rz-dropdown-label rz-inputtext ']) [2]");
	public By IsActiveChecked = By.xpath("//span[@class='rz-chkbox-icon rzi rzi-check']");
	public By IsActiveNotChecked = By.xpath("//div[@class='rz-chkbox-box']");
	public By OrganizationTypeDDL = By
			.xpath("(//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder' and text()='Select'])[2]");
	public By RoleDDL = By
			.xpath("(//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder' and text()='Select'])[1]");
	public By RoleDDLSearchField = By
			.xpath("(//input[@class='rz-dropdown-filter rz-inputtext   ' and @type='text'])[2]");
	public By NewUserRoleType = By.xpath("//label[text()='Role']//following-sibling::div");
	public By RoleDropdownValues = By.xpath("//div[@class='rz-dropdown-panel rz-popup']/child::div[2]/ul/li/span");
	public By UserGridTable = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']");
	public By UserGridRow = By.xpath("//tbody/tr[1]/td[3]/span");

	public By RoleDDLSearchedValue(String RoleName) {
		String xpathExpression;
		xpathExpression = "//li[normalize-space(span)='" + RoleName + "']";
		return By.xpath(xpathExpression);
	}

}