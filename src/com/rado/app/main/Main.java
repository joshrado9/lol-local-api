package com.rado.app.main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.simple.JSONObject;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

public class Main extends Application
{
	private Gui gui;
	private Server server;
	private BlockingQueue<JSONObject> mainQueue;

	private BooleanProperty testFlag = new SimpleBooleanProperty(false);

	public static enum MsgQueueIds {
		INVALID_MSG_ID,
		GUI_REQUEST_MSG_ID,
		SERVER_RESPONSE_MSG_ID
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage arg0)
	{
		Map<String, String> params = getParameters().getNamed();
		testFlag.set(params.containsKey("testFlag") ? Boolean.parseBoolean(params.get("testFlag")) : false);

		mainQueue = new LinkedBlockingQueue<JSONObject>();

		gui = new Gui(this);
		server = new Server(this);

		gui.run();
		server.run();

		while (true)
		{
			JSONObject msg = null;
			try {
				msg = mainQueue.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (msg != null)
			{
				MsgQueueIds msgId = MsgQueueIds.INVALID_MSG_ID;
				try {
					msgId = (MsgQueueIds) msg.get("msgId");
				} catch (Exception e) {
					System.err.println("No msgId in JSON");
					e.printStackTrace();
				}

				switch (msgId)
				{
					case GUI_REQUEST_MSG_ID:
						wrapGuiDataRequest(msg);
						break;
					default:
						System.err.println("Invalid MsgQueueId");
				}
			}
		}
	}

	/**
	 * Method sends a request from the Main queue to the Server queue
	 * @param msg
	 */
	private void wrapGuiDataRequest(JSONObject msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject data = (JSONObject) msg.get("data");
		map.put("data", data);
		map.put("msgId", Server.ServerQueueIds.REQ_MSG_ID);

		JSONObject req = new JSONObject(map);
		server.putMsgOnServerQueue(req);
	}

	public void putMsgOnMainQueue(JSONObject msg)
	{
		try {
			mainQueue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
