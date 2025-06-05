package com.Page_Repository;

import org.openqa.selenium.By;

public class DispositionMasterPageRepo {
	
	public static By dispositionMainMenu = By.xpath("//span[contains(text(), 'Disposition')]");
	public static By dispositionMasterSubMenu = By.xpath("//a[@title='Disposition Master']");
	public static By disposition = By.xpath("//ul[@role='tablist']");
	public static By previousDoubleArrowButtons = By.xpath("//li[@class='page-item  ']//span[contains(text(),'<<')]");
	public static By dispositionMasterHeader = By.xpath("//div[@class='main']");
	public static By Sub_Disposition = By.xpath("//span[contains(text(),'Sub-Disposition')]");
	public static By actionOwnerDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[1]");
	public static By editOption = By.xpath("(//button[contains(text(),'Edit')])[1]");
	public static By subactionOwnerDropdown = By.xpath("(//div//div[@onmousedown='Radzen.activeElement = null'])[2]");
	public static By dispositionSearchField = By.xpath("(//div//div[@onmousedown='Radzen.activeElement = null'])[3]");
	public static By subisActiveCheckbox = By.xpath("(//div//div//div[@class='rz-helper-hidden-accessible']//input[@type='checkbox' and @value='true'])[2]");
	public static By subsearchButton = By.xpath("(//button[contains(text(),'Search')])[2]");
	public static By addSubDispositionButton = By.xpath("//button[contains(text(),'Add Sub-Disposition')]");
	//public static By subDispositionList = By.xpath("//div[@class='rz-data-grid-data']//tr[contains(@class, 'rz-datatable-even')]");
	public static By subDispositionList = By.xpath("//tr[contains(@class, 'rz-data')]");
	public static By callCentreOption = By.xpath("(//li[contains(@aria-label,'Call Centre')])[2]");
	public static By collectionAgencyOption = By.xpath("(//li[contains(@aria-label,'Collection Agency')])[2]");
	public static By internalUserOption = By.xpath("(//li[contains(@aria-label,'Internal User')])[2]");
	public static By isActiveCheckbox = By.xpath("(//input[@type='checkbox' and @value='true'])[1]");
	public static By actionColumnButton = By.xpath("(//button[@id='dropdownMenu2'])[1]");
	public static By pagination = By.xpath("(//div[@class='table-footerpagination'])[1]//div//nav");
	//public static By paginationNextButton = By.xpath("(//li[@class='page-item  '])[2]//span");
	public static By paginationNextButton = By.xpath("(//span[text()='Next'])[1]");
	public static By page2 = By.xpath("(//li[@class='page-item  '])[1]//span");
	//public static By previousButton = By.xpath("(//li[@class='page-item  ']//span)[2]");
	public static By previousButton = By.xpath("(//span[text()='Previous'])[1]");
	public static By lastPageButton = By.xpath("(//li[@class='page-item  '])[3]//span");
	public static By nextButton = By.xpath("(//li[@class='page-item disabled '])[1]");
	public static By previouspage = By.xpath("(//li[@class='page-item  '])[2]");
	public static By firstpage = By.xpath("(//li[@class='page-item  '])[1]");
	public static By addDispositionButton = By.xpath("//button[contains(text(),'Add Disposition')]");
	public static By actionOwnerField = By.xpath("//label[contains(text(),'Action Owner')]//following::div[1]");
	public static By nameField = By.xpath("(//input[@name='Name'])[1]");
	//public static By assetCategoryField = By.xpath("(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]");
	public static By assetCategoryField = By.xpath("//label[text()='Asset Category']/following::span[text()='Select'][1]");
	public static By submitButton = By.xpath("//button[contains(text(),'Submit')]");
	public static By closeButton = By.xpath("//div[@class='rz-dialog-titlebar']//a");
	public static By nameErrorMessage = By.xpath("//div[contains(text(),'Name Required')]");
	//public static By errorMessage = By.xpath("//div[@class='rz-growl-item']//p[contains(text(),'This Disposition Already Exist')]");
	//public static By errorMessage = By.xpath("//p[contains(text(),'This Disposition Already Exist')]");
	public static By errorMessage = By.xpath("//*[contains(text(),'Disposition Already Exist')]");
	
