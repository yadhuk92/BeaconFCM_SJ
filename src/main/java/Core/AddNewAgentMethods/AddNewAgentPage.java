package Core.AddNewAgentMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Page_Repository.AgentListPageRepo;
import com.Utility.Log;

public class AddNewAgentPage {
	private WebDriver driver;
	
	// Constructor
		public AddNewAgentPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); // Initialize WebElements
	        Log.info("AddNewAgentPage initialized.");
	    }
		public String AgentCodeGenerator() {
			Random random = new Random();
			// Generate a 4-digit random number
			int min = 1000; // Smallest 4-digit number
			int max = 9999; // Largest 4-digit number

			// Generate random number in range [1000, 9999]
			int randomNumber = random.nextInt(max - min + 1) + min;
			String stringNum = Integer.toString(randomNumber);
			return stringNum;
		}
		public void ClearAddNewAgent() {
		driver.findElement(AgentListPageRepo.NameAgentCode).clear();
		driver.findElement(AgentListPageRepo.Name1).clear();
		driver.findElement(AgentListPageRepo.MobileInput).clear();
		driver.findElement(AgentListPageRepo.Tenurity).click();
		driver.findElement(AgentListPageRepo.Tenurity).clear();
		driver.findElement(AgentListPageRepo.ClearRole).click();
		}
		
		public String MobileNumberGenerator() {
			Random random = new Random();

			// Define the range for a 10-digit number (starting with 6, 7, 8, or 9)
			long min = 6000000000L; // Smallest 10-digit number starting with 6
			long max = 9999999999L; // Largest 10-digit number

			// Generate random number in range [min, max]
			long mobileNumber = min + (long) (random.nextDouble() * (max - min));
			String mobileNumberStr = String.valueOf(mobileNumber);

			System.out.println("Random 10-digit mobile number: " + mobileNumberStr);
			return mobileNumberStr;
		}

		public String RandomNameGenerator() {
			Random random = new Random();

			// Define the length of the name
			int nameLength = 10;

			// Generate the first letter as uppercase
			char firstLetter = (char) ('A' + random.nextInt(26));
			StringBuilder nameBuilder = new StringBuilder();
			nameBuilder.append(firstLetter);

			// Generate the remaining 9 letters as lowercase
			for (int i = 1; i < nameLength; i++) {
				char letter = (char) ('a' + random.nextInt(26));
				nameBuilder.append(letter);
			}

			// Convert StringBuilder to String and return
			return nameBuilder.toString();
		}

		public void WaitLoader() {
			By loader = By.xpath("//*[@class='spinner']");
			// wait for Processing icon
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
				wait.pollingEvery(Duration.ofSeconds(10));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
				Log.info("Loader disappeared.");
			} catch (Exception e) {
				Log.info("Loader did not appear, proceeding without delay.");
			}
		}

		public String PastDate() throws ParseException {
			// take last 60 days coverage date from current system date
			LocalDate currentDate = LocalDate.now();
			LocalDate currentDateMinus2Months = currentDate.minusDays(10);
			String Date = currentDateMinus2Months.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat output = new SimpleDateFormat("mm/dd/yyyy");
			Date data = sdf.parse(Date);
			String CoverageDate = output.format(data);
			return CoverageDate;
		}

		public String FutureDate() throws ParseException {
			// take last 60 days coverage date from current system date
			LocalDate currentDate = LocalDate.now();
			LocalDate currentDateMinus2Months = currentDate.plusDays(10);
			String Date = currentDateMinus2Months.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat output = new SimpleDateFormat("mm/dd/yyyy");
			Date data = sdf.parse(Date);
			String CoverageDate = output.format(data);
			return CoverageDate;
		}

		public static Properties configloader() throws IOException {
			FileInputStream File = new FileInputStream(".\\src\\test\\resources\\config.properties");
			Properties properties = new Properties();
			properties.load(File);
			return properties;
		}
	
	
}
