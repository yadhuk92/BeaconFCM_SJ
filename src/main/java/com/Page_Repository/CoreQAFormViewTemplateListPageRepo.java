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
	        public static By ExpectedAnswerType = By.xpath("//label[.='Expected Answer Type']//following-sibling::div");
	       
	        public static By Valuesfield1 = By.xpath("//label[text()='Values']/following::input[1]");
	      
	        public static By expectedAnswerTypeDropdown = By.xpath("//label[contains(@class,'rz-dropdown-label')]");
	        public static By dropdownvalue = By.xpath("//li[normalize-space(.)='Drop Down']");
	        public static By textBoxOption = By.xpath("//li[normalize-space(.)='TextBox']");
	        public static By parentQuestion = By.xpath("//label[normalize-space(.)='Parent Question']/following-sibling::div[contains(@class,'rz-dropdown')]");
	        public static By ParentValue = By.xpath("//label[normalize-space(.)='Parent Value']/following-sibling::div[contains(@class,'rz-dropdown')]");
	        public static By  IsMandatory= By.xpath("//div[@class='my-form-group form-group pr-0']//label[text()='Is Mandatory']");
			public static By Addbtn  = By.xpath("//input[@type='submit' and @value='Add']");
	        public static By clearbtn = By.xpath("//input[@type='button' and @value='Clear']");
	        //public static By Cancel = By.xpath("//input[@type='button' and @value='Cancel']");
	        
	        public static By Cancel = By.xpath("//input[@value='Cancel']");
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
	       
	        public static By loader = By.xpath("//*[@class='spinner']");
	        public static By successMessage = By.xpath("//*[contains(text(),'Template Updated Successfully')]");
	       
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
	       
	       public static By actionButton = By.xpath("(//button[@id='dropdownMenu2'])[last()]");
	       public static By action_Edit = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[text()='Edit']");
	       public static By action_View = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[text()='View']");
	       public static By action_ViewHistory = By.xpath("//ul[contains(@class,'dropdown-menu')]//button[contains(text(),'View History')]");
	       
	       public static By templateNameField = By.xpath("(//input[@name='Name'])[1]");
	       public static By remarkField = By.xpath("//input[@type='text' and @maxlength='100' and contains(@class, 'searchinput')]");
	       //public static By updateButton = By.xpath("//button[normalize-space()='Update']");
	      public static By updateButton = By.xpath("//input[@type='submit' and @value='Update']");

           public static By updatedscreenexpectedAnswerTypeDropdown = By.xpath("//label[normalize-space()='Expected Answer Type']/following-sibling::div[contains(@class, 'rz-dropdown')]");
           public static By UpdatedscreenAddQuestioncheckbox = By.xpath("//label[normalize-space()='Add Question']/preceding-sibling::div[contains(@class, 'rz-chkbox')]");
           
           public static By updatedscreenQuestionfieldtextbox = By.xpath("//label[normalize-space()='Question']/following-sibling::input");
           public static By updatedscreenIsMandatory = By.xpath("//label[normalize-space()='Is Mandatory']/preceding-sibling::div//div[contains(@class,'rz-chkbox-box')]");
           public static By updatedscreenAddbtn = By.xpath("//input[@type='submit' and @value='Add' and contains(@class, 'btn-primary')]");
           
         
           public static By QuestionAction = By.xpath("(//button[@id='dropdownMenu3'])[last()]");
           //public static By actionButtonAgainstQuestion = By.xpath("(//span[@title=\"Q1\"]/../../td)[8]//div[@class='morebtnmenu']/button");
           public static By actionButtonAgainstQuestion = By.xpath("(//div[contains(@class, 'morebtnmenu')]/button[contains(@class, 'morebtn')])[last()]");
           public static By ActionbuttonagainstEditbutton = By.xpath("(//ul[contains(@class,'dropdown-menu')]//button[normalize-space(text())='Edit'])[last()]");
           public static By deleteOption = By.xpath("(//button[text()='Delete'])[last()]");
           public static By yesButtonOnPopup = By.xpath("//button[@type='button' and contains(@class, 'rz-button') and .//span[text()='Yes']]");
           public static By deleteSuccessMessage = By.xpath("//p[normalize-space(.)='Question deleted successfully']");
           
           public static By questionField =By.xpath("//label[.='Question']//following-sibling::input");
           public static By valuesField= By.xpath("//input[@id='A23yW0BkX0' and @disabled]");
           public static By updatebutton = By.xpath("//input[@value='Update']");
           public static By ismandatoryuncheck = By.xpath("//label[normalize-space()='Is Mandatory']/preceding-sibling::div//div[contains(@class,'rz-chkbox-box')]");
         //  public static By ExpectedTypedropdown1 = By.xpath("//label[.='Expected Answer Type']/following-sibling::div");)
           public static By Datepickerdropdown = By.xpath("//li[@role='option']/span[normalize-space(.)='Date Picker']");
           public static By successToastMessage = By.xpath("//*[contains(text(),'Template Updated Successfully')]");
           
          // public static By cancelbtn43 = By.xpath("//input[@type='button' and @value='Cancel']");
          public static By tickactivecheckbox = By.xpath("//label[text()='Active']/preceding-sibling::div[contains(@class,'rz-chkbox')]");
           //public static By tickactivecheckbox = By.xpath("(//label[text()='Active']/preceding-sibling::div[contains(@class,'rz-chkbox')]//div)[2]");
           
       
           public static By QATemplategrid =By.xpath ("//table[@class='rz-grid-table rz-grid-table-fixed']");
           public static By QATemplategridviewbutton = By.xpath("//button[.='View']");
           //public static By ViewTemplatescreenexpectedcondition= By.xpath ("//div[.='View Template'])[2]");
           public static By ViewTemplatescreenexpectedcondition = By.xpath("//div[@class='dvPageheadingCaption' and normalize-space(text())='View Template']");
           //public static By  elementofthespecificrecord = By.xpath("//div[@class='card-caption']");
           public static By elementofthespecificrecord = By.xpath("//div[@class='card-caption']/span[normalize-space(text())='UpdatedTemplate123']");
           public static By  Viewtemplateheadertile = By.xpath("//div[@class='card-caption']/span");
           
           public static By ViewTemplateQuestiongrid = By.xpath("//table[contains(@class, 'rz-grid-table')]");
           public static By ViewTemplategridcancelicon = By.xpath("//a[contains(@class,'rz-dialog-titlebar-close')]");
           public static By QATemplatetile = By.xpath("//div[@class='dvPageheadingCaption' and normalize-space()='Q&A Template']");
           
           //Q&A Template Page View History Xpath
          
           public static By Viewhistorybutton = By.xpath("//button[@class='dropdown-item' and normalize-space()='View History']");
           //public static By templateHistoryPopupTitle = By.xpath("//div[@class='rz-dialog-titlebar']/span[contains(text(),'Template History')]");)
           public static By templateHistoryPopupTitle = By.xpath("//div[@class='rz-dialog-titlebar']/span[contains(text(),'Template History')]");
          
          public static By TemplateNamewrittentext = By.xpath("(//label[normalize-space(text())='Template Name'])[1]");
          public static By input_TemplateName_HistoryPopup = By.xpath("(//label[normalize-space(text())='Template Name'])[1]/following-sibling::input");
          public static By col_Status = By.xpath("((//span[normalize-space(.)='Status'])[1]/../../../th)[1]");
          public static By col_LastModified = By.xpath("((//span[normalize-space(.)='Status'])[1]/../../../th)[2]");
          public static By col_Remark = By.xpath("((//span[normalize-space(.)='Status'])[1]/../../../th)[3]");
          
          //TC 53
          public static By historyGridRow = By.xpath("//div[@class='rz-datatable-scrollable-body']//table//tbody//tr");
          public static By statusCell = By.xpath("//span[@class='rz-column-title' and contains(., 'Status')]");  // relative to row
          public static By lastModifiedCell = By.xpath("(//table[contains(@class,'rz-grid-table')]//tbody/tr[last()]/td[2]//span[@class='rz-cell-data'])");
          public static By remarkCell = By.xpath("//span[@class='rz-column-title' and contains(normalize-space(.), 'Remark')]");
          
          //TC 54
        
          public static By statusIconImg = By.xpath("//i[@class='rzi d-inline-flex justify-content-center align-items-center' and contains(text(), 'check_circle')]");  
          
          public static By templateNameSearchField = By.xpath("//input[@placeholder='Template Name']");
          public static By templateSearchButton = By.xpath("//input[@type='submit' and @value='Search']");
          
          public static By activestatustoastmessage = By.xpath("//p[normalize-space(text())='Template Updated Successfully.']");
          
          
          public static By questionTypeOption(String fieldType) {
        	   String  typeXpath = "//li[@role='option']/span[text()='"+fieldType+"']";
        	   return By.xpath(typeXpath);
           }
           public static By actionButtonAgainstQuestion(String questionName) {
        	   String  typeXpath = "(//span[@title='"+questionName+"']/../../td)[8]//div[@class='morebtnmenu']/button";
        	   return By.xpath(typeXpath);
           }  
           
           
}

