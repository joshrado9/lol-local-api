package com.rado.app.leagueproject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecentGames
{
	private long summonerID;
	private List<GameDto> games;

	public RecentGames(JSONObject jsonObject, long summonerID)
	{
		this.summonerID = summonerID;
		this.games = new ArrayList<GameDto>();
		if (jsonObject == null)
		{
			System.out.println("Error: recentGamesJSON invalid");
		}

		try
		{
			long sID = jsonObject.getLong("summonerId");
			if (sID != this.summonerID)
			{
				System.out.println("Error: recentGamesJSON incorrect summonerID");
			}

			JSONArray gamesList = jsonObject.getJSONArray("games");

			for (int i = 0; i < gamesList.length(); i ++)
			{
				JSONObject game = gamesList.getJSONObject(i);

				this.games.add(new GameDto(game));
			}
		}
		catch (JSONException e)
		{
			System.out.println("Error: invalid entries in recentGamesJSON");
			e.printStackTrace();
		}
	}

	public List<GameDto> getMatchList()
	{
		return this.games;
	}
}
