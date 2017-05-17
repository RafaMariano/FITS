package br.gov.sp.fatec.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageDateService {
	
		public Iterator<ImagesCollection> getImageByDayMonthYear(int day, int month, int year, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
		public Iterator<ImagesCollection> getImageByDayMonthYearWithCycle(int day, int month, int year, String cycle, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
		public Iterator<ImagesCollection> getImageByWazeAndStoke(int day, int month, int year, String cycle, List<String> waze,List<String> stoke, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
		public Iterator<ImagesCollection> getImageByWaze(int day, int month, int year, String cycle, List<String> waze, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;

		//		public String getImageByDayMonth(int day, int month, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
//		public String getImageByDay(int day, int page);
//		public String getImageByMonthYear(int month, int year, int page);
		
}
