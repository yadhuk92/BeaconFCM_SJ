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
	        //public static final By ValuesField = By.xpath("label[text()='Values']/following-sibling::input");
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
	        
	        public static By gridHeaderOrderNumber = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Order Number']]");
	        public static By gridHeaderQuestion = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Question']]");
	        public static By gridHeaderExpectedAnswerType = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Expected Answer Type']]");
	        public static By gridHeaderValues = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Values']]");
	        public static By gridHeaderParentQuestion = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Parent Question']]");
	        public static By gridHeaderParentValue = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Parent Value']]");
	        public static By gridHeaderIsMandatory = By.xpath("//table[contains(@class, 'rz-grid-table')]//th[.//span[normalize-space()='Is Mandatory']]");
	    
	        
	        public static By templateGrid = By.xpath("//table[contains(@class,'table')]");
	        public static By templateNameCells = By.xpath("//table[contains(@class,'table')]//tbody//tr//td[1]");
	       
	        public static By templatesearchNameInput = By.xpath("//input[@type='search' and contains(@class, 'searchinput')]");
	        public static By searchButton = By.xpath("//button[contains(text(),'Search')]");
	        //public static By templateNameColumn_LocatingsampleTemplaterowvalue= By.xpath("//table[contains(@class,'table')]/tbody/tr/td[1]");
	    
	        public static By header_TemplateName = By.xpath("//th//span[normalize-space()='Template Name']");
	        public static By header_Status = By.xpath("//th//span[normalize-space()='Status']");
	        public static By header_LastModified = By.xpath("//th//span[normalize-space()='Last Modified']");
	        public static By header_Action = By.xpath("//th//span[normalize-space()='Action']");
	        public static By statusGreenTickIcon = By.xpath("//i[contains(@class, 'rzi') and text()='check_circle' and contains(@style, 'color:green')]");
	      
	        
	       public static By lastModifiedColumn = By.xpath("//td[contains(@class,'last-modified-column') or position()=3]");
	       
	       public static By actionButton = By.xpath("//div[contains(@class,'morebtnmenu')]//button[contains(@class,'morebtn')]");
	       public static By action_Edit = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[text()='Edit']");
	       public static By action_View = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[text()='View']");
	       public static By action_ViewHistory = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[contains(text(),'View History')]");
	       
	       public static By templateNameField = By.xpath("//input[@name='Name']");
	       public static By updateButton = By.xpath("//button[normalize-space()='Update']");
	      
}
		

