package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreCollectionAgencyAgencyAccountAllocationRepo {
	public static By collectionAgencyMenu=By.xpath("//span[text()='Collection Agency']");
	public static By agencyAccountAllocationMenu=By.xpath("//a[@title='Agency Account Allocation']");
	public static By spinner=By.xpath("(//div[@class='spinner'])");
	public static By TypesOfAccountDropdownlabel=By.xpath("(//div[@class='my-form-group form-group'])[1]");
	public static By NotAllocatedValue=By.xpath("//*[@class='rz-dropdown-panel rz-popup']/div/ul/li[2]");
	public static By TypesOfAccountDrpAllValue=By.xpath("(//div[@class='rz-dropdown-panel rz-popup'])[1]");
	public static By AssetCategory=By.xpath("(//div[@class='my-form-group form-group'])[2]");
	public static By NPACategory=By.xpath("//li[@aria-label='NPA Category']");
	public static By SMACategory=By.xpath("//li[@aria-label='SMA Category']");
	public static By closeAssetCategoryValue=By.xpath("(//i[@class='rz-dropdown-clear-icon rzi rzi-times'])[1]");
	public static By zone_co=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[5]");
	public static By TypesOfAccdrp =By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[1]/div/div/label");
	public static By SMAdropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[3]/div/div/label");
	public static By SMA0=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='SMA 0 (01-30 Days)']");
	public static By SMA1=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='SMA 1 (31-60 Days)']");
	public static By SMA2=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='SMA 2 (61-90 Days)']");
	public static By NPAdropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[4]/div/div/label");
	public static By SubStandard=By.xpath("//li[@class='rz-multiselect-item '  and @aria-label='SUB-STANDARD']");
	public static By D1=By.xpath("//li[@class='rz-multiselect-item '  and @aria-label='DOUBTFUL-1']");
	public static By D2=By.xpath("//li[@class='rz-multiselect-item '  and @aria-label='DOUBTFUL-2']");
	public static By D3=By.xpath("//li[@class='rz-multiselect-item '  and @aria-label='DOUBTFUL-3']");
	public static By LossAsset=By.xpath("//li[@class='rz-multiselect-item '  and @aria-label='LOSS ASSET']");
	public static By zoneMumbai=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='Mumbai']");
	public static By regiondropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[6]/div/div");
	public static By regionMumbai1=By.xpath("//li[@class='rz-multiselect-item ' and  @aria-label='Mumbai I']");
	public static By branchdropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[7]/div/div");
	public static By branchSearch=By.xpath("//*[@id=\"search-branch-multi-select\"]");
	public static By AmravatiBranch=By.xpath("//*[@id=\"popup-branch-multi-select\"]/div[2]/ul/li[3]");
	public static By verticaldropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[9]/div/div");
//	public static By SelectallVerticalvalue=By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]");
	public static By lcg=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='LCG']");
	public static By mcg=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='MCG']");
	public static By pbg=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='PBG']");
	public static By rbg=By.xpath("//li[@class='rz-multiselect-item ' and @aria-label='RBG']");
    public static By schemedropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[10]/div/div");
    public static By SelectallSchemeValue=By.xpath("/html/body/div[10]/div[1]/div[1]/div[2]");
	public static By productTypedropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[11]/div/div");
	public static By SelectProductTypeAllValue=By.xpath("/html/body/div[11]/div[1]/div[1]/div[2]");
	public static By schemecodedropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[12]/div/div");
	public static By SelectAllSchemeCode=By.xpath("/html/body/div[12]/div[1]/div[1]/div[2]");
	public static By DPDDropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[17]/div/div/label");
	public static By equalOperator=By.xpath("(//li[@class='rz-dropdown-item ' and @aria-label='>='])[3]");
	public static By DPDValue=By.xpath("//*[@id=\"dvbody\"]/div[2]/div/form/div/div/div/div[18]/div/input");
	public static By assetTaggingTypeDropdown=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[19]/div/div");
	public static By SelectAllAssetTaggingValue=By.xpath("((//div[contains(@id,'popup')])[28]//div)[4]");
	public static By ActionOwnerdropdown=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div/form/div/div/div/div[21]/div/div");
	public static By SelectAllActionOwner=By.xpath("((//div[contains(@id,'popup')])[28]//div)[4]");
    public static By searchButton=By.xpath("//button[text()='Search']");
    public static By TotalAccountSelected=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[3]/div/div/div/div/div/table/tbody/tr/td[1]/span");
    public static By OutstandingAmount=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[3]/div/div/div/div/div/table/tbody/tr/td[2]");
    public static By AllocationDate=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[4]/div/div/div/div[1]/div[2]/div/div/span/input");
    public static By SelectCollectionAgency=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[4]/div/div/div/div[1]/div[1]/div/div");
    public static By CollectionAgencySearch=By.xpath("/html/body/div[10]/div[1]/input");
   
    public static By AllocateButton=By.xpath("//button[text()='Allocate']");
    public static By SuccessMsgForAllocation=By.xpath("/html/body/div[1]/div[1]/div/div/div/div[2]");
    public static By AllocatedListButton=By.xpath("//button[text()='Allocated List']");
    public static By PopUp=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]");
    public static By AgencyNameDropdown=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div/div");
    public static By AgencyNameSearch=By.xpath("(//input[@class='rz-inputtext'])[15]");
    public static By AllocatedAgency=By.xpath("//div[@class='rz-multiselect-panel rz-popup']/div[2]/ul/li[1]");
   
    public static By FromDate=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div/div/span/input");
    public static By ToDate=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div/div/span/input");
    public static By Search=By.xpath("(//button[text()='Search'])[1]");
    public static By selectColumn=By.xpath("(//span[@class='rz-column-title'])[1]");
    public static By allocationIDColumn=By.xpath("(//span[@class='rz-column-title'])[2]");
    public static By EINnoColumn=By.xpath("(//span[@class='rz-column-title'])[3]");
    public static By officerNameColumn=By.xpath("(//span[@class='rz-column-title'])[4]");
    public static By DateColumn=By.xpath("(//span[@class='rz-column-title'])[5]");
    public static By NoOfAllocationColumn=By.xpath("(//span[@class='rz-column-title'])[6]");
    public static By NextButton=By.xpath("//span[text()='Next']");
    public static By PreviousButton=By.xpath("//span[text()='Previous']");
    public static By ResetButton=By.xpath("(//button[text()='Reset'])[1]");
    public static By DownloadInExcelButton=By.xpath("//button[text()=' Download in Excel']");
    public static By CheckBox=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[1]/span/div/div[2]");
    public static By SuccessMsgForDownload=By.xpath("//p[text()='File downloaded successfully']");
    public static By WarningMsgForEmptySearchValue=By.xpath("//p[text()='Please select either agency or date']");
    public static By FirstCollectionAgencyName=By.xpath("//*[@class='rz-dropdown-panel rz-popup']/div[2]/ul/li[1]");
    
    public static By CloseButton=By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/a");
    public static By PreviousPage=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[4]/div/div");
    public static By Allocated=By.xpath("(//*[@class='rz-dropdown-panel rz-popup']/div/ul/li[1])[2]");
    public static By DeAllocateButton=By.xpath("//button[text()='De-Allocate']");
    
	

}
