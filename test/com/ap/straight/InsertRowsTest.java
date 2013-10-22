/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;

import com.ap.straight.util.*;

public class InsertRowsTest extends HashDatabaseTestCase
{
	
    protected void setUp() throws Exception
    {
        createDatabase();
        openConnection();
    }

    protected void tearDown() throws Exception
    {
        reset();
    }

    public void testCanGetStatement() throws SQLException
    {
        assertNotNull("Get as Statement object", conn.createStatement());
    }

    public void testInsertRows() throws SQLException
    {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO persons (1, 'LName1', 'FName1', 1)");
        stmt.executeUpdate("INSERT INTO persons (2, 'LName2', 'FName2', 1)");
        stmt.executeUpdate("INSERT INTO persons (3, 'LName3', 'FName3', 1)");
    }

    public void testInsertRowsAndCheck() throws SQLException
    {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO persons (1, 'LName1', 'FName1', 1)");
        stmt.executeUpdate("INSERT INTO persons (2, 'LName2', 'FName2', 1)");
        stmt.executeUpdate("INSERT INTO persons (3, 'LName3', 'FName3', 1)");

        ResultSet rs = stmt.executeQuery("SELECT * FROM persons");

        assertNotNull("Result set", rs);

        assertTrue("Record set is not empty", rs.next());

        assertEquals("Id", "1", rs.getString(1));
        assertEquals("Last name", "LName1", rs.getString(2));
        assertEquals("First name", "FName1", rs.getString(3));
        assertEquals("Country", "1", rs.getString(4));

        assertTrue("Record set is not empty", rs.next());

        assertEquals("Id", "2", rs.getString(1));
        assertEquals("Last name", "LName2", rs.getString(2));
        assertEquals("First name", "FName2", rs.getString(3));
        assertEquals("Country", "1", rs.getString(4));

        assertTrue("Record set is not empty", rs.next());

        assertEquals("Id", "3", rs.getString(1));
        assertEquals("Last name", "LName3", rs.getString(2));
        assertEquals("First name", "FName3", rs.getString(3));
        assertEquals("Country", "1", rs.getString(4));
    }
}
