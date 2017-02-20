package br.inpe.database;

import java.io.IOException;
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
	private static Document doc = new Document("_id", 1);
	
	public static String findOne(int value) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {

		JSONObject b = new JSONObject();
	
		FindIterable<Document> findI = Mongo.getInstance().getDataBase().getCollection("inpe").find()
				.projection(doc).limit(500).skip(value);
		
		for (Document doc: findI)
			b.append("_id", doc.get("_id"));
		
		//	return b.toString();
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(b.toString(),Object.class));

	}
	
	
	public static String findOne(String key) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("inpe");
		
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(collection.find().filter(Filters.eq("_id",key)).first().toJson(), Object.class));
		
	}
	public static String findOne(int day, int month, int year) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		JSONObject b = new JSONObject();
		
		FindIterable<Document> findI = Mongo.getInstance().getDataBase().getCollection("inpe").find().filter(new Document("DAY",day).append("MONTH", month).append("YEAR", year));
		for (Document doc: findI)
			b.append("_id", doc.get("_id"));
		
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(b.toString(),Object.class));
//		return getMapper().writerWithDefaultPrettyPrinter().
//				writeValueAsString(getMapper().readValue(collection.find().filter(Filters.eq(new Document("DAY",day).append("MONTH", month).append("YEAR", year))).first().toJson(), Object.class));
//		
	}
	
	public static String findOne(int day, int month, int year, int cycle) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException{
		JSONObject b = new JSONObject();
		
		FindIterable<Document> findI = Mongo.getInstance().getDataBase().getCollection("inpe").find().filter(new Document("DAY",day).append("MONTH", month).append("YEAR", year).append("CYCLE", cycle));
		for (Document doc: findI)
			b.append("_id", doc.get("_id"));
		
		return getMapper().writerWithDefaultPrettyPrinter().
				writeValueAsString(getMapper().readValue(b.toString(),Object.class));
//		return getMapper().writerWithDefaultPrettyPrinter().
//				writeValueAsString(getMapper().readValue(collection.find().filter(Filters.eq(new Document("DAY",day).append("MONTH", month).append("YEAR", year))).first().toJson(), Object.class));
//		
	}
	
	public static ObjectMapper getMapper() {
		return mapper;
	}

}
