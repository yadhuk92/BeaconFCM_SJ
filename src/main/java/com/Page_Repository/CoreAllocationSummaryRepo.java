package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAllocationSummaryRepo {
	public static By manualAllocation = By.cssSelector("[title|=\"Manual Allocation\"]");
	public static By selectText=By.xpath("//*[contains(text(),'Asset Category')]/following-sibling::div//*[text()='Select']");
	
	public static By allocationSummary = By.cssSelector("//a[@title=\\\"Allocation  Summary\\\"]");
	
	public static By AllocationSummaryPage=By.xpath("//div[@class='dvPageheadingCaption']");
	
	public static By Select_CallCentre=By.xpath("//label[contains(text(),'Select')]/following-sibling::div//label");
	
	public static By CallCentreDropdown=By.xpath("//li[@aria-label='>CallCentre 1']");
	
	public static By Submit=By.xpath("//button[@type='submit']");
	
	public static By CallCentreWarning=By.xpath("//p[contains(text(),\"Call Center Is Required\")]");
	
}
