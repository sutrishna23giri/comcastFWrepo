package com.comcast.crm.listenerUtility;

import org.testng.ITestResult;

public class RetryListenerImp {

	int count = 0;
	int limitcount = 5;
	public boolean retry(ITestResult result)
	{
		if(count<limitcount)
		{
			count++;
			return true;
		}
		return false;
	}
}
