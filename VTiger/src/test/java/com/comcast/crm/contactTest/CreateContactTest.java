package com.comcast.crm.contactTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.contactPage;
import com.comcast.crm.ObjectRepositoryUtility.createNewContactPage;
import com.crm.generic.baseUtility.BaseClass;

public class CreateContactTest extends BaseClass {

	@Test
	public void createcontacttest() throws Throwable {

		// read testscript data from excel file

		String LastName = ELib.getDataFromExcel("contact", 1, 2);

		// step 2: navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create Contact" button
		contactPage cp = new contactPage(driver);
		cp.getCreateContactButton().click();

		// step 4: enter all the details and create new organization
		createNewContactPage cnc = new createNewContactPage(driver);
		cnc.createcontact(LastName);

		// Varify Header Msg Expected Result
		String act_LastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (act_LastName.contains(LastName)) {
			System.out.println(LastName + "is created----->PASS");
		} else {
			System.out.println(LastName + "is not created----->FAIL");
		}

	}

}