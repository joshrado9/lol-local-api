package com.rado.app.api;

import org.json.simple.JSONObject;

public class RuneSlotDto
{
	public long runeSlotId;
	public long runeId;

	public RuneSlotDto() { }

	public RuneSlotDto(JSONObject json)
	{
		runeSlotId = (long) json.get("runeSlotId");
		runeId = (long) json.get("runeId");
	}
}
