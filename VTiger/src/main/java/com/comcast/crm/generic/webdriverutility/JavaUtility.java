package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber()
	{
		Random random = new Random();
		int randomnumber=random.nextInt(100000);
		return randomnumber;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
		
	}
	
	public String getRequiredDateYYYYDDMM(int days)
	{
		Date dateobj = new Date();
		SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dateobj);
		Calendar cal =sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String RequireDate=sim.format(cal.getTime());
		return RequireDate;
	}
}
