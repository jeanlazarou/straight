package com.ap.straight.unsupported;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.Executor;

public abstract class AbstractConnection implements Connection {

    public CallableStatement prepareCall(String sql) throws SQLException
    {
        throw new UnsupportedOperationException("Connection.prepareCall");
    }

    public String nativeSQL(String sql) throws SQLException
    {
        throw new UnsupportedOperationException("Connection.nativeSQL");
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException
    {
        throw new UnsupportedOperationException("Transactions not supported");
    }

    public void setReadOnly(boolean readOnly) throws SQLException
    {
        throw new UnsupportedOperationException("Connection.setReadOnly");
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException
    {
        throw new UnsupportedOperationException("Connection.prepareCall");
    }

    public void setHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Clob createClob() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Blob createBlob() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public NClob createNClob() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean isValid(int timeout) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        throw new UnsupportedOperationException();
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        throw new UnsupportedOperationException();
    }

    public String getClientInfo(String name) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Properties getClientInfo() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setSchema(String schema) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void abort(Executor executor) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }

}
