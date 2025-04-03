package com.comcast.crm.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.generic.baseUtility.BaseClass;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest Test;

	public void onStart(ISuite suite) {

		System.out.println("Report Configuration");

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

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");

		report.flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "====START====");
		Test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(Test);
		Test.log(Status.INFO, result.getMethod().getMethodName()+"------STARTED------");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "====END===");
		Test.log(Status.PASS, result.getMethod().getMethodName()+"------COMPLETED------");
	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		TakesScreenshot sc = (TakesScreenshot) BaseClass.sdriver;
		String filepath = sc.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace("", "").replace(":", "");

		Test.addScreenCaptureFromBase64String(filepath, testName + "_" + time);
		Test.log(Status.FAIL, result.getMethod().getMethodName()+"------FAILED------");
	}

	public void onTestSkipped(ITestResult result) {

		Test.log(Status.SKIP, result.getMethod().getMethodName()+"------SKIPPED------");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

}
