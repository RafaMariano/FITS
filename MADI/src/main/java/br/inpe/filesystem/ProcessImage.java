package br.inpe.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.inpe.log.Verify;
import br.inpe.model.Image;
import br.inpe.model.ImageFits;
import br.inpe.service.ImageServiceImpl;
import nom.tam.fits.FitsException;

public class ProcessImage {

	private ApplicationContext ctx;
	private ImageServiceImpl imagesService;
	private Verify verify;
	private MoveImage controller;

	public ProcessImage() throws IOException {

		this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		this.imagesService = (ImageServiceImpl) this.ctx.getBean("imageService");
		this.verify = (Verify) this.ctx.getBean("verify");
		this.controller = (MoveImage) this.ctx.getBean("controller");

		verifyImage();
	}

	public void processImage() throws IOException {
		
		List<String> imagesPathList = this.controller.getImages();
		for (String pathImage : imagesPathList) {

			try {
				
				ImageFits image = new ImageFits(pathImage);
				String newPath = this.controller.moveImage(pathImage);
				Image ima = image.getImage();
				ima.setPath(newPath);
				this.imagesService.saveImage(ima);
				
			} catch (FitsException | ParseException | IOException | NullPointerException fi) {
				this.verify.moveToCorrupted(pathImage, this.controller.getPathPrincipal());
			}
		}
	}
	
	public void getImagens() throws IOException, FitsException, ParseException{
		List<String> imagesPathList = this.controller.getImages();
		
		int cont = 0;
		for (String pathImage : imagesPathList) {
				
				String newPathImage = pathImage;
				newPathImage = 	newPathImage.substring(0,newPathImage.lastIndexOf("/")+1);
				
			
				Paths.get(pathImage).toFile().renameTo(new File(newPathImage + Integer.toString(cont) + ".fts"));
			//	Files.delete(Paths.get(pathImage));
				cont++;
			} 
	}
	
	private void verifyImage() throws IOException {

		Image imageColl = this.verify.verify();

		if (imageColl != null) {
			this.imagesService.saveImage(imageColl);
		}
	}
}
