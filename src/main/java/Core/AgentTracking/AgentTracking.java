package Core.AgentTracking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Page_Repository.AgentTracking_Repository;
import com.Page_Repository.LoginBannerConfiRepo;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;

//Constructor
public class AgentTracking extends Base_Class{
	AgentTracking_Repository Page_Repository=new AgentTracking_Repository();
  private static WebDriver driver;
 
 public AgentTracking(WebDriver driver) {
  	this.driver=driver;
  	PageFactory.initElements(driver, this);
  }
 

    // Method to check if "Agent Tracking" module is visible
    public boolean isAgentTrackingModuleDisplayed() {
        WebElement AgentTrakingMainMenu=driver.findElement(Page_Repository.AgentTrackingMainMenu);
        return AgentTrakingMainMenu.isDisplayed();
    }
    
  //method to click "Agent tracking" main menu 
    public boolean clickAgentTrackingMainMenu() {
    	WebElement AgentTrackingmainMenu=driver.findElement(Page_Repository.AgentTrackingMainMenu);
    	AgentTrackingmainMenu.click();
    	return true;
  }
    
    // Method to verify the presence of sub-modules
   public boolean areSubmodulesVisible() {
	   WebElement AgentTrackingSubmenu=driver.findElement(Page_Repository.AgentTrackingSubmenu);
	   WebElement  CashorChequeSettlementSubmenu=driver.findElement(Page_Repository.CashorChequeSettlementSubmenu);
	   
return	   AgentTrackingSubmenu.isDisplayed() && CashorChequeSettlementSubmenu.isDisplayed();
   }
   
   //method to click "Agent tracking" Sub menu 
   public boolean clickAgentTrackingSubMenu() {
   	WebElement AgentTrackingSubMenu=driver.findElement(Page_Repository.AgentTrackingSubmenu);
   	AgentTrackingSubMenu.click();
   	return true;
 }
   
 //method to find Filters for Officer Type, Zone, Region, Branch, Agency Name are present
   public boolean checkFiltersPresent() {
	   WebElement officertype=driver.findElement(Page_Repository.OfficerType);
	   WebElement zone_co=driver.findElement(Page_Repository.Zone_CO);
	   WebElement region=driver.findElement(Page_Repository.Region);
	   WebElement branch=driver.findElement(Page_Repository.Branch);
	   WebElement agencyname=driver.findElement(Page_Repository.AgencyName);
	   
	   return officertype.isDisplayed() &&
			  zone_co.isDisplayed() &&
			  region.isDisplayed() &&
			  branch.isDisplayed() &&
			  agencyname.isDisplayed();
	   
   }
}
