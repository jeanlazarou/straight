/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;

import com.ap.straight.util.*;

public class BasicTest extends HashDatabaseTestCase
{

    public void testCanCreateDatabase() throws SQLException
    {
        createDatabase("Test1");
        createDatabase("Test2");

        assertNotNull("Native access to Test1", HashContainer.get("Test1"));
        assertNotNull("Native access to Test2", HashContainer.get("Test2"));
        assertNull("Native failed access", HashContainer.get("Test3"));

        reset();
    }

    public void testCanConnect() throws SQLException
    {
        createDatabase();

        HashDriver driver = new HashDriver();

        DriverManager.registerDriver(driver);

        Connection con = DriverManager.getConnection("jdbc:ap:TestDatabase");

        assertNotNull("Get a JDBC connection", con);

        con.close();
        
        DriverManager.deregisterDriver(driver);

        reset();
        
    }

    public void createAndConnect()
    {
        try
        {
            DriverManager.registerDriver(new HashDriver());

            createDatabase();

            Connection con = DriverManager.getConnection("jdbc:ap:TestDatabase");
            
            con.close();
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
