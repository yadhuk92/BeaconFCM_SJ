package com.Page_Repository;

import org.openqa.selenium.By;

public class CollectionAgencyDispositionRepo {
	
	public static By collectionAgencyMenu = By.xpath("//span[text()='Collection Agency']");
	public static By agencyAccountAllocationLink = By.xpath("//a[@title='Agency Account Allocation']");
	public static By pageHeader = By.xpath("//div[@class='dvPageheadingCaption']");
	public static By ZoneCO = By.xpath("//label[text()='Zone/CO']");
	public static By accountTypeSelect = By.xpath("//label[text()='Types of Account']/following-sibling::div");
	public static By assetCategorySelect = By.xpath("//label[text()='Asset Category']/following-sibling::div");
	public static By assetCategorySelectAll = By.xpath("(//div[@id='popup-PFTNPADisable']//following::div//div)[1]");
	public static By smaCategorySelect = By.xpath("//label[text()='SMA Category']/following-sibling::div");
	public static By npaCategorySelect = By.xpath("//label[text()='NPA Category']/following-sibling::div");
	public static By regionSelect = By.xpath("//label[text()='Region']/following-sibling::div");
	public static By dpdDaysOperationSelect = By.xpath("//label[text()='DPD Days']/following-sibling::div");
	public static By dpdDaysField = By.xpath("(//input[@min='0' and @maxlength='30'])[2]");
	public static By searchButton = By.xpath("//button[text()='Search']");
	public static By collectionAgencyDropdown = By.xpath("//label[text()='Select Collection Agency']/following-sibling::div");
	public static By collectionAgencyvalue = By.xpath("(//div[starts-with(@id,'popup') and @class='rz-dropdown-panel rz-popup'])[3]//child::li");
	public static By totalaccountsandoutstandingamounts = By.xpath("//tbody/tr[@class='rz-datatable-even  ']/td");
	public static By downloadButton = By.xpath("//button[text()=' Download ']");
	public static By downloadmsg = By.xpath("//p[text()='File downloaded successfully']");
	public static By allocate = By.xpath("//button[text()='Allocate']");
	public static By successMessage = By.xpath("//div[@class='rz-growl-message']//p");
	public static By resetbutton = By.xpath("//button[text()='Reset']");
	public static By CollectionAgency = By.xpath("//span[text()='Collection Agency']");
	public static By Disposition = By.xpath("//a[text()='Disposition']");
	public static By UpdationofDispositionlabel = By.xpath("//div[@class='dvPageheadingCaption']");
	public static By TransactionDetails = By.xpath("//input[@value='Transaction Details']");
	public static By AccountNumberfield = By.xpath("//input[@placeholder='Account Number']");
	public static By Searchbutton = By.xpath("//input[@value='Search']");
	public static By Accountnumberdetails = By.xpath("(//div[@class='col-sm-12 row dvshadow']//child::table[1]//tr)[2]//td");
	public static By warningforinvalidaccountnumber = By.xpath("//p[text()='You are not authorized to do the disposition of this account number']");
	public static By CustomerName = By.xpath("//th[text()='Customer Name']");
	public static By AccountNumber = By.xpath("//th[text()='Account Number']");
	public static By DayPastDue = By.xpath("//th[text()='Day Past Due']");
	public static By ProductType = By.xpath("//th[text()='Product Type']");
	public static By TotalOverdue = By.xpath("//th[text()='Total Overdue']");
	public static By CriticalAmount = By.xpath("//th[text()='Critical Amount']");
	public static By OSAmount = By.xpath("//th[text()='O/S Amount']");
	public static By OverdueDate = By.xpath("//th[text()='Overdue Date']");
	public static By Risk = By.xpath("//th[text()='Risk']");
	public static By SecurityDetails = By.xpath("//th[text()='Security Details']");
	public static By Viewlink = By.xpath("//a[text()='View']");
	public static By securityDetailsGridLocator = By.xpath("//div[@class='table-wrapper-inner']");
	public static By closebutton = By.xpath("//span[@class='rzi rzi-times']");
	public static By nextActionOwnerDropdown = By.xpath("//label[text()='Next Action Owner']//following-sibling::div");
	public static By DispositionDropdown = By.xpath("//label[text()='Disposition']//following-sibling::div");
	public static By SubDispositionDropdown = By.xpath("//label[text()='Sub-Disposition']//following-sibling::div");
	public static By NextActionDatePicker = By.xpath("//label[text()='Next Action Date']//following-sibling::div");
	public static By Remarks = By.xpath("//label[text()='Remarks']//following-sibling::input");
	public static By Cancelbutton = By.xpath("//button[text()='Cancel']");
	public static By Savebutton = By.xpath("//button[text()='Save']");
	public static By Successmsg = By.xpath("//p[text()='Saved Successfully']");
	public static By CommittedAmountfield = By.xpath("//label[text()='Committed Amount']//following-sibling::input");
	public static By customerId = By.xpath("//label[text()='Cust ID']//following-sibling::label");
	public static By zone = By.xpath("//label[text()='Zone']//following-sibling::label");
	public static By region = By.xpath("//label[text()='Region']//following-sibling::label");
	public static By branch = By.xpath("//label[text()='Branch Name']//following-sibling::label");
	public static By solId = By.xpath("//label[text()='Sol ID']//following-sibling::label");
	public static By principalOverdue = By.xpath("//label[text()='Principal Overdue']//following-sibling::label");
	public static By interestOverdue = By.xpath("//label[text()='Interest Overdue']//following-sibling::label");
	public static By chargesDue = By.xpath("//label[text()='Charges Overdue']//following-sibling::label");
	public static By accountType = By.xpath("//label[text()='Account Type']//following-sibling::label");
	public static By category = By.xpath("//label[text()='Category']//following-sibling::label");
	public static By vertical = By.xpath("//label[text()='Vertical']//following-sibling::label");
	public static By totalCollection = By.xpath("//label[text()='Total Collection']//following-sibling::label");
	public static By contactNumber = By.xpath("//label[text()='Contact Number']//following-sibling::label");
	public static By alternateNumber = By.xpath("//label[text()='Alternate Number']//following-sibling::label");
	public static By address = By.xpath("//label[text()='Address']//following-sibling::label");
	public static By profitNpaStatus = By.xpath("//label[text()='PFTNPA']//following-sibling::label");
	public static By OtherAccountDetailsheading = By.xpath("//h6[text()='Other Account Details']");
	public static By accountNumberColumn = By.xpath("//span[text()='Ac.No.']");
	public static By productColumn = By.xpath("//span[text()='Product']");
	public static By outstandingBalanceColumn = By.xpath("//span[text()='O/S Balance']");
	public static By totalOverdueColumn = By.xpath("//span[text()='Total Od.']");
	public static By totalCollectionColumn = By.xpath("//span[text()='Total Col.']");
	public static By overdueDateColumn = By.xpath("//span[text()='Overdue Date']");
	public static By daysPastDueColumn = By.xpath("//span[text()='DPD']");
	public static By remarksvalidationmsg = By.xpath("//span[text()='Remarks is required']");
	public static By riskColumn = By.xpath("//th[text()='Risk']//following::tr[1]//td[9]");
	public static By accountType_and_collectionAgency_value(String value) {
        return By.xpath("//li[@role='option' and @aria-label='>" + value + "']");
    }
	public static By sma_npa_region_values(String value) {
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']");
    }
	public static By dpdoperatorvalue(String value) {
        return By.xpath("(//li[@role='option' and @aria-label='>" + value + "'])[3]");
    }
	public static By FieldsInGrid(String value) {
        return By.xpath("//span[text()='" + value + "']");
    }
	
}
