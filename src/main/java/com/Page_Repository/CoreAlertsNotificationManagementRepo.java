package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsNotificationManagementRepo {
	
	public static By notificationManagementMenu = By.xpath("//a[@title='Notification Management']");
	public static By pagination = By.xpath("//nav[@aria-label='Page navigation example']");
	public static By notificationTypeField = By.xpath("//label[text()='Notification Type']//following-sibling::div");
	public static By smsOption = By.xpath("//li[@role='option' and @aria-label='>SMS']");
	public static By emailOption = By.xpath("//li[@role='option' and @aria-label='>EMAIL']");
	public static By categoryDropdown = By.xpath("//label[text()='Category']//following-sibling::div");
	public static By PromotionsOption = By.xpath("//li[@role='option' and @aria-label='>Promotions']");
	public static By NotificationOption = By.xpath("//li[@role='option' and @aria-label='>Notification']");
	public static By scheduleTypeField = By.xpath("//label[text()='Schedule Type']//following-sibling::div");
	public static By oneTimeOption = By.xpath("//li[@role='option' and @aria-label='>One Time']");
	public static By dailyOption = By.xpath("//li[@role='option' and @aria-label='>Daily']");
	public static By weeklyOption = By.xpath("//li[@role='option' and @aria-label='>Weekly']");
	public static By monthlyOption = By.xpath("//li[@role='option' and @aria-label='>Monthly']");
	public static By statusField = By.xpath("//label[text()='Status']//following-sibling::div");
	public static By activeOption = By.xpath("//li[@role='option' and @aria-label='>Active']");
	public static By inactiveOption = By.xpath("//li[@role='option' and @aria-label='>Inactive']");
	public static By scheduleTypeColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Schedule Type']");
	public static By templateNameColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Template Name']");
	public static By scheduleStartColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Schedule Start']");
	public static By scheduleStartColumndateformat = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=3]");
	public static By scheduleEndColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Schedule End']");
	public static By scheduleEndColumndateformat = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=4]");
	public static By timeColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Time']");
	public static By recurrenceColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Recurrence']");
	public static By dayOfWeekColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Day Of Week']");
	public static By dayOfMonthColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Day Of Month']");
	public static By statusColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Status']");
	public static By navigationSection = By.xpath("//tbody//tr[position()=1]//div[@class='morebtnmenu']");
	public static By editButton = By.xpath("//tbody//tr[position()=1]//div[@class='morebtnmenu']//button[@type='button' and text()='Edit']");
	public static By editInterface = By.xpath("//div[contains(text(),'Notification Configuration')]//following::div[@class='row']");
	public static By paginationControls = By.xpath("//nav[@aria-label='Page navigation example']//ul//li//span");
	public static By searchButton = By.xpath("//button[@type='button' and text()='Search']");
	public static By searchResults = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=1]");
	public static By crossButton = By.xpath("//label[text()='Schedule Type']//following-sibling::div//i");
	public static By configureButton = By.xpath("//button[@type='button' and text()='Configure']");
	public static By cancelButton = By.xpath("//input[@type='button' and @value='Cancel']");
	public static By templateNameDropdown = By.xpath("//label[text()='Template']//following-sibling::div");
	public static By viewTemplateButton = By.xpath("//input[@type='button' and @value='View Template']");
	
	public static By Dropdownvalues(String value) {
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']"); 
    }

}