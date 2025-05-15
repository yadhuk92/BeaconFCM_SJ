package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreRoleManagementRepo {
	
public By loginSelectionSpinner = By.xpath("//div[@class='spinner']");	
	
public By SecurityManagement = By.xpath("//span[text()='Security Management']");

public By RoleManagement = By.xpath("//a[@title='Role Management']");

public By AddNewRole = By.xpath("//button[@class='addbutton btn btn-primary rounded-pill']");

public By RoleName = By.xpath("//input[@name='RoleName']");


}
