package com.rado.app.leagueproject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class RawStatsDto
{
	private Map<String, Object> rawStatsDtoMap;

	/*
	private void putInMapInt(JSONObject stats, String key)
	{
		int temp = 0;
		try
		{
			temp = stats.getInt(key);
		}
		catch (JSONException e) {}

		rawStatsDtoMap.put(key, temp);
	}

	private void putInMapBool(JSONObject stats, String key)
	{
		boolean temp = false;
		try
		{
			temp = stats.getBoolean(key);
		}
		catch (JSONException e) {}

		rawStatsDtoMap.put(key, temp);
	}
	 */


	public RawStatsDto(JSONObject stats)
	{
		rawStatsDtoMap = new HashMap<String, Object>();

		Iterator<?> keyList = stats.keys();

		while(keyList.hasNext())
		{
			String key = (String) keyList.next();
			try {
				rawStatsDtoMap.put(key, stats.get(key));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		/*
		putInMapInt(stats, "assists");
		putInMapInt(stats, "barracksKilled");
		putInMapInt(stats, "bountyLevel");
		putInMapInt(stats, "championsKilled");
		putInMapInt(stats, "combatPlayerScore");
		putInMapInt(stats, "consumablesPurchased");
		putInMapInt(stats, "damageDealtPlayer");
		putInMapInt(stats, "doubleKills");
		putInMapInt(stats, "firstBlood");
		putInMapInt(stats, "gold");
		putInMapInt(stats, "goldEarned");
		putInMapInt(stats, "goldSpent");
		putInMapInt(stats, "item0");
		putInMapInt(stats, "item1");
		putInMapInt(stats, "item2");
		putInMapInt(stats, "item3");
		putInMapInt(stats, "item4");
		putInMapInt(stats, "item5");
		putInMapInt(stats, "item6");
		putInMapInt(stats, "itemsPurchased");
		putInMapInt(stats, "killingSprees");
		putInMapInt(stats, "largestCriticalStrike");
		putInMapInt(stats, "largetsKillingSpree");
		putInMapInt(stats, "largestMultiKill");
		putInMapInt(stats, "legendaryItemsCreated");
		putInMapInt(stats, "level");
		putInMapInt(stats, "magicDamageDealtPlayer");
		putInMapInt(stats, "magicDamageDealtToChampions");
		putInMapInt(stats, "magicDamageTaken");
		putInMapInt(stats, "minionsDenied");
		putInMapInt(stats, "minionsKilled");
		putInMapInt(stats, "neutralMinionsKilled");
		putInMapInt(stats, "neutralMinionsKilledEnemyJungle");
		putInMapInt(stats, "neutralMinionsKilledYourJungle");
		putInMapBool(stats, "nexusKilled");
		putInMapInt(stats, "nodeCapture");
		putInMapInt(stats, "nodeCaptureAssist");
		putInMapInt(stats, "nodeNeutralize");
		putInMapInt(stats, "nodeNeutralizeAssist");
		putInMapInt(stats, "numDeaths");
		putInMapInt(stats, "numItemsBought");
		putInMapInt(stats, "objectivePlayerScore");
		putInMapInt(stats, "pentaKills");
		putInMapInt(stats, "physicalDamageDealtPlayer");
		putInMapInt(stats, "physicalDamageDealtToChampions");
		putInMapInt(stats, "physicalDamageTaken");
		putInMapInt(stats, "playerPosition");
		putInMapInt(stats, "playerRole");
		putInMapInt(stats, "playerScore0");
		putInMapInt(stats, "playerScore1");
		putInMapInt(stats, "playerScore2");
		putInMapInt(stats, "playerScore3");
		putInMapInt(stats, "playerScore4");
		putInMapInt(stats, "playerScore5");
		putInMapInt(stats, "playerScore6");
		putInMapInt(stats, "playerScore7");
		putInMapInt(stats, "playerScore8");
		putInMapInt(stats, "playerScore9");
		putInMapInt(stats, "quadraKills");
		putInMapInt(stats, "sightWardsBought");
		putInMapInt(stats, "spell1Cast");
		putInMapInt(stats, "spell2Cast");
		putInMapInt(stats, "spell3Cast");
		putInMapInt(stats, "spell4Cast");
		putInMapInt(stats, "summonSpell1Cast");
		putInMapInt(stats, "summonSpell2Cast");
		putInMapInt(stats, "superMonsterKilled");
		putInMapInt(stats, "team");
		putInMapInt(stats, "teamObjective");
		putInMapInt(stats, "timePlayed");
		putInMapInt(stats, "totalDamageDealt");
		putInMapInt(stats, "totalDamageDealtToChampions");
		putInMapInt(stats, "totalDamageTaken");
		putInMapInt(stats, "totalHeal");
		putInMapInt(stats, "totalPlayerScore");
		putInMapInt(stats, "totalScoreRank");
		putInMapInt(stats, "totalTimeCrowdControlDealt");
		putInMapInt(stats, "totalUnitsHealed");
		putInMapInt(stats, "tripleKills");
		putInMapInt(stats, "trueDamageDealtPlayer");
		putInMapInt(stats, "trueDamageDealtToChampions");
		putInMapInt(stats, "trueDamageTaken");
		putInMapInt(stats, "turretsKilled");
		putInMapInt(stats, "unrealKills");
		putInMapInt(stats, "victoryPointTotal");
		putInMapInt(stats, "visionWardsBought");
		putInMapInt(stats, "wardKilled");
		putInMapInt(stats, "wardPlaced");
		putInMapBool(stats, "win");
		*/
	}

	public Map<String, Object> getRawStatsDto()
	{
		return this.rawStatsDtoMap;
	}
}
