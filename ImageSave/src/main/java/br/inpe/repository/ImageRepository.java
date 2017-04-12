package br.inpe.repository;

import org.springframework.data.repository.Repository;
import br.inpe.model.Image;

public interface ImageRepository extends Repository<Image, Long>{
	
	public void insert(Image entity);
	
}
