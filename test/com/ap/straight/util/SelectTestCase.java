/*
 * @author Jean Lazarou
 * Date: 5 juin 2004
 */
package com.ap.straight.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ap.straight.HashDatabase;
import com.ap.straight.HashTable;

public class SelectTestCase extends HashDatabaseTestCase {

	protected void addStringTable() throws SQLException {
		addTable(String.class, new Object[] {"'A'", "'B'", "'C'", "'D'", "'C'"});
	}

	protected void addIntegerTable() throws SQLException {
		addTable(Integer.class, new Object[] {
			new Integer(1),
			new Integer(11),
			new Integer(3),
			new Integer(14),
			new Integer(3),
			});
	}

	protected void addIntTable() throws SQLException {
		addTable(int.class, new Object[] {
			new Integer(1),
			new Integer(11),
			new Integer(3),
			new Integer(14),
			new Integer(3),
			});
	}

	protected void addDateTable() throws SQLException {
		addTable(Date.class, new Object[] {
			"'2000-12-03'",
			"'2001-01-08'",
			"'2002-08-02'",
			"'2004-02-09'",
			"'2002-08-02'",
			});
	}

	protected void addBigDecimalTable() throws SQLException {
		addTable(BigDecimal.class, new Object[] {
			"11.0",
			"3.876",
			"17.573",
			"23",
			"17.573",
			});
	}

	public void addTable(Class type, Object[] values) throws SQLException {
		addTable(new Class[] {type}, new Object[] {values});
	}

	public void addTable(Class type1, Class type2, Object[] values1, Object[] values2) throws SQLException {
		addTable(new Class[] {type1, type2}, new Object[] {values1, values2});
	}

	public void addTable(Class type1, Class type2, Class type3, 
			             Object[] values1, Object[] values2, Object[] values3) throws SQLException {
		addTable(new Class[] {type1, type2, type2}, new Object[] {values1, values2, values3});
	}

	public void select(String value) throws Exception {
		select(value, '=');
	}

	public void select(String value, char op) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test WHERE field1 " + op + " " + value);
	}

	public void selectOr(String value1, String value2) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test WHERE field1 = " + value1 + " OR field1 = " + value2);
	}

	public void selectOr(String value1, String value2, String value3) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test WHERE field1 = " + value1 + " OR field1 = " + value2 + " OR field1 = " + value3);
	}

	public void selectAnd(String value1, String value2) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test WHERE field1 = " + value1 + " AND field2 = " + value2);
	}

	public void selectAnd(String value1, String value2, String value3) throws Exception {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM test WHERE field1 = " + value1 + " AND field2 = " + value2 + " AND field3 = " + value3);
	}

	public void verify(String expected) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected, rs.getString(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(String expected1, String expected2) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, rs.getString(1));
		
		assertTrue(rs.next());
		assertEquals(expected2, rs.getString(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(String expected1, String expected2, String expected3) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, rs.getString(1));
		
		assertTrue(rs.next());
		assertEquals(expected2, rs.getString(1));
		
		assertTrue(rs.next());
		assertEquals(expected3, rs.getString(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(String[] expected1, String[] expected2) throws Exception {
		
		assertEquals(expected1.length, rs.getMetaData().getColumnCount());
		
		for(int i = 1; i <= expected1.length; i++) {		
			assertEquals("field" + i, rs.getMetaData().getColumnName(i));
		}
		
		assertTrue(rs.next());

		for(int i = 0; i < expected1.length; i++) {		
			assertEquals(expected1[i], rs.getString(i + 1));
		}
		
		assertTrue(rs.next());

		for(int i = 0; i < expected1.length; i++) {		
			assertEquals(expected2[i], rs.getString(i + 1));
		}
		
		assertTrue(!rs.next());
		
	}

	public void verify(String[] expected1, String[] expected2, String[] expected3) throws Exception {
		
		assertEquals(expected1.length, rs.getMetaData().getColumnCount());
		
		for(int i = 1; i <= expected1.length; i++) {		
			assertEquals("field" + i, rs.getMetaData().getColumnName(i));
		}
		
		assertTrue(rs.next());

		for(int i = 0; i < expected1.length; i++) {		
			assertEquals(expected1[i], rs.getString(i + 1));
		}
		
		assertTrue(rs.next());

		for(int i = 0; i < expected1.length; i++) {		
			assertEquals(expected2[i], rs.getString(i + 1));
		}
		
		assertTrue(rs.next());

		for(int i = 0; i < expected1.length; i++) {		
			assertEquals(expected3[i], rs.getString(i + 1));
		}
		
		assertTrue(!rs.next());
		
	}

	public void verify(Integer expected) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected, new Integer(rs.getInt(1)));
		
		assertTrue(!rs.next());
		
	}

	public void verify(Integer expected1, Integer expected2) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, new Integer(rs.getInt(1)));
		
		assertTrue(rs.next());
		assertEquals(expected2, new Integer(rs.getInt(1)));
		
		assertTrue(!rs.next());
		
	}
	
	public void verify(int expected) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected, rs.getInt(1));
		
		assertTrue(!rs.next());
		
	}
	
	public void verify(int expected1, int expected2) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, rs.getInt(1));
		
		assertTrue(rs.next());
		assertEquals(expected2, rs.getInt(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(Date expected) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected, rs.getDate(1));
		
		assertTrue(!rs.next());
		
	}
	
	public void verify(Date expected1, Date expected2) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, rs.getDate(1));
		
		assertTrue(rs.next());
		assertEquals(expected2, rs.getDate(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(BigDecimal expected) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected, rs.getBigDecimal(1));
		
		assertTrue(!rs.next());
		
	}

	public void verify(BigDecimal expected1, BigDecimal expected2) throws Exception {
		
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field1", rs.getMetaData().getColumnName(1));
		
		assertTrue(rs.next());
		assertEquals(expected1, rs.getBigDecimal(1));
		
		assertTrue(rs.next());
		assertEquals(expected2, rs.getBigDecimal(1));
		
		assertTrue(!rs.next());
		
	}

	protected void setUp() throws Exception {

		createDatabase();
		openConnection();
		
		db = getDatabase();
	}

	protected void tearDown() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (conn != null) conn.close();
	}	
		
	void addTable(Class[] types, Object[] data) throws SQLException {

		HashTable table = new HashTable();
		
		table.setName("test");
		
		for (int i = 0; i < types.length; i++) {
			table.addColumn("field" + (i+1), types[i]);
		}
		
		db.add(table);
		
		stmt = conn.createStatement();
		
		StringBuffer sql = new StringBuffer();

		int rows = ((Object[]) data[0]).length;
		
		for (int i = 0; i < rows; i++) {
			
			sql.setLength(0);
			
			sql.append("insert into test (");
			
			for (int j = 0; j < data.length; j++) {

				Object value = ((Object[]) data[j])[i];
				
				if (j > 0) sql.append(",");
				
				sql.append(value);
				
			}
			
			sql.append(")");

			stmt.execute(sql.toString());
		}
		
		stmt.close();
		
		stmt = null;
	}

	HashDatabase db;	
	Statement stmt;
	ResultSet rs;

}
