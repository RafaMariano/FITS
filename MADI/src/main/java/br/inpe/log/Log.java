package br.inpe.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Log {

	private Path path;
	private List<String> listZero;

	public Log(String path) throws IOException {

		this.path = Paths.get(path);
		this.listZero = new ArrayList<>();

		if (Files.notExists(this.path, LinkOption.NOFOLLOW_LINKS))
			Files.createFile(this.path);
	}
	
	public List<String> getLog() throws IOException{
		return Files.readAllLines(this.path);
	}
	
	public void setLogPathOriginalAndDestination(String pathOriginal, String pathDestination) {

		try {

			List<String> lines = getLog();
			lines.add(pathOriginal);
			lines.add(pathDestination);
			Files.write(path, lines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteLog() throws IOException {

		Files.write(this.path, this.listZero);
	}

	public void setLogSucessful(FileSystemResult type) {

		try {

			List<String> lines = getLog();
			lines.add(type.toString());
			Files.write(path, lines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
