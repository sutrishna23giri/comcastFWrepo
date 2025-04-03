package testNG_test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationsPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationsPage;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerUtility.ListenerImplementationClass;
import com.crm.generic.baseUtility.BaseClass;

public class Vtiger_testNG_orginization extends BaseClass {

	@Test(groups="smokeTest")
	public void createorgtest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");
		
		// read testscript data from excel file with random number
		String orgname = ELib.getDataFromExcel("ORG", 1, 2) + JLib.getRandomNumber();

		// step 1: login to application
		UtilityClassObject.getTest().log(Status.INFO,"navigate to org page");
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: click on "create organization" button
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create org page");
		
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgButton().click();

		// step 4: enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO,"create a new org");
		
		CreatingNewOrganizationsPage CNOP = new CreatingNewOrganizationsPage(driver);
		CNOP.createOrg(orgname);
		
		UtilityClassObject.getTest().log(Status.INFO, orgname + "--->new org created");
		
		Thread.sleep(3000);

		// Varify Header Msg Expected Result
//		OrganizationsInfoPage OIP = new OrganizationsInfoPage(driver);
//		String ActOrgName = OIP.getHeaderMsg().getText();
//				
//			if(ActOrgName.contains(orgname))
//				{
//					System.out.println(orgname + "name is varified======PASS");
//				}
//				else
//				{
//					System.out.println(orgname + "name is not varified======FAIL");
//				}
//				
	}

	@Test(groups="regressionTest")

	public void CreateOrgWithPhoneNoTest() throws Throwable {

		// read testscript data from excel file with random number

		String orgname = ELib.getDataFromExcel("ORG", 1, 2) + JLib.getRandomNumber();
		String phone = ELib.getDataFromExcel("ORG", 9, 3);

		// step 1: login to application
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 2: navigate to organization module
		hp.getOrgLink().click();

		// step 3: click on "create organization" button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgButton().click();

		// step 4: enter all the details and create new organization
		CreatingNewOrganizationsPage CNOP = new CreatingNewOrganizationsPage(driver);
		CNOP.createOrg1(orgname, phone);
		
		Thread.sleep(3000);

	}

	@Test(groups="regressionTest")

	public void CreateOrgWithIndustryTest() throws Throwable {
		String orgname = ELib.getDataFromExcel("ORG", 1, 2) + JLib.getRandomNumber();
		String Industry = ELib.getDataFromExcel("ORG", 5, 3);

//step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

//step 3: click on "create organization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgButton().click();

//step 4: enter all the details and create new organization
		CreatingNewOrganizationsPage CNOP = new CreatingNewOrganizationsPage(driver);
//CNOP.createOrg(orgname);
		CNOP.createOrg(orgname, Industry);
		
		Thread.sleep(3000);

	}
}