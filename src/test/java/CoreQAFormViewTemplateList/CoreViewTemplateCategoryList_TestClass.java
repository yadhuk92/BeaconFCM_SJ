package CoreQAFormViewTemplateList;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	//import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;

	import org.testng.annotations.Test;
	import java.lang.reflect.Method;
	//import java.time.Duration;
	import java.io.File;
	import java.io.IOException;
	import java.util.Map;

	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.DataProvider;

	import com.aventstack.extentreports.MediaEntityBuilder;
	import com.aventstack.extentreports.Status;
	import com.extentReports.ExtentManager;

	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.LinkedHashMap;
	import java.util.List;

	import com.BasePackage.Base_Class;
	import com.BasePackage.DBUtils;

	import com.BasePackage.Login_Class;
	import com.Page_Repository.CoreViewTemplateCategoryListPageRepo;
	import com.aventstack.extentreports.ExtentTest;
	import com.extentReports.ExtentTestManager;
	import com.listeners.TestListener;


	import Core.QAFormViewTemplateList.CoreViewTemplateCategoryList_MainClass;
	


	public class CoreViewTemplateCategoryList_TestClass extends Base_Class {
		
		private  CoreViewTemplateCategoryList_MainClass page;
		private List<WebDriver> drivers = new ArrayList<>();
		Base_Class baseclass;

		static com.Utility.ExcelReader ExcelReader;
		WebDriver driver;
		TestListener TestListener;
		com.Utility.ScreenShot screenShot;
		ExtentTest extenttest;
		Login_Class callcenterlogin;
		CoreViewTemplateCategoryList_MainClass CoreViewTemplateCategoryList;
		
		

		@BeforeSuite

		public void SetUp() throws Exception {

			baseclass = new Base_Class();
			callcenterlogin = new Login_Class();
			callcenterlogin.CoreLogin();

			driver = baseclass.getDriver(); // Retrieve the driver instance

			CoreViewTemplateCategoryList = new CoreViewTemplateCategoryList_MainClass(driver);
			
			ExcelReader = new com.Utility.ExcelReader("CoreQAForm");
			TestListener = new TestListener();
			screenShot = new com.Utility.ScreenShot(driver);
		}

		@BeforeMethod
		public void setupTest(Method method) {
			baseclass = new Base_Class();
			driver = baseclass.getDriver();
			page = new CoreViewTemplateCategoryList_MainClass(driver);
			drivers.add(driver);

			callcenterlogin = new Login_Class();
			// Update the ScreenShot object with the new driver
			screenShot = new com.Utility.ScreenShot(driver);
			// Start a new ExtentTest for the current test method
			extenttest = ExtentTestManager.startTest(method.getName()).assignCategory("CoreQAForm");
		}

		@Test(priority = 1, enabled = true)
		public void Execute_the_Deletion_Query() throws Throwable {

			try {
				
				System.out.println(" ************** Testcase 1: Clear all the Database Tables Related to Q&A Form*****************");

				System.out.println();
				
				String DeleteQuery = "DELETE FROM QAF_OPTION_ANSWER_DETAILS";
				String Result = DBUtils.executeSQLStatement(DeleteQuery);
				System.out.println("Delete Query Result: " + Result);

				String DeleteQueryTemplate = "DELETE FROM QAF_OPTION_TEMPLATE_DETAILS";
				String TemplateDetails = DBUtils.executeSQLStatement(DeleteQueryTemplate);
				System.out.println("Delete Query Result: " + TemplateDetails);

				String DeleteQueryAnswerMaster = "DELETE FROM QAF_OPTION_ANSWER_MASTER";
				String AnswerMaster = DBUtils.executeSQLStatement(DeleteQueryAnswerMaster);
				System.out.println("Delete Query Result: " + AnswerMaster);

				String DeleteQueryTemplateDetails = "DELETE FROM QAF_QSTN_TEMPLATE_DETAILS";
				String TemplateDetail = DBUtils.executeSQLStatement(DeleteQueryTemplateDetails);
				System.out.println("Delete Query Result: " + TemplateDetail);

				String DeleteQueryTemplateHistory = "DELETE FROM QAF_QSTN_TEMPLATE_HISTORY";
				String TemplateHistory = DBUtils.executeSQLStatement(DeleteQueryTemplateHistory);
				System.out.println("Delete Query Result: " + TemplateHistory);

				String DeleteQueryCategoryMapping = "DELETE FROM QAF_TEMPLATE_CATEGORY_MAPPING";
				String CategoryMapping = DBUtils.executeSQLStatement(DeleteQueryCategoryMapping);
				System.out.println("Delete Query Result: " + CategoryMapping);

				String DeleteQueryTemplateMaster = "DELETE FROM QAF_QSTN_TEMPLATE_MASTER";
				String TemplateMaster = DBUtils.executeSQLStatement(DeleteQueryTemplateMaster);
				System.out.println("Delete Query Result: " + TemplateMaster);

			}

			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
		}

		@Test(priority = 2, enabled = true)
		public void Login_to_CoreWithHO_User() throws InterruptedException {

			try {
				ExtentTestManager.getTest().log(Status.PASS, "Logged in to Core Application.");

				// Home
				System.out.println(" ************** Testcase 2: Login to core application as HO User*****************");

				System.out.println();
				String currentUrl = driver.getCurrentUrl();
				Assert.assertTrue(currentUrl.contains("Home"), "Navigated to Home Page.");

				ExtentTestManager.getTest().log(Status.PASS, "User is navigated to Home  page, URL shows Home.");
				
			}

			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
		
		}

		@Test(priority = 3, enabled = true)
		public void View_Template_Creation_Page() throws InterruptedException {

			try {
				System.out.println(" ************** Testcase 3: Navigate_to_View_Template_Creation_Page *****************");
				System.out.println();
				ExtentTestManager.getTest().log(Status.PASS, "Navigate_to_View_Template_Creation_Page");

				// Navigate to QAFormView Main Menu

				CoreViewTemplateCategoryList.Navigate_to_View_Template_Creation_Page();
				ExtentTestManager.getTest().log(Status.PASS, "1. Create a Template with Question");
				
				ExtentTestManager.getTest().log(Status.PASS,
						"Should be possible to create Template with question");
			}

			catch (AssertionError | Exception e) {
				ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
				throw e;
			}
			
		}
		@Test(priority = 4, enabled = true)
		public void Navigate_to_view_template_category_mapping_list() throws InterruptedException {

		    try {
		        System.out.println(" ************** Testcase 4: Navigate_to_view_template_category_mapping_list *****************");
		        System.out.println();
		        ExtentTestManager.getTest().log(Status.PASS, "Navigate_to_view_template_category_mapping_list");

		        // Navigate to View Template Category Mapping List
		        CoreViewTemplateCategoryList_MainClass obj = new CoreViewTemplateCategoryList_MainClass(driver);
		        obj.Click_On_View_template_category_mapping_list();

		        ExtentTestManager.getTest().log(Status.PASS, "Navigated to 'Mapping Template and Category' page successfully.");

		    } catch (AssertionError | Exception e) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		        throw e;
		    }
		}

		@Test(priority = 5, enabled = true)
		public void validatingPresenceOfFieldsAndButtons() throws InterruptedException {
		    try {
		        System.out.println("************** Testcase 5: Validate presence of filters and buttons *****************");
		        System.out.println();
		        ExtentTestManager.getTest().log(Status.PASS, "Testcase 5: Validate presence of filters and buttons");

		        CoreViewTemplateCategoryList.validatePresenceOfFieldsAndButtons();
		        ExtentTestManager.getTest().log(Status.PASS, "All fields and buttons are present on the Mapping Template and Category page");

		    } catch (AssertionError | Exception e) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		        throw e;
		    }
		}

		@Test(priority = 6, enabled = true)
		public void validateSaveButtonWithoutDropdownSelections() throws Exception {
			 try {
			        System.out.println("************** Testcase 6: Save without Selection Validation *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, " Click the Save button without selecting any dropdown values.");
			 CoreViewTemplateCategoryList_MainClass obj = new CoreViewTemplateCategoryList_MainClass(driver);
		    obj.validateSaveWithoutSelections();
		    
		    ExtentTestManager.getTest().log(Status.PASS, " Validation messages appeared on each unselected field.");
		}catch (AssertionError | Exception e) {
	        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
	        throw e;
	    }
	}

		
		 @Test(priority = 7,enabled = true)
		    public void verifyTemplateNameDropdownValues_TC() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 7: Validate Template Name dropdown values *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on the Template Name dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. verify that  created templates are  listed in the drop down");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.verifyTemplateNameDropdownValues("CategoryList Template");
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Created Templates will be listed");
		        
		 } catch (AssertionError | Exception e) {
		        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
		        throw e;
		    }
		}
	    
		 
		 
		 @Test(priority = 8,enabled = true)
		    public void TemplateNameDropdownSelection() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 8: Template Name - Dropdown Option *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on the Template Name dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Select a template from the list.");
			        System.out.println("Test Case PASSED: Template Name dropdown option selection verified successfully.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.verifyTemplateNameDropdownOptionSelection();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Should be possible to select the Template  Name from the drop down .");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		    
		 @Test(priority = 9,enabled = true)
		    public void AssetCategoryDropdownvalues() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 9: Validate Asset Category - Dropdown values *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on Asset Category dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2.verify that SMA and NPA category is listing in the drop down");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.verifyAssetCategoryDropdownvalues();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "SMA  and NPA Category should be listed in the drop down");
		        //System.out.println("Test Case PASSED: Asset Category dropdown values verified successfully.");
		        
			 } catch (AssertionError | Exception e) {
				 System.out.println("Test Case FAILED: Exception occurred while verifying Asset Category dropdown values.");
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}

		 @Test(priority = 10,enabled = true)
		    public void AssetCategorySMASelectionImpact() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 10: Asset Category - SMA Selection Impact *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on Asset Category dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Select \"SMA category");

			        ExtentTestManager.getTest().log(Status.PASS, "3. Observe Account Category values list.");
			       // System.out.println("Test Case PASSED: SMA Category selection successfully displayed dependent options.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyAssetCategorySMASelectionImpact();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "SMA 0, SMA 1, SMA 2 are displayed in Account Category drop down.");

		        
			 } catch (AssertionError | Exception e) {
				 System.out.println("Test Case FAILED: Exception occurred during SMA Category impact verification.");
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 11,enabled = true)
		    public void AssetCategoryNPASelectionImpact() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 11: Asset Category - NPA Selection Impact *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on Asset Category dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Select NPA category");
			        ExtentTestManager.getTest().log(Status.PASS, "3. Observe Account Category values list.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyAssetCategoryNPASelectionImpact();
		        ExtentTestManager.getTest().log(Status.PASS, "Sub standard, D1, D2, D3, Loss asset are displayed.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 12,enabled = true)
		    public void AssetCategorySelection() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 12: Asset Category - Selection *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on Asset Category dropdown.");
			        ExtentTestManager.getTest().log(Status.PASS, "2.select SMA");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyAssetCategorySelection();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "should be possible to select SMA");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 13,enabled = true)
		    public void AccountCategorySelection() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 13: Validate Account Category - Selection *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. click on Account Category drop down");
			        ExtentTestManager.getTest().log(Status.PASS, "2.select SMA 0");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyAccountCategorySelection();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "should be possible to select SMA 0");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 14,enabled = true)
		    public void SaveButtonFunctionalitywithSelections() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 14: Save Button Functionality with Selections *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Select values from Template Name,Asset Category,Account Category Drop downs");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Click on the Save button.");


			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.SaveButtonFunctionality();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Data is saved, and a success message is shown on the UI.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 15,enabled = true)
		    public void ClearButtonFunctionality() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 15: Clear Button Functionality *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Select values from dropdowns.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Click the Clear button.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.ClearButtonFunctionality();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "All dropdown selections are reset to default state.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		  
		 @Test(priority = 16,enabled = true)
		    public void GridColumnsVerify() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 16: Grid Columns Verify *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Observe the grid section.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check the Column names");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyGridColumns();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Following Columns are present: Template Name, Account Category, Status, Action");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 17,enabled = true)
		    public void GridTemplateNameDisplay() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 17:Grid - Template Name Display *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Observe the grid section.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check the Template Name column.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyGridTemplateNameDisplays();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "The Template Name column displays Maped Templates");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 18,enabled = true)
		    public void GridAccountCategoryMapping() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 18:Validate Grid Account CategoryMapping *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Observe the grid section.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check the Account Category column.");
			        
			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyGridAccountCategoryMapping();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Displays account categories mapped to template names.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 19,enabled = true)
		    public void GridStatusDisplayActive() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 19: Grid - Status Display Active *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Observe the grid section.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check the Status column for an active template.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyGridActiveStatus();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Green tick is displayed for active templates.");
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 
		 @Test(priority = 20,enabled = true)
		    public void GridStatusDisplayInactive() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 20: Grid - Status Display Inactive *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Observe the grid section.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check the Status column for an inactive template.");
			        

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.Verifygridinactivestatus();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Red cross is displayed for inactive templates.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 

		 @Test(priority = 21,enabled = true)
		    public void ActionButtonValues() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 21: Action Button Values *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, " 1. Click on the Action button");
			        ExtentTestManager.getTest().log(Status.PASS, "2. verify the values.");
			        ExtentTestManager.getTest().log(Status.PASS, "3. Edit and Activate/De-activate options should be present");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyActionButtonsValue();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Edit and Activate/De-activate options should be present");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 @Test(priority = 22,enabled = true)
		    public void EditActionPrefillExistingValues() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 22: Edit Action - Prefill Existing Values *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click on Edit in the Action column of a grid row.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Check if filters are prefilled.");
			        ExtentTestManager.getTest().log(Status.PASS, "3. Try editing Asset or Account Category.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyEditActionPrefillExistingValues();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Template Name is editable, others are disabled.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		/* @Test(priority = 23,enabled = true)
		    public void EditandSaveChangesinGrid() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 23: Edit and Save Changes in Grid *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click Edit in the Action column.");
			         ExtentTestManager.getTest().log(Status.PASS, "2. Change Template Name.");
			          ExtentTestManager.getTest().log(Status.PASS, "3. Click on Save button");
			          

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyEditandSaveChangesingrid();
		        
		         ExtentTestManager.getTest().log(Status.PASS, "Changes are reflected in the grid for that row.");
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}*/
		 @Test(priority = 24,enabled = true)
		    public void ToggleActivate_DeactivateAction() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 24: Toggle Activate/Deactivate Action*****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click Activate/Deactivate in the Action column.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. Observe the Status column update.");
			        

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyToggleActivate_DeactivateAction();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Status changes and accurately reflects the new state.");
		        
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}
		 @Test(priority = 25,enabled = true)
		    public void EditandSaveChangesinGridwithsamedata() throws InterruptedException {
			 try {
			        System.out.println("************** Testcase 25: Edit and Save Changes in Grid with same data *****************");
			        System.out.println();
			        ExtentTestManager.getTest().log(Status.PASS, "1. Click Edit in the Action column.");
			        ExtentTestManager.getTest().log(Status.PASS, "2. do not change anything.");
			        ExtentTestManager.getTest().log(Status.PASS, "3. Click Save.");

			 CoreViewTemplateCategoryList_MainClass mainClass = new  CoreViewTemplateCategoryList_MainClass(driver);
		        mainClass.VerifyEditandSaveChangesingridwithsamedata();
		        
		        ExtentTestManager.getTest().log(Status.PASS, "Already Mapped Validation Will show");
			 } catch (AssertionError | Exception e) {
			        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + e.getMessage());
			        throw e;
			    }
			}


	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult result) throws IOException {
		// Check if the test case failed
		if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			try {
				// Take the screenshot for the failed test
				File image = screenShot.takeScreenShot(methodName, "Failure");

				extenttest.log(Status.INFO, "Screenshot of failure: ",
						MediaEntityBuilder.createScreenCaptureFromPath(image.getAbsolutePath()).build());

			} catch (IOException e) {
				System.err.println("Failed to capture screenshot: " + e.getMessage());
			}
		}
	}


	@DataProvider(name = "TestData")
	public static Object[][] gettestdate() throws IOException {
		ExcelReader = new com.Utility.ExcelReader("CoreQAForm");
		Object[][] objectarry = null;
		java.util.List<Map<String, String>> completedata = com.Utility.ExcelReader.getdata();

		objectarry = new Object[completedata.size()][1];

		for (int i = 0; i < completedata.size(); i++) {
			objectarry[i][0] = completedata.get(i);
		}
		return objectarry;
	}

	@AfterSuite
	public void afterEachTest() {
		ExtentManager.getInstance().flush();
		//Close all tracked browser instances
		for (WebDriver driverInstance : drivers) {
		if (driverInstance != null) {
	    driverInstance.quit();
	}
	 }

	//Clear the list of drivers
	drivers.clear();

	 System.out.println("All browser instances have been closed.");
	}

	}

