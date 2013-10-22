/*
 * Created on June 4, 2004
 */
package com.ap.straight.types;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

import com.ap.straight.util.DateFormatException;

public class Types {

	public static TypeConverter getConverter(Class type) {
		return (TypeConverter) map.get(type);
	}

	public static boolean supports(Class type) {
		return type != Integer.class && type != int.class &&
			   type != String.class && type != Date.class &&
			   type != BigDecimal.class;
	}
	
	public static Object bestConvert(String value) {

		Object result = null;
				
		try
		{
			result = getConverter(Integer.class).convert(value);
		}
		catch (NumberFormatException e)
		{
			try
			{
				result = getConverter(BigDecimal.class).convert(value);
			}
			catch (NumberFormatException e1)
			{
				try
				{
					result = getConverter(Date.class).convert(value);
				}
				catch (DateFormatException e3)
				{
					return null;
				}
			}
		}
		
		return result;

	}

	public static boolean areCompatible(Class a, Class b) {

		if (a.isAssignableFrom(b)) return true;

		if (a == Integer.class && b == int.class) return true;
		if (a == int.class && b == Integer.class) return true;
		
		if (a == BigDecimal.class && b == Integer.class) return true;
		if (a == Integer.class && b == BigDecimal.class) return true;
		
		return false;
	}
	
	private Types() {
		super();
	}

	static Map map = new HashMap();

	static {
		map.put(Integer.class, new StringToInteger());
		map.put(int.class, new StringToInteger());
		map.put(Date.class, new StringToDate());
		map.put(BigDecimal.class, new StringToBigDecimal());
	}
}
