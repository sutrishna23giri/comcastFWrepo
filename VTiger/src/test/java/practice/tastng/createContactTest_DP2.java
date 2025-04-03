package practice.tastng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class createContactTest_DP2 {

	@Test(dataProvider = "getData")
	public void createContactTest(String firstname , String lastname , long phonenumber)
	{
		System.out.println("Firstname :" + firstname + ", Lastname :"+ lastname +", Phonenumber :"+ phonenumber);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr = new Object[3][3];
		objArr[0][0] ="sutriahns";
		objArr[0][1] ="giri";
		objArr[0][2] =986544567;
		
		objArr[1][0] ="sanu";
		objArr[1][1] ="ahmed";
		objArr[1][2] =566778778;
		
		objArr[2][0] ="md saddam";
		objArr[2][1] ="husain";
		objArr[2][2] =989897567;
		
		return objArr;
		
	}
}
