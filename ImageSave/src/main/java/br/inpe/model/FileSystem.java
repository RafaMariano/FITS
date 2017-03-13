package br.inpe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystem {

	private static FileSystem fileSystem;

	private FileSystem() {

	}

	public static synchronized FileSystem getInstance() {
		if (fileSystem == null)
			fileSystem = new FileSystem();

		return fileSystem;
	}

	public String createDir(String pathImage, String pathDB, String pathPrincipal) throws IOException {
		
		//newPath.append(path.substring(length, path.lastIndexOf("/")));
		if (pathImage.substring(0, pathPrincipal.length()).equals(pathPrincipal)){
			StringBuilder newPathImage = new StringBuilder(pathImage);
			newPathImage.replace(0, pathPrincipal.length(), pathDB);
			
			//arrumar bug
			Files.createDirectories(Paths.get(newPathImage.substring(0,newPathImage.lastIndexOf("/"))));
			return newPathImage.toString();
		}
		else{
			return null;
		}
	}

	public void moveFile(String pathImage, String pathDestination) throws IOException, DirectoryNotEmptyException{
		//fazer um teste forcando um erro. O erro que dispara no IOExcpetion é o mesmo do DirectoryNotEmpty?
		//É a mesma mensagem?
			Files.move(Paths.get(pathImage), Paths.get(pathDestination));
		
	}

	public String deletePath(String source, final String pathPrincipal) throws IOException {
			
		if (source.equals(pathPrincipal) == false) {
			
			File file = new File(source);
			if (file.list().length <= 0) {
				if (file.delete()) {
					// log
					return deletePath(file.getParent(), pathPrincipal);
				}
			}
		}
		return source;
	}

}