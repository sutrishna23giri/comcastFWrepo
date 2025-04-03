package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createNewContactPage {

	WebDriver driver;
	public createNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//.........object creation..............//
	

	@FindBy(name="lastname")
	private WebElement lastnameEdit;
	
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement orgNameselectbutton;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	public WebElement getOrgNameselectbutton() {
		return orgNameselectbutton;
	}

	@FindBy(name="support_start_date")
	private WebElement startDate;
	
	@FindBy(name="support_end_date")
	private WebElement endtDate;
	
	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndtDate() {
		return endtDate;
	}

	public WebElement getLastnameEdit(String contact) {
		return lastnameEdit;
	}
    public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void createcontact(String contact)
	{
		lastnameEdit.sendKeys(contact);
		saveButton.click();
	}
	
	public void createcontact(String lastname, String sDate, String eDate)
	{
		lastnameEdit.sendKeys(lastname);
		startDate.clear();
		startDate.sendKeys(sDate);
		endtDate.clear();
		endtDate.sendKeys(eDate);
	}
	
	public void createcontact1(String lastname)
	{
		lastnameEdit.sendKeys(lastname);
		orgNameselectbutton.click();
	}
}
