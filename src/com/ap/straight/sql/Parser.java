/*
 * @author Jean Lazarou
 * Date: June 19, 2004
 */
package com.ap.straight.sql;

import java.sql.SQLException;
import java.util.Vector;

public class Parser {
	
	public static String[] splitCondition(String s) throws SQLException
	{
		char c;

		StringBuffer buffer = new StringBuffer();

		String res[] = new String[3];

		int part = 0;

		for (int i = 0; i < s.length();)
		{
			c = s.charAt(i);

			if (c == '\'' || c == '"')
			{
				if (part == 1)
				{
					throw new SQLException("Syntax error expected comparison operator");
				}

				i = extractStringValue(i, s, c, buffer);

				res[part++] = "'" + buffer.toString() + "'";

				buffer.setLength(0);
			}
			else if (Character.isWhitespace(c))
			{
				if (buffer.length() > 0)
				{
					if (part == 1)
					{
						throw new SQLException("Syntax error expected comparison operator");
					}

					res[part++] = buffer.toString();

					buffer.setLength(0);
				}
			}
			else if (c == '>' && i + 1 < s.length() && s.charAt(i + 1) == '=')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				i++;
				res[part++] = ">=";
			}
			else if (c == '<' && i + 1 < s.length() && s.charAt(i + 1) == '=')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				i++;
				res[part++] = "<=";
			}
			else if (c == '<' && i + 1 < s.length() && s.charAt(i + 1) == '>')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				i++;
				res[part++] = "<>";
			}
			else if (c == '=')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				res[part++] = "=";
			}
			else if (c == '<')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				res[part++] = "<";
			}
			else if (c == '>')
			{
				if (part != 1)
				{
					throw new SQLException("Syntax error unexpected operator " + c);
				}

				res[part++] = ">";
			}
			else
			{
				buffer.append(c);
			}

			i++;

			if (part > 2)
			{
				if (i < s.length() && s.substring(i).trim().length() != 0)
				{
					throw new SQLException("Syntax error unexpected caracters '" + s.substring(i) + "'");
				}
			}
		}

		if (buffer.length() > 0)
		{
			if (part == 1)
			{
				throw new SQLException("Syntax error expected comparison operator");
			}

			res[part++] = buffer.toString();

			buffer.setLength(0);
		}

		if (part != 3)
		{
			throw new SQLException("Syntax error in the WHERE clause");
		}

		return res;
	}


	public static Vector extractInsertValues(String s, int maxValues) throws SQLException
	{
		char c;

		boolean consumeDigits = false;
		boolean spacesInDigits = false;

		StringBuffer buffer = new StringBuffer();

		Vector values = new Vector(maxValues);

		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);

			if (c == '\'' || c == '"')
			{
				if (buffer.length() != 0)
				{
					throw new SQLException("Missing value in " + s);
				}

				i = extractStringValue(i, s, c, buffer);

				values.addElement(buffer.toString());

				buffer.setLength(0);

				consumeDigits = false;
			}
			else if (Character.isWhitespace(c))
			{
				if (consumeDigits && buffer.length() != 0) spacesInDigits = true;
			}
			else if (c == ',')
			{
				if (consumeDigits && buffer.length() == 0)
				{
					throw new SQLException("INSERT statement syntax error, missing value in " + s);
				}

				values.addElement(buffer.toString());

				buffer.setLength(0);
			}
			else if (Character.isDigit(c) || c == '.')
			{
				if (spacesInDigits)
				{
					throw new SQLException("INSERT statement syntax error, missing separator ',', in " + s);
				}

				spacesInDigits = false;
				consumeDigits = true;
				buffer.append(c);
			}
			else
			{
				throw new SQLException("INSERT statement syntax error, unexpected symbol '" + c + "', in " + s);
			}
		}

		if (consumeDigits)
		{
			if (buffer.length() == 0)
			{
				throw new SQLException("INSERT statement syntax error, missing value in " + s);
			}

			values.addElement(buffer.toString());

			buffer.setLength(0);
		}

		if (values.size() == 0)
		{
			throw new SQLException("INSERT statement syntax error, no value to insert");
		}
		else if (values.size() > maxValues)
		{
			throw new SQLException("INSERT statement syntax error, too many values");
		}
		else if (values.size() < maxValues)
		{
			throw new SQLException("INSERT statement syntax error, too few values");
		}

		return values;
		
	}

	private static int extractStringValue(int i, String s, char c, StringBuffer buffer) throws SQLException
	{
		boolean closed = false;

		char x;

		for (i++; i < s.length(); i++)
		{
			x = s.charAt(i);

			if (x == c)
			{
				if (i + 1 == s.length() || s.charAt(i + 1) != c)
				{
					i++;
					closed = true;
					break;
				}
				else
				{
					i++;
					buffer.append(x);
				}
			}
			else
			{
				buffer.append(x);
			}
		}

		if (!closed)
		{
			throw new SQLException("Unclosed string in " + s);
		}

		return i;
	}
}
