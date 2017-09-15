package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MasteryPageDto
{
	public boolean current;
	public List<MasteryDto> masteries;
	public String name;
	public long id;

	public MasteryPageDto() { }

	public MasteryPageDto(JSONObject json)
	{
		current = (boolean) json.get("current");
		name = (String) json.get("name");
		id = (long) json.get("id");

		masteries = new ArrayList<MasteryDto>();
		JSONArray tMasteries = (JSONArray) json.get("masteries");
		for (int i = 0; i < tMasteries.size(); i ++)
		{
			masteries.add(new MasteryDto((JSONObject) tMasteries.get(i)));
		}
	}

	public void setMasteryPageDto(MasteryPageDto value)
	{
		this.current = value.current;
		this.name = value.name;
		this.id = value.id;
		this.masteries = value.masteries;
	}

}
