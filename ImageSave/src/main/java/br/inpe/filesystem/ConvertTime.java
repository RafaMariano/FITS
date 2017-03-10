package br.inpe.filesystem;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.bson.Document;

public class ConvertTime {
	private java.util.Calendar calendar;
	
	public ConvertTime() throws ParseException{
		this.calendar = java.util.Calendar.getInstance();
	}
	
	public Document setTimeAndDay(String timeJuliano) throws ParseException{
		
		Date format = FormatDecimal.getInstance().getTimeJulianoFormat(timeJuliano);
		this.calendar.setTime(format);
		
		Document date = new Document();
		
		date.append("DAY",this.calendar.get(Calendar.DAY_OF_MONTH));
		date.append("MONTH",this.calendar.get(Calendar.MONTH) + 1);
		date.append("YEAR", this.calendar.get(Calendar.YEAR));
		
		Document time = new Document();
		time.append("HOUR", this.calendar.get(Calendar.HOUR));
		time.append("MINUTE", this.calendar.get(Calendar.MINUTE));
			
		time.append("SECOND",  FormatDecimal.getInstance().setFloat(
				this.calendar.get(Calendar.SECOND), this.calendar.get(Calendar.MILLISECOND)
				));
			
		return new Document().append("DATE", date).append("TIME", time);
	}

}
