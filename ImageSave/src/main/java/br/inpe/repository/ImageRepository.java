package br.inpe.repository;

import org.springframework.data.repository.Repository;
import br.inpe.filesystem.ImagesCollection;

public interface ImageRepository extends Repository<ImagesCollection, Long>{
	
	public ImagesCollection insert(ImagesCollection entity);
}
