package br.inpe.filesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.inpe.log.FileSystemResult;
import br.inpe.log.Log;
import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;
import br.inpe.service.ImageServiceImpl;
import nom.tam.fits.FitsException;

public class Main {
	
	public static void main(String[] args) throws IOException, ParseException, FitsException {
	
//		try{
//				
//			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//			
//			//Log log = (Log) ctx.getBean("log");
//			//log.verificacao();
//			Controller controller = (Controller) ctx.getBean("controller");
//			
//			ArrayList<String> imagesPathList = controller.getImages();
//			
//			ImageServiceImpl imagesService = (ImageServiceImpl) ctx.getBean("imageService");
//
//			for (String pathImage: imagesPathList){
//				
//				String newPath = controller.getNewPath(pathImage);
//				Image image = new Image(newPath);
//				ImagesCollection ima = new ImagesCollection();
//				ima.setDocument(image.getDocument());
//				imagesService.saveImage(ima);
//				
//			}	
//		}
//		catch(IOException io){
//			
//		}
		String[] a = null;
		Path path = Paths.get("/home/inpe/a.txt");
	//	List<String>lines = Files.readAllLines(path);
		///Files.write(path, a);
		
	}

}
