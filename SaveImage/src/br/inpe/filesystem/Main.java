package br.inpe.filesystem;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.inpe.database.Query;



public class Main {

	public static void main(String[] args) throws IOException, ParseException {


		try{
			FileSystem f = new FileSystem();
			f.sendToBD(f.getImages());
		}
		catch(IOException io){

		}
//		
//		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
//		otherSymbols.setDecimalSeparator('.');
//		DecimalFormat df = new DecimalFormat("##.##",otherSymbols);
//		df.setMaximumFractionDigits(2);
//		
//		
//		System.out.println(df.format((float)12/100));
//		 int a = 12;
//		 
//		 System.out.println((float)a/100);
		
		//Query.findOne("SECOND",5);
		
//		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");
//		LocalDate a = LocalDate.parse("2011-07-06T02:34:17.12", formato);
//		
//		System.out.println(a.getYear());
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
//		Date date =  dateFormat.parse("2011-07-06T02:34:17.12");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		
//		for (int cont = 0; cont < 999999999; cont++){
//		System.out.println(calendar.get(Calendar.SECOND));
//		}
//		
//	FileWriter a = new FileWriter("/home/inpe/texto.txt",true);
//	a.write("ss");
//	a.close();
	
	}

}
