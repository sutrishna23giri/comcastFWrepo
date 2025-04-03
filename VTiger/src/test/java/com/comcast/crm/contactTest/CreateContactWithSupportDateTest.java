package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.contactPage;
import com.comcast.crm.ObjectRepositoryUtility.createNewContactPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Throwable {
	
		
/*................CREATE OBJECT.................*/	
		
		PropertyFileUtility Flib = new PropertyFileUtility();
		ExcelUtility ELib = new ExcelUtility();
		JavaUtility JLib = new JavaUtility();
	
		//read common data from properties file
		  
		  String BROWSER=Flib.getDataFromPropertiesFile("browser"); 
		  String URL=Flib.getDataFromPropertiesFile("url"); 
		  String USERNAME=Flib.getDataFromPropertiesFile("username");
		  String PASSWORD=Flib.getDataFromPropertiesFile("password");
		 
		//read testscript data from excel file with random number
	
		String lastname = ELib.getDataFromExcel("contact",7, 3);
		  
		  
		WebDriver driver=null;
		
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if (BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if (BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		//step 1: login to application
		
		driver.get(URL);
		LoginPage login = PageFactory.initElements(driver,LoginPage.class);
		
		login.loginToApp(URL,USERNAME, PASSWORD);
		
		//step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: click on "create contact" button
		contactPage CP =new contactPage(driver);
		CP.getCreateContactButton().click();
		
		//start date and end date using JAVA
		
		String start_date = JLib.getSystemDateYYYYDDMM();
		String end_date = JLib.getRequiredDateYYYYDDMM(60);
		
		//step 4: enter all the details and create new organization
		 createNewContactPage cc = new createNewContactPage(driver);
		 cc.createcontact(lastname);
		 Thread.sleep(3000);
		 cc.createcontact(start_date);
		 cc.createcontact(end_date);
		
		//Varify Start Date & End Date
		
		String act_StartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(act_StartDate.contains(start_date))
		{
			System.out.println(start_date + "is created----->PASS");
		}
		else
		{
			System.out.println(start_date + "is not created----->FAIL");
		}
		
		
		String act_EndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(act_EndDate.contains(end_date))
		{
			System.out.println(end_date + "is created----->PASS");
		}
		else
		{
			System.out.println(end_date + "is not created----->FAIL");
		}
		
		//step 5: logout from application
		
		
		driver.quit();
	}

}