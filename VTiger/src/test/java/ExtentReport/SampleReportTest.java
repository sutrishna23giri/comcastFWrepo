package ExtentReport;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	public ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./advanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		

		// Add environment info and create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome");
	}

	@Test
	public void CreateContactTest() {

		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		//Take SCreenShot
		TakesScreenshot ss = (TakesScreenshot)driver;
		String filepath = ss.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest Test = report.createTest("CreateContactTest");
        Test.log(Status.INFO, "Login to app");
		Test.log(Status.INFO, "Navigate to contact page");
		Test.log(Status.INFO, "Create contact");
		if ("HDFC".equals("HFDC")) {
			Test.log(Status.PASS, "contact is created");
		} else {
			Test.addScreenCaptureFromBase64String(filepath, "ErrorFile");
		}

		driver.close();
	}

	@Test
	public void CreateContactWithORGTest() {
		ExtentTest Test = report.createTest("CreateContactWithORGTest");

		Test.log(Status.INFO, "Login to app");
		Test.log(Status.INFO, "Navigate to contact page");
		Test.log(Status.INFO, "Create contact");
		if ("HDFC".equals("HFDC")) {
			Test.log(Status.PASS, "contact is created");
		} else {
			Test.log(Status.FAIL, "contact is not created");
		}
	}

	@Test
	public void CreateContactWithPhNumberTest() {
		ExtentTest Test = report.createTest("CreateContactWithPhNumberTest");

		Test.log(Status.INFO, "Login to app");
		Test.log(Status.INFO, "Navigate to contact page");
		Test.log(Status.INFO, "Create contact");
		if ("HDFC".equals("HFDC")) {
			Test.log(Status.PASS, "contact is created");
		} else {
			Test.log(Status.FAIL, "contact is not created");
		}
	}

	@AfterSuite
	public void configAS() {
		report.flush();
	}
}
