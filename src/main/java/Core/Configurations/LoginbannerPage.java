package Core.Configurations;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Page_Repository.LoginBannerConfiRepo;


//Constructor
public class LoginbannerPage extends Base_Class{
	LoginBannerConfiRepo Page_Repository=new LoginBannerConfiRepo();
    private static WebDriver driver;
   
   public LoginbannerPage(WebDriver driver) {
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    // Method to click on the Configuration menu
    public boolean clickOnConfigurationMenu()throws InterruptedException {
    	//Common.fluentWait("Spinner to disappear", Page_Repository.spinner);
    	Common.fluentWait("Configuration menu to display", Page_Repository.configurationmenu);
    	click(Page_Repository.configurationmenu);
    	return true;
    }

    // Method to click on the Login Banner Configuration sub-menu
    public boolean clickOnLoginBannerConfigMenu() throws InterruptedException {
    	Common.fluentWait("LoginBannerConfi to display",Page_Repository.LoginBannerConfimenu);
        click(Page_Repository.LoginBannerConfimenu);
		return true;
    }   
    //Method to check if fields are Empty
    public boolean areFieldsEmpty() {
//    	Common.fluentWait("spinner", Page_Repository.spinner);
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(Page_Repository.usertypedropdown)));
    	
    	WebElement Usertypedropdown= driver.findElement(Page_Repository.usertypedropdown);
        WebElement Bannertypedropdown= driver.findElement(Page_Repository.bannertypedropdown);
    	WebElement Sectiondropdown= driver.findElement(Page_Repository.sectiondropdown);
    	WebElement Detailtext= driver.findElement(Page_Repository.detailtext);
    	WebElement Headingtext= driver.findElement(Page_Repository.headingtext);
//    	System.out.println("Element texts ="+Usertypedropdown.getText()+""+Bannertypedropdown.getText()+" "+Sectiondropdown.getText()+" "+Detailtext.getText()+" "+Headingtext.getText());
    	
 return   	Usertypedropdown.getText().trim().equals("Select")&&
    	Bannertypedropdown.getText().trim().equals("Select")&&
    	Sectiondropdown.getText().trim().equals("Select")&&
    	Detailtext.getText().trim().isEmpty()&&
    	Headingtext.getText().trim().isEmpty();
  			
   }
   // Method to check if buttons are displayed
   public boolean areButtonsDisplayed() throws InterruptedException {
    	WebElement searchbutton= driver.findElement(Page_Repository.searchbutton);
    	WebElement resetbutton= driver.findElement(Page_Repository.resetbutton);
       return searchbutton.isDisplayed() && resetbutton.isDisplayed();
    }
   // Method to leave fields empty and click submit
 //  public boolean leaveFieldsEmptyAndSubmit() throws InterruptedException {
       // Assuming the fields are already empty by default
//	   WebElement submitbutton =driver.findElement(Page_Repository.submitbutton);
 //      click(Page_Repository.submitbutton);
//	return true;
 //  }
   // Method to get the displayed warning message text
   public boolean getWarningMessageText() {
	   String warningmsg =driver.findElement(Page_Repository.warningmsg).getText();
       return true;
   }
   //Method click on Usertypedropdown
   public boolean clickuserdropdown()throws InterruptedException{
	   WebElement usertypedropdown=driver.findElement(Page_Repository.usertypedropdown);
	   usertypedropdown.click();

   // Validate dropdown values
    
       List<WebElement> allvalue=driver.findElements(Page_Repository.UserTypeallvalues); 
       List<String> userTypes = new ArrayList();
      
       userTypes.contains("Internal User");
       userTypes.contains("Call Centre");
       userTypes.contains("Collection Agency");
       return true;
   }
 
