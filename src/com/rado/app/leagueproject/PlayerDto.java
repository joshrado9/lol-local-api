package com.rado.app.leagueproject;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayerDto
{
	private Map<String, Object> playerDtoMap;

	public PlayerDto(JSONObject fellowPlayer)
	{
		this.playerDtoMap = new HashMap<String, Object>();
		try
		{
			playerDtoMap.put("championID", fellowPlayer.getInt("championId"));
			playerDtoMap.put("summonerID", fellowPlayer.getLong("summonerId"));
			playerDtoMap.put("teamID", fellowPlayer.getInt("teamId"));
		}
		catch (JSONException e)
		{
			System.out.println("Error: invalid PlayerDto JSONObject...");
			e.printStackTrace();
		}
	}

	public Map<String, Object> getPlayerDto()
	{
		return this.playerDtoMap;
	}
}
