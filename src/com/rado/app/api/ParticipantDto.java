package com.rado.app.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParticipantDto
{
	public ParticipantStatsDto stats;
	public long participantId;
	public List<RuneDto> runes;
	public ParticipantTimelineDto timeline;
	public long teamId;
	public long spell2Id;
	public List<MasteryMatchDto> masteries;
	public String highestAchievedSeasonTier;
	public long spell1Id;
	public long championId;

	public ParticipantDto() { }

	public ParticipantDto(JSONObject participant)
	{
		this.stats = new ParticipantStatsDto((JSONObject) participant.get("stats"));
		this.participantId = (long) participant.get("participantId");

		this.timeline = new ParticipantTimelineDto((JSONObject) participant.get("timeline"));
		this.teamId = (long) participant.get("teamId");
		this.spell2Id = (long) participant.get("spell2Id");

		this.highestAchievedSeasonTier = (String) participant.get("highestAchievedSeasonTier");
		this.spell1Id = (long) participant.get("spell1Id");
		this.championId = (long) participant.get("championId");

		this.runes = new ArrayList<RuneDto>();
		JSONArray runesArray = (JSONArray) participant.get("runes");
		for (int i = 0; i < runesArray.size(); i ++)
		{
			this.runes.add(new RuneDto((JSONObject) runesArray.get(i)));
		}

		this.masteries = new ArrayList<MasteryMatchDto>();
		JSONArray masteriesArray = (JSONArray) participant.get("masteries");
		for (int i = 0; i < masteriesArray.size(); i ++)
		{
			this.masteries.add(new MasteryMatchDto((JSONObject) masteriesArray.get(i)));
		}

	}
}
