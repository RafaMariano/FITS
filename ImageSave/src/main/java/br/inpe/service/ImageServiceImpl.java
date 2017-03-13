package br.inpe.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.inpe.model.ImagesCollection;
import br.inpe.repository.ImageRepository;

public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void setImageRepository(ImageRepository imageRepository){
		this.imageRepository = imageRepository;
	}

	@Override
	public void saveImage(ImagesCollection image) {
		this.imageRepository.insert(image);
		
	}
}
