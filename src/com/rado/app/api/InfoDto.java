package com.rado.app.api;

import org.json.simple.JSONObject;

public class InfoDto
{
	public long magic;
	public long difficulty;
	public long defense;
	public long attack;

	public InfoDto() { }

	public InfoDto(JSONObject info)
	{
		magic = (long) info.get("magic");
		difficulty = (long) info.get("difficulty");
		defense = (long) info.get("defense");
		attack = (long) info.get("attack");
	}

}
