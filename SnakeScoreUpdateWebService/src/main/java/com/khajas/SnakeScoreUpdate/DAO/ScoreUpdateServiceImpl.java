package com.khajas.SnakeScoreUpdate.DAO;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.Document;
import org.json.JSONObject;

import com.khajas.SnakeScoreUpdate.Model.HiScore;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ScoreUpdateServiceImpl implements ScoreUpdateService{
	private static HashSet<String> VALID_TOKENS=new HashSet<>();

	private MongoClient mongo;
	private MongoDatabase database;
	
	public ScoreUpdateServiceImpl(){
		mongo = new MongoClient( "52.87.171.153" , 27017 );
		MongoCredential.createCredential(USERNAME,DATABASE, 
				PASSWORD.toCharArray());
		System.out.println("Connected to the database successfully");
		//				Accessing the database
		database = mongo.getDatabase("snake");
		//				Retrieving a collection
	}
	
	@Override
	public String generateToken() {
		return RandomStringUtils.randomAlphanumeric(20).toUpperCase();
	}

	@Override
	public HiScore getHiScore() {
		String[] data=this.connectToDB();
		String score=data[0];
		String username=data[1];
		String token=this.generateToken();
		if(VALID_TOKENS.size()>10)
			VALID_TOKENS.clear();
		VALID_TOKENS.add(token);
		this.closeConn();
		return new HiScore(score, token, username);
	}

	@Override
	public void updateHiScore(HiScore newScore) {
		ScoreUpdateServiceImpl.VALID_TOKENS.add("abcd");
		System.out.println(newScore.getToken());
		if(ScoreUpdateServiceImpl.VALID_TOKENS.contains(newScore.getToken())){
			System.out.println(newScore.getToken());
			ScoreUpdateServiceImpl.VALID_TOKENS.remove(newScore.getToken());
			System.out.println(newScore.getNewScore());
			this.updateScoreOnDB(newScore);
		}
	}
	
	private String[] connectToDB(){
		//				Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("scores");
		System.out.println("Collection sampleCollection selected successfully");
		//				Getting the iterable object
		FindIterable<Document> iterDoc = collection.find();
		//Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		String[] data=new String[2];
		while (it.hasNext()) { 
			Document d=it.next();
			d.toJson();
			JSONObject j=new JSONObject(d.toJson());
			System.out.println(j.get("hiscore"));
			System.out.println(j.get("user"));
			data[0]=String.valueOf(j.get("hiscore"));
			data[1]=j.getString("user");
		}
		return data;
	}
	
	private void updateScoreOnDB(HiScore hiscore){
		//				Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("scores");
		collection.updateOne(Filters.eq("id", 1), Updates.set("hiscore", hiscore.getNewScore()));      
		System.out.println("Document update successfully...");
		//		Retrieving the		documents after updation
		//		Getting the iterable object
		FindIterable<Document> iterDoc = collection.find();
		//Getting the iterator
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) { 
			System.out.println(it.next()); 
		}		
		this.closeConn();
	}
	
	private void closeConn(){
		this.mongo.close();
	}
}
