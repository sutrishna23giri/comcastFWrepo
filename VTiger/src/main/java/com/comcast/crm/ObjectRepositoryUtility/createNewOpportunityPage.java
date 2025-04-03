package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class createNewOpportunityPage {

	WebDriver driver;
	public createNewOpportunityPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//.........object creation..............//
	
	@FindBy(name="potentialname")
	private WebElement opportunityNameEdit;
	
	@FindBy(xpath ="(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement RelatedToDD;
	
	@FindBy(name="sales_stage")
	private WebElement salesStageDD;
	
	@FindBy(name="assigned_user_id")
	private WebElement AssignToDD ;
	
	@FindBy(name="closingdate")
	private WebElement closedate ;
	
	public WebElement getOpportunityNameEdit() {
		return opportunityNameEdit;
	}

	public WebElement getRelatedToDD() {
		return RelatedToDD;
	}

	public WebElement getSalesStageDD() {
		return salesStageDD;
	}

	public WebElement getAssignToDD() {
		return AssignToDD;
	}

	public WebElement getClosedate() {
		return closedate;
	}
	
	public void createopprtunity(String opportunityName, String AssignTo)
	{
		opportunityNameEdit.sendKeys(opportunityName);
		
		Select sel = new Select(AssignToDD);
		sel.selectByVisibleText(AssignTo);
		
		RelatedToDD.click();

	}
	
	

}
