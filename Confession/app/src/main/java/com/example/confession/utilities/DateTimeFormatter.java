package com.example.confession.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class DateTimeFormatter {

	public static String FormatDateTillNow(Date date) {

		Date now = Calendar.getInstance().getTime();
		long day = TimeUnit.DAYS.convert(now.getTime() - date.getTime(), TimeUnit.MILLISECONDS);
		return day + "d ago";
	}

}
