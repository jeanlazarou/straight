/*
 * @author Jean Lazarou Date: May 17, 2005
 */
package com.ap.straight;

import java.sql.*;

import com.ap.straight.util.*;

public class DeleteTest extends HashDatabaseTestCase {

	protected void setUp() throws Exception {
		createDatabase();
		openConnection();
		insertPersons();
		insertCountries();
	}

	protected void tearDown() throws Exception {
		reset();

		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
	}

	public void testConditional() throws SQLException {

		stmt = conn.createStatement();

		int count = stmt.executeUpdate("DELETE FROM persons WHERE id = 1");

		assertEquals(1, count);
		
		rs = stmt.executeQuery("SELECT COUNT(*) FROM persons WHERE id = 1");
		
		rs.next();
		
		assertEquals(0, rs.getInt(1));
		
	}

	public void testDeleteAll() throws SQLException {

		stmt = conn.createStatement();

		int count = stmt.executeUpdate("DELETE FROM persons");

		assertEquals(3, count);
		
		rs = stmt.executeQuery("SELECT COUNT(*) FROM persons");
		
		rs.next();
		
		assertEquals(0, rs.getInt(1));
		
	}

	public void testNothing() throws SQLException {

		stmt = conn.createStatement();

		int count = stmt.executeUpdate("DELETE FROM persons WHERE id = 666");

		assertEquals(0, count);
		
		rs = stmt.executeQuery("SELECT COUNT(*) FROM persons");
		
		rs.next();
		
		assertEquals(3, rs.getInt(1));
		
	}

	ResultSet rs;
	Statement stmt;

}
