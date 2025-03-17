package com.Page_Repository;

import org.openqa.selenium.By;

public class CollectionAgencyAgentAccountAllocationRepo {
	
	public static By AccountsAllocationmainmenu = By.xpath("//span[text()='Accounts Allocation']");
	public static By AgentAccountAllocationsubmenu = By.xpath("//a[@class='dropdown-item' and @href='Collection/Admin/AgentAccountAllocation']");
	public static By AgentAccountAllocationpageheader = By.xpath("//div[@class='dvPageheadingCaption']");
	public static By AssetCategory = By.xpath("//label[text()='Asset Category']//following-sibling::div");
	public static By SMACategory = By.xpath("//label[text()='SMA Category']//following-sibling::div");
	public static By NPACategory = By.xpath("//label[text()='NPA Category']//following-sibling::div");
	public static By Zone_CO = By.xpath("//label[text()='Zone/CO']//following-sibling::div");
	public static By Region = By.xpath("//label[text()='Region']//following-sibling::div");
	public static By Branch = By.xpath("//label[text()='Branch']//following-sibling::div");
	public static By SchemeType = By.xpath("//label[text()='Scheme Type']//following-sibling::div");
	public static By ProductType = By.xpath("//label[text()='Product Type']//following-sibling::div");
	public static By Os_Balance_Operator = By.xpath("//label[text()='O/s Balance']//following-sibling::div");
	public static By Os_Balance_text_field = By.xpath("(//input[@min='0' and @maxlength='25'])[1]");
	public static By Overdue_to_EMI_Operator = By.xpath("//label[text()='%Overdue to EMI']//following-sibling::div");
	public static By Overdue_to_EMI_text_field = By.xpath("(//input[@min='0' and @maxlength='25'])[2]");
	public static By Overdue_Balance_Operator = By.xpath("//label[text()='Overdue Balance']//following-sibling::div");
	public static By Overdue_Balance_text_field = By.xpath("(//input[@min='0' and @maxlength='25'])[3]");
	public static By CollectionAgent = By.xpath("(//label[text()='Collection Agent']//following-sibling::div)[1]");
	public static By TypesofAccount = By.xpath("//label[text()='Types of Account']//following-sibling::div");
	public static By Searchbutton = By.xpath("//button[@type='submit' and text()='Search']");
	public static By Resetbutton = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By CollectionAgent2 = By.xpath("(//label[text()='Collection Agent']//following-sibling::div)[2]");
	public static By AllocationDate = By.xpath("//label[text()='Allocation Date']//following-sibling::div");
	public static By Allocatebutton = By.xpath("//button[@type='submit' and text()='Allocate']");
	public static By DeAllocatebutton = By.xpath("//button[@type='submit' and text()='De-Allocate']");
	public static By  Downloadbutton = By.xpath("//button[@type='submit' and text()=' Download ']");
	public static By  Closebutton = By.xpath("//button[@type='reset' and text()='Close']");
	public static By  AllocatedListbutton = By.xpath("//button[@type='submit' and text()='Allocated List']");
	public static By  AssetCategoryvalues = By.xpath("//div[@class='rz-multiselect-panel rz-popup']//following-sibling::div[@class='rz-chkbox']");
	public static By  Gridvalues = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tr//td[position()=3]");
	public static By  accountCheckbox = By.xpath("//tbody//tr[@class='rz-datatable-even  ']//div[@class='rz-chkbox-box']");
	public static By  firstrecordaccountCheckbox = By.xpath("//tbody//tr[@class='rz-datatable-even  ' and position()=1]//div[@class='rz-chkbox-box']");
	public static By  accountdetails = By.xpath("//tbody//tr[@class='rz-datatable-even  ']//td[position()>1]");
	public static By  accountdetailsoffirstrow = By.xpath("//tbody//tr[@class='rz-datatable-even  ' and position()=1]//td[position()>1]");
	public static By  collectionagentvalues = By.xpath("(//div[@class='rz-dropdown-panel rz-popup']//div[@class='rz-dropdown-items-wrapper']//ul[@role='listbox']//li[@class='rz-dropdown-item ' ])[position()>1]");
	public static By  Allocatedmsg = By.xpath("//p[text()='Allocated Successfully']");
	public static By  downloadedmsg = By.xpath("//p[text()='File downloaded successfully']");
	public static By  deallocatedmsg = By.xpath("//p[text()='De-Allocated Successfully']");
	public static By  allocatedlistpopup = By.xpath("//div[@class='rz-dialog-content']");
	public static By  allocatedlistpopupclosebtn = By.xpath("//a[@role='button']//span[@class='rzi rzi-times']");
	public static By  AgentName = By.xpath("//label[text()='Agent Name']//following-sibling::div");
	public static By  ActionDateFrom = By.xpath("//label[text()='Action Date From']//following-sibling::div");
	public static By  ActionDateTo = By.xpath("//label[text()='Action Date To']//following-sibling::div");
	public static By  Search = By.xpath("//div[@class='form-button justify-content-end']//button[@type='submit' and text()='Search']");
	public static By  Allocationdates = By.xpath("(//table[@class='rz-grid-table rz-grid-table-fixed'])[1]//tr[contains(@class, 'rz-datatable')]//td[position()=5]");
	public static By  Resetbuton = By.xpath("//div[@class='form-button justify-content-center formcol']//button[@type='reset']");
	public static By  Gridresetvalue = By.xpath("((//table[@class='rz-grid-table rz-grid-table-fixed'])[1]//tr)[2]");
	public static By  AllocationID = By.xpath("(//tbody//tr[@class='rz-datatable-even  '])[1]//td//div[@class='rz-chkbox']");
	public static By  downloadExcelButton = By.xpath("//button[@type='submit' and text()=' Download in Excel']");
	
	public static By SMA_Category_NPA_Category_CollectionAgent_value(String value) {
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']");
    }
	public static By Types_of_Account_Role_CollectionAgent_value(String value) {
        return By.xpath("//li[@role='option' and @aria-label='>" + value + "']");
    }
	public static By ActionDate_From_and_To_value(String value) {
        return By.xpath("//div[@class='rz-datepicker rz-popup']//table[@class='rz-datepicker-calendar']//td//span[@class='rz-state-default' and text()='" + value + "']");
      
    }
	public static By ActionDate_From_and_To_values(String value) {
        return By.xpath("(//div[@class='rz-datepicker rz-popup']//table[@class='rz-datepicker-calendar']//td//span[@class='rz-state-default' and text()='" + value + "'])[2]");
        
      
    }
	public static By Agent_Name(String value) {
        return By.xpath("(//div[@class='rz-multiselect-items-wrapper']//ul//li[@class='rz-multiselect-item ' and @aria-label='" + value + "'])[2]");
        
      
    }
}

