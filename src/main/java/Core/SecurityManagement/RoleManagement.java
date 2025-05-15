package Core.SecurityManagement;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.Login_Class;
import com.Page_Repository.UserManagement_Locators;
import com.Page_Repository.CoreRoleManagementRepo;


public class RoleManagement extends Base_Class {
	 CoreRoleManagementRepo PageRepository= new CoreRoleManagementRepo();
	 private WebDriver driver;
	 
	 com.Utility.ExcelReader ExcelReader;
	 
	 @BeforeSuite
		public void reference() throws Exception {
			ExcelReader = new com.Utility.ExcelReader("CoreRoleManagement");
		}

	 
	 public static void CoreLogin() throws Exception {
		 Login_Class.CoreLogin();
	        // method logic here
	    }
	 
	 
	 
	 public boolean SelectSecurityManagementMenu() throws InterruptedException 
		{
			//Common.waitForSpinnerToDisappear("Loading Spinner", PageRepository.moduleSelectionSpinner);
			Common.fluentWait("SecurityManagementMenu", PageRepository.SecurityManagement);
			click(PageRepository.SecurityManagement);
			return true;
		}
		public boolean SelectRoleManagementMenu() throws InterruptedException 
		{
			Common.fluentWait("RoleManagementMenu", PageRepository.RoleManagement);
			click(PageRepository.RoleManagement);
			//Common.waitForSpinnerToDisappear("Loading Spinner", PageRepository.moduleSelectionSpinner);
			return true;
		}
	 
	 
	 
	 public boolean AddNewRole() throws InterruptedException 
		{
			//Common.waitForSpinnerToDisappear("Loading Spinner", PageRepository.moduleSelectionSpinner);
			Common.fluentWait("AddNewRole", PageRepository.AddNewRole);
			click(PageRepository.AddNewRole);
			return true;
		}
	 
	 
	 public boolean EnterTestRole(String TestRole) throws InterruptedException 
		{
			try {
				Common.fluentWait("TestRole", PageRepository.RoleName);
				input(PageRepository.RoleName, TestRole);
				return true;
			}catch(Exception e){
				System.out.println("Error: "+e);
				return false;
			}
			
		}
	 
	 
	 
	 
	 
	 
	 
}
