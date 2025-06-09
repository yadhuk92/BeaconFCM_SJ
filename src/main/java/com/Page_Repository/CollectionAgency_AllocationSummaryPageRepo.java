package com.Page_Repository;

import org.openqa.selenium.By;


public class CollectionAgency_AllocationSummaryPageRepo {
	
	public static By dashboardMenu = By.xpath("//span[@class='text nav-text' and text()='Dash board']");
	//public static By dashboardMenu = By.xpath("//a[.//span[contains(text(),'Dashboard')]]");

	//a[.//span[contains(text(),'Dashboard')]]
	public static By allocationSummaryLink = By.xpath("//a[@class='dropdown-item' and @href='/Collection/Allocationsummary']");
	public static By allocationSummaryLabel = By.xpath("//div[@class='dvPageheadingCaption' and contains(text(),'Allocation Summary')]");
	public static By grid = By.xpath("//div[@class='card-wrapper']");
	public static By dateColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Date']");
	public static By totalAccountsReceivedColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Total Account Received']");
	public static By totalAccountsReceivedColumn1 = By.xpath("//span[@title='Total Account Received']");

	public static By allocatedToAgentsColumn = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//thead//th//span[text()='Allocated To Agents']");
	public static By allocatedToAgentsColumn1 = By.xpath("//span[@title='Allocated To Agents']");

	public static By gridValues = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody");
	public static By gridValues1 = By.xpath("/html/body/div/div[2]/div[1]/div[3]/div/div[2]/div/div/div/table/tbody/tr/td");

	public static By downloadButton = By.xpath("(//i[@class = 'rzi d-inline-flex justify-content-center align-items-center'])[1]");
	public static By downloadButton1 = By.xpath("//i[text()='download_for_offline']");

	public static By noDataMessage = By.xpath("//span[normalize-space()='No records to display.']");
	public static By agentManagement = By.xpath("//span[contains(text(), 'Agent Management')]");
	public static By addNewAgent = By.xpath("//a[@href='Collection/Agent/AddNewAgent']");
	public static By agentCode = By.xpath("(//input[contains(@class, 'rz-textbox')])[1]");
	public static By name = By.xpath("(//input[contains(@class, 'rz-textbox')])[2]");
	public static By phoneNumber = By.xpath("(//input[contains(@class, 'rz-textbox')])[3]");
	public static By tenurity = By.xpath("(//input[contains(@class, 'form-control valid')])");
	public static By role = By.xpath("(//div[contains(@class, 'rz-dropdown')])[1]");
	public static By roleDropdown = By.xpath("//li[@aria-label='>TESTROLE']");
	public static By roleDropdown1 = By.xpath("//li[@aria-label='Test2 Role']");

	public static By submit = By.xpath("//button[contains(text(), 'Submit')]");
	//Core Application
	public static By collectionAgency = By.xpath("//*[text()='Collection Agency']/ancestor::a");
	public static By agencyAccountAllocation = By.xpath("//a[@title = 'Agency Account Allocation']");
	public static By typesOfAccount = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[1]/div/div");
	public static By typesOfAccountDropdown = By.xpath("//li[contains(@aria-label, 'Not Allocated')]");
	public static By assetCoreCategory = By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[2]/div/div");
	public static By assetCoreCategoryDropdown1 = By.xpath("//li[@aria-label='SMA Category']//div[@class='rz-chkbox ']//div[1]");
	public static By assetCoreCategoryDropdown2 = By.xpath("//li[@aria-label='NPA Category']//div[@class='rz-chkbox ']//div[1]");
	public static By smaCategoryDropdown = By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[3]/div/div");
	public static By smaCategory1 = By.xpath("//li[@aria-label='SMA 1 (31-60 Days)']//div[@class='rz-chkbox ']//div[1]");
	
