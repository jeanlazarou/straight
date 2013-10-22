/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

import com.ap.straight.Column;

public class ConditionFactory {

	public static Condition getTrueCondition() {
		
		if (trueCondition == null) {
			trueCondition = new Condition() {

				public boolean isConstant() {
					return true;
				}

				public boolean isTrue() {
					return true;
				}

				public boolean accept(int rowIndex) {
					return true;
				}
				
			};
		}
		
		return trueCondition;
		
	}
	
	public static Condition createInstance(Class type, Column c1, String op, Column c2) {
		return new ColumnCondition(c1, Comparator.getComparator(op, type), c2);
	}

	public static Condition createInstance(Class type, Column c1, String op, Object v2) {
		return new RightCondition(c1, Comparator.getComparator(op, type), v2);
	}

	public static Condition createInstance(Class type, Object v1, String op, Column c2) {
		return new LeftCondition(v1, Comparator.getComparator(op, type), c2);
	}

	public static Condition createInstance(Object value1, String op, Object value2) {
		return new ConstantCondition(value1, Comparator.getComparator(op, value1.getClass()), value2);
	}
	
	protected ConditionFactory() {
	}

	private static Condition trueCondition;	
}
