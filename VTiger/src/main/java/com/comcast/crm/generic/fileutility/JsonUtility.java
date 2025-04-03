package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

public class JsonUtility {

	public String getDataFromJsonFile(String key) throws IOException, ParseException
	{
		FileReader file = new FileReader("./Config_App_Data/AppCommonData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(file);
		JSONObject map =(JSONObject)obj;
		String data =(String) map.get(key);
		return data;
	}
}
