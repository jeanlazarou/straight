/**
 * @author Jean Lazarou
 * Created on June 5, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Types;

public class JavaToSQL {
	public static int getType(Class type) {

		if (type.equals(Integer.class))
			return Types.INTEGER;
		else if (type.equals(int.class))
			return Types.INTEGER;
		else if (type.equals(Long.class))
			return Types.BIGINT;
		else if (type.equals(Double.class))
			return Types.DOUBLE;
		else if (type.equals(Float.class))
			return Types.FLOAT;
		else if (type.equals(String.class))
			return Types.VARCHAR;
		else if (type.equals(Date.class))
			return Types.DATE;
		else if (type.equals(BigDecimal.class))
			return Types.DECIMAL;
		else
			return Types.JAVA_OBJECT;

	}
}
