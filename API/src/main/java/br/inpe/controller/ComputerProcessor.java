package br.inpe.controller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import br.inpe.database.Query;
import br.inpe.model.Date;

@Path("/images")
public class ComputerProcessor {
	
	//retorna os id de todas as imagens paginado
	@GET
	@Path("{page: [0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountryById(@PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		return Response.ok(Query.findAll(page)).build();
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

	
	@GET		 //list
	@Path("/date/{month:[0-9]}/{day:[0-9]}/{year:[0-9]}/{page: [0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year, @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
		return Response.ok(Query.findAllOfDay(month,day, year, page)).build();
	}
	
	@GET																					//como permitir 33.2 segundos? +[0-9]?
	@Path("/date/{month:[0-9]}/{day:[0-9]}/{year:[0-9]}/time/{hour: [0-9]}/{minute: [0-9]}/{second: [0-9]}/{page: [0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,  @PathParam("hour") int hour, 
			 @PathParam("minute") int minute, @PathParam("second") int second, @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
		return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour, minute, second, page)).build();
  
	}
	
	@GET
	@Path("/date/{month:[0-9]}/{day:[0-9]}/{year:[0-9]}/time/{hour: [0-9]}/{minute: [0-9]}/{page: [0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,  @PathParam("hour") int hour, 
			 @PathParam("minute") int minute, @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
		return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour, minute, page)).build();
  
	}
	
	@GET
	@Path("/date/{month:[0-9]}/{day:[0-9]}/{year:[0-9]}/time/{hour:[0-9]}/{page:[0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year,  @PathParam("hour") int hour, 
			 @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
		return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour, page)).build();
  
	}

	@GET //time/{hour}/{minute}/{second}/
	@Path("/date/{month:[0-9]}/{day:[0-9]}/{year:[0-9]}/cycle/{cycle:[0-9]}/{page:[0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("day") int day, @PathParam("month") int month, @PathParam("year") int year, 
	 @PathParam("cycle") PathSegment pathSegment, @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
			ArrayList<Integer> array;
		
		try{
			 array = new ArrayList<Integer>();
			array.add(Integer.parseInt(pathSegment.getPath()));
			
			if (array.get(0) <= 0){
				return Response.ok("Erro: Somente numeros positivos são aceitos.").build();
			}
			
			for (String a: pathSegment.getMatrixParameters().keySet()){
				array.add(Integer.parseInt(a));
			}
				
		}catch(Exception ex){
			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
		}

		//return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour, minute, second, page)).build();
		 return Response.ok(array).build();
	}
	
	//TESTE
	@GET //time/{hour}/{minute}/{second}/
	@Path("/date/{second:[0-9]}/cycle/{cycle:[0-9]}/{page:[0-9]}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Response echoInputList(@PathParam("second") String second, @PathParam("cycle") PathSegment pathSegment, @PathParam("page") int page) throws JsonGenerationException, JsonMappingException, JsonParseException, IOException {
		
			ArrayList<Integer> array;
		
		try{
			 array = new ArrayList<Integer>();
			array.add(Integer.parseInt(pathSegment.getPath()));
		
//			if (array.get(0) <= 0){
//				return Response.ok("Erro: Somente numeros inteiros são aceitos.").build();
//			}
			
			for (String a: pathSegment.getMatrixParameters().keySet()){
				int cycle = Integer.parseInt(a);
				
				if (cycle > 0)
					array.add(cycle);
				else
					return Response.ok("Erro: Somente numeros inteiros são aceitos.").build();					
			}
				
		}catch(Exception ex){
			return Response.ok("Erro na pesquisa dos dados. Verifique os parâmetros.").build();
		}

		//return Response.ok(Query.findAllOfDayWithTime(month, day, year, hour, minute, second, page)).build();
		 return Response.ok(array).build();
	}
	
}
