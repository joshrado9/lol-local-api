package com.rado.app.util;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.scene.image.Image;

public class RiotDataDragon
{
	public static String currentVersion = "7.16.1";
	public static String language = "en_US";

	public static void initialize()
	{

	}

	public static JSONObject getChampNameFromDataDragon(long id)
	{
		String filePath = String.format("%s/dragontail-%s/%s/data/%s/champion.json", System.getProperty("user.dir"), currentVersion, currentVersion, language);

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject data = (JSONObject) jsonObject.get("data");
			Iterator<?> keys = data.keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				JSONObject champObject = (JSONObject) data.get(key);
				int champId = Integer.parseInt((String) champObject.get("key"));
				if (id == champId)
					return champObject;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Image getChampImageFromDataDragon(String fileName)
	{
		String filePath = String.format("%s/dragontail-%s/%s/img/champion/%s", System.getProperty("user.dir"), currentVersion, currentVersion, fileName);
		File file = new File(filePath);
		return new Image(file.toURI().toString(), 75, 75, true, false);
	}

	public static JSONObject getItemFromDataDragon(long id)
	{
		String filePath = String.format("%s/dragontail-%s/%s/data/%s/item.json", System.getProperty("user.dir"), currentVersion, currentVersion, language);

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject data = (JSONObject) jsonObject.get("data");
			Iterator<?> keys = data.keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				if (Long.parseLong(key) == id)
					return (JSONObject) data.get(key);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JSONObject getMasteryFromDataDragon(long id)
	{
		String filePath = String.format("%s/dragontail-%s/%s/data/%s/mastery.json", System.getProperty("user.dir"), currentVersion, currentVersion, language);

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject data = (JSONObject) jsonObject.get("data");
			Iterator<?> keys = data.keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				if (Long.parseLong(key) == id)
					return (JSONObject) data.get(key);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JSONObject getRuneFromDataDragon(long runeId)
	{
		String filePath = String.format("%s/dragontail-%s/%s/data/%s/rune.json", System.getProperty("user.dir"), currentVersion, currentVersion, language);

		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(filePath));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject data = (JSONObject) jsonObject.get("data");
			Iterator<?> keys = data.keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				if (Long.parseLong(key) == runeId)
					return (JSONObject) data.get(key);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

	public static Image getMasteryImageFromDataDragon(String fileName)
	{
		String filePath = String.format("%s/dragontail-%s/%s/img/mastery/%s", System.getProperty("user.dir"), currentVersion, currentVersion, fileName);
		File file = new File(filePath);
		return new Image(file.toURI().toString());
	}

	public static Image getItemImageFromDataDragon(String fileName)
	{
		String filePath = String.format("%s/dragontail-%s/%s/img/item/%s", System.getProperty("user.dir"), currentVersion, currentVersion, fileName);
		File file = new File(filePath);
		return new Image(file.toURI().toString(), 50, 50, true, false);
	}

	public static Image getRuneImageFromDataDragon(String fileName)
	{
		String filePath = String.format("%s/dragontail-%s/%s/img/rune/%s", System.getProperty("user.dir"), currentVersion, currentVersion, fileName);
		File file = new File(filePath);
		return new Image(file.toURI().toString(), 50, 50, true, false);
	}

	public static Image getProfileIconFromDataDragon(long iconId)
	{
		String filePath = String.format("%s/dragontail-%s/%s/img/profileicon/%d.png", System.getProperty("user.dir"), currentVersion, currentVersion, iconId);
		File file = new File(filePath);
		return new Image(file.toURI().toString(), 75, 75, true, false);
	}
}
