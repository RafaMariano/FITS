package br.inpe.filesystem;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.inpe.log.FileSystemResult;
import br.inpe.log.Log;
import br.inpe.model.FileSystem;
import br.inpe.model.Find;
import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;

public class Controller {
	private final String pathDB;
	private final String pathPrincipal;
	
	@Autowired
	private Log log;
	
	public Controller(String pathPrincipal, String pathDB){
		this.pathPrincipal = pathPrincipal;
		this.pathDB = pathDB;
	}
	
	public void setLog(Log log){
		this.log = log;
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
		
		
		String pathDestination =
			FileSystem.getInstance().createDir(pathImage, pathDB, pathPrincipal);
		this.log.setLogPathOriginalAndDestination(pathImage,pathDestination);
		this.log.setLogSucessuful(FileSystemResult.CREATE_SUCCESSFUL);
		
		FileSystem.getInstance().moveFile(pathImage, pathDestination);
		this.log.setLogSucessuful(FileSystemResult.MOVE_SUCCESSFUL);
		
		FileSystem.getInstance().deletePath(pathImage.substring(0, 
						pathImage.lastIndexOf("/"))
						, this.pathPrincipal);
		this.log.setLogSucessuful(FileSystemResult.DELETE_SUCCESSFUL);
		
		return pathDestination;
		}

	
	}
	

