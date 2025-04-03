package testNG_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.contactORGpopupPage;
import com.comcast.crm.ObjectRepositoryUtility.contactPage;
import com.comcast.crm.ObjectRepositoryUtility.createNewContactPage;
import com.crm.generic.baseUtility.BaseClass;
/**
 * 
 * @author Monu
 *
 */
public class Vtiger_testNg_contact extends BaseClass {

	@Test(groups="smokeTest")
	public void createcontacttest() throws Throwable {

		String LastName = ELib.getDataFromExcel("contact", 1, 2) + JLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		contactPage cp = new contactPage(driver);
		cp.getCreateContactButton().click();

		/* step 4: enter all the details and create new organization*/
		createNewContactPage cnc = new createNewContactPage(driver);
		cnc.createcontact(LastName);

		/*Varify Header Msg Expected Result*/
		String act_LastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (act_LastName.contains(LastName)) {
			System.out.println(LastName + "is created----->PASS");
		} else {
			System.out.println(LastName + "is not created----->FAIL");
		}

	}

	@Test(groups="regressionTest")
	public void createcontactwithORGtest() throws Throwable {

/*--------------- read testscript data from excel file with random number---------------*/
		String orgname = ELib.getDataFromExcel("contact", 7, 2) + JLib.getRandomNumber();
		String contactLastName = ELib.getDataFromExcel("contact", 7, 3) + JLib.getRandomNumber();

//-------------------step 1: login to application-----------------------
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

//-------------------step 3: click on "create organization" button---------------------
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgButton().click();

//-------------------step 4: enter all the details and create new organization--------------
		CreatingNewOrganizationsPage CNOP = new CreatingNewOrganizationsPage(driver);
		CNOP.createOrg(orgname);
		Thread.sleep(3000);

//---------------------navigate to Contact module-----------------------
		HomePage hp1 = new HomePage(driver);
		hp1.getContactLink().click();

//---------------------click on "create Contact" button--------------------
		contactPage cp = new contactPage(driver);
		cp.getCreateContactButton().click();

//---------------------enter all the details and create new organization------------------
		createNewContactPage cnc = new createNewContactPage(driver);
		cnc.createcontact1(contactLastName);

//---------------------switch to child browser-----------------------------
		wlib.switchToNewBrowserTab(driver, "module=Accounts");

		contactORGpopupPage popup = new contactORGpopupPage(driver);
		popup.searchORG(orgname);

		driver.findElement(By.xpath("//a[.='" + orgname + "']")).click();

//----------------------switch to child browser----------------------------
		wlib.switchToNewBrowserTab(driver, "module=Contacts");

		cnc.getSaveButton().click();

	}

	@Test(groups="regressionTest")
	public void createcontactwithSupportDatetest() throws Throwable {
//--------------- read testscript data from excel file with random number---------------
		String lastname = ELib.getDataFromExcel("contact", 4, 2) + JLib.getRandomNumber();

//-------------------step 1: login to application-----------------------
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

//-------------------step 3: click on "create organization" button---------------------
		contactPage cp = new contactPage(driver);
		cp.getCreateContactButton().click();
		
//-----------------step 4 : enter all details and create new contact-------------------
		String endDate = JLib.getSystemDateYYYYDDMM();
		String startDate =JLib.getRequiredDateYYYYDDMM(30);
		System.out.println(startDate);
		System.out.println(endDate);
		createNewContactPage cnc = new createNewContactPage(driver);
		cnc.createcontact(lastname, endDate, startDate);
		
//-------------------verify header info---------------------------
		
	}
}