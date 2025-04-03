package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrganizationTest {

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
		 
				
		//generate the random number
		
		Random rndm = new Random();
		int rndmint = rndm.nextInt(10000);
		
		//read testscript data from excel file
		
		FileInputStream fis1 = new FileInputStream("./TestScript_Data/ddt.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(7);
		String orgname = row.getCell(2).toString() + rndmint;
		String Contactname = row.getCell(3).getStringCellValue();
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
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
		
		//switch to child windows
		WLib.switchToNewBrowserTab(driver,"module=Accounts");
		
		//switch to parent windows
		WLib.switchToNewBrowserTab(driver,"Contacts&action");
		
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
		
	
		//---------Navigate to Contact Module-------------
		
		       // navigate to Contact module
				driver.findElement(By.linkText("Contacts")).click();
				
				//step 3: click on "create Contact" button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//step 4: enter all the details and create new organization
				driver.findElement(By.name("lastname")).sendKeys(Contactname);
				
				//step 5: navigate to the look up window
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//switch to child window
				Set<String> child = driver.getWindowHandles();
				Iterator<String> itt = child.iterator(); 
				while(itt.hasNext())
				{
					String windowid = itt.next();
					driver.switchTo().window(windowid);
					
					String url = driver.getCurrentUrl();
					if(url.contains("Accounts&action")) {
						break;
					}
				}
				
				//step 6: search the org name
				driver.findElement(By.name("search_text")).sendKeys(orgname);
				driver.findElement(By.name("search")).click();
				
				//creating dynamic xpath
				driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
				
				//switch to parent window
				Set<String> parent = driver.getWindowHandles();
				Iterator<String> itt1 = parent.iterator(); 
				while(itt1.hasNext())
				{
					String windowid = itt1.next();
					driver.switchTo().window(windowid);
					
					String url = driver.getCurrentUrl();
					if(url.contains("Contacts&action")) {
						break;
					}
				}
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Varify Header Msg Expected Result
				headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(Contactname))
				{
					System.out.println(Contactname + "is created----->PASS");
				}
				else
				{
					System.out.println(Contactname + "is not created----->FAIL");
				}
				
				
				//Varify Header Msg contactname Expected Result
				
				String act_orgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(act_orgname.contains(orgname))
				{
					System.out.println(orgname + "is created----->PASS");
				}
				else
				{
					System.out.println(orgname + "is not created----->FAIL");
				}
				driver.quit();

	}

}

