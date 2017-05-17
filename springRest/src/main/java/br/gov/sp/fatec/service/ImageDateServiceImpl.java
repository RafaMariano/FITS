package br.gov.sp.fatec.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.gov.sp.fatec.model.ImagesCollection;
import br.gov.sp.fatec.repository.ImageDateRepository;

@Service("imageDateService")
public class ImageDateServiceImpl implements ImageDateService {

	@Autowired
	private ImageDateRepository imageDateRepository;

	public void setImageDateRepository(ImageDateRepository imageDateRepository) {
		this.imageDateRepository = imageDateRepository;
	}
	
	@Override
	public Iterator<ImagesCollection> getImageByDayMonthYear(int day, int month, int year, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		
		Pageable pagination = new PageRequest((page - 1) * 500, 500);
		return this.imageDateRepository.findImagesByDayMonthYear(day, month, year, pagination).iterator();
	}

	@Override
	public Iterator<ImagesCollection> getImageByDayMonthYearWithCycle(int day, int month, int year, String cycle, int page)
			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		
		Pageable pagination = new PageRequest((page - 1) * 500, 500);
		return this.imageDateRepository.findImagesByDayMonthYearWithCycle(day, month, year, cycle, pagination).iterator();
	}

	@Override
	public Iterator<ImagesCollection> getImageByWazeAndStoke(int day, int month, int year, String cycle, List<String> waze, List<String> stoke, int page)
			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		
		Pageable pagination = new PageRequest((page - 1) * 500, 500);
		return this.imageDateRepository.findImagesByWazeAndStoke(day, month, year, cycle, waze, stoke, pagination).iterator();
	}

	@Override
	public Iterator<ImagesCollection> getImageByWaze(int day, int month, int year, String cycle, List<String> waze, int page)
			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		
		Pageable pagination = new PageRequest((page - 1) * 500, 500);
		return this.imageDateRepository.findImagesByWaze(day, month, year, cycle, waze, pagination).iterator();
	}
	
	
//	@Override
//	public String getImageByDayMonth(int day, int month, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
//		
//		Pageable pagination = new PageRequest((page - 1) * 500, 500);
//		return this.convert.toJSONId(this.imageDateRepository.findImagesByDayMonth(day, month, pagination));
//
//	}
//
//	@Override
//	public String getImageByDay(int day, int page) {
//		Pageable pagination = new PageRequest((page - 1) * 500, 500);
//		try {
//			return this.convert.toJSONId(this.imageDateRepository.findImagesByDay(day, pagination));
//		} catch (IOException e) {
//			return "Erro na busca pelos dados";// e.printStackTrace();
//		}
//
//	}
//	
//	@Override
//	public String getImageByMonthYear(int month, int year, int page) {
//
//		Pageable pagination = new PageRequest((page - 1) * 500, 500);
//		try {
//			return this.convert
//					.toJSONId(this.imageDateRepository.findImagesByMonthYear(month, year, pagination));
//		} catch (IOException e) {
//			return "Erro na busca pelos dados";// e.printStackTrace();
//		}
//
//	}
	
	
//	@Override
//	public String getImage(int day, int month, int year, int page) {
//	
//		try {
//
//		Page<ImagesCollection> pages;
//		Pageable pagination = new PageRequest((page - 1) * 500, 500);
//		
//		if(day == 0)
//			if(month == 0)
//				return "Erro - Falta de parametros";
//			else 
//				if (year == 0)
//					return "Erro - falta de parametros";
//				else
//					pages = this.imageTimeRepository.findImagesByMonthYear(month, year, pagination);
//		else
//			if(month == 0)
//				return "Erro - Falta de parametros";
//			else
//				if(year == 0)
//					pages = this.imageTimeRepository.findImagesByDayMonth(day, month, pagination);
//				else
//					pages = this.imageTimeRepository.findImagesByDayMonthYear(day, month, year, pagination);
//			
//			return this.convert.toJSONId(pages);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			return "Erro na busca pelos dados";// e.printStackTrace();
//		}


}
