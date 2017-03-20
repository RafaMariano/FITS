package br.inpe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.inpe.model.ImagesCollection;
import br.inpe.repository.ImageRepository;


@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	
	public void setImageRepository(ImageRepository imageRepository){
		this.imageRepository = imageRepository;
	}

	@Override
	public void saveImage(ImagesCollection image) {
		
	//	this.imageRepository.insert(image);
		
	}

	@Override
	public ImagesCollection buscarImage(String id) {
		return imageRepository.findOne(id);
		
	}

	@Override
	public List<ImagesCollection> buscarTodos() {
		return null;
		
	}
	

}
