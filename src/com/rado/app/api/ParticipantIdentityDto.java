package com.rado.app.api;

import org.json.simple.JSONObject;

public class ParticipantIdentityDto
{
	public PlayerDto player;
	public long participantId;

	public ParticipantIdentityDto() { }

	public ParticipantIdentityDto(JSONObject participant)
	{
		//if non-ranked game, this information won't be there
		this.player = new PlayerDto((JSONObject) participant.get("player"));

		this.participantId = (long) participant.get("participantId");
	}
}
