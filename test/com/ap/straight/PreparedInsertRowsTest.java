/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;

import com.ap.straight.util.*;

public class PreparedInsertRowsTest extends HashDatabaseTestCase
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

    public void testInsertRows() throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO persons (?, ?, ?, ?)");

		stmt.setInt(1, 1);
		stmt.setString(2, "LName1");
		stmt.setString(3, "FName1");
		stmt.setInt(4, 1);
		stmt.executeUpdate();
		
		stmt.setInt(4, 1);
		stmt.setString(3, "FName2");
		stmt.setString(2, "LName2");
		stmt.setInt(1, 2);
		stmt.executeUpdate();
		
		stmt.setString(3, "FName3");
		stmt.setInt(1, 3);
		stmt.setInt(4, 1);
		stmt.setString(2, "LName3");
		stmt.executeUpdate();

        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM persons");

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
        
        stmt.close();
        
        rs.close();
        rs.getStatement().close();
    }
}
