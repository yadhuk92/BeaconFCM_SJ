package com.Page_Repository;

import org.openqa.selenium.By;

public class AgentListPageRepo {

	public static By AgentManagement = By
			.xpath("//*[contains(text(),'Agent Management')]");
	public static By AgentManagement1 = By
			.xpath("(//*[contains(text(),'Agent Management')])[2]");
	
	public static By AgencyName = By.xpath("//*[@class='emailuser']");
	public static By AddNewAgent = By.xpath("//*[contains(text(),'Add New Agent')]");
	public static By Search = By.xpath("//*[contains(text(),'Search')]");
	public static By AddNewAgent1 = By.xpath("(//*[contains(text(),'Add New Agent')])[2]");
	
	
	public static By AgentList = By.xpath("//*[contains(text(),'Agent List')]");
	public static By UploadInvoice = By.xpath("//*[contains(text(),'Upload Invoice')]");
	public static By AgentCode = By.xpath("//*[@for='name']/..//*[@type='search']");
	public static By UserName = By.xpath("//*[@for='name']/..//*[contains(text(),'User Name')]");
	public static By UserNameInput = By.xpath("//*[@for='name']/..//*[contains(text(),'User Name')]/..//input");
	
	public static By AgentName1 = By.xpath("(//*//input[@type='search'])[3]");
	public static By Search1 = By.xpath("//*[@type='submit']");
	
	public static By AgentName = By.xpath("//*[@for='name']/..//*[contains(text(),'Agent Name')]");
	public static By Mobile = By.xpath("//*[@for='name']/..//*[contains(text(),'Mobile Number')]");
	public static By Role = By.xpath("//*[@for='name']/..//*[contains(text(),'Role')]");
	public static By Active = By.xpath("//*[@for='name']/..//*[contains(text(),'Is Active')]");
	public static By spinner = By.xpath("//*[@class='spinner']");
	public static By AddAgent = By.xpath("//*[contains(text(),'Add Agent')]");
	public static By html = By.xpath("//html");
	
	//Add new Agent page
	
	public static By Submit = By.xpath("//button[contains(text(),'Submit')]");
	public static By Close = By.xpath("//*[contains(text(),'Close')]");
	public static By Tenurity = By.xpath("//*[text()='Tenurity']/..//input");
	public static By PhoneNumberAddNewAgent = By.xpath("//*[@name='Mobile']");

	public static By DateofJoining = By.xpath("//*[contains(text(),'Date of Joining ')]");
	public static By AgentCodeAddNewAgent = By.xpath("//*[@for='name']/..//*[contains(text(),'Agent Code')]");
	public static By AgentCodeEdit = By.xpath("//*[@for='name']/..//*[contains(text(),'Agent Code')]/..//input");
	
	public static By Name = By.xpath("//*[@name='Name']");
	public static By Name1 = By.xpath("(//*[contains(text(),'Name')]/..//input)[2]");
	
	public static By RoleAddNewAgent = By.xpath("//*[text()='Role']/..//div");
	public static By ClearRole = By.xpath("//*[contains(@class,'dropdown-clear-icon')]");
	
	public static By CurrentDate = By.xpath("//input[@id='Date']");
	public static By ErrorForMandotoryFields = By.xpath("//*[@style='display: block']");
	public static By InvalidPhoneNumber = By.xpath("//*[contains(text(),'Invalid Phone Number')]");
//	public static By agenntrole = By.xpath("//*[@aria-label='>agenntrole']");
	public static By agenntrole = By.xpath("(//*[@role='listbox']/..//li[1])[3]");
	
	public static By NameAgentCode = By.xpath("//*[@name='Name']");
	public static By NameAgentName = By.xpath("//*[@id='uwkNjTLdHE']");
	public static By MobileInput = By.xpath("//*[@name='Mobile']");
	public static By Usercreatedsuccessfully = By.xpath("//*[contains(text(),'User created successfully . Preset password for the user:')]");
	public static By AlreadyUserExist = By.xpath("//*[contains(text(),'Already User Exist ')]");
	public static By DateOfJoining = By.xpath("//*[@id='Date']");
	public static By DatePicker = By.xpath("//*[contains(text(),'Date of Joining ')]/..//*[@type='button']");
	
	public static By NoRecordToDisplay = By.xpath("//*[contains(text(),'No records to display.')]");
//	Action
	public static By ActionMenu = By.xpath("//*[@id='dropdownMenu2']");
	public static By Edit = By.xpath("//button[contains(text(),'Edit')]");
	public static By ActivateDeactivate = By.xpath("//button[contains(text(),'Activate/De-activate')]");
	public static By ResetPassword = By.xpath("//button[contains(text(),'Reset Password')]");
	public static By StatusChanged = By.xpath("//*[contains(text(),'Status Changed')]");
	public static By InactiveStatus = By.xpath("//*[@style='color:red']");
	public static By ActiveStatus = By.xpath("//*[@style='color:green']");
	public static By PasswordResetStatus = By.xpath("//*[contains(text(),'The password has been reset successfully. Present  password for the user')]");
	public static By Action = By.xpath("//*[@id='dropdownMenu2']//span");
	
	
//	Edit Agent
	public static By Update = By.xpath("//*[@type='submit']");
	
	public static By Update1 = By.xpath("//button[contains(text(),'Update')]");
	public static By IsActive = By.xpath("//*[contains(text(),'Is Active')]/..//div");
	public static By EditAgent = By.xpath("//*[contains(text(),'Edit Agent')]");
	public static By RecordUpdatedSuccessfully = By.xpath("//*[contains(text(),'Record Updated Successfully')]");
	public static By UpdatedUsername = By.xpath("//table/tbody/tr[1]/td[3]");
	
//Calender
	public static By PreveousMonth = By.xpath("//*[contains(@class,'datepicker-prev-icon')]]");
	public static By NextMonth = By.xpath("//*[contains(@class,'datepicker-next-icon')]");
	
	
	
}
