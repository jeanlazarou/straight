/*
 * Created on June 4, 2004
 */
package com.ap.straight.types;

public class StringToInteger implements TypeConverter {

	public Object convert(String value) {
		return new Integer(value);
	}

	public String reverse(Object value) {
		return ((Integer)value).toString();
	}
}
