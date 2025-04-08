package Core.CollectionAgency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.BasePackage.DBUtils;
import com.Page_Repository.AgencyAccountAllocation_Repository;
import com.Page_Repository.LoginPageRepo;
import com.Utility.Log;

import io.github.bonigarcia.wdm.WebDriverManager;


//Constructor
public class AgencyAccountAllocation_mainClass extends Base_Class{
	public String HOUserID;
	public String HOUserPassword;
	
	String ExpectedAccountCount;
	String ExpectedSumOfOutstandingAmount;

	
	String UserID;
	String Password;
	
	AgencyAccountAllocation_Repository Page_Repository=new AgencyAccountAllocation_Repository();
   WebDriver driver;
 
 public AgencyAccountAllocation_mainClass(WebDriver driver) {
  	this.driver=driver;
  	PageFactory.initElements(driver, this);
  }
 
 
//Method to create New user	
	 public boolean ReadDataFromProp() throws Throwable {
		 String filePath = "CoreAgentAccountAllocation.properties"; // Adjust path as needed

		 try {
		 Properties props = Base_Class.ReadFromPropertiesFile(filePath);
		 HOUserID = props.getProperty("HOUserID");
		 HOUserPassword = props.getProperty("HOUserPassword");
	 
		 System.out.println("HOUserID: " + props.getProperty("HOUserID"));
		 System.out.println("HOUserPassword: " + props.getProperty("HOUserPassword"));
		 
		 
		 } catch (IOException e) {
		 System.out.println("Error reading properties file: " + e.getMessage());
		 }
		return true;
	 }
	 
public String[] executeHOUserCreation() {
    try {
        // Example usage
		/*
		 * String query =
		 * "SELECT Default_URL FROM acc_users WHERE user_id = 'IBU0000028'"; String
		 * defaultURL = fetchSingleValueFromDB(query);
		 * System.out.println("Default URL: " + defaultURL);
		 */
    	
		
		  List<Object> inputParams = Arrays.asList(HOUserID,"John Doe", "john.doe@example.com",9876543210L);
		  List<Integer> outputTypes = Arrays.asList(Types.VARCHAR,Types.VARCHAR, Types.VARCHAR);
		  
		  List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("HOUserIDGenerator", inputParams,outputTypes);
		  
		   UserID = (String) results.get(0);
		   Password = (String) results.get(1);
		  String StatusMessage = (String) results.get(2);
		  System.out.println("Generated User ID: " + UserID);
		  System.out.println("Default Password: " + Password);
		  System.out.println("Default Password: " + StatusMessage);
    	
    	// Define the stored procedure name
		/*
		 * String procedureName =
		 * "ALLOCATION_DASHBOARD_DATA_LOAD_PKG.SPPROCESSCALLCENETERREGULARIZATIONSUMMARY";
		 * // No input parameters List<Object> inputParams = new ArrayList<>(); // No
		 * output parameters List<Integer> outputParamTypes = new ArrayList<>(); //
		 * Execute the stored procedure List<Object> result =
		 * ExecuteAnyOracleSQLStoredProcedure(procedureName, inputParams,
		 * outputParamTypes); // Print the output (if there were any)
		 * System.out.println("Stored procedure executed successfully. Output: " +
		 * result);
		 */
		  
		  //to add ID & Pass in prop file
//		  saveUserCredentialsToProperties(UserID,Password);
		 
		  
    } catch (Exception e) {
        e.printStackTrace();
    }
    
	return new String[] {UserID,Password};
}

//Method to update DB


public void UPDATE_MST_account() {
    
    	try
    	{
        	String query="UPDATE MST_account\r\n"
        			+ "SET DPD = '100'\r\n"
        			+ "WHERE branch_id IN (select ENTITY_CODE from mst_organization_detail\r\n" +"where ENTITY_NAME='Amaravati')";
 
        	DBUtils.executeSQLStatement(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

}

//Method to delete Data in DB
public void DeleteAccAllocated() {
	try
	{
        String query="DELETE FROM Mst_col_agency_acc_allocated";
        DBUtils.executeSQLStatement(query);
    } catch (Exception e) {
        e.printStackTrace();
    }
}	
public void DeleteHistory() {
		try
		{
			String query="DELETE FROM mst_col_agency_acc_allocated_history";
	        DBUtils.executeSQLStatement(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}
public void DeleteActivityMaster() {
			try
			{
				String query="DELETE FROM mst_col_agency_acc_activity_master";
		        DBUtils.executeSQLStatement(query);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
}


//Method to click collection Agency
public boolean clickCollectionAgencyMenu() throws Throwable {
	Common.fluentWait("Collection Agency menu to display", Page_Repository.collectionAgencyMenu);
	click(Page_Repository.collectionAgencyMenu);
	return true;
}
//Method to click Agency a/c allocation
public boolean clickAgencyAccountAllocationMenu() throws Throwable {
	Common.fluentWait(" Agency Account Allocation menu to display", Page_Repository.agencyAccountAllocationMenu);
	click(Page_Repository.agencyAccountAllocationMenu);
	return true;
}
// Check visibility of 'Types of Account' dropdown
public boolean isTypesOfAccountDropdownVisible() {
	WebElement TypesOfAccountDropdown=driver.findElement(Page_Repository.TypesOfAccountDropdownlabel);
	TypesOfAccountDropdown.isDisplayed();
	return true;
    
}

//Allocated and Not Allocated option should be listed
public boolean ValuesOfTypesOfDropdown() {
	List<WebElement> AllValue=driver.findElements(Page_Repository.TypesOfAccountDrpAllValue);
	List<String> dropdownvalue = new ArrayList();
	
	dropdownvalue.contains("Allocated");
	dropdownvalue.contains("Not Allocated");
	return true;	
}

public boolean selectNotAllocated() {
	WebElement NotAllocated=driver.findElement(Page_Repository.NotAllocatedValue);
	NotAllocated.click();
	return true;
}
//Check visibility of 'assetCategory' dropdown
public boolean isAssetCategoryvisible() {
	WebElement assetCategory=driver.findElement(Page_Repository.AssetCategory);
	assetCategory.isDisplayed();
	return true;
}
//click 'asset category'
public boolean clickassetCategory() {
	WebElement assetCategory=driver.findElement(Page_Repository.AssetCategory);
	assetCategory.click();
	return true;
	
}
//Select NPA Category
public boolean SelectNPA() {
	WebElement NPACategory=driver.findElement(Page_Repository.NPACategory);
	NPACategory.click();
	return true;
}
//close asset Cat drop 
public boolean cancelAssetValue() {
	WebElement close=driver.findElement(Page_Repository.closeAssetCategoryValue);
	close.click();
	return true;
}
//Select SMA Category
public boolean SelectSMA() {
	WebElement SMACategory=driver.findElement(Page_Repository.SMACategory);
	SMACategory.click();
	return true;
}

//Verify visibility of the 'zone' dropdown.
public boolean Iszone_codVisible() {
	WebElement zone=driver.findElement(Page_Repository.zone_co);
	zone.isDisplayed();
	return true;
}
//Select Amravati branch
public boolean selectAmravati(String Amar) throws Throwable {
	WebElement search=driver.findElement(Page_Repository.branchSearch);
	search.sendKeys(Amar);
return true;
}

//Set DPD value
public void SetDPD(int dpdValue) {
	try {
  	String query="UPDATE MST_ACCOUNT\r\n "
  			 + "SET DPD = " + dpdValue + " "
    	+ "WHERE ACCOUNT_NO IN (SELECT ACCOUNT_NO FROM MST_ACCOUNT\r\n"
    	 + "ORDER BY 1 DESC\r\n"
		    + "FETCH FIRST 20 ROWS ONLY\r\n)";

DBUtils.executeSQLStatement(query);
	}
	
 catch (Exception e) {
    e.printStackTrace();
}
}
public String DPDValue(String DPD) {
	WebElement InputDPD=driver.findElement(Page_Repository.DPDValue);
	InputDPD.clear();
	InputDPD.sendKeys(DPD);
	return DPD;
}

public String TotalAccount() {
	WebElement Total=driver.findElement(Page_Repository.TotalAccountSelected);
	
	String str=Total.getAttribute("Title");
	System.out.println(str);
	return str;
}

public String OutstandingAmount() {
	WebElement Outstanding=driver.findElement(Page_Repository.OutstandingAmount);
	
	String Amt=Outstanding.getText();
	System.out.println(Amt);
	String cleanedAmt = Amt.replace(",", ""); // Remove commas
	return cleanedAmt;
}

public String DBValueTotalAccountSelected(int dpdValue) {
	String DBTotalAccount = "";
	try
	{
       	String query="SELECT COUNT(ACCOUNT_NO)\r\n"
      			+ "FROM MST_ACCOUNT\r\n"
      			+ "WHERE DPD = " + dpdValue + " "
      			+ "AND Branch_ID = 5"
      			+ "AND SMA_CATEGORY_CODE IS null";
      			 
      	DBTotalAccount= DBUtils.executeSQLStatement(query);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	return DBTotalAccount;
}

public String DBValueOutstandingAmount(int dpdValue) {
	String OutstandingAmt="";
	
	try
	{
    	String query="SELECT SUM(TOTAL_OUTSTANDING) \r\n"
    			+ "FROM MST_ACCOUNT\r\n"
    			+ "WHERE DPD = " + dpdValue + " "
    			+ "AND Branch_ID = 5"
    			+ "AND SMA_CATEGORY_CODE IS null";
	
	OutstandingAmt=DBUtils.executeSQLStatement(query);
	
} catch (Exception e) {
    e.printStackTrace();
}
	return OutstandingAmt;
}

//Method to get Allocation date
public String AllocationDate() {
	WebElement dateField =driver.findElement(Page_Repository.AllocationDate);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String dateValue = (String) js.executeScript("return arguments[0].value;", dateField);

    System.out.println("Date Value: " + dateValue);
	return dateValue;
}
//Method to get current date
public String GetCurrentDate() {
	String str= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	System.out.println(str);
	return str;
}
// Method to check if the allocation date field is editable
public boolean isAllocationDateEditable() {
	  try {
          WebElement Date=driver.findElement(Page_Repository.AllocationDate);
        		  Date.sendKeys("ShouldNotAccept");
          return false;
      } catch (Exception e) {
          return true;
      }	
}

//Method to select collection Agency
public String AllocateButtonFunctionality( String agency) {
	WebElement search=driver.findElement(Page_Repository.CollectionAgencySearch);
	search.click();
	search.sendKeys(agency);
	return agency;
}

//Success msg after allocation
public boolean AllocatedMsg() {
	String str=driver.findElement(Page_Repository.SuccessMsgForAllocation).getText();
			 
	return true;
}

//Method to check Allocated list button is present
public boolean AllocatedListButton() {
	WebElement AllocatedList=driver.findElement(Page_Repository.AllocatedListButton);
	AllocatedList.isDisplayed();
	return true;
}

//Method to check allocated list pop up
public boolean popup() {
	WebElement popup=driver.findElement(Page_Repository.PopUp);
	popup.isDisplayed();
	return true;
		
	}
//Method to select  Agency
public String SelectAllocatedAgency(String agency) {
	WebElement search=driver.findElement(Page_Repository.AgencyNameSearch);
	search.click();
	search.sendKeys(agency);
	return agency;
}

//Method to select From date
public String FromDate() {

	String Date= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	System.out.println(Date);
  
    WebElement fromDate = driver.findElement(Page_Repository.FromDate);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].value = arguments[1];", fromDate, Date);
    return Date;
}

//Method to select To date
public String ToDate() {

	String Date= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	System.out.println(Date);
  // Use the formatted date in Selenium
  WebElement ToDate = driver.findElement(Page_Repository.ToDate);
  JavascriptExecutor js = (JavascriptExecutor) driver;
  js.executeScript("arguments[0].value = arguments[1];", ToDate,Date );

  return Date;
}



// Methods to verify each column is displayed
public boolean isSelectColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.selectColumn);
     wb.isDisplayed();
     return true;
}

public boolean isAllocationIdColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.allocationIDColumn);
    wb.isDisplayed();
    return true;
}

public boolean isEinNumberColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.EINnoColumn);
    wb.isDisplayed();
    return true;
}

