/*
 * @author: Jean Lazarou
 * @date: 1 mars 04
 */
package com.ap.straight;

public class SQL {

	/**
	 * Transforms the given SQL script to a canonical form (removes all unecessary spaces and
	 * convert every character to its lower case)
	 * 
	 * @param sql the sql string to transform
	 * @return the canonical form of the sql 
	 */
	public static String normalize(String sql) {
		boolean skip = true;

		StringBuffer result = new StringBuffer(sql.length());

		char[] buffer = sql.toCharArray();

		for (int i = 0; i < buffer.length; i++) {
			
			char c = buffer[i];

			if (c == '\'') {
				
				result.append(c);
				
				for (i++; i < buffer.length; i++) {
					
					c = buffer[i];
					
					result.append(c);
					
					if (c == '\'') {
						if (i+1 == buffer.length || buffer[i+1] != '\'') {
							break;
						}

						i++;
						result.append(c);
					} 
					
				}
				
				skip = false;
				
			} else if (!Character.isWhitespace(c)) {
				
				if (isSeparator(c)) {
					if (result.length() > 0 && Character.isWhitespace(result.charAt(result.length() - 1))) {
						result.setLength(result.length() - 1);
					}
				}

				result.append(Character.toLowerCase(c));

				skip = isSeparator(c);
				
			} else if (!skip) {
				result.append(' ');

				skip = true;
			}
		}

		if (Character.isWhitespace(result.charAt(result.length() - 1))) {
			result.setLength(result.length() - 1);
		}

		return result.toString();
	}

	static boolean isSeparator(char c) {
		return c == ',' || c == '(' || c == ')' || c == '=' || c == '>' || c == '<' || c == '+' || c == '-';
	}

}
