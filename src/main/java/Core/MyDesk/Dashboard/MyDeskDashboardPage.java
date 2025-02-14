package Core.MyDesk.Dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.BasePackage.Base_Class;
import com.BasePackage.Common;
import com.Page_Repository.CallCenterRoleManagementRepo;
import com.Utility.Log;
import com.aventstack.extentreports.Status;
import com.extentReports.ExtentTestManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyDeskDashboardPage extends Base_Class {
	private WebDriver driver;
	public static String RoleName7;
	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\MyDeskDashboardconfig2";
	// Constructor
	public MyDeskDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
		Log.info("MyDeskDashboardPage");
	}

	

	public void SelectFunctionalities() {

	}

	public void SelectFunctionalitiesSelectedornot() {

	}

	public void DeSelectFunctionalities() {

	}

	public static Properties configloader() throws IOException {
		FileInputStream File = new FileInputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2");
		Properties properties = new Properties();
		properties.load(File);
		return properties;
	}

//	public void SaveConfigFileZone(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("ZoneUserID", Username);
//			properties.setProperty("Zonepassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void SaveConfigFileRegion(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("RegionUserID", Username);
//			properties.setProperty("Regionpassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void SaveConfigFileBranch(String Username, String Password) {
//		Properties properties = new Properties();
//
//		try (FileOutputStream output = new FileOutputStream(".\\src\\test\\resources\\MyDeskDashboardconfig2")) {
//
//			properties.setProperty("Core_BranchUserID", Username);
//			properties.setProperty("Core_Branchpassword", Password);
//			properties.store(output, "Updated Config File");
//			System.out.println("Config file updated successfully!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	 public void SaveConfigFileZone(String Username, String Password) {
	        updateConfig("ZoneUserID", Username, "Zonepassword", Password);
	        ExtentTestManager.getTest().log(Status.PASS, "Updated ZoneUserID in config File"+ Username);
	    }

	    public void SaveConfigFileRegion(String Username, String Password) {
	        updateConfig("RegionUserID", Username, "Regionpassword", Password);
	    }
	
	    public void SaveConfigFileBranch(String Username, String Password) {
	        updateConfig("Core_BranchUserID", Username, "Core_Branchpassword", Password);
	    }
	    private void updateConfig(String key1, String value1, String key2, String value2) {
	        Properties properties = new Properties();

	        // Load existing properties if the file exists
	        File configFile = new File(CONFIG_FILE_PATH);
	        if (configFile.exists()) {
	            try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
	                properties.load(input);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        // Update properties
	        properties.setProperty(key1, value1);
	        properties.setProperty(key2, value2);

	        // Save updated properties back to the file
	        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
	            properties.store(output, "Updated Config File");
	            System.out.println("Config file updated successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	
	
	
	
	
	
	
	
}
