/*
 * @author Jean Lazarou
 * Date: 5 juin 2004
 */
package com.ap.straight.util;

import java.sql.Date;
import java.util.Calendar;

public class DateFactory {

	public static Date newDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTime().getTime());
	}

}
