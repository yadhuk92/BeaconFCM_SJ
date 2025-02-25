package com.Page_Repository;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;




public class CallCenterHomePage_Repo {
	
//Left Side Menu
	
		public static By SecurityManagementMenu= By.xpath("//span[text()='Security Management']");
		public static By UserManagement = By.xpath("//a[text()='User Management']");
		public static By RoleManagement = By.xpath("//span[text()='Role Management']");
		public static By SupportRequestMenu = By.xpath("//span[text()='Support Request']");
		public static By SupportRequest = By.xpath("//a[text()='Support Request']");
		public static By SupportRequestStatus = By.xpath("//a[text()='Support Request Status']");
}
		

