package com.rado.app.api;

import org.json.simple.JSONObject;

public class MasteryMatchDto
{
	public long masteryId;
	public long rank;

	public MasteryMatchDto() { }

	public MasteryMatchDto(JSONObject mastery)
	{
		this.masteryId = (long) mastery.get("masteryId");
		this.rank = (long) mastery.get("rank");
	}
}
