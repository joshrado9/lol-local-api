package com.rado.app.leagueproject;

import org.json.JSONException;
import org.json.JSONObject;

public class ChampionDto
{
	public int id;
	public String name;
	public String title;

	public ChampionDto(JSONObject champion)
	{
		try
		{
			id = champion.getInt("id");
			name = champion.getString("name");
			title = champion.getString("title");
		}
		catch (JSONException e)
		{
			System.out.println("Error ChampionDto JSON...");
			e.printStackTrace();
		}
	}
}
