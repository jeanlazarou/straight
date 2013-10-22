/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

import java.sql.SQLException;

import com.ap.straight.Column;

public class ColumnCondition implements Condition {

	public ColumnCondition(Column c1, Comparator comp, Column c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.comp = comp;
	}
	
	public boolean isConstant() {
		return false;
	}
	
	public boolean isTrue() {
		throw new ConditionException("Invalid method call");
	}

	public boolean accept(int rowIndex) {
		try {
			return comp.accept(c1.get(rowIndex), c2.get(rowIndex));
		} catch (SQLException e) {
			throw new ConditionException(e);
		}
	}

	Column c1; 
	Column c2;
	
	Comparator comp;
}
