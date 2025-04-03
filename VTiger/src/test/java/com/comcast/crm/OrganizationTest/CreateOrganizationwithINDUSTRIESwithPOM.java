package com.comcast.crm.OrganizationTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationwithINDUSTRIESwithPOM {

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
		 
		 //generate the random number
		
		
		
		//read testscript data from excel file with random number
		
		  String orgname = ELib.getDataFromExcel("ORG", 1, 2) + JLib.getRandomNumber();
		  String Industry = ELib.getDataFromExcel("ORG",5, 3);
		
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
		
		//using business method
		login.loginToApp(USERNAME, PASSWORD);
				
		//step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//step 3: click on "create organization" button
		OrganizationsPage cnp =new OrganizationsPage(driver);
		cnp.getCreateNewOrgButton().click();
		
		//step 4: enter all the details and create new organization
		CreatingNewOrganizationsPage CNOP = new CreatingNewOrganizationsPage(driver);
		//CNOP.createOrg(orgname);
		CNOP.createOrg(orgname,Industry);
		
		
		//----------------Varify the industry and type info--------------------
		
		//industry varify
		
		Thread.sleep(2000);
		
		hp.logout();
		
		
	}



	}


