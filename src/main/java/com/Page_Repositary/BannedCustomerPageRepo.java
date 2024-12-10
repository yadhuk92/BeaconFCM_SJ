package com.Page_Repositary;

import org.openqa.selenium.By;

public class BannedCustomerPageRepo {
	
	public static By SecurityManagementMainMenu = By.xpath("//span[@class='text nav-text' and text()='Security Management']");
	public static By BannedCustomerSubMenu = By.xpath("//a[@class='dropdown-item' and @title='Banned Customer']");

}
