/*
 * @author: Jean Lazarou
 * @date: 17 Feb 2004
 */
package com.ap.straight;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Iterator;


/**
 * A <code>MemoryResultSetMetaData</code> gives all the meta data (information) of a result set
 *
 * @see com.ap.jdbc.ColumnMetaData
 */
public class MemoryResultSetMetaData implements ResultSetMetaData {

    /**
     * @param columns is a list of ColumnMetaData
     */
    public MemoryResultSetMetaData(List columns) {

        this.columns = new ColumnMetaData[columns.size()];

        Iterator it = columns.iterator();

        for(int i = 0; it.hasNext(); i++) {
            this.columns[i] = (ColumnMetaData) it.next();
        }
    }

    public MemoryResultSetMetaData(ColumnMetaData[] columns) {
        this.columns = columns;
    }

    public int getColumnCount() throws SQLException {
        return columns.length;
    }

    public boolean isAutoIncrement(int column) throws SQLException {
        return columns[column - 1].isAutoIncrement();
    }

    public boolean isCaseSensitive(int column) throws SQLException {
        return columns[column - 1].isCaseSensitive();
    }

    public boolean isSearchable(int column) throws SQLException {
        return columns[column - 1].isSearchable();
    }

    public boolean isCurrency(int column) throws SQLException {
        return columns[column - 1].isCurrency();
    }

    public int isNullable(int column) throws SQLException {
        return columns[column - 1].isNullable();
    }

    public boolean isSigned(int column) throws SQLException {
        return columns[column - 1].isSigned();
    }

    public int getColumnDisplaySize(int column) throws SQLException {
        return columns[column - 1].getColumnDisplaySize();
    }

    public String getColumnLabel(int column) throws SQLException {
        return columns[column - 1].getColumnLabel();
    }

    public String getColumnName(int column) throws SQLException {
        return columns[column - 1].getColumnName();
    }

    public String getSchemaName(int column) throws SQLException {
        return columns[column - 1].getSchemaName();
    }

    public int getPrecision(int column) throws SQLException {
        return columns[column - 1].getPrecision();
    }

    public int getScale(int column) throws SQLException {
        return columns[column - 1].getScale();
    }

    public String getTableName(int column) throws SQLException {
        return columns[column - 1].getTableName();
    }

    public String getCatalogName(int column) throws SQLException {
        return columns[column - 1].getCatalogName();
    }

    public int getColumnType(int column) throws SQLException {
        return columns[column - 1].getColumnType();
    }

    public String getColumnTypeName(int column) throws SQLException {
        return columns[column - 1].getColumnTypeName();
    }

    public boolean isReadOnly(int column) throws SQLException {
        return columns[column - 1].isReadOnly();
    }

    public boolean isWritable(int column) throws SQLException {
        return columns[column - 1].isWritable();
    }

    public boolean isDefinitelyWritable(int column) throws SQLException {
        return columns[column - 1].isDefinitelyWritable();
    }

    public String getColumnClassName(int column) throws SQLException {
        return columns[column - 1].getColumnClassName();
    }

    //----------------------- interface Wrapper ----------------------------------------

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    ColumnMetaData[] columns;
}
