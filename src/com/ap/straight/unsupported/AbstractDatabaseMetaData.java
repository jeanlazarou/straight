package com.ap.straight.unsupported;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

public abstract class AbstractDatabaseMetaData implements DatabaseMetaData {

    public int getDatabaseMajorVersion() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getDatabaseMinorVersion() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getJDBCMajorVersion() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getJDBCMinorVersion() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getResultSetHoldability() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getSQLStateType() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean locatorsUpdateCopy() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsGetGeneratedKeys() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsMultipleOpenResults() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsNamedParameters() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsSavepoints() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsStatementPooling() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public RowIdLifetime getRowIdLifetime() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getClientInfoProperties() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean generatedKeyAlwaysReturned() throws SQLException {
        throw new UnsupportedOperationException();
    }

}
