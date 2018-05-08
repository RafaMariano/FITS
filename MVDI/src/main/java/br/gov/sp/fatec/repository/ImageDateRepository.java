package br.gov.sp.fatec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodb.DBObject;

import br.gov.sp.fatec.model.Image;
import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageDateRepository extends MongoRepository<ImagesCollection, String>{

	@Query("?0")
	public Page<Image> findImages(DBObject query, Pageable pagination);
	
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 },{}")
//	public Page<Image> findImagesByDayMonthYear(int day, int month, int year, Pageable page);
//
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 , 'data.CYCLE':?3 },{}")
//	public Page<Image> findImagesByDayMonthYearWithCycle(int day, int month, int year,String cycle, Pageable page);
//
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 , 'data.CYCLE':?3 "
//			+ ",'data.WAZE_LEN': { '$in': ?4 }, 'data.ST_PARAM': { '$in': ?5}},{}")
//	public Page<Image> findImagesByWaveAndStoke(int day, int month, int year,String cycle,List<String> waze, List<String> stoke,  Pageable page);
//
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 ,'data.ST_PARAM': { '$in': ?3}},{}")
//	public Page<Image> findImagesByStoke_Param(int day, int month, int year, List<String> stoke_Param,
//			Pageable pagination);
//
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 ,'data.WAZE_LEN': { '$in': ?3 }},{}")
//	public Page<Image> findImagesByWave_L(int day, int month, int year, List<String> wave_L, Pageable pagination);
//
//	
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 ,'data.WAZE_LEN': { '$in': ?3 }, "
//			+ "'data.ST_PARAM': { '$in': ?4}},{}")
//	public Page<Image> findImagesByWave_LAndStoke_P(int day, int month, int year, List<String> wave_L,
//			List<String> stoke_Param, Pageable pagination);
//
//	@Query("{'date.DAY': ?0 ,'date.MONTH': ?1 ,'date.YEAR': ?2 , 'data.CYCLE':?3 "
//			+ ", 'data.ST_PARAM': { '$in': ?4}},{}")
//	public Page<Image> findImagesByCycleAndStoke_P(int day, int month, int year, String cycle,
//			List<String> stoke_Param, Pageable pagination);

}
