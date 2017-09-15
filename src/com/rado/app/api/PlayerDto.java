package com.rado.app.api;

import org.json.simple.JSONObject;

public class PlayerDto
{
	public Object currentPlatformId;
	public Object summonerName;
	public Object matchHistoryUri;
	public Object platformId;
	public Object currentAccountId;
	public Object profileIcon;
	public Object summonerId;
	public Object accountId;

	public PlayerDto() { }

	public PlayerDto(JSONObject player)
	{
		if (player != null)
		{
			this.currentPlatformId = player.get("currentPlatformId");
			this.summonerName = player.get("summonerName");
			this.matchHistoryUri = player.get("matchHistoryUri");
			this.platformId = player.get("platformId");
			this.currentAccountId = player.get("currentAccountId");
			this.profileIcon = player.get("profileIcon");
			this.summonerId = player.get("summonerId");
			this.accountId = player.get("accountId");
		}
	}
}
