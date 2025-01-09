package com.Page_Repository;

import org.openqa.selenium.By;

public class CollectionAgencyRoleManagement_Locators {
	
	public By SecurityManagement= By.xpath("//span[text()= 'Security Management']");
	public By SecurityManagementMenu= By.xpath("//span[text()='Security Management']");
	public By RoleManagementOption= By.xpath("//a[text()= 'Role Management']");
	public By SearchBtn= By.xpath("//button[@type= 'submit']");
	public By RoleNameSearch= By.xpath("//input[@type= 'search']");
	public By AddNewRoleBtn= By.xpath("//button[@class= 'addbutton btn btn-primary rounded-pill']");
	public By checkbox= By.xpath("(//input[@type= 'checkbox']) [1]");
	public By AllCheckbox= By.xpath("//input[@type='checkbox']");
	public By RolePermissionSave= By.xpath("//button[text()= 'Save']");
	public By RoleNameErrorMsg= By.xpath("//p[.= 'Enter Role Name']");
	public By CancelRole= By.xpath("//button[text()= 'Cancel']");
	public By RoleName= By.xpath("//input[@name= 'RoleName']");
	public By RoleNameSuccessMsg = By.xpath("//p[.= 'Record Saved Successfully']");
	public By AddedRoleName = By.xpath("//span[@title='Role5']");
	public By ActionColumn= By.xpath("(//span[@class='rz-column-title']) [2]");
	public By ThreedotButton= By.xpath("(//button[@class='btn btn-light morebtn']) [1]");
	public By EditBtn= By.xpath("(//button[@class='dropdown-item']) [1]");
	public By DispositionCheckbox= By.xpath("(//input[@type= 'checkbox']) [5]");
	public By UpdateRoleSuccessMsg = By.xpath("//p[.= 'Record Updated Successfully']");
	public By NoRecordsMsg = By.xpath("//span[text()= 'No records to display.']");
	public By closeIcon = By.xpath("//span[@class= 'rzi rzi-times']");
	public By tableColumn = By.xpath("//span[@title= 'Gordon']");
	public By waitSpinner = By.xpath("(//div[@class='spinner']) [1]");
	public By NextBtn = By.xpath("//li/span[text()='Next']");
	public By CloseBtn = By.xpath("//span[@class='rzi rzi-times']");
	public By AgentAccountAllocationCheckbox = By.xpath("//input[@type='checkbox' and @id='20']");
	public By AddNewAgentCheckbox = By.xpath("//input[@type='checkbox' and @id='16']");
	public By AgentListCheckbox = By.xpath("//input[@type='checkbox' and @id='17']");
	public By UploadInvoiceCheckbox = By.xpath("//input[@type='checkbox' and @id='26']");
	public By DispositionFunctionalityCheckbox = By.xpath("//input[@type='checkbox' and @id='18']");
	public By AllocationSummaryCheckbox = By.xpath("//input[@type='checkbox' and @id='39']");
	public By RoleManagementCheckbox = By.xpath("//input[@type='checkbox' and @id='19']");
	public By SupportRequestCheckbox = By.xpath("//input[@type='checkbox' and @id='48']");
	public By SupportRequestStatusCheckbox = By.xpath("//input[@type='checkbox' and @id='46']");
	public By FirstValueInRoleSearchGrid = By.xpath("(//span[@class='rz-cell-data'])[1]");
	
	
	public static By RoleSearchResultCheck(String RoleName) {
	    String xpathExpression;

	    xpathExpression = "//span[@class='rz-cell-data' and @title='" + RoleName + "']";

	    return By.xpath(xpathExpression);
	}
	
	public static By RoleSearchFirstValue(String RoleName) {
	    String xpathExpression;

	    xpathExpression = "//span[@class='rz-cell-data' and @title='"+RoleName+"']";

	    return By.xpath(xpathExpression);
	}

}
