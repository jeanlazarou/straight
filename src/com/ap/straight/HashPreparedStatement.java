/*
 * @author: Jean Lazarou
 * @date: 2 mars 04
 */
package com.ap.straight;

import java.sql.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ap.straight.types.Types;
import com.ap.straight.unsupported.AbstractPreparedStatement;

public class HashPreparedStatement extends AbstractPreparedStatement {

	public HashPreparedStatement(HashConnection conn, String sql) {
		super(conn);
		
		this.sql = sql;
	}

	public ResultSet executeQuery() throws SQLException {
		return super.executeQuery(prepareStatement());
	}

	public int executeUpdate() throws SQLException {
		return super.executeUpdate(prepareStatement());
	}

	public boolean execute() throws SQLException {
		return super.execute(prepareStatement());
	}

    public void clearParameters() throws SQLException {
		values.clear();
	}

	private String prepareStatement() throws SQLException {
		
		StringBuffer buffer = new StringBuffer();

		int index = 0;
		
		int prev = 0;
		int param = sql.indexOf('?');
		
		while (param != -1) {
			
			buffer.append(sql.substring(prev, param));
			
			if (index >= values.size()) {
				throw new SQLException("Parameter " + (index+1) + " not set, sql is <" + sql + ">" );
			}
			
			Object value = values.get(index++);
			
			if (value instanceof String) {
				buffer.append('\'');						
				buffer.append(value);						
				buffer.append('\'');						
			} else {

				String sval = Types.getConverter(value.getClass()).reverse(value);
				
				if (value instanceof Date) {
					buffer.append('\'');
					buffer.append(sval);
					buffer.append('\'');
				} else {
					buffer.append(sval);
				}

			}
			
			prev = param + 1;
			param = sql.indexOf('?', prev);
		}
		
		if (prev < sql.length()) {
			buffer.append(sql.substring(prev));
		}
		
		if (index != values.size()) {
			throw new SQLException("Number of parameters set is " + values.size() + " but expecting " + index);
		}
		
		return buffer.toString();
	}

	private void register(int parameterIndex, Object value) {
		
		parameterIndex--;
		
		for (int i = values.size(); i <= parameterIndex; i++) {
			values.add(null);
		}
		
		values.set(parameterIndex, value);
		
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		register(parameterIndex, x ? Boolean.TRUE : Boolean.FALSE);
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		register(parameterIndex, new Integer(x));
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
		register(parameterIndex, new Integer(x));
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		register(parameterIndex, new Integer(x));
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		register(parameterIndex, new Long(x));
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		register(parameterIndex, new Float(x));
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		register(parameterIndex, new Double(x));
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		register(parameterIndex, x);
	}

	public void setString(int parameterIndex, String x) throws SQLException {
		register(parameterIndex, x);
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {		
		register(parameterIndex, new String(x));
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		register(parameterIndex, x);
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
		register(parameterIndex, x);
	}

	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		register(parameterIndex, x);
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
		register(parameterIndex, x);
	}

	String sql;
	List values = new ArrayList();
	
}
