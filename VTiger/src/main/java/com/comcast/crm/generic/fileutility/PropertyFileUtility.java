package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {

	public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis= new FileInputStream("./Config_App_Data/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String data= pobj.getProperty(key);
		return data;
	}
}
