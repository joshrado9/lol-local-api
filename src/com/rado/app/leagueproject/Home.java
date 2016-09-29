package com.rado.app.leagueproject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

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
		ComboBox<String> comboBoxRegion = new ComboBox<String>(FXCollections.observableArrayList("br", "eune", "euw", "jp", "kr", "lan", "las", "na", "oce", "tr", "ru"));
		Map<String, String> regionMap = new HashMap<String, String>();

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
				getSummonerByName(name, region);
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

	private void getSummonerByName(String name, String region)
	{
		JSONObject jsonObject = RiotAPICalls.getSummonerByName(name, region);

		//parse summoner json object
		JSONObject object_type = null;
		try
		{
			object_type = jsonObject.getJSONObject(name);
			Summoner.id.set(object_type.getInt("id"));
			Summoner.name.set(object_type.getString("name"));
			Summoner.profileIconID.set(object_type.getInt("profileIconId"));
			Summoner.revisionDate.set(object_type.getLong("revisionDate"));
			Summoner.level.set(object_type.getInt("summonerLevel"));
		}
		catch (JSONException e)
		{
			System.out.println("Error: Settings, ");
			e.printStackTrace();
		}
	}
}