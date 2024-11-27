package com.Page_Repositary;

import org.openqa.selenium.By;

public class LoginPageRepo {
	 
	public static By UserNameField = By.xpath("//input[@placeholder='User Name']");
	public static By PasswordField = By.xpath("//input[@placeholder='Password']");
	public static By LoginButton = By.xpath("//button[@type='submit']");
	public static By Spinner = By.xpath("//div[@class='spinner']");
	public static By LoginHyperlink2Banner = By.xpath("//a[text()='https://docs.google.com/spreadsheets/d/1...']");
	public static By AlreadyLoginPopupYesButton = By.xpath("//span[text()='Yes' and @class='rz-button-text']");
}

