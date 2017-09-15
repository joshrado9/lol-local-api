package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MasteryPagesDto
{
	public List<MasteryPageDto> pages;
	public long summonerId;

	public MasteryPagesDto() { }

	public MasteryPagesDto(JSONObject json)
	{
		pages = new ArrayList<MasteryPageDto>();
		JSONArray tPages = (JSONArray) json.get("pages");
		for (int i = 0; i < tPages.size(); i ++)
		{
			pages.add(new MasteryPageDto((JSONObject) tPages.get(i)));
		}
		summonerId = (long) json.get("summonerId");
	}

	public void setMasteryPagesDto(MasteryPagesDto value)
	{
		this.pages = value.pages;
		this.summonerId = value.summonerId;
	}
}
