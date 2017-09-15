package com.rado.app.api;

import org.json.simple.JSONObject;

public class TeamBansDto
{
	public long pickTurn;
	public long championId;

	public TeamBansDto() { }

	public TeamBansDto(JSONObject ban)
	{
		this.pickTurn = (long) ban.get("pickTurn");
		this.championId = (long) ban.get("championId");
	}
}
