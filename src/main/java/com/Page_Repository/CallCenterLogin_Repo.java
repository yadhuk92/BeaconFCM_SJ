package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCenterLogin_Repo {

	public static By username = By.xpath("//input[@placeholder='User Name']");
	public static By password = By.xpath("//input[@placeholder='Password']");
	public static By login = By.xpath("//button[contains(text(),'LOGIN')]");
	
	
}
