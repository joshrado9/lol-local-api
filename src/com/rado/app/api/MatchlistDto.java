package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MatchlistDto
{
	public List<MatchReferenceDto> matchesList;
	public long totalGames;
	public long startIndex;
	public long endIndex;

	public MatchlistDto() { }

	public MatchlistDto(JSONObject matchlist)
	{
		this.totalGames = (long) matchlist.get("totalGames");
		this.startIndex = (long) matchlist.get("startIndex");
		this.endIndex = (long) matchlist.get("endIndex");

		JSONArray matches = (JSONArray) matchlist.get("matches");
		matchesList = new ArrayList<MatchReferenceDto>();
		for (int i = 0; i < matches.size(); i ++)
		{
			matchesList.add(new MatchReferenceDto((JSONObject) matches.get(i)));
		}
	}

	public void setMatchlistDto(MatchlistDto match)
	{
		this.endIndex = match.endIndex;
		this.startIndex = match.startIndex;
		this.totalGames = match.totalGames;
		this.matchesList = match.matchesList;
	}
}
