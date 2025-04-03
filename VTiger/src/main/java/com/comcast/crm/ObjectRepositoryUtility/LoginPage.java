package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * 
 * @author Monu
 *
 *Contains Login Page Element and Business Libraries like login() 
 */
//.......create separate java class........//

    public class LoginPage extends WebDriverUtility {
    	
    	WebDriver driver;
    	public LoginPage(WebDriver driver)
    	{
    		this.driver=driver;
    		PageFactory.initElements(driver, this);
    	}

//.........object creation..............//
    	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
//..........object initialization...............
	
	
//...........object encapsulation(getters).............//	

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
//............provide action(business method)...................//
/**
 * login to Application based on URL, UN, Pwd	
 * @param url
 * @param username
 * @param password
 */
	public void loginToApp (String url, String username, String password)
	{
		driver.get(url);
		usernameEdit.sendKeys("admin");
		passwordEdit.sendKeys("admin");
		loginButton.click();
	}
}
