package com.rado.app.main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.simple.JSONObject;

import com.rado.app.gui.CmdLine;

public class Gui implements Runnable
{
	private Main main;
	private BlockingQueue<JSONObject> guiQueue;
	private CmdLine cmdLine;

	public static enum GuiQueueIds {
		INVALID_MSG_ID,
		CMD_LINE_REQ_MSG_ID
	}

	public Gui(Main main)
	{
		this.main = main;
		this.guiQueue = new LinkedBlockingQueue<JSONObject>();
		this.cmdLine = new CmdLine(this);
	}

	@Override
	public void run()
	{
		while (true)
		{
			JSONObject msg = null;
			try {
				msg = guiQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (msg != null)
			{
				GuiQueueIds msgId = GuiQueueIds.INVALID_MSG_ID;
				try {
					msgId = (GuiQueueIds) msg.get("msgId");
				} catch (Exception e) {
					System.err.println("No msgId in JSON");
					e.printStackTrace();
				}

				switch (msgId)
				{
					case CMD_LINE_REQ_MSG_ID:
						wrapCmdLineRequest(msg);
						break;
					default:
						System.err.println("Invalid GuiQueueId");
				}
			}
		}
	}

	private void wrapCmdLineRequest(JSONObject msg)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgId", Main.MsgQueueIds.GUI_REQUEST_MSG_ID);
		map.put("data", msg);
		JSONObject json = new JSONObject(map);
		this.main.putMsgOnMainQueue(json);
	}

	public int putMsgOnGuiQueue(JSONObject	msg)
	{
		try {
			this.guiQueue.put(msg);
			return 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
