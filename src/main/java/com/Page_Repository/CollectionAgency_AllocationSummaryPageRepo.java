package com.Page_Repository;

import org.openqa.selenium.By;


public class CollectionAgency_AllocationSummaryPageRepo {
	
	public static By dashboardMenu = By.xpath("//span[@class='text nav-text' and text()='Dash board']");
	public static By allocationSummaryLink = By.xpath("//a[@class='dropdown-item' and @href='/Collection/Allocationsummary']");
	public static By allocationSummaryLabel = By.xpath("//div[@class='dvPageheadingCaption' and contains(text(),'Allocation Summary')]");
	public static By grid = By.xpath("//div[@class='card-wrapper']");
	public static By dateColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Date']");
	public static By totalAccountsReceivedColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Total Account Received']");
	public static By allocatedToAgentsColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Allocated To Agents']");
	public static By gridValues = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody");
	
	
	
	public static By Dropdownvalues(String value) { 
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
    }

}



