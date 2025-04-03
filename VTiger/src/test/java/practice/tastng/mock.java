package practice.tastng;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class mock {

	public static void main(String[] args) throws Throwable, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iPhone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("//span[.='Apple iPhone 15 (128 GB) - Black']")).click();
		String price = driver.findElement(By.xpath("//span[.='60,900']")).getText();
		System.out.println(price);
		Thread.sleep(20000);
		
		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Refrigerator");
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("(//a[@class='a-link-normal s-line-clamp-2 s-link-style a-text-normal'])[1]")).click();
		String price2=driver.findElement(By.xpath("//span[.='14,990']")).getText();
		System.out.println(price2);
		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("laptop");
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("(//div[@class='aok-relative'])[1]")).click();
		String price3=driver.findElement(By.xpath("//span[.='52,890']")).getText();
		System.out.println(price3);
		
		FileInputStream fis = new FileInputStream("C:/Users/Monu/Desktop/tekpyramid/ddt.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet("MOCK").getRow(1).createCell(2).setCellValue(price);
		wb.getSheet("MOCK").getRow(2).createCell(2).setCellValue(price2);
		wb.getSheet("MOCK").getRow(3).createCell(2).setCellValue(price3);
		FileOutputStream fos1 = new FileOutputStream("C:/Users/Monu/Desktop/tekpyramid/ddt.xlsx");
		wb.write(fos1);
		

	}

}
