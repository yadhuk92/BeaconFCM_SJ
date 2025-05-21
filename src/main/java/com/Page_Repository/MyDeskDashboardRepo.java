package com.Page_Repository;

import java.io.File;


import org.openqa.selenium.By;








//commit
public class MyDeskDashboardRepo {

	public static By profiledropdown  = By.xpath("//*[@class='dropdown profiledropdown']");
	public static By profiledropdownbutton  = By.xpath("//*[@class='dropdown profiledropdown']//button[@type='button']");
	
	public static By Logout  = By.xpath("//*[text()='Logout']");
	public static By AgencyName  = By.xpath("//*[@class='name']");
	public static By BranchName  = By.xpath("//*[@class='org']");
	
	public static By Collection  = By.xpath("//*[text()='Go Collection']");
	public static By MyDesk  = By.xpath("//*[text()='My Desk']");
	public static By AccountAllocatedSuccessfully  = By.xpath("//*[text()='Allocated Successfully.']");
	
	
	public static By SavedSuccessfully  = By.xpath("//*[text()='Saved Successfully']");
	
	public static By AccessDenied  = By.xpath("//*[text()='You are not authorized to view the disposition details of this account']");
	public static By AssetCategoryRequired  = By.xpath("//*[text()='Asset Category Required']");
	
	public static By DPDDaysOperatorShouldNotBeEmpty  = By.xpath("//*[text()='DPD Days Operator Should Not Be Empty']");
	public static By Filedownloadedsuccessfully  = By.xpath("//*[text()='File downloaded successfully']");
	public static By EnterallfieldsinLoanAtRisk  = By.xpath("//*[text()='Enter all fields in Loan At Risk']");
	
	public static By Assignedsuccessfully  = By.xpath("//*[text()='Assigned Successfully.']");
	public static By DPDError  = By.xpath("//*[text()='DPD From Should Not Be Greater than DPD To']");
	public static By AllocationTypesShouldNotBeEmpty  = By.xpath("//*[text()='Allocation Types Should Not Be Empty']");
	
	
	public static By AccountType  = By.xpath("//div/table/tbody/tr[1]/td[7]/span");
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
	
	
	public static By MonthlyCommitment  = By.xpath("//*[@class='tile-title' and text()=\"Monthly Commitments\"]");
	public static By TodaysCommitment  = By.xpath("//*[@class='tile-title' and text()=\"Today's Commitments\"]");
	public static By SupportRequests  = By.xpath("//*[text()='Support Requests']");
	public static By  UnAttended = By.xpath("//*[text()='UnAttended']");
	public static By AllocatedToUsers  = By.xpath("//*[text()='Allocated To Users']");
	public static By Allocations  = By.xpath("//*[text()='My Allocations']");
	public static By Resolved  = By.xpath("//*[text()='Resolved']");
		
	public static By LoanAtRisk  = By.xpath("//*[text()='Loan At Risk']");
	public static By LoanAtRiskPOPUP  = By.xpath("//label[text()='Zone/CO']");
	
	public static By  TotalSMA = By.xpath("//*[text()='Total SMA']");
	public static By  TotalMTNPA  = By.xpath("//*[text()='Total MTNPA']");
	public static By  TotalFTNPA = By.xpath("//*[text()='Total FTNPA']");
	public static By  TotalNPA  = By.xpath("//*[text()='Total NPA']");
	
	
	public static By UnassignedAccounts  = By.xpath("//*[text()='Unassigned Accounts']");
	public static By UnassignedAccountsSMA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='SMA']");
	public static By UnassignedAccountsFTNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='FTNPA']");
	public static By UnassignedAccountsNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='NPA']");
	public static By UnassignedAccountsMTNPA  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[text()='MTNPA']");
	public static By UnassignedAccountsCount  = By.xpath("//*[contains(text(),'Unassigned Accounts')]/..//*[contains(text(),'SMA')]/..//*[@class='tile-total']");
	public static By MyAccountNewAccountsCount  = By.xpath("//*[contains(text(),'My Accounts')]/..//*[contains(text(),'New Accounts')]/..//*[@class='tile-total']");
	public static By LoanAtRiskAccountsCount  = By.xpath("//*[contains(text(),'Loan At Risk')]/..//*[contains(text(),'Total SMA')]/..//*[@class='tile-total']");
	public static By FailedCommitmentsCount  = By.xpath("//*[text()='My Accounts']/..//*[contains(text(),'Failed Commitments')]/..//*[@class='tile-total']");
	public static By AutoAllocation  = By.xpath("//*[contains(@title,'Auto Allocation')]");
	public static By CallCentreSidebutton  = By.xpath("//*[contains(text(),'Call Centre')]");
	
	
	
	
	
	
