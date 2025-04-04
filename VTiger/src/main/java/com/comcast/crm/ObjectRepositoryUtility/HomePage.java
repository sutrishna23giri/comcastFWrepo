package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//.........object creation..............//
	
	
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Opportunities")
	private WebElement Opportunitieslink;
	
	@FindBy(linkText="Campaigns")
	private WebElement CampaignLink;
	
	@FindBy(linkText="Products")
	private WebElement ProductsLink;

	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText ="Sign Out")
	private WebElement signOutLink;
	
	

	public WebElement getCampaignLink() {
		return CampaignLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getOpportunitieslink() {
		return Opportunitieslink;
	}
	
	public WebElement getProductsLink() {
		return ProductsLink;
	}
	
	public WebElement getAdminImg() {
		return adminImg;
	}
	
	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	
	public void navigateTocampainPage()
	{
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		CampaignLink.click();
	}
	
	
public void logout()
{
   Actions act = new Actions(driver);
  act.moveToElement(adminImg).perform();
  signOutLink.click();
}



}
