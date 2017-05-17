package br.gov.sp.fatec.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageDateRepository extends MongoRepository<ImagesCollection, String>{
	
	final String date = "date";
	
		@Query("{'"+ date + ".DAY': ?0 ,'"+ date +".MONTH': ?1 ,'"+ date +".YEAR': ?2 },{_'_id':'1'}")
		public Page<ImagesCollection> findImagesByDayMonthYear(int day, int month, int year, Pageable page);
		
		@Query("{'"+ date + ".DAY': ?0 ,'"+ date +".MONTH': ?1 ,'"+ date +".YEAR': ?2, 'data.CYCLE':?3 },{_'_id':'1'}")
		public Page<ImagesCollection> findImagesByDayMonthYearWithCycle(int day, int month, int year,String cycle, Pageable page);
		
		@Query("{'"+ date + ".DAY': ?0 ,'"+ date +".MONTH': ?1 ,'"+ date +".YEAR': ?2, 'data.CYCLE':?3 "
				+ ",'data.WAZE_LEN': { '$in': ?4 }},{_'_id':'1'}")
		public Page<ImagesCollection> findImagesByWaze(int day, int month, int year,String cycle,List<String> waze, Pageable page);
		
		@Query("{'"+ date + ".DAY': ?0 ,'"+ date +".MONTH': ?1 ,'"+ date +".YEAR': ?2, 'data.CYCLE':?3 "
				+ ",'data.WAZE_LEN': { '$in': ?4 }, 'data.ST_PARAM': { '$in': ?5}},{_'_id':'1'}")
		public Page<ImagesCollection> findImagesByWazeAndStoke(int day, int month, int year,String cycle,List<String> waze, List<String> stoke,  Pageable page);
		
		
		
}
