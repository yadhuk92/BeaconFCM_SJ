package com.Page_Repository;

import org.openqa.selenium.By;

public class MyDeskDashboardRepo {

	public static By profiledropdown  = By.xpath("//*[@class='dropdown profiledropdown']");
	public static By Logout  = By.xpath("//*[text()='Logout']");
	
	public static By Collection  = By.xpath("//*[text()='Go Collection']");
	public static By MyDesk  = By.xpath("//*[text()='My Desk']");
	public static By AccountAllocatedSuccessfully  = By.xpath("//*[text()='Allocated Successfully.']");
	
	public static By Dashboard  = By.xpath("//*[@title='Dashboard']");
	
	public static By  BranchAttentionRequiredAccounts = By.xpath("//*[text()='Branch Attention Required Accounts ']");	
	public static By SMA  = By.xpath("//*[text()='SMA']");
	public static By MTNPA  = By.xpath("//*[text()='MTNPA']");
	public static By FTNPA  = By.xpath("//*[text()='FTNPA']");
	public static By NPA  = By.xpath("//*[text()='NPA']");
	
	public static By Accounts  = By.xpath("//*[text()='My Accounts']");
	public static By Commitments  = By.xpath("//*[text()='Commitments']");
	public static By FailedCommitments  = By.xpath("//*[text()='Failed Commitments']");
	public static By MyAccounts  = By.xpath("//*[text()='My Accounts']");
	public static By NewAccounts = By.xpath("//*[text()='New Accounts']");
	
	public static By SupportRequests  = By.xpath("//*[text()='Support Requests']");
	public static By  UnAttended = By.xpath("//*[text()='UnAttended']");
	public static By AllocatedToUsers  = By.xpath("//*[text()='Allocated To Users']");
	public static By Allocations  = By.xpath("//*[text()='My Allocations']");
	public static By Resolved  = By.xpath("//*[text()='Resolved']");
		
	public static By LoanAtRisk  = By.xpath("//*[text()='Loan At Risk']");		
	public static By  TotalSMA = By.xpath("//*[text()='Total SMA']");
	public static By  TotalMTNPA  = By.xpath("//*[text()='Total MTNPA']");
	public static By  TotalFTNPA = By.xpath("//*[text()='Total FTNPA']");
	public static By  TotalNPA  = By.xpath("//*[text()='Total NPA']");
	
	
	public static By UnassignedAccounts  = By.xpath("//*[text()='Unassigned Accounts']");
	public static By UnassignedAccountsSMA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='SMA']");
	public static By UnassignedAccountsFTNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='FTNPA']");
	public static By UnassignedAccountsNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='NPA']");
	public static By UnassignedAccountsMTNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='MTNPA']");
	public static By UnassignedAccountsCount  = By.xpath("//*[contains(text(),'')]/..//*[contains(text(),'')]/..//*[@class='tile-total']");
	
	
	
	
	
	
	
