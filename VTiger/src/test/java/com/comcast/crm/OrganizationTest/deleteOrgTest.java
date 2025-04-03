package com.comcast.crm.OrganizationTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class deleteOrgTest {

	public static void main(String[] args) throws Throwable {
		
/*................CREATE OBJECT.................*/	
		
		PropertyFileUtility Flib = new PropertyFileUtility();
		ExcelUtility ELib = new ExcelUtility();
		JavaUtility JLib = new JavaUtility();
		WebDriverUtility WLib = new WebDriverUtility();
	
		//read common data from properties file
		  
		  String BROWSER=Flib.getDataFromPropertiesFile("browser"); 
		  String URL=Flib.getDataFromPropertiesFile("url"); 
		  String USERNAME=Flib.getDataFromPropertiesFile("username");
		  String PASSWORD=Flib.getDataFromPropertiesFile("password");
		 
		//read testscript data from excel file with random number
	
		  String orgname = ELib.getDataFromExcel("ORG", 12, 2) + JLib.getRandomNumber();
		
		
	  
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
		
		//using getters
		//login.getUsernameEdit().sendKeys("admin");
		//login.getPasswordEdit().sendKeys("admin");
		//login.getLoginButton().click();
		
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
		CNOP.createOrg(orgname);
		
		//Varify Header Msg Expected Result
		OrganizationsInfoPage OIP = new OrganizationsInfoPage(driver);
		String ActOrgName = OIP.getHeaderMsg().getText();
		
		if(ActOrgName.contains(orgname))
		{
			System.out.println(orgname + "name is varified======PASS");
		}
		else
		{
			System.out.println(orgname + "name is not varified======FAIL");
		}
		
		
		//go back to organization page
		hp.getOrgLink().click();
		
		//search for organization name
		cnp.getSearchEdit().sendKeys(orgname);
		WLib.select(cnp.getSearchDropDown(), "Organization Name");
		cnp.getSearchButton().click();
		
		//dynamic xpath which execute in runtime
		//driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
		
		//in dynamic webtable select and delete org name
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
		Alert al = driver.switchTo().alert();
		al.accept();
		//step 5: logout from application
		
		
		driver.quit();
	}



	}


