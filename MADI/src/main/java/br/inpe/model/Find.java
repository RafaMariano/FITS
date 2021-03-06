package br.inpe.model;

import static java.nio.file.FileVisitResult.CONTINUE;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Find {
	private Finder finder;
	private static Find find;
	
	private Find(){
		this.finder = new Finder();
	}
	
	public static synchronized Find getInstance() {
		if (find == null)
			find = new Find();

		return find;
	}
	private class Finder extends SimpleFileVisitor<Path>  {
		private List<String> files;


		public Finder(){
			this.files = new ArrayList<String>();

		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			
			if (file.getFileName().toString().substring(0,1).equals(".") == false)
				this.files.add(file.toString());
			return CONTINUE;
		}

		public List<String> getFiles() {
			return files;
		}

	}

	public List<String> searchImage(String pathImages) throws IOException{
		Files.walkFileTree(Paths.get(pathImages), this.finder);
		return this.finder.getFiles();
	}

}
