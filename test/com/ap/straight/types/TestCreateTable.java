/*
 * @author Jean Lazarou
 * Date: Jun 3, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Types;

import com.ap.straight.Column;
import com.ap.straight.HashTable;
import com.ap.straight.util.HashDatabaseTestCase;


public class TestCreateTable extends HashDatabaseTestCase {

	public void testStringField() throws Exception {
		assertSupports(String.class, Types.VARCHAR);
	}

	public void testIntegerField() throws Exception {
		assertSupports(Integer.class, Types.INTEGER);
	}

	public void testIntField() throws Exception {
		assertSupports(int.class, Types.INTEGER);		
	}
	
	public void testDateField() throws Exception {
		assertSupports(Date.class, Types.DATE);
	}
	
	public void testBigDecimalField() throws Exception {
		assertSupports(BigDecimal.class, Types.DECIMAL);
	}

	static public void assertSupports(Class type, int sqlType) throws Exception {
		
		HashTable table = new HashTable();
		
		table.addColumn("field", type);
		
		assertEquals(1,	table.getColumnCount());
		
		Column col = table.getColumn("field");
		
		assertEquals("field", col.getColumnName());
		assertEquals(sqlType, col.getColumnType());
		
	} 
}
