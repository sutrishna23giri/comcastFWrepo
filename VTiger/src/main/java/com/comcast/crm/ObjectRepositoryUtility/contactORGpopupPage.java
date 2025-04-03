package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class contactORGpopupPage {
	
	WebDriver driver;
	public contactORGpopupPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(name="search")
	private WebElement searchButton;

	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}
	
	public void searchORG(String orgname)
	{
		searchEdit.sendKeys(orgname);
		searchButton.click();
	}
}
