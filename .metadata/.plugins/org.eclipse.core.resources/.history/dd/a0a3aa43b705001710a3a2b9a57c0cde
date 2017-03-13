package br.inpe.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDecimal {
	private DecimalFormatSymbols otherSymbols; 
	private DecimalFormat df; 
	private SimpleDateFormat dateFormat;
	private static FormatDecimal formatDecimal;
	
	private FormatDecimal() throws ParseException{
		
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		this.otherSymbols = new DecimalFormatSymbols();
		this.otherSymbols.setDecimalSeparator('.');
		this.df = new DecimalFormat("##.##", otherSymbols);
		this.df.setMaximumFractionDigits(2);
	
	}
	
	public static synchronized FormatDecimal getInstance() throws ParseException {
		if (formatDecimal == null)
			formatDecimal = new FormatDecimal();

		return formatDecimal;
	}
	
	public Date getTimeJulianoFormat(String timeJuliano) throws ParseException{
		return this.dateFormat.parse(timeJuliano);
	}
	
	public String setFloat(int number1, int number2){
		return this.df.format( (float)number1 + ((float)number2/100));
		
	}
	
	
	
}
