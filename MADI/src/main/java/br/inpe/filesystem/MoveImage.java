package br.inpe.filesystem;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.inpe.log.FileSystemResult;
import br.inpe.log.Log;
import br.inpe.model.FileSystem;
import br.inpe.model.Find;

public class MoveImage {
	private final String pathPrincipal;
	private final String pathDB;
	@Autowired
	private Log log;
	
	public MoveImage(String pathPrincipal, String pathDB){
		this.pathPrincipal = pathPrincipal;
		this.pathDB = pathDB;
	}
	
	public void setLog(Log log){
		this.log = log;
	}
	
	public String getPathPrincipal(){
		return this.pathPrincipal;
	}
	
	public String getPathDB(){
		return this.pathDB;
	}
	
	public List<String> getImages() throws IOException{
		return Find.getInstance().searchImage(this.pathPrincipal);	
	}	
	
	public String moveImage(String pathImage) throws IOException{
		
		StringBuilder pathDestination = new StringBuilder(pathImage);
		pathDestination.replace(0, this.pathPrincipal.length(), this.pathDB);
		String pathDestinationToString = pathDestination.toString();
		
		FileSystem.getInstance().createDir(pathDestinationToString);
		this.log.setLogPathOriginalAndDestination(pathImage,pathDestinationToString);
		this.log.setLogSucessful(FileSystemResult.CREATE_SUCCESSFUL);
		
		FileSystem.getInstance().moveFile(pathImage, pathDestinationToString);
		this.log.setLogSucessful(FileSystemResult.MOVE_SUCCESSFUL);
				
		FileSystem.getInstance().deletePath(pathImage.substring(0, 
					pathImage.lastIndexOf("/")), this.pathPrincipal);
		this.log.setLogSucessful(FileSystemResult.DELETE_SUCCESSFUL);
		return pathDestinationToString;
	}
}