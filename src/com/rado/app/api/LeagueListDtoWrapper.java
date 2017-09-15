package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LeagueListDtoWrapper
{
	public List<LeagueListDto> leagueList;

	public LeagueListDtoWrapper() { }

	public LeagueListDtoWrapper(JSONArray json)
	{
		this.leagueList = new ArrayList<LeagueListDto>();
		for (int i = 0; i < json.size(); i ++)
		{
			this.leagueList.add(new LeagueListDto((JSONObject) json.get(i)));
		}
	}

	public void set(LeagueListDtoWrapper value)
	{
		leagueList = value.leagueList;
	}

}
