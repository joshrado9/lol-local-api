package com.rado.app.leagueproject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Summoner
{
	public static IntegerProperty id = new SimpleIntegerProperty(-1);
	public static StringProperty name = new SimpleStringProperty("");
	public static IntegerProperty profileIconID = new SimpleIntegerProperty(-1);;
	public static LongProperty revisionDate = new SimpleLongProperty(-1);
	public static IntegerProperty level = new SimpleIntegerProperty(-1);


	public static ListProperty<String> getObjects()
	{
		return new SimpleListProperty<String>(FXCollections.observableArrayList("id", "name", "iconID", "revisionDate", "level"));
	}
}
