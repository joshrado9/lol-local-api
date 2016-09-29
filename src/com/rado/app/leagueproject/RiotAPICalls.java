package com.rado.app.leagueproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RiotAPICalls
{
	private static CloseableHttpClient CLIENT = HttpClients.createDefault();
	private static Map<String, String> regionMap;

	public static void initialize()
	{
		regionMap = new HashMap<String, String>();
		regionMap.put("Brazil", "br");
		regionMap.put("Europe Nordic & East", "eune");
		regionMap.put("Europe West", "euw");
		regionMap.put("Latin America North", "lan");
		regionMap.put("Latin America South", "las");
		regionMap.put("North America", "na");
		regionMap.put("Oceania", "oce");
		regionMap.put("Turkey", "tr");
		regionMap.put("Russia", "ru");
		regionMap.put("Japan", "jp");
		regionMap.put("Korea", "kr");
	}

	public static Summoner getSummonerByName(String name, String region)
	{
		String regionID = getRegionID(region);
		String content = null;
		try
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("api_key", "RGAPI-4CF5BFBA-660A-4F8D-8BBE-CD10CBEB91F7"));

			URI uri = new URIBuilder().setScheme("https").setHost(regionID + ".api.pvp.net")
					.setPath("/api/lol/" + regionID + "/v1.4/summoner/by-name/" + name).setParameters(params).build();

			HttpGet get = new HttpGet(uri);
			CloseableHttpResponse response = CLIENT.execute(get);

			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);

			//need to consume the http entity
			InputStream in = entity.getContent();
			if (in != null)
			{
				in.close();
			}
			response.close();
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jsonObject = null;
		try
		{
			jsonObject = new JSONObject(content);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		//parse summoner json object
		JSONObject object_type = null;
		try
		{
			object_type = jsonObject.getJSONObject(name);
			int id = object_type.getInt("id");
			String sName = object_type.getString("name");
			int iconID = object_type.getInt("profileIconId");
			long revDate = object_type.getLong("revisionDate");
			int sLevel = object_type.getInt("summonerLevel");
			return new Summoner(id, sName, iconID, revDate, sLevel);
		}
		catch (JSONException e)
		{
			System.out.println("Error: Settings, ");
			e.printStackTrace();
			return null;
		}
	}

	public static String getRegionID(String region)
	{
		String regionID = regionMap.get(region);
		return regionID;
	}

	public static List<String> getRegions()
	{
		return new ArrayList<String>(regionMap.keySet());
	}
}
