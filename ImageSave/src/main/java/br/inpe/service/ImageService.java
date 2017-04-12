package br.inpe.service;

import java.io.InputStream;

import org.bson.Document;
import org.springframework.core.io.Resource;

import com.mongodb.DBObject;

import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;

public interface ImageService {
	public void saveImage(Image image);

}
