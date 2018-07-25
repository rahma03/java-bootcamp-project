package com.mitrais.bootcamp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionUtility {
	public static String generateSession(){
		LocalDateTime dateTimeNow = LocalDateTime.now();
		dateTimeNow=dateTimeNow.plusMinutes(30);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dateTimeNow.format(dtf);
	}
	
	public static boolean checkSessionExpired(String session){
		LocalDateTime dateTimeNow = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTimeSession = LocalDateTime.parse(session, dtf);
		
		return dateTimeNow.isBefore(dateTimeSession);
	}
}
