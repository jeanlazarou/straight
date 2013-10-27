/*
 * @author Jean Lazarou
 * Date: June 3, 2004
 */
package com.ap.straight.types;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ap.straight.HashDatabase;
import com.ap.straight.HashTable;

import com.ap.straight.util.DateFactory;
import com.ap.straight.util.HashDatabaseTestCase;


public class TestInsert extends HashDatabaseTestCase {

	public void testStringField() throws Exception {
		addTable(String.class);
		
		insert("'Name1'");
		verify("Name1");
	}

	public void testIntegerField() throws Exception {
		addTable(Integer.class);
		
		insert("1751");
		verify(new Integer(1751));
	}

	public void testIntField() throws Exception {
		addTable(int.class);
		
		insert("1751");
		verify(1751);
	}
	
	public void testDateField() throws Exception {
		addTable(Date.class);
		
		insert("'2004-05-17'");
		verify(DateFactory.newDate(2004, 5, 17));
	}
	
	public void testBigDecimalField() throws Exception {
		addTable(BigDecimal.class);
		
		insert("17.573");
		verify(new BigDecimal("17.573"));
	}

	void addTable(Class type) throws SQLException {

		HashTable table = new HashTable();
		
		table.setName("test");
		
		table.addColumn("field", type);
		
		db.add(table);
		
	}

	private void insert(String value) throws Exception {
		stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO test VALUES (" + value + ")");
	}

	public void verify(String expected) throws Exception {
		
		verify();
		
		assertEquals(expected, rs.getString(1));
		assertTrue(!rs.next());
		
	}

	public void verify(Integer expected) throws Exception {
		
		verify();
		
		assertEquals(expected, rs.getObject(1));
		assertTrue(!rs.next());
		
	}

	public void verify(int expected) throws Exception {
		
		verify();
		
		assertEquals(expected, rs.getInt(1));
		assertTrue(!rs.next());
		
	}

	public void verify(BigDecimal expected) throws Exception {
		
		verify();
		
		assertEquals(expected, rs.getBigDecimal(1));
		assertTrue(!rs.next());
		
	}

	public void verify(Date expected) throws Exception {
		
		verify();
		
		assertEquals(expected, rs.getDate(1));
		assertTrue(!rs.next());
		
	}

	public void verify() throws Exception {
		
		rs = stmt.executeQuery("SELECT * FROM test");

		assertTrue(rs.next());
		assertEquals(1, rs.getMetaData().getColumnCount());		
		assertEquals("field", rs.getMetaData().getColumnName(1));
		
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

	HashDatabase db;	
	Statement stmt;
	ResultSet rs;

}
