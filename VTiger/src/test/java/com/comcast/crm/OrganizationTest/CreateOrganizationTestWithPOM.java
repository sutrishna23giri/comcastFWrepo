package com.comcast.crm.OrganizationTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationTestWithPOM {

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
		
		driver.get(URL);
		LoginPage login = PageFactory.initElements(driver,LoginPage.class);
		
		//using getters
		//login.getUsernameEdit().sendKeys("admin");
		//login.getPasswordEdit().sendKeys("admin");
		//login.getLoginButton().click();
		
		//using business method
		login.loginToApp(USERNAME, PASSWORD, URL);
		
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
		
		//step 5: logout from application
		hp.getAdminImg().click();
		hp.getSignOutLink().click();
	}



	}


