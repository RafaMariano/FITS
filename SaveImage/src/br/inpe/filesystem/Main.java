package br.inpe.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import nom.tam.fits.FitsException;

public class Main {

	public static void main(String[] args) throws IOException, FitsException {

		
		FileSystem f = new FileSystem();
		f.sendToBD(f.getImages());
		
		//Files.move(Paths.get("/home/inpe/Imagens/10_11_2016/1/Q/imagens.fits"), Paths.get("/home/inpe/Database"));
		
		//File file = new File("/home/inpe/Imagens/asasas/as/a");
		//System.out.println(file.getParent());
		//System.out.println(deletePath("/home/inpe/Database/10_11_2016/1/Q", "/home/inpe/Database"));
	//	System.out.println(file.list().length);
	}

}
