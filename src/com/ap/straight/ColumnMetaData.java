/*
 * @author: Jean Lazarou
 * @date: 17 févr. 04
 */
package com.ap.straight;

/**
 * The methods in this class macth the definition of <code>MemoryResultSetMetaData</code>
 *
 * @see java.sql.MemoryResultSetMetaData
 */
public interface ColumnMetaData {

    boolean isAutoIncrement();
    boolean isCaseSensitive();
    boolean isSearchable();
    boolean isCurrency();
    int isNullable();
    boolean isSigned();
    int getColumnDisplaySize();
    String getColumnLabel();
    String getColumnName();
    String getSchemaName();
    int getPrecision();
    int getScale();
    String getTableName();
    String getCatalogName();
    int getColumnType();
    String getColumnTypeName();
    boolean isReadOnly();
    boolean isWritable();
    boolean isDefinitelyWritable();
    String getColumnClassName();

}
