package com.rado.app.api;

import org.json.simple.JSONObject;

public class LeagueItemDto
{
	public String rank;
	public boolean hotStreak;
	public MiniSeriesDto miniSeries;
	public long wins;
	public boolean veteran;
	public long losses;
	public String playerOrTeamId;
	public String playerOrTeamName;
	public boolean inactive;
	public boolean freshBlood;
	public long leaguePoints;

	public LeagueItemDto() { }

	public LeagueItemDto(JSONObject json)
	{
		rank = (String) json.get("rank");
		hotStreak = (boolean) json.get("hotStreak");
		try {
			miniSeries = new MiniSeriesDto((JSONObject) json.get("miniSeries"));
		} catch (Exception e) { /*Do nothing*/ }
		wins = (long) json.get("wins");
		veteran = (boolean) json.get("veteran");
		losses = (long) json.get("losses");
		playerOrTeamId = (String) json.get("playerOrTeamId");
		playerOrTeamName = (String) json.get("playerOrTeamName");
		inactive = (boolean) json.get("inactive");
		freshBlood = (boolean) json.get("freshBlood");
		leaguePoints = (long) json.get("leaguePoints");
	}
}
