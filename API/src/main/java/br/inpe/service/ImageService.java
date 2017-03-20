package br.inpe.service;

import java.util.List;

import br.inpe.model.ImagesCollection;

public interface ImageService {
	public void saveImage(ImagesCollection image);
	public ImagesCollection buscarImage(String id);
	public List<ImagesCollection> buscarTodos();

}
