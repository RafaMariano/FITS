package br.inpe.filesystem;

import java.io.IOException;

import br.inpe.log.LogTest;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ProcessImage processImage;
		try {
			processImage = new ProcessImage();
			processImage.processImage();
		
		} catch (IOException e) {
			LogTest log = new LogTest("/home/inpe/Log/log.txt");
			log.setLog(e.toString());
		}
		
}
