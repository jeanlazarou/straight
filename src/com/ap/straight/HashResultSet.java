/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.math.BigDecimal;
import java.net.URL;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Calendar;
import java.util.Iterator;

public class HashResultSet implements ResultSet
{
    Column fields[];
    HashTable table;
    Iterator values;
    Statement stmt;

    // 0 is before first, 1 is after last, 2 is first, 3 is last
    int position = 0;
    int currentIndex = -1;
    boolean nextCalled = false;
    boolean lastValuetWasNull = false;

    public HashResultSet(Statement stmt, Iterator col, HashTable table, Column fields[])
    {
        this.stmt = stmt;
        this.values = col;
        this.table = table;
        this.fields = fields;
    }

    public boolean next() throws SQLException
    {
        nextCalled = true;

        if (values.hasNext())
        {
            if (position == 0) position = 2;

            currentIndex = ((Integer)values.next()).intValue();

            if (position == 1 && !values.hasNext()) position = 3;

            return true;
        }
        else
        {
            position = 1;
            currentIndex = -1;
            return false;
        }
    }

    public void close() throws SQLException
    {
    }

    public boolean wasNull() throws SQLException
    {
        return lastValuetWasNull;
    }

    public String getString(int columnIndex) throws SQLException
    {
        return convertToString(getValue(columnIndex));
    }

    public boolean getBoolean(int columnIndex) throws SQLException
    {
        return getInt(columnIndex) != 0;
    }

    public byte getByte(int columnIndex) throws SQLException
    {
        return (byte) getInt(columnIndex);
    }

    public short getShort(int columnIndex) throws SQLException
    {
        return (short) getInt(columnIndex);
    }

    public int getInt(int columnIndex) throws SQLException
    {
        return convertToInt(getValue(columnIndex));
    }

    public long getLong(int columnIndex) throws SQLException
    {
        return getInt(columnIndex);
    }

    public float getFloat(int columnIndex) throws SQLException
    {
        return getInt(columnIndex);
    }

