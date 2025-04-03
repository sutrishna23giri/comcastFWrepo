package com.comcast.crm.OpportunitiesTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.createNewOpportunityPage;
import com.comcast.crm.ObjectRepositoryUtility.opportunityPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOpportunityTestWithPOM {

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
	
		  String opportunityName = ELib.getDataFromExcel("opportunity", 1, 2) + JLib.getRandomNumber();
		  String AssignTo = ELib.getDataFromExcel("opportunity", 1, 4);
		
	  
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
		
		//navigate to opportunities link
        HomePage hp = new HomePage(driver);
		hp.getOpportunitieslink().click();
		
		//step 3: click on "create new opportunities" button
		opportunityPage OP = new opportunityPage(driver);
		OP.getcreateNewOpportunityButton().click();	
		
		//step 4: enter all the details and create new opportunity
		createNewOpportunityPage COP = new createNewOpportunityPage(driver);
		COP.createopprtunity(opportunityName, AssignTo);
		
		

	}

}
