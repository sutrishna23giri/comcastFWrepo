package com.crm.generic.baseUtility;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class BaseClass {

	/*----------------Create Object---------------*/

	public PropertyFileUtility Plib = new PropertyFileUtility();
	public ExcelUtility ELib = new ExcelUtility();
	public JavaUtility JLib = new JavaUtility();
	public DataBaseUtility dblib = new DataBaseUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("----connect to DB, Report config----");
		dblib.getdbconnection();
	}
		
@BeforeClass(groups={"smokeTest","regressionTest"})
  public void configBC() throws Throwable
	{
		System.out.println("-----launch a browse-----");
		String BROWSER =Plib.getDataFromPropertiesFile("browser");
		System.out.println(BROWSER);
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
	sdriver=driver;
	UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Throwable {
		System.out.println("-----login----");

		String URL = Plib.getDataFromPropertiesFile("url");
		String USERNAME = Plib.getDataFromPropertiesFile("username");
		String PASSWORD = Plib.getDataFromPropertiesFile("password");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAC() throws SQLException {
		System.out.println("-----logout-----");
		HomePage hp = new HomePage(driver);
		hp.getAdminImg().click();
		hp.getSignOutLink().click();
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("----close the browser----");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("----close DB , Report Backup----");
		dblib.closeDBconnection();

	}
}
