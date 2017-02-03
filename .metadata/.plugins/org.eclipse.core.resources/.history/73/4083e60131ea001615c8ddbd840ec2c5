package br.inpe.database;

import java.io.IOException;

import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Query {
	private static int a = 0;
	
	
//	
//	public static void findOne(String key, String value){
//		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
//		Document findI = collection.find().filter(Filters.eq(key,value)).first();
//		System.out.println(findI);
//	}
//	
	public static String findOne(int page) throws JsonGenerationException, JsonMappingException, IOException{
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(collection.find().limit(10).skip(page).first().toJson(), Object.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
				//collection.find().filter(Filters.eq(key,value)).first();
	}
	
}
