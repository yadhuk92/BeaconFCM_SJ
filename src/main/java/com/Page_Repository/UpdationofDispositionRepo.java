package com.Page_Repository;
import org.openqa.selenium.By;

public class UpdationofDispositionRepo {
	
	public static By spinner = By.xpath("//div[@class='spinner']");
	public static By dispositionMenu = By.xpath("//span[contains(text(), 'Disposition')]");
	public static By updationOfDispositionMenu = By.xpath("//a[@title='Updation of Disposition']");
	public static By transactiondetails = By.xpath("//input[@value='Transaction Details']");
	public static By accountNumberField = By.xpath("//input[@placeholder='Account Number']");
	public static By searchButton = By.xpath("//input[@value='Search']");
	public static By errorMessage = By.xpath("//p[contains(text(),'Invalid account number.')]");
	public static By errorMessage8 = By.xpath("//p[contains(text(),'Invalid Account Number')]");
	public static By loginuserid = By.xpath("//span[@class='emailuser']");
	public static By loginusername = By.xpath("(//span[@class='name'])[1]");
	public static By customername = By.xpath("//th[contains(text(),'Customer Name')]");
	public static By savebutton = By.xpath("//button[contains(text(),'Save')]");
	public static By errorMessage2 = By.xpath("//span[contains(text(),'Please Enter All Fields In The Interaction Details')]");
	public static By nextActionOwnerDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[1]");
	public static By nextActionOwnerDropdownvalues(String actionOwner) {
        return By.xpath("//div[@class='rz-dropdown-items-wrapper']//ul[@role='listbox']//li[@aria-label='>" + actionOwner + "']");
    }
	public static By errorMessage3 = By.xpath("//span[contains(text(),'Select Disposition')]");
	public static By dispositionDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[2]");
	public static By dispositionDropdownvalues(String disposition) {
        return By.xpath("//div[@class='rz-dropdown-items-wrapper']//ul[@role='listbox']//li[@aria-label='>" + disposition + "']");
    }
	public static By errorMessage4 = By.xpath("//span[contains(text(),'Select Sub Disposition')]");
	public static By subdispositionDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[3]");
	public static By errorMessage5 = By.xpath("//span[contains(text(),'Select Next Action Date')]");
	public static By datepicker = By.xpath("//input[@placeholder='DD-MM-YYYY']");
	public static By day(int day) {
        return By.xpath("//span[@class='rz-state-default' and contains(text(),'" + day + "')]");
    }
	public static By errorMessage6 = By.xpath("//span[contains(text(),'Remarks is required')]");
	public static By deviationrequestpopupclose = By.xpath("//span[@class='rzi rzi-times']");
	public static By remarks = By.xpath("//input[@maxlength='1000']");
	public static By successMessage = By.xpath("//p[contains(text(),'Saved Successfully')]");
	public static By transactionHeading(String date, String userType) {
	    return By.xpath(String.format("//label[contains(text(),'%s[%s]')]", date, userType));
	}
	public static By InteractionDetailsDisposition(String disposition) {
        return By.xpath("//span[contains(text(),'" + disposition + "')]");
    }
	public static By InteractionDetailsSubDisposition(String SubDisposition) {
        return By.xpath("//span[contains(text(),'" + SubDisposition + "')]");
    }
	public static By InteractionDetailsRemarks(String Remarks) {
        return By.xpath("//span[contains(text(),'" + Remarks + "')]");
    }
	public static By InteractionDetailsNextActionOwner(String NextActionOwner) {
        return By.xpath("//span[contains(text(),'" + NextActionOwner + "')]");
    }
	public static By InteractionDetailsNextActionDate(String NextActionDate) {
        return By.xpath("//span[contains(text(),'" + NextActionDate + "')]");
    }
	public static By InteractionDetailsActionDoneBy(String ActionDoneBy) {
        return By.xpath("//span[contains(text(),' Action Done By :   ')]//following::span[contains(text(),'" + ActionDoneBy + "')]");
    }
	public static By InteractionDetailsUserEIN(String UserEIN) {
        return By.xpath("//span[contains(text(),' User EIN :  ')]//following::span[contains(text(),'" + UserEIN + "')]");
    }
	public static By errorMessage7 = By.xpath("//p[contains(text(),'You are not authorized to do the disposition of this account number')]");
}