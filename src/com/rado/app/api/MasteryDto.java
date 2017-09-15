package com.rado.app.api;

import org.json.simple.JSONObject;

public class MasteryDto
{
	public long id;
	public long rank;

	public MasteryDto() { }

	public MasteryDto(JSONObject mastery)
	{
		this.id = (long) mastery.get("id");
		this.rank = (long) mastery.get("rank");
	}
}
