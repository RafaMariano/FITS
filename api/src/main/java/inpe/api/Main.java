package inpe.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;

public class Main {

	public static void main(String[] args) throws IOException {
		
		for (int contt = 0; contt < 999; ++contt){
				for (int cont = 0; cont < 210; ++cont){
				URL urlObj = new URL("http://localhost:8080/API/rest/images/2");   
				
				HttpURLConnection  httpConnection = (HttpURLConnection) urlObj.openConnection();
				httpConnection.setRequestMethod("GET");
				InputStream inputStream = httpConnection.getInputStream();
				 byte[] bytes = new byte[9999];
				inputStream.read(bytes);
				String a = new String(bytes);
				System.out.println(a);
				}
				}
	}

	}


