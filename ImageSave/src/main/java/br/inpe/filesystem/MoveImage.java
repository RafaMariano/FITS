package br.inpe.filesystem;

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

public class MoveImage {
	
	public static void main(String[] args) throws IOException, ParseException, FitsException {

			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			ImageServiceImpl imagesService = (ImageServiceImpl) ctx.getBean("imageService");
			
			Verify verify = (Verify) ctx.getBean("verify");
			Image imageColl = verify.verify();
			
			if (imageColl != null){
				imagesService.saveImage(imageColl);
			}
			
			FileSystemController controller = (FileSystemController) ctx.getBean("controller");
			
			List<String> imagesPathList = controller.getImages();

			for (String pathImage: imagesPathList){
			
				try{
//					saveImage(pathImage, controller, imagesService);
					ImageFits image = new ImageFits(pathImage);
					String newPath = controller.getNewPath(pathImage);					
					Image ima = image.getImage();
					ima.setPath(newPath);
					imagesService.saveImage(ima);
				}
				catch(FitsException  | ParseException | IOException | NullPointerException fi){
					verify.moveToCorrupted(pathImage, controller.getPathPrincipal());				
				}
				
			}	
		}
	
	private static void saveImage(String pathImage, FileSystemController controller, ImageServiceImpl imagesService) throws FitsException, ParseException, IOException{
		ImageFits image = new ImageFits(pathImage);
		String newPath = controller.getNewPath(pathImage);
//		image.setKeyValue("FILESYSTEM", newPath);
		
		Image ima = image.getImage();
////		ImagesCollection ima = new ImagesCollection();
////		ima.setDocument(image.getDocument());
		ima.setPath(newPath);
		imagesService.saveImage(ima);
		
	}
//	
	
//	
//	
//	
//			FileSystemController controller = (FileSystemController) ctx.getBean("controller");
	
	
//			StringBuilder destinatioPath = new StringBuilder("/home/inpe/a/b/c/d");
		//	destinatioPath.setLength(destinatioPath.lastIndexOf("/"));
//			System.out.println(destinatioPath.substring(destinatioPath.lastIndexOf("/")));
//			List<String> imagesFits =	controller.getImages();
//		int cont = 1;
//		int contt = 0;
////		
////		String[] st = {"Q","U","V","I"};
//		for(String image: imagesFits){
			
		
	//	System.out.println("/home/inpe/Database".concat(image.substring(18, image.length())));
			
//			Fits fits = new Fits(image);
////			
//			String st_param = image.substring(0, image.lastIndexOf("/"));
//			String st = st_param.substring(st_param.lastIndexOf("/")+1, st_param.length());
//			String comprimento_de_onde = st_param.substring(0,
//					st_param.lastIndexOf("/"));
//			String comp_Onda = comprimento_de_onde.substring(comprimento_de_onde.lastIndexOf("/")+1,
//					comprimento_de_onde.length());
//			
//			
//			fits.getHDU(0).getHeader().addValue("CYCLE", 1, "");
//			fits.getHDU(0).getHeader().addValue("ST_PARAM", st, "");
//			fits.getHDU(0).getHeader().addValue("WAZE_LEN", comp_Onda, "");
////		//	String newPath = controller.getNewPath(image);
//			fits.write(new File("/home/inpe/Database/".concat(image.substring(18, image.length()))
//			.concat(".fts")));
//	//	System.out.println(fits.getHDU(0).getHeader().getIntValue("SIZE"));
//		}
//		//String[] a = null;
//		//Path path = Paths.get("/home/inpe/a.txt");
//	//	List<String>lines = Files.readAllLines(path);
//		//Files.write(path, );
//		
//	}
		
}