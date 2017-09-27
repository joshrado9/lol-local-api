package com.rado.app.main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.simple.JSONObject;

public class Server implements Runnable
{
	private Database database;
	private Api api;
	private Main main;
	private BlockingQueue<JSONObject> serverQueue;

	public static enum ServerQueueIds {
		INVALID_MSG_ID,
		REQ_MSG_ID,
		RECV_MSG_ID
	}

	public Server(Main main)
	{
		this.database = new Database(this);
		this.api = new Api(this);
		this.main = main;
		this.serverQueue = new LinkedBlockingQueue<JSONObject>();
	}

	@Override
	public void run()
	{
		this.database.run();
		this.api.run();

		while(true)
		{
			JSONObject msg = null;
			try {
				msg = serverQueue.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (msg != null)
			{
				ServerQueueIds msgId = ServerQueueIds.INVALID_MSG_ID;
				try {
					msgId = (ServerQueueIds) msg.get("msgId");
				} catch (Exception e) {
					System.err.println("Malformed msg attempted to put on mainQueue");
					e.printStackTrace();
				}

				switch (msgId)
				{
					case REQ_MSG_ID:
						//check if the value is in the database first
						putMsgOnDatabaseQueue(msg);
						break;
					case RECV_MSG_ID:
						//process database response, determine if valid
						processDatabaseReceive(msg);
						break;
					default:
						System.err.println("Invalid ServerQueueId");
						break;
				}
			}
		}
	}

	public void putMsgOnServerQueue(JSONObject msg)
	{
		try {
			this.serverQueue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void putMsgOnDatabaseQueue(JSONObject msg)
	{
		JSONObject data = (JSONObject) msg.get("data");
		this.database.putMsgOnQueue(data);
	}

	private void putMsgOnMainQueue(JSONObject msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgId", Main.MsgQueueIds.SERVER_RESPONSE_MSG_ID);
		map.put("data", msg);
		JSONObject json = new JSONObject(map);
		this.main.putMsgOnMainQueue(json);
	}

	void processDatabaseReceive(JSONObject msg)
	{

	}
}
