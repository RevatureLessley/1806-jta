package com.revature.utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class StringManip {
	
	public static  LocalDateTime getLocalDateTime(String s){
		s = s.replaceAll(" ", "T");

		LocalDateTime ldt = LocalDateTime.parse(s);
		return ldt;
	}
	
	
	public static String getTimestamp(LocalDateTime t) {
		t.truncatedTo(ChronoUnit.MINUTES);
		String stamp = t.toString();
		stamp = stamp.replaceAll("T", " ");

		return stamp;
	}

	public static String formatCurrency(double a) {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = nf.format(a);

		return s;
	}
}
