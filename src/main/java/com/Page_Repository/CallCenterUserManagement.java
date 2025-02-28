package com.Page_Repository;

import org.openqa.selenium.By;

public class CallCenterUserManagement {

	//Security Management >> User Management Page

 
	 
	 public static By ExecutiveID = By.xpath("//*[contains(text(),'Executive ID')]/..//input[@type='search']");
	 public static By Username = By.xpath("//*[contains(text(),'Username')]/..//input[@type='search']");
	 public static By Name = By.xpath("//label[normalize-space()='Name']");
	 public static By Mobile = By.xpath("//label[normalize-space()='Mobile']");
	 public static By EmailID = By.xpath("//*[contains(text(),'Email ID')]/..//input[@type='search']");
	 public static By SelectRole = By.xpath("//*[contains(text(),'Select Role')]");
	 public static By IsActive = By.xpath("//*[contains(text(),'Is Active')]");
	 public static By SearchButton = By.xpath("//button[@type='submit']");
	 public static By AddUserButton = By.xpath("//a[normalize-space()='Add User']");
 
	 
	 //Table Headings
	 
	 public static By sl_no = By.xpath("//span[normalize-space()='SL No']");
	 public static By user_name =By.xpath("//span[normalize-space()='User Name']");
	 public static By executive_id = By.xpath("//span[normalize-space()='Executive ID']");
	 public static By name = By.xpath("//span[normalize-space()='Name']");
	 public static By role = By.xpath("//span[normalize-space()='Role']");
	 public static By mobile_no = By.xpath("//span[normalize-space()='Mobile No']");
	 public static By email_id = By.xpath("//span[normalize-space()='Email ID']");
	 public static By status = By.xpath("//span[normalize-space()='Status']");
	 public static By action = By.xpath("//span[normalize-space()='Action']");
	 
	 
		//Add New user

		public static By adduser_executiveid = By.xpath("//label[normalize-space()='Executive ID']");
		public static By adduser_name = By.xpath("//label[normalize-space()='Name']");
		public static By adduser_email = By.xpath("//label[normalize-space()='Email']");
		public static By adduser_phno = By.xpath("//label[normalize-space()='Phone Number']");
		public static By adduser_role = By.xpath("//label[normalize-space()='Role']");
		public static By adduser_tenure = By.xpath("//label[normalize-space()='Tenurity (In Years)']");
		public static By adduser_teamleader = By.xpath("//label[normalize-space()='Team Leader']");
		public static By adduser_dateofjoining = By.xpath("//label[normalize-space()='Team Leader']");
		public static By adduser_closebutton = By.xpath("//button[normalize-space()='Close']");
		public static By adduser_submitbutton = By.xpath("//button[normalize-space()='Submit']");
 }
	


			


