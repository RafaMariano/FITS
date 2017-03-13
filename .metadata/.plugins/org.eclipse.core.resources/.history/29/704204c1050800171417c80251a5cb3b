package br.inpe.filesystem;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;
import br.inpe.service.ImageServiceImpl;
import nom.tam.fits.FitsException;

public class Main {
	
	public static void main(String[] args) throws IOException, ParseException, FitsException {
		
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			Controller controller = (Controller) ctx.getBean("controller");
			
			ArrayList<String> imagesList = controller.getImages();
			
			ImageServiceImpl imagesService = (ImageServiceImpl) ctx.getBean("imageService");

			for (String pathImage: imagesList){
				
				String newPath = controller.getNewPath(pathImage);
				Image image = new Image(newPath);
				ImagesCollection ima = new ImagesCollection();
				ima.setDocument(image.getDocument());
				imagesService.saveImage(ima);
				
			}	
		}
		catch(IOException io){
			
		}

	}

}
