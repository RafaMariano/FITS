package br.inpe.repository;


import org.springframework.data.repository.CrudRepository;
import br.inpe.model.ImagesCollection;


public interface ImageRepository extends CrudRepository<ImagesCollection, Long>{
	
	//public ImagesCollection insert(ImagesCollection entity);
	public ImagesCollection findOne(String id);
}
