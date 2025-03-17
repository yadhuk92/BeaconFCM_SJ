package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreCollectionOfficerConfiguration {
	
	public static By SecurityManagementmainmenu = By.xpath("//span[text()='Security Management']");
	public static By RoleManagementsubmenu = By.xpath("//a[@title='Role Management']");
	public static By AddNewRolebutton = By.xpath("//button[text()='Add New Role']");
	public static By CollectionOfficerConfigcheckbox = By.xpath("//input[@type='checkbox' and @id=61]");
	public static By closebutton = By.xpath("//a[@role='button']");
	public static By Configurationsmainmenu = By.xpath("//span[text()='Configurations']");
	public static By CollectionOfficerConfigsubmenu = By.xpath("//a[@title='Collection Officer Config']");
	public static By ZoneCOfield = By.xpath("//label[text()='Zone/CO']//following-sibling::div");
	public static By Regionfield = By.xpath("//label[text()='Region']//following-sibling::div");
	public static By Branchfield = By.xpath("//label[text()='Branch']//following-sibling::div");
	public static By Searchbutton = By.xpath("//button[@type='submit' and text()='Search']");
	public static By Resetbutton = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By SaveConfigurationbutton = By.xpath("//button[@type='submit' and text()='Save Configuration']");
	public static By  DownloadinExcelbutton = By.xpath("//button[@type='submit' and text()=' Download in Excel']");
	public static By GridRows = By.xpath("//tbody//tr");
	public static By GridColumns = By.xpath("//tbody//tr//td");
	public static By GridColumnsrelative = By.xpath(".//td");
	public static By GridNextbutton = By.xpath("//span[text()='Next']");
	public static By isActiveCheckbox = By.xpath("//tbody//tr//td[position()=6]");
	public static By successmsg = By.xpath("//p[text()='Collection officers configured Successfully.']");
	public static By popup_yes_button = By.xpath("//span[text()='Yes']");
	public static By myDeskMainMenu = By.xpath("//span[text()='My Desk']");
	public static By dashboardSubMenu = By.xpath("//a[@title='Dashboard']");
	public static By unassignedAccountsTile = By.xpath("//div[@class='card-wrapper']//div[text()='Unassigned Accounts']//following::p[text()='SMA']");
	public static By allocateToDropdown = By.xpath("//label[text()='Allocate To']//following-sibling::div");
	public static By allocateToDropdownvalue = By.xpath("//li[@role='option' and @aria-label='>BCO']");
	public static By selectBCODropdown = By.xpath("//label[text()='Select BCO']//following-sibling::div");
	public static By username = By.xpath("//div[@class='user-details ']//span[@class='name']");
	public static By BranchAttentionRequiredAccounts  = By.xpath("(//div[@class='card-wrapper']//div[@class='tile-container'])[1]//p[text()='SMA']");
	public static By searchbutton = By.xpath("//button[@type='submit' and text()='Search']");
	public static By AssignReassignTodropdown = By.xpath("//label[text()='Assign / Reassign To']//following-sibling::div");
	public static By AssignReassignTo_Present_values = By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li[@role='option']");
	public static By  DownloadinExcelsuccessmsg = By.xpath("//p[text()='File downloaded successfully']");
	public static By  Saveconfigurationwarningmsg = By.xpath("//p[text()='Select atleast one record to process.']");
	public static By  dashboardicon = By.xpath("//span[@class='material-symbols-outlined' and contains(text(),'Dashboard')]");
	public static By  BCOvalues = By.xpath("(//div[@class='rz-dropdown-panel rz-popup']//div[@class='rz-dropdown-items-wrapper'])[2]//ul//li");
	public static By Region_value(String value) {
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']");
    }
	public static By Branch_BCO_value(String value) {
        return By.xpath("//li[@role='option' and @aria-label='>" + value + "']"); 
    }
	
	public static By AssignReassignTo_value(String value) {
        return By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li[@role='option' and @aria-label='>" + value + "']"); 
    }
	
}
