package com.rado.app.leagueproject;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Summoner
{
	private String region;

	private Map<String, Object> summonerMap;


	public Map<String, Object> getSummoner()
	{
		return this.summonerMap;
	}

	public Summoner(JSONObject summonerJSON, String name, String region)
	{
		this.summonerMap = new HashMap<String, Object>();

		this.region = region;
		//parse summoner json object
		if (summonerJSON == null)
		{
			System.out.println("Error: summonerJSON is invalid.");
			return;
		}

		try
		{
			JSONObject object_type = summonerJSON.getJSONObject(name);

			summonerMap.put("summonerID", object_type.getInt("id"));
			summonerMap.put("summonerName", object_type.getString("name"));
			summonerMap.put("summonerIconID", object_type.getInt("profileIconId"));
			summonerMap.put("summonerLastRevisionDate", object_type.getLong("revisionDate"));
			summonerMap.put("summonerLevel", object_type.getInt("summonerLevel"));
		}
		catch (JSONException e)
		{
			System.out.println("getSummonerByName parsing JSONObject error...");
			e.printStackTrace();
			return;
		}
	}

	public int getSummonerID()
	{
		return (int) this.summonerMap.get("summonerID");
	}

	public String getRegion()
	{
		return this.region;
	}
}
