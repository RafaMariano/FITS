package br.inpe.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystem {
	private static FileSystem fileSystem;

	private FileSystem(){}

	public static synchronized FileSystem getInstance() throws IOException {
		
		if (fileSystem == null){
			fileSystem = new FileSystem();
		}
		return fileSystem;
	}
	
	public void createDir(String fileSystemImage) throws IOException {
	

		Path imagePath = Paths.get(fileSystemImage);
		
		if (Files.isDirectory(imagePath))
			Files.createDirectories(imagePath);
		else
			Files.createDirectories(Paths.get(fileSystemImage.substring(0,fileSystemImage.lastIndexOf("/"))));

	}

	public void moveFile(String pathImage, String pathDestination) throws IOException{
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
//	
//	public List<String> getLog() throws IOException{
//		return this.log.getLog();
//	}

}
