package com.Page_Repository;

import org.openqa.selenium.By;



public class AddAgencyPageRepo {

	public static By AgencyManagementmainmenu = By.xpath("//span[contains(text(),'Agency Management')]");
	public static By AddAgencySubmenu = By.cssSelector("[title|=\"Add Agency\"]");
		public static By PanField = By.xpath("//label[contains(text(),'PAN Number')]/following-sibling::input");	
	public static By AgencyNameTable = By.xpath("//table/tbody/tr[1]/td[2]/span");
	public static By GSTField = By.xpath("//label[contains(text(),'GST Number')]/following-sibling::input");	
	public static By ConsultaionNameField = By.xpath("//label[contains(text(),'Collection Agency Name')]/following-sibling::input");
	public static By PanInvalidError = By.xpath("//label[contains(text(),'PAN Number')]/following-sibling::input/following-sibling::div");	
	public static By GSTInvalidError = By.xpath("//label[contains(text(),'GST Number')]/following-sibling::input/following-sibling::div");	
	public static By CoolectionAgencyInvalidError = By.xpath("//label[contains(text(),'Collection Agency Name')]/following-sibling::input/following-sibling::div");
	public static By PANNumber = By.xpath("//*[contains(text(),'PAN Number')]/following-sibling::input[contains(@class,'mandatory-color')]");
	public static By CollectionAgencyName = By.xpath("//*[contains(text(),'Collection Agency Name')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By ClearZone = By.xpath("//*[contains(text(),'Zone/CO')]/..//*[contains(@class,'dropdown-clear')]");	
	public static By ClearScheme = By.xpath("//*[contains(text(),'Scheme Type')]/..//*[contains(@class,'dropdown-clear')]");
	public static By Zone = By.xpath("//*[contains(text(),'Zone/CO')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By Region = By.xpath("//*[contains(text(),'Region')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By Scheme = By.xpath("//*[contains(text(),'Scheme Type')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By ProductType = By.xpath("//*[contains(text(),'Product Type')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By ContactNumber = By.xpath("//*[contains(text(),'Contact Number')]/following-sibling::*[contains(@class,'mandatory-color')]");
	//public static By DateEmpanelmentExpiry = By.xpath("//*[contains(text(),'Date of Empanelment Expiry')]/..//input");
	public static By DateEmpanelmentExpiry = By.xpath("//input[@name='Empanelmentexpiry']");
	//public static By DateEmpanelment = By.xpath("//*[contains(text(),'Date of Empanelment')]/..//input");
	public static By DateEmpanelment = By.xpath("(//input[@placeholder='DD-MM-YYYY'])[1]");
	//public static By AgreementStarting = By.xpath("//*[contains(text(),'Agreement Duration Starting')]/..//input");
	public static By AgreementStarting = By.xpath("//input[@name='AgreementStarting']");
	//public static By AgreementEnding = By.xpath("//*[contains(text(),'Agreement Duration Ending')]/..//input");
	public static By AgreementEnding = By.xpath("//input[@name='AgreementEnding']");
	public static By Remarks = By.xpath("//*[contains(text(),'Remarks')]/..//textarea");
	public static By GST = By.xpath("//*[contains(text(),'GST Number')]/..//input");
	public static By Constitution = By.xpath("//*[contains(text(),'Constitution Type')]/following-sibling::*");
	public static By RemarksMandatory = By.xpath("//*[contains(text(),'Remarks')]/following-sibling::*[contains(@class,'mandatory-color')]");
	public static By Close = By.xpath("//*[contains(text(),'Close')]");
	public static By Submit = By.xpath("//*[contains(text(),'Submit')]");
	public static By SaveAsDraft = By.xpath("//*[contains(text(),'Save As Draft')]");
//	public static By allZone = By.xpath("(//*[@class='rz-chkbox-box   '])[14]");//need to update
//	public static By allscheme = By.xpath("(//*[@class='rz-chkbox-box   '])[9]");//need to updat	
	public static By allZone = By.xpath("(//div[@class='rz-chkbox'])[last()]");
	public static By allscheme = By.xpath("(//div[@class='rz-chkbox'])[last()]");
	
	public static By SelectUser  = By.xpath("(//*[@role='listbox']/..//li[1])[last()]");
	public static String SelectUserTC  = "//*[@aria-label='%s']";
	public static By StateValueFromUI  = By.xpath("//*[contains(text(),'State')]/..//label[contains(@class,'dropdown')]");
	public static By CityValueFromUI  = By.xpath("//*[contains(text(),'City')]/..//label[contains(@class,'dropdown')]");
	public static By Address = By.xpath("//*[contains(text(),'Address')]/..//input");
	public static By State = By.xpath("//*[contains(text(),'State')]/..//div");
	public static By City = By.xpath("//*[contains(text(),'City')]/..//div");
	public static By ContactNumberinput = By.xpath("//*[contains(text(),'Contact Number')]/..//input");	
	public static By InvalidContactNumber = By.xpath("//*[contains(text(),'Invalid Contact Number')]");
	public static By EmpanelmentRequired = By.xpath("//*[contains(text(),'Empanelment Date is Required')]");
	public static By EmpanelmentExpiryDate = By.xpath("//*[contains(text(),'Empanelment Expiry Date is Required')]");
	public static By AgreementStartingError = By.xpath("//*[contains(text(),'Agreement Starting is Required')]");
	public static By AgreementEndingError = By.xpath("//*[contains(text(),'Agreement Ending is Required')]");
	public static By RemarksError = By.xpath("//*[contains(text(),'Remarks is Required')]");
	public static By SMA = By.xpath("//*[@title='SMA 0 (01-30 Days)']");
	public static By ModeOfCollection = By.xpath("//*[contains(text(),'Mode Of Collection')]/following-sibling::*");
	public static By Cheque = By.xpath("//*[contains(@aria-label,'Cheque')]");
	public static By CollectionLimitInGrid = By.xpath("//table//*[contains(text(),'Collection Limit')]");
	public static By ModeInGrid = By.xpath("//table//*[contains(text(),'Mode')]");
	public static By Symbols = By.xpath("//table/tr[2]/td[2]//*[@class='rz-dropdown-label rz-inputtext ']");
	public static By CollectionLimitAmount = By.xpath("//input[@name='CollectionLimit']");
	public static By TOTALOUTSTANDING = By.xpath("//*[contains(text(),'TOTAL OUTSTANDING BASED ON')]");
	public static By loader = By.xpath("//*[@class='spinner']");
	public static By AddNewAgency = By.xpath("//*[contains(text(),'Add New Agency')]");
	public static By AgencyName = By.xpath("//*[contains(text(),'Agency Name')]/..//input");
	public static By Usercreatedsuccessfully = By.xpath("//*[contains(text(),'User created successfully . Preset password for the user:')]");
	public static By Action = By.xpath("//*[@id='dropdownMenu3']");
	public static By AgencyUser = By.xpath("//*[@class='emailuser']");
	public static By PANNumberalreadyexists = By.xpath("//*[contains(text(),'PAN Number already exists.')]");
	public static By AgencyList = By.xpath("//*[@title='Agency List']");
	public static By AlreadyUsedPAN = By.xpath("//table/tbody/tr[1]/td[3]/span");
	
	
	
	
	
	public void labels(String fieldName)
	{
		String filednames= "//label[contains(text(), '" + fieldName + "')]";
	}
	
	public static By DateOfEmpanelment_Date(String Date) {
	    String xpathExpression;

	        xpathExpression = "(//span[text()='"+Date+"'])[4]";

	    return By.xpath(xpathExpression);
	}
}