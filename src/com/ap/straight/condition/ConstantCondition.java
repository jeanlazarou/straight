/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

public class ConstantCondition implements Condition {

	Boolean result;
	
	public ConstantCondition(Object value1, Comparator comp, Object value2) {
		if (comp.accept(value1, value2)) {
			result = Boolean.TRUE;
		} else {
			result = Boolean.FALSE;
		}
	}
	
	public boolean isConstant() {
		return true;
	}
	
	public boolean isTrue() {
		return result.booleanValue() == true;
	}

	public boolean accept(int rowIndex) {
		return result.booleanValue();
	}

}
