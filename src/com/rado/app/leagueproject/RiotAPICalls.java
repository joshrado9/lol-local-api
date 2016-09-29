package com.rado.app.leagueproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.json.JSONException;
import org.json.JSONObject;

public class RiotAPICalls
{
	private static CloseableHttpClient CLIENT = HttpClients.createDefault();

	public static JSONObject getSummonerByName(String name, String region)
	{
		String content = null;
		try
		{
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("api_key", "RGAPI-4CF5BFBA-660A-4F8D-8BBE-CD10CBEB91F7"));

			URI uri = new URIBuilder().setScheme("https").setHost(region + ".api.pvp.net")
					.setPath("/api/lol/" + region + "/v1.4/summoner/by-name/" + name).setParameters(params).build();

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

		return jsonObject;
	}
}
