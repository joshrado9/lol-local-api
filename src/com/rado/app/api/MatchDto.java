package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MatchDto {

	public long seasonId;
	public long queueId;
	public long gameId;
	public List<ParticipantIdentityDto> participantIdentities;
	public String gameVersion;
	public String platformId;
	public String gameMode;
	public long mapId;
	public String gameType;
	public List<TeamStatsDto> teams;
	public List<ParticipantDto> participants;
	public long gameDuration;
	public long gameCreation;

	public MatchDto() { }

	public MatchDto(JSONObject match)
	{
		this.seasonId = (long) match.get("seasonId");
		this.queueId = (long) match.get("queueId");
		this.gameId = (long) match.get("gameId");

		this.gameVersion = (String) match.get("gameVersion");
		this.platformId = (String) match.get("platformId");
		this.gameMode = (String) match.get("gameMode");
		this.mapId = (long) match.get("mapId");
		this.gameType = (String) match.get("gameType");

		this.gameDuration = (long) match.get("gameDuration");
		this.gameCreation = (long) match.get("gameCreation");

		this.participantIdentities = new ArrayList<ParticipantIdentityDto>();
		JSONArray participantIdentitiesArray = (JSONArray) match.get("participantIdentities");
		for (int i = 0; i < participantIdentitiesArray.size(); i ++)
		{
			this.participantIdentities.add(new ParticipantIdentityDto((JSONObject) participantIdentitiesArray.get(i)));
		}

		this.teams = new ArrayList<TeamStatsDto>();
		JSONArray teamsArray = (JSONArray) match.get("teams");
		for (int i = 0; i < teamsArray.size(); i ++)
		{
			this.teams.add(new TeamStatsDto((JSONObject) teamsArray.get(i)));
		}

		this.participants = new ArrayList<ParticipantDto>();
		JSONArray participantsArray = (JSONArray) match.get("participants");
		for (int i = 0; i < participantsArray.size(); i ++)
		{
			this.participants.add(new ParticipantDto((JSONObject) participantsArray.get(i)));
		}
	}

	public void setMatch(MatchDto value)
	{
		this.seasonId = value.seasonId;
		this.queueId = value.queueId;
		this.gameId = value.gameId;
		this.gameVersion = value.gameVersion;
		this.platformId = value.platformId;
		this.gameMode = value.gameMode;
		this.mapId = value.mapId;
		this.gameType = value.gameType;
		this.gameDuration = value.gameDuration;
		this.gameCreation = value.gameCreation;

		this.participantIdentities = value.participantIdentities;
		this.teams = value.teams;
		this.participants = value.participants;
	}

}
