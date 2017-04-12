package br.inpe.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import br.inpe.database.Query;

@Path("/images")
public class ComputerProcessor {

//	// retorna os id de todas as imagens paginado
	@GET
	@Path("{page: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountryById(@PathParam("page") int page)
		throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//		System.out.println("a");
		return Response.ok(Query.getJSON(page)).build();
		// return Query.findOne(id).toString();
	}
//
//	// retorna os dados da imagem, passando o ID dela
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getImageInf(@PathParam("id") String id)
			throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		//return Response.ok(Query.findOne(id)).build();
	return Query.findOne(id);
	}

	@GET // list
	@Path("/date/{month:[0-9]+}/{day:[0-9]+}/{year:[0-9]+}/{page: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
			@PathParam("page") int page)
					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		Document doc = new Document("DAY", day).append("MONTH", month).append("YEAR", year);
		return Response.ok(Query.getJSON(doc, page)).build();
		// return Response.ok(Query.findAllOfDay(month,day, year,
		// page)).build();
	}
//
//	@GET // como permitir 33.2 segundos? +[0-9]?
//	@Path("/date/{month:[0-9] \\d{2}+}/{day:[0-9] \\d{2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{2}+}/{second: [0-9] \\d{3}+}/{page: [0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour, @PathParam("minute") int minute, @PathParam("second") float second,
//			@PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//		// second is == float
//		return Response.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//				.append("HOUR", hour).append("MINUTE", minute).append("SECOND", second), page)).build();
//
//		// return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour,
//		// minute, second, page)).build();
//
//	}
//
//
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{1,2}+}/{page: [0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour, @PathParam("minute") int minute, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		return Response.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//				.append("HOUR", hour).append("MINUTE", minute), page)).build();
//		// return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour,
//		// minute, page)).build();
//
//	}
//
//	// {month:[0-9]+}/{day:[0-9]+}/{year:[0-9]+}/
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour:[0-9]+}/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		return Response.ok(Query.getJSON(
//				new Document("DAY", day).append("MONTH", month).append("YEAR", year).append("HOUR", hour), page))
//				.build();
//		// return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour,
//		// page)).build();
//
//	}
//
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/cycle/{cycle:[0-9](.+)?}/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("cycle") String cycle, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			 return Response.ok(Query.getJSON(
//			 new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//			 .append("CYCLE",new Document("$in",getArray(cycle))), page))
//			 .build();
//
//			//return Response.ok(getArray(cycle)).build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	}
//	
//	@GET 
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}"
//			+ "/cycle/{cycle:[0-9](.+)?}/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour, @PathParam("cycle") String cycle, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			return Response.ok(Query.getJSON(
//			 new Document("DAY", day).append("MONTH", month).append("YEAR",
//			 year).append("HOUR", hour)
//			 .append("CYCLE",new Document("$in",getArray(cycle))), page))
//			 .build();
//
//			//return Response.ok(getArray(cycle)).build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	}
//	
//	
//	@GET 
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{1,2}+}"
//			+ "/cycle/{cycle:[0-9](.+)?}/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour,@PathParam("minute") int minute,@PathParam("cycle") String cycle, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			return Response.ok(Query.getJSON(
//			 new Document("DAY", day).append("MONTH", month).append("YEAR",
//			 year).append("HOUR", hour).append("MINUTE", minute)
//			 .append("CYCLE",new Document("$in",getArray(cycle))), page))
//			 .build();
//
//			//return Response.ok(getArray(cycle)).build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	}
//	
//	@GET // time/{hour}/{minute}/{second}/
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{1,2}+}/"
//			+ "{second: [0-9] \\d{3}+}/cycle/{cycle:[0-9](.+)?}/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour,@PathParam("minute") int minute, @PathParam("second") int second,
//			@PathParam("cycle") String cycle, @PathParam("page") int page)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			return Response.ok(Query.getJSON(
//			 new Document("DAY", day).append("MONTH", month).append("YEAR",
//			 year).append("HOUR", hour).append("MINUTE", minute).append("SECOND", second)
//			 .append("CYCLE",new Document("$in",getArray(cycle))), page))
//			 .build();
//
//			//return Response.ok(getArray(cycle)).build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	}
//	
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{1,2}+}/"
//			+ "{second: [0-9] \\d{3}+}/cycle/{cycle:[0-9](.+)?}/PS/{st:[Q|I|U|V](.+[[Q|I|U|V]])?}/page/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour,@PathParam("minute") int minute, @PathParam("second") int second,
//			@DefaultValue("0") @PathParam("page") int page, @PathParam("st") String st,
//			@PathParam("cycle") String cycle)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			if (st.length() > 4)
//				return Response.ok("Numero máximo de parametros aceitos é 4").build();
//
//			return Response
//					.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//							.append("HOUR", hour).append("MINUTE", minute).append("SECOND", second)
//							.append("STOKE_PARAMETER", new Document("$in", st.split(",")))
//							.append("CYCLE", new Document("$in", getArray(cycle))), page))
//					.build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	
//	}	
//	
//	
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/{minute: [0-9] \\d{1,2}+}/"
//			+ "cycle/{cycle:[0-9](.+)?}/PS/{st:[Q|I|U|V](.+[[Q|I|U|V]])?}/page/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour,@PathParam("minute") int minute,
//			@DefaultValue("0") @PathParam("page") int page, @PathParam("st") String st,
//			@PathParam("cycle") String cycle)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			if (st.length() > 4)
//				return Response.ok("Numero máximo de parametros aceitos é 4").build();
//
//			return Response
//					.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//							.append("HOUR", hour).append("MINUTE", minute)
//							.append("STOKE_PARAMETER", new Document("$in", st.split(",")))
//							.append("CYCLE", new Document("$in", getArray(cycle))), page))
//					.build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	
//	}	
//	
//	
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/time/{hour: [0-9] \\d{2}+}/"
//			+ "cycle/{cycle:[0-9](.+)?}/PS/{st:[Q|I|U|V](.+[[Q|I|U|V]])?}/page/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@PathParam("hour") int hour,
//			@DefaultValue("0") @PathParam("page") int page, @PathParam("st") String st,
//			@PathParam("cycle") String cycle)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			if (st.length() > 4)
//				return Response.ok("Numero máximo de parametros aceitos é 4").build();
//
//			return Response
//					.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month).append("YEAR", year)
//							.append("HOUR", hour)
//							.append("STOKE_PARAMETER", new Document("$in", st.split(",")))
//							.append("CYCLE", new Document("$in", getArray(cycle))), page))
//					.build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//	
//	}
//	
//	@GET
//	@Path("/date/{month:[0-9] \\d{1,2}+}/{day:[0-9] \\d{1,2}+}/{year:[0-9] \\d{4}+}/cycle/{cycle:[0-9](.+)?}/PS/{st:[Q|I|U|V](.+[[Q|I|U|V]])?}/page/{page:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,
//			@DefaultValue("0") @PathParam("page") int page, @PathParam("st") String st,
//			@PathParam("cycle") String cycle)
//					throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//
//		try {
//
//			if (st.length() > 4)
//				return Response.ok("Numero máximo de parametros aceitos é 4").build();
//
//			return Response
//					.ok(Query.getJSON(new Document("DAY", day).append("MONTH", month)
//							.append("YEAR", year).append("STOKE_PARAMETER", new Document("$in", st.split(",")))
//							.append("CYCLE", new Document("$in", getArray(cycle))), page))
//					.build();
//
//		} catch (Exception ex) {
//			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
//		}
//
//		// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
//		// https://www.tutorialspoint.com/java/java_regular_expressions.htm
//		// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
//		// https://www.google.com.br/?gws_rd=ssl#q=expression+regular+with+matrix+jersey
//		// https://www.safaribooksonline.com/library/view/restful-java-with/9781449361433/ch04.html
//		// https://www.safaribooksonline.com/library/view/restful-java-with/9780596809300/ch04s02.html
//	}
//
//	// forçar ser inteiro, caso de erro
//	private ArrayList<Integer> getArray(String cycle) {
//		ArrayList<Integer> array = new ArrayList<Integer>();
//
//		for (String s : cycle.split(","))
//			array.add(Integer.parseInt(s));
//		return array;
//	}

}
