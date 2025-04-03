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

public class CreateOrgWithPhoneNoTest {

	public static void main(String[] args) throws Throwable {
		
		//read common data from properties file
		  
		  FileInputStream fis=new
		  FileInputStream("./Config_App_Data/commondata.properties"); 
		  Properties pobj=new Properties(); pobj.load(fis);
		  String BROWSER=pobj.getProperty("browser"); 
		  String URL=pobj.getProperty("url"); 
		  String USERNAME=pobj.getProperty("username");
		  String PASSWORD=pobj.getProperty("password");
		 
		
				        
				
		//generate the random number
		
		Random rndm = new Random();
		int rndmint = rndm.nextInt(10000);
		
		//read testscript data from excel file
		
		FileInputStream fis1 = new FileInputStream("./TestScript_Data/ddt.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("ORG");
		Row row = sh.getRow(9);
		String orgname = row.getCell(2).toString()+rndmint;
		String phoneNo = row.getCell(3).toString();
		
		
	    wb.close();
		
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
		
		driver.findElement(By.id("phone")).sendKeys(phoneNo);
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
		
		//verify phone no Exxpected Result
		String actphoneno=driver.findElement(By.id("dtlview_Phone")).getText();
		if(actphoneno.equals(phoneNo))
		{
			System.out.println(phoneNo + "is created----->PASS");
		}
		else
		{
			System.out.println(phoneNo + "is not created----->FAIL");
		}
		
		//step 5: logout from application
		
		
		driver.quit();
	}



	}


