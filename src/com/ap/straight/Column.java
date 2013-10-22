/*
 * @author Jean Lazarou
 * Date: April 27, 2002
 */
package com.ap.straight;

import java.util.Vector;

import java.sql.SQLException;

import com.ap.straight.types.Types;
import com.ap.straight.types.JavaToSQL;
import com.ap.straight.types.TypeConverter;

public class Column implements ColumnMetaData
{
    Class type;
    String name;
	TypeConverter converter;
    Vector values = new Vector();

    HashTable table;

    Column (HashTable table, String name, Class type)
    {
        this.name = name.toLowerCase();
        this.type = type;
        this.table = table;
        
        converter = Types.getConverter(type);
    }

    public void add(Object value) throws SQLException
    {
        values.add(convertValue(value));
    }

    public Object get(int index) throws SQLException
    {
        return values.get(index);
    }

    public void put(int index, Object value) throws SQLException
    {
        values.set(index, convertValue(value));
    }

    public int size() throws SQLException
    {
        return values.size();
    }

    private Object convertValue(Object value) throws SQLException
    {
        if (value.getClass() == type)
        {
            return value;
        }
        
        if (value instanceof String)
        {
            String sval = (String) value;

            return converter.convert(sval);
        }
        else if (value instanceof Integer)
        {
            Integer ival = (Integer) value;

            return ival.toString();
        }

        throw new SQLException ("Column " + name + " cannot convert from " + value.getClass().toString() + " to " + type.toString());
    }

    //----- ColumnMetaData interface -----------------------------------------------------------------------------------

    public boolean isAutoIncrement() {
        return false;
    }

    public boolean isCaseSensitive() {
        return false;
    }

    public boolean isSearchable() {
        return true;
    }

    public boolean isCurrency() {
        return false;
    }

    public int isNullable() {
        return java.sql.ResultSetMetaData.columnNullable;
    }

    public boolean isSigned() {
        return type.isAssignableFrom(Number.class);
    }

    public int getColumnDisplaySize() {
        return 0;
    }

    public String getColumnLabel() {
        return getColumnName();
    }

    public String getColumnName() {
        return name;
    }

    public String getSchemaName() {
        return "";
    }

    public int getPrecision() {
        return 0;
    }

    public int getScale() {
        return 0;
    }

    public String getTableName() {
        return table == null ? "" : table.getName();
    }

    public String getCatalogName() {
        return "";
    }

    public int getColumnType() {
		return JavaToSQL.getType(type);
    }

    public String getColumnTypeName() {
        return null;
    }

    public boolean isReadOnly() {
        return false;
    }

    public boolean isWritable() {
        return true;
    }

    public boolean isDefinitelyWritable() {
        return true;
    }

    public String getColumnClassName() {
        return type.getName();
    }
}