//Method to select 'Internal User'option from Usertype dropdown
   public  boolean selectinternaluser() throws InterruptedException {
	   List<WebElement> usertypeoptionvalue=driver.findElements(Page_Repository.UserTypeallvalues);
	   
	   for(WebElement wb:usertypeoptionvalue)
	   {
		   if(wb.getText().contains("Internal User"))
		   {
			   wb.click();
			   return true;
		   }
	   }

	return false;   
 }

   //Method click on Bannertypedropdown
   public boolean clickBannertypedropdown() throws InterruptedException{
	   WebElement bannertypedropdown=driver.findElement(Page_Repository.bannertypedropdown);
	   click(Page_Repository.bannertypedropdown);
	return true;
           }
   //Select Information/hyperlink from bannertype dropdown
   public boolean clickInfo() {
	   List<WebElement> allvalueofbannertype=driver.findElements(Page_Repository.Bannertypeallvalue);
//	   List <String> str=new ArrayList();
	   for(WebElement wb: allvalueofbannertype)
	   {
		   if(wb.getText().contains("Information"))
		   {
//			   wb.isDisplayed();
			   wb.click();
			   return true;
		   }
	   }
	   return false;
 }
   //Method to click Section dropdown
   public boolean clickSectiondropdown()throws InterruptedException{
	   WebElement sectiondropdown=driver.findElement(Page_Repository.sectiondropdown);
	   click(Page_Repository.sectiondropdown);
	   return true;
     }
   //select header1 from section dropdown
   public boolean selectheader1() throws InterruptedException {
//	   List<WebElement> headers=driver.findElements(Page_Repository.Sectionallvalue);
//	   List <String> str=new ArrayList();
	   WebElement header1=driver.findElement(Page_Repository.header1);
	   header1.click();
	   return true;

   }
  //Method to input value in "Heading"field
    // Method to enter text into the Heading field
   public String Headingfieldinput(String HeadingField) {
	   WebElement headingfield=driver.findElement(Page_Repository.headingtext);
	   headingfield.clear();
	   headingfield.sendKeys(HeadingField);
   
   // Method to get the text from the Heading field
      String Heading=headingfield.getAttribute("value");
      return Heading;
   }
 //Method to input value in "Details"field	
   // Method to enter text into the Details field
   public String Detailfieldinput(String DetailField) {
	   WebElement detailfield=driver.findElement(Page_Repository.detailtext);
	   detailfield.clear();
	   detailfield.sendKeys(DetailField);
	// Method to get the text from the Details field 
	   
	   String str=detailfield.getAttribute("value");
	   return str;
   }
 //Method to submit the details
   public boolean clicksubmit() throws InterruptedException {
	   WebElement submit=driver.findElement(Page_Repository.submitbutton);
	   click(Page_Repository.submitbutton);
	   return true;
   }
   
  //Method to click on profile dropdown
   public boolean Clickprofiledropdown(){
	   WebElement profiledropdown=driver.findElement(Page_Repository.profilebutton);
	   profiledropdown.click();
	   return true;
   }
  //Method to click logout button
   public boolean Clicklogout() {
	   WebElement logoutbutton=driver.findElement(Page_Repository.logout);
	   logoutbutton.click();
	   return true;
	   
   }
  
//Method to get profilepage text
   public boolean profilepagetext() {
	   List<WebElement> profilepage=driver.findElements(Page_Repository.profilepage);
	   List<String> str=new ArrayList();
	   
	   str.contains(Page_Repository.detailtext);
	   str.contains(Page_Repository.headingtext);
	   
	   return true;
   }
   
   //Method click on Usertypedropdown
   public boolean clickusertypedropdown()throws InterruptedException{
	   WebElement usertypedropdown=driver.findElement(Page_Repository.usertypedropdown);
	   usertypedropdown.click();
	return true; 
   }
   
 //Method to select hyperlink from bannertype
   public boolean hyperlink() {
	   WebElement hyperlink=driver.findElement(Page_Repository.hyperlink);
	   hyperlink.click();
	   return true;
   }
   
 //Method to select link1 from section dropdown
   public boolean selectlink1FromSectiondropdown() {
	   WebElement sectiondropdownlink1=driver.findElement(Page_Repository.sectionlink1);
	   sectiondropdownlink1.click();
	   return true;
   }
 
 //Method to Input link1 in Headerfield  
   public void headerlink(String Headerlink) {
	  WebElement headerlink=driver.findElement(Page_Repository.headingtext);
	   headerlink.clear();
	   headerlink.sendKeys(Headerlink);
	   
   }
   //Method to Input link1 in Detailfield  
   public void detaillink(String Detaillink) {
	  WebElement detaillink=driver.findElement(Page_Repository.detailtext);
	  detaillink.clear();
	  detaillink.sendKeys(Detaillink);
	   
   }
 //Method to get data(link1)from login page
   public boolean dataforlink1() {
	   List<WebElement>link1OnProfilePage= driver.findElements(Page_Repository.ProfilePagewithHeaderandDetaillink);
	   List<String> str =new ArrayList();
	   
	   str.contains(Page_Repository.headingtext);
	   str.contains(Page_Repository.detailtext);
	   
	   return true;
   }
   
   
 //Method to click on link1
   public void clickonProfilePagelink1() {
	   WebElement link1=driver.findElement(Page_Repository.link1OnProfilePage);
	   link1.click();
   }
   
  //Method to click on search button
   public void ClickSearchbutton() {
	   WebElement serchbutton=driver.findElement(Page_Repository.searchbutton);
	   serchbutton.click();
   }
   
}



    	
    	
    	
    	
    	
    	
    	
    	
    	




