package com.Page_Repository;

import org.openqa.selenium.By;

public class AgentTracking_Repository {
	
	public By AgentTrackingMainMenu=By.xpath("//span[text()='Agent Tracking']");
	public By AgentTrackingSubmenu=By.xpath("//a[@title='Agent Tracking']");
	public By CashorChequeSettlementSubmenu=By.xpath("//a[@title='Cash or Cheque Settlement']");
	public By spinner=By.xpath("//div[@class='spinner']");
	public By OfficerType=By.xpath("//div[@class='rz-dropdown valid form-control mandatory-color']");
	public By Zone_CO=By.xpath("//div[@class='rz-dropdown rz-state-disabled valid rz-clear form-control' and @onmousedown='Radzen.activeElement = null']");
	public By Region=By.xpath("(//div[@class='rz-dropdown valid rz-clear form-control'])[1]");
	public By Branch=By.xpath("(//div[@class='rz-dropdown valid rz-clear form-control'])[2]");
	public By AgencyName=By.xpath("//div[@class='rz-dropdown valid rz-clear form-control mandatory-color']");

}
