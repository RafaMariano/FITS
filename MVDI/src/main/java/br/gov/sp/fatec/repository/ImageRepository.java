package br.gov.sp.fatec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import com.mongodb.DBObject;

import br.gov.sp.fatec.model.Image;
import br.gov.sp.fatec.model.ImagesCollection;

public interface ImageRepository extends Repository<ImagesCollection, String>{
	
	@Query("{},{'_id':1}")
	public Page<Image> getAllPagedImagesId(Pageable page);
	
	@Query("{'_id': ?0 },{}")
	public Image findOneById(String id);
	
	@Query("?0")
	public Page<Image> findImages(DBObject query, Pageable pagination);
}
