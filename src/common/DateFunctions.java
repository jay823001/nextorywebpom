package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFunctions 
{
	public static String dateToDayAndTime(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'");
		
		String currentDate = dateFormat.format(date);
		
		return currentDate;
		
	}
	
}
