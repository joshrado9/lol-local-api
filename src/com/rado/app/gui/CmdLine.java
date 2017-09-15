package com.rado.app.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

import com.rado.app.main.Database;
import com.rado.app.main.Gui;

public class CmdLine implements Runnable
{
	private Gui gui;

	public CmdLine(Gui gui)
	{
		this.gui = gui;
		System.out.println("Command Line initialized");
	}

	@Override
	public void run()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter command:");
		while (true)
		{
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (s != null)
			{
				StringTokenizer st = new StringTokenizer(s);

				int cmdId = 0;
				try {
					cmdId = Integer.parseInt(st.nextToken());
				} catch (NumberFormatException e) {
					System.err.println("No msgId in JSON");
					e.printStackTrace();
				}

				int retCode = -1;
				switch (cmdId)
				{
					case 1:
					{
						retCode = processCmdLineGetSummonerProfileByName(st);
						break;
					}
					default:
						System.out.println("Invalid Command");
				}

				if (retCode == -1)
				{
					System.err.println("Error with request");
				}
			}
		}
	}

	private int processCmdLineGetSummonerProfileByName(StringTokenizer st)
	{
		String name;
		try {
			name = st.nextToken();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		Map<String, Object> profObj = new HashMap<String, Object>();
		profObj.put("msgId", Database.DatabaseQueueIds.GET_PROFILE_BY_NAME_MSG_ID);
		profObj.put("name", name);
		JSONObject jsonObj = new JSONObject(profObj);
		return this.gui.putMsgOnGuiQueue(jsonObj);
	}
}
