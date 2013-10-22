/*
 * @author Jean Lazarou
 * Date: June 19, 2004
 */
package com.ap.straight.sql;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConditionIterator implements Iterator {
	
	public ConditionIterator(String where) {
		
		this.where = where;
		this.lowered = where.toLowerCase();
		
		locateNext();
		
	}

	public boolean hasNext() {
		return hasNext;
	}

	public Object next() {
		
		if (!hasNext) {
			throw new NoSuchElementException();
		}
		
		String next = value.trim();
		
		locateNext();
		
		return next;
		
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	private void locateNext() {
		
		hasNext = true;
		
		pos += value.length();
		
		int i = -1;
		
		int iOr = lowered.indexOf("or", pos);
		int iAnd = lowered.indexOf("and", pos);
		
		if (iOr != -1 && iAnd != -1) 
			i = iOr < iAnd ? iOr : iAnd;
		else if (iOr != -1)
			i = iOr;
		else if (iAnd != -1)
			i = iAnd;
		
		if (i == -1) {
			
			hasNext = pos < where.length();
			value = where.substring(pos);

			return;
			
		} else if (i == pos) {
			value = i == iOr ? "or" : "and";
			hasNext = true;
			return;
		}
		
		value = where.substring(pos, i);
		
		if (value.trim().length() == 0) {
			locateNext();
		}
		
	}

	int pos;
	String where;
	String lowered;
	boolean hasNext;
	String value = "";
}