//for SMA inside page fileld verification	
	public static By TypesofAccount  = By.xpath("//*[contains(text(),'Types of Account')]");
	public static By AssetCategory  = By.xpath("//*[contains(text(),'Asset Category')]");
	public static By SMACategory = By.xpath("//*[contains(text(),'SMA Category')]");
	public static By NPACategory  = By.xpath("//*[contains(text(),'NPA Category')]");
	public static By ZoneCO  = By.xpath("//*[contains(text(),'Zone/CO')]");
	public static By Region  = By.xpath("//*[contains(text(),'Region')]");
	public static By Branch  = By.xpath("//*[contains(text(),'Branch')]");
	public static By Vertical  = By.xpath("//*[contains(text(),'Vertical')]");
	public static By SchemeType  = By.xpath("//*[contains(text(),'Scheme Type')]");
	public static By ProductType  = By.xpath("//*[contains(text(),'Product Type')]");
	public static By SchemeCode  = By.xpath("//*[contains(text(),'Scheme Code')]");
	public static By Balance  = By.xpath("//*[contains(text(),'O/s Balance')]");
	public static By Overdue  = By.xpath("//*[contains(text(),'%Overdue to EMI')]");
	public static By DPD  = By.xpath("//*[contains(text(),'DPD')]");
	public static By AssetTaggingType  = By.xpath("//*[contains(text(),'Asset Tagging Type')]");
	public static By Search  = By.xpath("//*[@type='submit']");
	public static By reset  = By.xpath("//*[@type='reset']");
	public static By Download  = By.xpath("//*[text()=' Download ']");
	public static By AllocateTo  = By.xpath("//*[text()='Allocate To']");
	public static By Allocate  = By.xpath("//*[text()='Allocate']");
	public static By Close  = By.xpath("//*[text()='Close']");
	public static By TotalAccountSelected  = By.xpath("//*/div/table/tbody/tr/td[1]/span");
	public static By AllocateToDropdown  = By.xpath("//*[contains(text(),'Allocate To')]/..//*[contains(@class,'down')]");
	public static By BranchUser  = By.xpath("//*[@aria-label='>Branch']");
	public static By CallcenterUser  = By.xpath("//*[@aria-label='>Call Center']");
	public static By CollectionAgency  = By.xpath("//*[@aria-label='>Collection Agency']");
	public static By CollectionAgency2  = By.xpath("(//*[@aria-label='>Collection Agency'])[2]");
	public static By SelectCallCenterDropdown  = By.xpath("//*[contains(text(),'Select Call Center')]/..//*[contains(@class,'down')]");
	public static By SelectAgencyDropDown  = By.xpath("//*[contains(text(),'Select Agency')]/..//*[contains(@class,'down')]");
	public static By SelectCallCenter  = By.xpath("//*[contains(@aria-label,'CallCent')]");
	public static By SelectAgency  = By.xpath("//*[contains(@aria-label,'agency')]");
	
	public static By AllocatedList  = By.xpath("//*[contains(text(),'Allocated List')]");
	public static By ActionDateFrom  = By.xpath("//*[@id='ActionDateFrom']");
	public static By ActionDateto  = By.xpath("//*[@id='ActionDateto']");
	public static By AllocatedDropdown  = By.xpath("//*[@role='dialog']/..//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By CallCenter  = By.xpath("(//*[@role='listbox']/..//*[@aria-label='>Call Center'])[2]");
	public static By Noofallocations  = By.xpath("//div/table/tbody/tr/td[4]/span");
	public static By AllocationPopUpClose  = By.xpath("//a[@role='button']");
	
	
	
	public static By CallCentreCallLogin  = By.xpath("//*[contains(text(),'Call Centre')]");
	public static By AccountFiltration  = By.xpath("//*[contains(text(),'Account Filtration')]");
	public static By AssetDropdown  = By.xpath("//*[contains(text(),'Asset Category')]/..//*[contains(@class,'down')]");
	public static By SMACategorycall  = By.xpath("//*[@aria-label='SMA Category']");
	public static By DropdownAllocatedTo  = By.xpath("//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By CallCentre  = By.xpath("//*[@aria-label='>Call Centre']");
	public static By SMACategoryDropdown  = By.xpath("//*[contains(text(),'SMA Category')]/..//*[contains(@class,'down')]");
	public static By SMACate  = By.xpath("//*[contains(@aria-label,'SMA')][2]");
	public static By AllocatedTodown  = By.xpath("//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By AllocatedTodownCall  = By.xpath("//*[@aria-label='>Call Centre']");
	
	
	
	
	
	
	
//after reset to check are they active or not
//*[text()='Types of Account']/..//*[contains(@class,'right')]
//Is disaplyed//*[@aria-label='>Allocated']
//*[contains(text(),'Asset Category')]/..//*[contains(@class,'right')]
//Is disaplyed//*[@aria-label='NPA Category']	
//*[contains(text(),'SMA Category')]/..//*[contains(@class,'right')]
//*[contains(text(),'NPA Category')]/..//*[contains(@class,'right')]
//*[contains(text(),'Zone/CO')]/..//*[contains(@class,'right')]
//*[contains(text(),'Region')]/..//*[contains(@class,'right')]
//*[contains(text(),'Branch')]/..//*[contains(@class,'right')]
//*[contains(text(),'Vertical')]/..//*[contains(@class,'right')]
//Is disaplyed//*[@aria-label='LCG']
//*[contains(text(),'Scheme Type')]/..//*[contains(@class,'right')]
//*[@aria-label='CAA']
//*[contains(text(),'Product Type')]/..//*[contains(@class,'right')]
//*[contains(text(),'Scheme Code')]/..//*[contains(@class,'right')]
//*[contains(text(),'O/s Balance')]/..//*[contains(@class,'right')]
//*[contains(text(),'%Overdue to EMI')]/..//*[contains(@class,'right')]
//*[contains(text(),'DPD')]/..//*[contains(@class,'right')]
//*[contains(text(),'Asset Tagging Type')]/..//*[contains(@class,'right')]
	
	public static By TypesofAccountDropDown  = By.xpath("//*[text()='Types of Account']/..//*[contains(@class,'right')]");
	public static By Allocated  = By.xpath("//*[@aria-label='>Allocated']");
	public static By AssetCategoryDropDown  = By.xpath("//*[contains(text(),'Asset Category')]/..//*[contains(@class,'right')]");
	public static By VerticalDropDown  = By.xpath("//*[contains(text(),'Vertical')]/..//*[contains(@class,'right')]");
	public static By NPAValue  = By.xpath("//*[@aria-label='NPA Category']");
	public static By LCG  = By.xpath("//*[@aria-label='LCG']");	
	public static By SchemeTypeDropDown  = By.xpath("//*[contains(text(),'Scheme Type')]/..//*[contains(@class,'right')]");
	public static By CAA  = By.xpath("//*[@aria-label='CAA']");	
	
	
	
	
	
	
	
	
}
