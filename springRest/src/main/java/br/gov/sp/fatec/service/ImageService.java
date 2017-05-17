package br.gov.sp.fatec.service;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageService {
	public ImagesCollection getImageById(String id) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
	public Iterator<ImagesCollection> getAllId(int page);
	public ImagesCollection getFilePathSystem(String id);
}
