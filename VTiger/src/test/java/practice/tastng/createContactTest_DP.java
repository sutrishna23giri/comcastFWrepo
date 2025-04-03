package practice.tastng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class createContactTest_DP {

	@Test(dataProvider = "getData")
	public void createContactTest(String firstname , String lastname)
	{
		System.out.println("Firstname :" + firstname + ", Lastname :"+ lastname);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr = new Object[3][2];
		objArr[0][0] ="sutriahns";
		objArr[0][1] ="giri";
		
		objArr[1][0] ="sanu";
		objArr[1][1] ="ahmed";
		
		objArr[2][0] ="md saddam";
		objArr[2][1] ="husain";
		return objArr;
		
	}
}
