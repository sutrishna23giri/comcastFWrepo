package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationsPage {
	

	WebDriver driver;
	public CreatingNewOrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//.........object creation..............//
	

	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name="phone")
	private WebElement phoneEdit;
	
	@FindBy(name="industry")
	private WebElement industryDD;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public WebElement getPhoneEdit() {
		return phoneEdit;
	}

	
	public void createOrg(String orgname)
	{
		orgNameEdit.sendKeys(orgname);
		saveButton.click();
	}
	
	public void createOrg1(String orgname, String phone)
	{
		orgNameEdit.sendKeys(orgname);
		phoneEdit.sendKeys(phone);
		saveButton.click();
	}
	
	public void createOrg(String orgname, String Industry)
	{
		orgNameEdit.sendKeys(orgname);
		
		Select sel = new Select(industryDD);
		sel.selectByVisibleText(Industry);
		saveButton.click();
	}
}
