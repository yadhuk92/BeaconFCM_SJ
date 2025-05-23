package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAgencyListRepo {
//	public static By  = By.cssSelector("");
	public static By loader = By.xpath("//*[@class='spinner']");
	public static String SelectUserTC = "//*[@aria-label='%s']";
	public static By Level = By.xpath("//*[@class='name']");
	public static By AgencyManagementmainmenu = By.xpath("//span[contains(text(),'Agency Management')]");
	public static By AgencyList = By.xpath("//*[@title='Agency List']");
	public static By Action = By.xpath("//*[@id='dropdownMenu3']");
	public static By Next = By.xpath("//*[contains(text(),'Next')]");
	public static By AddNewAgency = By.xpath("//*[contains(text(),'Add New Agency')]");
	public static By AgencyName = By.xpath("//*[contains(text(),'Agency Name')]/..//input");
	public static By PANNumber = By.xpath("//*[contains(text(),'PAN Number')]/..//input");
	public static By Zone = By.xpath(
			"//*[contains(text(),'Zone/CO')]/following-sibling::*");
	public static By Region = By.xpath(
			"//*[contains(text(),'Region')]/following-sibling::*[contains(@class,'rz-dropdown valid rz-clear form-control')]");
	public static By AgencyNameFromList = By.xpath("//table/tbody/tr[2]/td[2]/span");
	public static By PANFromList = By.xpath("//table/tbody/tr[2]/td[3]/span");
	public static By AgencyNameFromListResult = By.xpath("//table/tbody/tr[1]/td[2]/span");
	public static By PANFromListResult = By.xpath("//table/tbody/tr[1]/td[3]/span");
	public static By AdressFromListResult = By.xpath("//table/tbody/tr[1]/td[4]/span");
	public static By Search = By.xpath("//*[contains(text(),'Search')]");
	public static By SystemID = By.xpath("//table/tbody/tr[1]/td[1]/span");
	public static By Previous = By.xpath("//*[contains(text(),'Previous')]");
	public static By Edit = By.xpath("//ul[@class='dropdown-menu show']/..//*[contains(text(),'Edit')]");
	public static By ActivateDeactvate = By
			.xpath("//ul[@class='dropdown-menu show']/..//*[contains(text(),'Activate/De-activate')]");
	public static By ResetPassword = By
			.xpath("//ul[@class='dropdown-menu show']/..//*[contains(text(),'Reset Password')]");
	public static By Deempanel = By.xpath("//ul[@class='dropdown-menu show']/..//*[contains(text(),'De-empanel')]");
	public static By Contactnumber = By.xpath("//*[@name='Contactnumber']");
	public static By Pan = By.xpath("//*[@name='Pan']");
	public static By NameEdit = By.xpath("//*[@name='Name']");
	public static By ZoneEdit = By.xpath(
			"//*[contains(text(),'Zone/CO')]/..//div[@class='rz-dropdown rz-state-disabled valid rz-clear form-control']//label");
	public static By RegionEdit = By.xpath(
			"//*[contains(text(),'Region')]/..//div[@class='rz-dropdown rz-state-disabled valid rz-clear form-control']");
	public static By ProductEdit = By.xpath(
			"//*[contains(text(),'Product Type')]/..//div[@class='rz-dropdown rz-state-disabled valid rz-clear form-control']");
	public static By Remarks = By.xpath("//*[@name='Remarks']");
	public static By DateEmpanelment = By
			.xpath("//*[contains(text(),'Date of Empanelment')]/..//input[@placeholder='DD-MM-YYYY']");
	public static By DateEmpanelmentExpiry = By
			.xpath("//*[contains(text(),'Date of Empanelment Expiry')]/..//input[@placeholder='DD-MM-YYYY']");
	public static By DateStarting = By
			.xpath("//*[contains(text(),'Agreement Duration Starting')]/..//input[@placeholder='DD-MM-YYYY']");
	public static By DateEnding = By
			.xpath("//*[contains(text(),'Agreement Duration Ending')]/..//input[@placeholder='DD-MM-YYYY']");
	public static By Update = By.xpath("//*[contains(text(),'Update')]");
	public static By Address = By.xpath("//*[@name='Address']");
	public static By RecordUpdatedSuccessfully = By.xpath("//*[contains(text(),'Record Updated Successfully')]");
	public static By InvalidUsername = By.xpath("//*[contains(text(),'Invalid Username Or Password')]");

	public static By Close = By.xpath("//*[contains(text(),'Close')]");
	public static By CollectionAgencyName = By.xpath("//*[contains(text(),'Collection Agency Name')]");
	public static By Activate = By.xpath("//*[contains(text(),'Activate Date')]");
	public static By ActivateDate = By.xpath("//*[contains(text(),'Activate Date')]/..//input");
	public static By DeactivateDate = By.xpath("//*[contains(text(),'Deactivate Date')]/..//input");
	public static By CurrentStatus = By.xpath("//*[contains(text(),'Current Status')]/..//input");
	public static By CollectionAgencyNameInput = By.xpath("//*[contains(text(),'Collection Agency Name')]/..//input");
	public static By submit = By.xpath("//*[@type='submit']");
	public static By passwordresetsuccessfully = By
			.xpath("//*[contains(text(),'The password has been reset successfully.')]");
	public static By StatusChanged = By.xpath("//*[contains(text(),'Status Changed')]");
	public static By DeEmpanelmentPopUp = By.xpath("//*[contains(text(),'De-Empanelment')]");
	public static By EmpaneledDate = By.xpath("//*[contains(text(),'Empaneled Date')]/..//input");
	public static By DeEmpanelmentDate = By.xpath("//*[contains(text(),'De-empaneled Date')]/..//input");
	public static By Remark = By.xpath("//*[contains(text(),'Remark')]/..//textarea");
	public static By DeEmpanelmentdaterequired = By.xpath("//*[contains(text(),'De-Empanelment date is required')]");
	public static By RecordDeempaneledSuccessfully = By
			.xpath("//*[contains(text(),'Record De-empaneled Successfully')]");
	public static By profiledropdownbutton = By.xpath("//*[@class='dropdown profiledropdown']//button[@type='button']");

	public static By Logout = By.xpath("//*[text()='Logout']");
	public static By IncorrectUsername = By.xpath("//*[contains(text(),'Incorrect Username Or Password')]");
	public static By LastPageOfTheList = By.xpath("//*[contains(text(),'>>')]");
	public static By LastPageCount = By.xpath("//li[contains(@class, 'page-item') and contains(@class, 'active')]/span[@class='page-link']");
	public static By TotalAgencyRecords  = By.xpath("//table/tbody/tr[10]/td[1]/span");
	
	

}
