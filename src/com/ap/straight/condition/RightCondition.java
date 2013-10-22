/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

import java.sql.SQLException;

import com.ap.straight.Column;

public class RightCondition implements Condition {

	public RightCondition(Column c1, Comparator comp, Object v2) {
		this.c1 = c1;
		this.v2 = v2;
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
			return comp.accept(c1.get(rowIndex), v2);
		} catch (SQLException e) {
			throw new ConditionException(e);
		}
	}

	Column c1;
	Object v2; 
	
	Comparator comp;
}
