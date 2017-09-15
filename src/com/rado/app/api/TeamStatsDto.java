package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TeamStatsDto
{
	public boolean firstDragon;
	public boolean firstInhibitor;
	public List<TeamBansDto> bans;
	public long baronKills;
	public boolean firstRiftHerald;
	public boolean firstBaron;
	public long riftHeraldKills;
	public boolean firstBlood;
	public long teamId;
	public boolean firstTower;
	public long vilemawKills;
	public long inhibitorKills;
	public long towerKills;
	public long dominionVictoryScore;
	public String win;
	public long dragonKills;

	public TeamStatsDto() { }

	public TeamStatsDto(JSONObject stats)
	{
		this.firstDragon = (boolean) stats.get("firstDragon");
		this.firstInhibitor = (boolean) stats.get("firstInhibitor");

		this.baronKills = (long) stats.get("baronKills");
		this.firstRiftHerald = (boolean) stats.get("firstRiftHerald");
		this.firstBaron = (boolean) stats.get("firstBaron");
		this.riftHeraldKills = (long) stats.get("riftHeraldKills");
		this.firstBlood = (boolean) stats.get("firstBlood");
		this.teamId = (long) stats.get("teamId");
		this.firstTower = (boolean) stats.get("firstTower");
		this.vilemawKills = (long) stats.get("vilemawKills");
		this.inhibitorKills = (long) stats.get("towerKills");
		this.towerKills = (long) stats.get("towerKills");
		this.dominionVictoryScore = (long) stats.get("dominionVictoryScore");
		this.win = (String) stats.get("win");
		this.dragonKills = (long) stats.get("dragonKills");

		this.bans = new ArrayList<TeamBansDto>();
		JSONArray bansArray = (JSONArray) stats.get("bans");
		for (int i = 0; i < bansArray.size(); i ++)
		{
			this.bans.add(new TeamBansDto((JSONObject) bansArray.get(i)));
		}
	}
}
