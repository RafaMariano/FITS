package br.inpe.controller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import br.inpe.database.Query;
import br.inpe.model.Date;

@Path("GET/images")
public class ComputerProcessor {
	
	//retorna os id de todas as imagens paginado
	@GET
	@Path("{page: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountryById(@PathParam("page") int id) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		 return Response.ok(Query.findOne(id)).build();
		//return Query.findOne(id).toString();
	}
	
	//retorna os dados da imagem, passando o ID dela
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImageInf(@PathParam("id") String id) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//		return Query.findOne(id);
	return Response.ok(Query.findOne(id)).build();
	}

	//retorna uma colecao de dados perante o dia, mes e ano
//	@GET		 //list
//	@Path("/cycle/{cycle}/stoke_parameter/{st: \\d+}/date")
//	@Produces(MediaType.APPLICATION_JSON)
//	 public Response echoInputList(@PathParam("st") List<String> stoke_Parameter ,@PathParam("cycle") int cycle, @QueryParam("day") final int day, @QueryParam("month") final int month, @QueryParam("year") final int year) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
//		
//		//System.out.println(stoke_Parameter.size());
//		if(cycle < 0)
//			return Response.ok("cycle needs be > 0").build();
//		
//		String bool = Date.getInstance().dateIsCorrect(day, month, year);
//		
//		if(bool.equals("true"))
//			if(cycle == 0)
//				return Response.ok(Query.findOne(day, month, year)).build();
//			else
//				return Response.ok(Query.findOne(day, month, year,cycle)).build();
//		return Response.ok(bool).build();
//
//	   
//	}
	
	@GET		 //list
	@Path("/date/{month}/{day}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@QueryParam("day") final int day, @QueryParam("month") final int month, @QueryParam("year") final int year) {
		
	return null;

	   
	}

}
