package com.rado.app.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.simple.JSONObject;

import com.rado.app.main.Gui.GuiQueueIds;
import com.rado.app.util.Firebase;

public class Database implements Runnable
{
	private Server server;
	private BlockingQueue<JSONObject> databaseQueue;
	private Firebase firebase;

	public static enum DatabaseQueueIds {
		INVALID_MSG_ID,
		GET_PROFILE_BY_NAME_MSG_ID,
		RECV_PROFILE_MSG_ID
	}

	public Database(Server server)
	{
		this.server = server;
		this.databaseQueue = new LinkedBlockingQueue<JSONObject>();
		//initialize conncection to Firebase
		this.firebase = new Firebase(this);
	}

	@Override
	public void run()
	{
		while(true)
		{
			JSONObject msg = null;
			try {
				msg = databaseQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (msg != null)
			{
				DatabaseQueueIds msgId = DatabaseQueueIds.INVALID_MSG_ID;
				try {
					msgId = (DatabaseQueueIds) msg.get("msgId");
				} catch (Exception e) {
					System.err.println("No msgId in JSON");
					e.printStackTrace();
				}

				switch (msgId)
				{
					case GET_PROFILE_BY_NAME_MSG_ID:
						processGetProfileByName(msg);
						break;
					case RECV_PROFILE_MSG_ID:
						processRecvProfile(msg);
						break;
					default:
						System.err.println("Invalid GuiQueueId");
				}
			}
		}
	}

	private void processRecvProfile(JSONObject msg)
	{
		this.server.putMsgOnServerQueue(msg);
	}

	public void putMsgOnQueue(JSONObject json)
	{
		try {
			this.databaseQueue.put(json);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void processGetProfileByName(JSONObject json)
	{
		String name = (String) json.get("name");
		String region = (String) json.get("region");
		this.firebase.checkFirebaseSummonerByName(name, region);
	}

	public void recvSummonerByName(JSONObject value)
	{
		//send to server
		this.server.putMsgOnServerQueue(value);
	}
}
