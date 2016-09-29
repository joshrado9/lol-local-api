package com.rado.app.leagueproject;

import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class SummonerPage
{
	private Summoner summonerObject;

	public SummonerPage(String name, String region)
	{
		this.summonerObject = RiotAPICalls.getSummonerByName(name, region);
	}

	public Tab initializeTab()
	{
		Map<String, Object> summonerMap = summonerObject.getSummoner();

		Tab newTab = new Tab( (String) summonerMap.get("summonerName") );
		if (this.summonerObject != null)
		{
			VBox summonerBox = new VBox(5.0);

			Label nameLabel = new Label( (String) summonerMap.get("summonerName") );
			Label iconIDLabel = new Label( String.format("%d", summonerMap.get("summonerIconID")) );
			Label levelLabel = new Label( String.format("%d", summonerMap.get("summonerLevel")) );

			summonerBox.getChildren().addAll(nameLabel, iconIDLabel, levelLabel);
			newTab.setContent(summonerBox);
		}

		return newTab;
	}
}
