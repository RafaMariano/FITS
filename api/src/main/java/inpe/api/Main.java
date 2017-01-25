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
		
				URL urlObj = new URL("http://0.0.0.0:4567/hello");   
				
				HttpURLConnection  httpConnection = (HttpURLConnection) urlObj.openConnection();
				httpConnection.setRequestMethod("GET");
				InputStream inputStream = httpConnection.getInputStream();
				OutputStream outputStream = null;
				try {
				    int read = 0;
				    byte[] bytes = new byte[1024];
				    outputStream = new FileOutputStream(new File("/home/inpe/Database/asas.fits"));
				    while ((read = inputStream.read(bytes)) != -1) {
				        outputStream.write(bytes, 0, read);
				    }
				} catch (FileNotFoundException ex) {
				    ex.getMessage();
				} catch (IOException ex) {
				    ex.getMessage();
				} finally {
				    try {
				        if (outputStream != null) {
				            outputStream.close();
				        }
				    } catch (IOException ex) {
				        ex.getMessage();
				    }
				}

	}

}
