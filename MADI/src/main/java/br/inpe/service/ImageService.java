package br.inpe.service;

import java.io.IOException;

import br.inpe.model.Image;

public interface ImageService {
	public void saveImage(Image image) throws IOException;

}
