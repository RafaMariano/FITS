package br.inpe.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.Document;

import nom.tam.fits.Fits;
import nom.tam.fits.FitsException;
import nom.tam.fits.Header;
import nom.tam.fits.HeaderCard;
import nom.tam.util.Cursor;

public class Image {

	private Fits fits;
	private Document collection;
	private StringBuilder sb;

	public Image(String image, int length, String newPath) throws IOException{

		try{
			this.sb = new StringBuilder(image);
			this.fits = new Fits(image);
			this.collection = setDocument(new Document());
			this.collection.put("FileSystem", copyFile( image, createDir(this.sb, length, new StringBuilder(newPath)) ));
			deletePath(this.sb.substring(0, this.sb.lastIndexOf("/")), this.sb.substring(0, length-1));
			this.collection.put("_id", image.substring(image.lastIndexOf("/")+1));
			this.fits.close();

		}
		catch(StringIndexOutOfBoundsException a){
			System.out.println("Imagem em um caminho inválido:     " + image);
			//adicionar no log
			
		}catch (FitsException fits){
			System.out.println("Imagem fits com problema. Corrompido?   " + image);
			System.out.println(fits);
		}
	}

	private String createDir(StringBuilder path, int length, StringBuilder newPath) throws IOException{

		//Colocando o caminho da imagem sem o nome no /home/inpe/Database/
		newPath.append(path.substring(length, path.lastIndexOf("/")));
		//Criando os diretorios
		
		Files.createDirectories(Paths.get(newPath.toString()));
		//new File(newPath.toString()).mkdirs();
		//Adicionando o nome da imagem ao caminho
		newPath.append(path.substring(path.lastIndexOf("/")));

		return newPath.toString();
	}

	private String copyFile(String source, String destination) throws IOException {

		Files.move(Paths.get(source), Paths.get(destination));
//		FileChannel sourceChannel = null;
//		FileChannel destinationChannel = null;
//
//		try {
//			FileInputStream fileInputStream = new FileInputStream(source);
//			sourceChannel = fileInputStream.getChannel();
//			FileOutputStream fileOutputStream = new FileOutputStream(destination);
//			destinationChannel = fileOutputStream.getChannel();
//			sourceChannel.transferTo(0, sourceChannel.size(),
//					destinationChannel);
//			fileInputStream.close();
//			fileOutputStream.close();
//		} finally {
//
//			
//			if (sourceChannel != null && sourceChannel.isOpen())
//				sourceChannel.close();
//			if (destinationChannel != null && destinationChannel.isOpen())
//				destinationChannel.close();
//		}

		return destination;
	}
	
private String deletePath(String source, final String path) throws IOException{
		 System.out.println(path);
		 System.out.println(source);
		if(source.equals(path) == false){
			
			File file = new File(source);
			//System.out.println(source.toString());
			if (file.list().length <= 0){
				if (file.delete()){
					//log
					return deletePath (file.getParent(), path);
				}
			}
		}
		return source;
	}

	private Document setDocument(Document document) throws FitsException, IOException{
		Header header = this.fits.getHDU(0).getHeader();

		Cursor<String, HeaderCard> c = header.iterator();

		while(c.hasNext() ){
			HeaderCard bb = c.next();

			if(bb.getKey().equals("END") == false){

				if (bb.getKey() != null){
					if (bb.getKey().isEmpty() == false){

						if (bb.getKey().equals("COMMENT")){
							document.put(bb.getKey(),bb.getComment());
						}
						else if(bb.getValue() != null) {
							document.put(bb.getKey(), bb.getValue());

						}
						else {
							document.put(bb.getKey(),"");

						}
					}
				}
			}

		}

		return document;
	}

	public Document getDocument(){
		return this.collection;
	}

}
