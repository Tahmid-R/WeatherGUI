import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
public class Weather {
	
	
	
	public static String KtoC(double temp) {
		return String.format("%.2f", (temp - 273.15));
	}
	
	
	public static String DateTime(long time, String f) {
		Date date = new Date(time);
		
		DateFormat format = new SimpleDateFormat(f);
		
		format.setTimeZone(TimeZone.getTimeZone("ETC/UTC"));
		
		return format.format(date);
		
		
	}
	
		
	}


