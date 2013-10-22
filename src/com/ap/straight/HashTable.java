/*
 * @author Jean Lazarou
 * Date: Apr 27, 2002
 */
package com.ap.straight;

import java.util.HashMap;
import java.util.Vector;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ap.straight.condition.*;
import com.ap.straight.condition.Condition;
import com.ap.straight.types.Types;

public class HashTable
{
    String columnName;
    Vector all = new Vector();
    Vector columns = new Vector();
    HashMap columnsMap = new HashMap();

    public String getName()
    {
        return columnName;
    }

    public void setName(String name)
    {
        this.columnName = name;
    }

    public void addColumn(String name, Class type) throws SQLException
    {
        if (Types.supports(type))
        {
            throw new SQLException("Unsupported column type " + type.toString());
        }

        Column col = new Column(this, name, type);

        columns.add(col);
        columnsMap.put(name.toLowerCase(), col);
    }

	public void addRow(Vector values) throws SQLException
    {
        int n = values.size();

        if (columns.size() > n)
        {
            throw new SQLException("Too many values to insert in " + columnName);
        }

        int addedIndex = -1;

        for (int i = 0; i < n; i++)
        {
            Column col = (Column) columns.elementAt(i);

            col.add(values.get(i));

            addedIndex = col.size() - 1;
        }

        if (addedIndex != -1 ) all.add(new Integer(addedIndex));
    }

    public void deleteRow(int index) throws SQLException
    {

    	for (int i = 0; i < columns.size(); i++)
        {
            Column col = (Column) columns.elementAt(i);

            col.values.remove(index);

        }
        
        all.remove(index);
        
    }

    public Column getColumn(String name) throws SQLException
    {
        return (Column) columnsMap.get(name.toLowerCase());
    }

    public int getColumnCount()
    {
        return columnsMap.size();
    }
    
	public Condition createCondition(String[] cond) throws SQLException {
		return createCondition(cond[0], cond[1], cond[2]);
	}

	public Condition createCondition(String subject, String operator, String predicate) throws SQLException {

		String sa = null, sb = null;

		if (subject.startsWith("'") && subject.endsWith("'"))
		{
			sa = subject.substring(1, subject.length() - 1);
		}

		if (predicate.startsWith("'") && predicate.endsWith("'"))
		{
			sb = predicate.substring(1, predicate.length() - 1);
		}

		if (sa != null && sb != null)
		{
			return ConditionFactory.createInstance(sa, operator, sb);
		}

		Object oa = null, ob = null;
		Column ca = null, cb = null;

		if (sa == null)	
		{	
			ca = (Column) columnsMap.get(subject.toLowerCase());

			if (ca == null)	{
				oa = Types.bestConvert(subject);
			}
			
		}
		else
		{
			oa = sa;
		}

		if (sb == null)
		{		
			cb = (Column) columnsMap.get(predicate.toLowerCase());

			if (cb == null)	{
				ob = Types.bestConvert(predicate);
			}
		}
		else
		{
			ob = sb;
		}

		if (oa != null && ob != null)
		{
			if (!oa.getClass().isAssignableFrom(ob.getClass())) {
				throw new SQLException("Incompatible type in operands, compare: " + subject + " and " + predicate);
			}
			
			return ConditionFactory.createInstance(oa, operator, ob);
		}
		
		if (ca != null && cb != null)
		{
			if (ca.type != cb.type)
			{
				throw new SQLException("Columns " + ca.name + " and " + cb.name + " are not of same type");
			}

			return ConditionFactory.createInstance(ca.type, ca, operator, cb);
		}

		if (oa != null)
		{
			if (!Types.areCompatible(oa.getClass(), cb.type)) {
				
				try {
					oa = Types.getConverter(cb.type).convert((String) oa);
				} catch (Exception e) {
					throw new SQLException("Incompatible type in operands, compare: " + oa + " and type " + cb.type + ", column " + cb.getColumnName());
				}
				
			}
			
			return ConditionFactory.createInstance(cb.type, oa, operator, cb);
		}

		if (ob != null)
		{
			if (!Types.areCompatible(ob.getClass(), ca.type)) {
				
				try {
					ob = Types.getConverter(ca.type).convert((String) ob);
				} catch (Exception e) {
					throw new SQLException("Incompatible type in operands, compare: " + ob + " and type " + ca.type + ", column " + ca.getColumnName());
				}
				
			}
			
			return ConditionFactory.createInstance(ca.type, ca, operator, ob);
		}

		return null;
	}
	
