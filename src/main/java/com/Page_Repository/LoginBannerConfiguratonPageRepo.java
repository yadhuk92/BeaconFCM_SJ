package com.Page_Repository;

import org.openqa.selenium.By;import org.openqa.selenium.WebElement;

public class LoginBannerConfiguratonPageRepo {
	public By configurationmenu=By.xpath("//span[text()='Configurations']");
	public By LoginBannerConfimenu =By.xpath("//a[@title='Login Banner Config']");
	public By spinner=By.xpath("(//div[@class='spinner'])");
//	public By spinner2=By.xpath("(//div[@class='spinner'])[2]");
	public By usertypedropdown=By.xpath("//label[text()='User Type']/following-sibling::div[@onmousedown='Radzen.activeElement = null']");
    public By bannertypedropdown=By.xpath("//label[text()='Banner Type']/following-sibling::div[@onmousedown='Radzen.activeElement = null']");
    public By sectiondropdown=By.xpath("//label[text()='Section']/following-sibling::div[@onmousedown='Radzen.activeElement = null']");
    public By headingtext=By.xpath("//textarea[@name='Heading']");
    public By detailtext=By.xpath("//textarea[@name='Detail']");
    public By submitbutton=By.xpath("//button[text()='Submit']");
    public By searchbutton=By.xpath("//button[text()='Search']");
    public By resetbutton=By.xpath("//button[text()='Reset']");
    public By warningmsg=By.xpath("(//div[@class='rz-growl-message']/p)");
    public By UserTypeallvalues=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
    public By Bannertypeallvalue=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
    public By Sectionallvalue=By.xpath("//div[@class='rz-dropdown-panel rz-popup']//li");
    public By header1=By.xpath("(//li[@role='option'])[6]");
    public By profilebutton=By.xpath("//button[@class='btn dropdown-toggle']");
    public By logout=By.xpath("//a[text()='Logout']");
    public By profilepage=By.xpath("(//p[@class='constyle1'])[1]");
    public By hyperlink=By.xpath("(//li[@role='option'])[5]");
    public By sectionlink1=By.xpath("(//li[@role='option'])[6]");
    public By link1OnProfilePage=By.xpath("(//a[@target='_blank'])[1]");
//    public By headerForlink1=By.xpath("(//span[@class='constyle'])[5]");
    public By ProfilePagewithHeaderandDetaillink=By.xpath("(//p[@class='constyle1'])[4]");
    public By SomeErrorOccured=By.xpath("//span[text()='Some error occurred.']");
    public By InvalidUsernameandPassword=By.xpath("//span[text()='Invalid Username Or Password']");
    
}