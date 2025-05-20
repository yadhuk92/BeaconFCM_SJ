package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAgentListRepo {
	public static By AgentManagement = By.xpath("//*[contains(text(),'Agent Management')]");
	public static By AddNewAgent = By.xpath("//*[contains(text(),'Add New Agent')]");	
	public static By AgentCode = By.xpath("//*[contains(text(),'Agent Code')]/..//input");
	public static By Name = By.xpath("//*[contains(text(),'Name') and @for='name']/..//input");	
	public static By PhoneNumber = By.xpath("//*[contains(text(),'Phone Number')]/..//input");
	public static By Tenurity = By.xpath("//*[contains(text(),'Tenurity')]/..//input");	
	public static By AgentRequired = By.xpath("//*[contains(text(),'Agent Code is Required')]");
	public static By NameRequired = By.xpath("//*[contains(text(),'Name is Required')]");	
	public static By PhoneRequired = By.xpath("//*[contains(text(),' Number is Required')]");
	public static By RoleRequired = By.xpath("//*[contains(text(),'Role is Required')]");	
	public static By Search = By.xpath("//*[contains(text(),'Search')]");	
	public static By UsernameSearch = By.xpath("//table/tbody/tr/td[2]/span");
	public static By AgentCodeSearch = By.xpath("//table/tbody/tr/td[3]/span");	
	public static By MobileNoSearch = By.xpath("//table/tbody/tr/td[6]/span");	
	public static By StatusSearch = By.xpath("//table/tbody/tr/td[7]/span/i");	
	public static By StatusSearchInactive = By.xpath("//table/tbody/tr/td[8]/span/i");	
	
	public static By AgencyManagement = By.xpath("//*[contains(text(),'Agency Management')]");	
	public static By AgentList = By.xpath("//a[@title='Agent List']");	
	public static By UserName_HO = By.xpath("//*[contains(text(),'User Name')]/..//input");
//	public static By AgencyName = By.xpath("//*[contains(text(),'Agency Name')]/..//input");
	public static By AgencyName = By.xpath("//*[contains(text(),'Agency Name')]/..//*[ contains(@class,'down')]");
	
	public static By searchDropdown = By.xpath("(//*[contains(@id,'search')])[2]");
	public static By AgencyDropdown = By.xpath("(//*[@role='listbox']/..//li[1])[2]");
	public static By Reset  = By.xpath("//*[contains(text(),'Reset')]");
	public static By AgencyNameisRequired = By.xpath("//*[contains(text(),' Agency Name is Required')]");
	public static By RoleAddNewAgent = By.xpath("//*[text()='Role']/..//div");
	public static By ClearRole = By.xpath("//*[contains(@class,'dropdown-clear-icon')]");
	public static By agenntrole = By.xpath("(//*[@role='listbox']/..//li[1])[3]");
	public static By DateOfJoining = By.xpath("//*[@id='Date']");
	public static By DatePicker = By.xpath("//*[contains(text(),'Date of Joining ')]/..//*[@type='button']");
	public static By Submit = By.xpath("//button[contains(text(),'Submit')]");
	public static By Close = By.xpath("//*[contains(text(),'Close')]");
	public static By IsActive = By.xpath("//*[contains(text(),'Is Active')]/..//div");
	public static By NoRecordToDisplay = By.xpath("//*[contains(text(),'No records to display.')]");
	public static By Action = By.xpath("//*[@id='dropdownMenu2']//span");
	public static By ActivateDeactivate = By.xpath("//button[contains(text(),'Activate/De-activate')]");
	
}
