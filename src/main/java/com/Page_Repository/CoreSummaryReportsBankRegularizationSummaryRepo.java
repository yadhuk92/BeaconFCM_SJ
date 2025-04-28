package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreSummaryReportsBankRegularizationSummaryRepo {
	
	public static By bankRegularizationSummaryLink = By.xpath("//a[@title='Bank Regularization Summary']");
	public static By branchsearchspace = By.xpath("//div[@class='rz-dropdown-panel rz-popup']//input[@class='rz-dropdown-filter rz-inputtext   ' and @type='text']");
	public static By searchButton = By.xpath("//button[@type='submit' and text()=' Search']");
	public static By gridvalues = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//td");
	public static By resetButton = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By errorMessage = By.xpath("//p[text()='Please select Branch.']");
	public static By AccountCategory = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=1]");
	public static By accountsReceived = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=2]");
	public static By totalOSAmountsReceived = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=3]");
	public static By accountsRegularized = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=4]");
	public static By totalOSAmountsRegularized = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=5]");
	public static By downloadButton = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[position()=7]");
	public static By downloadsuccessmessage = By.xpath("//p[text()='File downloaded successfully']");
	public static By Dropdownvalues(String value) { 
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
    }
	public static By Dropdownvaluesforbranch(String value) { 
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']");  
    }
	public static By DateDropdownvalues(String value) { 
        return By.xpath("(//div[@class='rz-datepicker rz-popup']//table[@class='rz-datepicker-calendar']//span[@class='rz-state-default' and text()=" + value + "])[2]"); 
      
    }
}
