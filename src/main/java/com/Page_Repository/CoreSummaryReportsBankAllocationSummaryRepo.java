package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreSummaryReportsBankAllocationSummaryRepo {
	
	public static By MyDesk = By.xpath("//span[text()='My Desk']");
	public static By Dashboard = By.xpath("//a[@title='Dashboard']");
	public static By UnassignedAccounts = By.xpath("//div[text()='Unassigned Accounts']//following::p[text()='SMA']");
	public static By Download = By.xpath("//button[@type='submit' and text()=' Download ']");
	public static By Reset = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By AssetCategory = By.xpath("//label[text()='Asset Category']//following-sibling::div");
	public static By SMACategory = By.xpath("//label[text()='SMA Category']//following-sibling::div[@class='rz-dropdown valid rz-clear form-control mandatory-color']");
	public static By Region = By.xpath("//label[text()='Region']//following-sibling::div");
	public static By Branchsearchspace = By.xpath("//input[@id='search-branch-multi-select']"); 
	public static By Branch = By.xpath("//label[text()='Branch']//following-sibling::div");
	public static By OsBalance_Operatorsdropdown = By.xpath("//label[text()='O/s Balance']//following-sibling::div");
	public static By OsBalance_textamountfield = By.xpath("(//div[@class='my-form-group form-group']//following::input[@class='searchinput form-control'])[1]");
	public static By Search = By.xpath("//button[@type='submit' and text()='Search']");
	public static By AllocateTo = By.xpath("//label[text()='Allocate To']//following-sibling::div");
	public static By Allocate = By.xpath("//button[@type='submit' and text()='Allocate']");
	public static By Allocationsuccessmessage = By.xpath("//p[text()='Allocated Successfully.']");
	public static By BranchAttentionRequiredAccounts  = By.xpath("//div[text()='Branch Attention Required Accounts ']//following::p[text()='SMA']");
	public static By Pagination = By.xpath("//div[@class='table-footerpagination']");
	public static By Selectallfromgrid = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//div[@class='rz-chkbox-box']");
	public static By Assign_ReassignTo = By.xpath("//label[text()='Assign / Reassign To']//following-sibling::div");
	public static By Assign_ReassignTosearchspace = By.xpath("//div[@class='rz-dropdown-panel rz-popup']//following-sibling::input");
	public static By Assign = By.xpath("//button[@type='button' and text()='Assign']");
	public static By Assignsuccessmessage = By.xpath("//p[text()='Assigned Successfully.']");
	public static By AccountNumbersfromGrid = By.xpath("//tbody//tr//td[position()=3]");
	public static By SummaryReports = By.xpath("//span[text()='Summary Reports']");
	public static By BankAllocationSummary = By.xpath("//a[@title='Bank Allocation Summary']");
	public static By FromDate = By.xpath("//label[text()='From Date']//following-sibling::div");
	public static By ToDate = By.xpath("//label[text()='To Date']//following-sibling::div");
	public static By search = By.xpath("//button[@type='button' and text()='Search']");
	public static By Datefromgrid = By.xpath("//tr[@class='rz-datatable-even  ']//td[position()=1]");
	public static By CountofAccountsfromgrid = By.xpath("//tr[@class='rz-datatable-even  ']//td[position()=4]");
	public static By TOTAL_OS_AMOUNT_IN_LAKHSfromgrid = By.xpath("//tr[@class='rz-datatable-even  ']//td[position()=5]");
	public static By Downloadbuttonfromgrid = By.xpath("//span[@class='rz-cell-data']//following::i");
	public static By Downloadsuccessmessage = By.xpath("//p[text()='File downloaded successfully']");
	
	public static By Dropdownvalues(String value) { 
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
    }
	public static By OsBalance_OperatorsdropdownDropdownvalues(String value) { 
        return By.xpath("//label[text()='O/s Balance']//following::div[@class='rz-dropdown-panel rz-popup']//li[@role='option' and @aria-label='>" + value + "']"); 
    }
	public static By AllocateToDropdownvalues(String value) { 
        return By.xpath("//li[@role='option' and @aria-label='>" + value + "']"); 
      
    }
	public static By AssignReassignToDropdownvalues(String value) { 
        return By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li[@role='option' and @aria-label='>" + value + "']"); 
      
    }
	public static By DateDropdownvalues(String value) { 
        return By.xpath("//div[@class='rz-datepicker rz-popup']//table[@class='rz-datepicker-calendar']//span[@class='rz-state-default' and text()=" + value + "]"); 
      
    }

}
