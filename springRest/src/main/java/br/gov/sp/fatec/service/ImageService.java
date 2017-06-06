package br.gov.sp.fatec.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.gov.sp.fatec.model.Image;

public interface ImageService {
	public Image getImageById(String id) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
	public Iterable<Image> getAllId(int page);
	public String getFilePath(String id);
	//public Iterator<Image> getImage(DBObject query, int page);
	public Iterable<Image> getImage(int day, int month, int year, int hour, int minute, float second, List<String> wave,
			List<String> stoke, String cycle, int page);

	public String getPathZip(int day, int month, int year, int hour, int minute, float second, List<String> wave,
			List<String> stoke, String cycle, int page);
}
