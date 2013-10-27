/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.sql.*;

import java.util.Vector;
import java.util.StringTokenizer;

import com.ap.straight.sql.Parser;
import com.ap.straight.sql.WhereParser;

import com.ap.straight.condition.Condition;
import com.ap.straight.condition.ConditionFactory;

import com.ap.straight.unsupported.AbstractStatement;

public class HashStatement extends AbstractStatement
{
    HashConnection conn;

    int maxRows = 0;
    int queryTimeout = 0;
    int columnSizeLimit = 0;

    int fetchSize = 0;

    ResultSet rs;

    int updateCount = -2;

    protected HashStatement(HashConnection conn)
    {
        this.conn = conn;
    }

    public ResultSet executeQuery(String sql) throws SQLException
    {
        sql = sql.trim();

        String uc_sql = sql.toUpperCase();

        if (!uc_sql.startsWith("SELECT "))
        {
            throw new SQLException("SQL statement was not a SELECT statement");
        }

        selectStatement(sql, uc_sql);

        return rs;
    }

    public int executeUpdate(String sql) throws SQLException
    {
        sql = sql.trim();

        String uc_sql = sql.toUpperCase();

        if (!uc_sql.startsWith("INSERT ") && !uc_sql.startsWith("UPDATE ") && !uc_sql.startsWith("DELETE "))
        {
            throw new SQLException("Only INSERT, UPDATE and DELETE statements are supported:" + sql);
        }

        if (uc_sql.startsWith("INSERT "))
        {
            insertStatement(sql, uc_sql);
        }
        else if (uc_sql.startsWith("DELETE "))
        {
            deleteStatement(sql, uc_sql);
        }
        else
        {
            updateStatement(sql, uc_sql);
        }

        return updateCount;
        
    }

    public void close() throws SQLException
    {
    }

    public int getMaxFieldSize() throws SQLException
    {
        return columnSizeLimit;
    }

    public void setMaxFieldSize(int max) throws SQLException
    {
        this.columnSizeLimit = max;
    }

    public int getMaxRows() throws SQLException
    {
        return maxRows;
    }

    public void setMaxRows(int max) throws SQLException
    {
        this.maxRows = max;
    }

    public void setEscapeProcessing(boolean enable) throws SQLException
    {
    }

    public int getQueryTimeout() throws SQLException
    {
        return queryTimeout;
    }

    public void setQueryTimeout(int seconds) throws SQLException
    {
        this.queryTimeout = seconds;
    }

    public void cancel() throws SQLException
    {
    }

    public SQLWarning getWarnings() throws SQLException
    {
        return null;
    }

    public void clearWarnings() throws SQLException
    {
    }

    public void setCursorName(String name) throws SQLException
    {
    }

    public boolean execute(String sql) throws SQLException
    {
    	if (sql.length() > 7) {
    		
    		String op = sql.substring(0, 7).toUpperCase();
    		
			if ("INSERT ".equals(op) || "UPDATE ".equals(op) || "DELETE ".equals(op)) {
			
				executeUpdate(sql);

				return false;
			
			} else if ("SELECT ".equals(op)) {
			
				executeQuery(sql);

				return true;
			
			}
    	}

        throw new SQLException("Only SELECT, INSERT, DELETE and UPDATE statements are supported:" + sql);
    }

    public ResultSet getResultSet() throws SQLException
    {
        if (updateCount != -1) return null;

        return rs;
    }

    public int getUpdateCount() throws SQLException
    {
        if (updateCount == -2) throw new SQLException("Nothing was executes");

        return updateCount;
    }

    public boolean getMoreResults() throws SQLException
    {
        return false;
    }

    public void setFetchDirection(int direction) throws SQLException
    {
    }

    public int getFetchDirection() throws SQLException
    {
        return ResultSet.FETCH_FORWARD;
    }

    public void setFetchSize(int rows) throws SQLException
    {
        this.fetchSize = rows;
    }

    public int getFetchSize() throws SQLException
    {
        return fetchSize;
    }

    public int getResultSetConcurrency() throws SQLException
    {
        return ResultSet.CONCUR_READ_ONLY;
    }

