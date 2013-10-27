/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight.util;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

import junit.framework.TestCase;

import com.ap.straight.HashDatabase;
import com.ap.straight.HashContainer;
import com.ap.straight.HashTable;

public class HashDatabaseTestCase extends TestCase
{
    protected Connection conn;

	public void createDatabase() throws SQLException
	{
		createDatabase("TestDatabase");
	}

	public HashDatabase getDatabase() throws SQLException
	{
		return HashContainer.get("TestDatabase");
	}

    public void createDatabase(String name) throws SQLException
    {
        HashDatabase db = new HashDatabase();

        db.setName(name);

        HashContainer.add(db);

        HashTable table;

        table = new HashTable();
        table.setName("Persons");

        table.addColumn("Id", Integer.class);
        table.addColumn("LastName", String.class);
        table.addColumn("FirstName", String.class);
        table.addColumn("LivesIn", Integer.class);

        db.add(table);

        table = new HashTable();
        table.setName("Countries");

        table.addColumn("Id", Integer.class);
        table.addColumn("Name", String.class);

        db.add(table);
    }

    public void openConnection() throws SQLException
    {
        conn = DriverManager.getConnection("jdbc:ap:TestDatabase");
    }

    public void reset()
    {
        HashContainer.clear();
    }

    public void insertPersons () throws SQLException
    {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO persons VALUES (1, 'LName1', 'FName1', 1)");
        stmt.executeUpdate("INSERT INTO persons VALUES (2, 'LName2', 'FName2', 2)");
        stmt.executeUpdate("INSERT INTO persons VALUES (3, 'LName3', 'FName3', 2)");
    }

    public void insertCountries () throws SQLException
    {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("INSERT INTO countries VALUES (1, 'Country1')");
        stmt.executeUpdate("INSERT INTO countries VALUES (2, 'Country2')");
        stmt.executeUpdate("INSERT INTO countries VALUES (3, 'Country3')");
    }
}
