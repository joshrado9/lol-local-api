package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LeagueListDto
{
	public String tier;
	public String queue;
	public String name;
	public List<LeagueItemDto> entries;

	public LeagueListDto() { }

	public LeagueListDto(JSONObject json)
	{
		tier = (String) json.get("tier");
		queue = (String) json.get("queue");
		name = (String) json.get("name");

		entries = new ArrayList<LeagueItemDto>();
		JSONArray tEntries = (JSONArray) json.get("entries");
		for (int i = 0; i < tEntries.size(); i ++)
		{
			entries.add(new LeagueItemDto((JSONObject) tEntries.get(i)));
		}
	}
}
