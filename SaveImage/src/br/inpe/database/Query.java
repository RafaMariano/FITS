package br.inpe.database;

import org.bson.Document;
import org.json.JSONObject;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import br.inpe.filesystem.Image;

public class Query {
	private static Document doc = new Document("_id", 1);
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
	
	public static String findAll(int page) {

		JSONObject b = new JSONObject();
	
		FindIterable<Document> findI = Mongo.getInstance().getDataBase().getCollection("inpe").find()
				.projection(doc).limit(500).skip(page);
		
		for (Document doc: findI)
			b.append("_id", doc.get("_id"));
		
		return b.toString();

	}
	
	
	public static void findOne(int value) {

		Document a = new Document();
		
		FindIterable<Document> co = Mongo.getInstance().getDataBase().getCollection("inpe").find()
				.projection(new Document("DAY", 6)).limit(10).skip(value);
		
	
		for (Document doc: co){
			
			a.append("_id", doc.get("_id"));
		}
//			while (co.hasNext()) {
//			
//				a.put(co.next().toJson());
//			}
			System.out.println(a.toJson());
	//	return a.toList();

	}
}
