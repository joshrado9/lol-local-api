package com.rado.app.api;

import java.util.Map;

import org.json.simple.JSONObject;

import com.rado.app.util.JSONObjectConversions;

public class MapDataDto
{
	public String version;
	public String type;
	public Map<String, Object> data;

	public MapDataDto() { }

	public MapDataDto(JSONObject mapData)
	{
		this.version = (String) mapData.get("version");
		this.type = (String) mapData.get("type");

		this.data = JSONObjectConversions.convertJSONObjectToMap((JSONObject) mapData.get("data"));
	}

	public void setStaticMapData(MapDataDto value)
	{
		this.version = value.version;
		this.type = value.type;
		this.data = value.data;
	}
}