	public static By editButton = By.xpath("(//div[@class='morebtnmenu']//button//span//following::ul//li//button[contains(text(),'Edit')])[1]");
	public static By actionOwnerDropdowninpop = By.xpath("(//div//div[@class='rz-helper-hidden-accessible']//following::label)[1]");
	public static By nameFieldinpop = By.xpath("(//div//div//following::label)[3]//following::input[1]");
	public static By assetCategoryDropdowninpop = By.xpath("(//div//div[@class='rz-helper-hidden-accessible']//following::label)[4]");
	public static By updateButton = By.xpath("//div//div//button[contains(text(),'Update')]");
	public static By successMessage = By.xpath("//div[@class='rz-growl-item']//div//span[contains(text(),'Success ')]//following::p");
	public static By successMessageforsubdispostion = By.xpath("//div[@class='rz-growl-message']//span[contains(text(),'Success ')]");
	public static By errorMessageforsubdispostion = By.xpath("//div[@class='rz-growl-message']//p[contains(text(),'This Sub-Disposition Already Exist')]");
	public static By dispositionRow = By.xpath("//div[@class='rz-data-grid-data']//table//tr[@class='rz-datatable-even  ']//span[@title='Online Banking Issues']");
	public static By actionOwner = By.xpath("(//div[@class='rz-data-grid-data']//table//tr[@class='rz-datatable-even  '])[1]//span[@title='']");
	//public static By statusIcon = By.xpath("(//div[@class='rz-data-grid-data']//tr[contains(@class, 'rz-datatable-even')]//i[@style='color:green'])[1]");
	public static By statusIcon = By.xpath("(//i[contains(@class, 'rzi') and text()='check_circle' and contains(@style, 'color:green')])[1]");		
	public static By activateDeactivateOption = By.xpath("(//button[contains(text(),'Activate/De-activate')])[1]");
	public static By searchButton = By.xpath("(//div//button[contains(text(),'Search')])[1]");
	public static By subpopupactionOwnerDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null']//div[@class='rz-helper-hidden-accessible']//input[@name='Action_owner'])[1]");
	public static By subpopupdispositionDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null']//div[@class='rz-helper-hidden-accessible']//input[@name='Action_owner'])[2]");
	public static By subpopupsubDispositionNameField = By.xpath("//input[@name='Name']");
	public static By subpopupclosePopupButton = By.xpath("//a[@role='button']");
	public static By statusIconslist = By.xpath("//tr[contains(@class,'rz-datatable')]//td[3]//span//i");
    //public static By page1Button = By.xpath("(//li[@class='page-item  active'])[1]//span");
    public static By page1Button = By.xpath("(//span[text()='1'])[1]");
    public static By lastPageArrowButton = By.xpath("(//li[@class='page-item  '])[3]//span[contains(text(),'>>')]");
    public static By previousdoubleArrowButtons = By.xpath("//li[@class='page-item  ']//span[contains(text(),'<<')]");
    public static By spinner = By.xpath("//div[@class='spinner']");
    public static By selectAllOptionpath = By.xpath("(//*[contains(@id, 'popup')]/div[1]/div[1]/div[2])[4]");
    
