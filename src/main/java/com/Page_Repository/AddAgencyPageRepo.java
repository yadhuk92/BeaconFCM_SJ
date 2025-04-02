package com.Page_Repository;

import org.openqa.selenium.By;

public class AddAgencyPageRepo {

	public static By AgencyManagementmainmenu = By.xpath("//span[contains(text(),'Agency Management')]");

	public static By AddAgencySubmenu = By.cssSelector("[title|=\"Add Agency\"]");
	
	public static By PanField = By.xpath("//label[contains(text(),'PAN Number')]/following-sibling::input");
	
	public static By GSTField = By.xpath("//label[contains(text(),'GST Number')]/following-sibling::input");
	
	public static By ConsultaionNameField = By.xpath("//label[contains(text(),'Collection Agency Name')]/following-sibling::input");

	
	
	public static By PanInvalidError = By.xpath("//label[contains(text(),'PAN Number')]/following-sibling::input/following-sibling::div");
	
	public static By GSTInvalidError = By.xpath("//label[contains(text(),'GST Number')]/following-sibling::input/following-sibling::div");
	
	public static By CoolectionAgencyInvalidError = By.xpath("//label[contains(text(),'Collection Agency Name')]/following-sibling::input/following-sibling::div");


	
	
	
	public void labels(String fieldName)
	{
		String filednames= "//label[contains(text(), '" + fieldName + "')]";

	
	 
	}
	
}