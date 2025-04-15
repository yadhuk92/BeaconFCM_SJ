package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAlertsMaskAccountAndMaskingAuthorizationRepo {
	
	public static By maskAccountWindowLink = By.xpath("//a[@title='Mask Account']");
	public static By accountNumberField = By.xpath("//input[@type='number']");
	public static By getButton = By.xpath("//button[@type='submit' and text()='Get']");
	public static By statusFieldDropdown = By.xpath("//label[text()='Status']//following-sibling::div");
	public static By allAccountLifeRadioButton = By.xpath("//div[@class='rz-radio-button-list-horizontal valid']//div[@class='rz-radio-btn' and position()=1]");
	public static By reasonField = By.xpath("//label[text()='Reason']//following::input");
	public static By saveButton = By.xpath("//button[@type='submit' and text()='Save']");
	public static By confirmationPopupOkButton = By.xpath("//button[@class='rz-button rz-button-md btn-primary' and @type='button']");
	public static By successMessage = By.xpath("//p[text()='Masking saved successfully.']");
	public static By maskAuthorizationOption = By.xpath("//a[@title='Masking Authorization']");
	public static By maskedAccountNumberField = By.xpath("//label[text()='Account Number']//following-sibling::input");
	public static By searchButton = By.xpath("//button[@type='submit' and text()='Search']");
	public static By firstResult = By.xpath("//tbody//tr//td//div[@class='rz-chkbox']");
	public static By approvalButton = By.xpath("//input[@type='button' and @value='Approve']");
	public static By approvalsuccessMessage = By.xpath("//p[text()='Request approved successfully.']"); 
	public static By maskedAccountInput = By.xpath("//input[@type='number' and @placeholder='Account Number']");
	public static By validateButton = By.xpath("//input[@type='button' and @value='Validate']");
	public static By warningMessage = By.xpath("//p[text()='Masked Account Number.']");
	public static By errorMessage = By.xpath("//p[text()='Invalid account.']");
	public static By errorMsg = By.xpath("//p[text()='Account Number Required']");
	public static By errorMssg = By.xpath("//p[text()='Reason is required.']");
	public static By accountnumberfromdashboard = By.xpath("//h6[text()='SMA High Priority Accounts']//following::div[@style='height:300px !important']//tbody//tr[@class='rz-datatable-odd  ' and position()=2]//td");
	public static By Dropdownvalues(String value) {
        return By.xpath("//li[@class='rz-dropdown-item ' and @aria-label='>" + value + "']"); 
    }

}
