package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunePageDto
{
	public boolean current;
	public List<RuneSlotDto> slots;
	public String name;
	public long id;

	public RunePageDto() { }

	public RunePageDto(JSONObject json)
	{
		current = (boolean) json.get("current");

		slots = new ArrayList<RuneSlotDto>();
		JSONArray tSlots = (JSONArray) json.get("slots");
		for(int i = 0; i < tSlots.size(); i ++)
		{
			slots.add(new RuneSlotDto((JSONObject) tSlots.get(i)));
		}

		name = (String) json.get("name");
		id = (long) json.get("id");
	}
}
