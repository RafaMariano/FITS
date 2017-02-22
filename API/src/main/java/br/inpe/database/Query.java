package br.inpe.database;

import java.io.IOException;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Query {

	
//	
//	public static void findOne(String key, String value){
//		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
//		Document findI = collection.find().filter(Filters.eq(key,value)).first();
//		System.out.println(findI);
//	}
//	
	public static List<Object> findOne(int page){
		
		JSONArray a = new JSONArray();

		try (MongoCursor<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe").find().projection(new Document("_id",1)).limit(10).skip(0).iterator()) {
		 //http://stackoverflow.com/questions/25589113/how-to-select-a-single-field-in-mongodb
		
			while (collection.hasNext()) {
				//ss.append(collection.next());
		      
				a.put(collection.next().toJson());
		      
		    }
		}
		
		return a.toList();
//		Object json = mapper.readValue(collection.find().limit(10).skip(page).first().toJson(), Object.class);
//		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
				//collection.find().filter(Filters.eq(key,value)).first();
	}
	
=======
import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;


public class Query {
	
	private static ObjectMapper mapper = new ObjectMapper();
	private static final Document doc = new Document("_id", 1);
	
	public static String findAll(int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
		return getDB(
				getFindIterable(page)
				);

	}

	public static String findOne(String key) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
		
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(collection.find().filter(Filters.eq("_id",key)).first().toJson(), Object.class));
		
	}
	
	public static String findAllOfDay(int month, int day, int year, int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		
		return getDB(
				getFindIterable(new Document("DAY",day).append("MONTH", month).append("YEAR", year), page)
				);
	}
	
	public static String findAllOfDayWithTime(int month, int day, int year,int hour, int minute, int second, int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		
		return getDB(
				getFindIterable(new Document("DAY",day).append("MONTH", month).append("YEAR", year)
				.append("HOUR", hour).append("MINUTE", minute).append("SECOND", second), page)
				);
	}
	
	public static String findAllOfDayWithTime(int month, int day, int year,int hour, int minute, int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		
			return getDB(
					getFindIterable(new Document("DAY",day).append("MONTH", month).append("YEAR", year)
					.append("HOUR", hour).append("MINUTE", minute), page)
					);
		}
	
	public static String findAllOfDayWithTime(int month, int day, int year,int hour, int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		
			return getDB(
					getFindIterable(new Document("DAY",day).append("MONTH", month).append("YEAR", year)
					.append("HOUR", hour), page)
					);
		}
		
	private static String getDB(FindIterable<Document> findI) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		
		JSONObject b = new JSONObject();
		
		for (Document docu: findI)
			b.append("_id", docu.get("_id"));
		
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(b.toString(),Object.class));
	}
	
	
	private static FindIterable<Document> getFindIterable(int page){
		
		return Mongo.getInstance().getDataBase().getCollection("inpe").find()
				.projection(doc).limit(500).skip(page);
	}
	
	
	private static FindIterable<Document> getFindIterable(Document query, int page){
		
		return Mongo.getInstance().getDataBase().getCollection("inpe").find(query)
				.projection(doc).limit(500).skip(page);
	}
	
	
	private static ObjectMapper getMapper() {
		return mapper;
	}

>>>>>>> temp
}
