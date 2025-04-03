package practice.tastng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class amazon {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		WebElement DD = driver.findElement(By.id("s-result-sort-select"));
		//WebElement DD = driver.findElement(By.xpath("//span[.='Featured']/preceding-sibling::span/parent::span/../../parent::span//select[@id='s-result-sort-select']"));
		Thread.sleep(2000);
		Select sel = new Select(DD);
		sel.selectByVisibleText("Price: Low to High");
		
		Thread.sleep(2000);
		//driver.quit();
		

	}

}
