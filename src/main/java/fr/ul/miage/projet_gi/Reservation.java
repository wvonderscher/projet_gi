package fr.ul.miage.projet_gi;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reservation {
	private static final Pattern VALID_DATE_RESERVATION_REGEX = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}",Pattern.CASE_INSENSITIVE);


	public static boolean verifierDateReservation(String date) {
		if(date != null) {
			date = date.concat(":00");
			SimpleDateFormat dateSdf =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			try {
				Date testDate = dateSdf.parse(date);
				if(testDate.after(new Date())) {
					return true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		return false;
	}
		
	
	public static boolean valideFormatDateReservation(String date) {
		if(date != null) {
			Matcher matcher = VALID_DATE_RESERVATION_REGEX.matcher(date);
			return matcher.find();

		}
		return false;
	}	
}
