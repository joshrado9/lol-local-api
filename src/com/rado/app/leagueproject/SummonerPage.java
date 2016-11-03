package com.rado.app.leagueproject;

import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class SummonerPage
{
	private Summoner summonerObject;

	public SummonerPage(String name, String region)
	{
		this.summonerObject = new Summoner(RiotAPICalls.getSummonerByName(name, region), name, region);
	}

	public Tab initializeTab()
	{
		Map<String, Object> summonerMap = summonerObject.getSummoner();

		Tab newTab = new Tab( (String) summonerMap.get("summonerName") );

		VBox summonerBox = new VBox(5.0);

		Label nameLabel = new Label( (String) summonerMap.get("summonerName") );
		Label iconIDLabel = new Label( String.format("%d", summonerMap.get("summonerIconID")) );
		Label levelLabel = new Label( String.format("%d", summonerMap.get("summonerLevel")) );

		Button matchHistory = new Button("View Match History");
		matchHistory.setOnAction(event ->
		{
			MatchListPage mlp = new MatchListPage(summonerObject);
			Tab matchListTab = mlp.initializeTab();
			matchListTab.setClosable(true);
			Main.mainTabPane.getTabs().add(matchListTab);
			Main.mainTabPane.getSelectionModel().select(matchListTab);
		});

		summonerBox.getChildren().addAll(nameLabel, iconIDLabel, levelLabel, matchHistory);
		newTab.setContent(summonerBox);

		return newTab;
	}
}
