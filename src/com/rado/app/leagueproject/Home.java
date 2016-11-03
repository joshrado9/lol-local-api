package com.rado.app.leagueproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Home
{
	public Tab initializeTab()
	{
		Tab settingsTab = new Tab("Home");
		VBox settingsVBox = new VBox(5.0);
		TextField textFieldSummonerName = new TextField();
		ComboBox<String> comboBoxRegion = new ComboBox<String>(FXCollections.observableArrayList(RiotAPICalls.getRegions()));

		Label summonerLabel = new Label("Summoner Search");
		Label regionLabel = new Label("Select Region");
		Button buttonApply = new Button("Apply");
		buttonApply.setOnAction(event ->
		{
			String name = textFieldSummonerName.getText();
			String region = comboBoxRegion.getSelectionModel().getSelectedItem();
			if (validateName(name))
			{
				//valid name
				SummonerPage sp = new SummonerPage(name, region);
				Tab summonerTab = sp.initializeTab();
				summonerTab.setClosable(true);
				Main.mainTabPane.getTabs().add(summonerTab);
				Main.mainTabPane.getSelectionModel().select(summonerTab);
				//!highlight text field

			}
			else
			{
				//not a valid name - display pop-up and highlight text field red

			}
		});
		settingsVBox.getChildren().addAll(summonerLabel, textFieldSummonerName, regionLabel, comboBoxRegion, buttonApply);

		settingsTab.setContent(settingsVBox);
		return settingsTab;
	}

	private boolean validateName(String name)
	{
		Pattern p = Pattern.compile("^[0-9\\p{L} _\\.]+$");
		Matcher m = p.matcher(name);
		return m.matches();
	}
}