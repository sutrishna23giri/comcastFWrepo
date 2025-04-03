package com.comcast.crm.OrganizationTest;

import java.io.FileInputStream;
import java.time.Duration;
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
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationTest {

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
	
		  String orgname = ELib.getDataFromExcel("ORG", 1, 2) + JLib.getRandomNumber();
		
		
	  
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2: navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3: click on "create organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Varify Header Msg Expected Result
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgname))
		{
			System.out.println(orgname + "is created----->PASS");
		}
		else
		{
			System.out.println(orgname + "is not created----->FAIL");
		}
		
		//verify Header orgName info Exxpected Result
		String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgName.contains(orgname))
		{
			System.out.println(orgname + "is created----->PASS");
		}
		else
		{
			System.out.println(orgname + "is not created----->FAIL");
		}
		Thread.sleep(5000);
		//step 5: logout from application
		
		
		driver.quit();
	}



	}