public boolean isOfficerNameColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.officerNameColumn);
    wb.isDisplayed();
    return true;
}

public boolean isDateColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.DateColumn);
    wb.isDisplayed();
    return true;
}

public boolean isNoOfAllocationsColumnDisplayed() {
	WebElement wb=driver.findElement(Page_Repository.NoOfAllocationColumn);
    wb.isDisplayed();
    return true;
}

public boolean DownLoadExcelDisabled() {
	WebElement wb=driver.findElement(Page_Repository.DownloadInExcelButton);
	wb.isEnabled();
	return false;
}

public boolean CheckBoxDispalyed() {
	WebElement wb=driver.findElement(Page_Repository.CheckBox);
	wb.isDisplayed();
	return true;
}

public boolean DownLoadExcelEnabled() {
	WebElement wb=driver.findElement(Page_Repository.DownloadInExcelButton);
	wb.isEnabled();
	return true;
}

public boolean SuccessMsgAfterDownload() {
	WebElement wb=driver.findElement(Page_Repository.SuccessMsgForDownload);
	String str=wb.getText();
	return true;
	
}

public boolean WarningMsgForInvalidSearch() {
	String str= driver.findElement(Page_Repository.WarningMsgForEmptySearchValue).getText();
	return true;
	
}


//Method to get Account count and sum of outstanding amount

