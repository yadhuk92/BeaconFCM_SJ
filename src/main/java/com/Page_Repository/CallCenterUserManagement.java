package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCenterUserManagement {

	//Security Management >> User Management Page

 
	 
	 public static By ExecutiveID = By.xpath("//body/div[@id='app']/div[@class='page_split_wrapper']/div[@class='rightmain-section']/div[@class='main']/div/form/div[contains(@class,'card-wrapper')]/div[@class='mycard-header row']/div[@class='form-wrapper row']/div[1]/div[1]/input[1]");
	 public static By Username = By.xpath("//div[@class='main']//div//div[2]//div[1]//input[1]");
	 public static By Name = By.xpath("//body/div[@id='app']/div[@class='page_split_wrapper']/div[@class='rightmain-section']/div[@class='main']/div/form/div[contains(@class,'card-wrapper')]/div[@class='mycard-header row']/div[@class='form-wrapper row']/div[3]/div[1]/input[1]");
	 public static By Mobile = By.xpath("//input[@id='ACiFZxnTxE']");
	 public static By EmailID = By.xpath("//div[5]//div[1]//input[1]");
	 public static By SelectRole = By.xpath("//label[contains(@class,'rz-placeholder')]");
	 public static By IsActive = By.xpath("//span[@class='rz-chkbox-icon rzi rzi-check']");
	 public static By SearchButton = By.xpath("//button[@type='submit']");
	 public static By AddUserButton = By.xpath("//a[@class='addbutton btn btn-primary rounded-pill']");
 	
 }
	

			


