package br.inpe.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private static Mongo mongo;
	
	private Mongo(){
		this.mongoClient = new MongoClient();
		this.database = mongoClient.getDatabase("inpe");
	}
		
	public static synchronized Mongo getInstance(){
		if (mongo == null)
			mongo = new Mongo();
		
		return mongo;
	}
	
	public MongoDatabase getDataBase(){
		return database;
	}

}
