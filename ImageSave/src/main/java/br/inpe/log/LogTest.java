package br.inpe.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

public class LogTest {

	private Path path;

	public LogTest(String path) throws IOException {

		this.path = Paths.get(path);

		if (Files.notExists(this.path, LinkOption.NOFOLLOW_LINKS))
			Files.createFile(this.path);
	}
	
	public List<String> getLog() throws IOException{
		return Files.readAllLines(this.path);
	}
	
	
	public void setLog(String error) {
		try {
			
			List<String> lines = getLog();
			lines.add(Calendar.getInstance().getTime().toString() + ": " + error);
			Files.write(path, lines);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
