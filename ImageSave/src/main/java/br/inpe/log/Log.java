package br.inpe.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Log {

	private Path path;

	public Log(String path) throws IOException {

		this.path = Paths.get(path);
		if (Files.notExists(this.path, LinkOption.NOFOLLOW_LINKS))
			Files.createFile(this.path);
	}

	public void verificacao() throws IOException {
		List<String> lines;
		lines = Files.readAllLines(path);
		int size = lines.size() - 1;
		if (size > 0) {
			switch (FileSystemResult.valueOf(lines.get(size))) {
			case CREATE_SUCCESSFUL:
				break;
			case MOVE_SUCCESSFUL:
				break;
			case DELETE_SUCCESSFUL:
				break;
			default:
				// DELETA E CRIA DENOVO O ARQUIVO, PQ DE ALGUMA FORMA A O
				// DIRETORIO FOI CRIADO, MAS NAO SETADO
				break;
			}
		} else {
			System.out.println("Erro: Setar no log");
		}
	}

	public void setLogPathOriginalAndDestination(String pathOriginal, String pathDestination) {

		try {

			List<String> lines = Files.readAllLines(path);
			lines.add(pathOriginal);
			lines.add(pathDestination);
			
			Files.write(path, lines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteLog() {
		try {
			Files.delete(path);
		} catch (IOException e) {

		//	e.printStackTrace();
		}
	}

	public void setLogSucessuful(FileSystemResult type) {

		try {

			List<String> lines = Files.readAllLines(path);
			lines.add(type.toString());
			Files.write(path, lines);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
