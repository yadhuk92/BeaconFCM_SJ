package com.Page_Repository;
import org.openqa.selenium.By;
public class CoreViewTemplateCategoryListPageRepo {
	
	public static By QAFormMainMenu = By.xpath("//*[@title='View Template List']");
	
	public static By ViewTemplateListSubmenu = By.xpath("//*[contains(text(),'Q&A Form')]");
	public static By CreateNew = By.xpath("//button[contains(text(),'Create New')]");
    public static By TemplateName = By.xpath("//label[normalize-space(.)='Template Name']/following-sibling::input[@name='Name']");
    public static By Question  = By.xpath("//label[normalize-space(.)='Question']/following-sibling::input[@name='Name']");
    public static final By ExpectedAnswerTypeDropdown = By.xpath("//label[normalize-space(.)='Expected Answer Type']" + "/following-sibling::div[contains(@class,'rz-dropdown')]");
    public static By textBoxOption = By.xpath("//li[normalize-space(.)='TextBox']");
    public static By Addbtn  = By.xpath("//input[@type='submit' and @value='Add']");
    
    public static By ViewTemplatecategorylist = By.xpath("//a[@title='View Template Category Mapping List']");
    public static By MappingTemplateAndCategoryHeader = By.xpath("//div[contains(text(),'Mapping Template and Category')]");
   
    public static By Gridspantextbox = By.xpath("//span[@title='TextBox']");
    public static By header_LastModified = By.xpath("//th//span[normalize-space()='Last Modified']");
    
    public static By TemplateNameDropdown = By.xpath("//label[text()='Template Name']/following-sibling::div[contains(@class,'rz-dropdown')]");
    public static By AssetCategoryDropdown = By.xpath("//label[text()='Asset Category']/following-sibling::div[contains(@class,'rz-dropdown')]");
    public static By AccountCategoryDropdown = By.xpath("//label[text()='Account Category']/following-sibling::div[contains(@class,'rz-dropdown')]");
    public static By SaveButton = By.xpath("//input[@type='submit' and @value='Save']");
    public static By ClearButton = By.xpath("//input[@type='button' and @value='Clear']");
    
    //public static By TemplateNameCategorylistdropdownoption = By.xpath("//label[contains(@class,'rz-dropdown-label') and normalize-space()='CategoryList Template']");
   
    public static By Categorylistdropdownoption = By.xpath("//span[text()='CategoryList Template']");
    public static By exp_CategoryListdropdownOption = By.xpath("//label[text()='CategoryList Template']");
    
    public static By assertCategoryDropdown = By.xpath("//label[.='Asset Category']/following-sibling::div");
    public static By nap = By.xpath("//span[text()='NPA Category']");
    public static By exp_nap = By.xpath("//label[text()='NPA Category']");
    public static By sma = By.xpath("//span[text()='SMA Category']");
    public static By exp_sma = By.xpath("//label[text()='SMA Category']");
    
    //account category dropdown
    public static By AccCategoryDropdown = By.xpath("//label[.='Account Category']/following-sibling::div");
    public static By SMA0 = By.xpath("//span[contains(.,'SMA 0')]");
    public static By exp_SMA0 = By.xpath("//label[contains(.,'SMA 0')]");
    public static By SMA1 = By.xpath("//span[contains(.,'SMA 1')]");
    public static By SMA2 = By.xpath("//span[contains(.,'SMA 2')]");
    
    public static By d1 = By.xpath("//span[contains(.,'DOUBTFUL-1')]");
    public static By d2 = By.xpath("//span[contains(.,'DOUBTFUL-2')]");
    public static By d3 = By.xpath("//span[contains(.,'DOUBTFUL-3')]");
    public static By subStandard = By.xpath("//span[contains(.,'SUB-STANDARD')]");
    public static By lossAsset = By.xpath("//span[contains(.,'LOSS ASSET')]");
    
    public static By save = By.xpath("//input[@value='Save']");
    public static By exp_msg = By.xpath("//p[.='Template Mapping Added Successfully.']");
    public static By clear = By.xpath("//input[@value='Clear']");
    
    public static By gridTemplateName = By.xpath("//span[text()='Template Name']");
    public static By gridAccountCategory = By.xpath("//span[text()='Account Category']");
    public static By gridStatus = By.xpath("//span[text()='Status']");
    public static By gridActions = By.xpath("//span[text()='Action']");
    
    public static By templateNameData = By.xpath("//span[@title='CategoryList Template']");
    public static By accountCategoryData = By.xpath("//span[@title='SMA 0 (01-30 Days)']");
    public static By greenTick = By.xpath("(//span[@title='SMA 0 (01-30 Days)']/../../td)[3]//i[@style='color:green']");
    public static By redTick = By.xpath("(//span[@title='SMA 0 (01-30 Days)']/../../td)[3]//i[@style='color:red']");
    
    public static By menu = By.xpath("(//span[@title='SMA 0 (01-30 Days)']/../../td)[4]//button[@id='dropdownMenu2']");
    public static By deactivateActivate = By.xpath("//button[.='Activate/De-activate']");
    public static By edit = By.xpath("//button[.='Edit']");
    
    public static By assertCategoryGray = By.xpath("//label[.='Asset Category']/following-sibling::div//input");
    public static By accCategoryGray = By.xpath("(//label[.='Account Category']/following-sibling::div//input)[1]");
    public static By exp_updateMsg = By.xpath("//p[.='Template Already Mapped.']");
    
    public static By exp_ToastMsgTemplateName = By.xpath("//p[.='Please Select Template Name.']");
    public static By exp_ToastMsgAssertCategory = By.xpath("//p[.='Please Select Asset Category.']");
    public static By exp_ToastMsgAccountCategory = By.xpath("//p[.='Please Select Account Category.']");
    
    //menu
    public static By viewTemplateListMenu = By.xpath("//a[text()='View Template List']");

    
  
    
  
}
