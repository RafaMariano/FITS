package br.inpe.filesystem;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import br.inpe.database.Query;


public class FileSystem {
	private final String filesystemDB;
	private final String filesystemRsync;
	private final int length;
	private Find find;
	private Query query;
	private FormatDecimal formatDecimal;


	public FileSystem(){
		this.query = new Query();
		this.filesystemRsync = "/home/inpe/Imagens/";
		this.filesystemDB = "/home/inpe/Database/";
		this.length = this.filesystemRsync.length();
		this.formatDecimal = new FormatDecimal();

	}

	private int getLength(){
		return this.length;
	}

	private String getFileSystemDB(){

		return this.filesystemDB;
	}

	public ArrayList<String> getImages() throws IOException{
		this.find = new Find(this.filesystemRsync);
		return find.searchImage();	
	}


	public void sendToBD(ArrayList<String> files) throws ParseException {
		for (String file: files){
			try{
				System.out.println(file);
				//new Image(file,getLength(),getFileSystemDB());
				this.query.insertDocument(new Image(file,getLength(),getFileSystemDB(),this.formatDecimal));	

			}
			catch (Error e) {
				e.printStackTrace();

			}
		}

	}

}
