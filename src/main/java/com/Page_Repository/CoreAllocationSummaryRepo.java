package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAllocationSummaryRepo {
	public static By manualAllocation = By.cssSelector("[title|=\"Manual Allocation\"]");
	public static By selectText=By.xpath("//*[contains(text(),'Asset Category')]/following-sibling::div//*[text()='Select']");
	

}