//for SMA inside page fileld verification	
	public static By TypesofAccount  = By.xpath("//*[contains(text(),'Types of Account')]");
	public static By AssetCategory  = By.xpath("//*[contains(text(),'Asset Category')]");
	public static By SMACategory = By.xpath("//*[contains(text(),'SMA Category')]");
	public static By SMACate0 = By.xpath("//*[contains(@aria-label,'SMA 0')]");
	public static By SMACate1 = By.xpath("//*[contains(@aria-label,'SMA 1')]");
	public static By SMACate2 = By.xpath("//*[contains(@aria-label,'SMA 2')]");
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
	public static By NoRecordsToDisplay  = By.xpath("//*[contains(text(),'No records to display.')]");
	public static By DownloadSummary  = By.xpath("//*[contains(text(),'Download Summary')]");
	
	public static By reset  = By.xpath("//*[@type='reset']");
	public static By LoanAtRiskReset  = By.xpath("//*[@type='reset' and contains(text(),'Reset')]");
	
	public static By Download  = By.xpath("//*[text()=' Download ']");
	public static By AllocateTo  = By.xpath("//*[text()='Allocate To']");
	public static By Allocate  = By.xpath("//*[text()='Allocate']");
	public static By Close  = By.xpath("//*[text()='Close']");
	public static By TotalAccountSelected  = By.xpath("//*/div/table/tbody/tr/td[1]/span");
	public static By AllocateToDropdown  = By.xpath("//*[contains(text(),'Allocate To')]/..//*[contains(@class,'down')]");
	public static By BranchUser  = By.xpath("//*[@aria-label='>Branch']");
	
	public static By BCOUser  = By.xpath("//*[@aria-label='>BCO']");
	public static By CallcenterUser  = By.xpath("//*[@aria-label='>Call Center']");
	public static By CollectionAgency  = By.xpath("//*[@aria-label='>Collection Agency']");
	public static By CollectionAgency2  = By.xpath("(//*[@aria-label='>Collection Agency'])[2]");
	public static By SelectCallCenterDropdown  = By.xpath("//*[contains(text(),'Select Call Center')]/..//*[contains(@class,'down')]");
	public static By SelectAgencyDropDown  = By.xpath("//*[contains(text(),'Select Agency')]/..//*[contains(@class,'down')]");
	public static By SelectCallCenter  = By.xpath("//*[contains(@aria-label,'CallCent')]");
	public static By SelectCallCenterNew  = By.xpath("(//*[contains(@aria-label,'CallCent')])[2]");
	public static By SelectAgency  = By.xpath("//*[contains(@aria-label,'agency')]");
	
	public static By AllocatedList  = By.xpath("//*[contains(text(),'Allocated List')]");
	public static By ActionDateFrom  = By.xpath("//*[@id='ActionDateFrom']");
	public static By ActionDateto  = By.xpath("//*[@id='ActionDateto']");
	public static By AllocatedDropdown  = By.xpath("//*[@role='dialog']/..//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By SelectBCODropdown  = By.xpath("//*[contains(text(),'Select BCO')]/..//*[contains(@class,'down')]");
	public static By SelectBCOUser  = By.xpath("(//*[@role='listbox']/..//li[1])[last()]");
	public static By SelectRegion  = By.xpath("(//*[@role='listbox']/..//li[1])[last()]");
	
	public static By CallCenter  = By.xpath("(//*[@role='listbox']/..//*[@aria-label='>Call Center'])[2]");
	public static By BranchAllocatedList  = By.xpath("(//*[@role='listbox']/..//*[@aria-label='>Branch'])[2]");
	public static By Noofallocations  = By.xpath("//div/table/tbody/tr/td[4]/span");
	public static By AllocationPopUpClose  = By.xpath("//a[@role='button']");
	
	
	
	public static By CallCentreCallLogin  = By.xpath("//*[contains(text(),'Call Centre')]");
	public static By AccountFiltration  = By.xpath("//*[contains(text(),'Account Filtration')]");
	public static By AssetDropdown  = By.xpath("//*[contains(text(),'Asset Category')]/..//*[contains(@class,'down')]");
	public static By SMACategorycall  = By.xpath("//*[@aria-label='SMA Category']");
//	public static By TypesofAccount  = By.xpath("//*[contains(text(),'Types of Account')]/..//*[contains(@class,'down')]");
//	public static By SMACategorycall  = By.xpath("//*[@aria-label='SMA Category']");
	
	public static By DropdownAllocatedTo  = By.xpath("//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By CallCentre  = By.xpath("//*[@aria-label='>Call Centre']");
	public static By SMACategoryDropdown  = By.xpath("//*[contains(text(),'SMA Category')]/..//*[contains(@class,'down')]");
	public static By SMACate  = By.xpath("//*[contains(@aria-label,'SMA')][2]");
	public static By AllocatedTodown  = By.xpath("//*[contains(text(),'Allocated To')]/..//*[contains(@class,'down')]");
	public static By AllocatedTodownCall  = By.xpath("//*[@aria-label='>Call Centre']");
	public static By DownloadFileDropdown  = By.xpath("//*[contains(text(),'Download File')]/..//*[contains(@class,'down')]");
	public static By ListofAccounts  = By.xpath("//*[@aria-label='>List of Accounts - Excel']");
	public static By DownloadCallCenter  = By.xpath("//*[text()=' Download']");
	public static By Action  = By.xpath("//*[@id='dropdownMenu2']");
	public static By View  = By.xpath("//*[contains(text(),'View')]");
	public static By ActionOwner  = By.xpath("//*[contains(text(),'')]");
	public static By PleaseEnter  = By.xpath("//*[contains(text(),'Please Enter All Fields In The Interaction Details')]");
	
	public static By DropDownDisposition  = By.xpath("//*[@class='dropdown-item' and text()='Disposition']");
	
	

	
	public static By TypesofAccountDropDown  = By.xpath("//*[text()='Types of Account']/..//*[contains(@class,'right')]");
	public static By AllocationTypeofAccountDropDown  = By.xpath("//*[text()='Allocation Types']/..//*[contains(@class,'right')]");
	public static By NewAccountDashboard  = By.xpath("//*[text()='New Accounts']/..//*[contains(@class,'total')]");
	public static By TodaysCommitmentsDashboard  = By.xpath("//*[@class='tile-title' and text()=\"Today's Commitments\"]/..//*[contains(@class,'total')]");
	public static By Allocated  = By.xpath("//*[@aria-label='>Allocated']");
	public static By NewBeaconIDs  = By.xpath("//*[@aria-label='>new beacondis']");
	public static By NotAllocated  = By.xpath("//*[@aria-label='>Not Allocated']");
	public static By AssetCategoryDropDown  = By.xpath("//*[contains(text(),'Asset Category')]/..//*[contains(@class,'right')]");
	public static By NPACategoryDropDown  = By.xpath("//*[contains(text(),'NPA Category')]/..//*[contains(@class,'right')]");
	public static By VerticalDropDown  = By.xpath("//*[contains(text(),'Vertical')]/..//*[contains(@class,'right')]");
	public static By NPAValue  = By.xpath("//*[@aria-label='NPA Category']");
	public static By LCG  = By.xpath("//*[@aria-label='LCG']");	
	public static By SchemeTypeDropDown  = By.xpath("//*[contains(text(),'Scheme Type')]/..//*[contains(@class,'right')]");
	public static By CAA  = By.xpath("//*[@aria-label='CAA']");	
	
	public static By chkbox  = By.xpath("//*[@class='rz-chkbox-box']");
	public static By DownloadinExcel  = By.xpath("//*[contains(text(),' Download in Excel')]");	
	public static By close  = By.xpath("//*[contains(@class,'close')]");
	//103
	
	
	
	public static By CommitmentTypeLoan   = By.xpath("//*[contains(text(),'Commitment Type')]/..//*[contains(@class,'right')]");
	public static By CommitmentsValue  = By.xpath("//*[contains(@aria-label,'Commitments')]");
	public static By CommitmentFrom  = By.xpath("//*[contains(text(),'Commitment From')]/..//input");
	public static By CommitmentTo  = By.xpath("//*[contains(text(),'Commitment To')]/..//input");
	//107
	public static By LoanAtRiskPOPUPInput  = By.xpath("//label[text()='Zone/CO']");
	public static By Zone  = By.xpath("//label[text()='Zone/CO']/..//input");
	public static By RegionLoan  = By.xpath("//label[text()='Region']/..//input");
	public static By BranchLoan  = By.xpath("//label[text()='Branch']/..//input");
	public static By  Group = By.xpath("//label[text()='Group By']/..//input");
	
	public static By ZoneDisplay  = By.xpath("//label[text()='Zone/CO']");
	public static By RegionLoanDisplay  = By.xpath("//label[text()='Region']");
	public static By BranchLoanDisplay  = By.xpath("//label[text()='Branch']");
	public static By  GroupDisplay = By.xpath("//label[text()='Group By']");
	
	
	
	public static By OSBalance  = By.xpath("//*[contains(text(),'O/s Balance')]/..//input");
	public static By OverdueEMI  = By.xpath("//*[contains(text(),'%Overdue to EMI')]/..//input");
	public static By  DPDinput = By.xpath("//*[contains(text(),'DPD')]/..//input");
	public static By FirstInput  = By.xpath("//input[@type='number' and contains(@onkeypress,'Decimal')]");
	public static By  secondInput = By.xpath("//input[@type='number' and contains(@onkeypress,'Numeric')]");
	public static By  DPDTO = By.xpath("(//input[@type='number' and contains(@onkeypress,'Numeric')])[2]");
	
//	public static By STANDARD  = By.xpath("//ul[contains(@class, 'rz-multiselect-list')]//li[@aria-label='SUB-STANDARD']");
	public static By STANDARD  = By.xpath("(//*[contains(@id,'popup')]/div[1]/div[1]/div[2])[last()]");
	
	
	public static By AccountsAllocation  = By.xpath("//*[contains(text(),'Accounts Allocation')]");
	public static By AgentAccountAllocation  = By.xpath("//*[contains(text(),'Agent Account Allocation')]");
	
	public static By DPDInput  = By.xpath("//*[contains(text(),'DPD From')]/..//input");
	public static By DPDTo  = By.xpath("//*[contains(text(),'DPD To')]/..//input");
	public static By  Amount = By.xpath("//*[contains(text(),'O/s Amount')]/..//input");
	public static By Overdueinput  = By.xpath("//*[contains(text(),'Overdue Amount')]/..//input");
	public static By Scheme  = By.xpath("//*[contains(text(),'Scheme Type')]/..//input");
	public static By Product  = By.xpath("//*[contains(text(),'Product Type')]/..//input");
	public static By  SchemeCodeinput = By.xpath("//*[contains(text(),'Scheme Code')]/..//input");
	public static By Asset  = By.xpath("//*[contains(text(),'Asset Category')]/..//input");
	public static By SMACategoryinput  = By.xpath("//*[contains(text(),'SMA Category')]/..//input");
	public static By NPACategoryinput  = By.xpath("//*[contains(text(),'NPA Category')]/..//input");
	public static By AccountNo  = By.xpath("//*[contains(text(),'Account No')]/..//input");
	public static By AllocationTypes  = By.xpath("//*[contains(text(),'Allocation Types')]/..//input");
	public static By  AssignedTo  = By.xpath("//*[contains(text(),'Assigned To')]/..//input");
	public static By  AssignedToDropDown  = By.xpath("//*[contains(text(),'Assigned To')]/..//*[contains(@class,'right')]");
	public static By Category  = By.xpath("//*[text()='Category']");
	public static By Name  = By.xpath("//*[contains(text(),'')]/..//input[@max='25']");
	public static By CommitmentType  = By.xpath("//*[contains(text(),'Commitment Type')]/..//input");
	
	public static By CategoryType  = By.xpath("//*[text()='Category']/following-sibling::label");
	public static By ReassignTo  = By.xpath("//*[text()='Assign / Reassign To']/..//*[contains(@class,'right')]");
	public static By Assign  = By.xpath("//*[text()='Assign']");
	public static By SelectAccount  = By.xpath("//tbody//*[contains(@class,'box')]");
	public static By SelectAccountName  = By.xpath("//table/tbody/tr[1]/td[2]/span");
	public static By AssignedToGrid  = By.xpath("//table/tbody/tr[1]/td[9]/span");
	public static By AssigneeName  = By.xpath("//table/tbody/tr[1]/td[10]/span");
	public static By SelectAccountNumber  = By.xpath("//table/tbody/tr[1]/td[3]/span");
	public static By SelectAccountNumberDisposition  = By.xpath("//table/tr[2]/td[2]");
	
	public static By SelectTotalDue  = By.xpath("//table/tbody/tr[1]/td[7]/span");
	public static By SelectTotalOutstanding  = By.xpath("//table/tbody/tr[1]/td[8]/span");
	
// Disposition Page
	
	public static By NextActionOwner  = By.xpath("//*[text()='Next Action Owner']/..//*[contains(@class,'right')]");
	public static By NextActionOwnerValue  = By.xpath("(//*[@role='listbox']/..//li[1])[4]");
	//*[contains(@aria-label,'Call Centre')]	
	public static By Disposition  = By.xpath("//*[text()='Disposition']/..//*[contains(@class,'right')]");
	public static By DispositionValue  = By.xpath("(//*[@role='listbox']/..//li[1])[4]");
		
	public static By SubDisposition  = By.xpath("//*[text()='Sub-Disposition']/..//*[contains(@class,'right')]");
	public static By SubDispositionValue  = By.xpath("(//*[@role='listbox']/..//li[1])[4]");
	
	
	public static By NewProduc  = By.xpath("//*[@aria-label='>new produc']");
	public static By NextActionDateCalender  = By.xpath("//*[text()='Next Action Date']/..//input");		
	public static By Save  = By.xpath("//*[@type='submit']");	
	public static By Date  = By.xpath("//*[contains(text(),'')]");	
	public static By Remarks  = By.xpath("//*[text()='Remarks']/..//input");	
	
	
	
	//for disposition history created verification
	public static By ActionDoneBy  = By.xpath("//*[contains(text(),' Action Done By :   ')]");		
	public static By ActionDoneByName  = By.xpath("//*[contains(text(),' Action Done By :   ')]/following-sibling::span");	
	public static By SecurityManagement  = By.xpath("//*[contains(text(),'Security Management')]");	
	public static By UserManagement  = By.xpath("//*[@title='User Management']");
	public static By BCOUserNameInput  = By.xpath("//*[text()='Name']/..//input");
	public static By ResetPassword  = By.xpath("//*[text()='Reset Password']");
	public static By passwordresetsuccessfully  = By.xpath("//*[contains(text(),'The password has been reset successfully.')]");
	public static By RegionDropDown  = By.xpath("//*[contains(text(),'Region')]/..//*[contains(@class,'down')]");
	public static By ZoneDropDown  = By.xpath("//*[contains(text(),'Branch')]/..//*[contains(@class,'down')]");			
	public static By GroupByDropDown  = By.xpath("//*[contains(text(),'Group By')]/..//*[contains(@class,'down')]");	
	public static By GridAccount  = By.xpath("//table/tbody/tr/td[3]/span");	
	public static By DownloadOffline  = By.xpath("//*[contains(text(),'download_for_offline')]");	
	public static By AgencyManagementmainmenu = By.xpath("//span[contains(text(),'Agency Management')]");
	public static By AddAgencySubmenu = By.cssSelector("[title|=\"Add Agency\"]");
	
}
