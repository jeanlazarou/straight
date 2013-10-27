/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import com.ap.straight.unsupported.AbstractConnection;

import java.sql.*;
import java.util.Map;

public class HashConnection extends AbstractConnection
{
    Map map;

    HashDatabase db;

    public HashConnection(HashDatabase db)
    {
        this.db = db;
    }

    public Statement createStatement() throws SQLException
    {
        return createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException
    {
        return prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public boolean getAutoCommit() throws SQLException
    {
        return true;
    }

    public void commit() throws SQLException
    {
    }

    public void rollback() throws SQLException
    {
    }

    public void close() throws SQLException
    {
    }

    public boolean isClosed() throws SQLException
    {
        return false;
    }

    public DatabaseMetaData getMetaData() throws SQLException
    {
        return new HashDatabaseMetaData("jdbc:ap:" + db.getName());
    }

    public boolean isReadOnly() throws SQLException
    {
        return false;
    }

    public void setCatalog(String catalog) throws SQLException
    {
    }

    public String getCatalog() throws SQLException
    {
        return null;
    }

    public void setTransactionIsolation(int level) throws SQLException
    {
    }

    public int getTransactionIsolation() throws SQLException
    {
        return TRANSACTION_NONE;
    }

    public SQLWarning getWarnings() throws SQLException
    {
        return null;
    }

    public void clearWarnings() throws SQLException
    {
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException
    {
        return new HashStatement(this);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        return new HashPreparedStatement(this, sql);
    }

    public Map getTypeMap() throws SQLException
    {
        return map;
    }

    public void setTypeMap(Map map) throws SQLException
    {
        this.map = map;
    }

    //----------------------- interface Wrapper ----------------------------------------

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

}
