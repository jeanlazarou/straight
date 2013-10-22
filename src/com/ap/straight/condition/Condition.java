/*
 * @author Jean Lazarou
 * Date: June 16, 2004
 */
package com.ap.straight.condition;

public interface Condition {
	
	boolean isConstant();
	boolean isTrue();
	
	boolean accept(int rowIndex);
	
}
