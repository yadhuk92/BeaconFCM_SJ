package com.Page_Repository;
import org.openqa.selenium.By;
public class CoreQAFormViewTemplateListPageRepo {
	

			public static By QAFormMainMenu = By.xpath("//*[@title='View Template List']");
			public static By ViewTemplateListSubmenu = By.xpath("//*[contains(text(),'Q&A Form')]");
			public static By CreateNew = By.xpath("//button[contains(text(),'Create New')]");
	        public static By TemplateName = By.xpath("//label[normalize-space(.)='Template Name']/following-sibling::input[@name='Name']");
	        public static By Question  = By.xpath("//label[normalize-space(.)='Question']/following-sibling::input[@name='Name']");
	       
	        public static final By ExpectedAnswerTypeDropdown = By.xpath("//label[normalize-space(.)='Expected Answer Type']" + "/following-sibling::div[contains(@class,'rz-dropdown')]");
	        public static By ExpectedAnswerTypeOptions = By.xpath("//li[contains(@class, 'rz-dropdown-item')]");
	        public static final By ValuesField = By.xpath("//label[normalize-space(.)='Values']"+ "/following-sibling::input");
	       // public static By DropdownOption = By.xpath("//li[contains(@class, 'rz-dropdown-item') and normalize-space(text())='Drop Down']");
	        public static By expectedAnswerTypeDropdown = By.xpath("//label[contains(@class,'rz-dropdown-label')]");
	        public static By dropdownvalue = By.xpath("//label[text()='Expected Answer Type']/following-sibling::div//label[contains(@class,'rz-dropdown-label')]");
	        public static By textBoxOption = By.xpath("//li[normalize-space(.)='TextBox']");
	        public static By parentQuestion = By.xpath("//label[normalize-space(.)='Parent Question']/following-sibling::div[contains(@class,'rz-dropdown')]");
	        public static By ParentValue = By.xpath("//label[normalize-space(.)='Parent Value']/following-sibling::div[contains(@class,'rz-dropdown')]");
	        public static By  IsMandatory= By.xpath("//div[@class='my-form-group form-group pr-0']//label[text()='Is Mandatory']");
			public static By Addbtn  = By.xpath("//input[@type='submit' and @value='Add']");
	        public static By clearbtn = By.xpath("//input[@type='button' and @value='Clear']");
	        public static By Cancel = By.xpath("//input[@type='button' and @value='Cancel']");
	        public static By TemplateCreationGrid = By.xpath("//*[@id=\"dvbody\"]/div[3]/div");
	        public static By isMandatoryCheckbox = By.xpath("//div[label[text()='Is Mandatory']]//div[contains(@class,'rz-chkbox-box')]");
	        public static By addButton = By.xpath("//input[@type='submit' and @value='Add']");
	        public static By gridFirstRowQuestion = By.xpath("//table//tbody/tr[1]/td[2]");
	        public static By gridFirstRowMandatory = By.xpath("//table//tbody/tr[1]/td[7]");
	        
	        /*public void labels(String fieldName)
	        
	    	{
	    		String filednames= "//label[contains(text(), '" + fieldName + "')]";
	    	}*/
	        
	        public static By DropdownOption(String optionText) {return By.xpath("//li[contains(@class, 'rz-dropdown-item') and normalize-space(text())='" + optionText + "']");
	        }
	        }
	    
		

