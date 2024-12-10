package com.Page_Repositary;

import org.openqa.selenium.By;

public class LoginPageRepo {
	 
	public static By UserNameField = By.xpath("//input[@placeholder='User Name']");
	public static By PasswordField = By.xpath("//input[@placeholder='Password']");
	public static By LoginButton = By.xpath("//button[@type='submit']");
	public static By Spinner = By.xpath("//div[@class='spinner']");
	public static By LoginHyperlink2Banner = By.xpath("//a[text()='https://docs.google.com/spreadsheets/d/1...']");
	public static By AlreadyLoginPopupYesButton = By.xpath("//span[text()='Yes' and @class='rz-button-text']");
	public static By AccountCategoryLabelInDashboard = By.xpath("//span[@class='rz-column-title' and text()='Account Category']");
	public static By UserIDInDashboard = By.xpath("//span[@class='emailuser']");
	public static By GoCollectionButton = By.xpath("//button[@class='btn btn-primary' and text()='Go Collection']");
	public static By SetAsDefaultRadioButton = By.xpath("//div[@class='rz-chkbox-box']");
	public static By LoginPageSomeErrorOccurred = By.xpath("//span[@style='color:red' and text()='Some error occurred.']");
	
	public static By getORGDetailsinLoginLandingPage(String OrgID, String OrgType) {
	    String xpathExpression;

	    // Handle cases where both OrgID and OrgType are provided
	    if (OrgID != null && OrgType != null) {
	        xpathExpression = "//span[@class='org' and (contains(., '" + OrgID + "') or contains(., '" + OrgType + "'))]";
	    } 
	    // Handle cases where only OrgID is provided
	    else if (OrgID != null) {
	        xpathExpression = "//span[@class='org' and contains(., '" + OrgID + "')]";
	    } 
	    // Handle cases where only OrgType is provided
	    else if (OrgType != null) {
	        xpathExpression = "//span[@class='org' and contains(., '" + OrgType + "')]";
	    } 
	    // Handle cases where neither is provided (optional, based on requirement)
	    else {
	        throw new IllegalArgumentException("Both OrgID and OrgType cannot be null");
	    }

	    return By.xpath(xpathExpression);
	}

}

