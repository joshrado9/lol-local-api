package com.rado.app.leagueproject;

import java.util.List;
import java.util.Map;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MatchListPage
{
	public Summoner summonerObject;
	public RecentGames matchListObject;

	public MatchListPage(Summoner summonerObject)
	{
		this.summonerObject = summonerObject;
		this.matchListObject = new RecentGames(RiotAPICalls.getRecentGames(summonerObject.getSummonerID(), summonerObject.getRegion()), summonerObject.getSummonerID());
	}

	public Tab initializeTab()
	{
		Map<String, Object> summonerMap = summonerObject.getSummoner();
		List<GameDto> recentGames = matchListObject.getMatchList();

		String tabName = String.format("%s's Match History", summonerMap.get("summonerName"));
		Tab newTab = new Tab( tabName );

		ScrollPane matchListPane = new ScrollPane();
		matchListPane.setFitToHeight(true);
		matchListPane.setFitToWidth(true);
		matchListPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		VBox matchListVBox = new VBox(5.0);
		matchListVBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(matchListVBox, Priority.ALWAYS);

		for (GameDto game : recentGames)
		{
			//Helper method call in GameDto
			HBox match = game.getMatchView();

			HBox.setHgrow(match, Priority.ALWAYS);
			matchListVBox.getChildren().add(match);
		}

		matchListPane.setContent(matchListVBox);
		newTab.setContent(matchListPane);

		return newTab;
	}
}
