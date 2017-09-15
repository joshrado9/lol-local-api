package com.rado.app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.json.simple.JSONObject;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.Task;
import com.rado.app.api.ChampionMasteryScoreDto;
import com.rado.app.api.LeagueListDtoWrapper;
import com.rado.app.api.MasteryPagesDto;
import com.rado.app.api.MatchDto;
import com.rado.app.api.MatchlistDto;
import com.rado.app.api.RunePagesDto;
import com.rado.app.api.SummonerDto;
import com.rado.app.main.Database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Firebase
{
	private FirebaseDatabase ref;
	private Database database;

	public Firebase(Database database)
	{
		this.database = database;
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("leaguecache-87f17-firebase-adminsdk-6rymt-9aa9f636f0.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (serviceAccount != null)
		{
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
			  .setDatabaseUrl("https://leaguecache-87f17.firebaseio.com/")
			  .build();

			FirebaseApp.initializeApp(options);
			ref = FirebaseDatabase.getInstance();
		}
	}

	public void checkFirebaseSummonerByName(String name, String region)
	{
		DatabaseReference summonerRef = ref.getReference("Summoner/" + name + "/Profile");
		summonerRef.addListenerForSingleValueEvent(new ValueEventListener()
		{
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object snapshot = dataSnapshot.getValue();

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("msgId", Database.DatabaseQueueIds.RECV_PROFILE_MSG_ID);
				map.put("data", snapshot);
				JSONObject json = new JSONObject(map);
				database.recvSummonerByName(json);
			}
		});
	}

	public void saveSummoner(SummonerDto summoner) throws InterruptedException
	{
		DatabaseReference summonerRef = ref.getReference("Summoner/" + summoner.name + "/Profile");
		final CountDownLatch sync = new CountDownLatch(1);
		summonerRef.setValue(summoner).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> task) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public MatchlistDto checkFirebaseRecentGames(String name, String region) throws InterruptedException
	{
		DatabaseReference summonerRef = ref.getReference("Summoner/" + name + "/RecentMatchList");
		final CountDownLatch sync = new CountDownLatch(1);
		final MatchlistDto retVal = new MatchlistDto();
		summonerRef.addListenerForSingleValueEvent(new ValueEventListener()
		{
			@Override
			public void onCancelled(DatabaseError databaseError) {
				sync.countDown();
			}

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() != null)
					retVal.setMatchlistDto(dataSnapshot.getValue(MatchlistDto.class));
				sync.countDown();
			}
		});
		sync.await();
		return retVal;
	}

	public void saveRecentGames(String name, MatchlistDto matchListObject) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/RecentMatchList");
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(matchListObject).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public MatchDto checkMatch(long gameId) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Match/" + gameId);
		final MatchDto retVal = new MatchDto();
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onCancelled(DatabaseError arg0) {
				sync.countDown();
			}

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() != null)
					retVal.setMatch(dataSnapshot.getValue(MatchDto.class));
				sync.countDown();
			}
		});
		sync.await();
		return retVal;
	}

	public void saveMatch(MatchDto match) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Match/" + match.gameId);
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(match).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public void saveMasteries(MasteryPagesDto masteries, String name) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/Masteries");
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(masteries).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public void saveRunes(RunePagesDto runes, String name) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/Runes");
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(runes).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public void saveMasteryScore(ChampionMasteryScoreDto score, String name) throws InterruptedException
	{
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/MasteryScore");
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}

	public IntegerProperty getMasteryScore(String name) throws InterruptedException
	{
		DatabaseReference summonerRef = ref.getReference("Summoner/" + name + "/MasteryScore");
		final CountDownLatch sync = new CountDownLatch(1);
		final IntegerProperty retVal = new SimpleIntegerProperty();
		summonerRef.addListenerForSingleValueEvent(new ValueEventListener()
		{
			@Override
			public void onCancelled(DatabaseError databaseError) {
				sync.countDown();
			}

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() != null)
				{
					GenericTypeIndicator<HashMap<String, Integer>> t = new GenericTypeIndicator<HashMap<String, Integer>>() {};
					Map<String, Integer> val = dataSnapshot.getValue(t);
					int score = val.get("score");
					retVal.set(score);
				}
				sync.countDown();
			}
		});
		sync.await();
		return retVal;
	}

	public LeagueListDtoWrapper getFirebaseLeagueListBySummonerId(String name, String region) throws InterruptedException
	{
		//TODO implement Leagues section of database so we won't store all of this information
		//per user as this info pertains to all users in this League
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/LeagueList");
		final CountDownLatch sync = new CountDownLatch(1);
		final LeagueListDtoWrapper retVal = new LeagueListDtoWrapper();
		dataRef.addListenerForSingleValueEvent(new ValueEventListener()
		{

			@Override
			public void onCancelled(DatabaseError arg0) {
				sync.countDown();
			}

			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() != null)
					retVal.set(dataSnapshot.getValue(LeagueListDtoWrapper.class));
				sync.countDown();
			}
		});
		sync.await();
		return retVal;
	}

	public void saveLeagueList(String name, LeagueListDtoWrapper leagues) throws InterruptedException
	{
		//TODO implement Leagues section of database so we won't store all of this information
		//per user as this info pertains to all users in this League
		DatabaseReference dataRef = ref.getReference("Summoner/" + name + "/LeagueList");
		final CountDownLatch sync = new CountDownLatch(1);
		dataRef.setValue(leagues).addOnCompleteListener(new OnCompleteListener<Void>() {

			@Override
			public void onComplete(Task<Void> arg0) {
				sync.countDown();
			}
		});
		sync.await();
	}
}
