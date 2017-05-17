package br.gov.sp.fatec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageRepository extends MongoRepository<ImagesCollection, String>{
	
	public ImagesCollection insert(ImagesCollection entity);
	
	@Query("{},{'_id':1}")
	public Page<ImagesCollection> getImagesById(Pageable page);
	
	@Query("{'_id': ?0 },{}")
	public ImagesCollection findOneById(String id);
	
	
}
