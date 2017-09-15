package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunePagesDto
{
	public List<RunePageDto> pages;
	public long summonerId;

	public RunePagesDto() { }

	public RunePagesDto(JSONObject json)
	{
		pages = new ArrayList<RunePageDto>();
		JSONArray tPages = (JSONArray) json.get("pages");
		for (int i = 0; i < tPages.size(); i ++)
		{
			pages.add(new RunePageDto((JSONObject) tPages.get(i)));
		}

		summonerId = (long) json.get("summonerId");
	}
}
