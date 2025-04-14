package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsTemplateManagementRepo {
	
	public static By alertsAndNotificationssubMenus = By.xpath("//ul[@class='dropdown-menu show']");
	public static By TemplateManagementsubMenu = By.xpath("//a[@title='Template Management']");
	public static By notificationTypeDropdown = By.xpath("//label[text()='Notification Type']//following-sibling::div"); 
	public static By currentStatusField = By.xpath("//label[text()='Current Status']//following-sibling::div");
	public static By categoryField = By.xpath("//label[text()='Category']//following-sibling::div");
	public static By Dropdownvalues(String value) {
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']"); 
    }
	public static By filterButton = By.xpath("//button[@type='button' and text()='Search']");
	public static By templateResults = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr");
	public static By crossButton = By.xpath("//div[@title='Select Notification Type']//i[@class='rz-dropdown-clear-icon rzi rzi-times']");
	public static By columnName = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='Template Name']");
	public static By columnStatus = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='Status']");
	public static By columnLastModifiedDate = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='Last Modified Date']");
	public static By columnEdit = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='Edit']");
	public static By columnView = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='View']");
	public static By columnViewHistory = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th//span[text()='View History']");
	public static By nextPageButton = By.xpath("//span[@class='page-link' and text()='Next']");
	public static By tableRecords = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()>=1]");
	public static By addTemplateButton = By.xpath("//button[@type='button' and text()='Add Template']");
	public static By templatenotificationTypeDropdown = By.xpath("//div[@class='rz-dialog' and @role='dialog']//label[text()='Notification Type']//following-sibling::div");
	public static By templateInitialStatusDropdown = By.xpath("//div[@class='rz-dialog' and @role='dialog']//label[text()='Initial Status']//following-sibling::div");
	public static By templateCategoryDropdown = By.xpath("//div[@class='rz-dialog' and @role='dialog']//label[text()='Category']//following-sibling::div");
	public static By templateNameField = By.xpath("//div[@class='rz-dialog' and @role='dialog']//label[text()='Template Name']//following-sibling::input");
	public static By TemplateBodyField = By.xpath("//div[@class='rz-dialog' and @role='dialog']//div[@class='rz-html-editor-content']");
	public static By TemplateBodyFieldforsms = By.xpath("//label[text()='Template Body']//following-sibling::textarea");
	public static By templatesaveButton = By.xpath("//div[@class='rz-dialog' and @role='dialog']//button[text()='Save']");
	public static By templatecreationsuccessmsg = By.xpath("//p[text()='Template created successfully.']");
	public static By popupokbutton = By.xpath("//div[@class='rz-dialog-content']//button[@type='button']//span[text()='Ok']");
	public static By dropdownvaluesfortemplatecreation(String value) {
        return By.xpath("(//div[@class='rz-dropdown-items-wrapper' and @style='max-height: 200px;overflow-x: hidden'])[6]//li[@role='option' and @aria-label='>" + value + "']");    
    }
	public static By charCountIndicator = By.xpath("//label[text()='160/160 characters']");
	public static By subjectField = By.xpath("//div[@class='rz-dialog' and @role='dialog']//label[text()=' Subject']//following-sibling::textarea");
	public static By templateRecords = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()>=1]//td");
	public static By viewButton = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()=1]//td[position()=5]");
	public static By templateDetailNotificationType = By.xpath("//label[text()='Notification Type']//following-sibling::input");
	public static By templateDetailStatus = By.xpath("//label[text()='Status']//following-sibling::input");
	public static By templateDetailTemplateName = By.xpath("//label[text()='Template Name']//following-sibling::input");
	public static By templateDetailCategory = By.xpath("//label[text()='Category']//following-sibling::input");
	public static By viewdialogboxclosebutton = By.xpath("//span[@class='rzi rzi-times']");
	public static By editButton = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()=1]//td[position()=4]");
	public static By editInitialStatusdropdown = By.xpath("//div[@class='rz-dialog-content']//div[@title='Select Initial Status']");
	public static By editInitialStatusdropdownvalue(String value) {
        return By.xpath("(//div[@class='rz-dropdown-items-wrapper']//following-sibling::li[@role='option' and @aria-label='>" + value + "'])[2]");
        
    }
	public static By editreason = By.xpath("//label[text()='Reason']//following-sibling::textarea");
	public static By updatebutton = By.xpath("//button[@type='submit' and text()='Update']");
	public static By edittemplate = By.xpath("//div[@class='rz-dialog-content']");
	public static By modifiedsuccessmsg = By.xpath("//p[text()='Template modified successfully.']");
	public static By historyButton = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()=1]//td[position()=6]");
	public static By historytablefields = By.xpath("//div[@class='rz-dialog-content']//table[@class='rz-grid-table rz-grid-table-fixed']//tr//th");
	public static By historytabledata = By.xpath("//div[@class='rz-dialog-content']//table[@class='rz-grid-table rz-grid-table-fixed']//tr//td");
	public static By templatetable = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']");
	public static By templatetablerows = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()>=1]");
	public static By templatetablecolumns = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr[position()>=1]//td");
}
