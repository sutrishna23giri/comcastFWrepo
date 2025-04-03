package practice.homepageTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HomePageVarificationTest {
	
	@Test
	public void homepagetest()
	{
		String expectedTitle="Home";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String actualTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		if(actualTitle.trim().equals(expectedTitle))
		{
			System.out.println(expectedTitle + "page is varified-----PASS");
		}
		else {
			System.out.println(expectedTitle + "page is not varified------FAIL");
		}
	}
	
	
	@Test
	public void varifyLogohomepagetest()
	{
		
	}

}
