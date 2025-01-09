package com.CollectionAgency.SecurityManagement;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.CollectionAgencyRoleManagement_Locators;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class RoleManagement extends Base_Class {
	
	CollectionAgencyRoleManagement_Locators PageRepositry= new CollectionAgencyRoleManagement_Locators();

	public boolean ClickSecurityManagement() throws InterruptedException 
	{
		Thread.sleep(2000);
		Common.fluentWait("SecurityManagementMenu", PageRepositry.SecurityManagementMenu);
		click(PageRepositry.SecurityManagementMenu);
		Common.fluentWait("RoleManagementOption", PageRepositry.RoleManagementOption);
		click(PageRepositry.RoleManagementOption);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		Thread.sleep(5000);
		return true;
	}
	public boolean RoleNameSearchField() throws InterruptedException {
		click( PageRepositry.RoleNameSearch);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		return true;
	
	}
	public boolean clickRoleSearchField() throws InterruptedException {
	try{
		Common.fluentWait("SearchBtn", PageRepositry.SearchBtn);
		click( PageRepositry.SearchBtn);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		//Thread.sleep(5000);
		return true;
	}catch(Exception e){
		System.out.println("Error: " + e.getMessage());
        return false;
	}
		
	}
	
	public boolean RoleMngmtEltsVisibility() throws InterruptedException {
		Thread.sleep(4000);
		ElementDisplayed(PageRepositry.SearchBtn);
		ElementDisplayed(PageRepositry.RoleNameSearch);
		ElementDisplayed(PageRepositry.AddNewRoleBtn);
		return true;
		
	}
	public boolean RolePermissionPopup() throws InterruptedException {
		Common.fluentWait("AddNewRoleBtn", PageRepositry.AddNewRoleBtn);
		click(PageRepositry.AddNewRoleBtn);
		Thread.sleep(5000);
		Common.fluentWait("checkbox", PageRepositry.checkbox);
		Common.fluentWait("CloseBtn", PageRepositry.CloseBtn);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		click(PageRepositry.CloseBtn);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		Common.fluentWait("AddNewRoleBtn", PageRepositry.AddNewRoleBtn);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Thread.sleep(5000);
		click(PageRepositry.AddNewRoleBtn);
		Thread.sleep(3000);
		Common.fluentWait("checkbox", PageRepositry.checkbox);
		click(PageRepositry.checkbox);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		//Thread.sleep(2000);
		return true;
		
	}
		
	public boolean RolePermissionSave() throws InterruptedException {
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("RolePermissionSave", PageRepositry.RolePermissionSave);
		click(PageRepositry.RolePermissionSave);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		return true;
	}
	public boolean RoleNameErrorMsg() throws InterruptedException {
		ElementDisplayed(PageRepositry.RoleNameErrorMsg);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		return true;	
	}
	
	public static void zoomIn(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='100%'"); // Zoom in to 150%
    }

    // Method to zoom out (decrease zoom)
    public boolean zoomOut(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='70%'");
        return true;
    }
	public boolean ClickCancelRole() throws InterruptedException {
		click(PageRepositry.CancelRole);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		zoomIn(driver);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Thread.sleep(3000);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		Thread.sleep(5000);
		return true;
	}
	
	public void ClickingAllCheckbox() throws InterruptedException {
   	 
   	 By Checkboxes = (PageRepositry.AllCheckbox);
   	 List<WebElement> ListsCount = driver.findElements(Checkboxes);
   	 int rowsize =ListsCount.size();
   	 System.out.println("rowsize:-"+rowsize);  
   	 for(WebElement ListCount: ListsCount) {
   		 ListCount.click();
   	 }
	}
	public boolean AddNewRoleName(String Name) throws InterruptedException {
		Common.fluentWait("AddNewRoleBtn", PageRepositry.AddNewRoleBtn);
		click(PageRepositry.AddNewRoleBtn);
		Thread.sleep(5000);
		Common.fluentWait("checkbox", PageRepositry.checkbox);
		Common.fluentWait("CloseBtn", PageRepositry.CloseBtn);
		/*click(PageRepositry.CloseBtn);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentwait("Next Btn", PageRepositry.NextBtn);
		Common.fluentwait("AddNewRoleBtn", PageRepositry.AddNewRoleBtn);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Thread.sleep(5000);
		Common.fluentwait("AddNewRoleBtn", PageRepositry.AddNewRoleBtn);
		click(PageRepositry.AddNewRoleBtn);
		Common.fluentwait("checkbox", PageRepositry.checkbox);
		Thread.sleep(3000);*/
		input(PageRepositry.RoleName, Name);
		return true;
	}
	
	public boolean ClickCheckbox() throws InterruptedException {
		Common.fluentWait("checkbox", PageRepositry.checkbox);
		click(PageRepositry.checkbox);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		return true;
	}
    	 
	public boolean RoleNameSuccessMsg() throws InterruptedException {
		Common.fluentWait("RoleNameSuccessMsg", PageRepositry.RoleNameSuccessMsg);
		ElementDisplayed(PageRepositry.RoleNameSuccessMsg);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		return true;
	}
	
	public boolean InputSearchField(String SearchName) throws InterruptedException {
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		Thread.sleep(5000);
		input(PageRepositry.RoleNameSearch, SearchName);
		Thread.sleep(1000);
		return true;
	}
	
	public boolean DisplayAddedRoleName(String RoleName) throws InterruptedException {
		try{
			Common.fluentWait("RoleSearchResultCheck", CollectionAgencyRoleManagement_Locators.RoleSearchResultCheck(RoleName));
			Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			Thread.sleep(3000);
			ElementDisplayed(PageRepositry.ThreedotButton);
			ElementDisplayed(PageRepositry.ActionColumn);
			Thread.sleep(1000);
			return true;
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
	        return false;
		}
	}
	public boolean DisplayEditOption() throws InterruptedException {
		try{
			Common.fluentWait("ThreedotButton", PageRepositry.ThreedotButton);
			click(PageRepositry.ThreedotButton);
			ElementDisplayed(PageRepositry.EditBtn);
			//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			//Common.fluentwait("Next Btn", PageRepositry.NextBtn);
			return true;
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
	        return false;
		}
	}
	
	public boolean EditRole() throws InterruptedException {
		Common.fluentWait("EditBtn", PageRepositry.EditBtn);
		click(PageRepositry.EditBtn);
		Thread.sleep(3000);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("checkbox", PageRepositry.checkbox);
		Thread.sleep(3000);
		click(PageRepositry.checkbox);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		click(PageRepositry.DispositionCheckbox);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		return true;
	}
	
	public boolean UpdateRoleSuccessMsg() throws InterruptedException {
		ElementDisplayed(PageRepositry.UpdateRoleSuccessMsg);
		Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Common.fluentWait("Next Btn", PageRepositry.NextBtn);
		Thread.sleep(2000);
		return true;
	}
     
	public boolean clickSearchButton(String RoleName) throws InterruptedException {
		try {
			//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			Common.fluentWait("NextBtn", PageRepositry.NextBtn);
			Thread.sleep(3000);
			Common.fluentWait("SearchBtn", PageRepositry.SearchBtn);
			Common.fluentWait(RoleName, CollectionAgencyRoleManagement_Locators.RoleSearchFirstValue(RoleName));
			Thread.sleep(3000);
			click(PageRepositry.SearchBtn);
			//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			Common.fluentWait("Next Btn", PageRepositry.NextBtn);
			Thread.sleep(5000);
			return true;
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
	        return false;
		}
	}
	
	public boolean clickSearchButton2() throws InterruptedException {
		try{
			//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			Common.fluentWait("NextBtn", PageRepositry.NextBtn);
			Thread.sleep(3000);
			Common.fluentWait("SearchBtn", PageRepositry.SearchBtn);
			Thread.sleep(3000);
			click(PageRepositry.SearchBtn);
			//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
			Common.fluentWait("Next Btn", PageRepositry.NextBtn);
			Thread.sleep(5000);
			return true;
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
	        return false;
		}
	}
	
	public boolean inputRoleNameSearchField(String SearchRoleName) throws InterruptedException {
		input(PageRepositry.RoleNameSearch,SearchRoleName);
		return true;
	}
	public boolean NoRecordsMsg() throws InterruptedException {
		ElementDisplayed(PageRepositry.NoRecordsMsg);
		return true;
	}
	public boolean ClickAllCheckbox() throws InterruptedException 
	{
		click(PageRepositry.AgentAccountAllocationCheckbox);
		click(PageRepositry.AddNewAgentCheckbox);
		click(PageRepositry.AgentListCheckbox);
		click(PageRepositry.UploadInvoiceCheckbox);
		click(PageRepositry.DispositionFunctionalityCheckbox);
		click(PageRepositry.AllocationSummaryCheckbox);
		/*System.out.println("Before scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", PageRepositry.SupportRequestStatusCheckbox);
        System.out.println("After scroll");*/
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		
		Common.fluentWait("SupportRequestStatusCheckbox", PageRepositry.RoleManagementCheckbox);
		click(PageRepositry.RoleManagementCheckbox);
		Common.fluentWait("SupportRequestStatusCheckbox", PageRepositry.SupportRequestCheckbox);
		click(PageRepositry.SupportRequestCheckbox);
		Common.fluentWait("SupportRequestStatusCheckbox", PageRepositry.SupportRequestStatusCheckbox);
		click(PageRepositry.SupportRequestStatusCheckbox);
		//Common.waitForSpinnerToDisappear(driver, "Loading Spinner", PageRepositry.waitSpinner);
		Thread.sleep(3000);
		return true;
	}
     public boolean ValidatingTheRows() throws InterruptedException {
    	 
    	 By Rows = By.xpath("//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr");
    	 List<WebElement> ListsCount = driver.findElements(Rows);
    	 int rowsize =ListsCount.size();
    	 System.out.println("rowsize:-"+rowsize); 
    	 boolean flag = false;
         for(int i=1;i<=rowsize;i++) {
                int j = i*2-1;
                System.out.println("J:-"+j);
                Thread.sleep(2000);
                By Elements =By.xpath("(//table[@class='rz-grid-table rz-grid-table-fixed']//tbody//tr//td[@rowspan='1']//span[@class='rz-cell-data'])["+j+"]");
                String ActualColumnName =GetElementText(Elements);
                System.out.println("User found the "+i+"row value"+ActualColumnName);
         
    	  
     }
         if (rowsize>0) {
        	 flag=true; 
         }
         return flag;
        
     }
}
 
   
	
		
	
