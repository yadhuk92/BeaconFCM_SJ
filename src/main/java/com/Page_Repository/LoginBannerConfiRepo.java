package com.Page_Repository;

import org.openqa.selenium.By;

public class LoginBannerConfiRepo {
	public By configurationmenu=By.xpath("//span[text()='Configurations']");
	public By LoginBannerConfimenu =By.xpath("//a[@title='Login Banner Config']");
	public By spinner=By.xpath("//div[@class='spinner']");
	public By usertypedropdown=By.xpath("(//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder'])[1]");
    public By bannertypedropdown=By.xpath("//label[text()='Banner Type']/following-sibling::div[@onmousedown='Radzen.activeElement = null']");
    public By sectiondropdown=By.xpath("//label[text()='Section']/following-sibling::div[@onmousedown='Radzen.activeElement = null']");
    public By headingtext=By.xpath("//textarea[@name='Heading']");
    public By detailtext=By.xpath("//textarea[@name='Detail']");
    public By submitbutton=By.xpath("//button[text()='Submit']");
    public By searchbutton=By.xpath("//button[text()='Search']");
    public By resetbutton=By.xpath("//button[text()='Reset']");
    public By warningmsg=By.xpath("(//div[@class='rz-growl-message']/p)");
    public By internaluser=By.xpath("//li[@role='option' and @aria-label='>Internal User']");
    public By usertypesearch=By.xpath("(//input[@class='rz-dropdown-filter rz-inputtext   ' and @type='text'])[3]");
    public By UserTypeallvalues=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
    public By Bannertypeallvalue=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
    public By Sectionallvalue=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
}
