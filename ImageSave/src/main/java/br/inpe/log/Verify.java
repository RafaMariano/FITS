package br.inpe.log;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import br.inpe.model.FileSystem;
import br.inpe.model.Image;
import br.inpe.model.ImagesCollection;
import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;

public class Verify {
	private Path pathCorrupted;
	private Path log;

	public Verify(String pathCorrupted, String log) throws IOException {
		this.pathCorrupted = Paths.get(pathCorrupted);
		this.log = Paths.get(log);
		if (Files.notExists(this.log, LinkOption.NOFOLLOW_LINKS))
			Files.createFile(this.log);
	}

	public ImagesCollection verify() throws IOException {

		List<String> lines = FileSystem.getInstance().getLog();
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
					getVerifyDelete(lines.get(1));
				}
			} else {
				// deleteLog();
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
			ArrayList<String> paths = getDestinationPath(new StringBuilder(destinationPath),
					new StringBuilder(imagePath));
			FileSystem.getInstance().createDir(imagePath, paths.get(1), paths.get(0));
			FileSystem.getInstance().moveFile(imagePath, destinationPath);
			FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")), paths.get(0));
			return destinationPath;

		} else {
			moveToCorruptedDirectory(imagePath);
			return null;
		}
	}

	private void moveToCorruptedDirectory(String imagePath) throws DirectoryNotEmptyException, IOException {
		StringBuilder st = new StringBuilder(this.pathCorrupted.toString());
		st.append(imagePath.substring(imagePath.lastIndexOf("/") - 1));
		FileSystem.getInstance().moveFile(imagePath, st.toString());
	}
	
	private ImagesCollection getVerifyDelete(String path) throws DirectoryNotEmptyException, IOException{
		try {
			return createImageCollection(path);
		} catch (FitsException | ParseException | IOException e) {
			moveToCorruptedDirectory(path);
			return null;
		}
	}
	
	private ImagesCollection getVerifyMove(String imagePath, String destinationPath) throws DirectoryNotEmptyException, IOException{
		ArrayList<String> paths = getDestinationPath(new StringBuilder(destinationPath),
				new StringBuilder(imagePath));
		try {
			FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")),
					paths.get(0));
			
			return createImageCollection(destinationPath);
			
		} catch (IOException | FitsException | ParseException e) {
			moveToCorruptedDirectory(destinationPath);
			return null;
		}
	}
	
	private ImagesCollection createImageCollection(String path) throws FitsException, ParseException, IOException{
		
		Image image = new Image(path);
		ImagesCollection ima = new ImagesCollection();
		
		ima.setDocument(image.getDocument());
		return ima;
	}
	
	private ImagesCollection getVerifyCreate(String imagePath, String destinationPath) throws DirectoryNotEmptyException, IOException {

		if (Files.exists(Paths.get(imagePath), LinkOption.NOFOLLOW_LINKS)) {

			try {
				String path = fileExistInPathPrincipal(imagePath, destinationPath);

				if (path == null)
					return null;
				
				return createImageCollection(path);

			} catch (IOException | FitsException | ParseException eo) {
				moveToCorruptedDirectory(imagePath);
				return null;
			}
		} else if (Files.exists(Paths.get(destinationPath), LinkOption.NOFOLLOW_LINKS)) {

			try {
				if (isCorrupted(destinationPath) == false) {
					ArrayList<String> paths = getDestinationPath(new StringBuilder(destinationPath),
							new StringBuilder(imagePath));
					FileSystem.getInstance().deletePath(imagePath.substring(0, imagePath.lastIndexOf("/")),
							paths.get(0));

					return createImageCollection(destinationPath);
					
				} else {
					moveToCorruptedDirectory(destinationPath);
					return null;
				}
			} catch (IOException | FitsException | ParseException eo) {
				moveToCorruptedDirectory(destinationPath);
				return null;
			}

		}
		return null;
	}

	public ArrayList<String> getDestinationPath(StringBuilder destinatioPath, StringBuilder principalPath) {

		int lastIndexOfDestination = destinatioPath.lastIndexOf("/");
		int lastIndexOfPrincipal = principalPath.lastIndexOf("/");

		if (destinatioPath.substring(lastIndexOfDestination)
				.equals(principalPath.substring(lastIndexOfPrincipal)) == false) {

			ArrayList<String> paths = new ArrayList<>();
			paths.add(principalPath.toString());
			paths.add(destinatioPath.toString());
			return paths;
		}

		destinatioPath.setLength(lastIndexOfDestination);
		principalPath.setLength(lastIndexOfPrincipal);

		return getDestinationPath(destinatioPath, principalPath);
	}

}
