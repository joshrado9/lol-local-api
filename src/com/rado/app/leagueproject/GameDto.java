package com.rado.app.leagueproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameDto
{
	private Map<String, Object> gameDtoMap;

	public GameDto(JSONObject game)
	{
		this.gameDtoMap = new HashMap<String, Object>();

		try
		{
			gameDtoMap.put("championID", game.getInt("championId"));
			gameDtoMap.put("createDate", game.getLong("createDate"));
			gameDtoMap.put("gameID", game.getLong("gameId"));
			gameDtoMap.put("gameMode", game.getString("gameMode"));
			gameDtoMap.put("gameType", game.getString("gameType"));
			gameDtoMap.put("invalid", game.getBoolean("invalid"));
			gameDtoMap.put("ipEarned", game.getInt("ipEarned"));
			gameDtoMap.put("level", game.getInt("level"));
			gameDtoMap.put("mapId", game.getInt("mapId"));
			gameDtoMap.put("spell1", game.getInt("spell1"));
			gameDtoMap.put("spell2", game.getInt("spell2"));
			gameDtoMap.put("subType", game.getString("subType"));
			gameDtoMap.put("teamID", game.getInt("teamId"));

			JSONArray fellowPlayerList = game.getJSONArray("fellowPlayers");
			List<Map<String, Object>> fellowPlayers = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < fellowPlayerList.length(); i ++)
			{
				fellowPlayers.add(new PlayerDto(fellowPlayerList.getJSONObject(i)).getPlayerDto());
			}
			gameDtoMap.put("fellowPlayers", fellowPlayers);

			gameDtoMap.put("stats", new RawStatsDto(game.getJSONObject("stats")).getRawStatsDto());
		}
		catch (JSONException e)
		{
			System.out.println("Error: invalid GameDto JSON Object...");
			e.printStackTrace();
		}
	}

	public Map<String, Object> getGameDto()
	{
		return this.gameDtoMap;
	}

	@SuppressWarnings("unchecked")
	public HBox getMatchView()
	{
		HBox match = new HBox(5.0);
		match.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		List<Map<String, Object>> fellowPlayers = (List<Map<String, Object>>) gameDtoMap.get("fellowPlayers");
		Map<String, Object> gameRawStats = (Map<String, Object>) gameDtoMap.get("stats");

		VBox playersRed = new VBox(5.0);
		VBox playersBlue = new VBox(5.0);
		for (Map<String, Object> fellowPlayer : fellowPlayers)
		{
			HBox player = new HBox(5.0);

			String champName = RiotAPIStatic.championList.get(fellowPlayer.get("championID")).name;
			Label labelPlayerChamp = new Label( champName );
			Label labelPlayerID = new Label(fellowPlayer.get("summonerID").toString());

			player.getChildren().addAll(labelPlayerChamp, labelPlayerID);
			if ((int)fellowPlayer.get("teamID") == 100)
			{
				playersRed.getChildren().add(player);
			}
			else
			{
				playersBlue.getChildren().add(player);
			}
		}

		HBox myself = new HBox(5.0);

		String champName = RiotAPIStatic.championList.get(gameDtoMap.get("championID")).name;
		Label labelPlayerChamp = new Label( champName );
		Label labelPlayerID = new Label("Myself");
		myself.getChildren().addAll(labelPlayerChamp, labelPlayerID);

		if ((int) gameDtoMap.get("teamID") == 100)
		{
			playersRed.getChildren().add(myself);
		}
		else
		{
			playersBlue.getChildren().add(myself);
		}

		match.getChildren().addAll(playersRed, playersBlue);

		if ((boolean)gameRawStats.get("win"))
		{
			match.setStyle("-fx-background-color: lightblue;");
		}
		else
		{
			match.setStyle("-fx-background-color: lightpink;");
		}

		return match;
	}


}
