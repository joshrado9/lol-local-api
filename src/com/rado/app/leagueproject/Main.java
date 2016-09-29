package com.rado.app.leagueproject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application
{
	//"https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/joshrado?api_key=RGAPI-4CF5BFBA-660A-4F8D-8BBE-CD10CBEB91F7"

	private Home home = new Home();

	private Tab homeTab = new Tab();
	public static TabPane mainTabPane = new TabPane();

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//make a display.
		mainTabPane = new TabPane();

		homeTab = home.initializeTab();
		homeTab.setClosable(false);

		mainTabPane.getTabs().addAll(homeTab);
		mainTabPane.getSelectionModel().select(0);

		Scene scene = new Scene(mainTabPane);
		primaryStage.setTitle("LOL Project");
		primaryStage.setScene(scene);

		ChangeListener<Number> widthListener = new ChangeListener<Number> ()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldSceneWidth, Number newSceneWidth) { }
		};
		scene.widthProperty().addListener(widthListener);

		ChangeListener<Number> heightListener = new ChangeListener<Number> ()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldSceneHeight, Number newSceneHeight) { }
		};
		scene.widthProperty().addListener(heightListener);

		primaryStage.show();
	}
}