    public int getResultSetType() throws SQLException
    {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

    public void addBatch(String sql) throws SQLException
    {
        throw new UnsupportedOperationException("Statement.addBatch");
    }

    public void clearBatch() throws SQLException
    {
        throw new UnsupportedOperationException("Statement.clearBatch");
    }

    public int[] executeBatch() throws SQLException
    {
        throw new UnsupportedOperationException("Statement.executeBatch");
    }

    public Connection getConnection() throws SQLException
    {
        return conn;
    }

    private void selectStatement(String sql, String uc_sql) throws SQLException
    {
        String fields[];
        boolean isCount = false;

        sql = sql.substring("SELECT ".length()).trim();
        uc_sql = uc_sql.substring("SELECT ".length()).trim();

        int i = uc_sql.indexOf("FROM ");

        if (i == -1)
        {
            throw new SQLException("SELECT statement syntax error, missing FROM keyword");
        }

        if (uc_sql.startsWith("COUNT"))
        {
            isCount = true;

            int po = uc_sql.indexOf('(');
            int pc = uc_sql.indexOf(')');

            if (po < pc && pc < i && uc_sql.substring(5, po).trim().length() == 0)
            {
                if (uc_sql.substring(pc + 1, i).trim().length() != 0)
                {
                    throw new SQLException("In the SELECT statements with COUNT, only COUNT(*) and COUNT(file name) are supported");
                }

                fields = new String[1];
                fields[0] = uc_sql.substring(po + 1, pc).trim();
            }
            else
            {
                throw new SQLException("SELECT statement syntax error near COUNT");
            }
        }
        else if (uc_sql.startsWith("*"))
        {
            fields = new String[1];
            fields[0] = "*";
        }
        else
        {
            StringTokenizer tk = new StringTokenizer(uc_sql.substring(0, i).trim(), ",");

            fields = new String[tk.countTokens()];

            for (int k = 0; k < fields.length; k++)
            {
                fields[k] = tk.nextToken().trim();

                if (fields[k].indexOf(" AS ") != -1)
                {
                    throw new SQLException("In the SELECT statements AS keyword is not supported");
                }
                else if (fields[k].indexOf(' ') != -1)
                {
                    throw new SQLException("SELECT statement syntax error unexpected token " + fields[k].substring(fields[k].indexOf(' ')));
                }
            }
        }

        sql = sql.substring(i + "FROM ".length());
        uc_sql = uc_sql.substring(i + "FROM ".length());

        i = uc_sql.indexOf("WHERE");

        String tableName;

        if (i != -1)
        {
            tableName = uc_sql.substring(0, i).trim();
        }
        else
        {
            tableName = uc_sql.substring(0).trim();
        }

        HashTable table = conn.db.get(tableName);

        if (table == null)
        {
            throw new SQLException("SELECT statement error, unknown table " + tableName + ", in database : " + conn.db.getName());
        }

        if (tableName.indexOf(',') != -1)
        {
            throw new SQLException("SELECT statements with table JOIN are not implemented yet");
        }
        else if (tableName.indexOf(' ') != -1)
        {
            throw new SQLException("SELECT statements with table JOIN aliasing is not implemented yet");
        }

        if (uc_sql.indexOf(" ORDER ") != -1)
        {
            throw new SQLException("SELECT statements with ORDER BY directives are not implemented yet");
        }

        if (uc_sql.indexOf(" GROUP ") != -1 )
        {
            throw new SQLException("SELECT statements with GROUP BY directives are not supported");
        }

		Condition condition = null;
		
		if (i != -1) {
			
			String where = sql.substring(i + "WHERE ".length()).trim();
			
			WhereParser parser = new WhereParser(where, table);
			
			condition = parser.extractCondition();
			
		} else {
			condition = ConditionFactory.getTrueCondition();
		}
		
        if (isCount)
            rs = table.selectCount(this, fields, condition);
        else
            rs = table.select(this, fields, condition);

        updateCount = -1;
        
    }

    private void updateStatement(String sql, String uc_sql) throws SQLException
    {
        sql = sql.substring("UPDATE ".length()).trim();
        updateCount = 1;
    }

    private void insertStatement(String sql, String uc_sql) throws SQLException
    {
        sql = sql.substring("INSERT ".length()).trim();
        uc_sql = uc_sql.substring("INSERT ".length()).trim();

        if (!uc_sql.startsWith("INTO "))
        {
            throw new SQLException("INSERT statement syntax error, expected INTO after INSERT (INSERT " + sql + ")");
        }

        sql = sql.substring("INTO ".length()).trim();
        uc_sql = uc_sql.substring("INTO ".length()).trim();

        int i = uc_sql.indexOf(' ');

        if (i == -1)
        {
            throw new SQLException("INSERT statement syntax error, table name missing after INTO");
        }

        String tableName = uc_sql.substring(0, i);

        HashTable table = conn.db.get(tableName);

        if (table == null)
        {
            throw new SQLException("INSERT statement error, unknown table " + tableName + ", in database : " + conn.db.getName());
        }

        sql = sql.substring(i + 1).trim();
        uc_sql = uc_sql.substring(i + 1).trim();

        i = uc_sql.indexOf("VALUES");

        if (i == -1)
        {
            throw new SQLException("INSERT statement syntax error, missing VALUES keyword");
        }

        sql = sql.substring("VALUES".length()).trim();
        uc_sql = uc_sql.substring("VALUES".length()).trim();

        i = uc_sql.indexOf('(');

        if (i != 0)
        {
            throw new SQLException("INSERT statement syntax error, missing ( after VALUES");
        }

        int end = uc_sql.indexOf(')');

        if (end == -1)
        {
            throw new SQLException("INSERT statement syntax error, missing closing )");
        }

        if (end + 1 != uc_sql.length())
        {
            throw new SQLException("INSERT statement syntax error, trailing characters after )");
        }

        Vector values = Parser.extractInsertValues(sql.substring(i + 1, end), table.getColumnCount());

        table.addRow (values);

        updateCount = 1;
        
    }

    private void deleteStatement(String sqlIn, String uc_sql) throws SQLException
    {
    	int i = "DELETE ".length();
    	
        uc_sql = uc_sql.substring(i + "FROM ".length());

        i = uc_sql.indexOf("WHERE");

        String tableName;

        if (i != -1)
        {
            tableName = uc_sql.substring(0, i).trim();
        }
        else
        {
            tableName = uc_sql.substring(0).trim();
        }

        HashTable table = conn.db.get(tableName);

        if (table == null)
        {
            throw new SQLException("DELETE statement error, unknown table " + tableName + ", in database : " + conn.db.getName() + "\nWhile executing:" + sqlIn);
        }

		Condition condition = null;
		
		if (i != -1) {
			
			String where = uc_sql.substring(i + "WHERE ".length()).trim();
			
			WhereParser parser = new WhereParser(where, table);
			
			condition = parser.extractCondition();
			
		} else {
			condition = ConditionFactory.getTrueCondition();
		}
		
		updateCount = table.delete(condition);
        
    }

    //----------------------- interface Wrapper ----------------------------------------

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

}