	//li[@aria-label='SMA 0 (01-30 Days)']//div[@class='notranslate rz-chkbox-box ']
	public static By smaCategory2 = By.xpath("//li[@aria-label='SMA 2 (61-90 Days)']//div[@class='rz-chkbox ']//div[1]");
	
	
	public static By npaCategoryDropdown = By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[4]/div/div");
	public static By npaCategory = By.xpath("//li[@aria-label='DOUBTFUL-1']//div[@class='notranslate rz-chkbox-box ']");
	//li[@aria-label='DOUBTFUL-2']//div[@class='rz-chkbox ']//div[1]
	public static By npaCategory2 = By.xpath("//li[contains(@aria-label,'DOUBTFUL-3')]//div[contains(@class,'rz-chkbox')]//div[contains(@class,'')]");
	public static By selectOperator = By.xpath("(//div[@title='Select Financial Operator'])[1]");
	public static By selectOperatorValue = By.xpath("(//li[contains(@aria-label, '<=')])[3]");
	public static By selectOperatorValueField = By.xpath("(//input[@class = 'searchinput form-control'])[1]");


	
	
	
	public static By accoun1  = By.xpath("(//div[@class='rz-chkbox-box'])[2]");
	public static By accoun2  = By.xpath("(//div[@class='rz-chkbox-box'])[3]");
	public static By accoun3  = By.xpath("(//div[@class='rz-chkbox-box'])[4]");
	public static By accoun4  = By.xpath("(//div[@class='rz-chkbox-box'])[5]");

	
	public static By searchCore = By.xpath("//button[contains(text(), 'Search')]");					
	public static By selectCollectionAgency = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[4]/div/div/div/div[1]/div[1]/div/div");
	public static By collectionAgencyDropdown = By.xpath("//li[@aria-label='>TEST AUTOMA']");
	public static By collectionAgencyDropdown1 = By.xpath("(//li[@aria-label='FCM AGENCY AGY'])[2]");

	public static By allocate = By.xpath("(//button[contains(text(), 'Allocate')])[1]");
	
	public static By accountsAllocation = By.xpath("//a[.//span[contains(text(),'Accounts Allocation')]]");
	public static By agentAccountAllocation = By.xpath("//a[contains(text(), 'Agent Account Allocation')]");
	public static By assetCategory = By.xpath("(//div[contains(@class, 'rz-dropdown')])[1]");
	public static By assetCategoryDropdown = By.xpath("//li[contains(@aria-label,'SMA Category')]//div[contains(@class,'rz-chkbox')]//div[contains(@class,'')]");
	public static By assetCategoryDropdown1 = By.xpath("//li[contains(@aria-label,'NPA Category')]//div[contains(@class,'rz-chkbox')]//div[contains(@class,'')]");
	public static By SMACategoryCollection = By.xpath("(//div[contains(@class, 'rz-dropdown')])[3]");
	public static By SMACategory1 = By.xpath("//li[@aria-label='SMA 1 (31-60 Days)']//div[@class='rz-chkbox ']//div[1]");
	public static By SMACategory2 = By.xpath("//li[@aria-label='SMA 2 (61-90 Days)']//div[@class='rz-chkbox ']//div[1]");
	
	public static By NpaCategory = By.xpath("(//div[contains(@class, 'rz-dropdown')])[5]");
	public static By NpaCategory1 = By.xpath("//li[@aria-label='DOUBTFUL-2']//div[@class='rz-chkbox ']//div[1]");
	public static By NpaCategory2 = By.xpath("//li[contains(@aria-label,'DOUBTFUL-3')]//div[contains(@class,'rz-chkbox')]//div[contains(@class,'')]");		
	public static By TypesofAccount = By.xpath("//label[text()='Types of Account']/following-sibling::div[contains(@class, 'rz-dropdown')]");
	public static By NotAllocated = By.xpath("//li[contains(@aria-label, 'Not Allocated')]");
	public static By Search = By.xpath("//button[contains(text(), 'Search')]");
	public static By collectionAgent = By.xpath("/html/body/div/div[2]/div[1]/div[5]/div/div/div/div[1]/div[1]/div/div");
	public static By collectionAgentDropdown = By.xpath("//li[@aria-label='>Agent 5']");
	public static By collectionAgentDropdown1 = By.xpath("(//li[contains(@aria-label, 'Agent 6')])[2]");

	public static By allocateAgent = By.xpath("(//button[normalize-space()='Allocate'])[1]");
	public static By viewGrid = By.xpath("//div[@class='card-wrapper']");
	public static By dateColumn1 = By.xpath("//span[@title='Date']");
	public static By userIcon = By.xpath("(//button[@type='button'])[1]");
	public static By logOut = By.xpath("(//a[normalize-space()='Logout'])[1]");

	

	public static By Dropdownvalues(String value) { 
        return By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='" + value + "']"); 
    }

}



