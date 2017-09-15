package com.rado.app.api;

import org.json.simple.JSONObject;

public class RuneDto
{
	public long runeId;
	public long rank;

	public RuneDto() { }

	public RuneDto(JSONObject json)
	{
		runeId = (long) json.get("runeId");
		rank = (long) json.get("rank");
	}
}
