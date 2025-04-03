package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitforpagetoload(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitforElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//switch to new tab based on URL
	
	public void switchToNewBrowserTab(WebDriver driver, String partialURL)
	{
		Set<String> child = driver.getWindowHandles();
		Iterator<String> itt = child.iterator(); 
		while(itt.hasNext())
		{
			String windowid = itt.next();
			driver.switchTo().window(windowid);
			
			String url = driver.getCurrentUrl();
			if(url.contains(partialURL)) 
			{
				break;
			}
	}
	}
		
	// switch to new tab based on TITLE

		public void switchToNewTitleTab(WebDriver driver, String partialTitle)
		{
			Set<String> child = driver.getWindowHandles();
			Iterator<String> itt = child.iterator(); 
			while(itt.hasNext())
			{
				String windowid = itt.next();
				driver.switchTo().window(windowid);
				
				String url = driver.getCurrentUrl();
				if(url.contains(partialTitle))
				{
					break;
				}
		}
		}
			
	// switch to new tab based on Frames
		
			public void switchToFrame(WebDriver driver, int index)
			{
				driver.switchTo().frame(index);
			}
		
	// switch to new tab based on Name and Id Attribute		
			
			public void switchToFrame(WebDriver driver, String nameID)
			{
				driver.switchTo().frame(nameID);
			}
			
	// switch to new tab based on WebElement
			
			public void switchToFrame(WebDriver driver, WebElement Element)
			{
				driver.switchTo().frame(Element);
			}
	//handling pop up		
		public void switchtoAlertandAccept(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		
		public void switchtoAlertandDismiss(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
	
   //handling dropdown
		
		public void select(WebElement element, String text)
		{
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
		}
		
		public void select(WebElement element, int index)
		{
			Select sel = new Select(element);
			sel.selectByIndex(index);
		}
		
	//Handling mouse action
		
		public void mousemovetoelement(WebDriver driver, WebElement element)
		{
			Actions act = new Actions(driver);
			act.moveToElement(element).perform();
		}
		
		public void DoubleClick(WebDriver driver, WebElement element)
		{
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
		}
	}


