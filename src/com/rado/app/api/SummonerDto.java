package com.rado.app.api;

import org.json.simple.JSONObject;

public class SummonerDto
{
	public String region;
	public int profileIconId;
	public String name;
	public long summonerLevel;
	public long revisionDate;
	public long id;
	public long accountId;

	public SummonerDto() { }

	public SummonerDto(JSONObject summonerJSON, String region)
	{
		this.region = region;
		this.profileIconId = (int) summonerJSON.get("profileIconId");
		this.name = (String) summonerJSON.get("name");
		this.summonerLevel = (long) summonerJSON.get("summonerLevel");
		this.revisionDate = (long) summonerJSON.get("revisionDate");
		this.id = (long) summonerJSON.get("id");
		this.accountId = (long) summonerJSON.get("accountId");
	}

	public void setSummonerDto(SummonerDto sum)
	{
		this.region = sum.region;
		this.profileIconId = sum.profileIconId;
		this.name = sum.name;
		this.summonerLevel = sum.summonerLevel;
		this.revisionDate = sum.revisionDate;
		this.id = sum.id;
		this.accountId = sum.accountId;
	}
}
