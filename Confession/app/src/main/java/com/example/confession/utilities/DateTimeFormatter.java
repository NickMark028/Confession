package com.example.confession.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class DateTimeFormatter {

	public static String FormatDateTillNow(Date date) {

		Date now = Calendar.getInstance().getTime();
		long difference = now.getTime() - date.getTime();
		long day = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
		long hour = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
		return hour < 24 ? hour + "h ago" : day + "d ago";
	}
}
