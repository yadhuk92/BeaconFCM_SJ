package com.Page_Repository;

import org.openqa.selenium.By;

public class ViewTemplateList_Repository {
	public static By QandAMenu=By.xpath("//span[text()='Q&A Form']");
	public static By ViewTemplateListSubmenu=By.xpath("//a[@title='View Template List']");
	public static By createNewButton=By.xpath("//button[text()='Create New']");
	public static By Label_TemplateName=By.xpath("//label[text()='Template Name']");
    public static By Label_Question=By.xpath("//label[text()='Question']");
    public static By Label_ExpectedAnswerType=By.xpath("//label[text()='Expected Answer Type']");
    public static By Label_Values=By.xpath("//label[text()='Values']");
    public static By Label_ParentQuestion=By.xpath("//label[text()='Parent Question']");
    public static By Label_ParentValue=By.xpath("//label[text()='Parent Value']");
    public static By Label_IsMandatory=By.xpath("//label[text()='Is Mandatory']");
    public static By Button_Add=By.xpath("//input[@type='submit' and @value='Add']");
    public static By Button_Clear=By.xpath("//input[@type='button' and @value='Clear']");
    public static By Button_Cancel=By.xpath("//input[@type='button' and @value='Cancel']");
    public static By Col_orderNumber=By.xpath("//span[text()='Order Number']");
    public static By Col_question=By.xpath("//span[text()='Question']");
    public static By Col_ExpectedAnsType=By.xpath("//span[text()='Expected Answer Type']");
    public static By Col_Values=By.xpath("//span[text()='Values']");
    public static By Col_ParentQuestion=By.xpath("//span[text()='Parent Question']");
    public static By Col_ParentValue=By.xpath("//span[text()='Parent Value']");
    public static By Col_IsMandatory=By.xpath("//span[text()='Is Mandatory']");
    public static By Col_Action=By.xpath("//span[text()='Action']");
    
    public static By warningmsg=By.xpath("//p[text()='Please enter the Template Name.']");
    public static By Field_TemplateName=By.xpath("(//input[@class='rz-textbox valid form-control mandatory-color'])[1]");
    public static By Field_Question=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div[2]/form/div/div[3]/div/div[1]/div/input");
    public static By Field_ExpectedAnsType=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div[2]/form/div/div[3]/div/div[2]/div/div");
    public static By AllValue_ExpectedAnsType=By.xpath("/html/body/div[2]");
    public static By Field_Value=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div[2]/div[2]/form/div/div[3]/div/div[3]/div/input");
    
    public static By textbox=By.xpath("//li[@aria-label='>TextBox']");
    public static By dropdown=By.xpath("//li[@aria-label='>Drop Down']");
    public static By datepicker=By.xpath("//li[@aria-label='>Date Picker']");
    public static By numberInputField=By.xpath("//li[@aria-label='>Number input field']");
    public static By amountField=By.xpath("//li[@aria-label='>Amount field']");
    public static By percentageField=By.xpath("//li[@aria-label='>Percentage field']");
    
    public static By ClearIcon=By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/form/div/div[3]/div/div[2]/div/div/i");
}

