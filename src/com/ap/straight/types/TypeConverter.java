/*
 * Created on June 4, 2004
 */
package com.ap.straight.types;

public interface TypeConverter {
	Object convert(String value);
	String reverse(Object value);
}
