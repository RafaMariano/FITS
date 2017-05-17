package br.gov.sp.fatec.service;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.gov.sp.fatec.model.ImagesCollection;
import br.gov.sp.fatec.repository.ImageRepository;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public void setImageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public ImagesCollection getImageById(String id) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		//ImagesCollection data = 
//		if (data == null)
//			return "Dados não encontrado";
		return this.imageRepository.findOneById(id);
		
	}
	
	@Override
	public Iterator<ImagesCollection> getAllId(int page) {
		Pageable pageable = new PageRequest((page - 1) * 500, 500);
		//try {
		return this.imageRepository.getImagesById(pageable).iterator();
				
				
				//this.convert.toJSONId(
	//	} catch (IOException e) {
	//		return "Erro na criacao do JSON " + e.getMessage();
		//}

		// return this.imageRepository.findDistinctiById("_id");
	}

	@Override
	public ImagesCollection getFilePathSystem(String id) {
//		ImagesCollection data = this.imageRepository.findOneById(id);
//		if (data == null)
//			return null;
		
		return this.imageRepository.findOneById(id); 
	}
	


}
