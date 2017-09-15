package com.rado.app.api;

import org.json.simple.JSONObject;

public class MatchReferenceDto
{
	public String lane;
	public long gameId;
	public long champion;
	public String platformId;
	public long season;
	public long queue;
	public String role;
	public long timestamp;

	public MatchReferenceDto() { }

	public MatchReferenceDto(JSONObject match)
	{
		this.lane = (String) match.get("lane");
		this.gameId = (long) match.get("gameId");
		this.champion = (long) match.get("champion");
		this.platformId = (String) match.get("platformId");
		this.season = (long) match.get("season");
		this.queue = (long) match.get("queue");
		this.role = (String) match.get("role");
		this.timestamp = (long) match.get("timestamp");
	}

	public void setMatchReferenceDto(MatchReferenceDto mrDto)
	{
		this.lane = mrDto.lane;
		this.gameId = mrDto.gameId;
		this.champion = mrDto.champion;
		this.platformId = mrDto.platformId;
		this.season = mrDto.season;
		this.queue = mrDto.queue;
		this.role = mrDto.role;
		this.timestamp = mrDto.timestamp;
	}
}
