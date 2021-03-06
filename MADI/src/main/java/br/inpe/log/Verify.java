package br.inpe.log;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.inpe.model.FileSystem;
import br.inpe.model.Image;
import br.inpe.model.ImageFits;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class Verify {
	private Path pathCorrupted;
	private Path pathLog;
	private String pathPrincipal;
	private String pathDB;
	
	@Autowired
	private Log log;

	public Verify(String pathCorrupted, String log, String pathPrincipal, String pathDB) throws IOException {
		this.pathCorrupted = Paths.get(pathCorrupted);
		this.pathLog = Paths.get(log);
		this.pathPrincipal = pathPrincipal;
		this.pathDB = pathDB;
		if (Files.notExists(this.pathLog, LinkOption.NOFOLLOW_LINKS))
			Files.createFile(this.pathLog);	
	}

	public void setLog(Log log){
		this.log = log;
	}
	
	public void moveToCorrupted(String pathImage, String pathPrincipal) throws IOException{
		
		try {
			moveToCorruptedDirectory(pathImage, pathPrincipal);
		} catch (IOException e) {
			LogTest log = new LogTest(pathLog.toString());
			log.setLog(e.toString());
		}
	}
	
	public Image verify() throws IOException {

		List<String> lines = this.log.getLog();
		int size = lines.size() - 1;

		if (size > -1) {

			FileSystemResult result = FileSystemResult.convert(lines.get(size));
			if (result != null) {

				switch (result) {
				case CREATE_SUCCESSFUL:
					return getVerifyCreate(lines.get(0), lines.get(1));
					
				case MOVE_SUCCESSFUL:
					return getVerifyMove(lines.get(0), lines.get(1));
					
				case DELETE_SUCCESSFUL:
						getVerifyDelete(lines.get(0), lines.get(1));
				}
			}
		}
		return null;
	}

	private boolean isCorrupted(String imagePath) throws FitsException, IOException {
		Fits fits = new Fits(imagePath);
		long size = fits.getHDU(0).getHeader().getLongValue("SIZE");
		fits.close();
		if (Files.size(Paths.get(imagePath)) == size) {
			return false;

		} else {
			return true;
		}
	}
	
	private String fileExistInPathPrincipal(String imagePath, String destinationPath)
			throws FitsException, IOException {
		
		if (isCorrupted(imagePath) == false) {
			FileSystem.getInstance().createDir(destinationPath);
			FileSystem.getInstance().moveFile(imagePath, destinationPath);
			FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")),
					this.pathPrincipal);
		
			return destinationPath;

		} else {
			moveToCorruptedDirectory(imagePath, this.pathPrincipal);
			return null;
		}
	}
	
	private Image getVerifyDelete(String path, String destinationPath) throws DirectoryNotEmptyException, IOException{
		try {
			if (Files.exists(Paths.get(destinationPath), LinkOption.NOFOLLOW_LINKS)){
			return createImageCollection(destinationPath);
			}
			else{
				//setar log o erro
				return null;
			}
		} catch (FitsException | ParseException | IOException e) {	
			moveToCorruptedDirectory(destinationPath, this.pathDB);
			return null;
		}
	}
	
	private void moveToCorruptedDirectory(String imagePath, String pathPrincipal) throws IOException {
		
		StringBuilder st = new StringBuilder(this.pathCorrupted.toString());
		st.append(imagePath.substring(imagePath.lastIndexOf("/")));
		FileSystem.getInstance().moveFile(imagePath, st.toString());
		FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")), pathPrincipal);
		
		}
	
	private Image getVerifyMove(String imagePath, String destinationPath) throws DirectoryNotEmptyException, IOException{
		
		try {
			if (Files.exists(Paths.get(destinationPath), LinkOption.NOFOLLOW_LINKS)){
			FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")),
					this.pathPrincipal);
			
			return createImageCollection(destinationPath);
		}
			else{
				//setar log o erro
				return null;
			}
		} catch (IOException | FitsException | ParseException e) {
			moveToCorruptedDirectory(destinationPath, this.pathDB);
			return null;
		}
	}
	
	private Image createImageCollection(String path) throws FitsException, ParseException, IOException{
		
		ImageFits image = new ImageFits(path);
		return image.getImage();
	}
	
	private Image getVerifyCreate(String imagePath, String destinationPath) throws DirectoryNotEmptyException, IOException {

		if (Files.exists(Paths.get(imagePath), LinkOption.NOFOLLOW_LINKS)) {

			try {
				String path = fileExistInPathPrincipal(imagePath, destinationPath);

				if (path == null)
					return null;
				
				return createImageCollection(path);

			} catch (IOException | FitsException | ParseException eo) {
				moveToCorruptedDirectory(imagePath, this.pathPrincipal);
				//setar log o erro
				return null;
			}
			
		} else if (Files.exists(Paths.get(destinationPath), LinkOption.NOFOLLOW_LINKS)) {

			try {
				if (isCorrupted(destinationPath) == false) {
					return createImageCollection(destinationPath);
					
				} else {
					moveToCorruptedDirectory(destinationPath, this.pathDB);
					return null;
				}
			} catch (IOException | FitsException | ParseException eo) {
				moveToCorruptedDirectory(destinationPath, this.pathDB);
				//setar log o erro
				return null;
			}

		}
		return null;
	}	
	
}
