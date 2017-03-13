package br.inpe.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import br.inpe.model.FileSystem;
import br.inpe.model.Find;
import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;

public class Controller {
	private final String pathDB;
	private final String pathPrincipal;
	
	public Controller(String pathPrincipal, String pathDB){
		this.pathPrincipal = pathPrincipal;
		this.pathDB = pathDB;
	}

	public ArrayList<String> getImages() throws IOException{
		return Find.getInstance().searchImage(this.pathPrincipal);	
	}	
	
	//tirar?
	public ImagesCollection getImageCollection(Image image){			
		
		ImagesCollection ima = new ImagesCollection();
		ima.setDocument(image.getDocument());

		return ima;
	}
	
	public String getNewPath(String pathImage) throws IOException{
		
		//setLogFile(pathImage);
		String pathDestination =
			FileSystem.getInstance().createDir(pathImage, pathDB, pathPrincipal);
		//setLogFile(pathDestination);
		FileSystem.getInstance().moveFile(pathImage, pathDestination);
		FileSystem.getInstance().deletePath(pathImage.substring(0, 
						pathImage.lastIndexOf("/"))
						, this.pathPrincipal);
		//deleteFileLog();
		return pathDestination;
		}

	
	}
	

