package com.rado.app.api;

import org.json.simple.JSONObject;

public class ChampionMasteryDto
{
	public boolean chestGranted;
	public int championLevel;
	public int championPoints;
	public long championId;
	public long playerId;
	public long championPointsUntilNextLevel;
	public long championPointsSinceLastLevel;
	public long lastPlayTime;

	public ChampionMasteryDto() { }

	public ChampionMasteryDto(JSONObject json)
	{
		chestGranted = (boolean) json.get("chestGranted");
		championLevel = (int) json.get("championLevel");
		championPoints = (int) json.get("championPoints");
		championId = (long) json.get("championId");
		playerId = (long) json.get("playerId");
		championPointsUntilNextLevel = (long) json.get("championPointsUntilNextLevel");
		championPointsSinceLastLevel = (long) json.get("championPointsSinceLastLevel");
		lastPlayTime = (long) json.get("lastPlayTime");
	}

	public void setChampionMasteryDto(ChampionMasteryDto value)
	{
		this.chestGranted = value.chestGranted;
		this.championLevel = value.championLevel;
		this.championPoints = value.championPoints;
		this.championId = value.championId;
		this.playerId = value.playerId;
		this.championPointsUntilNextLevel = value.championPointsUntilNextLevel;
		this.championPointsSinceLastLevel = value.championPointsSinceLastLevel;
		this.lastPlayTime = value.lastPlayTime;
	}
}
