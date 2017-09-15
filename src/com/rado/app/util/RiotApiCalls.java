package com.rado.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RiotApiCalls
{
	private CloseableHttpClient CLIENT = HttpClients.createDefault();
	//private static Map<String, String> regionMap;
	//my summonerId = 59021813

	private final String API_KEY = "RGAPI-dfbb6d09-0992-4b32-8896-e40a0db1d638";

	public RiotApiCalls()
	{
		/*regionMap = new HashMap<String, String>();
		regionMap.put("Brazil", "br");
		regionMap.put("Europe Nordic & East", "eune");
		regionMap.put("Europe West", "euw");
		regionMap.put("Latin America North", "lan");
		regionMap.put("Latin America South", "las");
		regionMap.put("North America", "na1");
		regionMap.put("Oceania", "oce");
		regionMap.put("Turkey", "tr");
		regionMap.put("Russia", "ru");
		regionMap.put("Japan", "jp");
		regionMap.put("Korea", "kr");*/
	}

	public JSONObject getSummonerByName(String name, String region)
	{
		try {
			name = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			System.err.println("ERROR: getSummonerByName encoding");
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		String regionID = "na1"; //getRegionID(region);
		String host = String.format("%s.api.riotgames.com", regionID);
		String path = String.format("/lol/summoner/v3/summoners/by-name/%s", name);
		return getJsonFromRiot(host, path);
	}

	public JSONObject getRecentGamesById(long summonerID, String region)
	{
		String regionID = "na1"; //getRegionID(region);
		String host = String.format("%s.api.riotgames.com", regionID);
		String path = String.format("/lol/match/v3/matchlists/by-account/%d/recent", summonerID);
		return getJsonFromRiot(host, path);
	}

	public JSONObject getSummonerById(long summonerId, String region)
	{
		String regionID = "na1"; //getRegionID(region)
		String host = String.format("%s.api.riotgames.com", regionID);
		String path = String.format("/lol/summoner/v3/summoners/%d", summonerId);
		return getJsonFromRiot(host, path);
	}

	public JSONObject getMatchByMatchId(long matchId, String region)
	{
		String regionID = "na1"; //getRegionID(region)
		String host = String.format("%s.api.riotgames.com", regionID);
		String path = String.format("/lol/match/v3/matches/%d", matchId);
		return getJsonFromRiot(host, path);
	}

	public JSONObject getMasteryPagesBySummonerId(long summonerId, String region)
	{
		String regionId = "na1";
		String host = String.format("%s.api.riotgames.com", regionId);
		String path = String.format("/lol/platform/v3/masteries/by-summoner/%d", summonerId);
		return getJsonFromRiot(host, path);
	}

	public JSONObject getRunePagesBySummonerId(long summonerId, String region)
	{
		String regionId = "na1";
		String host = String.format("%s.api.riotgames.com", regionId);
		String path = String.format("/lol/platform/v3/runes/by-summoner/%d", summonerId);
		return getJsonFromRiot(host, path);
	}

	public long getChampionMasteryScoreBySummonerId(long summonerId, String region)
	{
		String regionId = "na1";
		String host = String.format("%s.api.riotgames.com", regionId);
		String path = String.format("/lol/champion-mastery/v3/scores/by-summoner/%d", summonerId);
		return getLongFromRiot(host, path);
	}

	public JSONArray getLeagueListBySummonerId(long summonerId, String region)
	{
		String regionId = "na1";
		String host = String.format("%s.api.riotgames.com", regionId);
		String path = String.format("/lol/league/v3/leagues/by-summoner/%d", summonerId);
		return getJsonArrayFromRiot(host, path);
	}

	private long getLongFromRiot(String host, String path)
	{
		String content = getStringFromRiot(host, path);

		JSONParser parser = new JSONParser();
		long score = 0;
		try {
			score = (long) parser.parse(content);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return score;
	}

	private JSONObject getJsonFromRiot(String host, String path)
	{
		String content = getStringFromRiot(host, path);

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(content);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	private JSONArray getJsonArrayFromRiot(String host, String path)
	{
		String content = getStringFromRiot(host, path);

		JSONParser parser = new JSONParser();
		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) parser.parse(content);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonArray;
	}

	private String getStringFromRiot(String host, String path)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_key", API_KEY));

		String content = null;

		try
		{
			URI uri = new URIBuilder().setScheme("https").setHost(host).setPath(path).setParameters(params).build();

			HttpGet get = new HttpGet(uri);
			CloseableHttpResponse response = CLIENT.execute(get);

			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);

			//need to consume the HTTP entity
			InputStream in = entity.getContent();
			if (in != null)
			{
				in.close();
			}
			response.close();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return content;
	}

	/*public static String getRegionID(String region)
	{
		String regionID = regionMap.get(region);
		return regionID;
	}

	public static List<String> getRegions()
	{
		return new ArrayList<String>(regionMap.keySet());
	}*/
}
