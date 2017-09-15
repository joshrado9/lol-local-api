package com.rado.app.api;

public class ChampionMasteryScoreDto
{
	public long score;

	public ChampionMasteryScoreDto() { }

	public ChampionMasteryScoreDto(long masteryScore)
	{
		this.score = masteryScore;
	}

	public void setScore(ChampionMasteryScoreDto value)
	{
		this.score = value.score;
	}
}
