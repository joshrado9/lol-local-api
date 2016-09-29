package com.rado.app.leagueproject;

import java.util.HashMap;
import java.util.Map;

public class Summoner
{
	private int summonerID;
	private String summonerName;
	private int summonerIconID;
	private long summonerLastRevisionDate;
	private int summonerLevel;


	public Map<String, Object> getSummoner()
	{
		Map<String, Object> summonerObject = new HashMap<String, Object>();

		summonerObject.put("summonerID", summonerID);
		summonerObject.put("summonerName", summonerName);
		summonerObject.put("summonerIconID", summonerIconID);
		summonerObject.put("summonerLastRevisionDate", summonerLastRevisionDate);
		summonerObject.put("summonerLevel", summonerLevel);

		return summonerObject;
	}

	public Summoner(int id, String name, int iconID, long revDate, int level)
	{
		this.summonerID = id;
		this.summonerName = name;
		this.summonerIconID = iconID;
		this.summonerLastRevisionDate = revDate;
		this.summonerLevel = level;
	}
}
