package br.inpe.filesystem;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatDecimal {
	private DecimalFormatSymbols otherSymbols; 
	private DecimalFormat df; 
	private SimpleDateFormat dateFormat; 
	private Calendar calendar; 
	
	public FormatDecimal(){
		
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");		
		this.calendar = Calendar.getInstance();
		
		this.otherSymbols = new DecimalFormatSymbols();
		this.otherSymbols.setDecimalSeparator('.');
		
		this.df = new DecimalFormat("##.##", otherSymbols);
		this.df.setMaximumFractionDigits(2);
	
	}
	
	public void setTime(String time) throws ParseException{
		this.calendar.setTime(this.dateFormat.parse(time));
		
	}
	
	public int getTime(int field){
		return calendar.get(field);
	}
	
	public String setFloat(int number1, int number2){
		return this.df.format( (float)number1 + ((float)number2/100));
		
	}
	
	
	
}