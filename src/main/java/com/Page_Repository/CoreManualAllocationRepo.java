package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreManualAllocationRepo {
	
	public static By manualallocationsubmenu = By.xpath("//a[@title='Manual Allocation']");
	public static By allocationName = By.xpath("//label[contains(text(),'Allocation Name')]//following-sibling::div[@onmousedown='Radzen.activeElement = null' and @tabindex='0']");
	public static By zone_co = By.xpath("//label[contains(text(),'Zone/CO')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By region = By.xpath("//label[contains(text(),'Region')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By branch = By.xpath("//label[contains(text(),'Branch')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By branch_id = By.xpath("//label[contains(text(),'Branch ID')]//following-sibling::input[@name='Name']");
	public static By vertical = By.xpath("//label[contains(text(),'Vertical')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By schemetype = By.xpath("//label[contains(text(),'Scheme Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By producttype = By.xpath("//label[contains(text(),'Product Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By schemecode = By.xpath("//label[contains(text(),'Scheme Code')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By assettaggingtype = By.xpath("//label[contains(text(),'Asset Tagging Type')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By assetcategory = By.xpath("//label[contains(text(),'Asset Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By smacategory = By.xpath("//label[contains(text(),'SMA Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By npacategory = By.xpath("//label[contains(text(),'NPA Category')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By dpdoperatorsdropdown = By.xpath("//label[contains(text(),'DPD')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By dpdtextfield = By.xpath("(//label[contains(text(),'DPD')]//following::input[@min='0' and @maxlength='35'])[1]");
	public static By osbalanceoperatorsdropdown = By.xpath("//label[contains(text(),'O/S Balance ')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By OutstandingBalLimitOperatordropdownvalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[3]");
    }
	public static By osbalancetextfield = By.xpath("//label[contains(text(),'O/S Balance ')]//following::input[@min='0' and @maxlength='35']");
	public static By overdueemioperatorsdropdown = By.xpath("//label[contains(text(),'%Overdue to EMI')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By overdueemiamounttextfield = By.xpath("//label[contains(text(),'%Overdue to EMI')]//following::input[@maxlength='3']");
	public static By actionowner = By.xpath("//label[contains(text(),'Action Owner')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By ActionDateFrom = By.xpath("//label[contains(text(),'Action Date From')]//following-sibling::div[@class='valid']");
	public static By ActionDateto = By.xpath("//label[contains(text(),'Action Date to')]//following-sibling::div[@class='valid']");
	public static By TypesofAccount = By.xpath("//label[contains(text(),'Types of Account')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By To = By.xpath("//label[(text()='To')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By tovalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[2]");
    }
	public static By IsPFTNPA = By.xpath("//label[(text()='Is PFTNPA')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By IsFTNPA = By.xpath("//label[(text()='Is FTNPA')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By SaveThisAllocationCriteria = By.xpath("//label[(text()='Save This Allocation Criteria')]//following-sibling::div[contains(@class,'rz-chkbox')]");
	public static By Searchbtn = By.xpath("//button[@type='submit' and text()='Search']");
	public static By Resetbtn = By.xpath("//button[@type='reset' and text()='Reset']");
	public static By EditAllocationCriteriabtn = By.xpath("//button[@type='button' and text()='Edit Allocation Criteria']");
	public static By AllocateTo = By.xpath("//label[(text()='Allocate To')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By ExpiryDate  = By.xpath("//label[contains(text(),'Expiry Date ')]//following::input[@name='ExpiryDate']");
	public static By Assignbtn = By.xpath("//button[@type='submit' and text()='Assign']");
	public static By  DownloadinExcel = By.xpath("//button[@type='submit' and text()=' Download in Excel']");
	public static By AssignedList = By.xpath("//a[@target='_blank']");
	public static By warningMessage = By.xpath("//span[@class='rz-growl-title']//following-sibling::p[text()='Asset Category Required']");
	public static By assetcategorysellectall = By.xpath("(//label[text()='Asset Category']//following::div[@class='rz-chkbox-box   '])[4]");
	public static By smacategorysellectall = By.xpath("(//label[text()='SMA Category']//following::div[contains(@class,' rz-multiselect-header')]//following-sibling::div[@class='rz-chkbox'])[4]");
	public static By npacategorysellectall = By.xpath("(//label[text()='NPA Category']//following::div[contains(@class,' rz-multiselect-header')]//following-sibling::div[@class='rz-chkbox'])[4]");
	public static By TotalAccountSelected = By.xpath("(//span[@class='rz-cell-data'])[1]");
	public static By TotalOutStandingAmount = By.xpath("(//span[@class='rz-cell-data'])[2]");
	public static By Disposition = By.xpath("//span[text()='Disposition']");
	public static By UpdationofDisposition = By.xpath("//a[text()='Updation of Disposition']");
	public static By AccountNumbertextfield = By.xpath("//input[@placeholder='Account Number']");
	public static By Searchbut = By.xpath("//input[@value='Search']");
	public static By CheckLiveBalancebut = By.xpath("//input[@value='Check Live Balance']");
	public static By TransactionDetailsbut = By.xpath("//input[@value='Transaction Details']");
	public static By warningmsg = By.xpath("//p[text()='You are not authorized to do the disposition of this account number']");
	public static By downloadedmsg = By.xpath("//p[text()='File downloaded successfully']");
	public static By Tofieldmandatorywarningmsg = By.xpath("//p[text()='To is Required']");
	public static By SelectCallCentre = By.xpath("//label[contains(text(),'Select Call Centre')]//following-sibling::div[@onmousedown='Radzen.activeElement = null']");
	public static By SelectCallCentrevalue(String value) {
        return By.xpath("//li[@ role='option' and @aria-label='>" + value + "']");
    }
	public static By validationMessage = By.xpath("//p[text()='Assigned Successfully']");
	public static By validationMessageforinvalidaccountnumber = By.xpath("//p[text()='Invalid Account Number']");
	public static By interactionDetailsField = By.xpath("//table[@style='border: none;border-collapse: collapse;']");
	public static By actionOwner = By.xpath("//label[text()='Next Action Owner']//following-sibling::div");
	public static By dispositionType = By.xpath("//label[text()='Disposition']//following-sibling::div");
	public static By subDisposition = By.xpath("//label[text()='Sub-Disposition']//following-sibling::div");
	public static By nextActionDate = By.xpath("//label[text()='Next Action Date']//following-sibling::div");
	public static By remark = By.xpath("//label[text()='Remarks']//following-sibling::input");
	public static By saveButton = By.xpath("//button[@type='submit']");
	public static By cancelButton = By.xpath("//button[text()='Cancel']");
	public static By internalUserOption(String value) {
        return By.xpath("//li[@aria-label='>" + value + "']");
        }
        public static By connectedOption(String value) {
            return By.xpath("//li[@aria-label='>" + value + "']");
    }
        public static By callBackOption(String value) {
            return By.xpath("//li[@aria-label='>" + value + "']"); 
    }
        public static By nextActionDatevalue(String value) {
            return By.xpath("//span[@class='rz-state-default' and text()='" + value + "']"); 
    }
        public static By validationMessageforInteractionDetails = By.xpath("//p[text()='Saved Successfully']");
        public static By interactionHistorySection = By.xpath("(//div[@class='col-sm-12'])[1]");
}
