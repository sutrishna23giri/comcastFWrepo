package practice.tastng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname , String productname)
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname,Keys.ENTER);
		
		//capture product info
		String x ="//span[text()='"+productname+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		ExcelUtility elib = new ExcelUtility();
		int rowcount = elib.getRowCount("productDP");
		
		Object[][] objArr = new Object[rowcount][2];
		
		for(int i=0;i<rowcount;i++) {
			objArr[i][0] =elib.getDataFromExcel("productDP", i+1, 0);
			objArr[i][1] =elib.getDataFromExcel("productDP", i+1, 1);
		}
		
		return objArr;
		
	}
}
