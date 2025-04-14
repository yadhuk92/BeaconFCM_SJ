package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsPlaceholderManagementRepo {
	
	public static By alertsAndNotificationsMenu = By.xpath("//nav[@class='sidebar ']");
	public static By AlertsandNotificationsmainmenu = By.xpath("//span[text()='Alerts and Notifications']");
	public static By PlaceholderManagementsubmenu = By.xpath("//a[@title='Placeholder Management']");
	public static By paginationControlsLocator = By.xpath("//nav[@aria-label='Page navigation example']");
	public static By nextPageButton = By.xpath("//span[@class='page-link' and text()='Next']");
	public static By recordTable = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']");
	public static By previousPageButton = By.xpath("//span[@class='page-link' and text()='Previous']");
	public static By recordTablecontent = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr[@class='rz-datatable-even  ']");
	public static By searchField = By.xpath("//input[@placeholder='Placeholder Name']");
	public static By searchButton = By.xpath("//button[@type='button' and text()='Search']");
	
	public static By Region_value(String value) {
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
    }

}
