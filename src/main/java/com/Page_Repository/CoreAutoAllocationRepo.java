package com.Page_Repository;

import org.openqa.selenium.By;

public class CoreAutoAllocationRepo {
	
	public static By callcentermainmenu = By.xpath("//span[contains(text(),'Call Centre')]");
	public static By accountfiltrationsubmenu = By.xpath("//a[contains(text(),'Account Filtration')]");
	public static By searchbutton = By.xpath("//button[contains(text(),'Search')]");
	public static By warningmsg = By.xpath("//p[contains(text(),'Allocated To is Required')]");
	public static By assetCategoryDropdown = By.xpath("(//label[contains(text(),'Asset Category')]//following::div[@onmousedown='Radzen.activeElement = null'])[1]");
	public static By assetcategoryvalue(String assetcategory) {
        return By.xpath("//li[@aria-label='" + assetcategory + "']");
    }
	public static By assetCategoryvalueall = By.xpath("(//div[@class='rz-chkbox'])[12]");
	public static By allocatedtodropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[27]");
	public static By allocatedtovalue(String allocatedto) {
        return By.xpath("//li[@aria-label='>" + allocatedto + "']");
    }
	public static By outarea = By.xpath("//div[@class='mainheader-top']");
	public static By warningmsg2 = By.xpath("//p[contains(text(),'Asset Category is Required')]");
	public static By SMAcategoryDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[13]");
	public static By SMAcategoryvalueall = By.xpath("(//div[@class='rz-chkbox'])[12]");
	public static By NPAcategoryDropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[14]");
	public static By NPAcategoryvalueall = By.xpath("(//div[@class='rz-chkbox'])[12]");
	public static By allocationtypedropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[26]");
	public static By allocationtypevalue(String allocationtype) {
        return By.xpath("//li[@aria-label='>" + allocationtype + "']");
    }
	
	
	public static By callcentermenu = By.xpath("//span[contains(text(),'Call Centre')]");
	public static By autoallocationsubmenu = By.xpath("//a[@title='Auto Allocation']");
	public static By outarea2 = By.xpath("//div[@class='mainheader-top']");
	public static By allocationName = By.xpath("//input[@type='text' and @maxlength='50' ]");
	public static By effectDate = By.xpath("(//input[@id='Date' ])[1]");
	public static By effectDatevalue(String date) {
        return By.xpath("(//span[@class='rz-state-default' and text()= '" + date + "'])[2]");
    }
	public static By assetCategory = By.xpath("//div[@id='PFTNPADisable']");
	public static By assetCategoryselectall = By.xpath("(//div[@class='rz-chkbox-box   '])[17]");
	public static By smaCategory = By.xpath("(//label[contains(text(),'SMA Category')]//following::div[@onmousedown='Radzen.activeElement = null'])[1]");
	public static By smaCategoryselectall = By.xpath("(//div[@class='rz-chkbox-box   '])[23]");
	//public static By npaCategory = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[3]");
    public static By npaCategory = By.xpath("(//label[@class='rz-dropdown-label rz-inputtext  rz-placeholder'])[3]");
	public static By npaCategoryselectall = By.xpath("(//div[@class='rz-chkbox-box   '])[17]");
	public static By zone = By.xpath("//label[text()='Zone']//following-sibling::div");
	public static By zoneall = By.xpath("//i[contains(@class, 'rz-dropdown-clear-icon rzi')]");
	public static By ZoneDropDownWithAll = By.xpath("//label[@class='rz-dropdown-label rz-inputtext ' and text()='All']");
	public static By zonevalue(String value) {
        return By.xpath("//li[@aria-label='>" + value + "']");
    }
	public static By vertical = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[5]");
	public static By schemeType = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[6]");
	public static By productType = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[7]");
	public static By schemeCode = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[8]");
	public static By assetTaggingType = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[9]");
	public static By outstandingBalance = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[12]");
	public static By outstandingBalancevalue = By.xpath("//input[@class='form-control']");
	public static By totalOverdue = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[13]");
	public static By totalOverduevalue = By.xpath("//input[@min='0' and @maxlength='30']");
	public static By dpd = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[14]");
	public static By dpdvalue = By.xpath("(//input[@min='0' and @maxlength='35'])[2]");
	public static By percentOverdueToEMI = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[15]");
	public static By percentOverdueToEMIvalue = By.xpath("//input[@min='0' and @maxlength='3']");
	public static By corporateBCBF = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[16]");
	public static By processingInterval = By.xpath("(//label[contains(text(),'Processing Interval')]//following::div[@onmousedown='Radzen.activeElement = null'])[1]");
	public static By processingIntervalvalue(String value) {
        return By.xpath("//li[@aria-label='>" + value + "']");
    }
	public static By expiryDate = By.xpath("(//input[@id='Date' ])[2]");
	public static By to = By.xpath("(//label[contains(text(),'To')]//following::div[@onmousedown='Radzen.activeElement = null'])[8]");
	public static By tovalue(String value) {
        return By.xpath("//li[@aria-label='>" + value + "']");
    }
	public static By addbutton = By.xpath("//button[contains(text(),'Add')]");
	public static By resetbutton = By.xpath("//button[contains(text(),'Reset')]");
	public static By editbutton = By.xpath("//button[contains(text(),'Edit')]");
	public static By activate_deactivatebutton = By.xpath("//button[contains(text(),'Activate/Deactivate  ')]");
	public static By gridvalues(String value) {
        return By.xpath("//tr[@class='rz-datatable-even  ']//span[@title='" + value + "']");
    }
	public static By firstrowingrid = By.xpath("(//tr[@class='rz-datatable-even  '])[1]");
	public static By statusColumnactive = By.xpath("(//i[@style='color:green'])[1]");
	public static By statusColumninactive = By.xpath("(//i[@style='color:red'])[1]");
	public static By allocationNameCheckbox = By.xpath("(//div[@class='rz-chkbox-box'])[1]");
	public static By deactivateButton = By.xpath("//button[contains(text(),'Deactivate')]");
	public static By activateButton = By.xpath("//button[contains(text(),'Activate')]");
	public static By validationMessage = By.xpath("//p[contains(text(),'Updated Successfully')]");
	public static By griddatarow = By.xpath("//tr[@class='rz-datatable-even  ']");
	public static By totalaccountselectedingrid = By.xpath("(//span[@class='rz-cell-data'])[1]");
	public static By totaloutstandingamountingrid = By.xpath("(//span[@class='rz-cell-data'])[2]");
	public static By downloadbutton = By.xpath("(//button[contains(text(),'Download')])[1]");
	public static By norecordsmsg = By.xpath("//span[contains(text(),'No records to display.')]");
	public static By downloadfiledropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[28]");
	public static By downloadfiledropdownvalue(String value) {
        return By.xpath("//li[@aria-label='>" + value + "']");
    }
	public static By OutstandingBalLimitOperatordropdown = By.xpath("(//div[@onmousedown='Radzen.activeElement = null'])[16]");
	public static By OutstandingBalLimitOperatordropdownvalue(String value) {
        return By.xpath("(//li[@aria-label='>" + value + "'])[3]");
    }
	public static By OutstandingBalLimit = By.xpath("//input[@onkeypress='DecimalWithHyphen(event, this); NumberWithMaxLengthWithHyphen(event, this)']");
	public static By getdownloadhistorybutton = By.xpath("//button[contains(text(),'Get Download History')]");
	public static By fromDateField = By.xpath("//input[@name='ActionDatefrom']");
	public static By fromdatevalue(String value) {
        return By.xpath("(//span[@class='rz-state-default' and text()='" + value + "'])[4]");
    }
	public static By todatevalue(String value) {
        return By.xpath("(//span[@class='rz-state-default' and text()='" + value + "'])[3]");
    }
	public static By toDateField = By.xpath("(//input[@name='ActionDateto' and @id='ActionDateto'])[1]");
	public static By searchbtn = By.xpath("(//button[@type='submit' and text()='Search'])[1]");
	public static By table = By.xpath("(//table[contains(@class, 'rz-grid-table-fixed')])[1]");
	public static By rows = By.xpath("//tr[contains(@class, 'rz-datatable')]");
	public static By cells = By.xpath("//span[@class='rz-cell-data']");
}
