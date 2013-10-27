package com.ap.straight.unsupported;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Map;

public abstract class AbstractResultSet implements ResultSet {

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Reader getCharacterStream(String columnName) throws SQLException {
        throw new UnsupportedOperationException();
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

    public Object getObject(int i, Map map) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Object getObject(String colName, Map map) throws SQLException {
        throw new UnsupportedOperationException();
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


    public Ref getRef(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public Blob getBlob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public Clob getClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public Array getArray(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public RowId getRowId(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public RowId getRowId(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException();
    }


    public boolean isClosed() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public String getNString(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public String getNString(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }


    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
