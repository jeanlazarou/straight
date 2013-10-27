/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;

import java.util.Properties;
import java.util.logging.Logger;

public class HashDriver implements Driver
{
    static {

        try {
            HashDriver driver = new HashDriver();

            DriverManager.registerDriver(driver);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connect(String url, Properties info) throws SQLException
    {
        if (!acceptsURL(url)) return null;

        String dbname = url.substring(8);

        HashDatabase db = HashContainer.get(dbname);

        if (db == null)
        {
            throw new SQLException("Database " + dbname + " not found.");
        }

        return new HashConnection(db);
    }

    public boolean acceptsURL(String url) throws SQLException
    {
        return url.toLowerCase().startsWith("jdbc:ap:");
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException
    {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion()
    {
        return 1;
    }

    public int getMinorVersion()
    {
        return 0;
    }

    public boolean jdbcCompliant()
    {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

}