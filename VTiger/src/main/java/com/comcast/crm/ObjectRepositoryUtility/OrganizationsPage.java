package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationsPage {
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(name="search_field")
	private WebElement searchDropDown;
	
	@FindBy(name="submit")
	private WebElement searchButton;
	
	public WebElement getSearchButton() {
		return searchButton;
	}


	public WebElement getSearchEdit() {
		return searchEdit;
	}


	public WebElement getSearchDropDown() {
		return searchDropDown;
	}

	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createNewOrgButton;
	
	
    public WebElement getCreateNewOrgButton() {
		return createNewOrgButton;
	}
	
}
