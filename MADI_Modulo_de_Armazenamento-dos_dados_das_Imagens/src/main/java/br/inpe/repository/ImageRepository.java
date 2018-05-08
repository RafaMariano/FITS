package br.inpe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;
import br.inpe.model.Image;

public interface ImageRepository extends MongoRepository<Image, String>{
	
	//public void insert(Image entity);
	
}
