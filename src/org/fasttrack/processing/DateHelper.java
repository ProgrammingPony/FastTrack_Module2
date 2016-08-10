package org.fasttrack.processing;

import java.util.Calendar;

public class DateHelper {
	
	private DateHelper(){}
	
	public static java.sql.Date addYearsToDate(java.sql.Date date, int years) {
		java.sql.Date logicalDate;
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.YEAR, years);
		
		return new java.sql.Date(c.getTimeInMillis());
	}
}
