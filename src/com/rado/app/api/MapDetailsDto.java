package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MapDetailsDto
{
	public String mapName;
	public ImageDto image;
	public long mapId;
	public List<Long> unpurchaseableItemList;

	public MapDetailsDto() { }

	public MapDetailsDto(JSONObject details)
	{
		this.mapName = (String) details.get("mapName");
		this.image = new ImageDto((JSONObject) details.get("image"));
		this.mapId = (long) details.get("mapId");

		this.unpurchaseableItemList = new ArrayList<Long>();
		JSONArray itemListArray = (JSONArray) details.get("unpurchaseableItemList");
		for (int i = 0; i < itemListArray.size(); i ++)
		{
			this.unpurchaseableItemList.add((long) itemListArray.get(i));
		}
	}
}
