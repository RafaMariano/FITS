//package br.gov.sp.fatec.model;
//
//import java.io.IOException;
//
//import org.bson.Document;
//import org.json.JSONObject;
//import org.springframework.data.domain.Page;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.gov.sp.fatec.view.View;
//
//public class Convert {
////
//	private ObjectMapper mapper;
//	
//	public Convert(){
//		this.mapper  = new ObjectMapper();
//	//	this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//	}
//	
//	
//	public String toJSONId(Page<ImagesCollection> findI)
//			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
//		JSONObject b = new JSONObject();
//	
//		for (ImagesCollection docu : findI)
//			b.append("_id", docu.getId());
//
//		return this.mapper.writerWithDefaultPrettyPrinter()
//				.writeValueAsString(this.mapper.readValue(b.toString(), Object.class));
//	}
//	
//	
//	public String toJSON(Document doc) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
//		
//		return getMapper().writerWithDefaultPrettyPrinter().
//				writeValueAsString(getMapper().readValue(
//						doc.toJson(),
//						Object.class));
//	}
//	
//	
//	
//	public Image getJson(Image image) throws JsonProcessingException{
//		
//		    String result = mapper
//		      .writerWithView(View.All.class)
//		      .writeValueAsString(image);
//		
//		return null;
//	}
//	
//	
//	private ObjectMapper getMapper() {
//		return mapper;
//	}
//}
