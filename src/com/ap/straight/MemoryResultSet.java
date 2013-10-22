package com.ap.straight;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.math.BigDecimal;
import java.net.URL;
import java.io.InputStream;
import java.io.Reader;


public class MemoryResultSet implements ResultSet {

    List row;
    int currentIndex;
    boolean lastValuetWasNull;

    Statement stmt;

    Iterator values;
    String columns[];

    ResultSetMetaData meta;

    /**
     * @param data a list that contains all the rows of the result, the first row is the result set's columns names,
     *             each row in given list (each the element) must be a <tt>List</tt> object
     */
    public MemoryResultSet(List data) {
        this(null, data.iterator());
    }

    public MemoryResultSet(Statement stmt, List data) {
		this(stmt, data.iterator());
    }

	public MemoryResultSet(Iterator data) {
		this(null, data);
	}
    
	public MemoryResultSet(Statement stmt, Iterator data) {

		this.stmt = stmt;
		this.values = data;

		List columnNames = (List) values.next();

		this.columns = new String[columnNames.size()];

		for (int i = 0; i < this.columns.length; i++) {
			this.columns[i] = ((String) columnNames.get(i)).toLowerCase();
		}

		currentIndex = 0;
	}
	
    public static MemoryResultSet create(ResultSet rs) {

		try {

			List data = toList(rs);
				
			MemoryResultSet res = new MemoryResultSet(rs.getStatement(), data);
				
			res.meta = rs.getMetaData();
				
			return res;

		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
    	
    }

	public static List toList(ResultSet rs) {

		try {

			ResultSetMetaData meta = rs.getMetaData();

			int n = meta.getColumnCount();

			List data = new ArrayList();

			List row = new ArrayList(n);

			for (int i = 0; i < n; i++) {
				row.add(meta.getColumnName(i + 1));
			}

			data.add(row);

			while(rs.next()) {

				row = new ArrayList(n);

				for (int i = 1; i <= n; i++) {
					row.add(rs.getObject(i));
				}

				data.add(row);

			}

			return data;			

		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
    public boolean next() throws SQLException {
        boolean ok = false;

        if (values.hasNext()) {
            row = (List) values.next();
            currentIndex++;
            ok = true;
        }

        return ok;
    }

    public void close() throws SQLException {
    }

    public boolean wasNull() throws SQLException {
        return lastValuetWasNull;
    }

    public String getString(int columnIndex) throws SQLException {
        if (row == null) throw new SQLException("No current row");

        return asString(columnIndex);
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        Object o = row.get(columnIndex - 1);

        if (o instanceof String) {
			String s = (String) o;

            if ("true".equalsIgnoreCase(s)) return true;

            if ("false".equalsIgnoreCase(s)) return false;

			return s.charAt(0) != '0';
        } else {
	        return asLong(columnIndex) != 0;
        }

    }

    public byte getByte(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return 0;

        return Byte.parseByte(s);
    }

    public short getShort(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return 0;

        return Short.parseShort(s);
    }

    public int getInt(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return 0;

        return Integer.parseInt(s);
    }

    public long getLong(int columnIndex) throws SQLException {
        return asLong(columnIndex);
    }

    public float getFloat(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return 0;

        return Float.parseFloat(s);
    }

    public double getDouble(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return 0;

        return Double.parseDouble(s);
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        String value = asString(columnIndex);
        
		return value == null ? null : new BigDecimal(value).setScale(scale, BigDecimal.ROUND_UP);
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return null;

        return s.getBytes();
    }

    public Date getDate(int columnIndex) throws SQLException {

        String s = asString(columnIndex);

        if (s == null) return null;

        try {

	        return Date.valueOf(s);

        } catch (IllegalArgumentException e) {
            // ("getDate, conversion failed : index = " + columnIndex + ", value = " + asString(columnIndex));
            throw e;
        }
    }

    public Time getTime(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return null;

        return Time.valueOf(s);
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        String s = asString(columnIndex);

        if (s == null) return null;

        return Timestamp.valueOf(s);
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return null;
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return null;
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return null;
    }

    public String getString(String columnName) throws SQLException {
        return getString(findColumn(columnName));
    }

    public boolean getBoolean(String columnName) throws SQLException {
        return getBoolean(findColumn(columnName));
    }

    public byte getByte(String columnName) throws SQLException {
        return getByte(findColumn(columnName));
    }

    public int getInt(String columnName) throws SQLException {
        return getInt(findColumn(columnName));
    }

    public long getLong(String columnName) throws SQLException {
        return getLong(findColumn(columnName));
    }

    public float getFloat(String columnName) throws SQLException {
        return getFloat(findColumn(columnName));
    }

    public double getDouble(String columnName) throws SQLException {
        return getDouble(findColumn(columnName));
    }

    public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
        String value = getString(columnName);
		return value == null ? null : new BigDecimal(value).setScale(scale, BigDecimal.ROUND_UP);
    }

    public byte[] getBytes(String columnName) throws SQLException {
        return getBytes(findColumn(columnName));
    }

    public Date getDate(String columnName) throws SQLException {
        return getDate(findColumn(columnName));
    }

    public Time getTime(String columnName) throws SQLException {
        return getTime(findColumn(columnName));
    }

    public Timestamp getTimestamp(String columnName) throws SQLException {
        return getTimestamp(findColumn(columnName));
    }

    public InputStream getAsciiStream(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public InputStream getUnicodeStream(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public InputStream getBinaryStream(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public void clearWarnings() throws SQLException {
    }

    public String getCursorName() throws SQLException {
        return null;
    }

    public ResultSetMetaData getMetaData() throws SQLException {

        if (meta == null) {

            Column[] colmeta = new Column[columns.length];

            for (int i = 0; i < columns.length; i++) {
                colmeta[i] = new Column(null, columns[i], String.class);
            }

            meta = new MemoryResultSetMetaData(colmeta);
        }

        return meta;
    }

    public Object getObject(int columnIndex) throws SQLException {
        return row.get(columnIndex - 1);
    }

    public Object getObject(String columnName) throws SQLException {
        return getObject(findColumn(columnName));
    }

    public int findColumn(String columnName) throws SQLException {
        String name = columnName.toLowerCase();

        for (int i = 0; i < columns.length; i++)
        {
            if (name.equals(columns[i]))
            {
                return i + 1;
            }
        }

        throw new SQLException("Column '" + columnName + "' not found");
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Reader getCharacterStream(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        String value = getString(columnIndex);
		return value == null ? null : new BigDecimal(value);
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        String value = getString(columnName);
		return value == null ? null : new BigDecimal(value);
    }

    public boolean isBeforeFirst() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean isAfterLast() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean isFirst() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean isLast() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void beforeFirst() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void afterLast() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean first() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean last() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getRow() throws SQLException {
        return currentIndex;
    }

    public boolean absolute(int rowNumber) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean relative(int rows) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean previous() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getFetchDirection() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setFetchSize(int rows) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getFetchSize() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getType() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public int getConcurrency() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean rowUpdated() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean rowInserted() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public boolean rowDeleted() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNull(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBytes(int columnIndex, byte x[]) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNull(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBoolean(String columnName, boolean x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateByte(String columnName, byte x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateShort(String columnName, short x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateInt(String columnName, int x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateLong(String columnName, long x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateFloat(String columnName, float x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateDouble(String columnName, double x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateString(String columnName, String x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBytes(String columnName, byte x[]) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateDate(String columnName, Date x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateTime(String columnName, Time x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateObject(String columnName, Object x, int scale) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateObject(String columnName, Object x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void insertRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void deleteRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void refreshRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void cancelRowUpdates() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void moveToInsertRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void moveToCurrentRow() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Statement getStatement() throws SQLException {
        return stmt;
    }

    public Ref getRef(int i) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Blob getBlob(int i) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Clob getClob(int i) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Array getArray(int i) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Ref getRef(String colName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Blob getBlob(String colName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Clob getClob(String colName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Array getArray(String colName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public short getShort(String columnName) throws SQLException {
        return getShort(findColumn(columnName));
    }

    public Object getObject(int i, Map map) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Object getObject(String colName, Map map) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return getDate(columnIndex);
    }

    public Date getDate(String columnName, Calendar cal) throws SQLException {
        return getDate(columnName);
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return getTime(columnIndex);
    }

    public Time getTime(String columnName, Calendar cal) throws SQLException {
        return getTime(columnName);
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return getTimestamp(columnIndex);
    }

    public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
        return getTimestamp(columnName);
    }

    private String asString(int columnIndex) throws SQLException {

        Object s = row.get(columnIndex - 1);

        lastValuetWasNull = s == null;

        return s == null ? null : s.toString();

    }

    private long asLong(int columnIndex) throws SQLException {

        Object o = row.get(columnIndex - 1);

        lastValuetWasNull = o == null;

        if (lastValuetWasNull) return 0;

        if (o instanceof String) {
			return Long.parseLong((String)o);
        } else if (o instanceof Integer) {
            return ((Integer) o).longValue();
        } else if (o instanceof Long) {
            return ((Long) o).longValue();
        }

        return 0;
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
