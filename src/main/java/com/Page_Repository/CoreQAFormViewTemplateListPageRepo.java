package com.Page_Repository;
import org.openqa.selenium.By;
public class CoreQAFormViewTemplateListPageRepo {
	

		
			public static By QAFormMainMenu = By.xpath("//*[@title='View Template List']");
			public static By ViewTemplateListSubmenu = By.xpath("//*[contains(text(),'Q&A Form')]");
			public static By CreateNew = By.xpath("//*[@id=\"dvbody\"]/form/div/div/div/div[1]/div/div/div[2]/div/div/button[2]");
	       // public static By TemplateName  = By.xpath("//*[@id=\"wuauCGXr3U\"]");
	       public static By TemplateName = By.xpath("//label[normalize-space(.)='Template Name']/following-sibling::input[@name='Name']");
	        public static By Question  = By.xpath("//label[normalize-space(.)='Question']/following-sibling::input[@name='Name']");
	        public static By ExpectedAnswerType = By.xpath("//*[@id=\"-ZLKN9yVv0\"]/label");
	        public static By Values = By.xpath("//*[@id=\"TUhLcMuwMk\"]");
	        public static By parentQuestion = By.xpath("//*[@id=\"VrNmpuZ3pU\"]/label");
	        public static By ParentValue = By.xpath("//*[@id=\"ZXc0Nocfpk\"]/label");
	        public static By  IsMandatory= By.xpath("//*[@id=\"ObcTt2pD7E\"]/div[2]");
			public static By Addbtn  = By.xpath("//*[@id=\"dvbody\"]/div[2]/div[2]/form/div/div[4]/div/div/div/input[1]");
	        public static By clear = By.xpath("//*[@id=\"dvbody\"]/div[2]/div[2]/form/div/div[4]/div/div/div/input[2]");
	        public static By Cancel = By.xpath("//*[@id=\"dvbody\"]/div[2]/div[2]/form/div/div[4]/div/div/div/input[3]");
	        public static By TemplateCreationGrid = By.xpath("//*[@id=\"dvbody\"]/div[3]/div");
	        
	        
	        
	        
	        public void labels(String fieldName)
	    	{
	    		String filednames= "//label[contains(text(), '" + fieldName + "')]";

	    	
	    	 
	    	}
		}