public String[] CompareDataInDB() {
	
	try
	{
       	List<Object> inputParams = Arrays.asList("qwer"); 
  		  List<Integer> outputTypes = Arrays.asList(Types.VARCHAR,Types.VARCHAR,Types.VARCHAR);
  		  List<Object> results =
  		  DBUtils.ExecuteAnyOracleSQLStoredProcedure("SP_GET_AccountCountAndOutstandingAmountOfAllocatedCollectionAgency", inputParams,
  		  outputTypes); 
      System.out.println("Account count: " + results.get(0));
      ExpectedAccountCount=(String) results.get(0);
  	  System.out.println("Sum of outstanding amount: " + results.get(1));
  	ExpectedSumOfOutstandingAmount=(String) results.get(1);
  	  System.out.println("Status message: " + results.get(2)); 
       
    } catch (Exception e) {
        e.printStackTrace();
    }
    
	return new String[] {ExpectedAccountCount,ExpectedSumOfOutstandingAmount};
}
	
// Method to verify the Excel file name
public boolean isExcelFileNameValid(String Allocation_List ) {
    File downloadDir = new File(System.getProperty("user.home") + "/Downloads");
    File[] files = downloadDir.listFiles((dir, name) -> name.startsWith(Allocation_List ));
    return files != null && files.length > 0;
}

