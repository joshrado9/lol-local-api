package com.rado.app.leagueproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RiotAPIStatic
{
	private static CloseableHttpClient CLIENT = HttpClients.createDefault();
	public static Map<Integer, ChampionDto> championList;

	private static void initChampionList()
	{
		String path = String.format("/api/lol/static-data/na/v1.2/champion");
		String host = String.format("global.api.pvp.net");
		JSONObject champListJSON = generalCallToRiot(host, path);

		championList = new HashMap<Integer, ChampionDto>();
		try
		{
			JSONObject champListObject = champListJSON.getJSONObject("data");

			Iterator<?> champListIterable = champListObject.keys();

			while(champListIterable.hasNext())
			{
				String key = (String) champListIterable.next();
				ChampionDto champ = new ChampionDto(champListObject.getJSONObject(key));
				championList.put(champ.id, champ);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	private static JSONObject generalCallToRiot(String host, String path)
	{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_key", "RGAPI-4CF5BFBA-660A-4F8D-8BBE-CD10CBEB91F7"));
		String content = null;

		try
		{
			URI uri = new URIBuilder().setScheme("https").setHost(host).setPath(path).setParameters(params).build();

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
			return null;
		}

		return jsonObject;
	}

	public static void initialize()
	{
		initChampionList();
	}
}
