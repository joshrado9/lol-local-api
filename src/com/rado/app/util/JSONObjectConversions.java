package com.rado.app.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class JSONObjectConversions
{
	public static Map<String, Object> convertJSONObjectToMap(JSONObject json)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<?> keys = json.keySet().iterator();
		while (keys.hasNext())
		{
			String key = (String) keys.next();
			map.put(key, json.get(key));
		}

		return map;
	}
}
