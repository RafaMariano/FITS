package br.gov.sp.fatec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageTimeRepository  extends MongoRepository<ImagesCollection, String>{
	
//	final String path = "document.TIMEANDDAY.TIME";
//	
//
//	@Query("{'"+ path + ".HOUR': ?0 ,'"+ path +".MINUTE': ?1 ,'"+ path +".SECOND': ?2 },{_'_id':'1'}")
//	public Page<ImagesCollection> findImagesByHourMinuteSecond(int hour, int minute, float second, Pageable page);
//	
//	@Query("{'"+ path + ".HOUR': ?0 ,'"+ path +".MINUTE': ?1 },{_'_id':'1'}")
//	public Page<ImagesCollection> findImagesByHourMinute(int hour, int minute, Pageable page);

}
