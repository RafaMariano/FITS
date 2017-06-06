package br.gov.sp.fatec.service;

import java.util.Iterator;

import com.mongodb.DBObject;

import br.gov.sp.fatec.model.Image;

public interface ImageDateService {
	

		public Iterator<Image> getImage(DBObject query, int page);

//		public Iterator<Image> getImageByDayMonthYear(int day, int month, int year, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
//		public Iterator<Image> getImageByDayMonthYearWithCycle(int day, int month, int year, String cycle, int page);
//		public Iterator<Image> getImageByWaveAndStoke(int day, int month, int year, String cycle, List<String> waze,List<String> stoke, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
//
//		//public Iterator<Image> getImage(String query, int page);
//		public Iterator<Image> getImageByStoke_Param(int day, int month, int year, List<String> stoke_Param, int page);
//		public Iterator<Image> getImageByWave_L(int day, int month, int year, List<String> wave_L, int page);
//		public Iterator<Image> getImageByWave_LAndStoke_P(int day, int month, int year, List<String> wave_L,
//				List<String> stoke_Param, int page);
//		public Iterator<Image> getImageByDayMonthYearWithCycleAndStoke_P(int day, int month, int year, String cycle,
//				List<String> stoke_Param, int page);
	//	public Iterator<Image> getImageByQuery(Query query, int page);
		
		//		public String getImageByDayMonth(int day, int month, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException;
//		public String getImageByDay(int day, int page);
//		public String getImageByMonthYear(int month, int year, int page);
		
}
