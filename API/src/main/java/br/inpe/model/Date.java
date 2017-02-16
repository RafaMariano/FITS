package br.inpe.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Date {
		private static Date date;
		private String bool = "true";
		private String errorYear = "the year is incorrect (year < 2000 and > 2100)";
		
		public synchronized static Date getInstance(){
			if(date == null)
				date = new Date();
			return date;
		}
		
		public String dateIsCorrect(int day, int month, int year){
			try{
				LocalDate.of(year, month, day);
				if (year < 2000 || year >2100){
					return errorYear;
				}
				return bool;
			}catch(DateTimeException ex){
				return ex.getMessage();
			}
		}
}
