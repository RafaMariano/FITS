package br.inpe.filesystem;

import java.io.IOException;
import java.util.ArrayList;
import br.inpe.database.Query;


public class FileSystem {
	private final String filesystemDB;
	private final String filesystemRsync;
	private final int length;
	private Find find;
	private Query query;

	
	public FileSystem(){
		//temporario
		this.query = new Query();
		this.filesystemRsync = "/home/inpe/Imagens/";
		this.filesystemDB = "/home/inpe/Database/"; 
		//"/home/inpe/Database/";

		this.length = this.filesystemRsync.length();
		//makeDirs();

	}
//	public void makeDirs(){
//		
//	};
	
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
	
	
	public void sendToBD(ArrayList<String> files) throws IOException{
		for (String file: files){
			try{
						
		
				//new Image(file,getLength(),getFileSystemDB());
				this.query.insertDocument(new Image(file,getLength(),getFileSystemDB()));	
			
				//mudar de lugar a imagem?
			
			}
			catch(IOException io){
				//adicionar no log o erro (investigar se é erro na criação da imagem)
			} catch (Error e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("as");
			}
			}
		
	}
	
}
