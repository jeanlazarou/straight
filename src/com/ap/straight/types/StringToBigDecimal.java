/*
 * Created on June 4, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

public class StringToBigDecimal implements TypeConverter {

	public Object convert(String value) {
		return new BigDecimal(value);
	}

	public String reverse(Object value) {
		
		BigDecimal bd = (BigDecimal) value;
		
		return bd.toString();

	}

}
