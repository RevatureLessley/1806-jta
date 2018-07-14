package com.revature.utils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.codehaus.jackson.map.ObjectMapper;

public class StringManip {

	public static LocalDateTime getLocalDateTime(String s) {
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

	public static LocalDateTime getLocalDateTimeForm(String s) {
		String[] dateSplit = s.split("-");

		int year = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int dayOfMonth = Integer.parseInt(dateSplit[2]);

		LocalDateTime ldt = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
		return ldt;
	}

	public static String formatDate(LocalDateTime eventDate) {
		return eventDate.getMonthValue() + "/" + eventDate.getDayOfMonth() + "/" + eventDate.getYear();
	}

	public static String getJSONString(Object o) {
		ObjectMapper mapper = new ObjectMapper();

		String json = null;

		try {
			json = mapper.writeValueAsString(o);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}
}