    public double getDouble(int columnIndex) throws SQLException
    {
        return getInt(columnIndex);
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException
    {
    	return getBigDecimal(getValue(columnIndex), scale);
    }

    public byte[] getBytes(int columnIndex) throws SQLException
    {
        return getString(columnIndex).getBytes();
    }

    public Date getDate(int columnIndex) throws SQLException
    {
        return convertToDate(getValue(columnIndex));
    }

    public Time getTime(int columnIndex) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException
    {
        return null;
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException
    {
        return null;
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException
    {
        return null;
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException
    {
        return null;
    }

    public String getString(String columnName) throws SQLException
    {
        return convertToString(getValue(columnName));
    }

    public boolean getBoolean(String columnName) throws SQLException
    {
        return getInt(columnName) != 0;
    }

    public byte getByte(String columnName) throws SQLException
    {
        return (byte) getInt(columnName);
    }

    public int getInt(String columnName) throws SQLException
    {
        return convertToInt(getValue(columnName));
    }

    public long getLong(String columnName) throws SQLException
    {
        return getInt(columnName);
    }

    public float getFloat(String columnName) throws SQLException
    {
        return getInt(columnName);
    }

    public double getDouble(String columnName) throws SQLException
    {
        return getInt(columnName);
    }

    public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException
    {
		return getBigDecimal(getValue(columnName), scale);
    }

    public byte[] getBytes(String columnName) throws SQLException
    {
        return getString(columnName).getBytes();
    }

    public Date getDate(String columnName) throws SQLException
    {
		return convertToDate(getValue(columnName));
    }

    public Time getTime(String columnName) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp(String columnName) throws SQLException
    {
        return null;
    }

    public InputStream getAsciiStream(String columnName) throws SQLException
    {
        return null;
    }

    public InputStream getUnicodeStream(String columnName) throws SQLException
    {
        return null;
    }

    public InputStream getBinaryStream(String columnName) throws SQLException
    {
        return null;
    }

    public SQLWarning getWarnings() throws SQLException
    {
        return null;
    }

    public void clearWarnings() throws SQLException
    {
    }

    public String getCursorName() throws SQLException
    {
        return null;
    }

    public ResultSetMetaData getMetaData() throws SQLException
    {
        return new MemoryResultSetMetaData(fields);
    }

    public Object getObject(int columnIndex) throws SQLException
    {
        return getValue(columnIndex);
    }

    public Object getObject(String columnName) throws SQLException
    {
        return getValue(columnName);
    }

    public int findColumn(String columnName) throws SQLException
    {
        columnName = columnName.toLowerCase();

        for (int i = 0; i < fields.length; i++)
        {
            if (columnName.equals(fields[i].name))
            {
                return i + 1;
            }
        }

        throw new SQLException("Column " + columnName + " not found");
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException
    {
        return null;
    }

    public Reader getCharacterStream(String columnName) throws SQLException
    {
        return null;
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException
    {
		return getBigDecimal(getValue(columnIndex), -1);
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException
    {
		return getBigDecimal(getValue(columnName), -1);
    }

    public boolean isBeforeFirst() throws SQLException
    {
        return position == 0;
    }

    public boolean isAfterLast() throws SQLException
    {
        return position == 1;
    }

    public boolean isFirst() throws SQLException
    {
        return position == 2;
    }

    public boolean isLast() throws SQLException
    {
        return position == 3;
    }

    public void beforeFirst() throws SQLException
    {
    }

    public void afterLast() throws SQLException
    {
    }

    public boolean first() throws SQLException
    {
        return false;
    }

    public boolean last() throws SQLException
    {
        return false;
    }

    public int getRow() throws SQLException
    {
        return currentIndex;
    }

    public boolean absolute(int row) throws SQLException
    {
        return false;
    }

    public boolean relative(int rows) throws SQLException
    {
        return false;
    }

    public boolean previous() throws SQLException
    {
        return false;
    }

    public void setFetchDirection(int direction) throws SQLException
    {
    }

    public int getFetchDirection() throws SQLException
    {
        return 0;
    }

    public void setFetchSize(int rows) throws SQLException
    {
    }

    public int getFetchSize() throws SQLException
    {
        return 0;
    }

    public int getType() throws SQLException
    {
        return 0;
    }

    public int getConcurrency() throws SQLException
    {
        return 0;
    }

    public boolean rowUpdated() throws SQLException
    {
        return false;
    }

    public boolean rowInserted() throws SQLException
    {
        return false;
    }

    public boolean rowDeleted() throws SQLException
    {
        return false;
    }

    public void updateNull(int columnIndex) throws SQLException
    {
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException
    {
    }

    public void updateByte(int columnIndex, byte x) throws SQLException
    {
    }

    public void updateShort(int columnIndex, short x) throws SQLException
    {
    }

    public void updateInt(int columnIndex, int x) throws SQLException
    {
    }

    public void updateLong(int columnIndex, long x) throws SQLException
    {
    }

    public void updateFloat(int columnIndex, float x) throws SQLException
    {
    }

    public void updateDouble(int columnIndex, double x) throws SQLException
    {
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException
    {
    }

    public void updateString(int columnIndex, String x) throws SQLException
    {
    }

    public void updateBytes(int columnIndex, byte x[]) throws SQLException
    {
    }

    public void updateDate(int columnIndex, Date x) throws SQLException
    {
    }

    public void updateTime(int columnIndex, Time x) throws SQLException
    {
    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException
    {
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException
    {
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException
    {
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException
    {
    }

    public void updateObject(int columnIndex, Object x, int scale) throws SQLException
    {
    }

    public void updateObject(int columnIndex, Object x) throws SQLException
    {
    }

    public void updateNull(String columnName) throws SQLException
    {
    }

    public void updateBoolean(String columnName, boolean x) throws SQLException
    {
    }

    public void updateByte(String columnName, byte x) throws SQLException
    {
    }

    public void updateShort(String columnName, short x) throws SQLException
    {
    }

    public void updateInt(String columnName, int x) throws SQLException
    {
    }

    public void updateLong(String columnName, long x) throws SQLException
    {
    }

    public void updateFloat(String columnName, float x) throws SQLException
    {
    }

    public void updateDouble(String columnName, double x) throws SQLException
    {
    }

    public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException
    {
    }

    public void updateString(String columnName, String x) throws SQLException
    {
    }

    public void updateBytes(String columnName, byte x[]) throws SQLException
    {
    }

    public void updateDate(String columnName, Date x) throws SQLException
    {
    }

    public void updateTime(String columnName, Time x) throws SQLException
    {
    }

    public void updateTimestamp(String columnName, Timestamp x) throws SQLException
    {
    }

    public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException
    {
    }

    public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException
    {
    }

    public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException
    {
    }

    public void updateObject(String columnName, Object x, int scale) throws SQLException
    {
    }

    public void updateObject(String columnName, Object x) throws SQLException
    {
    }

    public void insertRow() throws SQLException
    {
    }

    public void updateRow() throws SQLException
    {
    }

    public void deleteRow() throws SQLException
    {
    }

    public void refreshRow() throws SQLException
    {
    }

    public void cancelRowUpdates() throws SQLException
    {
    }

    public void moveToInsertRow() throws SQLException
    {
    }

    public void moveToCurrentRow() throws SQLException
    {
    }

    public Statement getStatement() throws SQLException
    {
        return stmt;
    }

    public Object getObject(int i, Map map) throws SQLException
    {
        return null;
    }

    public Ref getRef(int i) throws SQLException
    {
        return null;
    }

    public Blob getBlob(int i) throws SQLException
    {
        return null;
    }

    public Clob getClob(int i) throws SQLException
    {
        return null;
    }

    public Array getArray(int i) throws SQLException
    {
        return null;
    }

    public Object getObject(String colName, Map map) throws SQLException
    {
        return null;
    }

    public Ref getRef(String colName) throws SQLException
    {
        return null;
    }

    public Blob getBlob(String colName) throws SQLException
    {
        return null;
    }

    public Clob getClob(String colName) throws SQLException
    {
        return null;
    }

    public Array getArray(String colName) throws SQLException
    {
        return null;
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException
    {
		return convertToDate(getValue(columnIndex));
    }

    public Date getDate(String columnName, Calendar cal) throws SQLException
    {
		return convertToDate(getValue(columnName));
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException
    {
        return null;
    }

    public Time getTime(String columnName, Calendar cal) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException
    {
        return null;
    }

    public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException
    {
        return null;
    }

    public short getShort(String columnName) throws SQLException
    {
        return 0;
    }

    private Object getValue(int columnIndex) throws SQLException
    {
        if (currentIndex == -1)
        {
            throw new SQLException("No current row");
        }
        if (!nextCalled)
        {
            throw new SQLException("Next was not called");
        }
        if (columnIndex < 1 || columnIndex > fields.length)
        {
            throw new SQLException("Invalid column index");
        }

        return fields[columnIndex - 1].get(currentIndex);
    }

    private Object getValue(String columnname) throws SQLException
    {
        if (currentIndex == -1)
        {
            throw new SQLException("No current row");
        }
        if (!nextCalled)
        {
            throw new SQLException("Next was not called");
        }

        Column col = table.getColumn(columnname);

        if (col == null)
        {
            throw new SQLException("Column " + columnname + " not found");
        }

        return col.get(currentIndex);
    }

    private String convertToString(Object o)
    {
        if (o == null)
        {
            lastValuetWasNull = true;

            return "";
        }
        else
        {
            return o.toString();
        }
    }

    private int convertToInt(Object o)
    {
        if (o == null)
        {
            lastValuetWasNull = true;

            return 0;
        }
        else if (o instanceof Integer)
        {
            return ((Integer) o).intValue();
        }
        else if (o instanceof String)
        {
            return Integer.parseInt(((String) o));
        }

        return 0;
    }

	private Date convertToDate(Object o) {

		if (o == null) {
			lastValuetWasNull = true;
			return null;
		} else if (o instanceof Date) {
			return (Date) o;
		}

		try {
			java.util.Date d = DateFormat.getInstance().parse(o.toString());		
			return new Date(d.getTime());
		} catch (ParseException e) {
			
			long time = Long.parseLong(o.toString());
			
			return new Date(time);

		}

	}
	
	private BigDecimal getBigDecimal(Object value, int scale) throws SQLException
	{
		if (value instanceof BigDecimal) {
			
			BigDecimal x = (BigDecimal) value;
			
			if (scale == -1 || x.scale() == scale) {
				return x;
			} else {
				return new BigDecimal(x.toBigInteger(), scale);
			}
			
		} else {

			if (scale == -1) {
				return BigDecimal.valueOf(convertToInt(value));
			} else {
				return BigDecimal.valueOf(convertToInt(value), scale);
			}

		}
	}

	public URL getURL(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateArray(int columnIndex, Array x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateClob(int columnIndex, Clob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateRef(int columnIndex, Ref x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public URL getURL(String columnName) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateArray(String columnName, Array x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateBlob(String columnName, Blob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateClob(String columnName, Clob x) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void updateRef(String columnName, Ref x) throws SQLException {
		throw new UnsupportedOperationException();
	}
	
}
