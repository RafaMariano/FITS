package br.inpe.database;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import br.inpe.filesystem.Image;

public class Query {
	
	public void insertDocument(Image image) {
		try{
			
			MongoCollection<Document> collection =	Mongo.getInstance().getDataBase().getCollection("inpe");
			collection.insertOne(image.getDocument());
			
		}
		catch(MongoWriteException mw){
		//	System.err.println("Está imagem já existe cadastrado no banco " + mw);
		}
		catch(MongoSocketOpenException ms){
			//System.err.println("  " + ms);
		}
		}
	
	public static void findOne(String key, String value){
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
		Document findI = collection.find().filter(Filters.eq(key,value)).first();
		System.out.println(findI);
	}
	
	public static void findOne(String key, int value){
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
		Document findI = collection.find().filter(Filters.eq(key,value)).first();
		System.out.println(findI);
	}
	
}
