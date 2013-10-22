/*
 * Created on June 4, 2004
 */
package com.ap.straight.types;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.ap.straight.util.DateFactory;
import com.ap.straight.util.DateFormatException;

public class StringToDate implements TypeConverter {

	public Object convert(String value) {

		try {

			int prev = 0;
			
			int i = value.indexOf('-');
			
			if (i == -1) {
				throw new DateFormatException(value);
			}
			
			String year = value.substring(prev, i);
			
			prev = i + 1;
			i = value.indexOf('-', prev);
			
			if (i == -1) {
				throw new DateFormatException(value);
			}
			
			String month = value.substring(prev, i);
			
			String day = value.substring(i + 1);

			return DateFactory.newDate(Integer.parseInt(year),
			                           Integer.parseInt(month),
			                           Integer.parseInt(day));
			
	
		} catch (Exception e) {
			throw new DateFormatException(e, value);
		}
	}

	public String reverse(Object value) {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		return fmt.format((Date) value);

	}

}
