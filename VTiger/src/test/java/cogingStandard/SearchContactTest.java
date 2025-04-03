package cogingStandard;
/**
 * test class for contact module
 * @author Monu
 *
 */

import org.testng.annotations.Test;

import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.crm.generic.baseUtility.BaseClass;

public class SearchContactTest extends BaseClass {

	/**
	 * Scenario : login()----->navigateContact----->createContact----->verify
	 * 
	 */
	
	@Test
	public void searchContactTest()
	{
		/*step 1 : Login to App*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
		
	}
}
