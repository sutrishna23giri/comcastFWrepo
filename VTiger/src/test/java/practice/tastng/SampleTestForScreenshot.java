package practice.tastng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenshot {

	@Test
	public void amazonTest() throws IOException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
//-------------Step-1:Create an Object to EventFiringWebDriver------------------
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		
//-------------Step-2:use getscreenshotas method to get file type of screenshot------------------
		File src = edriver.getScreenshotAs(OutputType.FILE);
		
//-------------Step-3:store screenshot on local driver------------------
		FileUtils.copyFile(src, new File("./screenshot/test.png"));
	}
	
}
