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
	private static final Document doc = new Document("_id", 1);

	// public static String findAll(int page) throws JsonGenerationException,
	// JsonMappingException, JsonParseException, IOException {
	//
	// return getDB(
	// getFindIterable(page)
	// );
	// }

	public static String findOne(String key)
			throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		MongoCollection<Document> collection = Mongo.getInstance().getDataBase().getCollection("images");
		
		Document docc = collection.find().filter(Filters.eq("_id", key)).first();
		return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(
				getMapper().readValue(docc.toJson(), Object.class));

	}

	// public static String findAllOfDay(int month, int day, int year, int page)
	// throws JsonGenerationException, JsonMappingException, JsonParseException,
	// IOException{
	//
	// return getDB(
	// getFindIterable(new Document("DAY",day).append("MONTH",
	// month).append("YEAR", year), page)
	// );
	// }
	//
	// public static String findAllOfDayWithTime(int month, int day, int
	// year,int hour, int minute, int second, int page) throws
	// JsonGenerationException, JsonMappingException, JsonParseException,
	// IOException{
	//
	// return getDB(
	// getFindIterable(new Document("DAY",day).append("MONTH",
	// month).append("YEAR", year)
	// .append("HOUR", hour).append("MINUTE", minute).append("SECOND", second),
	// page)
	// );
	// }
	//
	// public static String findAllOfDayWithTime(int month, int day, int
	// year,int hour, int minute, int page) throws JsonGenerationException,
	// JsonMappingException, JsonParseException, IOException{
	//
	// return getDB(
	// getFindIterable(new Document("DAY",day).append("MONTH",
	// month).append("YEAR", year)
	// .append("HOUR", hour).append("MINUTE", minute), page)
	// );
	// }
	//
	// public static String findAllOfDayWithTime(int month, int day, int
	// year,int hour, int page) throws JsonGenerationException,
	// JsonMappingException, JsonParseException, IOException{
	//
	// return getDB(
	// getFindIterable(new Document("DAY",day).append("MONTH",
	// month).append("YEAR", year)
	// .append("HOUR", hour), page)
	// );
	// }
	//
	// public static String findAllOfDayWithCycle(int month, int day, int year,
	// ArrayList<Integer> cycle, int page) throws JsonGenerationException,
	// JsonMappingException, JsonParseException, IOException{
	//
	// return getDB(
	// getFindIterable(new Document("DAY",day).append("MONTH",
	// month).append("YEAR", year)
	// .append("CYCLE", new Document("$in",cycle)), page)
	// );
	// }
	//
	//
	public static String getJSON(Document document, int page)
			throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {

		return getDB(getFindIterable(document, page));
	}

	public static String getJSON(int page)
			throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {

		return getDB(getFindIterable(page));
	}

	private static String getDB(FindIterable<Document> findI)
			throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {

		JSONObject b = new JSONObject();

		for (Document docu : findI)
			b.append("_id", docu.get("_id"));

		return getMapper().writerWithDefaultPrettyPrinter()
				.writeValueAsString(getMapper().readValue(b.toString(), Object.class));
	}

	private static FindIterable<Document> getFindIterable(int page) {

		return Mongo.getInstance().getDataBase().getCollection("inpe").find().projection(doc).limit(500)
				.skip((page - 1) * 500);
	}

	private static FindIterable<Document> getFindIterable(Document query, int page) {

		return Mongo.getInstance().getDataBase().getCollection("inpe").find(query).projection(doc).limit(500)
				.skip((page - 1) * 500);
	}

	private static ObjectMapper getMapper() {
		return mapper;
	}

}
