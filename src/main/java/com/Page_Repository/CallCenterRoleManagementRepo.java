package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCenterRoleManagementRepo {
	
	public static By CallcenterUserID  = By.xpath("//*[@class='emailuser']");
	public static By emailuser = By.xpath("//*[@class='emailuser']");
	public static By SecurityManagement = By.xpath("//*[contains(text(),'Security Management')]");
	public static By RoleManagement = By.xpath("//*[contains(text(),'Role Management')]");
	public static By RoleManagementHeader = By.xpath("//*[@class='header']//*[contains(text(),'Role Management')]");
	public static By AddNewRole = By.xpath("//*[contains(text(),'Add New Role')]");
	public static By search = By.xpath("//*[@type='search']");
	public static By searchsubmit = By.xpath("//*[@type='submit']");
	public static By RoleName = By.xpath("//*[@name='RoleName']");
	public static By FunctionalityLevel = By.xpath("//*[text()='Functionality Level']");
	public static By Functionalities = By.xpath("//*[text()='Functionalities']");
	public static By Save = By.xpath("//*[text()='Save']");
	public static By Cancel = By.xpath("//*[text()='Cancel']");
	public static By EnterRoleName = By.xpath("//*[contains(text(),'Enter Role Name')]");
	public static By RolePermission = By.xpath("//*[text()='Role Permission']");
//	public static By RoleName = By.xpath("//*[@name='RoleName']");
	public static By RecordSavedSuccessfully = By.xpath("//*[contains(text(),'Record Saved Successfully')]");
	public static By Edit = By.xpath("//*[contains(text(),'Edit')]");
	public static By RecordupdatedSuccessfully = By.xpath("//*[contains(text(),'Record Updated Successfully')]");
	public static By LevelExistAlready  = By.xpath("//*[contains(text(),'Level Exist Already')]");
	public static By PleaseSelectFunctionality = By.xpath("//*[contains(text(),'Please Select A Functionality')]");
	public static By ActionMenu = By.xpath("//*[@id='dropdownMenu2']");
	public static By closeRolePermissionPopup  = By.xpath("//*[contains(@class,'close')]");
	public static By FunctionalityCheckbox = By.xpath("//*[@type='checkbox']");
	public static By Next = By.xpath("//*[contains(text(),'Next')]");
//	driver.findElement(CallCenterRoleManagementRepo.SecurityManagement).click();
}
