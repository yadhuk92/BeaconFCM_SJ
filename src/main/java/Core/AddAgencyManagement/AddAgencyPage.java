package Core.AddAgencyManagement;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.DownloadedExcelReader;
import com.BasePackage.DownloadedExcelReader.DataSummary;
import com.Page_Repository.AddAgencyPageRepo;
import com.Page_Repository.CoreAllocationSummaryRepo;
import com.Page_Repository.DispositionMasterPageRepo;
import com.Utility.Log;
import java.sql.Connection;

public class AddAgencyPage {
	
	private WebDriver driver;
	
	public AddAgencyPage(WebDriver driver) {
		Log.info("Initializing Agency Management Page...");
		this.driver = driver;
		Log.info("WebDriver instance assigned.");
		Log.info("Initializing Web elements using PageFactory...");
		PageFactory.initElements(driver, this);
		Log.info("Web elements initialized using PageFactory.");
		Log.info("CallCenterAccountFiltrationPage initialization completed.");
	}
	
	   // Method to navigate to Agency Management Main menu -AM
    public void navigateAgencyManagement() {
    	Log.info("Starting navigation to the Agency management Main Menu..");
    	WebElement AgencyManagementMainMenu = driver.findElement(AddAgencyPageRepo.AgencyManagementmainmenu);
    	AgencyManagementMainMenu.click();
    	Log.info("Clicked on Agency management Main Menu. ");
    }

    // Method to navigate to Add Agency submenu under  Agency Management Main menu -AM
    public void navigateToAddAgencyOption() {
    	Log.info("Navigating t Add agency submenu.");
    	WebElement AddAgencySubmenu = driver.findElement(AddAgencyPageRepo.AddAgencySubmenu);
    	AddAgencySubmenu.click();
    	Log.info("Clicked on Add Agency  sub Menu. ");
    }
    
    public void VerifyrequiredFileds()
    {
    	Log.info("Verifying PAN Number, GST Number, Constitution Type, Collection Agency Name fields displayed as expected.");
    	WebElement AddAgencySubmenu = driver.findElement(AddAgencyPageRepo.AddAgencySubmenu);
    	AddAgencySubmenu.click();
    	Log.info("Clicked on Add Agency  sub Menu. ");
    }
    
    public String  Validate_ErrorMessage_InvalidPAN()
    {
    	Log.info("Entering invalid PAN Number.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
    	PANField.sendKeys("A1234KJHG7");
    	Log.info("Entered invalid PAN number");
    	
    	Log.info("Clicking outside the Pan Field to validate the error message");
    	WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
    	GSTField.click();
    	
    	WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.PanInvalidError);
    	String message=ErrorMessage.getText();
    	
    	return message;
    }
    
    public String  Validate_ErrorMessage_InvalidGST()
    {
    	Log.info("Entering invalid GST Number.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.GSTField);
    	PANField.sendKeys("123com");
    	Log.info("Entered invalid GST number");
    	
    	Log.info("Clicking outside the Pan Field to validate the error message");
    	WebElement ConsultaionNameField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
    	ConsultaionNameField.click();
    	
    	WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.GSTInvalidError);
    	String message=ErrorMessage.getText();
    	
    	return message;
    }
    
    public String  Validate_ErrorMessage_InvalidCollectionName()
    {
    	Log.info("Entering special charcters in  Coolection Agency Name.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
    	PANField.sendKeys("ambi@ry");
    	Log.info("Entered special charcters in  Coolection Agency Name.");
    	
    	Log.info("Clicking outside the Pan Field to validate the error message");
    	WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
    	GSTField.click();
    	
    	WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.CoolectionAgencyInvalidError);
    	String message=ErrorMessage.getText();
    	
    	return message;
    }
    public String  Validate_ErrorMessage_ExistingPAN()
    {
    	Log.info("Entering invalid PAN Number.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
    	PANField.sendKeys("A1234KJHG7");
    	Log.info("Entered invalid PAN number");
    	
    	Log.info("Clicking outside the Pan Field to validate the error message");
    	WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
    	GSTField.click();
    	
    	WebElement ErrorMessage = driver.findElement(AddAgencyPageRepo.PanInvalidError);
    	String message=ErrorMessage.getText();
    	
    	return message;
    }
    
    public void  Validate_PAN()
    {
    	// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
    	Log.info("Entering  valid  PAN Number.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.PanField);
    	PANField.sendKeys("AVKHY9356B");
    	Log.info("Entered valid  PAN number");
    	
    	Log.info("Clicking outside the Pan Field to validate there is no error message");
    	WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
    	GSTField.click();
    }
    public void  Validate_GST()
    {
    	// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
    	Log.info("Entering  valid  GST Number.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.GSTField);
    	PANField.sendKeys("27AAAPA1234A1Z5");
    	Log.info("Entered valid  GST number");
    	
    	Log.info("Clicking outside the Pan Field to validate there is no error message");
    	WebElement ConsultaionNameField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
    	ConsultaionNameField.click();
    }
    
    public void  Validate_CollectionAgencyName()
    {
    	// Initialize WebDriverWait and wait for the element to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
    	Log.info("Entering  valid  Collection Agency Name.");
    	WebElement PANField = driver.findElement(AddAgencyPageRepo.ConsultaionNameField);
    	PANField.sendKeys("agency12");
    	Log.info("Entered valid   Collection Agency Name");
    	
    	Log.info("Clicking outside the Pan Field to validate there is no error message");
    	WebElement GSTField = driver.findElement(AddAgencyPageRepo.GSTField);
    	GSTField.click();
    }
    
    }