    public ResultSet selectCount(Statement stmt, String fields[], Condition conditions) throws SQLException
    {
        Column askedFields[] = new Column[1];

        askedFields[0] = new Column(null, "count(*)", Integer.class);

        return search(conditions, stmt, askedFields, true);
    }

    public ResultSet select(Statement stmt, String fields[], Condition conditions) throws SQLException
    {
        Column askedFields[];

        if (fields[0].length() == 1 && fields[0].charAt(0) == '*')
        {
            int n = columns.size();

            askedFields = new Column [n];

            for (int i = 0; i < n; i++)
            {
                askedFields[i] = (Column) columns.elementAt(i);
            }
        }
        else
        {
            String cname;

            askedFields = new Column [fields.length];

            for (int i = 0; i < fields.length; i++)
            {
                cname = fields[i].toLowerCase();

                if (columnsMap.containsKey(cname))
                {
                    askedFields[i] = (Column) columnsMap.get(cname);
                }
                else
                {
                    throw new SQLException("Column " + fields[i] + " does not exist in table " + columnName);
                }
            }
        }

        return search(conditions, stmt, askedFields, false);
    }

	public int delete(Condition condition) throws SQLException 
	{
		
		int count = 0;
		
		if (condition.isConstant())
		{
			if (condition.isTrue()) 
			{
				int n = ((Column) columns.elementAt(0)).size();
				
				for (int i = n - 1; i >= 0; i--)
				{
					if (condition.accept(i))
					{
						count++;
						deleteRow(i);
					}
				}
				
				return count;
				
			}
			else
			{
				return 0;
			}
		}

		int n = ((Column) columns.elementAt(0)).size();

		if (n == 0)
		{
			return 0;
		}

		for (int i = n - 1; i >= 0; i--)
		{
			if (condition.accept(i))
			{
				count++;
				deleteRow(i);
			}
		}
		
		return count;
		
	}
	
	private HashResultSet search(Condition condition, Statement stmt, Column[] askedFields, boolean count) throws SQLException
	{
		Vector res = new Vector();

		if (count)
		{
			res.add(new Integer(0));
		}

		if (condition.isConstant())
		{
			if (condition.isTrue()) 
			{
				if (count)
				{
					askedFields[0].add(new Integer(all.size()));
	
					return new HashResultSet(stmt, res.iterator(), this, askedFields);
				}
				else
				{
					return new HashResultSet(stmt, all.iterator(), this, askedFields);
				}
			}
			else
			{
				if (count)
				{
					askedFields[0].add(new Integer(0));
				}

				return new HashResultSet(stmt, res.iterator(), this, askedFields);
			}
		}

		int n = ((Column) columns.elementAt(0)).size();

		if (n == 0)
		{
			if (count)
			{
				askedFields[0].add(new Integer(0));
			}

			return new HashResultSet(stmt, res.iterator(), this, askedFields);
		}

		if (count)
		{
			int nbResults = 0;

			for (int i = 0; i < n; i++)
			{
				if (condition.accept(i))
				{
					nbResults++;
				}
			}

			askedFields[0].add(new Integer(nbResults));
		}
		else
		{
			for (int i = 0; i < n; i++)
			{
				if (condition.accept(i))
				{
					res.add(new Integer(i));
				}
			}
		}

		return new HashResultSet(stmt, res.iterator(), this, askedFields);

	}
	
}
