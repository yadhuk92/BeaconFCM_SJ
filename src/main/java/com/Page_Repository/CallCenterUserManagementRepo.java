package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCenterUserManagementRepo {

	//Textfield
	public static By clickuser = By.xpath("//span[contains(text(),'Security Management')]");
	public static By clickUserman = By.xpath("//a[contains(text(),'User Management')]");
	public static By executiveId = By.xpath("//label[contains(text(),'Executive ID')]");
	public static By username = By.xpath("//label[contains(text(),'Username')]");
	public static By name = By.xpath("//label[contains (text(),'Name')]");
	public static By mobile = By.xpath("//label[contains(text(),'Mobile')]");
	public static By email = By.xpath("//label[contains(text(),'Email ID')]");
	public static By searchBtn = By.xpath("//*[@type='submit']");
	public static By addNewUserBtn = By.xpath("//*[contains(text(),'Add User')]");

	//table
	public static By slNo = By.xpath("//*[text()='SL No']");
	public static By usernametable = By.xpath("//*[text()='User Name']");
	public static By nametable = By.xpath("//*[text()='Name']");
	public static By executiveIdtable = By.xpath("//*[text()='Executive ID']");
	public static By emailtable = By.xpath("//*[text()='Email ID']");
	public static By mobileNumber = By.xpath("//*[text()='Mobile No']");
	public static By role = By.xpath("//*[text()='Role']");
	public static By status = By.xpath("//*[text()='Status']");
	public static By actions = By.xpath("//*[text()='Action']");
	
	//inside add user
	
	public static By EXID = By.xpath("//label[contains(text(),'Executive ID')]");
	public static By NAME = By.xpath("//label[contains(text(),'Name')]");
	public static By EMAIL = By.xpath("//label[contains(text(),'Email')]");
	public static By PNO = By.xpath("//label[contains(text(),'Phone Number')]");
	public static By ROLE = By.xpath("//label[contains(text(),'Role')]");
	public static By TYEAR = By.xpath("//label[contains(text(),'Tenurity')]");
	public static By TLEADE = By.xpath("//label[contains(text(),'Team Leader')]");
	public static By DOB = By.xpath("//label[contains(text(),'Date of Joining ')]");
	public static By close= By.xpath("//*[@type='reset']");
	public static By submit2 = By.xpath("//*[@type='submit']");
	
	//Leave empty
	
	public static By clickonsubmit = By.xpath("");
	
	public static By EXID1 = By.xpath("//*[contains (text(), 'Executive id is required')]");
	public static By NAME1 = By.xpath("//*[contains (text(), 'Name is required')]");
	public static By EMAIL1 = By.xpath("//*[contains (text(), 'Email is required')]");
	public static By PNO1 = By.xpath("//*[contains (text(), 'Phone number is required')]");
	public static By ROLE1 = By.xpath("//*[contains (text(), 'Role is required')]");
	
	//enter data
	
	public static By addexecutive= By.xpath("//label[text()='Executive ID']/following-sibling::input");
	public static By addname= By.xpath("//label[text()='Name']/following-sibling::input");
	public static By addEMAIL= By.xpath("//label[text()='Email']/following-sibling::input");
	public static By addPNO = By.xpath("//label[text()='Phone Number']/following-sibling::input");
	public static By addROLE = By.xpath("//label[text()='Role']/following::div[contains(@class,'rz-dropdown-trigger')][1]");
	public static By enterRole= By.xpath("//*[text()='CallCentreRole']");
}
