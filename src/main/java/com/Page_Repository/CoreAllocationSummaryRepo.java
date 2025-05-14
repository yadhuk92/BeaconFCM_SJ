package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAllocationSummaryRepo {
	public static By username = By.xpath("//input[@placeholder='User Name']");
	public static By password = By.xpath("//input[@placeholder='Password']");
	public static By SignIn = By.xpath("//button[text()='LOGIN']");
	public static By home = By.xpath("//nav[@class='sidebar ']");
	public static By userIcon = By.xpath("(//button[@type='button'])[1]");
	public static By logOut = By.xpath("(//a[normalize-space()='Logout'])[1]");
	public static By callCentre = By.xpath("//a[.//span[contains(text(),'Call Centre')]]");
	public static By manualAllocation = By.cssSelector("[title|=\"Manual Allocation\"]");
	public static By selectText=By.xpath("//*[contains(text(),'Asset Category')]/following-sibling::div//*[text()='Select']");
	public static By allocationSummary = By.xpath("//a[text()='Allocation  Summary']");
	public static By allocationSummaryOption = By.cssSelector("[title|=\"Allocation  Summary\"]");
	public static By AllocationSummaryPage=By.xpath("//div[@class='dvPageheadingCaption']");
	public static By Select_CallCentre=By.xpath("//label[contains(text(),'Select')]/following-sibling::div//label");
	public static By selectCallCenterLabel=By.xpath("//label[text()='Select Call Centre']");
	public static By CallCentreDropdown=By.xpath("(//span[normalize-space()='CallCentre 1'])[1]");
	public static By Search=By.xpath("//button[@type='submit']");
	public static By CallCentreWarning=By.xpath("//p[contains(text(),\"Call Center Is Required\")]");
	public static By MonthlydownloadIcon=By.xpath("(//i[@id='PY4y-UNyOU'])[1]");
	public static By DaydownloadIcon=By.xpath("(//i[@id='Be38N5EAmE'])[1]");

	
	
}
