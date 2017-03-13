package br.inpe.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Log {
	
	private Path path;
	
	public Log(String path){
		this.path = Paths.get(path);
		if(Files.notExists(this.path, LinkOption.NOFOLLOW_LINKS));
			//Files.createFile(this.path);
	}
	
	public void setLog(String pathOriginal, String pathDestination){
		
		List<String> lines;
		
		try {
			
			lines = Files.readAllLines(path);
			lines.add("PATH_ORIGINAL = " + pathOriginal);
			lines.add("PATH_DESTINATION = " + pathDestination);
			Files.write(path, lines);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteLog() throws IOException{
		Files.delete(path);
	}
	
	public Map<String,String> getLog(){
		List<String> lines;
		Map<String,String> a2 = null;
	
		try {
		lines = Files.readAllLines(path);	
		
		a2 = new HashMap<>();
		for(String a: lines){
			

			if (a.contains("="))
				a2.put(a.substring(0, a.indexOf("=")-1), a.substring(a.indexOf("=")+2));
			else
				a2.put(a, "");
		}
		
		}catch (IOException e) {
		e.printStackTrace();
	}

		return a2;
	}
	
	public void setLog(FileSystemResult type){
		
		List<String> lines;
		
		try {
			
			lines = Files.readAllLines(path);
			lines.add(type.toString());
			Files.write(path, lines);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}