/*
 * @author Jean Lazarou
 * Date: Mar 2, 2004
 */
package com.ap.straight;

import java.sql.*;

import com.ap.straight.util.*;

public class PreparedSelectTest extends HashDatabaseTestCase
{
    int pids[] = {1, 2, 3};
    String lnames[] = {"LName1", "LName2", "LName3"};
    String fnames[] = {"FName1", "FName2", "FName3"};
    int pcountries[] = {1, 2, 2};

    int cids[] = {1, 2, 3};
    String cnames[] = {"Country1", "Country2", "Country3"};

    protected void setUp() throws Exception
    {
        createDatabase();
        openConnection();
        insertPersons();
        insertCountries();
    }

    protected void tearDown() throws Exception
    {
        reset();
        
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
    }

    public void testCanGetStatement() throws SQLException
    {
        assertNotNull(conn.prepareStatement("SELECT * FROM people"));
    }

    public void testWithIndexes() throws SQLException
    {
        int i = 0;
        int n = 3;

        stmt = conn.prepareStatement("SELECT * FROM persons");

        rs = stmt.executeQuery();

        while(rs.next())
        {
            assertEquals("Id", pids[i], rs.getInt(1));
            assertEquals("Last name", lnames[i], rs.getString(2));
            assertEquals("First name", fnames[i], rs.getString(3));
            assertEquals("Country", pcountries[i], rs.getInt(4));

            i++;
        }

        assertEquals("Number of results", n, i);
    }

    public void testWithNames() throws SQLException
    {
        int i = 0;
        int n = 3;

		stmt = conn.prepareStatement("SELECT * FROM persons");

		rs = stmt.executeQuery();

        while(rs.next())
        {
            assertEquals("Id", pids[i], rs.getInt("Id"));
            assertEquals("Last name", lnames[i], rs.getString("Lastname"));
            assertEquals("First name", fnames[i], rs.getString("firstName"));
            assertEquals("Country", pcountries[i], rs.getInt("LIVESIN"));

            i++;
        }

        assertEquals("Number of results", n, i);
    }

    public void testWithWhereClause() throws SQLException
    {
		stmt = conn.prepareStatement("SELECT id FROM persons WHERE livesin = ?");

		stmt.setInt(1, 2);
		
		rs = stmt.executeQuery();

        assertTrue("Row 1", rs.next());

        assertEquals("Id", pids[1], rs.getInt("Id"));

        assertTrue("Row 2", rs.next());

        assertEquals("Id", pids[2], rs.getInt("Id"));

        assertTrue("No more rows", !rs.next());
    }

    public void testCount() throws SQLException
    {
		stmt = conn.prepareStatement("SELECT COUNT(*) FROM persons WHERE livesin = ?");

		stmt.setInt(1, 2);
		
		rs = stmt.executeQuery();

		assertTrue("Exist one row in result set", rs.next());

		assertEquals("Count", 2, rs.getInt(1));

		assertTrue("No more rows", !rs.next());
    }

	public void testStringParameter() throws SQLException
	{
		stmt = conn.prepareStatement("SELECT * FROM persons WHERE firstName = ?");

		stmt.setString(1, "FName1");
		
		rs = stmt.executeQuery();

		assertTrue(rs.next());

		assertEquals(1, rs.getInt(1));
		assertEquals("LName1", rs.getString(2));
		assertEquals("FName1", rs.getString(3));
		assertEquals(1, rs.getInt(4));

		assertTrue(!rs.next());
	}
    
	ResultSet rs;
	PreparedStatement stmt;
}
