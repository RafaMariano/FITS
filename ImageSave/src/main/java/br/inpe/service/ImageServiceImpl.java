package br.inpe.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.inpe.log.Log;
import br.inpe.model.Image;
import br.inpe.repository.ImageRepository;


@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private Log log;
	
	public void setLog(Log log){
		this.log = log;
	}
	
	public void setImageRepository(ImageRepository imageRepository){
		this.imageRepository = imageRepository;
	}

	@Override
	public void saveImage(Image image) throws IOException {
		
		this.imageRepository.insert(image);
		this.log.deleteLog();
	}
}
