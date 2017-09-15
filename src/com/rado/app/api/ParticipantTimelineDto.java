package com.rado.app.api;

import java.util.Map;

import org.json.simple.JSONObject;

import com.rado.app.util.JSONObjectConversions;

public class ParticipantTimelineDto
{
	public String lane;
	public long participantId;
	public Map<String, Object> csDiffPerMinDeltas;
	public Map<String, Object> goldPerMinDeltas;
	public Map<String, Object> xpDiffPerMinDeltas;
	public Map<String, Object> creepsPerMinDeltas;
	public Map<String, Object> xpPerMinDeltas;
	public String role;
	public Map<String, Object> damageTakenDiffPerMinDeltas;
	public Map<String, Object> damageTakenPerMinDeltas;

	public ParticipantTimelineDto() { }

	public ParticipantTimelineDto(JSONObject time)
	{
		this.lane = (String) time.get("lane");
		this.participantId = (long) time.get("participantId");

		this.role = (String) time.get("role");

		this.csDiffPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("csDiffPerMinDeltas"));

		this.goldPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("goldPerMinDeltas"));

		this.xpDiffPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("xpDiffPerMinDeltas"));

		this.creepsPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("creepsPerMinDeltas"));

		this.xpPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("xpPerMinDeltas"));

		this.damageTakenDiffPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("damageTakenDiffPerMinDeltas"));

		this.damageTakenPerMinDeltas = JSONObjectConversions.convertJSONObjectToMap((JSONObject) time.get("damageTakenPerMinDeltas"));
	}
}
