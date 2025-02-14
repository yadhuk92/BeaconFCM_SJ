package Core.MyDesk.Dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.Utility.DBUtils;
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
		ExtentTestManager.getTest().log(Status.PASS, "Updated ZoneUserID in config File" + Username);
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

	public void VerifyConfigZone() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String ZoneUserID = configloader().getProperty("ZoneUserID").trim();
			String ZoneUserPW = configloader().getProperty("Zonepassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Config file Zone user ID  " + ZoneUserID);
			ExtentTestManager.getTest().log(Status.PASS, "Zone User PW  " + ZoneUserPW);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}
	}

	public void VerifyConfigRegion() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Region User ID from config2 file" + RegionUserID);

			String Regionpassword = MyDeskDashboardPage.configloader().getProperty("Regionpassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Region User password config2 file " + Regionpassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
			
		}
	}

	public void VerifyConfigBranch() throws IOException {
		try {
			File file = new File(".\\src\\test\\resources\\MyDeskDashboardconfig2");
			System.out.println("File exists: " + file.exists());
			ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + file.exists());
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User ID config2 file is " + Core_BranchUserID);
			String Core_Branchpassword = MyDeskDashboardPage.configloader().getProperty("Core_Branchpassword").trim();
			ExtentTestManager.getTest().log(Status.PASS, "Core Branch User password  " + Core_Branchpassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}
	}

	public void VerifyConfigBCOUser() throws IOException {
		// Define procedure name

		String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
		ExtentTestManager.getTest().log(Status.PASS, "My Desk Dashboard config2 file is present:" + Core_BranchUserID);
	}

	public void SQLQueryZone() throws IOException {
		try {
			String ZoneUserID = configloader().getProperty("ZoneUserID").trim();
			List<Object> inputParams = Arrays.asList(ZoneUserID, "John Doe", "john.doe@example.com", 9876543210L);
//		List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);
			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("ZoneUserIDGenerator", inputParams,
					outputTypes);

			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);
			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);

			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message.contains(Message)) {
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileZone(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}
	}

	public void SQLQueryRegion() throws IOException {
		try {
			String RegionUserID = MyDeskDashboardPage.configloader().getProperty("RegionUserID").trim();
			List<Object> inputParams = Arrays.asList(RegionUserID, "John Doe", "john.doe@example.com", 9876543210L);
//		List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("RegionUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);
			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);

			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message
						.contains("UserIDFromPropertiesFile exist in acc_users table. Zone user creation aborted.")) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileRegion(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}

	}

	public void SQLQueryBranch() throws IOException {

		try {
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			List<Object> inputParams = Arrays.asList(Core_BranchUserID, "John Doe", "john.doe@example.com", 9876543210L);
//	List<Object> inputParams = Arrays.asList(null, "John Doe", "john.doe@example.com", 9876543210L);
			List<Integer> outputTypes = Arrays.asList(Types.VARCHAR, Types.VARCHAR, Types.VARCHAR);

			List<Object> results = DBUtils.ExecuteAnyOracleSQLStoredProcedure("Core_BranchUserIDGenerator", inputParams,
					outputTypes);
			System.out.println("Generated User ID: " + results.get(0));
			String Username = (String) results.get(0);

			System.out.println("Default Password: " + results.get(1));
			String Password = (String) results.get(1);
			System.out.println("Status Message: " + results.get(2));
			String Message = (String) results.get(2);
			System.out.println(Message);
			if (Message != null) { // Check if Message is not null
				if (Message.contains("null")) {
					ExtentTestManager.getTest().log(Status.PASS, "Message is null need to create new USER ID ");
				} else if (Message.contains(Message)) {
					// Handle this case if needed
					ExtentTestManager.getTest().log(Status.PASS, "Message from the Data Base" + Message);
				}
			} else {
				System.out.println("Warning: Message is null");
				SaveConfigFileBranch(Username, Password);
				ExtentTestManager.getTest().log(Status.PASS, "Saved the User Details in Config file");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}

	}

	public void SQL_BCOUSer() throws IOException {
		try {
			String procedureName = "InsertToMSTEmployeeForCoreMyDeskDashboard";
			// Input parameters
			List<Object> inputParams = new ArrayList<>();
			String Core_BranchUserID = MyDeskDashboardPage.configloader().getProperty("Core_BranchUserID").trim();
			inputParams.add(Core_BranchUserID); // Example USER_ID
			// Output parameters
			List<Integer> outputParamTypes = new ArrayList<>();
			outputParamTypes.add(Types.VARCHAR); // p_MESSAGE output parameter

			// Execute stored procedure
			List<Object> output = DBUtils.InsertToMSTEmployeeForCoreMyDeskDashboard(procedureName, inputParams,
					outputParamTypes);
			ExtentTestManager.getTest().log(Status.PASS,
					"Insert To MST Employee For Core My Desk Dashboard procedure done ");
			// Print output
			if (!output.isEmpty()) {
				System.out.println("Stored Procedure Output: " + output.get(0));

				ExtentTestManager.getTest().log(Status.PASS, "Stored Procedure Output: " + output.get(0));
			} else {
				System.out.println("No output received.");
				ExtentTestManager.getTest().log(Status.FAIL, "Stored Procedure Output: " + output.get(0));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e.getMessage());	
		}
	}

}