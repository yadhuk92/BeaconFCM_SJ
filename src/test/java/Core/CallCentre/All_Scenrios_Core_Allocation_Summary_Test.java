package Core.CallCentre;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BasePackage.Base_Class;
import com.Utility.Log;
import com.extentReports.ExtentManager;
import com.extentReports.ExtentTestManager;
import com.listeners.TestListener;


public class All_Scenrios_Core_Allocation_Summary_Test extends Base_Class {
	
	com.Utility.ExcelReader ExcelReader;
	Base_Class Base_Class;
	Log log;
	TestListener TestListener;
	com.Utility.ScreenShot screenShot;

	Core_Allocation_Summary_Page allocationsummary = new Core_Allocation_Summary_Page();
	
	@BeforeSuite
	public void reference() {
		ExcelReader = new com.Utility.ExcelReader("Allocation_Summary");
		log = new Log();
		TestListener = new TestListener();
		screenShot = new com.Utility.ScreenShot(null);
		Base_Class = new Base_Class();
	}

	@Test(dataProvider = "TestData")
	public void Loan_Closure_Cash(Map<Object, Object> testdata, ITestContext context)
			throws ClassNotFoundException, InterruptedException, IOException {
		try {
			if (testdata.get("Run").toString().equalsIgnoreCase("Yes")) {
				ExtentTestManager.startTest(testdata.get("TestScenario").toString());
				Log.info("*** Running test method " + testdata.get("TestScenario").toString() + "...");
				context.setAttribute("fileName", "Login");

				// Application launch
				ExtentTestManager.startTest("Chrome Driver & Application Launch");
				Base_Class.SetUp();
				ExtentTestManager.endTest();
		

				// userLoginValidPaswrd
				allocationsummary.Login();
				
				//TC_01 ---> VerifyAllocationSummaryDisplayingasSubmenu
				allocationsummary.VerifyAllocationSummaryDisplayingasSubmenu();
				
				//TC_02 ---> ClickonAllocationSummarysubmenu
				allocationsummary.ClickonAllocationSummarysubmenu();
				
				//TC_03 ---> VerifyFieldsandButtononAllocationSummary
				allocationsummary.VerifyFieldsandButtononAllocationSummary();
				
				//TC_04 ---> SearchWithoutSelectingSelectCallCentredropdown
				allocationsummary.SearchWithoutSelectingSelectCallCentredropdown();
				
				//TC_05 ---> SearchwithSelectedCallCentre
				allocationsummary.SearchwithSelectedCallCentre();
				
				//TC_06 ---> DownloadMonthlyAllocationSummary
				allocationsummary.DownloadMonthlyAllocationSummary();
				
				//TC_07 ---> DataVerificationinDownloadedFile
				allocationsummary.DataVerificationinDownloadedFile();
				
				//TC_08 ---> DownloadDailyAllocationfromMonthlySummary
				allocationsummary.DownloadDailyAllocationfromMonthlySummary();
				
				//TC_09 ---> DataVerificationinnDownloadedFile
				allocationsummary.DataVerificationinnDownloadedFile();
		
						
				// Sign out
				Thread.sleep(3000);
				allocationsummary.logout();

				// EndTest
				// ExtentTestManager.endTest();
				ExtentManager.getInstance().flush();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {

		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}


}
		




