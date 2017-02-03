package br.inpe.database;

import org.bson.Document;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import br.inpe.filesystem.Image;

public class Query {
	private static int a = 0;
	
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
		
		StringBuilder ss = null;
		Document aa = new Document();
		
		//FindIterable<Document> a = Mongo.getInstance().getDataBase().getCollection("inpe").find().limit(10).skip(0);

		
		try (MongoCursor<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe").find("","").limit(10).skip(a).iterator()) {
		 //http://stackoverflow.com/questions/25589113/how-to-select-a-single-field-in-mongodb
			while (collection.hasNext()) {
				//ss.append(collection.next());
		        System.out.println(collection.next());
		    }
		}
		
		//System.out.println(ss);
	}
	
}
