package Core.QandAForm;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.BasePackage.Base_Class;
import com.BasePackage.DBUtils;
import com.Page_Repository.ViewTemplateList_Repository;
//Constructor
public class ViewTemplateList_MainClass extends Base_Class{
	
	
		ViewTemplateList_Repository Page_Repository=new ViewTemplateList_Repository();
	    private static WebDriver driver;
	   
	   public ViewTemplateList_MainClass(WebDriver driver) {
	    	this.driver=driver;
	    	PageFactory.initElements(driver, this);
	    }
//Execute the Deletion Query
 public void DeleteAnsDetails() {
			try
			{
				String query="DELETE FROM QAF_OPTION_ANSWER_DETAILS";
		        DBUtils.executeSQLStatement(query);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
public void DeleteTempDetails() {
			try
			{
				String query="DELETE FROM QAF_OPTION_TEMPLATE_DETAILS";
		        DBUtils.executeSQLStatement(query);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
 public void DeleteAnsMaster() {
		try
		{
			String query="DELETE FROM QAF_OPTION_ANSWER_MASTER";
	        DBUtils.executeSQLStatement(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}
 public void DeleteTemplateDetails() {
		try
		{
			String query="DELETE FROM QAF_QSTN_TEMPLATE_DETAILS";
	        DBUtils.executeSQLStatement(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}
public void DeleteTemplateHistory() {
		try
		{
			String query="DELETE FROM QAF_QSTN_TEMPLATE_HISTORY";
	        DBUtils.executeSQLStatement(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}
public void DeleteCategoryMapping() {
	try
	{
		String query="DELETE FROM QAF_TEMPLATE_CATEGORY_MAPPING";
        DBUtils.executeSQLStatement(query);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void DeleteTemplateMaster() {
	try
	{
		String query="DELETE FROM QAF_QSTN_TEMPLATE_MASTER";
        DBUtils.executeSQLStatement(query);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public boolean IsCreateNewPresent() {
	WebElement createNew=driver.findElement(Page_Repository.createNewButton);
	createNew.isDisplayed();
	return true;
}
public boolean IsTemplateNamePresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_TemplateName);
	wb.isDisplayed();
	return true;
}
public boolean IsQuestionPresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_Question);
	wb.isDisplayed();
	return true;
}
public boolean IsExpectedAnswerTypePresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_ExpectedAnswerType);
	wb.isDisplayed();
	return true;
}
public boolean IsValuesPresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_Values);
	wb.isDisplayed();
	return true;
}
public boolean IsParentQuestionPresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_ParentQuestion);
	wb.isDisplayed();
	return true;
}
public boolean IsParentValuePresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_ParentValue);
	wb.isDisplayed();
	return true;
}
public boolean IsMandatoryPresent() {
	WebElement wb=driver.findElement(Page_Repository.Label_IsMandatory);
	wb.isDisplayed();
	return true;
}
public boolean IsAddButtonPresent() {
	WebElement wb=driver.findElement(Page_Repository.Button_Add);
	wb.isDisplayed();
	return true;
}
public boolean IsClearButtonPresent() {
	WebElement wb=driver.findElement(Page_Repository.Button_Clear);
	wb.isDisplayed();
	return true;
}
public boolean IsCancelButtonPresent() {
	WebElement wb=driver.findElement(Page_Repository.Button_Cancel);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_OrdernumberPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_orderNumber);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_QuestionPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_question);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_ExpectedAnsTypePresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_ExpectedAnsType);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_ValuesPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_Values);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_ParentQuestionPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_ParentQuestion);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_ParentValuePresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_ParentValue);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_IsMandatoryPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_IsMandatory);
	wb.isDisplayed();
	return true;
}
public boolean IsCol_ActionPresent() {
	WebElement wb=driver.findElement(Page_Repository.Col_Action);
	wb.isDisplayed();
	return true;
}
public boolean warningmsg() {
	WebElement wb=driver.findElement(Page_Repository.warningmsg);
	wb.getText();
	return true;
}

public String InputTemplateName(String TemplateName) {
WebElement wb=driver.findElement(Page_Repository.Field_TemplateName);
wb.clear();
wb.sendKeys(TemplateName);
return TemplateName;
}
public String InputQuestion(String Question) {
WebElement wb=driver.findElement(Page_Repository.Field_Question);
wb.clear();
wb.sendKeys(Question);
return Question;
}
public boolean presenceofExpectedAnswerTypeFieldsvalues() {
	List <WebElement> wb=driver.findElements(Page_Repository.AllValue_ExpectedAnsType);
	List<String> AllValues= new ArrayList();
	
	AllValues.contains(">TextBox");
	AllValues.contains(">Drop Down");
	AllValues.contains(">Date Picker");
	AllValues.contains(">Number input field");
	AllValues.contains(">Amount field");
	AllValues.contains(">Percentage field");
return true;	
	
}
public boolean NonEditablilityOfField_Values() {
	WebElement wb=driver.findElement(Page_Repository.Field_Value);
	return!wb.isEnabled();
	 
}

public boolean EditablilityOfField_Values() {
	WebElement wb=driver.findElement(Page_Repository.Field_Value);
	wb.isEnabled();
	return true;
}
}