    public static By outsideElement = By.xpath("//div[@class='rz-dialog-content']");
    public static By clearValueIcon = By.xpath("(//div[@class='rz-helper-hidden-accessible']//following::label//following::i)[1]");
    public static By clearvaluepath = By.xpath("(//div[@onmousedown='Radzen.activeElement = null']//div[@class='rz-helper-hidden-accessible']//input//following::i)[2]");
    public static By clearvaluepath2 = By.xpath("((//div[@class='rz-helper-hidden-accessible']//input[@aria-haspopup='listbox']//following::label//following::span)[1]//following::i)[1]");
    public static By actionownerdropdownpath = By.xpath("((//div[contains(text(),'Action Owner')])[1]//following::div//div)[1]");
    public static By outsideElementPath2 = By.xpath("//div[@class='rz-tabview-panels']");
    public static By isActiveCheckbox2 = By.xpath("(//div[@style='padding-left:50px']//div)[1]");
    public static By subDispositionOption = By.xpath("(//div//ul[@role='tablist']//li)[2]");
    public static By subPopupDispositionDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[2]");
    public static By successMessage2 = By.xpath("//div[@class='rz-growl-message']//p[contains(text(),'Saved Successfully')]");
    public static By popup = By.xpath("//div[@class='rz-growl-item']");
    public static By msgLocator = By.xpath("//div[@class='rz-growl-message']//p");
    public static By popupDialogLocator = By.xpath("//div[@class='rz-dialog']");
    public static By actionButton = By.xpath("(//div[@class='morebtnmenu']//button[@id='dropdownMenu2']//span[@class='material-symbols-outlined'])[11]");
    public static By subdispoeditButton = By.xpath("((//div[@class='morebtnmenu']//button[@id='dropdownMenu2']//span[@class='material-symbols-outlined'])[11]//following::button[contains(text(),'Edit')])[1]");
    public static By subdispoupdateButton = By.xpath("//button[contains(text(),'Update')]");
    public static By outsideElementPath3 = By.xpath("//div[@class='card-wrapper']");
    public static By errorMessage2 = By.xpath("//div[@class='rz-growl-item']//p[contains(text(),'This Sub-Disposition Already Exist')]");
    public static By actionColumnButton2 = By.xpath("(//button[@id='dropdownMenu2'])[11]");
    public static By activateDeactivateOption2 = By.xpath("(//button[contains(text(),'Activate/De-activate')])[11]");
    public static By isActiveCheckbox3 = By.xpath("(//div[@style='padding-left:50px']//div)[4]");
    public static By searchButton2 = By.xpath("(//div//button[contains(text(),'Search')])[2]");
    public static By statusIconslist2 = By.xpath("(//div[@class='rz-data-grid-data'])[2]//tr[contains(@class,'rz-datatable')]//td[4]//span//i");
    public static By clearvaluepath3 = By.xpath("(//div[contains(@class, 'cardheaderleft')]//div[@onmousedown='Radzen.activeElement = null'])[2]//i");
    public static By clearvaluepath4 = By.xpath("(//div[contains(@class, 'cardheaderleft')]//div[@onmousedown='Radzen.activeElement = null'])[3]//i");
    public static By actionOwnerOption(String actionOwner) {
        return By.xpath("(//div[@class='rz-multiselect-items-wrapper'])[5]//ul//li[@aria-label='" + actionOwner + "']");
    }
    public static By actionOwnerOption4(String actionOwner) {
        return By.xpath("//div[@class='rz-dropdown-items-wrapper']//ul//li[@aria-label='>" + actionOwner + "']");
    }
    public static By assetCategoryOption(String category) {
        return By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + category + "']");
    }
    public static By dispositionByName(String name) {
        return By.xpath("//div//div[@class='table-wrapper-inner']//div//div//table//tr//td//span[@title='" + name + "']");
    }
    public static By actionOwnerText(String actionOwner) {
        return By.xpath("(//div//div[@class='table-wrapper-inner']//div//div//table//tr//td//span[@title='" + actionOwner + "'])[1]");
    }
    public static By assetCategoryOption2(String assetCategory) {
        return By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + assetCategory + "']");
    }
    public static By actionOwnerOption3(String actionOwner) {
        return By.xpath("(//div[@class='rz-multiselect-items-wrapper']//ul[@role='listbox'])[3]//li[@aria-label='" + actionOwner + "']");
    }
    public static By subDispositionActionOwnerOption(String owner) {
        return By.xpath("//div[@class='rz-dropdown-items-wrapper']//ul//li[@aria-label='>" + owner + "']");
    }
    public static By subPopupDispositionOption(String disposition) {
        return By.xpath("//div[@class='rz-dropdown-items-wrapper']//ul//li[@aria-label='>" + disposition + "']");
    }
    public static By subDispositionOption(String disposition) {
        return By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + disposition + "']");
    }
    public static By dispositionOption(String disposition) {
        return By.xpath("//div[@class='rz-multiselect-items-wrapper']//ul//li[@aria-label='" + disposition + "']");
    }
    //public static By successPopup = By.xpath("//div[@class='rz-growl-item']//div//span[contains(text(),'Success ')]");
    public static By successPopup = By.xpath("//span[text()='Success ']");
    public static By actionOwnerPath = By.xpath("(//label[contains(text(),'Asset Category')]//following::div//div[@class='rz-helper-hidden-accessible']//input//following::label)[1]");
    public static By popUpCloseButton = By.xpath("//div[@class='rz-dialog-titlebar']//a");
    
    public By FirstDisposition(String DispositionName) {
        return By.xpath("(//span[text()='"+DispositionName+"'])[1]");
    }
    		
    }