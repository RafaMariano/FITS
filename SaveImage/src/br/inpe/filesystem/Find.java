package br.inpe.filesystem;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;



public class Find {
	private Finder finder;
	private final String path;
	
	public Find(String path){
		this.path = path;
		this.finder = new Finder();
	}

	private class Finder extends SimpleFileVisitor<Path>  {
		private ArrayList<String> files;
		
		
		public Finder(){
			this.files = new ArrayList<String>();
			
		}

		//Printa o nome da image
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //  System.out.println(file.toFile().getAbsolutePath());
        	this.files.add(file.toFile().getAbsolutePath());
        	return CONTINUE;
        }

		public ArrayList<String> getFiles() {
			return files;
		}
		
		//Printa o nome da pasta
//        @Override
//	       public FileVisitResult postVisitDirectory(Path dir,
//	                                             IOException exc) {
//	          System.out.println(dir.getFileName());
//	           return CONTINUE;
//	       }
	}
	
	private String getPath(){
		return this.path;
	}
	
	   public ArrayList<String> searchImage() throws IOException{
			Files.walkFileTree(Paths.get(getPath()), this.finder);
			return this.finder.getFiles();
		}

}