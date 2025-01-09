package Core.SecurityManagement;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.BannedCustomerPageRepo;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;

public class BannedCustomer extends Base_Class {
	
	public void AccesBannedCustomerPage() throws InterruptedException 
	{
		Common.fluentWait("SecurityManagementMainMenu", BannedCustomerPageRepo.SecurityManagementMainMenu);
		click(BannedCustomerPageRepo.SecurityManagementMainMenu);
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on Security management main menu");
		
		Common.fluentWait("BannedCustomerSubMenu", BannedCustomerPageRepo.BannedCustomerSubMenu);
		click(BannedCustomerPageRepo.BannedCustomerSubMenu);
		ExtentTestManager.getTest().log(Status.PASS, "Clicked on Banned customer sub menu");
	}

}
