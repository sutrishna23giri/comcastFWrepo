package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class opportunityRelatedToPopUpPage {
	
	WebDriver driver;
	public opportunityRelatedToPopUpPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//.........object creation..............//

	@FindBy(name="search_text")
	private WebElement SearchEdit;

	public WebElement getSearchEdit() {
		return SearchEdit;
	}
	
	public void relatedToPopUp()
	{
		SearchEdit.sendKeys(null);
	}
}
