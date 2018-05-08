package br.gov.sp.fatec.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongodb.DBObject;

import br.gov.sp.fatec.model.Image;
import br.gov.sp.fatec.repository.ImageDateRepository;

@Service("imageDateService")
public class ImageDateServiceImpl implements ImageDateService {

	@Autowired
	private ImageDateRepository imageDateRepository;

	public void setImageDateRepository(ImageDateRepository imageDateRepository) {
		this.imageDateRepository = imageDateRepository;
	}
	
	@Override
	public Iterator<Image> getImage(DBObject query, int page) {
	
		Pageable pagination = new PageRequest((page - 1), 500);
		return this.imageDateRepository.findImages(query, pagination).iterator();
	
	}
	
//	@Override
//	public Iterator<Image> getImageByDayMonthYear(int day, int month, int year, int page) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
//		
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByDayMonthYear(day, month, year, pagination).iterator();
//	}
//
//	@Override
//	public Iterator<Image> getImageByDayMonthYearWithCycle(int day, int month, int year, String cycle, int page){
//	
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByDayMonthYearWithCycle(day, month, year, cycle, pagination).iterator();
//	}
//
//	@Override
//	public Iterator<Image> getImageByWaveAndStoke(int day, int month, int year, String cycle, List<String> waze, List<String> stoke, int page)
//			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
//		
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByWaveAndStoke(day, month, year, cycle, waze, stoke, pagination).iterator();
//	}
//
//	
//	
//	
//	@Override
//	public Iterator<Image> getImageByStoke_Param(int day, int month, int year, List<String> stoke_Param, int page) {
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByStoke_Param(day, month, year, stoke_Param, pagination).iterator();
//	}
//
//	@Override
//	public Iterator<Image> getImageByWave_L(int day, int month, int year, List<String> wave_L, int page) {
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByWave_L(day, month, year, wave_L, pagination).iterator();
//	}
//
//	@Override
//	public Iterator<Image> getImageByWave_LAndStoke_P(int day, int month, int year, List<String> wave_L,
//			List<String> stoke_Param, int page) {
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByWave_LAndStoke_P(day, month, year, wave_L, stoke_Param, pagination).iterator();
//	
//	}
//
//	@Override
//	public Iterator<Image> getImageByDayMonthYearWithCycleAndStoke_P(int day, int month, int year, String cycle,
//			List<String> stoke_Param, int page) {
//		// TODO Auto-generated method stub
//		Pageable pagination = new PageRequest((page - 1), 500);
//		return this.imageDateRepository.findImagesByCycleAndStoke_P(day, month, year, cycle, stoke_Param, pagination).iterator();
//	
//	}
//	

	//@Override
	//public Iterator<Image> getImageByQuery(Query query, int page) {
	//	Pageable pagination = new PageRequest((page - 1), 500);
	//	return this.imageDateRepository.findImagesByQuery(query, pagination).iterator();
	//return null;
	//}
	
	
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