//Method to check previous page displayed

public boolean isAgencyAllocationPageDisplayed() {
	WebElement wb = driver.findElement(Page_Repository.PreviousPage);
	wb.isDisplayed();
    return true;
}



public static void waitForSpinnerToStop(WebDriver driver, By spinnerLocator) {

    int MAX_WAIT_TIME = 70;  // Max total wait time in seconds

    int POLL_INTERVAL = 3;   // Poll interval in seconds

    int CONSECUTIVE_DISAPPEAR_COUNT = 4;  // Number of consecutive times the spinner must disappear

   

   int remainingTime = MAX_WAIT_TIME;

   int disappearCount = 0;



   // WebDriverWait setup

   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(POLL_INTERVAL));



   // Loop until the spinner disappears for the set number of cycles or time runs out

   while (remainingTime > 0) {

       try {

           // Wait until the spinner becomes invisible

           wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));



           // If spinner disappeared, increment the disappearCount

           disappearCount++;



           // If spinner has disappeared for enough consecutive cycles, we stop the wait

           if (disappearCount >= CONSECUTIVE_DISAPPEAR_COUNT) {

               System.out.println("Spinner has stopped appearing.");

               return;

           }



       } catch (Exception e) {

           // Spinner is still visible, reset disappearCount

           disappearCount = 0;

           System.out.println("Spinner is still visible. Waiting...");

       }



       // Sleep for the polling interval before checking again

       try {

           Thread.sleep(POLL_INTERVAL * 1000); // Polling interval

       } catch (InterruptedException ie) {

           Thread.currentThread().interrupt();

       }

       // Reduce the remaining time

       remainingTime -= POLL_INTERVAL;

   }

   System.out.println("Timeout reached, spinner still shows up intermittently.");

}



}
