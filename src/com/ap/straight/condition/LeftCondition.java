/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

import java.sql.SQLException;

import com.ap.straight.Column;

public class LeftCondition implements Condition {

	public LeftCondition(Object v1, Comparator comp, Column c2) {
		this.v1 = v1;
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
			return comp.accept(v1, c2.get(rowIndex));
		} catch (SQLException e) {
			throw new ConditionException(e);
		}
	}

	Object v1; 
	Column c2;
	
	Comparator comp;
}
