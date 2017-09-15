package com.rado.app.api;

import org.json.simple.JSONObject;

public class MiniSeriesDto
{
	public int wins;
	public int losses;
	public int target;
	public String progress;

	public MiniSeriesDto() { }

	public MiniSeriesDto(JSONObject json)
	{
		wins = (int)json.get("wins");
		losses = (int) json.get("losses");
		target = (int) json.get("target");
		progress = (String) json.get("progress");
	}
}
